package com.google.android.gms.common.api.internal;

import android.os.Bundle;
/* loaded from: classes.dex */
final class zza implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ LifecycleCallback f5715a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f5716b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzb f5717c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(zzb zzbVar, LifecycleCallback lifecycleCallback, String str) {
        this.f5717c = zzbVar;
        this.f5715a = lifecycleCallback;
        this.f5716b = str;
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
        zzb zzbVar = this.f5717c;
        i2 = zzbVar.zzc;
        if (i2 > 0) {
            LifecycleCallback lifecycleCallback = this.f5715a;
            bundle = zzbVar.zzd;
            if (bundle != null) {
                bundle3 = zzbVar.zzd;
                bundle2 = bundle3.getBundle(this.f5716b);
            } else {
                bundle2 = null;
            }
            lifecycleCallback.onCreate(bundle2);
        }
        i3 = this.f5717c.zzc;
        if (i3 >= 2) {
            this.f5715a.onStart();
        }
        i4 = this.f5717c.zzc;
        if (i4 >= 3) {
            this.f5715a.onResume();
        }
        i5 = this.f5717c.zzc;
        if (i5 >= 4) {
            this.f5715a.onStop();
        }
        i6 = this.f5717c.zzc;
        if (i6 >= 5) {
            this.f5715a.onDestroy();
        }
    }
}
