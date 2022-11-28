package com.psa.mym.mycitroenconnect.controller.activities.onboarding;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.messaging.FirebaseMessaging;
import com.psa.mym.mycitroenconnect.BuildConfig;
import com.psa.mym.mycitroenconnect.BusBaseActivity;
import com.psa.mym.mycitroenconnect.api.body.fcm.RegisterFCMBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.GetTokenBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.RegisterUserBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.VerifyOTPBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.AddCarActivity;
import com.psa.mym.mycitroenconnect.controller.activities.ConfirmCarDetailsActivity;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OtpSuccessBottomSheetFragment;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.FailResponse;
import com.psa.mym.mycitroenconnect.model.PostCommonResponse;
import com.psa.mym.mycitroenconnect.model.Token;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisterUserResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import com.psa.mym.mycitroenconnect.model.onboarding.SendOtpResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.UserProfileResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.VerifyOTPResponse;
import com.psa.mym.mycitroenconnect.services.FCMService;
import com.psa.mym.mycitroenconnect.services.OnBoardingService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsJVMKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class VerifyOtpActivity extends BusBaseActivity implements TextWatcher {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String mPageMode = "";
    @NotNull
    private String mobileNumber = "";
    @NotNull
    private String appPin = "";
    @NotNull
    private String vinNum = "";
    @Nullable
    private Boolean isFromMainScreen = Boolean.TRUE;
    @NotNull
    private RegisterUserResponse registerUserResponse = new RegisterUserResponse(null, null, null, null, null, 31, null);
    @NotNull
    private SendOtpResponse sendOtpResponse = new SendOtpResponse(null, null, null, null, 0, null, 63, null);
    @NotNull
    private UserProfileResponse userProfileResponse = new UserProfileResponse(null, null, null, null, null, null, null, null, null, null, null, null, 4095, null);

    private final void apiAuthenticateUser() {
        GetTokenBody getTokenBody = new GetTokenBody(null, null, null, null, 15, null);
        String str = this.mobileNumber;
        String substring = str.substring(3, str.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        getTokenBody.setUserName(substring);
        String substring2 = this.mobileNumber.substring(0, 3);
        Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
        getTokenBody.setCountryCode(substring2);
        new OnBoardingService().callGetUserTokenAPI(this, getTokenBody, false);
    }

    private final void apiSendOtp() {
        String str = this.mobileNumber;
        String substring = str.substring(9, str.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(R.string.label_otp_verification_msg);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.label_otp_verification_msg)");
        String format = String.format(string, Arrays.copyOf(new Object[]{substring}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvVerifyOtpMsg)).setText(format);
        RegisterUserBody registerUserBody = new RegisterUserBody();
        String str2 = this.mobileNumber;
        String substring2 = str2.substring(3, str2.length());
        Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
        registerUserBody.setMobileNum(substring2);
        registerUserBody.setCountryCode("+91");
        AppUtil.Companion.showDialog(this);
        new OnBoardingService().callSendOTPAPI(this, registerUserBody);
    }

    private final void apiVerifyOTP() {
        AppUtil.Companion.showDialog(this);
        VerifyOTPBody verifyOTPBody = new VerifyOTPBody(null, null, null, null, null, null, null, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
        String str = this.mobileNumber;
        String substring = str.substring(3, str.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        verifyOTPBody.setUserName(substring);
        verifyOTPBody.setCountryCode("+91");
        verifyOTPBody.setOtp(String.valueOf(((PinView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edVerification)).getText()));
        verifyOTPBody.setFullhash(this.sendOtpResponse.getFullHash());
        new OnBoardingService().callVerifyOTPAPI(this, verifyOTPBody, false);
    }

    private final void displayDialog(boolean z, boolean z2) {
        AppUtil.Companion.dismissDialog();
        OtpSuccessBottomSheetFragment newInstance = OtpSuccessBottomSheetFragment.Companion.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("mPageMode", this.mPageMode);
        bundle.putBoolean("isBio", z);
        bundle.putBoolean("isRegisteredUser", z2);
        newInstance.setArguments(bundle);
        newInstance.show(getSupportFragmentManager(), OtpSuccessBottomSheetFragment.TAG);
        newInstance.setCancelable(false);
    }

    private final void getIntentData() {
        String mobileNum;
        if (getIntent() == null || !getIntent().hasExtra("login")) {
            return;
        }
        String stringExtra = getIntent().getStringExtra("login");
        if (stringExtra != null) {
            this.mPageMode = stringExtra;
        }
        String str = this.mPageMode;
        switch (str.hashCode()) {
            case -2131583866:
                if (str.equals(AppConstants.PAGE_MODE_CHANGE_PIN)) {
                    this.mobileNumber = SharedPref.Companion.getMobileNumber(this);
                    if (getIntent() != null && getIntent().hasExtra(AppConstants.ARG_IS_FROM_MAIN_SCREEN)) {
                        this.isFromMainScreen = Boolean.valueOf(getIntent().getBooleanExtra(AppConstants.ARG_IS_FROM_MAIN_SCREEN, true));
                        break;
                    }
                }
                break;
            case -1350309703:
                if (str.equals(AppConstants.PAGE_MODE_REGISTRATION)) {
                    Parcelable parcelableExtra = getIntent().getParcelableExtra(AppConstants.REGUSER);
                    Intrinsics.checkNotNull(parcelableExtra);
                    RegisterUserResponse registerUserResponse = (RegisterUserResponse) parcelableExtra;
                    this.registerUserResponse = registerUserResponse;
                    mobileNum = registerUserResponse.getMobileNum();
                    Intrinsics.checkNotNull(mobileNum);
                    this.mobileNumber = mobileNum;
                    break;
                }
                break;
            case -1148260554:
                if (str.equals(AppConstants.PAGE_MODE_ADD_CAR)) {
                    this.mobileNumber = SharedPref.Companion.getMobileNumber(this);
                    if (getIntent() != null && getIntent().hasExtra(AppConstants.ADD_CAR_BUNDLE_NAME)) {
                        Bundle extras = getIntent().getExtras();
                        this.vinNum = String.valueOf(extras != null ? extras.get(AppConstants.ADD_CAR_BUNDLE_NAME) : null);
                        break;
                    }
                }
                break;
            case -305104263:
                if (str.equals(AppConstants.PAGE_MODE_FORGOT_PIN)) {
                    mobileNum = getIntent().getStringExtra(AppConstants.MOBILENUMBER);
                    Intrinsics.checkNotNull(mobileNum);
                    this.mobileNumber = mobileNum;
                    break;
                }
                break;
            case 103149417:
                if (str.equals("login")) {
                    String stringExtra2 = getIntent().getStringExtra(AppConstants.MOBILENUMBER);
                    Intrinsics.checkNotNull(stringExtra2);
                    this.mobileNumber = stringExtra2;
                    String stringExtra3 = getIntent().getStringExtra("AppPin");
                    Intrinsics.checkNotNull(stringExtra3);
                    this.appPin = stringExtra3;
                    break;
                }
                break;
        }
        apiSendOtp();
    }

    private final void initView() {
        int i2 = com.psa.mym.mycitroenconnect.R.id.edVerification;
        ((PinView) _$_findCachedViewById(i2)).requestFocus();
        ((PinView) _$_findCachedViewById(i2)).postDelayed(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.s
            @Override // java.lang.Runnable
            public final void run() {
                VerifyOtpActivity.m109initView$lambda0(VerifyOtpActivity.this);
            }
        }, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-0  reason: not valid java name */
    public static final void m109initView$lambda0(VerifyOtpActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ExtensionsKt.showKeyboard(this$0);
    }

    private final void registerFCMToken(List<String> list, final String str) {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.r
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                VerifyOtpActivity.m110registerFCMToken$lambda3(VerifyOtpActivity.this, str, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: registerFCMToken$lambda-3  reason: not valid java name */
    public static final void m110registerFCMToken$lambda3(VerifyOtpActivity this$0, String isGuest, Task task) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(isGuest, "$isGuest");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            String str = this$0.mobileNumber;
            String substring = str.substring(1, str.length());
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            new FCMService().registerToken(this$0, new RegisterFCMBody(null, substring, AppUtil.Companion.getDeviceId(this$0), (String) task.getResult(), BuildConfig.VERSION_NAME, isGuest, null, 65, null), false);
            return;
        }
        Logger logger = Logger.INSTANCE;
        logger.e("FCM Registration Token Failed " + task.getException());
        AppUtil.Companion.dismissDialog();
        String string = this$0.getString(R.string.something_went_wrong);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.something_went_wrong)");
        ExtensionsKt.showToast(this$0, string);
    }

    private final void setListeners() {
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnVerify)).setOnClickListener(this);
        ((AppCompatImageButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnBack)).setOnClickListener(this);
        ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvResendOtp)).setOnClickListener(this);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0086, code lost:
        if (r1.hasEnrolledFingerprints() == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a1, code lost:
        if (r1.hasEnrolledFingerprints() == false) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void showOTPSuccessDialog() {
        RegisteredVehicleItem registeredVehicleItem;
        SharedPref.Companion companion = SharedPref.Companion;
        companion.setUserProfileResponse(this, this.userProfileResponse);
        String fullName = this.userProfileResponse.getFullName();
        if (fullName != null) {
            companion.setUserFirstName(this, fullName);
        }
        if (this.userProfileResponse.getRegisteredVehicle() != null) {
            List<RegisteredVehicleItem> registeredVehicle = this.userProfileResponse.getRegisteredVehicle();
            Intrinsics.checkNotNull(registeredVehicle);
            if (!registeredVehicle.isEmpty()) {
                List<RegisteredVehicleItem> registeredVehicle2 = this.userProfileResponse.getRegisteredVehicle();
                if (registeredVehicle2 != null && (registeredVehicleItem = registeredVehicle2.get(0)) != null) {
                    String vinNum = registeredVehicleItem.getVinNum();
                    if (vinNum != null) {
                        companion.setVinNumber(this, vinNum);
                    }
                    companion.setVehicleType(this, registeredVehicleItem.getVehicleType());
                    String vehicleNumber = registeredVehicleItem.getVehicleNumber();
                    if (vehicleNumber != null) {
                        companion.setVehicleNumber(this, vehicleNumber);
                    }
                }
                FingerprintManagerCompat from = FingerprintManagerCompat.from(this);
                Intrinsics.checkNotNullExpressionValue(from, "from(this@VerifyOtpActivity)");
                if (!from.isHardwareDetected() || !from.hasEnrolledFingerprints()) {
                    companion.setIsFingerPrintAuth(this, "False");
                }
                displayDialog(false, true);
                return;
            }
            FingerprintManagerCompat from2 = FingerprintManagerCompat.from(this);
            Intrinsics.checkNotNullExpressionValue(from2, "from(this@VerifyOtpActivity)");
            if (from2.isHardwareDetected()) {
            }
            companion.setIsFingerPrintAuth(this, "False");
        } else {
            FingerprintManagerCompat from3 = FingerprintManagerCompat.from(this);
            Intrinsics.checkNotNullExpressionValue(from3, "from(this@VerifyOtpActivity)");
            if (from3.isHardwareDetected()) {
            }
            companion.setIsFingerPrintAuth(this, "False");
        }
        displayDialog(false, false);
    }

    private final void startCountDown() {
        new CountDownTimer() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.VerifyOtpActivity$startCountDown$timer$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(120000L, 1000L);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                ((AppCompatTextView) VerifyOtpActivity.this._$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvResendOtp)).setVisibility(0);
            }

            @Override // android.os.CountDownTimer
            @SuppressLint({"SetTextI18n"})
            public void onTick(long j2) {
                VerifyOtpActivity verifyOtpActivity = VerifyOtpActivity.this;
                int i2 = com.psa.mym.mycitroenconnect.R.id.tvOtpCounter;
                ((AppCompatTextView) verifyOtpActivity._$_findCachedViewById(i2)).setVisibility(0);
                ((AppCompatTextView) VerifyOtpActivity.this._$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvResendOtp)).setVisibility(8);
                DecimalFormat decimalFormat = new DecimalFormat("00");
                long j3 = 60;
                long j4 = (j2 / 60000) % j3;
                long j5 = (j2 / 1000) % j3;
                ((AppCompatTextView) VerifyOtpActivity.this._$_findCachedViewById(i2)).setText(decimalFormat.format(j4) + " : " + decimalFormat.format(j5));
            }
        }.start();
    }

    private final void verifyOTPClick() {
        String str = this.mPageMode;
        switch (str.hashCode()) {
            case -2131583866:
                if (!str.equals(AppConstants.PAGE_MODE_CHANGE_PIN)) {
                    return;
                }
                break;
            case -1350309703:
                if (!str.equals(AppConstants.PAGE_MODE_REGISTRATION)) {
                    return;
                }
                break;
            case -1148260554:
                if (!str.equals(AppConstants.PAGE_MODE_ADD_CAR)) {
                    return;
                }
                break;
            case -305104263:
                if (!str.equals(AppConstants.PAGE_MODE_FORGOT_PIN)) {
                    return;
                }
                break;
            case 103149417:
                if (!str.equals("login")) {
                    return;
                }
                break;
            default:
                return;
        }
        apiVerifyOTP();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseActivity, com.psa.mym.mycitroenconnect.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseActivity, com.psa.mym.mycitroenconnect.BaseActivity
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

    @Subscribe
    public final void getMessage(@NotNull ErrorResponse event) {
        String string;
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        if (Intrinsics.areEqual(event.getApiName(), "FCMRegisterToken")) {
            string = event.getMsg();
        } else if (event.getStatusCode() != 401) {
            return;
        } else {
            string = getString(R.string.err_incorrect_otp);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.err_incorrect_otp)");
        }
        ExtensionsKt.showToast(this, string);
    }

    @Subscribe
    public final void getMessage(@NotNull FailResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        String string = getString(R.string.err_incorrect_otp);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.err_incorrect_otp)");
        ExtensionsKt.showToast(this, string);
    }

    @Subscribe
    public final void getMessage(@NotNull PostCommonResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getStatusCode() == 200) {
            showOTPSuccessDialog();
        } else {
            ExtensionsKt.showToast(this, event.getMessage());
        }
    }

    @Subscribe
    public final void getMessage(@NotNull Token event) {
        Intrinsics.checkNotNullParameter(event, "event");
        SharedPref.Companion companion = SharedPref.Companion;
        companion.setMobileNumber(this, this.mobileNumber);
        companion.setAppPin(this, this.appPin);
        companion.setPrimaryUserTokenDetails(this, event);
        if (Intrinsics.areEqual(this.mPageMode, AppConstants.PAGE_MODE_REGISTRATION)) {
            AppUtil.Companion.dismissDialog();
            Intent intent = new Intent(this, AddCarActivity.class);
            intent.putExtra(AppConstants.REGUSER, this.registerUserResponse);
            intent.putExtra("login", AppConstants.PAGE_MODE_ADD_VEHICLE);
            startActivity(intent);
            return;
        }
        OnBoardingService onBoardingService = new OnBoardingService();
        String str = this.mobileNumber;
        String substring = str.substring(1, str.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        onBoardingService.callGetUserProfileAPI(this, substring);
    }

    @Subscribe
    public final void getMessage(@NotNull SendOtpResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        this.sendOtpResponse = event;
        ExtensionsKt.showToast(this, event.getMessage());
        startCountDown();
    }

    @Subscribe
    public final void getMessage(@NotNull UserProfileResponse event) {
        String str;
        List<RegisteredVehicleItem> registeredVehicle;
        Intrinsics.checkNotNullParameter(event, "event");
        this.userProfileResponse = event;
        str = "N";
        if (Intrinsics.areEqual(this.mPageMode, AppConstants.PAGE_MODE_FORGOT_PIN)) {
            AppUtil.Companion.dismissDialog();
            List<RegisteredVehicleItem> registeredVehicle2 = event.getRegisteredVehicle();
            Intrinsics.checkNotNull(registeredVehicle2);
            boolean z = !registeredVehicle2.isEmpty();
            str = z ? "N" : "Y";
            Intent intent = new Intent(this, SetNewPinActivity.class);
            intent.putExtra("isGuest", str);
            intent.putExtra("mobileNumber", this.mobileNumber);
            intent.putExtra("mPageMode", this.mPageMode);
            intent.putExtra("isRegisteredUser", z);
            startActivity(intent);
            finish();
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (event.getRegisteredVehicle() != null) {
            Intrinsics.checkNotNull(event.getRegisteredVehicle());
            if (!registeredVehicle.isEmpty()) {
                List<RegisteredVehicleItem> registeredVehicle3 = event.getRegisteredVehicle();
                Intrinsics.checkNotNull(registeredVehicle3);
                for (RegisteredVehicleItem registeredVehicleItem : registeredVehicle3) {
                    String vinNum = registeredVehicleItem.getVinNum();
                    Intrinsics.checkNotNull(vinNum);
                    arrayList.add(vinNum);
                }
                registerFCMToken(arrayList, str);
            }
        }
        str = "Y";
        registerFCMToken(arrayList, str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x005e, code lost:
        if (r1 != false) goto L26;
     */
    @Subscribe
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void getMessage(@NotNull VerifyOTPResponse event) {
        Intent intent;
        boolean isBlank;
        Intrinsics.checkNotNullParameter(event, "event");
        if (!Intrinsics.areEqual(event.getSuccess(), "true")) {
            AppUtil.Companion.dismissDialog();
            String string = getString(R.string.err_incorrect_otp);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.err_incorrect_otp)");
            ExtensionsKt.showToast(this, string);
            return;
        }
        String str = this.mPageMode;
        boolean z = true;
        switch (str.hashCode()) {
            case -2131583866:
                if (str.equals(AppConstants.PAGE_MODE_CHANGE_PIN)) {
                    intent = new Intent(this, SetNewPinActivity.class);
                    intent.putExtra("mPageMode", this.mPageMode);
                    intent.putExtra("isRegisteredUser", true);
                    intent.putExtra(AppConstants.ARG_IS_FROM_MAIN_SCREEN, this.isFromMainScreen);
                    startActivity(intent);
                    return;
                }
                return;
            case -1350309703:
                if (str.equals(AppConstants.PAGE_MODE_REGISTRATION)) {
                    AppUtil.Companion.dismissDialog();
                    SharedPref.Companion.setPrimaryUserToken(this, event.getAccessToken());
                    if (Intrinsics.areEqual(this.registerUserResponse.isGuestUser(), "Y")) {
                        intent = new Intent(this, RegistrationActivity.class);
                        intent.putExtra(AppConstants.REGUSER, this.registerUserResponse);
                        startActivity(intent);
                        return;
                    }
                    apiAuthenticateUser();
                    return;
                }
                return;
            case -1148260554:
                if (str.equals(AppConstants.PAGE_MODE_ADD_CAR)) {
                    AppUtil.Companion.dismissDialog();
                    SharedPref.Companion companion = SharedPref.Companion;
                    String vinNumber = companion.getVinNumber(this);
                    if (vinNumber != null && vinNumber.length() != 0) {
                        z = false;
                    }
                    if (!z) {
                        isBlank = StringsKt__StringsJVMKt.isBlank(companion.getVinNumber(this));
                        break;
                    }
                    companion.setVinNumber(this, this.vinNum);
                    Intent intent2 = new Intent(this, ConfirmCarDetailsActivity.class);
                    intent2.putExtra(AppConstants.ADD_CAR_BUNDLE_NAME, this.vinNum);
                    intent2.putExtra("login", AppConstants.PAGE_MODE_ADD_CAR);
                    startActivity(intent2);
                    return;
                }
                return;
            case -305104263:
                if (!str.equals(AppConstants.PAGE_MODE_FORGOT_PIN)) {
                    return;
                }
                apiAuthenticateUser();
                return;
            case 103149417:
                if (!str.equals("login")) {
                    return;
                }
                apiAuthenticateUser();
                return;
            default:
                return;
        }
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        TextInputLayout textInputLayout;
        int i2;
        if (!Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnVerify))) {
            if (Intrinsics.areEqual(view, (AppCompatImageButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnBack))) {
                AppUtil.Companion.hideKeyboard(this);
                finish();
                return;
            } else if (Intrinsics.areEqual(view, (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvResendOtp))) {
                AppUtil.Companion.hideKeyboard(this);
                apiSendOtp();
                return;
            } else {
                return;
            }
        }
        int i3 = com.psa.mym.mycitroenconnect.R.id.edVerification;
        if (String.valueOf(((PinView) _$_findCachedViewById(i3)).getText()).length() == 0) {
            ((PinView) _$_findCachedViewById(i3)).requestFocus();
            ((PinView) _$_findCachedViewById(i3)).setLineColor(getColor(R.color.dark_red));
            ((PinView) _$_findCachedViewById(i3)).setTextColor(getColor(R.color.dark_red));
            textInputLayout = (TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtVerification);
            i2 = R.string.err_enter_otp;
        } else if (String.valueOf(((PinView) _$_findCachedViewById(i3)).getText()).length() == 4) {
            AppUtil.Companion.hideKeyboard(this);
            verifyOTPClick();
            return;
        } else {
            ((PinView) _$_findCachedViewById(i3)).requestFocus();
            ((PinView) _$_findCachedViewById(i3)).setLineColor(getColor(R.color.dark_red));
            ((PinView) _$_findCachedViewById(i3)).setTextColor(getColor(R.color.dark_red));
            textInputLayout = (TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtVerification);
            i2 = R.string.err_otp_length_4;
        }
        textInputLayout.setError(getString(i2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_verify_otp);
        getIntentData();
        setListeners();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        initView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.psa.mym.mycitroenconnect.BusBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        AppUtil.Companion.dismissDialog();
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
        int i5 = com.psa.mym.mycitroenconnect.R.id.txtVerification;
        if (((TextInputLayout) _$_findCachedViewById(i5)).getError() != null) {
            ((TextInputLayout) _$_findCachedViewById(i5)).setError(null);
        }
        int i6 = com.psa.mym.mycitroenconnect.R.id.edVerification;
        ((PinView) _$_findCachedViewById(i6)).setLineColor(getColor(R.color.dark_grey));
        ((PinView) _$_findCachedViewById(i6)).setTextColor(getColor(R.color.black));
    }
}
