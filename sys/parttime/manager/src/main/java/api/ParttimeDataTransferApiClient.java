package api;

import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.kfwy.hkp.sys.parttime.entity.ParttimeEntity;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 17:44
 */
public class ParttimeDataTransferApiClient {

    /**
     * 转移ERP客户表数据客户端Api
     * @param start
     * @param pageSize
     * @return
     */
    public static List<ParttimeEntity> findCustomerTransfer(Integer start, Integer pageSize){
        ParttimeDataTransferApiRequest request = new ParttimeDataTransferApiRequest();
        request.setStart(start);
        request.setPageSize(pageSize);
        request.setKey("@Housekeeper$");
        ParttimeDataTransferApiResponse response = HttpRequestUtils.requestPost(request,"http://mo.kufangwuyou.com/kfwy-office/rs/userTransferApi/userTransfer",
                ParttimeDataTransferApiResponse.class,"application/json;charset=UTF-8");
        if (response.getIsSuccess()){
            return response.getResult();
        }
        return null;
    }


    public static void main(String[] args) {
      List<ParttimeEntity> result = findCustomerTransfer(0,1000);
    }
}

