package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import javax.annotation.CheckForNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzcb extends zzcc {

    /* renamed from: a  reason: collision with root package name */
    final transient int f6284a;

    /* renamed from: b  reason: collision with root package name */
    final transient int f6285b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzcc f6286c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcb(zzcc zzccVar, int i2, int i3) {
        this.f6286c = zzccVar;
        this.f6284a = i2;
        this.f6285b = i3;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbx
    final int b() {
        return this.f6286c.c() + this.f6284a + this.f6285b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbx
    public final int c() {
        return this.f6286c.c() + this.f6284a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbx
    @CheckForNull
    public final Object[] d() {
        return this.f6286c.d();
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzaq.zza(i2, this.f6285b, FirebaseAnalytics.Param.INDEX);
        return this.f6286c.get(i2 + this.f6284a);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f6285b;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcc, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i2, int i3) {
        return subList(i2, i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcc
    public final zzcc zzf(int i2, int i3) {
        zzaq.zzc(i2, i3, this.f6285b);
        zzcc zzccVar = this.f6286c;
        int i4 = this.f6284a;
        return zzccVar.subList(i2 + i4, i3 + i4);
    }
}
