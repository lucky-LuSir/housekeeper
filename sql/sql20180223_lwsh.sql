/**客户推送表新增字段**/

-- t_hkp_crm_customer_push

ALTER TABLE t_hkp_crm_customer_push add COLUMN push_open_flag BOOLEAN DEFAULT TRUE

COMMENT ON COLUMN  public.t_hkp_crm_customer_push.push_open_flag IS  '是否公开、隐藏当前推送的客户信息'


alter table t_hkp_hrm_org_dept
    add COLUMN focus_cus_see_time_limit boolean default true;

comment on column t_hkp_hrm_org_dept.focus_cus_see_time_limit is '集中获客是否开启拨打时间限制';



