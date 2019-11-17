CREATE SEQUENCE seq_hkp_trade_ord_invoice_apply
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_trade_ord_invoice_apply
  OWNER TO hkp;


CREATE TABLE "public"."t_hkp_trade_ord_invoice_apply" (
"id" int8 DEFAULT nextval('seq_hkp_trade_ord_invoice_apply'::regclass) NOT NULL,
"order_code" varchar COLLATE "default",
"invoice_code" varchar COLLATE "default",
"invoice_unit_amount" numeric(10,2),
"invoice_tax_rate" int8,
"invoice_tax" varchar COLLATE "default",
"invoice_total_price" numeric(10,2),
"invoice_upper_case" varchar COLLATE "default",
"invoice_time" timestamp(6),

"cpy_code" CHARACTER VARYING,
"cpy_name"  CHARACTER VARYING,
"consignee_name"   CHARACTER VARYING,
"consignee_address"   CHARACTER VARYING,
"consignee_phone"   CHARACTER VARYING,
"parent_dept_code"   CHARACTER VARYING,
"dept_code"   CHARACTER VARYING,
"parent_dept_name"   CHARACTER VARYING,
"dept_name"   CHARACTER VARYING,
"phone"   CHARACTER VARYING,
"address"   CHARACTER VARYING,
"bank_branch"  CHARACTER VARYING,
"bank_card"   CHARACTER VARYING,
"credit_code"   CHARACTER VARYING,
"apply_code" CHARACTER VARYING,
"apply_name" CHARACTER VARYING,

"remark" varchar COLLATE "default",
"create_code" varchar COLLATE "default",
"create_name" varchar COLLATE "default",
"create_time" timestamp(6),
"last_update_time" timestamp(6),
"is_deleted" bool DEFAULT false,
"last_update_code" varchar COLLATE "default",
"last_update_name" varchar COLLATE "default",
CONSTRAINT "pk_hkp_trade_ord_invoice_apply" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE)
;

ALTER TABLE "public"."t_hkp_trade_ord_invoice_apply"   OWNER TO hkp;

COMMENT ON TABLE "public"."t_hkp_trade_ord_invoice_apply" IS '发票申请表';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice_apply"."order_code" IS '订单编号';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice_apply"."invoice_code" IS '发票号';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice_apply"."invoice_unit_amount" IS '金额';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice_apply"."invoice_tax_rate" IS '税率';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice_apply"."invoice_tax" IS '税额';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice_apply"."invoice_total_price" IS '发票总额';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice_apply"."invoice_upper_case" IS '总额大写';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice_apply"."invoice_time" IS '开票日期';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice_apply"."remark" IS '备注';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice_apply"."create_code" IS '创建者编码';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice_apply"."create_name" IS '创建人姓名';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice_apply"."last_update_code" IS '最后修改人编码';

COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice_apply"."last_update_name" IS '最后修改人姓名';


COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice_apply"."apply_code" IS '申请人编码';
COMMENT ON COLUMN "public"."t_hkp_trade_ord_invoice_apply"."apply_name" IS '申请人姓名';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice_apply.cpy_code IS  '申请开票公司编码';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice_apply.cpy_name IS  '申请开票公司名称';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice_apply.consignee_name IS  '收件人名称';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice_apply.consignee_address IS  '收件人地址';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice_apply.consignee_phone IS  '收件人电话';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice_apply.parent_dept_code IS  '申请人所属大区';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice_apply.dept_code IS  '申请人分部';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice_apply.parent_dept_name IS  '申请人所属大区name';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice_apply.dept_name IS  '申请人分部name';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice_apply.phone IS  '申请开票公司电话';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice_apply.address IS  '申请开票公司地址';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice_apply.bank_branch IS  '申请开票公司银行支行信息';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice_apply.bank_card IS  '申请开票公司银行账号';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice_apply.credit_code IS  '社会统一信用代码（纳税人识别号）';


ALTER TABLE t_hkp_trade_ord_invoice_apply ADD COLUMN order_invoice_code CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_trade_ord_invoice_apply.order_invoice_code IS '订单开票编码';

ALTER TABLE t_hkp_trade_ord_invoice_apply ADD COLUMN apply_status CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_trade_ord_invoice_apply.apply_status IS '订单开票状态';