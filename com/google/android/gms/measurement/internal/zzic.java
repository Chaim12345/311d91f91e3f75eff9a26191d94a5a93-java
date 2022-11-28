package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzic implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AtomicReference f6855a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f6856b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ String f6857c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ boolean f6858d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzip f6859e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzic(zzip zzipVar, AtomicReference atomicReference, String str, String str2, String str3, boolean z) {
        this.f6859e = zzipVar;
        this.f6855a = atomicReference;
        this.f6856b = str2;
        this.f6857c = str3;
        this.f6858d = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6859e.f6809a.zzt().A(this.f6855a, null, this.f6856b, this.f6857c, this.f6858d);
    }
}
