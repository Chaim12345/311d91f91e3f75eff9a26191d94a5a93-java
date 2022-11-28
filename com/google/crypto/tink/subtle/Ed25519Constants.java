package com.google.crypto.tink.subtle;

import com.google.crypto.tink.subtle.Ed25519;
import java.lang.reflect.Array;
import java.math.BigInteger;
/* loaded from: classes2.dex */
final class Ed25519Constants {
    private static final BigInteger D2_BI;
    private static final BigInteger D_BI;
    private static final BigInteger P_BI;
    private static final BigInteger SQRTM1_BI;

    /* renamed from: a  reason: collision with root package name */
    static final long[] f9849a;

    /* renamed from: b  reason: collision with root package name */
    static final long[] f9850b;

    /* renamed from: c  reason: collision with root package name */
    static final long[] f9851c;

    /* renamed from: d  reason: collision with root package name */
    static final Ed25519.CachedXYT[][] f9852d;

    /* renamed from: e  reason: collision with root package name */
    static final Ed25519.CachedXYT[] f9853e;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class Point {
        private BigInteger x;
        private BigInteger y;

        private Point() {
        }
    }

    static {
        BigInteger subtract = BigInteger.valueOf(2L).pow(255).subtract(BigInteger.valueOf(19L));
        P_BI = subtract;
        BigInteger mod = BigInteger.valueOf(-121665L).multiply(BigInteger.valueOf(121666L).modInverse(subtract)).mod(subtract);
        D_BI = mod;
        BigInteger mod2 = BigInteger.valueOf(2L).multiply(mod).mod(subtract);
        D2_BI = mod2;
        BigInteger modPow = BigInteger.valueOf(2L).modPow(subtract.subtract(BigInteger.ONE).divide(BigInteger.valueOf(4L)), subtract);
        SQRTM1_BI = modPow;
        Point point = new Point();
        point.y = BigInteger.valueOf(4L).multiply(BigInteger.valueOf(5L).modInverse(subtract)).mod(subtract);
        point.x = recoverX(point.y);
        f9849a = Field25519.b(toLittleEndian(mod));
        f9850b = Field25519.b(toLittleEndian(mod2));
        f9851c = Field25519.b(toLittleEndian(modPow));
        f9852d = (Ed25519.CachedXYT[][]) Array.newInstance(Ed25519.CachedXYT.class, 32, 8);
        Point point2 = point;
        for (int i2 = 0; i2 < 32; i2++) {
            Point point3 = point2;
            for (int i3 = 0; i3 < 8; i3++) {
                f9852d[i2][i3] = getCachedXYT(point3);
                point3 = edwards(point3, point2);
            }
            for (int i4 = 0; i4 < 8; i4++) {
                point2 = edwards(point2, point2);
            }
        }
        Point edwards = edwards(point, point);
        f9853e = new Ed25519.CachedXYT[8];
        for (int i5 = 0; i5 < 8; i5++) {
            f9853e[i5] = getCachedXYT(point);
            point = edwards(point, edwards);
        }
    }

    private static Point edwards(Point point, Point point2) {
        Point point3 = new Point();
        BigInteger multiply = D_BI.multiply(point.x.multiply(point2.x).multiply(point.y).multiply(point2.y));
        BigInteger bigInteger = P_BI;
        BigInteger mod = multiply.mod(bigInteger);
        BigInteger add = point.x.multiply(point2.y).add(point2.x.multiply(point.y));
        BigInteger bigInteger2 = BigInteger.ONE;
        point3.x = add.multiply(bigInteger2.add(mod).modInverse(bigInteger)).mod(bigInteger);
        point3.y = point.y.multiply(point2.y).add(point.x.multiply(point2.x)).multiply(bigInteger2.subtract(mod).modInverse(bigInteger)).mod(bigInteger);
        return point3;
    }

    private static Ed25519.CachedXYT getCachedXYT(Point point) {
        BigInteger add = point.y.add(point.x);
        BigInteger bigInteger = P_BI;
        return new Ed25519.CachedXYT(Field25519.b(toLittleEndian(add.mod(bigInteger))), Field25519.b(toLittleEndian(point.y.subtract(point.x).mod(bigInteger))), Field25519.b(toLittleEndian(D2_BI.multiply(point.x).multiply(point.y).mod(bigInteger))));
    }

    private static BigInteger recoverX(BigInteger bigInteger) {
        BigInteger pow = bigInteger.pow(2);
        BigInteger bigInteger2 = BigInteger.ONE;
        BigInteger subtract = pow.subtract(bigInteger2);
        BigInteger add = D_BI.multiply(bigInteger.pow(2)).add(bigInteger2);
        BigInteger bigInteger3 = P_BI;
        BigInteger multiply = subtract.multiply(add.modInverse(bigInteger3));
        BigInteger modPow = multiply.modPow(bigInteger3.add(BigInteger.valueOf(3L)).divide(BigInteger.valueOf(8L)), bigInteger3);
        if (!modPow.pow(2).subtract(multiply).mod(bigInteger3).equals(BigInteger.ZERO)) {
            modPow = modPow.multiply(SQRTM1_BI).mod(bigInteger3);
        }
        return modPow.testBit(0) ? bigInteger3.subtract(modPow) : modPow;
    }

    private static byte[] toLittleEndian(BigInteger bigInteger) {
        byte[] bArr = new byte[32];
        byte[] byteArray = bigInteger.toByteArray();
        System.arraycopy(byteArray, 0, bArr, 32 - byteArray.length, byteArray.length);
        for (int i2 = 0; i2 < 16; i2++) {
            byte b2 = bArr[i2];
            int i3 = (32 - i2) - 1;
            bArr[i2] = bArr[i3];
            bArr[i3] = b2;
        }
        return bArr;
    }
}
