package cn.timd.Scratch4J;

import java.util.Map;

public interface HttpResponse extends Response {
    HttpResponse setStatusCode(int statusCode);
    int getStatusCode();
    Map<String, String> getHeaders();
    HttpResponse addHeader(String header, String value);
}
