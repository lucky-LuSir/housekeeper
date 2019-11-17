----用户表----
CREATE SEQUENCE seq_hkp_sys_user_user
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_sys_user_user
  OWNER TO hkp;

CREATE TABLE t_hkp_sys_user_user
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_sys_user_user' :: REGCLASS),
  user_code        CHARACTER VARYING,
  user_name        CHARACTER VARYING,
  user_phone       CHARACTER VARYING,
  user_img         CHARACTER VARYING,
  user_type        CHARACTER VARYING,
  work_number      CHARACTER VARYING,
  email            CHARACTER VARYING,
  sex              CHARACTER VARYING,
  password         CHARACTER VARYING,
  secure           CHARACTER VARYING,

  owner_dept_code  CHARACTER VARYING,
  owner_dept_name  CHARACTER VARYING,

  has_manager      BOOLEAN         DEFAULT FALSE,
  enter_type       CHARACTER VARYING,
  leader_code      CHARACTER VARYING,
  leader_name      CHARACTER VARYING,
  emp_post_code    CHARACTER VARYING,
  emp_post_name    CHARACTER VARYING,

  entry_time       TIMESTAMP WITHOUT TIME ZONE,
  quit_time        TIMESTAMP WITHOUT TIME ZONE,

  device_token     CHARACTER VARYING,
  need_auth        BOOLEAN         DEFAULT FALSE,
  identify_number  CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  create_dept_code CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_sys_user_user PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_sys_user_user
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_sys_user_user IS '用户表';
COMMENT ON COLUMN t_hkp_sys_user_user.user_code IS '用户编码';
COMMENT ON COLUMN t_hkp_sys_user_user.user_name IS '用户姓名';
COMMENT ON COLUMN t_hkp_sys_user_user.user_phone IS '用户电话';
COMMENT ON COLUMN t_hkp_sys_user_user.user_img IS '用户电话';
COMMENT ON COLUMN t_hkp_sys_user_user.user_type IS '用户类型';
COMMENT ON COLUMN t_hkp_sys_user_user.work_number IS '工号';
COMMENT ON COLUMN t_hkp_sys_user_user.email IS '邮箱';
COMMENT ON COLUMN t_hkp_sys_user_user.sex IS '性别';
COMMENT ON COLUMN t_hkp_sys_user_user.password IS '加密后的密码';
COMMENT ON COLUMN t_hkp_sys_user_user.secure IS '原密码';
COMMENT ON COLUMN t_hkp_sys_user_user.owner_dept_code IS '用户部门';
COMMENT ON COLUMN t_hkp_sys_user_user.owner_dept_name IS '部门名称';
COMMENT ON COLUMN t_hkp_sys_user_user.has_manager IS '是否经理';
COMMENT ON COLUMN t_hkp_sys_user_user.enter_type IS '创建类型';
COMMENT ON COLUMN t_hkp_sys_user_user.leader_code IS '领导编码';
COMMENT ON COLUMN t_hkp_sys_user_user.emp_post_code IS '岗位编码';
COMMENT ON COLUMN t_hkp_sys_user_user.emp_post_name IS '岗位名称';
COMMENT ON COLUMN t_hkp_sys_user_user.entry_time IS '入职时间';
COMMENT ON COLUMN t_hkp_sys_user_user.quit_time IS '离职时间';
COMMENT ON COLUMN t_hkp_sys_user_user.device_token IS '友盟token';
COMMENT ON COLUMN t_hkp_sys_user_user.need_auth IS '是否认证';
COMMENT ON COLUMN t_hkp_sys_user_user.identify_number IS '设备编号';

-----------------用户角色关联表---------
CREATE SEQUENCE seq_hkp_sys_user_user_role
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_sys_user_user_role
  OWNER TO hkp;

CREATE TABLE t_hkp_sys_user_user_role
(
  id        BIGINT NOT NULL DEFAULT nextval('seq_hkp_sys_user_user_role' :: REGCLASS),
  user_code CHARACTER VARYING,
  role_code CHARACTER VARYING,
  cpy_code  CHARACTER VARYING,
  CONSTRAINT pk_hkp_sys_user_user_role PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_sys_user_user_role
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_sys_user_user_role IS '用户表';
COMMENT ON COLUMN t_hkp_sys_user_user_role.user_code IS '用户编码';
COMMENT ON COLUMN t_hkp_sys_user_user_role.role_code IS '用户姓名';
COMMENT ON COLUMN t_hkp_sys_user_user_role.cpy_code IS '企业帐套';

---------个人负责区域表-----------
CREATE SEQUENCE seq_hkp_sys_user_user_area
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_sys_user_user_area
  OWNER TO hkp;

CREATE TABLE t_hkp_sys_user_user_area
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_sys_user_user_area' :: REGCLASS),
  user_code        CHARACTER VARYING,
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
  CONSTRAINT pk_t_hkp_sys_user_user_area PRIMARY KEY (id)
) WITH (OIDS = FALSE
);

ALTER TABLE t_hkp_sys_user_user_area
  OWNER TO hkp;

COMMENT ON TABLE t_hkp_sys_user_user_area IS '个人负责区域';
COMMENT ON COLUMN t_hkp_sys_user_user_area.id IS '数据库ID';
COMMENT ON COLUMN t_hkp_sys_user_user_area.user_code IS '部门编号';
COMMENT ON COLUMN t_hkp_sys_user_user_area.province_name IS '省名称';
COMMENT ON COLUMN t_hkp_sys_user_user_area.province_code IS '省编号';
COMMENT ON COLUMN t_hkp_sys_user_user_area.city_code IS '市编号';
COMMENT ON COLUMN t_hkp_sys_user_user_area.city_name IS '市名称';
COMMENT ON COLUMN t_hkp_sys_user_user_area.region_code IS '街道编号';
COMMENT ON COLUMN t_hkp_sys_user_user_area.region_name IS '街道名称';
COMMENT ON COLUMN t_hkp_sys_user_user_area.street_code IS '街道编码';
COMMENT ON COLUMN t_hkp_sys_user_user_area.street_name IS '街道名称';
