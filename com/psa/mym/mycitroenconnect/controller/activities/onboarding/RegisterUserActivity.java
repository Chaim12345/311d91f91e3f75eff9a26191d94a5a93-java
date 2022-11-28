package com.psa.mym.mycitroenconnect.controller.activities.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.psa.mym.mycitroenconnect.BusBaseActivity;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.api.body.onboarding.RegisterUserBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ContactSupportDialog;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisterErrorResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisterUserResponse;
import com.psa.mym.mycitroenconnect.services.OnBoardingService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class RegisterUserActivity extends BusBaseActivity implements TextWatcher {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private RegisterUserResponse registerUserResponse = new RegisterUserResponse(null, null, null, null, null, 31, null);

    private final void apiRegisterUser() {
        CharSequence trim;
        CharSequence trim2;
        CharSequence trim3;
        CharSequence trim4;
        TextInputLayout textInputLayout;
        int i2;
        boolean isBlank;
        String valueOf = String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edMobileNumber)).getText());
        if (valueOf.length() == 0) {
            isBlank = StringsKt__StringsJVMKt.isBlank(valueOf);
            if (isBlank) {
                textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutMobileNumber);
                i2 = uat.psa.mym.mycitroenconnect.R.string.please_enter_mobile_number;
                textInputLayout.setError(getString(i2));
            }
        }
        AppUtil.Companion companion = AppUtil.Companion;
        if (companion.isMobileNumberValidNew(valueOf)) {
            int i3 = R.id.edPasswordReg;
            trim = StringsKt__StringsKt.trim((CharSequence) String.valueOf(((TextInputEditText) _$_findCachedViewById(i3)).getText()));
            if (!(trim.toString().length() == 0)) {
                int i4 = R.id.edConfirmPassword;
                trim2 = StringsKt__StringsKt.trim((CharSequence) String.valueOf(((TextInputEditText) _$_findCachedViewById(i4)).getText()));
                if (trim2.toString().length() > 0) {
                    trim3 = StringsKt__StringsKt.trim((CharSequence) String.valueOf(((TextInputEditText) _$_findCachedViewById(i3)).getText()));
                    String obj = trim3.toString();
                    trim4 = StringsKt__StringsKt.trim((CharSequence) String.valueOf(((TextInputEditText) _$_findCachedViewById(i4)).getText()));
                    if (!Intrinsics.areEqual(obj, trim4.toString())) {
                        textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutPasswordReg);
                        i2 = uat.psa.mym.mycitroenconnect.R.string.new_confirm_password_not_match;
                    }
                }
                companion.showDialog(this);
                RegisterUserBody registerUserBody = new RegisterUserBody();
                registerUserBody.setMobileNum(valueOf);
                registerUserBody.setCountryCode("+91");
                new OnBoardingService().callUserRegisterAPI(this, registerUserBody);
                return;
            }
            textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutPasswordReg);
            i2 = uat.psa.mym.mycitroenconnect.R.string.enter_new_password;
        } else {
            textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutMobileNumber);
            i2 = uat.psa.mym.mycitroenconnect.R.string.enter_valid_mobile;
        }
        textInputLayout.setError(getString(i2));
    }

    private final void goToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private final void setListeners() {
        ((TextInputEditText) _$_findCachedViewById(R.id.edMobileNumber)).addTextChangedListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnNext)).setOnClickListener(this);
        ((LinearLayoutCompat) _$_findCachedViewById(R.id.layoutLoginNow)).setOnClickListener(this);
        ((LinearLayoutCompat) _$_findCachedViewById(R.id.layoutHavingTroubleView)).setOnClickListener(this);
    }

    private final void showContactSupportDialog() {
        ContactSupportDialog contactSupportDialog = new ContactSupportDialog();
        contactSupportDialog.show(getSupportFragmentManager(), ContactSupportDialog.TAG);
        contactSupportDialog.setCancelable(false);
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
    public final void getMessage(@NotNull RegisterErrorResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        ExtensionsKt.showToast(this, event.getMessage());
    }

    @Subscribe
    public final void getMessage(@NotNull RegisterUserResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        this.registerUserResponse = event;
        Intent intent = new Intent(this, VerifyOtpActivity.class);
        intent.putExtra(AppConstants.REGUSER, this.registerUserResponse);
        intent.putExtra("login", AppConstants.PAGE_MODE_REGISTRATION);
        startActivity(intent);
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        Intrinsics.checkNotNull(view);
        int id = view.getId();
        if (id == uat.psa.mym.mycitroenconnect.R.id.btnNext) {
            apiRegisterUser();
        } else if (id == uat.psa.mym.mycitroenconnect.R.id.layoutHavingTroubleView) {
            showContactSupportDialog();
        } else if (id != uat.psa.mym.mycitroenconnect.R.id.layoutLoginNow) {
        } else {
            goToLogin();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(uat.psa.mym.mycitroenconnect.R.layout.activity_register_user);
        setListeners();
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
        int i5 = R.id.txtLayoutMobileNumber;
        if (((TextInputLayout) _$_findCachedViewById(i5)).getError() != null) {
            ((TextInputLayout) _$_findCachedViewById(i5)).setError(null);
        }
    }
}
