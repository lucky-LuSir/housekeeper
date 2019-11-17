CREATE SEQUENCE seq_hkp_sys_port_user
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE seq_hkp_sys_port_user
    OWNER TO hkp;

CREATE TABLE t_hkp_sys_port_user
(
    ID               BIGINT DEFAULT nextval(
            'seq_hkp_sys_port_user' :: regclass
        ) NOT NULL
        CONSTRAINT pk_hkp_sys_port_user PRIMARY KEY,
    show_phone       VARCHAR,
    port_dept_name   VARCHAR,
    regional_name    VARCHAR,
    port_user_name   VARCHAR,
    create_code      VARCHAR,
    create_dept_code VARCHAR,
    create_time      TIMESTAMP
);


COMMENT ON TABLE t_hkp_sys_port_user IS '客户待新增表';

COMMENT ON COLUMN t_hkp_sys_port_user.show_phone IS '展示号码';

COMMENT ON COLUMN t_hkp_sys_port_user.port_dept_name IS '端口对应部门';

COMMENT ON COLUMN t_hkp_sys_port_user.regional_name IS '大区';

COMMENT ON COLUMN t_hkp_sys_port_user.port_user_name IS '端口使用人';



ALTER TABLE t_hkp_sys_port_user
    OWNER TO hkp;





