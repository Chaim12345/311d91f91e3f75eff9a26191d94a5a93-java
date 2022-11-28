package com.appmattus.certificatetransparency.internal.utils;

import java.io.IOException;
import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class LimitedSizeInputStream extends InputStream {
    private final long maxSize;
    @NotNull
    private final InputStream original;
    private long total;

    public LimitedSizeInputStream(@NotNull InputStream original, long j2) {
        Intrinsics.checkNotNullParameter(original, "original");
        this.original = original;
        this.maxSize = j2;
    }

    private final void incrementCounter(int i2) {
        long j2 = this.total + i2;
        this.total = j2;
        if (j2 <= this.maxSize) {
            return;
        }
        throw new IOException("InputStream exceeded maximum size " + this.maxSize + " bytes");
    }

    @Override // java.io.InputStream
    public int read() {
        int read = this.original.read();
        if (read >= 0) {
            incrementCounter(1);
        }
        return read;
    }

    @Override // java.io.InputStream
    public int read(@NotNull byte[] b2) {
        Intrinsics.checkNotNullParameter(b2, "b");
        return read(b2, 0, b2.length);
    }

    @Override // java.io.InputStream
    public int read(@NotNull byte[] b2, int i2, int i3) {
        Intrinsics.checkNotNullParameter(b2, "b");
        int read = this.original.read(b2, i2, i3);
        if (read >= 0) {
            incrementCounter(read);
        }
        return read;
    }
}
