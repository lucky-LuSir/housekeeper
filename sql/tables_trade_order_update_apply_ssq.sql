
/*
  订单修改申请表
*/
CREATE SEQUENCE seq_hkp_trade_order_update_apply
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_trade_order_update_apply
  OWNER TO hkp;

CREATE TABLE t_hkp_trade_order_update_apply
(
  id               BIGINT NOT NULL   DEFAULT nextval('seq_hkp_trade_order_update_apply' :: REGCLASS),
  apply_code       CHARACTER VARYING,
  apply_status     CHARACTER VARYING,
  audit_reason     CHARACTER VARYING,
  apply_reason      CHARACTER VARYING,
  update_fields     CHARACTER VARYING,
  update_content   CHARACTER VARYING,
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
  commission       NUMERIC(16, 6),
  dept_code        CHARACTER VARYING,

  cus_image_name CHARACTER VARYING,
  cus_commission_num CHARACTER VARYING,
  owner_image_name CHARACTER VARYING,
  owner_commission_num CHARACTER VARYING,
  expect_payment_time  TIMESTAMP WITHOUT TIME ZONE,
  has_commission_confirm BOOLEAN DEFAULT FALSE,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,

  CONSTRAINT pk_t_hkp_trade_order_update_apply PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

ALTER TABLE t_hkp_trade_order_update_apply
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_trade_order_update_apply IS '订单修改申请表';

COMMENT ON COLUMN t_hkp_trade_order_update_apply.apply_code IS '订单修改申请编码';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.apply_status IS '订单修改申请状态';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.audit_reason IS '审核理由 一般为已驳回理由';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.apply_reason IS '申请理由';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.update_fields IS '更改了哪些字段';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.update_content IS '更改了的内容拼接';

COMMENT ON COLUMN t_hkp_trade_order_update_apply.emp_code IS '成交人编码';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.order_code IS '订单编码';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.order_time IS '订单日期';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.order_status IS '订单状态';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.house_code IS '房源编码';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.houseowner_phone IS '业主手机号';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.cus_code IS '客户编码';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.cus_phone IS '客户手机号';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.acreage IS '出租面积';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.price IS '出租价格';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.unit IS '价格单位';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.contract_start IS '合同开始时间';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.contract_end IS '合同结束时间';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.month_count IS '交月份数';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.deposit IS '押金';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.receivable_hos IS '业主佣金';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.receivable_cus IS '客户佣金';

COMMENT ON COLUMN t_hkp_trade_order_update_apply.cus_rebate IS '客户返佣';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.owner_rebate IS '业主返佣';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.cus_parttime_money IS '客户兼职分钱';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.owner_parttime_money IS '业主兼职分钱';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.actual_commision IS '实际可分佣金总额';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.actual_branch_commision IS '实际分成佣金总额';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.month_rent IS '每月租金';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.commission IS '总佣金';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.cus_image_name IS '客户佣金确认书';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.cus_commission_num IS '客户佣金确认书编号';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.owner_image_name IS '业主佣金确认书';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.owner_commission_num IS '业主佣金确认书编号';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.expect_payment_time IS '预计收款时间';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.has_commission_confirm IS '收回佣金确认书';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.dept_code IS '订单所属部门';

COMMENT ON COLUMN t_hkp_trade_order_update_apply.remark IS '备注描述';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.cpy_code IS 'SAAS模式下的公司';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.create_code IS '创建者编码';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.create_dept_code IS '创建者部门编号';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.last_update_code IS '最后更新者';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.last_update_time IS '最后更新时间';
COMMENT ON COLUMN t_hkp_trade_order_update_apply.is_deleted IS '是否作废或者删除';
