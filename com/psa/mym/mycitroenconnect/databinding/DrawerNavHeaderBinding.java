package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.psa.mym.mycitroenconnect.views.CustomViewPager;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class DrawerNavHeaderBinding implements ViewBinding {
    @NonNull
    public final Guideline horiGuideLine60;
    @NonNull
    public final AppCompatImageView ivNavClose;
    @NonNull
    public final AppCompatImageView ivNavLeftArrow;
    @NonNull
    public final AppCompatImageView ivNavRightArrow;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView tvCarModelName;
    @NonNull
    public final AppCompatTextView tvNavCarName;
    @NonNull
    public final View view;
    @NonNull
    public final CustomViewPager viewPagerHeader;

    private DrawerNavHeaderBinding(@NonNull ConstraintLayout constraintLayout, @NonNull Guideline guideline, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull AppCompatImageView appCompatImageView3, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull View view, @NonNull CustomViewPager customViewPager) {
        this.rootView = constraintLayout;
        this.horiGuideLine60 = guideline;
        this.ivNavClose = appCompatImageView;
        this.ivNavLeftArrow = appCompatImageView2;
        this.ivNavRightArrow = appCompatImageView3;
        this.tvCarModelName = appCompatTextView;
        this.tvNavCarName = appCompatTextView2;
        this.view = view;
        this.viewPagerHeader = customViewPager;
    }

    @NonNull
    public static DrawerNavHeaderBinding bind(@NonNull View view) {
        int i2 = R.id.horiGuideLine60;
        Guideline guideline = (Guideline) ViewBindings.findChildViewById(view, R.id.horiGuideLine60);
        if (guideline != null) {
            i2 = R.id.ivNavClose;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivNavClose);
            if (appCompatImageView != null) {
                i2 = R.id.ivNavLeftArrow;
                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivNavLeftArrow);
                if (appCompatImageView2 != null) {
                    i2 = R.id.ivNavRightArrow;
                    AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivNavRightArrow);
                    if (appCompatImageView3 != null) {
                        i2 = R.id.tvCarModelName;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCarModelName);
                        if (appCompatTextView != null) {
                            i2 = R.id.tvNavCarName;
                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvNavCarName);
                            if (appCompatTextView2 != null) {
                                i2 = R.id.view;
                                View findChildViewById = ViewBindings.findChildViewById(view, R.id.view);
                                if (findChildViewById != null) {
                                    i2 = R.id.viewPagerHeader;
                                    CustomViewPager customViewPager = (CustomViewPager) ViewBindings.findChildViewById(view, R.id.viewPagerHeader);
                                    if (customViewPager != null) {
                                        return new DrawerNavHeaderBinding((ConstraintLayout) view, guideline, appCompatImageView, appCompatImageView2, appCompatImageView3, appCompatTextView, appCompatTextView2, findChildViewById, customViewPager);
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
    public static DrawerNavHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DrawerNavHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.drawer_nav_header, viewGroup, false);
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
