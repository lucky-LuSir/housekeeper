---------选址报告表---------
CREATE SEQUENCE seq_hkp_crm_select_address
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_crm_select_address
  OWNER TO hkp;

CREATE TABLE t_hkp_crm_select_address
(
  id               BIGINT NOT NULL   DEFAULT nextval('seq_hkp_crm_select_address' :: REGCLASS),
  select_address_code        CHARACTER VARYING,
  cus_code       CHARACTER VARYING,
  cus_name       CHARACTER VARYING,
  owner_user_phone       CHARACTER VARYING,
  address_url       CHARACTER VARYING,
  owner_code       CHARACTER VARYING,
  owner_name       CHARACTER VARYING,
  user_img       CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN           DEFAULT FALSE,
  CONSTRAINT pk_hkp_crm_select_address PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_crm_select_address
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_crm_select_address IS '选址报告表';
COMMENT ON COLUMN t_hkp_crm_select_address.select_address_code IS '选址唯一编码';
COMMENT ON COLUMN t_hkp_crm_select_address.cus_code IS '客户编码';
COMMENT ON COLUMN t_hkp_crm_select_address.cus_name IS '客户名称';
COMMENT ON COLUMN t_hkp_crm_select_address.owner_user_phone IS '发选址人手机';
COMMENT ON COLUMN t_hkp_crm_select_address.address_url IS '跳转的url';
COMMENT ON COLUMN t_hkp_crm_select_address.owner_code IS '发选址人编码';
COMMENT ON COLUMN t_hkp_crm_select_address.owner_name IS '发选址人中文名';

COMMENT ON COLUMN t_hkp_crm_select_address.create_code IS '创建人编码';
COMMENT ON COLUMN t_hkp_crm_select_address.last_update_code IS '修改人编码';
COMMENT ON COLUMN t_hkp_crm_select_address.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_crm_select_address.last_update_time IS '最后修改时间';
COMMENT ON COLUMN t_hkp_crm_select_address.is_deleted IS '是否删除';
