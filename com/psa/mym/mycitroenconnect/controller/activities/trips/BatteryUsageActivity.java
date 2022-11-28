package com.psa.mym.mycitroenconnect.controller.activities.trips;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.psa.mym.mycitroenconnect.BusBaseActivity;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.adapters.IdleTripsAdapter;
import com.psa.mym.mycitroenconnect.controller.dialog.DateRangePickerDialog;
import com.psa.mym.mycitroenconnect.controller.dialog.OnDateSelectListener;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.FailResponse;
import com.psa.mym.mycitroenconnect.model.trip.DrivingBehaviourResponse;
import com.psa.mym.mycitroenconnect.model.trip.EnergyUsageResponse;
import com.psa.mym.mycitroenconnect.model.trip.FuelReportResponse;
import com.psa.mym.mycitroenconnect.model.trip.IdleDetailsResponse;
import com.psa.mym.mycitroenconnect.model.trip.TripResponseDTOX;
import com.psa.mym.mycitroenconnect.services.TripService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.CustomSpinner;
import com.psa.mym.mycitroenconnect.views.RoundedBarChart;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.Skeleton;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayoutUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.Pair;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class BatteryUsageActivity extends BusBaseActivity {
    @Nullable
    private Long customEndDateSelection;
    @Nullable
    private Long customStartDateSelection;
    private DrivingBehaviourResponse drivingBehaviourResponse;
    @Nullable
    private FuelReportResponse fuelReportResponse;
    @Nullable
    private IdleTripsAdapter idleTripsAdapter;
    private boolean isFirstTimeCustom;
    @Nullable
    private ArrayAdapter<String> periodAdapter;
    @Nullable
    private Skeleton skeleton;
    private AdapterView.OnItemSelectedListener spinnerListener;
    private int spinnerSelectionPos;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private List<TripResponseDTOX> tripSummaryDataList = new ArrayList();
    @NotNull
    private String vehicleType = "";
    @NotNull
    private String type = "";
    @NotNull
    private String startDate = "";
    @NotNull
    private String endDate = "";
    @NotNull
    private ArrayList<String> periods = new ArrayList<>();

    private final void apiEnergyUsage() {
        new TripService().callGetEnergyUsage(this, SharedPref.Companion.getVinNumber(this), this.startDate + " 00:00:01", this.endDate + " 23:59:59");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void apiFuelReport() {
        displayLoading();
        new TripService().callGetFuelReport(this, SharedPref.Companion.getVinNumber(this), this.startDate + " 00:00:01", this.endDate + " 23:59:59");
    }

    private final void apiTripIdleDetails() {
        new TripService().callGetIdleDetails(this, SharedPref.Companion.getVinNumber(this), this.startDate + " 00:00:01", this.endDate + " 23:59:59");
    }

    private final void apiTripSummary() {
        new TripService().callGetTripSummaryAPI(this, SharedPref.Companion.getVinNumber(this), this.startDate + " 00:00:01", this.endDate + " 23:59:59");
    }

    private final void displayData() {
        Skeleton skeleton;
        Skeleton skeleton2 = this.skeleton;
        if (skeleton2 != null) {
            boolean z = false;
            if (skeleton2 != null && skeleton2.isSkeleton()) {
                z = true;
            }
            if (!z || (skeleton = this.skeleton) == null) {
                return;
            }
            ExtensionsKt.showData$default(skeleton, 0L, 1, null);
        }
    }

    private final void displayLoading() {
        if (this.skeleton == null) {
            ConstraintLayout clParent = (ConstraintLayout) _$_findCachedViewById(R.id.clParent);
            Intrinsics.checkNotNullExpressionValue(clParent, "clParent");
            Skeleton createSkeleton = SkeletonLayoutUtils.createSkeleton(clParent, ExtensionsKt.skeletonConfig(this));
            createSkeleton.showSkeleton();
            this.skeleton = createSkeleton;
        }
    }

    private final void getIntentData() {
        if (getIntent() != null && getIntent().hasExtra(AppConstants.ARG_TRIP_SPINNER_SEL)) {
            this.spinnerSelectionPos = getIntent().getIntExtra(AppConstants.ARG_TRIP_SPINNER_SEL, 0);
        }
        if (this.spinnerSelectionPos == 5) {
            if (getIntent() != null && getIntent().hasExtra(AppConstants.ARG_TRIP_SPINNER_SEL_START_DATE)) {
                this.customStartDateSelection = Long.valueOf(getIntent().getLongExtra(AppConstants.ARG_TRIP_SPINNER_SEL_START_DATE, MaterialDatePicker.todayInUtcMilliseconds()));
            }
            if (getIntent() == null || !getIntent().hasExtra(AppConstants.ARG_TRIP_SPINNER_SEL_END_DATE)) {
                return;
            }
            this.customEndDateSelection = Long.valueOf(getIntent().getLongExtra(AppConstants.ARG_TRIP_SPINNER_SEL_END_DATE, MaterialDatePicker.todayInUtcMilliseconds()));
        }
    }

    private final int getSpinnerSelection() {
        return this.spinnerSelectionPos;
    }

    private final void handleBackPressed() {
        Logger.INSTANCE.e(String.valueOf(getSpinnerSelection()));
        Intent intent = new Intent();
        intent.putExtra(AppConstants.ARG_TRIP_SPINNER_SEL, getSpinnerSelection());
        if (this.spinnerSelectionPos == 5) {
            intent.putExtra(AppConstants.ARG_TRIP_SPINNER_SEL_START_DATE, this.customStartDateSelection);
            intent.putExtra(AppConstants.ARG_TRIP_SPINNER_SEL_END_DATE, this.customEndDateSelection);
        }
        setResult(-1, intent);
        finish();
    }

    private final void init() {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        AppCompatTextView appCompatTextView;
        String string;
        String vehicleType = SharedPref.Companion.getVehicleType(this);
        this.vehicleType = vehicleType;
        if (Intrinsics.areEqual(vehicleType, AppConstants.ICE)) {
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvDbHeaderTitle)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.fuel_usage));
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvEnergyConsumption)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.fuel_consumption));
            ((TextView) _$_findCachedViewById(R.id.tvGraphLabel)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.fuel_consumption));
            int i8 = R.id.batteryCard1;
            View _$_findCachedViewById = _$_findCachedViewById(i8);
            i2 = R.id.tvCardTitle;
            ((AppCompatTextView) _$_findCachedViewById.findViewById(i2)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.total_fuel_consumed));
            View _$_findCachedViewById2 = _$_findCachedViewById(i8);
            i3 = R.id.tvCardUnit;
            i4 = uat.psa.mym.mycitroenconnect.R.string.litre_unit;
            ((AppCompatTextView) _$_findCachedViewById2.findViewById(i3)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.litre_unit));
            View _$_findCachedViewById3 = _$_findCachedViewById(i8);
            i5 = R.id.tvCardIcon;
            i6 = uat.psa.mym.mycitroenconnect.R.drawable.ic_fuel_drop;
            ((AppCompatImageView) _$_findCachedViewById3.findViewById(i5)).setImageDrawable(ContextCompat.getDrawable(this, uat.psa.mym.mycitroenconnect.R.drawable.ic_fuel_drop));
            int i9 = R.id.batteryCard2;
            ((AppCompatTextView) _$_findCachedViewById(i9).findViewById(i2)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.no_of_charge_cycle));
            ((AppCompatTextView) _$_findCachedViewById(i9).findViewById(i3)).setText("");
            ((AppCompatImageView) _$_findCachedViewById(i9).findViewById(i5)).setImageDrawable(ContextCompat.getDrawable(this, uat.psa.mym.mycitroenconnect.R.drawable.ic_cycle));
            i7 = R.id.idlingCard1;
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(i7).findViewById(i2);
            string = getString(uat.psa.mym.mycitroenconnect.R.string.fuel_consumed_in_idling);
        } else {
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvDbHeaderTitle)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.battery_usage));
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvEnergyConsumption)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.energy_consumption));
            ((TextView) _$_findCachedViewById(R.id.tvGraphLabel)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.energy_consumption));
            int i10 = R.id.batteryCard1;
            View _$_findCachedViewById4 = _$_findCachedViewById(i10);
            i2 = R.id.tvCardTitle;
            ((AppCompatTextView) _$_findCachedViewById4.findViewById(i2)).setText(getResources().getString(uat.psa.mym.mycitroenconnect.R.string.total_energy_consumed));
            View _$_findCachedViewById5 = _$_findCachedViewById(i10);
            i3 = R.id.tvCardUnit;
            i4 = uat.psa.mym.mycitroenconnect.R.string.kwh;
            ((AppCompatTextView) _$_findCachedViewById5.findViewById(i3)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.kwh));
            View _$_findCachedViewById6 = _$_findCachedViewById(i10);
            i5 = R.id.tvCardIcon;
            i6 = uat.psa.mym.mycitroenconnect.R.drawable.ic_energy;
            ((AppCompatImageView) _$_findCachedViewById6.findViewById(i5)).setImageDrawable(ContextCompat.getDrawable(this, uat.psa.mym.mycitroenconnect.R.drawable.ic_energy));
            int i11 = R.id.batteryCard2;
            ((AppCompatTextView) _$_findCachedViewById(i11).findViewById(i2)).setText(getResources().getString(uat.psa.mym.mycitroenconnect.R.string.no_of_charge_cycle));
            ((AppCompatTextView) _$_findCachedViewById(i11).findViewById(i3)).setText("");
            ((AppCompatImageView) _$_findCachedViewById(i11).findViewById(i5)).setImageDrawable(ContextCompat.getDrawable(this, uat.psa.mym.mycitroenconnect.R.drawable.ic_cycle));
            i7 = R.id.idlingCard1;
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(i7).findViewById(i2);
            string = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.energy_consumed_in_idling);
        }
        appCompatTextView.setText(string);
        ((AppCompatTextView) _$_findCachedViewById(i7).findViewById(i3)).setText(getString(i4));
        ((AppCompatImageView) _$_findCachedViewById(i7).findViewById(i5)).setImageDrawable(ContextCompat.getDrawable(this, i6));
        int i12 = R.id.idlingCard2;
        ((AppCompatTextView) _$_findCachedViewById(i12).findViewById(i2)).setText(getResources().getString(uat.psa.mym.mycitroenconnect.R.string.no_of_idling_instances));
        ((AppCompatTextView) _$_findCachedViewById(i12).findViewById(i3)).setText("");
        ((AppCompatImageView) _$_findCachedViewById(i12).findViewById(i5)).setImageDrawable(ContextCompat.getDrawable(this, uat.psa.mym.mycitroenconnect.R.drawable.ic_smoke));
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0197  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void initEnergyConsumptionCards() {
        String str;
        FuelReportResponse fuelReportResponse;
        String str2;
        AppCompatTextView appCompatTextView;
        FuelReportResponse fuelReportResponse2;
        String str3;
        FuelReportResponse fuelReportResponse3;
        String str4;
        FuelReportResponse fuelReportResponse4;
        boolean z = true;
        String str5 = "0";
        Integer num = null;
        if (Intrinsics.areEqual(this.vehicleType, AppConstants.ICE)) {
            View _$_findCachedViewById = _$_findCachedViewById(R.id.batteryCard1);
            int i2 = R.id.tvCardValue;
            AppCompatTextView appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById.findViewById(i2);
            FuelReportResponse fuelReportResponse5 = this.fuelReportResponse;
            if (fuelReportResponse5 != null) {
                if (!Intrinsics.areEqual(fuelReportResponse5 != null ? Double.valueOf(fuelReportResponse5.getTotalEnergyConsumption()) : null, 0.0d)) {
                    FuelReportResponse fuelReportResponse6 = this.fuelReportResponse;
                    if ((fuelReportResponse6 != null ? Double.valueOf(fuelReportResponse6.getTotalEnergyConsumption()) : null) != null) {
                        FuelReportResponse fuelReportResponse7 = this.fuelReportResponse;
                        str3 = fuelReportResponse7 != null ? ExtensionsKt.toOneDecimal(fuelReportResponse7.getTotalEnergyConsumption()) : null;
                        appCompatTextView2.setText(str3);
                        ((AppCompatTextView) _$_findCachedViewById(R.id.batteryCard2).findViewById(i2)).setText("0");
                        AppCompatTextView appCompatTextView3 = (AppCompatTextView) _$_findCachedViewById(R.id.idlingCard1).findViewById(i2);
                        fuelReportResponse3 = this.fuelReportResponse;
                        if (fuelReportResponse3 != null) {
                            if (!Intrinsics.areEqual(fuelReportResponse3 != null ? Double.valueOf(fuelReportResponse3.getIdleEnergyConsumption()) : null, 0.0d)) {
                                FuelReportResponse fuelReportResponse8 = this.fuelReportResponse;
                                if ((fuelReportResponse8 != null ? Double.valueOf(fuelReportResponse8.getIdleEnergyConsumption()) : null) != null) {
                                    FuelReportResponse fuelReportResponse9 = this.fuelReportResponse;
                                    str4 = fuelReportResponse9 != null ? ExtensionsKt.toTwoDecimal(fuelReportResponse9.getIdleEnergyConsumption()) : null;
                                    appCompatTextView3.setText(str4);
                                    appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.idlingCard2).findViewById(i2);
                                    fuelReportResponse4 = this.fuelReportResponse;
                                    if (fuelReportResponse4 != null) {
                                        if (fuelReportResponse4 == null || fuelReportResponse4.getNoOfIdleInstance() != 0) {
                                            z = false;
                                        }
                                        if (!z) {
                                            FuelReportResponse fuelReportResponse10 = this.fuelReportResponse;
                                            if ((fuelReportResponse10 != null ? Integer.valueOf(fuelReportResponse10.getNoOfIdleInstance()) : null) != null) {
                                                FuelReportResponse fuelReportResponse11 = this.fuelReportResponse;
                                                if (fuelReportResponse11 != null) {
                                                    num = Integer.valueOf(fuelReportResponse11.getNoOfIdleInstance());
                                                }
                                                str5 = String.valueOf(num);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        str4 = "0";
                        appCompatTextView3.setText(str4);
                        appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.idlingCard2).findViewById(i2);
                        fuelReportResponse4 = this.fuelReportResponse;
                        if (fuelReportResponse4 != null) {
                        }
                    }
                }
            }
            str3 = "0";
            appCompatTextView2.setText(str3);
            ((AppCompatTextView) _$_findCachedViewById(R.id.batteryCard2).findViewById(i2)).setText("0");
            AppCompatTextView appCompatTextView32 = (AppCompatTextView) _$_findCachedViewById(R.id.idlingCard1).findViewById(i2);
            fuelReportResponse3 = this.fuelReportResponse;
            if (fuelReportResponse3 != null) {
            }
            str4 = "0";
            appCompatTextView32.setText(str4);
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.idlingCard2).findViewById(i2);
            fuelReportResponse4 = this.fuelReportResponse;
            if (fuelReportResponse4 != null) {
            }
        } else {
            View _$_findCachedViewById2 = _$_findCachedViewById(R.id.batteryCard1);
            int i3 = R.id.tvCardValue;
            AppCompatTextView appCompatTextView4 = (AppCompatTextView) _$_findCachedViewById2.findViewById(i3);
            FuelReportResponse fuelReportResponse12 = this.fuelReportResponse;
            if (fuelReportResponse12 != null) {
                if (!Intrinsics.areEqual(fuelReportResponse12 != null ? Double.valueOf(fuelReportResponse12.getTotalEnergyConsumption()) : null, 0.0d)) {
                    FuelReportResponse fuelReportResponse13 = this.fuelReportResponse;
                    if ((fuelReportResponse13 != null ? Double.valueOf(fuelReportResponse13.getTotalEnergyConsumption()) : null) != null) {
                        FuelReportResponse fuelReportResponse14 = this.fuelReportResponse;
                        str = fuelReportResponse14 != null ? ExtensionsKt.toTwoDecimal(fuelReportResponse14.getTotalEnergyConsumption() / 1000) : null;
                        appCompatTextView4.setText(str);
                        ((AppCompatTextView) _$_findCachedViewById(R.id.batteryCard2).findViewById(i3)).setText("0");
                        AppCompatTextView appCompatTextView5 = (AppCompatTextView) _$_findCachedViewById(R.id.idlingCard1).findViewById(i3);
                        fuelReportResponse = this.fuelReportResponse;
                        if (fuelReportResponse != null) {
                            if (!Intrinsics.areEqual(fuelReportResponse != null ? Double.valueOf(fuelReportResponse.getIdleEnergyConsumption()) : null, 0.0d)) {
                                FuelReportResponse fuelReportResponse15 = this.fuelReportResponse;
                                if ((fuelReportResponse15 != null ? Double.valueOf(fuelReportResponse15.getIdleEnergyConsumption()) : null) != null) {
                                    FuelReportResponse fuelReportResponse16 = this.fuelReportResponse;
                                    str2 = fuelReportResponse16 != null ? ExtensionsKt.toOneDecimal(fuelReportResponse16.getIdleEnergyConsumption() / 1000) : null;
                                    appCompatTextView5.setText(str2);
                                    appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.idlingCard2).findViewById(i3);
                                    fuelReportResponse2 = this.fuelReportResponse;
                                    if (fuelReportResponse2 != null) {
                                        if (fuelReportResponse2 == null || fuelReportResponse2.getNoOfIdleInstance() != 0) {
                                            z = false;
                                        }
                                        if (!z) {
                                            FuelReportResponse fuelReportResponse17 = this.fuelReportResponse;
                                            if ((fuelReportResponse17 != null ? Integer.valueOf(fuelReportResponse17.getNoOfIdleInstance()) : null) != null) {
                                                FuelReportResponse fuelReportResponse18 = this.fuelReportResponse;
                                                if (fuelReportResponse18 != null) {
                                                    num = Integer.valueOf(fuelReportResponse18.getNoOfIdleInstance());
                                                }
                                                str5 = String.valueOf(num);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        str2 = "0";
                        appCompatTextView5.setText(str2);
                        appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.idlingCard2).findViewById(i3);
                        fuelReportResponse2 = this.fuelReportResponse;
                        if (fuelReportResponse2 != null) {
                        }
                    }
                }
            }
            str = "0";
            appCompatTextView4.setText(str);
            ((AppCompatTextView) _$_findCachedViewById(R.id.batteryCard2).findViewById(i3)).setText("0");
            AppCompatTextView appCompatTextView52 = (AppCompatTextView) _$_findCachedViewById(R.id.idlingCard1).findViewById(i3);
            fuelReportResponse = this.fuelReportResponse;
            if (fuelReportResponse != null) {
            }
            str2 = "0";
            appCompatTextView52.setText(str2);
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.idlingCard2).findViewById(i3);
            fuelReportResponse2 = this.fuelReportResponse;
            if (fuelReportResponse2 != null) {
            }
        }
        appCompatTextView.setText(str5);
    }

    private final void initIdleTripCards() {
        this.idleTripsAdapter = new IdleTripsAdapter(this, this.tripSummaryDataList, this.vehicleType);
        int i2 = R.id.idlingRV;
        ((RecyclerView) _$_findCachedViewById(i2)).setAdapter(this.idleTripsAdapter);
        ((RecyclerView) _$_findCachedViewById(i2)).setLayoutManager(new LinearLayoutManager(this));
    }

    private final void initSpinner() {
        List list;
        if (this.spinnerSelectionPos == 5 && this.customStartDateSelection != null && this.customEndDateSelection != null) {
            this.isFirstTimeCustom = true;
        }
        String[] stringArray = getResources().getStringArray(uat.psa.mym.mycitroenconnect.R.array.period);
        Intrinsics.checkNotNullExpressionValue(stringArray, "resources.getStringArray(R.array.period)");
        list = ArraysKt___ArraysKt.toList(stringArray);
        this.periods = (ArrayList) list;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, (int) uat.psa.mym.mycitroenconnect.R.layout.layout_curom_spinner_item, this.periods);
        arrayAdapter.setDropDownViewResource(uat.psa.mym.mycitroenconnect.R.layout.layout_curom_spinner_item);
        int i2 = R.id.batteryPeriodSpinner;
        ((CustomSpinner) _$_findCachedViewById(i2)).setAdapter((SpinnerAdapter) arrayAdapter);
        this.periodAdapter = arrayAdapter;
        this.spinnerListener = new AdapterView.OnItemSelectedListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.trips.BatteryUsageActivity$initSpinner$2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(@Nullable AdapterView<?> adapterView, @Nullable View view, int i3, long j2) {
                boolean z;
                TextView textView;
                ArrayList arrayList;
                Object obj;
                Pair<String, String> currentWeekDates;
                ArrayList arrayList2;
                boolean z2;
                Long l2;
                Long l3;
                if (i3 != 0) {
                    int i4 = 1;
                    if (i3 != 1) {
                        i4 = 2;
                        if (i3 != 2) {
                            i4 = 3;
                            if (i3 != 3) {
                                i4 = 4;
                                if (i3 == 4) {
                                    BatteryUsageActivity.this.type = "Month";
                                    currentWeekDates = AppUtil.Companion.getLastMonthDates();
                                } else if (i3 == 5) {
                                    BatteryUsageActivity.this.type = "Custom";
                                    z2 = BatteryUsageActivity.this.isFirstTimeCustom;
                                    if (z2) {
                                        BatteryUsageActivity.this.isFirstTimeCustom = false;
                                        BatteryUsageActivity batteryUsageActivity = BatteryUsageActivity.this;
                                        l2 = batteryUsageActivity.customStartDateSelection;
                                        l3 = BatteryUsageActivity.this.customEndDateSelection;
                                        batteryUsageActivity.setCustomPickerSelection(new androidx.core.util.Pair(l2, l3));
                                        BatteryUsageActivity.this.apiFuelReport();
                                    } else {
                                        BatteryUsageActivity.this.showDateRangePickerDialog();
                                    }
                                }
                            } else {
                                BatteryUsageActivity.this.type = "Month";
                                currentWeekDates = AppUtil.Companion.getCurrentMonthDates();
                            }
                        } else {
                            BatteryUsageActivity.this.type = "Week";
                            currentWeekDates = AppUtil.Companion.getLastWeekDate();
                        }
                    } else {
                        BatteryUsageActivity.this.type = "Week";
                        currentWeekDates = AppUtil.Companion.getCurrentWeekDates();
                    }
                    BatteryUsageActivity.this.startDate = currentWeekDates.getFirst();
                    BatteryUsageActivity.this.endDate = currentWeekDates.getSecond();
                    ((LinearLayout) BatteryUsageActivity.this._$_findCachedViewById(R.id.linBatteryProgressLayout)).setVisibility(8);
                    ((RoundedBarChart) BatteryUsageActivity.this._$_findCachedViewById(R.id.mBatteryEfficiency)).setVisibility(0);
                    textView = (TextView) BatteryUsageActivity.this._$_findCachedViewById(R.id.tvGraphLabel);
                    arrayList2 = BatteryUsageActivity.this.periods;
                    obj = arrayList2.get(i4);
                    textView.setText((CharSequence) obj);
                    BatteryUsageActivity.this.apiFuelReport();
                    BatteryUsageActivity batteryUsageActivity2 = BatteryUsageActivity.this;
                    String string = batteryUsageActivity2.getString(uat.psa.mym.mycitroenconnect.R.string.custom_dates);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.custom_dates)");
                    batteryUsageActivity2.updateCustomDateValue(string);
                } else {
                    z = BatteryUsageActivity.this.isFirstTimeCustom;
                    if (!z) {
                        BatteryUsageActivity.this.type = "Today";
                        BatteryUsageActivity batteryUsageActivity3 = BatteryUsageActivity.this;
                        AppUtil.Companion companion = AppUtil.Companion;
                        batteryUsageActivity3.startDate = companion.getCurrentDateString();
                        BatteryUsageActivity.this.endDate = companion.getCurrentDateString();
                        ((LinearLayout) BatteryUsageActivity.this._$_findCachedViewById(R.id.linBatteryProgressLayout)).setVisibility(8);
                        ((RoundedBarChart) BatteryUsageActivity.this._$_findCachedViewById(R.id.mBatteryEfficiency)).setVisibility(0);
                        textView = (TextView) BatteryUsageActivity.this._$_findCachedViewById(R.id.tvGraphLabel);
                        arrayList = BatteryUsageActivity.this.periods;
                        obj = arrayList.get(0);
                        textView.setText((CharSequence) obj);
                        BatteryUsageActivity.this.apiFuelReport();
                        BatteryUsageActivity batteryUsageActivity22 = BatteryUsageActivity.this;
                        String string2 = batteryUsageActivity22.getString(uat.psa.mym.mycitroenconnect.R.string.custom_dates);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.custom_dates)");
                        batteryUsageActivity22.updateCustomDateValue(string2);
                    }
                }
                BatteryUsageActivity.this.spinnerSelectionPos = i3;
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(@Nullable AdapterView<?> adapterView) {
            }
        };
        if (this.spinnerSelectionPos == 5) {
            setCustomPickerSelection(new androidx.core.util.Pair<>(this.customStartDateSelection, this.customEndDateSelection));
            setCustomSpinnerSelection();
        } else {
            ((CustomSpinner) _$_findCachedViewById(i2)).setSelection(this.spinnerSelectionPos);
        }
        CustomSpinner customSpinner = (CustomSpinner) _$_findCachedViewById(i2);
        AdapterView.OnItemSelectedListener onItemSelectedListener = this.spinnerListener;
        if (onItemSelectedListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinnerListener");
            onItemSelectedListener = null;
        }
        customSpinner.setOnItemSelectedListener(onItemSelectedListener);
    }

    private final void populateGraphData(FuelReportResponse fuelReportResponse) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        double d2 = 1000;
        arrayList.add(new BarEntry(1.0f, Float.parseFloat(ExtensionsKt.toTwoDecimal(fuelReportResponse.getTotalEnergyConsumption() / d2))));
        arrayList2.add(new BarEntry(1.0f, Float.parseFloat(ExtensionsKt.toTwoDecimal(fuelReportResponse.getIdleEnergyConsumption() / d2))));
        BarDataSet barDataSet = new BarDataSet(arrayList, getString(uat.psa.mym.mycitroenconnect.R.string.total_energy_consumption));
        barDataSet.setColor(Color.parseColor("#FCAEAE"));
        barDataSet.setHighLightColor(0);
        barDataSet.setHighLightAlpha(0);
        BarDataSet barDataSet2 = new BarDataSet(arrayList2, getString(uat.psa.mym.mycitroenconnect.R.string.total_idle_energy_consumption));
        barDataSet2.setColor(Color.parseColor("#9D0605"));
        barDataSet2.setHighLightColor(0);
        barDataSet2.setHighLightAlpha(0);
        BarData barData = new BarData(barDataSet);
        barData.setHighlightEnabled(false);
        barData.addDataSet(barDataSet2);
        int i2 = R.id.mBatteryEfficiency;
        ((RoundedBarChart) _$_findCachedViewById(i2)).setData(barData);
        barData.setDrawValues(false);
        barData.setBarWidth(0.4f);
        ((RoundedBarChart) _$_findCachedViewById(i2)).getAxisLeft().setAxisMinimum(0.0f);
        ((RoundedBarChart) _$_findCachedViewById(i2)).getXAxis().setAxisMinimum(0.0f);
        ((RoundedBarChart) _$_findCachedViewById(i2)).getDescription().setEnabled(false);
        setLegends();
        ((RoundedBarChart) _$_findCachedViewById(i2)).getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        ((RoundedBarChart) _$_findCachedViewById(i2)).getXAxis().setAxisMinimum(barData.getXMin() - 0.5f);
        ((RoundedBarChart) _$_findCachedViewById(i2)).getXAxis().setAxisMaximum(barData.getXMax() + 0.5f);
        ((RoundedBarChart) _$_findCachedViewById(i2)).getXAxis().setSpaceMax(5.0f);
        ((RoundedBarChart) _$_findCachedViewById(i2)).getAxisRight().setEnabled(false);
        ((RoundedBarChart) _$_findCachedViewById(i2)).setExtraOffsets(0.0f, 0.0f, 0.0f, 5.0f);
        ((RoundedBarChart) _$_findCachedViewById(i2)).setScaleEnabled(false);
        ((RoundedBarChart) _$_findCachedViewById(i2)).invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"SetTextI18n"})
    public final void setCustomPickerSelection(androidx.core.util.Pair<Long, Long> pair) {
        Long l2 = pair.first;
        Long l3 = pair.second;
        this.customStartDateSelection = l2;
        this.customEndDateSelection = l3;
        AppUtil.Companion companion = AppUtil.Companion;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.ONLY_DATE_FORMAT, companion.getDefaultLocale());
        this.startDate = simpleDateFormat.format(l2).toString();
        this.endDate = simpleDateFormat.format(l3).toString();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(AppConstants.MM_DD_YY_FORMAT, companion.getDefaultLocale());
        String str = simpleDateFormat2.format(l2).toString();
        String str2 = simpleDateFormat2.format(l3).toString();
        Logger logger = Logger.INSTANCE;
        logger.d("sDate : " + l2 + ", eDate: " + l3 + ",startDate: " + this.startDate + ", endDate: " + this.endDate + "startDate1: " + str + ", endDate1: " + str2);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" to ");
        sb.append(str2);
        ((TextView) _$_findCachedViewById(R.id.tvGraphLabel)).setText(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(" - ");
        sb2.append(str2);
        updateCustomDateValue(sb2.toString());
    }

    private final void setCustomSpinnerSelection() {
        ((CustomSpinner) _$_findCachedViewById(R.id.batteryPeriodSpinner)).post(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.activities.trips.b
            @Override // java.lang.Runnable
            public final void run() {
                BatteryUsageActivity.m111setCustomSpinnerSelection$lambda1(BatteryUsageActivity.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setCustomSpinnerSelection$lambda-1  reason: not valid java name */
    public static final void m111setCustomSpinnerSelection$lambda1(BatteryUsageActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((CustomSpinner) this$0._$_findCachedViewById(R.id.batteryPeriodSpinner)).setSelection(5);
    }

    private final void setLegends() {
        Legend legend = ((RoundedBarChart) _$_findCachedViewById(R.id.mBatteryEfficiency)).getLegend();
        Intrinsics.checkNotNullExpressionValue(legend, "mBatteryEfficiency.legend");
        legend.getEntries();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setYEntrySpace(5.0f);
        legend.setWordWrapEnabled(true);
        if (Intrinsics.areEqual(this.vehicleType, AppConstants.ICE)) {
            String string = getString(uat.psa.mym.mycitroenconnect.R.string.total_fuel_consumption);
            Legend.LegendForm legendForm = Legend.LegendForm.CIRCLE;
            legend.setCustom(new LegendEntry[]{new LegendEntry(string, legendForm, 10.0f, 2.0f, null, Color.parseColor("#FCAEAE")), new LegendEntry(getString(uat.psa.mym.mycitroenconnect.R.string.total_idle_fuel_consumption), legendForm, 10.0f, 2.0f, null, Color.parseColor("#9D0605"))});
        } else {
            String string2 = getString(uat.psa.mym.mycitroenconnect.R.string.total_energy_consumption);
            Legend.LegendForm legendForm2 = Legend.LegendForm.CIRCLE;
            legend.setCustom(new LegendEntry[]{new LegendEntry(string2, legendForm2, 10.0f, 2.0f, null, Color.parseColor("#FCAEAE")), new LegendEntry(getString(uat.psa.mym.mycitroenconnect.R.string.total_idle_energy_consumption), legendForm2, 10.0f, 2.0f, null, Color.parseColor("#9D0605"))});
        }
        legend.setEnabled(true);
    }

    private final void setListeners() {
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivBack)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivIdlingInfo)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivEnergyConsInfo)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivIdlingTripInfo)).setOnClickListener(this);
    }

    private final void showAlertDialogInfo() {
        Window window;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflate = getLayoutInflater().inflate(uat.psa.mym.mycitroenconnect.R.layout.layout_info_dlg, (ViewGroup) null);
        Intrinsics.checkNotNullExpressionValue(inflate, "layoutInflater.inflate(R…ut.layout_info_dlg, null)");
        builder.setView(inflate);
        final AlertDialog create = builder.create();
        ((AppCompatImageView) inflate.findViewById(R.id.ivInfoClose)).setOnClickListener(new View.OnClickListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.trips.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BatteryUsageActivity.m112showAlertDialogInfo$lambda3(create, view);
            }
        });
        if (create != null && (window = create.getWindow()) != null) {
            window.setBackgroundDrawableResource(uat.psa.mym.mycitroenconnect.R.drawable.ic_dialog_rounded_background);
        }
        if (create != null) {
            create.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showAlertDialogInfo$lambda-3  reason: not valid java name */
    public static final void m112showAlertDialogInfo$lambda3(AlertDialog alertDialog, View view) {
        if (alertDialog != null) {
            alertDialog.dismiss();
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
        newInstance.show(getSupportFragmentManager(), DateRangePickerDialog.Companion.getTAG());
        newInstance.setOnDateSelectListener(new OnDateSelectListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.trips.BatteryUsageActivity$showDateRangePickerDialog$1
            @Override // com.psa.mym.mycitroenconnect.controller.dialog.OnDateSelectListener
            public void onDateSelect(@NotNull androidx.core.util.Pair<Long, Long> selection) {
                Intrinsics.checkNotNullParameter(selection, "selection");
                BatteryUsageActivity.this.setCustomPickerSelection(selection);
                BatteryUsageActivity.this.apiFuelReport();
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

    @Override // com.psa.mym.mycitroenconnect.BusBaseActivity, com.psa.mym.mycitroenconnect.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseActivity, com.psa.mym.mycitroenconnect.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i2) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            if (findViewById != null) {
                map.put(Integer.valueOf(i2), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @Subscribe
    public final void getMessage(@NotNull ErrorResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (Intrinsics.areEqual(event.getApiName(), "summary")) {
            initEnergyConsumptionCards();
            ((AppCompatTextView) _$_findCachedViewById(R.id.vehicleIdleTime)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.zero_hour));
            apiTripIdleDetails();
            return;
        }
        displayData();
        Logger logger = Logger.INSTANCE;
        logger.e("Api Name: " + event.getApiName());
        logger.e("Error Code: " + event.getStatusCode());
        logger.e("Error Message: " + event.getMsg());
    }

    @Subscribe
    public final void getMessage(@NotNull FailResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        ExtensionsKt.showToast(this, event.getMessage());
        String apiName = event.getApiName();
        if (Intrinsics.areEqual(apiName, "FuelReport")) {
            apiTripSummary();
        } else if (!Intrinsics.areEqual(apiName, "EnergyUsage")) {
            displayData();
        } else {
            int i2 = R.id.mBatteryEfficiency;
            ((RoundedBarChart) _$_findCachedViewById(i2)).setNoDataText(event.getMessage());
            ((RoundedBarChart) _$_findCachedViewById(i2)).invalidate();
            if (((RoundedBarChart) _$_findCachedViewById(i2)).getData() != 0) {
                ((RoundedBarChart) _$_findCachedViewById(i2)).clear();
            }
        }
    }

    @Subscribe
    public final void getMessage(@NotNull DrivingBehaviourResponse event) {
        String string;
        Intrinsics.checkNotNullParameter(event, "event");
        this.drivingBehaviourResponse = event;
        initEnergyConsumptionCards();
        AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.vehicleIdleTime);
        DrivingBehaviourResponse drivingBehaviourResponse = this.drivingBehaviourResponse;
        DrivingBehaviourResponse drivingBehaviourResponse2 = null;
        if (drivingBehaviourResponse == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drivingBehaviourResponse");
            drivingBehaviourResponse = null;
        }
        if (drivingBehaviourResponse.getIdleTime() == 0.0d) {
            string = getString(uat.psa.mym.mycitroenconnect.R.string.zero_hour);
        } else {
            AppUtil.Companion companion = AppUtil.Companion;
            DrivingBehaviourResponse drivingBehaviourResponse3 = this.drivingBehaviourResponse;
            if (drivingBehaviourResponse3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("drivingBehaviourResponse");
            } else {
                drivingBehaviourResponse2 = drivingBehaviourResponse3;
            }
            string = companion.convertSeconds((long) (drivingBehaviourResponse2.getIdleTime() * 60));
        }
        appCompatTextView.setText(string);
        apiTripIdleDetails();
    }

    @Subscribe
    public final void getMessage(@NotNull EnergyUsageResponse event) {
        String str;
        Intrinsics.checkNotNullParameter(event, "event");
        int i2 = R.id.mBatteryEfficiency;
        if (((RoundedBarChart) _$_findCachedViewById(i2)).getData() != 0) {
            ((RoundedBarChart) _$_findCachedViewById(i2)).invalidate();
            ((RoundedBarChart) _$_findCachedViewById(i2)).clear();
        }
        Logger logger = Logger.INSTANCE;
        logger.e("EnergyUsageResponse size : " + event.getEnergyUsageResponse().size());
        if (event.getEnergyUsageResponse().size() > 0) {
            str = "EnergyUsageResponse : " + event;
        } else {
            str = "Get dummy data";
        }
        logger.e(str);
        ((LinearLayout) _$_findCachedViewById(R.id.linBatteryProgressLayout)).setVisibility(8);
        ((RoundedBarChart) _$_findCachedViewById(i2)).setVisibility(0);
    }

    @Subscribe
    public final void getMessage(@NotNull FuelReportResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.fuelReportResponse = event;
        populateGraphData(event);
        apiTripSummary();
    }

    @Subscribe
    public final void getMessage(@NotNull IdleDetailsResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        displayData();
        this.tripSummaryDataList = event.getTripListResponseDTO();
        initIdleTripCards();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        handleBackPressed();
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        Intrinsics.checkNotNull(view);
        switch (view.getId()) {
            case uat.psa.mym.mycitroenconnect.R.id.ivBack /* 2131362334 */:
                handleBackPressed();
                return;
            case uat.psa.mym.mycitroenconnect.R.id.ivEnergyConsInfo /* 2131362365 */:
            case uat.psa.mym.mycitroenconnect.R.id.ivIdlingInfo /* 2131362378 */:
            case uat.psa.mym.mycitroenconnect.R.id.ivIdlingTripInfo /* 2131362379 */:
                showAlertDialogInfo();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(uat.psa.mym.mycitroenconnect.R.layout.activity_battery_usage);
        getIntentData();
        init();
        initSpinner();
        setListeners();
    }
}
