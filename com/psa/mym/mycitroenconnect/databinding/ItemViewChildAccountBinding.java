package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ItemViewChildAccountBinding implements ViewBinding {
    @NonNull
    public final View divider;
    @NonNull
    public final AppCompatImageView ivDelete;
    @NonNull
    public final AppCompatImageView ivEdit;
    @NonNull
    public final LinearLayoutCompat llContactDetails;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final View status;
    @NonNull
    public final AppCompatTextView tvCarAccessCount;
    @NonNull
    public final AppCompatTextView tvName;
    @NonNull
    public final AppCompatTextView tvNumber;
    @NonNull
    public final AppCompatTextView tvStatus;

    private ItemViewChildAccountBinding(@NonNull ConstraintLayout constraintLayout, @NonNull View view, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull View view2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4) {
        this.rootView = constraintLayout;
        this.divider = view;
        this.ivDelete = appCompatImageView;
        this.ivEdit = appCompatImageView2;
        this.llContactDetails = linearLayoutCompat;
        this.status = view2;
        this.tvCarAccessCount = appCompatTextView;
        this.tvName = appCompatTextView2;
        this.tvNumber = appCompatTextView3;
        this.tvStatus = appCompatTextView4;
    }

    @NonNull
    public static ItemViewChildAccountBinding bind(@NonNull View view) {
        int i2 = R.id.divider;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
        if (findChildViewById != null) {
            i2 = R.id.ivDelete;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivDelete);
            if (appCompatImageView != null) {
                i2 = R.id.ivEdit;
                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivEdit);
                if (appCompatImageView2 != null) {
                    i2 = R.id.llContactDetails;
                    LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.llContactDetails);
                    if (linearLayoutCompat != null) {
                        i2 = R.id.status;
                        View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.status);
                        if (findChildViewById2 != null) {
                            i2 = R.id.tvCarAccessCount;
                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCarAccessCount);
                            if (appCompatTextView != null) {
                                i2 = R.id.tvName;
                                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvName);
                                if (appCompatTextView2 != null) {
                                    i2 = R.id.tvNumber;
                                    AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvNumber);
                                    if (appCompatTextView3 != null) {
                                        i2 = R.id.tvStatus;
                                        AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvStatus);
                                        if (appCompatTextView4 != null) {
                                            return new ItemViewChildAccountBinding((ConstraintLayout) view, findChildViewById, appCompatImageView, appCompatImageView2, linearLayoutCompat, findChildViewById2, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4);
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
    public static ItemViewChildAccountBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ItemViewChildAccountBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_view_child_account, viewGroup, false);
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
