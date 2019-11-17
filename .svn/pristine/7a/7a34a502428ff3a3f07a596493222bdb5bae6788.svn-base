package api;

import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.kfwy.hkp.sys.parttime.entity.PartTimerFollowupEntity;
import com.kfwy.hkp.sys.parttime.entity.ParttimeEntity;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 17:44
 */
public class ParttimeFollowupDataTransferApiClient {

    /**
     * 转移ERP客户表数据客户端Api
     * @param start
     * @param pageSize
     * @return
     */
    public static List<PartTimerFollowupEntity> findCustomerTransfer(Integer start, Integer pageSize){
        ParttimeFollowupDataTransferApiRequest request = new ParttimeFollowupDataTransferApiRequest();
        request.setStart(start);
        request.setPageSize(pageSize);
        request.setKey("@Housekeeper$");
        ParttimeFollowupDataTransferApiResponse response = HttpRequestUtils.requestPost(request,"http://mo.kufangwuyou.com/kfwy-office/rs/userTransferApi/userFollowupTransfer",
                ParttimeFollowupDataTransferApiResponse.class,"application/json;charset=UTF-8");
        if (response.getIsSuccess()){
            return response.getResult();
        }
        return null;
    }

}

