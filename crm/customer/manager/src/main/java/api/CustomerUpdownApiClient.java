package api;

import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.kfwy.hkp.crm.customer.entity.CustomerUpdownEntity;

import java.util.List;

/**
 * @author kfwy_it_013
 * @version 1.0
 * @description 客户需求区域Api
 * @auth liwensihan
 * @date 2018/10/31 17:44
 */
public class CustomerUpdownApiClient {

    /**
     * 转移ERP客户推送数据客户端Api
     * @param start
     * @param pageSize
     * @return
     */
    public static List<CustomerUpdownEntity> findCustomerUpdownTransfer(Integer start, Integer pageSize){
        CustomerUpdownApiRequest request = new CustomerUpdownApiRequest();
        request.setStart(start);
        request.setPageSize(pageSize);
        request.setKey("@Housekeeper$");
        CustomerUpdownApiResponse response = HttpRequestUtils.requestPost(request,"http://mo.kufangwuyou.com/kfwy-office/rs/customerTransferApi/customerUpdownDtoTransfer",
                CustomerUpdownApiResponse.class,"application/json;charset=UTF-8");
        if (response.getIsSuccess()){
            return response.getResult();
        }
        return null;
    }

    public static void main(String[] args) {

    }
}

