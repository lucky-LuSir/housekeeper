---------报表权限---------
CREATE SEQUENCE seq_hkp_sys_report_access
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_sys_report_access
  OWNER TO hkp;

CREATE TABLE t_hkp_sys_report_access
(
  id               BIGINT NOT NULL   DEFAULT nextval('seq_hkp_sys_report_access' :: REGCLASS),
  user_code        CHARACTER VARYING,
  dept_code       CHARACTER VARYING,
  CONSTRAINT pk_hkp_sys_report_access PRIMARY KEY (id)
) WITH (OIDS = FALSE
);
ALTER TABLE t_hkp_sys_report_access
  OWNER TO hkp;
COMMENT ON TABLE t_hkp_sys_report_access IS '报表权限表';
COMMENT ON COLUMN t_hkp_sys_report_access.user_code IS '用户编码';
COMMENT ON COLUMN t_hkp_sys_report_access.dept_code IS '部门编码';