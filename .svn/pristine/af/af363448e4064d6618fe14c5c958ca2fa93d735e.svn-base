package com.kfwy.hkp.controller.notice;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.base.SystemConfig;
import com.kfwy.hkp.common.annotion.IgnoreLog;
import com.kfwy.hkp.crm.customer.business.ICustomerManager;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerType;
import com.kfwy.hkp.sys.notice.business.INoticeManager;
import com.kfwy.hkp.sys.notice.business.INoticeRecordManager;
import com.kfwy.hkp.sys.notice.dao.INoticeDbDao;
import com.kfwy.hkp.sys.notice.dao.INoticeRecordDbDao;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.entity.NoticeRecordEntity;
import com.kfwy.hkp.controller.notice.vo.NoticeServiceRequest;
import com.kfwy.hkp.controller.notice.vo.NoticeServiceResponse;
import com.kfwy.hkp.sys.notice.enums.NoticeType;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息推送
 */
@RestController
@RequestMapping(path = "/notice")
public class NoticeServiceImpl extends SpringRestService {

    @Autowired
    private INoticeManager noticeManager;

    @Autowired
    private INoticeRecordManager noticeRecordManager;
    @Autowired
    private ICustomerManager customerManager;
    /**
     *统计各类型消息的总数
     * @param request
     * @return
     */
    @IgnoreLog
    @RequestMapping(path = "/count", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> countNotice(@RequestBody NoticeServiceRequest request) {
        NoticeServiceResponse response = new NoticeServiceResponse();

        Map param = new HashMap<String, Object>();
        param.put("empCode", CurrentContext.getUserCode());
        param.put("hasRead", Boolean.FALSE);
        response.setResult(this.noticeRecordManager.count(param));

        return this.success(response);
    }

    /**
     *查询列表
     * @param request
     * @return
     */
    @RequestMapping(path = "/queryList", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryList(@RequestBody NoticeServiceRequest request) {
        NoticeServiceResponse response = new NoticeServiceResponse();

        Map param = new HashMap<String, Object>();
        param.put("empCode", CurrentContext.getUserCode());
        if(StringUtils.isNotEmpty(request.getNoticeType())
                && !request.getNoticeType().equals("0")){
            param.put("noticeType", request.getNoticeType());
        }
        param.put("isDeleted", Boolean.FALSE);

        PageResult<NoticeEntity> pageResult = noticeManager.findByMap(param, request.getStart(), request.getPageSize(),"send_time",false);

        if (request.getNoticeType().equals(NoticeType.FOCUS_FOR_THE_GUEST)){
            if (pageResult.getData()!=null && pageResult.getData().size()>0){
                try {
                    setCloseTime(pageResult.getData());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        }
        response.setResult(pageResult);

        return this.success(response);
    }
    private void setCloseTime(List<NoticeEntity> noticeEntityList) throws ParseException {
        String focusToPlatformTime = SystemConfig.create().getString("focus_to_platform");
        if (focusToPlatformTime == null) {
            focusToPlatformTime = "48hour";
        }
        String[] hours = focusToPlatformTime.split("hour");
        String hour1 = hours[0];
        SimpleDateFormat simpleFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        for (NoticeEntity noticeEntity : noticeEntityList) {
            //由于客户上架也走集中获客，所以时间间隔改成最新上架时间开始的48小时
            CustomerEntity cus = customerManager.detail (noticeEntity.getBussinessCode ());
            Date lastUpshelfTime = cus.getLastUpshelfTime ();
            Date closeTime = DateUtils.addHours (lastUpshelfTime, Integer.parseInt (hour1));
            String fromDate = simpleFormat.format (new Date ());
            String toDate = simpleFormat.format (closeTime);
            long from = simpleFormat.parse (fromDate).getTime ();
            long to = simpleFormat.parse (toDate).getTime ();
            double hour = (double) ((to - from) / (1000 * 60 * 60));
            if (cus.getCusType ().equals (CustomerType.FOCUS_CUS)){
                noticeEntity.setCloseTime (hour > 0 ? hour : 0);
            }else{
                noticeEntity.setCloseTime(0);
            }
        }
    }
    /**
     *查询详情
     * @param request
     * @return
     */
    @RequestMapping(path = "/detail", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail(@RequestBody NoticeServiceRequest request) {
        NoticeServiceResponse response = new NoticeServiceResponse();

        Map param = new HashMap<String, Object>();
        param.put("recordCode", request.getRecordCode());//使用recordCode来查询单条记录的详情；
        param.put("empCode", CurrentContext.getUserCode());
        response.setResult(this.noticeManager.findOne(param));

        return this.success(response);
    }

    /**
     *更新消息为已读
     * @param request
     * @return
     */
    @IgnoreLog
    @RequestMapping(path = "/setRead", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> setRead(@RequestBody NoticeServiceRequest request) {
        NoticeServiceResponse response = new NoticeServiceResponse();

        NoticeRecordEntity NoticeRecordEntity = new NoticeRecordEntity();
        NoticeRecordEntity.setEmpCode(CurrentContext.getUserCode());
        NoticeRecordEntity.setRecordCode(request.getRecordCode());
        NoticeRecordEntity.setHasRead(true);
        NoticeRecordEntity.setLastUpdateTime (new Date ());
        response.setResult(this.noticeRecordManager.update(NoticeRecordEntity));

        return this.success(response);
    }


    /**
     *一键批量更新消息为已读
     * @param request
     * @return
     */
    @IgnoreLog
    @RequestMapping(path = "/setReadAllSelf", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> setReadAllSelf(@RequestBody NoticeServiceRequest request) {
        NoticeServiceResponse response = new NoticeServiceResponse();

        NoticeRecordEntity NoticeRecordEntity = new NoticeRecordEntity();
        NoticeRecordEntity.setEmpCode(CurrentContext.getUserCode());
        //前端调试传
        //NoticeRecordEntity.setEmpCode(request.getEntity().getCreateCode());
        NoticeRecordEntity.setRecordCode(request.getRecordCode());
        NoticeRecordEntity.setHasRead(true);
        response.setResult(this.noticeRecordManager.updateReadAllSelf(NoticeRecordEntity));

        return this.success(response);
    }

    /**
     * 首页下架的消息滚动
     * @param request
     * @return
     */
    @IgnoreLog
    @ApiOperation(value = "首页下架的消息滚动接口，通过创建时间排序，只查询前10条数据",
            notes = "首页下架的消息滚动接口，通过创建时间排序，只查询前10条数据")
    @RequestMapping(path = "/upDownNotice", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> upDownNotice(@RequestBody NoticeServiceRequest request) {
        NoticeServiceResponse response = new NoticeServiceResponse();
        List<NoticeEntity> list =noticeManager.upDownNotice();
        response.setResult(list);
        return this.success(response);
    }


    @Autowired
    INoticeDbDao noticeDbDao;

    @Autowired
    INoticeRecordDbDao noticeRecordDbDao;

   /* @IgnoreLog
    @RequestMapping(path = "/deleteNotice", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> deleteNotice(@RequestBody NoticeServiceRequest request){
        Integer start = 1;
        Integer pageSize = 50;
        Integer noticeCount = 0;
        Integer noticeRecordCount = 0;
        NoticeServiceResponse response = new NoticeServiceResponse();
        while (true){
            Map map = new HashMap ();
            map.put("noticeType", NoticeType.SYSTEM);
            map.put("bussinessType", NoticeOperateType.Add);

            List<NoticeEntity> result = noticeDbDao.noticeQuery (map, 0, pageSize);
            if (result==null){
                System.out.println ("删除完成");
                System.out.println ("删除notice共"+noticeCount+"条");
                System.out.println ("删除record共"+noticeRecordCount+"条");
                break;
            }

            for (NoticeEntity noticeEntity : result) {
                List<Long> collect = noticeEntity.getNoticeRecordEntityList ().stream ().map (p -> p.getId ()).collect (Collectors.toList ());
                int i = noticeRecordDbDao.batchDeleteByIds (collect);
                noticeRecordCount+=i;
                System.out.println ("成功删除"+noticeRecordCount+"条record数据");

            }

            List<Long> ids = result.stream ().map (p -> p.getId ()).collect (Collectors.toList ());
            int ids1 = noticeDbDao.batchDeleteByIds (ids);
            noticeCount+= ids1;
            System.out.println ("成功删除"+noticeCount+"条notice数据");


        }
        return this.success(response);
    }*/

}
