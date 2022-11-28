package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zab implements zah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Activity f5829a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Bundle f5830b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Bundle f5831c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ DeferredLifecycleHelper f5832d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zab(DeferredLifecycleHelper deferredLifecycleHelper, Activity activity, Bundle bundle, Bundle bundle2) {
        this.f5832d = deferredLifecycleHelper;
        this.f5829a = activity;
        this.f5830b = bundle;
        this.f5831c = bundle2;
    }

    @Override // com.google.android.gms.dynamic.zah
    public final int zaa() {
        return 0;
    }

    @Override // com.google.android.gms.dynamic.zah
    public final void zab(LifecycleDelegate lifecycleDelegate) {
        LifecycleDelegate lifecycleDelegate2;
        lifecycleDelegate2 = this.f5832d.zaa;
        lifecycleDelegate2.onInflate(this.f5829a, this.f5830b, this.f5831c);
    }
}
