package com.google.android.libraries.places.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzhq extends zzhg {
    private final zzhs zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhq(zzhs zzhsVar, int i2) {
        super(zzhsVar.size(), i2);
        this.zza = zzhsVar;
    }

    @Override // com.google.android.libraries.places.internal.zzhg
    protected final Object zza(int i2) {
        return this.zza.get(i2);
    }
}
