package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.analytics.FirebaseAnalytics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzar extends zzam {

    /* renamed from: b  reason: collision with root package name */
    static final zzam f6126b = new zzar(new Object[0], 0);

    /* renamed from: a  reason: collision with root package name */
    final transient Object[] f6127a;
    private final transient int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzar(Object[] objArr, int i2) {
        this.f6127a = objArr;
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzam, com.google.android.gms.internal.mlkit_common.zzai
    final int a(Object[] objArr, int i2) {
        System.arraycopy(this.f6127a, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzai
    final int b() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_common.zzai
    public final int c() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_common.zzai
    public final Object[] d() {
        return this.f6127a;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzab.zza(i2, this.zzc, FirebaseAnalytics.Param.INDEX);
        Object obj = this.f6127a[i2];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }
}
