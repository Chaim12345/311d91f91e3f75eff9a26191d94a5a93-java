package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
public final class DlgLayoutOtpVerifiedSuccessBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnNo;
    @NonNull
    public final AppCompatButton btnOkay;
    @NonNull
    public final AppCompatButton btnYes;
    @NonNull
    public final AppCompatImageView imgOtpVerified;
    @NonNull
    public final AppCompatImageView ivClose;
    @NonNull
    public final LinearLayout linBioLayout;
    @NonNull
    public final LinearLayout linOkay;
    @NonNull
    public final LinearLayoutCompat llFingerPrintEnableSuccessfully;
    @NonNull
    private final LinearLayout rootView;
    @NonNull
    public final AppCompatTextView tvFaceId;
    @NonNull
    public final AppCompatTextView tvFaceIdQuestion;
    @NonNull
    public final AppCompatTextView tvOtpVerified;

    private DlgLayoutOtpVerifiedSuccessBinding(@NonNull LinearLayout linearLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull AppCompatButton appCompatButton3, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull LinearLayout linearLayout2, @NonNull LinearLayout linearLayout3, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3) {
        this.rootView = linearLayout;
        this.btnNo = appCompatButton;
        this.btnOkay = appCompatButton2;
        this.btnYes = appCompatButton3;
        this.imgOtpVerified = appCompatImageView;
        this.ivClose = appCompatImageView2;
        this.linBioLayout = linearLayout2;
        this.linOkay = linearLayout3;
        this.llFingerPrintEnableSuccessfully = linearLayoutCompat;
        this.tvFaceId = appCompatTextView;
        this.tvFaceIdQuestion = appCompatTextView2;
        this.tvOtpVerified = appCompatTextView3;
    }

    @NonNull
    public static DlgLayoutOtpVerifiedSuccessBinding bind(@NonNull View view) {
        int i2 = R.id.btnNo;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnNo);
        if (appCompatButton != null) {
            i2 = R.id.btnOkay;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnOkay);
            if (appCompatButton2 != null) {
                i2 = R.id.btnYes;
                AppCompatButton appCompatButton3 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnYes);
                if (appCompatButton3 != null) {
                    i2 = R.id.imgOtpVerified;
                    AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.imgOtpVerified);
                    if (appCompatImageView != null) {
                        i2 = R.id.ivClose;
                        AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivClose);
                        if (appCompatImageView2 != null) {
                            i2 = R.id.linBioLayout;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linBioLayout);
                            if (linearLayout != null) {
                                i2 = R.id.linOkay;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linOkay);
                                if (linearLayout2 != null) {
                                    i2 = R.id.llFingerPrintEnableSuccessfully;
                                    LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.llFingerPrintEnableSuccessfully);
                                    if (linearLayoutCompat != null) {
                                        i2 = R.id.tvFaceId;
                                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvFaceId);
                                        if (appCompatTextView != null) {
                                            i2 = R.id.tvFaceIdQuestion;
                                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvFaceIdQuestion);
                                            if (appCompatTextView2 != null) {
                                                i2 = R.id.tvOtpVerified;
                                                AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOtpVerified);
                                                if (appCompatTextView3 != null) {
                                                    return new DlgLayoutOtpVerifiedSuccessBinding((LinearLayout) view, appCompatButton, appCompatButton2, appCompatButton3, appCompatImageView, appCompatImageView2, linearLayout, linearLayout2, linearLayoutCompat, appCompatTextView, appCompatTextView2, appCompatTextView3);
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
    public static DlgLayoutOtpVerifiedSuccessBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DlgLayoutOtpVerifiedSuccessBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dlg_layout_otp_verified_success, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public LinearLayout getRoot() {
        return this.rootView;
    }
}
