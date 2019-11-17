package api;

import cn.hutool.core.collection.CollUtil;
import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.kfwy.hkp.common.utils.ApiConfigUtils;
import com.kfwy.hkp.sys.notice.entity.ErpNoticeEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/12/20.
 */
public class ErpNoticeApiClient {

    public static List<ErpNoticeEntity> findErpNoticeList(Map<String, Object> map) {
        ErpNoticeApiRequest request = new ErpNoticeApiRequest();
        request.setMap(map);
        request.setKey("@Housekeeper$");
        return HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("noticeApi-query"),
                ErpNoticeApiResponse.class, "application/json;charset=UTF-8").getEntityList();
/*        ErpNoticeApiResponse response = HttpRequestUtils.requestPost(request,"http://localhost:8081/kfwy-backend/rs/noticeApi/query",
                ErpNoticeApiResponse.class,"application/json;charset=UTF-8");
            return response.getEntityList();*/
    }

    public static List<String> findErpNoticeListString(Map<String, Object> map) {
        ErpNoticeApiRequest request = new ErpNoticeApiRequest();
        request.setMap(map);
        request.setKey("@Housekeeper$");
        ErpNoticeApiResponse erpNoticeApiResponse = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("noticeApi-query"),
                ErpNoticeApiResponse.class, "application/json;charset=UTF-8");
        if (erpNoticeApiResponse.getIsSuccess()) {
            List<String> codes = new ArrayList<>();
            List<ErpNoticeEntity> entityList = erpNoticeApiResponse.getEntityList();
            if (CollUtil.isNotEmpty(entityList)) {
                for (ErpNoticeEntity e : entityList) {
                    codes.add(e.getNcCode());
                }
            }
            return codes;
        }
        return null;
    }

    public static ErpNoticeEntity findErpNotice(String ncCode) {
        ErpNoticeApiRequest request = new ErpNoticeApiRequest();
        request.setNcCode(ncCode);
        request.setKey("@Housekeeper$");
        return HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("noticeApi-detail"),
                ErpNoticeApiResponse.class, "application/json;charset=UTF-8").getEntity();
    }
}
