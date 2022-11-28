package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Map;
/* loaded from: classes2.dex */
final class zzbq extends zzbf {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzbs f6274a;
    private final Object zzb;
    private int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbq(zzbs zzbsVar, int i2) {
        this.f6274a = zzbsVar;
        this.zzb = zzbs.g(zzbsVar, i2);
        this.zzc = i2;
    }

    private final void zza() {
        int zzv;
        int i2 = this.zzc;
        if (i2 == -1 || i2 >= this.f6274a.size() || !zzam.zza(this.zzb, zzbs.g(this.f6274a, this.zzc))) {
            zzv = this.f6274a.zzv(this.zzb);
            this.zzc = zzv;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbf, java.util.Map.Entry
    public final Object getKey() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbf, java.util.Map.Entry
    public final Object getValue() {
        Map l2 = this.f6274a.l();
        if (l2 != null) {
            return l2.get(this.zzb);
        }
        zza();
        int i2 = this.zzc;
        if (i2 == -1) {
            return null;
        }
        return zzbs.j(this.f6274a, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbf, java.util.Map.Entry
    public final Object setValue(Object obj) {
        Map l2 = this.f6274a.l();
        if (l2 != null) {
            return l2.put(this.zzb, obj);
        }
        zza();
        int i2 = this.zzc;
        if (i2 == -1) {
            this.f6274a.put(this.zzb, obj);
            return null;
        }
        Object j2 = zzbs.j(this.f6274a, i2);
        zzbs.m(this.f6274a, this.zzc, obj);
        return j2;
    }
}
