package cn.timd.Scratch4J.Request.impl;

import cn.timd.Scratch4J.Downloader.IDownloader;
import cn.timd.Scratch4J.Downloader.impl.SimpleHttpDownloaderImpl;
import cn.timd.Scratch4J.Request.IHttpRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SimpleHttpRequestImpl implements IHttpRequest {
    private Class<? extends IDownloader> downloader = SimpleHttpDownloaderImpl.class;
    private int depth = 1;
    private Map<String, String> headers = new HashMap<String, String>();
    private String URL = null;
    private String requestMethod = "get";
    private byte[] requestContent = null;
    private int failureCount = 0;

    public Class<? extends IDownloader> getDownloader() {
        return downloader;
    }

    public SimpleHttpRequestImpl setDownloader(Class<? extends IDownloader> downloader) {
        this.downloader = downloader;
        return this;
    }

    public int getDepth() {
        return depth;
    }

    public SimpleHttpRequestImpl setDepth(int depth) {
        this.depth = depth;
        return this;
    }

    public SimpleHttpRequestImpl addHeader(String header, String value) {
        headers.put(header, value);
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getURL() {
        return URL;
    }

    public SimpleHttpRequestImpl setURL(String URL) {
        if (URL == null)
            throw new IllegalArgumentException("URL == null");
        this.URL = URL;
        return this;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public SimpleHttpRequestImpl setRequestMethod(String requestMethod) {
        // // TODO: 2017/3/28 check supported request method
        if (requestMethod != null)
            this.requestMethod = requestMethod.toLowerCase();
        return this;
    }

    public byte[] getRequestContent() {
        return requestContent;
    }

    public SimpleHttpRequestImpl setRequestContent(byte[] bytes) {
        if (bytes != null)
            requestContent = bytes;
        return this;
    }

    public SimpleHttpRequestImpl incrementFailureCount() {
        failureCount += 1;
        return this;
    }

    public int getFailureCount() {
        return failureCount;
    }

    @Override
    public String toString() {
        return "SimpleHttpRequestImpl{" +
                "downloader=" + downloader +
                ", depth=" + depth +
                ", headers=" + headers +
                ", URL='" + URL + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", requestContent=" + Arrays.toString(requestContent) +
                ", failureCount=" + failureCount +
                '}';
    }
}
