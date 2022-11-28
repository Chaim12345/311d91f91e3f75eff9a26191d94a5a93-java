package com.chuckerteam.chucker.internal.support;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016R\u0016\u0010\n\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/DepletingSource;", "Lokio/ForwardingSource;", "Lokio/Buffer;", "sink", "", "byteCount", "read", "", "close", "", "shouldDeplete", "Z", "Lokio/Source;", "delegate", "<init>", "(Lokio/Source;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class DepletingSource extends ForwardingSource {
    private boolean shouldDeplete;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DepletingSource(@NotNull Source delegate) {
        super(delegate);
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.shouldDeplete = true;
    }

    @Override // okio.ForwardingSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.shouldDeplete) {
            try {
                Okio.buffer(delegate()).readAll(Okio.blackhole());
            } catch (IOException e2) {
                new IOException("An error occurred while depleting the source", e2).printStackTrace();
            }
        }
        this.shouldDeplete = false;
        super.close();
    }

    @Override // okio.ForwardingSource, okio.Source
    public long read(@NotNull Buffer sink, long j2) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        try {
            long read = super.read(sink, j2);
            if (read == -1) {
                this.shouldDeplete = false;
            }
            return read;
        } catch (IOException e2) {
            this.shouldDeplete = false;
            throw e2;
        }
    }
}
