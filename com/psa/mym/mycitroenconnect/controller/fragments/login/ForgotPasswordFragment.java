package com.psa.mym.mycitroenconnect.controller.fragments.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.textfield.TextInputEditText;
import com.psa.mym.mycitroenconnect.BusBaseFragment;
import com.psa.mym.mycitroenconnect.api.body.onboarding.RegisterUserBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.onboarding.LoginActivity;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ContactSupportDialog;
import com.psa.mym.mycitroenconnect.databinding.FragmentForgotPasswordBinding;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisterErrorResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.SendOtpResponse;
import com.psa.mym.mycitroenconnect.services.OnBoardingService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ForgotPasswordFragment extends BusBaseFragment implements View.OnClickListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private FragmentForgotPasswordBinding binding;

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

    @Subscribe
    public final void getMessage(@NotNull RegisterErrorResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, event.getMessage());
    }

    @Subscribe
    public final void getMessage(@NotNull SendOtpResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion companion = AppUtil.Companion;
        companion.dismissDialog();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, event.getMessage());
        if (event.getStatusCode() == 200) {
            VerifyOtpFragment verifyOtpFragment = new VerifyOtpFragment();
            Bundle bundle = new Bundle();
            bundle.putString("login", AppConstants.PAGE_MODE_FORGOT_PWD);
            bundle.putString(AppConstants.MOBILENUMBER, event.getUsername());
            bundle.putParcelable(AppConstants.SENDOTPRESPONSE, event);
            verifyOtpFragment.setArguments(bundle);
            FragmentManager supportFragmentManager = requireActivity().getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "requireActivity().supportFragmentManager");
            companion.replaceFragment(supportFragmentManager, verifyOtpFragment);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        FragmentForgotPasswordBinding fragmentForgotPasswordBinding = this.binding;
        FragmentForgotPasswordBinding fragmentForgotPasswordBinding2 = null;
        if (fragmentForgotPasswordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentForgotPasswordBinding = null;
        }
        if (!Intrinsics.areEqual(view, fragmentForgotPasswordBinding.btnForgotPassword)) {
            FragmentForgotPasswordBinding fragmentForgotPasswordBinding3 = this.binding;
            if (fragmentForgotPasswordBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentForgotPasswordBinding3 = null;
            }
            if (Intrinsics.areEqual(view, fragmentForgotPasswordBinding3.tvRegisterNow)) {
                Intent intent = new Intent(requireActivity(), LoginActivity.class);
                intent.putExtra("login", AppConstants.PAGE_MODE_REGISTRATION);
                startActivity(intent);
                requireActivity().finish();
                return;
            }
            FragmentForgotPasswordBinding fragmentForgotPasswordBinding4 = this.binding;
            if (fragmentForgotPasswordBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentForgotPasswordBinding2 = fragmentForgotPasswordBinding4;
            }
            if (Intrinsics.areEqual(view, fragmentForgotPasswordBinding2.layoutHavingTroubleView)) {
                showContactSupportDialog();
                return;
            }
            return;
        }
        FragmentForgotPasswordBinding fragmentForgotPasswordBinding5 = this.binding;
        if (fragmentForgotPasswordBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentForgotPasswordBinding5 = null;
        }
        TextInputEditText textInputEditText = fragmentForgotPasswordBinding5.edMobileNumberFP;
        Intrinsics.checkNotNullExpressionValue(textInputEditText, "binding.edMobileNumberFP");
        if (!(ExtensionsKt.myText(textInputEditText).length() > 0)) {
            FragmentForgotPasswordBinding fragmentForgotPasswordBinding6 = this.binding;
            if (fragmentForgotPasswordBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentForgotPasswordBinding2 = fragmentForgotPasswordBinding6;
            }
            fragmentForgotPasswordBinding2.txtLayoutMobileNumberFP.setError(getString(R.string.err_empty_mobile_no));
            return;
        }
        RegisterUserBody registerUserBody = new RegisterUserBody();
        StringBuilder sb = new StringBuilder();
        sb.append("91");
        FragmentForgotPasswordBinding fragmentForgotPasswordBinding7 = this.binding;
        if (fragmentForgotPasswordBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentForgotPasswordBinding2 = fragmentForgotPasswordBinding7;
        }
        TextInputEditText textInputEditText2 = fragmentForgotPasswordBinding2.edMobileNumberFP;
        Intrinsics.checkNotNullExpressionValue(textInputEditText2, "binding.edMobileNumberFP");
        sb.append(ExtensionsKt.myText(textInputEditText2));
        registerUserBody.setMobileNumber(sb.toString());
        OnBoardingService onBoardingService = new OnBoardingService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        onBoardingService.sendForgotPasswordOtp(requireActivity, registerUserBody);
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
        FragmentForgotPasswordBinding inflate = FragmentForgotPasswordBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.binding = inflate;
        FragmentForgotPasswordBinding fragmentForgotPasswordBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        inflate.btnForgotPassword.setOnClickListener(this);
        FragmentForgotPasswordBinding fragmentForgotPasswordBinding2 = this.binding;
        if (fragmentForgotPasswordBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentForgotPasswordBinding2 = null;
        }
        fragmentForgotPasswordBinding2.tvRegisterNow.setOnClickListener(this);
        FragmentForgotPasswordBinding fragmentForgotPasswordBinding3 = this.binding;
        if (fragmentForgotPasswordBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentForgotPasswordBinding3 = null;
        }
        fragmentForgotPasswordBinding3.layoutHavingTroubleView.setOnClickListener(this);
        FragmentForgotPasswordBinding fragmentForgotPasswordBinding4 = this.binding;
        if (fragmentForgotPasswordBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentForgotPasswordBinding = fragmentForgotPasswordBinding4;
        }
        FrameLayout root = fragmentForgotPasswordBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }
}
