----订单表----
CREATE SEQUENCE seq_hkp_trade_order
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_trade_order
  OWNER TO hkp;

CREATE TABLE t_hkp_trade_order
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_trade_order' :: REGCLASS),
  emp_code        CHARACTER VARYING,
  order_code        CHARACTER VARYING,
  order_time        TIMESTAMP WITHOUT TIME ZONE,
  order_status        CHARACTER VARYING,
  house_code        CHARACTER VARYING,
  houseowner_phone        CHARACTER VARYING,
  cus_code      CHARACTER VARYING,
  cus_phone      CHARACTER VARYING,
  acreage      NUMERIC(16, 6),
  price      NUMERIC(16, 6),
  unit      CHARACTER VARYING,
  contract_start        TIMESTAMP WITHOUT TIME ZONE,
  contract_end       TIMESTAMP WITHOUT TIME ZONE,
  month_count       INTEGER,
  deposit       NUMERIC(16, 6),
  receivable_hos       NUMERIC(16, 6),
  receivable_cus       NUMERIC(16, 6),

  cus_rebate       NUMERIC(16, 6),
  owner_rebate       NUMERIC(16, 6),
  cus_parttime_money       NUMERIC(16, 6),
  owner_parttime_money       NUMERIC(16, 6),
  actual_commision       NUMERIC(16, 6),
  actual_branch_commision       NUMERIC(16, 6),
  month_rent       NUMERIC(16, 6),
  commission      NUMERIC(16, 6),

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_trade_order PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_trade_order
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_trade_order IS '订单表';
COMMENT ON COLUMN t_hkp_trade_order.emp_code IS '成交人编码';
COMMENT ON COLUMN t_hkp_trade_order.order_code IS '订单编码';
COMMENT ON COLUMN t_hkp_trade_order.order_time IS '订单日期';
COMMENT ON COLUMN t_hkp_trade_order.order_status IS '订单状态';
COMMENT ON COLUMN t_hkp_trade_order.house_code IS '房源编码';
COMMENT ON COLUMN t_hkp_trade_order.houseowner_phone IS '业主手机号';
COMMENT ON COLUMN t_hkp_trade_order.cus_code IS '客户编码';
COMMENT ON COLUMN t_hkp_trade_order.cus_phone IS '客户手机号';
COMMENT ON COLUMN t_hkp_trade_order.acreage IS '出租面积';
COMMENT ON COLUMN t_hkp_trade_order.price IS '出租价格';
COMMENT ON COLUMN t_hkp_trade_order.unit IS '价格单位';
COMMENT ON COLUMN t_hkp_trade_order.contract_start IS '合同开始时间';
COMMENT ON COLUMN t_hkp_trade_order.contract_end IS '合同结束时间';
COMMENT ON COLUMN t_hkp_trade_order.month_count IS '交月份数';
COMMENT ON COLUMN t_hkp_trade_order.deposit IS '押金';
COMMENT ON COLUMN t_hkp_trade_order.receivable_hos IS '业主佣金';
COMMENT ON COLUMN t_hkp_trade_order.receivable_cus IS '客户佣金';

COMMENT ON COLUMN t_hkp_trade_order.cus_rebate IS '客户返佣';
COMMENT ON COLUMN t_hkp_trade_order.owner_rebate IS '业主返佣';
COMMENT ON COLUMN t_hkp_trade_order.cus_parttime_money IS '客户兼职分钱';
COMMENT ON COLUMN t_hkp_trade_order.owner_parttime_money IS '业主兼职分钱';
COMMENT ON COLUMN t_hkp_trade_order.actual_commision IS '实际可分佣金总额';
COMMENT ON COLUMN t_hkp_trade_order.actual_branch_commision IS '实际分成佣金总额';
COMMENT ON COLUMN t_hkp_trade_order.month_rent IS '每月租金';

COMMENT ON COLUMN t_hkp_trade_order.commission IS '总佣金';

ALTER TABLE t_hkp_trade_order ADD COLUMN cus_image_name CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_trade_order.cus_image_name IS '客户佣金确认书';
ALTER TABLE t_hkp_trade_order ADD COLUMN cus_commission_num CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_trade_order.cus_commission_num IS '客户佣金确认书编号';
ALTER TABLE t_hkp_trade_order ADD COLUMN owner_image_name CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_trade_order.owner_image_name IS '业主佣金确认书';
ALTER TABLE t_hkp_trade_order ADD COLUMN owner_commission_num CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_trade_order.owner_commission_num IS '业主佣金确认书编号';
ALTER TABLE t_hkp_trade_order ADD COLUMN expect_payment_time TIMESTAMP WITHOUT TIME ZONE;
COMMENT ON COLUMN t_hkp_trade_order.expect_payment_time IS '预计收款时间';

ALTER TABLE t_hkp_trade_order ADD COLUMN has_commission_confirm BOOLEAN DEFAULT FALSE;
COMMENT ON COLUMN t_hkp_trade_order.has_commission_confirm IS '收回佣金确认书';

