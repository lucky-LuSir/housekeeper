----兼职表----
CREATE SEQUENCE seq_hkp_pt_parttimer
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_pt_parttimer
  OWNER TO hkp;

CREATE TABLE t_hkp_pt_parttimer
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_pt_parttimer' :: REGCLASS),
  pt_code     CHARACTER VARYING,
  pt_name     CHARACTER VARYING,
  sex     CHARACTER VARYING,
  pt_phone      CHARACTER VARYING,
  positions     CHARACTER VARYING,
  profession      CHARACTER VARYING,
  company         CHARACTER VARYING,
  owner_code     CHARACTER VARYING,
  owner_name     CHARACTER VARYING,
  owner_phone     CHARACTER VARYING,
  owner_dept_code     CHARACTER VARYING,
  owner_dept_name     CHARACTER VARYING,

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
  CONSTRAINT pk_hkp_pt_parttimer PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_pt_parttimer
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_pt_parttimer IS '兼职表';
COMMENT ON COLUMN t_hkp_pt_parttimer.pt_code IS '兼职编码';
COMMENT ON COLUMN t_hkp_pt_parttimer.pt_name IS '兼职姓名';
COMMENT ON COLUMN t_hkp_pt_parttimer.sex IS '性别';
COMMENT ON COLUMN t_hkp_pt_parttimer.pt_phone IS '手机号码';
COMMENT ON COLUMN t_hkp_pt_parttimer.positions IS '职位';
COMMENT ON COLUMN t_hkp_pt_parttimer.profession IS '职业';
COMMENT ON COLUMN t_hkp_pt_parttimer.company IS '公司名称';
COMMENT ON COLUMN t_hkp_pt_parttimer.owner_code IS '所属人员编码';
COMMENT ON COLUMN t_hkp_pt_parttimer.owner_name IS '所属人员';
COMMENT ON COLUMN t_hkp_pt_parttimer.owner_phone IS '所属人员电话';
COMMENT ON COLUMN t_hkp_pt_parttimer.owner_dept_code IS '所属部门编码';
COMMENT ON COLUMN t_hkp_pt_parttimer.owner_dept_name IS '所属部门';
COMMENT ON COLUMN t_hkp_pt_parttimer.remark IS '备注描述';