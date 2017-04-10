package cn.timd.Scratch4J.ParserImpl;

import java.util.Arrays;

public class DefaultVo {
    private String request;
    private byte[] content;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "DefaultVo{" +
                "request='" + request + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
