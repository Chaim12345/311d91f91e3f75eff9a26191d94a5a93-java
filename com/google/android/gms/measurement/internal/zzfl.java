package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes2.dex */
public final class zzfl {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzfp f6733a;
    private final String zzb;
    private final long zzc;
    private boolean zzd;
    private long zze;

    public zzfl(zzfp zzfpVar, String str, long j2) {
        this.f6733a = zzfpVar;
        Preconditions.checkNotEmpty(str);
        this.zzb = str;
        this.zzc = j2;
    }

    @WorkerThread
    public final long zza() {
        if (!this.zzd) {
            this.zzd = true;
            this.zze = this.f6733a.e().getLong(this.zzb, this.zzc);
        }
        return this.zze;
    }

    @WorkerThread
    public final void zzb(long j2) {
        SharedPreferences.Editor edit = this.f6733a.e().edit();
        edit.putLong(this.zzb, j2);
        edit.apply();
        this.zze = j2;
    }
}
