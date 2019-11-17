----部门区域表----
CREATE SEQUENCE seq_hkp_hrm_org_dept_area
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_hrm_org_dept_area
  OWNER TO hkp;

CREATE TABLE t_hkp_hrm_org_dept_area
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_hrm_org_dept_area' :: REGCLASS),
  dept_code        CHARACTER VARYING,
  province_code    CHARACTER VARYING,
  city_code        CHARACTER VARYING,
  region_code      CHARACTER VARYING,
  street_code      CHARACTER VARYING,
  province_name    CHARACTER VARYING,
  city_name        CHARACTER VARYING,
  region_name      CHARACTER VARYING,
  street_name      CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_t_hkp_hrm_org_dept_area PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

ALTER TABLE t_hkp_hrm_org_dept_area
  OWNER TO hkp;

COMMENT ON TABLE  t_hkp_hrm_org_dept_area IS '部门负责区域';
COMMENT ON COLUMN t_hkp_hrm_org_dept_area.id IS '数据库ID';
COMMENT ON COLUMN t_hkp_hrm_org_dept_area.dept_code IS '部门编号';
COMMENT ON COLUMN t_hkp_hrm_org_dept_area.province_name IS '省名称';
COMMENT ON COLUMN t_hkp_hrm_org_dept_area.province_code IS '省编号';
COMMENT ON COLUMN t_hkp_hrm_org_dept_area.city_code IS '市编号';
COMMENT ON COLUMN t_hkp_hrm_org_dept_area.city_name IS '市名称';
COMMENT ON COLUMN t_hkp_hrm_org_dept_area.region_code IS '街道编号';
COMMENT ON COLUMN t_hkp_hrm_org_dept_area.region_name IS '街道名称';
COMMENT ON COLUMN t_hkp_hrm_org_dept_area.street_code IS '街道编码';
COMMENT ON COLUMN t_hkp_hrm_org_dept_area.street_name IS '街道名称';
