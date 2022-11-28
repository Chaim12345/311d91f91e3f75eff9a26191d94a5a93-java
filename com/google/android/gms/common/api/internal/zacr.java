package com.google.android.gms.common.api.internal;
/* loaded from: classes.dex */
final class zacr implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ com.google.android.gms.signin.internal.zak f5694a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zact f5695b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zacr(zact zactVar, com.google.android.gms.signin.internal.zak zakVar) {
        this.f5695b = zactVar;
        this.f5694a = zakVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zact.c(this.f5695b, this.f5694a);
    }
}
