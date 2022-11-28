package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zacq implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zact f5693a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zacq(zact zactVar) {
        this.f5693a = zactVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zacs zacsVar;
        zacsVar = this.f5693a.zah;
        zacsVar.zae(new ConnectionResult(4));
    }
}
