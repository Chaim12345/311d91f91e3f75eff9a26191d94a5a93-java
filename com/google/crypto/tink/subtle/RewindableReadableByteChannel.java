package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes2.dex */
public final class RewindableReadableByteChannel implements ReadableByteChannel {
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    final ReadableByteChannel f9858a;
    @GuardedBy("this")

    /* renamed from: b  reason: collision with root package name */
    ByteBuffer f9859b = null;
    @GuardedBy("this")

    /* renamed from: c  reason: collision with root package name */
    boolean f9860c = true;
    @GuardedBy("this")

    /* renamed from: d  reason: collision with root package name */
    boolean f9861d = false;

    public RewindableReadableByteChannel(ReadableByteChannel readableByteChannel) {
        this.f9858a = readableByteChannel;
    }

    private synchronized void setBufferLimit(int i2) {
        if (this.f9859b.capacity() < i2) {
            int position = this.f9859b.position();
            ByteBuffer allocate = ByteBuffer.allocate(Math.max(this.f9859b.capacity() * 2, i2));
            this.f9859b.rewind();
            allocate.put(this.f9859b);
            allocate.position(position);
            this.f9859b = allocate;
        }
        this.f9859b.limit(i2);
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.f9860c = false;
        this.f9861d = true;
        this.f9858a.close();
    }

    public synchronized void disableRewinding() {
        this.f9860c = false;
    }

    @Override // java.nio.channels.Channel
    public synchronized boolean isOpen() {
        return this.f9858a.isOpen();
    }

    @Override // java.nio.channels.ReadableByteChannel
    public synchronized int read(ByteBuffer byteBuffer) {
        int i2;
        if (this.f9861d) {
            return this.f9858a.read(byteBuffer);
        }
        int remaining = byteBuffer.remaining();
        if (remaining == 0) {
            i2 = 0;
        } else {
            ByteBuffer byteBuffer2 = this.f9859b;
            if (byteBuffer2 == null) {
                if (!this.f9860c) {
                    this.f9861d = true;
                    return this.f9858a.read(byteBuffer);
                }
                ByteBuffer allocate = ByteBuffer.allocate(remaining);
                this.f9859b = allocate;
                int read = this.f9858a.read(allocate);
                this.f9859b.flip();
                if (read > 0) {
                    byteBuffer.put(this.f9859b);
                }
                return read;
            } else if (byteBuffer2.remaining() >= remaining) {
                int limit = this.f9859b.limit();
                ByteBuffer byteBuffer3 = this.f9859b;
                byteBuffer3.limit(byteBuffer3.position() + remaining);
                byteBuffer.put(this.f9859b);
                this.f9859b.limit(limit);
                if (!this.f9860c && !this.f9859b.hasRemaining()) {
                    this.f9859b = null;
                    this.f9861d = true;
                }
                return remaining;
            } else {
                int remaining2 = this.f9859b.remaining();
                int position = this.f9859b.position();
                int limit2 = this.f9859b.limit();
                setBufferLimit((remaining - remaining2) + limit2);
                this.f9859b.position(limit2);
                int read2 = this.f9858a.read(this.f9859b);
                this.f9859b.flip();
                this.f9859b.position(position);
                byteBuffer.put(this.f9859b);
                if (remaining2 != 0 || read2 >= 0) {
                    int position2 = this.f9859b.position() - position;
                    if (!this.f9860c && !this.f9859b.hasRemaining()) {
                        this.f9859b = null;
                        this.f9861d = true;
                    }
                    return position2;
                }
                i2 = -1;
            }
        }
        return i2;
    }

    public synchronized void rewind() {
        if (!this.f9860c) {
            throw new IOException("Cannot rewind anymore.");
        }
        ByteBuffer byteBuffer = this.f9859b;
        if (byteBuffer != null) {
            byteBuffer.position(0);
        }
    }
}
