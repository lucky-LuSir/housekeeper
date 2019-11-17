package com.kfwy.hkp.controller.count.report;

import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.bi.count.business.*;
import com.kfwy.hkp.bi.count.dto.CommonReportDto;
import com.kfwy.hkp.bi.count.dto.ReportDto;
import com.kfwy.hkp.bi.count.entity.*;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.controller.count.report.vo.ReportApiServiceRequest;
import com.kfwy.hkp.controller.count.report.vo.ReportApiServiceResponse;
import com.kfwy.hkp.controller.count.report.vo.ReportServiceResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create By hjh on 2018/10/24
 */
@Controller
@RequestMapping(path = "reportApi")
public class ReportApiService extends SpringRestService {
    /**
     * 业绩统计报表manager接口
     */
    @Autowired
    private IReportManager reportManager;
    /**
     * 多次带看统计报表manager接口
     */
    @Autowired
    private IMuchSeeReportManager muchSeeReportManager;
    /**
     * 市场供需报表manager接口
     */
    @Autowired
    private IMarketAnalysisManager marketAnalysisManager;
    /**
     * 客户转换率报表manager接口
     */
    @Autowired
    private IConvertRateManager convertRateManager;
    /**
     * 数据统计报表manager接口
     */
    @Autowired
    private IRecordManager recordManager;
    /**
     * 订单和分成比报表manager接口
     */
    @Autowired
    private IOrderDivideRatioManager orderDivideRatioManager;
    /**
     * 未开单人员报表manager接口
     */
    @Autowired
    private INotOrderPersonManager notOrderPersonManager;
    /**
     * 新人开单报表manager接口
     */
    @Autowired
    private INewPersonOrderManager newPersonOrderManager;


    /**
     * 多次带看统计报表
     *
     * @param
     * @return
     */
    @RequestMapping(path = "/cusMuchFollowup",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> count(@RequestBody ReportApiServiceRequest request) {

        // 校验key
        this.checkAuth(request.getKey());

        ReportApiServiceResponse response = new ReportApiServiceResponse();

        Date startTime = null;
        Date endTime = null;

        if (null == request.getStartDate()) {
            startTime = DateFormatUtil.monthBeginDay(DateFormatUtil.getCurrentDateTime());
        } else {
            startTime = DateFormatUtil.dayBegin(request.getStartDate());
        }

        if (null == request.getEndDate()) {
            endTime = DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime());
        } else {
            endTime = DateFormatUtil.dayEnd(request.getEndDate());
        }

        CommonReportDto<MuchSeeEntity> count = muchSeeReportManager.count(startTime, endTime, request.getCode());
        response.setResult(count.getReportEntities());
        return this.success(response);
    }

    /**
     * 业绩统计报表
     *
     * @param
     * @return
     */
    @RequestMapping(path = "/achievement",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> counts(@RequestBody ReportApiServiceRequest request) {

        // 校验key
        this.checkAuth(request.getKey());

        ReportApiServiceResponse response = new ReportApiServiceResponse();

        Date startTime = null;
        Date endTime = null;

        if (null == request.getStartDate()) {
            startTime = DateFormatUtil.monthBeginDay(DateFormatUtil.getCurrentDateTime());
        } else {
            startTime = DateFormatUtil.dayBegin(request.getStartDate());
        }

        if (null == request.getEndDate()) {
            endTime = DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime());
        } else {
            endTime = DateFormatUtil.dayEnd(request.getEndDate());
        }

        ReportDto reportDto = reportManager.countDto(startTime, endTime, request.getCode(), request.getDynamic());
        response.setResult(reportDto.getReportEntities());
        return this.success(response);
    }

    /**
     * 市场供需报表
     *
     * @param
     * @return
     */
    @RequestMapping(path = "/marketAnalysis",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> marketAnalysis(@RequestBody ReportApiServiceRequest request) {

        // 校验key
        this.checkAuth(request.getKey());

        ReportApiServiceResponse response = new ReportApiServiceResponse();

        Date startTime = null;
        Date endTime = null;

        if (null == request.getStartDate()) {
            startTime = DateFormatUtil.monthBeginDay(DateFormatUtil.getCurrentDateTime());
        } else {
            startTime = DateFormatUtil.dayBegin(request.getStartDate());
        }

        if (null == request.getEndDate()) {
            endTime = DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime());
        } else {
            endTime = DateFormatUtil.dayEnd(request.getEndDate());
        }

        CommonReportDto<MarketAnalysisEntity> ms = marketAnalysisManager.count(startTime, endTime, request.getCode());
        response.setResult(ms.getReportEntities());
        return this.success(response);
    }

    /**
     * 客户转换率报表
     *
     * @param
     * @return
     */
    @RequestMapping(path = "/convertRate",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> convertRate(@RequestBody ReportApiServiceRequest request) {

        // 校验key
        this.checkAuth(request.getKey());

        ReportApiServiceResponse response = new ReportApiServiceResponse();

        Date startTime = null;
        Date endTime = null;

        if (null == request.getStartDate()) {
            startTime = DateFormatUtil.monthBeginDay(DateFormatUtil.getCurrentDateTime());
        } else {
            startTime = DateFormatUtil.dayBegin(request.getStartDate());
        }

        if (null == request.getEndDate()) {
            endTime = DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime());
        } else {
            endTime = DateFormatUtil.dayEnd(request.getEndDate());
        }

        CommonReportDto<ConvertRateEntity> count = convertRateManager.count(startTime, endTime, request.getCode());
        List<ConvertRateEntity> result = new ArrayList<>();
        for (ConvertRateEntity c : count.getReportEntities()) {
            if (c.getConvertType().equals("获客到有效百分比") || c.getConvertType().equals("有效到成交百分比") || c.getConvertType().equals("获客到成交百分比")) {
                c.setWbSource(c.getWbSource().split("%")[0]);
                c.setGjSource(c.getGjSource().split("%")[0]);
                c.setFrontSource(c.getFrontSource().split("%")[0]);
                c.setPartSource(c.getPartSource().split("%")[0]);
                c.setOutdoorsSource(c.getOutdoorsSource().split("%")[0]);
                c.setOtherSource(c.getOtherSource().split("%")[0]);
                c.setCountSource(c.getCountSource().split("%")[0]);
                c.setSweepSource(c.getSweepSource().split("%")[0]);
                c.setCusService(c.getCusService().split("%")[0]);
                c.setMdbdSource(c.getMdbdSource().split("%")[0]);
            }
            result.add(c);
        }
        response.setResult(result);
        return this.success(response);
    }

    /**
     * 数据统计报表
     *
     * @param
     * @return
     */
    @RequestMapping(path = "/record",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> record(@RequestBody ReportApiServiceRequest request) {

        // 校验key
        this.checkAuth(request.getKey());

        ReportApiServiceResponse response = new ReportApiServiceResponse();

        Date startTime = null;
        Date endTime = null;

        if (null == request.getStartDate()) {
            startTime = DateFormatUtil.monthBeginDay(DateFormatUtil.getCurrentDateTime());
        } else {
            startTime = DateFormatUtil.dayBegin(request.getStartDate());
        }

        if (null == request.getEndDate()) {
            endTime = DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime());
        } else {
            endTime = DateFormatUtil.dayEnd(request.getEndDate());
        }

        List<RecordEntity> r = recordManager.count(request.getCode(), startTime, endTime);
        response.setResult(r);
        return this.success(response);
    }

    /**
     * 订单和分成比报表
     *
     * @param
     * @return
     */
    @RequestMapping(path = "/orderDivideRatio",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> orderDivideRatio(@RequestBody ReportApiServiceRequest request) {

        // 校验key
        this.checkAuth(request.getKey());

        ReportServiceResponse response = new ReportServiceResponse();

        Date startTime = null;
        Date endTime = null;

        if (null == request.getStartDate()) {
            startTime = DateFormatUtil.monthBeginDay(DateFormatUtil.getCurrentDateTime());
        } else {
            startTime = DateFormatUtil.dayBegin(request.getStartDate());
        }

        if (null == request.getEndDate()) {
            endTime = DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime());
        } else {
            endTime = DateFormatUtil.dayEnd(request.getEndDate());
        }

        CommonReportDto<OrderDivideRatioEntity> reportDto = orderDivideRatioManager.select(startTime, endTime, request.getCode());
        response.setResult(reportDto.getReportEntities());
        return this.success(response);
    }

    /**
     * 未开单人员报表
     *
     * @param
     * @return
     */
    @RequestMapping(path = "/notOrderPerson",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> notOrderPerson(@RequestBody ReportApiServiceRequest request) {

        // 校验key
        this.checkAuth(request.getKey());

        ReportServiceResponse response = new ReportServiceResponse();

        Date startTime = null;
        Date endTime = null;

        if (null == request.getStartDate()) {
            startTime = DateFormatUtil.monthBeginDay(DateFormatUtil.getCurrentDateTime());
        } else {
            startTime = DateFormatUtil.dayBegin(request.getStartDate());
        }

        if (null == request.getEndDate()) {
            endTime = DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime());
        } else {
            endTime = DateFormatUtil.dayEnd(request.getEndDate());
        }

        CommonReportDto<NotOrderPersonEntity> reportDto = notOrderPersonManager.select(startTime, endTime, request.getCode());
        response.setResult(reportDto.getReportEntities());
        return this.success(response);
    }


    /**
     * 新人开单报表
     *
     * @param
     * @return
     */
    @RequestMapping(path = "/newPersonOrder",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> newPersonOrder(@RequestBody ReportApiServiceRequest request) {

        // 校验key
        this.checkAuth(request.getKey());

        ReportServiceResponse response = new ReportServiceResponse();

        Date startTime = null;
        Date endTime = null;

        if (null == request.getStartDate()) {
            startTime = DateFormatUtil.monthBeginDay(DateFormatUtil.getCurrentDateTime());
        } else {
            startTime = DateFormatUtil.dayBegin(request.getStartDate());
        }

        if (null == request.getEndDate()) {
            endTime = DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime());
        } else {
            endTime = DateFormatUtil.dayEnd(request.getEndDate());
        }

        CommonReportDto<NewPersonOrderEntity> reportDto = newPersonOrderManager.select(startTime, endTime, request.getCode());
        response.setResult(reportDto.getReportEntities());
        return this.success(response);
    }

    public void checkAuth(String key) {
        if (StringUtils.isEmpty(key) || !"@Housekeeper$".equals(key)) {
            throw new RemoveStackBusinessException ("无权访问!");
        }
    }

}
