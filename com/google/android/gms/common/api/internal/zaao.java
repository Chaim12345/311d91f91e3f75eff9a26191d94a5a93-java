package com.google.android.gms.common.api.internal;

import android.content.Context;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zaao extends zaav {

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zaaw f5642b;
    private final Map<Api.Client, zaal> zac;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaao(zaaw zaawVar, Map<Api.Client, zaal> map) {
        super(zaawVar, null);
        this.f5642b = zaawVar;
        this.zac = map;
    }

    @Override // com.google.android.gms.common.api.internal.zaav
    @GuardedBy("mLock")
    @WorkerThread
    public final void zaa() {
        GoogleApiAvailabilityLight googleApiAvailabilityLight;
        Context context;
        boolean z;
        Context context2;
        zabi zabiVar;
        com.google.android.gms.signin.zae zaeVar;
        com.google.android.gms.signin.zae zaeVar2;
        zabi zabiVar2;
        Context context3;
        boolean z2;
        googleApiAvailabilityLight = this.f5642b.zad;
        com.google.android.gms.common.internal.zal zalVar = new com.google.android.gms.common.internal.zal(googleApiAvailabilityLight);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Api.Client client : this.zac.keySet()) {
            if (client.requiresGooglePlayServices()) {
                z2 = this.zac.get(client).zac;
                if (!z2) {
                    arrayList.add(client);
                }
            }
            arrayList2.add(client);
        }
        int i2 = -1;
        int i3 = 0;
        if (arrayList.isEmpty()) {
            int size = arrayList2.size();
            while (i3 < size) {
                context3 = this.f5642b.zac;
                i2 = zalVar.zab(context3, (Api.Client) arrayList2.get(i3));
                i3++;
                if (i2 == 0) {
                    break;
                }
            }
        } else {
            int size2 = arrayList.size();
            while (i3 < size2) {
                context = this.f5642b.zac;
                i2 = zalVar.zab(context, (Api.Client) arrayList.get(i3));
                i3++;
                if (i2 != 0) {
                    break;
                }
            }
        }
        if (i2 != 0) {
            ConnectionResult connectionResult = new ConnectionResult(i2, null);
            zaaw zaawVar = this.f5642b;
            zabiVar2 = zaawVar.zaa;
            zabiVar2.f(new zaam(this, zaawVar, connectionResult));
            return;
        }
        zaaw zaawVar2 = this.f5642b;
        z = zaawVar2.zam;
        if (z) {
            zaeVar = zaawVar2.zak;
            if (zaeVar != null) {
                zaeVar2 = zaawVar2.zak;
                zaeVar2.zab();
            }
        }
        for (Api.Client client2 : this.zac.keySet()) {
            zaal zaalVar = this.zac.get(client2);
            if (client2.requiresGooglePlayServices()) {
                context2 = this.f5642b.zac;
                if (zalVar.zab(context2, client2) != 0) {
                    zaaw zaawVar3 = this.f5642b;
                    zabiVar = zaawVar3.zaa;
                    zabiVar.f(new zaan(this, zaawVar3, zaalVar));
                }
            }
            client2.connect(zaalVar);
        }
    }
}
