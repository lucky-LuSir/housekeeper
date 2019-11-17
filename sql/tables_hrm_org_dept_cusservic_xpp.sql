---------部门动态配置客服表---------
CREATE SEQUENCE seq_hkp_org_dept_cusservice
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_org_dept_cusservice
  OWNER TO hkp;

CREATE TABLE t_hkp_org_dept_cusservice
(
  id               BIGINT NOT NULL   DEFAULT nextval('seq_hkp_org_dept_cusservice' :: REGCLASS),
  cusservice_code        CHARACTER VARYING,
  dept_code       CHARACTER VARYING,
  dept_name       CHARACTER VARYING,
  user_code       CHARACTER VARYING,
  user_name       CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code  CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN           DEFAULT FALSE,
  CONSTRAINT pk_hkp_org_dept_cusservice PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_org_dept_cusservice
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_org_dept_cusservice IS '部门动态配置客服表';
COMMENT ON COLUMN t_hkp_org_dept_cusservice.cusservice_code IS '配置客服表唯一编码';
COMMENT ON COLUMN t_hkp_org_dept_cusservice.dept_code IS '部门编码';
COMMENT ON COLUMN t_hkp_org_dept_cusservice.dept_name IS '部门名称';
COMMENT ON COLUMN t_hkp_org_dept_cusservice.user_code IS '客服人员编码';
COMMENT ON COLUMN t_hkp_org_dept_cusservice.user_name IS '客服人员名称';

COMMENT ON COLUMN t_hkp_org_dept_cusservice.create_code IS '创建人编码';
COMMENT ON COLUMN t_hkp_org_dept_cusservice.last_update_code IS '修改人编码';
COMMENT ON COLUMN t_hkp_org_dept_cusservice.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_org_dept_cusservice.last_update_time IS '最后修改时间';
COMMENT ON COLUMN t_hkp_org_dept_cusservice.is_deleted IS '是否删除';
