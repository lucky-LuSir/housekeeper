package com.kfwy.hkp.controller.base.activationCode;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.base.ActivationCodeDbDao;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.SimpleSecurityCodeUtil;
import com.kfwy.hkp.controller.base.activationCode.vo.ActivationCodeRequest;
import com.kfwy.hkp.controller.base.activationCode.vo.ActivationCodeResponse;
import com.kfwy.hkp.controller.base.activationCode.vo.ActivationCodeVo;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author liwensihan
 * @description TODO
 * @date 2019/3/13 16:11
 */
@RestController
@RequestMapping(path = "/activation")
public class ActivationCodeService extends SpringRestService {

    @Autowired
    private ActivationCodeDbDao activationCodeDbDao;

    /**
     * 得到激活码
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/getActivationCode",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> getActivationCode(@RequestBody ActivationCodeRequest request) throws IllegalAccessException {

        ActivationCodeResponse response = new ActivationCodeResponse();
        UserEntity userEntity = (UserEntity)CurrentContext.getUserInfo();
        if (userEntity.isCanGetActivationCode()==false){
            throw new RemoveStackBusinessException ("抱歉,您没有获取激活码的权限!");
        }
        String code = activationCodeDbDao.getActivationCodeByUserCode(CurrentContext.getUserCode());
        if (code!=null){
            response.setResult(code);
            return this.success(response);
        }

        //生成激活码
        String activationCode = SimpleSecurityCodeUtil.random(6) + "";

        activationCodeDbDao.setActvationCode(activationCode);

        if (activationCodeDbDao.getActivationCodeByUserCode(CurrentContext.getUserCode())!=null
                && activationCodeDbDao.getActivationInfoByActivationCode(activationCode)!=null){
            response.setResult(activationCode);
        }else{
            throw new RemoveStackBusinessException("生成激活码失败");
        }

        return this.success(response);
    }


    /**
     * 查询激活码
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/queryActivationCode",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryActivationCode(@RequestBody ActivationCodeRequest request) throws IllegalAccessException {

        ActivationCodeResponse response = new ActivationCodeResponse();
        if (StringUtils.isEmpty(request.getEntity().getActivationCode())){
            throw new RemoveStackBusinessException("激活码为空");
        }
        Map<String,String> map = activationCodeDbDao.getActivationInfoByActivationCode(request.getEntity().getActivationCode());
        if(map==null){
            throw new RemoveStackBusinessException("激活码不存在，或者已经过期！请通知邀请人重新申请激活码");
        }
        System.out.println("激活成功");
        ActivationCodeVo activationCodeVo = new ActivationCodeVo();
        activationCodeVo.setActivationCode(map.get("activationCode"));
        activationCodeVo.setCreateName(map.get("createName"));
        activationCodeVo.setOwnerDeptCode(map.get("ownerDeptCode"));
        activationCodeVo.setOwnerDeptName(map.get("ownerDeptName"));
        response.setResult(activationCodeVo);

        activationCodeDbDao.cleanActivationCode(map);

        if (activationCodeDbDao.getActivationCodeByUserCode(map.get("userCode"))==null
                && activationCodeDbDao.getActivationInfoByActivationCode(map.get("activationCode"))==null){

            return this.success(response,"激活成功");

        }else{
            throw new RemoveStackBusinessException("激活失败");
        }

    }



}
