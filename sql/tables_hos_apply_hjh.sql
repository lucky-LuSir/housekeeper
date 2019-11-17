---------房源申请表---------
CREATE SEQUENCE seq_hkp_hos_apply
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_hos_apply
  OWNER TO hkp;

CREATE TABLE t_hkp_hos_apply
(
  id               BIGINT NOT NULL   DEFAULT nextval('seq_hkp_hos_apply' :: REGCLASS),
  emp_code        CHARACTER VARYING,
  house_code       CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN           DEFAULT FALSE,
  CONSTRAINT pk_hkp_hos_apply PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_hos_apply
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_hos_apply IS '房源合作表';
COMMENT ON COLUMN t_hkp_hos_apply.emp_code IS '用户编码';
COMMENT ON COLUMN t_hkp_hos_apply.house_code IS '房源编码';
COMMENT ON COLUMN t_hkp_hos_apply.cpy_code IS '公司编号';
COMMENT ON COLUMN t_hkp_hos_apply.create_code IS '创建人编码';
COMMENT ON COLUMN t_hkp_hos_apply.last_update_code IS '修改人编码';
COMMENT ON COLUMN t_hkp_hos_apply.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_hos_apply.last_update_time IS '最后修改时间';
COMMENT ON COLUMN t_hkp_hos_apply.is_deleted IS '是否删除';
