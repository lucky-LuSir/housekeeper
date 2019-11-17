import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.hos.house.vo.HouseFavoriteServiceRequest;
import com.kfwy.hkp.controller.hos.house.vo.HouseFavoriteServiceResponse;
import com.kfwy.hkp.hos.house.api.BespeakApi.BespeakApiClient;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/12/10.
 */
@RestController
@RequestMapping(path = "/bespeak")
public class BespeakService extends SpringRestService {


    @Test
    public void query() {

        HouseFavoriteServiceResponse response = new HouseFavoriteServiceResponse();
        Map<String, Object> param = new HashMap<>();

        Object one = BespeakApiClient.getData(param, 0,30);

    }


}
