package com.google.android.gms.internal.mlkit_vision_common;
/* loaded from: classes2.dex */
final class zzac implements zzah {
    private final int zza;
    private final zzag zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzac(int i2, zzag zzagVar) {
        this.zza = i2;
        this.zzb = zzagVar;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return zzah.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzah) {
            zzah zzahVar = (zzah) obj;
            return this.zza == zzahVar.zza() && this.zzb.equals(zzahVar.zzb());
        }
        return false;
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (this.zza ^ 14552422) + (this.zzb.hashCode() ^ 2041407134);
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.zza + "intEncoding=" + this.zzb + ')';
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzah
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzah
    public final zzag zzb() {
        return this.zzb;
    }
}
