-------企业帐套---------------------------------------------------
CREATE SEQUENCE seq_hkp_sys_cpy_company
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_sys_cpy_company
  OWNER TO hkp;

CREATE TABLE t_hkp_sys_cpy_company
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_sys_cpy_company' :: REGCLASS),
  cpy_code         CHARACTER VARYING UNIQUE,
  cpy_name         CHARACTER VARYING,


  remark           CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_sys_cpy_company PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_sys_cpy_company
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_sys_cpy_company IS '企业帐套表';
COMMENT ON COLUMN t_hkp_sys_cpy_company.cpy_code IS '企业帐套编码';
COMMENT ON COLUMN t_hkp_sys_cpy_company.cpy_name IS '公司名称';
COMMENT ON COLUMN t_hkp_sys_cpy_company.remark IS '备注';