package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.rd.PageIndicatorView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityIntroBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnLogin;
    @NonNull
    public final AppCompatButton btnRegister;
    @NonNull
    public final PageIndicatorView pageIndicatorView;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final ViewPager2 viewPager;

    private ActivityIntroBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull PageIndicatorView pageIndicatorView, @NonNull ViewPager2 viewPager2) {
        this.rootView = constraintLayout;
        this.btnLogin = appCompatButton;
        this.btnRegister = appCompatButton2;
        this.pageIndicatorView = pageIndicatorView;
        this.viewPager = viewPager2;
    }

    @NonNull
    public static ActivityIntroBinding bind(@NonNull View view) {
        int i2 = R.id.btn_login;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btn_login);
        if (appCompatButton != null) {
            i2 = R.id.btn_register;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btn_register);
            if (appCompatButton2 != null) {
                i2 = R.id.pageIndicatorView;
                PageIndicatorView pageIndicatorView = (PageIndicatorView) ViewBindings.findChildViewById(view, R.id.pageIndicatorView);
                if (pageIndicatorView != null) {
                    i2 = R.id.viewPager;
                    ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.viewPager);
                    if (viewPager2 != null) {
                        return new ActivityIntroBinding((ConstraintLayout) view, appCompatButton, appCompatButton2, pageIndicatorView, viewPager2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ActivityIntroBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityIntroBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_intro, viewGroup, false);
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
