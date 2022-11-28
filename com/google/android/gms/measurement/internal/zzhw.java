package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzhw implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AtomicReference f6838a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ boolean f6839b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzip f6840c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhw(zzip zzipVar, AtomicReference atomicReference, boolean z) {
        this.f6840c = zzipVar;
        this.f6838a = atomicReference;
        this.f6839b = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6840c.f6809a.zzt().y(this.f6838a, this.f6839b);
    }
}
