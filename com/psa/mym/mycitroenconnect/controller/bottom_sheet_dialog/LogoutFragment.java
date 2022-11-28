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
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.psa.mym.mycitroenconnect.BusBaseBottomSheetFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.controller.activities.onboarding.LoginActivity;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.LogoutFragment;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.PostCommonResponse;
import com.psa.mym.mycitroenconnect.services.FCMService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SPUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class LogoutFragment extends BusBaseBottomSheetFragment implements View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "LogoutFragment";
    @Nullable
    private Handler handler;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private Runnable runnable = new Runnable() { // from class: k.b
        @Override // java.lang.Runnable
        public final void run() {
            LogoutFragment.m122runnable$lambda1(LogoutFragment.this);
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
        public final LogoutFragment newInstance() {
            LogoutFragment logoutFragment = new LogoutFragment();
            logoutFragment.setArguments(new Bundle());
            return logoutFragment;
        }
    }

    private final void deleteFCMToken() {
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.showDialog(requireContext);
        FCMService fCMService = new FCMService();
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        fCMService.deleteToken(requireContext2, companion.getDeviceId(requireContext3));
    }

    private final void initViews() {
        ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(R.id.layoutLogout);
        if (constraintLayout != null) {
            constraintLayout.setVisibility(0);
        }
        ConstraintLayout constraintLayout2 = (ConstraintLayout) _$_findCachedViewById(R.id.layoutLogoutSuccess);
        if (constraintLayout2 == null) {
            return;
        }
        constraintLayout2.setVisibility(8);
    }

    private final void logOutUser() {
        startAutoDismissCallback();
        SPUtil sPUtil = SPUtil.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        sPUtil.clearALLPreference(requireActivity);
        NotificationManagerCompat.from(requireActivity()).cancelAll();
        ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(R.id.layoutLogout);
        if (constraintLayout != null) {
            constraintLayout.setVisibility(8);
        }
        ConstraintLayout constraintLayout2 = (ConstraintLayout) _$_findCachedViewById(R.id.layoutLogoutSuccess);
        if (constraintLayout2 == null) {
            return;
        }
        constraintLayout2.setVisibility(0);
    }

    @JvmStatic
    @NotNull
    public static final LogoutFragment newInstance() {
        return Companion.newInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: runnable$lambda-1  reason: not valid java name */
    public static final void m122runnable$lambda1(LogoutFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppCompatImageView appCompatImageView = (AppCompatImageView) this$0._$_findCachedViewById(R.id.ivLogoutClose);
        if (appCompatImageView != null) {
            appCompatImageView.performClick();
        }
    }

    private final void setListener() {
        AppCompatButton appCompatButton = (AppCompatButton) _$_findCachedViewById(R.id.btnLogoutNo);
        if (appCompatButton != null) {
            appCompatButton.setOnClickListener(this);
        }
        AppCompatButton appCompatButton2 = (AppCompatButton) _$_findCachedViewById(R.id.btnLogoutYes);
        if (appCompatButton2 != null) {
            appCompatButton2.setOnClickListener(this);
        }
        AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(R.id.ivLogoutClose);
        if (appCompatImageView != null) {
            appCompatImageView.setOnClickListener(this);
        }
    }

    private final void startAutoDismissCallback() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(this.runnable, 5000L);
        this.handler = handler;
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

    @Subscribe
    public final void getMessage(@NotNull ErrorResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (Intrinsics.areEqual(event.getApiName(), "FCMDeleteToken")) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            ExtensionsKt.showToast(requireContext, event.getMsg());
        }
    }

    @Subscribe
    public final void getMessage(@NotNull PostCommonResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getStatusCode() == 200) {
            logOutUser();
            return;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, event.getMessage());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnLogoutYes))) {
            deleteFCMToken();
            return;
        }
        if (!Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnLogoutNo))) {
            if (!Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivLogoutClose))) {
                return;
            }
            ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(R.id.layoutLogoutSuccess);
            boolean z = false;
            if (constraintLayout != null && constraintLayout.getVisibility() == 0) {
                z = true;
            }
            if (z) {
                dismiss();
                Intent intent = new Intent(requireActivity(), LoginActivity.class);
                intent.addFlags(67108864);
                intent.addFlags(268435456);
                intent.putExtra("login", "login");
                startActivity(intent);
                requireActivity().finish();
                return;
            }
        }
        dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
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
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_logout, viewGroup, false);
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseBottomSheetFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
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
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        setListener();
        initViews();
    }
}
