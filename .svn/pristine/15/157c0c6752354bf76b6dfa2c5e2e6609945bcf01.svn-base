----客户跟进房源表----
CREATE SEQUENCE seq_hkp_crm_followup_house
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_crm_followup_house
  OWNER TO hkp;

CREATE TABLE t_hkp_crm_followup_house
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_crm_followup_house' :: REGCLASS),
  followup_code        CHARACTER VARYING,
  house_code        CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_crm_followup_house PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_crm_followup_house
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_crm_followup_house IS '客户跟进房源表';
COMMENT ON COLUMN t_hkp_crm_followup_house.followup_code IS '跟进编码';
COMMENT ON COLUMN t_hkp_crm_followup_house.house_code IS '房源编码';