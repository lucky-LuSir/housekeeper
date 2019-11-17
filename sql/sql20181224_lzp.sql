/**人员表新增字段**/

-- t_hkp_sys_user_user

ALTER TABLE PUBLIC .t_hkp_sys_user_user ADD user_ca_status VARCHAR NULL;

COMMENT ON COLUMN PUBLIC .t_hkp_sys_user_user.user_ca_status IS '认证状态1 未认证2认证中3已认证';

ALTER TABLE PUBLIC .t_hkp_sys_user_user ADD card VARCHAR NULL;

COMMENT ON COLUMN PUBLIC .t_hkp_sys_user_user.card IS '身份证号';


/**默认Employee为已认证**/

update t_hkp_sys_user_user set user_ca_status='3' where user_type='Employee';


