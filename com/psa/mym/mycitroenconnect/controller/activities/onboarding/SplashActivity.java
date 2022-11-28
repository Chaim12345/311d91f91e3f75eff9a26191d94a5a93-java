package com.psa.mym.mycitroenconnect.controller.activities.onboarding;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.safetynet.SafetyNetClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.psa.mym.mycitroenconnect.BaseActivity;
import com.psa.mym.mycitroenconnect.BuildConfig;
import com.psa.mym.mycitroenconnect.api.retrofit.ApiInterface;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.model.JWSRequest;
import com.psa.mym.mycitroenconnect.model.JwsResponse;
import com.psa.mym.mycitroenconnect.model.SafetynetResultModel;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayout;
import com.scottyab.rootbeer.RootBeer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class SplashActivity extends BaseActivity {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String GOOGLE_API_VERIFY_URL = "https://www.googleapis.com/androidcheck/v1/attestations/";
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private final Random mRandom = new SecureRandom();

    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final void checkGoogleApi() {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this, 13000000) == 0) {
            sendSafetynetRequest();
            return;
        }
        String string = getString(R.string.update_google_play_services);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.update_google_play_services)");
        ExtensionsKt.showToast(this, string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JsonWebSignature decodeJws(String str) {
        JsonWebSignature jsonWebSignature = null;
        try {
            jsonWebSignature = JsonWebSignature.parser(JacksonFactory.getDefaultInstance()).parse(str);
            Intrinsics.checkNotNull(jsonWebSignature);
            return jsonWebSignature;
        } catch (IOException unused) {
            Intrinsics.checkNotNull(jsonWebSignature);
            return jsonWebSignature;
        }
    }

    private final boolean findBinary(String str) {
        String[] strArr = {"/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};
        for (int i2 = 0; i2 < 8; i2++) {
            if (new File(strArr[i2] + str).exists()) {
                return true;
            }
        }
        return false;
    }

    private final byte[] getRequestNonce(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[24];
        this.mRandom.nextBytes(bArr);
        try {
            byteArrayOutputStream.write(bArr);
            byte[] bytes = str.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            byteArrayOutputStream.write(bytes);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            return null;
        }
    }

    private final void goToNextScreen() {
        startActivity(SharedPref.Companion.isLogin(this) ? new Intent(this, AppLockFingerprintActivity.class) : new Intent(this, IntroActivity.class));
        finish();
    }

    private final boolean isRooted() {
        return findBinary("su");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-1$lambda-0  reason: not valid java name */
    public static final void m103onCreate$lambda1$lambda0(SplashActivity this$0, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-2  reason: not valid java name */
    public static final void m104onCreate$lambda2(SplashActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.checkGoogleApi();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-3  reason: not valid java name */
    public static final void m105onCreate$lambda3(SplashActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.goToNextScreen();
    }

    private final void sendSafetynetRequest() {
        byte[] requestNonce = getRequestNonce("Safety Net Data: " + System.currentTimeMillis());
        SafetyNetClient client = SafetyNet.getClient((Activity) this);
        Intrinsics.checkNotNullExpressionValue(client, "getClient(this@SplashActivity)");
        Intrinsics.checkNotNull(requestNonce);
        Task<SafetyNetApi.AttestationResponse> attest = client.attest(requestNonce, BuildConfig.GOOGLE_MAPS_ANDROID_API_KEY);
        Intrinsics.checkNotNullExpressionValue(attest, "saftyNetClient.attest(no…GLE_MAPS_ANDROID_API_KEY)");
        attest.addOnSuccessListener(new OnSuccessListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.o
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                SplashActivity.m106sendSafetynetRequest$lambda5(SplashActivity.this, (SafetyNetApi.AttestationResponse) obj);
            }
        }).addOnFailureListener(n.f10431a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendSafetynetRequest$lambda-5  reason: not valid java name */
    public static final void m106sendSafetynetRequest$lambda5(final SplashActivity this$0, SafetyNetApi.AttestationResponse attestationResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String jwsResult = attestationResponse.getJwsResult();
        Intrinsics.checkNotNull(jwsResult);
        JsonWebSignature decodeJws = this$0.decodeJws(jwsResult);
        SafetynetResultModel safetynetResultModel = new SafetynetResultModel(String.valueOf(decodeJws.getPayload().get("basicIntegrity")), String.valueOf(decodeJws.getPayload().get("evaluationType")), String.valueOf(decodeJws.getPayload().get("ctsProfileMatch")));
        if (Boolean.parseBoolean(safetynetResultModel.getBasicIntegrity()) || Boolean.parseBoolean(safetynetResultModel.getProfileMatch())) {
            this$0.goToNextScreen();
            return;
        }
        try {
            AlertDialog create = new AlertDialog.Builder(this$0).create();
            Intrinsics.checkNotNullExpressionValue(create, "Builder(this@SplashActivity).create()");
            create.setTitle("Info");
            create.setMessage("You are a root user,\nto access the app use another device");
            create.setIcon(17301543);
            create.setButton(-1, this$0.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.m
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    SplashActivity.m107sendSafetynetRequest$lambda5$lambda4(SplashActivity.this, dialogInterface, i2);
                }
            });
            create.show();
        } catch (Exception e2) {
            System.out.print((Object) e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendSafetynetRequest$lambda-5$lambda-4  reason: not valid java name */
    public static final void m107sendSafetynetRequest$lambda5$lambda4(SplashActivity this$0, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendSafetynetRequest$lambda-6  reason: not valid java name */
    public static final void m108sendSafetynetRequest$lambda6(Exception it) {
        Intrinsics.checkNotNullParameter(it, "it");
        boolean z = it instanceof ApiException;
    }

    private final void verifyOnline(final String str) {
        Retrofit build = new Retrofit.Builder().baseUrl(GOOGLE_API_VERIFY_URL).addConverterFactory(GsonConverterFactory.create()).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .b…e())\n            .build()");
        Object create = build.create(ApiInterface.class);
        Intrinsics.checkNotNullExpressionValue(create, "retrofit.create(ApiInterface::class.java)");
        ((ApiInterface) create).getResult(new JWSRequest(str), BuildConfig.GOOGLE_MAPS_ANDROID_API_KEY).enqueue(new Callback<JwsResponse>() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.SplashActivity$verifyOnline$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<JwsResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                String localizedMessage = t2.getLocalizedMessage();
                if (localizedMessage != null) {
                    ExtensionsKt.showToast(SplashActivity.this, localizedMessage);
                }
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<JwsResponse> call, @NotNull Response<JwsResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                JwsResponse body = response.body();
                Intrinsics.checkNotNull(body);
                if (body.isValidSignature()) {
                    SplashActivity.this.decodeJws(str);
                    return;
                }
                SplashActivity splashActivity = SplashActivity.this;
                String string = splashActivity.getString(R.string.validation_error);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.validation_error)");
                ExtensionsKt.showToast(splashActivity, string);
            }
        });
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i2) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            if (findViewById != null) {
                map.put(Integer.valueOf(i2), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        Handler handler;
        Runnable runnable;
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        if (!Intrinsics.areEqual("preprod", "preprod") && !Intrinsics.areEqual("preprod", AppConstants.FLAVOR_PROD)) {
            handler = new Handler(Looper.getMainLooper());
            runnable = new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.p
                @Override // java.lang.Runnable
                public final void run() {
                    SplashActivity.m105onCreate$lambda3(SplashActivity.this);
                }
            };
        } else if (new RootBeer(this).isRooted() && isRooted()) {
            AlertDialog create = new AlertDialog.Builder(this).create();
            create.setTitle("Info");
            create.setMessage("You are a root user,\nto access the app use another device");
            create.setIcon(17301543);
            create.setButton(-1, "OK", new DialogInterface.OnClickListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.l
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    SplashActivity.m103onCreate$lambda1$lambda0(SplashActivity.this, dialogInterface, i2);
                }
            });
            create.show();
            return;
        } else {
            handler = new Handler(Looper.getMainLooper());
            runnable = new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.q
                @Override // java.lang.Runnable
                public final void run() {
                    SplashActivity.m104onCreate$lambda2(SplashActivity.this);
                }
            };
        }
        handler.postDelayed(runnable, SkeletonLayout.DEFAULT_SHIMMER_DURATION_IN_MILLIS);
    }
}
