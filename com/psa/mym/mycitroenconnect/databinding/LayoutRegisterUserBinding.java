package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class LayoutRegisterUserBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnNext;
    @NonNull
    public final TextInputEditText edConfirmPassword;
    @NonNull
    public final TextInputEditText edMobileNumber;
    @NonNull
    public final TextInputEditText edPasswordReg;
    @NonNull
    public final AppCompatImageView ivLogo;
    @NonNull
    public final LinearLayoutCompat layoutHavingTroubleView;
    @NonNull
    public final LinearLayoutCompat layoutLoginNow;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final TextView tvConfirmPassword;
    @NonNull
    public final AppCompatTextView tvContactSupport;
    @NonNull
    public final TextView tvEnterMobileNumber;
    @NonNull
    public final AppCompatTextView tvHavingTrouble;
    @NonNull
    public final AppCompatTextView tvNewUser;
    @NonNull
    public final TextView tvPassword;
    @NonNull
    public final AppCompatTextView tvRegisterNow;
    @NonNull
    public final TextView tvRegisterTitle;
    @NonNull
    public final TextInputLayout txtLayoutConfirmPassword;
    @NonNull
    public final TextInputLayout txtLayoutMobileNumber;
    @NonNull
    public final TextInputLayout txtLayoutPasswordReg;

    private LayoutRegisterUserBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull TextInputEditText textInputEditText, @NonNull TextInputEditText textInputEditText2, @NonNull TextInputEditText textInputEditText3, @NonNull AppCompatImageView appCompatImageView, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull LinearLayoutCompat linearLayoutCompat2, @NonNull TextView textView, @NonNull AppCompatTextView appCompatTextView, @NonNull TextView textView2, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull TextView textView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull TextView textView4, @NonNull TextInputLayout textInputLayout, @NonNull TextInputLayout textInputLayout2, @NonNull TextInputLayout textInputLayout3) {
        this.rootView = constraintLayout;
        this.btnNext = appCompatButton;
        this.edConfirmPassword = textInputEditText;
        this.edMobileNumber = textInputEditText2;
        this.edPasswordReg = textInputEditText3;
        this.ivLogo = appCompatImageView;
        this.layoutHavingTroubleView = linearLayoutCompat;
        this.layoutLoginNow = linearLayoutCompat2;
        this.tvConfirmPassword = textView;
        this.tvContactSupport = appCompatTextView;
        this.tvEnterMobileNumber = textView2;
        this.tvHavingTrouble = appCompatTextView2;
        this.tvNewUser = appCompatTextView3;
        this.tvPassword = textView3;
        this.tvRegisterNow = appCompatTextView4;
        this.tvRegisterTitle = textView4;
        this.txtLayoutConfirmPassword = textInputLayout;
        this.txtLayoutMobileNumber = textInputLayout2;
        this.txtLayoutPasswordReg = textInputLayout3;
    }

    @NonNull
    public static LayoutRegisterUserBinding bind(@NonNull View view) {
        int i2 = R.id.btnNext;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnNext);
        if (appCompatButton != null) {
            i2 = R.id.edConfirmPassword;
            TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edConfirmPassword);
            if (textInputEditText != null) {
                i2 = R.id.edMobileNumber;
                TextInputEditText textInputEditText2 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edMobileNumber);
                if (textInputEditText2 != null) {
                    i2 = R.id.edPasswordReg;
                    TextInputEditText textInputEditText3 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edPasswordReg);
                    if (textInputEditText3 != null) {
                        i2 = R.id.ivLogo;
                        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivLogo);
                        if (appCompatImageView != null) {
                            i2 = R.id.layoutHavingTroubleView;
                            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutHavingTroubleView);
                            if (linearLayoutCompat != null) {
                                i2 = R.id.layoutLoginNow;
                                LinearLayoutCompat linearLayoutCompat2 = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutLoginNow);
                                if (linearLayoutCompat2 != null) {
                                    i2 = R.id.tvConfirmPassword;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tvConfirmPassword);
                                    if (textView != null) {
                                        i2 = R.id.tvContactSupport;
                                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvContactSupport);
                                        if (appCompatTextView != null) {
                                            i2 = R.id.tvEnterMobileNumber;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tvEnterMobileNumber);
                                            if (textView2 != null) {
                                                i2 = R.id.tvHavingTrouble;
                                                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvHavingTrouble);
                                                if (appCompatTextView2 != null) {
                                                    i2 = R.id.tvNewUser;
                                                    AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvNewUser);
                                                    if (appCompatTextView3 != null) {
                                                        i2 = R.id.tvPassword;
                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tvPassword);
                                                        if (textView3 != null) {
                                                            i2 = R.id.tvRegisterNow;
                                                            AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvRegisterNow);
                                                            if (appCompatTextView4 != null) {
                                                                i2 = R.id.tvRegisterTitle;
                                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tvRegisterTitle);
                                                                if (textView4 != null) {
                                                                    i2 = R.id.txtLayoutConfirmPassword;
                                                                    TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutConfirmPassword);
                                                                    if (textInputLayout != null) {
                                                                        i2 = R.id.txtLayoutMobileNumber;
                                                                        TextInputLayout textInputLayout2 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutMobileNumber);
                                                                        if (textInputLayout2 != null) {
                                                                            i2 = R.id.txtLayoutPasswordReg;
                                                                            TextInputLayout textInputLayout3 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutPasswordReg);
                                                                            if (textInputLayout3 != null) {
                                                                                return new LayoutRegisterUserBinding((ConstraintLayout) view, appCompatButton, textInputEditText, textInputEditText2, textInputEditText3, appCompatImageView, linearLayoutCompat, linearLayoutCompat2, textView, appCompatTextView, textView2, appCompatTextView2, appCompatTextView3, textView3, appCompatTextView4, textView4, textInputLayout, textInputLayout2, textInputLayout3);
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
    public static LayoutRegisterUserBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static LayoutRegisterUserBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_register_user, viewGroup, false);
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
