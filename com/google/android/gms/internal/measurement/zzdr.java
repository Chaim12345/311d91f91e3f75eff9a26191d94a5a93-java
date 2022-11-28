package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzdr extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Long f6014e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ String f6015f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ String f6016g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ Bundle f6017h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ boolean f6018i;

    /* renamed from: j  reason: collision with root package name */
    final /* synthetic */ boolean f6019j;

    /* renamed from: k  reason: collision with root package name */
    final /* synthetic */ zzee f6020k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdr(zzee zzeeVar, Long l2, String str, String str2, Bundle bundle, boolean z, boolean z2) {
        super(zzeeVar, true);
        this.f6020k = zzeeVar;
        this.f6014e = l2;
        this.f6015f = str;
        this.f6016g = str2;
        this.f6017h = bundle;
        this.f6018i = z;
        this.f6019j = z2;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        Long l2 = this.f6014e;
        long longValue = l2 == null ? this.f6026a : l2.longValue();
        zzccVar = this.f6020k.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).logEvent(this.f6015f, this.f6016g, this.f6017h, this.f6018i, this.f6019j, longValue);
    }
}
