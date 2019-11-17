CREATE SEQUENCE seq_hkp_sys_announcement
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_sys_announcement
  OWNER TO hkp;


CREATE TABLE "public"."t_hkp_sys_announcement" (
"id" int8 DEFAULT nextval('seq_hkp_sys_announcement'::regclass) NOT NULL,
"user_code" varchar COLLATE "default",
"announcement_type" varchar COLLATE "default",
"year_month" varchar COLLATE "default",
"create_code" varchar COLLATE "default",
"create_dept_code" varchar COLLATE "default",
"last_update_code" varchar COLLATE "default",
"create_time" timestamp(6),
"last_update_time" timestamp(6),
"is_deleted" bool DEFAULT false,
CONSTRAINT "pk_hkp_sys_announcement" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE)
;

ALTER TABLE "public"."t_hkp_sys_announcement" OWNER TO "hkp";

COMMENT ON TABLE "public"."t_hkp_sys_announcement" IS '首页榜单表';

COMMENT ON COLUMN "public"."t_hkp_sys_announcement"."user_code" IS '员工编码';

COMMENT ON COLUMN "public"."t_hkp_sys_announcement"."announcement_type" IS '榜单类型';

COMMENT ON COLUMN "public"."t_hkp_sys_announcement"."year_month" IS '年月';

COMMENT ON COLUMN "public"."t_hkp_sys_announcement"."create_code" IS '创建人编码';

COMMENT ON COLUMN "public"."t_hkp_sys_announcement"."last_update_code" IS '修改人编码';

COMMENT ON COLUMN "public"."t_hkp_sys_announcement"."create_time" IS '创建时间';

COMMENT ON COLUMN "public"."t_hkp_sys_announcement"."last_update_time" IS '最后修改时间';

COMMENT ON COLUMN "public"."t_hkp_sys_announcement"."is_deleted" IS '是否删除';