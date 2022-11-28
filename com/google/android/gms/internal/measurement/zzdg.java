package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzdg extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ String f5990e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ Object f5991f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ zzee f5992g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdg(zzee zzeeVar, boolean z, int i2, String str, Object obj, Object obj2, Object obj3) {
        super(zzeeVar, false);
        this.f5992g = zzeeVar;
        this.f5990e = str;
        this.f5991f = obj;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f5992g.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).logHealthData(5, this.f5990e, ObjectWrapper.wrap(this.f5991f), ObjectWrapper.wrap(null), ObjectWrapper.wrap(null));
    }
}
