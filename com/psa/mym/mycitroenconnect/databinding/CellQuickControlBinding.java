package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class CellQuickControlBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageButton btnQuickControl;
    @NonNull
    public final LinearLayoutCompat linearLayout;
    @NonNull
    public final ConstraintLayout quickLayoutRootView;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView tvQCTitle;

    private CellQuickControlBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatImageButton appCompatImageButton, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull ConstraintLayout constraintLayout2, @NonNull AppCompatTextView appCompatTextView) {
        this.rootView = constraintLayout;
        this.btnQuickControl = appCompatImageButton;
        this.linearLayout = linearLayoutCompat;
        this.quickLayoutRootView = constraintLayout2;
        this.tvQCTitle = appCompatTextView;
    }

    @NonNull
    public static CellQuickControlBinding bind(@NonNull View view) {
        int i2 = R.id.btnQuickControl;
        AppCompatImageButton appCompatImageButton = (AppCompatImageButton) ViewBindings.findChildViewById(view, R.id.btnQuickControl);
        if (appCompatImageButton != null) {
            i2 = R.id.linearLayout;
            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.linearLayout);
            if (linearLayoutCompat != null) {
                ConstraintLayout constraintLayout = (ConstraintLayout) view;
                i2 = R.id.tvQCTitle;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvQCTitle);
                if (appCompatTextView != null) {
                    return new CellQuickControlBinding(constraintLayout, appCompatImageButton, linearLayoutCompat, constraintLayout, appCompatTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static CellQuickControlBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static CellQuickControlBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.cell_quick_control, viewGroup, false);
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
