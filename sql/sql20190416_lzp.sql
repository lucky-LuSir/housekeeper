/**合作管理2.0**/

-- t_hkp_union_cooperate

ALTER TABLE t_hkp_union_cooperate ADD COLUMN coo_open_flag bool default FALSE;
COMMENT ON COLUMN t_hkp_union_cooperate.coo_open_flag IS '合作是否隐藏信息';

ALTER TABLE t_hkp_union_cooperate ADD COLUMN divide_percentage NUMERIC(16, 6);
COMMENT ON COLUMN t_hkp_union_cooperate.divide_percentage IS '分成要求';


update t_hkp_union_cooperate set coo_open_flag=TRUE;