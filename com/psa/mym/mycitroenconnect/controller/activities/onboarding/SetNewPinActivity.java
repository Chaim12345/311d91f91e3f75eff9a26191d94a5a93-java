package com.psa.mym.mycitroenconnect.controller.activities.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import com.chaos.view.PinView;
import com.google.android.material.textfield.TextInputLayout;
import com.psa.mym.mycitroenconnect.BusBaseActivity;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.api.body.onboarding.SetPinBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OtpSuccessBottomSheetFragment;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.Token;
import com.psa.mym.mycitroenconnect.model.ValidationErrorResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.SetPinResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCar;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCarResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCars;
import com.psa.mym.mycitroenconnect.services.OnBoardingService;
import com.psa.mym.mycitroenconnect.services.SecondaryUserService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SetNewPinActivity extends BusBaseActivity {
    private boolean isRegisteredUser;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String mobileNumber = "";
    @NotNull
    private String isGuest = "";
    @NotNull
    private String mPageMode = "";
    private boolean isFromMainScreen = true;

    private final void apiPermission() {
        boolean isBlank;
        AppUtil.Companion.showDialog(this);
        if (this.mobileNumber.length() == 0) {
            isBlank = StringsKt__StringsJVMKt.isBlank(this.mobileNumber);
            if (isBlank) {
                this.mobileNumber = SharedPref.Companion.getMobileNumber(this);
            }
        }
        SecondaryUserService secondaryUserService = new SecondaryUserService();
        StringBuilder sb = new StringBuilder();
        sb.append("91");
        String str = this.mobileNumber;
        String substring = str.substring(3, str.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        sb.append(substring);
        secondaryUserService.getMyCarList(this, sb.toString(), AppConstants.SCREEN_SET_NEW_PIN);
    }

    private final void apiSetPin() {
        AppUtil.Companion.showDialog(this);
        String valueOf = String.valueOf(((PinView) _$_findCachedViewById(R.id.edtSetNewPin)).getText());
        String valueOf2 = String.valueOf(((PinView) _$_findCachedViewById(R.id.edtConfirmPin)).getText());
        String str = this.mobileNumber;
        String substring = str.substring(3, str.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        new OnBoardingService().callSetPinAPI(this, new SetPinBody(valueOf, valueOf2, substring, this.isGuest, "+91"));
    }

    private final void displayDialog(boolean z, boolean z2) {
        OtpSuccessBottomSheetFragment newInstance = OtpSuccessBottomSheetFragment.Companion.newInstance();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isBio", z);
        bundle.putBoolean("isRegisteredUser", z2);
        newInstance.setArguments(bundle);
        newInstance.show(getSupportFragmentManager(), OtpSuccessBottomSheetFragment.TAG);
        newInstance.setCancelable(false);
    }

    private final void displayDialogForChangePin() {
        OtpSuccessBottomSheetFragment newInstance = OtpSuccessBottomSheetFragment.Companion.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("mPageMode", this.mPageMode);
        bundle.putBoolean("isBio", false);
        bundle.putBoolean("isRegisteredUser", this.isRegisteredUser);
        bundle.putBoolean(AppConstants.ARG_IS_FROM_MAIN_SCREEN, this.isFromMainScreen);
        newInstance.setArguments(bundle);
        newInstance.show(getSupportFragmentManager(), OtpSuccessBottomSheetFragment.TAG);
        newInstance.setCancelable(false);
    }

    private final void getIntentData() {
        if (getIntent() != null) {
            if (getIntent().hasExtra("isGuest")) {
                String stringExtra = getIntent().getStringExtra("isGuest");
                Intrinsics.checkNotNull(stringExtra);
                this.isGuest = stringExtra;
            }
            if (getIntent().hasExtra("mobileNumber")) {
                String stringExtra2 = getIntent().getStringExtra("mobileNumber");
                Intrinsics.checkNotNull(stringExtra2);
                this.mobileNumber = stringExtra2;
            }
            if (getIntent().hasExtra("mPageMode")) {
                String stringExtra3 = getIntent().getStringExtra("mPageMode");
                Intrinsics.checkNotNull(stringExtra3);
                this.mPageMode = stringExtra3;
            }
            if (getIntent().hasExtra("isRegisteredUser")) {
                this.isRegisteredUser = getIntent().getBooleanExtra("isRegisteredUser", false);
            }
            if (getIntent().hasExtra(AppConstants.ARG_IS_FROM_MAIN_SCREEN)) {
                this.isFromMainScreen = getIntent().getBooleanExtra(AppConstants.ARG_IS_FROM_MAIN_SCREEN, true);
            }
        }
    }

    private final void initView() {
        StringBuilder sb = new StringBuilder();
        sb.append(getResources().getString(uat.psa.mym.mycitroenconnect.R.string.set_new_pin));
        sb.append("!");
        ((TextView) _$_findCachedViewById(R.id.tvTitle)).setText(sb);
        String str = this.mPageMode;
        int hashCode = str.hashCode();
        if (hashCode == -2131583866) {
            if (str.equals(AppConstants.PAGE_MODE_CHANGE_PIN)) {
                ((AppCompatButton) _$_findCachedViewById(R.id.setNewPinBtnVerify)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.lbl_update));
            }
        } else if (hashCode == -305104263) {
            if (str.equals(AppConstants.PAGE_MODE_FORGOT_PIN)) {
                AppCompatImageButton setNewPinBtnBack = (AppCompatImageButton) _$_findCachedViewById(R.id.setNewPinBtnBack);
                Intrinsics.checkNotNullExpressionValue(setNewPinBtnBack, "setNewPinBtnBack");
                ExtensionsKt.show(setNewPinBtnBack);
            }
        } else if (hashCode == 103149417 && str.equals("login")) {
            AppCompatImageButton setNewPinBtnBack2 = (AppCompatImageButton) _$_findCachedViewById(R.id.setNewPinBtnBack);
            Intrinsics.checkNotNullExpressionValue(setNewPinBtnBack2, "setNewPinBtnBack");
            ExtensionsKt.hide(setNewPinBtnBack2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:57:0x0013, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean isRegisteredUser(MyCars myCars) {
        String str;
        if (myCars.size() > 1) {
            CollectionsKt__MutableCollectionsJVMKt.sortWith(myCars, new Comparator() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.SetNewPinActivity$isRegisteredUser$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(T t2, T t3) {
                    int compareValues;
                    compareValues = ComparisonsKt__ComparisonsKt.compareValues(((MyCar) t2).getUserType(), ((MyCar) t3).getUserType());
                    return compareValues;
                }
            });
        }
        for (MyCar myCar : myCars) {
            String userType = myCar.getUserType();
            String str2 = null;
            if (userType != null) {
                str = userType.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            } else {
                str = null;
            }
            if (Intrinsics.areEqual(str, "p")) {
                SharedPref.Companion companion = SharedPref.Companion;
                companion.setIsPrimaryUser(this, "true");
                companion.setIsVerifiedUser(this, "true");
            } else if (!Intrinsics.areEqual(str, "s")) {
                return false;
            } else {
                SharedPref.Companion companion2 = SharedPref.Companion;
                companion2.setIsGuestUser(this, "true");
                String inviteStatus = myCar.getInviteStatus();
                if (inviteStatus != null) {
                    str2 = inviteStatus.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                }
                if (str2 != null) {
                    switch (str2.hashCode()) {
                        case -2146525273:
                            if (str2.equals("accepted")) {
                                companion2.setIsVerifiedUser(this, "true");
                                break;
                            } else {
                                continue;
                            }
                        case -1994383672:
                            if (str2.equals(AppConstants.SECONDARY_USER_STATE_VERIFIED)) {
                                companion2.setIsVerifiedUser(this, "true");
                                break;
                            } else {
                                continue;
                            }
                        case -682587753:
                            if (str2.equals(AppConstants.SECONDARY_USER_STATE_PENDING)) {
                                companion2.setIsVerifiedUser(this, "false");
                                return false;
                            }
                            continue;
                        case -608496514:
                            if (str2.equals("rejected")) {
                                companion2.setIsVerifiedUser(this, "false");
                                return false;
                            }
                            continue;
                    }
                }
            }
            setVehicleDetails(myCar);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onResume$lambda-0  reason: not valid java name */
    public static final void m102onResume$lambda0(SetNewPinActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ExtensionsKt.showKeyboard(this$0);
    }

    private final void redirectToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(268468224);
        intent.putExtra("login", "login");
        startActivity(intent);
        finish();
    }

    private final void setListeners() {
        ((AppCompatButton) _$_findCachedViewById(R.id.setNewPinBtnVerify)).setOnClickListener(this);
        ((AppCompatImageButton) _$_findCachedViewById(R.id.setNewPinBtnBack)).setOnClickListener(this);
        ((PinView) _$_findCachedViewById(R.id.edtSetNewPin)).addTextChangedListener(new TextWatcher() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.SetNewPinActivity$setListeners$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                SetNewPinActivity setNewPinActivity = SetNewPinActivity.this;
                int i5 = R.id.txtSetNewPin;
                if (((TextInputLayout) setNewPinActivity._$_findCachedViewById(i5)).getError() != null) {
                    ((TextInputLayout) SetNewPinActivity.this._$_findCachedViewById(i5)).setError(null);
                }
                SetNewPinActivity setNewPinActivity2 = SetNewPinActivity.this;
                int i6 = R.id.edtSetNewPin;
                ((PinView) setNewPinActivity2._$_findCachedViewById(i6)).setLineColor(SetNewPinActivity.this.getColor(uat.psa.mym.mycitroenconnect.R.color.dark_grey));
                ((PinView) SetNewPinActivity.this._$_findCachedViewById(i6)).setTextColor(SetNewPinActivity.this.getColor(uat.psa.mym.mycitroenconnect.R.color.black));
            }
        });
        ((PinView) _$_findCachedViewById(R.id.edtConfirmPin)).addTextChangedListener(new TextWatcher() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.SetNewPinActivity$setListeners$2
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                SetNewPinActivity setNewPinActivity = SetNewPinActivity.this;
                int i5 = R.id.txtConfirmPin;
                if (((TextInputLayout) setNewPinActivity._$_findCachedViewById(i5)).getError() != null) {
                    ((TextInputLayout) SetNewPinActivity.this._$_findCachedViewById(i5)).setError(null);
                }
                SetNewPinActivity setNewPinActivity2 = SetNewPinActivity.this;
                int i6 = R.id.edtConfirmPin;
                ((PinView) setNewPinActivity2._$_findCachedViewById(i6)).setLineColor(SetNewPinActivity.this.getColor(uat.psa.mym.mycitroenconnect.R.color.dark_grey));
                ((PinView) SetNewPinActivity.this._$_findCachedViewById(i6)).setTextColor(SetNewPinActivity.this.getColor(uat.psa.mym.mycitroenconnect.R.color.black));
                boolean z = false;
                if (charSequence != null && charSequence.length() == 4) {
                    z = true;
                }
                if (z) {
                    SetNewPinActivity.this.validate();
                }
            }
        });
    }

    private final void setTokenDetails(Token token) {
        if (token != null) {
            SharedPref.Companion.setPrimaryUserTokenDetails(this, token);
        }
    }

    private final void setVehicleDetails(MyCar myCar) {
        SharedPref.Companion companion = SharedPref.Companion;
        String vinNum = myCar.getVinNum();
        if (vinNum == null) {
            vinNum = "";
        }
        companion.setVinNumber(this, vinNum);
        String vehicleType = myCar.getVehicleType();
        if (vehicleType != null) {
            companion.setVehicleType(this, vehicleType);
        }
        setTokenDetails(myCar.getToken());
    }

    private final void showOTPSuccessDialog() {
        SharedPref.Companion companion = SharedPref.Companion;
        companion.setAppPin(this, String.valueOf(((PinView) _$_findCachedViewById(R.id.edtSetNewPin)).getText()));
        companion.setIsLogin(this, "true");
        FingerprintManagerCompat from = FingerprintManagerCompat.from(this);
        Intrinsics.checkNotNullExpressionValue(from, "from(this@SetNewPinActivity)");
        if (from.isHardwareDetected() && from.hasEnrolledFingerprints()) {
            displayDialog(true, this.isRegisteredUser);
            return;
        }
        companion.setIsFingerPrintAuth(this, "False");
        displayDialog(false, this.isRegisteredUser);
    }

    private final void showPinChangeSuccessDialog() {
        SharedPref.Companion.setAppPin(this, String.valueOf(((PinView) _$_findCachedViewById(R.id.edtSetNewPin)).getText()));
        displayDialogForChangePin();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean validate() {
        TextInputLayout textInputLayout;
        int i2;
        int i3 = R.id.edtSetNewPin;
        if (String.valueOf(((PinView) _$_findCachedViewById(i3)).getText()).length() == 0) {
            ((PinView) _$_findCachedViewById(i3)).requestFocus();
            ((PinView) _$_findCachedViewById(i3)).setLineColor(getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
            ((PinView) _$_findCachedViewById(i3)).setTextColor(getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
            textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtSetNewPin);
            i2 = uat.psa.mym.mycitroenconnect.R.string.err_enter_pin;
        } else if (String.valueOf(((PinView) _$_findCachedViewById(i3)).getText()).length() != 4) {
            ((PinView) _$_findCachedViewById(i3)).requestFocus();
            ((PinView) _$_findCachedViewById(i3)).setLineColor(getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
            ((PinView) _$_findCachedViewById(i3)).setTextColor(getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
            textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtSetNewPin);
            i2 = uat.psa.mym.mycitroenconnect.R.string.err_pin_length_4;
        } else {
            int i4 = R.id.edtConfirmPin;
            if (String.valueOf(((PinView) _$_findCachedViewById(i4)).getText()).length() == 0) {
                ((PinView) _$_findCachedViewById(i4)).requestFocus();
                ((PinView) _$_findCachedViewById(i4)).setLineColor(getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
                ((PinView) _$_findCachedViewById(i4)).setTextColor(getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
                textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtConfirmPin);
                i2 = uat.psa.mym.mycitroenconnect.R.string.err_enter_confirm_pin;
            } else if (String.valueOf(((PinView) _$_findCachedViewById(i4)).getText()).length() != 4) {
                ((PinView) _$_findCachedViewById(i4)).requestFocus();
                ((PinView) _$_findCachedViewById(i4)).setLineColor(getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
                ((PinView) _$_findCachedViewById(i4)).setTextColor(getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
                textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtConfirmPin);
                i2 = uat.psa.mym.mycitroenconnect.R.string.err_confirm_pin_length_4;
            } else if (!Intrinsics.areEqual(String.valueOf(((PinView) _$_findCachedViewById(i3)).getText()), String.valueOf(((PinView) _$_findCachedViewById(i4)).getText()))) {
                ((PinView) _$_findCachedViewById(i4)).requestFocus();
                ((PinView) _$_findCachedViewById(i4)).setLineColor(getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
                ((PinView) _$_findCachedViewById(i4)).setTextColor(getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
                textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtConfirmPin);
                i2 = uat.psa.mym.mycitroenconnect.R.string.err_pin_mismatch;
            } else if (!Intrinsics.areEqual(String.valueOf(((PinView) _$_findCachedViewById(i3)).getText()), SharedPref.Companion.getAppPin(this))) {
                return true;
            } else {
                ((PinView) _$_findCachedViewById(i3)).requestFocus();
                ((PinView) _$_findCachedViewById(i3)).setLineColor(getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
                ((PinView) _$_findCachedViewById(i3)).setTextColor(getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
                textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtSetNewPin);
                i2 = uat.psa.mym.mycitroenconnect.R.string.err_pin_same_as_current;
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
        AppUtil.Companion.dismissDialog();
        showOTPSuccessDialog();
    }

    @Subscribe
    public final void getMessage(@NotNull ValidationErrorResponse event) {
        String replace$default;
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        replace$default = StringsKt__StringsJVMKt.replace$default(event.getErrorList().get(0), "Unresolved key: ", "", false, 4, (Object) null);
        ExtensionsKt.showToast(this, replace$default);
    }

    @Subscribe
    public final void getMessage(@NotNull SetPinResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        ExtensionsKt.showToast(this, event.getMessage());
        redirectToLogin();
    }

    @Subscribe
    public final void getResponse(@NotNull MyCarResponse myCarResponse) {
        String str;
        Intrinsics.checkNotNullParameter(myCarResponse, "myCarResponse");
        AppUtil.Companion.dismissDialog();
        if (Intrinsics.areEqual(myCarResponse.getScreenName(), AppConstants.SCREEN_SET_NEW_PIN)) {
            MyCars myCars = myCarResponse.getMyCars();
            if (!(myCars == null || myCars.isEmpty())) {
                if ((true ^ myCars.isEmpty()) && myCars.size() > 0) {
                    MyCar myCar = myCars.get(0);
                    Intrinsics.checkNotNullExpressionValue(myCar, "response[0]");
                    MyCar myCar2 = myCar;
                    String userType = myCar2.getUserType();
                    if (userType != null) {
                        str = userType.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    } else {
                        str = null;
                    }
                    if (Intrinsics.areEqual(str, "g")) {
                        Token token = myCar2.getToken();
                        if (token != null) {
                            SharedPref.Companion.setSecondaryUserTokenDetails(this, token);
                        }
                        myCars.remove(0);
                    }
                }
                SharedPref.Companion.setVinTokenDetails(this, myCars);
            }
            this.isRegisteredUser = isRegisteredUser(myCars);
            showOTPSuccessDialog();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onClick(@Nullable View view) {
        boolean isBlank;
        AppUtil.Companion.hideKeyboard(this);
        if (!Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.setNewPinBtnVerify))) {
            if (Intrinsics.areEqual(view, (AppCompatImageButton) _$_findCachedViewById(R.id.setNewPinBtnBack))) {
                finish();
                return;
            }
            return;
        }
        String str = this.mPageMode;
        int hashCode = str.hashCode();
        if (hashCode != -2131583866) {
            if (hashCode != -305104263) {
                if (validate()) {
                    return;
                }
                apiPermission();
                return;
            } else if (validate()) {
            }
        } else if (str.equals(AppConstants.PAGE_MODE_CHANGE_PIN)) {
            if (validate()) {
                SharedPref.Companion.setIsPrimaryUser(this, "true");
                showPinChangeSuccessDialog();
                return;
            }
            return;
        }
        if (this.mobileNumber.length() == 0) {
            isBlank = StringsKt__StringsJVMKt.isBlank(this.mobileNumber);
            if (isBlank) {
                this.mobileNumber = SharedPref.Companion.getMobileNumber(this);
            }
        }
        apiSetPin();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(uat.psa.mym.mycitroenconnect.R.layout.activity_set_new_pin);
        getIntentData();
        initView();
        setListeners();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        int i2 = R.id.edtSetNewPin;
        ((PinView) _$_findCachedViewById(i2)).requestFocus();
        ((PinView) _$_findCachedViewById(i2)).postDelayed(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.k
            @Override // java.lang.Runnable
            public final void run() {
                SetNewPinActivity.m102onResume$lambda0(SetNewPinActivity.this);
            }
        }, 200L);
    }
}
