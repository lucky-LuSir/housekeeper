alter table t_hkp_crm_focus_cus
    add special_push boolean default false;

comment on column t_hkp_crm_focus_cus.special_push is '集中获客优先推送';
