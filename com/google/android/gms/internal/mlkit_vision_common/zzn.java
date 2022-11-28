package com.google.android.gms.internal.mlkit_vision_common;
/* loaded from: classes2.dex */
final class zzn extends zzh {
    private final zzp zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzn(zzp zzpVar, int i2) {
        super(zzpVar.size(), i2);
        this.zza = zzpVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzh
    protected final Object a(int i2) {
        return this.zza.get(i2);
    }
}
