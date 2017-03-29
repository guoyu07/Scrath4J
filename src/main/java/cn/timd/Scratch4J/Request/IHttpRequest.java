package cn.timd.Scratch4J.Request;

import java.util.Map;

public interface IHttpRequest extends IRequest {
    String getRequestMethod();
    String getURL();
    IHttpRequest setURL(String URL);
    Map<String, String> getHeaders();
}
