package com.psa.mym.mycitroenconnect.controller.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.ChangePasswordActivity;
import com.psa.mym.mycitroenconnect.controller.activities.ChangePinActivity;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SecurityFragment extends Fragment implements View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    private Boolean isFromMainScreen = Boolean.TRUE;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final SecurityFragment newInstance(boolean z) {
            SecurityFragment securityFragment = new SecurityFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean(AppConstants.ARG_IS_FROM_MAIN_SCREEN, z);
            securityFragment.setArguments(bundle);
            return securityFragment;
        }
    }

    private final void hideFingerPrintUI() {
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvEnableFingerPrint)).setVisibility(8);
        ((SwitchMaterial) _$_findCachedViewById(R.id.switchFingerPrint)).setVisibility(8);
        _$_findCachedViewById(R.id.horiSeperator3).setVisibility(8);
    }

    private final void initView() {
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!companion.checkForBiometrics(requireContext)) {
            hideFingerPrintUI();
            return;
        }
        showFingerPrintUI();
        SharedPref.Companion companion2 = SharedPref.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        ((SwitchMaterial) _$_findCachedViewById(R.id.switchFingerPrint)).setChecked(companion2.isFingerPrintAuth(requireContext2));
        toggleEnableFingerprintUI();
    }

    @JvmStatic
    @NotNull
    public static final SecurityFragment newInstance(boolean z) {
        return Companion.newInstance(z);
    }

    private final void setListener() {
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvChangePinNumber)).setOnClickListener(this);
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvChangePassword)).setOnClickListener(this);
        ((SwitchMaterial) _$_findCachedViewById(R.id.switchFingerPrint)).setOnClickListener(this);
    }

    private final void showFingerPrintUI() {
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvEnableFingerPrint)).setVisibility(0);
        ((SwitchMaterial) _$_findCachedViewById(R.id.switchFingerPrint)).setVisibility(0);
        _$_findCachedViewById(R.id.horiSeperator3).setVisibility(0);
    }

    private final void toggleEnableFingerprintUI() {
        AppCompatTextView appCompatTextView;
        Context requireContext;
        int i2;
        if (((SwitchMaterial) _$_findCachedViewById(R.id.switchFingerPrint)).isChecked()) {
            SharedPref.Companion companion = SharedPref.Companion;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "this.requireActivity()");
            companion.setIsFingerPrintAuth(requireActivity, "True");
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvEnableFingerPrint);
            requireContext = requireContext();
            i2 = uat.psa.mym.mycitroenconnect.R.color.dark_grey;
        } else {
            SharedPref.Companion companion2 = SharedPref.Companion;
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "this.requireActivity()");
            companion2.setIsFingerPrintAuth(requireActivity2, "False");
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvEnableFingerPrint);
            requireContext = requireContext();
            i2 = uat.psa.mym.mycitroenconnect.R.color.hot_grey_100;
        }
        appCompatTextView.setTextColor(ContextCompat.getColor(requireContext, i2));
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
        Intent intent;
        if (Intrinsics.areEqual(view, (AppCompatTextView) _$_findCachedViewById(R.id.tvChangePinNumber))) {
            intent = new Intent(requireActivity(), ChangePinActivity.class);
        } else if (!Intrinsics.areEqual(view, (AppCompatTextView) _$_findCachedViewById(R.id.tvChangePassword))) {
            if (Intrinsics.areEqual(view, (SwitchMaterial) _$_findCachedViewById(R.id.switchFingerPrint))) {
                toggleEnableFingerprintUI();
                return;
            }
            return;
        } else {
            intent = new Intent(requireActivity(), ChangePasswordActivity.class);
        }
        intent.putExtra(AppConstants.ARG_IS_FROM_MAIN_SCREEN, this.isFromMainScreen);
        startActivity(intent);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.isFromMainScreen = Boolean.valueOf(arguments.getBoolean(AppConstants.ARG_IS_FROM_MAIN_SCREEN));
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_security, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initView();
        setListener();
    }
}
