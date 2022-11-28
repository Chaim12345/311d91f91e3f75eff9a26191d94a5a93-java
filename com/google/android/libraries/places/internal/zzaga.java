package com.google.android.libraries.places.internal;

import sun.misc.Unsafe;
/* loaded from: classes2.dex */
final class zzaga extends zzagc {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaga(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.libraries.places.internal.zzagc
    public final double zza(Object obj, long j2) {
        return Double.longBitsToDouble(zzk(obj, j2));
    }

    @Override // com.google.android.libraries.places.internal.zzagc
    public final float zzb(Object obj, long j2) {
        return Float.intBitsToFloat(zzj(obj, j2));
    }

    @Override // com.google.android.libraries.places.internal.zzagc
    public final void zzc(Object obj, long j2, boolean z) {
        if (zzagd.zzb) {
            zzagd.zzD(obj, j2, r3 ? (byte) 1 : (byte) 0);
        } else {
            zzagd.zzE(obj, j2, r3 ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.libraries.places.internal.zzagc
    public final void zzd(Object obj, long j2, byte b2) {
        if (zzagd.zzb) {
            zzagd.zzD(obj, j2, b2);
        } else {
            zzagd.zzE(obj, j2, b2);
        }
    }

    @Override // com.google.android.libraries.places.internal.zzagc
    public final void zze(Object obj, long j2, double d2) {
        zzo(obj, j2, Double.doubleToLongBits(d2));
    }

    @Override // com.google.android.libraries.places.internal.zzagc
    public final void zzf(Object obj, long j2, float f2) {
        zzn(obj, j2, Float.floatToIntBits(f2));
    }

    @Override // com.google.android.libraries.places.internal.zzagc
    public final boolean zzg(Object obj, long j2) {
        return zzagd.zzb ? zzagd.zzt(obj, j2) : zzagd.zzu(obj, j2);
    }
}
