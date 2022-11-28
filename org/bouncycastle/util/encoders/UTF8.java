package org.bouncycastle.util.encoders;

import com.google.common.base.Ascii;
import okio.Utf8;
/* loaded from: classes4.dex */
public class UTF8 {
    private static final byte C_CR1 = 1;
    private static final byte C_CR2 = 2;
    private static final byte C_CR3 = 3;
    private static final byte C_ILL = 0;
    private static final byte C_L2A = 4;
    private static final byte C_L3A = 5;
    private static final byte C_L3B = 6;
    private static final byte C_L3C = 7;
    private static final byte C_L4A = 8;
    private static final byte C_L4B = 9;
    private static final byte C_L4C = 10;
    private static final byte S_CS1 = 0;
    private static final byte S_CS2 = 16;
    private static final byte S_CS3 = 32;
    private static final byte S_END = -1;
    private static final byte S_ERR = -2;
    private static final byte S_P3A = 48;
    private static final byte S_P3B = 64;
    private static final byte S_P4A = 80;
    private static final byte S_P4B = 96;
    private static final short[] firstUnitTable = new short[128];
    private static final byte[] transitionTable;

    static {
        byte[] bArr = new byte[112];
        transitionTable = bArr;
        byte[] bArr2 = new byte[128];
        fill(bArr2, 0, 15, (byte) 1);
        fill(bArr2, 16, 31, (byte) 2);
        fill(bArr2, 32, 63, (byte) 3);
        fill(bArr2, 64, 65, (byte) 0);
        fill(bArr2, 66, 95, (byte) 4);
        fill(bArr2, 96, 96, (byte) 5);
        fill(bArr2, 97, 108, (byte) 6);
        fill(bArr2, 109, 109, (byte) 7);
        fill(bArr2, 110, 111, (byte) 6);
        fill(bArr2, 112, 112, (byte) 8);
        fill(bArr2, 113, 115, (byte) 9);
        fill(bArr2, 116, 116, (byte) 10);
        fill(bArr2, 117, 127, (byte) 0);
        fill(bArr, 0, bArr.length - 1, S_ERR);
        fill(bArr, 8, 11, (byte) -1);
        fill(bArr, 24, 27, (byte) 0);
        fill(bArr, 40, 43, (byte) 16);
        fill(bArr, 58, 59, (byte) 0);
        fill(bArr, 72, 73, (byte) 0);
        fill(bArr, 89, 91, (byte) 16);
        fill(bArr, 104, 104, (byte) 16);
        byte[] bArr3 = {0, 0, 0, 0, Ascii.US, Ascii.SI, Ascii.SI, Ascii.SI, 7, 7, 7};
        byte[] bArr4 = {S_ERR, S_ERR, S_ERR, S_ERR, 0, S_P3A, 16, 64, S_P4A, 32, S_P4B};
        for (int i2 = 0; i2 < 128; i2++) {
            byte b2 = bArr2[i2];
            firstUnitTable[i2] = (short) (bArr4[b2] | ((bArr3[b2] & i2) << 8));
        }
    }

    private static void fill(byte[] bArr, int i2, int i3, byte b2) {
        while (i2 <= i3) {
            bArr[i2] = b2;
            i2++;
        }
    }

    public static int transcodeToUTF16(byte[] bArr, char[] cArr) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < bArr.length) {
            int i4 = i2 + 1;
            byte b2 = bArr[i2];
            if (b2 < 0) {
                short s2 = firstUnitTable[b2 & Byte.MAX_VALUE];
                int i5 = s2 >>> 8;
                byte b3 = (byte) s2;
                while (b3 >= 0) {
                    if (i4 >= bArr.length) {
                        return -1;
                    }
                    int i6 = i4 + 1;
                    byte b4 = bArr[i4];
                    i5 = (i5 << 6) | (b4 & Utf8.REPLACEMENT_BYTE);
                    b3 = transitionTable[b3 + ((b4 & 255) >>> 4)];
                    i4 = i6;
                }
                if (b3 == -2) {
                    return -1;
                }
                if (i5 <= 65535) {
                    if (i3 >= cArr.length) {
                        return -1;
                    }
                    cArr[i3] = (char) i5;
                    i3++;
                } else if (i3 >= cArr.length - 1) {
                    return -1;
                } else {
                    int i7 = i3 + 1;
                    cArr[i3] = (char) ((i5 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                    i3 = i7 + 1;
                    cArr[i7] = (char) (56320 | (i5 & 1023));
                }
                i2 = i4;
            } else if (i3 >= cArr.length) {
                return -1;
            } else {
                cArr[i3] = (char) b2;
                i2 = i4;
                i3++;
            }
        }
        return i3;
    }
}
