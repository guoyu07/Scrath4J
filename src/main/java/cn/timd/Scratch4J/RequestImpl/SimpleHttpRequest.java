package cn.timd.Scratch4J.RequestImpl;

import cn.timd.Scratch4J.Downloader;
import cn.timd.Scratch4J.DownloaderImpl.SimpleHttpDownloader;
import cn.timd.Scratch4J.HttpRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SimpleHttpRequest implements HttpRequest {
    private Downloader downloader = new SimpleHttpDownloader();
    private int depth = 1;
    private Map<String, String> headers = new HashMap<String, String>();
    private String URL = null;
    private String requestMethod = "get";
    private byte[] requestContent = null;
    private int failureCount = 0;

    public Downloader getDownloader() {
        return downloader;
    }

    public SimpleHttpRequest setDownloader(Downloader downloader) {
        this.downloader = downloader;
        return this;
    }

    public int getDepth() {
        return depth;
    }

    public SimpleHttpRequest setDepth(int depth) {
        this.depth = depth;
        return this;
    }

    public SimpleHttpRequest addHeader(String header, String value) {
        headers.put(header, value);
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getURL() {
        return URL;
    }

    public SimpleHttpRequest setURL(String URL) {
        if (URL == null)
            throw new IllegalArgumentException("URL == null");
        this.URL = URL;
        return this;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public SimpleHttpRequest setRequestMethod(String requestMethod) {
        // // TODO: 2017/3/28 check supported request method
        if (requestMethod != null)
            this.requestMethod = requestMethod.toLowerCase();
        return this;
    }

    public byte[] getRequestContent() {
        return requestContent;
    }

    public SimpleHttpRequest setRequestContent(byte[] bytes) {
        if (bytes != null)
            requestContent = bytes;
        return this;
    }

    public SimpleHttpRequest incrementFailureCount() {
        failureCount += 1;
        return this;
    }

    public int getFailureCount() {
        return failureCount;
    }

    @Override
    public String toString() {
        return "SimpleHttpRequest{" +
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
