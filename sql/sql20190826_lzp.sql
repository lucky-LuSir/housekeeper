/**开票编码数据初始化**/


update t_hkp_trade_ord_invoice set order_invoice_code=concat(order_code, id)

where order_invoice_code is null or order_invoice_code='' ;






UPDATE t_hkp_trade_order_commission_wage cw
SET pay_back_order_code = (
	SELECT
		oi.order_invoice_code
	FROM
t_hkp_trade_ord_invoice oi
	WHERE
		cw.pay_back_order_code = oi.invoice_code
		and cw.order_code = oi.order_code
and oi.is_deleted=false
)
where cw.wage_type='2';



ALTER TABLE t_hkp_crm_customer_push ADD push_message VARCHAR NULL;
COMMENT ON COLUMN t_hkp_crm_customer_push.push_message IS '推送留言';


