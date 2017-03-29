package cn.timd.Scratch4J.Downloader;

import cn.timd.Scratch4J.Request.IRequest;
import cn.timd.Scratch4J.Response.IResponse;

public interface IDownloader {
    IResponse download(IRequest request);
}
