package cn.timd.Scratch4J;

import cn.timd.Scratch4J.RequestImpl.SimpleHttpRequest;
import cn.timd.Scratch4J.SchedulerImpl.BaseScheduler;

public class Test {
    @org.junit.Test
    public void test() throws Exception {
        Scheduler scheduler = new BaseScheduler().configure(new AbstractStrategy() {
            private Parser parser = new TestParser();

            @Override
            public Parser getParser() {
                return parser;
            }
        });

        Request request = new SimpleHttpRequest().setURL("https://www.zhihu.com/");
        scheduler.submitRequest(request);
        long start = System.currentTimeMillis();
        scheduler.start();
        System.out.println(System.currentTimeMillis() - start);
    }
}
