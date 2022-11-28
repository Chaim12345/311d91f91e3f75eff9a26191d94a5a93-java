package com.google.android.libraries.places.internal;

import javax.annotation.CheckForNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class zzhd extends zzgl {
    final CharSequence zzb;
    final zzgq zzc;
    int zzd = 0;
    int zze;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzhd(zzhe zzheVar, CharSequence charSequence) {
        zzgq zzgqVar;
        zzgqVar = zzheVar.zza;
        this.zzc = zzgqVar;
        this.zze = Integer.MAX_VALUE;
        this.zzb = "2.6.0";
    }

    @Override // com.google.android.libraries.places.internal.zzgl
    @CheckForNull
    protected final /* bridge */ /* synthetic */ Object zza() {
        int zzc;
        int i2 = this.zzd;
        while (true) {
            int i3 = this.zzd;
            if (i3 == -1) {
                zzb();
                return null;
            }
            int zzd = zzd(i3);
            if (zzd == -1) {
                zzd = this.zzb.length();
                this.zzd = -1;
                zzc = -1;
            } else {
                zzc = zzc(zzd);
                this.zzd = zzc;
            }
            if (zzc != i2) {
                if (i2 < zzd) {
                    this.zzb.charAt(i2);
                }
                if (i2 < zzd) {
                    this.zzb.charAt(zzd - 1);
                }
                int i4 = this.zze;
                if (i4 == 1) {
                    zzd = this.zzb.length();
                    this.zzd = -1;
                    if (zzd > i2) {
                        this.zzb.charAt(zzd - 1);
                    }
                } else {
                    this.zze = i4 - 1;
                }
                return this.zzb.subSequence(i2, zzd).toString();
            }
            int i5 = zzc + 1;
            this.zzd = i5;
            if (i5 > this.zzb.length()) {
                this.zzd = -1;
            }
        }
    }

    abstract int zzc(int i2);

    abstract int zzd(int i2);
}
