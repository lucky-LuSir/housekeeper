---------历史记录表---------
CREATE SEQUENCE seq_hkp_base_his_history
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_base_his_history
  OWNER TO hkp;

CREATE TABLE t_hkp_base_his_history
(
  id               BIGINT NOT NULL   DEFAULT nextval('seq_hkp_base_his_history' :: REGCLASS),
  history_code        CHARACTER VARYING,
  class_name       CHARACTER VARYING,
  field            CHARACTER VARYING,
  old_value         CHARACTER VARYING,
  new_value       CHARACTER VARYING,
  opt_code       CHARACTER VARYING,
  opt_name       CHARACTER VARYING,
  opt_data_code       CHARACTER VARYING,
  batch_code       CHARACTER VARYING,
  old_entity         CHARACTER VARYING,
  new_entity         CHARACTER VARYING,

  cpy_code         CHARACTER VARYING,
  owner_code         CHARACTER VARYING,
  owner_name         CHARACTER VARYING,
  owner_dept_code         CHARACTER VARYING,
  owner_dept_name         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_name         CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  create_dept_name      CHARACTER VARYING,
  last_update_code    CHARACTER VARYING,
  last_update_name   CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN           DEFAULT FALSE,
  remark           CHARACTER VARYING,

  CONSTRAINT pk_hkp_base_his_history PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_base_his_history
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_base_his_history IS '历史记录表';
COMMENT ON COLUMN t_hkp_base_his_history.history_code IS '历史唯一编码';
COMMENT ON COLUMN t_hkp_base_his_history.class_name IS '记录类';
COMMENT ON COLUMN t_hkp_base_his_history.field IS '字段';
COMMENT ON COLUMN t_hkp_base_his_history.old_value IS '旧值';
COMMENT ON COLUMN t_hkp_base_his_history.new_value IS '新值';
COMMENT ON COLUMN t_hkp_base_his_history.opt_code IS '操作人code';
COMMENT ON COLUMN t_hkp_base_his_history.opt_name IS '操作人name';
COMMENT ON COLUMN t_hkp_base_his_history.opt_data_code IS '修改数据编码';
COMMENT ON COLUMN t_hkp_base_his_history.batch_code IS '批次code(可以当做外键)';
COMMENT ON COLUMN t_hkp_base_his_history.old_entity IS '旧实体json';
COMMENT ON COLUMN t_hkp_base_his_history.new_entity IS '新实体json';

COMMENT ON COLUMN t_hkp_base_his_history.cpy_code IS 'SAAS模式下的公司';
COMMENT ON COLUMN t_hkp_base_his_history.owner_code IS '所属用户编码';
COMMENT ON COLUMN t_hkp_base_his_history.owner_name IS '所属用户姓名';
COMMENT ON COLUMN t_hkp_base_his_history.owner_dept_code IS '数据所属部门';
COMMENT ON COLUMN t_hkp_base_his_history.owner_dept_name IS '数据所属部门名称';
COMMENT ON COLUMN t_hkp_base_his_history.create_code IS '创建者编码';
COMMENT ON COLUMN t_hkp_base_his_history.create_name IS '创建者名称';
COMMENT ON COLUMN t_hkp_base_his_history.create_dept_code IS '创建者部门编号';
COMMENT ON COLUMN t_hkp_base_his_history.create_dept_name IS '创建者部门名称';
COMMENT ON COLUMN t_hkp_base_his_history.last_update_code IS '最后更新者';
COMMENT ON COLUMN t_hkp_base_his_history.last_update_name IS '最后更新者名称';
COMMENT ON COLUMN t_hkp_base_his_history.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_base_his_history.last_update_time IS '最后更新时间';
COMMENT ON COLUMN t_hkp_base_his_history.is_deleted IS '是否作废或者删除';
COMMENT ON COLUMN t_hkp_base_his_history.remark IS '备注描述';
