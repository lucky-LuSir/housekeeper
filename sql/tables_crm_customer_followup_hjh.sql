----客户跟进表----
CREATE SEQUENCE seq_hkp_crm_customer_followup
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_crm_customer_followup
  OWNER TO hkp;

CREATE TABLE t_hkp_crm_customer_followup
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_crm_customer_followup' :: REGCLASS),
  followup_code        CHARACTER VARYING,
  followup_content      CHARACTER VARYING,
  followup_type        CHARACTER VARYING,
  cus_code        CHARACTER VARYING,
  emp_code      CHARACTER VARYING,
  dept_code       CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_crm_customer_followup PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_crm_customer_followup
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_crm_customer_followup IS '客户需求区域表';
COMMENT ON COLUMN t_hkp_crm_customer_followup.followup_code IS '跟进编码';
COMMENT ON COLUMN t_hkp_crm_customer_followup.followup_content IS '跟进内容';
COMMENT ON COLUMN t_hkp_crm_customer_followup.followup_type IS '跟进类型';
COMMENT ON COLUMN t_hkp_crm_customer_followup.cus_code IS '客户编码';
COMMENT ON COLUMN t_hkp_crm_customer_followup.emp_code IS '人员编码';
COMMENT ON COLUMN t_hkp_crm_customer_followup.dept_code IS '部门编码';

