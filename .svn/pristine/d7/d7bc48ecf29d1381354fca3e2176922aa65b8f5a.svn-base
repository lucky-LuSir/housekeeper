/**客户需求区域表，房源位置表新增字段**/

-- t_hkp_crm_customer_area，t_hkp_hos_location

ALTER TABLE t_hkp_crm_customer_area ADD COLUMN community VARCHAR;
COMMENT ON COLUMN t_hkp_crm_customer_area.community IS '社区编码';
ALTER TABLE t_hkp_crm_customer_area ADD COLUMN community_name VARCHAR;
COMMENT ON COLUMN t_hkp_crm_customer_area.community_name IS '社区名';

ALTER TABLE t_hkp_hos_location ADD COLUMN community VARCHAR;
COMMENT ON COLUMN t_hkp_hos_location.community IS '社区编码';
ALTER TABLE t_hkp_hos_location ADD COLUMN community_name VARCHAR;
COMMENT ON COLUMN t_hkp_hos_location.community_name IS '社区名';


alter table t_hkp_hos_house add column community VARCHAR;
COMMENT ON COLUMN t_hkp_hos_house.community IS '社区编码';
ALTER TABLE t_hkp_hos_house ADD COLUMN community_name VARCHAR;
COMMENT ON COLUMN t_hkp_hos_house.community_name IS '社区名';
alter table t_hkp_hos_house add column bearing int;
COMMENT ON COLUMN t_hkp_hos_house.bearing IS '承重';

alter table t_hkp_hos_house add column lease_negotiable bool;
comment on column t_hkp_hos_house.lease_negotiable IS '租期可协商';

alter table t_hkp_hos_house add column of_the_area int;
comment on column t_hkp_hos_house.of_the_area IS '租期可协商';

alter table t_hkp_crm_customer add column bearing int;
comment on column t_hkp_crm_customer.bearing is '承重';

alter TABLE t_hkp_sys_user_user add COLUMN user_level VARCHAR;
COMMENT ON COLUMN t_hkp_sys_user_user.user_level IS '用户级别';



