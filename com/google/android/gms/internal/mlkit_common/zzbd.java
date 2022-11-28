package com.google.android.gms.internal.mlkit_common;
/* loaded from: classes.dex */
final class zzbd implements zzbi {
    private final int zza;
    private final zzbh zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbd(int i2, zzbh zzbhVar) {
        this.zza = i2;
        this.zzb = zzbhVar;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return zzbi.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzbi) {
            zzbi zzbiVar = (zzbi) obj;
            return this.zza == zzbiVar.zza() && this.zzb.equals(zzbiVar.zzb());
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

    @Override // com.google.android.gms.internal.mlkit_common.zzbi
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzbi
    public final zzbh zzb() {
        return this.zzb;
    }
}
