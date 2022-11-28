package com.google.android.gms.internal.mlkit_common;
/* loaded from: classes.dex */
final class zzak extends zzad {
    private final zzam zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzak(zzam zzamVar, int i2) {
        super(zzamVar.size(), i2);
        this.zza = zzamVar;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzad
    protected final Object a(int i2) {
        return this.zza.get(i2);
    }
}
