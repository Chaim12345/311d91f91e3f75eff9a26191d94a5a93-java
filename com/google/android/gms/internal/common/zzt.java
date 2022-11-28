package com.google.android.gms.internal.common;

import com.google.firebase.analytics.FirebaseAnalytics;
/* loaded from: classes.dex */
final class zzt extends zzw {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzu f5855e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzt(zzu zzuVar, zzx zzxVar, CharSequence charSequence) {
        super(zzxVar, charSequence);
        this.f5855e = zzuVar;
    }

    @Override // com.google.android.gms.internal.common.zzw
    final int c(int i2) {
        return i2 + 1;
    }

    @Override // com.google.android.gms.internal.common.zzw
    final int d(int i2) {
        zzo zzoVar = this.f5855e.f5856a;
        CharSequence charSequence = this.f5859a;
        int length = charSequence.length();
        zzs.zzb(i2, length, FirebaseAnalytics.Param.INDEX);
        while (i2 < length) {
            if (zzoVar.zza(charSequence.charAt(i2))) {
                return i2;
            }
            i2++;
        }
        return -1;
    }
}
