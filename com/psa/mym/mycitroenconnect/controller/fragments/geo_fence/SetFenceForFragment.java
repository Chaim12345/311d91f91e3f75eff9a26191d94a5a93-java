package com.psa.mym.mycitroenconnect.controller.fragments.geo_fence;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.api.body.geo_fence.GeoFenceBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.dashboard.GeoFenceActivity;
import com.psa.mym.mycitroenconnect.controller.adapters.OnSetFenceForSelect;
import com.psa.mym.mycitroenconnect.controller.adapters.SetFenceForAdapter;
import com.psa.mym.mycitroenconnect.controller.viewmodel.GeoFenceViewModel;
import com.psa.mym.mycitroenconnect.model.GeoFenceCommonModel;
import com.psa.mym.mycitroenconnect.model.geo_fence.GeoFenceInfo;
import com.psa.mym.mycitroenconnect.model.geo_fence.GeoFenceType;
import com.psa.mym.mycitroenconnect.model.geo_fence.GetGeoFenceResponseItem;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class SetFenceForFragment extends Fragment implements View.OnClickListener, OnSetFenceForSelect {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private GeoFenceActivity parentActivity;
    @Nullable
    private SetFenceForAdapter setFenceForAdapter;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private ArrayList<GeoFenceInfo> fenceInfoList = new ArrayList<>();
    @NotNull
    private final Lazy viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(GeoFenceViewModel.class), new SetFenceForFragment$special$$inlined$activityViewModels$default$1(this), new SetFenceForFragment$special$$inlined$activityViewModels$default$2(this));

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final SetFenceForFragment newInstance() {
            SetFenceForFragment setFenceForFragment = new SetFenceForFragment();
            setFenceForFragment.setArguments(new Bundle());
            return setFenceForFragment;
        }
    }

    private final GeoFenceViewModel getViewModel() {
        return (GeoFenceViewModel) this.viewModel$delegate.getValue();
    }

    private final void initView() {
        GeoFenceActivity geoFenceActivity;
        ArrayList<GeoFenceInfo> arrayListOf;
        if (getActivity() == null || !(getActivity() instanceof GeoFenceActivity)) {
            geoFenceActivity = null;
        } else {
            FragmentActivity activity = getActivity();
            Objects.requireNonNull(activity, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.controller.activities.dashboard.GeoFenceActivity");
            geoFenceActivity = (GeoFenceActivity) activity;
        }
        this.parentActivity = geoFenceActivity;
        String string = getString(R.string.exit);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.exit)");
        String string2 = getString(R.string.exit_fence_info);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.exit_fence_info)");
        String string3 = getString(R.string.entry);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.entry)");
        String string4 = getString(R.string.entry_fence_info);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.entry_fence_info)");
        String string5 = getString(R.string.both);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.both)");
        String string6 = getString(R.string.both_fence_info);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.both_fence_info)");
        arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(new GeoFenceInfo(string, R.drawable.ic_geo_fence_exit, string2, false, 8, null), new GeoFenceInfo(string3, R.drawable.ic_geo_fence_entry, string4, false, 8, null), new GeoFenceInfo(string5, R.drawable.ic_geo_fence_both, string6, false, 8, null));
        this.fenceInfoList = arrayListOf;
        SetFenceForAdapter setFenceForAdapter = new SetFenceForAdapter(arrayListOf);
        this.setFenceForAdapter = setFenceForAdapter;
        setFenceForAdapter.setOnSetFenceFor(this);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rvFenceFor);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(this.setFenceForAdapter);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnCnfrmContinueSet)).setOnClickListener(this);
    }

    private final void navigateBasedOnFenceType() {
        GeoFenceActivity geoFenceActivity;
        int selectedFenceId = getViewModel().getSelectedFenceId();
        if (selectedFenceId == 0 || selectedFenceId == 1) {
            GeoFenceActivity geoFenceActivity2 = this.parentActivity;
            if (geoFenceActivity2 != null) {
                geoFenceActivity2.navigateToGeoFenceMapFragment();
            }
        } else if (selectedFenceId == 2 && (geoFenceActivity = this.parentActivity) != null) {
            geoFenceActivity.navigateToSetLocationFragment();
        }
    }

    @JvmStatic
    @NotNull
    public static final SetFenceForFragment newInstance() {
        return Companion.newInstance();
    }

    @SuppressLint({"NotifyDataSetChanged"})
    private final void setSelectionOfMode(boolean z) {
        GeoFenceInfo geoFenceInfo;
        GetGeoFenceResponseItem geoFenceGetResponse;
        GeoFenceBody geoFenceBody;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        String str = null;
        String transitionType = (geoFenceCommonModel == null || (geoFenceBody = geoFenceCommonModel.getGeoFenceBody()) == null) ? null : geoFenceBody.getTransitionType();
        if (z) {
            GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel2 != null && (geoFenceGetResponse = geoFenceCommonModel2.getGeoFenceGetResponse()) != null) {
                str = geoFenceGetResponse.getTransitionType();
            }
            transitionType = str;
        }
        for (GeoFenceInfo geoFenceInfo2 : this.fenceInfoList) {
            geoFenceInfo2.setSelected(false);
        }
        if (transitionType != null) {
            int hashCode = transitionType.hashCode();
            if (hashCode != 2341) {
                if (hashCode != 78638) {
                    if (hashCode == 69819369 && transitionType.equals(AppConstants.GEO_FENCE_TRANSITION_BOTH)) {
                        geoFenceInfo = this.fenceInfoList.get(2);
                        geoFenceInfo.setSelected(true);
                    }
                } else if (transitionType.equals(AppConstants.GEO_FENCE_TRANSITION_OUT)) {
                    geoFenceInfo = this.fenceInfoList.get(0);
                    geoFenceInfo.setSelected(true);
                }
            } else if (transitionType.equals(AppConstants.GEO_FENCE_TRANSITION_IN)) {
                geoFenceInfo = this.fenceInfoList.get(1);
                geoFenceInfo.setSelected(true);
            }
        }
        SetFenceForAdapter setFenceForAdapter = this.setFenceForAdapter;
        if (setFenceForAdapter != null) {
            setFenceForAdapter.notifyDataSetChanged();
        }
    }

    private final void setTransitionTypeInModel(GeoFenceInfo geoFenceInfo) {
        String str;
        GeoFenceBody geoFenceBody;
        String title = geoFenceInfo.getTitle();
        if (Intrinsics.areEqual(title, getString(R.string.exit))) {
            GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
            GeoFenceType geoFenceType = geoFenceCommonModel != null ? geoFenceCommonModel.getGeoFenceType() : null;
            str = AppConstants.GEO_FENCE_TRANSITION_OUT;
            if (geoFenceType != null) {
                geoFenceType.setFenceTransitionType(AppConstants.GEO_FENCE_TRANSITION_OUT);
            }
            GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
            geoFenceBody = geoFenceCommonModel2 != null ? geoFenceCommonModel2.getGeoFenceBody() : null;
            if (geoFenceBody == null) {
                return;
            }
        } else if (Intrinsics.areEqual(title, getString(R.string.entry))) {
            GeoFenceCommonModel geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
            GeoFenceType geoFenceType2 = geoFenceCommonModel3 != null ? geoFenceCommonModel3.getGeoFenceType() : null;
            str = AppConstants.GEO_FENCE_TRANSITION_IN;
            if (geoFenceType2 != null) {
                geoFenceType2.setFenceTransitionType(AppConstants.GEO_FENCE_TRANSITION_IN);
            }
            GeoFenceCommonModel geoFenceCommonModel4 = getViewModel().getGeoFenceCommonModel();
            geoFenceBody = geoFenceCommonModel4 != null ? geoFenceCommonModel4.getGeoFenceBody() : null;
            if (geoFenceBody == null) {
                return;
            }
        } else if (!Intrinsics.areEqual(title, getString(R.string.both))) {
            return;
        } else {
            GeoFenceCommonModel geoFenceCommonModel5 = getViewModel().getGeoFenceCommonModel();
            GeoFenceType geoFenceType3 = geoFenceCommonModel5 != null ? geoFenceCommonModel5.getGeoFenceType() : null;
            str = AppConstants.GEO_FENCE_TRANSITION_BOTH;
            if (geoFenceType3 != null) {
                geoFenceType3.setFenceTransitionType(AppConstants.GEO_FENCE_TRANSITION_BOTH);
            }
            GeoFenceCommonModel geoFenceCommonModel6 = getViewModel().getGeoFenceCommonModel();
            geoFenceBody = geoFenceCommonModel6 != null ? geoFenceCommonModel6.getGeoFenceBody() : null;
            if (geoFenceBody == null) {
                return;
            }
        }
        geoFenceBody.setTransitionType(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0073, code lost:
        if ((!r0) == true) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00d7, code lost:
        if ((!r0) == true) goto L60;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0147  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void setUIData() {
        boolean z;
        GeoFenceType geoFenceType;
        GetGeoFenceResponseItem geoFenceGetResponse;
        GetGeoFenceResponseItem geoFenceGetResponse2;
        String transitionType;
        boolean isBlank;
        GetGeoFenceResponseItem geoFenceGetResponse3;
        String transitionType2;
        boolean z2;
        GeoFenceBody geoFenceBody;
        String transitionType3;
        boolean isBlank2;
        GeoFenceBody geoFenceBody2;
        String transitionType4;
        boolean z3;
        GeoFenceBody geoFenceBody3;
        String transitionType5;
        boolean isBlank3;
        GeoFenceBody geoFenceBody4;
        String transitionType6;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        String str = null;
        Integer valueOf = geoFenceCommonModel != null ? Integer.valueOf(geoFenceCommonModel.getFenceCreationMode()) : null;
        boolean z4 = false;
        boolean z5 = true;
        if (valueOf != null && valueOf.intValue() == 5) {
            AppCompatButton btnCnfrmContinueSet = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnCnfrmContinueSet);
            Intrinsics.checkNotNullExpressionValue(btnCnfrmContinueSet, "btnCnfrmContinueSet");
            ExtensionsKt.hide(btnCnfrmContinueSet);
            GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel2 != null && (geoFenceBody4 = geoFenceCommonModel2.getGeoFenceBody()) != null && (transitionType6 = geoFenceBody4.getTransitionType()) != null) {
                if (transitionType6.length() > 0) {
                    z3 = true;
                    if (!z3) {
                        GeoFenceCommonModel geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
                        if (geoFenceCommonModel3 != null && (geoFenceBody3 = geoFenceCommonModel3.getGeoFenceBody()) != null && (transitionType5 = geoFenceBody3.getTransitionType()) != null) {
                            isBlank3 = StringsKt__StringsJVMKt.isBlank(transitionType5);
                        }
                        z5 = false;
                        if (!z5) {
                            return;
                        }
                    }
                }
            }
            z3 = false;
            if (!z3) {
            }
        } else if (valueOf == null || valueOf.intValue() != 7) {
            if (valueOf != null && valueOf.intValue() == 6) {
                AppCompatButton btnCnfrmContinueSet2 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnCnfrmContinueSet);
                Intrinsics.checkNotNullExpressionValue(btnCnfrmContinueSet2, "btnCnfrmContinueSet");
                ExtensionsKt.show(btnCnfrmContinueSet2);
                GeoFenceCommonModel geoFenceCommonModel4 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel4 != null && (geoFenceGetResponse3 = geoFenceCommonModel4.getGeoFenceGetResponse()) != null && (transitionType2 = geoFenceGetResponse3.getTransitionType()) != null) {
                    if (transitionType2.length() > 0) {
                        z = true;
                        if (!z) {
                            GeoFenceCommonModel geoFenceCommonModel5 = getViewModel().getGeoFenceCommonModel();
                            if (geoFenceCommonModel5 != null && (geoFenceGetResponse2 = geoFenceCommonModel5.getGeoFenceGetResponse()) != null && (transitionType = geoFenceGetResponse2.getTransitionType()) != null) {
                                isBlank = StringsKt__StringsJVMKt.isBlank(transitionType);
                                if (!isBlank) {
                                    z4 = true;
                                }
                            }
                            if (!z4) {
                                return;
                            }
                        }
                        GeoFenceCommonModel geoFenceCommonModel6 = getViewModel().getGeoFenceCommonModel();
                        geoFenceType = geoFenceCommonModel6 == null ? geoFenceCommonModel6.getGeoFenceType() : null;
                        if (geoFenceType != null) {
                            GeoFenceCommonModel geoFenceCommonModel7 = getViewModel().getGeoFenceCommonModel();
                            if (geoFenceCommonModel7 != null && (geoFenceGetResponse = geoFenceCommonModel7.getGeoFenceGetResponse()) != null) {
                                str = geoFenceGetResponse.getTransitionType();
                            }
                            Intrinsics.checkNotNull(str);
                            geoFenceType.setFenceTransitionType(str);
                        }
                        setSelectionOfMode(true);
                        return;
                    }
                }
                z = false;
                if (!z) {
                }
                GeoFenceCommonModel geoFenceCommonModel62 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel62 == null) {
                }
                if (geoFenceType != null) {
                }
                setSelectionOfMode(true);
                return;
            }
            return;
        } else {
            AppCompatButton btnCnfrmContinueSet3 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnCnfrmContinueSet);
            Intrinsics.checkNotNullExpressionValue(btnCnfrmContinueSet3, "btnCnfrmContinueSet");
            ExtensionsKt.show(btnCnfrmContinueSet3);
            GeoFenceCommonModel geoFenceCommonModel8 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel8 != null && (geoFenceBody2 = geoFenceCommonModel8.getGeoFenceBody()) != null && (transitionType4 = geoFenceBody2.getTransitionType()) != null) {
                if (transitionType4.length() > 0) {
                    z2 = true;
                    if (!z2) {
                        GeoFenceCommonModel geoFenceCommonModel9 = getViewModel().getGeoFenceCommonModel();
                        if (geoFenceCommonModel9 != null && (geoFenceBody = geoFenceCommonModel9.getGeoFenceBody()) != null && (transitionType3 = geoFenceBody.getTransitionType()) != null) {
                            isBlank2 = StringsKt__StringsJVMKt.isBlank(transitionType3);
                        }
                        z5 = false;
                        if (!z5) {
                            return;
                        }
                    }
                }
            }
            z2 = false;
            if (!z2) {
            }
        }
        setSelectionOfMode(false);
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
        GeoFenceInfo selectedSetFenceFor;
        GeoFenceInfo selectedSetFenceFor2;
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnCnfrmContinueSet))) {
            Logger logger = Logger.INSTANCE;
            SetFenceForAdapter setFenceForAdapter = this.setFenceForAdapter;
            logger.e((setFenceForAdapter == null || (selectedSetFenceFor2 = setFenceForAdapter.getSelectedSetFenceFor()) == null) ? null : selectedSetFenceFor2.getTitle());
            if (validate()) {
                SetFenceForAdapter setFenceForAdapter2 = this.setFenceForAdapter;
                if (setFenceForAdapter2 != null && (selectedSetFenceFor = setFenceForAdapter2.getSelectedSetFenceFor()) != null) {
                    setTransitionTypeInModel(selectedSetFenceFor);
                }
                GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel != null) {
                    int fenceCreationMode = geoFenceCommonModel.getFenceCreationMode();
                    if (fenceCreationMode == 5) {
                        navigateBasedOnFenceType();
                    } else if (fenceCreationMode == 6 || fenceCreationMode == 7) {
                        GeoFenceActivity geoFenceActivity = this.parentActivity;
                        if (geoFenceActivity != null) {
                            geoFenceActivity.navigateToSummaryFragment();
                        }
                    } else {
                        logger.e("Fence Creation Mode: " + fenceCreationMode);
                    }
                }
            }
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
        return inflater.inflate(R.layout.fragment_set_fence_for, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setUIData();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initView();
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.OnSetFenceForSelect
    public void selectSetFenceFor(@NotNull GeoFenceInfo fenceFor) {
        Intrinsics.checkNotNullParameter(fenceFor, "fenceFor");
        Logger.INSTANCE.e(fenceFor.getTitle());
        setTransitionTypeInModel(fenceFor);
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        boolean z = false;
        if (geoFenceCommonModel != null && geoFenceCommonModel.getFenceCreationMode() == 5) {
            z = true;
        }
        if (z) {
            navigateBasedOnFenceType();
        }
    }

    public final boolean validate() {
        SetFenceForAdapter setFenceForAdapter = this.setFenceForAdapter;
        if ((setFenceForAdapter != null ? setFenceForAdapter.getSelectedSetFenceFor() : null) == null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(R.string.select_fence_for);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.select_fence_for)");
            ExtensionsKt.showToast(requireContext, string);
            return false;
        }
        return true;
    }
}
