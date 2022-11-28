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
public final class FragmentLogoutBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnLogoutNo;
    @NonNull
    public final AppCompatButton btnLogoutYes;
    @NonNull
    public final AppCompatImageView ivLogoutClose;
    @NonNull
    public final AppCompatImageView ivLogoutSmiley;
    @NonNull
    public final ConstraintLayout layoutLogout;
    @NonNull
    public final LinearLayoutCompat layoutLogoutBtn;
    @NonNull
    public final ConstraintLayout layoutLogoutSuccess;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final AppCompatTextView tvlogout;
    @NonNull
    public final AppCompatTextView tvlogoutConf;

    private FragmentLogoutBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull ConstraintLayout constraintLayout, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull ConstraintLayout constraintLayout2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = frameLayout;
        this.btnLogoutNo = appCompatButton;
        this.btnLogoutYes = appCompatButton2;
        this.ivLogoutClose = appCompatImageView;
        this.ivLogoutSmiley = appCompatImageView2;
        this.layoutLogout = constraintLayout;
        this.layoutLogoutBtn = linearLayoutCompat;
        this.layoutLogoutSuccess = constraintLayout2;
        this.tvlogout = appCompatTextView;
        this.tvlogoutConf = appCompatTextView2;
    }

    @NonNull
    public static FragmentLogoutBinding bind(@NonNull View view) {
        int i2 = R.id.btnLogoutNo;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnLogoutNo);
        if (appCompatButton != null) {
            i2 = R.id.btnLogoutYes;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnLogoutYes);
            if (appCompatButton2 != null) {
                i2 = R.id.ivLogoutClose;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivLogoutClose);
                if (appCompatImageView != null) {
                    i2 = R.id.ivLogoutSmiley;
                    AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivLogoutSmiley);
                    if (appCompatImageView2 != null) {
                        i2 = R.id.layoutLogout;
                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutLogout);
                        if (constraintLayout != null) {
                            i2 = R.id.layoutLogoutBtn;
                            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutLogoutBtn);
                            if (linearLayoutCompat != null) {
                                i2 = R.id.layoutLogoutSuccess;
                                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutLogoutSuccess);
                                if (constraintLayout2 != null) {
                                    i2 = R.id.tvlogout;
                                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvlogout);
                                    if (appCompatTextView != null) {
                                        i2 = R.id.tvlogoutConf;
                                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvlogoutConf);
                                        if (appCompatTextView2 != null) {
                                            return new FragmentLogoutBinding((FrameLayout) view, appCompatButton, appCompatButton2, appCompatImageView, appCompatImageView2, constraintLayout, linearLayoutCompat, constraintLayout2, appCompatTextView, appCompatTextView2);
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
    public static FragmentLogoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentLogoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_logout, viewGroup, false);
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
