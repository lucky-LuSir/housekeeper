---------数据转移 hkp备份表---------
CREATE SEQUENCE seq_hkp_resource_transfer
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_resource_transfer
  OWNER TO hkp;

CREATE TABLE t_hkp_resource_transfer
(
  id               BIGINT NOT NULL   DEFAULT nextval('seq_hkp_resource_transfer' :: REGCLASS),

  his_emp_code        CHARACTER VARYING,
  his_emp_name       CHARACTER VARYING,
  new_emp_code       CHARACTER VARYING,
  new_emp_name       CHARACTER VARYING,

  shift_customer_count      INTEGER,
  shift_customer_data        CHARACTER VARYING,
  shift_house_count       INTEGER,
  shift_house_data       CHARACTER VARYING,
  shift_user_count       INTEGER,
  shift_user_data       CHARACTER VARYING,
  shift_owner_count        INTEGER,
  shift_owner_data         CHARACTER VARYING,

  is_withdraw   BOOLEAN         DEFAULT FALSE,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,

  CONSTRAINT pk_t_hkp_resource_transfer PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

ALTER TABLE t_hkp_resource_transfer
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_resource_transfer IS '数据转移 备份表';

COMMENT ON COLUMN t_hkp_resource_transfer.his_emp_code IS '被转移数据人员编号';
COMMENT ON COLUMN t_hkp_resource_transfer.his_emp_name IS '被转移数据人员name';
COMMENT ON COLUMN t_hkp_resource_transfer.new_emp_code IS '数据接收人code';
COMMENT ON COLUMN t_hkp_resource_transfer.new_emp_name IS '数据接收人name';

COMMENT ON COLUMN t_hkp_resource_transfer.shift_customer_count IS '备份的客户总数';
COMMENT ON COLUMN t_hkp_resource_transfer.shift_customer_data IS '备份的客户，逗号隔开';
COMMENT ON COLUMN t_hkp_resource_transfer.shift_house_count IS '备份的房源总数';
COMMENT ON COLUMN t_hkp_resource_transfer.shift_house_data IS '备份的房源，逗号隔开';
COMMENT ON COLUMN t_hkp_resource_transfer.shift_user_count IS '备份的兼职总数';
COMMENT ON COLUMN t_hkp_resource_transfer.shift_user_data IS '备份的兼职，逗号隔开';
COMMENT ON COLUMN t_hkp_resource_transfer.shift_owner_count IS '备份的业主总数';
COMMENT ON COLUMN t_hkp_resource_transfer.shift_owner_data IS '备份的业主，逗号隔开';

COMMENT ON COLUMN t_hkp_resource_transfer.is_withdraw IS '是否撤回';

COMMENT ON COLUMN t_hkp_resource_transfer.remark IS '备注描述';
COMMENT ON COLUMN t_hkp_resource_transfer.cpy_code IS 'SAAS模式下的公司';
COMMENT ON COLUMN t_hkp_resource_transfer.create_code IS '创建者编码';
COMMENT ON COLUMN t_hkp_resource_transfer.create_dept_code IS '创建者部门编号';
COMMENT ON COLUMN t_hkp_resource_transfer.last_update_code IS '最后更新者';
COMMENT ON COLUMN t_hkp_resource_transfer.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_resource_transfer.last_update_time IS '最后更新时间';
COMMENT ON COLUMN t_hkp_resource_transfer.is_deleted IS '是否作废或者删除';
