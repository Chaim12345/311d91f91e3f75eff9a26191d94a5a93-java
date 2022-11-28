package com.chuckerteam.chucker.internal.support;

import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import okio.Buffer;
import okio.Okio;
import okio.Sink;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001:\u0001\"B#\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\b\b\u0002\u0010\u001f\u001a\u00020\n¢\u0006\u0004\b \u0010!J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0011\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\f\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\u0004H\u0016J\b\u0010\u000e\u001a\u00020\u0004H\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016R\u0016\u0010\u0011\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0014\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0016\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0015R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001d\u001a\u00020\u001c8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u00020\n8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010\u0012¨\u0006#"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/ReportingSink;", "Lokio/Sink;", "Ljava/io/IOException;", "exception", "", "callDownstreamFailure", "safeCloseDownstream", "()Lkotlin/Unit;", "Lokio/Buffer;", "source", "", "byteCount", "write", "flush", "close", "Lokio/Timeout;", "timeout", "totalByteCount", "J", "", "isFailure", "Z", "isClosed", "downstream", "Lokio/Sink;", "Ljava/io/File;", "downstreamFile", "Ljava/io/File;", "Lcom/chuckerteam/chucker/internal/support/ReportingSink$Callback;", "callback", "Lcom/chuckerteam/chucker/internal/support/ReportingSink$Callback;", "writeByteLimit", "<init>", "(Ljava/io/File;Lcom/chuckerteam/chucker/internal/support/ReportingSink$Callback;J)V", "Callback", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class ReportingSink implements Sink {
    private final Callback callback;
    private Sink downstream;
    private final File downstreamFile;
    private boolean isClosed;
    private boolean isFailure;
    private long totalByteCount;
    private final long writeByteLimit;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&J\u001a\u0010\n\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\bH&¨\u0006\u000b"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/ReportingSink$Callback;", "", "Ljava/io/File;", "file", "", "sourceByteCount", "", "onClosed", "Ljava/io/IOException;", "exception", "onFailure", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public interface Callback {
        void onClosed(@Nullable File file, long j2);

        void onFailure(@Nullable File file, @NotNull IOException iOException);
    }

    public ReportingSink(@Nullable File file, @NotNull Callback callback, long j2) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.downstreamFile = file;
        this.callback = callback;
        this.writeByteLimit = j2;
        Sink sink = null;
        if (file != null) {
            try {
                sink = Okio.sink(file);
            } catch (IOException e2) {
                callDownstreamFailure(new IOException("Failed to use file " + this.downstreamFile + " by Chucker", e2));
            }
        }
        this.downstream = sink;
    }

    public /* synthetic */ ReportingSink(File file, Callback callback, long j2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, callback, (i2 & 4) != 0 ? LongCompanionObject.MAX_VALUE : j2);
    }

    private final void callDownstreamFailure(IOException iOException) {
        if (this.isFailure) {
            return;
        }
        this.isFailure = true;
        safeCloseDownstream();
        this.callback.onFailure(this.downstreamFile, iOException);
    }

    private final Unit safeCloseDownstream() {
        try {
            Sink sink = this.downstream;
            if (sink != null) {
                sink.close();
                return Unit.INSTANCE;
            }
            return null;
        } catch (IOException e2) {
            callDownstreamFailure(e2);
            return Unit.INSTANCE;
        }
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.isClosed) {
            return;
        }
        this.isClosed = true;
        safeCloseDownstream();
        this.callback.onClosed(this.downstreamFile, this.totalByteCount);
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() {
        if (this.isFailure) {
            return;
        }
        try {
            Sink sink = this.downstream;
            if (sink != null) {
                sink.flush();
            }
        } catch (IOException e2) {
            callDownstreamFailure(e2);
        }
    }

    @Override // okio.Sink
    @NotNull
    public Timeout timeout() {
        Timeout timeout;
        Sink sink = this.downstream;
        if (sink == null || (timeout = sink.timeout()) == null) {
            Timeout timeout2 = Timeout.NONE;
            Intrinsics.checkNotNullExpressionValue(timeout2, "Timeout.NONE");
            return timeout2;
        }
        return timeout;
    }

    @Override // okio.Sink
    public void write(@NotNull Buffer source, long j2) {
        Intrinsics.checkNotNullParameter(source, "source");
        long j3 = this.totalByteCount;
        this.totalByteCount = j3 + j2;
        if (this.isFailure) {
            return;
        }
        long j4 = this.writeByteLimit;
        if (j3 >= j4) {
            return;
        }
        if (j3 + j2 > j4) {
            j2 = j4 - j3;
        }
        if (j2 == 0) {
            return;
        }
        try {
            Sink sink = this.downstream;
            if (sink != null) {
                sink.write(source, j2);
            }
        } catch (IOException e2) {
            callDownstreamFailure(e2);
        }
    }
}
