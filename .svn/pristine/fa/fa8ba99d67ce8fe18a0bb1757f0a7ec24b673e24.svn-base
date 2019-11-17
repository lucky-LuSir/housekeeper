
----新增订单所属部门字段
----t_hkp_trade_order


ALTER TABLE t_hkp_trade_order ADD dept_code VARCHAR NULL;
COMMENT ON COLUMN t_hkp_trade_order.dept_code IS '订单所属部门';


update  t_hkp_trade_order set dept_code=create_dept_code;