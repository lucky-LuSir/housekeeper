package api;

import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerEntity;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerFollowupEntity;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 17:44
 */
public class HouseownerFollowupDataTransferApiClient {

    /**
     * 转移ERP客户表数据客户端Api
     * @param start
     * @param pageSize
     * @return
     */
    public static List<HouseownerFollowupEntity> findCustomerTransfer(Integer start, Integer pageSize){
        HouseownerFollowupDataTransferApiRequest request = new HouseownerFollowupDataTransferApiRequest();
        request.setStart(start);
        request.setPageSize(pageSize);
        request.setKey("@Housekeeper$");
        HouseownerFollowupDataTransferApiResponse response = HttpRequestUtils.requestPost(request,"http://mo.kufangwuyou.com/kfwy-office/rs/ownerApi/ownerFollowupDataTransfer",
                HouseownerFollowupDataTransferApiResponse.class,"application/json;charset=UTF-8");
        if (response.getIsSuccess()){
            return response.getResult();
        }
        return null;
    }


    public static void main(String[] args) {
      List<HouseownerFollowupEntity> result = findCustomerTransfer(0,1000);
    }
}

