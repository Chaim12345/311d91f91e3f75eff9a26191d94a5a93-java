package com.psa.mym.mycitroenconnect.controller.activities.onboarding;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
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
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OtpSuccessBottomSheetFragment;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.PostCommonResponse;
import com.psa.mym.mycitroenconnect.model.Token;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisterUserResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import com.psa.mym.mycitroenconnect.model.onboarding.UserProfileResponse;
import com.psa.mym.mycitroenconnect.services.FCMService;
import com.psa.mym.mycitroenconnect.services.OnBoardingService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class AppSecurityActivity extends BusBaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private RegisterUserResponse registerUserResponse = new RegisterUserResponse(null, null, null, null, null, 31, null);
    @NotNull
    private UserProfileResponse userProfileResponse = new UserProfileResponse(null, null, null, null, null, null, null, null, null, null, null, null, 4095, null);
    @NotNull
    private String mobileNumber = "";

    private final void apiAuthUser() {
        GetTokenBody getTokenBody = new GetTokenBody(null, null, null, null, 15, null);
        String str = this.mobileNumber;
        String substring = str.substring(3, str.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        getTokenBody.setUserName(substring);
        getTokenBody.setCountryCode("+91");
        OnBoardingService.callGetUserTokenAPI$default(new OnBoardingService(), this, getTokenBody, false, 4, null);
    }

    private final void displayDialog(boolean z, boolean z2) {
        OtpSuccessBottomSheetFragment newInstance = OtpSuccessBottomSheetFragment.Companion.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("mPageMode", AppConstants.PAGE_MODE_REGISTRATION);
        bundle.putBoolean("isBio", z);
        bundle.putBoolean("isRegisteredUser", z2);
        newInstance.setArguments(bundle);
        newInstance.show(getSupportFragmentManager(), OtpSuccessBottomSheetFragment.TAG);
        newInstance.setCancelable(false);
    }

    private final void getIntentData() {
        if (getIntent() == null || !getIntent().hasExtra(AppConstants.REGUSER)) {
            return;
        }
        Parcelable parcelableExtra = getIntent().getParcelableExtra(AppConstants.REGUSER);
        Intrinsics.checkNotNull(parcelableExtra);
        RegisterUserResponse registerUserResponse = (RegisterUserResponse) parcelableExtra;
        this.registerUserResponse = registerUserResponse;
        String mobileNum = registerUserResponse.getMobileNum();
        Intrinsics.checkNotNull(mobileNum);
        this.mobileNumber = mobileNum;
    }

    private final void registerFCMToken(List<String> list, final String str) {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.d
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                AppSecurityActivity.m96registerFCMToken$lambda0(AppSecurityActivity.this, str, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: registerFCMToken$lambda-0  reason: not valid java name */
    public static final void m96registerFCMToken$lambda0(AppSecurityActivity this$0, String isGuest, Task task) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(isGuest, "$isGuest");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            String str = this$0.mobileNumber;
            String substring = str.substring(1, str.length());
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            FCMService.registerToken$default(new FCMService(), this$0, new RegisterFCMBody(null, substring, AppUtil.Companion.getDeviceId(this$0), (String) task.getResult(), BuildConfig.VERSION_NAME, isGuest, null, 65, null), false, 4, null);
            return;
        }
        Logger logger = Logger.INSTANCE;
        logger.e("FCM Registration Token Failed " + task.getException());
        String string = this$0.getString(R.string.something_went_wrong);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.something_went_wrong)");
        ExtensionsKt.showToast(this$0, string);
    }

    private final void setListeners() {
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnConfirmAppSecurity)).setOnClickListener(this);
        ((PinView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edtQuickPin)).addTextChangedListener(new TextWatcher() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.AppSecurityActivity$setListeners$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                AppSecurityActivity appSecurityActivity = AppSecurityActivity.this;
                int i5 = com.psa.mym.mycitroenconnect.R.id.txtQuickPin;
                if (((TextInputLayout) appSecurityActivity._$_findCachedViewById(i5)).getError() != null) {
                    ((TextInputLayout) AppSecurityActivity.this._$_findCachedViewById(i5)).setError(null);
                }
                AppSecurityActivity appSecurityActivity2 = AppSecurityActivity.this;
                int i6 = com.psa.mym.mycitroenconnect.R.id.edtQuickPin;
                ((PinView) appSecurityActivity2._$_findCachedViewById(i6)).setLineColor(AppSecurityActivity.this.getColor(R.color.dark_grey));
                ((PinView) AppSecurityActivity.this._$_findCachedViewById(i6)).setTextColor(AppSecurityActivity.this.getColor(R.color.black));
            }
        });
        ((PinView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edtConfirmQuickPin)).addTextChangedListener(new TextWatcher() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.AppSecurityActivity$setListeners$2
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                AppSecurityActivity appSecurityActivity = AppSecurityActivity.this;
                int i5 = com.psa.mym.mycitroenconnect.R.id.txtConfirmQuickPin;
                if (((TextInputLayout) appSecurityActivity._$_findCachedViewById(i5)).getError() != null) {
                    ((TextInputLayout) AppSecurityActivity.this._$_findCachedViewById(i5)).setError(null);
                }
                AppSecurityActivity appSecurityActivity2 = AppSecurityActivity.this;
                int i6 = com.psa.mym.mycitroenconnect.R.id.edtConfirmQuickPin;
                ((PinView) appSecurityActivity2._$_findCachedViewById(i6)).setLineColor(AppSecurityActivity.this.getColor(R.color.dark_grey));
                ((PinView) AppSecurityActivity.this._$_findCachedViewById(i6)).setTextColor(AppSecurityActivity.this.getColor(R.color.black));
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0094, code lost:
        if (r1.hasEnrolledFingerprints() == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00af, code lost:
        if (r1.hasEnrolledFingerprints() == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b2, code lost:
        displayDialog(true, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b5, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void showOTPSuccessDialog() {
        RegisteredVehicleItem registeredVehicleItem;
        SharedPref.Companion companion = SharedPref.Companion;
        companion.setUserProfileResponse(this, this.userProfileResponse);
        companion.setMobileNumber(this, this.mobileNumber);
        String fullName = this.userProfileResponse.getFullName();
        if (fullName != null) {
            companion.setUserFirstName(this, fullName);
        }
        companion.setIsLogin(this, "true");
        if (this.userProfileResponse.getRegisteredVehicle() == null) {
            FingerprintManagerCompat from = FingerprintManagerCompat.from(this);
            Intrinsics.checkNotNullExpressionValue(from, "from(this@AppSecurityActivity)");
            if (from.isHardwareDetected()) {
            }
            companion.setIsFingerPrintAuth(this, "False");
            displayDialog(false, false);
            return;
        }
        List<RegisteredVehicleItem> registeredVehicle = this.userProfileResponse.getRegisteredVehicle();
        Intrinsics.checkNotNull(registeredVehicle);
        if (!(!registeredVehicle.isEmpty())) {
            FingerprintManagerCompat from2 = FingerprintManagerCompat.from(this);
            Intrinsics.checkNotNullExpressionValue(from2, "from(this@AppSecurityActivity)");
            if (from2.isHardwareDetected()) {
            }
            companion.setIsFingerPrintAuth(this, "False");
            displayDialog(false, false);
            return;
        }
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
        FingerprintManagerCompat from3 = FingerprintManagerCompat.from(this);
        Intrinsics.checkNotNullExpressionValue(from3, "from(this@AppSecurityActivity)");
        if (from3.isHardwareDetected() && from3.hasEnrolledFingerprints()) {
            displayDialog(true, true);
            return;
        }
        companion.setIsFingerPrintAuth(this, "False");
        displayDialog(false, true);
    }

    private final void storePin() {
        SharedPref.Companion companion = SharedPref.Companion;
        companion.setIsLogin(this, "true");
        companion.setAppPin(this, String.valueOf(((PinView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edtQuickPin)).getText()));
        AppUtil.Companion.showDialog(this);
        OnBoardingService onBoardingService = new OnBoardingService();
        String str = this.mobileNumber;
        String substring = str.substring(1, str.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        onBoardingService.callGetUserProfileAPI(this, substring);
    }

    private final boolean validate() {
        TextInputLayout textInputLayout;
        int i2;
        int i3 = com.psa.mym.mycitroenconnect.R.id.edtQuickPin;
        if (String.valueOf(((PinView) _$_findCachedViewById(i3)).getText()).length() == 0) {
            ((PinView) _$_findCachedViewById(i3)).requestFocus();
            ((PinView) _$_findCachedViewById(i3)).setLineColor(getColor(R.color.dark_red));
            ((PinView) _$_findCachedViewById(i3)).setTextColor(getColor(R.color.dark_red));
            textInputLayout = (TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtQuickPin);
            i2 = R.string.err_enter_pin;
        } else if (String.valueOf(((PinView) _$_findCachedViewById(i3)).getText()).length() != 4) {
            ((PinView) _$_findCachedViewById(i3)).requestFocus();
            ((PinView) _$_findCachedViewById(i3)).setLineColor(getColor(R.color.dark_red));
            ((PinView) _$_findCachedViewById(i3)).setTextColor(getColor(R.color.dark_red));
            textInputLayout = (TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtQuickPin);
            i2 = R.string.err_pin_length_4;
        } else {
            int i4 = com.psa.mym.mycitroenconnect.R.id.edtConfirmQuickPin;
            if (String.valueOf(((PinView) _$_findCachedViewById(i4)).getText()).length() == 0) {
                ((PinView) _$_findCachedViewById(i4)).requestFocus();
                ((PinView) _$_findCachedViewById(i4)).setLineColor(getColor(R.color.dark_red));
                ((PinView) _$_findCachedViewById(i4)).setTextColor(getColor(R.color.dark_red));
                textInputLayout = (TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtConfirmQuickPin);
                i2 = R.string.err_enter_confirm_pin;
            } else if (Intrinsics.areEqual(String.valueOf(((PinView) _$_findCachedViewById(i3)).getText()), String.valueOf(((PinView) _$_findCachedViewById(i4)).getText()))) {
                return true;
            } else {
                ((PinView) _$_findCachedViewById(i4)).requestFocus();
                ((PinView) _$_findCachedViewById(i4)).setLineColor(getColor(R.color.dark_red));
                ((PinView) _$_findCachedViewById(i4)).setTextColor(getColor(R.color.dark_red));
                textInputLayout = (TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtConfirmQuickPin);
                i2 = R.string.err_pin_mismatch;
            }
        }
        textInputLayout.setError(getString(i2));
        return false;
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

    @Subscribe
    public final void getMessage(@NotNull ErrorResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (Intrinsics.areEqual(event.getApiName(), "FCMRegisterToken")) {
            ExtensionsKt.showToast(this, event.getMsg());
        }
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
        AppUtil.Companion companion = AppUtil.Companion;
        companion.dismissDialog();
        SharedPref.Companion companion2 = SharedPref.Companion;
        companion2.setPrimaryUserTokenDetails(this, event);
        companion2.setIsLogin(this, "true");
        companion2.setAppPin(this, String.valueOf(((PinView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edtQuickPin)).getText()));
        companion.showDialog(this);
        OnBoardingService onBoardingService = new OnBoardingService();
        String str = this.mobileNumber;
        String substring = str.substring(1, str.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        onBoardingService.callGetUserProfileAPI(this, substring);
    }

    @Subscribe
    public final void getMessage(@NotNull UserProfileResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        this.userProfileResponse = event;
        ArrayList arrayList = new ArrayList();
        String str = "Y";
        if (event.getRegisteredVehicle() != null) {
            List<RegisteredVehicleItem> registeredVehicle = event.getRegisteredVehicle();
            Intrinsics.checkNotNull(registeredVehicle);
            if (!registeredVehicle.isEmpty()) {
                List<RegisteredVehicleItem> registeredVehicle2 = event.getRegisteredVehicle();
                Intrinsics.checkNotNull(registeredVehicle2);
                for (RegisteredVehicleItem registeredVehicleItem : registeredVehicle2) {
                    String vinNum = registeredVehicleItem.getVinNum();
                    Intrinsics.checkNotNull(vinNum);
                    arrayList.add(vinNum);
                }
                str = "N";
            }
        }
        registerFCMToken(arrayList, str);
    }

    @NotNull
    public final String getMobileNumber() {
        return this.mobileNumber;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnConfirmAppSecurity)) && validate()) {
            storePin();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_app_security);
        getIntentData();
        setListeners();
    }

    public final void setMobileNumber(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mobileNumber = str;
    }
}
