package com.google.android.gms.measurement.internal;

import java.util.List;
/* loaded from: classes2.dex */
final class zzfz implements com.google.android.gms.internal.measurement.zzr {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzgb f6744a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfz(zzgb zzgbVar) {
        this.f6744a = zzgbVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzr
    public final void zza(int i2, String str, List list, boolean z, boolean z2) {
        zzey zzc;
        int i3 = i2 - 1;
        if (i3 == 0) {
            zzc = this.f6744a.f6809a.zzay().zzc();
        } else if (i3 == 1) {
            zzfa zzay = this.f6744a.f6809a.zzay();
            zzc = z ? zzay.zzh() : !z2 ? zzay.zze() : zzay.zzd();
        } else if (i3 == 3) {
            zzc = this.f6744a.f6809a.zzay().zzj();
        } else if (i3 != 4) {
            zzc = this.f6744a.f6809a.zzay().zzi();
        } else {
            zzfa zzay2 = this.f6744a.f6809a.zzay();
            zzc = z ? zzay2.zzm() : !z2 ? zzay2.zzl() : zzay2.zzk();
        }
        int size = list.size();
        if (size == 1) {
            zzc.zzb(str, list.get(0));
        } else if (size == 2) {
            zzc.zzc(str, list.get(0), list.get(1));
        } else if (size != 3) {
            zzc.zza(str);
        } else {
            zzc.zzd(str, list.get(0), list.get(1), list.get(2));
        }
    }
}
