package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import java.util.Map;
/* loaded from: classes.dex */
final class zabt implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ConnectionResult f5683a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zabu f5684b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zabt(zabu zabuVar, ConnectionResult connectionResult) {
        this.f5684b = zabuVar;
        this.f5683a = connectionResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Map map;
        ApiKey apiKey;
        Api.Client client;
        Api.Client client2;
        Api.Client client3;
        Api.Client client4;
        zabu zabuVar = this.f5684b;
        map = zabuVar.f5685a.zap;
        apiKey = zabuVar.zac;
        zabq zabqVar = (zabq) map.get(apiKey);
        if (zabqVar == null) {
            return;
        }
        if (!this.f5683a.isSuccess()) {
            zabqVar.zar(this.f5683a, null);
            return;
        }
        this.f5684b.zaf = true;
        client = this.f5684b.zab;
        if (client.requiresSignIn()) {
            this.f5684b.zag();
            return;
        }
        try {
            zabu zabuVar2 = this.f5684b;
            client3 = zabuVar2.zab;
            client4 = zabuVar2.zab;
            client3.getRemoteService(null, client4.getScopesForConnectionlessNonSignIn());
        } catch (SecurityException e2) {
            Log.e("GoogleApiManager", "Failed to get service from broker. ", e2);
            client2 = this.f5684b.zab;
            client2.disconnect("Failed to get service from broker.");
            zabqVar.zar(new ConnectionResult(10), null);
        }
    }
}
