package okhttp3;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.Closeable;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http1.HeadersReader;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Options;
import okio.Source;
import okio.Timeout;
import org.apache.commons.cli.HelpFormatter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class MultipartReader implements Closeable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Options afterBoundaryOptions;
    @NotNull
    private final String boundary;
    private boolean closed;
    @NotNull
    private final ByteString crlfDashDashBoundary;
    @Nullable
    private PartSource currentPart;
    @NotNull
    private final ByteString dashDashBoundary;
    private boolean noMoreParts;
    private int partCount;
    @NotNull
    private final BufferedSource source;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Options getAfterBoundaryOptions() {
            return MultipartReader.afterBoundaryOptions;
        }
    }

    /* loaded from: classes3.dex */
    public static final class Part implements Closeable {
        @NotNull
        private final BufferedSource body;
        @NotNull
        private final Headers headers;

        public Part(@NotNull Headers headers, @NotNull BufferedSource body) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            Intrinsics.checkNotNullParameter(body, "body");
            this.headers = headers;
            this.body = body;
        }

        @JvmName(name = "body")
        @NotNull
        public final BufferedSource body() {
            return this.body;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.body.close();
        }

        @JvmName(name = "headers")
        @NotNull
        public final Headers headers() {
            return this.headers;
        }
    }

    /* loaded from: classes3.dex */
    private final class PartSource implements Source {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ MultipartReader f12507a;
        @NotNull
        private final Timeout timeout;

        public PartSource(MultipartReader this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.f12507a = this$0;
            this.timeout = new Timeout();
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (Intrinsics.areEqual(this.f12507a.currentPart, this)) {
                this.f12507a.currentPart = null;
            }
        }

        @Override // okio.Source
        public long read(@NotNull Buffer sink, long j2) {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (j2 >= 0) {
                if (Intrinsics.areEqual(this.f12507a.currentPart, this)) {
                    Timeout timeout = this.f12507a.source.timeout();
                    Timeout timeout2 = this.timeout;
                    MultipartReader multipartReader = this.f12507a;
                    long timeoutNanos = timeout.timeoutNanos();
                    long minTimeout = Timeout.Companion.minTimeout(timeout2.timeoutNanos(), timeout.timeoutNanos());
                    TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                    timeout.timeout(minTimeout, timeUnit);
                    if (!timeout.hasDeadline()) {
                        if (timeout2.hasDeadline()) {
                            timeout.deadlineNanoTime(timeout2.deadlineNanoTime());
                        }
                        try {
                            long currentPartBytesRemaining = multipartReader.currentPartBytesRemaining(j2);
                            long read = currentPartBytesRemaining == 0 ? -1L : multipartReader.source.read(sink, currentPartBytesRemaining);
                            timeout.timeout(timeoutNanos, timeUnit);
                            if (timeout2.hasDeadline()) {
                                timeout.clearDeadline();
                            }
                            return read;
                        } catch (Throwable th) {
                            timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                            if (timeout2.hasDeadline()) {
                                timeout.clearDeadline();
                            }
                            throw th;
                        }
                    }
                    long deadlineNanoTime = timeout.deadlineNanoTime();
                    if (timeout2.hasDeadline()) {
                        timeout.deadlineNanoTime(Math.min(timeout.deadlineNanoTime(), timeout2.deadlineNanoTime()));
                    }
                    try {
                        long currentPartBytesRemaining2 = multipartReader.currentPartBytesRemaining(j2);
                        long read2 = currentPartBytesRemaining2 == 0 ? -1L : multipartReader.source.read(sink, currentPartBytesRemaining2);
                        timeout.timeout(timeoutNanos, timeUnit);
                        if (timeout2.hasDeadline()) {
                            timeout.deadlineNanoTime(deadlineNanoTime);
                        }
                        return read2;
                    } catch (Throwable th2) {
                        timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                        if (timeout2.hasDeadline()) {
                            timeout.deadlineNanoTime(deadlineNanoTime);
                        }
                        throw th2;
                    }
                }
                throw new IllegalStateException("closed".toString());
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount < 0: ", Long.valueOf(j2)).toString());
        }

        @Override // okio.Source
        @NotNull
        public Timeout timeout() {
            return this.timeout;
        }
    }

    static {
        Options.Companion companion = Options.Companion;
        ByteString.Companion companion2 = ByteString.Companion;
        afterBoundaryOptions = companion.of(companion2.encodeUtf8("\r\n"), companion2.encodeUtf8(HelpFormatter.DEFAULT_LONG_OPT_PREFIX), companion2.encodeUtf8(" "), companion2.encodeUtf8("\t"));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public MultipartReader(@NotNull ResponseBody response) {
        this(r0, r3);
        Intrinsics.checkNotNullParameter(response, "response");
        BufferedSource source = response.source();
        MediaType contentType = response.contentType();
        String parameter = contentType == null ? null : contentType.parameter("boundary");
        if (parameter == null) {
            throw new ProtocolException("expected the Content-Type to have a boundary parameter");
        }
    }

    public MultipartReader(@NotNull BufferedSource source, @NotNull String boundary) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(boundary, "boundary");
        this.source = source;
        this.boundary = boundary;
        this.dashDashBoundary = new Buffer().writeUtf8(HelpFormatter.DEFAULT_LONG_OPT_PREFIX).writeUtf8(boundary).readByteString();
        this.crlfDashDashBoundary = new Buffer().writeUtf8("\r\n--").writeUtf8(boundary).readByteString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long currentPartBytesRemaining(long j2) {
        this.source.require(this.crlfDashDashBoundary.size());
        long indexOf = this.source.getBuffer().indexOf(this.crlfDashDashBoundary);
        if (indexOf == -1) {
            indexOf = (this.source.getBuffer().size() - this.crlfDashDashBoundary.size()) + 1;
        }
        return Math.min(j2, indexOf);
    }

    @JvmName(name = "boundary")
    @NotNull
    public final String boundary() {
        return this.boundary;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.currentPart = null;
        this.source.close();
    }

    @Nullable
    public final Part nextPart() {
        BufferedSource bufferedSource;
        ByteString byteString;
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        if (this.noMoreParts) {
            return null;
        }
        if (this.partCount == 0 && this.source.rangeEquals(0L, this.dashDashBoundary)) {
            bufferedSource = this.source;
            byteString = this.dashDashBoundary;
        } else {
            while (true) {
                long currentPartBytesRemaining = currentPartBytesRemaining(PlaybackStateCompat.ACTION_PLAY_FROM_URI);
                if (currentPartBytesRemaining == 0) {
                    break;
                }
                this.source.skip(currentPartBytesRemaining);
            }
            bufferedSource = this.source;
            byteString = this.crlfDashDashBoundary;
        }
        bufferedSource.skip(byteString.size());
        boolean z = false;
        while (true) {
            int select = this.source.select(afterBoundaryOptions);
            if (select == -1) {
                throw new ProtocolException("unexpected characters after boundary");
            }
            if (select == 0) {
                this.partCount++;
                Headers readHeaders = new HeadersReader(this.source).readHeaders();
                PartSource partSource = new PartSource(this);
                this.currentPart = partSource;
                return new Part(readHeaders, Okio.buffer(partSource));
            } else if (select == 1) {
                if (z) {
                    throw new ProtocolException("unexpected characters after boundary");
                }
                if (this.partCount != 0) {
                    this.noMoreParts = true;
                    return null;
                }
                throw new ProtocolException("expected at least 1 part");
            } else if (select == 2 || select == 3) {
                z = true;
            }
        }
    }
}
