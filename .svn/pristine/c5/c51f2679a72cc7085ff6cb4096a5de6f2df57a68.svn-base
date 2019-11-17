----订单跟进表----
CREATE SEQUENCE seq_hkp_trade_order_followup
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_trade_order_followup
  OWNER TO hkp;

CREATE TABLE t_hkp_trade_order_followup
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_trade_order_followup' :: REGCLASS),

  order_code      CHARACTER VARYING,
  followup_time   TIMESTAMP WITHOUT TIME ZONE,
  emp_code         CHARACTER VARYING,
  emp_name         CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_name      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  create_dept_name  CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_trade_orderfollowup PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_trade_order_followup
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_trade_order_followup IS '订单跟进表';
COMMENT ON COLUMN t_hkp_trade_order_followup.order_code IS '订单编码';
COMMENT ON COLUMN t_hkp_trade_order_followup.followup_time IS '跟进时间';
COMMENT ON COLUMN t_hkp_trade_order_followup.emp_code IS '跟进人编码';
COMMENT ON COLUMN t_hkp_trade_order_followup.emp_name IS '跟进人姓名';
COMMENT ON COLUMN t_hkp_trade_order_followup.remark IS '跟进订单内容';
COMMENT ON COLUMN t_hkp_trade_order_followup.cpy_code IS '所属公司编码';

