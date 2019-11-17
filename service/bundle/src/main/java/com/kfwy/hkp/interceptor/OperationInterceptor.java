package com.kfwy.hkp.interceptor;


import com.gniuu.framework.interceptor.AbstractSpringInterceptor;
/*import com.kfwy.hkp.log.manager.BaseOperationManager;*/
import com.kfwy.hkp.log.manager.BaseOperationMongoManager;
import com.kfwy.hkp.log.manager.IOperationManager;
import com.kfwy.hkp.log.manager.impl.BaseOperationMongoManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2019/1/19 16:33
 */
public class OperationInterceptor extends AbstractSpringInterceptor {

   /* @Autowired
    private BaseOperationManager baseOperationManager;*/

    @Autowired
    private BaseOperationMongoManager baseOperationMongoManager;
    /**
     * 需要记录操作日志的列表
     */
    public List<String> checkLogUrl;

    protected boolean pathContainsUrl(List<String> urls, String path) {
        for (String url : urls) {
            if (path.contains(url)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean preInterceptor(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String path = request.getRequestURI();

        if (StringUtils.isEmpty(path)) {
            path = request.getPathInfo();
        }

        if (StringUtils.isEmpty(path)) {
            path = request.getServletPath();
        }
        try {
            insertOperationLog(request, path, handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 新增操作日志
     *
     * @param request
     * @param path
     * @param handler
     */
    private void insertOperationLog(HttpServletRequest request, String path, Object handler) throws IOException {
        //基础操作日志
        //baseOperationManager.save(request,path,handler);
        baseOperationMongoManager.save(request,path,handler);
    }

    public List<String> getCheckLogUrl() {
        return checkLogUrl;
    }

    public void setCheckLogUrl(List<String> checkLogUrl) {
        this.checkLogUrl = checkLogUrl;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
