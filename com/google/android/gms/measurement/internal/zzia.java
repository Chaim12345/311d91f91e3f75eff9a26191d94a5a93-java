package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzia implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AtomicReference f6849a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f6850b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ String f6851c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzip f6852d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzia(zzip zzipVar, AtomicReference atomicReference, String str, String str2, String str3) {
        this.f6852d = zzipVar;
        this.f6849a = atomicReference;
        this.f6850b = str2;
        this.f6851c = str3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6852d.f6809a.zzt().x(this.f6849a, null, this.f6850b, this.f6851c);
    }
}
