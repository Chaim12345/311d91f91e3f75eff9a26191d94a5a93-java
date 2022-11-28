package com.psa.mym.mycitroenconnect.controller.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.psa.mym.mycitroenconnect.BusBaseFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.api.body.onboarding.RegisterUserBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.onboarding.LoginActivity;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ContactSupportDialog;
import com.psa.mym.mycitroenconnect.controller.fragments.login.VerifyOtpFragment;
import com.psa.mym.mycitroenconnect.databinding.FragmentRegistrationBinding;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisterErrorResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisterUserResponse;
import com.psa.mym.mycitroenconnect.services.OnBoardingService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class RegistrationFragment extends BusBaseFragment implements View.OnClickListener, TextWatcher {
    private FragmentRegistrationBinding binding;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private RegisterUserResponse registerUserResponse = new RegisterUserResponse(null, null, null, null, null, 31, null);

    private final void apiRegisterUser() {
        TextInputLayout textInputLayout;
        int i2;
        boolean isBlank;
        String valueOf = String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edMobileNumber)).getText());
        if (valueOf.length() == 0) {
            isBlank = StringsKt__StringsJVMKt.isBlank(valueOf);
            if (isBlank) {
                textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutMobileNumber);
                i2 = uat.psa.mym.mycitroenconnect.R.string.please_enter_mobile_number;
                textInputLayout.setError(getString(i2));
                return;
            }
        }
        AppUtil.Companion companion = AppUtil.Companion;
        if (!companion.isMobileNumberValidNew(valueOf)) {
            textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutMobileNumber);
            i2 = uat.psa.mym.mycitroenconnect.R.string.enter_valid_mobile;
            textInputLayout.setError(getString(i2));
            return;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.showDialog(requireContext);
        RegisterUserBody registerUserBody = new RegisterUserBody();
        registerUserBody.setMobileNum(valueOf);
        registerUserBody.setCountryCode("+91");
        OnBoardingService onBoardingService = new OnBoardingService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        onBoardingService.callUserRegisterAPI(requireActivity, registerUserBody);
    }

    private final void goToLogin() {
        Intent intent = new Intent(requireActivity(), LoginActivity.class);
        intent.putExtra("login", "login");
        startActivity(intent);
        requireActivity().finish();
    }

    private final void setListeners() {
        FragmentRegistrationBinding fragmentRegistrationBinding = this.binding;
        FragmentRegistrationBinding fragmentRegistrationBinding2 = null;
        if (fragmentRegistrationBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentRegistrationBinding = null;
        }
        fragmentRegistrationBinding.edMobileNumber.addTextChangedListener(this);
        FragmentRegistrationBinding fragmentRegistrationBinding3 = this.binding;
        if (fragmentRegistrationBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentRegistrationBinding3 = null;
        }
        fragmentRegistrationBinding3.btnNext.setOnClickListener(this);
        FragmentRegistrationBinding fragmentRegistrationBinding4 = this.binding;
        if (fragmentRegistrationBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentRegistrationBinding4 = null;
        }
        fragmentRegistrationBinding4.layoutLoginNow.setOnClickListener(this);
        FragmentRegistrationBinding fragmentRegistrationBinding5 = this.binding;
        if (fragmentRegistrationBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentRegistrationBinding2 = fragmentRegistrationBinding5;
        }
        fragmentRegistrationBinding2.layoutHavingTroubleView.setOnClickListener(this);
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
    public final void getMessage(@NotNull RegisterErrorResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        ExtensionsKt.showToast(requireActivity, event.getMessage());
    }

    @Subscribe
    public final void getMessage(@NotNull RegisterUserResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion companion = AppUtil.Companion;
        companion.dismissDialog();
        this.registerUserResponse = event;
        VerifyOtpFragment verifyOtpFragment = new VerifyOtpFragment();
        Bundle bundle = new Bundle();
        bundle.putString("login", AppConstants.PAGE_MODE_REGISTRATION);
        bundle.putParcelable(AppConstants.REGUSER, this.registerUserResponse);
        verifyOtpFragment.setArguments(bundle);
        FragmentManager supportFragmentManager = requireActivity().getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "requireActivity().supportFragmentManager");
        companion.replaceFragment(supportFragmentManager, verifyOtpFragment);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        Intrinsics.checkNotNull(view);
        int id = view.getId();
        if (id == uat.psa.mym.mycitroenconnect.R.id.btnNext) {
            apiRegisterUser();
        } else if (id == uat.psa.mym.mycitroenconnect.R.id.layoutHavingTroubleView) {
            showContactSupportDialog();
        } else if (id != uat.psa.mym.mycitroenconnect.R.id.layoutLoginNow) {
        } else {
            goToLogin();
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
        FragmentRegistrationBinding inflate = FragmentRegistrationBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.binding = inflate;
        setListeners();
        FragmentRegistrationBinding fragmentRegistrationBinding = this.binding;
        if (fragmentRegistrationBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentRegistrationBinding = null;
        }
        ConstraintLayout root = fragmentRegistrationBinding.getRoot();
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
