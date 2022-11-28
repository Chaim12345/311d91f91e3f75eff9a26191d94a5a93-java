package com.google.android.gms.internal.measurement;

import android.app.Activity;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzcr extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Activity f5953e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ String f5954f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ String f5955g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ zzee f5956h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcr(zzee zzeeVar, Activity activity, String str, String str2) {
        super(zzeeVar, true);
        this.f5956h = zzeeVar;
        this.f5953e = activity;
        this.f5954f = str;
        this.f5955g = str2;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f5956h.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).setCurrentScreen(ObjectWrapper.wrap(this.f5953e), this.f5954f, this.f5955g, this.f6026a);
    }
}
