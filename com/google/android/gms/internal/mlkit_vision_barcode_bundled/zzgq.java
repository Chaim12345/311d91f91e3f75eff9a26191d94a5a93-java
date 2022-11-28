package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Arrays;
/* loaded from: classes2.dex */
public final class zzgq {
    private static final zzgq zza = new zzgq(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzgq() {
        this(0, new int[8], new Object[8], true);
    }

    private zzgq(int i2, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i2;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgq a(zzgq zzgqVar, zzgq zzgqVar2) {
        int i2 = zzgqVar.zzb + zzgqVar2.zzb;
        int[] copyOf = Arrays.copyOf(zzgqVar.zzc, i2);
        System.arraycopy(zzgqVar2.zzc, 0, copyOf, zzgqVar.zzb, zzgqVar2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzgqVar.zzd, i2);
        System.arraycopy(zzgqVar2.zzd, 0, copyOf2, zzgqVar.zzb, zzgqVar2.zzb);
        return new zzgq(i2, copyOf, copyOf2, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgq b() {
        return new zzgq(0, new int[8], new Object[8], true);
    }

    public static zzgq zzc() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(StringBuilder sb, int i2) {
        for (int i3 = 0; i3 < this.zzb; i3++) {
            zzfn.b(sb, i2, String.valueOf(this.zzc[i3] >>> 3), this.zzd[i3]);
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void e(zzdj zzdjVar) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzdjVar.zzw(this.zzc[i2] >>> 3, this.zzd[i2]);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof zzgq)) {
            zzgq zzgqVar = (zzgq) obj;
            int i2 = this.zzb;
            if (i2 == zzgqVar.zzb) {
                int[] iArr = this.zzc;
                int[] iArr2 = zzgqVar.zzc;
                int i3 = 0;
                while (true) {
                    if (i3 >= i2) {
                        Object[] objArr = this.zzd;
                        Object[] objArr2 = zzgqVar.zzd;
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
        int zzD;
        int zzE;
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
                        i2 = zzdi.zzD(i7 << 3) + 8;
                    } else if (i8 == 2) {
                        int zzD2 = zzdi.zzD(i7 << 3);
                        int zzd = ((zzdb) this.zzd[i5]).zzd();
                        i4 += zzD2 + zzdi.zzD(zzd) + zzd;
                    } else if (i8 == 3) {
                        int zzC = zzdi.zzC(i7);
                        zzD = zzC + zzC;
                        zzE = ((zzgq) this.zzd[i5]).zza();
                    } else if (i8 != 5) {
                        throw new IllegalStateException(zzen.a());
                    } else {
                        ((Integer) this.zzd[i5]).intValue();
                        i2 = zzdi.zzD(i7 << 3) + 4;
                    }
                    i4 += i2;
                } else {
                    long longValue = ((Long) this.zzd[i5]).longValue();
                    zzD = zzdi.zzD(i7 << 3);
                    zzE = zzdi.zzE(longValue);
                }
                i2 = zzD + zzE;
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
                int zzD = zzdi.zzD(8);
                int zzd = ((zzdb) this.zzd[i4]).zzd();
                i3 += zzD + zzD + zzdi.zzD(16) + zzdi.zzD(i5 >>> 3) + zzdi.zzD(24) + zzdi.zzD(zzd) + zzd;
            }
            this.zze = i3;
            return i3;
        }
        return i2;
    }

    public final void zzf() {
        this.zzf = false;
    }

    public final void zzj(zzdj zzdjVar) {
        if (this.zzb != 0) {
            for (int i2 = 0; i2 < this.zzb; i2++) {
                int i3 = this.zzc[i2];
                Object obj = this.zzd[i2];
                int i4 = i3 >>> 3;
                int i5 = i3 & 7;
                if (i5 == 0) {
                    zzdjVar.zzt(i4, ((Long) obj).longValue());
                } else if (i5 == 1) {
                    zzdjVar.zzm(i4, ((Long) obj).longValue());
                } else if (i5 == 2) {
                    zzdjVar.zzd(i4, (zzdb) obj);
                } else if (i5 == 3) {
                    zzdjVar.zzF(i4);
                    ((zzgq) obj).zzj(zzdjVar);
                    zzdjVar.zzh(i4);
                } else if (i5 != 5) {
                    throw new RuntimeException(zzen.a());
                } else {
                    zzdjVar.zzk(i4, ((Integer) obj).intValue());
                }
            }
        }
    }
}
