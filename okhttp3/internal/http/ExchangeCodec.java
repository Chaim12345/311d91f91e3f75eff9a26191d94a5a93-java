package okhttp3.internal.http;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.connection.RealConnection;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface ExchangeCodec {
    @NotNull
    public static final Companion Companion = Companion.f12551a;
    public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;

    /* loaded from: classes3.dex */
    public static final class Companion {
        public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ Companion f12551a = new Companion();

        private Companion() {
        }
    }

    void cancel();

    @NotNull
    Sink createRequestBody(@NotNull Request request, long j2);

    void finishRequest();

    void flushRequest();

    @NotNull
    RealConnection getConnection();

    @NotNull
    Source openResponseBodySource(@NotNull Response response);

    @Nullable
    Response.Builder readResponseHeaders(boolean z);

    long reportedContentLength(@NotNull Response response);

    @NotNull
    Headers trailers();

    void writeRequestHeaders(@NotNull Request request);
}
