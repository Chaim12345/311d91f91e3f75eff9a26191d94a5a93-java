package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zznw extends LazyInstanceMap {
    private zznw() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zznw(zznv zznvVar) {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object a(Object obj) {
        zzne zzneVar = (zzne) obj;
        MlKitContext mlKitContext = MlKitContext.getInstance();
        return new zznm(mlKitContext.getApplicationContext(), (SharedPrefManager) mlKitContext.get(SharedPrefManager.class), new zznf(MlKitContext.getInstance().getApplicationContext(), zzneVar), zzneVar.zzb());
    }
}
