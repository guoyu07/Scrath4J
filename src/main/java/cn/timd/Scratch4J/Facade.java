package cn.timd.Scratch4J;

import java.util.List;

public interface Facade {
    Class<? extends Parser> getParserClass(Request request, Response response);
    Class<? extends PipeLine> getPipeLineClass(Request request, Response response, List<?> items);
}
