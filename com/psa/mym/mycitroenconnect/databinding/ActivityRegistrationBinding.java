package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityRegistrationBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnRegister;
    @NonNull
    public final CheckBox checkboxEmail;
    @NonNull
    public final CheckBox checkboxPhone;
    @NonNull
    public final CheckBox checkboxSms;
    @NonNull
    public final CheckBox checkboxWhatsApp;
    @NonNull
    public final TextInputEditText edEmailId;
    @NonNull
    public final TextInputEditText edFirstName;
    @NonNull
    public final TextInputEditText edLastName;
    @NonNull
    public final TextInputEditText edMobileNumber;
    @NonNull
    public final AppCompatTextView labelAlreadyHvAcc;
    @NonNull
    public final ConstraintLayout layoutNameEmail;
    @NonNull
    public final View regDummyView;
    @NonNull
    public final AppCompatImageView regLogoIcon;
    @NonNull
    public final ScrollView regParentScrollLayout;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView titleRegistration;
    @NonNull
    public final AppCompatTextView tvRegInfo1;
    @NonNull
    public final AppCompatTextView tvRegInfo2;
    @NonNull
    public final AppCompatTextView tvRegInfo3;
    @NonNull
    public final AppCompatTextView tvStayInTouch;
    @NonNull
    public final TextInputLayout txtLayoutEmailId;
    @NonNull
    public final TextInputLayout txtLayoutFirstName;
    @NonNull
    public final TextInputLayout txtLayoutLastName;
    @NonNull
    public final TextInputLayout txtLayoutMobileNumber;

    private ActivityRegistrationBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull CheckBox checkBox, @NonNull CheckBox checkBox2, @NonNull CheckBox checkBox3, @NonNull CheckBox checkBox4, @NonNull TextInputEditText textInputEditText, @NonNull TextInputEditText textInputEditText2, @NonNull TextInputEditText textInputEditText3, @NonNull TextInputEditText textInputEditText4, @NonNull AppCompatTextView appCompatTextView, @NonNull ConstraintLayout constraintLayout2, @NonNull View view, @NonNull AppCompatImageView appCompatImageView, @NonNull ScrollView scrollView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull AppCompatTextView appCompatTextView6, @NonNull TextInputLayout textInputLayout, @NonNull TextInputLayout textInputLayout2, @NonNull TextInputLayout textInputLayout3, @NonNull TextInputLayout textInputLayout4) {
        this.rootView = constraintLayout;
        this.btnRegister = appCompatButton;
        this.checkboxEmail = checkBox;
        this.checkboxPhone = checkBox2;
        this.checkboxSms = checkBox3;
        this.checkboxWhatsApp = checkBox4;
        this.edEmailId = textInputEditText;
        this.edFirstName = textInputEditText2;
        this.edLastName = textInputEditText3;
        this.edMobileNumber = textInputEditText4;
        this.labelAlreadyHvAcc = appCompatTextView;
        this.layoutNameEmail = constraintLayout2;
        this.regDummyView = view;
        this.regLogoIcon = appCompatImageView;
        this.regParentScrollLayout = scrollView;
        this.titleRegistration = appCompatTextView2;
        this.tvRegInfo1 = appCompatTextView3;
        this.tvRegInfo2 = appCompatTextView4;
        this.tvRegInfo3 = appCompatTextView5;
        this.tvStayInTouch = appCompatTextView6;
        this.txtLayoutEmailId = textInputLayout;
        this.txtLayoutFirstName = textInputLayout2;
        this.txtLayoutLastName = textInputLayout3;
        this.txtLayoutMobileNumber = textInputLayout4;
    }

    @NonNull
    public static ActivityRegistrationBinding bind(@NonNull View view) {
        int i2 = R.id.btnRegister;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnRegister);
        if (appCompatButton != null) {
            i2 = R.id.checkboxEmail;
            CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, R.id.checkboxEmail);
            if (checkBox != null) {
                i2 = R.id.checkboxPhone;
                CheckBox checkBox2 = (CheckBox) ViewBindings.findChildViewById(view, R.id.checkboxPhone);
                if (checkBox2 != null) {
                    i2 = R.id.checkboxSms;
                    CheckBox checkBox3 = (CheckBox) ViewBindings.findChildViewById(view, R.id.checkboxSms);
                    if (checkBox3 != null) {
                        i2 = R.id.checkboxWhatsApp;
                        CheckBox checkBox4 = (CheckBox) ViewBindings.findChildViewById(view, R.id.checkboxWhatsApp);
                        if (checkBox4 != null) {
                            i2 = R.id.edEmailId;
                            TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edEmailId);
                            if (textInputEditText != null) {
                                i2 = R.id.edFirstName;
                                TextInputEditText textInputEditText2 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edFirstName);
                                if (textInputEditText2 != null) {
                                    i2 = R.id.edLastName;
                                    TextInputEditText textInputEditText3 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edLastName);
                                    if (textInputEditText3 != null) {
                                        i2 = R.id.edMobileNumber;
                                        TextInputEditText textInputEditText4 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edMobileNumber);
                                        if (textInputEditText4 != null) {
                                            i2 = R.id.labelAlreadyHvAcc;
                                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.labelAlreadyHvAcc);
                                            if (appCompatTextView != null) {
                                                i2 = R.id.layoutNameEmail;
                                                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutNameEmail);
                                                if (constraintLayout != null) {
                                                    i2 = R.id.regDummyView;
                                                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.regDummyView);
                                                    if (findChildViewById != null) {
                                                        i2 = R.id.regLogoIcon;
                                                        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.regLogoIcon);
                                                        if (appCompatImageView != null) {
                                                            i2 = R.id.reg_parent_scroll_layout;
                                                            ScrollView scrollView = (ScrollView) ViewBindings.findChildViewById(view, R.id.reg_parent_scroll_layout);
                                                            if (scrollView != null) {
                                                                i2 = R.id.titleRegistration;
                                                                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.titleRegistration);
                                                                if (appCompatTextView2 != null) {
                                                                    i2 = R.id.tvRegInfo1;
                                                                    AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvRegInfo1);
                                                                    if (appCompatTextView3 != null) {
                                                                        i2 = R.id.tvRegInfo2;
                                                                        AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvRegInfo2);
                                                                        if (appCompatTextView4 != null) {
                                                                            i2 = R.id.tvRegInfo3;
                                                                            AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvRegInfo3);
                                                                            if (appCompatTextView5 != null) {
                                                                                i2 = R.id.tvStayInTouch;
                                                                                AppCompatTextView appCompatTextView6 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvStayInTouch);
                                                                                if (appCompatTextView6 != null) {
                                                                                    i2 = R.id.txtLayoutEmailId;
                                                                                    TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutEmailId);
                                                                                    if (textInputLayout != null) {
                                                                                        i2 = R.id.txtLayoutFirstName;
                                                                                        TextInputLayout textInputLayout2 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutFirstName);
                                                                                        if (textInputLayout2 != null) {
                                                                                            i2 = R.id.txtLayoutLastName;
                                                                                            TextInputLayout textInputLayout3 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutLastName);
                                                                                            if (textInputLayout3 != null) {
                                                                                                i2 = R.id.txtLayoutMobileNumber;
                                                                                                TextInputLayout textInputLayout4 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutMobileNumber);
                                                                                                if (textInputLayout4 != null) {
                                                                                                    return new ActivityRegistrationBinding((ConstraintLayout) view, appCompatButton, checkBox, checkBox2, checkBox3, checkBox4, textInputEditText, textInputEditText2, textInputEditText3, textInputEditText4, appCompatTextView, constraintLayout, findChildViewById, appCompatImageView, scrollView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, textInputLayout, textInputLayout2, textInputLayout3, textInputLayout4);
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ActivityRegistrationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityRegistrationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_registration, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public ConstraintLayout getRoot() {
        return this.rootView;
    }
}
