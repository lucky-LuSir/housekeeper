alter table t_hkp_hrm_org_dept
    add agent_cus_see_time_limit boolean default true;

comment on column t_hkp_hrm_org_dept.agent_cus_see_time_limit is '经纪人客户创建保护时间限制';

