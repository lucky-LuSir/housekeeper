------------------菜单权限表-------------------------------
CREATE SEQUENCE seq_hkp_sys_auth_menu
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_sys_auth_menu
  OWNER TO hkp;

CREATE TABLE t_hkp_sys_auth_menu
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_sys_auth_menu' :: REGCLASS),
  menu_code        CHARACTER VARYING,
  menu_name        CHARACTER VARYING,
  item             CHARACTER VARYING,
  parent_code      CHARACTER VARYING,
  menu_type        CHARACTER VARYING,
  pack             CHARACTER VARYING,
  icon_cls         CHARACTER VARYING,
  level            BIGINT,
  sort             BIGINT,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_sys_auth_menu PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_sys_auth_menu
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_sys_auth_menu IS '企业帐套表';
COMMENT ON COLUMN t_hkp_sys_auth_menu.menu_code IS '菜单编码';
COMMENT ON COLUMN t_hkp_sys_auth_menu.menu_name IS '菜单名称';
COMMENT ON COLUMN t_hkp_sys_auth_menu.item IS '菜单条目，页面中的ID';
COMMENT ON COLUMN t_hkp_sys_auth_menu.parent_code IS '父菜单编码';
COMMENT ON COLUMN t_hkp_sys_auth_menu.menu_type IS '菜单类型';
COMMENT ON COLUMN t_hkp_sys_auth_menu.pack IS '菜单包名';
COMMENT ON COLUMN t_hkp_sys_auth_menu.icon_cls IS '菜单图标class样式';
COMMENT ON COLUMN t_hkp_sys_auth_menu.level IS '菜单级别';
COMMENT ON COLUMN t_hkp_sys_auth_menu.sort IS '菜单排序';

COMMENT ON COLUMN t_hkp_sys_auth_menu.remark IS '角色描述';
COMMENT ON COLUMN t_hkp_sys_auth_menu.cpy_code IS '企业帐套编码';

------------------权限角色-------------------------------
CREATE SEQUENCE seq_hkp_sys_auth_role
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_sys_auth_role
  OWNER TO hkp;

CREATE TABLE t_hkp_sys_auth_role
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_sys_auth_role' :: REGCLASS),
  role_code        CHARACTER VARYING,
  role_name        CHARACTER VARYING,

  remark           CHARACTER VARYING,
  cpy_code         CHARACTER VARYING,
  create_code      CHARACTER VARYING,
  last_update_code CHARACTER VARYING,
  create_time      TIMESTAMP WITHOUT TIME ZONE,
  last_update_time TIMESTAMP WITHOUT TIME ZONE,
  is_deleted       BOOLEAN         DEFAULT FALSE,
  CONSTRAINT pk_hkp_sys_auth_role PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_sys_auth_role
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_sys_auth_role IS '企业帐套表';
COMMENT ON COLUMN t_hkp_sys_auth_role.role_code IS '角色编码';
COMMENT ON COLUMN t_hkp_sys_auth_role.role_name IS '角色名称';
COMMENT ON COLUMN t_hkp_sys_auth_role.remark IS '角色描述';
COMMENT ON COLUMN t_hkp_sys_auth_role.cpy_code IS '企业帐套编码';


------------------菜单角色关联表-------------------------------
CREATE SEQUENCE seq_hkp_sys_auth_role_menu
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_sys_auth_role_menu
  OWNER TO hkp;

CREATE TABLE t_hkp_sys_auth_role_menu
(
  id               BIGINT NOT NULL DEFAULT nextval('seq_hkp_sys_auth_role_menu' :: REGCLASS),
  role_code        CHARACTER VARYING,
  menu_code        CHARACTER VARYING,

  CONSTRAINT pk_hkp_sys_auth_role_menu PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_sys_auth_role_menu
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_sys_auth_role_menu IS '企业帐套表';
COMMENT ON COLUMN t_hkp_sys_auth_role_menu.role_code IS '角色编码';
COMMENT ON COLUMN t_hkp_sys_auth_role_menu.menu_code IS '菜单编码';
