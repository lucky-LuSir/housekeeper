package api;

import com.gniuu.framework.service.AbstractServiceResponse;
import com.kfwy.hkp.common.api.AbstractApiRequest;
import com.kfwy.hkp.sys.notice.entity.ErpNoticeEntity;

import java.util.List;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/12/20.
 */
public class ErpNoticeApiResponse extends AbstractServiceResponse {

    private List<ErpNoticeEntity> entityList;

    private ErpNoticeEntity entity;

    public List<ErpNoticeEntity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<ErpNoticeEntity> entityList) {
        this.entityList = entityList;
    }

    public ErpNoticeEntity getEntity() {
        return entity;
    }

    public void setEntity(ErpNoticeEntity entity) {
        this.entity = entity;
    }
}
