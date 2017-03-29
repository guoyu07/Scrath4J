package cn.timd.Scratch4J.ParserFacade;

import cn.timd.Scratch4J.Parser.IParser;
import cn.timd.Scratch4J.PipeLine.IPipeLine;
import cn.timd.Scratch4J.Request.IRequest;
import cn.timd.Scratch4J.Response.IResponse;

public interface IParserFacade {
    Class<? extends IParser> getParserClass(IRequest request, IResponse response);
    Class<? extends IPipeLine> getPipeLine(IRequest request, IResponse response);
}
