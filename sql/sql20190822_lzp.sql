--hkp-1108 客服分成所属部门修改为客服部门  数据刷新

UPDATE t_hkp_trade_order_percentage per
SET pe_dept_code = (
	SELECT
		owner_dept_code
	FROM
t_hkp_sys_user_user u
	WHERE
		u.user_code = per.pe_emp_code
)
where per.percentage_type='6'
and per.order_time>='2019-03-01 00:00:00'
and per.is_deleted=FALSE
and per.money_percent_type='1';