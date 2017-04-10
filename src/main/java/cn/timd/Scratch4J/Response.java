package cn.timd.Scratch4J;

public interface Response {
    Response markAsFailure();
    Response markAsSuccess();
    boolean isSuccessful();

    Response setResponseContent(byte[] bytes);
    byte[] getResponseContent();
    Throwable getException();
    Response setException(Throwable exception);

    boolean isRetry();
    Response setRetry(boolean retry);
}
