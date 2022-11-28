package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes2.dex */
final class InputStreamDecrypter extends InputStream {
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    boolean f9811a = false;
    @GuardedBy("this")

    /* renamed from: b  reason: collision with root package name */
    InputStream f9812b = null;
    @GuardedBy("this")

    /* renamed from: c  reason: collision with root package name */
    InputStream f9813c;

    /* renamed from: d  reason: collision with root package name */
    PrimitiveSet f9814d;

    /* renamed from: e  reason: collision with root package name */
    byte[] f9815e;

    public InputStreamDecrypter(PrimitiveSet<StreamingAead> primitiveSet, InputStream inputStream, byte[] bArr) {
        this.f9814d = primitiveSet;
        if (inputStream.markSupported()) {
            this.f9813c = inputStream;
        } else {
            this.f9813c = new BufferedInputStream(inputStream);
        }
        this.f9813c.mark(Integer.MAX_VALUE);
        this.f9815e = (byte[]) bArr.clone();
    }

    @GuardedBy("this")
    private void disableRewinding() {
        this.f9813c.mark(0);
    }

    @GuardedBy("this")
    private void rewind() {
        this.f9813c.reset();
    }

    @Override // java.io.InputStream
    @GuardedBy("this")
    public synchronized int available() {
        InputStream inputStream = this.f9812b;
        if (inputStream == null) {
            return 0;
        }
        return inputStream.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    @GuardedBy("this")
    public synchronized void close() {
        this.f9813c.close();
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.InputStream
    @GuardedBy("this")
    public synchronized int read() {
        byte[] bArr = new byte[1];
        if (read(bArr) == 1) {
            return bArr[0];
        }
        return -1;
    }

    @Override // java.io.InputStream
    @GuardedBy("this")
    public synchronized int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    @GuardedBy("this")
    public synchronized int read(byte[] bArr, int i2, int i3) {
        if (i3 == 0) {
            return 0;
        }
        InputStream inputStream = this.f9812b;
        if (inputStream != null) {
            return inputStream.read(bArr, i2, i3);
        } else if (this.f9811a) {
            throw new IOException("No matching key found for the ciphertext in the stream.");
        } else {
            this.f9811a = true;
            for (PrimitiveSet.Entry entry : this.f9814d.getRawPrimitives()) {
                try {
                    InputStream newDecryptingStream = ((StreamingAead) entry.getPrimitive()).newDecryptingStream(this.f9813c, this.f9815e);
                    int read = newDecryptingStream.read(bArr, i2, i3);
                    if (read != 0) {
                        this.f9812b = newDecryptingStream;
                        disableRewinding();
                        return read;
                    }
                    throw new IOException("Could not read bytes from the ciphertext stream");
                } catch (IOException | GeneralSecurityException unused) {
                    rewind();
                }
            }
            throw new IOException("No matching key found for the ciphertext in the stream.");
        }
    }
}
