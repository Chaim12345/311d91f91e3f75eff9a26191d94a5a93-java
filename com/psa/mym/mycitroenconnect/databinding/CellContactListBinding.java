package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class CellContactListBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageView ivCall;
    @NonNull
    public final ConstraintLayout layoutRootView;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView tvContactName;
    @NonNull
    public final AppCompatTextView tvContactNoImage;
    @NonNull
    public final AppCompatTextView tvContactNumber;

    private CellContactListBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatImageView appCompatImageView, @NonNull ConstraintLayout constraintLayout2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3) {
        this.rootView = constraintLayout;
        this.ivCall = appCompatImageView;
        this.layoutRootView = constraintLayout2;
        this.tvContactName = appCompatTextView;
        this.tvContactNoImage = appCompatTextView2;
        this.tvContactNumber = appCompatTextView3;
    }

    @NonNull
    public static CellContactListBinding bind(@NonNull View view) {
        int i2 = R.id.ivCall;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivCall);
        if (appCompatImageView != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            i2 = R.id.tvContactName;
            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvContactName);
            if (appCompatTextView != null) {
                i2 = R.id.tvContactNoImage;
                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvContactNoImage);
                if (appCompatTextView2 != null) {
                    i2 = R.id.tvContactNumber;
                    AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvContactNumber);
                    if (appCompatTextView3 != null) {
                        return new CellContactListBinding(constraintLayout, appCompatImageView, constraintLayout, appCompatTextView, appCompatTextView2, appCompatTextView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static CellContactListBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static CellContactListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.cell_contact_list, viewGroup, false);
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
