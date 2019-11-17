package com.kfwy.hkp.common.thread;


import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractThreadPool {

    public static Logger logger = LoggerFactory.getLogger(AbstractThreadPool.class);

    /**
     * 默认的最大线程数
     */
    private static final int DEFAULT_MAX_THREAD_SIZE = 3;

    /**
     * 线程池临时存储
     */
    private List<Thread> threads;

    public AbstractThreadPool() {
        this.threads = new ArrayList<Thread>();
    }

    /**
     * 初始化线程
     */
    public synchronized void initThread() {
        //判断队列中是否存在消息,如果消息堆积操作500则添加线程发送，如果线程数量超过了3个则不在增加
        boolean init = false;
        if (this.threads.isEmpty()) {
            init = true;
        } else if (this.getThreadSize() < this.getMaxThreadSize()) {
            if (this.isAddThread()) {
                init = true;
            }
        }
        if (init) {
            Thread thread = getThread();
            threads.add(thread);

            logger.info(String.format(">>>>>>>>>>>>>>正在添加线程,当前线程一共[%s]个", this.threads.size()));

            thread.start();
        }
    }

    /**
     * 是否添加
     *
     * @return
     */
    public boolean isAddThread() {
        int size = this.getQueueDepth();
        return size != 0 && size % 500 == 0;
    }

    /**
     * 获取最大的线程数
     *
     * @return
     */
    public int getMaxThreadSize() {
        return DEFAULT_MAX_THREAD_SIZE;
    }

    /**
     * 获取线程大小
     *
     * @return
     */
    public int getThreadSize() {
        return this.threads.size();
    }

    /**
     * 获取执行的线程
     *
     * @return
     */
    public abstract Thread getThread();

    /**
     * 获取当前队列深度
     *
     * @return
     */
    public abstract int getQueueDepth();
}
