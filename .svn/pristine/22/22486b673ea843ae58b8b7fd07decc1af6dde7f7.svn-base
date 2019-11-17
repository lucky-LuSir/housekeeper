---------共享申请表---------
CREATE SEQUENCE seq_hkp_share_pool_apply
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_share_pool_apply
  OWNER TO hkp;

CREATE TABLE t_hkp_share_pool_apply
(
  id               BIGINT NOT NULL   DEFAULT nextval('seq_hkp_share_pool_apply' :: REGCLASS),
  apply_record_code        CHARACTER VARYING,

  apply_person_code       CHARACTER VARYING,
  apply_person_name       CHARACTER VARYING,
  apply_person_img       CHARACTER VARYING,
  apply_dept_code       CHARACTER VARYING,
  apply_dept_name        CHARACTER VARYING,

  receive_person_code       CHARACTER VARYING,
  receive_person_name       CHARACTER VARYING,
  receive_person_img       CHARACTER VARYING,
  receive_dept_code       CHARACTER VARYING,
  receive_dept_name        CHARACTER VARYING,

  apply_state         CHARACTER VARYING,

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

  CONSTRAINT pk_hkp_share_pool_apply PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

ALTER TABLE t_hkp_share_pool_apply
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_share_pool_apply IS '共享申请表';
COMMENT ON COLUMN t_hkp_share_pool_apply.apply_record_code IS '唯一编码';

COMMENT ON COLUMN t_hkp_share_pool_apply.apply_person_code IS '申请人编码';
COMMENT ON COLUMN t_hkp_share_pool_apply.apply_person_name IS '申请人名字';
COMMENT ON COLUMN t_hkp_share_pool_apply.apply_person_img IS '申请人头像图片';
COMMENT ON COLUMN t_hkp_share_pool_apply.apply_dept_code IS '申请人部门编码';
COMMENT ON COLUMN t_hkp_share_pool_apply.apply_dept_name IS '申请人部门名字';

COMMENT ON COLUMN t_hkp_share_pool_apply.receive_person_code IS '接收人编码';
COMMENT ON COLUMN t_hkp_share_pool_apply.receive_person_name IS '接收人名字';
COMMENT ON COLUMN t_hkp_share_pool_apply.receive_person_img IS '接收人头像图片';
COMMENT ON COLUMN t_hkp_share_pool_apply.receive_dept_code IS '接收人部门编码';
COMMENT ON COLUMN t_hkp_share_pool_apply.receive_dept_name IS '接收人部门名字';

COMMENT ON COLUMN t_hkp_share_pool_apply.apply_state IS '申请状态';

COMMENT ON COLUMN t_hkp_share_pool_apply.cpy_code IS 'SAAS模式下的公司';
COMMENT ON COLUMN t_hkp_share_pool_apply.owner_code IS '所属用户编码';
COMMENT ON COLUMN t_hkp_share_pool_apply.owner_name IS '所属用户姓名';
COMMENT ON COLUMN t_hkp_share_pool_apply.owner_dept_code IS '数据所属部门';
COMMENT ON COLUMN t_hkp_share_pool_apply.owner_dept_name IS '数据所属部门名称';
COMMENT ON COLUMN t_hkp_share_pool_apply.create_code IS '创建者编码';
COMMENT ON COLUMN t_hkp_share_pool_apply.create_name IS '创建者名称';
COMMENT ON COLUMN t_hkp_share_pool_apply.create_dept_code IS '创建者部门编号';
COMMENT ON COLUMN t_hkp_share_pool_apply.create_dept_name IS '创建者部门名称';
COMMENT ON COLUMN t_hkp_share_pool_apply.last_update_code IS '最后更新者';
COMMENT ON COLUMN t_hkp_share_pool_apply.last_update_name IS '最后更新者名称';
COMMENT ON COLUMN t_hkp_share_pool_apply.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_share_pool_apply.last_update_time IS '最后更新时间';
COMMENT ON COLUMN t_hkp_share_pool_apply.is_deleted IS '是否作废或者删除';
COMMENT ON COLUMN t_hkp_share_pool_apply.remark IS '备注描述';
