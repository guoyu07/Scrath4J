package cn.timd.Scratch4J;

import java.util.Map;

public interface HttpRequest extends Request {
    String getRequestMethod();
    String getURL();
    HttpRequest setURL(String URL);
    Map<String, String> getHeaders();
}
