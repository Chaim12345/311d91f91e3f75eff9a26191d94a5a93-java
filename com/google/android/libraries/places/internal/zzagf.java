package com.google.android.libraries.places.internal;

import com.google.common.base.Ascii;
/* loaded from: classes2.dex */
final class zzagf extends zzage {
    @Override // com.google.android.libraries.places.internal.zzage
    final int zza(int i2, byte[] bArr, int i3, int i4) {
        int i5 = 0;
        while (i5 < i4 && bArr[i5] >= 0) {
            i5++;
        }
        if (i5 >= i4) {
            return 0;
        }
        while (i5 < i4) {
            int i6 = i5 + 1;
            byte b2 = bArr[i5];
            if (b2 < 0) {
                if (b2 < -32) {
                    if (i6 < i4) {
                        if (b2 >= -62) {
                            i5 = i6 + 1;
                            if (bArr[i6] > -65) {
                            }
                        }
                        return -1;
                    }
                    return b2;
                } else if (b2 >= -16) {
                    if (i6 >= i4 - 2) {
                        return zzagh.zza(bArr, i6, i4);
                    }
                    int i7 = i6 + 1;
                    byte b3 = bArr[i6];
                    if (b3 <= -65 && (((b2 << Ascii.FS) + (b3 + 112)) >> 30) == 0) {
                        int i8 = i7 + 1;
                        if (bArr[i7] <= -65) {
                            i6 = i8 + 1;
                            if (bArr[i8] > -65) {
                            }
                        }
                    }
                    return -1;
                } else if (i6 >= i4 - 1) {
                    return zzagh.zza(bArr, i6, i4);
                } else {
                    int i9 = i6 + 1;
                    byte b4 = bArr[i6];
                    if (b4 <= -65 && ((b2 != -32 || b4 >= -96) && (b2 != -19 || b4 < -96))) {
                        i5 = i9 + 1;
                        if (bArr[i9] > -65) {
                        }
                    }
                    return -1;
                }
            }
            i5 = i6;
        }
        return 0;
    }
}
