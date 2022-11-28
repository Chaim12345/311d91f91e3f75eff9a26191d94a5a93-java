package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class BottomSheetContactSupportBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnCallSupport;
    @NonNull
    public final AppCompatButton btnCancel;
    @NonNull
    public final AppCompatImageView ivClose;
    @NonNull
    public final LinearLayoutCompat layoutButton;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView tvECallCustomerCare;
    @NonNull
    public final AppCompatTextView tvECallCustomerCareDesc;
    @NonNull
    public final AppCompatTextView tvECallCustomerCareNo;
    @NonNull
    public final AppCompatTextView tvTitle;

    private BottomSheetContactSupportBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull AppCompatImageView appCompatImageView, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4) {
        this.rootView = constraintLayout;
        this.btnCallSupport = appCompatButton;
        this.btnCancel = appCompatButton2;
        this.ivClose = appCompatImageView;
        this.layoutButton = linearLayoutCompat;
        this.tvECallCustomerCare = appCompatTextView;
        this.tvECallCustomerCareDesc = appCompatTextView2;
        this.tvECallCustomerCareNo = appCompatTextView3;
        this.tvTitle = appCompatTextView4;
    }

    @NonNull
    public static BottomSheetContactSupportBinding bind(@NonNull View view) {
        int i2 = R.id.btnCallSupport;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnCallSupport);
        if (appCompatButton != null) {
            i2 = R.id.btnCancel;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnCancel);
            if (appCompatButton2 != null) {
                i2 = R.id.ivClose;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivClose);
                if (appCompatImageView != null) {
                    i2 = R.id.layoutButton;
                    LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutButton);
                    if (linearLayoutCompat != null) {
                        i2 = R.id.tvECallCustomerCare;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvECallCustomerCare);
                        if (appCompatTextView != null) {
                            i2 = R.id.tvECallCustomerCareDesc;
                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvECallCustomerCareDesc);
                            if (appCompatTextView2 != null) {
                                i2 = R.id.tvECallCustomerCareNo;
                                AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvECallCustomerCareNo);
                                if (appCompatTextView3 != null) {
                                    i2 = R.id.tvTitle;
                                    AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTitle);
                                    if (appCompatTextView4 != null) {
                                        return new BottomSheetContactSupportBinding((ConstraintLayout) view, appCompatButton, appCompatButton2, appCompatImageView, linearLayoutCompat, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4);
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
    public static BottomSheetContactSupportBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static BottomSheetContactSupportBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.bottom_sheet_contact_support, viewGroup, false);
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
