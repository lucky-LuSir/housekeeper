---------文件表---------
CREATE SEQUENCE seq_hkp_sys_file_relation
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_sys_file_relation
  OWNER TO hkp;

CREATE TABLE t_hkp_sys_file_relation
(
  id               BIGINT NOT NULL   DEFAULT nextval('seq_hkp_sys_file_relation' :: REGCLASS),
  file_code        CHARACTER VARYING,
  file_name        CHARACTER VARYING,
  file_type        CHARACTER VARYING,
  file_use        CHARACTER VARYING,
  owner_code       CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN           DEFAULT FALSE,
  CONSTRAINT pk_hkp_sys_file_relation PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_sys_file_relation
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_sys_file_relation IS '文件表';
COMMENT ON COLUMN t_hkp_sys_file_relation.file_code IS '文件编码';
COMMENT ON COLUMN t_hkp_sys_file_relation.file_name IS '文件名称';
COMMENT ON COLUMN t_hkp_sys_file_relation.file_type IS '文件类型';
COMMENT ON COLUMN t_hkp_sys_file_relation.file_use IS '文件用途';
COMMENT ON COLUMN t_hkp_sys_file_relation.owner_code IS '文件所有者编码';
COMMENT ON COLUMN t_hkp_sys_file_relation.cpy_code IS '公司编号';
COMMENT ON COLUMN t_hkp_sys_file_relation.create_code IS '创建人编码';
COMMENT ON COLUMN t_hkp_sys_file_relation.last_update_code IS '修改人编码';
COMMENT ON COLUMN t_hkp_sys_file_relation.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_sys_file_relation.last_update_time IS '最后修改时间';
COMMENT ON COLUMN t_hkp_sys_file_relation.is_deleted IS '是否删除';
