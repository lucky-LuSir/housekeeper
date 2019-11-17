---------提醒下架关联表---------
CREATE SEQUENCE seq_hkp_sys_remind_down_rack
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_sys_remind_down_rack
  OWNER TO hkp;

CREATE TABLE t_hkp_sys_remind_down_rack
(
  id               BIGINT NOT NULL   DEFAULT nextval('seq_hkp_sys_remind_down_rack' :: REGCLASS),
  remind_code        CHARACTER VARYING,
  owner_code        CHARACTER VARYING,
  owner_name        CHARACTER VARYING,
  down_type       CHARACTER VARYING,
  down_cause       CHARACTER VARYING,

  user_code       CHARACTER VARYING,
  user_cause       CHARACTER VARYING,
  has_operate       BOOLEAN       DEFAULT FALSE,
  lease_expiration      TIMESTAMP WITHOUT TIME ZONE,

  remark           CHARACTER VARYING,
  cpy_code           CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  create_name      CHARACTER VARYING,
  create_dept_name      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN           DEFAULT FALSE,
  CONSTRAINT pk_hkp_sys_remind_down_rack PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_sys_remind_down_rack
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_sys_remind_down_rack IS '下架关联表';
COMMENT ON COLUMN t_hkp_sys_remind_down_rack.remind_code IS '提醒编码';
COMMENT ON COLUMN t_hkp_sys_remind_down_rack.owner_code IS '下架的编码';
COMMENT ON COLUMN t_hkp_sys_remind_down_rack.owner_name IS '下架的编码';
COMMENT ON COLUMN t_hkp_sys_remind_down_rack.down_type IS '下架类型';
COMMENT ON COLUMN t_hkp_sys_remind_down_rack.down_cause IS '下架原因';

COMMENT ON COLUMN t_hkp_sys_remind_down_rack.user_code IS '用户代码';
COMMENT ON COLUMN t_hkp_sys_remind_down_rack.user_cause IS '用户原因';
COMMENT ON COLUMN t_hkp_sys_remind_down_rack.has_operate IS '是否操作';
COMMENT ON COLUMN t_hkp_sys_remind_down_rack.lease_expiration IS '租赁到期时间';

COMMENT ON COLUMN t_hkp_sys_remind_down_rack.remark IS '备注';
COMMENT ON COLUMN t_hkp_sys_remind_down_rack.create_code IS '创建人编码';
COMMENT ON COLUMN t_hkp_sys_remind_down_rack.create_dept_code IS '创建人编码';
COMMENT ON COLUMN t_hkp_sys_remind_down_rack.create_name IS '创建人编码';
COMMENT ON COLUMN t_hkp_sys_remind_down_rack.create_dept_name IS '创建人编码';
COMMENT ON COLUMN t_hkp_sys_remind_down_rack.last_update_code IS '修改人编码';
COMMENT ON COLUMN t_hkp_sys_remind_down_rack.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_sys_remind_down_rack.last_update_time IS '最后修改时间';
COMMENT ON COLUMN t_hkp_sys_remind_down_rack.is_deleted IS '是否删除';

ALTER TABLE t_hkp_sys_remind_down_rack ADD COLUMN remind_code CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_sys_remind_down_rack.remind_code IS '提醒编码';
