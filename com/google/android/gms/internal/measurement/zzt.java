package com.google.android.gms.internal.measurement;

import androidx.core.app.NotificationCompat;
import java.util.List;
/* loaded from: classes.dex */
public final class zzt extends zzai {
    private final zzr zza;

    public zzt(zzr zzrVar) {
        super("internal.logger");
        this.zza = zzrVar;
        this.f5925b.put("log", new zzs(this, false, true));
        this.f5925b.put(NotificationCompat.GROUP_KEY_SILENT, new zzp(this, NotificationCompat.GROUP_KEY_SILENT));
        ((zzai) this.f5925b.get(NotificationCompat.GROUP_KEY_SILENT)).zzr("log", new zzs(this, true, true));
        this.f5925b.put("unmonitored", new zzq(this, "unmonitored"));
        ((zzai) this.f5925b.get("unmonitored")).zzr("log", new zzs(this, false, false));
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzgVar, List list) {
        return zzap.zzf;
    }
}
