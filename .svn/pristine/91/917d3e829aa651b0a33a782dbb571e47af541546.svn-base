/**房源表增加共享部门是否可见**/

-- t_hkp_hos_house 共享部门房源详情是否可见
ALTER TABLE t_hkp_hos_house ADD COLUMN share_open_flag bool default true ;
COMMENT ON COLUMN t_hkp_hos_house.share_open_flag IS '共享部门详情是否可见';

--t_hkp_hos_house 合作房源详情是否可见
ALTER TABLE t_hkp_hos_house ADD COLUMN coo_open_flag bool default true ;
COMMENT ON COLUMN t_hkp_hos_house.coo_open_flag IS '合作房源详情是否可见';
