package com.psa.mym.mycitroenconnect.controller.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import com.chaos.view.PinView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.psa.mym.mycitroenconnect.BaseActivity;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.onboarding.VerifyOtpActivity;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ChangePinActivity extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    private Boolean isFromMainScreen = Boolean.TRUE;

    private final void getIntentData() {
        if (getIntent() == null || !getIntent().hasExtra(AppConstants.ARG_IS_FROM_MAIN_SCREEN)) {
            return;
        }
        this.isFromMainScreen = Boolean.valueOf(getIntent().getBooleanExtra(AppConstants.ARG_IS_FROM_MAIN_SCREEN, true));
    }

    private final void initView() {
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvDbHeaderTitle)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.lbl_change_pin));
    }

    private final boolean isRegisteredMobileNumber() {
        String valueOf = String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edChangePinRegisteredMobileNo)).getText());
        String mobileNumber = SharedPref.Companion.getMobileNumber(this);
        AppUtil.Companion companion = AppUtil.Companion;
        return Intrinsics.areEqual(companion.getValidPhoneString(mobileNumber), companion.getValidPhoneString(valueOf));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetMobileNoError() {
        int i2 = R.id.tilMobileNumber;
        if (((TextInputLayout) _$_findCachedViewById(i2)).getError() != null) {
            ((TextInputLayout) _$_findCachedViewById(i2)).setError(null);
        }
        AppCompatTextView tvChangePinOtpText = (AppCompatTextView) _$_findCachedViewById(R.id.tvChangePinOtpText);
        Intrinsics.checkNotNullExpressionValue(tvChangePinOtpText, "tvChangePinOtpText");
        ExtensionsKt.show(tvChangePinOtpText);
        int i3 = R.id.edChangePinRegisteredMobileNo;
        ((TextInputEditText) _$_findCachedViewById(i3)).setTextColor(getColor(uat.psa.mym.mycitroenconnect.R.color.dark_grey));
        ColorStateList valueOf = ColorStateList.valueOf(getColor(uat.psa.mym.mycitroenconnect.R.color.dark_grey));
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(getColor(R.color.dark_grey))");
        ViewCompat.setBackgroundTintList((TextInputEditText) _$_findCachedViewById(i3), valueOf);
        ((TextInputLayout) _$_findCachedViewById(i2)).setPrefixTextColor(valueOf);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetPinError() {
        int i2 = R.id.txtChangePin;
        if (((TextInputLayout) _$_findCachedViewById(i2)).getError() != null) {
            ((TextInputLayout) _$_findCachedViewById(i2)).setError(null);
        }
        int i3 = R.id.edChangePin;
        ((PinView) _$_findCachedViewById(i3)).setLineColor(getColor(uat.psa.mym.mycitroenconnect.R.color.dark_grey));
        ((PinView) _$_findCachedViewById(i3)).setTextColor(getColor(uat.psa.mym.mycitroenconnect.R.color.black));
    }

    private final void setListeners() {
        ((TextInputEditText) _$_findCachedViewById(R.id.edChangePinRegisteredMobileNo)).addTextChangedListener(new TextWatcher() { // from class: com.psa.mym.mycitroenconnect.controller.activities.ChangePinActivity$setListeners$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
                boolean isBlank;
                boolean z = true;
                if (editable == null || editable.length() == 0) {
                    return;
                }
                if (editable != null) {
                    isBlank = StringsKt__StringsJVMKt.isBlank(editable);
                    if (!isBlank) {
                        z = false;
                    }
                }
                if (z) {
                    return;
                }
                if (editable.length() == 10) {
                    ((AppCompatTextView) ChangePinActivity.this._$_findCachedViewById(R.id.tvChangePinOtpText)).setVisibility(0);
                } else {
                    ((AppCompatTextView) ChangePinActivity.this._$_findCachedViewById(R.id.tvChangePinOtpText)).setVisibility(8);
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
            }

            /* JADX WARN: Code restructure failed: missing block: B:12:0x0016, code lost:
                if (r1 != false) goto L15;
             */
            @Override // android.text.TextWatcher
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                boolean isBlank;
                boolean z = false;
                if (charSequence == null || charSequence.length() == 0) {
                    return;
                }
                if (charSequence != null) {
                    isBlank = StringsKt__StringsJVMKt.isBlank(charSequence);
                }
                z = true;
                if (z) {
                    return;
                }
                ChangePinActivity.this.resetMobileNoError();
            }
        });
        ((PinView) _$_findCachedViewById(R.id.edChangePin)).addTextChangedListener(new TextWatcher() { // from class: com.psa.mym.mycitroenconnect.controller.activities.ChangePinActivity$setListeners$2
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
            }

            /* JADX WARN: Code restructure failed: missing block: B:12:0x0016, code lost:
                if (r1 != false) goto L15;
             */
            @Override // android.text.TextWatcher
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                boolean isBlank;
                boolean z = false;
                if (charSequence == null || charSequence.length() == 0) {
                    return;
                }
                if (charSequence != null) {
                    isBlank = StringsKt__StringsJVMKt.isBlank(charSequence);
                }
                z = true;
                if (z) {
                    return;
                }
                ChangePinActivity.this.resetPinError();
            }
        });
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivViewPin)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnCancel)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnNext)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivBack)).setOnClickListener(this);
    }

    private final void setMobileNoError(String str) {
        int i2 = R.id.tilMobileNumber;
        ((TextInputLayout) _$_findCachedViewById(i2)).setError(str);
        AppCompatTextView tvChangePinOtpText = (AppCompatTextView) _$_findCachedViewById(R.id.tvChangePinOtpText);
        Intrinsics.checkNotNullExpressionValue(tvChangePinOtpText, "tvChangePinOtpText");
        ExtensionsKt.hide(tvChangePinOtpText);
        int i3 = R.id.edChangePinRegisteredMobileNo;
        ((TextInputEditText) _$_findCachedViewById(i3)).setTextColor(getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
        ColorStateList valueOf = ColorStateList.valueOf(getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(getColor(R.color.dark_red))");
        ViewCompat.setBackgroundTintList((TextInputEditText) _$_findCachedViewById(i3), valueOf);
        ((TextInputLayout) _$_findCachedViewById(i2)).setPrefixTextColor(valueOf);
    }

    private final void setPinError(String str) {
        ((TextInputLayout) _$_findCachedViewById(R.id.txtChangePin)).setError(str);
        int i2 = R.id.edChangePin;
        ((PinView) _$_findCachedViewById(i2)).setLineColor(getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
        ((PinView) _$_findCachedViewById(i2)).setTextColor(getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00c2 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean validate() {
        boolean z;
        String string;
        String str;
        boolean z2;
        Editable text;
        boolean z3;
        String string2;
        String str2;
        boolean z4;
        boolean isBlank;
        boolean isBlank2;
        int i2 = R.id.edChangePin;
        Editable text2 = ((PinView) _$_findCachedViewById(i2)).getText();
        if (text2 != null) {
            isBlank2 = StringsKt__StringsJVMKt.isBlank(text2);
            if (!isBlank2) {
                z = false;
                if (!z) {
                    Editable text3 = ((PinView) _$_findCachedViewById(i2)).getText();
                    if (!(text3 == null || text3.length() == 0)) {
                        if (Intrinsics.areEqual(SharedPref.Companion.getAppPin(this), String.valueOf(((PinView) _$_findCachedViewById(i2)).getText()))) {
                            z2 = true;
                            int i3 = R.id.edChangePinRegisteredMobileNo;
                            text = ((TextInputEditText) _$_findCachedViewById(i3)).getText();
                            if (text != null) {
                            }
                            z3 = true;
                            if (!z3) {
                            }
                            string2 = getString(uat.psa.mym.mycitroenconnect.R.string.err_empty_mobile_no);
                            str2 = "getString(R.string.err_empty_mobile_no)";
                            Intrinsics.checkNotNullExpressionValue(string2, str2);
                            setMobileNoError(string2);
                            z4 = false;
                            if (z4) {
                            }
                        }
                        string = getString(uat.psa.mym.mycitroenconnect.R.string.err_change_incorrect_pin);
                        str = "getString(R.string.err_change_incorrect_pin)";
                        Intrinsics.checkNotNullExpressionValue(string, str);
                        setPinError(string);
                        z2 = false;
                        int i32 = R.id.edChangePinRegisteredMobileNo;
                        text = ((TextInputEditText) _$_findCachedViewById(i32)).getText();
                        if (text != null) {
                            isBlank = StringsKt__StringsJVMKt.isBlank(text);
                            if (!isBlank) {
                                z3 = false;
                                if (!z3) {
                                    Editable text4 = ((TextInputEditText) _$_findCachedViewById(i32)).getText();
                                    if (!(text4 == null || text4.length() == 0)) {
                                        if (isRegisteredMobileNumber()) {
                                            z4 = true;
                                            if (z4) {
                                            }
                                        }
                                        string2 = getString(uat.psa.mym.mycitroenconnect.R.string.err_change_incorrect_mobile);
                                        str2 = "getString(R.string.err_change_incorrect_mobile)";
                                        Intrinsics.checkNotNullExpressionValue(string2, str2);
                                        setMobileNoError(string2);
                                        z4 = false;
                                        return !z4 && z2;
                                    }
                                }
                                string2 = getString(uat.psa.mym.mycitroenconnect.R.string.err_empty_mobile_no);
                                str2 = "getString(R.string.err_empty_mobile_no)";
                                Intrinsics.checkNotNullExpressionValue(string2, str2);
                                setMobileNoError(string2);
                                z4 = false;
                                if (z4) {
                                }
                            }
                        }
                        z3 = true;
                        if (!z3) {
                        }
                        string2 = getString(uat.psa.mym.mycitroenconnect.R.string.err_empty_mobile_no);
                        str2 = "getString(R.string.err_empty_mobile_no)";
                        Intrinsics.checkNotNullExpressionValue(string2, str2);
                        setMobileNoError(string2);
                        z4 = false;
                        if (z4) {
                        }
                    }
                }
                string = getString(uat.psa.mym.mycitroenconnect.R.string.err_empty_pin);
                str = "getString(R.string.err_empty_pin)";
                Intrinsics.checkNotNullExpressionValue(string, str);
                setPinError(string);
                z2 = false;
                int i322 = R.id.edChangePinRegisteredMobileNo;
                text = ((TextInputEditText) _$_findCachedViewById(i322)).getText();
                if (text != null) {
                }
                z3 = true;
                if (!z3) {
                }
                string2 = getString(uat.psa.mym.mycitroenconnect.R.string.err_empty_mobile_no);
                str2 = "getString(R.string.err_empty_mobile_no)";
                Intrinsics.checkNotNullExpressionValue(string2, str2);
                setMobileNoError(string2);
                z4 = false;
                if (z4) {
                }
            }
        }
        z = true;
        if (!z) {
        }
        string = getString(uat.psa.mym.mycitroenconnect.R.string.err_empty_pin);
        str = "getString(R.string.err_empty_pin)";
        Intrinsics.checkNotNullExpressionValue(string, str);
        setPinError(string);
        z2 = false;
        int i3222 = R.id.edChangePinRegisteredMobileNo;
        text = ((TextInputEditText) _$_findCachedViewById(i3222)).getText();
        if (text != null) {
        }
        z3 = true;
        if (!z3) {
        }
        string2 = getString(uat.psa.mym.mycitroenconnect.R.string.err_empty_mobile_no);
        str2 = "getString(R.string.err_empty_mobile_no)";
        Intrinsics.checkNotNullExpressionValue(string2, str2);
        setMobileNoError(string2);
        z4 = false;
        if (z4) {
        }
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

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        AppCompatImageView appCompatImageView;
        int i2;
        int i3 = R.id.ivViewPin;
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(i3))) {
            int i4 = R.id.edChangePin;
            ((PinView) _$_findCachedViewById(i4)).setPasswordHidden(!((PinView) _$_findCachedViewById(i4)).isPasswordHidden());
            if (((PinView) _$_findCachedViewById(i4)).isPasswordHidden()) {
                appCompatImageView = (AppCompatImageView) _$_findCachedViewById(i3);
                i2 = uat.psa.mym.mycitroenconnect.R.drawable.ic_pin_hide;
            } else {
                appCompatImageView = (AppCompatImageView) _$_findCachedViewById(i3);
                i2 = uat.psa.mym.mycitroenconnect.R.drawable.ic_pin_view;
            }
            appCompatImageView.setImageResource(i2);
            return;
        }
        if (!Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnCancel))) {
            if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnNext))) {
                resetPinError();
                resetMobileNoError();
                if (validate()) {
                    Intent intent = new Intent(this, VerifyOtpActivity.class);
                    intent.putExtra("login", AppConstants.PAGE_MODE_CHANGE_PIN);
                    intent.putExtra(AppConstants.ARG_IS_FROM_MAIN_SCREEN, this.isFromMainScreen);
                    startActivity(intent);
                    return;
                }
                return;
            } else if (!Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivBack))) {
                return;
            }
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(uat.psa.mym.mycitroenconnect.R.layout.activity_change_pin);
        initView();
        setListeners();
        getIntentData();
    }
}
