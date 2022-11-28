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
import com.google.android.material.textfield.TextInputLayout;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class LayoutAppSecurityBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnConfirmAppSecurity;
    @NonNull
    public final PinView edtConfirmQuickPin;
    @NonNull
    public final PinView edtQuickPin;
    @NonNull
    public final AppCompatImageView ivLogo;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView tvAppSecurityMsg;
    @NonNull
    public final AppCompatTextView tvAppSecurityTitle;
    @NonNull
    public final AppCompatTextView tvConfirmQuickPin;
    @NonNull
    public final AppCompatTextView tvEnterOtp;
    @NonNull
    public final TextInputLayout txtConfirmQuickPin;
    @NonNull
    public final TextInputLayout txtQuickPin;

    private LayoutAppSecurityBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull PinView pinView, @NonNull PinView pinView2, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull TextInputLayout textInputLayout, @NonNull TextInputLayout textInputLayout2) {
        this.rootView = constraintLayout;
        this.btnConfirmAppSecurity = appCompatButton;
        this.edtConfirmQuickPin = pinView;
        this.edtQuickPin = pinView2;
        this.ivLogo = appCompatImageView;
        this.tvAppSecurityMsg = appCompatTextView;
        this.tvAppSecurityTitle = appCompatTextView2;
        this.tvConfirmQuickPin = appCompatTextView3;
        this.tvEnterOtp = appCompatTextView4;
        this.txtConfirmQuickPin = textInputLayout;
        this.txtQuickPin = textInputLayout2;
    }

    @NonNull
    public static LayoutAppSecurityBinding bind(@NonNull View view) {
        int i2 = R.id.btnConfirmAppSecurity;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnConfirmAppSecurity);
        if (appCompatButton != null) {
            i2 = R.id.edtConfirmQuickPin;
            PinView pinView = (PinView) ViewBindings.findChildViewById(view, R.id.edtConfirmQuickPin);
            if (pinView != null) {
                i2 = R.id.edtQuickPin;
                PinView pinView2 = (PinView) ViewBindings.findChildViewById(view, R.id.edtQuickPin);
                if (pinView2 != null) {
                    i2 = R.id.ivLogo;
                    AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivLogo);
                    if (appCompatImageView != null) {
                        i2 = R.id.tvAppSecurityMsg;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvAppSecurityMsg);
                        if (appCompatTextView != null) {
                            i2 = R.id.tvAppSecurityTitle;
                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvAppSecurityTitle);
                            if (appCompatTextView2 != null) {
                                i2 = R.id.tvConfirmQuickPin;
                                AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvConfirmQuickPin);
                                if (appCompatTextView3 != null) {
                                    i2 = R.id.tvEnterOtp;
                                    AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvEnterOtp);
                                    if (appCompatTextView4 != null) {
                                        i2 = R.id.txtConfirmQuickPin;
                                        TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtConfirmQuickPin);
                                        if (textInputLayout != null) {
                                            i2 = R.id.txtQuickPin;
                                            TextInputLayout textInputLayout2 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtQuickPin);
                                            if (textInputLayout2 != null) {
                                                return new LayoutAppSecurityBinding((ConstraintLayout) view, appCompatButton, pinView, pinView2, appCompatImageView, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, textInputLayout, textInputLayout2);
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
    public static LayoutAppSecurityBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static LayoutAppSecurityBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_app_security, viewGroup, false);
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
