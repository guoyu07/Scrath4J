package cn.timd.Scratch4J.Response;

public interface IResponse {
    IResponse markAsFailure();
    IResponse markAsSuccess();
    boolean isSuccessful();

    IResponse setResponseContent(byte[] bytes);
    byte[] getResponseContent();
    Class<?> getException();
    IResponse setException(Class<?> exception);

    boolean isRetry();
    IResponse setRetry(boolean retry);
}
