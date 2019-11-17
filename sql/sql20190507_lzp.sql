/**房源导出日志增加字段.0**/

-- t_hkp_sys_hos_export_log
ALTER TABLE t_hkp_sys_hos_export_log ADD remark VARCHAR NULL;
COMMENT ON COLUMN t_hkp_sys_hos_export_log.remark IS '备注';