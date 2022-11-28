package okhttp3;

import okio.Timeout;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface Call extends Cloneable {

    /* loaded from: classes3.dex */
    public interface Factory {
        @NotNull
        Call newCall(@NotNull Request request);
    }

    void cancel();

    @NotNull
    Call clone();

    void enqueue(@NotNull Callback callback);

    @NotNull
    Response execute();

    boolean isCanceled();

    boolean isExecuted();

    @NotNull
    Request request();

    @NotNull
    Timeout timeout();
}
