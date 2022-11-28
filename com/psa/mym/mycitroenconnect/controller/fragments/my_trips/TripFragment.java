package com.psa.mym.mycitroenconnect.controller.fragments.my_trips;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.psa.mym.mycitroenconnect.BusBaseFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.trips.BatteryUsageActivity;
import com.psa.mym.mycitroenconnect.controller.adapters.MyTripsCardAdapter;
import com.psa.mym.mycitroenconnect.controller.adapters.view_pager.CommonChildViewPagerAdapter;
import com.psa.mym.mycitroenconnect.controller.dialog.DateRangePickerDialog;
import com.psa.mym.mycitroenconnect.controller.dialog.OnDateSelectListener;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.FailResponse;
import com.psa.mym.mycitroenconnect.model.TripCardData;
import com.psa.mym.mycitroenconnect.model.dashboard.DashboardResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.StoredDashboardData;
import com.psa.mym.mycitroenconnect.model.trip.FuelReportResponse;
import com.psa.mym.mycitroenconnect.services.DashboardService;
import com.psa.mym.mycitroenconnect.services.TripService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.CustomSpinner;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.Skeleton;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayoutUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt__CharKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class TripFragment extends BusBaseFragment implements View.OnClickListener, TabLayout.OnTabSelectedListener {
    @Nullable
    private MyTripsCardAdapter cardAdapter;
    private Skeleton cardSkeleton;
    @Nullable
    private Long customEndDateSelection;
    @Nullable
    private Long customStartDateSelection;
    @Nullable
    private DashboardResponse dashboardResponse;
    private boolean isRefreshing;
    private long lastClickTime;
    @Nullable
    private View mainView;
    @Nullable
    private ArrayAdapter<String> periodAdapter;
    @Nullable
    private RecyclerView recyclerView;
    private Skeleton skeleton;
    private AdapterView.OnItemSelectedListener spinnerListener;
    private int spinnerSelectionPos;
    @Nullable
    private TabLayout tabLayout;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private ArrayList<String> periods = new ArrayList<>();

    private final void animateUpdateButton() {
        AppCompatImageView ivTripRefresh = (AppCompatImageView) _$_findCachedViewById(R.id.ivTripRefresh);
        Intrinsics.checkNotNullExpressionValue(ivTripRefresh, "ivTripRefresh");
        ExtensionsKt.rotateAnimation$default(ivTripRefresh, 0L, 0, new TripFragment$animateUpdateButton$1(this), TripFragment$animateUpdateButton$2.INSTANCE, TripFragment$animateUpdateButton$3.INSTANCE, 3, null);
    }

    private final void displayData() {
        if (this.skeleton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skeleton");
        }
        Skeleton skeleton = this.skeleton;
        if (skeleton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skeleton");
            skeleton = null;
        }
        ExtensionsKt.showData$default(skeleton, 0L, 1, null);
    }

    private final void displayFuelData() {
        if (this.cardSkeleton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardSkeleton");
        }
        Skeleton skeleton = this.cardSkeleton;
        if (skeleton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardSkeleton");
            skeleton = null;
        }
        ExtensionsKt.showData$default(skeleton, 0L, 1, null);
    }

    private final void displayFuelLoading() {
        if (!isAdded() || getActivity() == null) {
            return;
        }
        RecyclerView mtCardViews = (RecyclerView) _$_findCachedViewById(R.id.mtCardViews);
        Intrinsics.checkNotNullExpressionValue(mtCardViews, "mtCardViews");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Skeleton createSkeleton = SkeletonLayoutUtils.createSkeleton(mtCardViews, ExtensionsKt.skeletonConfig(requireContext));
        createSkeleton.showSkeleton();
        this.cardSkeleton = createSkeleton;
    }

    private final void displayLoading() {
        if (!isAdded() || getActivity() == null || getActivity() == null) {
            return;
        }
        LinearLayout llTrips = (LinearLayout) _$_findCachedViewById(R.id.llTrips);
        Intrinsics.checkNotNullExpressionValue(llTrips, "llTrips");
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        Skeleton createSkeleton = SkeletonLayoutUtils.createSkeleton(llTrips, ExtensionsKt.skeletonConfig(requireActivity));
        createSkeleton.showSkeleton();
        this.skeleton = createSkeleton;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void e(TripFragment tripFragment, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = false;
        }
        tripFragment.getTripData(z);
    }

    static /* synthetic */ void f(TripFragment tripFragment, FuelReportResponse fuelReportResponse, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            fuelReportResponse = null;
        }
        tripFragment.initTripCards(fuelReportResponse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getFuelReport() {
        displayFuelLoading();
        if (!isAdded() || getActivity() == null) {
            return;
        }
        TripService tripService = new TripService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String vinNumber = companion.getVinNumber(requireContext);
        StringBuilder sb = new StringBuilder();
        AppConstants.Companion companion2 = AppConstants.Companion;
        sb.append(companion2.getStartDate());
        sb.append(" 00:00:01");
        String sb2 = sb.toString();
        tripService.callGetFuelReport(requireActivity, vinNumber, sb2, companion2.getEndDate() + " 23:59:59");
        if (this.tabLayout == null) {
            initTabLayout();
            return;
        }
        ViewPager2 mtPager = (ViewPager2) _$_findCachedViewById(R.id.mtPager);
        Intrinsics.checkNotNullExpressionValue(mtPager, "mtPager");
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        Fragment findCurrentFragment = ExtensionsKt.findCurrentFragment(mtPager, childFragmentManager);
        if (findCurrentFragment != null && Intrinsics.areEqual(ExtensionsKt.getName(findCurrentFragment), ExtensionsKt.getName(new TripSummaryFragment()))) {
            ((TripSummaryFragment) findCurrentFragment).getTripList();
            return;
        }
        Objects.requireNonNull(findCurrentFragment, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.controller.fragments.my_trips.DrivingBehaviourFragment");
        ((DrivingBehaviourFragment) findCurrentFragment).getDrivingBehaviourData();
    }

    private final void getTripData(boolean z) {
        displayLoading();
        DashboardService dashboardService = new DashboardService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        dashboardService.callDashboardAPI(requireActivity, companion.getVinNumber(requireContext));
        this.isRefreshing = !z;
    }

    private final void initSpinner() {
        List list;
        if (this.spinnerSelectionPos == 5 && this.customStartDateSelection != null && this.customEndDateSelection != null) {
            Logger logger = Logger.INSTANCE;
            logger.e("Spinner Selection Position: " + this.spinnerSelectionPos);
            logger.e("Custom Start Date Selection: " + this.customStartDateSelection);
            logger.e("Custom End Date Selection: " + this.customEndDateSelection);
        }
        String[] stringArray = getResources().getStringArray(uat.psa.mym.mycitroenconnect.R.array.period);
        Intrinsics.checkNotNullExpressionValue(stringArray, "resources.getStringArray(R.array.period)");
        list = ArraysKt___ArraysKt.toList(stringArray);
        this.periods = (ArrayList) list;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(requireContext(), (int) uat.psa.mym.mycitroenconnect.R.layout.layout_curom_spinner_item, this.periods);
        arrayAdapter.setDropDownViewResource(uat.psa.mym.mycitroenconnect.R.layout.layout_curom_spinner_item);
        int i2 = R.id.mtPeriodSpinner;
        ((CustomSpinner) _$_findCachedViewById(i2)).setAdapter((SpinnerAdapter) arrayAdapter);
        this.periodAdapter = arrayAdapter;
        if (this.spinnerSelectionPos == 5) {
            setCustomPickerSelection(new Pair<>(this.customStartDateSelection, this.customEndDateSelection));
            setCustomSpinnerSelection();
        } else {
            ((CustomSpinner) _$_findCachedViewById(i2)).setSelection(this.spinnerSelectionPos);
        }
        this.spinnerListener = new TripFragment$initSpinner$2(this);
        CustomSpinner customSpinner = (CustomSpinner) _$_findCachedViewById(i2);
        AdapterView.OnItemSelectedListener onItemSelectedListener = this.spinnerListener;
        if (onItemSelectedListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinnerListener");
            onItemSelectedListener = null;
        }
        customSpinner.setOnItemSelectedListener(onItemSelectedListener);
    }

    private final void initTabLayout() {
        final ArrayList arrayListOf;
        ArrayList arrayListOf2;
        View view = this.mainView;
        TabLayout tabLayout = view != null ? (TabLayout) view.findViewById(uat.psa.mym.mycitroenconnect.R.id.mtTabLayout) : null;
        Intrinsics.checkNotNull(tabLayout);
        this.tabLayout = tabLayout;
        arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(getResources().getString(uat.psa.mym.mycitroenconnect.R.string.trip_summary), getResources().getString(uat.psa.mym.mycitroenconnect.R.string.driving_behaviour));
        arrayListOf2 = CollectionsKt__CollectionsKt.arrayListOf(new TripSummaryFragment(), new DrivingBehaviourFragment());
        View view2 = this.mainView;
        ViewPager2 viewPager2 = view2 != null ? (ViewPager2) view2.findViewById(uat.psa.mym.mycitroenconnect.R.id.mtPager) : null;
        if (viewPager2 != null) {
            viewPager2.setUserInputEnabled(false);
        }
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        Lifecycle lifecycle = getViewLifecycleOwner().getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "viewLifecycleOwner.lifecycle");
        CommonChildViewPagerAdapter commonChildViewPagerAdapter = new CommonChildViewPagerAdapter(childFragmentManager, lifecycle, arrayListOf2);
        if (viewPager2 != null) {
            viewPager2.setAdapter(commonChildViewPagerAdapter);
        }
        if (viewPager2 != null) {
            TabLayout tabLayout2 = this.tabLayout;
            Intrinsics.checkNotNull(tabLayout2);
            new TabLayoutMediator(tabLayout2, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.my_trips.a
                @Override // com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
                public final void onConfigureTab(TabLayout.Tab tab, int i2) {
                    TripFragment.m151initTabLayout$lambda3(arrayListOf, tab, i2);
                }
            }).attach();
        }
        TabLayout tabLayout3 = this.tabLayout;
        Intrinsics.checkNotNull(tabLayout3);
        tabLayout3.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initTabLayout$lambda-3  reason: not valid java name */
    public static final void m151initTabLayout$lambda3(ArrayList tabTitle, TabLayout.Tab tab, int i2) {
        Intrinsics.checkNotNullParameter(tabTitle, "$tabTitle");
        Intrinsics.checkNotNullParameter(tab, "tab");
        Object obj = tabTitle.get(i2);
        Intrinsics.checkNotNullExpressionValue(obj, "tabTitle[position]");
        String str = (String) obj;
        if (str.length() > 0) {
            StringBuilder sb = new StringBuilder();
            char charAt = str.charAt(0);
            sb.append((Object) (Character.isLowerCase(charAt) ? CharsKt__CharKt.titlecase(charAt) : String.valueOf(charAt)));
            String substring = str.substring(1);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
            sb.append(substring);
            str = sb.toString();
        }
        tab.setText(str);
    }

    private final void initTripCards(FuelReportResponse fuelReportResponse) {
        List<TripCardData> mutableListOf;
        String twoDecimal;
        boolean z;
        String oneDecimal;
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String str = "0";
        if (Intrinsics.areEqual(companion.getVehicleType(requireContext), AppConstants.ICE)) {
            TripCardData[] tripCardDataArr = new TripCardData[4];
            String string = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.current_fuel);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.current_fuel)");
            DashboardResponse dashboardResponse = this.dashboardResponse;
            tripCardDataArr[0] = new TripCardData(string, String.valueOf((int) Math.ceil(dashboardResponse != null ? dashboardResponse.getFuelLevel() / 0.08d : 0.0d)), "%", uat.psa.mym.mycitroenconnect.R.drawable.ic_current_fuel_level);
            String string2 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.total_fuel_consumed);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.total_fuel_consumed)");
            String str2 = (fuelReportResponse == null || (str2 = ExtensionsKt.toOneDecimal(fuelReportResponse.getTotalEnergyConsumption())) == null) ? "0" : "0";
            String string3 = getString(uat.psa.mym.mycitroenconnect.R.string.litre_unit);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.litre_unit)");
            tripCardDataArr[1] = new TripCardData(string2, str2, string3, uat.psa.mym.mycitroenconnect.R.drawable.ic_fuel_drop);
            String string4 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.average_fuel_economy);
            Intrinsics.checkNotNullExpressionValue(string4, "resources.getString(R.string.average_fuel_economy)");
            String str3 = (fuelReportResponse == null || (str3 = ExtensionsKt.toOneDecimal(fuelReportResponse.getMileage())) == null) ? "0" : "0";
            String string5 = getString(uat.psa.mym.mycitroenconnect.R.string.kmpl);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.kmpl)");
            tripCardDataArr[2] = new TripCardData(string4, str3, string5, uat.psa.mym.mycitroenconnect.R.drawable.ic_range);
            String string6 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.fuel_consumed_in_idling);
            Intrinsics.checkNotNullExpressionValue(string6, "resources.getString(R.st….fuel_consumed_in_idling)");
            if (fuelReportResponse != null && (oneDecimal = ExtensionsKt.toOneDecimal(fuelReportResponse.getIdleEnergyConsumption())) != null) {
                str = oneDecimal;
            }
            String string7 = getString(uat.psa.mym.mycitroenconnect.R.string.litre_unit);
            Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.litre_unit)");
            tripCardDataArr[3] = new TripCardData(string6, str, string7, uat.psa.mym.mycitroenconnect.R.drawable.ic_fuel_drop);
            mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(tripCardDataArr);
        } else {
            TripCardData[] tripCardDataArr2 = new TripCardData[4];
            String string8 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.current_charge);
            Intrinsics.checkNotNullExpressionValue(string8, "resources.getString(R.string.current_charge)");
            DashboardResponse dashboardResponse2 = this.dashboardResponse;
            tripCardDataArr2[0] = new TripCardData(string8, (dashboardResponse2 == null || (r11 = Integer.valueOf(dashboardResponse2.getStateOfCharge()).toString()) == null) ? "0" : "0", "%", uat.psa.mym.mycitroenconnect.R.drawable.ic_charge);
            String string9 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.total_energy_consumed);
            Intrinsics.checkNotNullExpressionValue(string9, "resources.getString(R.st…ng.total_energy_consumed)");
            String str4 = (fuelReportResponse == null || (str4 = ExtensionsKt.toTwoDecimal(fuelReportResponse.getTotalEnergyConsumption() / ((double) 1000))) == null) ? "0" : "0";
            String string10 = getString(uat.psa.mym.mycitroenconnect.R.string.kwh);
            Intrinsics.checkNotNullExpressionValue(string10, "getString(R.string.kwh)");
            tripCardDataArr2[1] = new TripCardData(string9, str4, string10, uat.psa.mym.mycitroenconnect.R.drawable.ic_energy);
            String string11 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.average_power_consumption);
            Intrinsics.checkNotNullExpressionValue(string11, "resources.getString(R.st…verage_power_consumption)");
            String str5 = (fuelReportResponse == null || (str5 = ExtensionsKt.toTwoDecimal(fuelReportResponse.getMileage() / ((double) 1000))) == null) ? "0" : "0";
            String string12 = getString(uat.psa.mym.mycitroenconnect.R.string.kwh_km);
            Intrinsics.checkNotNullExpressionValue(string12, "getString(R.string.kwh_km)");
            tripCardDataArr2[2] = new TripCardData(string11, str5, string12, uat.psa.mym.mycitroenconnect.R.drawable.ic_range);
            String string13 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.energy_consumed_in_idling);
            Intrinsics.checkNotNullExpressionValue(string13, "resources.getString(R.st…nergy_consumed_in_idling)");
            if (fuelReportResponse != null && (twoDecimal = ExtensionsKt.toTwoDecimal(fuelReportResponse.getIdleEnergyConsumption() / 1000)) != null) {
                str = twoDecimal;
            }
            String string14 = getString(uat.psa.mym.mycitroenconnect.R.string.kwh);
            Intrinsics.checkNotNullExpressionValue(string14, "getString(R.string.kwh)");
            tripCardDataArr2[3] = new TripCardData(string13, str, string14, uat.psa.mym.mycitroenconnect.R.drawable.ic_energy);
            mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(tripCardDataArr2);
        }
        MyTripsCardAdapter myTripsCardAdapter = this.cardAdapter;
        if (myTripsCardAdapter != null) {
            if (myTripsCardAdapter != null) {
                myTripsCardAdapter.updateList(mutableListOf);
                return;
            }
            return;
        }
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        this.cardAdapter = new MyTripsCardAdapter(requireContext2, mutableListOf);
        View view = this.mainView;
        RecyclerView recyclerView = view != null ? (RecyclerView) view.findViewById(uat.psa.mym.mycitroenconnect.R.id.mtCardViews) : null;
        if (recyclerView == null) {
            z = true;
        } else {
            z = true;
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2, 1, false));
        }
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(z);
        }
        if (recyclerView != null) {
            recyclerView.setAdapter(this.cardAdapter);
        }
        this.recyclerView = recyclerView;
    }

    private final void initViews() {
        initSpinner();
        ((AppCompatTextView) _$_findCachedViewById(R.id.mtViewDetails)).setOnClickListener(this);
        ((LinearLayoutCompat) _$_findCachedViewById(R.id.llTripUpdatedTime)).setOnClickListener(this);
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        StoredDashboardData saveDashboardData = companion.getSaveDashboardData(requireContext);
        if (saveDashboardData != null) {
            setUpdateTime(saveDashboardData.getUpdatedTimeStamp());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onTabSelected$lambda-0  reason: not valid java name */
    public static final void m152onTabSelected$lambda0(TripFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppConstants.Companion companion = AppConstants.Companion;
        if (companion.isDateChanged()) {
            companion.setDateChanged(false);
            ViewPager2 mtPager = (ViewPager2) this$0._$_findCachedViewById(R.id.mtPager);
            Intrinsics.checkNotNullExpressionValue(mtPager, "mtPager");
            FragmentManager childFragmentManager = this$0.getChildFragmentManager();
            Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
            Fragment findCurrentFragment = ExtensionsKt.findCurrentFragment(mtPager, childFragmentManager);
            if (findCurrentFragment != null && Intrinsics.areEqual(ExtensionsKt.getName(findCurrentFragment), ExtensionsKt.getName(new TripSummaryFragment()))) {
                ((TripSummaryFragment) findCurrentFragment).getTripList();
                return;
            }
            Objects.requireNonNull(findCurrentFragment, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.controller.fragments.my_trips.DrivingBehaviourFragment");
            ((DrivingBehaviourFragment) findCurrentFragment).getDrivingBehaviourData();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCustomPickerSelection(Pair<Long, Long> pair) {
        Long l2 = pair.first;
        Long l3 = pair.second;
        this.customStartDateSelection = l2;
        this.customEndDateSelection = l3;
        AppUtil.Companion companion = AppUtil.Companion;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.ONLY_DATE_FORMAT, companion.getDefaultLocale());
        AppConstants.Companion companion2 = AppConstants.Companion;
        companion2.setStartDate(simpleDateFormat.format(l2).toString());
        companion2.setEndDate(simpleDateFormat.format(l3).toString());
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(AppConstants.MM_DD_YY_FORMAT, companion.getDefaultLocale());
        String str = simpleDateFormat2.format(l2).toString();
        String str2 = simpleDateFormat2.format(l3).toString();
        companion2.setDateChanged(true);
        updateCustomDateValue(str + " - " + str2);
    }

    private final void setCustomSpinnerSelection() {
        int i2 = R.id.mtPeriodSpinner;
        ((CustomSpinner) _$_findCachedViewById(i2)).setOnItemSelectedListener(null);
        ((CustomSpinner) _$_findCachedViewById(i2)).post(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.my_trips.c
            @Override // java.lang.Runnable
            public final void run() {
                TripFragment.m153setCustomSpinnerSelection$lambda10(TripFragment.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setCustomSpinnerSelection$lambda-10  reason: not valid java name */
    public static final void m153setCustomSpinnerSelection$lambda10(final TripFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i2 = R.id.mtPeriodSpinner;
        ((CustomSpinner) this$0._$_findCachedViewById(i2)).setSelection(this$0.spinnerSelectionPos);
        ((CustomSpinner) this$0._$_findCachedViewById(i2)).post(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.my_trips.b
            @Override // java.lang.Runnable
            public final void run() {
                TripFragment.m154setCustomSpinnerSelection$lambda10$lambda9(TripFragment.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setCustomSpinnerSelection$lambda-10$lambda-9  reason: not valid java name */
    public static final void m154setCustomSpinnerSelection$lambda10$lambda9(TripFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CustomSpinner customSpinner = (CustomSpinner) this$0._$_findCachedViewById(R.id.mtPeriodSpinner);
        AdapterView.OnItemSelectedListener onItemSelectedListener = this$0.spinnerListener;
        if (onItemSelectedListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinnerListener");
            onItemSelectedListener = null;
        }
        customSpinner.setOnItemSelectedListener(onItemSelectedListener);
    }

    private final void setUpdateTime(String str) {
        try {
            AppUtil.Companion companion = AppUtil.Companion;
            Date parse = new SimpleDateFormat(AppConstants.UTC_DATE_FORMAT, companion.getDefaultLocale()).parse(str);
            if (parse != null) {
                ((AppCompatTextView) _$_findCachedViewById(R.id.tvTripUpdated)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.updated_time, companion.getTimeAgo(parse.getTime())));
            }
        } catch (Exception e2) {
            Logger.INSTANCE.e(e2.getLocalizedMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDateRangePickerDialog() {
        DateRangePickerDialog newInstance;
        if (this.customStartDateSelection == null || this.customEndDateSelection == null) {
            DateRangePickerDialog.Companion companion = DateRangePickerDialog.Companion;
            Calendar calendar = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
            Calendar calendar2 = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
            newInstance = companion.newInstance(calendar, calendar2);
        } else {
            Calendar startCalendar = Calendar.getInstance();
            Long l2 = this.customStartDateSelection;
            Objects.requireNonNull(l2, "null cannot be cast to non-null type kotlin.Long");
            startCalendar.setTimeInMillis(l2.longValue());
            Calendar endCalendar = Calendar.getInstance();
            Long l3 = this.customEndDateSelection;
            Objects.requireNonNull(l3, "null cannot be cast to non-null type kotlin.Long");
            endCalendar.setTimeInMillis(l3.longValue());
            DateRangePickerDialog.Companion companion2 = DateRangePickerDialog.Companion;
            Intrinsics.checkNotNullExpressionValue(startCalendar, "startCalendar");
            Intrinsics.checkNotNullExpressionValue(endCalendar, "endCalendar");
            newInstance = companion2.newInstance(startCalendar, endCalendar);
        }
        newInstance.show(getChildFragmentManager(), DateRangePickerDialog.Companion.getTAG());
        newInstance.setOnDateSelectListener(new OnDateSelectListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.my_trips.TripFragment$showDateRangePickerDialog$1
            @Override // com.psa.mym.mycitroenconnect.controller.dialog.OnDateSelectListener
            public void onDateSelect(@NotNull Pair<Long, Long> selection) {
                Intrinsics.checkNotNullParameter(selection, "selection");
                TripFragment.this.setCustomPickerSelection(selection);
                TripFragment.this.getFuelReport();
            }
        });
        newInstance.setCancelable(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateCustomDateValue(String str) {
        ArrayList<String> arrayList = this.periods;
        arrayList.set(arrayList.size() - 1, str);
        ArrayAdapter<String> arrayAdapter = this.periodAdapter;
        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged();
        }
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
        Intrinsics.checkNotNullParameter(event, "event");
        displayData();
        if (Intrinsics.areEqual(event.getApiName(), AppConstants.API_NAME_DASHBOARD)) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            ExtensionsKt.showToast(requireActivity, event.getMsg());
            this.dashboardResponse = null;
            if (this.isRefreshing) {
                this.isRefreshing = false;
                getFuelReport();
                return;
            }
            return;
        }
        Logger logger = Logger.INSTANCE;
        logger.e("API Name : " + event.getApiName());
        logger.e("Status Code : " + event.getStatusCode());
        logger.e("Message : " + event.getMsg());
    }

    @Subscribe
    public final void getMessage(@NotNull FailResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        displayFuelData();
        if (event.getStatusCode() != 204) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            ExtensionsKt.showToast(requireActivity, event.getMessage());
            return;
        }
        f(this, null, 1, null);
        Logger logger = Logger.INSTANCE;
        logger.e("Response Code: " + event.getStatusCode() + " Message: " + event.getMessage());
    }

    @Subscribe
    public final void getMessage(@NotNull DashboardResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        displayData();
        this.dashboardResponse = event;
        setUpdateTime(event.getUpdatedTimeStamp());
        if (this.isRefreshing) {
            this.isRefreshing = false;
            getFuelReport();
        }
    }

    @Subscribe
    public final void getMessage(@NotNull FuelReportResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        displayFuelData();
        initTripCards(event);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        Long l2;
        Long l3;
        super.onActivityResult(i2, i3, intent);
        if (i3 == -1 && i2 == 678) {
            if (intent != null && intent.hasExtra(AppConstants.ARG_TRIP_SPINNER_SEL)) {
                this.spinnerSelectionPos = intent.getIntExtra(AppConstants.ARG_TRIP_SPINNER_SEL, 0);
            }
            if (this.spinnerSelectionPos == 5) {
                if (intent != null && intent.hasExtra(AppConstants.ARG_TRIP_SPINNER_SEL_START_DATE)) {
                    this.customStartDateSelection = Long.valueOf(intent.getLongExtra(AppConstants.ARG_TRIP_SPINNER_SEL_START_DATE, MaterialDatePicker.todayInUtcMilliseconds()));
                }
                if (intent != null && intent.hasExtra(AppConstants.ARG_TRIP_SPINNER_SEL_END_DATE)) {
                    this.customEndDateSelection = Long.valueOf(intent.getLongExtra(AppConstants.ARG_TRIP_SPINNER_SEL_END_DATE, MaterialDatePicker.todayInUtcMilliseconds()));
                }
            }
            if (this.spinnerSelectionPos != 5 || (l2 = this.customStartDateSelection) == null || (l3 = this.customEndDateSelection) == null) {
                ((CustomSpinner) _$_findCachedViewById(R.id.mtPeriodSpinner)).setSelection(this.spinnerSelectionPos);
                return;
            }
            setCustomPickerSelection(new Pair<>(l2, l3));
            getFuelReport();
            setCustomSpinnerSelection();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (SystemClock.elapsedRealtime() - this.lastClickTime < 1500) {
            return;
        }
        this.lastClickTime = SystemClock.elapsedRealtime();
        if (!Intrinsics.areEqual(view, (AppCompatTextView) _$_findCachedViewById(R.id.mtViewDetails))) {
            if (Intrinsics.areEqual(view, (LinearLayoutCompat) _$_findCachedViewById(R.id.llTripUpdatedTime))) {
                e(this, false, 1, null);
                return;
            }
            return;
        }
        Intent intent = new Intent(requireActivity(), BatteryUsageActivity.class);
        intent.putExtra(AppConstants.ARG_TRIP_SPINNER_SEL, this.spinnerSelectionPos);
        if (this.spinnerSelectionPos == 5) {
            intent.putExtra(AppConstants.ARG_TRIP_SPINNER_SEL_START_DATE, this.customStartDateSelection);
            intent.putExtra(AppConstants.ARG_TRIP_SPINNER_SEL_END_DATE, this.customEndDateSelection);
        }
        startActivityForResult(intent, AppConstants.REQ_TRIP_SPINNER_SELECTION);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_trip, viewGroup, false);
        this.mainView = inflate;
        return inflate;
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabReselected(@Nullable TabLayout.Tab tab) {
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabSelected(@Nullable TabLayout.Tab tab) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.my_trips.d
            @Override // java.lang.Runnable
            public final void run() {
                TripFragment.m152onTabSelected$lambda0(TripFragment.this);
            }
        }, 500L);
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabUnselected(@Nullable TabLayout.Tab tab) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initViews();
        getTripData(true);
    }
}
