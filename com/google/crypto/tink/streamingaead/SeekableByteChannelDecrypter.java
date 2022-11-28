package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.SeekableByteChannel;
import java.security.GeneralSecurityException;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes2.dex */
final class SeekableByteChannelDecrypter implements SeekableByteChannel {
    @GuardedBy("this")

    /* renamed from: c  reason: collision with root package name */
    SeekableByteChannel f9823c;
    @GuardedBy("this")

    /* renamed from: d  reason: collision with root package name */
    long f9824d;
    @GuardedBy("this")

    /* renamed from: e  reason: collision with root package name */
    long f9825e;

    /* renamed from: g  reason: collision with root package name */
    byte[] f9827g;
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    SeekableByteChannel f9821a = null;
    @GuardedBy("this")

    /* renamed from: b  reason: collision with root package name */
    SeekableByteChannel f9822b = null;

    /* renamed from: f  reason: collision with root package name */
    Deque f9826f = new ArrayDeque();

    public SeekableByteChannelDecrypter(PrimitiveSet<StreamingAead> primitiveSet, SeekableByteChannel seekableByteChannel, byte[] bArr) {
        for (PrimitiveSet.Entry<StreamingAead> entry : primitiveSet.getRawPrimitives()) {
            this.f9826f.add(entry.getPrimitive());
        }
        this.f9823c = seekableByteChannel;
        this.f9824d = -1L;
        this.f9825e = seekableByteChannel.position();
        this.f9827g = (byte[]) bArr.clone();
    }

    @GuardedBy("this")
    private synchronized SeekableByteChannel nextAttemptingChannel() {
        SeekableByteChannel newSeekableDecryptingChannel;
        while (!this.f9826f.isEmpty()) {
            this.f9823c.position(this.f9825e);
            try {
                newSeekableDecryptingChannel = ((StreamingAead) this.f9826f.removeFirst()).newSeekableDecryptingChannel(this.f9823c, this.f9827g);
                long j2 = this.f9824d;
                if (j2 >= 0) {
                    newSeekableDecryptingChannel.position(j2);
                }
            } catch (GeneralSecurityException unused) {
            }
        }
        throw new IOException("No matching key found for the ciphertext in the stream.");
        return newSeekableDecryptingChannel;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    @GuardedBy("this")
    public synchronized void close() {
        this.f9823c.close();
    }

    @Override // java.nio.channels.Channel
    @GuardedBy("this")
    public synchronized boolean isOpen() {
        return this.f9823c.isOpen();
    }

    @Override // java.nio.channels.SeekableByteChannel
    @GuardedBy("this")
    public synchronized long position() {
        SeekableByteChannel seekableByteChannel = this.f9822b;
        if (seekableByteChannel != null) {
            return seekableByteChannel.position();
        }
        return this.f9824d;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0013, code lost:
        if (r0 != null) goto L5;
     */
    @Override // java.nio.channels.SeekableByteChannel
    @GuardedBy("this")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized SeekableByteChannel position(long j2) {
        SeekableByteChannel seekableByteChannel = this.f9822b;
        if (seekableByteChannel == null) {
            if (j2 < 0) {
                throw new IllegalArgumentException("Position must be non-negative");
            }
            this.f9824d = j2;
            seekableByteChannel = this.f9821a;
        }
        seekableByteChannel.position(j2);
        return this;
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.ReadableByteChannel
    @GuardedBy("this")
    public synchronized int read(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() == 0) {
            return 0;
        }
        SeekableByteChannel seekableByteChannel = this.f9822b;
        if (seekableByteChannel != null) {
            return seekableByteChannel.read(byteBuffer);
        }
        if (this.f9821a == null) {
            this.f9821a = nextAttemptingChannel();
        }
        while (true) {
            try {
                int read = this.f9821a.read(byteBuffer);
                if (read == 0) {
                    return 0;
                }
                this.f9822b = this.f9821a;
                this.f9821a = null;
                return read;
            } catch (IOException unused) {
                this.f9821a = nextAttemptingChannel();
            }
            this.f9821a = nextAttemptingChannel();
        }
    }

    @Override // java.nio.channels.SeekableByteChannel
    @GuardedBy("this")
    public synchronized long size() {
        SeekableByteChannel seekableByteChannel;
        seekableByteChannel = this.f9822b;
        if (seekableByteChannel == null) {
            throw new IOException("Cannot determine size before first read()-call.");
        }
        return seekableByteChannel.size();
    }

    @Override // java.nio.channels.SeekableByteChannel
    public SeekableByteChannel truncate(long j2) {
        throw new NonWritableChannelException();
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        throw new NonWritableChannelException();
    }
}
