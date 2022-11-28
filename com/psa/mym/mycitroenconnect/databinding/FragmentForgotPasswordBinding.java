package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentForgotPasswordBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnForgotPassword;
    @NonNull
    public final TextInputEditText edMobileNumberFP;
    @NonNull
    public final AppCompatImageView ivLogo;
    @NonNull
    public final LinearLayoutCompat layoutHavingTroubleView;
    @NonNull
    public final LinearLayoutCompat layoutRegisterNow;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final AppCompatTextView tvContactSupport;
    @NonNull
    public final TextView tvEnterMobileNumberFP;
    @NonNull
    public final AppCompatTextView tvForgotPasswordTitle;
    @NonNull
    public final AppCompatTextView tvHavingTrouble;
    @NonNull
    public final AppCompatTextView tvNewUser;
    @NonNull
    public final AppCompatTextView tvRegisterNow;
    @NonNull
    public final TextInputLayout txtLayoutMobileNumberFP;

    private FragmentForgotPasswordBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatButton appCompatButton, @NonNull TextInputEditText textInputEditText, @NonNull AppCompatImageView appCompatImageView, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull LinearLayoutCompat linearLayoutCompat2, @NonNull AppCompatTextView appCompatTextView, @NonNull TextView textView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull TextInputLayout textInputLayout) {
        this.rootView = frameLayout;
        this.btnForgotPassword = appCompatButton;
        this.edMobileNumberFP = textInputEditText;
        this.ivLogo = appCompatImageView;
        this.layoutHavingTroubleView = linearLayoutCompat;
        this.layoutRegisterNow = linearLayoutCompat2;
        this.tvContactSupport = appCompatTextView;
        this.tvEnterMobileNumberFP = textView;
        this.tvForgotPasswordTitle = appCompatTextView2;
        this.tvHavingTrouble = appCompatTextView3;
        this.tvNewUser = appCompatTextView4;
        this.tvRegisterNow = appCompatTextView5;
        this.txtLayoutMobileNumberFP = textInputLayout;
    }

    @NonNull
    public static FragmentForgotPasswordBinding bind(@NonNull View view) {
        int i2 = R.id.btnForgotPassword;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnForgotPassword);
        if (appCompatButton != null) {
            i2 = R.id.edMobileNumberFP;
            TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edMobileNumberFP);
            if (textInputEditText != null) {
                i2 = R.id.ivLogo;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivLogo);
                if (appCompatImageView != null) {
                    i2 = R.id.layoutHavingTroubleView;
                    LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutHavingTroubleView);
                    if (linearLayoutCompat != null) {
                        i2 = R.id.layoutRegisterNow;
                        LinearLayoutCompat linearLayoutCompat2 = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutRegisterNow);
                        if (linearLayoutCompat2 != null) {
                            i2 = R.id.tvContactSupport;
                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvContactSupport);
                            if (appCompatTextView != null) {
                                i2 = R.id.tvEnterMobileNumberFP;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tvEnterMobileNumberFP);
                                if (textView != null) {
                                    i2 = R.id.tvForgotPasswordTitle;
                                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvForgotPasswordTitle);
                                    if (appCompatTextView2 != null) {
                                        i2 = R.id.tvHavingTrouble;
                                        AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvHavingTrouble);
                                        if (appCompatTextView3 != null) {
                                            i2 = R.id.tvNewUser;
                                            AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvNewUser);
                                            if (appCompatTextView4 != null) {
                                                i2 = R.id.tvRegisterNow;
                                                AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvRegisterNow);
                                                if (appCompatTextView5 != null) {
                                                    i2 = R.id.txtLayoutMobileNumberFP;
                                                    TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutMobileNumberFP);
                                                    if (textInputLayout != null) {
                                                        return new FragmentForgotPasswordBinding((FrameLayout) view, appCompatButton, textInputEditText, appCompatImageView, linearLayoutCompat, linearLayoutCompat2, appCompatTextView, textView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, textInputLayout);
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
    public static FragmentForgotPasswordBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentForgotPasswordBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_forgot_password, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public FrameLayout getRoot() {
        return this.rootView;
    }
}
