package com.kfwy.hkp.controller.sys.area;

import cn.hutool.core.collection.CollectionUtil;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.annotion.IgnoreLog;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.controller.sys.area.vo.CompanyAreaServiceRequest;
import com.kfwy.hkp.controller.sys.area.vo.CompanyAreaServiceResponse;
import com.kfwy.hkp.sys.area.business.ICompanyAreaManager;
import com.kfwy.hkp.sys.area.dictionary.AreaStatus;
import com.kfwy.hkp.sys.area.entity.AreaAllEntity;
import com.kfwy.hkp.sys.area.entity.CompanyAreaEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author baiye
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/7/3 09:27
 */
@RestController
@RequestMapping(path = "/cpyArea")
public class CompanyAreaService extends SpringRestService {

    @Autowired
    private ICompanyAreaManager companyAreaManager;

    private final static int[] LI_SEC_POS_VALUE = { 1601, 1637, 1833, 2078, 2274,
            2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858,
            4027, 4086, 4390, 4558, 4684, 4925, 5249, 5590 };
    private final static String[] LI_FIRST_LETTER = { "a", "b", "c", "d", "e",
            "f", "g", "h", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "w", "x", "y", "z" };

    @RequestMapping(value = "/queryNoPage", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody CompanyAreaServiceRequest request) {

        CompanyAreaServiceResponse response = new CompanyAreaServiceResponse();


        //查询省市区，考虑到没有分页的情况，不能查询很多数据
        if (request.getEntity() == null
                || (StringUtils.isEmpty(request.getEntity().getAreaCode())
                && StringUtils.isEmpty(request.getEntity().getParentCode())
                && request.getEntity().getLevel() == null)
                || (request.getEntity().getLevel() != null
                && request.getEntity().getLevel() > 2
                && StringUtils.isEmpty(request.getEntity().getAreaCode())
                && StringUtils.isEmpty(request.getEntity().getParentCode()))
                ) {

            return this.error(response,"此接口最多只支持查询不分页的不指定父级编码的区以上的数据");


        }

        Map<String,Object> param = new HashMap<String,Object>(16);

        if(!StringUtils.isEmpty(request.getEntity().getAreaCode())){
            param.put("areaCode",request.getEntity().getAreaCode());

            response.setResult(companyAreaManager.findAreaByCode(request.getEntity().getAreaCode()));
            return this.success(response);

        }
        if(!StringUtils.isEmpty(request.getEntity().getParentCode())){
            param.put("parentCode",request.getEntity().getParentCode());
        }
        if(!StringUtils.isEmpty(request.getEntity().getLevel())){
            param.put("level",request.getEntity().getLevel());
        }
        param.put("cpyCode", CurrentContext.getCpyCode());
        param.put("status", AreaStatus.ENABLE);


        List<CompanyAreaEntity> list = companyAreaManager.findByMap(param,"sort",true);



        response.setResult(list);

        return this.success(response);
    }


    /**
     * 获取全部省市区街道
     * @return
     */
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryAll(@RequestBody CompanyAreaServiceRequest request) {
        CompanyAreaServiceResponse response = new CompanyAreaServiceResponse();
        //先把所有的省查出来
        List<AreaAllEntity> areaList = getAreaAllEntity("0");
        for(AreaAllEntity areaAllEntity : areaList){
            List<AreaAllEntity> areaList2 = getAreaAllEntity(getAreaCode(areaAllEntity.getAreaCode()));
            areaAllEntity.setCities(areaList2);
            for(AreaAllEntity areaAllEntity2 : areaList2){
                List<AreaAllEntity> areaList3 = getAreaAllEntity(getAreaCode(areaAllEntity2.getAreaCode()));
                areaAllEntity2.setCities(areaList3);
            }
        }
        response.setResult(areaList);
        return this.success(response);
    }
    /**
     * 获取省市区areaCode 一开始areaCode是code+name 这里主要是是分开
     * @param areaCode
     * @return
     */
    private String getAreaCode(String areaCode){

        //将name与code分开
        String [] areaCodeName = areaCode.split(",");
        return  areaCodeName[0];
    }

    /**
     * 获取全部省市区街道
     * @return
     */
    @RequestMapping(value = "/queryAllCascader", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryAllCascader(@RequestBody CompanyAreaServiceRequest request) {
        CompanyAreaServiceResponse response = new CompanyAreaServiceResponse();
        //先把所有的省查出来
        List<AreaAllEntity> areaList = companyAreaManager.getCascaderArea();
        response.setResult(areaList);
        return this.success(response);
    }



    /**
     * 主要是根据parentCode获取子节点  eg:根据省areaCode获取其省的市信息
     * @param parentCode
     * @return
     */
    private List<AreaAllEntity> getAreaAllEntity(String parentCode){

        Map<String,Object> param = new HashMap<String,Object>(16);
        param.put("parentCode",parentCode);
        param.put("status", AreaStatus.ENABLE);
        param.put("cpyCode", CurrentContext.getCpyCode());
        List<CompanyAreaEntity> list = this.companyAreaManager.findByMap(param,"sort",true);
        List<AreaAllEntity> areaList = new ArrayList<>();

        for (CompanyAreaEntity a : list){
            AreaAllEntity areaAllEntity = new AreaAllEntity();
            //将省市区name与code都放入code里 便于在前端传输给后台
            areaAllEntity.setAreaCode(a.getAreaCode()+","+a.getName());
            areaAllEntity.setName(a.getName());
            areaList.add(areaAllEntity);
        }

        return areaList;
    }

    @RequestMapping(value = "/queryByChName", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryByChName(@RequestBody CompanyAreaServiceRequest request) {

        CompanyAreaServiceResponse response = new CompanyAreaServiceResponse();
        List<CompanyAreaEntity> totalList = new ArrayList<CompanyAreaEntity>();

        String provinceName = request.getProvinceName();
        String cityName = request.getCityName();
        String regionName = request.getRegionName();
        String streetName = request.getStreetName();

        if(cityName==null){throw new RemoveStackBusinessException ("市cityName需要传!");}
        if(cityName!=null){
            Map<String,Object> cityMap = new HashMap<String,Object>();
            cityMap.put("name", cityName);
            cityMap.put("level",2);
            List<CompanyAreaEntity> cityList = companyAreaManager.findByMap(cityMap,"sort",true);

            if(cityList!=null){
                if(cityList.size()==0){
                    throw new RemoveStackBusinessException("该市未激活:"+cityName);
                }
                if(cityList.size()>0){
                    totalList.add(cityList.get(0));
                    CompanyAreaEntity provinceEntity = companyAreaManager.findAreaByCode(cityList.get(0).getParentCode());
                    totalList.add(provinceEntity);
                }
            }
        }

        if(regionName!=null){
            if(cityName==null){throw new RemoveStackBusinessException("传了区regionName,市cityName需要传!");}
            Map<String,Object> regionMap = new HashMap<String,Object>();
            regionMap.put("name", regionName);
            regionMap.put("level",3);
            List<CompanyAreaEntity> regionList = companyAreaManager.findByMap(regionMap,"sort",true);

            if(regionList!=null){
                if(regionList.size()==0){
                    throw new RemoveStackBusinessException("该区未激活:"+regionName);
                }
                if(regionList.size()>0){
                    totalList.add(regionList.get(0));
                }
            }
        }


        if(streetName!=null){
            if(regionName==null){throw new RemoveStackBusinessException("传了街道streetName,区regionName需要传!");}
            if(cityName==null){throw new RemoveStackBusinessException("传了街道streetName,市cityName需要传!");}
            Map<String,Object> streetMap = new HashMap<String,Object>();
            streetMap.put("name", streetName);
            streetMap.put("level",4);
            List<CompanyAreaEntity> streetList = companyAreaManager.findByMap(streetMap,"sort",true);

            if(streetList!=null){
                if(streetList.size()>0){
                    totalList.add(streetList.get(0));
                }
            }
        }


        response.setResult(totalList);

        return this.success(response);
    }

    @RequestMapping(value = "/queryByLetterBook", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryByLetterBook(@RequestBody CompanyAreaServiceRequest request) {

        CompanyAreaServiceResponse response = new CompanyAreaServiceResponse();
        Map<String, Object> map = new TreeMap<String, Object>();
        List<CompanyAreaEntity> totalList = new ArrayList<CompanyAreaEntity>();

        request.setStart(0);
        request.setPageSize(1000);
        if (request.getStart() == null && request.getPageSize() == null){
            throw new RemoveStackBusinessException("此方法需要传入分页参数");
        }

        Map<String,Object> cityMap = new HashMap<String,Object>();
        cityMap.put("level",2);
        PageResult<CompanyAreaEntity> resultPageResult = companyAreaManager.findByMap(cityMap, request.getStart(), request.getPageSize());
        List<CompanyAreaEntity> A=new ArrayList();
        List<CompanyAreaEntity> B=new ArrayList();
        List<CompanyAreaEntity> C=new ArrayList();
        List<CompanyAreaEntity> D=new ArrayList();
        List<CompanyAreaEntity> E=new ArrayList();
        List<CompanyAreaEntity> F=new ArrayList();
        List<CompanyAreaEntity> G=new ArrayList();
        List<CompanyAreaEntity> H=new ArrayList();
        List<CompanyAreaEntity> I=new ArrayList();
        List<CompanyAreaEntity> J=new ArrayList();
        List<CompanyAreaEntity> K=new ArrayList();
        List<CompanyAreaEntity> L=new ArrayList();
        List<CompanyAreaEntity> M=new ArrayList();
        List<CompanyAreaEntity> N=new ArrayList();
        List<CompanyAreaEntity> O=new ArrayList();
        List<CompanyAreaEntity> P=new ArrayList();
        List<CompanyAreaEntity> Q=new ArrayList();
        List<CompanyAreaEntity> R=new ArrayList();
        List<CompanyAreaEntity> S=new ArrayList();
        List<CompanyAreaEntity> T=new ArrayList();
        List<CompanyAreaEntity> U=new ArrayList();
        List<CompanyAreaEntity> V=new ArrayList();
        List<CompanyAreaEntity> W=new ArrayList();
        List<CompanyAreaEntity> X=new ArrayList();
        List<CompanyAreaEntity> Y=new ArrayList();
        List<CompanyAreaEntity> Z=new ArrayList();
        if(resultPageResult.getData()!=null) {
            for (int i = 0; i < resultPageResult.getData().size(); i++) {
                if ("a".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    A.add(resultPageResult.getData().get(i));
                } else if ("b".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    B.add(resultPageResult.getData().get(i));
                } else if ("c".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    C.add(resultPageResult.getData().get(i));
                } else if ("d".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    D.add(resultPageResult.getData().get(i));
                } else if ("e".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    E.add(resultPageResult.getData().get(i));
                } else if ("f".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    F.add(resultPageResult.getData().get(i));
                } else if ("g".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    G.add(resultPageResult.getData().get(i));
                } else if ("h".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    H.add(resultPageResult.getData().get(i));
                } else if ("i".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    I.add(resultPageResult.getData().get(i));
                } else if ("j".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    J.add(resultPageResult.getData().get(i));
                } else if ("k".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    K.add(resultPageResult.getData().get(i));
                } else if ("l".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    L.add(resultPageResult.getData().get(i));
                } else if ("m".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    M.add(resultPageResult.getData().get(i));
                } else if ("n".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    N.add(resultPageResult.getData().get(i));
                } else if ("o".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    O.add(resultPageResult.getData().get(i));
                } else if ("p".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    P.add(resultPageResult.getData().get(i));
                } else if ("q".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    Q.add(resultPageResult.getData().get(i));
                } else if ("r".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    R.add(resultPageResult.getData().get(i));
                } else if ("s".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    S.add(resultPageResult.getData().get(i));
                } else if ("t".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    T.add(resultPageResult.getData().get(i));
                } else if ("u".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    U.add(resultPageResult.getData().get(i));
                } else if ("v".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    V.add(resultPageResult.getData().get(i));
                } else if ("w".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    W.add(resultPageResult.getData().get(i));
                } else if ("x".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    X.add(resultPageResult.getData().get(i));
                } else if ("y".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    Y.add(resultPageResult.getData().get(i));
                } else if ("z".equalsIgnoreCase(getFirstLetter(resultPageResult.getData().get(i).getName()))) {
                    Z.add(resultPageResult.getData().get(i));
                }
            }
        }
        if(CollectionUtil.isNotEmpty(A)){
            map.put("A",A);
        }
        if(CollectionUtil.isNotEmpty(B)){
            map.put("B",B);
        }
        if(CollectionUtil.isNotEmpty(C)){
            map.put("C",C);
        }
        if(CollectionUtil.isNotEmpty(D)){
            map.put("D",D);
        }
        if(CollectionUtil.isNotEmpty(E)){
            map.put("E",E);
        }
        if(CollectionUtil.isNotEmpty(F)){
            map.put("F",F);
        }
        if(CollectionUtil.isNotEmpty(G)){
            map.put("G",G);
        }
        if(CollectionUtil.isNotEmpty(H)){
            map.put("H",H);
        }
        if(CollectionUtil.isNotEmpty(I)){
            map.put("I",I);
        }
        if(CollectionUtil.isNotEmpty(J)){
            map.put("J",J);
        }
        if(CollectionUtil.isNotEmpty(K)){
            map.put("K",K);
        }
        if(CollectionUtil.isNotEmpty(L)){
            map.put("L",L);
        }
        if(CollectionUtil.isNotEmpty(M)){
            map.put("M",M);
        }


        if(CollectionUtil.isNotEmpty(N)){
            map.put("N",N);
        }
        if(CollectionUtil.isNotEmpty(O)){
            map.put("O",O);
        }
        if(CollectionUtil.isNotEmpty(P)){
            map.put("P",P);
        }
        if(CollectionUtil.isNotEmpty(Q)){
            map.put("Q",Q);
        }
        if(CollectionUtil.isNotEmpty(R)){
            map.put("R",R);
        }
        if(CollectionUtil.isNotEmpty(S)){
            map.put("S",S);
        }
        if(CollectionUtil.isNotEmpty(T)){
            map.put("T",T);
        }
        if(CollectionUtil.isNotEmpty(U)){
            map.put("U",U);
        }
        if(CollectionUtil.isNotEmpty(V)){
            map.put("V",V);
        }
        if(CollectionUtil.isNotEmpty(W)){
            map.put("W",W);
        }
        if(CollectionUtil.isNotEmpty(X)){
            map.put("X",X);
        }
        if(CollectionUtil.isNotEmpty(Y)){
            map.put("Y",Y);
        }
        if(CollectionUtil.isNotEmpty(Z)){
            map.put("Z",Z);
        }


        response.setResult(map);
        return this.success(response);
    }


    public String getFirstLetter(String chinese) {
        if (chinese == null || chinese.trim().length() == 0) {
            return "";
        }
        chinese = this.conversionStr(chinese, "GB2312", "ISO8859-1");
        // 判断是不是汉字
        if (chinese.length() > 1)
        {
            // 汉字区码
            int li_SectorCode = (int) chinese.charAt(0);
            // 汉字位码
            int li_PositionCode = (int) chinese.charAt(1);
            li_SectorCode = li_SectorCode - 160;
            li_PositionCode = li_PositionCode - 160;
            // 汉字区位码
            int li_SecPosCode = li_SectorCode * 100 + li_PositionCode;
            if (li_SecPosCode > 1600 && li_SecPosCode < 5590) {
                for (int i = 0; i < 23; i++) {
                    if (li_SecPosCode >= LI_SEC_POS_VALUE[i]
                            && li_SecPosCode < LI_SEC_POS_VALUE[i + 1]) {
                        chinese = LI_FIRST_LETTER[i];
                        break;
                    }
                }
                // 非汉字字符,如图形符号或ASCII码
            } else
            {
                chinese = this.conversionStr(chinese, "ISO8859-1", "GB2312");
                chinese = chinese.substring(0, 1);
            }
        }
        return chinese;
    }

    private String conversionStr(String str, String charsetName,String toCharsetName) {
        try {
            str = new String(str.getBytes(charsetName), toCharsetName);
        } catch (UnsupportedEncodingException ex) {
            System.out.println("字符串编码转换异常：" + ex.getMessage());
        }
        return str;
    }
}
