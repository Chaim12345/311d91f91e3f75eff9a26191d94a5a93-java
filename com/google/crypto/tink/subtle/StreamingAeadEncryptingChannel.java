package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class StreamingAeadEncryptingChannel implements WritableByteChannel {

    /* renamed from: a  reason: collision with root package name */
    ByteBuffer f9863a;

    /* renamed from: b  reason: collision with root package name */
    ByteBuffer f9864b;

    /* renamed from: c  reason: collision with root package name */
    boolean f9865c = true;
    private WritableByteChannel ciphertextChannel;
    private StreamSegmentEncrypter encrypter;
    private int plaintextSegmentSize;

    public StreamingAeadEncryptingChannel(NonceBasedStreamingAead nonceBasedStreamingAead, WritableByteChannel writableByteChannel, byte[] bArr) {
        this.ciphertextChannel = writableByteChannel;
        this.encrypter = nonceBasedStreamingAead.newStreamSegmentEncrypter(bArr);
        int plaintextSegmentSize = nonceBasedStreamingAead.getPlaintextSegmentSize();
        this.plaintextSegmentSize = plaintextSegmentSize;
        ByteBuffer allocate = ByteBuffer.allocate(plaintextSegmentSize);
        this.f9863a = allocate;
        allocate.limit(this.plaintextSegmentSize - nonceBasedStreamingAead.getCiphertextOffset());
        ByteBuffer allocate2 = ByteBuffer.allocate(nonceBasedStreamingAead.getCiphertextSegmentSize());
        this.f9864b = allocate2;
        allocate2.put(this.encrypter.getHeader());
        this.f9864b.flip();
        writableByteChannel.write(this.f9864b);
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.f9865c) {
            while (this.f9864b.remaining() > 0) {
                if (this.ciphertextChannel.write(this.f9864b) <= 0) {
                    throw new IOException("Failed to write ciphertext before closing");
                }
            }
            try {
                this.f9864b.clear();
                this.f9863a.flip();
                this.encrypter.encryptSegment(this.f9863a, true, this.f9864b);
                this.f9864b.flip();
                while (this.f9864b.remaining() > 0) {
                    if (this.ciphertextChannel.write(this.f9864b) <= 0) {
                        throw new IOException("Failed to write ciphertext before closing");
                    }
                }
                this.ciphertextChannel.close();
                this.f9865c = false;
            } catch (GeneralSecurityException e2) {
                throw new IOException(e2);
            }
        }
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return this.f9865c;
    }

    @Override // java.nio.channels.WritableByteChannel
    public synchronized int write(ByteBuffer byteBuffer) {
        int position;
        if (!this.f9865c) {
            throw new ClosedChannelException();
        }
        if (this.f9864b.remaining() > 0) {
            this.ciphertextChannel.write(this.f9864b);
        }
        position = byteBuffer.position();
        while (true) {
            if (byteBuffer.remaining() <= this.f9863a.remaining()) {
                this.f9863a.put(byteBuffer);
                break;
            } else if (this.f9864b.remaining() > 0) {
                break;
            } else {
                int remaining = this.f9863a.remaining();
                ByteBuffer slice = byteBuffer.slice();
                slice.limit(remaining);
                byteBuffer.position(byteBuffer.position() + remaining);
                try {
                    this.f9863a.flip();
                    this.f9864b.clear();
                    if (slice.remaining() != 0) {
                        this.encrypter.encryptSegment(this.f9863a, slice, false, this.f9864b);
                    } else {
                        this.encrypter.encryptSegment(this.f9863a, false, this.f9864b);
                    }
                    this.f9864b.flip();
                    this.ciphertextChannel.write(this.f9864b);
                    this.f9863a.clear();
                    this.f9863a.limit(this.plaintextSegmentSize);
                } catch (GeneralSecurityException e2) {
                    throw new IOException(e2);
                }
            }
        }
        return byteBuffer.position() - position;
    }
}
