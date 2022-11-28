package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ItemViewSetFenceForBinding implements ViewBinding {
    @NonNull
    public final MaterialCardView cvMapSelect;
    @NonNull
    public final View divider;
    @NonNull
    public final AppCompatImageView ivFence;
    @NonNull
    public final AppCompatRadioButton ivSelect;
    @NonNull
    private final LinearLayoutCompat rootView;
    @NonNull
    public final AppCompatTextView tvFenceInfo;
    @NonNull
    public final AppCompatTextView tvFenceType;

    private ItemViewSetFenceForBinding(@NonNull LinearLayoutCompat linearLayoutCompat, @NonNull MaterialCardView materialCardView, @NonNull View view, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatRadioButton appCompatRadioButton, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = linearLayoutCompat;
        this.cvMapSelect = materialCardView;
        this.divider = view;
        this.ivFence = appCompatImageView;
        this.ivSelect = appCompatRadioButton;
        this.tvFenceInfo = appCompatTextView;
        this.tvFenceType = appCompatTextView2;
    }

    @NonNull
    public static ItemViewSetFenceForBinding bind(@NonNull View view) {
        int i2 = R.id.cvMapSelect;
        MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(view, R.id.cvMapSelect);
        if (materialCardView != null) {
            i2 = R.id.divider;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
            if (findChildViewById != null) {
                i2 = R.id.ivFence;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivFence);
                if (appCompatImageView != null) {
                    i2 = R.id.ivSelect;
                    AppCompatRadioButton appCompatRadioButton = (AppCompatRadioButton) ViewBindings.findChildViewById(view, R.id.ivSelect);
                    if (appCompatRadioButton != null) {
                        i2 = R.id.tvFenceInfo;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvFenceInfo);
                        if (appCompatTextView != null) {
                            i2 = R.id.tvFenceType;
                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvFenceType);
                            if (appCompatTextView2 != null) {
                                return new ItemViewSetFenceForBinding((LinearLayoutCompat) view, materialCardView, findChildViewById, appCompatImageView, appCompatRadioButton, appCompatTextView, appCompatTextView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ItemViewSetFenceForBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ItemViewSetFenceForBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_view_set_fence_for, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }
}
