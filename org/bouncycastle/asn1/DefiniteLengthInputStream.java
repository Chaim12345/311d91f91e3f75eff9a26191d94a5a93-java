package org.bouncycastle.asn1;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes3.dex */
class DefiniteLengthInputStream extends LimitedInputStream {
    private static final byte[] EMPTY_BYTES = new byte[0];
    private final int _originalLength;
    private int _remaining;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefiniteLengthInputStream(InputStream inputStream, int i2, int i3) {
        super(inputStream, i3);
        if (i2 <= 0) {
            if (i2 < 0) {
                throw new IllegalArgumentException("negative lengths not allowed");
            }
            b(true);
        }
        this._originalLength = i2;
        this._remaining = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c() {
        return this._remaining;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(byte[] bArr) {
        int i2 = this._remaining;
        if (i2 != bArr.length) {
            throw new IllegalArgumentException("buffer length not right for data");
        }
        if (i2 == 0) {
            return;
        }
        int a2 = a();
        int i3 = this._remaining;
        if (i3 >= a2) {
            throw new IOException("corrupted stream - out of bounds length found: " + this._remaining + " >= " + a2);
        }
        int readFully = i3 - Streams.readFully(this.f12749a, bArr, 0, bArr.length);
        this._remaining = readFully;
        if (readFully == 0) {
            b(true);
            return;
        }
        throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] e() {
        if (this._remaining == 0) {
            return EMPTY_BYTES;
        }
        int a2 = a();
        int i2 = this._remaining;
        if (i2 >= a2) {
            throw new IOException("corrupted stream - out of bounds length found: " + this._remaining + " >= " + a2);
        }
        byte[] bArr = new byte[i2];
        int readFully = i2 - Streams.readFully(this.f12749a, bArr, 0, i2);
        this._remaining = readFully;
        if (readFully == 0) {
            b(true);
            return bArr;
        }
        throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
    }

    @Override // java.io.InputStream
    public int read() {
        if (this._remaining == 0) {
            return -1;
        }
        int read = this.f12749a.read();
        if (read >= 0) {
            int i2 = this._remaining - 1;
            this._remaining = i2;
            if (i2 == 0) {
                b(true);
            }
            return read;
        }
        throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        int i4 = this._remaining;
        if (i4 == 0) {
            return -1;
        }
        int read = this.f12749a.read(bArr, i2, Math.min(i3, i4));
        if (read >= 0) {
            int i5 = this._remaining - read;
            this._remaining = i5;
            if (i5 == 0) {
                b(true);
            }
            return read;
        }
        throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
    }
}
