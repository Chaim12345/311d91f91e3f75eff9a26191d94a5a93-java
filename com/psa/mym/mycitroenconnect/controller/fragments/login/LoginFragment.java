package com.psa.mym.mycitroenconnect.controller.fragments.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.psa.mym.mycitroenconnect.BusBaseFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.api.body.onboarding.GetTokenBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.LoginBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.onboarding.ForgotPinActivity;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ContactSupportDialog;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OtpSuccessBottomSheetFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.RegistrationFragment;
import com.psa.mym.mycitroenconnect.databinding.FragmentLoginBinding;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.FailResponse;
import com.psa.mym.mycitroenconnect.model.Token;
import com.psa.mym.mycitroenconnect.model.ValidationErrorResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.LoginResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import com.psa.mym.mycitroenconnect.model.onboarding.UserProfileResponse;
import com.psa.mym.mycitroenconnect.services.OnBoardingService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class LoginFragment extends BusBaseFragment implements View.OnClickListener, TextWatcher {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String appPin;
    private FragmentLoginBinding binding;
    private String mFullHash;
    private String mobileNumber;

    private final void apiAuthenticateUser() {
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.showDialog(requireContext);
        GetTokenBody getTokenBody = new GetTokenBody(null, null, null, null, 15, null);
        String str = this.mobileNumber;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
            str = null;
        }
        getTokenBody.setUserName(str);
        getTokenBody.setCountryCode("+91");
        OnBoardingService onBoardingService = new OnBoardingService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        OnBoardingService.callGetUserTokenAPI$default(onBoardingService, requireActivity, getTokenBody, false, 4, null);
    }

    private final void apiLogin(String str, String str2) {
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.showDialog(requireContext);
        LoginBody loginBody = new LoginBody(str, "+91", str2);
        OnBoardingService onBoardingService = new OnBoardingService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        onBoardingService.callLoginUserAPI(requireActivity, loginBody);
    }

    private final void displayDialog(boolean z, boolean z2) {
        OtpSuccessBottomSheetFragment newInstance = OtpSuccessBottomSheetFragment.Companion.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("mPageMode", "login");
        bundle.putBoolean("isBio", z);
        bundle.putBoolean("isRegisteredUser", z2);
        newInstance.setArguments(bundle);
        newInstance.show(requireActivity().getSupportFragmentManager(), OtpSuccessBottomSheetFragment.TAG);
        newInstance.setCancelable(false);
    }

    private final void goToForgetPin() {
        startActivity(new Intent(requireActivity(), ForgotPinActivity.class));
    }

    private final void goToRegistration() {
        AppUtil.Companion companion = AppUtil.Companion;
        FragmentManager supportFragmentManager = requireActivity().getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "requireActivity().supportFragmentManager");
        companion.replaceFragment(supportFragmentManager, new RegistrationFragment());
    }

    private final void goToVerifyOTP() {
        boolean startsWith$default;
        String str = this.mobileNumber;
        FragmentLoginBinding fragmentLoginBinding = null;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
            str = null;
        }
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(str, "+91", false, 2, null);
        if (!startsWith$default) {
            StringBuilder sb = new StringBuilder();
            sb.append("+91");
            String str2 = this.mobileNumber;
            if (str2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
                str2 = null;
            }
            sb.append(str2);
            this.mobileNumber = sb.toString();
        }
        VerifyOtpFragment verifyOtpFragment = new VerifyOtpFragment();
        Bundle bundle = new Bundle();
        bundle.putString("login", "login");
        String str3 = this.mobileNumber;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
            str3 = null;
        }
        bundle.putString(AppConstants.MOBILENUMBER, str3);
        String str4 = this.mFullHash;
        if (str4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFullHash");
            str4 = null;
        }
        bundle.putString(AppConstants.FULLHASH, str4);
        FragmentLoginBinding fragmentLoginBinding2 = this.binding;
        if (fragmentLoginBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentLoginBinding = fragmentLoginBinding2;
        }
        bundle.putString("AppPin", String.valueOf(fragmentLoginBinding.edVerification.getText()));
        verifyOtpFragment.setArguments(bundle);
        AppUtil.Companion companion = AppUtil.Companion;
        FragmentManager supportFragmentManager = requireActivity().getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "requireActivity().supportFragmentManager");
        companion.replaceFragment(supportFragmentManager, verifyOtpFragment);
    }

    private final void loginUser() {
        CharSequence trim;
        CharSequence trim2;
        CharSequence trim3;
        TextInputLayout textInputLayout;
        int i2;
        boolean isBlank;
        int i3 = R.id.edMobileNumber;
        String valueOf = String.valueOf(((TextInputEditText) _$_findCachedViewById(i3)).getText());
        this.mobileNumber = valueOf;
        String str = null;
        if (valueOf.length() == 0) {
            String str2 = this.mobileNumber;
            if (str2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
                str2 = null;
            }
            isBlank = StringsKt__StringsJVMKt.isBlank(str2);
            if (isBlank) {
                textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutMobileNumber);
                i2 = uat.psa.mym.mycitroenconnect.R.string.please_enter_mobile_number;
                textInputLayout.setError(getString(i2));
            }
        }
        AppUtil.Companion companion = AppUtil.Companion;
        String str3 = this.mobileNumber;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
        } else {
            str = str3;
        }
        if (companion.isMobileNumberValidNew(str)) {
            int i4 = R.id.edPassword;
            trim = StringsKt__StringsKt.trim((CharSequence) String.valueOf(((TextInputEditText) _$_findCachedViewById(i4)).getText()));
            if (!(trim.toString().length() == 0)) {
                trim2 = StringsKt__StringsKt.trim((CharSequence) String.valueOf(((TextInputEditText) _$_findCachedViewById(i3)).getText()));
                String obj = trim2.toString();
                trim3 = StringsKt__StringsKt.trim((CharSequence) String.valueOf(((TextInputEditText) _$_findCachedViewById(i4)).getText()));
                apiLogin(obj, trim3.toString());
                return;
            }
            textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutPassword);
            i2 = uat.psa.mym.mycitroenconnect.R.string.enter_password;
        } else {
            textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutMobileNumber);
            i2 = uat.psa.mym.mycitroenconnect.R.string.enter_valid_mobile;
        }
        textInputLayout.setError(getString(i2));
    }

    private final void setListeners() {
        FragmentLoginBinding fragmentLoginBinding = this.binding;
        FragmentLoginBinding fragmentLoginBinding2 = null;
        if (fragmentLoginBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLoginBinding = null;
        }
        fragmentLoginBinding.edMobileNumber.addTextChangedListener(this);
        FragmentLoginBinding fragmentLoginBinding3 = this.binding;
        if (fragmentLoginBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLoginBinding3 = null;
        }
        fragmentLoginBinding3.layoutHavingTroubleView.setOnClickListener(this);
        FragmentLoginBinding fragmentLoginBinding4 = this.binding;
        if (fragmentLoginBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLoginBinding4 = null;
        }
        fragmentLoginBinding4.forgotPin.setOnClickListener(this);
        FragmentLoginBinding fragmentLoginBinding5 = this.binding;
        if (fragmentLoginBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLoginBinding5 = null;
        }
        fragmentLoginBinding5.btnLogin.setOnClickListener(this);
        FragmentLoginBinding fragmentLoginBinding6 = this.binding;
        if (fragmentLoginBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLoginBinding6 = null;
        }
        fragmentLoginBinding6.tvRegisterNow.setOnClickListener(this);
        FragmentLoginBinding fragmentLoginBinding7 = this.binding;
        if (fragmentLoginBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentLoginBinding2 = fragmentLoginBinding7;
        }
        fragmentLoginBinding2.tvForgotPassword.setOnClickListener(this);
    }

    private final void showContactSupportDialog() {
        ContactSupportDialog contactSupportDialog = new ContactSupportDialog();
        contactSupportDialog.show(requireActivity().getSupportFragmentManager(), ContactSupportDialog.TAG);
        contactSupportDialog.setCancelable(false);
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
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, event.getMsg());
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
    public final void getMessage(@NotNull Token event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        SharedPref.Companion companion = SharedPref.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String str = this.mobileNumber;
        String str2 = null;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mobileNumber");
            str = null;
        }
        companion.setMobileNumber(requireActivity, str);
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        String str3 = this.appPin;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appPin");
        } else {
            str2 = str3;
        }
        companion.setAppPin(requireActivity2, str2);
        FragmentActivity requireActivity3 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
        companion.setPrimaryUserTokenDetails(requireActivity3, event);
        goToVerifyOTP();
    }

    @Subscribe
    public final void getMessage(@NotNull ValidationErrorResponse event) {
        String replace$default;
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        replace$default = StringsKt__StringsJVMKt.replace$default(event.getErrorList().get(0), "Unresolved key: ", "", false, 4, (Object) null);
        ExtensionsKt.showToast(requireContext, replace$default);
    }

    @Subscribe
    public final void getMessage(@NotNull LoginResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        this.mobileNumber = event.getMobileNum();
        this.mFullHash = event.getFullhash();
        goToVerifyOTP();
    }

    @Subscribe
    public final void getMessage(@NotNull UserProfileResponse event) {
        RegisteredVehicleItem registeredVehicleItem;
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        SharedPref.Companion companion = SharedPref.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        companion.setUserProfileResponse(requireActivity, event);
        String fullName = event.getFullName();
        if (fullName != null) {
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            companion.setUserFirstName(requireActivity2, fullName);
        }
        FragmentActivity requireActivity3 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
        companion.setIsLogin(requireActivity3, "true");
        List<RegisteredVehicleItem> registeredVehicle = event.getRegisteredVehicle();
        Intrinsics.checkNotNull(registeredVehicle);
        if (!(!registeredVehicle.isEmpty())) {
            FingerprintManagerCompat from = FingerprintManagerCompat.from(requireActivity());
            Intrinsics.checkNotNullExpressionValue(from, "from(requireActivity())");
            if (from.isHardwareDetected() && from.hasEnrolledFingerprints()) {
                displayDialog(true, false);
                return;
            }
            FragmentActivity requireActivity4 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
            companion.setIsFingerPrintAuth(requireActivity4, "False");
            displayDialog(false, false);
            return;
        }
        List<RegisteredVehicleItem> registeredVehicle2 = event.getRegisteredVehicle();
        if (registeredVehicle2 != null && (registeredVehicleItem = registeredVehicle2.get(0)) != null) {
            String vinNum = registeredVehicleItem.getVinNum();
            if (vinNum != null) {
                FragmentActivity requireActivity5 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity5, "requireActivity()");
                companion.setVinNumber(requireActivity5, vinNum);
            }
            FragmentActivity requireActivity6 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity6, "requireActivity()");
            companion.setVehicleType(requireActivity6, registeredVehicleItem.getVehicleType());
            String vehicleNumber = registeredVehicleItem.getVehicleNumber();
            if (vehicleNumber != null) {
                FragmentActivity requireActivity7 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity7, "requireActivity()");
                companion.setVehicleNumber(requireActivity7, vehicleNumber);
            }
        }
        FingerprintManagerCompat from2 = FingerprintManagerCompat.from(requireActivity());
        Intrinsics.checkNotNullExpressionValue(from2, "from(requireActivity())");
        if (from2.isHardwareDetected() && from2.hasEnrolledFingerprints()) {
            displayDialog(true, true);
            return;
        }
        FragmentActivity requireActivity8 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity8, "requireActivity()");
        companion.setIsFingerPrintAuth(requireActivity8, "False");
        displayDialog(false, true);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        FragmentLoginBinding fragmentLoginBinding = this.binding;
        FragmentLoginBinding fragmentLoginBinding2 = null;
        if (fragmentLoginBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLoginBinding = null;
        }
        if (Intrinsics.areEqual(view, fragmentLoginBinding.forgotPin)) {
            goToForgetPin();
            return;
        }
        FragmentLoginBinding fragmentLoginBinding3 = this.binding;
        if (fragmentLoginBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLoginBinding3 = null;
        }
        if (Intrinsics.areEqual(view, fragmentLoginBinding3.tvRegisterNow)) {
            goToRegistration();
            return;
        }
        FragmentLoginBinding fragmentLoginBinding4 = this.binding;
        if (fragmentLoginBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLoginBinding4 = null;
        }
        if (Intrinsics.areEqual(view, fragmentLoginBinding4.btnLogin)) {
            loginUser();
            return;
        }
        FragmentLoginBinding fragmentLoginBinding5 = this.binding;
        if (fragmentLoginBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLoginBinding5 = null;
        }
        if (Intrinsics.areEqual(view, fragmentLoginBinding5.layoutHavingTroubleView)) {
            showContactSupportDialog();
            return;
        }
        FragmentLoginBinding fragmentLoginBinding6 = this.binding;
        if (fragmentLoginBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentLoginBinding2 = fragmentLoginBinding6;
        }
        if (Intrinsics.areEqual(view, fragmentLoginBinding2.tvForgotPassword)) {
            AppUtil.Companion companion = AppUtil.Companion;
            FragmentManager supportFragmentManager = requireActivity().getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "requireActivity().supportFragmentManager");
            companion.replaceFragment(supportFragmentManager, new ForgotPasswordFragment());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentLoginBinding inflate = FragmentLoginBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.binding = inflate;
        setListeners();
        FragmentLoginBinding fragmentLoginBinding = this.binding;
        FragmentLoginBinding fragmentLoginBinding2 = null;
        if (fragmentLoginBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentLoginBinding = null;
        }
        fragmentLoginBinding.getRoot().setFilterTouchesWhenObscured(true);
        FragmentLoginBinding fragmentLoginBinding3 = this.binding;
        if (fragmentLoginBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentLoginBinding2 = fragmentLoginBinding3;
        }
        ConstraintLayout root = fragmentLoginBinding2.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
        int i5 = R.id.txtLayoutMobileNumber;
        if (((TextInputLayout) _$_findCachedViewById(i5)).getError() != null) {
            ((TextInputLayout) _$_findCachedViewById(i5)).setError(null);
        }
    }
}
