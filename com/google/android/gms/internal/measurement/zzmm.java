package com.google.android.gms.internal.measurement;

import java.util.Arrays;
/* loaded from: classes.dex */
public final class zzmm {
    private static final zzmm zza = new zzmm(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzmm() {
        this(0, new int[8], new Object[8], true);
    }

    private zzmm(int i2, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i2;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzmm a(zzmm zzmmVar, zzmm zzmmVar2) {
        int i2 = zzmmVar.zzb + zzmmVar2.zzb;
        int[] copyOf = Arrays.copyOf(zzmmVar.zzc, i2);
        System.arraycopy(zzmmVar2.zzc, 0, copyOf, zzmmVar.zzb, zzmmVar2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzmmVar.zzd, i2);
        System.arraycopy(zzmmVar2.zzd, 0, copyOf2, zzmmVar.zzb, zzmmVar2.zzb);
        return new zzmm(i2, copyOf, copyOf2, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzmm b() {
        return new zzmm(0, new int[8], new Object[8], true);
    }

    public static zzmm zzc() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(StringBuilder sb, int i2) {
        for (int i3 = 0; i3 < this.zzb; i3++) {
            zzll.b(sb, i2, String.valueOf(this.zzc[i3] >>> 3), this.zzd[i3]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d(int i2, Object obj) {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
        int i3 = this.zzb;
        int[] iArr = this.zzc;
        if (i3 == iArr.length) {
            int i4 = i3 + (i3 < 4 ? 8 : i3 >> 1);
            this.zzc = Arrays.copyOf(iArr, i4);
            this.zzd = Arrays.copyOf(this.zzd, i4);
        }
        int[] iArr2 = this.zzc;
        int i5 = this.zzb;
        iArr2[i5] = i2;
        this.zzd[i5] = obj;
        this.zzb = i5 + 1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof zzmm)) {
            zzmm zzmmVar = (zzmm) obj;
            int i2 = this.zzb;
            if (i2 == zzmmVar.zzb) {
                int[] iArr = this.zzc;
                int[] iArr2 = zzmmVar.zzc;
                int i3 = 0;
                while (true) {
                    if (i3 >= i2) {
                        Object[] objArr = this.zzd;
                        Object[] objArr2 = zzmmVar.zzd;
                        int i4 = this.zzb;
                        for (int i5 = 0; i5 < i4; i5++) {
                            if (objArr[i5].equals(objArr2[i5])) {
                            }
                        }
                        return true;
                    } else if (iArr[i3] != iArr2[i3]) {
                        break;
                    } else {
                        i3++;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int i2 = this.zzb;
        int i3 = (i2 + 527) * 31;
        int[] iArr = this.zzc;
        int i4 = 17;
        int i5 = 17;
        for (int i6 = 0; i6 < i2; i6++) {
            i5 = (i5 * 31) + iArr[i6];
        }
        int i7 = (i3 + i5) * 31;
        Object[] objArr = this.zzd;
        int i8 = this.zzb;
        for (int i9 = 0; i9 < i8; i9++) {
            i4 = (i4 * 31) + objArr[i9].hashCode();
        }
        return i7 + i4;
    }

    public final int zza() {
        int zzA;
        int zzB;
        int i2;
        int i3 = this.zze;
        if (i3 == -1) {
            int i4 = 0;
            for (int i5 = 0; i5 < this.zzb; i5++) {
                int i6 = this.zzc[i5];
                int i7 = i6 >>> 3;
                int i8 = i6 & 7;
                if (i8 != 0) {
                    if (i8 == 1) {
                        ((Long) this.zzd[i5]).longValue();
                        i2 = zzjj.zzA(i7 << 3) + 8;
                    } else if (i8 == 2) {
                        int zzA2 = zzjj.zzA(i7 << 3);
                        int zzd = ((zzjb) this.zzd[i5]).zzd();
                        i4 += zzA2 + zzjj.zzA(zzd) + zzd;
                    } else if (i8 == 3) {
                        int zzz = zzjj.zzz(i7);
                        zzA = zzz + zzz;
                        zzB = ((zzmm) this.zzd[i5]).zza();
                    } else if (i8 != 5) {
                        throw new IllegalStateException(zzkm.a());
                    } else {
                        ((Integer) this.zzd[i5]).intValue();
                        i2 = zzjj.zzA(i7 << 3) + 4;
                    }
                    i4 += i2;
                } else {
                    long longValue = ((Long) this.zzd[i5]).longValue();
                    zzA = zzjj.zzA(i7 << 3);
                    zzB = zzjj.zzB(longValue);
                }
                i2 = zzA + zzB;
                i4 += i2;
            }
            this.zze = i4;
            return i4;
        }
        return i3;
    }

    public final int zzb() {
        int i2 = this.zze;
        if (i2 == -1) {
            int i3 = 0;
            for (int i4 = 0; i4 < this.zzb; i4++) {
                int i5 = this.zzc[i4];
                int zzA = zzjj.zzA(8);
                int zzd = ((zzjb) this.zzd[i4]).zzd();
                i3 += zzA + zzA + zzjj.zzA(16) + zzjj.zzA(i5 >>> 3) + zzjj.zzA(24) + zzjj.zzA(zzd) + zzd;
            }
            this.zze = i3;
            return i3;
        }
        return i2;
    }

    public final void zzf() {
        this.zzf = false;
    }

    public final void zzi(zznd zzndVar) {
        if (this.zzb != 0) {
            for (int i2 = 0; i2 < this.zzb; i2++) {
                int i3 = this.zzc[i2];
                Object obj = this.zzd[i2];
                int i4 = i3 >>> 3;
                int i5 = i3 & 7;
                if (i5 == 0) {
                    zzndVar.zzt(i4, ((Long) obj).longValue());
                } else if (i5 == 1) {
                    zzndVar.zzm(i4, ((Long) obj).longValue());
                } else if (i5 == 2) {
                    zzndVar.zzd(i4, (zzjb) obj);
                } else if (i5 == 3) {
                    zzndVar.zzE(i4);
                    ((zzmm) obj).zzi(zzndVar);
                    zzndVar.zzh(i4);
                } else if (i5 != 5) {
                    throw new RuntimeException(zzkm.a());
                } else {
                    zzndVar.zzk(i4, ((Integer) obj).intValue());
                }
            }
        }
    }
}
