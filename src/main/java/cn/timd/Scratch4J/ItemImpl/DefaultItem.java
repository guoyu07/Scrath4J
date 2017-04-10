package cn.timd.Scratch4J.ItemImpl;

import cn.timd.Scratch4J.Item;
import cn.timd.Scratch4J.ParserImpl.DefaultVo;
import cn.timd.Scratch4J.Request;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultItem implements Item {
    private List<DefaultVo> items = new ArrayList<DefaultVo>();
    private Set<Request> subRequests = new HashSet<Request>();

    public DefaultItem addItem(Object item) {
        items.add((DefaultVo) item);
        return this;
    }

    public DefaultItem addSubRequest(Request request) {
        subRequests.add(request);
        return this;
    }

    public List<?> getItems() {
        return items;
    }

    public Set<Request> getSubRequests() {
        return subRequests;
    }
}
