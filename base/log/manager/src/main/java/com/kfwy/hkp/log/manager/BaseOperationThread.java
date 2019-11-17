package com.kfwy.hkp.log.manager;


import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.kfwy.hkp.log.dao.IBaseOperationMongoDao;
import com.kfwy.hkp.log.entity.BaseOperationEntity;

import java.util.concurrent.BlockingQueue;

/**
 * @author liwensihan
 */
public class BaseOperationThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(BaseOperationThread.class);

    /**
     * 文件信息队列
     */
    private BlockingQueue<BaseOperationEntity> queue;

    /**
     * 日志处理器
     */
    private IBaseOperationMongoDao baseOperationMongoDao;

    public BaseOperationThread(BlockingQueue<BaseOperationEntity> queue, IBaseOperationMongoDao baseOperationMongoDao, int i) {
        this.queue = queue;
        this.baseOperationMongoDao = baseOperationMongoDao;
        this.setDaemon(true);
        this.setName("LOG-PROCESS-THREAD" + i);
        logger.info(String.format(">>>>>>>>>>创建日志记录处理线程[%s]成功", this.getName()));
    }

    @Override
    public void run() {
        while (true) {
            try {
                BaseOperationEntity baseOperationEntity = this.queue.take();

                this.baseOperationMongoDao.save(baseOperationEntity);

            } catch (Exception e) {
                //TODO
            }
        }
    }
}
