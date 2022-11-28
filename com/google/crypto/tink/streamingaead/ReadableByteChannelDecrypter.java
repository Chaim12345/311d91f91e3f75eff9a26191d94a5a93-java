package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.subtle.RewindableReadableByteChannel;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.security.GeneralSecurityException;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes2.dex */
final class ReadableByteChannelDecrypter implements ReadableByteChannel {
    @GuardedBy("this")

    /* renamed from: c  reason: collision with root package name */
    RewindableReadableByteChannel f9818c;

    /* renamed from: e  reason: collision with root package name */
    byte[] f9820e;
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    ReadableByteChannel f9816a = null;
    @GuardedBy("this")

    /* renamed from: b  reason: collision with root package name */
    ReadableByteChannel f9817b = null;

    /* renamed from: d  reason: collision with root package name */
    Deque f9819d = new ArrayDeque();

    public ReadableByteChannelDecrypter(PrimitiveSet<StreamingAead> primitiveSet, ReadableByteChannel readableByteChannel, byte[] bArr) {
        for (PrimitiveSet.Entry<StreamingAead> entry : primitiveSet.getRawPrimitives()) {
            this.f9819d.add(entry.getPrimitive());
        }
        this.f9818c = new RewindableReadableByteChannel(readableByteChannel);
        this.f9820e = (byte[]) bArr.clone();
    }

    @GuardedBy("this")
    private synchronized ReadableByteChannel nextAttemptingChannel() {
        while (!this.f9819d.isEmpty()) {
            try {
            } catch (GeneralSecurityException unused) {
                this.f9818c.rewind();
            }
        }
        throw new IOException("No matching key found for the ciphertext in the stream.");
        return ((StreamingAead) this.f9819d.removeFirst()).newDecryptingChannel(this.f9818c, this.f9820e);
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.f9818c.close();
    }

    @Override // java.nio.channels.Channel
    public synchronized boolean isOpen() {
        return this.f9818c.isOpen();
    }

    @Override // java.nio.channels.ReadableByteChannel
    public synchronized int read(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() == 0) {
            return 0;
        }
        ReadableByteChannel readableByteChannel = this.f9817b;
        if (readableByteChannel != null) {
            return readableByteChannel.read(byteBuffer);
        }
        if (this.f9816a == null) {
            this.f9816a = nextAttemptingChannel();
        }
        while (true) {
            try {
                int read = this.f9816a.read(byteBuffer);
                if (read == 0) {
                    return 0;
                }
                this.f9817b = this.f9816a;
                this.f9816a = null;
                this.f9818c.disableRewinding();
                return read;
            } catch (IOException unused) {
                this.f9818c.rewind();
                this.f9816a = nextAttemptingChannel();
            }
            this.f9816a = nextAttemptingChannel();
        }
    }
}
