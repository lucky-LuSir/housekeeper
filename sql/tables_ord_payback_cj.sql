CREATE SEQUENCE seq_hkp_trade_ord_payback
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_trade_ord_payback
  OWNER TO hkp;

CREATE TABLE "public"."t_hkp_trade_ord_payback" (
"id" int8 DEFAULT nextval('seq_hkp_trade_ord_payback'::regclass) NOT NULL,
"order_code" varchar COLLATE "default",
"payment" numeric(20,8),
"back_time" timestamp(6),
"create_time" timestamp(6),
"last_update_time" timestamp(6),
"is_deleted" bool DEFAULT false,
"description" varchar COLLATE "default",
"channel" varchar COLLATE "default",
"create_code" varchar COLLATE "default",
"create_name" varchar COLLATE "default",
"last_update_code" varchar COLLATE "default",
"last_update_name" varchar COLLATE "default",
CONSTRAINT "pk_hkp_trade_ord_payback" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE)
;

ALTER TABLE "public"."t_hkp_trade_ord_payback"  OWNER TO hkp;

COMMENT ON TABLE "public"."t_hkp_trade_ord_payback" IS '订单回款信息表';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_payback"."order_code" IS '订单编码';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_payback"."payment" IS '回款金额';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_payback"."back_time" IS '回款时间';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_payback"."create_time" IS '创建时间';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_payback"."description" IS '创建时间';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_payback"."channel" IS '付款渠道支付宝、银行、微信';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_payback"."create_code" IS '创建人编码';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_payback"."create_name" IS '创建人姓名';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_payback"."last_update_code" IS '最后修改人编码';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_payback"."last_update_name" IS '最后修改人姓名';

