----客户表----
CREATE SEQUENCE seq_hkp_crm_customer
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_crm_customer
  OWNER TO hkp;

CREATE TABLE t_hkp_crm_customer
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_crm_customer' :: REGCLASS),
  cus_code        CHARACTER VARYING,
  emp_code        CHARACTER VARYING,
  cus_status      CHARACTER VARYING,
  cus_type      INTEGER,
  cus_name        CHARACTER VARYING,
  cus_phone       CHARACTER VARYING,
  cus_sex       CHARACTER VARYING,
  company_name      CHARACTER VARYING,
  industry      CHARACTER VARYING,
  products      CHARACTER VARYING,
  need_acreage         INTEGER,
  need_price           NUMERIC(16, 6),
  price_unit           CHARACTER VARYING,
  layer_num         INTEGER,
  layer_height         NUMERIC(16, 6),
  need_voltage           NUMERIC(16, 6),
  enter_time           TIMESTAMP WITHOUT TIME ZONE,
  expect_term          CHARACTER VARYING,
  fire_level          CHARACTER VARYING,
  need_eia          BOOLEAN,
  need_register           BOOLEAN,
  need_certificate          BOOLEAN,
  need_single_building           BOOLEAN,
  has_office_area           BOOLEAN,
  open_flag           BOOLEAN,
  house_type           CHARACTER VARYING,
  description         CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_crm_customer PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_crm_customer
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_crm_customer IS '客户表';
COMMENT ON COLUMN t_hkp_crm_customer.cus_code IS '客户编码';
COMMENT ON COLUMN t_hkp_crm_customer.emp_code IS '所属专员编码';
COMMENT ON COLUMN t_hkp_crm_customer.cus_status IS '客户状态';
COMMENT ON COLUMN t_hkp_crm_customer.cus_type IS '客户类型';
COMMENT ON COLUMN t_hkp_crm_customer.cus_name IS '客户姓名';
COMMENT ON COLUMN t_hkp_crm_customer.cus_phone IS '客户手机号';
COMMENT ON COLUMN t_hkp_crm_customer.cus_sex IS '客户性别';
COMMENT ON COLUMN t_hkp_crm_customer.company_name IS '公司名字';
COMMENT ON COLUMN t_hkp_crm_customer.industry IS '行业性质';
COMMENT ON COLUMN t_hkp_crm_customer.products IS '产品信息';
COMMENT ON COLUMN t_hkp_crm_customer.need_acreage IS '需求面积';
COMMENT ON COLUMN t_hkp_crm_customer.need_price IS '要求价格';
COMMENT ON COLUMN t_hkp_crm_customer.price_unit IS '价格单位';
COMMENT ON COLUMN t_hkp_crm_customer.layer_num IS '楼层类型';
COMMENT ON COLUMN t_hkp_crm_customer.layer_height IS '楼层高';
COMMENT ON COLUMN t_hkp_crm_customer.need_voltage IS '要求电量';
COMMENT ON COLUMN t_hkp_crm_customer.enter_time IS '入住时间';
COMMENT ON COLUMN t_hkp_crm_customer.expect_term IS '期望租期';
COMMENT ON COLUMN t_hkp_crm_customer.fire_level IS '消防等级';
COMMENT ON COLUMN t_hkp_crm_customer.need_eia IS '是否需要环评';
COMMENT ON COLUMN t_hkp_crm_customer.need_register IS '是否需要注册';
COMMENT ON COLUMN t_hkp_crm_customer.need_certificate IS '是否需要产证';
COMMENT ON COLUMN t_hkp_crm_customer.need_single_building IS '是否需要单栋';
COMMENT ON COLUMN t_hkp_crm_customer.has_office_area IS '是否需要办公区';
COMMENT ON COLUMN t_hkp_crm_customer.open_flag IS '是否公开';
COMMENT ON COLUMN t_hkp_crm_customer.house_type IS '需求类型(找房用途)';
COMMENT ON COLUMN t_hkp_crm_customer.description IS '需求描述';
/*  兼职新增字段 */
ALTER TABLE t_hkp_crm_customer ADD COLUMN pt_code CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_crm_customer.pt_code IS '兼职编码';
ALTER TABLE t_hkp_crm_customer ADD COLUMN pt_name CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_crm_customer.pt_name IS '兼职名称';
ALTER TABLE t_hkp_crm_customer ADD COLUMN divide_type CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_crm_customer.divide_type IS '分成类型';
ALTER TABLE t_hkp_crm_customer ADD COLUMN divide_ratio NUMERIC (16, 6);
COMMENT ON COLUMN t_hkp_crm_customer.divide_ratio IS '比例';
ALTER TABLE t_hkp_crm_customer ADD COLUMN divide_cash NUMERIC (16, 6);
COMMENT ON COLUMN t_hkp_crm_customer.divide_cash IS '现金';
ALTER TABLE t_hkp_crm_customer ADD COLUMN cus_from CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_crm_customer.cus_from IS '客户来源';

/* 客户上下架新增字段 */
ALTER TABLE t_hkp_crm_customer ADD COLUMN cus_up_user CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_crm_customer.cus_up_user IS '客户最新上架人';

ALTER TABLE t_hkp_crm_customer ADD COLUMN cus_down_user CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_crm_customer.cus_down_user IS '客户最新下架人';


ALTER TABLE t_hkp_crm_customer ADD COLUMN cus_up_user_name CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_crm_customer.cus_up_user_name IS '客户最新上架人姓名';

ALTER TABLE t_hkp_crm_customer ADD COLUMN cus_down_user_name CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_crm_customer.cus_down_user_name IS '客户最新下架人姓名';
