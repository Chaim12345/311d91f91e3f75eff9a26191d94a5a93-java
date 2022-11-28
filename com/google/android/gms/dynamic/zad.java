package com.google.android.gms.dynamic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zad implements zah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ FrameLayout f5835a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ LayoutInflater f5836b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ ViewGroup f5837c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Bundle f5838d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ DeferredLifecycleHelper f5839e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zad(DeferredLifecycleHelper deferredLifecycleHelper, FrameLayout frameLayout, LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f5839e = deferredLifecycleHelper;
        this.f5835a = frameLayout;
        this.f5836b = layoutInflater;
        this.f5837c = viewGroup;
        this.f5838d = bundle;
    }

    @Override // com.google.android.gms.dynamic.zah
    public final int zaa() {
        return 2;
    }

    @Override // com.google.android.gms.dynamic.zah
    public final void zab(LifecycleDelegate lifecycleDelegate) {
        LifecycleDelegate lifecycleDelegate2;
        this.f5835a.removeAllViews();
        FrameLayout frameLayout = this.f5835a;
        lifecycleDelegate2 = this.f5839e.zaa;
        frameLayout.addView(lifecycleDelegate2.onCreateView(this.f5836b, this.f5837c, this.f5838d));
    }
}
