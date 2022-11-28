package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ItemViewRepeatGeoFenceBinding implements ViewBinding {
    @NonNull
    public final MaterialCardView cvRepeat;
    @NonNull
    public final AppCompatImageView ivSelect;
    @NonNull
    private final MaterialCardView rootView;
    @NonNull
    public final AppCompatTextView tvRepeat;

    private ItemViewRepeatGeoFenceBinding(@NonNull MaterialCardView materialCardView, @NonNull MaterialCardView materialCardView2, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatTextView appCompatTextView) {
        this.rootView = materialCardView;
        this.cvRepeat = materialCardView2;
        this.ivSelect = appCompatImageView;
        this.tvRepeat = appCompatTextView;
    }

    @NonNull
    public static ItemViewRepeatGeoFenceBinding bind(@NonNull View view) {
        MaterialCardView materialCardView = (MaterialCardView) view;
        int i2 = R.id.ivSelect;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivSelect);
        if (appCompatImageView != null) {
            i2 = R.id.tvRepeat;
            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvRepeat);
            if (appCompatTextView != null) {
                return new ItemViewRepeatGeoFenceBinding(materialCardView, materialCardView, appCompatImageView, appCompatTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ItemViewRepeatGeoFenceBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ItemViewRepeatGeoFenceBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_view_repeat_geo_fence, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public MaterialCardView getRoot() {
        return this.rootView;
    }
}
