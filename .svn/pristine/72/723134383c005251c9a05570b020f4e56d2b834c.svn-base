---------信息推送表---------
CREATE SEQUENCE seq_hkp_sys_notice
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_sys_notice
  OWNER TO hkp;

CREATE TABLE t_hkp_sys_notice (
id BIGINT NOT NULL   DEFAULT nextval('seq_hkp_sys_notice'::regclass),
notice_code CHARACTER VARYING,
notice_type CHARACTER VARYING,
bussiness_code CHARACTER VARYING,
bussiness_type CHARACTER VARYING,
bussiness_emp_name CHARACTER VARYING,
bussiness_dept_name CHARACTER VARYING,
notice_title CHARACTER VARYING,
notice_content CHARACTER VARYING,

remark           CHARACTER VARYING,
cpy_code         CHARACTER VARYING,
create_code      CHARACTER VARYING,
create_dept_code      CHARACTER VARYING,
last_update_code CHARACTER VARYING,
create_time      TIMESTAMP WITHOUT TIME ZONE,
last_update_time TIMESTAMP WITHOUT TIME ZONE,
is_deleted       BOOLEAN           DEFAULT FALSE,

CONSTRAINT pk_hkp_sys_notice PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;
ALTER TABLE t_hkp_sys_notice OWNER TO hkp;
COMMENT ON TABLE t_hkp_sys_notice IS '消息推送表';
COMMENT ON COLUMN t_hkp_sys_notice.notice_code IS '消息编码';
COMMENT ON COLUMN t_hkp_sys_notice.notice_type IS '消息类型: 1、房源 2、客户 3、合作 4、系统';
COMMENT ON COLUMN t_hkp_sys_notice.bussiness_code IS '业务编码（例如房源编码）';
COMMENT ON COLUMN t_hkp_sys_notice.bussiness_type IS '业务类型：1、新增 2、修改 3、合作';
COMMENT ON COLUMN t_hkp_sys_notice.bussiness_emp_name IS '业务操作人名字';
COMMENT ON COLUMN t_hkp_sys_notice.bussiness_dept_name IS '业务操作人部门名字';
COMMENT ON COLUMN t_hkp_sys_notice.notice_title IS '消息的标题';
COMMENT ON COLUMN t_hkp_sys_notice.notice_content IS '消息的内容';