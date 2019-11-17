CREATE SEQUENCE seq_hkp_trade_ord_invoice
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_trade_ord_invoice
  OWNER TO hkp;


CREATE TABLE "public"."t_hkp_trade_ord_invoice" (
"id" int8 DEFAULT nextval('seq_hkp_trade_ord_invoice'::regclass) NOT NULL,
"order_code" varchar COLLATE "default",
"invoice_code" varchar COLLATE "default",
"invoice_unit_amount" numeric(10,2),
"invoice_tax_rate" int8,
"invoice_tax" varchar COLLATE "default",
"invoice_total_price" numeric(10,2),
"invoice_upper_case" varchar COLLATE "default",
"invoice_time" timestamp(6),
"remark" varchar COLLATE "default",
"create_code" varchar COLLATE "default",
"create_name" varchar COLLATE "default",
"create_time" timestamp(6),
"last_update_time" timestamp(6),
"is_deleted" bool DEFAULT false,
"last_update_code" varchar COLLATE "default",
"last_update_name" varchar COLLATE "default",
CONSTRAINT "pk_hkp_trade_ord_invoice" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE)
;

ALTER TABLE "public"."t_hkp_trade_ord_invoice" OWNER TO "kfwy";

COMMENT ON TABLE "public"."t_hkp_trade_ord_invoice" IS '发票表';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice"."order_code" IS '订单编号';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice"."invoice_code" IS '发票号';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice"."invoice_unit_amount" IS '金额';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice"."invoice_tax_rate" IS '税率';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice"."invoice_tax" IS '税额';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice"."invoice_total_price" IS '发票总额';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice"."invoice_upper_case" IS '总额大写';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice"."invoice_time" IS '开票日期';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice"."remark" IS '备注';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice"."create_code" IS '创建者编码';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice"."create_name" IS '创建人姓名';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice"."last_update_code" IS '最后修改人编码';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice"."last_update_name" IS '最后修改人姓名';

ALTER TABLE t_hkp_trade_ord_invoice ADD COLUMN order_invoice_code CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_trade_ord_invoice.order_invoice_code IS '订单开票编码';
