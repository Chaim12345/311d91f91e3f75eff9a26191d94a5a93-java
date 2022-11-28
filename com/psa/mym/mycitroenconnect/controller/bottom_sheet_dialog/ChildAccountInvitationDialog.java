package com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chaos.view.PinView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;
import com.psa.mym.mycitroenconnect.BusBaseBottomSheetFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.api.body.onboarding.RegisterUserBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.VerifyOTPBody;
import com.psa.mym.mycitroenconnect.api.body.secondary_user.CancelSecondaryUserBody;
import com.psa.mym.mycitroenconnect.api.body.secondary_user.NewVerifySecondaryUserBody;
import com.psa.mym.mycitroenconnect.api.body.secondary_user.SharedVehicleBody;
import com.psa.mym.mycitroenconnect.controller.adapters.ChildAccountInvitationAdapter;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.FailResponse;
import com.psa.mym.mycitroenconnect.model.PostCommonResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.SendOtpResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.VerifyOTPResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.SecondaryUser;
import com.psa.mym.mycitroenconnect.model.secondary_user.SecondaryUserVerify;
import com.psa.mym.mycitroenconnect.model.secondary_user.SharedVehicle;
import com.psa.mym.mycitroenconnect.services.OnBoardingService;
import com.psa.mym.mycitroenconnect.services.SecondaryUserService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.rd.PageIndicatorView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ChildAccountInvitationDialog extends BusBaseBottomSheetFragment implements View.OnClickListener, TextWatcher {
    @NotNull
    private static final String CHILD_ACCOUNT_INVITATION = "child_account_invitation";
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "ChildAccountInvitationDialog";
    @Nullable
    private ChildAccountInvitationAdapter childAcctInvitationAdapter;
    @Nullable
    private OnInvitationView onInvitationView;
    @Nullable
    private SecondaryUser secondaryUser;
    @Nullable
    private List<SharedVehicle> sharedVehicle;
    @Nullable
    private CountDownTimer timer;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String mobileNumber = "";
    @NotNull
    private String fullHashKey = "";

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final ChildAccountInvitationDialog newInstance(@NotNull SecondaryUser secondaryUser) {
            Intrinsics.checkNotNullParameter(secondaryUser, "secondaryUser");
            ChildAccountInvitationDialog childAccountInvitationDialog = new ChildAccountInvitationDialog();
            Bundle bundle = new Bundle();
            bundle.putParcelable(ChildAccountInvitationDialog.CHILD_ACCOUNT_INVITATION, secondaryUser);
            childAccountInvitationDialog.setArguments(bundle);
            return childAccountInvitationDialog;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void apiAcceptInvitation() {
        SharedVehicle sharedVehicle;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        List<SharedVehicle> list = this.sharedVehicle;
        String str = null;
        if (list != null) {
            String vinNum = list.get(0).getVinNum();
            objectRef.element = vinNum != null ? new SharedVehicleBody(null, null, null, null, vinNum, null, 47, null) : 0;
        }
        String str2 = this.mobileNumber;
        String substring = str2.substring(3, str2.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        T t2 = objectRef.element;
        Intrinsics.checkNotNull(t2);
        SharedVehicleBody sharedVehicleBody = (SharedVehicleBody) t2;
        StringBuilder sb = new StringBuilder();
        sb.append("91");
        List<SharedVehicle> list2 = this.sharedVehicle;
        if (list2 != null && (sharedVehicle = list2.get(0)) != null) {
            str = sharedVehicle.getMobileNum();
        }
        Intrinsics.checkNotNull(str);
        sb.append(str);
        NewVerifySecondaryUserBody newVerifySecondaryUserBody = new NewVerifySecondaryUserBody(sb.toString(), "+91", substring, sharedVehicleBody);
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.showDialog(requireContext);
        SecondaryUserService secondaryUserService = new SecondaryUserService();
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        secondaryUserService.verifyChildAccount(requireContext2, newVerifySecondaryUserBody);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void apiDeclineInvitation() {
        SharedVehicle sharedVehicle;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        List<SharedVehicle> list = this.sharedVehicle;
        String str = null;
        if (list != null) {
            String vinNum = list.get(0).getVinNum();
            objectRef.element = vinNum != null ? new SharedVehicleBody(null, null, null, null, vinNum, null, 47, null) : 0;
        }
        String str2 = this.mobileNumber;
        String substring = str2.substring(3, str2.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        StringBuilder sb = new StringBuilder();
        sb.append("91");
        List<SharedVehicle> list2 = this.sharedVehicle;
        if (list2 != null && (sharedVehicle = list2.get(0)) != null) {
            str = sharedVehicle.getMobileNum();
        }
        Intrinsics.checkNotNull(str);
        sb.append(str);
        String sb2 = sb.toString();
        T t2 = objectRef.element;
        Intrinsics.checkNotNull(t2);
        CancelSecondaryUserBody cancelSecondaryUserBody = new CancelSecondaryUserBody(substring, "+91", sb2, (SharedVehicleBody) t2);
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.showDialog(requireContext);
        SecondaryUserService secondaryUserService = new SecondaryUserService();
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        secondaryUserService.cancelChildAccount(requireContext2, cancelSecondaryUserBody);
    }

    private final void apiSendOTP() {
        RegisterUserBody registerUserBody = new RegisterUserBody();
        String str = this.mobileNumber;
        String substring = str.substring(3, str.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        registerUserBody.setMobileNum(substring);
        registerUserBody.setCountryCode("+91");
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.showDialog(requireContext);
        OnBoardingService onBoardingService = new OnBoardingService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        onBoardingService.callSendOTPAPI(requireActivity, registerUserBody);
    }

    private final void apiVerifyOTP() {
        SharedVehicle sharedVehicle;
        VerifyOTPBody verifyOTPBody = new VerifyOTPBody(null, null, null, null, null, null, null, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
        String str = this.mobileNumber;
        String substring = str.substring(3, str.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        verifyOTPBody.setUserName(substring);
        verifyOTPBody.setCountryCode("+91");
        verifyOTPBody.setOtp(String.valueOf(((PinView) _$_findCachedViewById(R.id.edVerification)).getText()));
        verifyOTPBody.setFullhash(this.fullHashKey);
        List<SharedVehicle> list = this.sharedVehicle;
        verifyOTPBody.setVinNum((list == null || (sharedVehicle = list.get(0)) == null) ? null : sharedVehicle.getVinNum());
        OnBoardingService onBoardingService = new OnBoardingService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        OnBoardingService.callVerifyOtpChildInvite$default(onBoardingService, requireActivity, verifyOTPBody, false, 4, null);
    }

    private final void initChildAcctInvitationRV() {
        List<SharedVehicle> list = this.sharedVehicle;
        if (list != null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            this.childAcctInvitationAdapter = new ChildAccountInvitationAdapter(requireContext, (ArrayList) list);
            int i2 = R.id.rvChildAcctInvitation;
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i2);
            if (recyclerView != null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
            }
            if (recyclerView != null) {
                recyclerView.setHasFixedSize(true);
            }
            if (recyclerView != null) {
                recyclerView.setAdapter(this.childAcctInvitationAdapter);
            }
            final Ref.IntRef intRef = new Ref.IntRef();
            final Ref.IntRef intRef2 = new Ref.IntRef();
            PageIndicatorView pageIndicatorView = (PageIndicatorView) _$_findCachedViewById(R.id.pageIndicatorViewCAI);
            if (pageIndicatorView != null) {
                List<SharedVehicle> list2 = this.sharedVehicle;
                Intrinsics.checkNotNull(list2);
                pageIndicatorView.setCount(list2.size());
            }
            ((RecyclerView) _$_findCachedViewById(i2)).addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ChildAccountInvitationDialog$initChildAcctInvitationRV$1$2
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(@NotNull RecyclerView recyclerView2, int i3, int i4) {
                    Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                    super.onScrolled(recyclerView2, i3, i4);
                    Ref.IntRef.this.element += i3;
                    View childAt = recyclerView2.getChildAt(0);
                    if (childAt != null) {
                        int width = childAt.getWidth();
                        Ref.IntRef intRef3 = Ref.IntRef.this;
                        Ref.IntRef intRef4 = intRef;
                        ChildAccountInvitationDialog childAccountInvitationDialog = this;
                        float f2 = width;
                        int floor = (int) Math.floor((intRef3.element + (f2 / 2.0f)) / f2);
                        int i5 = intRef4.element;
                        if (i5 != floor) {
                            childAccountInvitationDialog.setCurrentItem(i5 < floor ? i5 + 1 : i5 - 1);
                        }
                        intRef4.element = floor;
                    }
                }
            });
        }
    }

    private final void initView() {
        String sb;
        SharedVehicle sharedVehicle;
        SecondaryUser secondaryUser = this.secondaryUser;
        String str = null;
        List<SharedVehicle> sharedVehicle2 = secondaryUser != null ? secondaryUser.getSharedVehicle() : null;
        this.sharedVehicle = sharedVehicle2;
        Intrinsics.checkNotNull(sharedVehicle2);
        int size = sharedVehicle2.size();
        if (size <= 1) {
            sb = "";
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append('(');
            sb2.append(size);
            sb2.append(')');
            sb = sb2.toString();
        }
        if (size <= 1) {
            PageIndicatorView pageIndicatorViewCAI = (PageIndicatorView) _$_findCachedViewById(R.id.pageIndicatorViewCAI);
            Intrinsics.checkNotNullExpressionValue(pageIndicatorViewCAI, "pageIndicatorViewCAI");
            ExtensionsKt.invisible(pageIndicatorViewCAI);
        } else {
            PageIndicatorView pageIndicatorViewCAI2 = (PageIndicatorView) _$_findCachedViewById(R.id.pageIndicatorViewCAI);
            Intrinsics.checkNotNullExpressionValue(pageIndicatorViewCAI2, "pageIndicatorViewCAI");
            ExtensionsKt.show(pageIndicatorViewCAI2);
        }
        List<SharedVehicle> list = this.sharedVehicle;
        if (list != null && (sharedVehicle = list.get(0)) != null) {
            String mobileNum = sharedVehicle.getMobileNum();
            if (mobileNum != null) {
                String mobileNum2 = sharedVehicle.getMobileNum();
                Integer valueOf = mobileNum2 != null ? Integer.valueOf(mobileNum2.length()) : null;
                Intrinsics.checkNotNull(valueOf);
                str = mobileNum.substring(8, valueOf.intValue());
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(uat.psa.mym.mycitroenconnect.R.string.child_account_invitation_message);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.child…count_invitation_message)");
            String format = String.format(string, Arrays.copyOf(new Object[]{sharedVehicle.getOwnerName(), sharedVehicle.getCountryCode(), str, sb}, 4));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvMessage)).setText(format);
            initChildAcctInvitationRV();
        }
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String mobileNumber = companion.getMobileNumber(requireContext);
        this.mobileNumber = mobileNumber;
        String substring = mobileNumber.substring(9, mobileNumber.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String string2 = getString(uat.psa.mym.mycitroenconnect.R.string.label_otp_verification_msg);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.label_otp_verification_msg)");
        String format2 = String.format(string2, Arrays.copyOf(new Object[]{substring}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvOtpVerifyMsg)).setText(format2);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivClose)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnCancel)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnVerify)).setOnClickListener(this);
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvResendOtp)).setOnClickListener(this);
        ((AppCompatImageButton) _$_findCachedViewById(R.id.btnBack)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnOtpVerify)).setOnClickListener(this);
        showChildAccountInvitationDialog();
    }

    @JvmStatic
    @NotNull
    public static final ChildAccountInvitationDialog newInstance(@NotNull SecondaryUser secondaryUser) {
        return Companion.newInstance(secondaryUser);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCurrentItem(int i2) {
        ((PageIndicatorView) _$_findCachedViewById(R.id.pageIndicatorViewCAI)).setSelection(Math.abs(i2));
    }

    private final void showChildAccountInvitationDialog() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        ExtensionsKt.hideKeyboard(requireActivity);
        RelativeLayout clChildAccountInvitation = (RelativeLayout) _$_findCachedViewById(R.id.clChildAccountInvitation);
        Intrinsics.checkNotNullExpressionValue(clChildAccountInvitation, "clChildAccountInvitation");
        ExtensionsKt.show(clChildAccountInvitation);
        ConstraintLayout clOtpVerify = (ConstraintLayout) _$_findCachedViewById(R.id.clOtpVerify);
        Intrinsics.checkNotNullExpressionValue(clOtpVerify, "clOtpVerify");
        ExtensionsKt.hide(clOtpVerify);
        ConstraintLayout otpVerifySuccess = (ConstraintLayout) _$_findCachedViewById(R.id.otpVerifySuccess);
        Intrinsics.checkNotNullExpressionValue(otpVerifySuccess, "otpVerifySuccess");
        ExtensionsKt.hide(otpVerifySuccess);
    }

    private final void showOtpVerifyDialog() {
        ((PinView) _$_findCachedViewById(R.id.edVerification)).requestFocus();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        ExtensionsKt.showKeyboard(requireActivity);
        ConstraintLayout clOtpVerify = (ConstraintLayout) _$_findCachedViewById(R.id.clOtpVerify);
        Intrinsics.checkNotNullExpressionValue(clOtpVerify, "clOtpVerify");
        ExtensionsKt.show(clOtpVerify);
        RelativeLayout clChildAccountInvitation = (RelativeLayout) _$_findCachedViewById(R.id.clChildAccountInvitation);
        Intrinsics.checkNotNullExpressionValue(clChildAccountInvitation, "clChildAccountInvitation");
        ExtensionsKt.hide(clChildAccountInvitation);
        ConstraintLayout otpVerifySuccess = (ConstraintLayout) _$_findCachedViewById(R.id.otpVerifySuccess);
        Intrinsics.checkNotNullExpressionValue(otpVerifySuccess, "otpVerifySuccess");
        ExtensionsKt.hide(otpVerifySuccess);
    }

    private final void showOtpVerifySuccessDialog() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        ExtensionsKt.hideKeyboard(requireActivity);
        ConstraintLayout otpVerifySuccess = (ConstraintLayout) _$_findCachedViewById(R.id.otpVerifySuccess);
        Intrinsics.checkNotNullExpressionValue(otpVerifySuccess, "otpVerifySuccess");
        ExtensionsKt.show(otpVerifySuccess);
        RelativeLayout clChildAccountInvitation = (RelativeLayout) _$_findCachedViewById(R.id.clChildAccountInvitation);
        Intrinsics.checkNotNullExpressionValue(clChildAccountInvitation, "clChildAccountInvitation");
        ExtensionsKt.hide(clChildAccountInvitation);
        ConstraintLayout clOtpVerify = (ConstraintLayout) _$_findCachedViewById(R.id.clOtpVerify);
        Intrinsics.checkNotNullExpressionValue(clOtpVerify, "clOtpVerify");
        ExtensionsKt.hide(clOtpVerify);
    }

    private final void startCountDown() {
        CountDownTimer countDownTimer = this.timer;
        if (countDownTimer != null && countDownTimer != null) {
            countDownTimer.cancel();
        }
        this.timer = new CountDownTimer() { // from class: com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ChildAccountInvitationDialog$startCountDown$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(120000L, 1000L);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                ((AppCompatTextView) ChildAccountInvitationDialog.this._$_findCachedViewById(R.id.tvResendOtp)).setVisibility(0);
                cancel();
            }

            @Override // android.os.CountDownTimer
            @SuppressLint({"SetTextI18n"})
            public void onTick(long j2) {
                ChildAccountInvitationDialog childAccountInvitationDialog = ChildAccountInvitationDialog.this;
                int i2 = R.id.tvOtpCounter;
                ((AppCompatTextView) childAccountInvitationDialog._$_findCachedViewById(i2)).setVisibility(0);
                ((AppCompatTextView) ChildAccountInvitationDialog.this._$_findCachedViewById(R.id.tvResendOtp)).setVisibility(8);
                DecimalFormat decimalFormat = new DecimalFormat("00");
                long j3 = 60;
                long j4 = (j2 / 60000) % j3;
                long j5 = (j2 / 1000) % j3;
                ((AppCompatTextView) ChildAccountInvitationDialog.this._$_findCachedViewById(i2)).setText(decimalFormat.format(j4) + " : " + decimalFormat.format(j5));
            }
        }.start();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseBottomSheetFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseBottomSheetFragment
    @Nullable
    public View _$_findCachedViewById(int i2) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i2)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(@Nullable Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
    }

    @Subscribe
    public final void getResponse(@NotNull ErrorResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        String apiName = response.getApiName();
        if (Intrinsics.areEqual(apiName, "CancelChildAccount") || Intrinsics.areEqual(apiName, "VerifyChildAccount")) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            ExtensionsKt.showToast(requireContext, response.getMsg());
        }
    }

    @Subscribe
    public final void getResponse(@NotNull FailResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        AppUtil.Companion.dismissDialog();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, response.getMessage());
    }

    @Subscribe
    public final void getResponse(@NotNull PostCommonResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        if (Intrinsics.areEqual(response.getApiName(), "CancelChildAccount")) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            ExtensionsKt.showToast(requireContext, response.getMessage());
            if (response.getStatusCode() == 200) {
                dismiss();
                OnInvitationView onInvitationView = this.onInvitationView;
                if (onInvitationView == null || onInvitationView == null) {
                    return;
                }
                onInvitationView.onRejected();
                return;
            }
            Logger logger = Logger.INSTANCE;
            logger.e("API Name: " + response.getApiName());
            logger.e("Response Code: " + response.getStatusCode());
            logger.e("Message: " + response.getMessage());
        }
    }

    @Subscribe
    public final void getResponse(@NotNull SendOtpResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        AppUtil.Companion.dismissDialog();
        this.fullHashKey = response.getFullHash();
        startCountDown();
    }

    @Subscribe
    public final void getResponse(@NotNull VerifyOTPResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        if (Intrinsics.areEqual(response.getSuccess(), "true")) {
            showOtpVerifySuccessDialog();
            return;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getString(uat.psa.mym.mycitroenconnect.R.string.msg_invalid_otp);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.msg_invalid_otp)");
        ExtensionsKt.showToast(requireContext, string);
    }

    @Subscribe
    public final void getResponse(@NotNull SecondaryUserVerify response) {
        Intrinsics.checkNotNullParameter(response, "response");
        if (Intrinsics.areEqual(response.getSuccess(), "true")) {
            showOtpVerifyDialog();
            this.fullHashKey = response.getFullHash();
            startCountDown();
            return;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, response.getMessage());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        TextInputLayout textInputLayout;
        int i2;
        OnInvitationView onInvitationView;
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivClose))) {
            RelativeLayout clChildAccountInvitation = (RelativeLayout) _$_findCachedViewById(R.id.clChildAccountInvitation);
            Intrinsics.checkNotNullExpressionValue(clChildAccountInvitation, "clChildAccountInvitation");
            if (ExtensionsKt.isVisible(clChildAccountInvitation)) {
                dismiss();
                onInvitationView = this.onInvitationView;
                if (onInvitationView == null || onInvitationView == null) {
                    return;
                }
            } else {
                ConstraintLayout clOtpVerify = (ConstraintLayout) _$_findCachedViewById(R.id.clOtpVerify);
                Intrinsics.checkNotNullExpressionValue(clOtpVerify, "clOtpVerify");
                if (!ExtensionsKt.isVisible(clOtpVerify)) {
                    ConstraintLayout otpVerifySuccess = (ConstraintLayout) _$_findCachedViewById(R.id.otpVerifySuccess);
                    Intrinsics.checkNotNullExpressionValue(otpVerifySuccess, "otpVerifySuccess");
                    if (ExtensionsKt.isVisible(otpVerifySuccess)) {
                        dismiss();
                        OnInvitationView onInvitationView2 = this.onInvitationView;
                        if (onInvitationView2 == null || onInvitationView2 == null) {
                            return;
                        }
                        onInvitationView2.onAccepted();
                        return;
                    }
                    return;
                }
                dismiss();
                onInvitationView = this.onInvitationView;
                if (onInvitationView == null || onInvitationView == null) {
                    return;
                }
            }
            onInvitationView.onClosed();
        } else if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnCancel))) {
            apiDeclineInvitation();
        } else if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnVerify))) {
            apiAcceptInvitation();
        } else if (Intrinsics.areEqual(view, (AppCompatImageButton) _$_findCachedViewById(R.id.btnBack))) {
            showChildAccountInvitationDialog();
        } else if (!Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnOtpVerify))) {
            if (Intrinsics.areEqual(view, (AppCompatTextView) _$_findCachedViewById(R.id.tvResendOtp))) {
                apiSendOTP();
            }
        } else {
            int i3 = R.id.edVerification;
            if (String.valueOf(((PinView) _$_findCachedViewById(i3)).getText()).length() == 0) {
                ((PinView) _$_findCachedViewById(i3)).requestFocus();
                ((PinView) _$_findCachedViewById(i3)).setLineColor(ContextCompat.getColor(requireContext(), uat.psa.mym.mycitroenconnect.R.color.dark_red));
                ((PinView) _$_findCachedViewById(i3)).setTextColor(ContextCompat.getColor(requireContext(), uat.psa.mym.mycitroenconnect.R.color.dark_red));
                textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtVerification);
                i2 = uat.psa.mym.mycitroenconnect.R.string.err_enter_otp;
            } else if (String.valueOf(((PinView) _$_findCachedViewById(i3)).getText()).length() == 4) {
                apiVerifyOTP();
                return;
            } else {
                ((PinView) _$_findCachedViewById(i3)).requestFocus();
                ((PinView) _$_findCachedViewById(i3)).setLineColor(ContextCompat.getColor(requireContext(), uat.psa.mym.mycitroenconnect.R.color.dark_red));
                ((PinView) _$_findCachedViewById(i3)).setTextColor(ContextCompat.getColor(requireContext(), uat.psa.mym.mycitroenconnect.R.color.dark_red));
                textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtVerification);
                i2 = uat.psa.mym.mycitroenconnect.R.string.err_otp_length_4;
            }
            textInputLayout.setError(getString(i2));
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.secondaryUser = (SecondaryUser) arguments.getParcelable(CHILD_ACCOUNT_INVITATION);
        }
        setStyle(0, uat.psa.mym.mycitroenconnect.R.style.DialogStyle);
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialogFragment, androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    @NotNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext(), getTheme());
        bottomSheetDialog.getBehavior().setState(3);
        bottomSheetDialog.getBehavior().setSkipCollapsed(true);
        return bottomSheetDialog;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.bottom_sheet_child_account_invitation, viewGroup, false);
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseBottomSheetFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(@NotNull DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
        CountDownTimer countDownTimer = this.timer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
        int i5 = R.id.txtVerification;
        if (((TextInputLayout) _$_findCachedViewById(i5)).getError() != null) {
            ((TextInputLayout) _$_findCachedViewById(i5)).setError(null);
        }
        int i6 = R.id.edVerification;
        ((PinView) _$_findCachedViewById(i6)).setLineColor(ContextCompat.getColor(requireContext(), uat.psa.mym.mycitroenconnect.R.color.dark_grey));
        ((PinView) _$_findCachedViewById(i6)).setTextColor(ContextCompat.getColor(requireContext(), uat.psa.mym.mycitroenconnect.R.color.black));
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initView();
    }

    public final void setOnInvitationView(@NotNull OnInvitationView onInvitationView) {
        Intrinsics.checkNotNullParameter(onInvitationView, "onInvitationView");
        this.onInvitationView = onInvitationView;
    }
}
