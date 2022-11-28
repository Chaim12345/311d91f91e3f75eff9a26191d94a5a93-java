package com.google.android.gms.common.internal;

import android.os.Bundle;
import androidx.annotation.BinderThread;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
/* loaded from: classes.dex */
public final class zzg extends zza {

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ BaseGmsClient f5789c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @BinderThread
    public zzg(BaseGmsClient baseGmsClient, @Nullable int i2, Bundle bundle) {
        super(baseGmsClient, i2, null);
        this.f5789c = baseGmsClient;
    }

    @Override // com.google.android.gms.common.internal.zza
    protected final void c(ConnectionResult connectionResult) {
        if (this.f5789c.b() && BaseGmsClient.C(this.f5789c)) {
            BaseGmsClient.y(this.f5789c, 16);
            return;
        }
        this.f5789c.f5746c.onReportServiceBinding(connectionResult);
        this.f5789c.k(connectionResult);
    }

    @Override // com.google.android.gms.common.internal.zza
    protected final boolean d() {
        this.f5789c.f5746c.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
        return true;
    }
}
