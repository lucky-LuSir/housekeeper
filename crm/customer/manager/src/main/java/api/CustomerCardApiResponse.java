package api;

import com.gniuu.framework.service.AbstractServiceResponse;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 17:46
 */
public class CustomerCardApiResponse extends AbstractServiceResponse {

    private List<FileRelationEntity> result;


    @Override
    public List<FileRelationEntity> getResult() {
        return result;
    }

    public void setResult(List<FileRelationEntity> result) {
        this.result = result;
    }
}
