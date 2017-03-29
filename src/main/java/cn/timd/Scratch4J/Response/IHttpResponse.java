package cn.timd.Scratch4J.Response;

import java.util.Map;

public interface IHttpResponse extends IResponse {
    IHttpResponse setStatusCode(int statusCode);
    int getStatusCode();
    Map<String, String> getHeaders();
    IHttpResponse setHeader(String header, String value);
}
