package cn.timd.Scratch4J;

import cn.timd.Scratch4J.Request.IRequest;
import cn.timd.Scratch4J.Request.impl.SimpleHttpRequestImpl;
import cn.timd.Scratch4J.Scheduler.IScheduler;
import cn.timd.Scratch4J.Scheduler.impl.BaseScheduler;
import cn.timd.Scratch4J.Strategy.AbstractStrategy;


public class Test {
    @org.junit.Test
    public void test() throws Exception {
        IScheduler scheduler = new BaseScheduler();
        scheduler.configure(new AbstractStrategy() {});
        IRequest request = new SimpleHttpRequestImpl().setURL("http://timd.cn/");
        scheduler.submitRequest(request);
        scheduler.start();
    }
}
