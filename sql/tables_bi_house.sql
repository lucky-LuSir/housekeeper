/**房源表增加字段**/



ALTER TABLE t_hkp_hos_house ADD COLUMN is_hot bool;
COMMENT ON COLUMN t_hkp_hos_house.is_hot IS '是否是热门房源';
ALTER TABLE t_hkp_hos_house ADD COLUMN decoration_type VARCHAR;
COMMENT ON COLUMN t_hkp_hos_house.decoration_type IS '装修类型';

ALTER TABLE t_hkp_hos_house ADD COLUMN auth_dept_code VARCHAR;
COMMENT ON COLUMN t_hkp_hos_house.auth_dept_code IS '房源委托人所在部门编码';
ALTER TABLE t_hkp_hos_house ADD COLUMN auth_image VARCHAR;
COMMENT ON COLUMN t_hkp_hos_house.auth_image IS '房源添加协议信息图片资源';
ALTER TABLE t_hkp_hos_house ADD COLUMN auth_code VARCHAR;
COMMENT ON COLUMN t_hkp_hos_house.auth_code IS '房源签协议人编码';
ALTER TABLE t_hkp_hos_house ADD COLUMN auth_time TIMESTAMP;
COMMENT ON COLUMN t_hkp_hos_house.auth_time IS '房源协议签订时间';
ALTER TABLE t_hkp_hos_house ADD COLUMN has_crane bool;
COMMENT ON COLUMN t_hkp_hos_house.has_crane IS '是否可以装行车';

ALTER TABLE t_hkp_hos_house ADD COLUMN property_company VARCHAR;
COMMENT ON COLUMN t_hkp_hos_house.property_company IS '物业公司';
ALTER TABLE t_hkp_hos_house ADD COLUMN max_pass_car VARCHAR;
COMMENT ON COLUMN t_hkp_hos_house.max_pass_car IS '最大通行车辆';
ALTER TABLE t_hkp_hos_house ADD COLUMN elevator_standard VARCHAR;
COMMENT ON COLUMN t_hkp_hos_house.elevator_standard IS '货梯规格';
ALTER TABLE t_hkp_hos_house ADD COLUMN elevator_number INT;
COMMENT ON COLUMN t_hkp_hos_house.elevator_number IS '货梯数量';
ALTER TABLE t_hkp_hos_house ADD COLUMN min_acreage VARCHAR;
COMMENT ON COLUMN t_hkp_hos_house.min_acreage IS '最小房源分割面积';
ALTER TABLE t_hkp_hos_house ADD COLUMN elevator_door VARCHAR;
COMMENT ON COLUMN t_hkp_hos_house.elevator_door IS '货梯开门';

----房源上下架日志表----
CREATE SEQUENCE seq_hkp_hos_updown_log
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_hos_updown_log
  OWNER TO hkp;

CREATE TABLE t_hkp_hos_updown_log
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_hos_updown_log' :: REGCLASS),
  house_code        CHARACTER VARYING NOT NULL,
  pre_status        CHARACTER VARYING NOT NULL,
  aft_status           CHARACTER VARYING NOT NULL,
  remark         CHARACTER VARYING,
  user_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_hos_updown_log PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_hos_updown_log
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_hos_updown_log IS '房源上下架日志表';
COMMENT ON COLUMN t_hkp_hos_updown_log.house_code IS '房源编码';
COMMENT ON COLUMN t_hkp_hos_updown_log.pre_status IS '修改前状态';
COMMENT ON COLUMN t_hkp_hos_updown_log.aft_status IS '修改后状态';
COMMENT ON COLUMN t_hkp_hos_updown_log.remark IS '备注';
COMMENT ON COLUMN t_hkp_hos_updown_log.user_code IS '用户编码';
COMMENT ON COLUMN t_hkp_hos_updown_log.create_code IS '创建人编码';
COMMENT ON COLUMN t_hkp_hos_updown_log.create_dept_code IS '创建部门编码';
COMMENT ON COLUMN t_hkp_hos_updown_log.last_update_code IS '上次修改人编码';