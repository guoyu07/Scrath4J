package cn.timd.Scratch4J.SchedulerImpl;

import cn.timd.Scratch4J.Downloader;
import cn.timd.Scratch4J.Item;
import cn.timd.Scratch4J.Parser;
import cn.timd.Scratch4J.PipeLine;
import cn.timd.Scratch4J.Request;
import cn.timd.Scratch4J.Response;
import cn.timd.Scratch4J.Scheduler;
import cn.timd.Scratch4J.Strategy.AbstractStrategy;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BaseScheduler implements Scheduler {
    private final Queue<Request> requests = new LinkedBlockingQueue<Request>();
    private AbstractStrategy strategy = new AbstractStrategy() {};

    public BaseScheduler configure(AbstractStrategy strategy) {
        this.strategy = strategy;
        return this;
    }

    public void submitRequest(Request request) {
        requests.add(request);
    }

    private void mainWorkFlow(Request request) {
        Downloader downloader = request.getDownloader();
        Response response = downloader.download(request);
        if (!response.isSuccessful()) {
            request.incrementFailureCount();
            if (response.isRetry())
                submitRequest(request);
            return;
        }

        Parser parser = strategy.getParser();
        Item item = parser.parse(request, response);

        for (Request subRequest: item.getSubRequests()) {
            if (subRequest.getDownloader() == null)
                subRequest.setDownloader(request.getDownloader());
            submitRequest(subRequest.setDepth(request.getDepth() + 1));
        }

        PipeLine pipeLine = strategy.getPipeLine();
        pipeLine.pipeLine(request, response, item.getItems());
    }

    public boolean toBeContinue(Request request) {
        if (strategy.getMaxFailureCount() > 0
                && (request.getFailureCount() >= strategy.getMaxFailureCount()))
            return false;
        if (strategy.getMaxDepth() > 0
                && (request.getDepth() >= strategy.getMaxDepth()))
            return false;
        return true;
    }

    public void start() {
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(
                strategy.getCorePoolSize(), strategy.getMaxPoolSize(),
                strategy.getKeepAliveTimeMS(), TimeUnit.MILLISECONDS,
                strategy.getBlockingQueue(), strategy.getHandler());
        final Request[] request = new Request[1];

        while (executor.getActiveCount() != 0 || !requests.isEmpty()) {
            if (executor.getActiveCount() == executor.getMaximumPoolSize() ||
                    requests.isEmpty())
                try {
                    Thread.sleep(1);
                    continue;
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                    break;
                }

            request[0] = requests.remove();
            if (!toBeContinue(request[0]))
                continue;
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        mainWorkFlow(request[0]);
                    } catch (Exception ex)  {
                        ex.printStackTrace();
                    }
                }
            });
        }

        executor.shutdown();
    }
}

