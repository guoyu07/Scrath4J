package cn.timd.Scratch4J.PipeLine.impl;

import cn.timd.Scratch4J.PipeLine.IPipeLine;
import cn.timd.Scratch4J.Request.IRequest;
import cn.timd.Scratch4J.Response.IResponse;

import java.util.List;

public class DefaultPipeLineImpl implements IPipeLine {
    public void pipeLine(IRequest request, IResponse response, List<?> items) {
        System.out.println("DefaultPipeLineImpl");
        System.out.println(request);
        System.out.println(response);
        for (Object item: items)
            System.out.println(item);
    }
}
