----订单提成比例表----
CREATE SEQUENCE seq_hkp_trade_order_commission_ratio INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

ALTER TABLE seq_hkp_trade_order_commission_ratio OWNER TO hkp;

CREATE TABLE t_hkp_trade_order_commission_ratio (
	ID BIGINT NOT NULL DEFAULT nextval(
		'seq_hkp_trade_order_commission_ratio' :: REGCLASS
	),
	commission_ratio_code CHARACTER VARYING,
	order_code CHARACTER VARYING,
	pe_order_code CHARACTER VARYING,
	percentage NUMERIC (16, 6),
	dept_code CHARACTER VARYING,
	user_code CHARACTER VARYING,
	commission_ratio NUMERIC (16, 6),
	salary_type CHARACTER VARYING,
	group_user_code CHARACTER VARYING,
	group_percentage NUMERIC (16, 6),
	leader_code CHARACTER VARYING,
	leader_percentage NUMERIC (16, 6),
	manager_user_code CHARACTER VARYING,
	manager_percentage NUMERIC (16, 6),
	recommend_code CHARACTER VARYING,
	recommend_percentage NUMERIC (16, 6),
	has_changed BOOLEAN DEFAULT FALSE,
	order_time TIMESTAMP WITHOUT TIME ZONE,
	remark CHARACTER VARYING,
	create_code CHARACTER VARYING,
	create_dept_code CHARACTER VARYING,
	last_update_code CHARACTER VARYING,
	create_time TIMESTAMP WITHOUT TIME ZONE,
	last_update_time TIMESTAMP WITHOUT TIME ZONE,
	is_deleted BOOLEAN DEFAULT FALSE,
	CONSTRAINT pk_hkp_trade_order_commission_ratio PRIMARY KEY (ID)
) WITH (OIDS = FALSE);

ALTER TABLE t_hkp_trade_order_commission_ratio OWNER TO hkp;

COMMENT ON TABLE t_hkp_trade_order_commission_ratio IS '订单提成表';

COMMENT ON COLUMN t_hkp_trade_order_commission_ratio.commission_ratio_code IS '提成编码';

COMMENT ON COLUMN t_hkp_trade_order_commission_ratio.order_code IS '订单编码';

COMMENT ON COLUMN t_hkp_trade_order_commission_ratio.user_code IS '提成所属人';

COMMENT ON COLUMN t_hkp_trade_order_commission_ratio.pe_order_code IS '分成编码';

COMMENT ON COLUMN t_hkp_trade_order_commission_ratio.percentage IS '分成比例';

COMMENT ON COLUMN t_hkp_trade_order_commission_ratio.commission_ratio IS '提成比';

COMMENT ON COLUMN t_hkp_trade_order_commission_ratio.salary_type IS '薪资类型';

COMMENT ON COLUMN t_hkp_trade_order_commission_ratio.group_user_code IS '组长编码';

COMMENT ON COLUMN t_hkp_trade_order_commission_ratio.group_percentage IS '组长提成比例';

COMMENT ON COLUMN t_hkp_trade_order_commission_ratio.leader_code IS '经理编码';

COMMENT ON COLUMN t_hkp_trade_order_commission_ratio.leader_percentage IS '经理提成比例';

COMMENT ON COLUMN t_hkp_trade_order_commission_ratio.manager_user_code IS '资深经理';

COMMENT ON COLUMN t_hkp_trade_order_commission_ratio.manager_percentage IS '资深经理提成比例';

COMMENT ON COLUMN t_hkp_trade_order_commission_ratio.recommend_code IS '推荐人编码';

COMMENT ON COLUMN t_hkp_trade_order_commission_ratio.recommend_percentage IS '推荐人提成比例';

COMMENT ON COLUMN t_hkp_trade_order_commission_ratio.has_changed IS '是否修改';

COMMENT ON COLUMN t_hkp_trade_order_commission_ratio.order_time IS '订单时间';





----订单提成表----
CREATE SEQUENCE seq_hkp_trade_order_commission_wage
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_trade_order_commission_wage
  OWNER TO hkp;

CREATE TABLE t_hkp_trade_order_commission_wage
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_trade_order_commission_wage' :: REGCLASS),
  commission_wage_code        CHARACTER VARYING,
  commission_ratio_code        CHARACTER VARYING,
  order_code        CHARACTER VARYING,
  pe_order_code      CHARACTER VARYING,
--   percentage      NUMERIC(16, 6),
  pay_back_order_code      CHARACTER VARYING,
	pay_back_amount      NUMERIC(16, 6),
	pay_back_achievements      NUMERIC(16, 6),
  user_code         CHARACTER VARYING,
  commission_wage      NUMERIC(16, 6),
  commission_ratio      NUMERIC(16, 6),
  commission_type     CHARACTER VARYING,
  pay_back_time        TIMESTAMP WITHOUT TIME ZONE,
  has_changed       BOOLEAN         DEFAULT FALSE,
  is_settlement BOOLEAN DEFAULT TRUE,
  wage_type     CHARACTER VARYING,
  remark           CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_trade_order_commission_wage PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_trade_order_commission_wage
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_trade_order_commission_wage IS '订单提成业绩表';
COMMENT ON COLUMN t_hkp_trade_order_commission_wage.commission_wage_code IS '业绩提成编码';
COMMENT ON COLUMN t_hkp_trade_order_commission_wage.commission_ratio_code IS '提成编码';
COMMENT ON COLUMN t_hkp_trade_order_commission_wage.order_code IS '订单编码';
COMMENT ON COLUMN t_hkp_trade_order_commission_wage.pe_order_code IS '分成编码';
-- COMMENT ON COLUMN t_hkp_trade_order_commission_wage.percentage IS '分成比例，字段冗余';
COMMENT ON COLUMN t_hkp_trade_order_commission_wage.pay_back_order_code IS '回款编码';
COMMENT ON COLUMN t_hkp_trade_order_commission_wage.pay_back_amount IS '回款金额';
COMMENT ON COLUMN t_hkp_trade_order_commission_wage.pay_back_achievements IS '回款业绩';
COMMENT ON COLUMN t_hkp_trade_order_commission_wage.commission_wage IS '提成业绩';
COMMENT ON COLUMN t_hkp_trade_order_commission_wage.commission_ratio IS '提成比例';
COMMENT ON COLUMN t_hkp_trade_order_commission_wage.commission_type IS '提成类型';
COMMENT ON COLUMN t_hkp_trade_order_commission_wage.has_changed IS '是否被修改';
COMMENT ON COLUMN t_hkp_trade_order_commission_wage.is_settlement IS '是否结算';
COMMENT ON COLUMN t_hkp_trade_order_commission_wage.wage_type IS '工资类型 1回款 2开票';



