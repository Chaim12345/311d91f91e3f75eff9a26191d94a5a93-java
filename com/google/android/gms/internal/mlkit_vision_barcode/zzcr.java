package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.analytics.FirebaseAnalytics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzcr extends zzcc {

    /* renamed from: b  reason: collision with root package name */
    static final zzcc f6289b = new zzcr(new Object[0], 0);

    /* renamed from: a  reason: collision with root package name */
    final transient Object[] f6290a;
    private final transient int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcr(Object[] objArr, int i2) {
        this.f6290a = objArr;
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcc, com.google.android.gms.internal.mlkit_vision_barcode.zzbx
    final int a(Object[] objArr, int i2) {
        System.arraycopy(this.f6290a, 0, objArr, i2, this.zzc);
        return i2 + this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbx
    final int b() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbx
    public final int c() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbx
    public final Object[] d() {
        return this.f6290a;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzaq.zza(i2, this.zzc, FirebaseAnalytics.Param.INDEX);
        Object obj = this.f6290a[i2];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }
}
