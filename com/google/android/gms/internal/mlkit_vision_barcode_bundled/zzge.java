package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzge extends zzgl {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzge(int i2) {
        super(i2, null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgl
    public final void zza() {
        if (!zzj()) {
            for (int i2 = 0; i2 < zzb(); i2++) {
                ((zzdr) zzg(i2).getKey()).zzg();
            }
            for (Map.Entry entry : zzc()) {
                ((zzdr) entry.getKey()).zzg();
            }
        }
        super.zza();
    }
}
