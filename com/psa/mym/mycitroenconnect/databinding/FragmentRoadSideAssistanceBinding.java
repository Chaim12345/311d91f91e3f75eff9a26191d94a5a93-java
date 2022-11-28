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
public final class FragmentRoadSideAssistanceBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnRSACallSupport;
    @NonNull
    public final AppCompatButton btnRSACancel;
    @NonNull
    public final AppCompatImageView ivRSABackImage;
    @NonNull
    public final AppCompatImageView ivRSAClose;
    @NonNull
    public final AppCompatImageView ivRSAFrontImage;
    @NonNull
    public final LinearLayoutCompat layoutRSAButton;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final AppCompatTextView tvRSACustomerCare;
    @NonNull
    public final AppCompatTextView tvRSACustomerCareDesc;
    @NonNull
    public final AppCompatTextView tvRSACustomerCareNo;
    @NonNull
    public final AppCompatTextView tvRSANeedAssistance;
    @NonNull
    public final AppCompatTextView tvRSATitle;

    private FragmentRoadSideAssistanceBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull AppCompatImageView appCompatImageView3, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5) {
        this.rootView = frameLayout;
        this.btnRSACallSupport = appCompatButton;
        this.btnRSACancel = appCompatButton2;
        this.ivRSABackImage = appCompatImageView;
        this.ivRSAClose = appCompatImageView2;
        this.ivRSAFrontImage = appCompatImageView3;
        this.layoutRSAButton = linearLayoutCompat;
        this.tvRSACustomerCare = appCompatTextView;
        this.tvRSACustomerCareDesc = appCompatTextView2;
        this.tvRSACustomerCareNo = appCompatTextView3;
        this.tvRSANeedAssistance = appCompatTextView4;
        this.tvRSATitle = appCompatTextView5;
    }

    @NonNull
    public static FragmentRoadSideAssistanceBinding bind(@NonNull View view) {
        int i2 = R.id.btnRSACallSupport;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnRSACallSupport);
        if (appCompatButton != null) {
            i2 = R.id.btnRSACancel;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnRSACancel);
            if (appCompatButton2 != null) {
                i2 = R.id.ivRSABackImage;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivRSABackImage);
                if (appCompatImageView != null) {
                    i2 = R.id.ivRSAClose;
                    AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivRSAClose);
                    if (appCompatImageView2 != null) {
                        i2 = R.id.ivRSAFrontImage;
                        AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivRSAFrontImage);
                        if (appCompatImageView3 != null) {
                            i2 = R.id.layoutRSAButton;
                            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutRSAButton);
                            if (linearLayoutCompat != null) {
                                i2 = R.id.tvRSACustomerCare;
                                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvRSACustomerCare);
                                if (appCompatTextView != null) {
                                    i2 = R.id.tvRSACustomerCareDesc;
                                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvRSACustomerCareDesc);
                                    if (appCompatTextView2 != null) {
                                        i2 = R.id.tvRSACustomerCareNo;
                                        AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvRSACustomerCareNo);
                                        if (appCompatTextView3 != null) {
                                            i2 = R.id.tvRSANeedAssistance;
                                            AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvRSANeedAssistance);
                                            if (appCompatTextView4 != null) {
                                                i2 = R.id.tvRSATitle;
                                                AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvRSATitle);
                                                if (appCompatTextView5 != null) {
                                                    return new FragmentRoadSideAssistanceBinding((FrameLayout) view, appCompatButton, appCompatButton2, appCompatImageView, appCompatImageView2, appCompatImageView3, linearLayoutCompat, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5);
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
    public static FragmentRoadSideAssistanceBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentRoadSideAssistanceBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_road_side_assistance, viewGroup, false);
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
