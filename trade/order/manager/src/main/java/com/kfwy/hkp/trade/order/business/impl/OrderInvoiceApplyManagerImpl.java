package com.kfwy.hkp.trade.order.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.utils.ValidationResult;
import com.gniuu.framework.utils.ValidationUtils;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.trade.order.business.IOrderInvoiceApplyManager;
import com.kfwy.hkp.trade.order.dao.IOrderInvoiceApplyDbDao;
import com.kfwy.hkp.trade.order.entity.OrderInvoiceApplyEntity;
import com.kfwy.hkp.trade.order.enums.InvoiceApplyStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 订单开票申请manager实现
 */
@Service
public class OrderInvoiceApplyManagerImpl extends AbstractManager<OrderInvoiceApplyEntity> implements IOrderInvoiceApplyManager {
    @Override
    protected IMyBatisBaseDao<OrderInvoiceApplyEntity, Long> getMyBatisDao() {
        return this.orderInvoiceApplyDbDao;
    }

    @Autowired
    private IOrderInvoiceApplyDbDao orderInvoiceApplyDbDao;
    @Autowired
    private IDeptManager deptManager;


    @Override
    protected void beforeCreate(OrderInvoiceApplyEntity e) {
        e.setOrderInvoiceCode(CodeGenUtils.generate("OIA"));
        e.setApplyStatus(InvoiceApplyStatus.Handle);
    }

    @Override
    public void createApply(OrderInvoiceApplyEntity orderInvoiceApplyEntity) {
        checkCreateIsNull(orderInvoiceApplyEntity);
        fillCreate(orderInvoiceApplyEntity);
        this.create(orderInvoiceApplyEntity);
    }


    /**
     * 判断orderInvoiceApplyEntity部分值是否为空
     * @param orderInvoiceApplyEntity
     */
    private void checkCreateIsNull(OrderInvoiceApplyEntity orderInvoiceApplyEntity){

        ValidationResult result = ValidationUtils.validate(orderInvoiceApplyEntity,
                "orderCode","creditCode","invoiceTaxRate","invoiceTotalPrice",
                "consigneeName","consigneeAddress","consigneePhone","invoiceType");
        if (result.getSuccess()){
            if(StringUtils.isEmpty(orderInvoiceApplyEntity.getCpyName())){
                throw new RemoveStackBusinessException("公司名称为空");
            }
        }else{
            throw new RemoveStackBusinessException(result.getMessage());
        }
    }

        /**
     * 填充准备创建的订单开票申请实体
     * @param orderInvoiceApplyEntity
     */
    private void fillCreate(OrderInvoiceApplyEntity orderInvoiceApplyEntity){
        Date date = new Date();
        UserEntity userEntity =(UserEntity) CurrentContext.getUserInfo();
        DeptEntity deptEntity = deptManager.findOne("deptCode",userEntity.getOwnerDeptCode());
        DeptEntity parentDept = deptManager.findOne("deptCode",deptEntity.getParentCode());
        orderInvoiceApplyEntity.setCpyCode(orderInvoiceApplyEntity.getCpyName());
        orderInvoiceApplyEntity.setCreateCode(userEntity.getUserCode());
        orderInvoiceApplyEntity.setCreateName(userEntity.getUserName());
        orderInvoiceApplyEntity.setApplyCode(userEntity.getUserCode());
        orderInvoiceApplyEntity.setApplyName(userEntity.getUserName());
        orderInvoiceApplyEntity.setLastUpdateCode(userEntity.getUserCode());
        orderInvoiceApplyEntity.setLastUpdateName(userEntity.getUserName());
        orderInvoiceApplyEntity.setDeptCode(userEntity.getOwnerDeptCode());
        orderInvoiceApplyEntity.setDeptName(userEntity.getOwnerDeptName());
        orderInvoiceApplyEntity.setParentDeptCode(parentDept.getDeptCode());
        orderInvoiceApplyEntity.setParentDeptName(parentDept.getDeptName());
        orderInvoiceApplyEntity.setIsDeleted(Boolean.FALSE);
        orderInvoiceApplyEntity.setCreateTime(date);
        orderInvoiceApplyEntity.setLastUpdateTime(date);
        orderInvoiceApplyEntity.setInvoiceTime(date);
    }

}
