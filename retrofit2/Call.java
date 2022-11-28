package retrofit2;

import okhttp3.Request;
import okio.Timeout;
/* loaded from: classes4.dex */
public interface Call<T> extends Cloneable {
    void cancel();

    Call<T> clone();

    void enqueue(Callback<T> callback);

    Response<T> execute();

    boolean isCanceled();

    boolean isExecuted();

    Request request();

    Timeout timeout();
}
