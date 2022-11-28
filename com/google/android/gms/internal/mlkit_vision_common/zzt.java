package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.analytics.FirebaseAnalytics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzt extends zzp {

    /* renamed from: b  reason: collision with root package name */
    static final zzp f6580b = new zzt(new Object[0], 0);

    /* renamed from: a  reason: collision with root package name */
    final transient Object[] f6581a;
    private final transient int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzt(Object[] objArr, int i2) {
        this.f6581a = objArr;
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzp, com.google.android.gms.internal.mlkit_vision_common.zzl
    final int a(Object[] objArr, int i2) {
        System.arraycopy(this.f6581a, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzl
    final int b() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_common.zzl
    public final int c() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_common.zzl
    public final Object[] d() {
        return this.f6581a;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzf.zza(i2, this.zzc, FirebaseAnalytics.Param.INDEX);
        Object obj = this.f6581a[i2];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }
}
