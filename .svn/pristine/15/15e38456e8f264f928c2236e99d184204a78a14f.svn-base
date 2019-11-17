
CREATE SEQUENCE seq_hkp_report_last_day
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_report_last_day
  OWNER TO hkp;

CREATE TABLE t_hkp_report_last_day(
id BIGINT NOT NULL  DEFAULT nextval('seq_hkp_report_last_day'  :: REGCLASS),
user_code	CHARACTER VARYING,
dept_code	CHARACTER VARYING,
in_time	timestamp(6),
call_emp_phone 	int4,
see_hos_detail 	int4,
see_cus_detail 	int4,
call_house_owner_phone 	int4,
call_cus_phone 	int4,
see_house_owner_detail 	int4,
remark	CHARACTER VARYING,
create_time	timestamp(6),
is_deleted	bool,
CONSTRAINT pk_hkp_report_last_day PRIMARY KEY (id)
)
WITH (OIDS=FALSE);


ALTER TABLE t_hkp_report_last_day OWNER TO hkp;
COMMENT ON TABLE  t_hkp_report_last_day IS '作日操作数据';
COMMENT ON COLUMN t_hkp_report_last_day.user_code IS '员工编码';
COMMENT ON COLUMN t_hkp_report_last_day.dept_code IS '部门编码';
COMMENT ON COLUMN t_hkp_report_last_day.in_time IS '时间';
COMMENT ON COLUMN t_hkp_report_last_day.call_emp_phone IS '员工编码';
COMMENT ON COLUMN t_hkp_report_last_day.see_hos_detail IS '查看房源详情次数';
COMMENT ON COLUMN t_hkp_report_last_day.see_cus_detail IS '查看客户详情次数';
COMMENT ON COLUMN t_hkp_report_last_day.call_house_owner_phone IS '拨打业主电话次数';
COMMENT ON COLUMN t_hkp_report_last_day.call_cus_phone IS '拨打客户电话次数';
COMMENT ON COLUMN t_hkp_report_last_day.see_house_owner_detail IS '查看业主详情次数';
