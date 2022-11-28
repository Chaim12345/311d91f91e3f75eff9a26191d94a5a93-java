package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzdf extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ String f5985e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ String f5986f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ boolean f5987g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ zzbz f5988h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ zzee f5989i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdf(zzee zzeeVar, String str, String str2, boolean z, zzbz zzbzVar) {
        super(zzeeVar, true);
        this.f5989i = zzeeVar;
        this.f5985e = str;
        this.f5986f = str2;
        this.f5987g = z;
        this.f5988h = zzbzVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    protected final void a() {
        this.f5988h.zzd(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    final void zza() {
        zzcc zzccVar;
        zzccVar = this.f5989i.zzj;
        ((zzcc) Preconditions.checkNotNull(zzccVar)).getUserProperties(this.f5985e, this.f5986f, this.f5987g, this.f5988h);
    }
}
