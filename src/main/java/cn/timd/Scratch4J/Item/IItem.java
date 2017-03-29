package cn.timd.Scratch4J.Item;

import cn.timd.Scratch4J.Request.IRequest;

import java.util.List;
import java.util.Set;

public interface IItem {
    IItem addSubRequest(IRequest request);
    Set<IRequest> getSubRequests();

    IItem addItem(Object item);
    List<?> getItems();
}
