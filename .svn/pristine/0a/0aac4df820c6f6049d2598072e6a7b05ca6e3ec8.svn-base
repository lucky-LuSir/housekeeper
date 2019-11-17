CREATE SEQUENCE seq_hkp_crm_customer_wait_add
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE seq_hkp_crm_customer_wait_add
    OWNER TO hkp;

CREATE TABLE t_hkp_crm_customer_wait_add
(
    ID                   BIGINT  DEFAULT nextval(
            'seq_hkp_crm_customer_wait_add' :: regclass
        ) NOT NULL
        CONSTRAINT pk_hkp_crm_customer_wait_add PRIMARY KEY,
    cus_type             INTEGER,
    cus_name             VARCHAR,
    cus_phone            VARCHAR,
    cus_sex              VARCHAR,
    company_name         VARCHAR,
    industry             VARCHAR,
    products             VARCHAR,
    need_acreage         INTEGER,
    need_price           NUMERIC(16, 6),
    price_unit           VARCHAR,
    layer_num            INTEGER,
    layer_height         NUMERIC(16, 6),
    need_voltage         NUMERIC(16, 6),
    enter_time           TIMESTAMP,
    expect_term          VARCHAR,
    fire_level           VARCHAR,
    need_eia             BOOLEAN,
    need_register        BOOLEAN,
    need_certificate     BOOLEAN,
    need_single_building BOOLEAN,
    has_office_area      BOOLEAN,
    open_flag            BOOLEAN,
    house_type           VARCHAR,
    description          VARCHAR,
    remark               VARCHAR,
    cpy_code             VARCHAR,
    create_code          VARCHAR,
    create_dept_code     VARCHAR,
    last_update_code     VARCHAR,
    create_time          TIMESTAMP,
    last_update_time     TIMESTAMP,
    is_deleted           BOOLEAN DEFAULT FALSE,
    last_followup_time   TIMESTAMP,
    pt_code              VARCHAR,
    pt_name              VARCHAR,
    cus_from             VARCHAR
);


COMMENT ON TABLE t_hkp_crm_customer_wait_add IS '客户待新增表';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.cus_type IS '客户类型';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.cus_name IS '客户姓名';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.cus_phone IS '客户手机号';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.cus_sex IS '客户性别';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.company_name IS '公司名字';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.industry IS '行业性质';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.products IS '产品信息';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.need_acreage IS '需求面积';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.need_price IS '要求价格';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.price_unit IS '价格单位';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.layer_num IS '楼层数';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.layer_height IS '楼层高';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.need_voltage IS '要求电量';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.enter_time IS '入住时间';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.expect_term IS '期望租期';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.fire_level IS '消防等级';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.need_eia IS '是否需要环评';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.need_register IS '是否需要注册';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.need_certificate IS '是否需要产证';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.need_single_building IS '是否需要单栋';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.has_office_area IS '是否需要办公区';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.open_flag IS '是否公开';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.house_type IS '需求类型(找房用途)';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.description IS '需求描述';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.pt_code IS '兼职编码';

COMMENT ON COLUMN t_hkp_crm_customer_wait_add.pt_name IS '兼职名称';


ALTER TABLE t_hkp_crm_customer_wait_add
    OWNER TO hkp;


/*客户待新增区域表*/
CREATE SEQUENCE seq_customer_wait_add_area
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE seq_customer_wait_add_area
    OWNER TO hkp;


create table t_hkp_crm_customer_wait_add_area
(
    id               bigint  default nextval('seq_customer_wait_add_area'::regclass) not null
        constraint pk_hkp_crm_customer_wait_add_area
            primary key,
    cus_phone        varchar,
    province         varchar,
    city             varchar,
    region           varchar,
    street           varchar,
    province_name    varchar,
    city_name        varchar,
    region_name      varchar,
    street_name      varchar,
    remark           varchar,
    cpy_code         varchar,
    create_code      varchar,
    create_dept_code varchar,
    last_update_code varchar,
    create_time      timestamp,
    last_update_time timestamp,
    is_deleted       boolean default false,
    community        varchar,
    community_name   varchar
);

comment on table t_hkp_crm_customer_wait_add_area is '客户待新增需求区域表';

comment on column t_hkp_crm_customer_wait_add_area.cus_phone is '客户号码';

comment on column t_hkp_crm_customer_wait_add_area.province is '省份编码';

comment on column t_hkp_crm_customer_wait_add_area.city is '城市编码';

comment on column t_hkp_crm_customer_wait_add_area.region is '区域编码';

comment on column t_hkp_crm_customer_wait_add_area.street is '街道编码';

comment on column t_hkp_crm_customer_wait_add_area.province_name is '省份名';

comment on column t_hkp_crm_customer_wait_add_area.city_name is '城市名';

comment on column t_hkp_crm_customer_wait_add_area.region_name is '区域名';

comment on column t_hkp_crm_customer_wait_add_area.street_name is '街道名';

comment on column t_hkp_crm_customer_wait_add_area.community is '社区编码';

comment on column t_hkp_crm_customer_wait_add_area.community_name is '社区名';

alter table t_hkp_crm_customer_wait_add_area
    owner to hkp;





