package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class AppHeaderBinding implements ViewBinding {
    @NonNull
    public final CustomBadgeBackgroundBinding layoutNotifCounter;
    @NonNull
    private final Toolbar rootView;
    @NonNull
    public final AppCompatTextView tvTitle;
    @NonNull
    public final AppCompatImageView viewSos;

    private AppHeaderBinding(@NonNull Toolbar toolbar, @NonNull CustomBadgeBackgroundBinding customBadgeBackgroundBinding, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatImageView appCompatImageView) {
        this.rootView = toolbar;
        this.layoutNotifCounter = customBadgeBackgroundBinding;
        this.tvTitle = appCompatTextView;
        this.viewSos = appCompatImageView;
    }

    @NonNull
    public static AppHeaderBinding bind(@NonNull View view) {
        int i2 = R.id.layoutNotifCounter;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.layoutNotifCounter);
        if (findChildViewById != null) {
            CustomBadgeBackgroundBinding bind = CustomBadgeBackgroundBinding.bind(findChildViewById);
            int i3 = R.id.tvTitle;
            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTitle);
            if (appCompatTextView != null) {
                i3 = R.id.viewSos;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.viewSos);
                if (appCompatImageView != null) {
                    return new AppHeaderBinding((Toolbar) view, bind, appCompatTextView, appCompatImageView);
                }
            }
            i2 = i3;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static AppHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static AppHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.app_header, viewGroup, false);
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
