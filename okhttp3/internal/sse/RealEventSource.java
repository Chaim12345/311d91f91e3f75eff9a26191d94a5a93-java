package okhttp3.internal.sse;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.sse.ServerSentEventReader;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.bouncycastle.i18n.TextBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0017\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\b%\u0010&J\f\u0010\u0006\u001a\u00020\u0005*\u00020\u0004H\u0002J\u000e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007J\u0018\u0010\u000f\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u000e\u0010\u0010\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\rJ\u0018\u0010\u0013\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\b\u0010\u0016\u001a\u00020\tH\u0016J$\u0010\u001b\u001a\u00020\t2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\u0017H\u0016J\u0010\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016R\u0016\u0010\f\u001a\u00020\u001f8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\f\u0010 R\u0016\u0010\u0015\u001a\u00020\u00148\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010!R\u0016\u0010#\u001a\u00020\"8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006'"}, d2 = {"Lokhttp3/internal/sse/RealEventSource;", "Lokhttp3/sse/EventSource;", "Lokhttp3/internal/sse/ServerSentEventReader$Callback;", "Lokhttp3/Callback;", "Lokhttp3/ResponseBody;", "", "isEventStream", "Lokhttp3/OkHttpClient;", "client", "", "connect", "Lokhttp3/Call;", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Response;", "response", "onResponse", "processResponse", "Ljava/io/IOException;", "e", "onFailure", "Lokhttp3/Request;", "request", "cancel", "", "id", "type", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "onEvent", "", "timeMs", "onRetryChange", "Lokhttp3/internal/connection/RealCall;", "Lokhttp3/internal/connection/RealCall;", "Lokhttp3/Request;", "Lokhttp3/sse/EventSourceListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lokhttp3/sse/EventSourceListener;", "<init>", "(Lokhttp3/Request;Lokhttp3/sse/EventSourceListener;)V", "okhttp-sse"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class RealEventSource implements EventSource, ServerSentEventReader.Callback, Callback {
    private RealCall call;
    private final EventSourceListener listener;
    private final Request request;

    public RealEventSource(@NotNull Request request, @NotNull EventSourceListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.request = request;
        this.listener = listener;
    }

    private final boolean isEventStream(ResponseBody responseBody) {
        MediaType contentType = responseBody.contentType();
        return contentType != null && Intrinsics.areEqual(contentType.type(), TextBundle.TEXT_ENTRY) && Intrinsics.areEqual(contentType.subtype(), "event-stream");
    }

    @Override // okhttp3.sse.EventSource
    public void cancel() {
        RealCall realCall = this.call;
        if (realCall == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_CALL);
        }
        realCall.cancel();
    }

    public final void connect(@NotNull OkHttpClient client) {
        Intrinsics.checkNotNullParameter(client, "client");
        Call newCall = client.newBuilder().eventListener(EventListener.NONE).build().newCall(this.request);
        Objects.requireNonNull(newCall, "null cannot be cast to non-null type okhttp3.internal.connection.RealCall");
        RealCall realCall = (RealCall) newCall;
        this.call = realCall;
        realCall.enqueue(this);
    }

    @Override // okhttp3.internal.sse.ServerSentEventReader.Callback
    public void onEvent(@Nullable String str, @Nullable String str2, @NotNull String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.listener.onEvent(this, str, str2, data);
    }

    @Override // okhttp3.Callback
    public void onFailure(@NotNull Call call, @NotNull IOException e2) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(e2, "e");
        this.listener.onFailure(this, e2, null);
    }

    @Override // okhttp3.Callback
    public void onResponse(@NotNull Call call, @NotNull Response response) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        processResponse(response);
    }

    @Override // okhttp3.internal.sse.ServerSentEventReader.Callback
    public void onRetryChange(long j2) {
    }

    public final void processResponse(@NotNull Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        try {
            if (!response.isSuccessful()) {
                this.listener.onFailure(this, null, response);
                CloseableKt.closeFinally(response, null);
                return;
            }
            ResponseBody body = response.body();
            Intrinsics.checkNotNull(body);
            if (!isEventStream(body)) {
                EventSourceListener eventSourceListener = this.listener;
                eventSourceListener.onFailure(this, new IllegalStateException("Invalid content-type: " + body.contentType()), response);
                CloseableKt.closeFinally(response, null);
                return;
            }
            RealCall realCall = this.call;
            if (realCall == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_CALL);
            }
            realCall.timeoutEarlyExit();
            Response build = response.newBuilder().body(Util.EMPTY_RESPONSE).build();
            ServerSentEventReader serverSentEventReader = new ServerSentEventReader(body.source(), this);
            try {
                this.listener.onOpen(this, build);
                do {
                } while (serverSentEventReader.processNextEvent());
                this.listener.onClosed(this);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(response, null);
            } catch (Exception e2) {
                this.listener.onFailure(this, e2, build);
                CloseableKt.closeFinally(response, null);
            }
        } finally {
        }
    }

    @Override // okhttp3.sse.EventSource
    @NotNull
    public Request request() {
        return this.request;
    }
}
