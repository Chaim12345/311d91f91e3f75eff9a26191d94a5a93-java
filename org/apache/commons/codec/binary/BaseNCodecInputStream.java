package org.apache.commons.codec.binary;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import org.apache.commons.codec.binary.BaseNCodec;
/* loaded from: classes3.dex */
public class BaseNCodecInputStream extends FilterInputStream {
    private final BaseNCodec baseNCodec;
    private final BaseNCodec.Context context;
    private final boolean doEncode;
    private final byte[] singleByte;

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseNCodecInputStream(InputStream inputStream, BaseNCodec baseNCodec, boolean z) {
        super(inputStream);
        this.singleByte = new byte[1];
        this.context = new BaseNCodec.Context();
        this.doEncode = z;
        this.baseNCodec = baseNCodec;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() {
        return !this.context.eof ? 1 : 0;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i2) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        int read = read(this.singleByte, 0, 1);
        while (read == 0) {
            read = read(this.singleByte, 0, 1);
        }
        if (read > 0) {
            byte b2 = this.singleByte[0];
            return b2 < 0 ? b2 + 256 : b2;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        Objects.requireNonNull(bArr);
        if (i2 < 0 || i3 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 > bArr.length || i2 + i3 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        if (i3 == 0) {
            return 0;
        }
        int i4 = 0;
        while (i4 == 0) {
            if (!this.baseNCodec.hasData(this.context)) {
                byte[] bArr2 = new byte[this.doEncode ? 4096 : 8192];
                int read = ((FilterInputStream) this).in.read(bArr2);
                if (this.doEncode) {
                    this.baseNCodec.encode(bArr2, 0, read, this.context);
                } else {
                    this.baseNCodec.decode(bArr2, 0, read, this.context);
                }
            }
            i4 = this.baseNCodec.readResults(bArr, i2, i3, this.context);
        }
        return i4;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() {
        throw new IOException("mark/reset not supported");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j2) {
        int read;
        if (j2 < 0) {
            throw new IllegalArgumentException("Negative skip length: " + j2);
        }
        byte[] bArr = new byte[512];
        long j3 = j2;
        while (j3 > 0 && (read = read(bArr, 0, (int) Math.min(512, j3))) != -1) {
            j3 -= read;
        }
        return j2 - j3;
    }
}
