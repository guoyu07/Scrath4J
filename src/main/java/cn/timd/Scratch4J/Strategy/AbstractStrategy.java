package cn.timd.Scratch4J.Strategy;

import cn.timd.Scratch4J.Facade;
import cn.timd.Scratch4J.FacadeImpl.DefaultFacade;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public abstract class AbstractStrategy {
    private Facade facade = new DefaultFacade();
    private int maxFailureCount = 3;
    // 0 means infinite
    private int maxDepth = 10;

    private int corePoolSize = 2 * Runtime.getRuntime().availableProcessors() + 1;
    private int maxPoolSize = (int)Math.pow(Runtime.getRuntime().availableProcessors(), 2);
    private int keepAliveTimeMS = 10000;
    private BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(100);
    private RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

    public Facade getFacade() {
        return facade;
    }

    public int getMaxFailureCount() {
        return maxFailureCount;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public int getKeepAliveTimeMS() {
        return keepAliveTimeMS;
    }

    public BlockingQueue<Runnable> getBlockingQueue() {
        return blockingQueue;
    }

    public RejectedExecutionHandler getHandler() {
        return handler;
    }
}
