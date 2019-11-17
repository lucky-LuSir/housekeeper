package com.kfwy.hkp.base;


import com.gniuu.framework.exception.IllegalArgsException;

/**
 * 系统配置信息枚举
 * @author 李文思汗
 * @date 2018/8/28
 */
public enum SystemConfigKey {

    Cus_Contract_Census_Time("cus_contract_census_time", "客户合同周期统计时间间隔"),
    Cus_Followup_Census_Time("cus_followup_census_time", "客户跟进统计时间间隔"),
    Cus_Followup_Record_Time("cus_followup_record_time", "客户跟进报表时间间隔"),
    Cus_Create_Scope_Value("cus_create_scope_value", "创建客户默认公盘或私盘"),
    Hos_Contract_Census_Time("hos_contract_census_time", "房源合同周期统计时间间隔"),
    Hos_Followup_Census_Time("hos_followup_census_time", "房源跟进统计时间间隔"),
    Hos_Followup_Record_Time("hos_followup_record_time", "房源跟进报表时间间隔"),
    Cus_Market_Industry_Value("cus_market_industry_value", "客户行业性质需求面积分析"),
    Cus_Market_Area_Value("cus_market_area_value", "客户需求面积分段"),
    Sms_Send_Every_Time("sms_send_every_time", "短信发送每次数量"),
    Sms_Send_Thread_Start("sms_send_thread_start", "短信线程是否启动"),
    Sms_Send_Channel_Start("sms_send_channel_start", "短信渠道是否启动"),
    Cus_Followup_Rest_Time("cus_followup_rest_time", "客户指定期间未跟进自动变更为公盘客户"),
    Job_Cus_Followup_Run("job_cus_followup_run", "扫描未跟进客户任务启动"),
    Job_Cus_Followup_Company_Codes("job_cus_followup_company_codes", "参与扫描未跟进客户的公司编码"),
    Operation_Look_Log_Type("operation_look_log_type", "查看操作日志类型"),
    Notice_Roll_Box_Time("notice_roll_box_time", "通知滚动支持时间区间"),
    Monthly_Roll_Box_Time("monthly_roll_box_time","月刊滚动支持时间区间"),
    Sms_Send_Customer_Followup("sms_send_customer_followup", "录入客户带看短信发送"),
    Employee_Payroll_Company_Codes("employee_payroll_company_codes", "员工工资核算参与公司编码"),
    Flow_Leave_Approve_Code("flow_leave_approve_code", "请假工作流审批人员编码"),
    Flow_Quit_Approve_Code("flow_quit_approve_code", "离职工作流审批人员编码"),
    PERSONNEL_CODE("personel_code","人事编码"),
    DATA_TRANSFER_CODE("data_transfer_code","数据转移人编码"),
    PORT_TRANSFER_CODE("port_transfer_code","端口转移人编码"),
    FINANCIAL_EXAMINE_CODE("financial_examine_code","财务审批人编码"),
    DATA_TRANSFER_NOTICE_CODE("data_transfer_notice_code","数据转交通知人编码"),
    Hos_Inside_Recommend_Count("hos_inside_recommend_count", "房源内部推荐允许房源数"),
    Look_Business_Data_Alert_Codes("look_business_data_alert_codes", "查看业务资料信息预警通知人员列表"),
    Look_Owner_Business_Data_Limit("look_owner_business_data_limit", "查询业主资料信息达到上限后是否继续查询"),
    Hos_Inside_Recommend_Rest_Time("hos_inside_recommend_rest_time", "房源内部推荐信息超过多少天自动重置"),
    KFWY_ATTENDANCE_APPROVAL_PERSONNEL_EMP_CODE("kfwy_attendance_approval_personnel_emp_code", "考勤工作流人事审核人员"),
    KFWY_CEO_DEPT_CODE("kfwy_ceo_dept_code", "库房无忧CEO部门编码"),
    KFWY_CEO_EMP_CODE("kfwy_ceo_emp_code", "库房无忧CEO人员编码"),
    Emp_Post_High_Level("emp_post_high_level", "高层领导的级别数"),
    Flow_Reimbursement_Payment_Code("flow_reimbursement_payment_code","报销工作流财务已付款工作流审批"),
    Flow_Entry_Approve_Code("flow_entry_approve_code","入职审批人"),
    FLOW_QUIT_PERSONNEL_CODE("flow_quit_personnel_code","离职人事审批人"),
    FLOW_QUIT_DATATRANSFER_CODE("flow_quit_datatransfer_code","离职数据转移人"),
    FLOW_QUIT_PORTTRANSFER_CODE("flow_quit_porttransfer_code","离职端口转移人"),
    FLOW_QUIT_FINANCIALEXAMINE_CODE("flow_quit_financialexamine_code","离职财务审批人"),
    FLOW_QUIT_PAYROLLEXAMINE_CODE("flow_quit_payrollexamine_code","离职发放工资复核人"),
    FLOW_QUIT_PERSONNEL_RECRUITMENT_CODE("flow_quit_personnel_recruitment_code","人事招聘");
    private String code;
    private String name;

    private SystemConfigKey(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static SystemConfigKey getCode(int code) {
        for (SystemConfigKey temp : SystemConfigKey.values()) {
            if (temp.getCode().equals(code)) {
                return temp;
            }
        }
        throw new IllegalArgsException("未能找到匹配的SystemConfigKey:" + code);
    }

}
