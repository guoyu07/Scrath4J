package cn.timd.Scratch4J.Request;

import cn.timd.Scratch4J.Downloader.IDownloader;

public interface IRequest {
    int getFailureCount();
    IRequest incrementFailureCount();

    byte[] getRequestContent();
    IRequest setRequestContent(byte[] bytes);

    int getDepth();
    IRequest setDepth(int depth);

    IRequest setDownloader(Class<? extends IDownloader> downloader);
    Class<?> getDownloader();
}
