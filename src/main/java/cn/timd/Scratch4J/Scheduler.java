package cn.timd.Scratch4J;

public interface Scheduler {
    void submitRequest(Request request);
    void start();
    boolean toBeContinue(Request request);
    Scheduler configure(AbstractStrategy strategy);
}
