package com.psa.mym.mycitroenconnect.controller.fragments.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.messaging.FirebaseMessaging;
import com.psa.mym.mycitroenconnect.BuildConfig;
import com.psa.mym.mycitroenconnect.BusBaseFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.api.body.fcm.RegisterFCMBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.GetTokenBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.RegisterUserBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.VerifyOTPBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.ConfirmCarDetailsActivity;
import com.psa.mym.mycitroenconnect.controller.activities.onboarding.SetNewPinActivity;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ChangePasswordOtpSuccessBottomFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OtpSuccessBottomSheetFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.login.VerifyOtpFragment;
import com.psa.mym.mycitroenconnect.databinding.FragmentVerifyOtpBinding;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.FailResponse;
import com.psa.mym.mycitroenconnect.model.PostCommonResponse;
import com.psa.mym.mycitroenconnect.model.Token;
import com.psa.mym.mycitroenconnect.model.onboarding.ChangePasswordResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisterUserResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import com.psa.mym.mycitroenconnect.model.onboarding.SendOtpResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.UserProfileResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.VerifyOTPResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCar;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCarResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCars;
import com.psa.mym.mycitroenconnect.services.FCMService;
import com.psa.mym.mycitroenconnect.services.OnBoardingService;
import com.psa.mym.mycitroenconnect.services.SecondaryUserService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsJVMKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class VerifyOtpFragment extends BusBaseFragment implements View.OnClickListener, TextWatcher {
    @Nullable
    private String appPin;
    private FragmentVerifyOtpBinding binding;
    private ChangePasswordResponse changePasswordResponse;
    private String mFullHash;
    private String mPageMode;
    private String mobileNumber;
    private RegisterUserResponse registerUserResponse;
    private String vinNum;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    private Boolean isFromMainScreen = Boolean.TRUE;
    @NotNull
    private SendOtpResponse sendOtpResponse = new SendOtpResponse(null, null, null, null, 0, null, 63, null);
    @NotNull
    private UserProfileResponse userProfileResponse = new UserProfileResponse(null, null, null, null, null, null, null, null, null, null, null, null, 4095, null);

    private final void apiAuthenticateUser(VerifyOTPResponse verifyOTPResponse) {
        String str = null;
        RegisterUserResponse registerUserResponse = null;
        GetTokenBody getTokenBody = new GetTokenBody(null, null, null, null, 15, null);
        String str2 = this.mobileNumber;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
            str2 = null;
        }
        String str3 = this.mobileNumber;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
            str3 = null;
        }
        String substring = str2.substring(3, str3.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        getTokenBody.setUserName(substring);
        String str4 = this.mobileNumber;
        if (str4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
            str4 = null;
        }
        String substring2 = str4.substring(0, 3);
        Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
        getTokenBody.setCountryCode(substring2);
        OnBoardingService onBoardingService = new OnBoardingService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        onBoardingService.callGetUserTokenAPI(requireActivity, getTokenBody, false);
        SharedPref.Companion companion = SharedPref.Companion;
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        String str5 = this.mobileNumber;
        if (str5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
            str5 = null;
        }
        companion.setMobileNumber(requireActivity2, str5);
        String str6 = this.appPin;
        if (str6 != null) {
            FragmentActivity requireActivity3 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
            companion.setAppPin(requireActivity3, str6);
        }
        if (verifyOTPResponse.getToken() != null) {
            FragmentActivity requireActivity4 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
            companion.setSecondaryUserTokenDetails(requireActivity4, verifyOTPResponse.getToken());
        }
        AppUtil.Companion companion2 = AppUtil.Companion;
        companion2.dismissDialog();
        String str7 = this.mPageMode;
        if (str7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPageMode");
            str7 = null;
        }
        if (Intrinsics.areEqual(str7, AppConstants.PAGE_MODE_REGISTRATION)) {
            SetNewPasswordFragment setNewPasswordFragment = new SetNewPasswordFragment();
            Bundle bundle = new Bundle();
            bundle.putString("login", AppConstants.PAGE_MODE_ADD_VEHICLE);
            RegisterUserResponse registerUserResponse2 = this.registerUserResponse;
            if (registerUserResponse2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("registerUserResponse");
            } else {
                registerUserResponse = registerUserResponse2;
            }
            bundle.putParcelable(AppConstants.REGUSER, registerUserResponse);
            bundle.putString(AppConstants.TOKEN_HASH, verifyOTPResponse.getTokenHash());
            setNewPasswordFragment.setArguments(bundle);
            FragmentManager supportFragmentManager = requireActivity().getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "requireActivity().supportFragmentManager");
            companion2.replaceFragment(supportFragmentManager, setNewPasswordFragment);
            return;
        }
        String str8 = this.mPageMode;
        if (str8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPageMode");
            str8 = null;
        }
        if (Intrinsics.areEqual(str8, AppConstants.PAGE_MODE_CHANGE_PWD)) {
            requireActivity().onBackPressed();
            return;
        }
        String str9 = this.mPageMode;
        if (str9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPageMode");
            str9 = null;
        }
        if (Intrinsics.areEqual(str9, "login")) {
            apiPermission();
            return;
        }
        OnBoardingService onBoardingService2 = new OnBoardingService();
        FragmentActivity requireActivity5 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity5, "requireActivity()");
        String str10 = this.mobileNumber;
        if (str10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
            str10 = null;
        }
        String str11 = this.mobileNumber;
        if (str11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
        } else {
            str = str11;
        }
        String substring3 = str10.substring(1, str.length());
        Intrinsics.checkNotNullExpressionValue(substring3, "this as java.lang.String…ing(startIndex, endIndex)");
        onBoardingService2.callGetUserProfileAPI(requireActivity5, substring3);
    }

    private final void apiPermission() {
        AppUtil.Companion companion = AppUtil.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        companion.showDialog(requireActivity);
        SharedPref.Companion companion2 = SharedPref.Companion;
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        this.mobileNumber = companion2.getMobileNumber(requireActivity2);
        SecondaryUserService secondaryUserService = new SecondaryUserService();
        FragmentActivity requireActivity3 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
        StringBuilder sb = new StringBuilder();
        sb.append("91");
        String str = this.mobileNumber;
        String str2 = null;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
            str = null;
        }
        String str3 = this.mobileNumber;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
        } else {
            str2 = str3;
        }
        String substring = str.substring(3, str2.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        sb.append(substring);
        secondaryUserService.getMyCarList(requireActivity3, sb.toString(), AppConstants.SCREEN_SET_NEW_PIN);
    }

    private final void apiSendOtp() {
        RegisterUserBody registerUserBody = new RegisterUserBody();
        String str = this.mobileNumber;
        String str2 = null;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
            str = null;
        }
        String str3 = this.mobileNumber;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
        } else {
            str2 = str3;
        }
        String substring = str.substring(3, str2.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        registerUserBody.setMobileNum(substring);
        registerUserBody.setCountryCode("+91");
        AppUtil.Companion companion = AppUtil.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        companion.showDialog(requireActivity);
        OnBoardingService onBoardingService = new OnBoardingService();
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        onBoardingService.callSendOTPAPI(requireActivity2, registerUserBody);
    }

    private final void apiVerifyOTP() {
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.showDialog(requireContext);
        VerifyOTPBody verifyOTPBody = new VerifyOTPBody(null, null, null, null, null, null, null, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
        String str = this.mobileNumber;
        String str2 = null;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
            str = null;
        }
        String str3 = this.mobileNumber;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
            str3 = null;
        }
        String substring = str.substring(3, str3.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        verifyOTPBody.setUserName(substring);
        verifyOTPBody.setCountryCode("+91");
        FragmentVerifyOtpBinding fragmentVerifyOtpBinding = this.binding;
        if (fragmentVerifyOtpBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVerifyOtpBinding = null;
        }
        verifyOTPBody.setOtp(String.valueOf(fragmentVerifyOtpBinding.edVerification.getText()));
        String str4 = this.mPageMode;
        if (str4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPageMode");
            str4 = null;
        }
        verifyOTPBody.setApiFrom(str4);
        String str5 = this.mPageMode;
        if (str5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPageMode");
            str5 = null;
        }
        if (Intrinsics.areEqual(str5, AppConstants.PAGE_MODE_REGISTRATION)) {
            String str6 = this.mFullHash;
            if (str6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFullHash");
            } else {
                str2 = str6;
            }
            verifyOTPBody.setFullhash(str2);
            OnBoardingService onBoardingService = new OnBoardingService();
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            onBoardingService.callVerifyOTPAPI(requireActivity, verifyOTPBody, false);
            return;
        }
        String str7 = this.mPageMode;
        if (str7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPageMode");
            str7 = null;
        }
        if (Intrinsics.areEqual(str7, AppConstants.PAGE_MODE_FORGOT_PWD)) {
            String str8 = this.mFullHash;
            if (str8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFullHash");
            } else {
                str2 = str8;
            }
            verifyOTPBody.setFullHash(str2);
            OnBoardingService onBoardingService2 = new OnBoardingService();
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            onBoardingService2.callVerifyOtpForgotPassword(requireActivity2, verifyOTPBody, false);
            return;
        }
        String str9 = this.mPageMode;
        if (str9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPageMode");
            str9 = null;
        }
        if (Intrinsics.areEqual(str9, "login")) {
            String str10 = this.mFullHash;
            if (str10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFullHash");
            } else {
                str2 = str10;
            }
            verifyOTPBody.setFullhash(str2);
            OnBoardingService onBoardingService3 = new OnBoardingService();
            FragmentActivity requireActivity3 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
            onBoardingService3.callVerifyOtpLoginPassword(requireActivity3, verifyOTPBody, false);
            return;
        }
        String str11 = this.mPageMode;
        if (str11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPageMode");
            str11 = null;
        }
        if (Intrinsics.areEqual(str11, AppConstants.PAGE_MODE_CHANGE_PWD)) {
            String str12 = this.mFullHash;
            if (str12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFullHash");
            } else {
                str2 = str12;
            }
            verifyOTPBody.setFullhash(str2);
            OnBoardingService onBoardingService4 = new OnBoardingService();
            FragmentActivity requireActivity4 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
            onBoardingService4.callVerifyOtpChangePassword(requireActivity4, verifyOTPBody, false);
        }
    }

    private final void displayDialog(boolean z, boolean z2) {
        AppUtil.Companion.dismissDialog();
        OtpSuccessBottomSheetFragment newInstance = OtpSuccessBottomSheetFragment.Companion.newInstance();
        Bundle bundle = new Bundle();
        String str = this.mPageMode;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPageMode");
            str = null;
        }
        bundle.putString("mPageMode", str);
        bundle.putBoolean("isBio", z);
        bundle.putBoolean("isRegisteredUser", z2);
        newInstance.setArguments(bundle);
        newInstance.show(requireActivity().getSupportFragmentManager(), OtpSuccessBottomSheetFragment.TAG);
        newInstance.setCancelable(false);
    }

    private final void displayOtpSuccessDialog(boolean z) {
        AppUtil.Companion.dismissDialog();
        ChangePasswordOtpSuccessBottomFragment newInstance = ChangePasswordOtpSuccessBottomFragment.Companion.newInstance();
        Bundle bundle = new Bundle();
        String str = this.mPageMode;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPageMode");
            str = null;
        }
        bundle.putString("mPageMode", str);
        newInstance.setArguments(bundle);
        newInstance.show(requireActivity().getSupportFragmentManager(), ChangePasswordOtpSuccessBottomFragment.TAG);
        newInstance.setCancelable(false);
    }

    private final void initView() {
        int i2 = R.id.edVerification;
        ((PinView) _$_findCachedViewById(i2)).requestFocus();
        ((PinView) _$_findCachedViewById(i2)).postDelayed(new Runnable() { // from class: m.d
            @Override // java.lang.Runnable
            public final void run() {
                VerifyOtpFragment.m148initView$lambda23(VerifyOtpFragment.this);
            }
        }, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-23  reason: not valid java name */
    public static final void m148initView$lambda23(VerifyOtpFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        ExtensionsKt.showKeyboard(requireActivity);
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x00d0, code lost:
        r10 = com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref.Companion;
        r2 = requireActivity();
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, "requireActivity()");
        r10.setIsGuestUser(r2, "false");
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00ea, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0013, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean isRegisteredUser(MyCars myCars) {
        MyCar next;
        String str;
        SharedPref.Companion companion;
        if (myCars.size() > 1) {
            CollectionsKt__MutableCollectionsJVMKt.sortWith(myCars, new Comparator() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.login.VerifyOtpFragment$isRegisteredUser$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(T t2, T t3) {
                    int compareValues;
                    compareValues = ComparisonsKt__ComparisonsKt.compareValues(((MyCar) t2).getUserType(), ((MyCar) t3).getUserType());
                    return compareValues;
                }
            });
        }
        Iterator<MyCar> it = myCars.iterator();
        while (true) {
            if (it.hasNext()) {
                next = it.next();
                String userType = next.getUserType();
                String str2 = null;
                if (userType != null) {
                    str = userType.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                } else {
                    str = null;
                }
                if (Intrinsics.areEqual(str, "p")) {
                    companion = SharedPref.Companion;
                    FragmentActivity requireActivity = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                    companion.setIsPrimaryUser(requireActivity, "true");
                } else if (Intrinsics.areEqual(str, "s")) {
                    String inviteStatus = next.getInviteStatus();
                    if (inviteStatus != null) {
                        str2 = inviteStatus.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    }
                    if (str2 != null) {
                        switch (str2.hashCode()) {
                            case -2146525273:
                                if (str2.equals("accepted")) {
                                    break;
                                } else {
                                    continue;
                                }
                            case -1994383672:
                                if (str2.equals(AppConstants.SECONDARY_USER_STATE_VERIFIED)) {
                                    break;
                                } else {
                                    continue;
                                }
                            case -682587753:
                                if (str2.equals(AppConstants.SECONDARY_USER_STATE_PENDING)) {
                                    SharedPref.Companion companion2 = SharedPref.Companion;
                                    FragmentActivity requireActivity2 = requireActivity();
                                    Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                                    companion2.setIsGuestUser(requireActivity2, "true");
                                    FragmentActivity requireActivity3 = requireActivity();
                                    Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
                                    companion2.setIsVerifiedUser(requireActivity3, "false");
                                    return false;
                                }
                                continue;
                            case -608496514:
                                if (str2.equals("rejected")) {
                                    SharedPref.Companion companion3 = SharedPref.Companion;
                                    FragmentActivity requireActivity4 = requireActivity();
                                    Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
                                    companion3.setIsGuestUser(requireActivity4, "true");
                                    FragmentActivity requireActivity5 = requireActivity();
                                    Intrinsics.checkNotNullExpressionValue(requireActivity5, "requireActivity()");
                                    companion3.setIsVerifiedUser(requireActivity5, "false");
                                    return false;
                                }
                                continue;
                        }
                    }
                } else {
                    SharedPref.Companion companion4 = SharedPref.Companion;
                    FragmentActivity requireActivity6 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity6, "requireActivity()");
                    companion4.setIsGuestUser(requireActivity6, "true");
                }
            }
        }
        FragmentActivity requireActivity7 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity7, "requireActivity()");
        companion.setIsVerifiedUser(requireActivity7, "true");
        setVehicleDetails(next);
        return true;
    }

    private final void registerFCMToken(List<String> list, final String str) {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener() { // from class: m.c
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                VerifyOtpFragment.m149registerFCMToken$lambda12(VerifyOtpFragment.this, str, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: registerFCMToken$lambda-12  reason: not valid java name */
    public static final void m149registerFCMToken$lambda12(VerifyOtpFragment this$0, String isGuest, Task task) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(isGuest, "$isGuest");
        Intrinsics.checkNotNullParameter(task, "task");
        if (!task.isSuccessful()) {
            Logger.INSTANCE.e("FCM Registration Token Failed " + task.getException());
            AppUtil.Companion.dismissDialog();
            Context requireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = this$0.getString(uat.psa.mym.mycitroenconnect.R.string.something_went_wrong);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.something_went_wrong)");
            ExtensionsKt.showToast(requireContext, string);
            return;
        }
        String str = (String) task.getResult();
        String str2 = this$0.mobileNumber;
        String str3 = null;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
            str2 = null;
        }
        String str4 = this$0.mobileNumber;
        if (str4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
        } else {
            str3 = str4;
        }
        String substring = str2.substring(1, str3.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        AppUtil.Companion companion = AppUtil.Companion;
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        RegisterFCMBody registerFCMBody = new RegisterFCMBody(null, substring, companion.getDeviceId(requireActivity), str, BuildConfig.VERSION_NAME, isGuest, null, 65, null);
        FCMService fCMService = new FCMService();
        FragmentActivity requireActivity2 = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        fCMService.registerToken(requireActivity2, registerFCMBody, false);
    }

    private final void setListeners() {
        FragmentVerifyOtpBinding fragmentVerifyOtpBinding = this.binding;
        FragmentVerifyOtpBinding fragmentVerifyOtpBinding2 = null;
        if (fragmentVerifyOtpBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVerifyOtpBinding = null;
        }
        fragmentVerifyOtpBinding.btnVerify.setOnClickListener(this);
        FragmentVerifyOtpBinding fragmentVerifyOtpBinding3 = this.binding;
        if (fragmentVerifyOtpBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVerifyOtpBinding3 = null;
        }
        fragmentVerifyOtpBinding3.btnBack.setOnClickListener(this);
        FragmentVerifyOtpBinding fragmentVerifyOtpBinding4 = this.binding;
        if (fragmentVerifyOtpBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentVerifyOtpBinding2 = fragmentVerifyOtpBinding4;
        }
        fragmentVerifyOtpBinding2.tvResendOtp.setOnClickListener(this);
    }

    private final void setTokenDetails(Token token) {
        if (token != null) {
            SharedPref.Companion companion = SharedPref.Companion;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            companion.setPrimaryUserTokenDetails(requireActivity, token);
        }
    }

    private final void setVehicleDetails(MyCar myCar) {
        SharedPref.Companion companion = SharedPref.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String vinNum = myCar.getVinNum();
        if (vinNum == null) {
            vinNum = "";
        }
        companion.setVinNumber(requireActivity, vinNum);
        String vehicleType = myCar.getVehicleType();
        if (vehicleType != null) {
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            companion.setVehicleType(requireActivity2, vehicleType);
        }
        setTokenDetails(myCar.getToken());
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00ba, code lost:
        if (r1.hasEnrolledFingerprints() == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00e0, code lost:
        if (r1.hasEnrolledFingerprints() == false) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void showOTPSuccessDialog() {
        RegisteredVehicleItem registeredVehicleItem;
        SharedPref.Companion companion = SharedPref.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        companion.setUserProfileResponse(requireActivity, this.userProfileResponse);
        String fullName = this.userProfileResponse.getFullName();
        if (fullName != null) {
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            companion.setUserFirstName(requireActivity2, fullName);
        }
        if (this.userProfileResponse.getRegisteredVehicle() != null) {
            List<RegisteredVehicleItem> registeredVehicle = this.userProfileResponse.getRegisteredVehicle();
            Intrinsics.checkNotNull(registeredVehicle);
            if (!registeredVehicle.isEmpty()) {
                List<RegisteredVehicleItem> registeredVehicle2 = this.userProfileResponse.getRegisteredVehicle();
                if (registeredVehicle2 != null && (registeredVehicleItem = registeredVehicle2.get(0)) != null) {
                    String vinNum = registeredVehicleItem.getVinNum();
                    if (vinNum != null) {
                        FragmentActivity requireActivity3 = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
                        companion.setVinNumber(requireActivity3, vinNum);
                    }
                    FragmentActivity requireActivity4 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
                    companion.setVehicleType(requireActivity4, registeredVehicleItem.getVehicleType());
                    String vehicleNumber = registeredVehicleItem.getVehicleNumber();
                    if (vehicleNumber != null) {
                        FragmentActivity requireActivity5 = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity5, "requireActivity()");
                        companion.setVehicleNumber(requireActivity5, vehicleNumber);
                    }
                }
                FingerprintManagerCompat from = FingerprintManagerCompat.from(requireActivity());
                Intrinsics.checkNotNullExpressionValue(from, "from(requireActivity())");
                if (!from.isHardwareDetected() || !from.hasEnrolledFingerprints()) {
                    FragmentActivity requireActivity6 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity6, "requireActivity()");
                    companion.setIsFingerPrintAuth(requireActivity6, "False");
                }
                displayDialog(false, true);
                return;
            }
            FingerprintManagerCompat from2 = FingerprintManagerCompat.from(requireActivity());
            Intrinsics.checkNotNullExpressionValue(from2, "from(requireActivity())");
            if (from2.isHardwareDetected()) {
            }
            FragmentActivity requireActivity7 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity7, "requireActivity()");
            companion.setIsFingerPrintAuth(requireActivity7, "False");
        } else {
            FingerprintManagerCompat from3 = FingerprintManagerCompat.from(requireActivity());
            Intrinsics.checkNotNullExpressionValue(from3, "from(requireActivity())");
            if (from3.isHardwareDetected()) {
            }
            FragmentActivity requireActivity72 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity72, "requireActivity()");
            companion.setIsFingerPrintAuth(requireActivity72, "False");
        }
        displayDialog(false, false);
    }

    private final void startCountDown() {
        new CountDownTimer() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.login.VerifyOtpFragment$startCountDown$timer$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(120000L, 1000L);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                FragmentVerifyOtpBinding fragmentVerifyOtpBinding;
                fragmentVerifyOtpBinding = VerifyOtpFragment.this.binding;
                if (fragmentVerifyOtpBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentVerifyOtpBinding = null;
                }
                fragmentVerifyOtpBinding.tvResendOtp.setVisibility(0);
            }

            @Override // android.os.CountDownTimer
            @SuppressLint({"SetTextI18n"})
            public void onTick(long j2) {
                FragmentVerifyOtpBinding fragmentVerifyOtpBinding;
                FragmentVerifyOtpBinding fragmentVerifyOtpBinding2;
                FragmentVerifyOtpBinding fragmentVerifyOtpBinding3;
                fragmentVerifyOtpBinding = VerifyOtpFragment.this.binding;
                FragmentVerifyOtpBinding fragmentVerifyOtpBinding4 = null;
                if (fragmentVerifyOtpBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentVerifyOtpBinding = null;
                }
                fragmentVerifyOtpBinding.tvOtpCounter.setVisibility(0);
                fragmentVerifyOtpBinding2 = VerifyOtpFragment.this.binding;
                if (fragmentVerifyOtpBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentVerifyOtpBinding2 = null;
                }
                fragmentVerifyOtpBinding2.tvResendOtp.setVisibility(8);
                DecimalFormat decimalFormat = new DecimalFormat("00");
                long j3 = 60;
                long j4 = (j2 / 60000) % j3;
                long j5 = (j2 / 1000) % j3;
                fragmentVerifyOtpBinding3 = VerifyOtpFragment.this.binding;
                if (fragmentVerifyOtpBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentVerifyOtpBinding4 = fragmentVerifyOtpBinding3;
                }
                fragmentVerifyOtpBinding4.tvOtpCounter.setText(decimalFormat.format(j4) + " : " + decimalFormat.format(j5));
            }
        }.start();
    }

    private final void verifyOTPClick() {
        String str = this.mPageMode;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPageMode");
            str = null;
        }
        switch (str.hashCode()) {
            case -2131583866:
                if (!str.equals(AppConstants.PAGE_MODE_CHANGE_PIN)) {
                    return;
                }
                break;
            case -2131583442:
                if (!str.equals(AppConstants.PAGE_MODE_CHANGE_PWD)) {
                    return;
                }
                break;
            case -1350309703:
                if (!str.equals(AppConstants.PAGE_MODE_REGISTRATION)) {
                    return;
                }
                break;
            case -1148260554:
                if (!str.equals(AppConstants.PAGE_MODE_ADD_CAR)) {
                    return;
                }
                break;
            case -305104263:
                if (!str.equals(AppConstants.PAGE_MODE_FORGOT_PIN)) {
                    return;
                }
                break;
            case -305103839:
                if (!str.equals(AppConstants.PAGE_MODE_FORGOT_PWD)) {
                    return;
                }
                break;
            case 103149417:
                if (!str.equals("login")) {
                    return;
                }
                break;
            default:
                return;
        }
        apiVerifyOTP();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment
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
    public final void getMessage(@NotNull ErrorResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        if (Intrinsics.areEqual(event.getApiName(), "FCMRegisterToken")) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            ExtensionsKt.showToast(requireContext, event.getMsg());
        } else if (event.getStatusCode() == 401) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            String string = getString(uat.psa.mym.mycitroenconnect.R.string.err_incorrect_otp);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.err_incorrect_otp)");
            ExtensionsKt.showToast(requireContext2, string);
        }
    }

    @Subscribe
    public final void getMessage(@NotNull FailResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, event.getMessage());
    }

    @Subscribe
    public final void getMessage(@NotNull PostCommonResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getStatusCode() == 200) {
            showOTPSuccessDialog();
            return;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, event.getMessage());
    }

    @Subscribe
    public final void getMessage(@NotNull SendOtpResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        this.sendOtpResponse = event;
        this.mFullHash = event.getFullHash();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, event.getMessage());
        startCountDown();
    }

    @Subscribe
    public final void getMessage(@NotNull UserProfileResponse event) {
        String str;
        List<RegisteredVehicleItem> registeredVehicle;
        Intrinsics.checkNotNullParameter(event, "event");
        this.userProfileResponse = event;
        String str2 = this.mPageMode;
        String str3 = null;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPageMode");
            str2 = null;
        }
        str = "N";
        if (!Intrinsics.areEqual(str2, AppConstants.PAGE_MODE_FORGOT_PIN)) {
            ArrayList arrayList = new ArrayList();
            if (event.getRegisteredVehicle() != null) {
                Intrinsics.checkNotNull(event.getRegisteredVehicle());
                if (!registeredVehicle.isEmpty()) {
                    List<RegisteredVehicleItem> registeredVehicle2 = event.getRegisteredVehicle();
                    Intrinsics.checkNotNull(registeredVehicle2);
                    for (RegisteredVehicleItem registeredVehicleItem : registeredVehicle2) {
                        String vinNum = registeredVehicleItem.getVinNum();
                        Intrinsics.checkNotNull(vinNum);
                        arrayList.add(vinNum);
                    }
                    registerFCMToken(arrayList, str);
                    return;
                }
            }
            str = "Y";
            registerFCMToken(arrayList, str);
            return;
        }
        AppUtil.Companion.dismissDialog();
        List<RegisteredVehicleItem> registeredVehicle3 = event.getRegisteredVehicle();
        Intrinsics.checkNotNull(registeredVehicle3);
        boolean z = !registeredVehicle3.isEmpty();
        str = z ? "N" : "Y";
        Intent intent = new Intent(requireActivity(), SetNewPinActivity.class);
        intent.putExtra("isGuest", str);
        String str4 = this.mobileNumber;
        if (str4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
            str4 = null;
        }
        intent.putExtra("mobileNumber", str4);
        String str5 = this.mPageMode;
        if (str5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPageMode");
        } else {
            str3 = str5;
        }
        intent.putExtra("mPageMode", str3);
        intent.putExtra("isRegisteredUser", z);
        startActivity(intent);
        requireActivity().finish();
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00de, code lost:
        if (r6 != false) goto L48;
     */
    @Subscribe
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void getMessage(@NotNull VerifyOTPResponse event) {
        Intent intent;
        SetNewPasswordFragment setNewPasswordFragment;
        boolean isBlank;
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion companion = AppUtil.Companion;
        companion.dismissDialog();
        String str = null;
        RegisterUserResponse registerUserResponse = null;
        String str2 = null;
        RegisterUserResponse registerUserResponse2 = null;
        if (!Intrinsics.areEqual(event.getSuccess(), "true")) {
            Animation loadAnimation = AnimationUtils.loadAnimation(requireContext(), uat.psa.mym.mycitroenconnect.R.anim.otp_shake_animation);
            Intrinsics.checkNotNullExpressionValue(loadAnimation, "loadAnimation(requireCon…anim.otp_shake_animation)");
            int i2 = R.id.edVerification;
            ((PinView) _$_findCachedViewById(i2)).startAnimation(loadAnimation);
            ((PinView) _$_findCachedViewById(i2)).setLineColor(getResources().getColorStateList(uat.psa.mym.mycitroenconnect.R.color.dark_red, null));
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(uat.psa.mym.mycitroenconnect.R.string.err_incorrect_otp);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.err_incorrect_otp)");
            ExtensionsKt.showToast(requireContext, string);
            return;
        }
        String str3 = this.mPageMode;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPageMode");
            str3 = null;
        }
        boolean z = true;
        switch (str3.hashCode()) {
            case -2131583866:
                if (str3.equals(AppConstants.PAGE_MODE_CHANGE_PIN)) {
                    intent = new Intent(requireActivity(), SetNewPinActivity.class);
                    String str4 = this.mPageMode;
                    if (str4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mPageMode");
                    } else {
                        str = str4;
                    }
                    intent.putExtra("mPageMode", str);
                    intent.putExtra("isRegisteredUser", true);
                    intent.putExtra(AppConstants.ARG_IS_FROM_MAIN_SCREEN, this.isFromMainScreen);
                    startActivity(intent);
                    return;
                }
                return;
            case -2131583442:
                if (str3.equals(AppConstants.PAGE_MODE_CHANGE_PWD)) {
                    displayOtpSuccessDialog(true);
                    return;
                }
                return;
            case -1350309703:
                if (str3.equals(AppConstants.PAGE_MODE_REGISTRATION)) {
                    companion.dismissDialog();
                    SharedPref.Companion companion2 = SharedPref.Companion;
                    FragmentActivity requireActivity = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                    companion2.setPrimaryUserToken(requireActivity, event.getTokenHash());
                    RegisterUserResponse registerUserResponse3 = this.registerUserResponse;
                    if (registerUserResponse3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("registerUserResponse");
                        registerUserResponse3 = null;
                    }
                    if (Intrinsics.areEqual(registerUserResponse3.isGuestUser(), "Y")) {
                        setNewPasswordFragment = new SetNewPasswordFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("login", AppConstants.PAGE_MODE_REGISTRATION);
                        RegisterUserResponse registerUserResponse4 = this.registerUserResponse;
                        if (registerUserResponse4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("registerUserResponse");
                        } else {
                            registerUserResponse2 = registerUserResponse4;
                        }
                        bundle.putParcelable(AppConstants.REGUSER, registerUserResponse2);
                        bundle.putString(AppConstants.TOKEN_HASH, event.getTokenHash());
                        setNewPasswordFragment.setArguments(bundle);
                        FragmentManager supportFragmentManager = requireActivity().getSupportFragmentManager();
                        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "requireActivity().supportFragmentManager");
                        companion.replaceFragment(supportFragmentManager, setNewPasswordFragment);
                        return;
                    }
                    apiAuthenticateUser(event);
                    return;
                }
                return;
            case -1148260554:
                if (str3.equals(AppConstants.PAGE_MODE_ADD_CAR)) {
                    companion.dismissDialog();
                    SharedPref.Companion companion3 = SharedPref.Companion;
                    Context requireContext2 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    String vinNumber = companion3.getVinNumber(requireContext2);
                    if (vinNumber != null && vinNumber.length() != 0) {
                        z = false;
                    }
                    if (!z) {
                        Context requireContext3 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                        isBlank = StringsKt__StringsJVMKt.isBlank(companion3.getVinNumber(requireContext3));
                        break;
                    }
                    Context requireContext4 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                    String str5 = this.vinNum;
                    if (str5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("vinNum");
                        str5 = null;
                    }
                    companion3.setVinNumber(requireContext4, str5);
                    intent = new Intent(requireContext(), ConfirmCarDetailsActivity.class);
                    String str6 = this.vinNum;
                    if (str6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("vinNum");
                    } else {
                        str2 = str6;
                    }
                    intent.putExtra(AppConstants.ADD_CAR_BUNDLE_NAME, str2);
                    intent.putExtra("login", AppConstants.PAGE_MODE_ADD_CAR);
                    startActivity(intent);
                    return;
                }
                return;
            case -305104263:
                if (!str3.equals(AppConstants.PAGE_MODE_FORGOT_PIN)) {
                    return;
                }
                apiAuthenticateUser(event);
                return;
            case -305103839:
                if (str3.equals(AppConstants.PAGE_MODE_FORGOT_PWD)) {
                    setNewPasswordFragment = new SetNewPasswordFragment();
                    Bundle bundle2 = new Bundle();
                    RegisterUserResponse registerUserResponse5 = new RegisterUserResponse(null, null, null, null, null, 31, null);
                    registerUserResponse5.setMobileNum(this.sendOtpResponse.getMobileNum());
                    this.registerUserResponse = registerUserResponse5;
                    String str7 = this.mPageMode;
                    if (str7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mPageMode");
                        str7 = null;
                    }
                    bundle2.putString("login", str7);
                    RegisterUserResponse registerUserResponse6 = this.registerUserResponse;
                    if (registerUserResponse6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("registerUserResponse");
                    } else {
                        registerUserResponse = registerUserResponse6;
                    }
                    bundle2.putParcelable(AppConstants.REGUSER, registerUserResponse);
                    bundle2.putString(AppConstants.TOKEN_HASH, event.getTokenHash());
                    setNewPasswordFragment.setArguments(bundle2);
                    FragmentManager supportFragmentManager2 = requireActivity().getSupportFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(supportFragmentManager2, "requireActivity().supportFragmentManager");
                    companion.replaceFragment(supportFragmentManager2, setNewPasswordFragment);
                    return;
                }
                return;
            case 103149417:
                if (!str3.equals("login")) {
                    return;
                }
                apiAuthenticateUser(event);
                return;
            default:
                return;
        }
    }

    @Subscribe
    public final void getResponse(@NotNull MyCarResponse myCarResponse) {
        String str;
        Intrinsics.checkNotNullParameter(myCarResponse, "myCarResponse");
        AppUtil.Companion.dismissDialog();
        MyCars myCars = myCarResponse.getMyCars();
        String str2 = null;
        if (!(myCars == null || myCars.isEmpty())) {
            if ((!myCars.isEmpty()) && myCars.size() > 0) {
                MyCar myCar = myCars.get(0);
                Intrinsics.checkNotNullExpressionValue(myCar, "response[0]");
                MyCar myCar2 = myCar;
                String userType = myCar2.getUserType();
                if (userType != null) {
                    str = userType.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                } else {
                    str = null;
                }
                if (Intrinsics.areEqual(str, "g")) {
                    Token token = myCar2.getToken();
                    if (token != null) {
                        SharedPref.Companion companion = SharedPref.Companion;
                        FragmentActivity requireActivity = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                        companion.setSecondaryUserTokenDetails(requireActivity, token);
                    }
                    myCars.remove(0);
                }
            }
            SharedPref.Companion companion2 = SharedPref.Companion;
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            companion2.setVinTokenDetails(requireActivity2, myCars);
        }
        isRegisteredUser(myCars);
        OnBoardingService onBoardingService = new OnBoardingService();
        FragmentActivity requireActivity3 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
        String str3 = this.mobileNumber;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
            str3 = null;
        }
        String str4 = this.mobileNumber;
        if (str4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
        } else {
            str2 = str4;
        }
        String substring = str3.substring(1, str2.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        onBoardingService.callGetUserProfileAPI(requireActivity3, substring);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        TextInputLayout textInputLayout;
        int i2;
        FragmentVerifyOtpBinding fragmentVerifyOtpBinding = this.binding;
        FragmentVerifyOtpBinding fragmentVerifyOtpBinding2 = null;
        if (fragmentVerifyOtpBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVerifyOtpBinding = null;
        }
        if (Intrinsics.areEqual(view, fragmentVerifyOtpBinding.btnVerify)) {
            int i3 = R.id.edVerification;
            if (String.valueOf(((PinView) _$_findCachedViewById(i3)).getText()).length() == 0) {
                ((PinView) _$_findCachedViewById(i3)).requestFocus();
                ((PinView) _$_findCachedViewById(i3)).setLineColor(requireContext().getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
                ((PinView) _$_findCachedViewById(i3)).setTextColor(requireContext().getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
                textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtVerification);
                i2 = uat.psa.mym.mycitroenconnect.R.string.err_enter_otp;
            } else if (String.valueOf(((PinView) _$_findCachedViewById(i3)).getText()).length() == 4) {
                AppUtil.Companion companion = AppUtil.Companion;
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                companion.hideKeyboard(requireActivity);
                verifyOTPClick();
                return;
            } else {
                ((PinView) _$_findCachedViewById(i3)).requestFocus();
                ((PinView) _$_findCachedViewById(i3)).setLineColor(requireContext().getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
                ((PinView) _$_findCachedViewById(i3)).setTextColor(requireContext().getColor(uat.psa.mym.mycitroenconnect.R.color.dark_red));
                textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtVerification);
                i2 = uat.psa.mym.mycitroenconnect.R.string.err_otp_length_4;
            }
            textInputLayout.setError(getString(i2));
            return;
        }
        FragmentVerifyOtpBinding fragmentVerifyOtpBinding3 = this.binding;
        if (fragmentVerifyOtpBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVerifyOtpBinding3 = null;
        }
        if (Intrinsics.areEqual(view, fragmentVerifyOtpBinding3.btnBack)) {
            AppUtil.Companion companion2 = AppUtil.Companion;
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            companion2.hideKeyboard(requireActivity2);
            requireActivity().onBackPressed();
            return;
        }
        FragmentVerifyOtpBinding fragmentVerifyOtpBinding4 = this.binding;
        if (fragmentVerifyOtpBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentVerifyOtpBinding2 = fragmentVerifyOtpBinding4;
        }
        if (Intrinsics.areEqual(view, fragmentVerifyOtpBinding2.tvResendOtp)) {
            AppUtil.Companion companion3 = AppUtil.Companion;
            FragmentActivity requireActivity3 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
            companion3.hideKeyboard(requireActivity3);
            apiSendOtp();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        String fullHash;
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        Intrinsics.checkNotNull(arguments);
        String string = arguments.getString("login", "");
        Intrinsics.checkNotNullExpressionValue(string, "it!!.getString(AppConstants.PAGE_MODE, \"\")");
        this.mPageMode = string;
        ChangePasswordResponse changePasswordResponse = null;
        RegisterUserResponse registerUserResponse = null;
        if (string == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPageMode");
            string = null;
        }
        switch (string.hashCode()) {
            case -2131583866:
                if (string.equals(AppConstants.PAGE_MODE_CHANGE_PIN)) {
                    SharedPref.Companion companion = SharedPref.Companion;
                    FragmentActivity requireActivity = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                    this.mobileNumber = companion.getMobileNumber(requireActivity);
                    this.isFromMainScreen = Boolean.valueOf(arguments.getBoolean(AppConstants.ARG_IS_FROM_MAIN_SCREEN, true));
                    return;
                }
                return;
            case -2131583442:
                if (string.equals(AppConstants.PAGE_MODE_CHANGE_PWD)) {
                    String string2 = arguments.getString(AppConstants.MOBILENUMBER, "");
                    Intrinsics.checkNotNullExpressionValue(string2, "it.getString(AppConstants.MOBILENUMBER, \"\")");
                    this.mobileNumber = string2;
                    Parcelable parcelable = arguments.getParcelable(AppConstants.SENDOTPRESPONSE);
                    Intrinsics.checkNotNull(parcelable);
                    ChangePasswordResponse changePasswordResponse2 = (ChangePasswordResponse) parcelable;
                    this.changePasswordResponse = changePasswordResponse2;
                    if (changePasswordResponse2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("changePasswordResponse");
                    } else {
                        changePasswordResponse = changePasswordResponse2;
                    }
                    fullHash = changePasswordResponse.getFullHash();
                    this.mFullHash = fullHash;
                    return;
                }
                return;
            case -1350309703:
                if (string.equals(AppConstants.PAGE_MODE_REGISTRATION)) {
                    Parcelable parcelable2 = arguments.getParcelable(AppConstants.REGUSER);
                    Intrinsics.checkNotNull(parcelable2);
                    RegisterUserResponse registerUserResponse2 = (RegisterUserResponse) parcelable2;
                    this.registerUserResponse = registerUserResponse2;
                    if (registerUserResponse2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("registerUserResponse");
                        registerUserResponse2 = null;
                    }
                    String mobileNum = registerUserResponse2.getMobileNum();
                    Intrinsics.checkNotNull(mobileNum);
                    this.mobileNumber = mobileNum;
                    RegisterUserResponse registerUserResponse3 = this.registerUserResponse;
                    if (registerUserResponse3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("registerUserResponse");
                    } else {
                        registerUserResponse = registerUserResponse3;
                    }
                    fullHash = registerUserResponse.getFullHash();
                    Intrinsics.checkNotNull(fullHash);
                    this.mFullHash = fullHash;
                    return;
                }
                return;
            case -1148260554:
                if (string.equals(AppConstants.PAGE_MODE_ADD_CAR)) {
                    SharedPref.Companion companion2 = SharedPref.Companion;
                    FragmentActivity requireActivity2 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                    this.mobileNumber = companion2.getMobileNumber(requireActivity2);
                    this.vinNum = String.valueOf(arguments.get(AppConstants.ADD_CAR_BUNDLE_NAME));
                    return;
                }
                return;
            case -305104263:
                if (string.equals(AppConstants.PAGE_MODE_FORGOT_PIN)) {
                    String string3 = arguments.getString(AppConstants.MOBILENUMBER, "");
                    Intrinsics.checkNotNullExpressionValue(string3, "it.getString(AppConstants.MOBILENUMBER, \"\")");
                    this.mobileNumber = string3;
                    this.appPin = "";
                    return;
                }
                return;
            case -305103839:
                if (string.equals(AppConstants.PAGE_MODE_FORGOT_PWD)) {
                    String string4 = arguments.getString(AppConstants.MOBILENUMBER, "");
                    Intrinsics.checkNotNullExpressionValue(string4, "it.getString(AppConstants.MOBILENUMBER, \"\")");
                    this.mobileNumber = string4;
                    Parcelable parcelable3 = arguments.getParcelable(AppConstants.SENDOTPRESPONSE);
                    Intrinsics.checkNotNull(parcelable3);
                    SendOtpResponse sendOtpResponse = (SendOtpResponse) parcelable3;
                    this.sendOtpResponse = sendOtpResponse;
                    this.mFullHash = sendOtpResponse.getFullHash();
                    this.appPin = "";
                    return;
                }
                return;
            case 103149417:
                if (string.equals("login")) {
                    String string5 = arguments.getString(AppConstants.MOBILENUMBER, "");
                    Intrinsics.checkNotNullExpressionValue(string5, "it.getString(AppConstants.MOBILENUMBER, \"\")");
                    this.mobileNumber = string5;
                    String string6 = arguments.getString(AppConstants.FULLHASH, "");
                    Intrinsics.checkNotNullExpressionValue(string6, "it.getString(AppConstants.FULLHASH, \"\")");
                    this.mFullHash = string6;
                    this.appPin = arguments.getString("AppPin", "");
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentVerifyOtpBinding inflate = FragmentVerifyOtpBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.binding = inflate;
        setListeners();
        FragmentVerifyOtpBinding fragmentVerifyOtpBinding = this.binding;
        FragmentVerifyOtpBinding fragmentVerifyOtpBinding2 = null;
        if (fragmentVerifyOtpBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVerifyOtpBinding = null;
        }
        AppCompatTextView appCompatTextView = fragmentVerifyOtpBinding.tvVerifyOtpMsg;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(uat.psa.mym.mycitroenconnect.R.string.label_otp_verification_msg);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.label_otp_verification_msg)");
        Object[] objArr = new Object[1];
        String str = this.mobileNumber;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
            str = null;
        }
        String str2 = this.mobileNumber;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
            str2 = null;
        }
        String substring = str.substring(9, str2.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        objArr[0] = substring;
        String format = String.format(string, Arrays.copyOf(objArr, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        appCompatTextView.setText(format);
        startCountDown();
        FragmentVerifyOtpBinding fragmentVerifyOtpBinding3 = this.binding;
        if (fragmentVerifyOtpBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentVerifyOtpBinding2 = fragmentVerifyOtpBinding3;
        }
        ConstraintLayout root = fragmentVerifyOtpBinding2.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        initView();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        AppUtil.Companion.dismissDialog();
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
        int i5 = R.id.txtVerification;
        if (((TextInputLayout) _$_findCachedViewById(i5)).getError() != null) {
            ((TextInputLayout) _$_findCachedViewById(i5)).setError(null);
        }
        int i6 = R.id.edVerification;
        ((PinView) _$_findCachedViewById(i6)).setLineColor(requireContext().getColor(uat.psa.mym.mycitroenconnect.R.color.dark_grey));
        ((PinView) _$_findCachedViewById(i6)).setTextColor(requireContext().getColor(uat.psa.mym.mycitroenconnect.R.color.black));
    }
}
