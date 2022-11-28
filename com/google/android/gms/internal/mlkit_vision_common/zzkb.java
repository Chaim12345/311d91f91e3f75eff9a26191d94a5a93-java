package com.google.android.gms.internal.mlkit_vision_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzkb extends LazyInstanceMap {
    private zzkb() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzkb(zzka zzkaVar) {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object a(Object obj) {
        zzjn zzjnVar = (zzjn) obj;
        MlKitContext mlKitContext = MlKitContext.getInstance();
        return new zzjt(mlKitContext.getApplicationContext(), (SharedPrefManager) mlKitContext.get(SharedPrefManager.class), new zzjo(MlKitContext.getInstance().getApplicationContext(), zzjnVar), zzjnVar.zzb());
    }
}
