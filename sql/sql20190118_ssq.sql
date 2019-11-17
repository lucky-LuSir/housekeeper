/**
    房源边新增字段
 */
-- t_hkp_hos_house


ALTER TABLE t_hkp_hos_house ADD down_reason CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_hos_house.down_reason IS '下架原因';



/**
  user表新增字段
 */
 --t_hkp_sys_user_user

ALTER TABLE t_hkp_sys_user_user ADD card_image_name CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_sys_user_user.card_image_name IS '银行卡正面图片';

ALTER TABLE t_hkp_sys_user_user ADD bank_card_image_name CHARACTER VARYING;
COMMENT ON COLUMN t_hkp_sys_user_user.bank_card_image_name IS '身份证正面图片';