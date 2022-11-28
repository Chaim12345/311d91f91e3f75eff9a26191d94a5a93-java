package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzjv implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AtomicReference f6966a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f6967b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ String f6968c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzq f6969d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzke f6970e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjv(zzke zzkeVar, AtomicReference atomicReference, String str, String str2, String str3, zzq zzqVar) {
        this.f6970e = zzkeVar;
        this.f6966a = atomicReference;
        this.f6967b = str2;
        this.f6968c = str3;
        this.f6969d = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AtomicReference atomicReference;
        zzke zzkeVar;
        zzeq zzeqVar;
        AtomicReference atomicReference2;
        List zzg;
        synchronized (this.f6966a) {
            try {
                zzkeVar = this.f6970e;
                zzeqVar = zzkeVar.zzb;
            } catch (RemoteException e2) {
                this.f6970e.f6809a.zzay().zzd().zzd("(legacy) Failed to get conditional properties; remote exception", null, this.f6967b, e2);
                this.f6966a.set(Collections.emptyList());
                atomicReference = this.f6966a;
            }
            if (zzeqVar == null) {
                zzkeVar.f6809a.zzay().zzd().zzd("(legacy) Failed to get conditional properties; not connected to service", null, this.f6967b, this.f6968c);
                this.f6966a.set(Collections.emptyList());
                this.f6966a.notify();
                return;
            }
            if (TextUtils.isEmpty(null)) {
                Preconditions.checkNotNull(this.f6969d);
                atomicReference2 = this.f6966a;
                zzg = zzeqVar.zzf(this.f6967b, this.f6968c, this.f6969d);
            } else {
                atomicReference2 = this.f6966a;
                zzg = zzeqVar.zzg(null, this.f6967b, this.f6968c);
            }
            atomicReference2.set(zzg);
            this.f6970e.zzQ();
            atomicReference = this.f6966a;
            atomicReference.notify();
        }
    }
}
