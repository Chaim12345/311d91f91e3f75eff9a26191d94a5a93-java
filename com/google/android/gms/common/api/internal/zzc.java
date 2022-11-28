package com.google.android.gms.common.api.internal;

import android.os.Bundle;
/* loaded from: classes.dex */
final class zzc implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ LifecycleCallback f5718a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f5719b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzd f5720c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzc(zzd zzdVar, LifecycleCallback lifecycleCallback, String str) {
        this.f5720c = zzdVar;
        this.f5718a = lifecycleCallback;
        this.f5719b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Bundle bundle;
        Bundle bundle2;
        Bundle bundle3;
        zzd zzdVar = this.f5720c;
        i2 = zzdVar.zzc;
        if (i2 > 0) {
            LifecycleCallback lifecycleCallback = this.f5718a;
            bundle = zzdVar.zzd;
            if (bundle != null) {
                bundle3 = zzdVar.zzd;
                bundle2 = bundle3.getBundle(this.f5719b);
            } else {
                bundle2 = null;
            }
            lifecycleCallback.onCreate(bundle2);
        }
        i3 = this.f5720c.zzc;
        if (i3 >= 2) {
            this.f5718a.onStart();
        }
        i4 = this.f5720c.zzc;
        if (i4 >= 3) {
            this.f5718a.onResume();
        }
        i5 = this.f5720c.zzc;
        if (i5 >= 4) {
            this.f5718a.onStop();
        }
        i6 = this.f5720c.zzc;
        if (i6 >= 5) {
            this.f5718a.onDestroy();
        }
    }
}
