package org.bouncycastle.crypto.fpe;

import java.math.BigInteger;
import kotlin.UShort;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
class SP80038G {

    /* renamed from: a  reason: collision with root package name */
    protected static final double f13410a = Math.log(2.0d);

    /* renamed from: b  reason: collision with root package name */
    protected static final double f13411b = Math.pow(2.0d, 96.0d);

    protected static BigInteger A(byte[] bArr, int i2, int i3) {
        return new BigInteger(1, Arrays.copyOfRange(bArr, i2, i3 + i2));
    }

    protected static byte[] B(BlockCipher blockCipher, byte[] bArr) {
        if (bArr.length % 16 == 0) {
            int length = bArr.length / 16;
            byte[] bArr2 = new byte[16];
            for (int i2 = 0; i2 < length; i2++) {
                F(bArr, i2 * 16, bArr2, 0, 16);
                blockCipher.processBlock(bArr2, 0, bArr2, 0);
            }
            return bArr2;
        }
        throw new IllegalArgumentException();
    }

    protected static void C(byte[] bArr) {
        int length = bArr.length / 2;
        int length2 = bArr.length - 1;
        for (int i2 = 0; i2 < length; i2++) {
            byte b2 = bArr[i2];
            int i3 = length2 - i2;
            bArr[i2] = bArr[i3];
            bArr[i3] = b2;
        }
    }

    protected static void D(short[] sArr) {
        int length = sArr.length / 2;
        int length2 = sArr.length - 1;
        for (int i2 = 0; i2 < length; i2++) {
            short s2 = sArr[i2];
            int i3 = length2 - i2;
            sArr[i2] = sArr[i3];
            sArr[i3] = s2;
        }
    }

    protected static void E(BigInteger bigInteger, BigInteger bigInteger2, int i2, short[] sArr, int i3) {
        if (bigInteger2.signum() < 0) {
            throw new IllegalArgumentException();
        }
        for (int i4 = 1; i4 <= i2; i4++) {
            BigInteger[] divideAndRemainder = bigInteger2.divideAndRemainder(bigInteger);
            sArr[(i3 + i2) - i4] = (short) divideAndRemainder[1].intValue();
            bigInteger2 = divideAndRemainder[0];
        }
        if (bigInteger2.signum() != 0) {
            throw new IllegalArgumentException();
        }
    }

    protected static void F(byte[] bArr, int i2, byte[] bArr2, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i3 + i5;
            bArr2[i6] = (byte) (bArr2[i6] ^ bArr[i2 + i5]);
        }
    }

    protected static BigInteger[] a(BigInteger bigInteger, int i2, int i3) {
        BigInteger[] bigIntegerArr = {bigInteger.pow(i2), bigIntegerArr[0]};
        if (i3 != i2) {
            bigIntegerArr[1] = bigIntegerArr[1].multiply(bigInteger);
        }
        return bigIntegerArr;
    }

    protected static byte[] b(int i2, byte b2, int i3, int i4) {
        byte[] bArr = {1, 2, 1, 0, (byte) (i2 >> 8), (byte) i2, 10, b2};
        Pack.intToBigEndian(i3, bArr, 8);
        Pack.intToBigEndian(i4, bArr, 12);
        return bArr;
    }

    protected static byte[] c(byte[] bArr) {
        return new byte[]{bArr[0], bArr[1], bArr[2], (byte) (bArr[3] & 240), bArr[4], bArr[5], bArr[6], (byte) (bArr[3] << 4)};
    }

    private static void checkLength(boolean z, int i2, int i3) {
        int floor;
        if (i3 >= 2) {
            double d2 = i2;
            if (Math.pow(d2, i3) >= 1000000.0d) {
                if (z || i3 <= (floor = ((int) Math.floor(Math.log(f13411b) / Math.log(d2))) * 2)) {
                    return;
                }
                throw new IllegalArgumentException("maximum input length is " + floor);
            }
        }
        throw new IllegalArgumentException("input too short");
    }

    protected static BigInteger d(BlockCipher blockCipher, BigInteger bigInteger, byte[] bArr, int i2, int i3, int i4, byte[] bArr2, short[] sArr) {
        int length = bArr.length;
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(z(bigInteger, sArr));
        int i5 = ((-(length + i2 + 1)) & 15) + length;
        int i6 = i5 + 1 + i2;
        byte[] bArr3 = new byte[i6];
        System.arraycopy(bArr, 0, bArr3, 0, length);
        bArr3[i5] = (byte) i4;
        System.arraycopy(asUnsignedByteArray, 0, bArr3, i6 - asUnsignedByteArray.length, asUnsignedByteArray.length);
        byte[] B = B(blockCipher, Arrays.concatenate(bArr2, bArr3));
        if (i3 > 16) {
            int i7 = ((i3 + 16) - 1) / 16;
            byte[] bArr4 = new byte[i7 * 16];
            System.arraycopy(B, 0, bArr4, 0, 16);
            byte[] bArr5 = new byte[4];
            for (int i8 = 1; i8 < i7; i8++) {
                int i9 = i8 * 16;
                System.arraycopy(B, 0, bArr4, i9, 16);
                Pack.intToBigEndian(i8, bArr5, 0);
                F(bArr5, 0, bArr4, (i9 + 16) - 4, 4);
                blockCipher.processBlock(bArr4, i9, bArr4, i9);
            }
            B = bArr4;
        }
        return A(B, 0, i3);
    }

    private static short[] decFF3_1(BlockCipher blockCipher, int i2, byte[] bArr, int i3, int i4, int i5, short[] sArr, short[] sArr2) {
        BigInteger valueOf = BigInteger.valueOf(i2);
        int i6 = i5;
        BigInteger[] a2 = a(valueOf, i4, i6);
        D(sArr);
        D(sArr2);
        short[] sArr3 = sArr;
        short[] sArr4 = sArr2;
        int i7 = 7;
        while (i7 >= 0) {
            int i8 = i3 - i6;
            int i9 = i7 & 1;
            E(valueOf, z(valueOf, sArr4).subtract(e(blockCipher, valueOf, bArr, 4 - (i9 * 4), i7, sArr3)).mod(a2[1 - i9]), i8, sArr4, 0);
            i7--;
            i6 = i8;
            short[] sArr5 = sArr4;
            sArr4 = sArr3;
            sArr3 = sArr5;
        }
        D(sArr3);
        D(sArr4);
        return Arrays.concatenate(sArr3, sArr4);
    }

    protected static BigInteger e(BlockCipher blockCipher, BigInteger bigInteger, byte[] bArr, int i2, int i3, short[] sArr) {
        byte[] bArr2 = new byte[16];
        Pack.intToBigEndian(i3, bArr2, 0);
        F(bArr, i2, bArr2, 0, 4);
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(z(bigInteger, sArr));
        if (16 - asUnsignedByteArray.length >= 4) {
            System.arraycopy(asUnsignedByteArray, 0, bArr2, 16 - asUnsignedByteArray.length, asUnsignedByteArray.length);
            C(bArr2);
            blockCipher.processBlock(bArr2, 0, bArr2, 0);
            C(bArr2);
            return A(bArr2, 0, 16);
        }
        throw new IllegalStateException("input out of range");
    }

    private static short[] encFF1(BlockCipher blockCipher, int i2, byte[] bArr, int i3, int i4, int i5, short[] sArr, short[] sArr2) {
        int length = bArr.length;
        int ceil = (((int) Math.ceil((Math.log(i2) * i5) / f13410a)) + 7) / 8;
        int i6 = (((ceil + 3) / 4) * 4) + 4;
        byte[] b2 = b(i2, (byte) i4, i3, length);
        BigInteger valueOf = BigInteger.valueOf(i2);
        BigInteger[] a2 = a(valueOf, i4, i5);
        short[] sArr3 = sArr;
        short[] sArr4 = sArr2;
        int i7 = i5;
        int i8 = 0;
        while (i8 < 10) {
            int i9 = i8;
            short[] sArr5 = sArr3;
            sArr3 = sArr4;
            BigInteger d2 = d(blockCipher, valueOf, bArr, ceil, i6, i8, b2, sArr3);
            int i10 = i3 - i7;
            E(valueOf, z(valueOf, sArr5).add(d2).mod(a2[i9 & 1]), i10, sArr5, 0);
            i8 = i9 + 1;
            i7 = i10;
            sArr4 = sArr5;
        }
        return Arrays.concatenate(sArr3, sArr4);
    }

    private static short[] encFF3_1(BlockCipher blockCipher, int i2, byte[] bArr, int i3, int i4, int i5, short[] sArr, short[] sArr2) {
        BigInteger valueOf = BigInteger.valueOf(i2);
        int i6 = i4;
        BigInteger[] a2 = a(valueOf, i6, i5);
        D(sArr);
        D(sArr2);
        short[] sArr3 = sArr;
        short[] sArr4 = sArr2;
        int i7 = 0;
        while (i7 < 8) {
            i6 = i3 - i6;
            int i8 = i7 & 1;
            E(valueOf, z(valueOf, sArr3).add(e(blockCipher, valueOf, bArr, 4 - (i8 * 4), i7, sArr4)).mod(a2[1 - i8]), i6, sArr3, 0);
            i7++;
            short[] sArr5 = sArr4;
            sArr4 = sArr3;
            sArr3 = sArr5;
        }
        D(sArr3);
        D(sArr4);
        return Arrays.concatenate(sArr3, sArr4);
    }

    protected static void f(BlockCipher blockCipher, boolean z, int i2, byte[] bArr, int i3, int i4) {
        h(blockCipher);
        if (i2 < 2 || i2 > 256) {
            throw new IllegalArgumentException();
        }
        i(z, i2, bArr, i3, i4);
    }

    protected static void g(BlockCipher blockCipher, boolean z, int i2, short[] sArr, int i3, int i4) {
        h(blockCipher);
        if (i2 < 2 || i2 > 65536) {
            throw new IllegalArgumentException();
        }
        j(z, i2, sArr, i3, i4);
    }

    protected static void h(BlockCipher blockCipher) {
        if (16 != blockCipher.getBlockSize()) {
            throw new IllegalArgumentException();
        }
    }

    protected static void i(boolean z, int i2, byte[] bArr, int i3, int i4) {
        checkLength(z, i2, i4);
        for (int i5 = 0; i5 < i4; i5++) {
            if ((bArr[i3 + i5] & 255) >= i2) {
                throw new IllegalArgumentException("input data outside of radix");
            }
        }
    }

    protected static void j(boolean z, int i2, short[] sArr, int i3, int i4) {
        checkLength(z, i2, i4);
        for (int i5 = 0; i5 < i4; i5++) {
            if ((sArr[i3 + i5] & UShort.MAX_VALUE) >= i2) {
                throw new IllegalArgumentException("input data outside of radix");
            }
        }
    }

    static short[] k(BlockCipher blockCipher, int i2, byte[] bArr, int i3, int i4, int i5, short[] sArr, short[] sArr2) {
        int length = bArr.length;
        int ceil = (((int) Math.ceil((Math.log(i2) * i5) / f13410a)) + 7) / 8;
        int i6 = (((ceil + 3) / 4) * 4) + 4;
        byte[] b2 = b(i2, (byte) i4, i3, length);
        BigInteger valueOf = BigInteger.valueOf(i2);
        BigInteger[] a2 = a(valueOf, i4, i5);
        short[] sArr3 = sArr;
        short[] sArr4 = sArr2;
        int i7 = i4;
        int i8 = 9;
        while (i8 >= 0) {
            short[] sArr5 = sArr4;
            i7 = i3 - i7;
            E(valueOf, z(valueOf, sArr5).subtract(d(blockCipher, valueOf, bArr, ceil, i6, i8, b2, sArr3)).mod(a2[i8 & 1]), i7, sArr5, 0);
            i8--;
            sArr4 = sArr3;
            sArr3 = sArr5;
        }
        return Arrays.concatenate(sArr3, sArr4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] l(BlockCipher blockCipher, int i2, byte[] bArr, byte[] bArr2, int i3, int i4) {
        f(blockCipher, true, i2, bArr2, i3, i4);
        int i5 = i4 / 2;
        int i6 = i4 - i5;
        return toByte(k(blockCipher, i2, bArr, i4, i5, i6, toShort(bArr2, i3, i5), toShort(bArr2, i3 + i5, i6)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short[] m(BlockCipher blockCipher, int i2, byte[] bArr, short[] sArr, int i3, int i4) {
        g(blockCipher, true, i2, sArr, i3, i4);
        int i5 = i4 / 2;
        int i6 = i4 - i5;
        short[] sArr2 = new short[i5];
        short[] sArr3 = new short[i6];
        System.arraycopy(sArr, i3, sArr2, 0, i5);
        System.arraycopy(sArr, i3 + i5, sArr3, 0, i6);
        return k(blockCipher, i2, bArr, i4, i5, i6, sArr2, sArr3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] n(BlockCipher blockCipher, int i2, byte[] bArr, byte[] bArr2, int i3, int i4) {
        f(blockCipher, false, i2, bArr2, i3, i4);
        if (bArr.length == 7) {
            return v(blockCipher, i2, c(bArr), bArr2, i3, i4);
        }
        throw new IllegalArgumentException("tweak should be 56 bits");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short[] o(BlockCipher blockCipher, int i2, byte[] bArr, short[] sArr, int i3, int i4) {
        g(blockCipher, false, i2, sArr, i3, i4);
        if (bArr.length == 7) {
            return w(blockCipher, i2, c(bArr), sArr, i3, i4);
        }
        throw new IllegalArgumentException("tweak should be 56 bits");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] p(BlockCipher blockCipher, int i2, byte[] bArr, byte[] bArr2, int i3, int i4) {
        f(blockCipher, true, i2, bArr2, i3, i4);
        int i5 = i4 / 2;
        int i6 = i4 - i5;
        return toByte(encFF1(blockCipher, i2, bArr, i4, i5, i6, toShort(bArr2, i3, i5), toShort(bArr2, i3 + i5, i6)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short[] q(BlockCipher blockCipher, int i2, byte[] bArr, short[] sArr, int i3, int i4) {
        g(blockCipher, true, i2, sArr, i3, i4);
        int i5 = i4 / 2;
        int i6 = i4 - i5;
        short[] sArr2 = new short[i5];
        short[] sArr3 = new short[i6];
        System.arraycopy(sArr, i3, sArr2, 0, i5);
        System.arraycopy(sArr, i3 + i5, sArr3, 0, i6);
        return encFF1(blockCipher, i2, bArr, i4, i5, i6, sArr2, sArr3);
    }

    static byte[] r(BlockCipher blockCipher, int i2, byte[] bArr, byte[] bArr2, int i3, int i4) {
        f(blockCipher, false, i2, bArr2, i3, i4);
        if (bArr.length == 8) {
            return x(blockCipher, i2, bArr, bArr2, i3, i4);
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] s(BlockCipher blockCipher, int i2, byte[] bArr, byte[] bArr2, int i3, int i4) {
        f(blockCipher, false, i2, bArr2, i3, i4);
        if (bArr.length == 7) {
            return r(blockCipher, i2, c(bArr), bArr2, i3, i4);
        }
        throw new IllegalArgumentException("tweak should be 56 bits");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short[] t(BlockCipher blockCipher, int i2, byte[] bArr, short[] sArr, int i3, int i4) {
        g(blockCipher, false, i2, sArr, i3, i4);
        if (bArr.length == 7) {
            return u(blockCipher, i2, c(bArr), sArr, i3, i4);
        }
        throw new IllegalArgumentException("tweak should be 56 bits");
    }

    private static byte[] toByte(short[] sArr) {
        int length = sArr.length;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 != length; i2++) {
            bArr[i2] = (byte) sArr[i2];
        }
        return bArr;
    }

    private static short[] toShort(byte[] bArr, int i2, int i3) {
        short[] sArr = new short[i3];
        for (int i4 = 0; i4 != i3; i4++) {
            sArr[i4] = (short) (bArr[i2 + i4] & 255);
        }
        return sArr;
    }

    static short[] u(BlockCipher blockCipher, int i2, byte[] bArr, short[] sArr, int i3, int i4) {
        g(blockCipher, false, i2, sArr, i3, i4);
        if (bArr.length == 8) {
            return y(blockCipher, i2, bArr, sArr, i3, i4);
        }
        throw new IllegalArgumentException();
    }

    protected static byte[] v(BlockCipher blockCipher, int i2, byte[] bArr, byte[] bArr2, int i3, int i4) {
        int i5 = i4 / 2;
        int i6 = i4 - i5;
        return toByte(decFF3_1(blockCipher, i2, bArr, i4, i5, i6, toShort(bArr2, i3, i6), toShort(bArr2, i3 + i6, i5)));
    }

    protected static short[] w(BlockCipher blockCipher, int i2, byte[] bArr, short[] sArr, int i3, int i4) {
        int i5 = i4 / 2;
        int i6 = i4 - i5;
        short[] sArr2 = new short[i6];
        short[] sArr3 = new short[i5];
        System.arraycopy(sArr, i3, sArr2, 0, i6);
        System.arraycopy(sArr, i3 + i6, sArr3, 0, i5);
        return decFF3_1(blockCipher, i2, bArr, i4, i5, i6, sArr2, sArr3);
    }

    protected static byte[] x(BlockCipher blockCipher, int i2, byte[] bArr, byte[] bArr2, int i3, int i4) {
        int i5 = i4 / 2;
        int i6 = i4 - i5;
        return toByte(encFF3_1(blockCipher, i2, bArr, i4, i5, i6, toShort(bArr2, i3, i6), toShort(bArr2, i3 + i6, i5)));
    }

    protected static short[] y(BlockCipher blockCipher, int i2, byte[] bArr, short[] sArr, int i3, int i4) {
        int i5 = i4 / 2;
        int i6 = i4 - i5;
        short[] sArr2 = new short[i6];
        short[] sArr3 = new short[i5];
        System.arraycopy(sArr, i3, sArr2, 0, i6);
        System.arraycopy(sArr, i3 + i6, sArr3, 0, i5);
        return encFF3_1(blockCipher, i2, bArr, i4, i5, i6, sArr2, sArr3);
    }

    protected static BigInteger z(BigInteger bigInteger, short[] sArr) {
        BigInteger bigInteger2 = BigIntegers.ZERO;
        for (short s2 : sArr) {
            bigInteger2 = bigInteger2.multiply(bigInteger).add(BigInteger.valueOf(s2 & UShort.MAX_VALUE));
        }
        return bigInteger2;
    }
}
