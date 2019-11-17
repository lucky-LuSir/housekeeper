/**回款开票扣除**/

-- t_hkp_trade_ord_payback
ALTER TABLE t_hkp_trade_ord_payback ADD COLUMN commission_deductions NUMERIC(16, 6) DEFAULT 0;
COMMENT ON COLUMN t_hkp_trade_ord_payback.commission_deductions IS '返佣扣款';

ALTER TABLE t_hkp_trade_ord_payback ADD COLUMN tax_deductions NUMERIC(16, 6) DEFAULT 0;
COMMENT ON COLUMN t_hkp_trade_ord_payback.tax_deductions IS '开票扣款';


-- t_hkp_trade_ord_invoice

ALTER TABLE t_hkp_trade_ord_invoice ADD COLUMN has_deductions bool default FALSE;
COMMENT ON COLUMN t_hkp_trade_ord_invoice.has_deductions IS '是否被扣除';