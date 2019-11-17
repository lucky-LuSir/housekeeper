package com.kfwy.hkp.controller.count.report;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.bi.count.business.IFinanceWagesReportManager;
import com.kfwy.hkp.bi.count.business.IFocusPayBackWageReportManager;
import com.kfwy.hkp.bi.count.business.IFocusWageReportManager;
import com.kfwy.hkp.bi.count.business.IHrWageReportManager;
import com.kfwy.hkp.bi.count.dto.FinanceWagesReportDto;
import com.kfwy.hkp.bi.count.dto.HrWageReportDto;
import com.kfwy.hkp.bi.count.entity.FocusPayBackWageReportEntity;
import com.kfwy.hkp.bi.count.entity.FocusWageReportEntity;
import com.kfwy.hkp.common.annotion.IgnoreLog;
import com.kfwy.hkp.common.easyexcel.export.ExportConfigFactory;
import com.kfwy.hkp.common.easyexcel.export.FileExportor;
import com.kfwy.hkp.common.easyexcel.export.domain.common.ExportConfig;
import com.kfwy.hkp.common.easyexcel.export.exception.FileExportException;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.controller.count.report.vo.PersonnelReportServiceRequest;
import com.kfwy.hkp.controller.count.report.vo.PersonnelReportServiceResponse;
import com.kfwy.hkp.controller.count.report.vo.ReportServiceRequest;
import com.kfwy.hkp.controller.count.report.vo.ReportServiceResponse;
import com.kfwy.hkp.trade.wage.business.IWageManager;
import com.kfwy.hkp.trade.wage.entity.WageEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.util.*;

/**
 * Create By hjh on 2018/10/24
 */
@Controller
@RequestMapping(path = "wages")
public class WageReportService extends SpringRestService {

    /**
     * 财务版工资报表manager接口
     */
    @Autowired
    private IFinanceWagesReportManager financeWagesReportManager;
    @Autowired
    private IWageManager wagesManager;
    @Autowired
    private IHrWageReportManager hrWageReportManager;
    @Autowired
    private IFocusWageReportManager focusWageReportManager;
    @Autowired
    private IFocusPayBackWageReportManager focusPayBackWageReportManager;



    @RequestMapping(path = "/list",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> wagesReport(@RequestBody ReportServiceRequest request) throws ParseException {
        ReportServiceResponse response = new ReportServiceResponse();
        Date inMonth = request.getEntity().getInMonth();
        Date startTime = new Date();
        Date endTime = new Date();
        if (null==inMonth){
            inMonth=new Date();
        }

        //所选月
        Calendar cal = Calendar.getInstance();
        cal.setTime(inMonth);
        cal.set(Calendar.DAY_OF_MONTH,1);
        //获取所在月第一天
        startTime =DateFormatUtil.dayBegin(cal.getTime());
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(inMonth);
        cal2.add(Calendar.MONTH,1);
        cal2.set(Calendar.DAY_OF_MONTH,1);
        //获取所在月下月第一天
        endTime = DateFormatUtil.dayBegin(cal2.getTime());

        Map<String,Object> param=new HashMap<>();
        param.put("payBackTimeStart",startTime);
        param.put("payBackTimeEnd",endTime);
        param.put("isDeleted",false);
        param.put("isSettlement",true);

        PageResult<WageEntity> pageResult = wagesManager.wageReport(param,request.getStart(),request.getPageSize());
        response.setResult(pageResult);
        return this.success(response);
    }

    @RequestMapping(path = "/details",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> wageDetails(@RequestBody ReportServiceRequest request) throws ParseException {
        ReportServiceResponse response = new ReportServiceResponse();
        Map<String,Object> map =new HashMap<>();
        List<WageEntity> wageEntities = wagesManager.wageDetailsReport(map);
        response.setResult(wageEntities);
        return this.success(response);
    }
    @IgnoreLog
    @RequestMapping(path = "/wagesReportExport",
            method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws FileExportException, IOException, ParseException {

        Workbook workbook = null;
        ServletOutputStream out = httpServletResponse.getOutputStream();
        PrintWriter printWriter = null;
        Map<String, Object> map = new HashMap<String, Object>(2);
        Date startTime = new Date();
        Date endTime = new Date();
        Date inMonth = new Date();

        if (null!=httpServletRequest.getParameter("inMonth")&&StringUtils.isNotEmpty(httpServletRequest.getParameter("inMonth"))){
            String monthStr = httpServletRequest.getParameter("inMonth");
            if (StringUtils.isNotEmpty(monthStr)){
                inMonth=DateFormatUtil.dateTransition(httpServletRequest.getParameter("inMonth"));
            }
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(inMonth);
        cal.set(Calendar.DAY_OF_MONTH,1);
        //获取所在月第一天
        startTime =DateFormatUtil.dayBegin(cal.getTime());
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(inMonth);
        cal2.add(Calendar.MONTH,1);
        cal2.set(Calendar.DAY_OF_MONTH,1);
        //获取所在月下月第一天
        endTime = DateFormatUtil.dayBegin(cal2.getTime());

        map.put("payBackTimeStart",startTime);
        map.put("payBackTimeEnd",endTime);
        map.put("isDeleted",false);
        map.put("isSettlement",true);

        String path = "/exportTemplate/" + "wagesReportExport" + ".xml";
        ExportConfig exportConfig = ExportConfigFactory.getExportConfig(WageReportService.class.getResourceAsStream(path));
        StringBuffer name = new StringBuffer();

        //数据
        List<WageEntity> data = wagesManager.wageReport(map);


        try {
            workbook=  FileExportor.getExportWorkbook(exportConfig,data);
            String _name = name.toString() + "工资提成汇总.xlsx";
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
        }catch (Exception e){

        }finally {

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
    @IgnoreLog
    @RequestMapping(path = "/exportDetailsExcel",
            method = RequestMethod.GET)
    public void exportDetailsExcel(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws FileExportException, IOException, ParseException {

//        HSSFWorkbook workbook = null;
        Workbook workbook = null;
        ServletOutputStream out = httpServletResponse.getOutputStream();
        PrintWriter printWriter = null;
        Map<String, Object> map = new HashMap<String, Object>(2);

        Date startTime = new Date();
        Date endTime = new Date();
        Date inMonth = new Date();



        if (null!=httpServletRequest.getParameter("inMonth")){
            String monthStr = httpServletRequest.getParameter("inMonth");
            if (StringUtils.isNotEmpty(monthStr)){
                inMonth=DateFormatUtil.dateTransition(httpServletRequest.getParameter("inMonth"));
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(inMonth);
            cal.set(Calendar.DAY_OF_MONTH,1);
            //获取所在月第一天
            startTime =DateFormatUtil.dayBegin(cal.getTime());
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(inMonth);
            cal2.add(Calendar.MONTH,1);
            cal2.set(Calendar.DAY_OF_MONTH,1);
            //获取所在月下月第一天
            endTime = DateFormatUtil.dayBegin(cal2.getTime());
        }else{
            if (null!=httpServletRequest.getParameter("startTime")&&StringUtils.isNotEmpty(httpServletRequest.getParameter("startTime"))){
                String startTimeStr = httpServletRequest.getParameter("startTime");
                if (StringUtils.isNotEmpty(startTimeStr)){
                    startTime=DateFormatUtil.dayBegin(DateFormatUtil.dateTransition(httpServletRequest.getParameter("startTime")));
                }
            }else {
                throw new RemoveStackBusinessException ("起止时间不能为空");
            }
            if (null!=httpServletRequest.getParameter("endTime")&&StringUtils.isNotEmpty(httpServletRequest.getParameter("endTime"))){
                String endTimeStr = httpServletRequest.getParameter("endTime");
                if (StringUtils.isNotEmpty(endTimeStr)){
                    endTime=DateFormatUtil.dayEnd(DateFormatUtil.dateTransition(httpServletRequest.getParameter("endTime")));
                }
            }else {
                throw new RemoveStackBusinessException("起止时间不能为空");
            }
        }


        map.put("payBackTimeStart",startTime);
        map.put("payBackTimeEnd",endTime);
        map.put("isDeleted",false);


        String path = "/exportTemplate/" + "wagesDetailsReportExport" + ".xml";
        ExportConfig exportConfig = ExportConfigFactory.getExportConfig(WageReportService.class.getResourceAsStream(path));
        StringBuffer name = new StringBuffer();

        //数据
        List<WageEntity> data = wagesManager.wageDetailsReport(map);


        try {
            workbook=  FileExportor.getExportWorkbook(exportConfig,data);
            String _name = name.toString() + "工资提成明细.xlsx";
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
        }catch (Exception e){

        }finally {

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

    @RequestMapping(path = "/financeWagesQuery",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> financeWagesReportQuery(@RequestBody ReportServiceRequest request){
        ReportServiceResponse response = new ReportServiceResponse();
        Map<String, Object> map = new HashMap<String, Object>(2);
        Date startTime = null;
        Date endTime = null;
        if(null == request.getEntity().getStartDate()){
            startTime = DateFormatUtil.monthBeginDay(DateFormatUtil.dayBeginFormat("2017-08-01"));
        }else{
            startTime = DateFormatUtil.dayBegin(request.getEntity().getStartDate());
        }
        map.put("orderTimeStart",startTime);
        if(null == request.getEntity().getEndDate()){
            endTime = DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime());
        }else{
            endTime = DateFormatUtil.dayEnd(request.getEntity().getEndDate());
        }
        map.put("orderTimeEnd",endTime);
        map.put("isDeleted",false);
        PageResult<FinanceWagesReportDto> pageResult = financeWagesReportManager.financeWagesReportSearch(map,request.getStart(),request.getPageSize());
        response.setResult(pageResult);
        return this.success(response);
    }

    @IgnoreLog
    @RequestMapping(path = "/financeWagesReportExport",
            method = RequestMethod.GET)
    public void export(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws FileExportException, IOException {

//        HSSFWorkbook workbook = null;
        Workbook workbook = null;
        ServletOutputStream out = httpServletResponse.getOutputStream();
        PrintWriter printWriter = null;
        Map<String, Object> map = new HashMap<String, Object>(2);

        if (null!=httpServletRequest.getParameter("startDate")&&StringUtils.isNotEmpty(httpServletRequest.getParameter("startDate"))){
            String startTime = httpServletRequest.getParameter("startDate");
            if (!startTime.equals("null")){
                map.put("orderTimeStart",DateFormatUtil.dayBegin(new Date(new Long(startTime))));
            }
        }
        if (null!=httpServletRequest.getParameter("endDate")&&StringUtils.isNotEmpty(httpServletRequest.getParameter("endDate"))){
            String endTime = httpServletRequest.getParameter("endDate");
            if (!endTime.equals("null")){
                map.put("orderTimeEnd",DateFormatUtil.dayEnd(new Date(new Long(endTime))));
            }
        }
//        Date startTime  = DateFormatUtil.monthBeginDay(DateFormatUtil.dayBeginFormat("2017-08-01"));


        String path = "/exportTemplate/" + "financeWagesReportExport" + ".xml";
        ExportConfig exportConfig = ExportConfigFactory.getExportConfig(WageReportService.class.getResourceAsStream(path));
        StringBuffer name = new StringBuffer();

        //数据
        List<FinanceWagesReportDto> data = financeWagesReportManager.query(map);


        try {
            workbook=  FileExportor.getExportWorkbook(exportConfig,data);
            String _name = name.toString() + "财务版报表.xlsx";
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
        }catch (Exception e){

        }finally {

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



    /**
     * 人事工资报表(改)
     * @param
     * @return
     */
    @RequestMapping(path = "/hrWageReportSearch",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> hrWageReportSearch(@RequestBody PersonnelReportServiceRequest request){
        PersonnelReportServiceResponse response = new PersonnelReportServiceResponse();
        Map<String, Object> map = new HashMap<String, Object>(2);
//        Date startTime = null;
//        Date endTime = null;
//        if(null == request.getStartTime()){
//            startTime = DateFormatUtil.monthBeginDay(DateFormatUtil.dayBeginFormat("2017-08-01"));
//        }else{
//            startTime = DateFormatUtil.dayBegin(request.getStartTime());
//        }
//        map.put("orderTimeStart",startTime);
//        if(null == request.getEndTime()){
//            endTime = DateFormatUtil.dayEnd(DateFormatUtil.getCurrentDateTime());
//        }else{
//            endTime = DateFormatUtil.dayEnd(request.getEndTime());
//        }
//        map.put("orderTimeEnd",endTime);
        map.put("isDeleted",false);
        PageResult<HrWageReportDto> pageResult = hrWageReportManager.hrWageReportSearch(map,request.getStart(),request.getPageSize());
        response.setResult(pageResult);
        return this.success(response);
    }



    /**
     * 人事工资报表导出(改)
     * @param
     * @return
     */
    @IgnoreLog
    @RequestMapping(path = "/hrWageReportExport",
            method = RequestMethod.GET)
    public void hrWageReportExport(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, FileExportException {

        Map<String, Object> map = new HashMap<String, Object>(2);


//        if (null!=httpServletRequest.getParameter("startTime")&&StringUtils.isNotEmpty(httpServletRequest.getParameter("startDate"))){
//            String startTime = httpServletRequest.getParameter("startTime");
//            if (!startTime.equals("null")){
//                map.put("orderTimeStart",DateFormatUtil.dayBegin(new Date(new Long(startTime))));
//            }
//        }
//        if (null!=httpServletRequest.getParameter("endTime")&&StringUtils.isNotEmpty(httpServletRequest.getParameter("endDate"))){
//            String endTime = httpServletRequest.getParameter("endTime");
//            if (!endTime.equals("null")){
//                map.put("orderTimeEnd",DateFormatUtil.dayEnd(new Date(new Long(endTime))));
//            }
//        }

        map.put("isDeleted",false);
        PageResult<HrWageReportDto> pageResult = hrWageReportManager.hrWageReportSearch(map, 0, 10000);

        //数据
        List<HrWageReportDto> data = pageResult.getData();
        String dir =  "/exportTemplate/";
        String xmlName =  "hrWageReportExport.xml";
        String exportName = "人事版报表";
        String path =dir+xmlName;

        InputStream inputStream = this.getClass().getResourceAsStream(path);
        ExportConfigFactory.exportMethod(data,httpServletResponse,inputStream,exportName);
    }

    /**
     * 集中获客开单分成所属部门按照人员分成比例划分导出
     */

    @IgnoreLog
    @RequestMapping(path = "/focusWageReportExport",
            method = RequestMethod.GET)
    public void focusReportExport(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, FileExportException {

        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("isDeleted",false);
        List<FocusWageReportEntity> data = focusWageReportManager.selectFocusWageReportByMap(map);

        //数据
        String dir =  "/exportTemplate/";
        String xmlName =  "focusWageReportExport.xml";
        String exportName = "集中获客开单分成所属部门报表";
        String path =dir+xmlName;

        InputStream inputStream = this.getClass().getResourceAsStream(path);
        ExportConfigFactory.exportMethod(data,httpServletResponse,inputStream,exportName);
    }


    @IgnoreLog
    @RequestMapping(path = "/focusPayBackWageReportExport",
            method = RequestMethod.GET)
    public void focusPayBackReportExport(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, FileExportException {

        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("isDeleted",false);
        List<FocusPayBackWageReportEntity> data = focusPayBackWageReportManager.selectFocusPayBackWageReportByMap(map);

        String dir =  "/exportTemplate/";
        String xmlName =  "focusPayBackWageReportExport.xml";
        String exportName = "集中获客回款提成所属部门报表";
        String path =dir+xmlName;

        InputStream inputStream = this.getClass().getResourceAsStream(path);
        ExportConfigFactory.exportMethod(data,httpServletResponse,inputStream,exportName);
    }

}
