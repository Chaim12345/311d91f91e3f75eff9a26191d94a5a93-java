package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class CustomBadgeBackgroundBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnNotif;
    @NonNull
    public final RelativeLayout layoutBadge;
    @NonNull
    private final RelativeLayout rootView;
    @NonNull
    public final AppCompatTextView tvBadgeNotifCount;

    private CustomBadgeBackgroundBinding(@NonNull RelativeLayout relativeLayout, @NonNull AppCompatButton appCompatButton, @NonNull RelativeLayout relativeLayout2, @NonNull AppCompatTextView appCompatTextView) {
        this.rootView = relativeLayout;
        this.btnNotif = appCompatButton;
        this.layoutBadge = relativeLayout2;
        this.tvBadgeNotifCount = appCompatTextView;
    }

    @NonNull
    public static CustomBadgeBackgroundBinding bind(@NonNull View view) {
        int i2 = R.id.btnNotif;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnNotif);
        if (appCompatButton != null) {
            i2 = R.id.layoutBadge;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.layoutBadge);
            if (relativeLayout != null) {
                i2 = R.id.tvBadgeNotifCount;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvBadgeNotifCount);
                if (appCompatTextView != null) {
                    return new CustomBadgeBackgroundBinding((RelativeLayout) view, appCompatButton, relativeLayout, appCompatTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static CustomBadgeBackgroundBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static CustomBadgeBackgroundBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.custom_badge_background, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public RelativeLayout getRoot() {
        return this.rootView;
    }
}
