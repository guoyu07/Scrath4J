package cn.timd.Scratch4J.DownloaderImpl;

import cn.timd.Scratch4J.Downloader;
import cn.timd.Scratch4J.HttpRequest;
import cn.timd.Scratch4J.Request;
import cn.timd.Scratch4J.HttpResponse;
import cn.timd.Scratch4J.Response;
import cn.timd.Scratch4J.ResponseImpl.SimpleHttpResponse;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

public class SimpleHttpDownloader implements Downloader {
    private static final OkHttpClient client = new OkHttpClient();

    static {
        //// TODO: 2017/3/29 configure `client` such as cookie, cache, timeout and so on
    }

    private void convertResponse(okhttp3.Response okHttpResponse, HttpResponse httpResponse)
            throws IOException {
        int statusCode = okHttpResponse.code();
        httpResponse.setStatusCode(statusCode).markAsSuccess();

        Headers headers = okHttpResponse.headers();
        for (String header: headers.names())
            httpResponse.addHeader(header, headers.get(header));

        httpResponse.setResponseContent(okHttpResponse.body().bytes());
        // // TODO: 2017/4/10 set encoding too

        if (!okHttpResponse.isSuccessful()) {
            httpResponse.markAsFailure().setRetry(false);
            if (statusCode == 502 || statusCode == 503 || statusCode == 504)
                httpResponse.setRetry(true);
        }
    }

    public Response download(Request request)
            throws ClassCastException, NullPointerException {
        HttpRequest httpRequest = (HttpRequest) request;
        HttpResponse httpResponse = new SimpleHttpResponse();

        okhttp3.Request.Builder requestBuilder = new okhttp3.Request.Builder()
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
        okhttp3.Request okHttpRequest = requestBuilder.build();

        final Call call = client.newCall(okHttpRequest);
        try {
            okhttp3.Response okHttpResponse = call.execute();
            convertResponse(okHttpResponse, httpResponse);
        } catch (IOException ex) {
            httpResponse.setException(ex).markAsFailure().setRetry(true);
        }

        return httpResponse;
    }
}
