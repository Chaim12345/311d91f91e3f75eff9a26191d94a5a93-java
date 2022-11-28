package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzip {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(byte[] bArr, int i2, zzio zzioVar) {
        int j2 = j(bArr, i2, zzioVar);
        int i3 = zzioVar.zza;
        if (i3 >= 0) {
            if (i3 <= bArr.length - j2) {
                if (i3 == 0) {
                    zzioVar.zzc = zzjb.zzb;
                    return j2;
                }
                zzioVar.zzc = zzjb.zzl(bArr, j2, i3);
                return j2 + i3;
            }
            throw zzkm.f();
        }
        throw zzkm.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(byte[] bArr, int i2) {
        return ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(zzlu zzluVar, byte[] bArr, int i2, int i3, int i4, zzio zzioVar) {
        zzlm zzlmVar = (zzlm) zzluVar;
        Object zze = zzlmVar.zze();
        int a2 = zzlmVar.a(zze, bArr, i2, i3, i4, zzioVar);
        zzlmVar.zzf(zze);
        zzioVar.zzc = zze;
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int d(zzlu zzluVar, byte[] bArr, int i2, int i3, zzio zzioVar) {
        int i4 = i2 + 1;
        int i5 = bArr[i2];
        if (i5 < 0) {
            i4 = k(i5, bArr, i4, zzioVar);
            i5 = zzioVar.zza;
        }
        int i6 = i4;
        if (i5 < 0 || i5 > i3 - i6) {
            throw zzkm.f();
        }
        Object zze = zzluVar.zze();
        int i7 = i5 + i6;
        zzluVar.zzh(zze, bArr, i6, i7, zzioVar);
        zzluVar.zzf(zze);
        zzioVar.zzc = zze;
        return i7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int e(zzlu zzluVar, int i2, byte[] bArr, int i3, int i4, zzkj zzkjVar, zzio zzioVar) {
        int d2 = d(zzluVar, bArr, i3, i4, zzioVar);
        while (true) {
            zzkjVar.add(zzioVar.zzc);
            if (d2 >= i4) {
                break;
            }
            int j2 = j(bArr, d2, zzioVar);
            if (i2 != zzioVar.zza) {
                break;
            }
            d2 = d(zzluVar, bArr, j2, i4, zzioVar);
        }
        return d2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int f(byte[] bArr, int i2, zzkj zzkjVar, zzio zzioVar) {
        zzkd zzkdVar = (zzkd) zzkjVar;
        int j2 = j(bArr, i2, zzioVar);
        int i3 = zzioVar.zza + j2;
        while (j2 < i3) {
            j2 = j(bArr, j2, zzioVar);
            zzkdVar.zzh(zzioVar.zza);
        }
        if (j2 == i3) {
            return j2;
        }
        throw zzkm.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int g(byte[] bArr, int i2, zzio zzioVar) {
        int j2 = j(bArr, i2, zzioVar);
        int i3 = zzioVar.zza;
        if (i3 >= 0) {
            if (i3 == 0) {
                zzioVar.zzc = "";
                return j2;
            }
            zzioVar.zzc = new String(bArr, j2, i3, zzkk.f6100a);
            return j2 + i3;
        }
        throw zzkm.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int h(byte[] bArr, int i2, zzio zzioVar) {
        int j2 = j(bArr, i2, zzioVar);
        int i3 = zzioVar.zza;
        if (i3 >= 0) {
            if (i3 == 0) {
                zzioVar.zzc = "";
                return j2;
            }
            zzioVar.zzc = zzna.d(bArr, j2, i3);
            return j2 + i3;
        }
        throw zzkm.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int i(int i2, byte[] bArr, int i3, int i4, zzmm zzmmVar, zzio zzioVar) {
        if ((i2 >>> 3) != 0) {
            int i5 = i2 & 7;
            if (i5 == 0) {
                int m2 = m(bArr, i3, zzioVar);
                zzmmVar.d(i2, Long.valueOf(zzioVar.zzb));
                return m2;
            } else if (i5 == 1) {
                zzmmVar.d(i2, Long.valueOf(n(bArr, i3)));
                return i3 + 8;
            } else if (i5 == 2) {
                int j2 = j(bArr, i3, zzioVar);
                int i6 = zzioVar.zza;
                if (i6 >= 0) {
                    if (i6 <= bArr.length - j2) {
                        zzmmVar.d(i2, i6 == 0 ? zzjb.zzb : zzjb.zzl(bArr, j2, i6));
                        return j2 + i6;
                    }
                    throw zzkm.f();
                }
                throw zzkm.d();
            } else if (i5 != 3) {
                if (i5 == 5) {
                    zzmmVar.d(i2, Integer.valueOf(b(bArr, i3)));
                    return i3 + 4;
                }
                throw zzkm.b();
            } else {
                int i7 = (i2 & (-8)) | 4;
                zzmm b2 = zzmm.b();
                int i8 = 0;
                while (true) {
                    if (i3 >= i4) {
                        break;
                    }
                    int j3 = j(bArr, i3, zzioVar);
                    int i9 = zzioVar.zza;
                    i8 = i9;
                    if (i9 == i7) {
                        i3 = j3;
                        break;
                    }
                    int i10 = i(i8, bArr, j3, i4, b2, zzioVar);
                    i8 = i9;
                    i3 = i10;
                }
                if (i3 > i4 || i8 != i7) {
                    throw zzkm.e();
                }
                zzmmVar.d(i2, b2);
                return i3;
            }
        }
        throw zzkm.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int j(byte[] bArr, int i2, zzio zzioVar) {
        int i3 = i2 + 1;
        byte b2 = bArr[i2];
        if (b2 >= 0) {
            zzioVar.zza = b2;
            return i3;
        }
        return k(b2, bArr, i3, zzioVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int k(int i2, byte[] bArr, int i3, zzio zzioVar) {
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
                                zzioVar.zza = i10;
                                return i11;
                            }
                            i9 = i11;
                        }
                    }
                }
            }
            zzioVar.zza = i8 | i4;
            return i9;
        }
        i5 = b2 << 7;
        zzioVar.zza = i6 | i5;
        return i7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int l(int i2, byte[] bArr, int i3, int i4, zzkj zzkjVar, zzio zzioVar) {
        zzkd zzkdVar = (zzkd) zzkjVar;
        int j2 = j(bArr, i3, zzioVar);
        while (true) {
            zzkdVar.zzh(zzioVar.zza);
            if (j2 >= i4) {
                break;
            }
            int j3 = j(bArr, j2, zzioVar);
            if (i2 != zzioVar.zza) {
                break;
            }
            j2 = j(bArr, j3, zzioVar);
        }
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int m(byte[] bArr, int i2, zzio zzioVar) {
        byte b2;
        int i3 = i2 + 1;
        long j2 = bArr[i2];
        if (j2 >= 0) {
            zzioVar.zzb = j2;
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
        zzioVar.zzb = j3;
        return i4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long n(byte[] bArr, int i2) {
        return ((bArr[i2 + 7] & 255) << 56) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16) | ((bArr[i2 + 3] & 255) << 24) | ((bArr[i2 + 4] & 255) << 32) | ((bArr[i2 + 5] & 255) << 40) | ((bArr[i2 + 6] & 255) << 48);
    }
}
