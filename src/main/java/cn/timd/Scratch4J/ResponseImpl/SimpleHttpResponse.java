package cn.timd.Scratch4J.ResponseImpl;

import cn.timd.Scratch4J.HttpResponse;

import java.util.HashMap;
import java.util.Map;

public class SimpleHttpResponse implements HttpResponse {
    private boolean successful = true;
    private Map<String, String> headers = new HashMap<String, String>();
    private int statusCode = 200;
    private Throwable exception = null;
    private byte[] responseContent = null;
    private boolean retry = true;

    public SimpleHttpResponse markAsFailure() {
        successful = false;
        return this;
    }

    public SimpleHttpResponse markAsSuccess() {
        successful = true;
        return this;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public SimpleHttpResponse addHeader(String header, String value) {
        headers.put(header, value);
        return this;
    }

    public SimpleHttpResponse setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Throwable getException() {
        return exception;
    }

    public SimpleHttpResponse setException(Throwable exception) {
        this.exception = exception;
        return this;
    }

    public byte[] getResponseContent() {
        return responseContent;
    }

    public SimpleHttpResponse setResponseContent(byte[] responseContent) {
        this.responseContent = responseContent;
        return this;
    }

    public boolean isRetry() {
        return retry;
    }

    public SimpleHttpResponse setRetry(boolean retry) {
        this.retry = retry;
        return this;
    }

    @Override
    public String toString() {
        return "SimpleHttpResponse{" +
                "successful=" + successful +
                ", headers=" + headers +
                ", statusCode=" + statusCode +
                ", exception=" + exception +
                ", retry=" + retry +
                '}';
    }
}
