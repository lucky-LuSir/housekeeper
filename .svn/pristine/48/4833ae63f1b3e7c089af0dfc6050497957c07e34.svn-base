/**客服分成所属部门**/

UPDATE t_hkp_trade_order_percentage per
SET pe_dept_code = (
	SELECT
		create_dept_code
	FROM
		t_hkp_trade_order ord
	WHERE
		ord.order_code = per.order_code
)
where per.percentage_type='6'
and per.order_time>='2019-03-01 00:00:00'
and per.is_deleted=FALSE
and per.money_percent_type='1'
