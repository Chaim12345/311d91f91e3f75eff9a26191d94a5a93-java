package com.google.android.play.core.assetpacks;

import androidx.annotation.Nullable;
import java.util.Arrays;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzds {
    private byte[] zza = new byte[4096];
    private int zzb;
    private long zzc;
    private long zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private boolean zzh;
    @Nullable
    private String zzi;

    public zzds() {
        zzd();
    }

    private final int zze(int i2, byte[] bArr, int i3, int i4) {
        int i5 = this.zzb;
        if (i5 < i2) {
            int min = Math.min(i4, i2 - i5);
            System.arraycopy(bArr, i3, this.zza, this.zzb, min);
            int i6 = this.zzb + min;
            this.zzb = i6;
            if (i6 < i2) {
                return -1;
            }
            return min;
        }
        return 0;
    }

    public final int zza() {
        return this.zzf;
    }

    public final int zzb(byte[] bArr, int i2, int i3) {
        int zze = zze(30, bArr, i2, i3);
        if (zze != -1) {
            if (this.zzc == -1) {
                long c2 = zzbr.c(this.zza, 0);
                this.zzc = c2;
                if (c2 == 67324752) {
                    this.zzh = false;
                    this.zzd = zzbr.c(this.zza, 18);
                    this.zzg = zzbr.a(this.zza, 8);
                    this.zze = zzbr.a(this.zza, 26);
                    int a2 = this.zze + 30 + zzbr.a(this.zza, 28);
                    this.zzf = a2;
                    int length = this.zza.length;
                    if (length < a2) {
                        do {
                            length += length;
                        } while (length < a2);
                        this.zza = Arrays.copyOf(this.zza, length);
                    }
                } else {
                    this.zzh = true;
                }
            }
            int zze2 = zze(this.zzf, bArr, i2 + zze, i3 - zze);
            if (zze2 == -1) {
                return -1;
            }
            int i4 = zze + zze2;
            if (!this.zzh && this.zzi == null) {
                this.zzi = new String(this.zza, 30, this.zze);
            }
            return i4;
        }
        return -1;
    }

    public final zzet zzc() {
        int i2 = this.zzb;
        int i3 = this.zzf;
        if (i2 < i3) {
            return new zzbq(this.zzi, this.zzd, this.zzg, true, this.zzh, Arrays.copyOf(this.zza, i2));
        }
        zzbq zzbqVar = new zzbq(this.zzi, this.zzd, this.zzg, false, this.zzh, Arrays.copyOf(this.zza, i3));
        zzd();
        return zzbqVar;
    }

    public final void zzd() {
        this.zzb = 0;
        this.zze = -1;
        this.zzc = -1L;
        this.zzh = false;
        this.zzf = 30;
        this.zzd = -1L;
        this.zzg = -1;
        this.zzi = null;
    }
}
