----客户需求区域表----
CREATE SEQUENCE seq_hkp_crm_customer_area
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_crm_customer_area
  OWNER TO hkp;

CREATE TABLE t_hkp_crm_customer_area
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_crm_customer_area' :: REGCLASS),
  cus_code        CHARACTER VARYING,
  province      CHARACTER VARYING,
  city        CHARACTER VARYING,
  region       CHARACTER VARYING,
  street       CHARACTER VARYING,
  province_name      CHARACTER VARYING,
  city_name        CHARACTER VARYING,
  region_name       CHARACTER VARYING,
  street_name       CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_crm_customer_area PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_crm_customer_area
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_crm_customer_area IS '客户需求区域表';
COMMENT ON COLUMN t_hkp_crm_customer_area.cus_code IS '客户编码';
COMMENT ON COLUMN t_hkp_crm_customer_area.province IS '省份编码';
COMMENT ON COLUMN t_hkp_crm_customer_area.city IS '城市编码';
COMMENT ON COLUMN t_hkp_crm_customer_area.region IS '区域编码';
COMMENT ON COLUMN t_hkp_crm_customer_area.street IS '街道编码';
COMMENT ON COLUMN t_hkp_crm_customer_area.province_name IS '省份名';
COMMENT ON COLUMN t_hkp_crm_customer_area.city_name IS '城市名';
COMMENT ON COLUMN t_hkp_crm_customer_area.region_name IS '区域名';
COMMENT ON COLUMN t_hkp_crm_customer_area.street_name IS '街道名';

