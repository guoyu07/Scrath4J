package cn.timd.Scratch4J;

import cn.timd.Scratch4J.ParserImpl.DefaultParser;
import cn.timd.Scratch4J.PipeLineImpl.DefaultPipeLine;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public abstract class AbstractStrategy {
    private int maxFailureCount = 3;
    private int maxDepth = 10;
    private Parser parser = new DefaultParser();
    private PipeLine pipeLine = new DefaultPipeLine();

    private int corePoolSize = 2 * Runtime.getRuntime().availableProcessors() + 1;
    private int maxPoolSize = (int)Math.pow(Runtime.getRuntime().availableProcessors(), 2);
    private int keepAliveTimeMS = 10000;
    private BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(100);
    private RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

    public int getMaxFailureCount() {
        return maxFailureCount;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public Parser getParser() {
        return parser;
    }

    public PipeLine getPipeLine() {
        return pipeLine;
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
