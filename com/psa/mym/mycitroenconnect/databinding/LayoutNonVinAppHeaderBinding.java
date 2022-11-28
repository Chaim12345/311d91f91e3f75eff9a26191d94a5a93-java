package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class LayoutNonVinAppHeaderBinding implements ViewBinding {
    @NonNull
    public final CustomBadgeBackgroundBinding layoutNotifCounter;
    @NonNull
    private final Toolbar rootView;
    @NonNull
    public final TextView tvNonVinTitle;

    private LayoutNonVinAppHeaderBinding(@NonNull Toolbar toolbar, @NonNull CustomBadgeBackgroundBinding customBadgeBackgroundBinding, @NonNull TextView textView) {
        this.rootView = toolbar;
        this.layoutNotifCounter = customBadgeBackgroundBinding;
        this.tvNonVinTitle = textView;
    }

    @NonNull
    public static LayoutNonVinAppHeaderBinding bind(@NonNull View view) {
        int i2 = R.id.layoutNotifCounter;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.layoutNotifCounter);
        if (findChildViewById != null) {
            CustomBadgeBackgroundBinding bind = CustomBadgeBackgroundBinding.bind(findChildViewById);
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tvNonVinTitle);
            if (textView != null) {
                return new LayoutNonVinAppHeaderBinding((Toolbar) view, bind, textView);
            }
            i2 = R.id.tvNonVinTitle;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static LayoutNonVinAppHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static LayoutNonVinAppHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_non_vin_app_header, viewGroup, false);
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
