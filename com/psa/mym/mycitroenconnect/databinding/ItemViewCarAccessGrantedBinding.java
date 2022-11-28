package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ItemViewCarAccessGrantedBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnRevokeAccess;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final LinearLayoutCompat statusView;
    @NonNull
    public final AppCompatTextView tvCagCarNumber;
    @NonNull
    public final AppCompatTextView tvCagName;
    @NonNull
    public final AppCompatTextView tvCagStatus;
    @NonNull
    public final View viewStatusDot;

    private ItemViewCarAccessGrantedBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull View view) {
        this.rootView = constraintLayout;
        this.btnRevokeAccess = appCompatButton;
        this.statusView = linearLayoutCompat;
        this.tvCagCarNumber = appCompatTextView;
        this.tvCagName = appCompatTextView2;
        this.tvCagStatus = appCompatTextView3;
        this.viewStatusDot = view;
    }

    @NonNull
    public static ItemViewCarAccessGrantedBinding bind(@NonNull View view) {
        int i2 = R.id.btnRevokeAccess;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnRevokeAccess);
        if (appCompatButton != null) {
            i2 = R.id.statusView;
            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.statusView);
            if (linearLayoutCompat != null) {
                i2 = R.id.tvCagCarNumber;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCagCarNumber);
                if (appCompatTextView != null) {
                    i2 = R.id.tvCagName;
                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCagName);
                    if (appCompatTextView2 != null) {
                        i2 = R.id.tvCagStatus;
                        AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCagStatus);
                        if (appCompatTextView3 != null) {
                            i2 = R.id.viewStatusDot;
                            View findChildViewById = ViewBindings.findChildViewById(view, R.id.viewStatusDot);
                            if (findChildViewById != null) {
                                return new ItemViewCarAccessGrantedBinding((ConstraintLayout) view, appCompatButton, linearLayoutCompat, appCompatTextView, appCompatTextView2, appCompatTextView3, findChildViewById);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ItemViewCarAccessGrantedBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ItemViewCarAccessGrantedBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_view_car_access_granted, viewGroup, false);
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
