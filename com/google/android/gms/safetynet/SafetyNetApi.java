package com.google.android.gms.safetynet;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.List;
@KeepForSdkWithMembers
/* loaded from: classes2.dex */
public interface SafetyNetApi {

    /* loaded from: classes2.dex */
    public static class AttestationResponse extends Response<AttestationResult> {
        @Nullable
        public String getJwsResult() {
            return ((AttestationResult) a()).getJwsResult();
        }
    }

    @Deprecated
    /* loaded from: classes2.dex */
    public interface AttestationResult extends Result {
        @Nullable
        String getJwsResult();
    }

    /* loaded from: classes2.dex */
    public static class HarmfulAppsResponse extends Response<HarmfulAppsResult> {
        @NonNull
        public List<HarmfulAppsData> getHarmfulAppsList() {
            return ((HarmfulAppsResult) a()).getHarmfulAppsList();
        }

        public int getHoursSinceLastScanWithHarmfulApp() {
            return ((HarmfulAppsResult) a()).getHoursSinceLastScanWithHarmfulApp();
        }

        public long getLastScanTimeMs() {
            return ((HarmfulAppsResult) a()).getLastScanTimeMs();
        }
    }

    @Deprecated
    /* loaded from: classes2.dex */
    public interface HarmfulAppsResult extends Result {
        @NonNull
        List<HarmfulAppsData> getHarmfulAppsList();

        int getHoursSinceLastScanWithHarmfulApp();

        long getLastScanTimeMs();
    }

    /* loaded from: classes2.dex */
    public static class RecaptchaTokenResponse extends Response<RecaptchaTokenResult> {
        @Nullable
        public String getTokenResult() {
            return ((RecaptchaTokenResult) a()).getTokenResult();
        }
    }

    @Deprecated
    /* loaded from: classes2.dex */
    public interface RecaptchaTokenResult extends Result {
        @Nullable
        String getTokenResult();
    }

    @KeepForSdkWithMembers
    /* loaded from: classes2.dex */
    public static class SafeBrowsingResponse extends Response<SafeBrowsingResult> {
        @NonNull
        public List<SafeBrowsingThreat> getDetectedThreats() {
            return ((SafeBrowsingResult) a()).getDetectedThreats();
        }

        @ShowFirstParty
        public long getLastUpdateTimeMs() {
            return ((SafeBrowsingResult) a()).getLastUpdateTimeMs();
        }

        @Nullable
        @ShowFirstParty
        public String getMetadata() {
            return ((SafeBrowsingResult) a()).getMetadata();
        }

        @Nullable
        @ShowFirstParty
        public byte[] getState() {
            return ((SafeBrowsingResult) a()).getState();
        }
    }

    @KeepForSdkWithMembers
    @Deprecated
    /* loaded from: classes2.dex */
    public interface SafeBrowsingResult extends Result {
        @NonNull
        List<SafeBrowsingThreat> getDetectedThreats();

        long getLastUpdateTimeMs();

        @Nullable
        String getMetadata();

        @Nullable
        byte[] getState();
    }

    /* loaded from: classes2.dex */
    public static class VerifyAppsUserResponse extends Response<VerifyAppsUserResult> {
        public boolean isVerifyAppsEnabled() {
            return ((VerifyAppsUserResult) a()).isVerifyAppsEnabled();
        }
    }

    @Deprecated
    /* loaded from: classes2.dex */
    public interface VerifyAppsUserResult extends Result {
        boolean isVerifyAppsEnabled();
    }

    @NonNull
    @Deprecated
    PendingResult<AttestationResult> attest(@NonNull GoogleApiClient googleApiClient, @NonNull byte[] bArr);

    @NonNull
    @Deprecated
    PendingResult<VerifyAppsUserResult> enableVerifyApps(@NonNull GoogleApiClient googleApiClient);

    @NonNull
    @Deprecated
    PendingResult<VerifyAppsUserResult> isVerifyAppsEnabled(@NonNull GoogleApiClient googleApiClient);

    @Deprecated
    boolean isVerifyAppsEnabled(@NonNull Context context);

    @NonNull
    @Deprecated
    PendingResult<HarmfulAppsResult> listHarmfulApps(@NonNull GoogleApiClient googleApiClient);

    @NonNull
    @Deprecated
    PendingResult<SafeBrowsingResult> lookupUri(@NonNull GoogleApiClient googleApiClient, @NonNull String str, @NonNull String str2, @NonNull int... iArr);

    @NonNull
    PendingResult<SafeBrowsingResult> lookupUri(@NonNull GoogleApiClient googleApiClient, @NonNull List<Integer> list, @NonNull String str);

    @NonNull
    @Deprecated
    PendingResult<RecaptchaTokenResult> verifyWithRecaptcha(@NonNull GoogleApiClient googleApiClient, @NonNull String str);
}
