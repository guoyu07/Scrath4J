package cn.timd.Scratch4J.PipeLineImpl;

import cn.timd.Scratch4J.PipeLine;
import cn.timd.Scratch4J.Request;
import cn.timd.Scratch4J.Response;

import java.util.List;

public class DefaultPipeLine implements PipeLine {
    public void pipeLine(Request request, Response response, List<?> items) {
        System.out.println("DefaultPipeLine");
        System.out.println(request);
        System.out.println(response);
        for (Object item: items)
            System.out.println(item);
    }
}
