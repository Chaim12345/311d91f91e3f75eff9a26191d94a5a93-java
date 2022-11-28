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
public final class FragmentRegistrationBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnNext;
    @NonNull
    public final TextInputEditText edMobileNumber;
    @NonNull
    public final AppCompatImageView ivLogo;
    @NonNull
    public final LinearLayoutCompat layoutHavingTroubleView;
    @NonNull
    public final LinearLayoutCompat layoutLoginNow;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView tvContactSupport;
    @NonNull
    public final TextView tvEnterMobileNumber;
    @NonNull
    public final AppCompatTextView tvHavingTrouble;
    @NonNull
    public final AppCompatTextView tvNewUser;
    @NonNull
    public final AppCompatTextView tvRegisterNow;
    @NonNull
    public final TextView tvRegisterTitle;
    @NonNull
    public final TextInputLayout txtLayoutMobileNumber;

    private FragmentRegistrationBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull TextInputEditText textInputEditText, @NonNull AppCompatImageView appCompatImageView, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull LinearLayoutCompat linearLayoutCompat2, @NonNull AppCompatTextView appCompatTextView, @NonNull TextView textView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull TextView textView2, @NonNull TextInputLayout textInputLayout) {
        this.rootView = constraintLayout;
        this.btnNext = appCompatButton;
        this.edMobileNumber = textInputEditText;
        this.ivLogo = appCompatImageView;
        this.layoutHavingTroubleView = linearLayoutCompat;
        this.layoutLoginNow = linearLayoutCompat2;
        this.tvContactSupport = appCompatTextView;
        this.tvEnterMobileNumber = textView;
        this.tvHavingTrouble = appCompatTextView2;
        this.tvNewUser = appCompatTextView3;
        this.tvRegisterNow = appCompatTextView4;
        this.tvRegisterTitle = textView2;
        this.txtLayoutMobileNumber = textInputLayout;
    }

    @NonNull
    public static FragmentRegistrationBinding bind(@NonNull View view) {
        int i2 = R.id.btnNext;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnNext);
        if (appCompatButton != null) {
            i2 = R.id.edMobileNumber;
            TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edMobileNumber);
            if (textInputEditText != null) {
                i2 = R.id.ivLogo;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivLogo);
                if (appCompatImageView != null) {
                    i2 = R.id.layoutHavingTroubleView;
                    LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutHavingTroubleView);
                    if (linearLayoutCompat != null) {
                        i2 = R.id.layoutLoginNow;
                        LinearLayoutCompat linearLayoutCompat2 = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutLoginNow);
                        if (linearLayoutCompat2 != null) {
                            i2 = R.id.tvContactSupport;
                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvContactSupport);
                            if (appCompatTextView != null) {
                                i2 = R.id.tvEnterMobileNumber;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tvEnterMobileNumber);
                                if (textView != null) {
                                    i2 = R.id.tvHavingTrouble;
                                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvHavingTrouble);
                                    if (appCompatTextView2 != null) {
                                        i2 = R.id.tvNewUser;
                                        AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvNewUser);
                                        if (appCompatTextView3 != null) {
                                            i2 = R.id.tvRegisterNow;
                                            AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvRegisterNow);
                                            if (appCompatTextView4 != null) {
                                                i2 = R.id.tvRegisterTitle;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tvRegisterTitle);
                                                if (textView2 != null) {
                                                    i2 = R.id.txtLayoutMobileNumber;
                                                    TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutMobileNumber);
                                                    if (textInputLayout != null) {
                                                        return new FragmentRegistrationBinding((ConstraintLayout) view, appCompatButton, textInputEditText, appCompatImageView, linearLayoutCompat, linearLayoutCompat2, appCompatTextView, textView, appCompatTextView2, appCompatTextView3, appCompatTextView4, textView2, textInputLayout);
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
    public static FragmentRegistrationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentRegistrationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_registration, viewGroup, false);
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
