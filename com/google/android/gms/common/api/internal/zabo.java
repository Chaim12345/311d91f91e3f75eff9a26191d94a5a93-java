package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
/* loaded from: classes.dex */
final class zabo implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zabp f5680a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zabo(zabp zabpVar) {
        this.f5680a = zabpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Api.Client client;
        Api.Client client2;
        zabq zabqVar = this.f5680a.f5681a;
        client = zabqVar.zac;
        client2 = zabqVar.zac;
        client.disconnect(client2.getClass().getName().concat(" disconnecting because it was signed out."));
    }
}
