package cn.timd.Scratch4J.Scheduler;

import cn.timd.Scratch4J.Request.IRequest;
import cn.timd.Scratch4J.Strategy.AbstractStrategy;

public interface IScheduler {
    void submitRequest(IRequest request);
    void start();
    boolean toBeContinue(IRequest request);
    IScheduler configure(AbstractStrategy strategy);
}
