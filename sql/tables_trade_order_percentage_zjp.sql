----订单分成表----
CREATE SEQUENCE seq_hkp_trade_order_percentage
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_trade_order_percentage
  OWNER TO hkp;

CREATE TABLE t_hkp_trade_order_percentage
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_trade_order_percentage' :: REGCLASS),
  percentage_code        CHARACTER VARYING,
  order_code        CHARACTER VARYING,
  order_time        TIMESTAMP WITHOUT TIME ZONE,
  pe_emp_code        CHARACTER VARYING,
  pe_dept_code        CHARACTER VARYING,
  percentage_name      CHARACTER VARYING,
  percentage_type     CHARACTER VARYING,
  money_percent_type     CHARACTER VARYING,
  percentage      NUMERIC(16, 6),
  profit      NUMERIC(16, 6),

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_trade_order_percentage PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_trade_order_percentage
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_trade_order_percentage IS '订单表';
COMMENT ON COLUMN t_hkp_trade_order_percentage.percentage_code IS '分成编码';
COMMENT ON COLUMN t_hkp_trade_order_percentage.order_code IS '订单编码';
COMMENT ON COLUMN t_hkp_trade_order_percentage.order_time IS '订单日期';
COMMENT ON COLUMN t_hkp_trade_order_percentage.pe_emp_code IS '分成人编码';
COMMENT ON COLUMN t_hkp_trade_order_percentage.pe_dept_code IS '分成人部门编码';
COMMENT ON COLUMN t_hkp_trade_order_percentage.percentage_name IS '分成名字';
COMMENT ON COLUMN t_hkp_trade_order_percentage.percentage_type IS '分成类型';
COMMENT ON COLUMN t_hkp_trade_order_percentage.money_percent_type IS '金额分成类型';
COMMENT ON COLUMN t_hkp_trade_order_percentage.percentage IS '分成百分比';
COMMENT ON COLUMN t_hkp_trade_order_percentage.profit IS '分成业绩';

