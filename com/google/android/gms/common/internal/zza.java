package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import androidx.annotation.BinderThread;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
/* loaded from: classes.dex */
abstract class zza extends zzc<Boolean> {

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ BaseGmsClient f5783b;
    public final int zza;
    @Nullable
    public final Bundle zzb;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @BinderThread
    public zza(BaseGmsClient baseGmsClient, @Nullable int i2, Bundle bundle) {
        super(baseGmsClient, Boolean.TRUE);
        this.f5783b = baseGmsClient;
        this.zza = i2;
        this.zzb = bundle;
    }

    @Override // com.google.android.gms.common.internal.zzc
    protected final /* bridge */ /* synthetic */ void a(Object obj) {
        ConnectionResult connectionResult;
        if (this.zza != 0) {
            this.f5783b.zzp(1, null);
            Bundle bundle = this.zzb;
            connectionResult = new ConnectionResult(this.zza, bundle != null ? (PendingIntent) bundle.getParcelable(BaseGmsClient.KEY_PENDING_INTENT) : null);
        } else if (d()) {
            return;
        } else {
            this.f5783b.zzp(1, null);
            connectionResult = new ConnectionResult(8, null);
        }
        c(connectionResult);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.zzc
    public final void b() {
    }

    protected abstract void c(ConnectionResult connectionResult);

    protected abstract boolean d();
}
