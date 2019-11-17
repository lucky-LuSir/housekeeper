
-----------------********************* 部门管理表  begin     **************************----------------------

CREATE SEQUENCE seq_hkp_hrm_org_dept
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_hrm_org_dept
  OWNER TO hkp;

DROP TABLE IF EXISTS t_hkp_hrm_org_dept;
CREATE TABLE t_hkp_hrm_org_dept
(
  id                     BIGINT NOT NULL DEFAULT nextval('seq_hkp_hrm_org_dept'::regclass),
  dept_code             CHARACTER VARYING,
  dept_name             CHARACTER VARYING,
  parent_code     CHARACTER VARYING,
  parent_name     CHARACTER VARYING,
  dept_type             CHARACTER VARYING,
  dept_level             CHARACTER VARYING,
  leader_code       CHARACTER VARYING,
  leader_name       CHARACTER VARYING,
  dept_address         CHARACTER VARYING,

  -- 基础字段,
  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_hrm_org_dept PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;

CREATE UNIQUE INDEX dept_code ON t_hkp_hrm_org_dept (dept_code);
CREATE INDEX parent_code ON t_hkp_hrm_org_dept(parent_code);
CREATE INDEX leader_code ON t_hkp_hrm_org_dept(leader_code);

ALTER TABLE t_hkp_hrm_org_dept
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_hrm_org_dept IS '部门管理表';
COMMENT ON COLUMN t_hkp_hrm_org_dept.dept_code IS '部门编号';
COMMENT ON COLUMN t_hkp_hrm_org_dept.dept_name IS '部门名称';
COMMENT ON COLUMN t_hkp_hrm_org_dept.dept_phone IS '部门电话';
COMMENT ON COLUMN t_hkp_hrm_org_dept.parent_code IS '上级部门编码';
COMMENT ON COLUMN t_hkp_hrm_org_dept.parent_name IS '上级部门名称';
COMMENT ON COLUMN t_hkp_hrm_org_dept.dept_type IS '部门类型';
COMMENT ON COLUMN t_hkp_hrm_org_dept.dept_level IS '部门级别';
COMMENT ON COLUMN t_hkp_hrm_org_dept.leader_code IS '部门负责人编码';
COMMENT ON COLUMN t_hkp_hrm_org_dept.leader_name IS '部门负责人名称';
COMMENT ON COLUMN t_hkp_hrm_org_dept.leader_phone IS '部门领导人电话';

COMMENT ON COLUMN t_hkp_hrm_org_dept.dept_address IS '详细地址';
COMMENT ON COLUMN t_hkp_hrm_org_dept.dept_synopsis IS '部门介绍';


-----------------********************* 部门管理表表  end     **************************----------------------
insert into t_hkp_hrm_org_dept(dept_code, dept_name, parent_dept_code, parent_dept_name, dept_type, dept_head_code, dept_head_name, province, province_name, city, city_name, region, region_name, street, street_name, dept_phone, dept_head_phone, dept_synopsis, address, create_time, last_update_time)  values ('DP201802020002704d','深圳宝安沙井分部','DP201802020001e0f3','华南大区','1','E201607180001','任友茂','广东省','440000','深圳市','440300','宝安区','440306', '', '','13735541805','13735541805', '', '',now(),now());
insert into t_hkp_hrm_org_dept(dept_code, dept_name, parent_dept_code, parent_dept_name, dept_type, dept_head_code, dept_head_name, province, province_name, city, city_name, region, region_name, street, street_name, dept_phone, dept_head_phone, dept_synopsis, address, create_time, last_update_time)  values ('DP20180515000126ad','产品及运维组','DP201605260001','IT管理部','1','E201605250001','陈伟','上海','310000','上海市','310100','静安区','310106', '', '','13399649225','13399649225', '', '',now(),now());
insert into t_hkp_hrm_org_dept(dept_code, dept_name, parent_dept_code, parent_dept_name, dept_type, dept_head_code, dept_head_name, province, province_name, city, city_name, region, region_name, street, street_name, dept_phone, dept_head_phone, dept_synopsis, address, create_time, last_update_time)  values ('DP201803280001bc43','东莞长安分部','DP201802020001e0f3','华南大区','1','E201711210003f178','刘晓宇','广东省','440000','东莞市','441900','长安镇','441901119', '', '','17621310717','17621310717', '', '',now(),now());
insert into t_hkp_hrm_org_dept(dept_code, dept_name, parent_dept_code, parent_dept_name, dept_type, dept_head_code, dept_head_name, province, province_name, city, city_name, region, region_name, street, street_name, dept_phone, dept_head_phone, dept_synopsis, address, create_time, last_update_time)  values ('DP201805150003ef51','ERP开发组','DP201605260001','IT管理部','1','E20171020000420fe','陈杰',null,null,null,null,null,null, '', '','17621010814','17621010814', '', '',now(),now());
insert into t_hkp_hrm_org_dept(dept_code, dept_name, parent_dept_code, parent_dept_name, dept_type, dept_head_code, dept_head_name, province, province_name, city, city_name, region, region_name, street, street_name, dept_phone, dept_head_phone, dept_synopsis, address, create_time, last_update_time)  values ('DP201805150002b875','官网开发组','DP201605260001','IT管理部','1','E201710200005a88b','郑俊朋',null,null,null,null,null,null, '', '','13523549323','13523549323', '', '',now(),now());
insert into t_hkp_hrm_org_dept(dept_code, dept_name, parent_dept_code, parent_dept_name, dept_type, dept_head_code, dept_head_name, province, province_name, city, city_name, region, region_name, street, street_name, dept_phone, dept_head_phone, dept_synopsis, address, create_time, last_update_time)  values ('DP2018051500040622','园区运营系统开发组','DP201605260001','IT管理部','1','E201712040001cedd','刘正阳',null,null,null,null,null,null, '', '','17621511366','17621511366', '', '',now(),now());
insert into t_hkp_hrm_org_dept(dept_code, dept_name, parent_dept_code, parent_dept_name, dept_type, dept_head_code, dept_head_name, province, province_name, city, city_name, region, region_name, street, street_name, dept_phone, dept_head_phone, dept_synopsis, address, create_time, last_update_time)  values ('DP201605270001','CEO','',null,'1','E201603310001','康波',null,null,null,null,null,null, '', '','18917995991','18917995991', '', '',now(),now());
insert into t_hkp_hrm_org_dept(dept_code, dept_name, parent_dept_code, parent_dept_name, dept_type, dept_head_code, dept_head_name, province, province_name, city, city_name, region, region_name, street, street_name, dept_phone, dept_head_phone, dept_synopsis, address, create_time, last_update_time)  values ('DP201802020001e0f3','华南大区','DP201607040005','企业招商选址事业部','1','E201606140002','王建成','广东省','440000','深圳市','440300','宝安区','440306', '', '','18355312283','18355312283', '', '',now(),now());
insert into t_hkp_hrm_org_dept(dept_code, dept_name, parent_dept_code, parent_dept_name, dept_type, dept_head_code, dept_head_name, province, province_name, city, city_name, region, region_name, street, street_name, dept_phone, dept_head_phone, dept_synopsis, address, create_time, last_update_time)  values ('DP2018051600011284','深圳龙华分部','DP201802020001e0f3','华南大区','1','E2017092500035716','吴玉书','广东省','440000','深圳市','440300','龙华新区','440323', '', '','13167011606','13167011606', '', '',now(),now());
insert into t_hkp_hrm_org_dept(dept_code, dept_name, parent_dept_code, parent_dept_name, dept_type, dept_head_code, dept_head_name, province, province_name, city, city_name, region, region_name, street, street_name, dept_phone, dept_head_phone, dept_synopsis, address, create_time, last_update_time)  values ('DP20180526000116be','深圳宝安石岩分部','DP201802020001e0f3','华南大区','1','E2018050200091982','朱旭','广东省','440000','深圳市','440300','宝安区','440306', '', '','13728824902','13728824902', '', '',now(),now());
insert into t_hkp_hrm_org_dept(dept_code, dept_name, parent_dept_code, parent_dept_name, dept_type, dept_head_code, dept_head_name, province, province_name, city, city_name, region, region_name, street, street_name, dept_phone, dept_head_phone, dept_synopsis, address, create_time, last_update_time)  values ('DP201605260001','IT管理部','DP201605270001','CEO','1','E2015112000001','寸代永','上海','310000','上海市','310100','徐汇区','310104', '', '','13917182631','13917182631', '', '',now(),now());
insert into t_hkp_hrm_org_dept(dept_code, dept_name, parent_dept_code, parent_dept_name, dept_type, dept_head_code, dept_head_name, province, province_name, city, city_name, region, region_name, street, street_name, dept_phone, dept_head_phone, dept_synopsis, address, create_time, last_update_time)  values ('DP201607040005','企业招商选址事业部','DP201605270001','CEO','1','E201602160002','李宝印',null,null,null,null,null,null, '', '','13681673714','13681673714', '', '',now(),now());
insert into t_hkp_hrm_org_dept(dept_code, dept_name, parent_dept_code, parent_dept_name, dept_type, dept_head_code, dept_head_name, province, province_name, city, city_name, region, region_name, street, street_name, dept_phone, dept_head_phone, dept_synopsis, address, create_time, last_update_time)  values ('DP201805170002617b','深圳宝安松岗分部','DP201802020001e0f3','华南大区','1','E201707110001295c','陈云飞','广东省','440000','深圳市','440300','宝安区','440306', '', '','18848950070','18848950070', '', '',now(),now());
insert into t_hkp_hrm_org_dept(dept_code, dept_name, parent_dept_code, parent_dept_name, dept_type, dept_head_code, dept_head_name, province, province_name, city, city_name, region, region_name, street, street_name, dept_phone, dept_head_phone, dept_synopsis, address, create_time, last_update_time)  values ('DP20180726000148f3','深圳公明分部','DP201802020001e0f3','华南大区','1','E20170505000123a6566f','张亮',null,null,null,null,null,null, '', '','15994236393','15994236393', '', '',now(),now());
insert into t_hkp_hrm_org_dept(dept_code, dept_name, parent_dept_code, parent_dept_name, dept_type, dept_head_code, dept_head_name, province, province_name, city, city_name, region, region_name, street, street_name, dept_phone, dept_head_phone, dept_synopsis, address, create_time, last_update_time)  values ('DP201807020001999c','深圳宝安福永分部','DP201802020001e0f3','华南大区','1','E2018032200012a2b','姚泽豪',null,null,null,null,null,null, '', '','17634911214','17634911214', '', '',now(),now());


ALTER TABLE t_hkp_hrm_org_dept ADD COLUMN delete_time TIMESTAMP WITHOUT TIME ZONE;
COMMENT ON COLUMN t_hkp_hrm_org_dept.delete_time IS '部门删除时间';