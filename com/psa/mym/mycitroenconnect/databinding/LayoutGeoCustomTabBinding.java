package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class LayoutGeoCustomTabBinding implements ViewBinding {
    @NonNull
    private final LinearLayout rootView;
    @NonNull
    public final AppCompatTextView tabTitle;

    private LayoutGeoCustomTabBinding(@NonNull LinearLayout linearLayout, @NonNull AppCompatTextView appCompatTextView) {
        this.rootView = linearLayout;
        this.tabTitle = appCompatTextView;
    }

    @NonNull
    public static LayoutGeoCustomTabBinding bind(@NonNull View view) {
        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tabTitle);
        if (appCompatTextView != null) {
            return new LayoutGeoCustomTabBinding((LinearLayout) view, appCompatTextView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.tabTitle)));
    }

    @NonNull
    public static LayoutGeoCustomTabBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static LayoutGeoCustomTabBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_geo_custom_tab, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public LinearLayout getRoot() {
        return this.rootView;
    }
}
