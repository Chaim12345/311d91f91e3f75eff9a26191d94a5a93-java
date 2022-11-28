package com.google.mlkit.vision.common;
/* loaded from: classes2.dex */
final class zza extends PointF3D {
    private final float zza;
    private final float zzb;
    private final float zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(float f2, float f3, float f4) {
        this.zza = f2;
        this.zzb = f3;
        this.zzc = f4;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PointF3D) {
            PointF3D pointF3D = (PointF3D) obj;
            if (Float.floatToIntBits(this.zza) == Float.floatToIntBits(pointF3D.getX()) && Float.floatToIntBits(this.zzb) == Float.floatToIntBits(pointF3D.getY()) && Float.floatToIntBits(this.zzc) == Float.floatToIntBits(pointF3D.getZ())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.mlkit.vision.common.PointF3D
    public final float getX() {
        return this.zza;
    }

    @Override // com.google.mlkit.vision.common.PointF3D
    public final float getY() {
        return this.zzb;
    }

    @Override // com.google.mlkit.vision.common.PointF3D
    public final float getZ() {
        return this.zzc;
    }

    public final int hashCode() {
        return ((((Float.floatToIntBits(this.zza) ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.zzb)) * 1000003) ^ Float.floatToIntBits(this.zzc);
    }

    public final String toString() {
        float f2 = this.zza;
        float f3 = this.zzb;
        float f4 = this.zzc;
        StringBuilder sb = new StringBuilder(65);
        sb.append("PointF3D{x=");
        sb.append(f2);
        sb.append(", y=");
        sb.append(f3);
        sb.append(", z=");
        sb.append(f4);
        sb.append("}");
        return sb.toString();
    }
}
