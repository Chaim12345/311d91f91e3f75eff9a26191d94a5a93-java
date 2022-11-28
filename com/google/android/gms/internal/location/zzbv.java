package com.google.android.gms.internal.location;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzbv extends zzbt {
    private final zzbx zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbv(zzbx zzbxVar, int i2) {
        super(zzbxVar.size(), i2);
        this.zza = zzbxVar;
    }

    @Override // com.google.android.gms.internal.location.zzbt
    protected final Object a(int i2) {
        return this.zza.get(i2);
    }
}
