package com.bumptech.glide.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;
@Deprecated
/* loaded from: classes.dex */
public class ExceptionCatchingInputStream extends InputStream {
    private static final Queue<ExceptionCatchingInputStream> QUEUE = Util.createQueue(0);
    private IOException exception;
    private InputStream wrapped;

    ExceptionCatchingInputStream() {
    }

    @NonNull
    public static ExceptionCatchingInputStream obtain(@NonNull InputStream inputStream) {
        ExceptionCatchingInputStream poll;
        Queue<ExceptionCatchingInputStream> queue = QUEUE;
        synchronized (queue) {
            poll = queue.poll();
        }
        if (poll == null) {
            poll = new ExceptionCatchingInputStream();
        }
        poll.a(inputStream);
        return poll;
    }

    void a(@NonNull InputStream inputStream) {
        this.wrapped = inputStream;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.wrapped.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.wrapped.close();
    }

    @Nullable
    public IOException getException() {
        return this.exception;
    }

    @Override // java.io.InputStream
    public void mark(int i2) {
        this.wrapped.mark(i2);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.wrapped.markSupported();
    }

    @Override // java.io.InputStream
    public int read() {
        try {
            return this.wrapped.read();
        } catch (IOException e2) {
            this.exception = e2;
            return -1;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        try {
            return this.wrapped.read(bArr);
        } catch (IOException e2) {
            this.exception = e2;
            return -1;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        try {
            return this.wrapped.read(bArr, i2, i3);
        } catch (IOException e2) {
            this.exception = e2;
            return -1;
        }
    }

    public void release() {
        this.exception = null;
        this.wrapped = null;
        Queue<ExceptionCatchingInputStream> queue = QUEUE;
        synchronized (queue) {
            queue.offer(this);
        }
    }

    @Override // java.io.InputStream
    public synchronized void reset() {
        this.wrapped.reset();
    }

    @Override // java.io.InputStream
    public long skip(long j2) {
        try {
            return this.wrapped.skip(j2);
        } catch (IOException e2) {
            this.exception = e2;
            return 0L;
        }
    }
}
