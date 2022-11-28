package com.google.android.gms.internal.location;

import com.google.firebase.analytics.FirebaseAnalytics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzby extends zzbx {

    /* renamed from: b  reason: collision with root package name */
    static final zzbx f5888b = new zzby(new Object[0], 0);

    /* renamed from: a  reason: collision with root package name */
    final transient Object[] f5889a;
    private final transient int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzby(Object[] objArr, int i2) {
        this.f5889a = objArr;
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.location.zzbx, com.google.android.gms.internal.location.zzbu
    final int a(Object[] objArr, int i2) {
        System.arraycopy(this.f5889a, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.location.zzbu
    final int b() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.location.zzbu
    public final int c() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.location.zzbu
    public final boolean d() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.location.zzbu
    public final Object[] e() {
        return this.f5889a;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzbr.zza(i2, this.zzc, FirebaseAnalytics.Param.INDEX);
        Object obj = this.f5889a[i2];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }
}
