package com.google.android.gms.safetynet;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.internal.safetynet.zzae;
import com.google.android.gms.internal.safetynet.zzaf;
import com.google.android.gms.internal.safetynet.zzag;
/* loaded from: classes2.dex */
public final class SafetyNet {
    @NonNull
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> API;
    @NonNull
    @Deprecated
    public static final SafetyNetApi SafetyNetApi;
    @NonNull
    public static final Api.ClientKey<zzaf> zza;
    @NonNull
    public static final Api.AbstractClientBuilder<zzaf, Api.ApiOptions.NoOptions> zzb;
    @ShowFirstParty
    public static final zzag zzc;

    static {
        Api.ClientKey<zzaf> clientKey = new Api.ClientKey<>();
        zza = clientKey;
        zzk zzkVar = new zzk();
        zzb = zzkVar;
        API = new Api<>("SafetyNet.API", zzkVar, clientKey);
        SafetyNetApi = new zzae();
        zzc = new zzag();
    }

    private SafetyNet() {
    }

    @NonNull
    public static SafetyNetClient getClient(@NonNull Activity activity) {
        return new SafetyNetClient(activity);
    }

    @NonNull
    public static SafetyNetClient getClient(@NonNull Context context) {
        return new SafetyNetClient(context);
    }
}
