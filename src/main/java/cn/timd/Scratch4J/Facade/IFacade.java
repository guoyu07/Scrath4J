package cn.timd.Scratch4J.Facade;

import cn.timd.Scratch4J.Parser.IParser;
import cn.timd.Scratch4J.PipeLine.IPipeLine;
import cn.timd.Scratch4J.Request.IRequest;
import cn.timd.Scratch4J.Response.IResponse;

import java.util.List;

public interface IFacade {
    Class<? extends IParser> getParserClass(IRequest request, IResponse response);
    Class<? extends IPipeLine> getPipeLineClass(IRequest request, IResponse response, List<?> items);
}
