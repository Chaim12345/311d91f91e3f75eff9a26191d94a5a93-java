package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chaos.view.PinView;
import com.google.android.material.textfield.TextInputLayout;
import com.rd.PageIndicatorView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class BottomSheetChildAccountInvitationBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageButton btnBack;
    @NonNull
    public final AppCompatButton btnCancel;
    @NonNull
    public final AppCompatButton btnOtpVerify;
    @NonNull
    public final AppCompatButton btnVerify;
    @NonNull
    public final RelativeLayout clChildAccountInvitation;
    @NonNull
    public final ConstraintLayout clOtpVerify;
    @NonNull
    public final PinView edVerification;
    @NonNull
    public final AppCompatImageView ivClose;
    @NonNull
    public final AppCompatImageView ivVerifyOTP;
    @NonNull
    public final LinearLayoutCompat llButtons;
    @NonNull
    public final ConstraintLayout otpVerifySuccess;
    @NonNull
    public final PageIndicatorView pageIndicatorViewCAI;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final RecyclerView rvChildAcctInvitation;
    @NonNull
    public final NestedScrollView svDetails;
    @NonNull
    public final AppCompatTextView tvEnterOtp;
    @NonNull
    public final AppCompatTextView tvIncorrectOtpError;
    @NonNull
    public final AppCompatTextView tvMessage;
    @NonNull
    public final AppCompatTextView tvOtpCounter;
    @NonNull
    public final AppCompatTextView tvOtpSendMessage;
    @NonNull
    public final AppCompatTextView tvOtpVerified;
    @NonNull
    public final AppCompatTextView tvOtpVerifyMsg;
    @NonNull
    public final AppCompatTextView tvOtpVerifyTitle;
    @NonNull
    public final AppCompatTextView tvResendOtp;
    @NonNull
    public final AppCompatTextView tvTitle;
    @NonNull
    public final TextInputLayout txtVerification;
    @NonNull
    public final View viewHorizontalLine;

    private BottomSheetChildAccountInvitationBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatImageButton appCompatImageButton, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull AppCompatButton appCompatButton3, @NonNull RelativeLayout relativeLayout, @NonNull ConstraintLayout constraintLayout, @NonNull PinView pinView, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull ConstraintLayout constraintLayout2, @NonNull PageIndicatorView pageIndicatorView, @NonNull RecyclerView recyclerView, @NonNull NestedScrollView nestedScrollView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull AppCompatTextView appCompatTextView6, @NonNull AppCompatTextView appCompatTextView7, @NonNull AppCompatTextView appCompatTextView8, @NonNull AppCompatTextView appCompatTextView9, @NonNull AppCompatTextView appCompatTextView10, @NonNull TextInputLayout textInputLayout, @NonNull View view) {
        this.rootView = frameLayout;
        this.btnBack = appCompatImageButton;
        this.btnCancel = appCompatButton;
        this.btnOtpVerify = appCompatButton2;
        this.btnVerify = appCompatButton3;
        this.clChildAccountInvitation = relativeLayout;
        this.clOtpVerify = constraintLayout;
        this.edVerification = pinView;
        this.ivClose = appCompatImageView;
        this.ivVerifyOTP = appCompatImageView2;
        this.llButtons = linearLayoutCompat;
        this.otpVerifySuccess = constraintLayout2;
        this.pageIndicatorViewCAI = pageIndicatorView;
        this.rvChildAcctInvitation = recyclerView;
        this.svDetails = nestedScrollView;
        this.tvEnterOtp = appCompatTextView;
        this.tvIncorrectOtpError = appCompatTextView2;
        this.tvMessage = appCompatTextView3;
        this.tvOtpCounter = appCompatTextView4;
        this.tvOtpSendMessage = appCompatTextView5;
        this.tvOtpVerified = appCompatTextView6;
        this.tvOtpVerifyMsg = appCompatTextView7;
        this.tvOtpVerifyTitle = appCompatTextView8;
        this.tvResendOtp = appCompatTextView9;
        this.tvTitle = appCompatTextView10;
        this.txtVerification = textInputLayout;
        this.viewHorizontalLine = view;
    }

    @NonNull
    public static BottomSheetChildAccountInvitationBinding bind(@NonNull View view) {
        int i2 = R.id.btnBack;
        AppCompatImageButton appCompatImageButton = (AppCompatImageButton) ViewBindings.findChildViewById(view, R.id.btnBack);
        if (appCompatImageButton != null) {
            i2 = R.id.btnCancel;
            AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnCancel);
            if (appCompatButton != null) {
                i2 = R.id.btnOtpVerify;
                AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnOtpVerify);
                if (appCompatButton2 != null) {
                    i2 = R.id.btnVerify;
                    AppCompatButton appCompatButton3 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnVerify);
                    if (appCompatButton3 != null) {
                        i2 = R.id.clChildAccountInvitation;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.clChildAccountInvitation);
                        if (relativeLayout != null) {
                            i2 = R.id.clOtpVerify;
                            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.clOtpVerify);
                            if (constraintLayout != null) {
                                i2 = R.id.edVerification;
                                PinView pinView = (PinView) ViewBindings.findChildViewById(view, R.id.edVerification);
                                if (pinView != null) {
                                    i2 = R.id.ivClose;
                                    AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivClose);
                                    if (appCompatImageView != null) {
                                        i2 = R.id.ivVerifyOTP;
                                        AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivVerifyOTP);
                                        if (appCompatImageView2 != null) {
                                            i2 = R.id.llButtons;
                                            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.llButtons);
                                            if (linearLayoutCompat != null) {
                                                i2 = R.id.otpVerifySuccess;
                                                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.otpVerifySuccess);
                                                if (constraintLayout2 != null) {
                                                    i2 = R.id.pageIndicatorViewCAI;
                                                    PageIndicatorView pageIndicatorView = (PageIndicatorView) ViewBindings.findChildViewById(view, R.id.pageIndicatorViewCAI);
                                                    if (pageIndicatorView != null) {
                                                        i2 = R.id.rvChildAcctInvitation;
                                                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvChildAcctInvitation);
                                                        if (recyclerView != null) {
                                                            i2 = R.id.svDetails;
                                                            NestedScrollView nestedScrollView = (NestedScrollView) ViewBindings.findChildViewById(view, R.id.svDetails);
                                                            if (nestedScrollView != null) {
                                                                i2 = R.id.tvEnterOtp;
                                                                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvEnterOtp);
                                                                if (appCompatTextView != null) {
                                                                    i2 = R.id.tvIncorrectOtpError;
                                                                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvIncorrectOtpError);
                                                                    if (appCompatTextView2 != null) {
                                                                        i2 = R.id.tvMessage;
                                                                        AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvMessage);
                                                                        if (appCompatTextView3 != null) {
                                                                            i2 = R.id.tvOtpCounter;
                                                                            AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOtpCounter);
                                                                            if (appCompatTextView4 != null) {
                                                                                i2 = R.id.tvOtpSendMessage;
                                                                                AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOtpSendMessage);
                                                                                if (appCompatTextView5 != null) {
                                                                                    i2 = R.id.tvOtpVerified;
                                                                                    AppCompatTextView appCompatTextView6 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOtpVerified);
                                                                                    if (appCompatTextView6 != null) {
                                                                                        i2 = R.id.tvOtpVerifyMsg;
                                                                                        AppCompatTextView appCompatTextView7 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOtpVerifyMsg);
                                                                                        if (appCompatTextView7 != null) {
                                                                                            i2 = R.id.tvOtpVerifyTitle;
                                                                                            AppCompatTextView appCompatTextView8 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOtpVerifyTitle);
                                                                                            if (appCompatTextView8 != null) {
                                                                                                i2 = R.id.tvResendOtp;
                                                                                                AppCompatTextView appCompatTextView9 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvResendOtp);
                                                                                                if (appCompatTextView9 != null) {
                                                                                                    i2 = R.id.tvTitle;
                                                                                                    AppCompatTextView appCompatTextView10 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTitle);
                                                                                                    if (appCompatTextView10 != null) {
                                                                                                        i2 = R.id.txtVerification;
                                                                                                        TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtVerification);
                                                                                                        if (textInputLayout != null) {
                                                                                                            i2 = R.id.viewHorizontalLine;
                                                                                                            View findChildViewById = ViewBindings.findChildViewById(view, R.id.viewHorizontalLine);
                                                                                                            if (findChildViewById != null) {
                                                                                                                return new BottomSheetChildAccountInvitationBinding((FrameLayout) view, appCompatImageButton, appCompatButton, appCompatButton2, appCompatButton3, relativeLayout, constraintLayout, pinView, appCompatImageView, appCompatImageView2, linearLayoutCompat, constraintLayout2, pageIndicatorView, recyclerView, nestedScrollView, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8, appCompatTextView9, appCompatTextView10, textInputLayout, findChildViewById);
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
    public static BottomSheetChildAccountInvitationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static BottomSheetChildAccountInvitationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.bottom_sheet_child_account_invitation, viewGroup, false);
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
