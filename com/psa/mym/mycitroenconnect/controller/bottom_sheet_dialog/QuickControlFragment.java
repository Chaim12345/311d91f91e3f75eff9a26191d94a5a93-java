package com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.MainActivity;
import com.psa.mym.mycitroenconnect.controller.adapters.QuickControlAdapter;
import com.psa.mym.mycitroenconnect.controller.adapters.QuickControlInterface;
import com.psa.mym.mycitroenconnect.model.QuickControl;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import k.d;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class QuickControlFragment extends BottomSheetDialogFragment implements View.OnClickListener, QuickControlInterface {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static String TAG = "QuickControlFragment";
    @Nullable
    private QuickControlAdapter controlAdapter;
    @Nullable
    private MainActivity parentActivity;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String vehicleType = "";

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getTAG() {
            return QuickControlFragment.TAG;
        }

        @JvmStatic
        @NotNull
        public final QuickControlFragment newInstance() {
            QuickControlFragment quickControlFragment = new QuickControlFragment();
            quickControlFragment.setArguments(new Bundle());
            return quickControlFragment;
        }

        public final void setTAG(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            QuickControlFragment.TAG = str;
        }
    }

    private final void initViews() {
        ArrayList arrayListOf;
        ArrayList arrayListOf2;
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.isGuestUser(requireContext)) {
            String string = getString(R.string.label_geo_fence);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.label_geo_fence)");
            String string2 = getString(R.string.label_valet_mode);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.label_valet_mode)");
            String string3 = getString(R.string.label_locate_car);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.label_locate_car)");
            arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(new QuickControl(string, R.drawable.ic_vector_geofence), new QuickControl(string2, R.drawable.ic_vector_valet), new QuickControl(string3, R.drawable.ic_locate_car));
            String string4 = getString(R.string.label_geo_fence);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.label_geo_fence)");
            String string5 = getString(R.string.label_valet_mode);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.label_valet_mode)");
            String string6 = getString(R.string.label_locate_car);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.label_locate_car)");
            arrayListOf2 = CollectionsKt__CollectionsKt.arrayListOf(new QuickControl(string4, R.drawable.ic_vector_geofence), new QuickControl(string5, R.drawable.ic_vector_valet), new QuickControl(string6, R.drawable.ic_locate_car));
        } else {
            String string7 = getString(R.string.label_geo_fence);
            Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.label_geo_fence)");
            String string8 = getString(R.string.label_valet_mode);
            Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.label_valet_mode)");
            String string9 = getString(R.string.label_locate_car);
            Intrinsics.checkNotNullExpressionValue(string9, "getString(R.string.label_locate_car)");
            arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(new QuickControl(string7, R.drawable.ic_vector_geofence), new QuickControl(string8, R.drawable.ic_vector_valet), new QuickControl(string9, R.drawable.ic_locate_car));
            String string10 = getString(R.string.label_geo_fence);
            Intrinsics.checkNotNullExpressionValue(string10, "getString(R.string.label_geo_fence)");
            String string11 = getString(R.string.label_valet_mode);
            Intrinsics.checkNotNullExpressionValue(string11, "getString(R.string.label_valet_mode)");
            String string12 = getString(R.string.label_locate_car);
            Intrinsics.checkNotNullExpressionValue(string12, "getString(R.string.label_locate_car)");
            arrayListOf2 = CollectionsKt__CollectionsKt.arrayListOf(new QuickControl(string10, R.drawable.ic_vector_geofence), new QuickControl(string11, R.drawable.ic_vector_valet), new QuickControl(string12, R.drawable.ic_locate_car));
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String vehicleType = companion.getVehicleType(requireActivity);
        this.vehicleType = vehicleType;
        if (!Intrinsics.areEqual(vehicleType, AppConstants.ICE)) {
            arrayListOf = arrayListOf2;
        }
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        this.controlAdapter = new QuickControlAdapter(requireContext2, arrayListOf, this);
        int i2 = com.psa.mym.mycitroenconnect.R.id.rvQuickControl;
        ((RecyclerView) _$_findCachedViewById(i2)).setLayoutManager(new GridLayoutManager(requireContext(), 3));
        ((RecyclerView) _$_findCachedViewById(i2)).setAdapter(this.controlAdapter);
    }

    @JvmStatic
    @NotNull
    public static final QuickControlFragment newInstance() {
        return Companion.newInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreateView$lambda-1  reason: not valid java name */
    public static final void m124onCreateView$lambda1(DialogInterface dialogInterface) {
        Objects.requireNonNull(dialogInterface, "null cannot be cast to non-null type com.google.android.material.bottomsheet.BottomSheetDialog");
        FrameLayout frameLayout = (FrameLayout) ((BottomSheetDialog) dialogInterface).findViewById(R.id.design_bottom_sheet);
        Intrinsics.checkNotNull(frameLayout);
        ViewParent parent = frameLayout.getParent();
        Objects.requireNonNull(parent, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout");
        BottomSheetBehavior from = BottomSheetBehavior.from(frameLayout);
        Intrinsics.checkNotNullExpressionValue(from, "from(bottomSheet)");
        from.setPeekHeight(frameLayout.getHeight());
        ((CoordinatorLayout) parent).getParent().requestLayout();
    }

    private final void setListeners() {
        ((FloatingActionButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.fabQuickClose)).setOnClickListener(this);
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

    @Nullable
    public final MainActivity getParentActivity() {
        return this.parentActivity;
    }

    @NotNull
    public final String getVehicleType() {
        return this.vehicleType;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (FloatingActionButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.fabQuickClose))) {
            dismiss();
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
        setStyle(0, R.style.DialogStyle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.fragment_quick_control, viewGroup, false);
        Dialog dialog = getDialog();
        Intrinsics.checkNotNull(dialog);
        dialog.setOnShowListener(d.f11013a);
        return inflate;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.QuickControlInterface
    public void onItemClick(@NotNull QuickControl objControl, int i2) {
        Intrinsics.checkNotNullParameter(objControl, "objControl");
        MainActivity mainActivity = this.parentActivity;
        if (mainActivity != null) {
            mainActivity.onQuickControlSelection(objControl.getControlName());
        }
        dismiss();
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
        initViews();
        setListeners();
    }

    public final void setParentActivity(@Nullable MainActivity mainActivity) {
        this.parentActivity = mainActivity;
    }

    public final void setVehicleType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vehicleType = str;
    }
}
