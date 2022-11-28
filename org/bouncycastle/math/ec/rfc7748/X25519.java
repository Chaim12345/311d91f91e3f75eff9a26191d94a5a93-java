package org.bouncycastle.math.ec.rfc7748;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.security.SecureRandom;
import org.bouncycastle.math.ec.rfc8032.Ed25519;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public abstract class X25519 {
    private static final int C_A = 486662;
    private static final int C_A24 = 121666;
    public static final int POINT_SIZE = 32;
    public static final int SCALAR_SIZE = 32;

    /* loaded from: classes4.dex */
    private static class F extends X25519Field {
        private F() {
        }
    }

    /* loaded from: classes4.dex */
    public static class Friend {
        private static final Friend INSTANCE = new Friend();

        private Friend() {
        }
    }

    public static boolean calculateAgreement(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4) {
        scalarMult(bArr, i2, bArr2, i3, bArr3, i4);
        return !Arrays.areAllZeroes(bArr3, i4, 32);
    }

    private static int decode32(byte[] bArr, int i2) {
        int i3 = i2 + 1;
        int i4 = i3 + 1;
        return (bArr[i4 + 1] << Ascii.CAN) | (bArr[i2] & 255) | ((bArr[i3] & 255) << 8) | ((bArr[i4] & 255) << 16);
    }

    private static void decodeScalar(byte[] bArr, int i2, int[] iArr) {
        for (int i3 = 0; i3 < 8; i3++) {
            iArr[i3] = decode32(bArr, (i3 * 4) + i2);
        }
        iArr[0] = iArr[0] & (-8);
        iArr[7] = iArr[7] & Integer.MAX_VALUE;
        iArr[7] = iArr[7] | 1073741824;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
        bArr[0] = (byte) (bArr[0] & 248);
        bArr[31] = (byte) (bArr[31] & Byte.MAX_VALUE);
        bArr[31] = (byte) (bArr[31] | SignedBytes.MAX_POWER_OF_TWO);
    }

    public static void generatePublicKey(byte[] bArr, int i2, byte[] bArr2, int i3) {
        scalarMultBase(bArr, i2, bArr2, i3);
    }

    private static void pointDouble(int[] iArr, int[] iArr2) {
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        X25519Field.apm(iArr, iArr2, create, create2);
        X25519Field.sqr(create, create);
        X25519Field.sqr(create2, create2);
        X25519Field.mul(create, create2, iArr);
        X25519Field.sub(create, create2, create);
        X25519Field.mul(create, (int) C_A24, iArr2);
        X25519Field.add(iArr2, create2, iArr2);
        X25519Field.mul(iArr2, create, iArr2);
    }

    public static void precompute() {
        Ed25519.precompute();
    }

    public static void scalarMult(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4) {
        int[] iArr = new int[8];
        decodeScalar(bArr, i2, iArr);
        int[] create = X25519Field.create();
        X25519Field.decode(bArr2, i3, create);
        int[] create2 = X25519Field.create();
        X25519Field.copy(create, 0, create2, 0);
        int[] create3 = X25519Field.create();
        create3[0] = 1;
        int[] create4 = X25519Field.create();
        create4[0] = 1;
        int[] create5 = X25519Field.create();
        int[] create6 = X25519Field.create();
        int[] create7 = X25519Field.create();
        int i5 = 254;
        int i6 = 1;
        while (true) {
            X25519Field.apm(create4, create5, create6, create4);
            X25519Field.apm(create2, create3, create5, create2);
            X25519Field.mul(create6, create2, create6);
            X25519Field.mul(create4, create5, create4);
            X25519Field.sqr(create5, create5);
            X25519Field.sqr(create2, create2);
            X25519Field.sub(create5, create2, create7);
            X25519Field.mul(create7, (int) C_A24, create3);
            X25519Field.add(create3, create2, create3);
            X25519Field.mul(create3, create7, create3);
            X25519Field.mul(create2, create5, create2);
            X25519Field.apm(create6, create4, create4, create5);
            X25519Field.sqr(create4, create4);
            X25519Field.sqr(create5, create5);
            X25519Field.mul(create5, create, create5);
            i5--;
            int i7 = (iArr[i5 >>> 5] >>> (i5 & 31)) & 1;
            int i8 = i6 ^ i7;
            X25519Field.cswap(i8, create2, create4);
            X25519Field.cswap(i8, create3, create5);
            if (i5 < 3) {
                break;
            }
            i6 = i7;
        }
        for (int i9 = 0; i9 < 3; i9++) {
            pointDouble(create2, create3);
        }
        X25519Field.inv(create3, create3);
        X25519Field.mul(create2, create3, create2);
        X25519Field.normalize(create2);
        X25519Field.encode(create2, bArr3, i4);
    }

    public static void scalarMultBase(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int[] create = X25519Field.create();
        int[] create2 = X25519Field.create();
        Ed25519.scalarMultBaseYZ(Friend.INSTANCE, bArr, i2, create, create2);
        X25519Field.apm(create2, create, create, create2);
        X25519Field.inv(create2, create2);
        X25519Field.mul(create, create2, create);
        X25519Field.normalize(create);
        X25519Field.encode(create, bArr2, i3);
    }
}
