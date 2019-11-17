ALTER TABLE PUBLIC .t_hkp_crm_customer ADD LEVEL INT NULL;

COMMENT ON COLUMN PUBLIC .t_hkp_crm_customer. LEVEL IS '客户分级，优、良、中、差';

ALTER TABLE PUBLIC .t_hkp_crm_customer ADD cus_prop INT NULL;

COMMENT ON COLUMN PUBLIC .t_hkp_crm_customer.cus_prop IS '客户属性, 1, 普通客户 2,房源字段客户';

ALTER TABLE PUBLIC .t_hkp_crm_customer ADD contract_start_time TIMESTAMP (6) NULL;

COMMENT ON COLUMN PUBLIC .t_hkp_crm_customer.contract_start_time IS '合同签订日期';

ALTER TABLE PUBLIC .t_hkp_crm_customer ADD contract_end_time TIMESTAMP (6) NULL;

COMMENT ON COLUMN PUBLIC .t_hkp_crm_customer.contract_end_time IS '合同结束日期';

ALTER TABLE PUBLIC .t_hkp_crm_customer ADD need_nearby_facilities VARCHAR NULL;

COMMENT ON COLUMN PUBLIC .t_hkp_crm_customer.need_nearby_facilities IS '周边及配套要求';

ALTER TABLE PUBLIC .t_hkp_crm_customer ADD category VARCHAR NULL;

COMMENT ON COLUMN PUBLIC .t_hkp_crm_customer.category IS '库房类型分类：普通仓库厂房，冷链仓库、高台仓库、危险品仓库';

ALTER TABLE PUBLIC .t_hkp_crm_customer ADD gain_type VARCHAR NULL;

COMMENT ON COLUMN PUBLIC .t_hkp_crm_customer.gain_type IS '1:公司获客 2:个人获客';