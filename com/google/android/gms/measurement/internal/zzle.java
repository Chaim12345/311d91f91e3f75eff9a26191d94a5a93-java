package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzle implements Callable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzq f7026a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzll f7027b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzle(zzll zzllVar, zzq zzqVar) {
        this.f7027b = zzllVar;
        this.f7026a = zzqVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() {
        zzai C = this.f7027b.C((String) Preconditions.checkNotNull(this.f7026a.zza));
        zzah zzahVar = zzah.ANALYTICS_STORAGE;
        if (C.zzi(zzahVar) && zzai.zzb(this.f7026a.zzv).zzi(zzahVar)) {
            return this.f7027b.B(this.f7026a).zzu();
        }
        this.f7027b.zzay().zzj().zza("Analytics storage consent denied. Returning null app instance id");
        return null;
    }
}
