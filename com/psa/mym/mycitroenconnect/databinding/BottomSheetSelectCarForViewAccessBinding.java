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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class BottomSheetSelectCarForViewAccessBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnConfirm;
    @NonNull
    public final AppCompatImageView ivClose;
    @NonNull
    public final ConstraintLayout layoutSelectedContact;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final RecyclerView rvCarAccess;
    @NonNull
    public final AppCompatTextView tvName;
    @NonNull
    public final AppCompatTextView tvNumber;
    @NonNull
    public final AppCompatTextView tvTitle;

    private BottomSheetSelectCarForViewAccessBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatImageView appCompatImageView, @NonNull ConstraintLayout constraintLayout, @NonNull RecyclerView recyclerView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3) {
        this.rootView = frameLayout;
        this.btnConfirm = appCompatButton;
        this.ivClose = appCompatImageView;
        this.layoutSelectedContact = constraintLayout;
        this.rvCarAccess = recyclerView;
        this.tvName = appCompatTextView;
        this.tvNumber = appCompatTextView2;
        this.tvTitle = appCompatTextView3;
    }

    @NonNull
    public static BottomSheetSelectCarForViewAccessBinding bind(@NonNull View view) {
        int i2 = R.id.btnConfirm;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnConfirm);
        if (appCompatButton != null) {
            i2 = R.id.ivClose;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivClose);
            if (appCompatImageView != null) {
                i2 = R.id.layoutSelectedContact;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutSelectedContact);
                if (constraintLayout != null) {
                    i2 = R.id.rvCarAccess;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvCarAccess);
                    if (recyclerView != null) {
                        i2 = R.id.tvName;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvName);
                        if (appCompatTextView != null) {
                            i2 = R.id.tvNumber;
                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvNumber);
                            if (appCompatTextView2 != null) {
                                i2 = R.id.tvTitle;
                                AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTitle);
                                if (appCompatTextView3 != null) {
                                    return new BottomSheetSelectCarForViewAccessBinding((FrameLayout) view, appCompatButton, appCompatImageView, constraintLayout, recyclerView, appCompatTextView, appCompatTextView2, appCompatTextView3);
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
    public static BottomSheetSelectCarForViewAccessBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static BottomSheetSelectCarForViewAccessBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.bottom_sheet_select_car_for_view_access, viewGroup, false);
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
