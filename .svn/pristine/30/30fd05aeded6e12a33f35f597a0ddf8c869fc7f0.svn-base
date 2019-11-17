
/**
用户表 新增 是否冻结账号 字段
 */
-- t_hkp_sys_user_user
ALTER TABLE t_hkp_sys_user_user ADD is_frost  bool default false ;
COMMENT ON COLUMN t_hkp_sys_user_user.is_frost IS '是否冻结账号';


/**
t_hkp_trade_ord_invoice  订单开票表增加  发票类型字段
 */
ALTER TABLE t_hkp_trade_ord_invoice add  invoice_type   VARCHAR NULL;
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice.invoice_type IS  '发票类型';


/**
t_hkp_trade_ord_invoice_apply 订单开票申请表 发票类型字段
 */
ALTER TABLE t_hkp_trade_ord_invoice_apply add  invoice_type   VARCHAR NULL;
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice_apply.invoice_type IS  '发票类型';