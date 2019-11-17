/**发票表  新增字段**/

-- t_hkp_trade_ord_invoice

ALTER TABLE t_hkp_trade_ord_invoice add  cpy_code  VARCHAR NULL;
ALTER TABLE t_hkp_trade_ord_invoice add  cpy_name  VARCHAR NULL;
ALTER TABLE t_hkp_trade_ord_invoice add  consignee_name   VARCHAR NULL;
ALTER TABLE t_hkp_trade_ord_invoice add  consignee_address   VARCHAR NULL;
ALTER TABLE t_hkp_trade_ord_invoice add  consignee_phone   VARCHAR NULL;
ALTER TABLE t_hkp_trade_ord_invoice add  parent_dept_code   VARCHAR NULL;
ALTER TABLE t_hkp_trade_ord_invoice add  dept_code   VARCHAR NULL;
ALTER TABLE t_hkp_trade_ord_invoice add  parent_dept_name   VARCHAR NULL;
ALTER TABLE t_hkp_trade_ord_invoice add  dept_name   VARCHAR NULL;
ALTER TABLE t_hkp_trade_ord_invoice add  phone   VARCHAR NULL;
ALTER TABLE t_hkp_trade_ord_invoice add  address   VARCHAR NULL;
ALTER TABLE t_hkp_trade_ord_invoice add  bank_branch   VARCHAR NULL;
ALTER TABLE t_hkp_trade_ord_invoice add  bank_card   VARCHAR NULL;
ALTER TABLE t_hkp_trade_ord_invoice add  credit_code   VARCHAR NULL;
ALTER TABLE t_hkp_trade_ord_invoice add  apply_code   VARCHAR NULL;
ALTER TABLE t_hkp_trade_ord_invoice add  apply_name   VARCHAR NULL;

COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice.cpy_code IS  '开票公司编码';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice.cpy_name IS  '开票公司名称';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice.consignee_name IS  '收件人名称';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice.consignee_address IS  '收件人地址';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice.consignee_phone IS  '收件人电话';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice.parent_dept_code IS  '申请人所属大区';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice.dept_code IS  '申请人分部编码';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice.parent_dept_name IS  '申请人所属大区name';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice.dept_name IS  '申请人分部name';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice.phone IS  '申请人公司电话';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice.address IS  '开票公司地址';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice.bank_branch IS  '开票公司银行支行信息';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice.bank_card IS  '开票公司银行账号';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice.credit_code IS  '社会统一信用代码（纳税人识别号）';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice.apply_code IS  '申请人编码';
COMMENT ON COLUMN  public.t_hkp_trade_ord_invoice.apply_name IS  '申请人name';

