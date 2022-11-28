package com.google.android.gms.measurement.internal;

import android.os.Handler;
import androidx.annotation.WorkerThread;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayout;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzkq {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzku f7008a;
    private zzkp zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkq(zzku zzkuVar) {
        this.f7008a = zzkuVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void a(long j2) {
        Handler handler;
        this.zzb = new zzkp(this, this.f7008a.f6809a.zzav().currentTimeMillis(), j2);
        handler = this.f7008a.zzd;
        handler.postDelayed(this.zzb, SkeletonLayout.DEFAULT_SHIMMER_DURATION_IN_MILLIS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void b() {
        Handler handler;
        this.f7008a.zzg();
        zzkp zzkpVar = this.zzb;
        if (zzkpVar != null) {
            handler = this.f7008a.zzd;
            handler.removeCallbacks(zzkpVar);
        }
        this.f7008a.f6809a.zzm().zzl.zza(false);
    }
}
