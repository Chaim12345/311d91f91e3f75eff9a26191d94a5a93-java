package com.psa.mym.mycitroenconnect.controller.activities.dashboard;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.BaseActivity;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.adapters.ServiceAvailableAdapter;
import com.psa.mym.mycitroenconnect.model.NearbyStationModel;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ChargingStationDetailsActivity extends BaseActivity {
    @Nullable
    private NearbyStationModel chargingStationDetails;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String vehicleType = "";
    private double destinationLatitude = 23.0153d;
    private double destinationLongitude = 72.5918d;

    private final void getIntentData() {
        if (getIntent() == null || !getIntent().hasExtra(AppConstants.ARG_CHARGING_STATION_DETAILS)) {
            return;
        }
        this.chargingStationDetails = (NearbyStationModel) getIntent().getParcelableExtra(AppConstants.ARG_CHARGING_STATION_DETAILS);
    }

    private final void init() {
        Collection collection;
        this.vehicleType = SharedPref.Companion.getVehicleType(this);
        SwitchCompat switchDashHeader = (SwitchCompat) _$_findCachedViewById(R.id.switchDashHeader);
        Intrinsics.checkNotNullExpressionValue(switchDashHeader, "switchDashHeader");
        ExtensionsKt.hide(switchDashHeader);
        if (Intrinsics.areEqual(this.vehicleType, AppConstants.ICE)) {
            ((AppCompatTextView) _$_findCachedViewById(R.id.layoutChrgngStnDetailHeader).findViewById(R.id.tvDbHeaderTitle)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.lbl_fuel_station));
            String[] stringArray = getResources().getStringArray(uat.psa.mym.mycitroenconnect.R.array.service_list_pv);
            Intrinsics.checkNotNullExpressionValue(stringArray, "resources.getStringArray(R.array.service_list_pv)");
            collection = ArraysKt___ArraysKt.toCollection(stringArray, new ArrayList());
        } else {
            ((AppCompatTextView) _$_findCachedViewById(R.id.layoutChrgngStnDetailHeader).findViewById(R.id.tvDbHeaderTitle)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_charging_station));
            String[] stringArray2 = getResources().getStringArray(uat.psa.mym.mycitroenconnect.R.array.service_list_ev);
            Intrinsics.checkNotNullExpressionValue(stringArray2, "resources.getStringArray(R.array.service_list_ev)");
            collection = ArraysKt___ArraysKt.toCollection(stringArray2, new ArrayList());
        }
        ServiceAvailableAdapter serviceAvailableAdapter = new ServiceAvailableAdapter((ArrayList) collection);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvServicesAvailable);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(serviceAvailableAdapter);
        Intrinsics.checkNotNullExpressionValue(recyclerView, "this");
        ExtensionsKt.show(recyclerView);
        NearbyStationModel nearbyStationModel = this.chargingStationDetails;
        if (nearbyStationModel == null || nearbyStationModel == null) {
            return;
        }
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvChargeLocation)).setText(nearbyStationModel.getStationName());
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvChargeRate)).setText(nearbyStationModel.getStationCharge());
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvChargeUnit)).setText(nearbyStationModel.getStationUnit());
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvChargeSubLocation)).setText(nearbyStationModel.getStationSubLocation());
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvChargeTime)).setText(nearbyStationModel.getStationTime());
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvChargeDistance)).setText(nearbyStationModel.getStationDistance());
    }

    private final void setListener() {
        ((AppCompatImageView) _$_findCachedViewById(R.id.layoutChrgngStnDetailHeader).findViewById(R.id.ivBack)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnNavigate)).setOnClickListener(this);
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity
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

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.layoutChrgngStnDetailHeader).findViewById(R.id.ivBack))) {
            finish();
        } else if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnNavigate))) {
            AppUtil.Companion.navigateToDestination(this, this.destinationLatitude, this.destinationLongitude);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(uat.psa.mym.mycitroenconnect.R.layout.activity_charging_station_detail);
        getIntentData();
        init();
        setListener();
    }
}
