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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chaos.view.PinView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class BottomSheetDeleteCarConfirmationBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnCancel;
    @NonNull
    public final AppCompatButton btnConfirmDelete;
    @NonNull
    public final AppCompatButton btnDelete;
    @NonNull
    public final ConstraintLayout clAppSecurityPin;
    @NonNull
    public final ConstraintLayout clDeleteCar;
    @NonNull
    public final ConstraintLayout deleteSuccess;
    @NonNull
    public final PinView edVerification;
    @NonNull
    public final AppCompatImageView ivClose;
    @NonNull
    public final AppCompatImageView ivDelete;
    @NonNull
    public final AppCompatImageView ivVerifyOTP;
    @NonNull
    public final LinearLayoutCompat layoutDeleteBtn;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final AppCompatTextView tvDeleteConfDesc;
    @NonNull
    public final AppCompatTextView tvDeleteConfirmation;
    @NonNull
    public final AppCompatTextView tvEnterOtp;
    @NonNull
    public final AppCompatTextView tvOtpVerified;
    @NonNull
    public final AppCompatTextView tvOtpVerifyMsg;
    @NonNull
    public final AppCompatTextView tvOtpVerifyTitle;
    @NonNull
    public final AppCompatTextView tvWrongPin;

    private BottomSheetDeleteCarConfirmationBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull AppCompatButton appCompatButton3, @NonNull ConstraintLayout constraintLayout, @NonNull ConstraintLayout constraintLayout2, @NonNull ConstraintLayout constraintLayout3, @NonNull PinView pinView, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull AppCompatImageView appCompatImageView3, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull AppCompatTextView appCompatTextView6, @NonNull AppCompatTextView appCompatTextView7) {
        this.rootView = frameLayout;
        this.btnCancel = appCompatButton;
        this.btnConfirmDelete = appCompatButton2;
        this.btnDelete = appCompatButton3;
        this.clAppSecurityPin = constraintLayout;
        this.clDeleteCar = constraintLayout2;
        this.deleteSuccess = constraintLayout3;
        this.edVerification = pinView;
        this.ivClose = appCompatImageView;
        this.ivDelete = appCompatImageView2;
        this.ivVerifyOTP = appCompatImageView3;
        this.layoutDeleteBtn = linearLayoutCompat;
        this.tvDeleteConfDesc = appCompatTextView;
        this.tvDeleteConfirmation = appCompatTextView2;
        this.tvEnterOtp = appCompatTextView3;
        this.tvOtpVerified = appCompatTextView4;
        this.tvOtpVerifyMsg = appCompatTextView5;
        this.tvOtpVerifyTitle = appCompatTextView6;
        this.tvWrongPin = appCompatTextView7;
    }

    @NonNull
    public static BottomSheetDeleteCarConfirmationBinding bind(@NonNull View view) {
        int i2 = R.id.btnCancel;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnCancel);
        if (appCompatButton != null) {
            i2 = R.id.btnConfirmDelete;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnConfirmDelete);
            if (appCompatButton2 != null) {
                i2 = R.id.btnDelete;
                AppCompatButton appCompatButton3 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnDelete);
                if (appCompatButton3 != null) {
                    i2 = R.id.clAppSecurityPin;
                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.clAppSecurityPin);
                    if (constraintLayout != null) {
                        i2 = R.id.clDeleteCar;
                        ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.clDeleteCar);
                        if (constraintLayout2 != null) {
                            i2 = R.id.deleteSuccess;
                            ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.deleteSuccess);
                            if (constraintLayout3 != null) {
                                i2 = R.id.edVerification;
                                PinView pinView = (PinView) ViewBindings.findChildViewById(view, R.id.edVerification);
                                if (pinView != null) {
                                    i2 = R.id.ivClose;
                                    AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivClose);
                                    if (appCompatImageView != null) {
                                        i2 = R.id.ivDelete;
                                        AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivDelete);
                                        if (appCompatImageView2 != null) {
                                            i2 = R.id.ivVerifyOTP;
                                            AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivVerifyOTP);
                                            if (appCompatImageView3 != null) {
                                                i2 = R.id.layoutDeleteBtn;
                                                LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutDeleteBtn);
                                                if (linearLayoutCompat != null) {
                                                    i2 = R.id.tvDeleteConfDesc;
                                                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDeleteConfDesc);
                                                    if (appCompatTextView != null) {
                                                        i2 = R.id.tvDeleteConfirmation;
                                                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDeleteConfirmation);
                                                        if (appCompatTextView2 != null) {
                                                            i2 = R.id.tvEnterOtp;
                                                            AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvEnterOtp);
                                                            if (appCompatTextView3 != null) {
                                                                i2 = R.id.tvOtpVerified;
                                                                AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOtpVerified);
                                                                if (appCompatTextView4 != null) {
                                                                    i2 = R.id.tvOtpVerifyMsg;
                                                                    AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOtpVerifyMsg);
                                                                    if (appCompatTextView5 != null) {
                                                                        i2 = R.id.tvOtpVerifyTitle;
                                                                        AppCompatTextView appCompatTextView6 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOtpVerifyTitle);
                                                                        if (appCompatTextView6 != null) {
                                                                            i2 = R.id.tvWrongPin;
                                                                            AppCompatTextView appCompatTextView7 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvWrongPin);
                                                                            if (appCompatTextView7 != null) {
                                                                                return new BottomSheetDeleteCarConfirmationBinding((FrameLayout) view, appCompatButton, appCompatButton2, appCompatButton3, constraintLayout, constraintLayout2, constraintLayout3, pinView, appCompatImageView, appCompatImageView2, appCompatImageView3, linearLayoutCompat, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7);
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
    public static BottomSheetDeleteCarConfirmationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static BottomSheetDeleteCarConfirmationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.bottom_sheet_delete_car_confirmation, viewGroup, false);
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
