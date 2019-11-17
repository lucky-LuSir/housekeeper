----房源业主表----

/*增加emp_code列*/
ALTER TABLE t_hkp_hos_houseowner ADD emp_code VARCHAR
/*emp_code等于create_code*/
UPDATE t_hkp_hos_houseowner SET emp_code = create_code


CREATE SEQUENCE seq_hkp_crm_houseowner
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_crm_houseowner
  OWNER TO hkp;

CREATE TABLE t_hkp_hos_houseowner
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_crm_houseowner' :: REGCLASS),
  houseowner_code        CHARACTER VARYING,
  houseowner_name        CHARACTER VARYING,
  houseowner_phone       CHARACTER VARYING,
  houseowner_sex       CHARACTER VARYING,
  houseowner_type       CHARACTER VARYING,
  company_name      CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_crm_houseowner PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_hos_houseowner
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_hos_houseowner IS '业主表';
COMMENT ON COLUMN t_hkp_hos_houseowner.houseowner_code IS '业主编码';
COMMENT ON COLUMN t_hkp_hos_houseowner.houseowner_name IS '业主姓名';
COMMENT ON COLUMN t_hkp_hos_houseowner.houseowner_phone IS '业主手机号';
COMMENT ON COLUMN t_hkp_hos_houseowner.houseowner_sex IS '业主性别';
COMMENT ON COLUMN t_hkp_hos_houseowner.houseowner_type IS '业主类型';
COMMENT ON COLUMN t_hkp_hos_houseowner.company_name IS '公司名字';

