package org.bouncycastle.asn1;

import java.io.EOFException;
import java.io.InputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class IndefiniteLengthInputStream extends LimitedInputStream {
    private int _b1;
    private int _b2;
    private boolean _eofOn00;
    private boolean _eofReached;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IndefiniteLengthInputStream(InputStream inputStream, int i2) {
        super(inputStream, i2);
        this._eofReached = false;
        this._eofOn00 = true;
        this._b1 = inputStream.read();
        int read = inputStream.read();
        this._b2 = read;
        if (read < 0) {
            throw new EOFException();
        }
        checkForEof();
    }

    private boolean checkForEof() {
        if (!this._eofReached && this._eofOn00 && this._b1 == 0 && this._b2 == 0) {
            this._eofReached = true;
            b(true);
        }
        return this._eofReached;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(boolean z) {
        this._eofOn00 = z;
        checkForEof();
    }

    @Override // java.io.InputStream
    public int read() {
        if (checkForEof()) {
            return -1;
        }
        int read = this.f12749a.read();
        if (read >= 0) {
            int i2 = this._b1;
            this._b1 = this._b2;
            this._b2 = read;
            return i2;
        }
        throw new EOFException();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        if (this._eofOn00 || i3 < 3) {
            return super.read(bArr, i2, i3);
        }
        if (this._eofReached) {
            return -1;
        }
        int read = this.f12749a.read(bArr, i2 + 2, i3 - 2);
        if (read >= 0) {
            bArr[i2] = (byte) this._b1;
            bArr[i2 + 1] = (byte) this._b2;
            this._b1 = this.f12749a.read();
            int read2 = this.f12749a.read();
            this._b2 = read2;
            if (read2 >= 0) {
                return read + 2;
            }
            throw new EOFException();
        }
        throw new EOFException();
    }
}
