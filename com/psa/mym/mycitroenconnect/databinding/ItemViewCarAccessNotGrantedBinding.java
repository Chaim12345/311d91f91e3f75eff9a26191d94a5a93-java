package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ItemViewCarAccessNotGrantedBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnGrantAccess;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView tvCangCarName;
    @NonNull
    public final AppCompatTextView tvCangCarNumber;

    private ItemViewCarAccessNotGrantedBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = constraintLayout;
        this.btnGrantAccess = appCompatButton;
        this.tvCangCarName = appCompatTextView;
        this.tvCangCarNumber = appCompatTextView2;
    }

    @NonNull
    public static ItemViewCarAccessNotGrantedBinding bind(@NonNull View view) {
        int i2 = R.id.btnGrantAccess;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnGrantAccess);
        if (appCompatButton != null) {
            i2 = R.id.tvCangCarName;
            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCangCarName);
            if (appCompatTextView != null) {
                i2 = R.id.tvCangCarNumber;
                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCangCarNumber);
                if (appCompatTextView2 != null) {
                    return new ItemViewCarAccessNotGrantedBinding((ConstraintLayout) view, appCompatButton, appCompatTextView, appCompatTextView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ItemViewCarAccessNotGrantedBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ItemViewCarAccessNotGrantedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_view_car_access_not_granted, viewGroup, false);
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
