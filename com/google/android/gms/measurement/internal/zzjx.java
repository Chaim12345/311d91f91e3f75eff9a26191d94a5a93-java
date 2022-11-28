package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzjx implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AtomicReference f6976a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f6977b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ String f6978c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzq f6979d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ boolean f6980e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzke f6981f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjx(zzke zzkeVar, AtomicReference atomicReference, String str, String str2, String str3, zzq zzqVar, boolean z) {
        this.f6981f = zzkeVar;
        this.f6976a = atomicReference;
        this.f6977b = str2;
        this.f6978c = str3;
        this.f6979d = zzqVar;
        this.f6980e = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AtomicReference atomicReference;
        zzke zzkeVar;
        zzeq zzeqVar;
        AtomicReference atomicReference2;
        List zzi;
        synchronized (this.f6976a) {
            try {
                zzkeVar = this.f6981f;
                zzeqVar = zzkeVar.zzb;
            } catch (RemoteException e2) {
                this.f6981f.f6809a.zzay().zzd().zzd("(legacy) Failed to get user properties; remote exception", null, this.f6977b, e2);
                this.f6976a.set(Collections.emptyList());
                atomicReference = this.f6976a;
            }
            if (zzeqVar == null) {
                zzkeVar.f6809a.zzay().zzd().zzd("(legacy) Failed to get user properties; not connected to service", null, this.f6977b, this.f6978c);
                this.f6976a.set(Collections.emptyList());
                this.f6976a.notify();
                return;
            }
            if (TextUtils.isEmpty(null)) {
                Preconditions.checkNotNull(this.f6979d);
                atomicReference2 = this.f6976a;
                zzi = zzeqVar.zzh(this.f6977b, this.f6978c, this.f6980e, this.f6979d);
            } else {
                atomicReference2 = this.f6976a;
                zzi = zzeqVar.zzi(null, this.f6977b, this.f6978c, this.f6980e);
            }
            atomicReference2.set(zzi);
            this.f6981f.zzQ();
            atomicReference = this.f6976a;
            atomicReference.notify();
        }
    }
}
