package io.opencensus.trace;

import io.opencensus.internal.Utils;
import java.util.Arrays;
/* loaded from: classes3.dex */
final class BigendianEncoding {
    private static final String ALPHABET = "0123456789abcdef";
    private static final int ASCII_CHARACTERS = 128;
    private static final char[] ENCODING = buildEncodingArray();
    private static final byte[] DECODING = buildDecodingArray();

    private BigendianEncoding() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte a(CharSequence charSequence, int i2) {
        Utils.checkArgument(charSequence.length() >= i2 + 2, "chars too small");
        return decodeByte(charSequence.charAt(i2), charSequence.charAt(i2 + 1));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(byte b2, char[] cArr, int i2) {
        byteToBase16(b2, cArr, i2);
    }

    private static byte[] buildDecodingArray() {
        byte[] bArr = new byte[128];
        Arrays.fill(bArr, (byte) -1);
        for (int i2 = 0; i2 < 16; i2++) {
            bArr[ALPHABET.charAt(i2)] = (byte) i2;
        }
        return bArr;
    }

    private static char[] buildEncodingArray() {
        char[] cArr = new char[512];
        for (int i2 = 0; i2 < 256; i2++) {
            cArr[i2] = ALPHABET.charAt(i2 >>> 4);
            cArr[i2 | 256] = ALPHABET.charAt(i2 & 15);
        }
        return cArr;
    }

    private static void byteToBase16(byte b2, char[] cArr, int i2) {
        int i3 = b2 & 255;
        char[] cArr2 = ENCODING;
        cArr[i2] = cArr2[i3];
        cArr[i2 + 1] = cArr2[i3 | 256];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long c(CharSequence charSequence, int i2) {
        Utils.checkArgument(charSequence.length() >= i2 + 16, "chars too small");
        return (decodeByte(charSequence.charAt(i2 + 14), charSequence.charAt(i2 + 15)) & 255) | ((decodeByte(charSequence.charAt(i2), charSequence.charAt(i2 + 1)) & 255) << 56) | ((decodeByte(charSequence.charAt(i2 + 2), charSequence.charAt(i2 + 3)) & 255) << 48) | ((decodeByte(charSequence.charAt(i2 + 4), charSequence.charAt(i2 + 5)) & 255) << 40) | ((decodeByte(charSequence.charAt(i2 + 6), charSequence.charAt(i2 + 7)) & 255) << 32) | ((decodeByte(charSequence.charAt(i2 + 8), charSequence.charAt(i2 + 9)) & 255) << 24) | ((decodeByte(charSequence.charAt(i2 + 10), charSequence.charAt(i2 + 11)) & 255) << 16) | ((decodeByte(charSequence.charAt(i2 + 12), charSequence.charAt(i2 + 13)) & 255) << 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long d(byte[] bArr, int i2) {
        Utils.checkArgument(bArr.length >= i2 + 8, "array too small");
        return (bArr[i2 + 7] & 255) | ((bArr[i2] & 255) << 56) | ((bArr[i2 + 1] & 255) << 48) | ((bArr[i2 + 2] & 255) << 40) | ((bArr[i2 + 3] & 255) << 32) | ((bArr[i2 + 4] & 255) << 24) | ((bArr[i2 + 5] & 255) << 16) | ((bArr[i2 + 6] & 255) << 8);
    }

    private static byte decodeByte(char c2, char c3) {
        boolean z = true;
        Utils.checkArgument(c3 < 128 && DECODING[c3] != -1, "invalid character " + c3);
        if (c2 >= 128 || DECODING[c2] == -1) {
            z = false;
        }
        Utils.checkArgument(z, "invalid character " + c2);
        byte[] bArr = DECODING;
        return (byte) ((bArr[c2] << 4) | bArr[c3]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(long j2, char[] cArr, int i2) {
        byteToBase16((byte) ((j2 >> 56) & 255), cArr, i2);
        byteToBase16((byte) ((j2 >> 48) & 255), cArr, i2 + 2);
        byteToBase16((byte) ((j2 >> 40) & 255), cArr, i2 + 4);
        byteToBase16((byte) ((j2 >> 32) & 255), cArr, i2 + 6);
        byteToBase16((byte) ((j2 >> 24) & 255), cArr, i2 + 8);
        byteToBase16((byte) ((j2 >> 16) & 255), cArr, i2 + 10);
        byteToBase16((byte) ((j2 >> 8) & 255), cArr, i2 + 12);
        byteToBase16((byte) (j2 & 255), cArr, i2 + 14);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void f(long j2, byte[] bArr, int i2) {
        Utils.checkArgument(bArr.length >= i2 + 8, "array too small");
        bArr[i2 + 7] = (byte) (j2 & 255);
        bArr[i2 + 6] = (byte) ((j2 >> 8) & 255);
        bArr[i2 + 5] = (byte) ((j2 >> 16) & 255);
        bArr[i2 + 4] = (byte) ((j2 >> 24) & 255);
        bArr[i2 + 3] = (byte) ((j2 >> 32) & 255);
        bArr[i2 + 2] = (byte) ((j2 >> 40) & 255);
        bArr[i2 + 1] = (byte) ((j2 >> 48) & 255);
        bArr[i2] = (byte) ((j2 >> 56) & 255);
    }
}
