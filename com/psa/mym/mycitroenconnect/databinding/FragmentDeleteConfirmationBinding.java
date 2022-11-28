package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentDeleteConfirmationBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnDltCancel;
    @NonNull
    public final AppCompatButton btnDltDelete;
    @NonNull
    public final AppCompatImageView ivDelete;
    @NonNull
    public final LinearLayoutCompat layoutDeleteBtn;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final AppCompatTextView tvDeleteConfDesc;
    @NonNull
    public final AppCompatTextView tvDeleteConfirmation;

    private FragmentDeleteConfirmationBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull AppCompatImageView appCompatImageView, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = frameLayout;
        this.btnDltCancel = appCompatButton;
        this.btnDltDelete = appCompatButton2;
        this.ivDelete = appCompatImageView;
        this.layoutDeleteBtn = linearLayoutCompat;
        this.tvDeleteConfDesc = appCompatTextView;
        this.tvDeleteConfirmation = appCompatTextView2;
    }

    @NonNull
    public static FragmentDeleteConfirmationBinding bind(@NonNull View view) {
        int i2 = R.id.btnDltCancel;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnDltCancel);
        if (appCompatButton != null) {
            i2 = R.id.btnDltDelete;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnDltDelete);
            if (appCompatButton2 != null) {
                i2 = R.id.ivDelete;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivDelete);
                if (appCompatImageView != null) {
                    i2 = R.id.layoutDeleteBtn;
                    LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutDeleteBtn);
                    if (linearLayoutCompat != null) {
                        i2 = R.id.tvDeleteConfDesc;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDeleteConfDesc);
                        if (appCompatTextView != null) {
                            i2 = R.id.tvDeleteConfirmation;
                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDeleteConfirmation);
                            if (appCompatTextView2 != null) {
                                return new FragmentDeleteConfirmationBinding((FrameLayout) view, appCompatButton, appCompatButton2, appCompatImageView, linearLayoutCompat, appCompatTextView, appCompatTextView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentDeleteConfirmationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentDeleteConfirmationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_delete_confirmation, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public FrameLayout getRoot() {
        return this.rootView;
    }
}
