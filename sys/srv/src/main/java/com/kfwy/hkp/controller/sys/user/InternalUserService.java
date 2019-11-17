package com.kfwy.hkp.controller.sys.user;


import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.utils.ToolUtil;
import com.kfwy.hkp.controller.sys.user.vo.InternalUserRequest;
import com.kfwy.hkp.controller.sys.user.vo.UserServiceResponse;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.log.manager.IOperationManager;
import com.kfwy.hkp.sys.auth.business.IRoleManager;
import com.kfwy.hkp.sys.user.business.IUserDataManager;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.POST;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by lzp on 2018/11/12.
 * 内部人员信息
 */
@Controller
@RequestMapping("/internalUser")
public class InternalUserService extends SpringRestService {

    @Autowired
    private IUserDataManager dataManager;
    @Autowired
    private IUserManager userManager;

    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private IOperationManager operationManager;

    private final static int[] LI_SEC_POS_VALUE = { 1601, 1637, 1833, 2078, 2274,
            2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858,
            4027, 4086, 4390, 4558, 4684, 4925, 5249, 5590 };
    private final static String[] LI_FIRST_LETTER = { "a", "b", "c", "d", "e",
            "f", "g", "h", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "w", "x", "y", "z" };

    /**
     * 人员列表
     */
    @Autowired
    private IRoleManager roleManager;
    //未被调用
    @RequestMapping(path = "/queryList",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> queryList(@RequestBody InternalUserRequest request) throws IllegalAccessException {
        UserServiceResponse response = new UserServiceResponse();
        Map map = new HashMap();
        map.put("keyword",request.getKeyword());
        //List<UserEntity> result= UserApiClient.findUserList(userApiVo);
        PageResult<UserEntity> result =dataManager.findByMap(map,request.getStart(),request.getPageSize());
        response.setResult(result.getData());
        return this.success(response);
    }

    /**
     * 查询当前时间的有效人员列表
     * @param request
     * @return
     * @throws IllegalAccessException
     */
    @RequestMapping(path = "/queryValid",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> queryValid(@RequestBody InternalUserRequest request) throws IllegalAccessException {

        UserServiceResponse response = new UserServiceResponse();

        if (request.getStart() == null && request.getPageSize() == null){
            throw new BusinessException("此方法需要传入分页参数");
        }
        Map map = new HashMap();
        map.put("keyword",request.getKeyword());
        map.put("quitTimeStart", DateUtil.beginOfMonth(new Date()));
        map.put("entryTimeEnd", DateUtil.endOfDay(new Date()));
        PageResult<UserEntity> result = userManager.selectUserBasisInfoByMap(map,request.getStart(),request.getPageSize());
        response.setResult(result);
        return this.success(response);
    }



    /**
     * 查询人员列表
     * @param request
     * @return
     * @throws IllegalAccessException
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> query(@RequestBody InternalUserRequest request) throws IllegalAccessException {

        UserServiceResponse response = new UserServiceResponse();

        UserEntity userApiVo=request.getEntity();
        if (request.getStart() == null && request.getPageSize() == null){
            throw new BusinessException("此方法需要传入分页参数");
        }
        // PageResult<UserEntity> result=UserApiClient.findUserListPage(userApiVo);
        Map map = new HashMap();
        map.put("keyword",request.getKeyword());
        map.put("userTypes","1");
        PageResult<UserEntity> result = userManager.selectUserBasisInfoByMap(map,request.getStart(),request.getPageSize());
        response.setResult(result);
        return this.success(response);
    }

    /**
     * 查询全部人员接口
     * 传入分页参数即分页
     * 不传入则查询全部
     * isDeleted 不传则查询所有  传false则查询在职
     * @param request
     * @return
     * @throws IllegalAccessException
     */
    @RequestMapping(path = "/queryAll",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> queryAll(@RequestBody InternalUserRequest request) throws IllegalAccessException {

        UserServiceResponse response = new UserServiceResponse();

        UserEntity userApiVo=request.getEntity();

        // PageResult<UserEntity> result=UserApiClient.findUserListPage(userApiVo);
        Map map = new HashMap();
        map.put("keyword",request.getKeyword());
        if (null!=request.getEntity().getIsDeleted()){
            map.put("isDeleted",request.getEntity().getIsDeleted());
        }

        if (null!=request.getStart()&&null!=request.getPageSize()){
            PageResult<UserEntity> result = userManager.selectUserBasisInfoByMap(map,request.getStart(),request.getPageSize());
            response.setResult(result);
        }else {
            List<UserEntity> result = userManager.selectUserBasisInfoByMap(map);
            response.setResult(result);
        }

        return this.success(response);
    }

    @RequestMapping(path = "/queryAddressBook",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> queryAddressBook(@RequestBody InternalUserRequest request) throws IllegalAccessException {

        UserServiceResponse response = new UserServiceResponse();
        if (request.getStart() == null && request.getPageSize() == null){
            throw new BusinessException("此方法需要传入分页参数");
        }
        UserEntity entity = (UserEntity) CurrentContext.getUserInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        if(entity.getUserLevel().equals("K0")){
            Map param = new HashMap();
            param.put("keyword",request.getKeyword());
            param.put("isDeleted",Boolean.FALSE);
            param.put("userType","Employee");
            PageResult<UserEntity> result=null;
            if(deptManager.getHasSeeAddressBook(entity.getOwnerDeptCode())){
                result = userManager.selectUserBasisInfoByMap(param,request.getStart(),request.getPageSize());
            }
            List<UserEntity> A=new ArrayList();
            List<UserEntity> B=new ArrayList();
            List<UserEntity> C=new ArrayList();
            List<UserEntity> D=new ArrayList();
            List<UserEntity> E=new ArrayList();
            List<UserEntity> F=new ArrayList();
            List<UserEntity> G=new ArrayList();
            List<UserEntity> H=new ArrayList();
            List<UserEntity> I=new ArrayList();
            List<UserEntity> J=new ArrayList();
            List<UserEntity> K=new ArrayList();
            List<UserEntity> L=new ArrayList();
            List<UserEntity> M=new ArrayList();
            List<UserEntity> N=new ArrayList();
            List<UserEntity> O=new ArrayList();
            List<UserEntity> P=new ArrayList();
            List<UserEntity> Q=new ArrayList();
            List<UserEntity> R=new ArrayList();
            List<UserEntity> S=new ArrayList();
            List<UserEntity> T=new ArrayList();
            List<UserEntity> U=new ArrayList();
            List<UserEntity> V=new ArrayList();
            List<UserEntity> W=new ArrayList();
            List<UserEntity> X=new ArrayList();
            List<UserEntity> Y=new ArrayList();
            List<UserEntity> Z=new ArrayList();
            if(result!=null) {
                if(result.getData()!=null){
                    for (int i = 0; i < result.getData().size(); i++) {
                        if ("a".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            A.add(result.getData().get(i));
                        } else if ("b".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            B.add(result.getData().get(i));
                        } else if ("c".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            if(!("CEO").equals(result.getData().get(i).getOwnerDeptName())){
                                C.add(result.getData().get(i));
                            }
                        } else if ("d".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            D.add(result.getData().get(i));
                        } else if ("e".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            E.add(result.getData().get(i));
                        } else if ("f".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            F.add(result.getData().get(i));
                        } else if ("g".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            G.add(result.getData().get(i));
                        } else if ("h".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            H.add(result.getData().get(i));
                        } else if ("i".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            I.add(result.getData().get(i));
                        } else if ("j".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            J.add(result.getData().get(i));
                        } else if ("k".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            K.add(result.getData().get(i));
                        } else if ("l".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            L.add(result.getData().get(i));
                        } else if ("m".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            M.add(result.getData().get(i));
                        } else if ("n".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            N.add(result.getData().get(i));
                        } else if ("o".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            O.add(result.getData().get(i));
                        } else if ("p".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            P.add(result.getData().get(i));
                        } else if ("q".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            Q.add(result.getData().get(i));
                        } else if ("r".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            R.add(result.getData().get(i));
                        } else if ("s".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            S.add(result.getData().get(i));
                        } else if ("t".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            T.add(result.getData().get(i));
                        } else if ("u".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            U.add(result.getData().get(i));
                        } else if ("v".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            V.add(result.getData().get(i));
                        } else if ("w".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            W.add(result.getData().get(i));
                        } else if ("x".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            X.add(result.getData().get(i));
                        } else if ("y".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            Y.add(result.getData().get(i));
                        } else if ("z".equalsIgnoreCase(ToolUtil.getFirstLetter(result.getData().get(i).getOwnerDeptName()))) {
                            Z.add(result.getData().get(i));
                        }
                    }
                }
            }
            map.put("A",A);
            map.put("B",B);
            map.put("C",C);
            map.put("D",D);
            map.put("E",E);
            map.put("F",F);
            map.put("G",G);
            map.put("H",H);
            map.put("I",I);
            map.put("J",J);
            map.put("K",K);
            map.put("L",L);
            map.put("M",M);
            map.put("N",N);
            map.put("O",O);
            map.put("P",P);
            map.put("Q",Q);
            map.put("R",R);
            map.put("S",S);
            map.put("T",T);
            map.put("U",U);
            map.put("V",V);
            map.put("W",W);
            map.put("X",X);
            map.put("Y",Y);
            map.put("Z",Z);
            response.setResult(map);
        }else{
            response.setResult(map);
        }
        return this.success(response);
    }

    @POST
    @RequestMapping(path = "/selectUserByDeptAll",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> selectUserByDept(@RequestBody InternalUserRequest request) throws IllegalAccessException{
        // 权限校验
        String userCode=CurrentContext.getUserCode();

        UserServiceResponse response = new UserServiceResponse();
        UserEntity userEntity=request.getEntity();
        userEntity.setUserCode(userCode);
        Map map = new HashMap();
        map.put("ownerDeptCode",userEntity.getOwnerDeptCode());
        map.put("isDeleted",false);
        List<UserEntity> result = userManager.selectUserBasisInfoByMap(map);
        response.setResult(result);
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

//    /**
//     * 這是测试接口
//     * @param request
//     * @return
//     * @throws IllegalAccessException
//     */
//    @POST
//    @RequestMapping(path = "/selectUserName",
//            method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ResponseBody
//    public ResponseEntity<IServiceResponse> selectUserName(@RequestBody InternalUserRequest request) throws IllegalAccessException{
//        // 权限校验
//        UserServiceResponse response = new UserServiceResponse();
//        UserEntity userEntity=request.getEntity();
//        List<UserEntity> result= UserApiClient.findUsersAll(userEntity);
//        response.setResult(result);
//        return this.success(response);
//    }
}
