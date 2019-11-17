package com.kfwy.hkp.controller.count.report;

import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.bi.count.business.ILocationAnalysisManager;
import com.kfwy.hkp.bi.count.business.IMarketAnalysisManager;
import com.kfwy.hkp.bi.count.dto.CommonReportDto;
import com.kfwy.hkp.bi.count.entity.LocationAnalysisEntity;
import com.kfwy.hkp.controller.count.report.vo.ReportServiceRequest;
import com.kfwy.hkp.controller.count.report.vo.ReportServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Create By xpp on 2019/02/26
 */
@Controller
@RequestMapping(path = "locationAnalysis")
public class LocationAnalysisService extends SpringRestService {
    /**
     * 业绩统计报表manager接口
     */
//    @Autowired
//    private IReportManager reportManager
//
    /**
     * 市场供需报表manager接口
     */
    @Autowired
    private IMarketAnalysisManager marketAnalysisManager;
    @Autowired
    private ILocationAnalysisManager locationAnalysisManager;




    /**
     * 单个位置统计数据,给位置地图使用
     * @param
     * @return
     */
    @RequestMapping(path = "/locMapCount",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> locMapCount(@RequestBody ReportServiceRequest request){

        ReportServiceResponse response = new ReportServiceResponse();

        CommonReportDto<LocationAnalysisEntity> ls = locationAnalysisManager.locMapCount(request.getCode());
        response.setResult(ls);
        return this.success(response);
    }





}
