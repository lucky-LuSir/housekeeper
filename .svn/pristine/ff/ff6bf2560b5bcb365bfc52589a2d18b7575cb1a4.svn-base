----房源业主更多联系人表----
CREATE SEQUENCE seq_hkp_crm_houseowner_contacts
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_crm_houseowner_contacts
  OWNER TO hkp;

CREATE TABLE t_hkp_hos_houseowner_contacts
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_crm_houseowner_contacts' :: REGCLASS),
  contact_code        CHARACTER VARYING,
  contact_name        CHARACTER VARYING,
  contact_phone       CHARACTER VARYING,
  contact_sex       CHARACTER VARYING,
  houseowner_code       CHARACTER VARYING,
  house_code      CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_crm_houseowner_contacts PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_hos_houseowner_contacts
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_hos_houseowner_contacts IS '房源业主更多联系人表';
COMMENT ON COLUMN t_hkp_hos_houseowner_contacts.contact_code IS '联系人编码';
COMMENT ON COLUMN t_hkp_hos_houseowner_contacts.contact_name IS '联系人姓名';
COMMENT ON COLUMN t_hkp_hos_houseowner_contacts.contact_phone IS '联系人手机号';
COMMENT ON COLUMN t_hkp_hos_houseowner_contacts.contact_sex IS '联系人性别';
COMMENT ON COLUMN t_hkp_hos_houseowner_contacts.houseowner_code IS '业主编码';
COMMENT ON COLUMN t_hkp_hos_houseowner_contacts.house_code IS '房源编码';

