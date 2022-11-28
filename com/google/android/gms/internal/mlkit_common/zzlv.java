package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzlv extends LazyInstanceMap {
    private zzlv() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzlv(zzlu zzluVar) {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object a(Object obj) {
        zzle zzleVar = (zzle) obj;
        MlKitContext mlKitContext = MlKitContext.getInstance();
        return new zzll(mlKitContext.getApplicationContext(), (SharedPrefManager) mlKitContext.get(SharedPrefManager.class), new zzlf(MlKitContext.getInstance().getApplicationContext(), zzleVar), zzleVar.zzb());
    }
}
