package com.google.crypto.tink.subtle;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class StreamingAeadEncryptingStream extends FilterOutputStream {

    /* renamed from: a  reason: collision with root package name */
    ByteBuffer f9866a;

    /* renamed from: b  reason: collision with root package name */
    ByteBuffer f9867b;

    /* renamed from: c  reason: collision with root package name */
    boolean f9868c;
    private StreamSegmentEncrypter encrypter;
    private int plaintextSegmentSize;

    public StreamingAeadEncryptingStream(NonceBasedStreamingAead nonceBasedStreamingAead, OutputStream outputStream, byte[] bArr) {
        super(outputStream);
        this.encrypter = nonceBasedStreamingAead.newStreamSegmentEncrypter(bArr);
        int plaintextSegmentSize = nonceBasedStreamingAead.getPlaintextSegmentSize();
        this.plaintextSegmentSize = plaintextSegmentSize;
        this.f9866a = ByteBuffer.allocate(plaintextSegmentSize);
        this.f9867b = ByteBuffer.allocate(nonceBasedStreamingAead.getCiphertextSegmentSize());
        this.f9866a.limit(this.plaintextSegmentSize - nonceBasedStreamingAead.getCiphertextOffset());
        ByteBuffer header = this.encrypter.getHeader();
        byte[] bArr2 = new byte[header.remaining()];
        header.get(bArr2);
        ((FilterOutputStream) this).out.write(bArr2);
        this.f9868c = true;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.f9868c) {
            try {
                this.f9866a.flip();
                this.f9867b.clear();
                this.encrypter.encryptSegment(this.f9866a, true, this.f9867b);
                this.f9867b.flip();
                ((FilterOutputStream) this).out.write(this.f9867b.array(), this.f9867b.position(), this.f9867b.remaining());
                this.f9868c = false;
                super.close();
            } catch (GeneralSecurityException e2) {
                throw new IOException("ptBuffer.remaining():" + this.f9866a.remaining() + " ctBuffer.remaining():" + this.f9867b.remaining(), e2);
            }
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i2) {
        write(new byte[]{(byte) i2});
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(byte[] bArr, int i2, int i3) {
        if (!this.f9868c) {
            throw new IOException("Trying to write to closed stream");
        }
        while (i3 > this.f9866a.remaining()) {
            int remaining = this.f9866a.remaining();
            ByteBuffer wrap = ByteBuffer.wrap(bArr, i2, remaining);
            i2 += remaining;
            i3 -= remaining;
            try {
                this.f9866a.flip();
                this.f9867b.clear();
                this.encrypter.encryptSegment(this.f9866a, wrap, false, this.f9867b);
                this.f9867b.flip();
                ((FilterOutputStream) this).out.write(this.f9867b.array(), this.f9867b.position(), this.f9867b.remaining());
                this.f9866a.clear();
                this.f9866a.limit(this.plaintextSegmentSize);
            } catch (GeneralSecurityException e2) {
                throw new IOException(e2);
            }
        }
        this.f9866a.put(bArr, i2, i3);
    }
}
