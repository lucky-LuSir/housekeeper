CREATE SEQUENCE seq_hkp_crm_customer_updown
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_crm_customer_updown OWNER TO hkp;
CREATE TABLE "public"."t_hkp_crm_customer_updown" (
"id" int8 DEFAULT nextval('seq_hkp_crm_customer_updown'::regclass) NOT NULL,
"cus_code" varchar COLLATE "default",
"cus_down_type" varchar COLLATE "default",
"cus_updown_type" varchar COLLATE "default",
"cus_down_reason" varchar COLLATE "default",
"province" varchar COLLATE "default",
"city" varchar COLLATE "default",
"region" varchar COLLATE "default",
"province_name" varchar COLLATE "default",
"city_name" varchar COLLATE "default",
"region_name" varchar COLLATE "default",
"deal_people" varchar COLLATE "default",
"emp_code" varchar COLLATE "default",
"emp_name" varchar COLLATE "default",
"cus_emp_code" varchar COLLATE "default",
"cus_emp_name" varchar COLLATE "default",
"create_code" varchar COLLATE "default",
"create_name" varchar COLLATE "default",
"create_dept_code" varchar COLLATE "default",
"create_dept_name" varchar COLLATE "default",
"is_deleted" bool DEFAULT false,
"create_time" timestamp(6),
"last_update_time" timestamp(6),
"contract_start_time" timestamp(6),
"contract_end_time" timestamp(6),
CONSTRAINT "t_hkp_customer_updown_pkey" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE)
;

ALTER TABLE "public"."t_hkp_crm_customer_updown" OWNER TO "hkp";

COMMENT ON TABLE "public"."t_hkp_crm_customer_updown" IS '客户上下架';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."id" IS '客户上下架ID';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."cus_code" IS '客户编号';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."cus_down_type" IS '下架类型(内部成交，外部成交，不租了)';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."cus_updown_type" IS '类型(上架，下架)';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."cus_down_reason" IS '下架原因';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."province" IS '省份编号';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."city" IS '城市编号';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."region" IS '区域编号';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."province_name" IS '省份名称';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."city_name" IS '城市名称';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."region_name" IS '区域名称';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."deal_people" IS '被谁成交';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."emp_code" IS '上下架员工编号';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."emp_name" IS '上下架员工姓名';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."cus_emp_code" IS '上架或者下架的时候客户所属人编号';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."cus_emp_name" IS '上架或者下架的时候客户所属人姓名';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."create_code" IS '创建员工编码';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."create_name" IS '创建员工名字';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."create_dept_code" IS '创建部门编号';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."create_dept_name" IS '创建部门名称';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."is_deleted" IS '是否已删除';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."create_time" IS '创建时间';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."last_update_time" IS '最后更新时间';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."contract_start_time" IS '合同开始';

COMMENT ON COLUMN "public"."t_hkp_crm_customer_updown"."contract_end_time" IS '合同结束';