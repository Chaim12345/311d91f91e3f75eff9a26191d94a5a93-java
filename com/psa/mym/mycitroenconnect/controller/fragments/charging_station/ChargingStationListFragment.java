package com.psa.mym.mycitroenconnect.controller.fragments.charging_station;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.MainActivity;
import com.psa.mym.mycitroenconnect.controller.activities.dashboard.ChargingStationDetailsActivity;
import com.psa.mym.mycitroenconnect.controller.adapters.NearbyChargingInterface;
import com.psa.mym.mycitroenconnect.controller.adapters.NearbyChargingStationAdapter;
import com.psa.mym.mycitroenconnect.model.NearbyStationModel;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ChargingStationListFragment extends Fragment implements TextWatcher, NearbyChargingInterface {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private MainActivity parentActivity;
    @Nullable
    private NearbyChargingStationAdapter stationAdapter;
    @Nullable
    private List<NearbyStationModel> stationDataList;
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

        @JvmStatic
        @NotNull
        public final ChargingStationListFragment newInstance() {
            ChargingStationListFragment chargingStationListFragment = new ChargingStationListFragment();
            chargingStationListFragment.setArguments(new Bundle());
            return chargingStationListFragment;
        }
    }

    private final void filterList(String str) {
        boolean contains$default;
        ArrayList<NearbyStationModel> arrayList = new ArrayList<>();
        List<NearbyStationModel> list = this.stationDataList;
        if (list == null || str == null) {
            return;
        }
        Intrinsics.checkNotNull(list);
        for (NearbyStationModel nearbyStationModel : list) {
            String lowerCase = nearbyStationModel.getStationName().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            contains$default = StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) str, false, 2, (Object) null);
            if (contains$default) {
                arrayList.add(nearbyStationModel);
            }
        }
        NearbyChargingStationAdapter nearbyChargingStationAdapter = this.stationAdapter;
        if (nearbyChargingStationAdapter != null) {
            nearbyChargingStationAdapter.updateStationList(arrayList);
        }
    }

    private final void initStationList() {
        List<NearbyStationModel> mutableListOf;
        mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(new NearbyStationModel("Murtajapur charging station", "0.3km", "₹ 5", "/unit", "Down street, Akshaya Nagar", "Open: 7.00 AM to 8 PM"), new NearbyStationModel("Delta charging station", "0.8km", "₹ 4.5", "/unit", "Down street, Akshaya Nagar", "Open: 7.00 AM to 8 PM"), new NearbyStationModel("Alpha charging station", "1.1km", "₹ 5", "/unit", "Down street, Akshaya Nagar", "Open: 7.00 AM to 8 PM"));
        this.stationDataList = mutableListOf;
        Objects.requireNonNull(mutableListOf, "null cannot be cast to non-null type java.util.ArrayList<com.psa.mym.mycitroenconnect.model.NearbyStationModel>{ kotlin.collections.TypeAliasesKt.ArrayList<com.psa.mym.mycitroenconnect.model.NearbyStationModel> }");
        this.stationAdapter = new NearbyChargingStationAdapter((ArrayList) mutableListOf, this);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvNearbyChargingStation);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(), 1, false));
        }
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
        }
        if (recyclerView == null) {
            return;
        }
        recyclerView.setAdapter(this.stationAdapter);
    }

    private final void initView() {
        AppCompatEditText appCompatEditText;
        int i2;
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String vehicleType = companion.getVehicleType(requireContext);
        this.vehicleType = vehicleType;
        if (Intrinsics.areEqual(vehicleType, AppConstants.ICE)) {
            appCompatEditText = (AppCompatEditText) _$_findCachedViewById(R.id.edSearchNearbyStation);
            i2 = uat.psa.mym.mycitroenconnect.R.string.hint_nearby_fuel_station;
        } else {
            appCompatEditText = (AppCompatEditText) _$_findCachedViewById(R.id.edSearchNearbyStation);
            i2 = uat.psa.mym.mycitroenconnect.R.string.hint_nearby_charging_station;
        }
        appCompatEditText.setHint(getString(i2));
        initStationList();
    }

    @JvmStatic
    @NotNull
    public static final ChargingStationListFragment newInstance() {
        return Companion.newInstance();
    }

    private final void removeListeners() {
        ((AppCompatEditText) _$_findCachedViewById(R.id.edSearchNearbyStation)).removeTextChangedListener(this);
    }

    private final void setListener() {
        int i2 = R.id.edSearchNearbyStation;
        ((AppCompatEditText) _$_findCachedViewById(i2)).addTextChangedListener(this);
        AppCompatEditText edSearchNearbyStation = (AppCompatEditText) _$_findCachedViewById(i2);
        Intrinsics.checkNotNullExpressionValue(edSearchNearbyStation, "edSearchNearbyStation");
        ExtensionsKt.onRightDrawableClicked(edSearchNearbyStation, new ChargingStationListFragment$setListener$1(this));
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

    @Override // android.text.TextWatcher
    public void afterTextChanged(@Nullable Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
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
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_charging_station_list, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.NearbyChargingInterface
    public void onItemClick(@NotNull NearbyStationModel objNearbyCharging) {
        Intrinsics.checkNotNullParameter(objNearbyCharging, "objNearbyCharging");
        Intent intent = new Intent(requireContext(), ChargingStationDetailsActivity.class);
        intent.putExtra(AppConstants.ARG_CHARGING_STATION_DETAILS, objNearbyCharging);
        startActivity(intent);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        MainActivity mainActivity;
        super.onResume();
        if (getActivity() == null || !(getActivity() instanceof MainActivity)) {
            mainActivity = null;
        } else {
            FragmentActivity activity = getActivity();
            Objects.requireNonNull(activity, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.controller.activities.MainActivity");
            mainActivity = (MainActivity) activity;
        }
        this.parentActivity = mainActivity;
        if (mainActivity != null) {
            mainActivity.showHeader();
        }
        initView();
        setListener();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        removeListeners();
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
        filterList(charSequence != null ? charSequence.toString() : null);
    }
}
