
----新增房源编号字段
----t_hkp_hos_house


ALTER TABLE t_hkp_hos_house ADD house_number VARCHAR NULL;
COMMENT ON COLUMN t_hkp_hos_house.house_number IS '订单所属部门';


update t_hkp_hos_house set house_number=concat('0000000',id) where  id >=0 and  id <10;

update t_hkp_hos_house set house_number=concat('000000',id) where  id >=10 and  id <100;

update t_hkp_hos_house set house_number=concat('00000',id) where  id >=100 and  id <1000;

update t_hkp_hos_house set house_number=concat('0000',id) where  id >=1000 and  id <10000;

update t_hkp_hos_house set house_number=concat('000',id) where  id >=10000 and  id <100000;

update t_hkp_hos_house set house_number=concat('00',id) where  id >=100000 and  id <1000000;