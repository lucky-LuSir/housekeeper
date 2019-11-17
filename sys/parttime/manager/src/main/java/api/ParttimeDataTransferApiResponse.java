package api;

import com.gniuu.framework.service.AbstractServiceResponse;
import com.kfwy.hkp.sys.parttime.entity.ParttimeEntity;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 17:46
 */
public class ParttimeDataTransferApiResponse extends AbstractServiceResponse {

    private List<ParttimeEntity> result;


    @Override
    public List<ParttimeEntity> getResult() {
        return result;
    }

    public void setResult(List<ParttimeEntity> result) {
        this.result = result;
    }
}
