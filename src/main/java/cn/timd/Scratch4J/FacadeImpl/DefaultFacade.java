package cn.timd.Scratch4J.FacadeImpl;

import cn.timd.Scratch4J.*;
import cn.timd.Scratch4J.ParserImpl.DefaultParser;
import cn.timd.Scratch4J.PipeLineImpl.DefaultPipeLine;

import java.util.List;

public class DefaultFacade implements Facade {
    public Class<? extends Parser> getParserClass(Request request, Response response) {
        return DefaultParser.class;
    }

    public Class<? extends PipeLine> getPipeLineClass(Request request, Response response, List<?> items) {
        return DefaultPipeLine.class;
    }
}
