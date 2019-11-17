

ALTER TABLE t_hkp_hos_house ADD COLUMN has_whole_rental  bool DEFAULT false;
COMMENT ON COLUMN "public"."t_hkp_hos_house"."has_whole_rental" IS '是否独栋出租';


ALTER TABLE t_hkp_hos_house ADD COLUMN hos_describe  varchar COLLATE "default";
COMMENT ON COLUMN "public"."t_hkp_hos_house"."hos_describe" IS '房源描述';

UPDATE t_hkp_hos_house set hos_describe='暂无描述' WHERE hos_describe is NULL;

SELECT * from t_hkp_hos_house WHERE hos_describe is NULL;









