package com.google.android.gms.internal.mlkit_vision_barcode;
/* loaded from: classes2.dex */
final class zzca extends zzas {
    private final zzcc zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzca(zzcc zzccVar, int i2) {
        super(zzccVar.size(), i2);
        this.zza = zzccVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzas
    protected final Object a(int i2) {
        return this.zza.get(i2);
    }
}
