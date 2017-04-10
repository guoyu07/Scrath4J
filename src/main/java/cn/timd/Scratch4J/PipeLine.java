package cn.timd.Scratch4J;

import java.util.List;

public interface PipeLine {
    void pipeLine(Request request, Response response, List<?> items);
}
