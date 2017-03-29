package cn.timd.Scratch4J.Parser;

import cn.timd.Scratch4J.Item.IItem;
import cn.timd.Scratch4J.Request.IRequest;
import cn.timd.Scratch4J.Response.IResponse;

public interface IParser {
    IItem parse(IRequest request, IResponse response);
}
