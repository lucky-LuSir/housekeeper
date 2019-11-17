ALTER TABLE t_hkp_pt_parttimer ADD COLUMN password  varchar COLLATE "default";
COMMENT ON COLUMN "public"."t_hkp_pt_parttimer"."password" IS '密码';

ALTER TABLE t_hkp_pt_parttimer ADD COLUMN org_password  varchar COLLATE "default";
COMMENT ON COLUMN "public"."t_hkp_pt_parttimer"."org_password" IS '密码';

ALTER TABLE t_hkp_pt_parttimer ADD COLUMN from_type varchar COLLATE "default";
COMMENT ON COLUMN "public"."t_hkp_pt_parttimer"."from_type" IS '用户来源（1.移动端2.后台3.小程序）';

ALTER TABLE t_hkp_pt_parttimer ADD COLUMN is_weixin bool;
COMMENT ON COLUMN "public"."t_hkp_pt_parttimer"."is_weixin" IS '是否微信';

ALTER TABLE t_hkp_pt_parttimer ADD COLUMN wx_open_id  varchar COLLATE "default";
COMMENT ON COLUMN "public"."t_hkp_pt_parttimer"."wx_open_id" IS 'openID';

ALTER TABLE t_hkp_pt_parttimer ADD COLUMN wx_union_id  varchar COLLATE "default";
COMMENT ON COLUMN "public"."t_hkp_pt_parttimer"."wx_union_id" IS 'unionId';