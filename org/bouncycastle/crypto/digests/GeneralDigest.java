package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public abstract class GeneralDigest implements ExtendedDigest, Memoable {
    private static final int BYTE_LENGTH = 64;
    private long byteCount;
    private final byte[] xBuf;
    private int xBufOff;

    /* JADX INFO: Access modifiers changed from: protected */
    public GeneralDigest() {
        this.xBuf = new byte[4];
        this.xBufOff = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GeneralDigest(GeneralDigest generalDigest) {
        this.xBuf = new byte[4];
        a(generalDigest);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GeneralDigest(byte[] bArr) {
        byte[] bArr2 = new byte[4];
        this.xBuf = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.xBufOff = Pack.bigEndianToInt(bArr, 4);
        this.byteCount = Pack.bigEndianToLong(bArr, 8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(GeneralDigest generalDigest) {
        byte[] bArr = generalDigest.xBuf;
        System.arraycopy(bArr, 0, this.xBuf, 0, bArr.length);
        this.xBufOff = generalDigest.xBufOff;
        this.byteCount = generalDigest.byteCount;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(byte[] bArr) {
        System.arraycopy(this.xBuf, 0, bArr, 0, this.xBufOff);
        Pack.intToBigEndian(this.xBufOff, bArr, 4);
        Pack.longToBigEndian(this.byteCount, bArr, 8);
    }

    protected abstract void c();

    protected abstract void d(long j2);

    protected abstract void e(byte[] bArr, int i2);

    public void finish() {
        long j2 = this.byteCount << 3;
        byte b2 = Byte.MIN_VALUE;
        while (true) {
            update(b2);
            if (this.xBufOff == 0) {
                d(j2);
                c();
                return;
            }
            b2 = 0;
        }
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 64;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.byteCount = 0L;
        this.xBufOff = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.xBuf;
            if (i2 >= bArr.length) {
                return;
            }
            bArr[i2] = 0;
            i2++;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b2) {
        byte[] bArr = this.xBuf;
        int i2 = this.xBufOff;
        int i3 = i2 + 1;
        this.xBufOff = i3;
        bArr[i2] = b2;
        if (i3 == bArr.length) {
            e(bArr, 0);
            this.xBufOff = 0;
        }
        this.byteCount++;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i2, int i3) {
        int i4 = 0;
        int max = Math.max(0, i3);
        if (this.xBufOff != 0) {
            int i5 = 0;
            while (true) {
                if (i5 >= max) {
                    i4 = i5;
                    break;
                }
                byte[] bArr2 = this.xBuf;
                int i6 = this.xBufOff;
                int i7 = i6 + 1;
                this.xBufOff = i7;
                int i8 = i5 + 1;
                bArr2[i6] = bArr[i5 + i2];
                if (i7 == 4) {
                    e(bArr2, 0);
                    this.xBufOff = 0;
                    i4 = i8;
                    break;
                }
                i5 = i8;
            }
        }
        int i9 = ((max - i4) & (-4)) + i4;
        while (i4 < i9) {
            e(bArr, i2 + i4);
            i4 += 4;
        }
        while (i4 < max) {
            byte[] bArr3 = this.xBuf;
            int i10 = this.xBufOff;
            this.xBufOff = i10 + 1;
            bArr3[i10] = bArr[i4 + i2];
            i4++;
        }
        this.byteCount += max;
    }
}
