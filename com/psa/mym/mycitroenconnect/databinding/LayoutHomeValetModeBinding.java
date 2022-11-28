package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class LayoutHomeValetModeBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageView ivCarValet;
    @NonNull
    public final AppCompatImageView ivCarValetLeft;
    @NonNull
    public final AppCompatImageView ivCarValetRight;
    @NonNull
    private final ConstraintLayout rootView;

    private LayoutHomeValetModeBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull AppCompatImageView appCompatImageView3) {
        this.rootView = constraintLayout;
        this.ivCarValet = appCompatImageView;
        this.ivCarValetLeft = appCompatImageView2;
        this.ivCarValetRight = appCompatImageView3;
    }

    @NonNull
    public static LayoutHomeValetModeBinding bind(@NonNull View view) {
        int i2 = R.id.ivCarValet;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivCarValet);
        if (appCompatImageView != null) {
            i2 = R.id.ivCarValetLeft;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivCarValetLeft);
            if (appCompatImageView2 != null) {
                i2 = R.id.ivCarValetRight;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivCarValetRight);
                if (appCompatImageView3 != null) {
                    return new LayoutHomeValetModeBinding((ConstraintLayout) view, appCompatImageView, appCompatImageView2, appCompatImageView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static LayoutHomeValetModeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static LayoutHomeValetModeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_home_valet_mode, viewGroup, false);
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
