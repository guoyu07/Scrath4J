package cn.timd.Scratch4J;

public interface Request {
    int getFailureCount();
    Request incrementFailureCount();

    byte[] getRequestContent();
    Request setRequestContent(byte[] bytes);

    int getDepth();
    Request setDepth(int depth);

    Request setDownloader(Downloader downloader);
    Downloader getDownloader();
}
