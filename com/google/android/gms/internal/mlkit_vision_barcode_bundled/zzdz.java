package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzdz implements zzdr {

    /* renamed from: a  reason: collision with root package name */
    final int f6418a;

    /* renamed from: b  reason: collision with root package name */
    final zzhf f6419b;

    /* renamed from: c  reason: collision with root package name */
    final boolean f6420c = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdz(zzef zzefVar, int i2, zzhf zzhfVar, boolean z, boolean z2) {
        this.f6418a = i2;
        this.f6419b = zzhfVar;
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        return this.f6418a - ((zzdz) obj).f6418a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdr
    public final int zza() {
        return this.f6418a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdr
    public final zzfk zzb(zzfk zzfkVar, zzfl zzflVar) {
        ((zzdw) zzfkVar).zzi((zzec) zzflVar);
        return zzfkVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdr
    public final zzfq zzc(zzfq zzfqVar, zzfq zzfqVar2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdr
    public final zzhf zzd() {
        return this.f6419b;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdr
    public final zzhg zze() {
        return this.f6419b.zza();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdr
    public final boolean zzf() {
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdr
    public final boolean zzg() {
        return false;
    }
}
