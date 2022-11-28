package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chaos.view.PinView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentLoginBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnLogin;
    @NonNull
    public final ConstraintLayout clLoginMain;
    @NonNull
    public final ConstraintLayout clQuickAccess;
    @NonNull
    public final TextInputEditText edMobileNumber;
    @NonNull
    public final TextInputEditText edPassword;
    @NonNull
    public final PinView edVerification;
    @NonNull
    public final TextView forgotPin;
    @NonNull
    public final AppCompatImageView ivLogo;
    @NonNull
    public final LinearLayoutCompat layoutHavingTroubleView;
    @NonNull
    public final LinearLayoutCompat layoutRegisterNow;
    @NonNull
    public final RelativeLayout relPin;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView tvContactSupport;
    @NonNull
    public final TextView tvEnterMobileNumber;
    @NonNull
    public final TextView tvEnterPassword;
    @NonNull
    public final AppCompatTextView tvForgotPassword;
    @NonNull
    public final AppCompatTextView tvHavingTrouble;
    @NonNull
    public final TextView tvLoginTitle;
    @NonNull
    public final AppCompatTextView tvNewUser;
    @NonNull
    public final AppCompatTextView tvQuickAccessPin;
    @NonNull
    public final AppCompatTextView tvRegisterNow;
    @NonNull
    public final TextInputLayout txtLayoutMobileNumber;
    @NonNull
    public final TextInputLayout txtLayoutPassword;

    private FragmentLoginBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull ConstraintLayout constraintLayout2, @NonNull ConstraintLayout constraintLayout3, @NonNull TextInputEditText textInputEditText, @NonNull TextInputEditText textInputEditText2, @NonNull PinView pinView, @NonNull TextView textView, @NonNull AppCompatImageView appCompatImageView, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull LinearLayoutCompat linearLayoutCompat2, @NonNull RelativeLayout relativeLayout, @NonNull AppCompatTextView appCompatTextView, @NonNull TextView textView2, @NonNull TextView textView3, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull TextView textView4, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull AppCompatTextView appCompatTextView6, @NonNull TextInputLayout textInputLayout, @NonNull TextInputLayout textInputLayout2) {
        this.rootView = constraintLayout;
        this.btnLogin = appCompatButton;
        this.clLoginMain = constraintLayout2;
        this.clQuickAccess = constraintLayout3;
        this.edMobileNumber = textInputEditText;
        this.edPassword = textInputEditText2;
        this.edVerification = pinView;
        this.forgotPin = textView;
        this.ivLogo = appCompatImageView;
        this.layoutHavingTroubleView = linearLayoutCompat;
        this.layoutRegisterNow = linearLayoutCompat2;
        this.relPin = relativeLayout;
        this.tvContactSupport = appCompatTextView;
        this.tvEnterMobileNumber = textView2;
        this.tvEnterPassword = textView3;
        this.tvForgotPassword = appCompatTextView2;
        this.tvHavingTrouble = appCompatTextView3;
        this.tvLoginTitle = textView4;
        this.tvNewUser = appCompatTextView4;
        this.tvQuickAccessPin = appCompatTextView5;
        this.tvRegisterNow = appCompatTextView6;
        this.txtLayoutMobileNumber = textInputLayout;
        this.txtLayoutPassword = textInputLayout2;
    }

    @NonNull
    public static FragmentLoginBinding bind(@NonNull View view) {
        int i2 = R.id.btnLogin;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnLogin);
        if (appCompatButton != null) {
            i2 = R.id.cl_loginMain;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.cl_loginMain);
            if (constraintLayout != null) {
                i2 = R.id.cl_quickAccess;
                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.cl_quickAccess);
                if (constraintLayout2 != null) {
                    i2 = R.id.edMobileNumber;
                    TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edMobileNumber);
                    if (textInputEditText != null) {
                        i2 = R.id.edPassword;
                        TextInputEditText textInputEditText2 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edPassword);
                        if (textInputEditText2 != null) {
                            i2 = R.id.edVerification;
                            PinView pinView = (PinView) ViewBindings.findChildViewById(view, R.id.edVerification);
                            if (pinView != null) {
                                i2 = R.id.forgotPin;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.forgotPin);
                                if (textView != null) {
                                    i2 = R.id.ivLogo;
                                    AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivLogo);
                                    if (appCompatImageView != null) {
                                        i2 = R.id.layoutHavingTroubleView;
                                        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutHavingTroubleView);
                                        if (linearLayoutCompat != null) {
                                            i2 = R.id.layoutRegisterNow;
                                            LinearLayoutCompat linearLayoutCompat2 = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutRegisterNow);
                                            if (linearLayoutCompat2 != null) {
                                                i2 = R.id.relPin;
                                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.relPin);
                                                if (relativeLayout != null) {
                                                    i2 = R.id.tvContactSupport;
                                                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvContactSupport);
                                                    if (appCompatTextView != null) {
                                                        i2 = R.id.tvEnterMobileNumber;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tvEnterMobileNumber);
                                                        if (textView2 != null) {
                                                            i2 = R.id.tvEnterPassword;
                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tvEnterPassword);
                                                            if (textView3 != null) {
                                                                i2 = R.id.tvForgotPassword;
                                                                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvForgotPassword);
                                                                if (appCompatTextView2 != null) {
                                                                    i2 = R.id.tvHavingTrouble;
                                                                    AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvHavingTrouble);
                                                                    if (appCompatTextView3 != null) {
                                                                        i2 = R.id.tvLoginTitle;
                                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tvLoginTitle);
                                                                        if (textView4 != null) {
                                                                            i2 = R.id.tvNewUser;
                                                                            AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvNewUser);
                                                                            if (appCompatTextView4 != null) {
                                                                                i2 = R.id.tvQuickAccessPin;
                                                                                AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvQuickAccessPin);
                                                                                if (appCompatTextView5 != null) {
                                                                                    i2 = R.id.tvRegisterNow;
                                                                                    AppCompatTextView appCompatTextView6 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvRegisterNow);
                                                                                    if (appCompatTextView6 != null) {
                                                                                        i2 = R.id.txtLayoutMobileNumber;
                                                                                        TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutMobileNumber);
                                                                                        if (textInputLayout != null) {
                                                                                            i2 = R.id.txtLayoutPassword;
                                                                                            TextInputLayout textInputLayout2 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutPassword);
                                                                                            if (textInputLayout2 != null) {
                                                                                                return new FragmentLoginBinding((ConstraintLayout) view, appCompatButton, constraintLayout, constraintLayout2, textInputEditText, textInputEditText2, pinView, textView, appCompatImageView, linearLayoutCompat, linearLayoutCompat2, relativeLayout, appCompatTextView, textView2, textView3, appCompatTextView2, appCompatTextView3, textView4, appCompatTextView4, appCompatTextView5, appCompatTextView6, textInputLayout, textInputLayout2);
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
    public static FragmentLoginBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentLoginBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_login, viewGroup, false);
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
