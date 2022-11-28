package com.google.android.gms.internal.common;

import com.google.firebase.analytics.FirebaseAnalytics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzai<E> extends zzag<E> {

    /* renamed from: b  reason: collision with root package name */
    static final zzag f5852b = new zzai(new Object[0], 0);

    /* renamed from: a  reason: collision with root package name */
    final transient Object[] f5853a;
    private final transient int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzai(Object[] objArr, int i2) {
        this.f5853a = objArr;
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.common.zzag, com.google.android.gms.internal.common.zzac
    final int a(Object[] objArr, int i2) {
        System.arraycopy(this.f5853a, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.common.zzac
    final int b() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.common.zzac
    public final int c() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.common.zzac
    public final boolean d() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.common.zzac
    public final Object[] e() {
        return this.f5853a;
    }

    @Override // java.util.List
    public final E get(int i2) {
        zzs.zza(i2, this.zzc, FirebaseAnalytics.Param.INDEX);
        E e2 = (E) this.f5853a[i2];
        e2.getClass();
        return e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }
}
