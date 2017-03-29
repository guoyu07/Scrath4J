package cn.timd.Scratch4J.Facade.impl;

import cn.timd.Scratch4J.Parser.IParser;
import cn.timd.Scratch4J.Parser.impl.DefaultParserImpl;
import cn.timd.Scratch4J.Facade.IFacade;
import cn.timd.Scratch4J.PipeLine.IPipeLine;
import cn.timd.Scratch4J.PipeLine.impl.DefaultPipeLineImpl;
import cn.timd.Scratch4J.Request.IRequest;
import cn.timd.Scratch4J.Response.IResponse;

import java.util.List;

public class DefaultFacadeImpl implements IFacade {
    public Class<? extends IParser> getParserClass(IRequest request, IResponse response) {
        return DefaultParserImpl.class;
    }

    public Class<? extends IPipeLine> getPipeLineClass(IRequest request, IResponse response, List<?> items) {
        return DefaultPipeLineImpl.class;
    }
}
