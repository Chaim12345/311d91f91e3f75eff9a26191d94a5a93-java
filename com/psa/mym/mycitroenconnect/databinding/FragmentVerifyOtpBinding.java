package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chaos.view.PinView;
import com.google.android.material.textfield.TextInputLayout;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentVerifyOtpBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageButton btnBack;
    @NonNull
    public final AppCompatButton btnVerify;
    @NonNull
    public final PinView edVerification;
    @NonNull
    public final AppCompatImageView ivLogo;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView tvEnterOtp;
    @NonNull
    public final AppCompatTextView tvIncorrectOtpError;
    @NonNull
    public final AppCompatTextView tvOtpCounter;
    @NonNull
    public final AppCompatTextView tvResendOtp;
    @NonNull
    public final AppCompatTextView tvVerifyOtpMsg;
    @NonNull
    public final AppCompatTextView tvVerifyOtpTitle;
    @NonNull
    public final TextInputLayout txtVerification;

    private FragmentVerifyOtpBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatImageButton appCompatImageButton, @NonNull AppCompatButton appCompatButton, @NonNull PinView pinView, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull AppCompatTextView appCompatTextView6, @NonNull TextInputLayout textInputLayout) {
        this.rootView = constraintLayout;
        this.btnBack = appCompatImageButton;
        this.btnVerify = appCompatButton;
        this.edVerification = pinView;
        this.ivLogo = appCompatImageView;
        this.tvEnterOtp = appCompatTextView;
        this.tvIncorrectOtpError = appCompatTextView2;
        this.tvOtpCounter = appCompatTextView3;
        this.tvResendOtp = appCompatTextView4;
        this.tvVerifyOtpMsg = appCompatTextView5;
        this.tvVerifyOtpTitle = appCompatTextView6;
        this.txtVerification = textInputLayout;
    }

    @NonNull
    public static FragmentVerifyOtpBinding bind(@NonNull View view) {
        int i2 = R.id.btnBack;
        AppCompatImageButton appCompatImageButton = (AppCompatImageButton) ViewBindings.findChildViewById(view, R.id.btnBack);
        if (appCompatImageButton != null) {
            i2 = R.id.btnVerify;
            AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnVerify);
            if (appCompatButton != null) {
                i2 = R.id.edVerification;
                PinView pinView = (PinView) ViewBindings.findChildViewById(view, R.id.edVerification);
                if (pinView != null) {
                    i2 = R.id.ivLogo;
                    AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivLogo);
                    if (appCompatImageView != null) {
                        i2 = R.id.tvEnterOtp;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvEnterOtp);
                        if (appCompatTextView != null) {
                            i2 = R.id.tvIncorrectOtpError;
                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvIncorrectOtpError);
                            if (appCompatTextView2 != null) {
                                i2 = R.id.tvOtpCounter;
                                AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOtpCounter);
                                if (appCompatTextView3 != null) {
                                    i2 = R.id.tvResendOtp;
                                    AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvResendOtp);
                                    if (appCompatTextView4 != null) {
                                        i2 = R.id.tvVerifyOtpMsg;
                                        AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvVerifyOtpMsg);
                                        if (appCompatTextView5 != null) {
                                            i2 = R.id.tvVerifyOtpTitle;
                                            AppCompatTextView appCompatTextView6 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvVerifyOtpTitle);
                                            if (appCompatTextView6 != null) {
                                                i2 = R.id.txtVerification;
                                                TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtVerification);
                                                if (textInputLayout != null) {
                                                    return new FragmentVerifyOtpBinding((ConstraintLayout) view, appCompatImageButton, appCompatButton, pinView, appCompatImageView, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, textInputLayout);
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
    public static FragmentVerifyOtpBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentVerifyOtpBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_verify_otp, viewGroup, false);
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
