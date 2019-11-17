CREATE SEQUENCE seq_hkp_sys_area_area
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_sys_area_area
  OWNER TO hkp;

CREATE TABLE t_hkp_sys_area_area
(
  id               BIGINT NOT NULL   DEFAULT nextval('seq_hkp_sys_area_area' :: REGCLASS),
  area_code        CHARACTER VARYING,
  parent_code      CHARACTER VARYING DEFAULT '0',
  name             CHARACTER VARYING,
  short_name       CHARACTER VARYING,
  latitude         NUMERIC(16, 13),
  longitude        NUMERIC(16, 13),
  level            SMALLINT,
  sort             INT,
  status           CHARACTER VARYING DEFAULT '0',
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN           DEFAULT FALSE,
  CONSTRAINT pk_hkp_sys_area_area PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_sys_area_area
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_sys_area_area IS '区域信息基础表';
COMMENT ON COLUMN t_hkp_sys_area_area.area_code IS '区域编码';
COMMENT ON COLUMN t_hkp_sys_area_area.parent_code IS '父区域编码';
COMMENT ON COLUMN t_hkp_sys_area_area.name IS '区域名称';
COMMENT ON COLUMN t_hkp_sys_area_area.short_name IS '区域名称简称';
COMMENT ON COLUMN t_hkp_sys_area_area.longitude IS '经度坐标';
COMMENT ON COLUMN t_hkp_sys_area_area.latitude IS '纬度坐标';
COMMENT ON COLUMN t_hkp_sys_area_area.level IS '1/2/3/4(省市区镇)';
COMMENT ON COLUMN t_hkp_sys_area_area.sort IS '排序';
COMMENT ON COLUMN t_hkp_sys_area_area.status IS '状态(0禁用1启用)';
COMMENT ON COLUMN t_hkp_sys_area_area.cpy_code IS '企业帐套编码';

---------根据公司相关的区域-----------
CREATE SEQUENCE seq_hkp_sys_area_company
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_sys_area_company
  OWNER TO hkp;

CREATE TABLE t_hkp_sys_area_company
(
  id               BIGINT NOT NULL   DEFAULT nextval('seq_hkp_sys_area_company' :: REGCLASS),
  area_code        CHARACTER VARYING,
  parent_code      CHARACTER VARYING DEFAULT '0',
  name             CHARACTER VARYING,
  short_name       CHARACTER VARYING,
  latitude         NUMERIC(16, 13),
  longitude        NUMERIC(16, 13),
  level            SMALLINT,
  sort             INT,
  status           CHARACTER VARYING DEFAULT '0',
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN           DEFAULT FALSE,
  CONSTRAINT pk_hkp_sys_area_company PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_sys_area_company
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_sys_area_company IS '区域信息表关联到公司';
COMMENT ON COLUMN t_hkp_sys_area_company.area_code IS '区域编码';
COMMENT ON COLUMN t_hkp_sys_area_company.parent_code IS '父区域编码';
COMMENT ON COLUMN t_hkp_sys_area_company.name IS '区域名称';
COMMENT ON COLUMN t_hkp_sys_area_company.short_name IS '区域名称简称';
COMMENT ON COLUMN t_hkp_sys_area_company.longitude IS '经度坐标';
COMMENT ON COLUMN t_hkp_sys_area_company.latitude IS '纬度坐标';
COMMENT ON COLUMN t_hkp_sys_area_company.level IS '1/2/3/4(省市区镇)';
COMMENT ON COLUMN t_hkp_sys_area_company.sort IS '排序';
COMMENT ON COLUMN t_hkp_sys_area_company.status IS '状态(0禁用1启用)';
COMMENT ON COLUMN t_hkp_sys_area_company.cpy_code IS '企业帐套编码';
