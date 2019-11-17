---------推送记录表---------
CREATE SEQUENCE seq_hkp_sys_notice_record
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_sys_notice_record
  OWNER TO hkp;

CREATE TABLE t_hkp_sys_notice_record (
id BIGINT NOT NULL   DEFAULT nextval('seq_hkp_sys_notice_record'::regclass),
record_code CHARACTER VARYING,
emp_code CHARACTER VARYING,
notice_code CHARACTER VARYING,
has_read BOOLEAN,

remark           CHARACTER VARYING,
cpy_code         CHARACTER VARYING,
create_code      CHARACTER VARYING,
create_dept_code      CHARACTER VARYING,
last_update_code CHARACTER VARYING,
create_time      TIMESTAMP WITHOUT TIME ZONE,
last_update_time TIMESTAMP WITHOUT TIME ZONE,
is_deleted       BOOLEAN           DEFAULT FALSE,

CONSTRAINT pk_hkp_sys_notice_record PRIMARY KEY (id)
)
WITH (OIDS=FALSE)
;
ALTER TABLE t_hkp_sys_notice_record OWNER TO hkp;
COMMENT ON TABLE t_hkp_sys_notice_record IS '消息推送表';
COMMENT ON COLUMN t_hkp_sys_notice_record.record_code IS '记录编码';
COMMENT ON COLUMN t_hkp_sys_notice_record.emp_code IS '记录人编码';
COMMENT ON COLUMN t_hkp_sys_notice_record.notice_code IS '消息编码';
COMMENT ON COLUMN t_hkp_sys_notice_record.has_read IS '是否已读';