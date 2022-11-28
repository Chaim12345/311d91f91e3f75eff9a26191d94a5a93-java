package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes2.dex */
final class zzfc implements zzfj {
    private final zzfj[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfc(zzfj... zzfjVarArr) {
        this.zza = zzfjVarArr;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfj
    public final zzfi zzb(Class cls) {
        zzfj[] zzfjVarArr = this.zza;
        for (int i2 = 0; i2 < 2; i2++) {
            zzfj zzfjVar = zzfjVarArr[i2];
            if (zzfjVar.zzc(cls)) {
                return zzfjVar.zzb(cls);
            }
        }
        String name = cls.getName();
        throw new UnsupportedOperationException(name.length() != 0 ? "No factory is available for message type: ".concat(name) : new String("No factory is available for message type: "));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfj
    public final boolean zzc(Class cls) {
        zzfj[] zzfjVarArr = this.zza;
        for (int i2 = 0; i2 < 2; i2++) {
            if (zzfjVarArr[i2].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
