package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.google.common.base.Ascii;
/* loaded from: classes2.dex */
final class zzhc extends zzhb {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x001c, code lost:
        if (r13[r14] <= (-65)) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0047, code lost:
        if (r13[r14] <= (-65)) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0083, code lost:
        if (r13[r14] <= (-65)) goto L11;
     */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int a(int i2, byte[] bArr, int i3, int i4) {
        byte b2;
        int i5;
        int zzk;
        int zzj;
        int zzj2;
        if (i2 != 0) {
            if (i3 >= i4) {
                return i2;
            }
            byte b3 = (byte) i2;
            if (b3 < -32) {
                if (b3 >= -62) {
                    i5 = i3 + 1;
                }
                return -1;
            } else if (b3 < -16) {
                byte b4 = (byte) (~(i2 >> 8));
                if (b4 == 0) {
                    int i6 = i3 + 1;
                    byte b5 = bArr[i3];
                    if (i6 >= i4) {
                        zzj2 = zzhe.zzj(b3, b5);
                        return zzj2;
                    }
                    i3 = i6;
                    b4 = b5;
                }
                if (b4 <= -65 && ((b3 != -32 || b4 >= -96) && (b3 != -19 || b4 < -96))) {
                    i5 = i3 + 1;
                }
                return -1;
            } else {
                byte b6 = (byte) (~(i2 >> 8));
                if (b6 == 0) {
                    int i7 = i3 + 1;
                    b6 = bArr[i3];
                    if (i7 >= i4) {
                        zzj = zzhe.zzj(b3, b6);
                        return zzj;
                    }
                    i3 = i7;
                    b2 = 0;
                } else {
                    b2 = i2 >> 16;
                }
                if (b2 == 0) {
                    int i8 = i3 + 1;
                    byte b7 = bArr[i3];
                    if (i8 >= i4) {
                        zzk = zzhe.zzk(b3, b6, b7);
                        return zzk;
                    }
                    i3 = i8;
                    b2 = b7;
                }
                if (b6 <= -65 && (((b3 << Ascii.FS) + (b6 + 112)) >> 30) == 0 && b2 <= -65) {
                    i5 = i3 + 1;
                }
                return -1;
            }
            i3 = i5;
        }
        while (i3 < i4 && bArr[i3] >= 0) {
            i3++;
        }
        if (i3 >= i4) {
            return 0;
        }
        while (i3 < i4) {
            int i9 = i3 + 1;
            byte b8 = bArr[i3];
            if (b8 < 0) {
                if (b8 < -32) {
                    if (i9 < i4) {
                        if (b8 >= -62) {
                            i3 = i9 + 1;
                            if (bArr[i9] > -65) {
                            }
                        }
                        return -1;
                    }
                    return b8;
                } else if (b8 >= -16) {
                    if (i9 >= i4 - 2) {
                        return zzhe.c(bArr, i9, i4);
                    }
                    int i10 = i9 + 1;
                    byte b9 = bArr[i9];
                    if (b9 <= -65 && (((b8 << Ascii.FS) + (b9 + 112)) >> 30) == 0) {
                        int i11 = i10 + 1;
                        if (bArr[i10] <= -65) {
                            i3 = i11 + 1;
                            if (bArr[i11] > -65) {
                            }
                        }
                    }
                    return -1;
                } else if (i9 >= i4 - 1) {
                    return zzhe.c(bArr, i9, i4);
                } else {
                    int i12 = i9 + 1;
                    byte b10 = bArr[i9];
                    if (b10 <= -65 && ((b8 != -32 || b10 >= -96) && (b8 != -19 || b10 < -96))) {
                        i3 = i12 + 1;
                        if (bArr[i12] > -65) {
                        }
                    }
                    return -1;
                }
            }
            i3 = i9;
        }
        return 0;
    }
}
