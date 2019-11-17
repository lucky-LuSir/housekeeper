package com.kfwy.hkp.log.manager.impl;

import ch.qos.logback.classic.gaffer.PropertyUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.gniuu.framework.utils.JsonMapper;
import com.kfwy.hkp.common.annotion.IgnoreLog;
import com.kfwy.hkp.common.utils.HttpContext;
import com.kfwy.hkp.common.utils.ProfileUtil;
import com.kfwy.hkp.log.annotation.OperLog;
import com.kfwy.hkp.log.manager.BaseOperationMongoManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;


@Aspect
@Component
public class OperLogAspect {

    @Autowired
    private HttpServletRequest request;


    private static final Logger LOGGER = LoggerFactory.getLogger(OperLogAspect.class);

    @Autowired
    private BaseOperationMongoManager baseOperationMongoManager;

    /**
     * Service层切点
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) || within(@org.springframework.stereotype.Controller *)")
    public void aspect() {
      //  LOGGER.info(request.getParameterMap ().toString ());
    }

    /**
     * doServiceLog:获取注解参数，记录日志. <br/>
     *
     * @param joinPoint 切入点参数
     */
    @AfterReturning(pointcut = "aspect()",returning ="returnValue")
    public void doServiceLog(JoinPoint joinPoint,Object returnValue) {
        LOGGER.info("日志记录");
        try {
            saveSysLog(joinPoint);
        } catch (Exception e) {
            LOGGER.error("异常信息:{}", e);
        }
    }

    private void saveSysLog(JoinPoint joinPoint) throws Exception {
        UserEntity user = (UserEntity) CurrentContext.getUserInfo();
        IgnoreLog log = checkIgnorLog(joinPoint);
        if (log==null){
            if (user != null) {
                //获取request
                HttpServletRequest request = HttpContext.getRequest();
                //请求url
                String url = request.getRequestURI();
                //请求的参数
                Object[] args = joinPoint.getArgs();
                String params="";
                if (ArrayUtils.isNotEmpty(args)){
                    params = JsonMapper.nonDefaultMapper().toJson(args[0]);
                }
                baseOperationMongoManager.save(url,params);
            }
        }

    }



    private long warnWhenOverTime = 2 * 60 * 1000L;

    @Around("execution(* org.springframework.jdbc.core.JdbcTemplate.*(..))")
    public Object logSqlExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long costTime = System.currentTimeMillis() - startTime;
        if (costTime > warnWhenOverTime) {
            StringBuilder sb = new StringBuilder();
            sb.append("execute method :").append(joinPoint.getSignature());
            sb.append("args: ").append(arrayToString(joinPoint.getArgs()));
            sb.append(" cost time[").append(costTime).append("]ms");
            LOGGER.warn(String.valueOf(sb));
        } else if (LOGGER.isInfoEnabled()) {
            StringBuilder sb = new StringBuilder();
            sb.append("execute method :").append(joinPoint.getSignature());
            sb.append("args: ").append(arrayToString(joinPoint.getArgs()));
            sb.append(" cost time[").append(costTime).append("]ms");
            LOGGER.info(String.valueOf(sb));
        }
        return result;
    }

    private static String arrayToString(Object[] a) {
        if (a == null) {
            return "null";
        }
        int iMax = a.length - 1;
        if (iMax == -1) {
            return "[]";
        }
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            if (a[i] instanceof Object[]) {
                b.append(arrayToString((Object[]) a[i]));
            } else {
                b.append(String.valueOf(a[i]));
            }
            if (i == iMax) {
                return b.append(']').toString();
            }
            b.append(", ");
        }
    }

    /**
     * 检查是否忽略记录当前接口的日志
     *
     * @param joinPoint 切点
     * @return 方法描述
     */
    private IgnoreLog checkIgnorLog(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        IgnoreLog log = method.getAnnotation(IgnoreLog.class);
        return log;
    }



    /**
     * 获取目标主机的ip
     *
     * @param request
     * @return
     */
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }


    /**
     * @Description: 开发环境console打印信息
     * @Param:
     * @return:
     * @Author: liwensihan
     */
    private void devEnvironmentLog(JoinPoint joinPoint, Object returnValue) {
        StringBuilder sb = new StringBuilder("\n接口调用信息： -------- ")
                .append(new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss").format(new Date ()))
                .append(" ------------------------------\n");
        sb.append(getController(joinPoint));
        sb.append(getMethod(joinPoint));
        sb.append(getUri());
        sb.append(getParameter(joinPoint));
        sb.append("RemoteAddr  : " + getRemoteHost(request) + "\n");
        sb.append(getReturn(returnValue));
        sb.append("--------------------------------------------------------------------------------\n");
        System.out.print(sb.toString());
    }

    /**
     * @Description: 正式环境打印的信息
     * @Param:
     * @return:
     * @Author: 关洪昌
     * @Date: 2018/7/20
     */
    private void productEnvironmentLog(JoinPoint joinPoint, Object returnValue) {
        StringBuilder sb = new StringBuilder();

        sb.append(request.getRequestURI()).append(", ");
        sb.append("IP: " + getRemoteHost(request)).append(", [");

        Map<String, String[]> parameters = request.getParameterMap();

        for (Map.Entry<String, String[]> entity : parameters.entrySet()) {

            sb.append(String.format("%s = %s, ", entity.getKey(), StringUtils.join(entity.getValue(), ',')));
        }

        sb.delete(sb.length() - 2, sb.length()).append("]");

        LOGGER.info(sb.toString());
    }

    private Map<String, MultipartFile> getRequestFileMap(JoinPoint joinPoint) {
        Map<String, MultipartFile> fileMap = null;
        if (System.getProperty("java.specification.version").equals ("1.8")) {
            Object[] args = joinPoint.getArgs();
            for (Object object : args) {
                if (object instanceof MultipartHttpServletRequest) {
                    MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) object;
                    fileMap = multipartHttpServletRequest.getFileMap();
                    break;
                }
            }
        }
        return fileMap;
    }


    private String getController(JoinPoint joinPoint) {
        return new StringBuffer().append("Controller  : ").append(joinPoint.getTarget().getClass().getName()).append(".(")
                .append(joinPoint.getTarget().getClass().getSimpleName()).append(".java:1)").toString();
    }

    private String getMethod(JoinPoint joinPoint) {

        return new StringBuffer().append("\nMethod      : ").append(joinPoint.getSignature().getName()).append("\n").toString();
    }

    private String getUri() {
        String uri = request.getRequestURI();
        if (uri != null) {
            UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
            return new StringBuffer().append("url         : ").append(uri)
                    .append(" user=" + cur.getUserName ()).append("\n").toString();
        }
        return "";
    }

    private String getParameter(JoinPoint joinPoint) {
        StringBuffer sb = new StringBuffer();
        Map<String, MultipartFile> fileMap = getRequestFileMap(joinPoint);
        Enumeration<String> e = request.getParameterNames();
        if (e.hasMoreElements() || (fileMap != null && fileMap.size() > 0)) {
            sb.append("Parameter   : ");
            while (e.hasMoreElements()) {
                String name = e.nextElement();
                String[] values = request.getParameterValues(name);
                if (values.length == 1) {
                    sb.append(name).append("=").append(values[0]);
                } else {
                    sb.append(name).append("[]={");
                    for (int i = 0; i < values.length; i++) {
                        if (i > 0)
                            sb.append(",");
                        sb.append(values[i]);
                    }
                    sb.append("}");
                }
                sb.append("  ");
            }
            if (fileMap != null && fileMap.size() > 0) {
                for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
                    MultipartFile file = entry.getValue();
                    sb.append(entry.getKey()).append("=").append(file.getOriginalFilename());
                    sb.append(" (contentType=" + file.getContentType() + ",size=" + file.getSize() + ")");
                    sb.append("  ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private String getReturn(Object returnValue) {
        StringBuffer sb = new StringBuffer ();
        String returnJSON = "";
        returnJSON = JSONObject.toJSONString (returnValue);
        sb.append ("return      : " + returnJSON);
        sb.append ("\n");
        return sb.toString ();
    }

}
