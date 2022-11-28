package com.google.android.gms.measurement.internal;

import android.app.ActivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzoc;
import com.google.android.gms.internal.measurement.zzps;
import kotlinx.coroutines.DebugKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzkt {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzku f7013a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkt(zzku zzkuVar) {
        this.f7013a = zzkuVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void a() {
        this.f7013a.zzg();
        if (this.f7013a.f6809a.zzm().l(this.f7013a.f6809a.zzav().currentTimeMillis())) {
            this.f7013a.f6809a.zzm().zzg.zza(true);
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (runningAppProcessInfo.importance == 100) {
                this.f7013a.f6809a.zzay().zzj().zza("Detected application was in foreground");
                c(this.f7013a.f6809a.zzav().currentTimeMillis(), false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void b(long j2, boolean z) {
        this.f7013a.zzg();
        this.f7013a.zzm();
        if (this.f7013a.f6809a.zzm().l(j2)) {
            this.f7013a.f6809a.zzm().zzg.zza(true);
            zzps.zzc();
            if (this.f7013a.f6809a.zzf().zzs(null, zzen.zzaI)) {
                this.f7013a.f6809a.zzh().i();
            }
        }
        this.f7013a.f6809a.zzm().zzj.zzb(j2);
        if (this.f7013a.f6809a.zzm().zzg.zzb()) {
            c(j2, z);
        }
    }

    @VisibleForTesting
    @WorkerThread
    final void c(long j2, boolean z) {
        this.f7013a.zzg();
        if (this.f7013a.f6809a.zzJ()) {
            this.f7013a.f6809a.zzm().zzj.zzb(j2);
            this.f7013a.f6809a.zzay().zzj().zzb("Session started, time", Long.valueOf(this.f7013a.f6809a.zzav().elapsedRealtime()));
            Long valueOf = Long.valueOf(j2 / 1000);
            this.f7013a.f6809a.zzq().n(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_sid", valueOf, j2);
            this.f7013a.f6809a.zzm().zzg.zza(false);
            Bundle bundle = new Bundle();
            bundle.putLong("_sid", valueOf.longValue());
            if (this.f7013a.f6809a.zzf().zzs(null, zzen.zzaa) && z) {
                bundle.putLong("_aib", 1L);
            }
            this.f7013a.f6809a.zzq().g(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_s", j2, bundle);
            zzoc.zzc();
            if (this.f7013a.f6809a.zzf().zzs(null, zzen.zzad)) {
                String zza = this.f7013a.f6809a.zzm().zzo.zza();
                if (TextUtils.isEmpty(zza)) {
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString("_ffr", zza);
                this.f7013a.f6809a.zzq().g(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_ssr", j2, bundle2);
            }
        }
    }
}
