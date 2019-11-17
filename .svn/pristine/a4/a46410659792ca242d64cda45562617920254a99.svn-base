---------选址报告房源子表---------
CREATE SEQUENCE seq_hkp_crm_select_house
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_crm_select_house
  OWNER TO hkp;

CREATE TABLE t_hkp_crm_select_house
(
  id               BIGINT NOT NULL   DEFAULT nextval('seq_hkp_crm_select_house' :: REGCLASS),
  select_house_code        CHARACTER VARYING,
  select_address_code       CHARACTER VARYING,
  house_code       CHARACTER VARYING,
  house_name       CHARACTER VARYING,
  house_url       CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code  CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN           DEFAULT FALSE,
  CONSTRAINT pk_hkp_crm_select_house PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_crm_select_house
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_crm_select_house IS '选址报告房源子表';
COMMENT ON COLUMN t_hkp_crm_select_house.select_house_code IS '选址房源唯一编码';
COMMENT ON COLUMN t_hkp_crm_select_house.select_address_code IS '选址唯一编码';
COMMENT ON COLUMN t_hkp_crm_select_house.house_code IS '房源编码';
COMMENT ON COLUMN t_hkp_crm_select_house.house_name IS '房源标题名';
COMMENT ON COLUMN t_hkp_crm_select_house.house_url IS '房源详情跳转的url';

COMMENT ON COLUMN t_hkp_crm_select_house.create_code IS '创建人编码';
COMMENT ON COLUMN t_hkp_crm_select_house.last_update_code IS '修改人编码';
COMMENT ON COLUMN t_hkp_crm_select_house.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_crm_select_house.last_update_time IS '最后修改时间';
COMMENT ON COLUMN t_hkp_crm_select_house.is_deleted IS '是否删除';
