package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzlg implements zzls {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzll f7032a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzlg(zzll zzllVar) {
        this.f7032a = zzllVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzls
    public final void zza(String str, String str2, Bundle bundle) {
        zzgk zzgkVar;
        zzgk zzgkVar2;
        if (!TextUtils.isEmpty(str)) {
            this.f7032a.zzaz().zzp(new zzlf(this, str, "_err", bundle));
            return;
        }
        zzll zzllVar = this.f7032a;
        zzgkVar = zzllVar.zzn;
        if (zzgkVar != null) {
            zzgkVar2 = zzllVar.zzn;
            zzgkVar2.zzay().zzd().zzb("AppId not known when logging event", "_err");
        }
    }
}
