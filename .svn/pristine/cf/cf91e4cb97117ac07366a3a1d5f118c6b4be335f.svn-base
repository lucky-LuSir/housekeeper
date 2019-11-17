--集中获客指定部门选择记录表
CREATE SEQUENCE seq_hkp_crm_focus_cus_receive_dept_log INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

ALTER TABLE seq_hkp_crm_focus_cus_receive_dept_log OWNER TO hkp;

CREATE TABLE t_hkp_crm_focus_cus_receive_dept_log (
	ID BIGINT NOT NULL DEFAULT nextval(
		'seq_hkp_crm_focus_cus_receive_dept_log' :: regclass
	),
	cus_code CHARACTER VARYING,
	dept_codes CHARACTER VARYING,
	create_code CHARACTER VARYING,
	create_time TIMESTAMP (6),
	CONSTRAINT pk_hkp_crm_focus_cus_receive_dept_log PRIMARY KEY (ID)
) WITH (OIDS = FALSE);

ALTER TABLE t_hkp_crm_focus_cus_receive_dept_log OWNER TO hkp;

COMMENT ON TABLE t_hkp_crm_focus_cus_receive_dept_log IS '集中获客指定部门选择记录表';

COMMENT ON COLUMN t_hkp_crm_focus_cus_receive_dept_log.cus_code IS '集中获客客户编码';

COMMENT ON COLUMN t_hkp_crm_focus_cus_receive_dept_log.dept_codes IS '集中获客指定选择部门编码';

COMMENT ON COLUMN t_hkp_crm_focus_cus_receive_dept_log.create_code IS '集中获客创建人编码';

COMMENT ON COLUMN t_hkp_crm_focus_cus_receive_dept_log.create_time IS '创建时间';


