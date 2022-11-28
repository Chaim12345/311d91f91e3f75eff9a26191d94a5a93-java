package com.chuckerteam.chucker.internal.support;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0013\u001a\u00020\u0001\u0012\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0018\u0010\u0019J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\b\u001a\u00020\u0006H\u0002J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\u0006H\u0016J\b\u0010\r\u001a\u00020\fH\u0016R\u0016\u0010\u000e\u001a\u00020\u00028\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0011\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u00020\u00018\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0016\u001a\u00020\u00158\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/TeeSource;", "Lokio/Source;", "Lokio/Buffer;", "sink", "", "bytesRead", "", "copyBytesToSideStream", "safeCloseSideStream", "byteCount", "read", "close", "Lokio/Timeout;", "timeout", "tempBuffer", "Lokio/Buffer;", "", "isFailure", "Z", "upstream", "Lokio/Source;", "Lokio/Sink;", "sideStream", "Lokio/Sink;", "<init>", "(Lokio/Source;Lokio/Sink;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class TeeSource implements Source {
    private boolean isFailure;
    private final Sink sideStream;
    private final Buffer tempBuffer;
    private final Source upstream;

    public TeeSource(@NotNull Source upstream, @NotNull Sink sideStream) {
        Intrinsics.checkNotNullParameter(upstream, "upstream");
        Intrinsics.checkNotNullParameter(sideStream, "sideStream");
        this.upstream = upstream;
        this.sideStream = sideStream;
        this.tempBuffer = new Buffer();
    }

    private final void copyBytesToSideStream(Buffer buffer, long j2) {
        buffer.copyTo(this.tempBuffer, buffer.size() - j2, j2);
        try {
            this.sideStream.write(this.tempBuffer, j2);
        } catch (IOException unused) {
            this.isFailure = true;
            safeCloseSideStream();
        }
    }

    private final void safeCloseSideStream() {
        try {
            this.sideStream.close();
        } catch (IOException unused) {
            this.isFailure = true;
        }
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        safeCloseSideStream();
        this.upstream.close();
    }

    @Override // okio.Source
    public long read(@NotNull Buffer sink, long j2) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        long read = this.upstream.read(sink, j2);
        if (read == -1) {
            safeCloseSideStream();
            return -1L;
        }
        if (!this.isFailure) {
            copyBytesToSideStream(sink, read);
        }
        return read;
    }

    @Override // okio.Source
    @NotNull
    public Timeout timeout() {
        Timeout timeout = this.upstream.timeout();
        Intrinsics.checkNotNullExpressionValue(timeout, "upstream.timeout()");
        return timeout;
    }
}
