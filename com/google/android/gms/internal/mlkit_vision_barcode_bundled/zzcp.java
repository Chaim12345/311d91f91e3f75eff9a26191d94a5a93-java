package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.google.common.base.Ascii;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzcp {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(byte[] bArr, int i2, zzco zzcoVar) {
        int j2 = j(bArr, i2, zzcoVar);
        int i3 = zzcoVar.zza;
        if (i3 >= 0) {
            if (i3 <= bArr.length - j2) {
                if (i3 == 0) {
                    zzcoVar.zzc = zzdb.zzb;
                    return j2;
                }
                zzcoVar.zzc = zzdb.zzr(bArr, j2, i3);
                return j2 + i3;
            }
            throw zzen.f();
        }
        throw zzen.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(byte[] bArr, int i2) {
        return ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(zzgb zzgbVar, byte[] bArr, int i2, int i3, int i4, zzco zzcoVar) {
        zzfo zzfoVar = (zzfo) zzgbVar;
        Object zze = zzfoVar.zze();
        int a2 = zzfoVar.a(zze, bArr, i2, i3, i4, zzcoVar);
        zzfoVar.zzf(zze);
        zzcoVar.zzc = zze;
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int d(zzgb zzgbVar, byte[] bArr, int i2, int i3, zzco zzcoVar) {
        int i4 = i2 + 1;
        int i5 = bArr[i2];
        if (i5 < 0) {
            i4 = k(i5, bArr, i4, zzcoVar);
            i5 = zzcoVar.zza;
        }
        int i6 = i4;
        if (i5 < 0 || i5 > i3 - i6) {
            throw zzen.f();
        }
        Object zze = zzgbVar.zze();
        int i7 = i5 + i6;
        zzgbVar.zzh(zze, bArr, i6, i7, zzcoVar);
        zzgbVar.zzf(zze);
        zzcoVar.zzc = zze;
        return i7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int e(zzgb zzgbVar, int i2, byte[] bArr, int i3, int i4, zzek zzekVar, zzco zzcoVar) {
        int d2 = d(zzgbVar, bArr, i3, i4, zzcoVar);
        while (true) {
            zzekVar.add(zzcoVar.zzc);
            if (d2 >= i4) {
                break;
            }
            int j2 = j(bArr, d2, zzcoVar);
            if (i2 != zzcoVar.zza) {
                break;
            }
            d2 = d(zzgbVar, bArr, j2, i4, zzcoVar);
        }
        return d2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int f(byte[] bArr, int i2, zzek zzekVar, zzco zzcoVar) {
        zzed zzedVar = (zzed) zzekVar;
        int j2 = j(bArr, i2, zzcoVar);
        int i3 = zzcoVar.zza + j2;
        while (j2 < i3) {
            j2 = j(bArr, j2, zzcoVar);
            zzedVar.zzg(zzcoVar.zza);
        }
        if (j2 == i3) {
            return j2;
        }
        throw zzen.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int g(byte[] bArr, int i2, zzco zzcoVar) {
        int j2 = j(bArr, i2, zzcoVar);
        int i3 = zzcoVar.zza;
        if (i3 >= 0) {
            if (i3 == 0) {
                zzcoVar.zzc = "";
                return j2;
            }
            zzcoVar.zzc = new String(bArr, j2, i3, zzel.f6425a);
            return j2 + i3;
        }
        throw zzen.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int h(byte[] bArr, int i2, zzco zzcoVar) {
        int j2 = j(bArr, i2, zzcoVar);
        int i3 = zzcoVar.zza;
        if (i3 >= 0) {
            if (i3 == 0) {
                zzcoVar.zzc = "";
                return j2;
            }
            zzcoVar.zzc = zzhe.g(bArr, j2, i3);
            return j2 + i3;
        }
        throw zzen.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int i(int i2, byte[] bArr, int i3, int i4, zzgq zzgqVar, zzco zzcoVar) {
        if ((i2 >>> 3) != 0) {
            int i5 = i2 & 7;
            if (i5 == 0) {
                int m2 = m(bArr, i3, zzcoVar);
                zzgqVar.d(i2, Long.valueOf(zzcoVar.zzb));
                return m2;
            } else if (i5 == 1) {
                zzgqVar.d(i2, Long.valueOf(o(bArr, i3)));
                return i3 + 8;
            } else if (i5 == 2) {
                int j2 = j(bArr, i3, zzcoVar);
                int i6 = zzcoVar.zza;
                if (i6 >= 0) {
                    if (i6 <= bArr.length - j2) {
                        zzgqVar.d(i2, i6 == 0 ? zzdb.zzb : zzdb.zzr(bArr, j2, i6));
                        return j2 + i6;
                    }
                    throw zzen.f();
                }
                throw zzen.d();
            } else if (i5 != 3) {
                if (i5 == 5) {
                    zzgqVar.d(i2, Integer.valueOf(b(bArr, i3)));
                    return i3 + 4;
                }
                throw zzen.b();
            } else {
                int i7 = (i2 & (-8)) | 4;
                zzgq b2 = zzgq.b();
                int i8 = 0;
                while (true) {
                    if (i3 >= i4) {
                        break;
                    }
                    int j3 = j(bArr, i3, zzcoVar);
                    int i9 = zzcoVar.zza;
                    i8 = i9;
                    if (i9 == i7) {
                        i3 = j3;
                        break;
                    }
                    int i10 = i(i8, bArr, j3, i4, b2, zzcoVar);
                    i8 = i9;
                    i3 = i10;
                }
                if (i3 > i4 || i8 != i7) {
                    throw zzen.e();
                }
                zzgqVar.d(i2, b2);
                return i3;
            }
        }
        throw zzen.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int j(byte[] bArr, int i2, zzco zzcoVar) {
        int i3 = i2 + 1;
        byte b2 = bArr[i2];
        if (b2 >= 0) {
            zzcoVar.zza = b2;
            return i3;
        }
        return k(b2, bArr, i3, zzcoVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int k(int i2, byte[] bArr, int i3, zzco zzcoVar) {
        int i4;
        int i5;
        int i6 = i2 & 127;
        int i7 = i3 + 1;
        byte b2 = bArr[i3];
        if (b2 < 0) {
            int i8 = i6 | ((b2 & Byte.MAX_VALUE) << 7);
            int i9 = i7 + 1;
            byte b3 = bArr[i7];
            if (b3 >= 0) {
                i4 = b3 << Ascii.SO;
            } else {
                i6 = i8 | ((b3 & Byte.MAX_VALUE) << 14);
                i7 = i9 + 1;
                byte b4 = bArr[i9];
                if (b4 >= 0) {
                    i5 = b4 << Ascii.NAK;
                } else {
                    i8 = i6 | ((b4 & Byte.MAX_VALUE) << 21);
                    i9 = i7 + 1;
                    byte b5 = bArr[i7];
                    if (b5 >= 0) {
                        i4 = b5 << Ascii.FS;
                    } else {
                        int i10 = i8 | ((b5 & Byte.MAX_VALUE) << 28);
                        while (true) {
                            int i11 = i9 + 1;
                            if (bArr[i9] >= 0) {
                                zzcoVar.zza = i10;
                                return i11;
                            }
                            i9 = i11;
                        }
                    }
                }
            }
            zzcoVar.zza = i8 | i4;
            return i9;
        }
        i5 = b2 << 7;
        zzcoVar.zza = i6 | i5;
        return i7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int l(int i2, byte[] bArr, int i3, int i4, zzek zzekVar, zzco zzcoVar) {
        zzed zzedVar = (zzed) zzekVar;
        int j2 = j(bArr, i3, zzcoVar);
        while (true) {
            zzedVar.zzg(zzcoVar.zza);
            if (j2 >= i4) {
                break;
            }
            int j3 = j(bArr, j2, zzcoVar);
            if (i2 != zzcoVar.zza) {
                break;
            }
            j2 = j(bArr, j3, zzcoVar);
        }
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int m(byte[] bArr, int i2, zzco zzcoVar) {
        byte b2;
        int i3 = i2 + 1;
        long j2 = bArr[i2];
        if (j2 >= 0) {
            zzcoVar.zzb = j2;
            return i3;
        }
        int i4 = i3 + 1;
        byte b3 = bArr[i3];
        long j3 = (j2 & 127) | ((b3 & Byte.MAX_VALUE) << 7);
        int i5 = 7;
        while (b3 < 0) {
            int i6 = i4 + 1;
            i5 += 7;
            j3 |= (b2 & Byte.MAX_VALUE) << i5;
            b3 = bArr[i4];
            i4 = i6;
        }
        zzcoVar.zzb = j3;
        return i4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int n(int i2, byte[] bArr, int i3, int i4, zzco zzcoVar) {
        if ((i2 >>> 3) != 0) {
            int i5 = i2 & 7;
            if (i5 != 0) {
                if (i5 != 1) {
                    if (i5 != 2) {
                        if (i5 != 3) {
                            if (i5 == 5) {
                                return i3 + 4;
                            }
                            throw zzen.b();
                        }
                        int i6 = (i2 & (-8)) | 4;
                        int i7 = 0;
                        while (i3 < i4) {
                            i3 = j(bArr, i3, zzcoVar);
                            i7 = zzcoVar.zza;
                            if (i7 == i6) {
                                break;
                            }
                            i3 = n(i7, bArr, i3, i4, zzcoVar);
                        }
                        if (i3 > i4 || i7 != i6) {
                            throw zzen.e();
                        }
                        return i3;
                    }
                    return j(bArr, i3, zzcoVar) + zzcoVar.zza;
                }
                return i3 + 8;
            }
            return m(bArr, i3, zzcoVar);
        }
        throw zzen.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long o(byte[] bArr, int i2) {
        return ((bArr[i2 + 7] & 255) << 56) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16) | ((bArr[i2 + 3] & 255) << 24) | ((bArr[i2 + 4] & 255) << 32) | ((bArr[i2 + 5] & 255) << 40) | ((bArr[i2 + 6] & 255) << 48);
    }
}
