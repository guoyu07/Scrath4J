package cn.timd.Scratch4J.Item.impl;

import cn.timd.Scratch4J.Item.IItem;
import cn.timd.Scratch4J.Parser.impl.DefaultVo;
import cn.timd.Scratch4J.Request.IRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultItemImpl implements IItem {
    private List<DefaultVo> items = new ArrayList<DefaultVo>();
    private Set<IRequest> subRequests = new HashSet<IRequest>();

    public DefaultItemImpl addItem(Object item) {
        items.add((DefaultVo) item);
        return this;
    }

    public DefaultItemImpl addSubRequest(IRequest request) {
        subRequests.add(request);
        return this;
    }

    public List<?> getItems() {
        return items;
    }

    public Set<IRequest> getSubRequests() {
        return subRequests;
    }
}
