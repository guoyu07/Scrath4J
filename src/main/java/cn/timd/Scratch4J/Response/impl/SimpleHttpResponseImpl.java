package cn.timd.Scratch4J.Response.impl;

import cn.timd.Scratch4J.Response.IHttpResponse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SimpleHttpResponseImpl implements IHttpResponse {
    private boolean successful = true;
    private Map<String, String> headers = new HashMap<String, String>();
    private int statusCode = 200;
    private Throwable exception = null;
    private byte[] responseContent = null;
    private boolean retry = true;

    public SimpleHttpResponseImpl markAsFailure() {
        successful = false;
        return this;
    }

    public SimpleHttpResponseImpl markAsSuccess() {
        successful = true;
        return this;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public SimpleHttpResponseImpl addHeader(String header, String value) {
        headers.put(header, value);
        return this;
    }

    public SimpleHttpResponseImpl setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Throwable getException() {
        return exception;
    }

    public SimpleHttpResponseImpl setException(Throwable exception) {
        this.exception = exception;
        return this;
    }

    public byte[] getResponseContent() {
        return responseContent;
    }

    public SimpleHttpResponseImpl setResponseContent(byte[] responseContent) {
        this.responseContent = responseContent;
        return this;
    }

    public boolean isRetry() {
        return retry;
    }

    public SimpleHttpResponseImpl setRetry(boolean retry) {
        this.retry = retry;
        return this;
    }

    @Override
    public String toString() {
        return "SimpleHttpResponseImpl{" +
                "successful=" + successful +
                ", headers=" + headers +
                ", statusCode=" + statusCode +
                ", exception=" + exception +
                ", retry=" + retry +
                '}';
    }
}
