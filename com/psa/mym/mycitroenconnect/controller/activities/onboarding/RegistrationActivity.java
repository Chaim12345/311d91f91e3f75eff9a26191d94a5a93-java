package com.psa.mym.mycitroenconnect.controller.activities.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CheckBox;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.psa.mym.mycitroenconnect.BusBaseActivity;
import com.psa.mym.mycitroenconnect.api.body.onboarding.CreateUserBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.UpdateProfileBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.TermsConditionActivity;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.Token;
import com.psa.mym.mycitroenconnect.model.ValidationErrorResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.CreateUserResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisterUserResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.UpdateUserProfileResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.UserPreferenceItem;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCar;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCarResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCars;
import com.psa.mym.mycitroenconnect.services.OnBoardingService;
import com.psa.mym.mycitroenconnect.services.SecondaryUserService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.apache.http.message.TokenParser;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class RegistrationActivity extends BusBaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private RegisterUserResponse registerUserResponse = new RegisterUserResponse(null, null, null, null, null, 31, null);
    @NotNull
    private String mobileNumber = "";

    private final void apiPermission() {
        boolean isBlank;
        isBlank = StringsKt__StringsJVMKt.isBlank(this.mobileNumber);
        if (!isBlank) {
            if (this.mobileNumber.length() > 0) {
                SecondaryUserService secondaryUserService = new SecondaryUserService();
                StringBuilder sb = new StringBuilder();
                sb.append("91");
                String str = this.mobileNumber;
                String substring = str.substring(3, str.length());
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                sb.append(substring);
                secondaryUserService.getMyCarList(this, sb.toString(), "Registration");
            }
        }
    }

    private final void createUser() {
        CreateUserBody createUserBody = new CreateUserBody(null, null, 3, null);
        String str = this.mobileNumber;
        String substring = str.substring(3, str.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        createUserBody.setMobileNum(substring);
        createUserBody.setCountryCode("+91");
        AppUtil.Companion.showDialog(this);
        new OnBoardingService().callCreateUserAPI(this, createUserBody);
    }

    private final void initView() {
        int indexOf$default;
        int indexOf$default2;
        boolean contains$default;
        CharSequence text = getResources().getText(R.string.registration_info3);
        Intrinsics.checkNotNullExpressionValue(text, "resources.getText(R.string.registration_info3)");
        int i2 = com.psa.mym.mycitroenconnect.R.id.labelAlreadyHvAcc;
        AppCompatTextView labelAlreadyHvAcc = (AppCompatTextView) _$_findCachedViewById(i2);
        Intrinsics.checkNotNullExpressionValue(labelAlreadyHvAcc, "labelAlreadyHvAcc");
        setSpannableString(labelAlreadyHvAcc, getResources().getText(R.string.already_have_acc).toString(), 9, 0);
        AppCompatTextView tvRegInfo1 = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvRegInfo1);
        Intrinsics.checkNotNullExpressionValue(tvRegInfo1, "tvRegInfo1");
        setSpannableString(tvRegInfo1, getResources().getText(R.string.registration_info1).toString(), 15, 0);
        AppCompatTextView tvRegInfo3 = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvRegInfo3);
        Intrinsics.checkNotNullExpressionValue(tvRegInfo3, "tvRegInfo3");
        String obj = getResources().getText(R.string.registration_info3).toString();
        indexOf$default = StringsKt__StringsKt.indexOf$default(text, "Consent", 0, false, 6, (Object) null);
        indexOf$default2 = StringsKt__StringsKt.indexOf$default(text, ". I have", 0, false, 6, (Object) null);
        setSpannableString(tvRegInfo3, obj, indexOf$default, indexOf$default2);
        int i3 = com.psa.mym.mycitroenconnect.R.id.edMobileNumber;
        ((TextInputEditText) _$_findCachedViewById(i3)).setFocusable(false);
        ((TextInputEditText) _$_findCachedViewById(i3)).setFocusableInTouchMode(false);
        ((TextInputEditText) _$_findCachedViewById(i3)).setEnabled(false);
        ((TextInputEditText) _$_findCachedViewById(i3)).setCursorVisible(false);
        ((TextInputEditText) _$_findCachedViewById(i3)).setKeyListener(null);
        if (getIntent() != null && getIntent().hasExtra(AppConstants.REGUSER)) {
            Parcelable parcelableExtra = getIntent().getParcelableExtra(AppConstants.REGUSER);
            Intrinsics.checkNotNull(parcelableExtra);
            RegisterUserResponse registerUserResponse = (RegisterUserResponse) parcelableExtra;
            this.registerUserResponse = registerUserResponse;
            String mobileNum = registerUserResponse.getMobileNum();
            Intrinsics.checkNotNull(mobileNum);
            this.mobileNumber = mobileNum;
            contains$default = StringsKt__StringsKt.contains$default((CharSequence) mobileNum, (CharSequence) "+91", false, 2, (Object) null);
            if (contains$default) {
                String str = this.mobileNumber;
                String substring = str.substring(3, str.length());
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                ((TextInputEditText) _$_findCachedViewById(i3)).setText(substring);
            } else {
                ((TextInputEditText) _$_findCachedViewById(i3)).setText(this.mobileNumber);
            }
        }
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRegister)).setOnClickListener(this);
        ((AppCompatTextView) _$_findCachedViewById(i2)).setOnClickListener(this);
        TextInputEditText edFirstName = (TextInputEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edFirstName);
        Intrinsics.checkNotNullExpressionValue(edFirstName, "edFirstName");
        TextInputLayout txtLayoutFirstName = (TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutFirstName);
        Intrinsics.checkNotNullExpressionValue(txtLayoutFirstName, "txtLayoutFirstName");
        ExtensionsKt.removeError(edFirstName, txtLayoutFirstName);
        TextInputEditText edLastName = (TextInputEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edLastName);
        Intrinsics.checkNotNullExpressionValue(edLastName, "edLastName");
        TextInputLayout txtLayoutLastName = (TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutLastName);
        Intrinsics.checkNotNullExpressionValue(txtLayoutLastName, "txtLayoutLastName");
        ExtensionsKt.removeError(edLastName, txtLayoutLastName);
        TextInputEditText edMobileNumber = (TextInputEditText) _$_findCachedViewById(i3);
        Intrinsics.checkNotNullExpressionValue(edMobileNumber, "edMobileNumber");
        TextInputLayout txtLayoutMobileNumber = (TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutMobileNumber);
        Intrinsics.checkNotNullExpressionValue(txtLayoutMobileNumber, "txtLayoutMobileNumber");
        ExtensionsKt.removeError(edMobileNumber, txtLayoutMobileNumber);
        TextInputEditText edEmailId = (TextInputEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edEmailId);
        Intrinsics.checkNotNullExpressionValue(edEmailId, "edEmailId");
        TextInputLayout txtLayoutEmailId = (TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutEmailId);
        Intrinsics.checkNotNullExpressionValue(txtLayoutEmailId, "txtLayoutEmailId");
        ExtensionsKt.removeError(edEmailId, txtLayoutEmailId);
    }

    private final void setSpannableString(AppCompatTextView appCompatTextView, String str, int i2, int i3) {
        SpannableString spannableString = new SpannableString(str);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ContextCompat.getColor(this, R.color.primary_color_1));
        if (Intrinsics.areEqual(appCompatTextView, (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvRegInfo3))) {
            spannableString.setSpan(foregroundColorSpan, i2, i3, 33);
        } else {
            spannableString.setSpan(foregroundColorSpan, str.length() - i2, str.length() - i3, 33);
            spannableString.setSpan(new ClickableSpan() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.RegistrationActivity$setSpannableString$clickableSpan$1
                @Override // android.text.style.ClickableSpan
                public void onClick(@NotNull View textView) {
                    Intrinsics.checkNotNullParameter(textView, "textView");
                    Intent intent = new Intent(RegistrationActivity.this, TermsConditionActivity.class);
                    intent.putExtra("isTerms", false);
                    RegistrationActivity.this.startActivity(intent);
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(@NotNull TextPaint ds) {
                    Intrinsics.checkNotNullParameter(ds, "ds");
                    super.updateDrawState(ds);
                    ds.setColor(ContextCompat.getColor(RegistrationActivity.this, R.color.primary_color_1));
                    ds.setUnderlineText(false);
                }
            }, str.length() - i2, str.length() - i3, 33);
            appCompatTextView.setMovementMethod(LinkMovementMethod.getInstance());
            appCompatTextView.setHighlightColor(0);
        }
        appCompatTextView.setText(spannableString);
    }

    private final void setVehicleDetails(MyCar myCar) {
        Token token = myCar.getToken();
        if (token != null) {
            SharedPref.Companion.setPrimaryUserTokenDetails(this, token);
        }
        String vinNum = myCar.getVinNum();
        if (vinNum != null) {
            SharedPref.Companion.setVinNumber(this, vinNum);
        }
        String vehicleType = myCar.getVehicleType();
        if (vehicleType != null) {
            SharedPref.Companion.setVehicleType(this, vehicleType);
        }
        String vehicleRegNo = myCar.getVehicleRegNo();
        if (vehicleRegNo != null) {
            SharedPref.Companion.setVehicleNumber(this, vehicleRegNo);
        }
    }

    private final void updateUser() {
        UpdateProfileBody updateProfileBody = new UpdateProfileBody(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
        StringBuilder sb = new StringBuilder();
        sb.append((Object) ((TextInputEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edFirstName)).getText());
        sb.append(TokenParser.SP);
        sb.append((Object) ((TextInputEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edLastName)).getText());
        updateProfileBody.setFullName(sb.toString());
        updateProfileBody.setEmail(String.valueOf(((TextInputEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edEmailId)).getText()));
        updateProfileBody.setUserName(this.mobileNumber);
        updateProfileBody.setAddress(null);
        updateProfileBody.setBloodGroup(null);
        updateProfileBody.setCity(null);
        updateProfileBody.setDateOfBirth(null);
        updateProfileBody.setGender(null);
        updateProfileBody.setRegisteredVehicle(new ArrayList<>());
        updateProfileBody.setProfilePic(null);
        UserPreferenceItem userPreferenceItem = new UserPreferenceItem(null, null, null, null, 15, null);
        userPreferenceItem.setEmail(String.valueOf(((CheckBox) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.checkboxEmail)).isChecked()));
        userPreferenceItem.setSms(String.valueOf(((CheckBox) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.checkboxSms)).isChecked()));
        userPreferenceItem.setPhone(String.valueOf(((CheckBox) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.checkboxPhone)).isChecked()));
        userPreferenceItem.setWhatsapp(String.valueOf(((CheckBox) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.checkboxWhatsApp)).isChecked()));
        ArrayList<UserPreferenceItem> arrayList = new ArrayList<>();
        arrayList.add(userPreferenceItem);
        updateProfileBody.setUserPreference(arrayList);
        new OnBoardingService().callUpdateUserProfileAPI(this, updateProfileBody);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0110  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean validateDetails() {
        boolean z;
        Editable text;
        boolean z2;
        Editable text2;
        boolean z3;
        Editable text3;
        boolean z4;
        boolean isBlank;
        boolean isBlank2;
        boolean isBlank3;
        boolean isBlank4;
        int i2 = com.psa.mym.mycitroenconnect.R.id.edFirstName;
        Editable text4 = ((TextInputEditText) _$_findCachedViewById(i2)).getText();
        boolean z5 = true;
        boolean z6 = false;
        if (text4 != null) {
            isBlank4 = StringsKt__StringsJVMKt.isBlank(text4);
            if (!isBlank4) {
                z = false;
                if (!z) {
                    Editable text5 = ((TextInputEditText) _$_findCachedViewById(i2)).getText();
                    if (!(text5 == null || text5.length() == 0)) {
                        ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutFirstName)).setError(null);
                        int i3 = com.psa.mym.mycitroenconnect.R.id.edMobileNumber;
                        text = ((TextInputEditText) _$_findCachedViewById(i3)).getText();
                        if (text != null) {
                            isBlank3 = StringsKt__StringsJVMKt.isBlank(text);
                            if (!isBlank3) {
                                z2 = false;
                                if (!z2) {
                                    Editable text6 = ((TextInputEditText) _$_findCachedViewById(i3)).getText();
                                    if (!(text6 == null || text6.length() == 0)) {
                                        ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutMobileNumber)).setError(null);
                                        int i4 = com.psa.mym.mycitroenconnect.R.id.edLastName;
                                        text2 = ((TextInputEditText) _$_findCachedViewById(i4)).getText();
                                        if (text2 != null) {
                                            isBlank2 = StringsKt__StringsJVMKt.isBlank(text2);
                                            if (!isBlank2) {
                                                z3 = false;
                                                if (!z3) {
                                                    Editable text7 = ((TextInputEditText) _$_findCachedViewById(i4)).getText();
                                                    if (!(text7 == null || text7.length() == 0)) {
                                                        ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutLastName)).setError(null);
                                                        int i5 = com.psa.mym.mycitroenconnect.R.id.edEmailId;
                                                        text3 = ((TextInputEditText) _$_findCachedViewById(i5)).getText();
                                                        if (text3 != null) {
                                                            isBlank = StringsKt__StringsJVMKt.isBlank(text3);
                                                            if (!isBlank) {
                                                                z4 = false;
                                                                if (!z4) {
                                                                    Editable text8 = ((TextInputEditText) _$_findCachedViewById(i5)).getText();
                                                                    if (text8 != null && text8.length() != 0) {
                                                                        z5 = false;
                                                                    }
                                                                    if (!z5) {
                                                                        AppUtil.Companion companion = AppUtil.Companion;
                                                                        int i6 = com.psa.mym.mycitroenconnect.R.id.txtLayoutEmailId;
                                                                        String valueOf = String.valueOf(((TextInputEditText) _$_findCachedViewById(i5)).getText());
                                                                        String string = getString(R.string.err_invalid_email);
                                                                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.err_invalid_email)");
                                                                        z6 = companion.isEmailAddress((TextInputLayout) _$_findCachedViewById(i6), valueOf, string);
                                                                        if (z6) {
                                                                            ((TextInputLayout) _$_findCachedViewById(i6)).setError(null);
                                                                        }
                                                                        return z6;
                                                                    }
                                                                }
                                                                ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutEmailId)).setError(getString(R.string.err_empty_email));
                                                                return z6;
                                                            }
                                                        }
                                                        z4 = true;
                                                        if (!z4) {
                                                        }
                                                        ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutEmailId)).setError(getString(R.string.err_empty_email));
                                                        return z6;
                                                    }
                                                }
                                                ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutLastName)).setError(getString(R.string.err_empty_last_name));
                                                int i52 = com.psa.mym.mycitroenconnect.R.id.edEmailId;
                                                text3 = ((TextInputEditText) _$_findCachedViewById(i52)).getText();
                                                if (text3 != null) {
                                                }
                                                z4 = true;
                                                if (!z4) {
                                                }
                                                ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutEmailId)).setError(getString(R.string.err_empty_email));
                                                return z6;
                                            }
                                        }
                                        z3 = true;
                                        if (!z3) {
                                        }
                                        ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutLastName)).setError(getString(R.string.err_empty_last_name));
                                        int i522 = com.psa.mym.mycitroenconnect.R.id.edEmailId;
                                        text3 = ((TextInputEditText) _$_findCachedViewById(i522)).getText();
                                        if (text3 != null) {
                                        }
                                        z4 = true;
                                        if (!z4) {
                                        }
                                        ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutEmailId)).setError(getString(R.string.err_empty_email));
                                        return z6;
                                    }
                                }
                                ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutMobileNumber)).setError(getString(R.string.err_empty_mobile_no));
                                int i42 = com.psa.mym.mycitroenconnect.R.id.edLastName;
                                text2 = ((TextInputEditText) _$_findCachedViewById(i42)).getText();
                                if (text2 != null) {
                                }
                                z3 = true;
                                if (!z3) {
                                }
                                ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutLastName)).setError(getString(R.string.err_empty_last_name));
                                int i5222 = com.psa.mym.mycitroenconnect.R.id.edEmailId;
                                text3 = ((TextInputEditText) _$_findCachedViewById(i5222)).getText();
                                if (text3 != null) {
                                }
                                z4 = true;
                                if (!z4) {
                                }
                                ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutEmailId)).setError(getString(R.string.err_empty_email));
                                return z6;
                            }
                        }
                        z2 = true;
                        if (!z2) {
                        }
                        ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutMobileNumber)).setError(getString(R.string.err_empty_mobile_no));
                        int i422 = com.psa.mym.mycitroenconnect.R.id.edLastName;
                        text2 = ((TextInputEditText) _$_findCachedViewById(i422)).getText();
                        if (text2 != null) {
                        }
                        z3 = true;
                        if (!z3) {
                        }
                        ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutLastName)).setError(getString(R.string.err_empty_last_name));
                        int i52222 = com.psa.mym.mycitroenconnect.R.id.edEmailId;
                        text3 = ((TextInputEditText) _$_findCachedViewById(i52222)).getText();
                        if (text3 != null) {
                        }
                        z4 = true;
                        if (!z4) {
                        }
                        ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutEmailId)).setError(getString(R.string.err_empty_email));
                        return z6;
                    }
                }
                ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutFirstName)).setError(getString(R.string.err_empty_first_name));
                int i32 = com.psa.mym.mycitroenconnect.R.id.edMobileNumber;
                text = ((TextInputEditText) _$_findCachedViewById(i32)).getText();
                if (text != null) {
                }
                z2 = true;
                if (!z2) {
                }
                ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutMobileNumber)).setError(getString(R.string.err_empty_mobile_no));
                int i4222 = com.psa.mym.mycitroenconnect.R.id.edLastName;
                text2 = ((TextInputEditText) _$_findCachedViewById(i4222)).getText();
                if (text2 != null) {
                }
                z3 = true;
                if (!z3) {
                }
                ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutLastName)).setError(getString(R.string.err_empty_last_name));
                int i522222 = com.psa.mym.mycitroenconnect.R.id.edEmailId;
                text3 = ((TextInputEditText) _$_findCachedViewById(i522222)).getText();
                if (text3 != null) {
                }
                z4 = true;
                if (!z4) {
                }
                ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutEmailId)).setError(getString(R.string.err_empty_email));
                return z6;
            }
        }
        z = true;
        if (!z) {
        }
        ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutFirstName)).setError(getString(R.string.err_empty_first_name));
        int i322 = com.psa.mym.mycitroenconnect.R.id.edMobileNumber;
        text = ((TextInputEditText) _$_findCachedViewById(i322)).getText();
        if (text != null) {
        }
        z2 = true;
        if (!z2) {
        }
        ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutMobileNumber)).setError(getString(R.string.err_empty_mobile_no));
        int i42222 = com.psa.mym.mycitroenconnect.R.id.edLastName;
        text2 = ((TextInputEditText) _$_findCachedViewById(i42222)).getText();
        if (text2 != null) {
        }
        z3 = true;
        if (!z3) {
        }
        ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutLastName)).setError(getString(R.string.err_empty_last_name));
        int i5222222 = com.psa.mym.mycitroenconnect.R.id.edEmailId;
        text3 = ((TextInputEditText) _$_findCachedViewById(i5222222)).getText();
        if (text3 != null) {
        }
        z4 = true;
        if (!z4) {
        }
        ((TextInputLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.txtLayoutEmailId)).setError(getString(R.string.err_empty_email));
        return z6;
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
        Logger.INSTANCE.e(String.valueOf(event));
    }

    @Subscribe
    public final void getMessage(@NotNull ValidationErrorResponse validationError) {
        String replace$default;
        Intrinsics.checkNotNullParameter(validationError, "validationError");
        replace$default = StringsKt__StringsJVMKt.replace$default(validationError.getErrorList().get(0), "Unresolved key: ", "", false, 4, (Object) null);
        ExtensionsKt.showToast(this, replace$default);
    }

    @Subscribe
    public final void getMessage(@NotNull CreateUserResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        apiPermission();
    }

    @Subscribe
    public final void getMessage(@NotNull UpdateUserProfileResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        Intent intent = new Intent(this, AppSecurityActivity.class);
        intent.putExtra(AppConstants.REGUSER, this.registerUserResponse);
        startActivity(intent);
        finish();
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00be, code lost:
        if ((kotlin.jvm.internal.Intrinsics.areEqual(r4, "accepted") ? true : kotlin.jvm.internal.Intrinsics.areEqual(r4, com.psa.mym.mycitroenconnect.common.AppConstants.SECONDARY_USER_STATE_VERIFIED)) != false) goto L28;
     */
    @Subscribe
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void getResponse(@NotNull MyCarResponse response) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(response, "response");
        if (Intrinsics.areEqual(response.getScreenName(), "Registration")) {
            MyCars myCars = response.getMyCars();
            if (myCars == null || myCars.isEmpty()) {
                return;
            }
            MyCars myCars2 = response.getMyCars();
            String str3 = null;
            if (myCars2.size() > 0) {
                String userType = myCars2.get(0).getUserType();
                if (userType != null) {
                    str2 = userType.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                } else {
                    str2 = null;
                }
                if (Intrinsics.areEqual(str2, "g")) {
                    Token token = myCars2.get(0).getToken();
                    if (token != null) {
                        SharedPref.Companion.setSecondaryUserTokenDetails(this, token);
                    }
                    myCars2.remove(0);
                }
            }
            SharedPref.Companion.setVinTokenDetails(this, myCars2);
            if (myCars2.size() > 0) {
                MyCar it = myCars2.get(0);
                String userType2 = it.getUserType();
                if (userType2 != null) {
                    str = userType2.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                } else {
                    str = null;
                }
                if (!Intrinsics.areEqual(str, "p")) {
                    if (Intrinsics.areEqual(str, "s")) {
                        String inviteStatus = it.getInviteStatus();
                        if (inviteStatus != null) {
                            str3 = inviteStatus.toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        }
                    }
                    Logger.INSTANCE.e(String.valueOf(it));
                    return;
                }
                Intrinsics.checkNotNullExpressionValue(it, "it");
                setVehicleDetails(it);
            }
        }
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        Intrinsics.checkNotNull(view);
        int id = view.getId();
        if (id == R.id.btnRegister) {
            if (validateDetails()) {
                updateUser();
            }
        } else if (id != R.id.labelAlreadyHvAcc) {
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("login", "login");
            startActivity(intent);
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_registration);
        initView();
    }
}
