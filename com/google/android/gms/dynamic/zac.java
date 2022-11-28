package com.google.android.gms.dynamic;

import android.os.Bundle;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zac implements zah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Bundle f5833a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ DeferredLifecycleHelper f5834b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zac(DeferredLifecycleHelper deferredLifecycleHelper, Bundle bundle) {
        this.f5834b = deferredLifecycleHelper;
        this.f5833a = bundle;
    }

    @Override // com.google.android.gms.dynamic.zah
    public final int zaa() {
        return 1;
    }

    @Override // com.google.android.gms.dynamic.zah
    public final void zab(LifecycleDelegate lifecycleDelegate) {
        LifecycleDelegate lifecycleDelegate2;
        lifecycleDelegate2 = this.f5834b.zaa;
        lifecycleDelegate2.onCreate(this.f5833a);
    }
}
