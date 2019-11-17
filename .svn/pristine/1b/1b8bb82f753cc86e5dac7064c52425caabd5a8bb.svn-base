package com.kfwy.hkp.controller.crm.customer;

import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.gniuu.framework.utils.ValidateUtils;
import com.gniuu.framework.utils.ValidationResult;
import com.gniuu.framework.utils.ValidationUtils;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.controller.crm.customer.vo.CustomerFollowupServiceRequest;
import com.kfwy.hkp.controller.crm.customer.vo.CustomerFollowupServiceResponse;
import com.kfwy.hkp.controller.crm.customer.vo.CustomerFollowupVo;
import com.kfwy.hkp.crm.customer.business.ICustomerFollowupManager;
import com.kfwy.hkp.crm.customer.business.ICustomerManager;
import com.kfwy.hkp.crm.customer.business.IFocusCusManager;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerFollowupEntity;
import com.kfwy.hkp.crm.customer.entity.FocusCusEntity;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by hjh on 2018/8/23.
 */
@RestController
@RequestMapping (path = "/customerFollowup")
public class CustomerFollowupService extends SpringRestService {

    @Autowired
    private ICustomerFollowupManager customerFollowupManager;

    @Autowired
    private IUserManager userManager;

    @Autowired
    private ICustomerManager customerManager;

    @Autowired
    private IDeptManager deptManager;

    /**
     * 新增跟进
     *
     * @param request
     * @return
     */
    @RequestMapping (path = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create (@RequestBody CustomerFollowupServiceRequest request) throws IllegalAccessException {

        CustomerFollowupServiceResponse response = new CustomerFollowupServiceResponse ();
        ValidationResult vr = ValidationUtils.validate (request.getEntity (), "cusCode");
        if (! vr.getSuccess ()) {
            throw new RemoveStackBusinessException (vr.getMessage ());
        }

        CustomerFollowupEntity entity = request.getEntity ();
        customerFollowupManager.create (entity);
        return this.success (response);
    }


    /**
     * 查询
     *
     * @param request
     * @return
     */
    @RequestMapping (path = "/select", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> select (@RequestBody CustomerFollowupServiceRequest request) {
        CustomerFollowupServiceResponse response = new CustomerFollowupServiceResponse ();
        CustomerFollowupVo followupVo = request.getEntity ();
        if (followupVo == null) {
            followupVo = request.getCustomerFollowupVo ();
        }
        //设置查询条件
        Map param = setSelectCondition (followupVo);
        PageResult<CustomerFollowupEntity> byMap = customerFollowupManager.selectByMap (param, request.getStart (), request.getPageSize ());

        response.setResult (byMap);
        return this.success (response);
    }

    private Map setSelectCondition (CustomerFollowupVo followupVo) {
        Map param = new HashMap<String, Object> (16);
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        if (null != followupVo) {
            if (StringUtils.isNotEmpty (followupVo.getDeptName ())) {
                param.put ("deptName", followupVo.getDeptName ());
            }
            if (StringUtils.isNotEmpty (followupVo.getCreateDeptName ())) {
                param.put ("createDeptName", followupVo.getCreateDeptName ());
            }
            if (StringUtils.isNotEmpty (followupVo.getCreateDeptCode ())) {
                param.put ("createDeptCode", followupVo.getCreateDeptCode ());
            }
            if (StringUtils.isNotEmpty (followupVo.getEmpName ())) {
                param.put ("empName", followupVo.getEmpName ());
            }
            if (StringUtils.isNotEmpty (followupVo.getFollowupType ())) {
                param.put ("followupType", followupVo.getFollowupType ());
            }
            if (StringUtils.isNotEmpty (followupVo.getCusCode ())) {
                param.put ("cusCode", followupVo.getCusCode ());
            }
            //创建时间
            if (null != followupVo.getCreateTimeStart ()) {
                try {
                    param.put ("createTimeStart", DateUtil.beginOfDay (followupVo.getCreateTimeStart ()));
                } catch (Exception e) {
                }
            }
            if (null != followupVo.getCreateTimeEnd ()) {
                try {
                    param.put ("createTimeEnd", DateUtil.endOfDay (followupVo.getCreateTimeEnd ()));
                } catch (Exception e) {
                }
            }
            //个人客户跟进查询
            if (null != followupVo.getCusCode () && ! ("").equals (followupVo.getCusCode ())) {
                CustomerEntity entity = customerManager.detail (followupVo.getCusCode ());

                if (entity.getOpenFlag () == false
                        && ! CurrentContext.getUserCode ().equals (entity.getEmpCode ())) {

                    if (! cur.getEnterType ().equals ("1")
                            && StringUtils.isNotEmpty (cur.getOwnerDeptCode ())) {

                        String leaderCode = deptManager.getDeptEntity (cur.getOwnerDeptCode ()).getLeaderCode ();
                        UserEntity owner = userManager.findUserByUserCode (entity.getEmpCode ());

                        if (leaderCode.equals (cur.getUserCode ())) {
                            List<String> deptCodes = deptManager.getDownDeptCode (cur.getOwnerDeptCode ());
                            if (!cur.getOwnerDeptCode ().equals (owner.getOwnerDeptCode ()) && deptCodes==null && deptCodes.size ()<1){
                                param.put ("notIn", entity.getEmpCode ());
                            }
                        } else {
                            param.put ("notIn", entity.getEmpCode ());
                        }
                    }

                }
            } else {
                getLowerDeptCodes (param, cur);
            }
        }
        //客户跟进增加公司编码筛选条件
        if (null != CurrentContext.getCpyCode () && "" != CurrentContext.getCpyCode ()) {
            param.put ("cpyCode", CurrentContext.getCpyCode ());
        }
        return param;
    }

    private void getLowerDeptCodes (Map param, UserEntity cur) {
        if (! cur.getEnterType ().equals ("1")) {
            if (StringUtils.isNotEmpty (cur.getOwnerDeptCode ())) {
                String leaderCode = deptManager.getDeptEntity (cur.getOwnerDeptCode ()).getLeaderCode ();
                if (leaderCode.equals (cur.getUserCode ())) {
                    List<String> deptCodes = deptManager.getDownDeptCode (cur.getOwnerDeptCode ());
                    if (deptCodes != null && deptCodes.size () >= 1) {
                        param.put ("createDeptCodes", deptCodes);
                    } else {
                        param.put ("createDeptCode", cur.getOwnerDeptCode ());
                    }
                } else {
                    param.put ("createCode", cur.getUserCode ());
                }
            }
        }

    }

    /**
     * 详情
     *
     * @param request
     * @return
     */
    @RequestMapping (path = "/detail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail (@RequestBody CustomerFollowupServiceRequest request) {

        CustomerFollowupServiceResponse response = new CustomerFollowupServiceResponse ();

        Map param = new HashMap<String, Object> ();
        param.put ("followupCode", request.getEntity ().getFollowupCode ());
        param.put ("followupType", request.getEntity ().getFollowupType ());
        CustomerFollowupEntity one = customerFollowupManager.findOne (param);

        response.setResult (one);
        return this.success (response);
    }


}
