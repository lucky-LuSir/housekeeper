---------部门分成比例---------
CREATE SEQUENCE seq_hkp_hrm_org_dept_profit_share
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_hrm_org_dept_profit_share
  OWNER TO hkp;

CREATE TABLE t_hkp_hrm_org_dept_profit_share
(
  id               BIGINT NOT NULL   DEFAULT nextval('seq_hkp_hrm_org_dept_profit_share' :: REGCLASS),

  dept_code        CHARACTER VARYING,
  dept_name        CHARACTER VARYING,

  profit_share_type        CHARACTER VARYING,

  acquire_cus        integer,
  cus_push       integer,

  followup_see       integer,
  order_deal       integer,

  house_develop       integer,
  house_entrust       integer,

  remark           CHARACTER VARYING,
  cpy_code           CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  create_name      CHARACTER VARYING,
  create_dept_name      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN           DEFAULT FALSE,
  CONSTRAINT pk_hkp_hrm_org_dept_profit_share PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_hrm_org_dept_profit_share
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_hrm_org_dept_profit_share IS '部门分成';

COMMENT ON COLUMN t_hkp_hrm_org_dept_profit_share.dept_code IS '部门编码';
COMMENT ON COLUMN t_hkp_hrm_org_dept_profit_share.dept_name IS '部门名称';

COMMENT ON COLUMN t_hkp_hrm_org_dept_profit_share.profit_share_type IS '分成类型';

COMMENT ON COLUMN t_hkp_hrm_org_dept_profit_share.acquire_cus IS '获客分成';
COMMENT ON COLUMN t_hkp_hrm_org_dept_profit_share.cus_push IS '客户推送';

COMMENT ON COLUMN t_hkp_hrm_org_dept_profit_share.followup_see IS '带看';
COMMENT ON COLUMN t_hkp_hrm_org_dept_profit_share.order_deal IS '成交';

COMMENT ON COLUMN t_hkp_hrm_org_dept_profit_share.house_develop IS '房源开发';
COMMENT ON COLUMN t_hkp_hrm_org_dept_profit_share.house_entrust IS '房源委托';

COMMENT ON COLUMN t_hkp_hrm_org_dept_profit_share.remark IS '备注';
COMMENT ON COLUMN t_hkp_hrm_org_dept_profit_share.create_code IS '创建人编码';
COMMENT ON COLUMN t_hkp_hrm_org_dept_profit_share.create_dept_code IS '创建人编码';
COMMENT ON COLUMN t_hkp_hrm_org_dept_profit_share.create_name IS '创建人编码';
COMMENT ON COLUMN t_hkp_hrm_org_dept_profit_share.create_dept_name IS '创建人编码';
COMMENT ON COLUMN t_hkp_hrm_org_dept_profit_share.last_update_code IS '修改人编码';
COMMENT ON COLUMN t_hkp_hrm_org_dept_profit_share.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_hrm_org_dept_profit_share.last_update_time IS '最后修改时间';
COMMENT ON COLUMN t_hkp_hrm_org_dept_profit_share.is_deleted IS '是否删除';