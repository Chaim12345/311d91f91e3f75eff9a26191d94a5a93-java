package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentAboutCitroenBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageView ivMyCitroenCarImg;
    @NonNull
    private final ScrollView rootView;
    @NonNull
    public final ScrollView scrollViewAboutCitroen;
    @NonNull
    public final AppCompatTextView tvAppInfoDesc;
    @NonNull
    public final AppCompatTextView tvTitleMyCitroen;

    private FragmentAboutCitroenBinding(@NonNull ScrollView scrollView, @NonNull AppCompatImageView appCompatImageView, @NonNull ScrollView scrollView2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = scrollView;
        this.ivMyCitroenCarImg = appCompatImageView;
        this.scrollViewAboutCitroen = scrollView2;
        this.tvAppInfoDesc = appCompatTextView;
        this.tvTitleMyCitroen = appCompatTextView2;
    }

    @NonNull
    public static FragmentAboutCitroenBinding bind(@NonNull View view) {
        int i2 = R.id.ivMyCitroenCarImg;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivMyCitroenCarImg);
        if (appCompatImageView != null) {
            ScrollView scrollView = (ScrollView) view;
            i2 = R.id.tvAppInfoDesc;
            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvAppInfoDesc);
            if (appCompatTextView != null) {
                i2 = R.id.tvTitleMyCitroen;
                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTitleMyCitroen);
                if (appCompatTextView2 != null) {
                    return new FragmentAboutCitroenBinding(scrollView, appCompatImageView, scrollView, appCompatTextView, appCompatTextView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentAboutCitroenBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentAboutCitroenBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_about_citroen, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public ScrollView getRoot() {
        return this.rootView;
    }
}
