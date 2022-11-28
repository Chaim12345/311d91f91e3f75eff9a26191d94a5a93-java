package com.google.android.gms.security;

import android.content.Context;
import android.os.AsyncTask;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
/* loaded from: classes2.dex */
final class zza extends AsyncTask<Void, Void, Integer> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f7067a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ ProviderInstaller.ProviderInstallListener f7068b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(Context context, ProviderInstaller.ProviderInstallListener providerInstallListener) {
        this.f7067a = context;
        this.f7068b = providerInstallListener;
    }

    @Override // android.os.AsyncTask
    protected final /* bridge */ /* synthetic */ Integer doInBackground(Void[] voidArr) {
        int connectionStatusCode;
        try {
            ProviderInstaller.installIfNeeded(this.f7067a);
            connectionStatusCode = 0;
        } catch (GooglePlayServicesNotAvailableException e2) {
            connectionStatusCode = e2.errorCode;
        } catch (GooglePlayServicesRepairableException e3) {
            connectionStatusCode = e3.getConnectionStatusCode();
        }
        return Integer.valueOf(connectionStatusCode);
    }

    @Override // android.os.AsyncTask
    protected final /* bridge */ /* synthetic */ void onPostExecute(Integer num) {
        GoogleApiAvailabilityLight googleApiAvailabilityLight;
        Integer num2 = num;
        if (num2.intValue() == 0) {
            this.f7068b.onProviderInstalled();
            return;
        }
        googleApiAvailabilityLight = ProviderInstaller.zza;
        this.f7068b.onProviderInstallFailed(num2.intValue(), googleApiAvailabilityLight.getErrorResolutionIntent(this.f7067a, num2.intValue(), "pi"));
    }
}
