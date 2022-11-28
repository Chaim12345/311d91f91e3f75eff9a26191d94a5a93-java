package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes.dex */
final class zaam extends zabg {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ConnectionResult f5639a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zaao f5640b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaam(zaao zaaoVar, zabf zabfVar, ConnectionResult connectionResult) {
        super(zabfVar);
        this.f5640b = zaaoVar;
        this.f5639a = connectionResult;
    }

    @Override // com.google.android.gms.common.api.internal.zabg
    @GuardedBy("mLock")
    public final void zaa() {
        this.f5640b.f5642b.zaD(this.f5639a);
    }
}
