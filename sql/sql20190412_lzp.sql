--房源导出日志表
CREATE SEQUENCE seq_hkp_sys_hos_export_log
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE seq_hkp_sys_hos_export_log
  OWNER TO hkp;


CREATE TABLE t_hkp_sys_hos_export_log(
id BIGINT NOT NULL  DEFAULT nextval('seq_hkp_sys_hos_export_log'::regclass),
user_code CHARACTER VARYING,
start_time timestamp(6),
end_time  timestamp(6),
dept_codes CHARACTER VARYING,
result_number int,
create_code CHARACTER VARYING,
create_time timestamp(6),
is_deleted BOOLEAN DEFAULT false,
CONSTRAINT pk_hkp_sys_hos_export_log PRIMARY KEY (id)
)
WITH (OIDS=FALSE);

ALTER TABLE t_hkp_sys_hos_export_log OWNER TO hkp;

COMMENT ON TABLE t_hkp_sys_hos_export_log IS '首页榜单表';

COMMENT ON COLUMN t_hkp_sys_hos_export_log.user_code IS '员工编码';

COMMENT ON COLUMN t_hkp_sys_hos_export_log.start_time IS '查询开始时间';

COMMENT ON COLUMN t_hkp_sys_hos_export_log.end_time IS '查询结束时间';

COMMENT ON COLUMN t_hkp_sys_hos_export_log.dept_codes IS '查询部门编码';

COMMENT ON COLUMN t_hkp_sys_hos_export_log.result_number IS '查询结果数量';


-------------------------------------------------------------------------------

ALTER TABLE t_hkp_hos_house ADD COLUMN commission numeric(16,6);
COMMENT ON COLUMN t_hkp_hos_house.commission IS '服务费';


