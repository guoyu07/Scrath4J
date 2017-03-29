package cn.timd.Scratch4J.Downloader.impl;

import cn.timd.Scratch4J.Downloader.IDownloader;
import cn.timd.Scratch4J.Request.IHttpRequest;
import cn.timd.Scratch4J.Request.IRequest;
import cn.timd.Scratch4J.Response.IHttpResponse;
import cn.timd.Scratch4J.Response.IResponse;
import cn.timd.Scratch4J.Response.impl.SimpleHttpResponseImpl;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

public class SimpleHttpDownloaderImpl implements IDownloader {
    private static final OkHttpClient client = new OkHttpClient();

    static {
        // // TODO: 2017/3/28 set cookies and cache, in order to keep session
        // // TODO: 2017/3/28 set timeout also
    }

    private void convertResponse(Response okHttpResponse, IHttpResponse httpResponse)
            throws IOException {
        int statusCode = okHttpResponse.code();
        httpResponse.setStatusCode(statusCode).markAsSuccess();

        Headers headers = okHttpResponse.headers();
        for (String header: headers.names())
            httpResponse.setHeader(header, headers.get(header));

        httpResponse.setResponseContent(okHttpResponse.body().bytes());

        if (!okHttpResponse.isSuccessful()) {
            httpResponse.markAsFailure().setRetry(false);
            if (statusCode == 502 || statusCode == 503 || statusCode == 504)
                httpResponse.setRetry(true);
        }
    }

    public IResponse download(IRequest request)
            throws ClassCastException, NullPointerException {
        IHttpRequest httpRequest = (IHttpRequest) request;
        IHttpResponse httpResponse = new SimpleHttpResponseImpl();

        Request.Builder requestBuilder = new Request.Builder()
                .url(httpRequest.getURL());

        String contentType = "application/octet-stream";
        if (httpRequest.getHeaders() != null)
            for (Map.Entry<String, String> entry: httpRequest.getHeaders().entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
                if (entry.getKey().toLowerCase().equals("content-type"))
                    contentType = entry.getValue();
        }

        String requestMethod = httpRequest.getRequestMethod();
        if ("post".equals(requestMethod))
            requestBuilder.post(RequestBody.create(
                    MediaType.parse(contentType), httpRequest.getRequestContent()));
        else if ("put".equals(requestMethod))
            requestBuilder.put(RequestBody.create(
                    MediaType.parse(contentType), httpRequest.getRequestContent()));
        else if ("delete".equals(requestMethod))
            requestBuilder.delete(RequestBody.create(
                    MediaType.parse(contentType), httpRequest.getRequestContent()));
        else
            requestBuilder.get();
        Request okHttpRequest = requestBuilder.build();

        final Call call = client.newCall(okHttpRequest);
        try {
            Response okHttpResponse = call.execute();
            convertResponse(okHttpResponse, httpResponse);
        } catch (IOException ex) {
            httpResponse.setException(ex.getClass()).markAsFailure().setRetry(true);
        }

        return httpResponse;
    }
}
