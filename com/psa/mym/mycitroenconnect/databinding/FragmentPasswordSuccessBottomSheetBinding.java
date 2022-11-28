package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentPasswordSuccessBottomSheetBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnLogin;
    @NonNull
    public final AppCompatImageView imgOtpVerified;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final AppCompatTextView tvOtpVerified;

    private FragmentPasswordSuccessBottomSheetBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatTextView appCompatTextView) {
        this.rootView = frameLayout;
        this.btnLogin = appCompatButton;
        this.imgOtpVerified = appCompatImageView;
        this.tvOtpVerified = appCompatTextView;
    }

    @NonNull
    public static FragmentPasswordSuccessBottomSheetBinding bind(@NonNull View view) {
        int i2 = R.id.btnLogin;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnLogin);
        if (appCompatButton != null) {
            i2 = R.id.imgOtpVerified;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.imgOtpVerified);
            if (appCompatImageView != null) {
                i2 = R.id.tvOtpVerified;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOtpVerified);
                if (appCompatTextView != null) {
                    return new FragmentPasswordSuccessBottomSheetBinding((FrameLayout) view, appCompatButton, appCompatImageView, appCompatTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentPasswordSuccessBottomSheetBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentPasswordSuccessBottomSheetBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_password_success_bottom_sheet, viewGroup, false);
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
