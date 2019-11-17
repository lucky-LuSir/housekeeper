package com.kfwy.hkp.log.manager;

import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.kfwy.hkp.common.thread.AbstractThreadPool;
import com.kfwy.hkp.log.dao.IBaseOperationMongoDao;
import com.kfwy.hkp.log.entity.BaseOperationEntity;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 基础操作日志队列
 * @author liwensihan
 */
//@Service
public class BaseOperationQueue extends AbstractThreadPool implements IBaseOperationQueue, ApplicationContextAware {

    public static Logger logger = LoggerFactory.getLogger(BaseOperationQueue.class);

    /**
     * @Fields DETAULT_QUEUE_SIZE : 默认队列信息
     */
    private static final int DETAULT_QUEUE_SIZE = 6 * 10000;

    /**
     * @Fields queue : 告警队列
     */
    private BlockingQueue<BaseOperationEntity> queue;

    /**
     * @Fields queueSize : 队列深度
     */
    private int queueSize = DETAULT_QUEUE_SIZE;

    /**
     * 默认的最大线程数
     */
    private static final int DEFAULT_MAX_THREAD_SIZE = 3;

    /**
     * 线程池临时存储
     */
    private List<Thread> threads;

    @Autowired
    private IBaseOperationMongoDao baseOperationMongoDao;

    @Override
    public void addBaseOperationLog(BaseOperationEntity entity) {
        try {
             this.queue.add(entity);
            this.initThread();
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(">>>>>>>>>>添加基础操作日志到处理车间失败.", e);
            }
        }
    }


    @Override
    public void setApplicationContext(ApplicationContext app) throws BeansException {
        this.init(app);
    }

    /**
     * 初始化服务
     *
     * @param app
     */
    protected void init(ApplicationContext app) {
        if (queue == null) {
            this.queue = new ArrayBlockingQueue<BaseOperationEntity>(queueSize);
        }
        this.threads = new ArrayList<Thread>();
    }

    @Override
    public Thread getThread() {
        return new BaseOperationThread(this.queue, this.baseOperationMongoDao, getThreadSize());
    }

    @Override
    public int getQueueDepth() {
        return this.queue.size();
    }

}
