package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.lang.reflect.Field;
import sun.misc.Unsafe;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class zzgy {

    /* renamed from: a  reason: collision with root package name */
    final Unsafe f6440a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgy(Unsafe unsafe) {
        this.f6440a = unsafe;
    }

    public abstract double zza(Object obj, long j2);

    public abstract float zzb(Object obj, long j2);

    public abstract void zzc(Object obj, long j2, boolean z);

    public abstract void zzd(Object obj, long j2, byte b2);

    public abstract void zze(Object obj, long j2, double d2);

    public abstract void zzf(Object obj, long j2, float f2);

    public abstract boolean zzg(Object obj, long j2);

    public final int zzh(Class cls) {
        return this.f6440a.arrayBaseOffset(cls);
    }

    public final int zzi(Class cls) {
        return this.f6440a.arrayIndexScale(cls);
    }

    public final int zzj(Object obj, long j2) {
        return this.f6440a.getInt(obj, j2);
    }

    public final long zzk(Object obj, long j2) {
        return this.f6440a.getLong(obj, j2);
    }

    public final long zzl(Field field) {
        return this.f6440a.objectFieldOffset(field);
    }

    public final Object zzm(Object obj, long j2) {
        return this.f6440a.getObject(obj, j2);
    }

    public final void zzn(Object obj, long j2, int i2) {
        this.f6440a.putInt(obj, j2, i2);
    }

    public final void zzo(Object obj, long j2, long j3) {
        this.f6440a.putLong(obj, j2, j3);
    }

    public final void zzp(Object obj, long j2, Object obj2) {
        this.f6440a.putObject(obj, j2, obj2);
    }
}
