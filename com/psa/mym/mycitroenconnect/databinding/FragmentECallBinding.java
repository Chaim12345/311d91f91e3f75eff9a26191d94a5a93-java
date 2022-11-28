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
public final class FragmentECallBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnCallSupport;
    @NonNull
    public final AppCompatButton btnECallCancel;
    @NonNull
    public final AppCompatImageView ivECallBackImage;
    @NonNull
    public final AppCompatImageView ivECallFrontImage;
    @NonNull
    public final AppCompatImageView ivEcallClose;
    @NonNull
    public final LinearLayoutCompat layoutECallButton;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final AppCompatTextView tvECallCustomerCare;
    @NonNull
    public final AppCompatTextView tvECallCustomerCareDesc;
    @NonNull
    public final AppCompatTextView tvECallCustomerCareNo;
    @NonNull
    public final AppCompatTextView tvECallNeedAssistance;
    @NonNull
    public final AppCompatTextView tvECallTitle;

    private FragmentECallBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull AppCompatImageView appCompatImageView3, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5) {
        this.rootView = frameLayout;
        this.btnCallSupport = appCompatButton;
        this.btnECallCancel = appCompatButton2;
        this.ivECallBackImage = appCompatImageView;
        this.ivECallFrontImage = appCompatImageView2;
        this.ivEcallClose = appCompatImageView3;
        this.layoutECallButton = linearLayoutCompat;
        this.tvECallCustomerCare = appCompatTextView;
        this.tvECallCustomerCareDesc = appCompatTextView2;
        this.tvECallCustomerCareNo = appCompatTextView3;
        this.tvECallNeedAssistance = appCompatTextView4;
        this.tvECallTitle = appCompatTextView5;
    }

    @NonNull
    public static FragmentECallBinding bind(@NonNull View view) {
        int i2 = R.id.btnCallSupport;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnCallSupport);
        if (appCompatButton != null) {
            i2 = R.id.btnECallCancel;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnECallCancel);
            if (appCompatButton2 != null) {
                i2 = R.id.ivECallBackImage;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivECallBackImage);
                if (appCompatImageView != null) {
                    i2 = R.id.ivECallFrontImage;
                    AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivECallFrontImage);
                    if (appCompatImageView2 != null) {
                        i2 = R.id.ivEcallClose;
                        AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivEcallClose);
                        if (appCompatImageView3 != null) {
                            i2 = R.id.layoutECallButton;
                            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutECallButton);
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
                                            i2 = R.id.tvECallNeedAssistance;
                                            AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvECallNeedAssistance);
                                            if (appCompatTextView4 != null) {
                                                i2 = R.id.tvECallTitle;
                                                AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvECallTitle);
                                                if (appCompatTextView5 != null) {
                                                    return new FragmentECallBinding((FrameLayout) view, appCompatButton, appCompatButton2, appCompatImageView, appCompatImageView2, appCompatImageView3, linearLayoutCompat, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5);
                                                }
                                            }
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
    public static FragmentECallBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentECallBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_e_call, viewGroup, false);
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
