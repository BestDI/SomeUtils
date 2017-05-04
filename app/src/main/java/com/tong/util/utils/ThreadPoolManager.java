package com.tong.util.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManager
{

    private static final String TAG = "ThreadPoolManager";

    private static final int MAX_RUN_NUM = 5;

    /** 每次执行限定个数个任务的线程池 */
    private static ExecutorService sLimitedTaskExecutor = null;

    private static ThreadPoolManager sInstance;

    private static byte[] lock = new byte[1];

    static {
        sLimitedTaskExecutor = Executors.newFixedThreadPool(MAX_RUN_NUM);// 限制线程池大小为5的线程池
    }


    /**
     * 默认构造函数
     */
    private ThreadPoolManager()
    {
    }

    /**
     * 获取单例
     */
    public static ThreadPoolManager getInstance()
    {
        synchronized (lock) {
            if (null == sInstance) {
                sInstance = new ThreadPoolManager();
            }
            return sInstance;
        }
    }

    /**
     * 执行处理
     *
     * @param run 想要执行的任务
     */
    public void execute(Runnable run)
    {
        sLimitedTaskExecutor.execute(run);
    }

    /**
     * 关闭线程池
     */
    public void release()
    {
        if (sLimitedTaskExecutor != null && !sLimitedTaskExecutor.isShutdown()) {
            sLimitedTaskExecutor.shutdown();
        }
    }
}
