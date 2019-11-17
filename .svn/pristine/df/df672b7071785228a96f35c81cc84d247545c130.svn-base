package api;

import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.kfwy.hkp.crm.customer.entity.CustomerFavoriteEntity;

import java.util.List;

/**
 * @author kfwy_it_013
 * @version 1.0
 * @description 客户收藏Api
 * @auth liwensihan
 * @date 2018/10/31 17:44
 */
public class CustomerFavoriteApiClient {



    /**
     * 转移ERP客户收藏表数据客户端Api
     * @param start
     * @param pageSize
     * @return
     */
    public static List<CustomerFavoriteEntity> findCustomerFavoriteTransfer(Integer start, Integer pageSize){
        CustomerFavoriteApiRequest request = new CustomerFavoriteApiRequest();
        request.setStart(start);
        request.setPageSize(pageSize);
        request.setKey("@Housekeeper$");
        CustomerFavoriteApiResponse response = HttpRequestUtils.requestPost(request,"http://mo.kufangwuyou.com/kfwy-office/rs/customerTransferApi/customerFavoriteTransferQuery",
                CustomerFavoriteApiResponse.class,"application/json;charset=UTF-8");
        if (response.getIsSuccess()){
            return response.getResult();
        }
        return null;
    }

    public static void main(String[] args) {


    }
}

