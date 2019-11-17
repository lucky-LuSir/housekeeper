----房源表----
CREATE SEQUENCE seq_hkp_hos_house
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_hos_house
  OWNER TO hkp;

CREATE TABLE t_hkp_hos_house
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_hos_house' :: REGCLASS),
  house_code        CHARACTER VARYING,
  house_name        CHARACTER VARYING,
  house_type       CHARACTER VARYING,
  house_status       CHARACTER VARYING,
  house_purpose       CHARACTER VARYING,
  house_style       CHARACTER VARYING,
  location_code       CHARACTER VARYING,
  owner_code       CHARACTER VARYING,
  emp_code       CHARACTER VARYING,
  has_alone_building      BOOLEAN,
  where_building      CHARACTER VARYING,
  has_monolayer      BOOLEAN,
  several_layers     CHARACTER VARYING,
  where_layer     CHARACTER VARYING,
  has_eia     BOOLEAN,
  has_cut     BOOLEAN,
  has_certificate     BOOLEAN,
  has_registry     BOOLEAN,
  has_short_lease      BOOLEAN,
  has_parking      BOOLEAN,
  has_platform      BOOLEAN,
  has_elevator      BOOLEAN,
  has_install_crane      BOOLEAN,
  has_bathroom      BOOLEAN,
  has_office_area      BOOLEAN,
  open_flag      BOOLEAN,
  has_discharge_sewage      BOOLEAN,
  area           numeric(16,6),
  layer_area           numeric(16,6),
  price           numeric(16,6),
  unit           CHARACTER VARYING,
  layer_height           numeric(16,6),
  property_fee           numeric(16,6),
  less_lease           integer,
  more_lease           integer,
  max_electric           integer,
  enter_time           TIMESTAMP WITHOUT TIME ZONE,
  fire_level           CHARACTER VARYING,
  house_structure           CHARACTER VARYING,
  industry_restriction           CHARACTER VARYING,
  for_insdustry           CHARACTER VARYING,
  features           CHARACTER VARYING,
  province           CHARACTER VARYING,
  province_name           CHARACTER VARYING,
  city           CHARACTER VARYING,
  city_name           CHARACTER VARYING,
  region           CHARACTER VARYING,
  region_name           CHARACTER VARYING,
  street           CHARACTER VARYING,
  street_name           CHARACTER VARYING,
  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_hos_house PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_hos_house
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_hos_house IS '房源表';
/*房源信息*/
COMMENT ON COLUMN t_hkp_hos_house.house_code IS '房源编码';
COMMENT ON COLUMN t_hkp_hos_house.house_name IS '房源名称';
COMMENT ON COLUMN t_hkp_hos_house.house_type IS '房源类型';
COMMENT ON COLUMN t_hkp_hos_house.house_status IS '房源状态';
COMMENT ON COLUMN t_hkp_hos_house.house_purpose IS '房源用途';
COMMENT ON COLUMN t_hkp_hos_house.house_style IS '房源风格';
COMMENT ON COLUMN t_hkp_hos_house.location_code IS '房源地址编码';
COMMENT ON COLUMN t_hkp_hos_house.owner_code IS '业主编码';
COMMENT ON COLUMN t_hkp_hos_house.emp_code IS '所属者编码';
COMMENT ON COLUMN t_hkp_hos_house.has_alone_building IS '是否独栋';
COMMENT ON COLUMN t_hkp_hos_house.where_building IS '所在栋';
COMMENT ON COLUMN t_hkp_hos_house.has_monolayer IS '是否单层';
COMMENT ON COLUMN t_hkp_hos_house.several_layers IS '共几层';
COMMENT ON COLUMN t_hkp_hos_house.where_layer IS '所在层';
COMMENT ON COLUMN t_hkp_hos_house.has_eia IS '是否可环评';
COMMENT ON COLUMN t_hkp_hos_house.has_cut IS '是否可分割';
COMMENT ON COLUMN t_hkp_hos_house.has_certificate IS '是否有证书';
COMMENT ON COLUMN t_hkp_hos_house.has_registry IS '是否可注册';
COMMENT ON COLUMN t_hkp_hos_house.has_short_lease IS '是否可短租';
COMMENT ON COLUMN t_hkp_hos_house.has_parking IS '是否有停车位';
COMMENT ON COLUMN t_hkp_hos_house.has_platform IS '是否有货台';
COMMENT ON COLUMN t_hkp_hos_house.has_elevator IS '是否有货梯';
COMMENT ON COLUMN t_hkp_hos_house.has_install_crane IS '是否有行车';
COMMENT ON COLUMN t_hkp_hos_house.has_bathroom IS '有无卫生间';
COMMENT ON COLUMN t_hkp_hos_house.has_office_area IS '有无办公区域';
COMMENT ON COLUMN t_hkp_hos_house.open_flag IS '是否公开';
COMMENT ON COLUMN t_hkp_hos_house.has_discharge_sewage IS '有无排污证';
COMMENT ON COLUMN t_hkp_hos_house.area IS '可租面积';
COMMENT ON COLUMN t_hkp_hos_house.layer_area IS '层面积';
COMMENT ON COLUMN t_hkp_hos_house.price IS '价格';
COMMENT ON COLUMN t_hkp_hos_house.unit IS '价格单位';
COMMENT ON COLUMN t_hkp_hos_house.layer_height IS '层高';
COMMENT ON COLUMN t_hkp_hos_house.property_fee IS '物业费';
COMMENT ON COLUMN t_hkp_hos_house.less_lease IS '最短租期';
COMMENT ON COLUMN t_hkp_hos_house.more_lease IS '最长租期';
COMMENT ON COLUMN t_hkp_hos_house.max_electric IS '最大电量';
COMMENT ON COLUMN t_hkp_hos_house.more_lease IS '最长租期';
COMMENT ON COLUMN t_hkp_hos_house.enter_time IS '入住时间';
COMMENT ON COLUMN t_hkp_hos_house.fire_level IS '消防等级';
COMMENT ON COLUMN t_hkp_hos_house.house_structure IS '房屋结构';
COMMENT ON COLUMN t_hkp_hos_house.industry_restriction IS '行业限制';
COMMENT ON COLUMN t_hkp_hos_house.for_insdustry IS '行业特殊';
COMMENT ON COLUMN t_hkp_hos_house.features IS '房源特色';
COMMENT ON COLUMN t_hkp_hos_house.province IS '省份编码';
COMMENT ON COLUMN t_hkp_hos_house.province_name IS '省份名称';
COMMENT ON COLUMN t_hkp_hos_house.city IS '城市编码';
COMMENT ON COLUMN t_hkp_hos_house.city_name IS '城市名称';
COMMENT ON COLUMN t_hkp_hos_house.region IS '区域编码';
COMMENT ON COLUMN t_hkp_hos_house.region_name IS '区域信息';
COMMENT ON COLUMN t_hkp_hos_house.street IS '街道编码';
COMMENT ON COLUMN t_hkp_hos_house.street_name IS '街道信息';
COMMENT ON COLUMN t_hkp_sys_file_relation.cpy_code IS '公司编号';
COMMENT ON COLUMN t_hkp_sys_file_relation.create_code IS '创建人编码';
COMMENT ON COLUMN t_hkp_sys_file_relation.last_update_code IS '修改人编码';
COMMENT ON COLUMN t_hkp_sys_file_relation.create_time IS '创建时间';
COMMENT ON COLUMN t_hkp_sys_file_relation.last_update_time IS '最后修改时间';
COMMENT ON COLUMN t_hkp_sys_file_relation.is_deleted IS '是否删除';
/*  兼职新增字段 */
ALTER TABLE t_hkp_hos_house ADD COLUMN pt_code CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_hos_house.pt_code IS '兼职编码';
ALTER TABLE t_hkp_hos_house ADD COLUMN pt_name CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_hos_house.pt_name IS '兼职名称';
ALTER TABLE t_hkp_hos_house ADD COLUMN divide_type CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_hos_house.divide_type IS '分成类型';
ALTER TABLE t_hkp_hos_house ADD COLUMN divide_ratio NUMERIC (16, 6);
COMMENT ON COLUMN t_hkp_hos_house.divide_ratio IS '比例';
ALTER TABLE t_hkp_hos_house ADD COLUMN divide_cash NUMERIC (16, 6);
COMMENT ON COLUMN t_hkp_hos_house.divide_cash IS '现金';
ALTER TABLE t_hkp_hos_house ADD COLUMN house_from CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_hos_house.house_from IS '房源来源';
ALTER TABLE t_hkp_hos_house ADD COLUMN lease_expiration TIMESTAMP WITHOUT TIME ZONE;
COMMENT ON COLUMN t_hkp_hos_house.lease_expiration IS '租赁到期时间';


ALTER TABLE t_hkp_hos_house ADD COLUMN batch_code  varchar COLLATE "default";
COMMENT ON COLUMN "public"."t_hkp_hos_house"."batch_code" IS '批次编码';