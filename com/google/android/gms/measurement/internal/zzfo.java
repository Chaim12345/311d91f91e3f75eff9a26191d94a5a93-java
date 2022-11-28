package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes2.dex */
public final class zzfo {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzfp f6736a;
    private final String zzb;
    private boolean zzc;
    private String zzd;

    public zzfo(zzfp zzfpVar, String str, String str2) {
        this.f6736a = zzfpVar;
        Preconditions.checkNotEmpty(str);
        this.zzb = str;
    }

    @WorkerThread
    public final String zza() {
        if (!this.zzc) {
            this.zzc = true;
            this.zzd = this.f6736a.e().getString(this.zzb, null);
        }
        return this.zzd;
    }

    @WorkerThread
    public final void zzb(String str) {
        SharedPreferences.Editor edit = this.f6736a.e().edit();
        edit.putString(this.zzb, str);
        edit.apply();
        this.zzd = str;
    }
}
