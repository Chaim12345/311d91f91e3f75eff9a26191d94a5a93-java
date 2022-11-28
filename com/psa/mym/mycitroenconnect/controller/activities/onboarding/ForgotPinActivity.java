package com.psa.mym.mycitroenconnect.controller.activities.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.psa.mym.mycitroenconnect.BusBaseActivity;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.api.body.onboarding.GetTokenBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ContactSupportDialog;
import com.psa.mym.mycitroenconnect.model.Token;
import com.psa.mym.mycitroenconnect.services.OnBoardingService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt__CharJVMKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ForgotPinActivity extends BusBaseActivity implements TextWatcher {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String mobileNumber = "";

    private final void initView() {
        int i2 = R.id.labelForgotPinContact_us;
        AppCompatTextView labelForgotPinContact_us = (AppCompatTextView) _$_findCachedViewById(i2);
        Intrinsics.checkNotNullExpressionValue(labelForgotPinContact_us, "labelForgotPinContact_us");
        setSpannableString(labelForgotPinContact_us, getResources().getText(uat.psa.mym.mycitroenconnect.R.string.having_trouble).toString(), 15, 0);
        ((AppCompatTextView) _$_findCachedViewById(i2)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.forgotPinBtnNext)).setOnClickListener(this);
        ((TextInputEditText) _$_findCachedViewById(R.id.edForgotPinMobileNumber)).addTextChangedListener(this);
    }

    private final boolean isRegisteredMobileNumber() {
        this.mobileNumber = String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edForgotPinMobileNumber)).getText());
        String mobileNumber = SharedPref.Companion.getMobileNumber(this);
        AppUtil.Companion companion = AppUtil.Companion;
        return Intrinsics.areEqual(companion.getValidPhoneString(mobileNumber), companion.getValidPhoneString(this.mobileNumber));
    }

    private final void setSpannableString(AppCompatTextView appCompatTextView, String str, int i2, int i3) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, uat.psa.mym.mycitroenconnect.R.color.primary_color_1)), str.length() - i2, str.length() - i3, 33);
        appCompatTextView.setText(spannableString);
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
    public final void getMessage(@NotNull Token event) {
        boolean isWhitespace;
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        SharedPref.Companion.setPrimaryUserTokenDetails(this, event);
        Intent intent = new Intent(this, VerifyOtpActivity.class);
        intent.putExtra("login", AppConstants.PAGE_MODE_FORGOT_PIN);
        StringBuilder sb = new StringBuilder();
        sb.append("+91");
        String str = this.mobileNumber;
        StringBuilder sb2 = new StringBuilder();
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            isWhitespace = CharsKt__CharJVMKt.isWhitespace(charAt);
            if (!isWhitespace) {
                sb2.append(charAt);
            }
        }
        String sb3 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(sb3, "filterTo(StringBuilder(), predicate).toString()");
        sb.append(sb3);
        intent.putExtra(AppConstants.MOBILENUMBER, sb.toString());
        startActivity(intent);
        finish();
    }

    @NotNull
    public final String getMobileNumber() {
        return this.mobileNumber;
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        TextInputLayout textInputLayout;
        int i2;
        Intrinsics.checkNotNull(view);
        int id = view.getId();
        if (id != uat.psa.mym.mycitroenconnect.R.id.forgotPinBtnNext) {
            if (id != uat.psa.mym.mycitroenconnect.R.id.labelForgotPinContact_us) {
                return;
            }
            showContactSupportDialog();
            return;
        }
        String valueOf = String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edForgotPinMobileNumber)).getText());
        this.mobileNumber = valueOf;
        AppUtil.Companion companion = AppUtil.Companion;
        if (!companion.isMobileNumberValidNew(valueOf)) {
            textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtForgotPinMobileNumber);
            i2 = uat.psa.mym.mycitroenconnect.R.string.enter_valid_mobile;
        } else if (isRegisteredMobileNumber()) {
            companion.showDialog(this);
            GetTokenBody getTokenBody = new GetTokenBody(null, null, null, null, 15, null);
            getTokenBody.setUserName(this.mobileNumber);
            getTokenBody.setCountryCode("+91");
            OnBoardingService.callGetUserTokenAPI$default(new OnBoardingService(), this, getTokenBody, false, 4, null);
            return;
        } else {
            textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtForgotPinMobileNumber);
            i2 = uat.psa.mym.mycitroenconnect.R.string.err_incorrect_reg_number;
        }
        textInputLayout.setError(getString(i2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(uat.psa.mym.mycitroenconnect.R.layout.activity_forgot_pin);
        initView();
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
        int i5 = R.id.txtForgotPinMobileNumber;
        if (((TextInputLayout) _$_findCachedViewById(i5)).getError() != null) {
            ((TextInputLayout) _$_findCachedViewById(i5)).setError(null);
        }
    }

    public final void setMobileNumber(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mobileNumber = str;
    }
}
