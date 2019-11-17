----房源地址表----

CREATE SEQUENCE seq_hkp_hos_location_region
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_hos_location_region
  OWNER TO hkp;

CREATE TABLE t_hkp_hos_location_region
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_hos_location_region' :: REGCLASS),
  location_code        CHARACTER VARYING,
  location_alias           CHARACTER VARYING,
  num                 integer,
  longitude           numeric(16,6),
  latitude           numeric(16,6),
  region_batch_code        CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_hos_location_region PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_hos_location_region
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_hos_location_region IS '房源地址多边形区域表';
COMMENT ON COLUMN t_hkp_hos_location_region.location_code IS '地址编码';
COMMENT ON COLUMN t_hkp_hos_location_region.location_alias IS '地址别名';
COMMENT ON COLUMN t_hkp_hos_location_region.num IS '点顺序号';
COMMENT ON COLUMN t_hkp_hos_location_region.longitude IS '经度';
COMMENT ON COLUMN t_hkp_hos_location_region.latitude IS '纬度';
COMMENT ON COLUMN t_hkp_hos_location_region.region_batch_code IS '多边形批次编码';

COMMENT ON COLUMN t_hkp_hos_location_region.cpy_code IS '公司编号';
COMMENT ON COLUMN t_hkp_hos_location_region.create_code IS '创建人编码';
COMMENT ON COLUMN t_hkp_hos_location_region.last_update_code IS '修改人编码';
COMMENT ON COLUMN t_hkp_hos_location_region.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_hos_location_region.last_update_time IS '最后修改时间';
COMMENT ON COLUMN t_hkp_hos_location_region.is_deleted IS '是否删除';