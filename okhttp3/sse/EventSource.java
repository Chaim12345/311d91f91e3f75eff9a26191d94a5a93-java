package okhttp3.sse;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\u0006J\b\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u0005\u001a\u00020\u0004H&¨\u0006\u0007"}, d2 = {"Lokhttp3/sse/EventSource;", "", "Lokhttp3/Request;", "request", "", "cancel", "Factory", "okhttp-sse"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public interface EventSource {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&¨\u0006\b"}, d2 = {"Lokhttp3/sse/EventSource$Factory;", "", "Lokhttp3/Request;", "request", "Lokhttp3/sse/EventSourceListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lokhttp3/sse/EventSource;", "newEventSource", "okhttp-sse"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes3.dex */
    public interface Factory {
        @NotNull
        EventSource newEventSource(@NotNull Request request, @NotNull EventSourceListener eventSourceListener);
    }

    void cancel();

    @NotNull
    Request request();
}
