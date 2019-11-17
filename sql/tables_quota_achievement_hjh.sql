/*业绩指标表*/
CREATE SEQUENCE seq_hkp_quota_achievement
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_quota_achievement
  OWNER TO hkp;

CREATE TABLE t_hkp_quota_achievement
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_quota_achievement' :: REGCLASS),
  qa_time   CHARACTER VARYING,
  quota_type  INT,
  owner_code   CHARACTER VARYING,
  hos_value   INT,
  hos_auth_value   INT,
  hos_follow_value   INT,
  cus_value   INT,
  cus_follow_value   INT,
  cus_effective_value  INT,
  pt_value  INT,
  see_value   INT,
  negotiation   INT DEFAULT 0,
  owner_follow_value   INT,
  signing   INT,
  total_money_value  numeric(20,2),

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_name      CHARACTER VARYING,
  create_dept_code CHARACTER VARYING,
  create_dept_name CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_quota_achievement PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_quota_achievement
  OWNER TO hkp;
COMMENT ON TABLE public.t_hkp_quota_achievement IS '业绩指标信息表';
COMMENT ON COLUMN public.t_hkp_quota_achievement.qa_time IS '指标指定时间';
COMMENT ON COLUMN public.t_hkp_quota_achievement.quota_type IS '指标类型,1:部门指标,2:个人指标';
COMMENT ON COLUMN public.t_hkp_quota_achievement.owner_code IS '所属者编码';
COMMENT ON COLUMN public.t_hkp_quota_achievement.hos_value IS '新增房源指标';
COMMENT ON COLUMN public.t_hkp_quota_achievement.hos_auth_value IS '签委托房源指标';
COMMENT ON COLUMN public.t_hkp_quota_achievement.hos_follow_value IS '房源跟进指标';
COMMENT ON COLUMN public.t_hkp_quota_achievement.cus_value IS '客户指标';
COMMENT ON COLUMN public.t_hkp_quota_achievement.cus_follow_value IS '客户跟进指标';
COMMENT ON COLUMN public.t_hkp_quota_achievement.cus_effective_value IS '有效客户指标';
COMMENT ON COLUMN public.t_hkp_quota_achievement.pt_value IS '新增兼职指标';
COMMENT ON COLUMN public.t_hkp_quota_achievement.see_value IS '带看指标';
COMMENT ON COLUMN public.t_hkp_quota_achievement.negotiation IS '谈判次数';
COMMENT ON COLUMN public.t_hkp_quota_achievement.owner_follow_value IS '业主拜访指标';
COMMENT ON COLUMN public.t_hkp_quota_achievement.signing IS '签约指标';
COMMENT ON COLUMN public.t_hkp_quota_achievement.total_money_value IS '业绩指标';
