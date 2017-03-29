package cn.timd.Scratch4J.PipeLine;

import cn.timd.Scratch4J.Request.IRequest;
import cn.timd.Scratch4J.Response.IResponse;

import java.util.List;

public interface IPipeLine {
    void pipeLine(IRequest request, IResponse response, List<?> items);
}
