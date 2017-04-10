package cn.timd.Scratch4J.SchedulerImpl;

import cn.timd.Scratch4J.Downloader;
import cn.timd.Scratch4J.Item;
import cn.timd.Scratch4J.Parser;
import cn.timd.Scratch4J.PipeLine;
import cn.timd.Scratch4J.Request;
import cn.timd.Scratch4J.Response;
import cn.timd.Scratch4J.Scheduler;
import cn.timd.Scratch4J.AbstractStrategy;

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

    private class CustomRunnable implements Runnable {
        private Request request;

        CustomRunnable(Request request) {
            super();
            this.request = request;
        }

        public void run() {
            try {
                mainWorkFlow(request);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void start() {
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(
                strategy.getCorePoolSize(), strategy.getMaxPoolSize(),
                strategy.getKeepAliveTimeMS(), TimeUnit.MILLISECONDS,
                strategy.getBlockingQueue(), strategy.getHandler());
        Request request;

        while (executor.getActiveCount() > 0 || !requests.isEmpty()) {
            if (executor.getActiveCount() < executor.getMaximumPoolSize() && requests.size() > 0) {
                request = requests.remove();
                if (toBeContinue(request))
                    executor.execute(new CustomRunnable(request));
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                break;
            }
        }

        executor.shutdown();
    }
}

