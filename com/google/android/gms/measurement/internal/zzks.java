package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzoo;
import kotlinx.coroutines.DebugKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzks {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    protected long f7010a;
    @VisibleForTesting

    /* renamed from: b  reason: collision with root package name */
    protected long f7011b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzku f7012c;
    private final zzap zzd;

    public zzks(zzku zzkuVar) {
        this.f7012c = zzkuVar;
        this.zzd = new zzkr(this, zzkuVar.f6809a);
        long elapsedRealtime = zzkuVar.f6809a.zzav().elapsedRealtime();
        this.f7010a = elapsedRealtime;
        this.f7011b = elapsedRealtime;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        this.zzd.b();
        this.f7010a = 0L;
        this.f7011b = 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void b(long j2) {
        this.zzd.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void c(long j2) {
        this.f7012c.zzg();
        this.zzd.b();
        this.f7010a = j2;
        this.f7011b = j2;
    }

    @WorkerThread
    public final boolean zzd(boolean z, boolean z2, long j2) {
        this.f7012c.zzg();
        this.f7012c.zza();
        zzoo.zzc();
        if (!this.f7012c.f6809a.zzf().zzs(null, zzen.zzae) || this.f7012c.f6809a.zzJ()) {
            this.f7012c.f6809a.zzm().zzj.zzb(this.f7012c.f6809a.zzav().currentTimeMillis());
        }
        long j3 = j2 - this.f7010a;
        if (!z && j3 < 1000) {
            this.f7012c.f6809a.zzay().zzj().zzb("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(j3));
            return false;
        }
        if (!z2) {
            j3 = j2 - this.f7011b;
            this.f7011b = j2;
        }
        this.f7012c.f6809a.zzay().zzj().zzb("Recording user engagement, ms", Long.valueOf(j3));
        Bundle bundle = new Bundle();
        bundle.putLong("_et", j3);
        zzlt.zzK(this.f7012c.f6809a.zzs().zzj(!this.f7012c.f6809a.zzf().zzu()), bundle, true);
        if (!z2) {
            this.f7012c.f6809a.zzq().f(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_e", bundle);
        }
        this.f7010a = j2;
        this.zzd.b();
        this.zzd.zzd(3600000L);
        return true;
    }
}
