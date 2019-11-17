ALTER TABLE PUBLIC .t_hkp_crm_customer_followup ADD is_decision bool NULL;

COMMENT ON COLUMN PUBLIC .t_hkp_crm_customer_followup. is_decision IS '是否决策人：是/否';

ALTER TABLE PUBLIC .t_hkp_crm_customer_followup ADD is_initiative_ask bool NULL;

COMMENT ON COLUMN PUBLIC .t_hkp_crm_customer_followup. is_initiative_ask IS '是否主动询问找房进度：是/否';

ALTER TABLE PUBLIC .t_hkp_crm_customer_followup ADD is_active bool NULL;

COMMENT ON COLUMN PUBLIC .t_hkp_crm_customer_followup. is_active IS '带看是否积极主动：是/否';

ALTER TABLE PUBLIC .t_hkp_crm_customer_followup ADD is_find_local bool NULL;

COMMENT ON COLUMN PUBLIC .t_hkp_crm_customer_followup. is_find_local IS '是否在当地找过房子：是/否';

ALTER TABLE PUBLIC .t_hkp_crm_customer_followup ADD manager_followup bool NULL;

COMMENT ON COLUMN PUBLIC .t_hkp_crm_customer_followup. manager_followup IS '经理对意向客户的跟进';