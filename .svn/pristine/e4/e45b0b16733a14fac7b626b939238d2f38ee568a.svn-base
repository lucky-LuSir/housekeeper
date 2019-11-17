package com.kfwy.hkp.controller.count.report;

import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.bi.count.business.*;
import com.kfwy.hkp.bi.count.dto.CommonReportDto;
import com.kfwy.hkp.bi.count.dto.CommonTotalDto;
import com.kfwy.hkp.bi.count.dto.PersonnelReportDto;
import com.kfwy.hkp.bi.count.dto.ReportDto;
import com.kfwy.hkp.bi.count.entity.*;
import com.kfwy.hkp.common.annotion.IgnoreLog;
import com.kfwy.hkp.common.easyexcel.export.ExportConfigFactory;
import com.kfwy.hkp.common.easyexcel.export.FileExportor;
import com.kfwy.hkp.common.easyexcel.export.domain.common.ExportConfig;
import com.kfwy.hkp.common.easyexcel.export.exception.FileExportException;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.controller.count.report.vo.PersonnelReportServiceRequest;
import com.kfwy.hkp.controller.count.report.vo.PersonnelReportServiceResponse;
import com.kfwy.hkp.controller.count.report.vo.ReportServiceRequest;
import com.kfwy.hkp.controller.count.report.vo.ReportServiceResponse;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserLevel;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.*;

/**
 * Create By hjh on 2018/10/24
 */
@Controller
@RequestMapping(path = "report")
public class ReportService extends SpringRestService {
    /**
     * 业绩统计报表manager接口
     */
    @Autowired
    private IReportManager reportManager;
    /**
     * 人事版工资报表manager接口
     */
    @Autowired
    private IPersonnelReportManager personnelReportManager;
    /**
     * 多次带看统计报表manager接口
     */
    @Autowired
    private IMuchSeeReportManager muchFollowupReportManager;
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
     * 房源字典数据报表manager接口
     */
    @Autowired
    private IHouseDictionaryManager houseDictionaryManager;
    /**
     * 订单和分成比报表manager接口
     */
    @Autowired
    private IOrderDivideRatioManager orderDivideRatioManager;
    /**
     * 订单和分成比报表manager接口
     */
    @Autowired
    private INotOrderPersonManager notOrderPersonManager;
    /**
     * 合同周期面板
     */
    @Autowired
    private IContractReportManager contractReportManager;
    /**
     * 房源客户的年度统计
     */
    @Autowired
    private IHosAndCusTotalManager hosAndCusTotalManager;
    /**
     * 房源客户的年度统计
     */
    @Autowired
    private IGraphManager graphManager;
    /**
     * 作日报告统计
     */
    @Autowired
    private ILastDayReportManager lastDayReportManager;
    /**
     * 集中获客报表
     */
    @Autowired
    private IFocusReportManager focusReportManager;

    /**
     * 多次带看统计报表
     * @param
     * @return
     */
    @RequestMapping(path = "/cusMuchFollowup",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> count(@RequestBody ReportServiceRequest request){

        ReportServiceResponse response = new ReportServiceResponse();

        Date startTime = null;
        Date endTime = null;

        if(null == request.getStartDate()){
            startTime = DateFormatUtil.monthBeginDay(DateFormatUtil.getCurrentDateTime());
        }else{
            startTime = DateFormatUtil.dayBegin(request.getStartDate());
        }

        if(null == request.getEndDate()){
            endTime = DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime());
        }else{
            endTime = DateFormatUtil.dayEnd(request.getEndDate());
        }

        CommonReportDto<MuchSeeEntity> count = muchFollowupReportManager.count(startTime, endTime, request.getCode());
        response.setResult(count);
        return this.success(response);
    }

    /**
     * 业绩统计报表
     * @param
     * @return
     */
    @RequestMapping(path = "/countDto",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> counts(@RequestBody ReportServiceRequest request){

        ReportServiceResponse response = new ReportServiceResponse();

        if (((UserEntity) CurrentContext.getUserInfo()).getUserLevel().equals(UserLevel.K2)){
            throw new BusinessException("needPay","跳转引导页面");
        }


        Date startTime = null;
        Date endTime = null;

        if(null == request.getEntity().getStartDate()){
            startTime = DateFormatUtil.monthBeginDay(DateFormatUtil.getCurrentDateTime());
        }else{
            startTime = DateFormatUtil.dayBegin(request.getEntity().getStartDate());
        }

        if(null == request.getEntity().getEndDate()){
            endTime = DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime());
        }else{
            endTime = DateFormatUtil.dayEnd(request.getEntity().getEndDate());
        }

        ReportDto reportDto = reportManager.countDto(startTime, endTime, request.getEntity().getDeptCode(),request.getDynamic());
        response.setResult(reportDto);
        return this.success(response);
    }

    /**
     * 市场供需报表
     * @param
     * @return
     */
    @RequestMapping(path = "/marketAnalysis",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> marketAnalysis(@RequestBody ReportServiceRequest request){

        ReportServiceResponse response = new ReportServiceResponse();

        Date startTime = null;
        Date endTime = null;

        if(null == request.getStartDate()){
            startTime = DateFormatUtil.monthBeginDay(DateFormatUtil.getCurrentDateTime());
        }else{
            startTime = DateFormatUtil.dayBegin(request.getStartDate());
        }

        if(null == request.getEndDate()){
            endTime = DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime());
        }else{
            endTime = DateFormatUtil.dayEnd(request.getEndDate());
        }

        CommonReportDto<MarketAnalysisEntity> ms = marketAnalysisManager.count(startTime, endTime, request.getCode());
        response.setResult(ms);
        return this.success(response);
    }

    /**
     * 客户转换率报表
     * @param
     * @return
     */
    @RequestMapping(path = "/convertRate",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> convertRate(@RequestBody ReportServiceRequest request){

        ReportServiceResponse response = new ReportServiceResponse();

        Date startTime = null;
        Date endTime = null;

        if(null == request.getStartDate()){
            startTime = DateFormatUtil.monthBeginDay(DateFormatUtil.getCurrentDateTime());
        }else{
            startTime = DateFormatUtil.dayBegin(request.getStartDate());
        }

        if(null == request.getEndDate()){
            endTime = DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime());
        }else{
            endTime = DateFormatUtil.dayEnd(request.getEndDate());
        }

        CommonReportDto<ConvertRateEntity> count = convertRateManager.count(startTime, endTime, request.getCode());
        response.setResult(count);
        return this.success(response);
    }

    /**
     * 数据统计报表
     * @param
     * @return
     */
    @RequestMapping(path = "/record",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> record(@RequestBody ReportServiceRequest request){

        ReportServiceResponse response = new ReportServiceResponse();
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();

        if (cur.getUserLevel().equals(UserLevel.K2)){
            throw new BusinessException("101","跳转到付费引导样式A");
        }

        Date startTime = null;
        Date endTime = null;

        if(null == request.getStartDate()){
            startTime = DateFormatUtil.monthBeginDay(DateFormatUtil.getCurrentDateTime());
        }else{
            startTime = DateFormatUtil.dayBegin(request.getStartDate());
        }

        if(null == request.getEndDate()){
            endTime = DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime());
        }else{
            endTime = DateFormatUtil.dayEnd(request.getEndDate());
        }

        List<RecordEntity> r = recordManager.count(request.getCode(), startTime, endTime);
        response.setResult(r);
        return this.success(response);
    }

    /**
     * 订单和分成比报表
     * @param
     * @return
     */
    @RequestMapping(path = "/orderDivideRatio",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> orderDivideRatio(@RequestBody ReportServiceRequest request){

        ReportServiceResponse response = new ReportServiceResponse();

        Date startTime = null;
        Date endTime = null;

        if(null == request.getStartDate()){
            startTime = DateFormatUtil.monthBeginDay(DateFormatUtil.getCurrentDateTime());
        }else{
            startTime = DateFormatUtil.dayBegin(request.getStartDate());
        }

        if(null == request.getEndDate()){
            endTime = DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime());
        }else{
            endTime = DateFormatUtil.dayEnd(request.getEndDate());
        }

        CommonReportDto<OrderDivideRatioEntity> reportDto = orderDivideRatioManager.select(startTime, endTime, request.getCode());
        response.setResult(reportDto);
        return this.success(response);
    }

    /**
     * 未开单人员报表
     * @param
     * @return
     */
    @RequestMapping(path = "/notOrderPerson",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> notOrderPerson(@RequestBody ReportServiceRequest request){

        ReportServiceResponse response = new ReportServiceResponse();

        Date startTime = null;
        Date endTime = null;

        if(null == request.getStartDate()){
            startTime = DateFormatUtil.monthBeginDay(DateFormatUtil.getCurrentDateTime());
        }else{
            startTime = DateFormatUtil.dayBegin(request.getStartDate());
        }

        if(null == request.getEndDate()){
            endTime = DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime());
        }else{
            endTime = DateFormatUtil.dayEnd(request.getEndDate());
        }

        CommonReportDto<NotOrderPersonEntity> reportDto = notOrderPersonManager.select(startTime, endTime, request.getCode());
        response.setResult(reportDto);
        return this.success(response);
    }


    /**
     * 房源客户折线图报表
     * @param
     * @return
     */
    @RequestMapping(path = "/hosAndCusTotal",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> hosAndCusTotal(@RequestBody ReportServiceRequest request){

        ReportServiceResponse response = new ReportServiceResponse();

        String startTime = null;
        String endTime = null;

        if (null == request.getStartDate()) {
            startTime = DateUtil.format(new Date(), "yyyy") + "-01";
        } else {
            startTime = DateUtil.format(request.getStartDate(), "yyyy") + "-01";
        }

        if (null == request.getStartDate()) {
            endTime = DateUtil.format(new Date(), "yyyy-MM");
        } else {
            endTime = DateUtil.format(request.getStartDate(), "yyyy") + "-12";
        }

        CommonTotalDto<HosAndCusTotalEntity> select = hosAndCusTotalManager.select(startTime, endTime);
        response.setResult(select);
        return this.success(response);
    }

    /**
     * 集中获客报表
     * @param
     * @return
     */
    @RequestMapping(path = "/focus",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> focus(@RequestBody ReportServiceRequest request){

        ReportServiceResponse response = new ReportServiceResponse();

        Date startTime = null;
        Date endTime = null;

        if (null == request.getStartDate()) {
            startTime = DateUtil.beginOfMonth(new Date());
        } else {
            startTime = DateUtil.beginOfDay(request.getStartDate());
        }

        if (null == request.getStartDate()) {
            endTime = DateUtil.endOfDay(new Date());
        } else {
            endTime = DateUtil.endOfDay(request.getEndDate());
        }

        CommonReportDto<FocusReportEntity> select = focusReportManager.count(request.getCode(),startTime, endTime);
        response.setResult(select);
        return this.success(response);
    }

    /**
     * 房源字典数据报表
     * @param
     * @return
     */
    @RequestMapping(path = "/houseDictionary",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> houseDictionary(@RequestBody ReportServiceRequest request){
        ReportServiceResponse response = new ReportServiceResponse();

        List<HouseDictionaryEntity> r = houseDictionaryManager.select(request.getCode());
        response.setResult(r);
        return this.success(response);
    }

    /**
     * 房源字典数据汇总
     * @param
     * @return
     */
    @RequestMapping(path = "/houseDictionaryTotal",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> houseDictionaryTotal(@RequestBody ReportServiceRequest request){
        ReportServiceResponse response = new ReportServiceResponse();

        List<HouseDictionaryEntity> r = houseDictionaryManager.select(request.getCode());
        List<HouseDictionaryEntity> totalList = new ArrayList<HouseDictionaryEntity>();
        HouseDictionaryEntity totalEntity = new HouseDictionaryEntity();
        Long marketParkTotal = new Long(0);
        Long parkTotal = new Long(0);
        Long parkHotRent = new Long(0);
        Long parkRented = new Long(0);

        Long marketHouseTotal = new Long(0);
        Long houseTotal = new Long(0);
        Long houseHotRent = new Long(0);
        Long houseRented = new Long(0);

        Float marketHouseAreaTotal = new Float(0);
        Float houseAreaTotal = new Float(0);
        Float houseAreaHotRent = new Float(0);
        Float houseAreaRented = new Float(0);
        for(int i=0;i<r.size();i++){
            marketParkTotal = marketParkTotal + r.get(i).getMarketParkTotal();
            parkTotal = parkTotal + r.get(i).getParkTotal();
            parkHotRent = parkHotRent + r.get(i).getParkHotRent();
            parkRented = parkRented + r.get(i).getParkRented();

            marketHouseTotal = marketHouseTotal + r.get(i).getMarketHouseTotal();
            houseTotal = houseTotal + r.get(i).getHouseTotal();
            houseHotRent = houseHotRent + r.get(i).getHouseHotRent();
            houseRented = houseRented + r.get(i).getHouseRented();

            marketHouseAreaTotal = marketHouseAreaTotal + r.get(i).getMarketHouseAreaTotal();
            houseAreaTotal = houseAreaTotal + r.get(i).getHouseAreaTotal();
            houseAreaHotRent = houseAreaHotRent + r.get(i).getHouseAreaHotRent();
            houseAreaRented = houseAreaRented + r.get(i).getHouseAreaRented();

        }
        //totalEntity.setMarketParkTotal(marketParkTotal);
        //totalEntity.setParkHotRent(parkHotRent);
        //totalEntity.setParkRented(parkRented);
        totalEntity.setParkTotal(parkTotal);


        //totalEntity.setMarketHouseTotal(marketHouseTotal);
        //totalEntity.setHouseHotRent(houseHotRent);
        //totalEntity.setHouseRented(houseRented);
        totalEntity.setHouseTotal(houseTotal);


        //totalEntity.setMarketHouseAreaTotal(marketHouseAreaTotal);
        //totalEntity.setHouseAreaHotRent(houseAreaHotRent);
        //totalEntity.setHouseAreaRented(houseAreaRented);
        totalEntity.setHouseAreaTotal(houseAreaTotal);


        response.setResult(totalEntity);
        return this.success(response);
    }

    /**
     * 房源字典数据汇总,只查传的当前地区code
     * @param
     * @return
     */
    @RequestMapping(path = "/getHosDicByCurCode",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> getHosDicByCurCode(@RequestBody ReportServiceRequest request){
        ReportServiceResponse response = new ReportServiceResponse();

        HouseDictionaryEntity totalEntity = houseDictionaryManager.getHosDicByCurCode(request.getCode());


        response.setResult(totalEntity);
        return this.success(response);
    }
    /**
     * 客户合同周期
     * @param
     * @return
     */
    @RequestMapping(path = "/contractPeriodBoardCus",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> contractPeriodBoard(@RequestBody ReportServiceRequest request){
        ReportServiceResponse response = new ReportServiceResponse();
        Map<String,Object> param = new HashMap<>();

        Date startTime=new Date();
        Date endTime = new Date();

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.MONTH,-11);
        cal2.set(Calendar.DAY_OF_MONTH,1);

        startTime=cal2.getTime();

        cal2.add(Calendar.MONTH,24);
        cal2.set(Calendar.DAY_OF_MONTH,1);
        endTime = cal2.getTime();

        param.put("startTime",startTime);
        param.put("endTime",endTime);

        List<ContractPeriodEntity> r = contractReportManager.selectContractPeriodCus(param,startTime);
        response.setResult(r);
        return this.success(response);
    }


    /**
     * 房源合同周期
     * @param
     * @return
     */
    @RequestMapping(path = "/contractPeriodBoardHos",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> contractPeriodBoardHos(@RequestBody ReportServiceRequest request){
        ReportServiceResponse response = new ReportServiceResponse();

        Map<String,Object> param = new HashMap<>();

        Date startTime=new Date();
        Date endTime = new Date();

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.MONTH,-11);
        cal2.set(Calendar.DAY_OF_MONTH,1);

        startTime=cal2.getTime();

        cal2.add(Calendar.MONTH,24);
        cal2.set(Calendar.DAY_OF_MONTH,1);
        endTime = cal2.getTime();

        param.put("startTime",startTime);
        param.put("endTime",endTime);

        List<ContractPeriodEntity> r = contractReportManager.selectContractPeriodHos(param,startTime);
        response.setResult(r);
        return this.success(response);
    }

    /**
     * 预约委托折线
     * @param
     * @return
     */
    @RequestMapping(path = "/graph",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> graph(@RequestBody ReportServiceRequest request){
        ReportServiceResponse response = new ReportServiceResponse();
        String timeStr =request.getTimeStr();
        Date time = request.getTime();
        Map<String,Object> param = new HashMap<>();
        if (StringUtils.isEmpty(request.getRegion())){
            if (StringUtils.isEmpty(request.getCity())){
                if (StringUtils.isNotEmpty(request.getProvince())){
                    param.put("province",request.getProvince());
                }
            }else {
                param.put("city",request.getCity());
            }
        }else {
            param.put("region",request.getRegion());
        }

        GraphEntity result = graphManager.selectEntrustAndBespeak(timeStr,time,param);
        response.setResult(result);
        return this.success(response);
    }


    /**
     * 作日报告
     * @param
     * @return
     */
    @RequestMapping(path = "/selectLastDayReport",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectLastDayReport(@RequestBody ReportServiceRequest request) throws ParseException {
        ReportServiceResponse response = new ReportServiceResponse();
        Date time = request.getTime();
        List<LastDayReportEntity> result = lastDayReportManager.selectLastDayReport(time);
        response.setResult(result);
        return this.success(response);
    }

    /**
     * 人事工资报表
     * @param
     * @return
     */
    @RequestMapping(path = "/personnelReportSearch",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> personnelReportSearch(@RequestBody PersonnelReportServiceRequest request){
        PersonnelReportServiceResponse response = new PersonnelReportServiceResponse();
        Map<String, Object> map = new HashMap<String, Object>(2);
        Date startTime = null;
        Date endTime = null;
        if(null == request.getStartTime()){
            startTime = DateFormatUtil.monthBeginDay(DateFormatUtil.dayBeginFormat("2017-08-01"));
        }else{
            startTime = DateFormatUtil.dayBegin(request.getStartTime());
        }
        map.put("orderTimeStart",startTime);
        if(null == request.getEndTime()){
            endTime = DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime());
        }else{
            endTime = DateFormatUtil.dayEnd(request.getEndTime());
        }
        map.put("orderTimeEnd",endTime);
        map.put("isDeleted",false);
        PageResult<PersonnelReportDto> pageResult = personnelReportManager.personnelReportSearch(map,request.getStart(),request.getPageSize());
        response.setResult(pageResult);
        return this.success(response);
    }



    /**
     * 人事工资报表导出
     * @param
     * @return
     */
    @IgnoreLog
    @RequestMapping(path = "/personnelReportExport",
            method = RequestMethod.GET)
    public void personnelReportExport(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, FileExportException {

        Map<String, Object> map = new HashMap<String, Object>(2);


        if (null!=httpServletRequest.getParameter("startTime")&&StringUtils.isNotEmpty(httpServletRequest.getParameter("startDate"))){
            String startTime = httpServletRequest.getParameter("startTime");
            if (!startTime.equals("null")){
                map.put("orderTimeStart",DateFormatUtil.dayBegin(new Date(new Long(startTime))));
            }
        }
        if (null!=httpServletRequest.getParameter("endTime")&&StringUtils.isNotEmpty(httpServletRequest.getParameter("endDate"))){
            String endTime = httpServletRequest.getParameter("endTime");
            if (!endTime.equals("null")){
                map.put("orderTimeEnd",DateFormatUtil.dayEnd(new Date(new Long(endTime))));
            }
        }

        map.put("isDeleted",false);
        PageResult<PersonnelReportDto> pageResult = personnelReportManager.personnelReportSearch(map, 0, 10000);

        //数据
        List<PersonnelReportDto> data = pageResult.getData();
        String dir =  "/exportTemplate/";
        String xmlName =  "personnelReportExport.xml";
        String exportName = "人事版报表";
        exportMethod(data,httpServletResponse,dir,xmlName,exportName);
    }

    /**
     *
     * @param data 需要导出的数据
     * @param httpServletResponse 响应类型
     * @param dir
     * @param xmlName
     * @throws IOException
     * @throws FileExportException
     */
    private void exportMethod(List<?> data,HttpServletResponse httpServletResponse,String dir,String xmlName,String exportName) throws IOException, FileExportException {
        Workbook workbook = null;
        ServletOutputStream out = httpServletResponse.getOutputStream();
        PrintWriter printWriter = null;
        String path =dir+xmlName;
        ExportConfig exportConfig = ExportConfigFactory.getExportConfig(ReportService.class.getResourceAsStream(path));
        StringBuffer name = new StringBuffer();
        try {
            workbook = FileExportor.getExportWorkbook(exportConfig, data);
            String _name = name.toString() +exportName+".xlsx";
            String filename = new String(_name.getBytes("UTF-8"), "ISO8859-1");
            httpServletResponse.setHeader("Content-Type", "application/vnd.ms-excel");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + filename);
            httpServletResponse.setContentType("application/vnd.ms-excel");
            httpServletResponse.setCharacterEncoding("UTF-8");
            out = httpServletResponse.getOutputStream();
            if (workbook != null) {
                workbook.write(out);
            } else {
                workbook.write(out);
            }
            out.flush();
        } catch (Exception e) {

        } finally {
            if (printWriter != null) {
                try {
                    printWriter.close();
                } catch (Exception e) {
                    printWriter = null;
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    out = null;
                }
            }
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    workbook = null;
                }
            }
        }
    }
}
