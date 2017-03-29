package cn.timd.Scratch4J.Parser.impl;

import cn.timd.Scratch4J.Item.IItem;
import cn.timd.Scratch4J.Item.impl.DefaultItemImpl;
import cn.timd.Scratch4J.Parser.IParser;
import cn.timd.Scratch4J.Request.IRequest;
import cn.timd.Scratch4J.Request.impl.SimpleHttpRequestImpl;
import cn.timd.Scratch4J.Response.IResponse;

public class DefaultParserImpl implements IParser {
    public IItem parse(IRequest request, IResponse response) {
        IItem item = new DefaultItemImpl();

        DefaultVo defaultVo = new DefaultVo();
        defaultVo.setRequest(request.toString());
        defaultVo.setContent(response.getResponseContent());

        // item.addSubRequest(new SimpleHttpRequestImpl().setURL("http://timd.cn/"));
        return item.addItem(defaultVo);
    }
}
