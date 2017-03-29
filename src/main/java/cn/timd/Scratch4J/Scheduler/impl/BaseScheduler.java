package cn.timd.Scratch4J.Scheduler.impl;

import cn.timd.Scratch4J.Downloader.IDownloader;
import cn.timd.Scratch4J.Facade.IFacade;
import cn.timd.Scratch4J.Item.IItem;
import cn.timd.Scratch4J.Parser.IParser;
import cn.timd.Scratch4J.PipeLine.IPipeLine;
import cn.timd.Scratch4J.Request.IRequest;
import cn.timd.Scratch4J.Response.IResponse;
import cn.timd.Scratch4J.Scheduler.IScheduler;
import cn.timd.Scratch4J.Strategy.AbstractStrategy;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BaseScheduler implements IScheduler {
    // // TODO: 2017/3/28 change to delay queue???
    private Queue<IRequest> requests = new LinkedBlockingQueue<IRequest>();
    private AbstractStrategy strategy = new AbstractStrategy() {};

    public BaseScheduler configure(AbstractStrategy strategy) {
        this.strategy = strategy;
        return this;
    }

    public void submitRequest(IRequest request) {
        requests.add(request);
    }

    private void mainWorkFlow(IRequest request)
            throws IllegalAccessException, InstantiationException {
        Class<? extends IDownloader> downloader = request.getDownloader();
        IResponse response = downloader.newInstance().download(request);
        if (!response.isSuccessful()) {
            request.incrementFailureCount();
            if (response.isRetry())
                submitRequest(request);
            return;
        }

        IFacade facade = strategy.getFacade();
        Class<? extends IParser> parserClass = facade.getParserClass(request, response);
        IItem item = parserClass.newInstance().parse(request, response);

        for (IRequest subRequest: item.getSubRequests()) {
            if (subRequest.getDownloader() == null)
                subRequest.setDownloader(request.getDownloader());
            submitRequest(subRequest.setDepth(request.getDepth() + 1));
        }

        Class<? extends IPipeLine> pipeLineClass = facade.getPipeLineClass(
                request, response, item.getItems());
        pipeLineClass.newInstance().pipeLine(request, response, item.getItems());
    }

    public boolean toBeContinue(IRequest request) {
        if (request.getFailureCount() >= strategy.getMaxFailureCount())
            return false;
        if (strategy.getMaxDepth() != 0
                && (request.getDepth() >= strategy.getMaxDepth()))
            return false;

        return true;
    }

    public void start() {
        // // TODO: 2017/3/28 multiple thread support
        while (!requests.isEmpty()) {
            IRequest request = requests.remove();
            if (!toBeContinue(request))
                continue;

            try {
                mainWorkFlow(request);
            } catch (Exception ex)  {
                ex.printStackTrace();
            }
        }
    }
}

