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
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentExitBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnExitNo;
    @NonNull
    public final AppCompatButton btnExitYes;
    @NonNull
    public final AppCompatImageView ivLogoutClose;
    @NonNull
    public final LinearLayoutCompat layoutExitBtn;
    @NonNull
    public final ConstraintLayout layoutLogout;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final AppCompatTextView tvExit;
    @NonNull
    public final AppCompatTextView tvexitConf;

    private FragmentExitBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull AppCompatImageView appCompatImageView, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull ConstraintLayout constraintLayout, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = frameLayout;
        this.btnExitNo = appCompatButton;
        this.btnExitYes = appCompatButton2;
        this.ivLogoutClose = appCompatImageView;
        this.layoutExitBtn = linearLayoutCompat;
        this.layoutLogout = constraintLayout;
        this.tvExit = appCompatTextView;
        this.tvexitConf = appCompatTextView2;
    }

    @NonNull
    public static FragmentExitBinding bind(@NonNull View view) {
        int i2 = R.id.btnExitNo;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnExitNo);
        if (appCompatButton != null) {
            i2 = R.id.btnExitYes;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnExitYes);
            if (appCompatButton2 != null) {
                i2 = R.id.ivLogoutClose;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivLogoutClose);
                if (appCompatImageView != null) {
                    i2 = R.id.layoutExitBtn;
                    LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutExitBtn);
                    if (linearLayoutCompat != null) {
                        i2 = R.id.layoutLogout;
                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutLogout);
                        if (constraintLayout != null) {
                            i2 = R.id.tvExit;
                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvExit);
                            if (appCompatTextView != null) {
                                i2 = R.id.tvexitConf;
                                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvexitConf);
                                if (appCompatTextView2 != null) {
                                    return new FragmentExitBinding((FrameLayout) view, appCompatButton, appCompatButton2, appCompatImageView, linearLayoutCompat, constraintLayout, appCompatTextView, appCompatTextView2);
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
    public static FragmentExitBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentExitBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_exit, viewGroup, false);
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
