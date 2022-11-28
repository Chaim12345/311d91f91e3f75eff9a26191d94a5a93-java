package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textview.MaterialTextView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ItemViewGeoFenceListBinding implements ViewBinding {
    @NonNull
    public final View divider;
    @NonNull
    public final AppCompatImageView ivDelete;
    @NonNull
    public final AppCompatImageView ivEdit;
    @NonNull
    public final LinearLayoutCompat llButtons;
    @NonNull
    public final LinearLayoutCompat llGeoFenceDetails;
    @NonNull
    private final LinearLayoutCompat rootView;
    @NonNull
    public final SwitchMaterial switchFence;
    @NonNull
    public final MaterialTextView tvDate;
    @NonNull
    public final MaterialTextView tvFenceType;
    @NonNull
    public final MaterialTextView tvRadius;
    @NonNull
    public final MaterialTextView tvTime;
    @NonNull
    public final MaterialTextView tvTitle;

    private ItemViewGeoFenceListBinding(@NonNull LinearLayoutCompat linearLayoutCompat, @NonNull View view, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull LinearLayoutCompat linearLayoutCompat2, @NonNull LinearLayoutCompat linearLayoutCompat3, @NonNull SwitchMaterial switchMaterial, @NonNull MaterialTextView materialTextView, @NonNull MaterialTextView materialTextView2, @NonNull MaterialTextView materialTextView3, @NonNull MaterialTextView materialTextView4, @NonNull MaterialTextView materialTextView5) {
        this.rootView = linearLayoutCompat;
        this.divider = view;
        this.ivDelete = appCompatImageView;
        this.ivEdit = appCompatImageView2;
        this.llButtons = linearLayoutCompat2;
        this.llGeoFenceDetails = linearLayoutCompat3;
        this.switchFence = switchMaterial;
        this.tvDate = materialTextView;
        this.tvFenceType = materialTextView2;
        this.tvRadius = materialTextView3;
        this.tvTime = materialTextView4;
        this.tvTitle = materialTextView5;
    }

    @NonNull
    public static ItemViewGeoFenceListBinding bind(@NonNull View view) {
        int i2 = R.id.divider;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
        if (findChildViewById != null) {
            i2 = R.id.ivDelete;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivDelete);
            if (appCompatImageView != null) {
                i2 = R.id.ivEdit;
                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivEdit);
                if (appCompatImageView2 != null) {
                    i2 = R.id.llButtons;
                    LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.llButtons);
                    if (linearLayoutCompat != null) {
                        i2 = R.id.llGeoFenceDetails;
                        LinearLayoutCompat linearLayoutCompat2 = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.llGeoFenceDetails);
                        if (linearLayoutCompat2 != null) {
                            i2 = R.id.switchFence;
                            SwitchMaterial switchMaterial = (SwitchMaterial) ViewBindings.findChildViewById(view, R.id.switchFence);
                            if (switchMaterial != null) {
                                i2 = R.id.tvDate;
                                MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvDate);
                                if (materialTextView != null) {
                                    i2 = R.id.tvFenceType;
                                    MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvFenceType);
                                    if (materialTextView2 != null) {
                                        i2 = R.id.tvRadius;
                                        MaterialTextView materialTextView3 = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvRadius);
                                        if (materialTextView3 != null) {
                                            i2 = R.id.tvTime;
                                            MaterialTextView materialTextView4 = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvTime);
                                            if (materialTextView4 != null) {
                                                i2 = R.id.tvTitle;
                                                MaterialTextView materialTextView5 = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvTitle);
                                                if (materialTextView5 != null) {
                                                    return new ItemViewGeoFenceListBinding((LinearLayoutCompat) view, findChildViewById, appCompatImageView, appCompatImageView2, linearLayoutCompat, linearLayoutCompat2, switchMaterial, materialTextView, materialTextView2, materialTextView3, materialTextView4, materialTextView5);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ItemViewGeoFenceListBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ItemViewGeoFenceListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_view_geo_fence_list, viewGroup, false);
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
