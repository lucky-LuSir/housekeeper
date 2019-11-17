
alter table t_hkp_hrm_org_dept
    add COLUMN focus_cus_see_time_limit boolean default true;

comment on column t_hkp_hrm_org_dept.focus_cus_see_time_limit is '集中获客是否开启拨打时间限制';

alter table t_hkp_crm_focus_cus
    add see_cus_time timestamp default now();

comment on column t_hkp_crm_focus_cus.see_cus_time is '可以查看客户号码的时间';
