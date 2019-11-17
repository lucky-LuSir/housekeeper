package com.kfwy.hkp.controller.union.cooperate;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.union.cooperate.vo.*;
import com.kfwy.hkp.cooperate.business.ICooperateManager;
import com.kfwy.hkp.cooperate.entity.CooperateEntity;
import com.kfwy.hkp.cooperate.entity.EvaluateEntity;
import com.kfwy.hkp.cooperate.enums.CooStatus;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.kfwy.hkp.common.utils.ParamUtil;

import java.util.*;


/**
 * Created by zjp on 2018/8/16.
 */
@Api(description = "合作功能管理")
@RestController
@RequestMapping(path = "/cooperate")
public class CooperateService extends SpringRestService {

    @Autowired
    private ICooperateManager cooperateManager;

    /**
     * 新增合作:老版本不用
     * @param request
     * @return
     */
    @Deprecated
    @RequestMapping(path = "/createNoUse",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody CooperateServiceRequest request) throws IllegalAccessException {

        CooperateServiceResponse response = new CooperateServiceResponse();
        // 根据业务需求对相应字段校验--start
//        CooperateEntity coopOne = new CooperateEntity();
//        coopOne.setCusCode(request.getEntity().getCusCode());
//        List<String> list = new ArrayList();
//        list.add("cusCode");
//        ParamUtil<CooperateEntity> paramUtil = new ParamUtil<>();
//        paramUtil.check(coopOne,list);
//        // 根据业务需求对相应字段校验--end
//
//        cooperateManager.create(request.getEntity());
        return this.success(response);
    }

    /**
     * 新增合作客户
     * @param request
     * @return
     */
    @RequestMapping(path = "/createcus",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> createcus(@RequestBody CooperateServiceRequest request) throws IllegalAccessException {

        CooperateServiceResponse response = new CooperateServiceResponse();
        // 根据业务需求对相应字段校验--start
        CooperateEntity coopOne = new CooperateEntity();
        coopOne.setCusCode(request.getEntity().getCusCode());
        coopOne.setReceiveCode(request.getEntity().getReceiveCode());
        coopOne.setDividePercentage(request.getEntity().getDividePercentage());
        List<String> list = new ArrayList();
        list.add("receiveCode");
        list.add("cusCode");
        list.add("dividePercentage");
        ParamUtil<CooperateEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(coopOne,list);
        // 根据业务需求对相应字段校验--end

        cooperateManager.createcus(request.getEntity());

        return this.success(response);
    }

    /**
     * 新增合作
     * @param request
     * @return
     */
    @RequestMapping(path = "/createhos",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> createhos(@RequestBody CooperateServiceRequest request) throws IllegalAccessException {

        CooperateServiceResponse response = new CooperateServiceResponse();
        // 根据业务需求对相应字段校验--start
        CooperateEntity coopOne = new CooperateEntity();
        coopOne.setReceiveCode(request.getEntity().getReceiveCode());
        coopOne.setHouseCode(request.getEntity().getHouseCode());
        coopOne.setDividePercentage(request.getEntity().getDividePercentage());
        List<String> list = new ArrayList();
        list.add("receiveCode");
        list.add("houseCode");
        list.add("dividePercentage");
        ParamUtil<CooperateEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(coopOne,list);
        // 根据业务需求对相应字段校验--end

        cooperateManager.createhos(request.getEntity());

        return this.success(response);
    }

    /**
     * 批量新增合作
     * @param request
     * @return
     */
    @RequestMapping(path = "/batchCreate",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> batchCreate(@RequestBody CooperateServiceRequest request) throws IllegalAccessException {

        CooperateServiceResponse response = new CooperateServiceResponse();

        List<CooperateEntity> cooperList = new ArrayList<CooperateEntity>();
        cooperList = request.getList();
        for(int i=0;i<cooperList.size();i++){
            // 根据业务需求对相应字段校验--start
            CooperateEntity one = new CooperateEntity();
            one = cooperList.get(i);
            List<String> list = new ArrayList();
            list.add("cusCode");
            ParamUtil<CooperateEntity> paramUtil = new ParamUtil<>();
            paramUtil.check(one,list);
            // 根据业务需求对相应字段校验--end
        }
        cooperateManager.batchCreate(request.getList());

        return this.success(response);
    }

    /**
     * 查询合作列表
     * @param request
     * @return
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody CooperateServiceRequest request) {

        CooperateServiceResponse response = new CooperateServiceResponse();

        Map param = new HashMap<String, Object>();
        CooperateVO cooperateVO = request.getEntity();

        if (StringUtils.isNotEmpty(cooperateVO.getApplyCode())) { // 我申请的
            param.put("applyCode", CurrentContext.getUserCode());
        }

        if (StringUtils.isNotEmpty(cooperateVO.getReceiveCode())) {// 我收到的
            param.put("receiveCode", CurrentContext.getUserCode());
        }

        String applyCode = cooperateVO.getApplyCode();
        String receiveCode = cooperateVO.getReceiveCode();
        if(StringUtils.isEmpty(applyCode)
                && StringUtils.isEmpty(receiveCode)){
            throw new BusinessException("applyCode和receiveCode不能同时为空.");
        }
        if(StringUtils.isNotEmpty(applyCode)
                && StringUtils.isNotEmpty(receiveCode)){
            throw new BusinessException("applyCode和receiveCode不能2个值同时传.applyCode为:"+applyCode+"--receiveCode为"+receiveCode);
        }
        String applyOrRecei = "init";
        if(StringUtils.isNotEmpty(applyCode)){
            applyOrRecei = "applyInit";
        }
        if(StringUtils.isNotEmpty(receiveCode)){
            applyOrRecei = "receiveInit";
        }

        if (StringUtils.isNotEmpty(cooperateVO.getCooStatus())) {// 合作状态
            param.put("cooStatus", cooperateVO.getCooStatus());
        }

        if (StringUtils.isNotEmpty(cooperateVO.getCusPhone())) { // 客户电话
            param.put("cusPhone", cooperateVO.getCusPhone());
        }

        if (StringUtils.isNotEmpty(cooperateVO.getHouseownerPhone())) { // 业主电话
            param.put("houseownerPhone", cooperateVO.getHouseownerPhone());
        }

        // 街道
        if (StringUtils.isNotEmpty(cooperateVO.getStreet())) {
            param.put("street", cooperateVO.getStreet());
        // 区域
        } else if (StringUtils.isNotEmpty(cooperateVO.getRegion())) {
            param.put("region", cooperateVO.getRegion());
        // 城市
        } else if (StringUtils.isNotEmpty(cooperateVO.getCity())) {
            param.put("city", cooperateVO.getCity());
        // 省份
        } else if (StringUtils.isNotEmpty(cooperateVO.getProvince())) {
            param.put("province", cooperateVO.getProvince());
        }

        if (StringUtils.isNotEmpty(cooperateVO.getDetailAddress())) { // 房源地址
            param.put("detailAddress", cooperateVO.getDetailAddress());
        }

        if (StringUtils.isNotEmpty(cooperateVO.getHouseName())) { // 房源标题
            param.put("houseName", cooperateVO.getHouseName());
        }

        if (StringUtils.isNotEmpty(cooperateVO.getUnionName())) { // 合作人员姓名
            param.put("unionName", cooperateVO.getUnionName());
        }

        if (StringUtils.isNotEmpty(cooperateVO.getUnionPhone())) { // 合作人员电话
            param.put("unionPhone", cooperateVO.getUnionPhone());
        }

        PageResult<CooperateEntity> result = cooperateManager.findByMapCoo(param, request.getStart(), request.getPageSize(), "create_time", false,applyOrRecei);
        response.setResult(result);

        return this.success(response);
    }

    /**
     * 合作详情
     * @param request
     * @return
     */
    @RequestMapping(path = "/detail",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail(@RequestBody CooperateServiceRequest request) {

        CooperateServiceResponse response = new CooperateServiceResponse();
        CooperateEntity cooperateEntity = cooperateManager.detail(request.getEntity().getCooCode());
        String rejectStr = cooperateEntity.getRejectReason();
        String preStr ="感谢您的支持参与\n";
        String sufStr = "\n您可以去我的个人主页中查看我的其它房客资源,进行合作!\n资源共享,合作共赢!";
        if(rejectStr==null){
            rejectStr=" ";
        }
        rejectStr = preStr+rejectStr+sufStr;
        cooperateEntity.setRejectReason(rejectStr);
        response.setResult(cooperateEntity);

        return this.success(response);
    }

    /**
     * 同意合作
     * @param request
     * @return
     */
    @RequestMapping(path = "/agree",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> agree(@RequestBody CooperateServiceRequest request) {

        CooperateServiceResponse response = new CooperateServiceResponse();
        String cooCode = request.getEntity().getCooCode();
        Boolean cooOpenFlag = request.getEntity().getCooOpenFlag();
        CooperateEntity cooperateEntity = cooperateManager.findOne("cooCode", cooCode);
        cooperateEntity.setReceiveCode(CurrentContext.getUserCode());
        cooperateEntity.setCooStatus(CooStatus.ACCEPT);
        cooperateEntity.setCooOpenFlag(cooOpenFlag);
        cooperateManager.update(cooperateEntity);

        return this.success(response);
    }

    /**
     * 拒绝合作
     * @param request
     * @return
     *
     */
    @RequestMapping(path = "/refuse",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> refuse(@RequestBody CooperateServiceRequest request) {

        CooperateServiceResponse response = new CooperateServiceResponse();
        String cooCode = request.getEntity().getCooCode();
        CooperateEntity cooperateEntity = cooperateManager.findOne("cooCode", cooCode);
        cooperateEntity.setReceiveCode(CurrentContext.getUserCode());
        cooperateEntity.setCooStatus(CooStatus.REFUSE);
        cooperateEntity.setRejectReason(request.getEntity().getRejectReason());

        cooperateManager.update(cooperateEntity);

        return this.success(response);
    }

    /**
     * 结束合作
     * @param request
     * @return
     *
     */
    @RequestMapping(path = "/end",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> end(@RequestBody CooperateServiceRequest request) {

        CooperateServiceResponse response = new CooperateServiceResponse();
        String cooCode = request.getEntity().getCooCode();
        CooperateEntity cooperateEntity = cooperateManager.findOne("cooCode", cooCode);
        if (!CurrentContext.getUserCode().equals(cooperateEntity.getApplyCode()) && !CurrentContext.getUserCode().equals(cooperateEntity.getReceiveCode())) {
            return this.success(response);
        }
        Boolean isEnd = cooperateManager.endCoo(cooperateEntity,CurrentContext.getUserCode());
        if (isEnd){
            cooperateEntity.setCooStatus(CooStatus.END);
            cooperateManager.update(cooperateEntity);
        }
        return this.success(response);
    }

    /**
     * 用户合作评价 新增
     * @param request
     * @return
     */
    @RequestMapping(path = "/evaluvteInsert",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> evaluvteInsert(@RequestBody ApplyUserEvaluateRequest request) {
        ApplyUserEvaluateResponse response = new ApplyUserEvaluateResponse();
        this.cooperateManager.evaluvteInsert(request.getEntity());
        return this.success(response);
    }

    /**
     * 用户合作 评价 查询
     * @param request
     * @return
     */
    @RequestMapping(path = "/evaluvteSelect",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> evaluvte(@RequestBody ApplyUserEvaluateRequest request) {
        ApplyUserEvaluateResponse response = new ApplyUserEvaluateResponse();
        PageResult<EvaluateEntity> result = this.cooperateManager.evaluvteSelect(
                request.getEntity().getUserCode(),request.getStart(),request.getPageSize());
        response.setResult(result);
        return this.success(response);
    }

    /**
     * 公开合作中客户or业主号码
     * @param request
     * @return
     */
    @RequestMapping(path="/openCooFlag",
                    method =  RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> openCooFlag(@RequestBody CooperateServiceRequest request) {
        CooperateServiceResponse response = new CooperateServiceResponse();
        String cooCode = request.getEntity().getCooCode();
        CooperateEntity cooperateEntity = cooperateManager.findOne("cooCode", cooCode);
        if (null==cooperateEntity){
            throw new BusinessException("合作不存在");
        }
        if (StringUtils.isNotEmpty(cooperateEntity.getReceiveCode())&&!CurrentContext.getUserCode().equals(cooperateEntity.getReceiveCode())){
            throw new BusinessException("您不是合作接收方，无法执行该操作");
        }else {
            if (!cooperateEntity.getCooOpenFlag()){
                cooperateEntity.setCooOpenFlag(true);
                cooperateManager.update(cooperateEntity);
            }else{
                throw new BusinessException("该合作详情处于公开状态无需重复该操作");
            }
        }

        return this.success(response);
    }

    /**
     * /关闭合作中客户or业主号码
     * @param request
     * @return
     */
    @RequestMapping(path="/closeCooFlag",
            method =  RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> closeCooFlag(@RequestBody CooperateServiceRequest request) {
        CooperateServiceResponse response = new CooperateServiceResponse();
        String cooCode = request.getEntity().getCooCode();
        CooperateEntity cooperateEntity = cooperateManager.findOne("cooCode", cooCode);
        if (null==cooperateEntity){
            throw new BusinessException("合作不存在");
        }
        if (StringUtils.isNotEmpty(cooperateEntity.getReceiveCode())&&!CurrentContext.getUserCode().equals(cooperateEntity.getReceiveCode())){
            throw new BusinessException("您不是合作接收方，无法执行该操作");
        }else {
            if (cooperateEntity.getCooOpenFlag()){
                cooperateEntity.setCooOpenFlag(false);
                cooperateManager.update(cooperateEntity);
            }else{
                throw new BusinessException("该合作详情处于隐藏状态无需重复该操作");
            }
        }

        return this.success(response);
    }
}