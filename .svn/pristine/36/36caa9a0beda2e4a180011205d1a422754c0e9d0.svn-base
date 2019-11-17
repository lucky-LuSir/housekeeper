---------文件表---------
CREATE SEQUENCE seq_hkp_sys_file_file
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_sys_file_file
  OWNER TO hkp;

CREATE TABLE t_hkp_sys_file_file
(
  id               BIGINT NOT NULL   DEFAULT nextval('seq_hkp_sys_file_file' :: REGCLASS),
  file_code        CHARACTER VARYING,
  file_name        CHARACTER VARYING,
  file_type        CHARACTER VARYING,
  path_head        CHARACTER VARYING,
  file_path        CHARACTER VARYING,

  parent_code      CHARACTER VARYING DEFAULT '0',
  is_share         BOOLEAN           DEFAULT FALSE,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN           DEFAULT FALSE,
  CONSTRAINT pk_hkp_sys_file_file PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_sys_file_file
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_sys_file_file IS '文件表';
COMMENT ON COLUMN t_hkp_sys_file_file.file_code IS '文件编码';
COMMENT ON COLUMN t_hkp_sys_file_file.file_name IS '文件名称';
COMMENT ON COLUMN t_hkp_sys_file_file.file_type IS '文件类型';
COMMENT ON COLUMN t_hkp_sys_file_file.path_head IS '文件路径前缀';
COMMENT ON COLUMN t_hkp_sys_file_file.file_path IS '文件相对路径';
COMMENT ON COLUMN t_hkp_sys_file_file.parent_code IS '所属文件夹编码';
COMMENT ON COLUMN t_hkp_sys_file_file.is_share IS '是否共享';
COMMENT ON COLUMN t_hkp_sys_file_file.cpy_code IS '公司编号';
COMMENT ON COLUMN t_hkp_sys_file_file.create_code IS '创建人编码';
COMMENT ON COLUMN t_hkp_sys_file_file.last_update_code IS '修改人编码';
COMMENT ON COLUMN t_hkp_sys_file_file.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_sys_file_file.last_update_time IS '最后修改时间';
COMMENT ON COLUMN t_hkp_sys_file_file.is_deleted IS '是否删除';
