package api;

import com.gniuu.framework.service.AbstractServiceResponse;
import com.kfwy.hkp.crm.customer.entity.CustomerFavoriteEntity;

import java.util.List;

/**
 * @author liwensihan
 * @version 1.0
 * @description TODO
 * @date 2018/12/1 17:46
 */
public class CustomerFavoriteApiResponse extends AbstractServiceResponse {

    private List<CustomerFavoriteEntity> result;


    @Override
    public List<CustomerFavoriteEntity> getResult() {
        return result;
    }

    public void setResult(List<CustomerFavoriteEntity> result) {
        this.result = result;
    }
}
