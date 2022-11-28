package okhttp3.internal.sse;

import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Options;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0002\u0016\u0015B\u0017\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0013\u0010\u0014J$\u0010\b\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u0006\u0010\n\u001a\u00020\tR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0016\u0010\u000e\u001a\u00020\r8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0011\u001a\u00020\u00108\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0017"}, d2 = {"Lokhttp3/internal/sse/ServerSentEventReader;", "", "", "id", "type", "Lokio/Buffer;", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "", "completeEvent", "", "processNextEvent", "lastId", "Ljava/lang/String;", "Lokio/BufferedSource;", "source", "Lokio/BufferedSource;", "Lokhttp3/internal/sse/ServerSentEventReader$Callback;", "callback", "Lokhttp3/internal/sse/ServerSentEventReader$Callback;", "<init>", "(Lokio/BufferedSource;Lokhttp3/internal/sse/ServerSentEventReader$Callback;)V", "Companion", "Callback", "okhttp-sse"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class ServerSentEventReader {
    private static final ByteString CRLF;
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Options options;
    private final Callback callback;
    private String lastId;
    private final BufferedSource source;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J$\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0002H&J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH&¨\u0006\u000b"}, d2 = {"Lokhttp3/internal/sse/ServerSentEventReader$Callback;", "", "", "id", "type", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "", "onEvent", "", "timeMs", "onRetryChange", "okhttp-sse"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes3.dex */
    public interface Callback {
        void onEvent(@Nullable String str, @Nullable String str2, @NotNull String str3);

        void onRetryChange(long j2);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0014\u0010\u0006\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\f\u0010\b\u001a\u00020\u0007*\u00020\u0002H\u0002R\u0019\u0010\n\u001a\u00020\t8\u0006@\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0016\u0010\u000f\u001a\u00020\u000e8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lokhttp3/internal/sse/ServerSentEventReader$Companion;", "", "Lokio/BufferedSource;", "Lokio/Buffer;", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "", "readData", "", "readRetryMs", "Lokio/Options;", "options", "Lokio/Options;", "getOptions", "()Lokio/Options;", "Lokio/ByteString;", "CRLF", "Lokio/ByteString;", "<init>", "()V", "okhttp-sse"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void readData(BufferedSource bufferedSource, Buffer buffer) {
            buffer.writeByte(10);
            bufferedSource.readFully(buffer, bufferedSource.indexOfElement(ServerSentEventReader.CRLF));
            bufferedSource.select(getOptions());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final long readRetryMs(BufferedSource bufferedSource) {
            return Util.toLongOrDefault(bufferedSource.readUtf8LineStrict(), -1L);
        }

        @NotNull
        public final Options getOptions() {
            return ServerSentEventReader.options;
        }
    }

    static {
        Options.Companion companion = Options.Companion;
        ByteString.Companion companion2 = ByteString.Companion;
        options = companion.of(companion2.encodeUtf8("\r\n"), companion2.encodeUtf8("\r"), companion2.encodeUtf8("\n"), companion2.encodeUtf8("data: "), companion2.encodeUtf8("data:"), companion2.encodeUtf8("data\r\n"), companion2.encodeUtf8("data\r"), companion2.encodeUtf8("data\n"), companion2.encodeUtf8("id: "), companion2.encodeUtf8("id:"), companion2.encodeUtf8("id\r\n"), companion2.encodeUtf8("id\r"), companion2.encodeUtf8("id\n"), companion2.encodeUtf8("event: "), companion2.encodeUtf8("event:"), companion2.encodeUtf8("event\r\n"), companion2.encodeUtf8("event\r"), companion2.encodeUtf8("event\n"), companion2.encodeUtf8("retry: "), companion2.encodeUtf8("retry:"));
        CRLF = companion2.encodeUtf8("\r\n");
    }

    public ServerSentEventReader(@NotNull BufferedSource source, @NotNull Callback callback) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.source = source;
        this.callback = callback;
    }

    private final void completeEvent(String str, String str2, Buffer buffer) {
        if (buffer.size() != 0) {
            this.lastId = str;
            buffer.skip(1L);
            this.callback.onEvent(str, str2, buffer.readUtf8());
        }
    }

    public final boolean processNextEvent() {
        String str = this.lastId;
        Buffer buffer = new Buffer();
        while (true) {
            String str2 = null;
            while (true) {
                BufferedSource bufferedSource = this.source;
                Options options2 = options;
                int select = bufferedSource.select(options2);
                if (select >= 0 && 2 >= select) {
                    completeEvent(str, str2, buffer);
                    return true;
                } else if (3 <= select && 4 >= select) {
                    Companion.readData(this.source, buffer);
                } else if (5 <= select && 7 >= select) {
                    buffer.writeByte(10);
                } else if (8 <= select && 9 >= select) {
                    str = this.source.readUtf8LineStrict();
                    if (!(str.length() > 0)) {
                        str = null;
                    }
                } else if (10 <= select && 12 >= select) {
                    str = null;
                } else if (13 <= select && 14 >= select) {
                    str2 = this.source.readUtf8LineStrict();
                    if (str2.length() <= 0) {
                        r7 = false;
                    }
                    if (r7) {
                    }
                } else if (15 <= select && 17 >= select) {
                    break;
                } else if (18 <= select && 19 >= select) {
                    long readRetryMs = Companion.readRetryMs(this.source);
                    if (readRetryMs != -1) {
                        this.callback.onRetryChange(readRetryMs);
                    }
                } else if (select != -1) {
                    throw new AssertionError();
                } else {
                    long indexOfElement = this.source.indexOfElement(CRLF);
                    if (indexOfElement == -1) {
                        return false;
                    }
                    this.source.skip(indexOfElement);
                    this.source.select(options2);
                }
            }
        }
    }
}
