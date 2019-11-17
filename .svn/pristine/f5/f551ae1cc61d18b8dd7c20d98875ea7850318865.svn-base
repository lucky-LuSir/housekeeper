CREATE SEQUENCE seq_hkp_crm_cus_house
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_crm_cus_house OWNER TO hkp;
CREATE TABLE "public"."t_hkp_crm_cus_house" (
	"id" int8 DEFAULT nextval(
		'seq_hkp_crm_cus_house' :: regclass
	) NOT NULL,
	"cus_code" VARCHAR COLLATE "default",
	"house_code" VARCHAR COLLATE "default",
	"followup_type" int2,
	"remark" VARCHAR COLLATE "default",
	"create_code" VARCHAR COLLATE "default",
	"create_time" TIMESTAMP (6),
	"last_update_time" TIMESTAMP (6),
	"followup_code" VARCHAR COLLATE "default",
	"is_deleted" bool,
	"create_dept_code" VARCHAR COLLATE "default",
	"acreage" NUMERIC (15, 5),
	"price" NUMERIC (15, 5),
	"unit" VARCHAR COLLATE "default",
	"area_size" int4,
	"is_location_appropriate" bool,
	"is_floor_appropriate" bool,
	"is_power_appropriate" bool,
	"is_eia" bool,
	"is_tax" bool,
	"is_accommodation" bool,
	"is_traffic" bool,
	"is_rent" bool,
	"is_period" bool,
	CONSTRAINT "t_hkp_crm_cus_house_pkey" PRIMARY KEY ("id")
) WITH (OIDS = FALSE);

ALTER TABLE "public"."t_hkp_crm_cus_house" OWNER TO "hkp";

COMMENT ON TABLE "public"."t_hkp_crm_cus_house" IS '客户看房明细表';

COMMENT ON COLUMN "public"."t_hkp_crm_cus_house"."cus_code" IS '房源编号';

COMMENT ON COLUMN "public"."t_hkp_crm_cus_house"."followup_type" IS '带看类型';

COMMENT ON COLUMN "public"."t_hkp_crm_cus_house"."remark" IS '跟进结果';

COMMENT ON COLUMN "public"."t_hkp_crm_cus_house"."area_size" IS '面积大小';

COMMENT ON COLUMN "public"."t_hkp_crm_cus_house"."is_location_appropriate" IS '位置是否合适';

COMMENT ON COLUMN "public"."t_hkp_crm_cus_house"."is_floor_appropriate" IS '层高是否合适';

COMMENT ON COLUMN "public"."t_hkp_crm_cus_house"."is_power_appropriate" IS '用电量要求';

COMMENT ON COLUMN "public"."t_hkp_crm_cus_house"."is_eia" IS '环评要求';

COMMENT ON COLUMN "public"."t_hkp_crm_cus_house"."is_tax" IS '税收政策要求';

COMMENT ON COLUMN "public"."t_hkp_crm_cus_house"."is_accommodation" IS '食宿要求';

COMMENT ON COLUMN "public"."t_hkp_crm_cus_house"."is_traffic" IS '交通要求';

COMMENT ON COLUMN "public"."t_hkp_crm_cus_house"."is_rent" IS '租金接受';

COMMENT ON COLUMN "public"."t_hkp_crm_cus_house"."is_period" IS '租期要求';

CREATE INDEX "idx_t_hkp_cus_house_create_code" ON "public"."t_hkp_crm_cus_house" USING btree (create_code);

CREATE INDEX "idx_t_hkp_cus_house_cus_code" ON "public"."t_hkp_crm_cus_house" USING btree (cus_code);

CREATE INDEX "idx_t_hkp_cus_house_followup_code" ON "public"."t_hkp_crm_cus_house" USING btree (followup_code);

CREATE INDEX "t_hkp_crm_cus_house_house_code" ON "public"."t_hkp_crm_cus_house" USING btree (house_code);