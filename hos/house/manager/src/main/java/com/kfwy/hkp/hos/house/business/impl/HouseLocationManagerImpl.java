package com.kfwy.hkp.hos.house.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.exception.RestBusinessException;
import com.gniuu.framework.utils.ValidationResult;
import com.gniuu.framework.utils.ValidationUtils;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.ListUtils;
import com.kfwy.hkp.hos.house.business.IHouseLocationManager;
import com.kfwy.hkp.hos.house.dao.IHouseLocRegionDbDao;
import com.kfwy.hkp.hos.house.dao.IHouseLocationDbDao;
import com.kfwy.hkp.hos.house.entity.HouseLocPolygonEntity;
import com.kfwy.hkp.hos.house.entity.HouseLocRegionEntity;
import com.kfwy.hkp.hos.house.entity.HouseLocationEntity;
import com.kfwy.hkp.sys.area.dao.ICompanyAreaDbDao;
import com.kfwy.hkp.sys.area.entity.CompanyAreaEntity;
import com.kfwy.hkp.sys.file.business.IFileRelationManager;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;
import org.apache.commons.collections4.functors.ExceptionClosure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.*;

/**
 * Create By hjh on 2018/7/31
 */
@Service
public class HouseLocationManagerImpl extends AbstractManager<HouseLocationEntity> implements IHouseLocationManager {

    @Autowired
    private IHouseLocationDbDao houseLocationDbDao;
    @Autowired
    private IFileRelationManager fileRelationManager;
    @Autowired
    private IHouseLocRegionDbDao houseLocRegionDbDao;
    @Autowired
    private ICompanyAreaDbDao companyAreaDbDao;

    @Override
    protected IMyBatisBaseDao<HouseLocationEntity, Long> getMyBatisDao() {
        return this.houseLocationDbDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(HouseLocationEntity hl){
        hl.setLocationCode(CodeGenUtils.generate("loc"));
        OperateFillUtils.fill(hl);
        // 新增文件
        if(null != hl.getFileList()){
            List<FileRelationEntity> list = new ArrayList<>();
            for (FileRelationEntity file :hl.getFileList()){
                OperateFillUtils.fill(file);
                file.setOwnerCode(hl.getLocationCode());
                list.add(file);
            }
            try {
                fileRelationManager.create(list);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (null != hl.getLocRegionList() && hl.getLocRegionList().size()>0){
            //this.createLocRegion(hl);
            System.out.println("---有区域");
        }

        int i = houseLocationDbDao.insert(hl);

        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createLoc(HouseLocRegionEntity leftDown, HouseLocRegionEntity rightUp, HouseLocationEntity hl) {

        hl.setLocationCode(CodeGenUtils.generate("loc"));
        OperateFillUtils.fill(hl);
        // 新增文件
        if(null != hl.getFileList()){
            List<FileRelationEntity> list = new ArrayList<>();
            for (FileRelationEntity file :hl.getFileList()){
                OperateFillUtils.fill(file);
                file.setOwnerCode(hl.getLocationCode());
                list.add(file);
            }
            try {
                fileRelationManager.create(list);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (null != hl.getLocRegionList() && hl.getLocRegionList().size()>0){
            //this.createLocRegion(hl);
            System.out.println("---有区域");
            boolean isRepeat = this.findRepeatRegion(leftDown,rightUp,hl);
            if(isRepeat){
                throw new RemoveStackBusinessException ("重复了,与其他多边形重复");
            }else if(isRepeat==false){
                this.createLocRegion(hl);
            }
        }
        if(hl.getLongitude()==null){throw new RemoveStackBusinessException("异常一:传入中心点经度为空(longitude)");}
        if(hl.getLatitude()==null){throw new RemoveStackBusinessException("异常二:传入中心点纬度为空(latitude)");}

        int i = houseLocationDbDao.insert(hl);

        return i;
    }

    @Override
    public List<HouseLocationEntity> queryLocByHos(Map<String, Object> param) {
        return houseLocationDbDao.selectLocByHos(param);
    }

    @Override
    public void editPointAndRegion(HouseLocRegionEntity leftDown, HouseLocRegionEntity rightUp, HouseLocationEntity hl) {

//        OperateFillUtils.fill(hl);
        hl.setLastUpdateTime(new Date());
        hl.setLastUpdateCode(CurrentContext.getUserInfo().getUserCode());
        //1.1修改hasLocRegion布尔字段为true
        String locCode =hl.getLocationCode();
        BigDecimal longitude=hl.getLongitude();
        BigDecimal latitude=hl.getLatitude();
        if(locCode ==null || locCode.length() ==0){throw new RemoveStackBusinessException("位置编码不可为空locCode");}
        if(longitude ==null){throw new RemoveStackBusinessException("经度不可为空longitude");}
        if(latitude ==null){throw new RemoveStackBusinessException("纬度不可为空latitude");}

        if(locCode !=null && locCode.length() !=0){
//            String preSql = "UPDATE t_hkp_hos_location  set longitude = \' " + longitude + "\',"+"latitude = \' " + latitude + "\',"+
//                    "where location_code = \'" + locCode + "\'";
//            houseLocRegionDbDao.selectByNativeSql(preSql);
            houseLocationDbDao.update(hl);

        }

        if (null != hl.getLocRegionList() && hl.getLocRegionList().size()>0){
            //this.createLocRegion(hl);
            System.out.println("---有区域");
            boolean isRepeat = this.findRepeatRegion(leftDown,rightUp,hl);
            if(isRepeat){
                throw new RemoveStackBusinessException("重复了,与其他多边形重复");
            }else if(isRepeat==false){
                this.createLocRegion(hl);
            }
        }
        //修改点和区域完成
    }

    @Override
    @Transactional
    public int update(HouseLocationEntity hl){
        if (hl.getId() == null && hl.getLocationCode() == null) {
            throw new RestBusinessException("更新房源时数据为空");
        }

        HouseLocationEntity location = this.findOne("locationCode", hl.getLocationCode());
        if(null==location){
            throw new RemoveStackBusinessException("未查到该位置:"+hl.getLocationCode());
        }
        hl.setId(location.getId());
        //v2.8.0位置目前不传图片
        //fileRelationManager.update(hl.getFileList(),hl.getLocationCode(),hl.getLocationCode(),hl.getDetailAddress(),hl.getCreateCode());
        int i = houseLocationDbDao.update(hl);
        return i;
    }

    @Override
    public HouseLocationEntity findOne(Map<String, Object> param) {

        HouseLocationEntity locationEntity = houseLocationDbDao.selectUniqueByMap(param);
        param.clear();
        if(null != locationEntity){
            param.put("ownerCode",locationEntity.getLocationCode());
            List<FileRelationEntity> fileRelationEntities = fileRelationManager.findByMap(param);
            locationEntity.setFileList(fileRelationEntities);

            //查多边形区域
            param.clear();
            param.put("locationCode",locationEntity.getLocationCode());
            List<HouseLocRegionEntity> hlrList = new ArrayList<HouseLocRegionEntity>();
            hlrList = houseLocRegionDbDao.selectByMap(param);
            locationEntity.setLocRegionList(hlrList);
        }
        return locationEntity;
    }

    @Override
    public PageResult<HouseLocationEntity> findByMap(Map<String, Object> param, int start, int pageSize) {
        param.put("isDelete",Boolean.FALSE);
        String orderBy = "create_time";
        return houseLocationDbDao.selectByMap(param,start,pageSize,orderBy,Boolean.FALSE);
    }

    @Override
    public int createLocRegion(HouseLocationEntity hLoc) {

        List<HouseLocRegionEntity> hlrList = hLoc.getLocRegionList();
        int addNumber = 0;
        if(hLoc.getLocationCode()==null){throw new RemoveStackBusinessException("新增区域时,位置编码locationCode不能为空.");}
        if(ListUtils.isEmpty(hlrList)){throw new RemoveStackBusinessException("传入区域不能为空,请画对应区域.");}
        int listCount = hlrList.size();
        if(listCount<=2){throw new RemoveStackBusinessException("新增多边形区域时,多边形的点需要大于等于3");}
        if(null != hlrList){
            List<HouseLocRegionEntity> list = new ArrayList<>();
            String regionBatchCode =CodeGenUtils.generate("region");

            for (HouseLocRegionEntity region :hlrList){
                // 根据业务需求对相应字段校验--start

                ValidationResult vr = ValidationUtils.validate(region,"num","longitude","latitude");
                if (!vr.getSuccess()){
                    throw new RemoveStackBusinessException(vr.getMessage());
                }
                // 根据业务需求对相应字段校验--end
                region.setLocationCode(hLoc.getLocationCode());
                region.setLocationAlias(hLoc.getLocationAlias());
                region.setRegionBatchCode(regionBatchCode);
                OperateFillUtils.fill(region);
                list.add(region);
            }

            //新增前先删除原来区域
            houseLocRegionDbDao.deleteByLocationCode(hLoc.getLocationCode());
            //新增区域
            addNumber =  houseLocRegionDbDao.batchInsert(list);

            //1.1修改hasLocRegion布尔字段为true
            String locCode =hLoc.getLocationCode();
            if(locCode !=null && locCode.length() !=0){
                String preSql = "UPDATE t_hkp_hos_location  set has_loc_region = true " +
                        "where location_code = \'" + locCode + "\'";
                houseLocRegionDbDao.selectByNativeSql(preSql);
            }


        }
        return addNumber;
    }

    @Override
    public List<HouseLocPolygonEntity> findSeeArea(HouseLocRegionEntity leftDown, HouseLocRegionEntity rightUp, String locationCodedbOld) {
        //1.1找出所有的可视区域的点
        String preSql = "select * from t_hkp_hos_location_region " +
                "where longitude < \'" + rightUp.getLongitude() + "\'"+"and longitude > \'" + leftDown.getLongitude() + "\'"
                +"and latitude < \'" + rightUp.getLatitude() + "\'"
                +"and latitude > \'" + leftDown.getLatitude() + "\'";

        List<HouseLocRegionEntity> locRetionListAll = houseLocRegionDbDao.selectByNativeSql(preSql);
        //1.2在1.1基础上找出所有locationCode
        List<String> locCodeList = new ArrayList<String>();
        if(ListUtils.isNotEmpty(locRetionListAll)){
            for(HouseLocRegionEntity locRe : locRetionListAll){
                if(locRe.getLocationCode()!=null){
                    locCodeList.add(locRe.getLocationCode());
                }

            }
        }

        //1.3,在1.2基础上去重locationCode并去除传入的locationCode,然后找出所有的locationCode对应的区域
        Set<String> setList = new HashSet<>();
        setList.addAll(locCodeList);
        setList.remove(locationCodedbOld);
        List<HouseLocPolygonEntity> polygonList = new ArrayList<HouseLocPolygonEntity>();

        List<HouseLocPolygonEntity> polyList = new ArrayList<HouseLocPolygonEntity>();
        int i = 1;
        for(String locCode : setList){
            HouseLocPolygonEntity onePoly = new HouseLocPolygonEntity();
            List<HouseLocRegionEntity> hlrList = new ArrayList<HouseLocRegionEntity>();
            Map<String,Object> param = new HashMap<>();
            param.put("locationCode",locCode);
            HouseLocationEntity hlocEntity = houseLocationDbDao.selectUniqueByMap(param);
            hlrList = houseLocRegionDbDao.selectByMap(param);
            onePoly.setPointRegionList(hlrList);
            onePoly.setPolygonNum(i);
            onePoly.setLocationAlias(hlrList.get(0).getLocationAlias());
            onePoly.setLocationCode(hlrList.get(0).getLocationCode());
            if(hlocEntity!=null){
                onePoly.setLongitude(hlocEntity.getLongitude());//位置删除,位置上的多边形未删除,会出现hlocEntity为空情况
                onePoly.setLatitude(hlocEntity.getLatitude());
            }else{
                onePoly.setLongitude(hlrList.get(0).getLongitude());//位置删除,位置上的多边形未删除,会出现hlocEntity为空情况
                onePoly.setLatitude(hlrList.get(0).getLatitude());
            }

            i++;
            polyList.add(onePoly);

        }

        return polyList;
    }

    @Override
    public List<HouseLocationEntity> findMapLoc(Map<String, Object> map) {
        Map<String, Object> mp = new HashMap<String, Object>();
        mp.put("areaCode", map.get("street"));
        CompanyAreaEntity cpyAreaObj = companyAreaDbDao.selectUniqueByMap(mp);
        Integer level = cpyAreaObj.getLevel();
        Map<String, Object> mpQuery = new HashMap<String, Object>();
        if(level.equals(1)){
            mpQuery.put("province",cpyAreaObj.getAreaCode());
        }if(level.equals(2)){
            mpQuery.put("city",cpyAreaObj.getAreaCode());
        }if(level.equals(3)){
            mpQuery.put("region",cpyAreaObj.getAreaCode());
        }if(level.equals(4)){
            mpQuery.put("street",cpyAreaObj.getAreaCode());
        }

        List<HouseLocationEntity> result = houseLocationDbDao.selectByMap(mpQuery);

        return result;
    }

    @Override
    public boolean findRepeatRegion(HouseLocRegionEntity leftDown, HouseLocRegionEntity rightUp, HouseLocationEntity locationEntityOld) {
        //获取可视区域的多边形集合
        List<HouseLocPolygonEntity> polyDbList = this.findSeeArea(leftDown,rightUp,locationEntityOld.getLocationCode());
        //获取传入的单个多边形
        List<HouseLocRegionEntity> longlyPoly = locationEntityOld.getLocRegionList();
        HouseLocPolygonEntity plyA = new HouseLocPolygonEntity();
        plyA.setRemark("要对比的多边形");
        plyA.setPointRegionList(longlyPoly);
        boolean isRepeat = false;
        //循环判断多边形是否在多边形集合中
        for(int i=0;i<polyDbList.size();i++){
            HouseLocPolygonEntity plyB = polyDbList.get(i);
            isRepeat = mapLapOverlap(plyA,plyB);
            if(isRepeat){
                return isRepeat;
            }
        }



        return isRepeat;
    }



    public boolean mapLapOverlap(HouseLocPolygonEntity plyA, HouseLocPolygonEntity plyB){

        boolean resultFlag = false;
        boolean mapPolyFlag = this.mapLapPolygon(plyA,plyB);
        boolean mapPointFlag = this.mapLapPoint(plyA,plyB);
        resultFlag = mapPolyFlag||mapPointFlag;
        return resultFlag;
    }

    /**
    * @Description 描述:判断两多边形所有线段是否相交
    * @Auth xpp
    * @Date 2019/3/8 16:12
    * @Version 1.0
    * @Param [plyA, plyB]
    * @return boolean
    **/
    public boolean mapLapPolygon(HouseLocPolygonEntity plyA, HouseLocPolygonEntity plyB){
        int il = plyA.getPointRegionList().size();
        int jl = plyB.getPointRegionList().size();
        List<HouseLocRegionEntity> segA = new ArrayList<HouseLocRegionEntity>();
        List<HouseLocRegionEntity> segB = new ArrayList<HouseLocRegionEntity>();
        for(int i=0;i<il;i++){
            for(int j=0;j<jl;j++){
                segA.clear();
                segB.clear();
                segA.add(plyA.getPointRegionList().get(i));
                if(i==(il-1)){
                    segA.add(plyA.getPointRegionList().get(0));
                }else{
                    segA.add(plyA.getPointRegionList().get(i+1));
                }

                segB.add(plyB.getPointRegionList().get(j));
                if(j==(jl-1)){
                    segB.add(plyB.getPointRegionList().get(0));
                }else{
                    segB.add(plyB.getPointRegionList().get(j+1));
                }

                boolean lineFlag = this.mapLapLine(segA,segB);
                if(lineFlag){
                    System.out.println("---线段相交了---");
                    return true;
                }
            }
        }

        return false;
    }

    private boolean mapLapLine(List<HouseLocRegionEntity> segA, List<HouseLocRegionEntity> segB) {

        BigDecimal c_A = (segA.get(0).getLatitude().subtract(segB.get(0).getLatitude())).multiply(segA.get(1).getLongitude().subtract(segB.get(0).getLongitude()));
        BigDecimal c_B = (segA.get(0).getLongitude().subtract(segB.get(0).getLongitude())).multiply(segA.get(1).getLatitude().subtract(segB.get(0).getLatitude()));
        BigDecimal abc_AB = c_A.subtract(c_B);

        BigDecimal d_A = (segA.get(0).getLatitude().subtract(segB.get(1).getLatitude())).multiply(segA.get(1).getLongitude().subtract(segB.get(1).getLongitude()));
        BigDecimal d_B = (segA.get(0).getLongitude().subtract(segB.get(1).getLongitude())).multiply(segA.get(1).getLatitude().subtract(segB.get(1).getLatitude()));
        BigDecimal abd_AB = d_A.subtract(d_B);

        BigDecimal cdTotal = (abc_AB.multiply(abd_AB));
        int r=cdTotal.compareTo(BigDecimal.ZERO); //和0，Zero比较
        if(r==1 || r==0){
            return false;
        } //bdTotal大于等于0

        BigDecimal e_A = (segB.get(0).getLatitude().subtract(segA.get(0).getLatitude())).multiply(segB.get(1).getLongitude().subtract(segA.get(0).getLongitude()));
        BigDecimal e_B = (segB.get(0).getLongitude().subtract(segA.get(0).getLongitude())).multiply(segB.get(1).getLatitude().subtract(segA.get(0).getLatitude()));
        BigDecimal cda_AB = e_A.subtract(e_B);
        BigDecimal cdb = (cda_AB.add(abc_AB)).subtract(abd_AB);
        BigDecimal cdbTotal = cda_AB.multiply(cdb);

        int crt=cdbTotal.compareTo(BigDecimal.ZERO); //和0，Zero比较

        boolean jflag = false;
        jflag = (crt==-1);

        return jflag;
    }


    public boolean mapLapPoint(HouseLocPolygonEntity plyA, HouseLocPolygonEntity plyB){

        //TODO 判断两多边形是否存在点与区域的内包含关系
        boolean flag = false;
        List<HouseLocRegionEntity> pointListA = plyA.getPointRegionList();
        List<HouseLocRegionEntity> pointListB = plyB.getPointRegionList();
        HouseLocRegionEntity houseLocRegion = new HouseLocRegionEntity();
        List<Point2D.Double> plyA2D = new ArrayList<Point2D.Double>();
        for(HouseLocRegionEntity objOne:pointListA){
            plyA2D.add(new Point2D.Double(objOne.getLongitude().doubleValue(),objOne.getLatitude().doubleValue()));
        }

        List<Point2D.Double> plyB2D = new ArrayList<Point2D.Double>();
        for(HouseLocRegionEntity objOne:pointListB){
            plyB2D.add(new Point2D.Double(objOne.getLongitude().doubleValue(),objOne.getLatitude().doubleValue()));
        }

        for(HouseLocRegionEntity enOne:pointListA ){
            Point2D.Double pointA = new Point2D.Double(enOne.getLongitude().doubleValue(),enOne.getLatitude().doubleValue());
            if(IsPtInPoly(pointA, plyB2D)){
                System.out.println("a点在多边形b内");
                flag = true;
            }else{
                System.out.println("a点在多边形b外");
            }
        }

        for(HouseLocRegionEntity enOne:pointListB ){
            Point2D.Double pointB = new Point2D.Double(enOne.getLongitude().doubleValue(),enOne.getLatitude().doubleValue());
            if(IsPtInPoly(pointB, plyA2D)){
                System.out.println("b点在多边形a内");
                flag = true;
            }else{
                System.out.println("b点在多边形a外");
            }
        }






//            Point2D.Double point = new Point2D.Double(116.404072, 39.916605);
//
//        List<Point2D.Double> pts = new ArrayList<Point2D.Double>();
//        pts.add(new Point2D.Double(116.395, 39.910));
//        pts.add(new Point2D.Double(116.394, 39.914));
//        pts.add(new Point2D.Double(116.403, 39.920));
//        pts.add(new Point2D.Double(116.402, 39.914));
//        pts.add(new Point2D.Double(116.410, 39.913));
//
//        if(IsPtInPoly(point, pts)){
//            System.out.println("点在多边形内");
//        }else{
//            System.out.println("点在多边形外");
//        }

        return flag;
    }

    /**
     * 判断点是否在多边形内
     * @param point 检测点
     * @param pts   多边形的顶点
     * @return      点在多边形内返回true,否则返回false
     */
    public static boolean IsPtInPoly(Point2D.Double point, List<Point2D.Double> pts){

        int N = pts.size();
        boolean boundOrVertex = true; //如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
        int intersectCount = 0;//cross points count of x
        double precision = 2e-10; //浮点类型计算时候与0比较时候的容差
        Point2D.Double p1, p2;//neighbour bound vertices
        Point2D.Double p = point; //当前点

        p1 = pts.get(0);//left vertex
        for(int i = 1; i <= N; ++i){//check all rays
            if(p.equals(p1)){
                return boundOrVertex;//p is an vertex
            }

            p2 = pts.get(i % N);//right vertex
            if(p.x < Math.min(p1.x, p2.x) || p.x > Math.max(p1.x, p2.x)){//ray is outside of our interests
                p1 = p2;
                continue;//next ray left point
            }

            if(p.x > Math.min(p1.x, p2.x) && p.x < Math.max(p1.x, p2.x)){//ray is crossing over by the algorithm (common part of)
                if(p.y <= Math.max(p1.y, p2.y)){//x is before of ray
                    if(p1.x == p2.x && p.y >= Math.min(p1.y, p2.y)){//overlies on a horizontal ray
                        return boundOrVertex;
                    }

                    if(p1.y == p2.y){//ray is vertical
                        if(p1.y == p.y){//overlies on a vertical ray
                            return boundOrVertex;
                        }else{//before ray
                            ++intersectCount;
                        }
                    }else{//cross point on the left side
                        double xinters = (p.x - p1.x) * (p2.y - p1.y) / (p2.x - p1.x) + p1.y;//cross point of y
                        if(Math.abs(p.y - xinters) < precision){//overlies on a ray
                            return boundOrVertex;
                        }

                        if(p.y < xinters){//before ray
                            ++intersectCount;
                        }
                    }
                }
            }else{//special case when ray is crossing through the vertex
                if(p.x == p2.x && p.y <= p2.y){//p crossing over p2
                    Point2D.Double p3 = pts.get((i+1) % N); //next vertex
                    if(p.x >= Math.min(p1.x, p3.x) && p.x <= Math.max(p1.x, p3.x)){//p.x lies between p1.x & p3.x
                        ++intersectCount;
                    }else{
                        intersectCount += 2;
                    }
                }
            }
            p1 = p2;//next ray left point
        }

        if(intersectCount % 2 == 0){//偶数在多边形外
            return false;
        } else { //奇数在多边形内
            return true;
        }

    }



}
