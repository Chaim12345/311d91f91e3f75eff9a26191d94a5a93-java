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
import com.psa.mym.mycitroenconnect.controller.adapters.ChooseGeoFenceTypeAdapter;
import com.psa.mym.mycitroenconnect.controller.adapters.OnGeoFenceTypeSelect;
import com.psa.mym.mycitroenconnect.controller.viewmodel.GeoFenceViewModel;
import com.psa.mym.mycitroenconnect.model.GeoFenceCommonModel;
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
public final class ChooseGeoFenceTypeFragment extends Fragment implements View.OnClickListener, OnGeoFenceTypeSelect {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private ChooseGeoFenceTypeAdapter geoFenceTypeAdapter;
    @Nullable
    private GeoFenceActivity parentActivity;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private ArrayList<GeoFenceType> fenceTypes = new ArrayList<>();
    @NotNull
    private final Lazy viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(GeoFenceViewModel.class), new ChooseGeoFenceTypeFragment$special$$inlined$activityViewModels$default$1(this), new ChooseGeoFenceTypeFragment$special$$inlined$activityViewModels$default$2(this));

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final ChooseGeoFenceTypeFragment newInstance() {
            ChooseGeoFenceTypeFragment chooseGeoFenceTypeFragment = new ChooseGeoFenceTypeFragment();
            chooseGeoFenceTypeFragment.setArguments(new Bundle());
            return chooseGeoFenceTypeFragment;
        }
    }

    private final GeoFenceViewModel getViewModel() {
        return (GeoFenceViewModel) this.viewModel$delegate.getValue();
    }

    private final void initView() {
        GeoFenceActivity geoFenceActivity;
        ArrayList<GeoFenceType> arrayListOf;
        if (getActivity() == null || !(getActivity() instanceof GeoFenceActivity)) {
            geoFenceActivity = null;
        } else {
            FragmentActivity activity = getActivity();
            Objects.requireNonNull(activity, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.controller.activities.dashboard.GeoFenceActivity");
            geoFenceActivity = (GeoFenceActivity) activity;
        }
        this.parentActivity = geoFenceActivity;
        String string = getString(R.string.radius_fence);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.radius_fence)");
        String string2 = getString(R.string.custom_fence);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.custom_fence)");
        String string3 = getString(R.string.route_fence);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.route_fence)");
        arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(new GeoFenceType(0, string, R.drawable.ic_radius_fence_map, false, null, 0.0d, 48, null), new GeoFenceType(1, string2, R.drawable.ic_custom_fence_map, false, null, 0.0d, 48, null), new GeoFenceType(2, string3, R.drawable.ic_route_fence_map, false, null, 0.0d, 48, null));
        this.fenceTypes = arrayListOf;
        ChooseGeoFenceTypeAdapter chooseGeoFenceTypeAdapter = new ChooseGeoFenceTypeAdapter(arrayListOf);
        this.geoFenceTypeAdapter = chooseGeoFenceTypeAdapter;
        chooseGeoFenceTypeAdapter.setonGeoFenceTypeSelect(this);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rvFenceType);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(this.geoFenceTypeAdapter);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnCnfrmContinue)).setOnClickListener(this);
    }

    @JvmStatic
    @NotNull
    public static final ChooseGeoFenceTypeFragment newInstance() {
        return Companion.newInstance();
    }

    private final void setFenceType(String str) {
        GeoFenceBody geoFenceBody;
        String str2;
        if (Intrinsics.areEqual(str, getString(R.string.radius_fence))) {
            GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
            geoFenceBody = geoFenceCommonModel != null ? geoFenceCommonModel.getGeoFenceBody() : null;
            if (geoFenceBody == null) {
                return;
            }
            str2 = AppConstants.GEO_FENCE_MODE_RADIUS;
        } else if (Intrinsics.areEqual(str, getString(R.string.custom_fence))) {
            GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
            geoFenceBody = geoFenceCommonModel2 != null ? geoFenceCommonModel2.getGeoFenceBody() : null;
            if (geoFenceBody == null) {
                return;
            }
            str2 = AppConstants.GEO_FENCE_MODE_CUSTOM;
        } else if (!Intrinsics.areEqual(str, getString(R.string.route_fence))) {
            return;
        } else {
            GeoFenceCommonModel geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
            geoFenceBody = geoFenceCommonModel3 != null ? geoFenceCommonModel3.getGeoFenceBody() : null;
            if (geoFenceBody == null) {
                return;
            }
            str2 = AppConstants.GEO_FENCE_MODE_ROUTE;
        }
        geoFenceBody.setFenceMode(str2);
    }

    @SuppressLint({"NotifyDataSetChanged"})
    private final void setSelectionOfFence(boolean z) {
        GeoFenceType geoFenceType;
        GetGeoFenceResponseItem geoFenceGetResponse;
        GeoFenceBody geoFenceBody;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        String str = null;
        String fenceMode = (geoFenceCommonModel == null || (geoFenceBody = geoFenceCommonModel.getGeoFenceBody()) == null) ? null : geoFenceBody.getFenceMode();
        if (z) {
            GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel2 != null && (geoFenceGetResponse = geoFenceCommonModel2.getGeoFenceGetResponse()) != null) {
                str = geoFenceGetResponse.getFenceMode();
            }
            fenceMode = str;
        }
        for (GeoFenceType geoFenceType2 : this.fenceTypes) {
            geoFenceType2.setSelected(false);
        }
        if (fenceMode != null) {
            int hashCode = fenceMode.hashCode();
            if (hashCode != -1349088399) {
                if (hashCode != -938578798) {
                    if (hashCode == 108704329 && fenceMode.equals(AppConstants.GEO_FENCE_MODE_ROUTE)) {
                        geoFenceType = this.fenceTypes.get(2);
                        geoFenceType.setSelected(true);
                    }
                } else if (fenceMode.equals(AppConstants.GEO_FENCE_MODE_RADIUS)) {
                    geoFenceType = this.fenceTypes.get(0);
                    geoFenceType.setSelected(true);
                }
            } else if (fenceMode.equals(AppConstants.GEO_FENCE_MODE_CUSTOM)) {
                geoFenceType = this.fenceTypes.get(1);
                geoFenceType.setSelected(true);
            }
        }
        ChooseGeoFenceTypeAdapter chooseGeoFenceTypeAdapter = this.geoFenceTypeAdapter;
        if (chooseGeoFenceTypeAdapter != null) {
            chooseGeoFenceTypeAdapter.notifyDataSetChanged();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x008c, code lost:
        if ((!r0) == true) goto L35;
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00d5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void setUIData() {
        boolean z;
        GetGeoFenceResponseItem geoFenceGetResponse;
        String fenceMode;
        boolean isBlank;
        GetGeoFenceResponseItem geoFenceGetResponse2;
        String fenceMode2;
        boolean z2;
        GeoFenceType geoFenceType;
        String fenceType;
        boolean isBlank2;
        GeoFenceType geoFenceType2;
        String fenceType2;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        Integer valueOf = geoFenceCommonModel != null ? Integer.valueOf(geoFenceCommonModel.getFenceCreationMode()) : null;
        if (valueOf != null && valueOf.intValue() == 5) {
            AppCompatButton btnCnfrmContinue = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnCnfrmContinue);
            Intrinsics.checkNotNullExpressionValue(btnCnfrmContinue, "btnCnfrmContinue");
            ExtensionsKt.hide(btnCnfrmContinue);
            return;
        }
        boolean z3 = false;
        boolean z4 = true;
        if (valueOf != null && valueOf.intValue() == 7) {
            AppCompatButton btnCnfrmContinue2 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnCnfrmContinue);
            Intrinsics.checkNotNullExpressionValue(btnCnfrmContinue2, "btnCnfrmContinue");
            ExtensionsKt.show(btnCnfrmContinue2);
            GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel2 != null && (geoFenceType2 = geoFenceCommonModel2.getGeoFenceType()) != null && (fenceType2 = geoFenceType2.getFenceType()) != null) {
                if (fenceType2.length() > 0) {
                    z2 = true;
                    if (!z2) {
                        GeoFenceCommonModel geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
                        if (geoFenceCommonModel3 != null && (geoFenceType = geoFenceCommonModel3.getGeoFenceType()) != null && (fenceType = geoFenceType.getFenceType()) != null) {
                            isBlank2 = StringsKt__StringsJVMKt.isBlank(fenceType);
                        }
                        z4 = false;
                        if (!z4) {
                            return;
                        }
                    }
                    setSelectionOfFence(false);
                }
            }
            z2 = false;
            if (!z2) {
            }
            setSelectionOfFence(false);
        } else if (valueOf != null && valueOf.intValue() == 6) {
            AppCompatButton btnCnfrmContinue3 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnCnfrmContinue);
            Intrinsics.checkNotNullExpressionValue(btnCnfrmContinue3, "btnCnfrmContinue");
            ExtensionsKt.show(btnCnfrmContinue3);
            GeoFenceCommonModel geoFenceCommonModel4 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel4 != null && (geoFenceGetResponse2 = geoFenceCommonModel4.getGeoFenceGetResponse()) != null && (fenceMode2 = geoFenceGetResponse2.getFenceMode()) != null) {
                if (fenceMode2.length() > 0) {
                    z = true;
                    if (!z) {
                        GeoFenceCommonModel geoFenceCommonModel5 = getViewModel().getGeoFenceCommonModel();
                        if (geoFenceCommonModel5 != null && (geoFenceGetResponse = geoFenceCommonModel5.getGeoFenceGetResponse()) != null && (fenceMode = geoFenceGetResponse.getFenceMode()) != null) {
                            isBlank = StringsKt__StringsJVMKt.isBlank(fenceMode);
                            if (!isBlank) {
                                z3 = true;
                            }
                        }
                        if (!z3) {
                            return;
                        }
                    }
                    setSelectionOfFence(true);
                }
            }
            z = false;
            if (!z) {
            }
            setSelectionOfFence(true);
        }
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
        GeoFenceCommonModel geoFenceCommonModel;
        GeoFenceCommonModel geoFenceCommonModel2;
        if (!Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnCnfrmContinue)) || (geoFenceCommonModel = getViewModel().getGeoFenceCommonModel()) == null) {
            return;
        }
        int fenceCreationMode = geoFenceCommonModel.getFenceCreationMode();
        if (fenceCreationMode != 5) {
            if (fenceCreationMode == 6) {
                GeoFenceActivity geoFenceActivity = this.parentActivity;
                if (geoFenceActivity != null) {
                    geoFenceActivity.navigateToSummaryFragment();
                    return;
                }
                return;
            } else if (fenceCreationMode != 7) {
                Logger logger = Logger.INSTANCE;
                logger.e("Fence Creation Mode: " + fenceCreationMode);
                return;
            }
        }
        if (fenceCreationMode == 7 && (geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel()) != null) {
            geoFenceCommonModel2.setFenceCreationMode(5);
        }
        GeoFenceActivity geoFenceActivity2 = this.parentActivity;
        if (geoFenceActivity2 != null) {
            geoFenceActivity2.navigateToSetFenceFragment();
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
        return inflater.inflate(R.layout.fragment_choose_geo_fence_type, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    @SuppressLint({"NotifyDataSetChanged"})
    public void onResume() {
        super.onResume();
        if (getViewModel().getSelectedFenceId() != -1) {
            int i2 = 0;
            for (Object obj : this.fenceTypes) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                ((GeoFenceType) obj).setSelected(i2 == getViewModel().getSelectedFenceId());
                i2 = i3;
            }
            ChooseGeoFenceTypeAdapter chooseGeoFenceTypeAdapter = this.geoFenceTypeAdapter;
            if (chooseGeoFenceTypeAdapter != null) {
                chooseGeoFenceTypeAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initView();
        setUIData();
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.OnGeoFenceTypeSelect
    public void selectedGeoFenceType(@NotNull GeoFenceType fenceType) {
        GeoFenceActivity geoFenceActivity;
        Intrinsics.checkNotNullParameter(fenceType, "fenceType");
        Logger.INSTANCE.e(fenceType.toString());
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel != null) {
            geoFenceCommonModel.setGeoFenceType(fenceType);
        }
        getViewModel().setSelectedFenceId(fenceType.getFenceId());
        setFenceType(fenceType.getFenceType());
        GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
        boolean z = false;
        if (geoFenceCommonModel2 != null && geoFenceCommonModel2.getFenceCreationMode() == 5) {
            z = true;
        }
        if (!z || (geoFenceActivity = this.parentActivity) == null) {
            return;
        }
        geoFenceActivity.navigateToSetFenceFragment();
    }

    public final boolean validate() {
        if (getViewModel().getSelectedFenceId() == -1) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(R.string.select_at_least_one_fence);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.select_at_least_one_fence)");
            ExtensionsKt.showToast(requireContext, string);
            return false;
        }
        return true;
    }
}
