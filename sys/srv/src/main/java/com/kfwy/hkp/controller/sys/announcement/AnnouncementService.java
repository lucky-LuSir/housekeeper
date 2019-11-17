package com.kfwy.hkp.controller.sys.announcement;


import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.annotion.IgnoreLog;
import com.kfwy.hkp.controller.sys.announcement.vo.AnnouncementServiceRequest;
import com.kfwy.hkp.controller.sys.announcement.vo.AnnouncementServiceResponse;
import com.kfwy.hkp.sys.announcement.business.IAnnouncementManager;
import com.kfwy.hkp.sys.announcement.entity.AnnouncementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/announcement")
public class AnnouncementService extends SpringRestService {

    @Autowired
    private IAnnouncementManager announcementManager;
    @IgnoreLog
    @RequestMapping(path = "/create",
            method = RequestMethod.POST)
    public ResponseEntity<IServiceResponse> create(@RequestBody AnnouncementServiceRequest request){

        AnnouncementServiceResponse response = new AnnouncementServiceResponse();

        List<String> deptCodes=request.getDeptCodes();
        String announcementType=request.getAnnouncementType();

        announcementManager.createAnnouncement(deptCodes,announcementType);

        return this.success(response);
    }
    @IgnoreLog
    @RequestMapping(path = "/query",
            method = RequestMethod.POST)
    public ResponseEntity<IServiceResponse> query(){

        AnnouncementServiceResponse response = new AnnouncementServiceResponse();
        AnnouncementEntity announcementEntity =announcementManager.query();

        response.setResult(announcementEntity);
        return this.success(response);
    }

}
