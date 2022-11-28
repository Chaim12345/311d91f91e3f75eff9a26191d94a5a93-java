package com.google.android.libraries.places.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzhb extends zzhd {
    final /* synthetic */ zzhc zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzhb(zzhc zzhcVar, zzhe zzheVar, CharSequence charSequence) {
        super(zzheVar, "2.6.0");
        this.zza = zzhcVar;
    }

    @Override // com.google.android.libraries.places.internal.zzhd
    final int zzc(int i2) {
        return i2 + 1;
    }

    @Override // com.google.android.libraries.places.internal.zzhd
    final int zzd(int i2) {
        int length = "2.6.0".length();
        zzha.zzb(i2, length, FirebaseAnalytics.Param.INDEX);
        while (i2 < length) {
            if ("2.6.0".charAt(i2) == '.') {
                return i2;
            }
            i2++;
        }
        return -1;
    }
}
