package com.kfwy.hkp.sys.notice.business.exceptionNotice;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.kfwy.hkp.sys.notice.business.NoticeTaskExecutor;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.entity.NoticeRecordEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeOperateType;
import com.kfwy.hkp.sys.notice.enums.NoticeType;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;


/**
 * 全局异常通知
 */
@Component
@Aspect
public class ExceptionNotice {


    @Autowired
    protected NoticeTaskExecutor noticeTaskExecutor;
    @Autowired
    protected IUserManager userManager;

    @Pointcut("target(com.kfwy.hkp.common.handler.RestExceptionHandler) " +
            "&& execution(* handlerDefaultException(..))")
    public void exceptionAdvice(){

    }

    @AfterReturning("exceptionAdvice()")
    public void afterException(JoinPoint point){
        Object[] args = point.getArgs();
        Object obj = null;
        if (ArrayUtils.isNotEmpty(args)){
            obj = args[0];
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Exception ex = (Exception) obj;
        String message = request.getRequestURL() + "=>" + getMessage(ex);

        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        if(cur == null){//用户未登录 不发通知
            return;
        }
        noticeTaskExecutor.saveAndPush(create(message));
    }


    protected NoticeEntity create(String message){
        //初始化通知消息
        NoticeEntity notice = new NoticeEntity();

        notice.setNoticeCode(CodeGenUtils.generate());
        notice.setNoticeType(NoticeType.SYSTEM);
        notice.setBussinessEmpName("系统异常");
        notice.setBussinessDeptName("");
        notice.setIsDeleted(Boolean.FALSE);
        notice.setBussinessType(NoticeOperateType.Add);
        notice.setCreateTime(new Date());
        notice.setLastUpdateTime(new Date());

        UserEntity cur = (UserEntity)CurrentContext.getUserInfo();

        //设置推送内容
        notice.setNoticeTitle(String.format("系统异常<%s-%s>",cur.getOwnerDeptName(),cur.getUserName()));
        notice.setNoticeContent(message);

        //获取推送目标人群
        List<UserEntity> targetUsers = getTargetUsers();

        //设置推送目标人员
        addNoticeRecord(notice,targetUsers);
        return notice;
    }

    /**
     * 获取异常信息
     * @param e
     * @return
     */
    private  String getMessage(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        try {
            sw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        pw.close();
        return sw.toString();
    }

    //设置推送人群
    public  List<UserEntity> getTargetUsers(){
        Map<String,Object> map = new HashMap<>(2);
        List<UserEntity> list = new ArrayList<>();
        map.put("ownerDeptCode","DP201805150003ef51");//研发一组的
        map.put("isDeleted",Boolean.FALSE);
        list.addAll(userManager.findByMap(map));

        map.put("ownerDeptCode","DP201605260001");//it管理部
        list.addAll(userManager.findByMap(map));

        map.put("ownerDeptCode","DP20180515000126ad");//产品组
        list.addAll(userManager.findByMap(map));

        return list;
    }

    //设置推送人员
    protected void addNoticeRecord(NoticeEntity notice,List<UserEntity> targetUsers){

        if (!CollectionUtils.isEmpty(targetUsers)){
            List<NoticeRecordEntity> recordEntityList = new ArrayList<NoticeRecordEntity>();

            for (UserEntity user : targetUsers){

                if (StringUtils.isNotEmpty(user.getDeviceToken())){
                    NoticeRecordEntity record = new NoticeRecordEntity();

                    record.setRecordCode(CodeGenUtils.generate());
                    record.setNoticeCode(notice.getNoticeCode());
                    record.setHasRead(Boolean.FALSE);
                    record.setEmpCode(user.getUserCode());
                    record.setDeviceToken(user.getDeviceToken());
                    record.setLastUpdateCode(user.getUserCode());
                    record.setCreateCode(user.getUserCode());
                    record.setIsDeleted(Boolean.FALSE);
                    recordEntityList.add(record);
                }

            }
            notice.setNoticeRecordEntityList(recordEntityList);
        }
    }
}
