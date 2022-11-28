package okhttp3.sse;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.sse.RealEventSource;
import okhttp3.sse.EventSource;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0007¨\u0006\u000e"}, d2 = {"Lokhttp3/sse/EventSources;", "", "Lokhttp3/OkHttpClient;", "client", "Lokhttp3/sse/EventSource$Factory;", "createFactory", "Lokhttp3/Response;", "response", "Lokhttp3/sse/EventSourceListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "", "processResponse", "<init>", "()V", "okhttp-sse"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class EventSources {
    public static final EventSources INSTANCE = new EventSources();

    private EventSources() {
    }

    @JvmStatic
    @NotNull
    public static final EventSource.Factory createFactory(@NotNull final OkHttpClient client) {
        Intrinsics.checkNotNullParameter(client, "client");
        return new EventSource.Factory() { // from class: okhttp3.sse.EventSources$createFactory$1
            @Override // okhttp3.sse.EventSource.Factory
            @NotNull
            public final EventSource newEventSource(@NotNull Request request, @NotNull EventSourceListener listener) {
                Intrinsics.checkNotNullParameter(request, "request");
                Intrinsics.checkNotNullParameter(listener, "listener");
                if (request.header("Accept") == null) {
                    request = request.newBuilder().addHeader("Accept", "text/event-stream").build();
                }
                RealEventSource realEventSource = new RealEventSource(request, listener);
                realEventSource.connect(OkHttpClient.this);
                return realEventSource;
            }
        };
    }

    @JvmStatic
    public static final void processResponse(@NotNull Response response, @NotNull EventSourceListener listener) {
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(listener, "listener");
        new RealEventSource(response.request(), listener).processResponse(response);
    }
}
