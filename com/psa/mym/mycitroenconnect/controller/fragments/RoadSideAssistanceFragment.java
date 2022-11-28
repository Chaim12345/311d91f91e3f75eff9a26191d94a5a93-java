package com.psa.mym.mycitroenconnect.controller.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class RoadSideAssistanceFragment extends Fragment implements View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final RoadSideAssistanceFragment newInstance() {
            RoadSideAssistanceFragment roadSideAssistanceFragment = new RoadSideAssistanceFragment();
            roadSideAssistanceFragment.setArguments(new Bundle());
            return roadSideAssistanceFragment;
        }
    }

    private final void checkForPermissionAndMakeCall() {
        Dexter.withActivity(requireActivity()).withPermissions("android.permission.CALL_PHONE").withListener(new MultiplePermissionsListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.RoadSideAssistanceFragment$checkForPermissionAndMakeCall$1
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
                    FragmentActivity requireActivity = RoadSideAssistanceFragment.this.requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                    String string = RoadSideAssistanceFragment.this.getString(R.string.lbl_call_permission_msg);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.lbl_call_permission_msg)");
                    ExtensionsKt.showToast(requireActivity, string);
                    return;
                }
                try {
                    String string2 = RoadSideAssistanceFragment.this.getString(R.string.label_customer_care_number);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.label_customer_care_number)");
                    trim = StringsKt__StringsKt.trim((CharSequence) string2);
                    String obj = trim.toString();
                    RoadSideAssistanceFragment.this.requireActivity().startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + obj)));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }).check();
    }

    @JvmStatic
    @NotNull
    public static final RoadSideAssistanceFragment newInstance() {
        return Companion.newInstance();
    }

    private final void setListeners() {
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivRSAClose)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRSACancel)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRSACallSupport)).setOnClickListener(this);
        ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvRSACustomerCareNo)).setOnClickListener(this);
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
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivRSAClose)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRSACancel))) {
            requireActivity().onBackPressed();
            return;
        }
        if (Intrinsics.areEqual(view, (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvRSACustomerCareNo)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRSACallSupport))) {
            checkForPermissionAndMakeCall();
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
        return inflater.inflate(R.layout.fragment_road_side_assistance, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setListeners();
    }
}
