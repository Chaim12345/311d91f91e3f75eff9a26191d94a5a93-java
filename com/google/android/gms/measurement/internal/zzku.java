package com.google.android.gms.measurement.internal;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.WorkerThread;
/* loaded from: classes2.dex */
public final class zzku extends zzf {

    /* renamed from: b  reason: collision with root package name */
    protected final zzkt f7014b;

    /* renamed from: c  reason: collision with root package name */
    protected final zzks f7015c;

    /* renamed from: d  reason: collision with root package name */
    protected final zzkq f7016d;
    private Handler zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzku(zzgk zzgkVar) {
        super(zzgkVar);
        this.f7014b = new zzkt(this);
        this.f7015c = new zzks(this);
        this.f7016d = new zzkq(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void f(zzku zzkuVar, long j2) {
        zzkuVar.zzg();
        zzkuVar.zzm();
        zzkuVar.f6809a.zzay().zzj().zzb("Activity paused, time", Long.valueOf(j2));
        zzkuVar.f7016d.a(j2);
        if (zzkuVar.f6809a.zzf().zzu()) {
            zzkuVar.f7015c.b(j2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void g(zzku zzkuVar, long j2) {
        zzkuVar.zzg();
        zzkuVar.zzm();
        zzkuVar.f6809a.zzay().zzj().zzb("Activity resumed, time", Long.valueOf(j2));
        if (zzkuVar.f6809a.zzf().zzu() || zzkuVar.f6809a.zzm().zzl.zzb()) {
            zzkuVar.f7015c.c(j2);
        }
        zzkuVar.f7016d.b();
        zzkt zzktVar = zzkuVar.f7014b;
        zzktVar.f7013a.zzg();
        if (zzktVar.f7013a.f6809a.zzJ()) {
            zzktVar.b(zzktVar.f7013a.f6809a.zzav().currentTimeMillis(), false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzm() {
        zzg();
        if (this.zzd == null) {
            this.zzd = new com.google.android.gms.internal.measurement.zzby(Looper.getMainLooper());
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean c() {
        return false;
    }
}
