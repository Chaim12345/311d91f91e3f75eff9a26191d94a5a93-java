package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class LayoutDashboardModeHeaderBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageView ivBack;
    @NonNull
    public final AppCompatImageView ivRefresh;
    @NonNull
    private final Toolbar rootView;
    @NonNull
    public final SwitchCompat switchDashHeader;
    @NonNull
    public final AppCompatTextView tvDbHeaderTitle;
    @NonNull
    public final AppCompatTextView tvDbSubTitle;
    @NonNull
    public final AppCompatTextView tvSkip;

    private LayoutDashboardModeHeaderBinding(@NonNull Toolbar toolbar, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull SwitchCompat switchCompat, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3) {
        this.rootView = toolbar;
        this.ivBack = appCompatImageView;
        this.ivRefresh = appCompatImageView2;
        this.switchDashHeader = switchCompat;
        this.tvDbHeaderTitle = appCompatTextView;
        this.tvDbSubTitle = appCompatTextView2;
        this.tvSkip = appCompatTextView3;
    }

    @NonNull
    public static LayoutDashboardModeHeaderBinding bind(@NonNull View view) {
        int i2 = R.id.ivBack;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivBack);
        if (appCompatImageView != null) {
            i2 = R.id.ivRefresh;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivRefresh);
            if (appCompatImageView2 != null) {
                i2 = R.id.switchDashHeader;
                SwitchCompat switchCompat = (SwitchCompat) ViewBindings.findChildViewById(view, R.id.switchDashHeader);
                if (switchCompat != null) {
                    i2 = R.id.tvDbHeaderTitle;
                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDbHeaderTitle);
                    if (appCompatTextView != null) {
                        i2 = R.id.tvDbSubTitle;
                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDbSubTitle);
                        if (appCompatTextView2 != null) {
                            i2 = R.id.tvSkip;
                            AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSkip);
                            if (appCompatTextView3 != null) {
                                return new LayoutDashboardModeHeaderBinding((Toolbar) view, appCompatImageView, appCompatImageView2, switchCompat, appCompatTextView, appCompatTextView2, appCompatTextView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static LayoutDashboardModeHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static LayoutDashboardModeHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_dashboard_mode_header, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public Toolbar getRoot() {
        return this.rootView;
    }
}
