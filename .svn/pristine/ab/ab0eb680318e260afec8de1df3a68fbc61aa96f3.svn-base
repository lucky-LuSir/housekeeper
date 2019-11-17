package api;

import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.kfwy.hkp.crm.customer.entity.CustomerAreaEntity;

import java.util.List;

/**
 * @author kfwy_it_013
 * @version 1.0
 * @description 客户需求区域Api
 * @auth liwensihan
 * @date 2018/10/31 17:44
 */
public class CustomerAreaApiClient {

    /**
     * 转移ERP客户需求区域数据客户端Api
     * @param start
     * @param pageSize
     * @return
     */
    public static List<CustomerAreaEntity> findCustomerAreaTransfer(Integer start, Integer pageSize){
        CustomerAreaApiRequest request = new CustomerAreaApiRequest();
        request.setStart(start);
        request.setPageSize(pageSize);
        request.setKey("@Housekeeper$");
        CustomerAreaApiResponse response = HttpRequestUtils.requestPost(request,"http://mo.kufangwuyou.com/kfwy-office/rs/customerTransferApi/customerAreaDtoTransfer",
                CustomerAreaApiResponse.class,"application/json;charset=UTF-8");
        if (response.getIsSuccess()){
            return response.getResult();
        }
        return null;
    }

    public static void main(String[] args) {

    }
}

