ALTER TABLE t_hkp_hrm_org_dept ADD COLUMN has_register  bool;
COMMENT ON COLUMN "public"."t_hkp_hrm_org_dept"."has_register" IS '是否注册公司';

ALTER TABLE t_hkp_hrm_org_dept ADD COLUMN cpy_show_name  varchar COLLATE "default";
COMMENT ON COLUMN "public"."t_hkp_hrm_org_dept"."cpy_show_name" IS '公司显示名称';

ALTER TABLE t_hkp_hrm_org_dept ADD COLUMN has_limit_hiden  bool DEFAULT false;
COMMENT ON COLUMN "public"."t_hkp_hrm_org_dept"."has_limit_hiden" IS '是否限制隐藏';

ALTER TABLE t_hkp_hrm_org_dept ADD COLUMN has_finance_proxy  bool;
COMMENT ON COLUMN "public"."t_hkp_hrm_org_dept"."has_finance_proxy" IS '是否财务代理';

ALTER TABLE t_hkp_hrm_org_dept ADD COLUMN team_flag  bool DEFAULT false;
COMMENT ON COLUMN "public"."t_hkp_hrm_org_dept"."team_flag" IS '是否团队';

ALTER TABLE t_hkp_hrm_org_dept ADD COLUMN pay_status  varchar COLLATE "default";
COMMENT ON COLUMN "public"."t_hkp_hrm_org_dept"."pay_status" IS '支付状态';

ALTER TABLE t_hkp_hrm_org_dept ADD COLUMN create_name  varchar COLLATE "default";
COMMENT ON COLUMN "public"."t_hkp_hrm_org_dept"."create_name" IS '创建人姓名';

ALTER TABLE t_hkp_hrm_org_dept ADD COLUMN last_update_name  varchar COLLATE "default";
COMMENT ON COLUMN "public"."t_hkp_hrm_org_dept"."last_update_name" IS '最后修改人名';


ALTER TABLE t_hkp_sys_user_user ADD COLUMN invite_state  varchar COLLATE "default";
COMMENT ON COLUMN "public"."t_hkp_sys_user_user"."invite_state" IS '邀请状态';

ALTER TABLE t_hkp_sys_user_user ADD in_dept_team_time TIMESTAMP (6) NULL;

COMMENT ON COLUMN "public"."t_hkp_sys_user_user"."in_dept_team_time" IS '加入部门团队日期';

UPDATE t_hkp_sys_user_user set invite_state='NotInvite' WHERE user_type='Individual';

--
ALTER TABLE t_hkp_share_pool ADD COLUMN apply_record_code  varchar COLLATE "default";
COMMENT ON COLUMN "public"."t_hkp_share_pool"."apply_record_code" IS '共享申请唯一编码';
