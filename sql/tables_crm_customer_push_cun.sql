---------房源申请表---------
CREATE SEQUENCE seq_hkp_crm_customer_push
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_crm_customer_push
  OWNER TO hkp;

CREATE TABLE t_hkp_crm_customer_push
(
  id                BIGINT NOT NULL   DEFAULT nextval('seq_hkp_crm_customer_push' :: REGCLASS),
  push_code         CHARACTER VARYING,
  push_name         CHARACTER VARYING,
  push_type         CHARACTER VARYING,
  receive_code      CHARACTER VARYING,
  receive_name      CHARACTER VARYING,
  receive_dept_code CHARACTER VARYING,
  receive_dept_name CHARACTER VARYING,
  cus_code          CHARACTER VARYING,

  remark            CHARACTER VARYING,
  cpy_code          CHARACTER VARYING,
  create_code       CHARACTER VARYING,
  create_dept_code  CHARACTER VARYING,
  last_update_code  CHARACTER VARYING,
  create_time       TIMESTAMP WITHOUT TIME ZONE,
  last_update_time  TIMESTAMP WITHOUT TIME ZONE,
  is_deleted        BOOLEAN           DEFAULT FALSE,
  CONSTRAINT pk_hkp_crm_customer_push PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_crm_customer_push
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_crm_customer_push IS '客户推送表';
COMMENT ON COLUMN t_hkp_crm_customer_push.push_code IS '推送用户编码';
COMMENT ON COLUMN t_hkp_crm_customer_push.push_name IS '推送用户姓名';
COMMENT ON COLUMN t_hkp_crm_customer_push.push_type IS '推送类型';
COMMENT ON COLUMN t_hkp_crm_customer_push.receive_code IS '接收人编码';
COMMENT ON COLUMN t_hkp_crm_customer_push.receive_name IS '接收人姓名';
COMMENT ON COLUMN t_hkp_crm_customer_push.receive_dept_code IS '接收部门编码';
COMMENT ON COLUMN t_hkp_crm_customer_push.receive_dept_name IS '接收部门名称';
COMMENT ON COLUMN t_hkp_crm_customer_push.cus_code IS '客户编码';

COMMENT ON COLUMN t_hkp_crm_customer_push.cpy_code IS '公司编号';
COMMENT ON COLUMN t_hkp_crm_customer_push.create_code IS '创建人编码';
COMMENT ON COLUMN t_hkp_crm_customer_push.last_update_code IS '修改人编码';
COMMENT ON COLUMN t_hkp_crm_customer_push.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_crm_customer_push.last_update_time IS '最后修改时间';
COMMENT ON COLUMN t_hkp_crm_customer_push.is_deleted IS '是否删除';
