package cn.timd.Scratch4J;

import cn.timd.Scratch4J.Strategy.AbstractStrategy;

public interface Scheduler {
    void submitRequest(Request request);
    void start();
    boolean toBeContinue(Request request);
    Scheduler configure(AbstractStrategy strategy);
}
