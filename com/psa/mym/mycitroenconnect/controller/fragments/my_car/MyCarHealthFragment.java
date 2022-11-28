package com.psa.mym.mycitroenconnect.controller.fragments.my_car;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.BusBaseFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.adapters.MyCarHealthAdapter;
import com.psa.mym.mycitroenconnect.controller.adapters.MyCarStatusAdapter;
import com.psa.mym.mycitroenconnect.controller.adapters.MyVehicleHealthAdapter;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.FailResponse;
import com.psa.mym.mycitroenconnect.model.VehicleStatus;
import com.psa.mym.mycitroenconnect.model.my_car.MyCarHealth;
import com.psa.mym.mycitroenconnect.model.my_car.MyCarStatus;
import com.psa.mym.mycitroenconnect.model.my_car.MyVehicleStatus;
import com.psa.mym.mycitroenconnect.model.trip.DrivingBehaviourResponse;
import com.psa.mym.mycitroenconnect.model.trip.FuelReportResponse;
import com.psa.mym.mycitroenconnect.services.SnapShotService;
import com.psa.mym.mycitroenconnect.services.TripService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.CustomSpinner;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.Skeleton;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayoutUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt__MathJVMKt;
import kotlin.text.StringsKt__StringsJVMKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class MyCarHealthFragment extends BusBaseFragment implements View.OnClickListener {
    @Nullable
    private CarFragment mCarFragment;
    @Nullable
    private MyVehicleHealthAdapter myVehicleHealthAdapter;
    @Nullable
    private MyCarStatusAdapter myVehicleStatusAdapter;
    @Nullable
    private ArrayAdapter<String> periodAdapter;
    @Nullable
    private View rootView;
    @Nullable
    private RecyclerView rvVehicleHealth;
    @Nullable
    private RecyclerView rvVehicleStatus;
    private Skeleton skeleton;
    private MyCarHealthAdapter vehicleHealthAdapter;
    private ArrayList<MyCarHealth> vehicleHealthDetailsArray;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private List<MyCarStatus> mCarStatusList = new ArrayList();
    @NotNull
    private List<MyVehicleStatus> mCarEventList = new ArrayList();
    @NotNull
    private ArrayList<String> periods = new ArrayList<>();
    @NotNull
    private String type = "";
    @NotNull
    private String startDate = "";
    @NotNull
    private String endDate = "";

    private final void displayData() {
        Skeleton skeleton = this.skeleton;
        if (skeleton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skeleton");
            skeleton = null;
        }
        ExtensionsKt.showData$default(skeleton, 0L, 1, null);
        CarFragment carFragment = this.mCarFragment;
        if (carFragment != null) {
            carFragment.displayData();
        }
    }

    private final void displayLoading() {
        ConstraintLayout clParent = (ConstraintLayout) _$_findCachedViewById(R.id.clParent);
        Intrinsics.checkNotNullExpressionValue(clParent, "clParent");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Skeleton createSkeleton = SkeletonLayoutUtils.createSkeleton(clParent, ExtensionsKt.skeletonConfig(requireContext));
        createSkeleton.showSkeleton();
        this.skeleton = createSkeleton;
    }

    private final void getSnapShotVehicleStatus() {
        SnapShotService snapShotService = new SnapShotService();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        snapShotService.getVehicleStatus(requireContext, companion.getVinNumber(requireContext2));
    }

    private final void getTripFuelReport() {
        TripService tripService = new TripService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        SharedPref.Companion companion = SharedPref.Companion;
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        tripService.callGetFuelReport(requireActivity, companion.getVinNumber(requireActivity2), this.startDate + " 00:00:01", this.endDate + " 23:59:59");
    }

    private final void getTripSummary() {
        displayLoading();
        TripService tripService = new TripService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        tripService.callGetTripSummaryAPI(requireActivity, companion.getVinNumber(requireContext), this.startDate + " 00:00:01", this.endDate + " 23:59:59");
    }

    private final void initPeriodSpinner() {
        ArrayList<String> arrayListOf;
        AppUtil.Companion companion = AppUtil.Companion;
        arrayListOf = CollectionsKt__CollectionsKt.arrayListOf("This month", companion.getPastMonth(1), companion.getPastMonth(2), companion.getPastMonth(3), companion.getPastMonth(4), companion.getPastMonth(5), companion.getPastMonth(6));
        this.periods = arrayListOf;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireActivity(), (int) uat.psa.mym.mycitroenconnect.R.layout.layout_curom_spinner_item, this.periods);
        arrayAdapter.setDropDownViewResource(uat.psa.mym.mycitroenconnect.R.layout.layout_curom_spinner_item);
        int i2 = R.id.spPeriod;
        ((CustomSpinner) _$_findCachedViewById(i2)).setAdapter((SpinnerAdapter) arrayAdapter);
        this.periodAdapter = arrayAdapter;
        ((CustomSpinner) _$_findCachedViewById(i2)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.my_car.MyCarHealthFragment$initPeriodSpinner$2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(@Nullable AdapterView<?> adapterView, @Nullable View view, int i3, long j2) {
                ArrayList arrayList;
                Pair<String, String> currentMonthDates;
                AppUtil.Companion companion2;
                int i4;
                StringBuilder sb = new StringBuilder();
                sb.append('(');
                arrayList = MyCarHealthFragment.this.periods;
                sb.append((String) arrayList.get(i3));
                sb.append(')');
                ((AppCompatTextView) MyCarHealthFragment.this._$_findCachedViewById(R.id.tvChargingPeriod)).setText(sb.toString());
                switch (i3) {
                    case 0:
                        MyCarHealthFragment.this.type = "Month";
                        currentMonthDates = AppUtil.Companion.getCurrentMonthDates();
                        break;
                    case 1:
                        MyCarHealthFragment.this.type = "Month";
                        companion2 = AppUtil.Companion;
                        i4 = 1;
                        currentMonthDates = companion2.getPastMonthDates(i4);
                        break;
                    case 2:
                        MyCarHealthFragment.this.type = "Month";
                        companion2 = AppUtil.Companion;
                        i4 = 2;
                        currentMonthDates = companion2.getPastMonthDates(i4);
                        break;
                    case 3:
                        MyCarHealthFragment.this.type = "Month";
                        companion2 = AppUtil.Companion;
                        i4 = 3;
                        currentMonthDates = companion2.getPastMonthDates(i4);
                        break;
                    case 4:
                        MyCarHealthFragment.this.type = "Month";
                        companion2 = AppUtil.Companion;
                        i4 = 4;
                        currentMonthDates = companion2.getPastMonthDates(i4);
                        break;
                    case 5:
                        MyCarHealthFragment.this.type = "Month";
                        companion2 = AppUtil.Companion;
                        i4 = 5;
                        currentMonthDates = companion2.getPastMonthDates(i4);
                        break;
                    case 6:
                        MyCarHealthFragment.this.type = "Month";
                        companion2 = AppUtil.Companion;
                        i4 = 6;
                        currentMonthDates = companion2.getPastMonthDates(i4);
                        break;
                    default:
                        return;
                }
                MyCarHealthFragment.this.startDate = currentMonthDates.getFirst();
                MyCarHealthFragment.this.endDate = currentMonthDates.getSecond();
                MyCarHealthFragment.this.updateHealthDetails();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(@Nullable AdapterView<?> adapterView) {
            }
        });
    }

    private final void initVehicleHealth() {
        MyVehicleHealthAdapter myVehicleHealthAdapter = this.myVehicleHealthAdapter;
        if (myVehicleHealthAdapter != null) {
            if (myVehicleHealthAdapter != null) {
                myVehicleHealthAdapter.updateList(this.mCarEventList);
                return;
            }
            return;
        }
        this.myVehicleHealthAdapter = new MyVehicleHealthAdapter(this.mCarEventList);
        View view = this.rootView;
        RecyclerView recyclerView = view != null ? (RecyclerView) view.findViewById(uat.psa.mym.mycitroenconnect.R.id.rvVehicleHealth) : null;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        }
        if (recyclerView != null) {
            recyclerView.setAdapter(this.myVehicleHealthAdapter);
        }
        this.rvVehicleHealth = recyclerView;
    }

    private final void initVehicleHealthDetails() {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvVehicleHealthDetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(this.myVehicleHealthAdapter);
    }

    private final void initVehicleStatus() {
        this.myVehicleStatusAdapter = new MyCarStatusAdapter(this.mCarStatusList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.my_car.MyCarHealthFragment$initVehicleStatus$1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i2) {
                return (i2 == 0 || i2 == 1 || i2 == 2) ? 2 : 1;
            }
        });
        View view = this.rootView;
        RecyclerView recyclerView = view != null ? (RecyclerView) view.findViewById(uat.psa.mym.mycitroenconnect.R.id.rvVehicleStatus) : null;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(gridLayoutManager);
        }
        if (recyclerView != null) {
            recyclerView.setAdapter(this.myVehicleStatusAdapter);
        }
        this.rvVehicleStatus = recyclerView;
    }

    private final void initViews() {
        initPeriodSpinner();
        initVehicleHealth();
        initVehicleStatus();
        initVehicleHealthDetails();
        getSnapShotVehicleStatus();
    }

    private final int isCarStatusExist(String str) {
        List<MyCarStatus> list = this.mCarStatusList;
        if (list == null || !(!list.isEmpty()) || this.mCarStatusList.size() <= 1) {
            return -1;
        }
        int i2 = 0;
        for (Object obj : this.mCarStatusList) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            if (Intrinsics.areEqual(((MyCarStatus) obj).getTitle(), str)) {
                return i2;
            }
            i2 = i3;
        }
        return -1;
    }

    private final void prepareCarEventList(DrivingBehaviourResponse drivingBehaviourResponse) {
        List<MyVehicleStatus> mutableListOf;
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvEventsLbl)).setVisibility(0);
        String string = getString(uat.psa.mym.mycitroenconnect.R.string.hard_braking);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.hard_braking)");
        String string2 = getString(uat.psa.mym.mycitroenconnect.R.string.harsh_acceleration);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.harsh_acceleration)");
        String string3 = getString(uat.psa.mym.mycitroenconnect.R.string.over_speed);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.over_speed)");
        mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(new MyVehicleStatus(string, String.valueOf((int) drivingBehaviourResponse.getHardBrake()), uat.psa.mym.mycitroenconnect.R.color.special_color_1, uat.psa.mym.mycitroenconnect.R.drawable.ic_disc_brake), new MyVehicleStatus(string2, String.valueOf((int) drivingBehaviourResponse.getAggressiveAcceleration()), uat.psa.mym.mycitroenconnect.R.color.special_color_3, uat.psa.mym.mycitroenconnect.R.drawable.ic_harsh_acceleration), new MyVehicleStatus(string3, String.valueOf((int) drivingBehaviourResponse.getHighSpeed()), uat.psa.mym.mycitroenconnect.R.color.pastel_yellow, uat.psa.mym.mycitroenconnect.R.drawable.ic_over_speed));
        this.mCarEventList = mutableListOf;
    }

    private final void resetUIView() {
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvEventsLbl)).setVisibility(8);
        this.mCarEventList.clear();
        initVehicleHealth();
        ((CardView) _$_findCachedViewById(R.id.cvTotalDriveTime)).setVisibility(8);
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvVehicleHealthLbl)).setVisibility(8);
        this.mCarStatusList.clear();
        updateCarStatusList();
    }

    private final void updateAvgVehicleSpeed(String str) {
        String string = getString(uat.psa.mym.mycitroenconnect.R.string.label_avg_speed);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.label_avg_speed)");
        int isCarStatusExist = isCarStatusExist(string);
        if (isCarStatusExist != -1) {
            this.mCarStatusList.get(isCarStatusExist).setValue(str);
            return;
        }
        List<MyCarStatus> list = this.mCarStatusList;
        String string2 = getString(uat.psa.mym.mycitroenconnect.R.string.label_avg_speed);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.label_avg_speed)");
        String string3 = getString(uat.psa.mym.mycitroenconnect.R.string.km_per_hr);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.km_per_hr)");
        list.add(new MyCarStatus(string2, str, string3, uat.psa.mym.mycitroenconnect.R.drawable.ic_avg_speed));
    }

    private final void updateCarStatusList() {
        MyCarStatusAdapter myCarStatusAdapter = this.myVehicleStatusAdapter;
        if (myCarStatusAdapter != null) {
            myCarStatusAdapter.updateList(this.mCarStatusList);
        }
    }

    private final void updateDistanceValue(String str) {
        String string = getString(uat.psa.mym.mycitroenconnect.R.string.distance);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.distance)");
        int isCarStatusExist = isCarStatusExist(string);
        if (isCarStatusExist != -1) {
            this.mCarStatusList.get(isCarStatusExist).setValue(str);
            return;
        }
        List<MyCarStatus> list = this.mCarStatusList;
        String string2 = getString(uat.psa.mym.mycitroenconnect.R.string.distance);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.distance)");
        String string3 = getString(uat.psa.mym.mycitroenconnect.R.string.lbl_kms_value);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.lbl_kms_value)");
        list.add(new MyCarStatus(string2, str, string3, uat.psa.mym.mycitroenconnect.R.drawable.ic_range));
    }

    private final void updateEnergyEfficiency(double d2) {
        String string;
        String str;
        String string2;
        String str2;
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (Intrinsics.areEqual(companion.getVehicleType(requireContext), AppConstants.ICE)) {
            string = getString(uat.psa.mym.mycitroenconnect.R.string.average_fuel_economy);
            str = "{\n                getStr…el_economy)\n            }";
        } else {
            string = getString(uat.psa.mym.mycitroenconnect.R.string.average_power_consumption);
            str = "{\n                getStr…onsumption)\n            }";
        }
        Intrinsics.checkNotNullExpressionValue(string, str);
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        if (Intrinsics.areEqual(companion.getVehicleType(requireContext2), AppConstants.ICE)) {
            string2 = getString(uat.psa.mym.mycitroenconnect.R.string.kmpl);
            str2 = "{\n                getStr…tring.kmpl)\n            }";
        } else {
            string2 = getString(uat.psa.mym.mycitroenconnect.R.string.kwh_km);
            str2 = "{\n                getStr…ing.kwh_km)\n            }";
        }
        Intrinsics.checkNotNullExpressionValue(string2, str2);
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        int i2 = Intrinsics.areEqual(companion.getVehicleType(requireContext3), AppConstants.ICE) ? uat.psa.mym.mycitroenconnect.R.drawable.ic_current_fuel_level : uat.psa.mym.mycitroenconnect.R.drawable.ic_energy;
        int isCarStatusExist = isCarStatusExist(string);
        Context requireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
        String twoDecimal = !Intrinsics.areEqual(companion.getVehicleType(requireContext4), AppConstants.ICE) ? ExtensionsKt.toTwoDecimal(d2 / 1000) : String.valueOf(d2);
        if (isCarStatusExist == -1) {
            this.mCarStatusList.add(new MyCarStatus(string, twoDecimal, string2, i2));
        } else {
            this.mCarStatusList.get(isCarStatusExist).setValue(twoDecimal);
        }
    }

    private final void updateTotalTimeTravelled(double d2) {
        ((CardView) _$_findCachedViewById(R.id.cvTotalDriveTime)).setVisibility(0);
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvVehicleHealthLbl)).setVisibility(0);
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvHour)).setText(AppUtil.Companion.convertSeconds((long) (d2 * 60)));
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment
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
        boolean isBlank;
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        if (event.getApiName().length() > 0) {
            isBlank = StringsKt__StringsJVMKt.isBlank(event.getApiName());
            if (!isBlank) {
                if (Intrinsics.areEqual(event.getApiName(), "summary")) {
                    displayData();
                    if (event.getStatusCode() == 204) {
                        resetUIView();
                    }
                    getTripFuelReport();
                    return;
                }
                return;
            }
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        ExtensionsKt.showToast(requireActivity, event.getMsg());
    }

    @Subscribe
    public final void getMessage(@NotNull FailResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        displayData();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        ExtensionsKt.showToast(requireActivity, event.getMessage());
    }

    @Subscribe
    public final void getMessage(@NotNull DrivingBehaviourResponse event) {
        int roundToInt;
        Intrinsics.checkNotNullParameter(event, "event");
        prepareCarEventList(event);
        initVehicleHealth();
        event.getDistanceTravelled();
        updateDistanceValue(ExtensionsKt.toOneDecimal(event.getDistanceTravelled()));
        event.getAvgVehicleSpeed();
        roundToInt = MathKt__MathJVMKt.roundToInt(event.getAvgVehicleSpeed());
        updateAvgVehicleSpeed(String.valueOf(roundToInt));
        updateCarStatusList();
        updateTotalTimeTravelled(event.getTotalTimeTravelled());
        getTripFuelReport();
    }

    @Subscribe
    public final void getMessage(@NotNull FuelReportResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        displayData();
        updateEnergyEfficiency(event.getMileage());
        updateCarStatusList();
    }

    @Subscribe
    public final void getResponse(@NotNull VehicleStatus response) {
        Intrinsics.checkNotNullParameter(response, "response");
        displayData();
        if (response.getVehicleStateHealth() == null) {
            ((RecyclerView) _$_findCachedViewById(R.id.rvVehicleHealthDetails)).setVisibility(8);
        }
        HashMap<String, String> vehicleStateHealth = response.getVehicleStateHealth();
        this.vehicleHealthDetailsArray = new ArrayList<>();
        MyCarHealthAdapter myCarHealthAdapter = null;
        if (vehicleStateHealth != null) {
            for (Map.Entry<String, String> entry : vehicleStateHealth.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                ArrayList<MyCarHealth> arrayList = this.vehicleHealthDetailsArray;
                if (arrayList == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("vehicleHealthDetailsArray");
                    arrayList = null;
                }
                arrayList.add(new MyCarHealth(key, value));
            }
        }
        ArrayList<MyCarHealth> arrayList2 = this.vehicleHealthDetailsArray;
        if (arrayList2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vehicleHealthDetailsArray");
            arrayList2 = null;
        }
        this.vehicleHealthDetailsArray = arrayList2;
        int i2 = R.id.rvVehicleHealthDetails;
        ((RecyclerView) _$_findCachedViewById(i2)).setVisibility(0);
        MyCarHealthAdapter myCarHealthAdapter2 = new MyCarHealthAdapter();
        this.vehicleHealthAdapter = myCarHealthAdapter2;
        ArrayList<MyCarHealth> arrayList3 = this.vehicleHealthDetailsArray;
        if (arrayList3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vehicleHealthDetailsArray");
            arrayList3 = null;
        }
        myCarHealthAdapter2.setVehicleHealthList(arrayList3);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i2);
        MyCarHealthAdapter myCarHealthAdapter3 = this.vehicleHealthAdapter;
        if (myCarHealthAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vehicleHealthAdapter");
        } else {
            myCarHealthAdapter = myCarHealthAdapter3;
        }
        recyclerView.setAdapter(myCarHealthAdapter);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_my_car_health, viewGroup, false);
        this.rootView = inflate;
        return inflate;
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        CarFragment carFragment;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        if (getParentFragment() == null || !(getParentFragment() instanceof CarFragment)) {
            carFragment = null;
        } else {
            Fragment parentFragment = getParentFragment();
            Objects.requireNonNull(parentFragment, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.controller.fragments.my_car.CarFragment");
            carFragment = (CarFragment) parentFragment;
        }
        this.mCarFragment = carFragment;
        initViews();
    }

    public final void updateHealthDetails() {
        boolean isBlank;
        boolean isBlank2;
        Logger logger = Logger.INSTANCE;
        logger.e("Start Date: " + this.startDate);
        logger.e("End Date: " + this.endDate);
        if (Intrinsics.areEqual(this.startDate, "")) {
            return;
        }
        isBlank = StringsKt__StringsJVMKt.isBlank(this.startDate);
        if (!isBlank) {
            if (!(this.startDate.length() > 0) || Intrinsics.areEqual(this.endDate, "")) {
                return;
            }
            isBlank2 = StringsKt__StringsJVMKt.isBlank(this.endDate);
            if (!isBlank2) {
                if (this.endDate.length() > 0) {
                    getTripSummary();
                }
            }
        }
    }
}
