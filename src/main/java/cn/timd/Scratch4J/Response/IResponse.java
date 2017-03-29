package cn.timd.Scratch4J.Response;

public interface IResponse {
    IResponse markAsFailure();
    IResponse markAsSuccess();
    boolean isSuccessful();

    IResponse setResponseContent(byte[] bytes);
    byte[] getResponseContent();
    Throwable getException();
    IResponse setException(Throwable exception);

    boolean isRetry();
    IResponse setRetry(boolean retry);
}
