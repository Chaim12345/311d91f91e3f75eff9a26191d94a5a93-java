package com.google.android.gms.internal.measurement;
/* loaded from: classes.dex */
final class zzjn {
    private final Object zza;
    private final int zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjn(Object obj, int i2) {
        this.zza = obj;
        this.zzb = i2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzjn) {
            zzjn zzjnVar = (zzjn) obj;
            return this.zza == zzjnVar.zza && this.zzb == zzjnVar.zzb;
        }
        return false;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * 65535) + this.zzb;
    }
}
