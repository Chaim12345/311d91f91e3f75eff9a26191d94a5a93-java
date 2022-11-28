package com.google.android.play.core.internal;

import java.lang.reflect.Field;
/* loaded from: classes2.dex */
public class zzbv {
    private final Object zza;
    private final Field zzb;
    private final Class zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbv(Object obj, Field field, Class cls) {
        this.zza = obj;
        this.zzb = field;
        this.zzc = cls;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Field a() {
        return this.zzb;
    }

    public final Object zzc() {
        try {
            return this.zzc.cast(this.zzb.get(this.zza));
        } catch (Exception e2) {
            throw new zzbx(String.format("Failed to get value of field %s of type %s on object of type %s", this.zzb.getName(), this.zza.getClass().getName(), this.zzc.getName()), e2);
        }
    }

    public final void zze(Object obj) {
        try {
            this.zzb.set(this.zza, obj);
        } catch (Exception e2) {
            throw new zzbx(String.format("Failed to set value of field %s of type %s on object of type %s", this.zzb.getName(), this.zza.getClass().getName(), this.zzc.getName()), e2);
        }
    }
}
