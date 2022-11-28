package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzds extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ String f6021e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ String f6022f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Object f6023g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ boolean f6024h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ zzee f6025i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzds(zzee zzeeVar, String str, String str2, Object obj, boolean z) {
        super(zzeeVar, true);
        this.f6025i = zzeeVar;
        this.f6021e = str;
        this.f6022f = str2;
        this.f6023g = obj;
        this.f6024h = z;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f6025i.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).setUserProperty(this.f6021e, this.f6022f, ObjectWrapper.wrap(this.f6023g), this.f6024h, this.f6026a);
    }
}
