package cn.timd.Scratch4J;

import cn.timd.Scratch4J.ItemImpl.DefaultItem;
import cn.timd.Scratch4J.ParserImpl.DefaultVo;
import cn.timd.Scratch4J.RequestImpl.SimpleHttpRequest;

public class TestParser implements Parser {
    public Item parse(Request request, Response response) {
        Item item = new DefaultItem();

        DefaultVo defaultVo = new DefaultVo();
        defaultVo.setRequest(request.toString());
        defaultVo.setContent(response.getResponseContent());

        return item
                .addItem(defaultVo)
                .addSubRequest(new SimpleHttpRequest()
                    .setURL(((HttpRequest)request).getURL()));
    }
}
