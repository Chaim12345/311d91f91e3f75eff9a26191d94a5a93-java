package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public final class FragmentChildAccountBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnAddChildAcct;
    @NonNull
    public final ConstraintLayout clParent;
    @NonNull
    public final AppCompatImageView ivChildAcct;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final RecyclerView rvChildAccount;
    @NonNull
    public final AppCompatTextView tvChildAccountCount;
    @NonNull
    public final AppCompatTextView tvChildAccountTitle;
    @NonNull
    public final AppCompatTextView tvChildAcctDesc;

    private FragmentChildAccountBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull ConstraintLayout constraintLayout2, @NonNull AppCompatImageView appCompatImageView, @NonNull RecyclerView recyclerView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3) {
        this.rootView = constraintLayout;
        this.btnAddChildAcct = appCompatButton;
        this.clParent = constraintLayout2;
        this.ivChildAcct = appCompatImageView;
        this.rvChildAccount = recyclerView;
        this.tvChildAccountCount = appCompatTextView;
        this.tvChildAccountTitle = appCompatTextView2;
        this.tvChildAcctDesc = appCompatTextView3;
    }

    @NonNull
    public static FragmentChildAccountBinding bind(@NonNull View view) {
        int i2 = R.id.btnAddChildAcct;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnAddChildAcct);
        if (appCompatButton != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            i2 = R.id.ivChildAcct;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivChildAcct);
            if (appCompatImageView != null) {
                i2 = R.id.rvChildAccount;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvChildAccount);
                if (recyclerView != null) {
                    i2 = R.id.tvChildAccountCount;
                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChildAccountCount);
                    if (appCompatTextView != null) {
                        i2 = R.id.tvChildAccountTitle;
                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChildAccountTitle);
                        if (appCompatTextView2 != null) {
                            i2 = R.id.tvChildAcctDesc;
                            AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChildAcctDesc);
                            if (appCompatTextView3 != null) {
                                return new FragmentChildAccountBinding(constraintLayout, appCompatButton, constraintLayout, appCompatImageView, recyclerView, appCompatTextView, appCompatTextView2, appCompatTextView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentChildAccountBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentChildAccountBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_child_account, viewGroup, false);
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
