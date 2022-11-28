package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import kotlin.jvm.internal.LongCompanionObject;
/* loaded from: classes2.dex */
public final class zzfn {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    final String f6734a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzfp f6735b;
    private final String zzc;
    private final String zzd;
    private final long zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfn(zzfp zzfpVar, String str, long j2, zzfm zzfmVar) {
        this.f6735b = zzfpVar;
        Preconditions.checkNotEmpty("health_monitor");
        Preconditions.checkArgument(j2 > 0);
        this.f6734a = "health_monitor:start";
        this.zzc = "health_monitor:count";
        this.zzd = "health_monitor:value";
        this.zze = j2;
    }

    @WorkerThread
    private final long zzc() {
        return this.f6735b.e().getLong(this.f6734a, 0L);
    }

    @WorkerThread
    private final void zzd() {
        this.f6735b.zzg();
        long currentTimeMillis = this.f6735b.f6809a.zzav().currentTimeMillis();
        SharedPreferences.Editor edit = this.f6735b.e().edit();
        edit.remove(this.zzc);
        edit.remove(this.zzd);
        edit.putLong(this.f6734a, currentTimeMillis);
        edit.apply();
    }

    @WorkerThread
    public final Pair zza() {
        long abs;
        this.f6735b.zzg();
        this.f6735b.zzg();
        long zzc = zzc();
        if (zzc == 0) {
            zzd();
            abs = 0;
        } else {
            abs = Math.abs(zzc - this.f6735b.f6809a.zzav().currentTimeMillis());
        }
        long j2 = this.zze;
        if (abs < j2) {
            return null;
        }
        if (abs > j2 + j2) {
            zzd();
            return null;
        }
        String string = this.f6735b.e().getString(this.zzd, null);
        long j3 = this.f6735b.e().getLong(this.zzc, 0L);
        zzd();
        return (string == null || j3 <= 0) ? zzfp.f6737b : new Pair(string, Long.valueOf(j3));
    }

    @WorkerThread
    public final void zzb(String str, long j2) {
        this.f6735b.zzg();
        if (zzc() == 0) {
            zzd();
        }
        if (str == null) {
            str = "";
        }
        long j3 = this.f6735b.e().getLong(this.zzc, 0L);
        if (j3 <= 0) {
            SharedPreferences.Editor edit = this.f6735b.e().edit();
            edit.putString(this.zzd, str);
            edit.putLong(this.zzc, 1L);
            edit.apply();
            return;
        }
        long nextLong = this.f6735b.f6809a.zzv().i().nextLong();
        long j4 = j3 + 1;
        long j5 = LongCompanionObject.MAX_VALUE / j4;
        SharedPreferences.Editor edit2 = this.f6735b.e().edit();
        if ((LongCompanionObject.MAX_VALUE & nextLong) < j5) {
            edit2.putString(this.zzd, str);
        }
        edit2.putLong(this.zzc, j4);
        edit2.apply();
    }
}
