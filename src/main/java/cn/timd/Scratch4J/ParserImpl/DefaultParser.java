package cn.timd.Scratch4J.ParserImpl;

import cn.timd.Scratch4J.Item;
import cn.timd.Scratch4J.ItemImpl.DefaultItem;
import cn.timd.Scratch4J.Parser;
import cn.timd.Scratch4J.Request;
import cn.timd.Scratch4J.Response;

public class DefaultParser implements Parser {
    public Item parse(Request request, Response response) {
        Item item = new DefaultItem();

        DefaultVo defaultVo = new DefaultVo();
        defaultVo.setRequest(request.toString());
        defaultVo.setContent(response.getResponseContent());

        return item.addItem(defaultVo);
    }
}
