package org.bouncycastle.oer;

import com.google.common.primitives.SignedBytes;
import java.io.OutputStream;
import java.math.BigInteger;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class BitBuilder {
    private static final byte[] bits = {Byte.MIN_VALUE, SignedBytes.MAX_POWER_OF_TWO, 32, 16, 8, 4, 2, 1};

    /* renamed from: a  reason: collision with root package name */
    byte[] f14370a = new byte[1];

    /* renamed from: b  reason: collision with root package name */
    int f14371b = 0;

    protected void finalize() {
        zero();
        super.finalize();
    }

    public void pad() {
        int i2 = this.f14371b;
        this.f14371b = i2 + (i2 % 8);
    }

    public int write(OutputStream outputStream) {
        int i2 = this.f14371b;
        int i3 = (i2 + (i2 % 8)) / 8;
        outputStream.write(this.f14370a, 0, i3);
        outputStream.flush();
        return i3;
    }

    public void write7BitBytes(int i2) {
        boolean z = false;
        for (int i3 = 4; i3 >= 0; i3--) {
            if (!z && ((-33554432) & i2) != 0) {
                z = true;
            }
            if (z) {
                writeBit(i3).writeBits(i2, 32, 7);
            }
            i2 <<= 7;
        }
    }

    public void write7BitBytes(BigInteger bigInteger) {
        int i2;
        int bitLength = (bigInteger.bitLength() + (bigInteger.bitLength() % 8)) / 8;
        BigInteger shiftLeft = BigInteger.valueOf(254L).shiftLeft(bitLength * 8);
        boolean z = false;
        while (bitLength >= 0) {
            if (!z && bigInteger.and(shiftLeft).compareTo(BigInteger.ZERO) != 0) {
                z = true;
            }
            if (z) {
                writeBit(bitLength).writeBits(bigInteger.and(shiftLeft).shiftRight(i2 - 8).intValue(), 8, 7);
            }
            bigInteger = bigInteger.shiftLeft(7);
            bitLength--;
        }
    }

    public int writeAndClear(OutputStream outputStream) {
        int i2 = this.f14371b;
        int i3 = (i2 + (i2 % 8)) / 8;
        outputStream.write(this.f14370a, 0, i3);
        outputStream.flush();
        zero();
        return i3;
    }

    public BitBuilder writeBit(int i2) {
        int i3 = this.f14371b;
        int i4 = i3 / 8;
        byte[] bArr = this.f14370a;
        if (i4 >= bArr.length) {
            byte[] bArr2 = new byte[bArr.length + 4];
            System.arraycopy(bArr, 0, bArr2, 0, i3 / 8);
            Arrays.clear(this.f14370a);
            this.f14370a = bArr2;
        }
        if (i2 == 0) {
            byte[] bArr3 = this.f14370a;
            int i5 = this.f14371b;
            int i6 = i5 / 8;
            bArr3[i6] = (byte) ((~bits[i5 % 8]) & bArr3[i6]);
        } else {
            byte[] bArr4 = this.f14370a;
            int i7 = this.f14371b;
            int i8 = i7 / 8;
            bArr4[i8] = (byte) (bits[i7 % 8] | bArr4[i8]);
        }
        this.f14371b++;
        return this;
    }

    public BitBuilder writeBits(long j2, int i2) {
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            writeBit(((1 << i3) & j2) > 0 ? 1 : 0);
        }
        return this;
    }

    public BitBuilder writeBits(long j2, int i2, int i3) {
        for (int i4 = i2 - 1; i4 >= i2 - i3; i4--) {
            writeBit(((1 << i4) & j2) != 0 ? 1 : 0);
        }
        return this;
    }

    public void zero() {
        Arrays.clear(this.f14370a);
        this.f14371b = 0;
    }
}
