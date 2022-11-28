package com.facebook.stetho.websocket;
/* loaded from: classes.dex */
class MaskingHelper {
    MaskingHelper() {
    }

    public static void unmask(byte[] bArr, byte[] bArr2, int i2, int i3) {
        int i4 = 0;
        while (true) {
            int i5 = i3 - 1;
            if (i3 <= 0) {
                return;
            }
            bArr2[i2] = (byte) (bArr[i4 % bArr.length] ^ bArr2[i2]);
            i2++;
            i3 = i5;
            i4++;
        }
    }
}
