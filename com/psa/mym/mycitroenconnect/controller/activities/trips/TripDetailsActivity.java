package com.psa.mym.mycitroenconnect.controller.activities.trips;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.textfield.TextInputEditText;
import com.psa.mym.mycitroenconnect.BusBaseActivity;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.dashboard.LocateCarActivity;
import com.psa.mym.mycitroenconnect.controller.adapters.MyTripsCardAdapter;
import com.psa.mym.mycitroenconnect.controller.adapters.TipsCardAdapter;
import com.psa.mym.mycitroenconnect.controller.dialog.ImagePreviewDialog;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.FailResponse;
import com.psa.mym.mycitroenconnect.model.PostCommonResponse;
import com.psa.mym.mycitroenconnect.model.TipsCardData;
import com.psa.mym.mycitroenconnect.model.TripCardData;
import com.psa.mym.mycitroenconnect.model.dashboard.VehicleLocationResponse;
import com.psa.mym.mycitroenconnect.model.trip.EnergyUsageResponse;
import com.psa.mym.mycitroenconnect.model.trip.OnGoingResponse;
import com.psa.mym.mycitroenconnect.model.trip.TripDetailsResponse;
import com.psa.mym.mycitroenconnect.services.DashboardService;
import com.psa.mym.mycitroenconnect.services.TripService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.google_map_direction.GMapV2Direction;
import com.psa.mym.mycitroenconnect.utils.google_map_direction.GMapV2DirectionAsyncTask;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.RoundedBarChart;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.Skeleton;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayoutUtils;
import com.rd.PageIndicatorView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.cli.HelpFormatter;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class TripDetailsActivity extends BusBaseActivity {
    @Nullable
    private MyTripsCardAdapter batteryCardAdapter;
    @Nullable
    private LatLng destLatLng;
    @Nullable
    private GridLayoutManager gridLayoutManager;
    private boolean isMapDrawn;
    private boolean isOngoingTrip;
    private long lastClickTime;
    @Nullable
    private OnGoingResponse onGoingRespObj;
    private Skeleton skeleton;
    @Nullable
    private LatLng startLatLng;
    @Nullable
    private TipsCardAdapter tipsCardAdapter;
    @Nullable
    private MyTripsCardAdapter tripCardAdapter;
    private TripDetailsResponse tripDetailsResponse;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String tripID = "";
    private int position = -1;
    @NotNull
    private String vehicleType = "";
    @NotNull
    private ArrayList<String> viaLocations = new ArrayList<>();
    @NotNull
    private String startLanLong = "";
    @NotNull
    private String endLanLong = "";
    @NotNull
    private String mapURL = "";

    private final void disableEditTripName() {
        int i2 = R.id.edtTripName;
        ((TextInputEditText) _$_findCachedViewById(i2)).setEnabled(false);
        TextInputEditText textInputEditText = (TextInputEditText) _$_findCachedViewById(i2);
        TripDetailsResponse tripDetailsResponse = this.tripDetailsResponse;
        if (tripDetailsResponse == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
            tripDetailsResponse = null;
        }
        textInputEditText.setText(tripDetailsResponse.getTripName());
        AppCompatImageView ivEditTripName = (AppCompatImageView) _$_findCachedViewById(R.id.ivEditTripName);
        Intrinsics.checkNotNullExpressionValue(ivEditTripName, "ivEditTripName");
        ExtensionsKt.show(ivEditTripName);
        View viewTripNameLine = _$_findCachedViewById(R.id.viewTripNameLine);
        Intrinsics.checkNotNullExpressionValue(viewTripNameLine, "viewTripNameLine");
        ExtensionsKt.hide(viewTripNameLine);
        AppCompatImageView ivCloseTripNameEdit = (AppCompatImageView) _$_findCachedViewById(R.id.ivCloseTripNameEdit);
        Intrinsics.checkNotNullExpressionValue(ivCloseTripNameEdit, "ivCloseTripNameEdit");
        ExtensionsKt.hide(ivCloseTripNameEdit);
        AppCompatImageView ivUpdateTripName = (AppCompatImageView) _$_findCachedViewById(R.id.ivUpdateTripName);
        Intrinsics.checkNotNullExpressionValue(ivUpdateTripName, "ivUpdateTripName");
        ExtensionsKt.hide(ivUpdateTripName);
    }

    private final void displayChart() {
        LinearLayout batteryUsageChart = (LinearLayout) _$_findCachedViewById(R.id.batteryUsageChart);
        Intrinsics.checkNotNullExpressionValue(batteryUsageChart, "batteryUsageChart");
        ExtensionsKt.show(batteryUsageChart);
        LinearLayout linChart = (LinearLayout) _$_findCachedViewById(R.id.linChart);
        Intrinsics.checkNotNullExpressionValue(linChart, "linChart");
        ExtensionsKt.show(linChart);
        LinearLayout linBatteryProgressLayout = (LinearLayout) _$_findCachedViewById(R.id.linBatteryProgressLayout);
        Intrinsics.checkNotNullExpressionValue(linBatteryProgressLayout, "linBatteryProgressLayout");
        ExtensionsKt.hide(linBatteryProgressLayout);
    }

    private final void displayData() {
        Skeleton skeleton = this.skeleton;
        if (skeleton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skeleton");
            skeleton = null;
        }
        ExtensionsKt.showData$default(skeleton, 0L, 1, null);
    }

    private final void displayLoading() {
        ConstraintLayout clParent = (ConstraintLayout) _$_findCachedViewById(R.id.clParent);
        Intrinsics.checkNotNullExpressionValue(clParent, "clParent");
        Skeleton createSkeleton = SkeletonLayoutUtils.createSkeleton(clParent, ExtensionsKt.skeletonConfig(this));
        createSkeleton.showSkeleton();
        this.skeleton = createSkeleton;
    }

    private final void enableEditTripName() {
        ((TextInputEditText) _$_findCachedViewById(R.id.edtTripName)).setEnabled(true);
        View viewTripNameLine = _$_findCachedViewById(R.id.viewTripNameLine);
        Intrinsics.checkNotNullExpressionValue(viewTripNameLine, "viewTripNameLine");
        ExtensionsKt.show(viewTripNameLine);
        AppCompatImageView ivCloseTripNameEdit = (AppCompatImageView) _$_findCachedViewById(R.id.ivCloseTripNameEdit);
        Intrinsics.checkNotNullExpressionValue(ivCloseTripNameEdit, "ivCloseTripNameEdit");
        ExtensionsKt.show(ivCloseTripNameEdit);
        AppCompatImageView ivUpdateTripName = (AppCompatImageView) _$_findCachedViewById(R.id.ivUpdateTripName);
        Intrinsics.checkNotNullExpressionValue(ivUpdateTripName, "ivUpdateTripName");
        ExtensionsKt.show(ivUpdateTripName);
        AppCompatImageView ivEditTripName = (AppCompatImageView) _$_findCachedViewById(R.id.ivEditTripName);
        Intrinsics.checkNotNullExpressionValue(ivEditTripName, "ivEditTripName");
        ExtensionsKt.hide(ivEditTripName);
    }

    private final void getIntentData() {
        if (getIntent().hasExtra(AppConstants.BUNDLE_KEY_IS_ONGOING_TRIP)) {
            this.isOngoingTrip = getIntent().getBooleanExtra(AppConstants.BUNDLE_KEY_IS_ONGOING_TRIP, false);
        }
        if (getIntent().hasExtra(AppConstants.BUNDLE_KEY_ONGOING_TRIP_OBJ)) {
            this.onGoingRespObj = (OnGoingResponse) getIntent().getParcelableExtra(AppConstants.BUNDLE_KEY_ONGOING_TRIP_OBJ);
        }
        String stringExtra = getIntent().getStringExtra("tripId");
        Intrinsics.checkNotNull(stringExtra);
        this.tripID = stringExtra;
        if (getIntent().hasExtra(AppConstants.ARG_POSITION)) {
            this.position = getIntent().getIntExtra(AppConstants.ARG_POSITION, -1);
        }
    }

    private final void getOnGoingTripData() {
        displayChart();
        if (!this.isOngoingTrip) {
            displayData();
            return;
        }
        getOngoingTrip();
        LinearLayout batteryUsageChart = (LinearLayout) _$_findCachedViewById(R.id.batteryUsageChart);
        Intrinsics.checkNotNullExpressionValue(batteryUsageChart, "batteryUsageChart");
        ExtensionsKt.hide(batteryUsageChart);
    }

    private final void getOngoingTrip() {
        new TripService().callOnGoingAPI(this, SharedPref.Companion.getVinNumber(this));
    }

    private final void getVehicleLocation() {
        new DashboardService().callGetVehicleCurrentLocation(this, SharedPref.Companion.getVinNumber(this));
    }

    private final void initBatteryCards() {
        String str;
        List mutableListOf;
        String str2;
        TripDetailsResponse tripDetailsResponse = null;
        if (Intrinsics.areEqual(this.vehicleType, AppConstants.ICE)) {
            TripCardData[] tripCardDataArr = new TripCardData[4];
            String string = getString(uat.psa.mym.mycitroenconnect.R.string.lbl_fuel_consumed);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.lbl_fuel_consumed)");
            TripDetailsResponse tripDetailsResponse2 = this.tripDetailsResponse;
            if (tripDetailsResponse2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
                tripDetailsResponse2 = null;
            }
            String oneDecimal = ExtensionsKt.toOneDecimal(tripDetailsResponse2.getTotalEnergyConsumption());
            String string2 = getString(uat.psa.mym.mycitroenconnect.R.string.litre_unit);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.litre_unit)");
            tripCardDataArr[0] = new TripCardData(string, oneDecimal, string2, uat.psa.mym.mycitroenconnect.R.drawable.ic_fuel_drop);
            String string3 = getString(uat.psa.mym.mycitroenconnect.R.string.label_no_of_idling_instances);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.label_no_of_idling_instances)");
            TripDetailsResponse tripDetailsResponse3 = this.tripDetailsResponse;
            if (tripDetailsResponse3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
                tripDetailsResponse3 = null;
            }
            tripCardDataArr[1] = new TripCardData(string3, String.valueOf(tripDetailsResponse3.getNoOfIdleInstance()), "", uat.psa.mym.mycitroenconnect.R.drawable.ic_smoke);
            String string4 = getString(uat.psa.mym.mycitroenconnect.R.string.label_vehicle_idling_time);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.label_vehicle_idling_time)");
            TripDetailsResponse tripDetailsResponse4 = this.tripDetailsResponse;
            if (tripDetailsResponse4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
                tripDetailsResponse4 = null;
            }
            if (tripDetailsResponse4.getIdleTime() == 0.0d) {
                String string5 = getString(uat.psa.mym.mycitroenconnect.R.string.zero_hour);
                Intrinsics.checkNotNullExpressionValue(string5, "{\n                      …ur)\n                    }");
                str2 = string5;
            } else {
                AppUtil.Companion companion = AppUtil.Companion;
                TripDetailsResponse tripDetailsResponse5 = this.tripDetailsResponse;
                if (tripDetailsResponse5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
                    tripDetailsResponse5 = null;
                }
                str2 = companion.convertSeconds((long) (tripDetailsResponse5.getIdleTime() * 60));
            }
            tripCardDataArr[2] = new TripCardData(string4, str2, "", uat.psa.mym.mycitroenconnect.R.drawable.ic_clock);
            String string6 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.label_fuel_consumed);
            Intrinsics.checkNotNullExpressionValue(string6, "resources.getString(R.string.label_fuel_consumed)");
            TripDetailsResponse tripDetailsResponse6 = this.tripDetailsResponse;
            if (tripDetailsResponse6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
            } else {
                tripDetailsResponse = tripDetailsResponse6;
            }
            String valueOf = String.valueOf((int) tripDetailsResponse.getIdleEnergyConsumption());
            String string7 = getString(uat.psa.mym.mycitroenconnect.R.string.litre_unit);
            Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.litre_unit)");
            tripCardDataArr[3] = new TripCardData(string6, valueOf, string7, uat.psa.mym.mycitroenconnect.R.drawable.ic_fuel_drop);
            mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(tripCardDataArr);
        } else {
            TripCardData[] tripCardDataArr2 = new TripCardData[4];
            String string8 = getString(uat.psa.mym.mycitroenconnect.R.string.lbl_energy_consumed);
            Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.lbl_energy_consumed)");
            TripDetailsResponse tripDetailsResponse7 = this.tripDetailsResponse;
            if (tripDetailsResponse7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
                tripDetailsResponse7 = null;
            }
            double d2 = 1000;
            String twoDecimal = ExtensionsKt.toTwoDecimal(tripDetailsResponse7.getTotalEnergyConsumption() / d2);
            String string9 = getString(uat.psa.mym.mycitroenconnect.R.string.kwh);
            Intrinsics.checkNotNullExpressionValue(string9, "getString(R.string.kwh)");
            tripCardDataArr2[0] = new TripCardData(string8, twoDecimal, string9, uat.psa.mym.mycitroenconnect.R.drawable.ic_energy);
            String string10 = getString(uat.psa.mym.mycitroenconnect.R.string.label_no_of_idling_instances);
            Intrinsics.checkNotNullExpressionValue(string10, "getString(R.string.label_no_of_idling_instances)");
            TripDetailsResponse tripDetailsResponse8 = this.tripDetailsResponse;
            if (tripDetailsResponse8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
                tripDetailsResponse8 = null;
            }
            tripCardDataArr2[1] = new TripCardData(string10, String.valueOf(tripDetailsResponse8.getNoOfIdleInstance()), "", uat.psa.mym.mycitroenconnect.R.drawable.ic_smoke);
            String string11 = getString(uat.psa.mym.mycitroenconnect.R.string.label_vehicle_idling_time);
            Intrinsics.checkNotNullExpressionValue(string11, "getString(R.string.label_vehicle_idling_time)");
            TripDetailsResponse tripDetailsResponse9 = this.tripDetailsResponse;
            if (tripDetailsResponse9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
                tripDetailsResponse9 = null;
            }
            if (tripDetailsResponse9.getIdleTime() == 0.0d) {
                String string12 = getString(uat.psa.mym.mycitroenconnect.R.string.zero_hour);
                Intrinsics.checkNotNullExpressionValue(string12, "{\n                      …ur)\n                    }");
                str = string12;
            } else {
                AppUtil.Companion companion2 = AppUtil.Companion;
                TripDetailsResponse tripDetailsResponse10 = this.tripDetailsResponse;
                if (tripDetailsResponse10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
                    tripDetailsResponse10 = null;
                }
                str = companion2.convertSeconds((long) (tripDetailsResponse10.getIdleTime() * 60));
            }
            tripCardDataArr2[2] = new TripCardData(string11, str, "", uat.psa.mym.mycitroenconnect.R.drawable.ic_clock);
            String string13 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.label_energy_consumed);
            Intrinsics.checkNotNullExpressionValue(string13, "resources.getString(R.st…ng.label_energy_consumed)");
            TripDetailsResponse tripDetailsResponse11 = this.tripDetailsResponse;
            if (tripDetailsResponse11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
            } else {
                tripDetailsResponse = tripDetailsResponse11;
            }
            String twoDecimal2 = ExtensionsKt.toTwoDecimal(tripDetailsResponse.getIdleEnergyConsumption() / d2);
            String string14 = getString(uat.psa.mym.mycitroenconnect.R.string.kwh);
            Intrinsics.checkNotNullExpressionValue(string14, "getString(R.string.kwh)");
            tripCardDataArr2[3] = new TripCardData(string13, twoDecimal2, string14, uat.psa.mym.mycitroenconnect.R.drawable.ic_energy);
            mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(tripCardDataArr2);
        }
        this.batteryCardAdapter = new MyTripsCardAdapter(this, mutableListOf);
        GridLayoutManager gridLayoutManager = new GridLayoutManager((Context) this, 2, 1, false);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvBatteryConsCardViews);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(this.batteryCardAdapter);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void initSourceDestCard(TripDetailsResponse tripDetailsResponse, OnGoingResponse onGoingResponse) {
        AppCompatTextView appCompatTextView;
        String string;
        int i2;
        char c2;
        int i3;
        AppCompatTextView appCompatTextView2;
        String string2;
        CardView cardView;
        String str;
        boolean z;
        if (!this.isOngoingTrip) {
            if (tripDetailsResponse != null) {
                View cardTdOngngTrip = _$_findCachedViewById(R.id.cardTdOngngTrip);
                Intrinsics.checkNotNullExpressionValue(cardTdOngngTrip, "cardTdOngngTrip");
                ExtensionsKt.hide(cardTdOngngTrip);
                int i4 = R.id.cardTdTrip;
                View cardTdTrip = _$_findCachedViewById(i4);
                Intrinsics.checkNotNullExpressionValue(cardTdTrip, "cardTdTrip");
                ExtensionsKt.show(cardTdTrip);
                List<String> tripRoute = tripDetailsResponse.getTripRoute();
                if (!(tripRoute == null || tripRoute.isEmpty())) {
                    if (tripDetailsResponse.getTripRoute().size() == 1) {
                        String[] split = TextUtils.split(tripDetailsResponse.getTripRoute().get(0), ",");
                        AppCompatTextView appCompatTextView3 = (AppCompatTextView) _$_findCachedViewById(i4).findViewById(R.id.tvTripSummarySource);
                        AppUtil.Companion companion = AppUtil.Companion;
                        String str2 = split[0];
                        Intrinsics.checkNotNullExpressionValue(str2, "allIdsArray[0]");
                        double parseDouble = Double.parseDouble(str2);
                        String str3 = split[1];
                        Intrinsics.checkNotNullExpressionValue(str3, "allIdsArray[1]");
                        appCompatTextView3.setText(companion.getAddressNameString(this, parseDouble, Double.parseDouble(str3)));
                        AppCompatTextView appCompatTextView4 = (AppCompatTextView) _$_findCachedViewById(i4).findViewById(R.id.tvTripSummaryDest);
                        String str4 = split[0];
                        Intrinsics.checkNotNullExpressionValue(str4, "allIdsArray[0]");
                        double parseDouble2 = Double.parseDouble(str4);
                        String str5 = split[1];
                        Intrinsics.checkNotNullExpressionValue(str5, "allIdsArray[1]");
                        appCompatTextView4.setText(companion.getAddressNameString(this, parseDouble2, Double.parseDouble(str5)));
                        String str6 = split[0];
                        Intrinsics.checkNotNullExpressionValue(str6, "allIdsArray[0]");
                        double parseDouble3 = Double.parseDouble(str6);
                        String str7 = split[1];
                        Intrinsics.checkNotNullExpressionValue(str7, "allIdsArray[1]");
                        this.startLatLng = new LatLng(parseDouble3, Double.parseDouble(str7));
                        String str8 = split[0];
                        Intrinsics.checkNotNullExpressionValue(str8, "allIdsArray[0]");
                        double parseDouble4 = Double.parseDouble(str8);
                        String str9 = split[1];
                        Intrinsics.checkNotNullExpressionValue(str9, "allIdsArray[1]");
                        this.destLatLng = new LatLng(parseDouble4, Double.parseDouble(str9));
                    } else {
                        int i5 = 0;
                        for (Object obj : tripDetailsResponse.getTripRoute()) {
                            int i6 = i5 + 1;
                            if (i5 < 0) {
                                CollectionsKt__CollectionsKt.throwIndexOverflow();
                            }
                            String str10 = (String) obj;
                            if (i5 != 0 && i5 != tripDetailsResponse.getTripRoute().size() - 1) {
                                this.viaLocations.add(str10);
                            }
                            i5 = i6;
                        }
                        String[] split2 = TextUtils.split(tripDetailsResponse.getTripRoute().get(0), ",");
                        StringBuilder sb = new StringBuilder();
                        String str11 = split2[0];
                        Intrinsics.checkNotNullExpressionValue(str11, "startAllIdsArray[0]");
                        sb.append(Double.parseDouble(str11));
                        sb.append(AbstractJsonLexerKt.COMMA);
                        String str12 = split2[1];
                        Intrinsics.checkNotNullExpressionValue(str12, "startAllIdsArray[1]");
                        sb.append(Double.parseDouble(str12));
                        this.startLanLong = sb.toString();
                        int i7 = R.id.cardTdTrip;
                        AppCompatTextView appCompatTextView5 = (AppCompatTextView) _$_findCachedViewById(i7).findViewById(R.id.tvTripSummarySource);
                        AppUtil.Companion companion2 = AppUtil.Companion;
                        String str13 = split2[0];
                        Intrinsics.checkNotNullExpressionValue(str13, "startAllIdsArray[0]");
                        double parseDouble5 = Double.parseDouble(str13);
                        String str14 = split2[1];
                        Intrinsics.checkNotNullExpressionValue(str14, "startAllIdsArray[1]");
                        appCompatTextView5.setText(companion2.getAddressNameString(this, parseDouble5, Double.parseDouble(str14)));
                        String str15 = split2[0];
                        Intrinsics.checkNotNullExpressionValue(str15, "startAllIdsArray[0]");
                        double parseDouble6 = Double.parseDouble(str15);
                        String str16 = split2[1];
                        Intrinsics.checkNotNullExpressionValue(str16, "startAllIdsArray[1]");
                        this.startLatLng = new LatLng(parseDouble6, Double.parseDouble(str16));
                        String[] split3 = TextUtils.split(tripDetailsResponse.getTripRoute().get(tripDetailsResponse.getTripRoute().size() - 1), ",");
                        StringBuilder sb2 = new StringBuilder();
                        String str17 = split3[0];
                        Intrinsics.checkNotNullExpressionValue(str17, "endAllIdsArray[0]");
                        sb2.append(Double.parseDouble(str17));
                        sb2.append(AbstractJsonLexerKt.COMMA);
                        String str18 = split3[1];
                        Intrinsics.checkNotNullExpressionValue(str18, "endAllIdsArray[1]");
                        sb2.append(Double.parseDouble(str18));
                        this.endLanLong = sb2.toString();
                        String str19 = split3[0];
                        Intrinsics.checkNotNullExpressionValue(str19, "endAllIdsArray[0]");
                        double parseDouble7 = Double.parseDouble(str19);
                        String str20 = split3[1];
                        Intrinsics.checkNotNullExpressionValue(str20, "endAllIdsArray[1]");
                        this.destLatLng = new LatLng(parseDouble7, Double.parseDouble(str20));
                        AppCompatTextView appCompatTextView6 = (AppCompatTextView) _$_findCachedViewById(i7).findViewById(R.id.tvTripSummaryDest);
                        String str21 = split3[0];
                        Intrinsics.checkNotNullExpressionValue(str21, "endAllIdsArray[0]");
                        double parseDouble8 = Double.parseDouble(str21);
                        String str22 = split3[1];
                        Intrinsics.checkNotNullExpressionValue(str22, "endAllIdsArray[1]");
                        appCompatTextView6.setText(companion2.getAddressNameString(this, parseDouble8, Double.parseDouble(str22)));
                    }
                }
                if (tripDetailsResponse.getDistanceTravelled() > 1.0d) {
                    appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.cardTdTrip).findViewById(R.id.tvTripDistanceCovered);
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String format = String.format("%.1f", Arrays.copyOf(new Object[]{Double.valueOf(tripDetailsResponse.getDistanceTravelled())}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    string = getString(uat.psa.mym.mycitroenconnect.R.string.kms, new Object[]{String.valueOf(Double.parseDouble(format))});
                } else {
                    appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.cardTdTrip).findViewById(R.id.tvTripDistanceCovered);
                    StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                    String format2 = String.format("%.1f", Arrays.copyOf(new Object[]{Double.valueOf(tripDetailsResponse.getDistanceTravelled())}, 1));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
                    string = getString(uat.psa.mym.mycitroenconnect.R.string.km, new Object[]{String.valueOf(Double.parseDouble(format2))});
                }
                appCompatTextView.setText(string);
                ((AppCompatTextView) _$_findCachedViewById(R.id.cardTdTrip).findViewById(R.id.tvTripDuration)).setText(AppUtil.Companion.convertSeconds((long) (tripDetailsResponse.getTotalTimeTravelled() * 60)));
                return;
            }
            return;
        }
        int i8 = R.id.cardTdOngngTrip;
        View cardTdOngngTrip2 = _$_findCachedViewById(i8);
        Intrinsics.checkNotNullExpressionValue(cardTdOngngTrip2, "cardTdOngngTrip");
        ExtensionsKt.show(cardTdOngngTrip2);
        View cardTdTrip2 = _$_findCachedViewById(R.id.cardTdTrip);
        Intrinsics.checkNotNullExpressionValue(cardTdTrip2, "cardTdTrip");
        ExtensionsKt.hide(cardTdTrip2);
        if (onGoingResponse == null) {
            return;
        }
        if (onGoingResponse.getLocation().length() > 0) {
            String[] split4 = TextUtils.split(onGoingResponse.getLocation(), ",");
            if (split4 != null) {
                if (!(split4.length == 0)) {
                    z = false;
                    if (!z) {
                        StringBuilder sb3 = new StringBuilder();
                        String str23 = split4[0];
                        Intrinsics.checkNotNullExpressionValue(str23, "allIdsArray[0]");
                        sb3.append(Double.parseDouble(str23));
                        sb3.append(AbstractJsonLexerKt.COMMA);
                        String str24 = split4[1];
                        Intrinsics.checkNotNullExpressionValue(str24, "allIdsArray[1]");
                        sb3.append(Double.parseDouble(str24));
                        this.startLanLong = sb3.toString();
                        AppCompatTextView appCompatTextView7 = (AppCompatTextView) _$_findCachedViewById(i8).findViewById(R.id.tvOngnTripSummarySource);
                        AppUtil.Companion companion3 = AppUtil.Companion;
                        String str25 = split4[0];
                        Intrinsics.checkNotNullExpressionValue(str25, "allIdsArray[0]");
                        double parseDouble9 = Double.parseDouble(str25);
                        String str26 = split4[1];
                        Intrinsics.checkNotNullExpressionValue(str26, "allIdsArray[1]");
                        i2 = i8;
                        c2 = 0;
                        i3 = 1;
                        appCompatTextView7.setText(companion3.getAddressNameString(this, parseDouble9, Double.parseDouble(str26)));
                        String str27 = split4[0];
                        Intrinsics.checkNotNullExpressionValue(str27, "allIdsArray[0]");
                        double parseDouble10 = Double.parseDouble(str27);
                        String str28 = split4[1];
                        Intrinsics.checkNotNullExpressionValue(str28, "allIdsArray[1]");
                        this.startLatLng = new LatLng(parseDouble10, Double.parseDouble(str28));
                        if (onGoingResponse.getDistanceTravelled() <= 1.0d) {
                            appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(i2).findViewById(R.id.tvOngnTripDistanceCovered);
                            Object[] objArr = new Object[i3];
                            objArr[c2] = ExtensionsKt.toOneDecimal(onGoingResponse.getDistanceTravelled());
                            string2 = getString(uat.psa.mym.mycitroenconnect.R.string.kms, objArr);
                        } else {
                            appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(i2).findViewById(R.id.tvOngnTripDistanceCovered);
                            Object[] objArr2 = new Object[i3];
                            objArr2[c2] = ExtensionsKt.toOneDecimal(onGoingResponse.getDistanceTravelled());
                            string2 = getString(uat.psa.mym.mycitroenconnect.R.string.km, objArr2);
                        }
                        appCompatTextView2.setText(string2);
                        ((AppCompatTextView) _$_findCachedViewById(i2).findViewById(R.id.tvOngnTripDuration)).setText(AppUtil.Companion.convertSeconds((long) (onGoingResponse.getTotalTimeTravelled() * 60)));
                        if (onGoingResponse.getIgnitionStatus() != null) {
                            String ignitionStatus = onGoingResponse.getIgnitionStatus();
                            if (ignitionStatus != null) {
                                str = ignitionStatus.toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                            } else {
                                str = null;
                            }
                            String lowerCase = AppConstants.IGNITION_STATUS_STOP.toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                            if (Intrinsics.areEqual(str, lowerCase)) {
                                CardView cardView2 = (CardView) _$_findCachedViewById(i2).findViewById(R.id.layoutOngoingTripCard);
                                if (cardView2 != null) {
                                    cardView2.setCardBackgroundColor(ContextCompat.getColor(this, uat.psa.mym.mycitroenconnect.R.color.white));
                                }
                                AppCompatTextView tvOngoingRunningChip = (AppCompatTextView) _$_findCachedViewById(R.id.tvOngoingRunningChip);
                                Intrinsics.checkNotNullExpressionValue(tvOngoingRunningChip, "tvOngoingRunningChip");
                                ExtensionsKt.hide(tvOngoingRunningChip);
                                AppCompatTextView tvOngoingIdlingChip = (AppCompatTextView) _$_findCachedViewById(R.id.tvOngoingIdlingChip);
                                Intrinsics.checkNotNullExpressionValue(tvOngoingIdlingChip, "tvOngoingIdlingChip");
                                ExtensionsKt.hide(tvOngoingIdlingChip);
                                return;
                            }
                        }
                        cardView = (CardView) _$_findCachedViewById(i2).findViewById(R.id.layoutOngoingTripCard);
                        if (cardView != null) {
                            cardView.setCardBackgroundColor(ContextCompat.getColor(this, uat.psa.mym.mycitroenconnect.R.color.pastel_yellow));
                        }
                        if (Intrinsics.areEqual(onGoingResponse.getTripCurrentStatus(), AppConstants.ONGOING_TRIP_STATUS_RUNNING)) {
                            AppCompatTextView tvOngoingRunningChip2 = (AppCompatTextView) _$_findCachedViewById(R.id.tvOngoingRunningChip);
                            Intrinsics.checkNotNullExpressionValue(tvOngoingRunningChip2, "tvOngoingRunningChip");
                            ExtensionsKt.hide(tvOngoingRunningChip2);
                            AppCompatTextView tvOngoingIdlingChip2 = (AppCompatTextView) _$_findCachedViewById(R.id.tvOngoingIdlingChip);
                            Intrinsics.checkNotNullExpressionValue(tvOngoingIdlingChip2, "tvOngoingIdlingChip");
                            ExtensionsKt.show(tvOngoingIdlingChip2);
                            return;
                        }
                        AppCompatTextView tvOngoingRunningChip3 = (AppCompatTextView) _$_findCachedViewById(R.id.tvOngoingRunningChip);
                        Intrinsics.checkNotNullExpressionValue(tvOngoingRunningChip3, "tvOngoingRunningChip");
                        ExtensionsKt.show(tvOngoingRunningChip3);
                        AppCompatTextView tvOngoingIdlingChip3 = (AppCompatTextView) _$_findCachedViewById(R.id.tvOngoingIdlingChip);
                        Intrinsics.checkNotNullExpressionValue(tvOngoingIdlingChip3, "tvOngoingIdlingChip");
                        ExtensionsKt.hide(tvOngoingIdlingChip3);
                        return;
                    }
                }
            }
            z = true;
            if (!z) {
            }
        }
        i2 = i8;
        c2 = 0;
        i3 = 1;
        if (onGoingResponse.getDistanceTravelled() <= 1.0d) {
        }
        appCompatTextView2.setText(string2);
        ((AppCompatTextView) _$_findCachedViewById(i2).findViewById(R.id.tvOngnTripDuration)).setText(AppUtil.Companion.convertSeconds((long) (onGoingResponse.getTotalTimeTravelled() * 60)));
        if (onGoingResponse.getIgnitionStatus() != null) {
        }
        cardView = (CardView) _$_findCachedViewById(i2).findViewById(R.id.layoutOngoingTripCard);
        if (cardView != null) {
        }
        if (Intrinsics.areEqual(onGoingResponse.getTripCurrentStatus(), AppConstants.ONGOING_TRIP_STATUS_RUNNING)) {
        }
    }

    private final void initTipsCards() {
        List mutableListOf;
        String string = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.harsh_braking);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.harsh_braking)");
        String string2 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.harsh_breaking_desc);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.harsh_breaking_desc)");
        String string3 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.harsh_acceleration);
        Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.string.harsh_acceleration)");
        String string4 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.harsh_acceleration_desc);
        Intrinsics.checkNotNullExpressionValue(string4, "resources.getString(R.st….harsh_acceleration_desc)");
        String string5 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.over_speed);
        Intrinsics.checkNotNullExpressionValue(string5, "resources.getString(R.string.over_speed)");
        String string6 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.over_speed_desc);
        Intrinsics.checkNotNullExpressionValue(string6, "resources.getString(R.string.over_speed_desc)");
        mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(new TipsCardData(string, string2), new TipsCardData(string3, string4), new TipsCardData(string5, string6));
        this.tipsCardAdapter = new TipsCardAdapter(this, mutableListOf);
        int i2 = R.id.rvTips;
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i2);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, 0, false));
            recyclerView.setHasFixedSize(false);
            recyclerView.setAdapter(this.tipsCardAdapter);
        }
        final Ref.IntRef intRef = new Ref.IntRef();
        final Ref.IntRef intRef2 = new Ref.IntRef();
        PageIndicatorView pageIndicatorView = (PageIndicatorView) _$_findCachedViewById(R.id.pageIndicatorViewTd);
        if (pageIndicatorView != null) {
            pageIndicatorView.setCount(mutableListOf.size());
        }
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(i2);
        if (recyclerView2 != null) {
            recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.trips.TripDetailsActivity$initTipsCards$2
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(@NotNull RecyclerView recyclerView3, int i3, int i4) {
                    Intrinsics.checkNotNullParameter(recyclerView3, "recyclerView");
                    super.onScrolled(recyclerView3, i3, i4);
                    Ref.IntRef.this.element += i3;
                    View childAt = recyclerView3.getChildAt(0);
                    if (childAt != null) {
                        int width = childAt.getWidth();
                        Ref.IntRef intRef3 = Ref.IntRef.this;
                        Ref.IntRef intRef4 = intRef;
                        TripDetailsActivity tripDetailsActivity = this;
                        float f2 = width;
                        int floor = (int) Math.floor((intRef3.element + (f2 / 2.0f)) / f2);
                        int i5 = intRef4.element;
                        if (i5 != floor) {
                            tripDetailsActivity.setCurrentItem(i5 < floor ? i5 + 1 : i5 - 1);
                        }
                        intRef4.element = floor;
                    }
                }
            });
        }
    }

    private final void initTripCards() {
        TripDetailsResponse tripDetailsResponse;
        List mutableListOf;
        TripDetailsResponse tripDetailsResponse2;
        if (Intrinsics.areEqual(this.vehicleType, AppConstants.ICE)) {
            TripCardData[] tripCardDataArr = new TripCardData[3];
            String string = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.label_avg_speed);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.label_avg_speed)");
            TripDetailsResponse tripDetailsResponse3 = this.tripDetailsResponse;
            if (tripDetailsResponse3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
                tripDetailsResponse3 = null;
            }
            String valueOf = String.valueOf((int) tripDetailsResponse3.getAvgVehicleSpeed());
            String string2 = getString(uat.psa.mym.mycitroenconnect.R.string.km_per_hr);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.km_per_hr)");
            tripCardDataArr[0] = new TripCardData(string, valueOf, string2, uat.psa.mym.mycitroenconnect.R.drawable.ic_avg_speed);
            String string3 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.label_top_speed);
            Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.string.label_top_speed)");
            TripDetailsResponse tripDetailsResponse4 = this.tripDetailsResponse;
            if (tripDetailsResponse4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
                tripDetailsResponse4 = null;
            }
            String valueOf2 = String.valueOf((int) tripDetailsResponse4.getMaxVehicleSpeed());
            String string4 = getString(uat.psa.mym.mycitroenconnect.R.string.km_per_hr);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.km_per_hr)");
            tripCardDataArr[1] = new TripCardData(string3, valueOf2, string4, uat.psa.mym.mycitroenconnect.R.drawable.ic_top_speed);
            String string5 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.average_fuel_economy);
            Intrinsics.checkNotNullExpressionValue(string5, "resources.getString(R.string.average_fuel_economy)");
            TripDetailsResponse tripDetailsResponse5 = this.tripDetailsResponse;
            if (tripDetailsResponse5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
                tripDetailsResponse2 = null;
            } else {
                tripDetailsResponse2 = tripDetailsResponse5;
            }
            String oneDecimal = ExtensionsKt.toOneDecimal(tripDetailsResponse2.getMileage());
            String string6 = getString(uat.psa.mym.mycitroenconnect.R.string.kmpl);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.kmpl)");
            tripCardDataArr[2] = new TripCardData(string5, oneDecimal, string6, uat.psa.mym.mycitroenconnect.R.drawable.ic_range);
            mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(tripCardDataArr);
        } else {
            TripCardData[] tripCardDataArr2 = new TripCardData[3];
            String string7 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.label_avg_speed);
            Intrinsics.checkNotNullExpressionValue(string7, "resources.getString(R.string.label_avg_speed)");
            TripDetailsResponse tripDetailsResponse6 = this.tripDetailsResponse;
            if (tripDetailsResponse6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
                tripDetailsResponse6 = null;
            }
            String valueOf3 = String.valueOf((int) tripDetailsResponse6.getAvgVehicleSpeed());
            String string8 = getString(uat.psa.mym.mycitroenconnect.R.string.km_per_hr);
            Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.km_per_hr)");
            tripCardDataArr2[0] = new TripCardData(string7, valueOf3, string8, uat.psa.mym.mycitroenconnect.R.drawable.ic_avg_speed);
            String string9 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.label_top_speed);
            Intrinsics.checkNotNullExpressionValue(string9, "resources.getString(R.string.label_top_speed)");
            TripDetailsResponse tripDetailsResponse7 = this.tripDetailsResponse;
            if (tripDetailsResponse7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
                tripDetailsResponse7 = null;
            }
            String valueOf4 = String.valueOf((int) tripDetailsResponse7.getMaxVehicleSpeed());
            String string10 = getString(uat.psa.mym.mycitroenconnect.R.string.km_per_hr);
            Intrinsics.checkNotNullExpressionValue(string10, "getString(R.string.km_per_hr)");
            tripCardDataArr2[1] = new TripCardData(string9, valueOf4, string10, uat.psa.mym.mycitroenconnect.R.drawable.ic_top_speed);
            String string11 = getResources().getString(uat.psa.mym.mycitroenconnect.R.string.average_power_consumption);
            Intrinsics.checkNotNullExpressionValue(string11, "resources.getString(R.st…verage_power_consumption)");
            TripDetailsResponse tripDetailsResponse8 = this.tripDetailsResponse;
            if (tripDetailsResponse8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
                tripDetailsResponse = null;
            } else {
                tripDetailsResponse = tripDetailsResponse8;
            }
            String twoDecimal = ExtensionsKt.toTwoDecimal(tripDetailsResponse.getMileage() / 1000);
            String string12 = getString(uat.psa.mym.mycitroenconnect.R.string.kwh_km);
            Intrinsics.checkNotNullExpressionValue(string12, "getString(R.string.kwh_km)");
            tripCardDataArr2[2] = new TripCardData(string11, twoDecimal, string12, uat.psa.mym.mycitroenconnect.R.drawable.ic_range);
            mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(tripCardDataArr2);
        }
        this.tripCardAdapter = new MyTripsCardAdapter(this, (ArrayList) mutableListOf);
        this.gridLayoutManager = new GridLayoutManager((Context) this, 2, 1, false);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvTdCardViews);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(this.gridLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(this.tripCardAdapter);
        }
    }

    private final void initViews() {
        AppCompatTextView appCompatTextView;
        int i2;
        int i3 = R.id.tripDetailsHeader;
        ((AppCompatTextView) _$_findCachedViewById(i3).findViewById(R.id.tvDbHeaderTitle)).setText(getResources().getString(uat.psa.mym.mycitroenconnect.R.string.trip_details));
        SharedPref.Companion companion = SharedPref.Companion;
        String vehicleType = companion.getVehicleType(this);
        this.vehicleType = vehicleType;
        if (Intrinsics.areEqual(vehicleType, AppConstants.ICE)) {
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvTdBatteryConsumption);
            i2 = uat.psa.mym.mycitroenconnect.R.string.fuel_consumption;
        } else {
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvTdBatteryConsumption);
            i2 = uat.psa.mym.mycitroenconnect.R.string.label_battery_consumption;
        }
        appCompatTextView.setText(getString(i2));
        TextInputEditText edtTripName = (TextInputEditText) _$_findCachedViewById(R.id.edtTripName);
        Intrinsics.checkNotNullExpressionValue(edtTripName, "edtTripName");
        ExtensionsKt.disableEmoji(edtTripName);
        initTipsCards();
        if (companion.isPrimaryUser(this)) {
            AppCompatImageView ivEditTripName = (AppCompatImageView) _$_findCachedViewById(R.id.ivEditTripName);
            Intrinsics.checkNotNullExpressionValue(ivEditTripName, "ivEditTripName");
            ExtensionsKt.show(ivEditTripName);
        } else {
            AppCompatImageView ivEditTripName2 = (AppCompatImageView) _$_findCachedViewById(R.id.ivEditTripName);
            Intrinsics.checkNotNullExpressionValue(ivEditTripName2, "ivEditTripName");
            ExtensionsKt.hide(ivEditTripName2);
        }
        if (!this.isOngoingTrip) {
            AppCompatTextView tvViewOnMap = (AppCompatTextView) _$_findCachedViewById(R.id.tvViewOnMap);
            Intrinsics.checkNotNullExpressionValue(tvViewOnMap, "tvViewOnMap");
            ExtensionsKt.hide(tvViewOnMap);
            RelativeLayout layoutOnGoingChip = (RelativeLayout) _$_findCachedViewById(R.id.layoutOnGoingChip);
            Intrinsics.checkNotNullExpressionValue(layoutOnGoingChip, "layoutOnGoingChip");
            ExtensionsKt.hide(layoutOnGoingChip);
            AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(i3).findViewById(R.id.ivRefresh);
            Intrinsics.checkNotNullExpressionValue(appCompatImageView, "tripDetailsHeader.ivRefresh");
            ExtensionsKt.hide(appCompatImageView);
            return;
        }
        AppCompatTextView tvViewOnMap2 = (AppCompatTextView) _$_findCachedViewById(R.id.tvViewOnMap);
        Intrinsics.checkNotNullExpressionValue(tvViewOnMap2, "tvViewOnMap");
        ExtensionsKt.show(tvViewOnMap2);
        RelativeLayout layoutOnGoingChip2 = (RelativeLayout) _$_findCachedViewById(R.id.layoutOnGoingChip);
        Intrinsics.checkNotNullExpressionValue(layoutOnGoingChip2, "layoutOnGoingChip");
        ExtensionsKt.show(layoutOnGoingChip2);
        getVehicleLocation();
        AppCompatImageView appCompatImageView2 = (AppCompatImageView) _$_findCachedViewById(i3).findViewById(R.id.ivRefresh);
        Intrinsics.checkNotNullExpressionValue(appCompatImageView2, "tripDetailsHeader.ivRefresh");
        ExtensionsKt.show(appCompatImageView2);
    }

    private final void populateGraphData(final TripDetailsResponse tripDetailsResponse) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        double d2 = 1000;
        arrayList.add(new BarEntry(1.0f, (float) (tripDetailsResponse.getTotalEnergyConsumption() / d2)));
        arrayList2.add(new BarEntry(1.0f, (float) (tripDetailsResponse.getIdleEnergyConsumption() / d2)));
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
        ((RoundedBarChart) _$_findCachedViewById(i2)).getXAxis().setValueFormatter(new IAxisValueFormatter() { // from class: com.psa.mym.mycitroenconnect.controller.activities.trips.d
            @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
            public final String getFormattedValue(float f2, AxisBase axisBase) {
                String m113populateGraphData$lambda10;
                m113populateGraphData$lambda10 = TripDetailsActivity.m113populateGraphData$lambda10(TripDetailsResponse.this, f2, axisBase);
                return m113populateGraphData$lambda10;
            }
        });
        ((RoundedBarChart) _$_findCachedViewById(i2)).getAxisLeft().setAxisMinimum(0.0f);
        ((RoundedBarChart) _$_findCachedViewById(i2)).getXAxis().setAxisMinimum(0.0f);
        ((RoundedBarChart) _$_findCachedViewById(i2)).getDescription().setEnabled(false);
        setLegends();
        ((RoundedBarChart) _$_findCachedViewById(i2)).getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        ((RoundedBarChart) _$_findCachedViewById(i2)).getXAxis().setAxisMinimum(barData.getXMin() - 0.5f);
        ((RoundedBarChart) _$_findCachedViewById(i2)).getXAxis().setAxisMaximum(barData.getXMax() + 0.5f);
        ((RoundedBarChart) _$_findCachedViewById(i2)).getXAxis().setSpaceMax(10.0f);
        ((RoundedBarChart) _$_findCachedViewById(i2)).getAxisRight().setEnabled(false);
        ((RoundedBarChart) _$_findCachedViewById(i2)).setExtraOffsets(0.0f, 0.0f, 0.0f, 10.0f);
        ((RoundedBarChart) _$_findCachedViewById(i2)).setScaleEnabled(false);
        ((RoundedBarChart) _$_findCachedViewById(i2)).invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: populateGraphData$lambda-10  reason: not valid java name */
    public static final String m113populateGraphData$lambda10(TripDetailsResponse event, float f2, AxisBase axisBase) {
        List split$default;
        List split$default2;
        Intrinsics.checkNotNullParameter(event, "$event");
        try {
            if (AppUtil.Companion.isInteger(f2)) {
                split$default = StringsKt__StringsKt.split$default((CharSequence) event.getTripStartDate(), new String[]{HelpFormatter.DEFAULT_OPT_PREFIX}, false, 0, 6, (Object) null);
                StringBuilder sb = new StringBuilder();
                split$default2 = StringsKt__StringsKt.split$default((CharSequence) split$default.get(2), new String[]{" "}, false, 0, 6, (Object) null);
                sb.append((String) split$default2.get(0));
                sb.append(" / ");
                sb.append((String) split$default.get(1));
                return sb.toString();
            }
            return "";
        } catch (Exception unused) {
            return String.valueOf(f2);
        }
    }

    private final void route(LatLng latLng) {
        TripDetailsActivity$route$handler$1 tripDetailsActivity$route$handler$1 = new TripDetailsActivity$route$handler$1(this, Looper.getMainLooper());
        LatLng latLng2 = this.startLatLng;
        new GMapV2DirectionAsyncTask(tripDetailsActivity$route$handler$1, latLng2 != null ? new LatLng(latLng2.latitude, latLng2.longitude) : null, latLng, this.viaLocations, GMapV2Direction.MODE_DRIVING).getDistanceFromGoogle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCurrentItem(int i2) {
        ((PageIndicatorView) _$_findCachedViewById(R.id.pageIndicatorViewTd)).setSelection(Math.abs(i2));
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
        int i2 = R.id.tripDetailsHeader;
        ((AppCompatImageView) _$_findCachedViewById(i2).findViewById(R.id.ivBack)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(i2).findViewById(R.id.ivRefresh)).setOnClickListener(this);
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvViewOnMap)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivTdIdlingTripInfo)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivTdBatteryInfo)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivEditTripName)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivCloseTripNameEdit)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivUpdateTripName)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivTdMap)).setOnClickListener(this);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [android.app.AlertDialog, T] */
    private final void showAlertDialogInfo() {
        Window window;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflate = getLayoutInflater().inflate(uat.psa.mym.mycitroenconnect.R.layout.layout_info_dlg, (ViewGroup) null);
        Intrinsics.checkNotNullExpressionValue(inflate, "layoutInflater.inflate(R…ut.layout_info_dlg, null)");
        builder.setView(inflate);
        ((AppCompatImageView) inflate.findViewById(R.id.ivInfoClose)).setOnClickListener(new View.OnClickListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.trips.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TripDetailsActivity.m114showAlertDialogInfo$lambda7(Ref.ObjectRef.this, view);
            }
        });
        ?? create = builder.create();
        objectRef.element = create;
        AlertDialog alertDialog = (AlertDialog) create;
        if (alertDialog != null && (window = alertDialog.getWindow()) != null) {
            window.setBackgroundDrawableResource(uat.psa.mym.mycitroenconnect.R.drawable.ic_dialog_rounded_background);
        }
        ((AlertDialog) objectRef.element).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showAlertDialogInfo$lambda-7  reason: not valid java name */
    public static final void m114showAlertDialogInfo$lambda7(Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        AlertDialog alertDialog = (AlertDialog) dialog.element;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002c, code lost:
        if (r1 != false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void updateTripName() {
        boolean isBlank;
        int i2 = R.id.edtTripName;
        Editable text = ((TextInputEditText) _$_findCachedViewById(i2)).getText();
        boolean z = false;
        if (!(text == null || text.length() == 0)) {
            Editable text2 = ((TextInputEditText) _$_findCachedViewById(i2)).getText();
            if (text2 != null) {
                isBlank = StringsKt__StringsJVMKt.isBlank(text2);
            }
            z = true;
            if (!z) {
                AppUtil.Companion.showDialog(this);
                new TripService().updateTripName(this, SharedPref.Companion.getVinNumber(this), this.tripID, String.valueOf(((TextInputEditText) _$_findCachedViewById(i2)).getText()));
                return;
            }
        }
        String string = getString(uat.psa.mym.mycitroenconnect.R.string.enter_trip_name);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.enter_trip_name)");
        ExtensionsKt.showToast(this, string);
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

    public final void animateRefreshButton() {
        AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(R.id.tripDetailsHeader).findViewById(R.id.ivRefresh);
        Intrinsics.checkNotNullExpressionValue(appCompatImageView, "tripDetailsHeader.ivRefresh");
        ExtensionsKt.rotateAnimation$default(appCompatImageView, 250L, 0, new TripDetailsActivity$animateRefreshButton$1(this), TripDetailsActivity$animateRefreshButton$2.INSTANCE, TripDetailsActivity$animateRefreshButton$3.INSTANCE, 2, null);
    }

    @Subscribe
    public final void getMessage(@NotNull ErrorResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        displayData();
        if (!Intrinsics.areEqual(event.getApiName(), AppConstants.API_NAME_UPDATE_TRIP_NAME) && event.getStatusCode() == 204) {
            ((LinearLayout) _$_findCachedViewById(R.id.layoutTdDriving)).setVisibility(8);
        } else {
            ExtensionsKt.showToast(this, event.getMsg());
        }
    }

    @Subscribe
    public final void getMessage(@NotNull FailResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        displayData();
        ExtensionsKt.showToast(this, event.getMessage());
        initTripCards();
        initBatteryCards();
        Logger.INSTANCE.e("Get dummy data");
        getOnGoingTripData();
    }

    @Subscribe
    public final void getMessage(@NotNull VehicleLocationResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.destLatLng = new LatLng(event.getCoordinate().getX(), event.getCoordinate().getY());
        StringBuilder sb = new StringBuilder();
        LatLng latLng = this.destLatLng;
        Intrinsics.checkNotNull(latLng);
        sb.append(latLng.latitude);
        sb.append(AbstractJsonLexerKt.COMMA);
        LatLng latLng2 = this.destLatLng;
        Intrinsics.checkNotNull(latLng2);
        sb.append(latLng2.longitude);
        this.endLanLong = sb.toString();
        if (this.isMapDrawn || this.startLatLng == null) {
            return;
        }
        if (this.startLanLong.length() > 0) {
            if (this.endLanLong.length() > 0) {
                StringBuilder sb2 = new StringBuilder();
                LatLng latLng3 = this.destLatLng;
                Intrinsics.checkNotNull(latLng3);
                sb2.append(latLng3.latitude);
                sb2.append(AbstractJsonLexerKt.COMMA);
                LatLng latLng4 = this.destLatLng;
                Intrinsics.checkNotNull(latLng4);
                sb2.append(latLng4.longitude);
                this.endLanLong = sb2.toString();
                LatLng latLng5 = this.destLatLng;
                Intrinsics.checkNotNull(latLng5);
                route(latLng5);
                this.isMapDrawn = true;
            }
        }
    }

    @Subscribe
    public final void getMessage(@NotNull EnergyUsageResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        int i2 = R.id.mBatteryEfficiency;
        if (((RoundedBarChart) _$_findCachedViewById(i2)).getData() != 0) {
            ((RoundedBarChart) _$_findCachedViewById(i2)).invalidate();
            ((RoundedBarChart) _$_findCachedViewById(i2)).clear();
        }
        if (event.getEnergyUsageResponse().size() > 0) {
            Logger logger = Logger.INSTANCE;
            logger.e(" energyUsageResponse : " + event);
        } else {
            Logger.INSTANCE.e("Get dummy data");
        }
        getOnGoingTripData();
    }

    @Subscribe
    public final void getMessage(@NotNull OnGoingResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        displayData();
        if (Intrinsics.areEqual(event.getTripType(), "Active Trip")) {
            OnGoingResponse onGoingResponse = this.onGoingRespObj;
            if (onGoingResponse != null) {
                if (Intrinsics.areEqual(onGoingResponse != null ? onGoingResponse.getTripId() : null, event.getTripId())) {
                    this.onGoingRespObj = event;
                }
            }
            initSourceDestCard(null, event);
            LatLng latLng = this.destLatLng;
            boolean z = false;
            if (latLng != null && latLng != null) {
                Intrinsics.checkNotNull(latLng);
                route(latLng);
                z = true;
            }
            this.isMapDrawn = z;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    @Subscribe
    @SuppressLint({"SetTextI18n"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void getMessage(@NotNull TripDetailsResponse event) {
        boolean isBlank;
        boolean isBlank2;
        Intrinsics.checkNotNullParameter(event, "event");
        this.tripDetailsResponse = event;
        ((TextInputEditText) _$_findCachedViewById(R.id.edtTripName)).setText(event.getTripName());
        AppUtil.Companion companion = AppUtil.Companion;
        companion.getCurrentTimeStringUtc();
        isBlank = StringsKt__StringsJVMKt.isBlank(event.getTripEndDate());
        if (!isBlank) {
            if (!(event.getTripEndDate().length() == 0)) {
                ((AppCompatTextView) _$_findCachedViewById(R.id.tvTdTripDuration)).setText(companion.convertDate(event.getTripStartDate()) + " to " + companion.convertDate(event.getTripEndDate()));
                event.getTripEndDate();
                if (event.getTripStartDate().length() <= 0) {
                    isBlank2 = StringsKt__StringsJVMKt.isBlank(event.getTripStartDate());
                    if ((!isBlank2) && !this.isOngoingTrip) {
                        populateGraphData(event);
                    }
                }
                getOnGoingTripData();
                ((AppCompatTextView) _$_findCachedViewById(R.id.tvTdDbCardValueHarshBraking)).setText(String.valueOf((int) event.getHardBrake()));
                ((AppCompatTextView) _$_findCachedViewById(R.id.tvTdDbCardValueHarshAcc)).setText(String.valueOf((int) event.getAggressiveAcceleration()));
                ((AppCompatTextView) _$_findCachedViewById(R.id.tvTdDbCardValue)).setText(String.valueOf((int) event.getHighSpeed()));
                ((LinearLayout) _$_findCachedViewById(R.id.layoutTdDriving)).setVisibility(0);
                initTripCards();
                initBatteryCards();
                if (this.isOngoingTrip) {
                    initSourceDestCard(event, null);
                    LatLng latLng = this.destLatLng;
                    if (latLng != null) {
                        Intrinsics.checkNotNull(latLng);
                        route(latLng);
                        return;
                    }
                    return;
                }
                return;
            }
        }
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvTdTripDuration)).setText(companion.convertDate(event.getTripStartDate()));
        if (event.getTripStartDate().length() <= 0) {
        }
        getOnGoingTripData();
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvTdDbCardValueHarshBraking)).setText(String.valueOf((int) event.getHardBrake()));
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvTdDbCardValueHarshAcc)).setText(String.valueOf((int) event.getAggressiveAcceleration()));
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvTdDbCardValue)).setText(String.valueOf((int) event.getHighSpeed()));
        ((LinearLayout) _$_findCachedViewById(R.id.layoutTdDriving)).setVisibility(0);
        initTripCards();
        initBatteryCards();
        if (this.isOngoingTrip) {
        }
    }

    @Subscribe
    public final void getResponse(@NotNull PostCommonResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        if (Intrinsics.areEqual(response.getApiName(), AppConstants.API_NAME_UPDATE_TRIP_NAME)) {
            ExtensionsKt.showToast(this, response.getMessage());
            TripDetailsResponse tripDetailsResponse = this.tripDetailsResponse;
            if (tripDetailsResponse == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
                tripDetailsResponse = null;
            }
            tripDetailsResponse.setTripName(String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edtTripName)).getText()));
            disableEditTripName();
            return;
        }
        Logger logger = Logger.INSTANCE;
        logger.e("API Name: " + response.getApiName());
        logger.e("Message: " + response.getMessage());
        logger.e("Status Code: " + response.getStatusCode());
    }

    public final void getTripDetails() {
        displayLoading();
        new TripService().callGetTripDetailsByID(this, SharedPref.Companion.getVinNumber(this), this.tripID);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Intent intent;
        TripDetailsResponse tripDetailsResponse = null;
        if (this.isOngoingTrip) {
            intent = new Intent();
            intent.putExtra(AppConstants.BUNDLE_KEY_IS_ONGOING_TRIP, this.isOngoingTrip);
            intent.putExtra(AppConstants.BUNDLE_KEY_ONGOING_TRIP_OBJ, this.onGoingRespObj);
            if (!((TextInputEditText) _$_findCachedViewById(R.id.edtTripName)).isEnabled()) {
                TripDetailsResponse tripDetailsResponse2 = this.tripDetailsResponse;
                if (tripDetailsResponse2 != null) {
                    if (tripDetailsResponse2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
                    } else {
                        tripDetailsResponse = tripDetailsResponse2;
                    }
                    intent.putExtra("tripName", tripDetailsResponse.getTripName());
                }
                intent.putExtra(AppConstants.ARG_POSITION, this.position);
                setResult(-1, intent);
            }
        } else if (!((TextInputEditText) _$_findCachedViewById(R.id.edtTripName)).isEnabled()) {
            intent = new Intent();
            TripDetailsResponse tripDetailsResponse3 = this.tripDetailsResponse;
            if (tripDetailsResponse3 != null) {
                if (tripDetailsResponse3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tripDetailsResponse");
                } else {
                    tripDetailsResponse = tripDetailsResponse3;
                }
                intent.putExtra("tripName", tripDetailsResponse.getTripName());
            }
            intent.putExtra(AppConstants.ARG_POSITION, this.position);
            setResult(-1, intent);
        }
        finish();
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (SystemClock.elapsedRealtime() - this.lastClickTime < 1500) {
            return;
        }
        this.lastClickTime = SystemClock.elapsedRealtime();
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivTdBatteryInfo)) ? true : Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivTdIdlingTripInfo))) {
            showAlertDialogInfo();
        } else if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivEditTripName))) {
            enableEditTripName();
        } else if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivUpdateTripName))) {
            updateTripName();
        } else if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivCloseTripNameEdit))) {
            disableEditTripName();
        } else {
            int i2 = R.id.tripDetailsHeader;
            if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(i2).findViewById(R.id.ivBack))) {
                onBackPressed();
            } else if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(i2).findViewById(R.id.ivRefresh))) {
                animateRefreshButton();
            } else if (Intrinsics.areEqual(view, (AppCompatTextView) _$_findCachedViewById(R.id.tvViewOnMap))) {
                startActivity(new Intent(this, LocateCarActivity.class));
            } else if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivTdMap))) {
                new ImagePreviewDialog(this, this.mapURL).show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(uat.psa.mym.mycitroenconnect.R.layout.activity_trip_details);
        getIntentData();
        initViews();
        setListeners();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        getTripDetails();
    }
}
