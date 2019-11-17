package api;

import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.kfwy.hkp.crm.customer.entity.CustomerFollowupEntity;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth liwensihan
 * @date 2018/10/31 17:44
 */
public class CustomerFollowupApiClient {


    /**
     * 转移ERP客户表数据客户端Api
     * @param start
     * @param pageSize
     * @return
     */
    public static List<CustomerFollowupEntity> findCustomerFollowupTransfer(Integer start, Integer pageSize){
        CustomerFollowupApiRequest request = new CustomerFollowupApiRequest();
        request.setStart(start);
        request.setPageSize(pageSize);
        request.setKey("@Housekeeper$");
        CustomerFollowupApiResponse response = HttpRequestUtils.requestPost(request,"http://mo.kufangwuyou.com/kfwy-office/rs/customerTransferApi/customerFollowupTransfer",
                CustomerFollowupApiResponse.class,"application/json;charset=UTF-8");
        if (response.getIsSuccess()){
            return response.getResult();
        }
        return null;
    }

    public static void main(String[] args) {

    }
}

