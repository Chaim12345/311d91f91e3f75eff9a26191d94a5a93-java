package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentFullScreenAlertBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageView ivAlertCloseBtn;
    @NonNull
    public final AppCompatImageView ivAlertImage;
    @NonNull
    public final AppCompatImageView ivAlertNavImage;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final AppCompatTextView tvAlertNavTitle;
    @NonNull
    public final AppCompatTextView tvAlertTitle;

    private FragmentFullScreenAlertBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull AppCompatImageView appCompatImageView3, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = frameLayout;
        this.ivAlertCloseBtn = appCompatImageView;
        this.ivAlertImage = appCompatImageView2;
        this.ivAlertNavImage = appCompatImageView3;
        this.tvAlertNavTitle = appCompatTextView;
        this.tvAlertTitle = appCompatTextView2;
    }

    @NonNull
    public static FragmentFullScreenAlertBinding bind(@NonNull View view) {
        int i2 = R.id.ivAlertCloseBtn;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivAlertCloseBtn);
        if (appCompatImageView != null) {
            i2 = R.id.ivAlertImage;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivAlertImage);
            if (appCompatImageView2 != null) {
                i2 = R.id.ivAlertNavImage;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivAlertNavImage);
                if (appCompatImageView3 != null) {
                    i2 = R.id.tvAlertNavTitle;
                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvAlertNavTitle);
                    if (appCompatTextView != null) {
                        i2 = R.id.tvAlertTitle;
                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvAlertTitle);
                        if (appCompatTextView2 != null) {
                            return new FragmentFullScreenAlertBinding((FrameLayout) view, appCompatImageView, appCompatImageView2, appCompatImageView3, appCompatTextView, appCompatTextView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentFullScreenAlertBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentFullScreenAlertBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_full_screen_alert, viewGroup, false);
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
