package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ItemViewCarAccessConfirmBinding implements ViewBinding {
    @NonNull
    public final View divider;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final RecyclerView rvCarAccess;
    @NonNull
    public final AppCompatTextView tvName;
    @NonNull
    public final AppCompatTextView tvNumber;

    private ItemViewCarAccessConfirmBinding(@NonNull ConstraintLayout constraintLayout, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = constraintLayout;
        this.divider = view;
        this.rvCarAccess = recyclerView;
        this.tvName = appCompatTextView;
        this.tvNumber = appCompatTextView2;
    }

    @NonNull
    public static ItemViewCarAccessConfirmBinding bind(@NonNull View view) {
        int i2 = R.id.divider;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
        if (findChildViewById != null) {
            i2 = R.id.rvCarAccess;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvCarAccess);
            if (recyclerView != null) {
                i2 = R.id.tvName;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvName);
                if (appCompatTextView != null) {
                    i2 = R.id.tvNumber;
                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvNumber);
                    if (appCompatTextView2 != null) {
                        return new ItemViewCarAccessConfirmBinding((ConstraintLayout) view, findChildViewById, recyclerView, appCompatTextView, appCompatTextView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ItemViewCarAccessConfirmBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ItemViewCarAccessConfirmBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_view_car_access_confirm, viewGroup, false);
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
