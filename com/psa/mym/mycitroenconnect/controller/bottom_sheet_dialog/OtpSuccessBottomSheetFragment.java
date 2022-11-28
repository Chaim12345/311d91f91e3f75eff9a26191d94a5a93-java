package com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.MainActivity;
import com.psa.mym.mycitroenconnect.controller.activities.NonVinMainActivity;
import com.psa.mym.mycitroenconnect.controller.activities.onboarding.SetNewPinActivity;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class OtpSuccessBottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "OtpSuccessBottomSheetFragment";
    @Nullable
    private Handler handler;
    private boolean isBio;
    private boolean isRegisteredUser;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String mPageMode = "";
    private boolean isFromMainScreen = true;
    @NotNull
    private Runnable runnable = new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OtpSuccessBottomSheetFragment$special$$inlined$Runnable$1
        @Override // java.lang.Runnable
        public final void run() {
            OtpSuccessBottomSheetFragment otpSuccessBottomSheetFragment = OtpSuccessBottomSheetFragment.this;
            int i2 = R.id.linOkay;
            if (((LinearLayout) otpSuccessBottomSheetFragment._$_findCachedViewById(i2)) != null) {
                LinearLayout linOkay = (LinearLayout) OtpSuccessBottomSheetFragment.this._$_findCachedViewById(i2);
                Intrinsics.checkNotNullExpressionValue(linOkay, "linOkay");
                if (ExtensionsKt.isVisible(linOkay)) {
                    ((AppCompatButton) OtpSuccessBottomSheetFragment.this._$_findCachedViewById(R.id.btnOkay)).performClick();
                    return;
                }
            }
            OtpSuccessBottomSheetFragment otpSuccessBottomSheetFragment2 = OtpSuccessBottomSheetFragment.this;
            int i3 = R.id.llFingerPrintEnableSuccessfully;
            if (((LinearLayoutCompat) otpSuccessBottomSheetFragment2._$_findCachedViewById(i3)) != null) {
                LinearLayoutCompat llFingerPrintEnableSuccessfully = (LinearLayoutCompat) OtpSuccessBottomSheetFragment.this._$_findCachedViewById(i3);
                Intrinsics.checkNotNullExpressionValue(llFingerPrintEnableSuccessfully, "llFingerPrintEnableSuccessfully");
                if (ExtensionsKt.isVisible(llFingerPrintEnableSuccessfully)) {
                    ((AppCompatImageView) OtpSuccessBottomSheetFragment.this._$_findCachedViewById(R.id.ivClose)).performClick();
                    return;
                }
            }
            Logger.INSTANCE.e("Not required condition comes here");
        }
    };

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final OtpSuccessBottomSheetFragment newInstance() {
            return new OtpSuccessBottomSheetFragment();
        }
    }

    private final void navigateToDB() {
        Intent intent;
        if (Intrinsics.areEqual(this.mPageMode, "login")) {
            redirectToSetPin();
            return;
        }
        if (this.isRegisteredUser) {
            intent = new Intent(requireActivity(), MainActivity.class);
        } else {
            SharedPref.Companion companion = SharedPref.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            companion.setIsPrimaryUser(requireContext, "false");
            intent = new Intent(requireActivity(), NonVinMainActivity.class);
        }
        intent.addFlags(67108864);
        intent.addFlags(268435456);
        requireActivity().startActivity(intent);
        requireActivity().finish();
    }

    @JvmStatic
    @NotNull
    public static final OtpSuccessBottomSheetFragment newInstance() {
        return Companion.newInstance();
    }

    private final void redirectToSetPin() {
        Intent intent = new Intent(requireContext(), SetNewPinActivity.class);
        intent.putExtra("isRegisteredUser", this.isRegisteredUser);
        intent.putExtra("mPageMode", this.mPageMode);
        startActivity(intent);
        requireActivity().finish();
    }

    private final void setListeners() {
        ((AppCompatButton) _$_findCachedViewById(R.id.btnYes)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnNo)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnOkay)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivClose)).setOnClickListener(this);
    }

    private final void showFingerPrintEnabledSuccessfully() {
        LinearLayoutCompat llFingerPrintEnableSuccessfully = (LinearLayoutCompat) _$_findCachedViewById(R.id.llFingerPrintEnableSuccessfully);
        Intrinsics.checkNotNullExpressionValue(llFingerPrintEnableSuccessfully, "llFingerPrintEnableSuccessfully");
        ExtensionsKt.show(llFingerPrintEnableSuccessfully);
        LinearLayout linBioLayout = (LinearLayout) _$_findCachedViewById(R.id.linBioLayout);
        Intrinsics.checkNotNullExpressionValue(linBioLayout, "linBioLayout");
        ExtensionsKt.hide(linBioLayout);
        LinearLayout linOkay = (LinearLayout) _$_findCachedViewById(R.id.linOkay);
        Intrinsics.checkNotNullExpressionValue(linOkay, "linOkay");
        ExtensionsKt.hide(linOkay);
        AppCompatTextView tvOtpVerified = (AppCompatTextView) _$_findCachedViewById(R.id.tvOtpVerified);
        Intrinsics.checkNotNullExpressionValue(tvOtpVerified, "tvOtpVerified");
        ExtensionsKt.hide(tvOtpVerified);
        AppCompatImageView imgOtpVerified = (AppCompatImageView) _$_findCachedViewById(R.id.imgOtpVerified);
        Intrinsics.checkNotNullExpressionValue(imgOtpVerified, "imgOtpVerified");
        ExtensionsKt.hide(imgOtpVerified);
        startAutoDismissCallback();
    }

    private final void startAnimation() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OtpSuccessBottomSheetFragment$startAnimation$$inlined$Runnable$1
            @Override // java.lang.Runnable
            public final void run() {
            }
        }, 300L);
    }

    private final void startAutoDismissCallback() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(this.runnable, 5000L);
        this.handler = handler;
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (!Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivClose))) {
            if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnYes))) {
                SharedPref.Companion companion = SharedPref.Companion;
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "this.requireActivity()");
                companion.setIsFingerPrintAuth(requireActivity, "True");
                showFingerPrintEnabledSuccessfully();
                return;
            }
            if (!(Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnNo)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnOkay)))) {
                return;
            }
            if (Intrinsics.areEqual(this.mPageMode, AppConstants.PAGE_MODE_CHANGE_PIN)) {
                this.isRegisteredUser = this.isFromMainScreen;
            } else {
                SharedPref.Companion companion2 = SharedPref.Companion;
                FragmentActivity requireActivity2 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity2, "this.requireActivity()");
                companion2.setIsFingerPrintAuth(requireActivity2, "False");
            }
        }
        navigateToDB();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
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
        View inflate = inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_otp_success_bottom_sheet, viewGroup, false);
        Bundle requireArguments = requireArguments();
        Intrinsics.checkNotNullExpressionValue(requireArguments, "requireArguments()");
        if (requireArguments.containsKey("mPageMode")) {
            String string = requireArguments.getString("mPageMode");
            Intrinsics.checkNotNull(string);
            this.mPageMode = string;
        }
        if (requireArguments.containsKey("isBio")) {
            this.isBio = requireArguments.getBoolean("isBio");
        }
        if (requireArguments.containsKey("isRegisteredUser")) {
            this.isRegisteredUser = requireArguments.getBoolean("isRegisteredUser");
        }
        if (requireArguments.containsKey(AppConstants.ARG_IS_FROM_MAIN_SCREEN)) {
            this.isFromMainScreen = requireArguments.getBoolean(AppConstants.ARG_IS_FROM_MAIN_SCREEN, true);
        }
        startAnimation();
        return inflate;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(@NotNull DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        Handler handler = this.handler;
        if (handler == null || handler == null) {
            return;
        }
        handler.removeCallbacks(this.runnable);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        AppCompatTextView appCompatTextView;
        int i2;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        setListeners();
        String str = this.mPageMode;
        if (Intrinsics.areEqual(str, "login")) {
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvOtpVerified);
            i2 = uat.psa.mym.mycitroenconnect.R.string.label_otp_verified_success;
        } else if (Intrinsics.areEqual(str, AppConstants.PAGE_MODE_CHANGE_PIN)) {
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvOtpVerified);
            i2 = uat.psa.mym.mycitroenconnect.R.string.label_change_pin_success;
        } else {
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvOtpVerified);
            i2 = uat.psa.mym.mycitroenconnect.R.string.label_pin_set_success;
        }
        appCompatTextView.setText(getString(i2));
        if (this.isBio) {
            ((LinearLayout) _$_findCachedViewById(R.id.linOkay)).setVisibility(8);
            ((LinearLayout) _$_findCachedViewById(R.id.linBioLayout)).setVisibility(0);
            return;
        }
        ((LinearLayout) _$_findCachedViewById(R.id.linOkay)).setVisibility(0);
        ((LinearLayout) _$_findCachedViewById(R.id.linBioLayout)).setVisibility(8);
    }
}
