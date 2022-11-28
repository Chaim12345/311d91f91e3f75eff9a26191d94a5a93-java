package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import javax.annotation.CheckForNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzo extends zzp {

    /* renamed from: a  reason: collision with root package name */
    final transient int f6577a;

    /* renamed from: b  reason: collision with root package name */
    final transient int f6578b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzp f6579c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(zzp zzpVar, int i2, int i3) {
        this.f6579c = zzpVar;
        this.f6577a = i2;
        this.f6578b = i3;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzl
    final int b() {
        return this.f6579c.c() + this.f6577a + this.f6578b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_common.zzl
    public final int c() {
        return this.f6579c.c() + this.f6577a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_common.zzl
    @CheckForNull
    public final Object[] d() {
        return this.f6579c.d();
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzf.zza(i2, this.f6578b, FirebaseAnalytics.Param.INDEX);
        return this.f6579c.get(i2 + this.f6577a);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f6578b;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzp, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i2, int i3) {
        return subList(i2, i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzp
    public final zzp zzf(int i2, int i3) {
        zzf.zzc(i2, i3, this.f6578b);
        zzp zzpVar = this.f6579c;
        int i4 = this.f6577a;
        return zzpVar.subList(i2 + i4, i3 + i4);
    }
}
