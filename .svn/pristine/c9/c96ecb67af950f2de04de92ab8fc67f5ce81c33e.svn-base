----业主拜访表----
CREATE SEQUENCE seq_hkp_hos_houseowner_followup
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_hos_houseowner_followup
  OWNER TO hkp;

CREATE TABLE t_hkp_hos_houseowner_followup
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_hos_houseowner_followup' :: REGCLASS),
  houseowner_code        CHARACTER VARYING,
  followup_type        CHARACTER VARYING,
  followup_context        CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_hos_houseowner_followup PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_hos_houseowner_followup
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_hos_houseowner_followup IS '业主拜访表';
COMMENT ON COLUMN t_hkp_hos_houseowner_followup.houseowner_code IS '业主编码';
COMMENT ON COLUMN t_hkp_hos_houseowner_followup.followup_type IS '跟进类型';
COMMENT ON COLUMN t_hkp_hos_houseowner_followup.followup_context IS '跟进内容';