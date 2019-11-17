
----薪资结算人员信息同步字段
----t_hkp_sys_user_user

ALTER TABLE t_hkp_sys_user_user ADD COLUMN is_referrer bool default false ;
COMMENT ON COLUMN t_hkp_sys_user_user.is_referrer IS '是否被推荐';
ALTER TABLE t_hkp_sys_user_user ADD salary_type VARCHAR NULL;
COMMENT ON COLUMN t_hkp_sys_user_user.card IS '薪资方案';
ALTER TABLE t_hkp_sys_user_user ADD referrer_code VARCHAR NULL;
COMMENT ON COLUMN t_hkp_sys_user_user.referrer_code IS '推荐人编码';
