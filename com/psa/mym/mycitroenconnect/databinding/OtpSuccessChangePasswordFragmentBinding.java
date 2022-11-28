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
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class OtpSuccessChangePasswordFragmentBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnOkay;
    @NonNull
    public final ConstraintLayout clOtpSuccess;
    @NonNull
    public final AppCompatImageView imgOtpVerified;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView tvOtpVerified;

    private OtpSuccessChangePasswordFragmentBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull ConstraintLayout constraintLayout2, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatTextView appCompatTextView) {
        this.rootView = constraintLayout;
        this.btnOkay = appCompatButton;
        this.clOtpSuccess = constraintLayout2;
        this.imgOtpVerified = appCompatImageView;
        this.tvOtpVerified = appCompatTextView;
    }

    @NonNull
    public static OtpSuccessChangePasswordFragmentBinding bind(@NonNull View view) {
        int i2 = R.id.btnOkay;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnOkay);
        if (appCompatButton != null) {
            i2 = R.id.cl_otp_success;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.cl_otp_success);
            if (constraintLayout != null) {
                i2 = R.id.imgOtpVerified;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.imgOtpVerified);
                if (appCompatImageView != null) {
                    i2 = R.id.tvOtpVerified;
                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOtpVerified);
                    if (appCompatTextView != null) {
                        return new OtpSuccessChangePasswordFragmentBinding((ConstraintLayout) view, appCompatButton, constraintLayout, appCompatImageView, appCompatTextView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static OtpSuccessChangePasswordFragmentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static OtpSuccessChangePasswordFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.otp_success_change_password_fragment, viewGroup, false);
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
