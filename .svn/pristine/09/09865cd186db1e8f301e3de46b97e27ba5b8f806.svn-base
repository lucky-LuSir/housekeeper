----房源地址表----

CREATE SEQUENCE seq_hkp_hos_location
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_hos_location
  OWNER TO hkp;

CREATE TABLE t_hkp_hos_location
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_hos_location' :: REGCLASS),
  location_code        CHARACTER VARYING,
  location_alias           CHARACTER VARYING,
  province           CHARACTER VARYING,
  province_name           CHARACTER VARYING,
  city           CHARACTER VARYING,
  city_name           CHARACTER VARYING,
  region           CHARACTER VARYING,
  region_name           CHARACTER VARYING,
  street           CHARACTER VARYING,
  street_name           CHARACTER VARYING,
  detail_address           CHARACTER VARYING,
  door_number           CHARACTER VARYING,
  longitude           numeric(16,6),
  latitude           numeric(16,6),
  traffic_facilities           CHARACTER VARYING,
  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  has_loc_region      BOOLEAN        DEFAULT FALSE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_hos_location PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_hos_location
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_hos_location IS '房源地址表';
COMMENT ON COLUMN t_hkp_hos_location.location_code IS '地址编码';
COMMENT ON COLUMN t_hkp_hos_location.location_alias IS '地址别名';
COMMENT ON COLUMN t_hkp_hos_location.province IS '省份编码';
COMMENT ON COLUMN t_hkp_hos_location.province_name IS '省份名称';
COMMENT ON COLUMN t_hkp_hos_location.city IS '城市编码';
COMMENT ON COLUMN t_hkp_hos_location.city_name IS '城市名称';
COMMENT ON COLUMN t_hkp_hos_location.region IS '区域编码';
COMMENT ON COLUMN t_hkp_hos_location.region_name IS '区域信息';
COMMENT ON COLUMN t_hkp_hos_location.street IS '街道编码';
COMMENT ON COLUMN t_hkp_hos_location.street_name IS '街道信息';
COMMENT ON COLUMN t_hkp_hos_location.detail_address IS '详细地址';
COMMENT ON COLUMN t_hkp_hos_location.door_number IS '详细地址';
COMMENT ON COLUMN t_hkp_hos_location.longitude IS '经度';
COMMENT ON COLUMN t_hkp_hos_location.latitude IS '纬度';
COMMENT ON COLUMN t_hkp_hos_location.traffic_facilities IS '交通设施';
COMMENT ON COLUMN t_hkp_sys_file_relation.cpy_code IS '公司编号';
COMMENT ON COLUMN t_hkp_sys_file_relation.create_code IS '创建人编码';
COMMENT ON COLUMN t_hkp_sys_file_relation.last_update_code IS '修改人编码';
COMMENT ON COLUMN t_hkp_sys_file_relation.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_sys_file_relation.last_update_time IS '最后修改时间';
COMMENT ON COLUMN t_hkp_sys_file_relation.is_deleted IS '是否删除';