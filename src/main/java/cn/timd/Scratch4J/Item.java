package cn.timd.Scratch4J;

import java.util.List;
import java.util.Set;

public interface Item {
    Item addSubRequest(Request request);
    Set<Request> getSubRequests();

    Item addItem(Object item);
    List<?> getItems();
}
