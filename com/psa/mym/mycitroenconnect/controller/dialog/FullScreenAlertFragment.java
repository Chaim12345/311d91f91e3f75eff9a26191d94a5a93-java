package com.psa.mym.mycitroenconnect.controller.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;
import com.psa.mym.mycitroenconnect.GlideApp;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FullScreenAlertFragment extends DialogFragment implements View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private static final String TAG = Reflection.getOrCreateKotlinClass(FullScreenAlertFragment.class).getSimpleName();
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    private String alertType;
    @Nullable
    private FullScreenAlertInterface fullScreenAlertInterface;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final String getTAG() {
            return FullScreenAlertFragment.TAG;
        }

        @JvmStatic
        @NotNull
        public final FullScreenAlertFragment newInstance(@NotNull String param1) {
            Intrinsics.checkNotNullParameter(param1, "param1");
            FullScreenAlertFragment fullScreenAlertFragment = new FullScreenAlertFragment();
            Bundle bundle = new Bundle();
            bundle.putString("alertType", param1);
            fullScreenAlertFragment.setArguments(bundle);
            return fullScreenAlertFragment;
        }
    }

    private final void initView() {
        AppCompatTextView appCompatTextView;
        int i2;
        String str = this.alertType;
        if (str != null) {
            switch (str.hashCode()) {
                case -894483947:
                    if (str.equals(AppConstants.Notification_CrashAlert)) {
                        setAlertImage(R.drawable.ic_crash_alert_new);
                        appCompatTextView = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvAlertTitle);
                        i2 = R.string.title_crash_alert;
                        break;
                    } else {
                        return;
                    }
                case 93415538:
                    if (str.equals(AppConstants.Notification_LowFuelAlert)) {
                        SharedPref.Companion companion = SharedPref.Companion;
                        Context requireContext = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        if (Intrinsics.areEqual(companion.getVehicleType(requireContext), AppConstants.ICE)) {
                            setAlertImage(R.drawable.ic_fuel_alert_new);
                            ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvAlertTitle)).setText(getString(R.string.title_critical_low_fuel_alert));
                            AppCompatTextView tvAlertNavTitle = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvAlertNavTitle);
                            Intrinsics.checkNotNullExpressionValue(tvAlertNavTitle, "tvAlertNavTitle");
                            ExtensionsKt.hide(tvAlertNavTitle);
                            AppCompatImageView ivAlertNavImage = (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivAlertNavImage);
                            Intrinsics.checkNotNullExpressionValue(ivAlertNavImage, "ivAlertNavImage");
                            ExtensionsKt.hide(ivAlertNavImage);
                        } else {
                            setAlertImage(R.drawable.ic_charge_alert_new);
                            ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvAlertTitle)).setText(getString(R.string.title_critical_low_charge_alert));
                            ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvAlertNavTitle)).setText(getString(R.string.lbl_locate_nearby_charging_stn));
                            ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivAlertNavImage)).setImageResource(R.drawable.ic_alert_charging_stn);
                        }
                        AppCompatTextView tvAlertNavTitle2 = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvAlertNavTitle);
                        Intrinsics.checkNotNullExpressionValue(tvAlertNavTitle2, "tvAlertNavTitle");
                        ExtensionsKt.hide(tvAlertNavTitle2);
                        AppCompatImageView ivAlertNavImage2 = (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivAlertNavImage);
                        Intrinsics.checkNotNullExpressionValue(ivAlertNavImage2, "ivAlertNavImage");
                        ExtensionsKt.hide(ivAlertNavImage2);
                        return;
                    }
                    return;
                case 970664341:
                    if (str.equals(AppConstants.Notification_IntrusionAlert)) {
                        setAlertImage(R.drawable.ic_alert_intruder_new);
                        appCompatTextView = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvAlertTitle);
                        i2 = R.string.title_intruder_alert;
                        break;
                    } else {
                        return;
                    }
                case 1881199922:
                    if (str.equals(AppConstants.Notification_TowAwayAlert)) {
                        setAlertImage(R.drawable.ic_tow_away_alert_new);
                        appCompatTextView = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvAlertTitle);
                        i2 = R.string.title_tow_away_alert;
                        break;
                    } else {
                        return;
                    }
                default:
                    return;
            }
            appCompatTextView.setText(getString(i2));
            ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvAlertNavTitle)).setText(getString(R.string.alert_title_locate_car));
            ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivAlertNavImage)).setImageResource(R.drawable.ic_alert_locate_my_car);
        }
    }

    @JvmStatic
    @NotNull
    public static final FullScreenAlertFragment newInstance(@NotNull String str) {
        return Companion.newInstance(str);
    }

    private final void setAlertImage(@DrawableRes int i2) {
        GlideApp.with(requireContext()).load(Integer.valueOf(i2)).centerCrop().placeholder(i2).into((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivAlertImage));
    }

    private final void setListeners() {
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivAlertNavImage)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivAlertCloseBtn)).setOnClickListener(this);
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
        Logger logger;
        String str;
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivAlertNavImage))) {
            dismiss();
            FullScreenAlertInterface fullScreenAlertInterface = this.fullScreenAlertInterface;
            if (fullScreenAlertInterface != null) {
                fullScreenAlertInterface.onAlertBtnClick(this.alertType);
            }
            logger = Logger.INSTANCE;
            str = "ivAlertNavImage pressed for alert type : " + this.alertType;
        } else if (!Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivAlertCloseBtn))) {
            return;
        } else {
            dismiss();
            FullScreenAlertInterface fullScreenAlertInterface2 = this.fullScreenAlertInterface;
            if (fullScreenAlertInterface2 != null) {
                fullScreenAlertInterface2.onAlertDismissClick();
            }
            logger = Logger.INSTANCE;
            str = "ivAlertCloseBtn pressed for alert type";
        }
        logger.e(str);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.alertType = arguments.getString("alertType");
        }
        setStyle(0, R.style.FullScreenDialog);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_full_screen_alert, viewGroup, false);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            Dialog dialog = getDialog();
            Intrinsics.checkNotNull(dialog);
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(-1, -1);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initView();
        setListeners();
    }

    public final void setOnFullScreenAlertListener(@NotNull FullScreenAlertInterface listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.fullScreenAlertInterface = listener;
    }
}
