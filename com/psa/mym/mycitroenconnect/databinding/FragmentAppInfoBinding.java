package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentAppInfoBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnUpdateApp;
    @NonNull
    public final ConstraintLayout clUpdate;
    @NonNull
    private final ScrollView rootView;
    @NonNull
    public final AppCompatTextView tvAppInfoDesc;
    @NonNull
    public final AppCompatTextView tvAppUpdateDesc;
    @NonNull
    public final AppCompatTextView tvTitleMyCitroen;
    @NonNull
    public final AppCompatTextView tvVersion;

    private FragmentAppInfoBinding(@NonNull ScrollView scrollView, @NonNull AppCompatButton appCompatButton, @NonNull ConstraintLayout constraintLayout, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4) {
        this.rootView = scrollView;
        this.btnUpdateApp = appCompatButton;
        this.clUpdate = constraintLayout;
        this.tvAppInfoDesc = appCompatTextView;
        this.tvAppUpdateDesc = appCompatTextView2;
        this.tvTitleMyCitroen = appCompatTextView3;
        this.tvVersion = appCompatTextView4;
    }

    @NonNull
    public static FragmentAppInfoBinding bind(@NonNull View view) {
        int i2 = R.id.btnUpdateApp;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnUpdateApp);
        if (appCompatButton != null) {
            i2 = R.id.clUpdate;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.clUpdate);
            if (constraintLayout != null) {
                i2 = R.id.tvAppInfoDesc;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvAppInfoDesc);
                if (appCompatTextView != null) {
                    i2 = R.id.tvAppUpdateDesc;
                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvAppUpdateDesc);
                    if (appCompatTextView2 != null) {
                        i2 = R.id.tvTitleMyCitroen;
                        AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTitleMyCitroen);
                        if (appCompatTextView3 != null) {
                            i2 = R.id.tvVersion;
                            AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvVersion);
                            if (appCompatTextView4 != null) {
                                return new FragmentAppInfoBinding((ScrollView) view, appCompatButton, constraintLayout, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentAppInfoBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentAppInfoBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_app_info, viewGroup, false);
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
