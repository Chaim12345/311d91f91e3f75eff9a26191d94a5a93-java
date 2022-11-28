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
import com.chaos.view.PinView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityChangePinBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnCancel;
    @NonNull
    public final AppCompatButton btnNext;
    @NonNull
    public final PinView edChangePin;
    @NonNull
    public final TextInputEditText edChangePinRegisteredMobileNo;
    @NonNull
    public final AppCompatImageView ivViewPin;
    @NonNull
    public final LayoutDashboardModeHeaderBinding layoutChangePinHeader;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final TextInputLayout tilMobileNumber;
    @NonNull
    public final AppCompatTextView tvChangePinOtpText;
    @NonNull
    public final AppCompatTextView tvEnterCurrPin;
    @NonNull
    public final TextInputLayout txtChangePin;

    private ActivityChangePinBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull PinView pinView, @NonNull TextInputEditText textInputEditText, @NonNull AppCompatImageView appCompatImageView, @NonNull LayoutDashboardModeHeaderBinding layoutDashboardModeHeaderBinding, @NonNull TextInputLayout textInputLayout, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull TextInputLayout textInputLayout2) {
        this.rootView = constraintLayout;
        this.btnCancel = appCompatButton;
        this.btnNext = appCompatButton2;
        this.edChangePin = pinView;
        this.edChangePinRegisteredMobileNo = textInputEditText;
        this.ivViewPin = appCompatImageView;
        this.layoutChangePinHeader = layoutDashboardModeHeaderBinding;
        this.tilMobileNumber = textInputLayout;
        this.tvChangePinOtpText = appCompatTextView;
        this.tvEnterCurrPin = appCompatTextView2;
        this.txtChangePin = textInputLayout2;
    }

    @NonNull
    public static ActivityChangePinBinding bind(@NonNull View view) {
        int i2 = R.id.btnCancel;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnCancel);
        if (appCompatButton != null) {
            i2 = R.id.btnNext;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnNext);
            if (appCompatButton2 != null) {
                i2 = R.id.edChangePin;
                PinView pinView = (PinView) ViewBindings.findChildViewById(view, R.id.edChangePin);
                if (pinView != null) {
                    i2 = R.id.edChangePinRegisteredMobileNo;
                    TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edChangePinRegisteredMobileNo);
                    if (textInputEditText != null) {
                        i2 = R.id.ivViewPin;
                        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivViewPin);
                        if (appCompatImageView != null) {
                            i2 = R.id.layoutChangePinHeader;
                            View findChildViewById = ViewBindings.findChildViewById(view, R.id.layoutChangePinHeader);
                            if (findChildViewById != null) {
                                LayoutDashboardModeHeaderBinding bind = LayoutDashboardModeHeaderBinding.bind(findChildViewById);
                                i2 = R.id.tilMobileNumber;
                                TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.tilMobileNumber);
                                if (textInputLayout != null) {
                                    i2 = R.id.tvChangePinOtpText;
                                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChangePinOtpText);
                                    if (appCompatTextView != null) {
                                        i2 = R.id.tvEnterCurrPin;
                                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvEnterCurrPin);
                                        if (appCompatTextView2 != null) {
                                            i2 = R.id.txtChangePin;
                                            TextInputLayout textInputLayout2 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtChangePin);
                                            if (textInputLayout2 != null) {
                                                return new ActivityChangePinBinding((ConstraintLayout) view, appCompatButton, appCompatButton2, pinView, textInputEditText, appCompatImageView, bind, textInputLayout, appCompatTextView, appCompatTextView2, textInputLayout2);
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
    public static ActivityChangePinBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityChangePinBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_change_pin, viewGroup, false);
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
