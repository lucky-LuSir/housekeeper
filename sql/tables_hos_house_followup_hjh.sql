----房源跟进表----
CREATE SEQUENCE seq_hkp_hos_house_followup
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_hos_house_followup
  OWNER TO hkp;

CREATE TABLE t_hkp_hos_house_followup
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_hos_house_followup' :: REGCLASS),
  emp_code        CHARACTER VARYING,
  dept_code        CHARACTER VARYING,
  house_code       CHARACTER VARYING,
  house_name       CHARACTER VARYING,
  house_purpose      CHARACTER VARYING,
  house_address       CHARACTER VARYING,
  followup_context       CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_hos_house_followup PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_hos_house_followup
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_hos_house_followup IS '客户需求区域表';
COMMENT ON COLUMN t_hkp_hos_house_followup.emp_code IS '所有者编码';
COMMENT ON COLUMN t_hkp_hos_house_followup.dept_code IS '部门编码';
COMMENT ON COLUMN t_hkp_hos_house_followup.house_code IS '房源编码';
COMMENT ON COLUMN t_hkp_hos_house_followup.house_name IS '房源名称';
COMMENT ON COLUMN t_hkp_hos_house_followup.house_purpose IS '房源类型';
COMMENT ON COLUMN t_hkp_hos_house_followup.house_address IS '房源地址';
COMMENT ON COLUMN t_hkp_hos_house_followup.followup_context IS '跟进内容';

