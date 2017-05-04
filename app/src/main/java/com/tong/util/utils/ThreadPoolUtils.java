package com.tong.util.utils;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolUtils
{

    /** 日志TAG */
    private static final String TAG = "ThreadPoolUtils";

    /** 拒绝运行 */
    public static final int EXECUTE_RESULT_REJECTED = -1;

    /** 正常运行 */
    public static final int EXECUTE_RESULT_GOOD = 0;

    /** 线程池维护线程的最少数量(默认值: 5) */
    private static int CORE_POOL_SIZE = 5;

    /** 线程池维护线程的最大数量(默认值: 8) */
    private static int MAX_POOL_SIZE = 20;
    // DTS2014052908765 Modify by h00140213 2014-06-04,不超过SDK的线程数量

    /** 线程池维护线程所允许的空闲时间(默认值: 30s) */
    private static int KEEP_ALIVE_TIME = 30;

    /** When block use queue */
    private static BlockingQueue<Runnable> workQueue = new SynchronousQueue<Runnable>();

    /** Thread factory */
    private static ThreadFactory threadFactory = new ThreadFactory()
    {
        private final AtomicInteger integer = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r)
        {
            return new Thread(r, "HW ThreadPool thread:" + integer.getAndIncrement());
        }
    };

    private static ThreadPoolExecutor threadPool;

    static {
        // 处理程序遭到拒绝将抛出 RejectedExecutionException
        threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
                                            TimeUnit.SECONDS, workQueue, threadFactory,
                                            new ThreadPoolExecutor.AbortPolicy()
        );
        // AbortPolicy策略会抛出RejectedExecutionException异常，修改为CallerRunsPolicy策略
    }

    public static ThreadPoolExecutor getThreadPollExInstance()
    {
        if (threadPool == null) {
        }
        else {
        }
        return threadPool;
    }


    /**
     * 设置运行线程
     *
     * @param runnable 运行线程
     *
     * @return 是否正常运行(EXECUTE_RESULT_GOOD, EXECUTE_RESULT_REJECTED)
     */
    public static int execute(Runnable runnable)
    {
        try {
            threadPool.execute(runnable);

            return EXECUTE_RESULT_GOOD;
        }
        catch (RejectedExecutionException e) {
            return EXECUTE_RESULT_REJECTED;
        }
    }

    /**
     * 获取线程池维护线程的最少数量
     *
     * @return 返回 CORE_POOL_SIZE
     */
    public static int getCORE_POOL_SIZE()
    {
        return CORE_POOL_SIZE;
    }

    /**
     * 设置线程池维护线程的最少数量
     */
    public static void setCORE_POOL_SIZE(int cORE_POOL_SIZE)
    {
        CORE_POOL_SIZE = cORE_POOL_SIZE;
    }

    /**
     * 获取线程池维护线程的最大数量
     *
     * @return 返回 MAX_POOL_SIZE
     */
    public static int getMAX_POOL_SIZE()
    {
        return MAX_POOL_SIZE;
    }

    /**
     * 设置线程池维护线程的最大数量
     */
    public static void setMAX_POOL_SIZE(int mAX_POOL_SIZE)
    {
        MAX_POOL_SIZE = mAX_POOL_SIZE;
    }

    /**
     * 获取线程池维护线程所允许的空闲时间
     *
     * @return 返回 KEEP_ALIVE_TIME
     */
    public static int getKEEP_ALIVE_TIME()
    {
        return KEEP_ALIVE_TIME;
    }

    /**
     * 设置线程池维护线程所允许的空闲时间
     */
    public static void setKEEP_ALIVE_TIME(int kEEP_ALIVE_TIME)
    {
        KEEP_ALIVE_TIME = kEEP_ALIVE_TIME;
    }
}
