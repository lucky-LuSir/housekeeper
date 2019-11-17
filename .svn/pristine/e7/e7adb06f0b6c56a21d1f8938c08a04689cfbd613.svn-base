package api;

import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.kfwy.hkp.crm.customer.entity.CustomerHouseEntity;

import java.util.List;

/**
 * @version 1.0
 * @description 客户看房明细Api
 * @auth liwensihan
 * @date 2018/10/31 17:44
 */
public class CustomerHouseApiClient {



    /**
     * 转移ERP客户表数据客户端Api
     * @param start
     * @param pageSize
     * @return
     */
    public static List<CustomerHouseEntity> findCustomerHouseTransfer(Integer start, Integer pageSize){
        CustomerHouseApiRequest request = new CustomerHouseApiRequest();
        request.setStart(start);
        request.setPageSize(pageSize);
        request.setKey("@Housekeeper$");
        CustomerHouseApiResponse response = HttpRequestUtils.requestPost(request,"http://mo.kufangwuyou.com/kfwy-office/rs/customerTransferApi/customerHouseQuery",
                CustomerHouseApiResponse.class,"application/json;charset=UTF-8");
        if (response.getIsSuccess()){
            return response.getResult();
        }
        return null;
    }

    public static void main(String[] args) {
      List<CustomerHouseEntity> list = findCustomerHouseTransfer(0,10000);

       /* for (CustomerHouseEntity customerHouseEntity:
             list) {
            System.out.println(customerHouseEntity.getCusCode());
            System.out.println(customerHouseEntity.getHouseCode());
            System.out.println(customerHouseEntity.getFollowupType());
            System.out.println(customerHouseEntity.getRemark());
            System.out.println(customerHouseEntity.getCreateCode());
            System.out.println(customerHouseEntity.getCreateTime());
            System.out.println(customerHouseEntity.getLastUpdateTime());
            System.out.println(customerHouseEntity.getFollowupCode());
            System.out.println(customerHouseEntity.getIsDeleted());
            System.out.println(customerHouseEntity.getCreateDeptCode());
            System.out.println(customerHouseEntity.getAcreage());
            System.out.println(customerHouseEntity.getPrice());
            System.out.println(customerHouseEntity.getUnit());
            System.out.println(customerHouseEntity.getAreaSize());
            System.out.println(customerHouseEntity.getIsLocationAppropriate());
            System.out.println(customerHouseEntity.getIsAccommodation());
            System.out.println(customerHouseEntity.getIsEia());
            System.out.println(customerHouseEntity.getIsFloorAppropriate());
            System.out.println(customerHouseEntity.getIsPeriod());
            System.out.println(customerHouseEntity.getIsPowerAppropriate());
            System.out.println(customerHouseEntity.getIsRent());
            System.out.println(customerHouseEntity.getIsTax()+"\n\n\n\n");
        }*/

    }
}

