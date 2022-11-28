package com.google.crypto.tink.hybrid.subtle;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
/* loaded from: classes2.dex */
class RsaKem {

    /* renamed from: a  reason: collision with root package name */
    static final byte[] f9630a = new byte[0];

    private RsaKem() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(BigInteger bigInteger) {
        return (bigInteger.bitLength() + 7) / 8;
    }

    static byte[] b(BigInteger bigInteger, int i2) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length == i2) {
            return byteArray;
        }
        byte[] bArr = new byte[i2];
        if (byteArray.length == i2 + 1) {
            if (byteArray[0] != 0) {
                throw new IllegalArgumentException("Value is one-byte longer than the expected size, but its first byte is not 0");
            }
            System.arraycopy(byteArray, 1, bArr, 0, i2);
        } else if (byteArray.length >= i2) {
            throw new IllegalArgumentException(String.format("Value has invalid length, must be of length at most (%d + 1), but got %d", Integer.valueOf(i2), Integer.valueOf(byteArray.length)));
        } else {
            System.arraycopy(byteArray, 0, bArr, i2 - byteArray.length, byteArray.length);
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] c(BigInteger bigInteger) {
        int a2 = a(bigInteger);
        SecureRandom secureRandom = new SecureRandom();
        while (true) {
            BigInteger bigInteger2 = new BigInteger(bigInteger.bitLength(), secureRandom);
            if (bigInteger2.signum() > 0 && bigInteger2.compareTo(bigInteger) < 0) {
                return b(bigInteger2, a2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(BigInteger bigInteger) {
        if (bigInteger.bitLength() < 2048) {
            throw new GeneralSecurityException(String.format("RSA key must be of at least size %d bits, but got %d", 2048, Integer.valueOf(bigInteger.bitLength())));
        }
    }
}
