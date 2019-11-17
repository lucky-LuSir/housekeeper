/**回款增加回款编码字段**/

-- t_hkp_trade_ord_payback
ALTER TABLE t_hkp_trade_ord_payback ADD pay_back_code VARCHAR NULL;
COMMENT ON COLUMN t_hkp_trade_ord_payback.pay_back_code IS '回款编码';


update t_hkp_trade_ord_payback  set pay_back_code=CONCAT(order_code,id);