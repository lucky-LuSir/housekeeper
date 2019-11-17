package api;

import com.gniuu.framework.service.AbstractServiceResponse;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerEntity;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 17:46
 */
public class HouseownerDataTransferApiResponse extends AbstractServiceResponse {

    private List<HouseownerEntity> result;


    @Override
    public List<HouseownerEntity> getResult() {
        return result;
    }

    public void setResult(List<HouseownerEntity> result) {
        this.result = result;
    }
}
