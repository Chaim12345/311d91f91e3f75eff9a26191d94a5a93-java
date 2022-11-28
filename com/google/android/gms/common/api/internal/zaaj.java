package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Set;
/* loaded from: classes.dex */
public final class zaaj implements zabf {
    private final zabi zaa;
    private boolean zab = false;

    public zaaj(zabi zabiVar) {
        this.zaa = zabiVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b() {
        if (this.zab) {
            this.zab = false;
            this.zaa.f5674g.f5666i.zab();
            zaj();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T zaa(T t2) {
        zab(t2);
        return t2;
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T zab(T t2) {
        try {
            this.zaa.f5674g.f5666i.a(t2);
            zabe zabeVar = this.zaa.f5674g;
            Api.Client client = (Api.Client) zabeVar.f5660c.get(t2.getClientKey());
            Preconditions.checkNotNull(client, "Appropriate Api was not requested.");
            if (client.isConnected() || !this.zaa.f5669b.containsKey(t2.getClientKey())) {
                t2.run(client);
            } else {
                t2.setFailedResult(new Status(17));
            }
        } catch (DeadObjectException unused) {
            this.zaa.f(new zaah(this, this));
        }
        return t2;
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void zad() {
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void zae() {
        if (this.zab) {
            this.zab = false;
            this.zaa.f(new zaai(this, this));
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void zag(@Nullable Bundle bundle) {
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void zah(ConnectionResult connectionResult, Api<?> api, boolean z) {
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void zai(int i2) {
        this.zaa.e(null);
        this.zaa.f5675h.zac(i2, this.zab);
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final boolean zaj() {
        if (this.zab) {
            return false;
        }
        Set<zada> set = this.zaa.f5674g.f5665h;
        if (set == null || set.isEmpty()) {
            this.zaa.e(null);
            return true;
        }
        this.zab = true;
        for (zada zadaVar : set) {
            zadaVar.h();
        }
        return false;
    }
}
