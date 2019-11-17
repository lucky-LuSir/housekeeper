---------客户收藏表---------
CREATE SEQUENCE seq_hkp_crm_customer_favorite
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_crm_customer_favorite
  OWNER TO hkp;

CREATE TABLE t_hkp_crm_customer_favorite
(
  id               BIGINT NOT NULL   DEFAULT nextval('seq_hkp_crm_customer_favorite' :: REGCLASS),
  emp_code        CHARACTER VARYING,
  cus_code       CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN           DEFAULT FALSE,
  CONSTRAINT pk_hkp_crm_customer_favorite PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_crm_customer_favorite
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_crm_customer_favorite IS '客户收藏表';
COMMENT ON COLUMN t_hkp_crm_customer_favorite.emp_code IS '用户编码';
COMMENT ON COLUMN t_hkp_crm_customer_favorite.cus_code IS '客户编码';
