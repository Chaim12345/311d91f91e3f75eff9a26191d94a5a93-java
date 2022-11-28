package com.google.android.gms.safetynet;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.internal.safetynet.zzae;
import com.google.android.gms.internal.safetynet.zzaf;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes2.dex */
public class SafetyNetClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    public static final /* synthetic */ int zza = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SafetyNetClient(@NonNull Activity activity) {
        super(activity, (Api<Api.ApiOptions>) SafetyNet.API, (Api.ApiOptions) null, (StatusExceptionMapper) new ApiExceptionMapper());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SafetyNetClient(@NonNull Context context) {
        super(context, SafetyNet.API, (Api.ApiOptions) null, new ApiExceptionMapper());
    }

    @NonNull
    public Task<SafetyNetApi.AttestationResponse> attest(@NonNull byte[] bArr, @NonNull String str) {
        return PendingResultUtil.toResponseTask(zzae.zza(asGoogleApiClient(), bArr, str), new SafetyNetApi.AttestationResponse());
    }

    @NonNull
    public Task<SafetyNetApi.VerifyAppsUserResponse> enableVerifyApps() {
        return PendingResultUtil.toResponseTask(SafetyNet.SafetyNetApi.enableVerifyApps(asGoogleApiClient()), new SafetyNetApi.VerifyAppsUserResponse());
    }

    @NonNull
    public Task<Void> initSafeBrowsing() {
        return doRead(TaskApiCall.builder().setMethodKey(4202).run(new RemoteCall() { // from class: com.google.android.gms.safetynet.zzl
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                ((com.google.android.gms.internal.safetynet.zzh) ((zzaf) obj).getService()).zze(new zzp(SafetyNetClient.this, (TaskCompletionSource) obj2));
            }
        }).build());
    }

    @NonNull
    public Task<SafetyNetApi.VerifyAppsUserResponse> isVerifyAppsEnabled() {
        return doRead(TaskApiCall.builder().setMethodKey(4201).run(new RemoteCall() { // from class: com.google.android.gms.safetynet.zzm
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                ((com.google.android.gms.internal.safetynet.zzh) ((zzaf) obj).getService()).zzf(new zzo(SafetyNetClient.this, (TaskCompletionSource) obj2));
            }
        }).build());
    }

    @NonNull
    public Task<SafetyNetApi.HarmfulAppsResponse> listHarmfulApps() {
        return PendingResultUtil.toResponseTask(SafetyNet.SafetyNetApi.listHarmfulApps(asGoogleApiClient()), new SafetyNetApi.HarmfulAppsResponse());
    }

    @NonNull
    public Task<SafetyNetApi.SafeBrowsingResponse> lookupUri(@NonNull String str, @NonNull String str2, @NonNull int... iArr) {
        return PendingResultUtil.toResponseTask(zzae.zzb(asGoogleApiClient(), str, 3, str2, iArr), new SafetyNetApi.SafeBrowsingResponse());
    }

    @NonNull
    public Task<Void> shutdownSafeBrowsing() {
        return doRead(TaskApiCall.builder().setMethodKey(4203).run(zzn.zza).build());
    }

    @NonNull
    public Task<SafetyNetApi.RecaptchaTokenResponse> verifyWithRecaptcha(@NonNull String str) {
        return PendingResultUtil.toResponseTask(SafetyNet.SafetyNetApi.verifyWithRecaptcha(asGoogleApiClient(), str), new SafetyNetApi.RecaptchaTokenResponse());
    }
}
