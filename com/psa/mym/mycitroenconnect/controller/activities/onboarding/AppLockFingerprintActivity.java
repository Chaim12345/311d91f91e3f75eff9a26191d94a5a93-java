package com.psa.mym.mycitroenconnect.controller.activities.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.biometric.BiometricPrompt;
import com.chaos.view.PinView;
import com.google.android.material.textfield.TextInputLayout;
import com.psa.mym.mycitroenconnect.BaseActivity;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.MainActivity;
import com.psa.mym.mycitroenconnect.controller.activities.NonVinMainActivity;
import com.psa.mym.mycitroenconnect.model.MyNotificationModel;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import com.psa.mym.mycitroenconnect.model.onboarding.UserProfileResponse;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class AppLockFingerprintActivity extends BaseActivity implements TextWatcher {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private boolean isFromPushNotif;
    @Nullable
    private MyNotificationModel notificationModel;
    @Nullable
    private UserProfileResponse userProfileResponse;

    private final void getIntentData() {
        if (getIntent() != null && getIntent().hasExtra(AppConstants.ARG_IS_FROM_PUSH_NOTIF) && getIntent().hasExtra(AppConstants.ARG_PUSH_NOTIF_MODEL)) {
            this.isFromPushNotif = getIntent().getBooleanExtra(AppConstants.ARG_IS_FROM_PUSH_NOTIF, false);
            MyNotificationModel myNotificationModel = (MyNotificationModel) getIntent().getParcelableExtra(AppConstants.ARG_PUSH_NOTIF_MODEL);
            this.notificationModel = myNotificationModel;
            if (this.isFromPushNotif && myNotificationModel == null) {
                finish();
            }
        }
    }

    private final Intent goToMainDashboard() {
        Logger logger = Logger.INSTANCE;
        StringBuilder sb = new StringBuilder();
        sb.append("===Navigation Starts : ");
        AppUtil.Companion.logStartTime("goToMainDashboard");
        sb.append(Unit.INSTANCE);
        logger.e(sb.toString());
        Intent intent = new Intent(this, MainActivity.class);
        if (this.isFromPushNotif) {
            intent.putExtra(AppConstants.ARG_PUSH_NOTIF_MODEL, this.notificationModel);
            intent.putExtra(AppConstants.ARG_IS_FROM_PUSH_NOTIF, true);
        }
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void goToNextScreen() {
        List<RegisteredVehicleItem> registeredVehicle;
        UserProfileResponse userProfileResponse = this.userProfileResponse;
        boolean z = true;
        if (userProfileResponse == null || (registeredVehicle = userProfileResponse.getRegisteredVehicle()) == null || !registeredVehicle.isEmpty()) {
            z = false;
        }
        startActivity((z || SharedPref.Companion.isVerifiedUser(this)) ? goToMainDashboard() : new Intent(this, NonVinMainActivity.class));
        finish();
    }

    @RequiresApi(26)
    private final void initFingerPrint() {
        final BiometricPrompt biometricPrompt = new BiometricPrompt(this, Executors.newSingleThreadExecutor(), new AppLockFingerprintActivity$initFingerPrint$biometricPrompt$1(this));
        final BiometricPrompt.PromptInfo build = new BiometricPrompt.PromptInfo.Builder().setTitle(getString(R.string.authentication_prompt)).setNegativeButtonText(getString(R.string.label_cancel)).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…el))\n            .build()");
        biometricPrompt.authenticate(build);
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.appLockFingerPrint)).setOnClickListener(new View.OnClickListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AppLockFingerprintActivity.m93initFingerPrint$lambda0(BiometricPrompt.this, build, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initFingerPrint$lambda-0  reason: not valid java name */
    public static final void m93initFingerPrint$lambda0(BiometricPrompt biometricPrompt, BiometricPrompt.PromptInfo promptInfo, View view) {
        Intrinsics.checkNotNullParameter(biometricPrompt, "$biometricPrompt");
        Intrinsics.checkNotNullParameter(promptInfo, "$promptInfo");
        biometricPrompt.authenticate(promptInfo);
    }

    @RequiresApi(26)
    private final void initView() {
        TextView textView;
        int i2;
        boolean isBlank;
        SharedPref.Companion companion = SharedPref.Companion;
        if (companion.isFingerPrintAuth(this)) {
            ((LinearLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.linAuth)).setVisibility(0);
            _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.view).setVisibility(8);
            initFingerPrint();
            textView = (TextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.lableText);
            i2 = R.string.or_enter_4digit_pin;
        } else {
            ((LinearLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.linAuth)).setVisibility(8);
            _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.view).setVisibility(0);
            textView = (TextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.lableText);
            i2 = R.string.enter_4digit_pin;
        }
        textView.setText(getString(i2));
        this.userProfileResponse = companion.getUserProfileResponse(this);
        String userFirstName = companion.getUserFirstName(this);
        if (userFirstName.length() > 0) {
            isBlank = StringsKt__StringsJVMKt.isBlank(userFirstName);
            if (!isBlank) {
                ((TextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvWelcome)).setText(getString(R.string.welcome, new Object[]{userFirstName}));
                int i3 = com.psa.mym.mycitroenconnect.R.id.ivCar;
                AppCompatImageView ivCar = (AppCompatImageView) _$_findCachedViewById(i3);
                Intrinsics.checkNotNullExpressionValue(ivCar, "ivCar");
                ExtensionsKt.show(ivCar);
                AppCompatImageView ivCar2 = (AppCompatImageView) _$_findCachedViewById(i3);
                Intrinsics.checkNotNullExpressionValue(ivCar2, "ivCar");
                ExtensionsKt.inFromLeftAnimation$default(ivCar2, null, 1, null);
                ((TextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvAppLockRegNow)).setOnClickListener(this);
                ((TextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.appLockForgotPin)).setOnClickListener(this);
                ((PinView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edtAppLockPin)).addTextChangedListener(this);
            }
        }
        ((TextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvWelcome)).setText(getString(R.string.welcome, new Object[]{""}));
        int i32 = com.psa.mym.mycitroenconnect.R.id.ivCar;
        AppCompatImageView ivCar3 = (AppCompatImageView) _$_findCachedViewById(i32);
        Intrinsics.checkNotNullExpressionValue(ivCar3, "ivCar");
        ExtensionsKt.show(ivCar3);
        AppCompatImageView ivCar22 = (AppCompatImageView) _$_findCachedViewById(i32);
        Intrinsics.checkNotNullExpressionValue(ivCar22, "ivCar");
        ExtensionsKt.inFromLeftAnimation$default(ivCar22, null, 1, null);
        ((TextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvAppLockRegNow)).setOnClickListener(this);
        ((TextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.appLockForgotPin)).setOnClickListener(this);
        ((PinView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edtAppLockPin)).addTextChangedListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void runExitAnimation() {
        AppCompatImageView ivCar = (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivCar);
        Intrinsics.checkNotNullExpressionValue(ivCar, "ivCar");
        ExtensionsKt.outToRightAnimation(ivCar, new Animation.AnimationListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.AppLockFingerprintActivity$runExitAnimation$1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(@Nullable Animation animation) {
                ((AppCompatImageView) AppLockFingerprintActivity.this._$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivCar)).setVisibility(4);
                AppLockFingerprintActivity.this.goToNextScreen();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(@Nullable Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(@Nullable Animation animation) {
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

    @Override // android.text.TextWatcher
    public void afterTextChanged(@Nullable Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
    }

    @Nullable
    public final MyNotificationModel getNotificationModel() {
        return this.notificationModel;
    }

    @Nullable
    public final UserProfileResponse getUserProfileResponse() {
        return this.userProfileResponse;
    }

    public final boolean isFromPushNotif() {
        return this.isFromPushNotif;
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (!Intrinsics.areEqual(view, (TextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvAppLockRegNow))) {
            if (Intrinsics.areEqual(view, (TextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.appLockForgotPin))) {
                SharedPref.Companion.logoutFromApp(this);
                return;
            }
            return;
        }
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("login", AppConstants.PAGE_MODE_REGISTRATION);
        startActivity(intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @RequiresApi(26)
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_app_lock_fingerprint);
        getIntentData();
        initView();
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
        int i5 = com.psa.mym.mycitroenconnect.R.id.txtAppLockPin;
        if (((TextInputLayout) _$_findCachedViewById(i5)).getError() != null) {
            ((TextInputLayout) _$_findCachedViewById(i5)).setError(null);
        }
        boolean z = false;
        if (charSequence != null && charSequence.length() == 4) {
            z = true;
        }
        if (z) {
            if (Intrinsics.areEqual(SharedPref.Companion.getAppPin(this), charSequence.toString())) {
                runExitAnimation();
            } else {
                ((TextInputLayout) _$_findCachedViewById(i5)).setError(getString(R.string.err_incorrect_pin));
            }
        }
    }

    public final void setFromPushNotif(boolean z) {
        this.isFromPushNotif = z;
    }

    public final void setNotificationModel(@Nullable MyNotificationModel myNotificationModel) {
        this.notificationModel = myNotificationModel;
    }

    public final void setUserProfileResponse(@Nullable UserProfileResponse userProfileResponse) {
        this.userProfileResponse = userProfileResponse;
    }
}
