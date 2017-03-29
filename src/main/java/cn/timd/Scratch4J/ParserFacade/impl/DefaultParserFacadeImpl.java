package cn.timd.Scratch4J.ParserFacade.impl;

import cn.timd.Scratch4J.Parser.IParser;
import cn.timd.Scratch4J.Parser.impl.DefaultParserImpl;
import cn.timd.Scratch4J.ParserFacade.IParserFacade;
import cn.timd.Scratch4J.PipeLine.IPipeLine;
import cn.timd.Scratch4J.PipeLine.impl.DefaultPipeLineImpl;
import cn.timd.Scratch4J.Request.IRequest;
import cn.timd.Scratch4J.Response.IResponse;

public class DefaultParserFacadeImpl implements IParserFacade {
    public Class<? extends IParser> getParserClass(IRequest request, IResponse response) {
        return DefaultParserImpl.class;
    }

    public Class<? extends IPipeLine> getPipeLine(IRequest request, IResponse response) {
        return DefaultPipeLineImpl.class;
    }
}
