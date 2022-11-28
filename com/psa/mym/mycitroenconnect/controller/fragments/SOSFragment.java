package com.psa.mym.mycitroenconnect.controller.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.psa.mym.mycitroenconnect.controller.activities.MainActivity;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.skyfishjy.library.RippleBackground;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class SOSFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private long downPressTime;
    @Nullable
    private MainActivity parentActivity;
    private long upPressTime;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final SOSFragment newInstance() {
            SOSFragment sOSFragment = new SOSFragment();
            sOSFragment.setArguments(new Bundle());
            return sOSFragment;
        }
    }

    private final void checkForPermissionAndMakeCall() {
        Dexter.withActivity(requireActivity()).withPermissions("android.permission.CALL_PHONE").withListener(new MultiplePermissionsListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.SOSFragment$checkForPermissionAndMakeCall$1
            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
            public void onPermissionRationaleShouldBeShown(@Nullable List<PermissionRequest> list, @NotNull PermissionToken token) {
                Intrinsics.checkNotNullParameter(token, "token");
                token.continuePermissionRequest();
            }

            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
            @SuppressLint({"MissingPermission"})
            public void onPermissionsChecked(@NotNull MultiplePermissionsReport report) {
                CharSequence trim;
                Intrinsics.checkNotNullParameter(report, "report");
                if (!report.areAllPermissionsGranted()) {
                    FragmentActivity requireActivity = SOSFragment.this.requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                    String string = SOSFragment.this.getString(R.string.lbl_call_permission_msg);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.lbl_call_permission_msg)");
                    ExtensionsKt.showToast(requireActivity, string);
                    return;
                }
                try {
                    String string2 = SOSFragment.this.getString(R.string.label_customer_care_number);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.label_customer_care_number)");
                    trim = StringsKt__StringsKt.trim((CharSequence) string2);
                    String obj = trim.toString();
                    SOSFragment.this.requireActivity().startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + obj)));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }).check();
    }

    @JvmStatic
    @NotNull
    public static final SOSFragment newInstance() {
        return Companion.newInstance();
    }

    private final void setListeners() {
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivSosClose)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSosCancel)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivSOS)).setOnTouchListener(this);
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
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSosCancel)) ? true : Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivSosClose))) {
            requireActivity().onBackPressed();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_s_o_s, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x007f, code lost:
        if (r0 != false) goto L38;
     */
    @Override // android.view.View.OnTouchListener
    @SuppressLint({"ClickableViewAccessibility"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouch(@Nullable View view, @Nullable MotionEvent motionEvent) {
        ViewParent parent;
        boolean z = true;
        if (view != null && (parent = view.getParent()) != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        int i2 = com.psa.mym.mycitroenconnect.R.id.ivSOS;
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(i2))) {
            Logger logger = Logger.INSTANCE;
            StringBuilder sb = new StringBuilder();
            sb.append("onTouch");
            sb.append(motionEvent != null ? Integer.valueOf(motionEvent.getAction()) : null);
            logger.i(sb.toString());
            if (!(motionEvent != null && motionEvent.getAction() == 0)) {
                if (!(motionEvent != null && motionEvent.getAction() == 5)) {
                    if (!(motionEvent != null && motionEvent.getAction() == 1)) {
                        if (!(motionEvent != null && motionEvent.getAction() == 6)) {
                            if (motionEvent == null || motionEvent.getAction() != 3) {
                                z = false;
                            }
                        }
                    }
                    ((RippleBackground) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rippleBackgroundSos)).stopRippleAnimation();
                    ((AppCompatImageView) _$_findCachedViewById(i2)).setImageResource(R.drawable.ic_sos_press_here_btn);
                    long currentTimeMillis = System.currentTimeMillis();
                    this.upPressTime = currentTimeMillis;
                    if (currentTimeMillis - this.downPressTime > 3000) {
                        checkForPermissionAndMakeCall();
                    }
                }
            }
            ((AppCompatImageView) _$_findCachedViewById(i2)).setImageResource(R.drawable.ic_sos_pressed);
            ((RippleBackground) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rippleBackgroundSos)).startRippleAnimation();
            this.downPressTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        MainActivity mainActivity;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        if (getActivity() == null || !(getActivity() instanceof MainActivity)) {
            mainActivity = null;
        } else {
            FragmentActivity activity = getActivity();
            Objects.requireNonNull(activity, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.controller.activities.MainActivity");
            mainActivity = (MainActivity) activity;
        }
        this.parentActivity = mainActivity;
        setListeners();
    }
}
