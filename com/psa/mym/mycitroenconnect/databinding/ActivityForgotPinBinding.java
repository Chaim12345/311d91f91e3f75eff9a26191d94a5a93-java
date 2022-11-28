package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public final class ActivityForgotPinBinding implements ViewBinding {
    @NonNull
    public final TextInputEditText edForgotPinMobileNumber;
    @NonNull
    public final AppCompatButton forgotPinBtnNext;
    @NonNull
    public final AppCompatImageView forgotPinLogoIcon;
    @NonNull
    public final AppCompatTextView labelForgotPinContactUs;
    @NonNull
    public final AppCompatTextView labelNoWorries;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView titleForgotPin;
    @NonNull
    public final TextInputLayout txtForgotPinMobileNumber;

    private ActivityForgotPinBinding(@NonNull ConstraintLayout constraintLayout, @NonNull TextInputEditText textInputEditText, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull TextInputLayout textInputLayout) {
        this.rootView = constraintLayout;
        this.edForgotPinMobileNumber = textInputEditText;
        this.forgotPinBtnNext = appCompatButton;
        this.forgotPinLogoIcon = appCompatImageView;
        this.labelForgotPinContactUs = appCompatTextView;
        this.labelNoWorries = appCompatTextView2;
        this.titleForgotPin = appCompatTextView3;
        this.txtForgotPinMobileNumber = textInputLayout;
    }

    @NonNull
    public static ActivityForgotPinBinding bind(@NonNull View view) {
        int i2 = R.id.edForgotPinMobileNumber;
        TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edForgotPinMobileNumber);
        if (textInputEditText != null) {
            i2 = R.id.forgotPinBtnNext;
            AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.forgotPinBtnNext);
            if (appCompatButton != null) {
                i2 = R.id.forgotPinLogoIcon;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.forgotPinLogoIcon);
                if (appCompatImageView != null) {
                    i2 = R.id.labelForgotPinContact_us;
                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.labelForgotPinContact_us);
                    if (appCompatTextView != null) {
                        i2 = R.id.labelNoWorries;
                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.labelNoWorries);
                        if (appCompatTextView2 != null) {
                            i2 = R.id.titleForgotPin;
                            AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.titleForgotPin);
                            if (appCompatTextView3 != null) {
                                i2 = R.id.txtForgotPinMobileNumber;
                                TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtForgotPinMobileNumber);
                                if (textInputLayout != null) {
                                    return new ActivityForgotPinBinding((ConstraintLayout) view, textInputEditText, appCompatButton, appCompatImageView, appCompatTextView, appCompatTextView2, appCompatTextView3, textInputLayout);
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
    public static ActivityForgotPinBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityForgotPinBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_forgot_pin, viewGroup, false);
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
