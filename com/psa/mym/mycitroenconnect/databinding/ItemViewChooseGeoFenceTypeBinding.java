package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ItemViewChooseGeoFenceTypeBinding implements ViewBinding {
    @NonNull
    public final MaterialCardView cvMapSelect;
    @NonNull
    public final AppCompatImageView ivFence;
    @NonNull
    public final AppCompatRadioButton ivSelect;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView tvFenceType;

    private ItemViewChooseGeoFenceTypeBinding(@NonNull ConstraintLayout constraintLayout, @NonNull MaterialCardView materialCardView, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatRadioButton appCompatRadioButton, @NonNull AppCompatTextView appCompatTextView) {
        this.rootView = constraintLayout;
        this.cvMapSelect = materialCardView;
        this.ivFence = appCompatImageView;
        this.ivSelect = appCompatRadioButton;
        this.tvFenceType = appCompatTextView;
    }

    @NonNull
    public static ItemViewChooseGeoFenceTypeBinding bind(@NonNull View view) {
        int i2 = R.id.cvMapSelect;
        MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(view, R.id.cvMapSelect);
        if (materialCardView != null) {
            i2 = R.id.ivFence;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivFence);
            if (appCompatImageView != null) {
                i2 = R.id.ivSelect;
                AppCompatRadioButton appCompatRadioButton = (AppCompatRadioButton) ViewBindings.findChildViewById(view, R.id.ivSelect);
                if (appCompatRadioButton != null) {
                    i2 = R.id.tvFenceType;
                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvFenceType);
                    if (appCompatTextView != null) {
                        return new ItemViewChooseGeoFenceTypeBinding((ConstraintLayout) view, materialCardView, appCompatImageView, appCompatRadioButton, appCompatTextView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ItemViewChooseGeoFenceTypeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ItemViewChooseGeoFenceTypeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_view_choose_geo_fence_type, viewGroup, false);
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
