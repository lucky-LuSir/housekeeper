package com.kfwy.hkp.controller.sys.dic;

import com.gniuu.framework.dic.Dictionary;
import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.annotion.IgnoreLog;
import com.kfwy.hkp.controller.sys.dic.vo.DicServiceRequest;
import com.kfwy.hkp.controller.sys.dic.vo.DicServiceResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by zjp on 2018/8/10.
 */
@RestController
@RequestMapping("/dic")
public class DicService extends SpringRestService {
    @IgnoreLog
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody DicServiceRequest request) {

        DicServiceResponse response = new DicServiceResponse();

        Dictionary dictionary = DictionaryStorage.get(request.getKey());
        response.setResult(dictionary.getChildren());

        return this.success(response);
    }

}
