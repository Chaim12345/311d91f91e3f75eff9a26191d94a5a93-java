package com.psa.mym.mycitroenconnect.car_console.utils;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Looper;
import androidx.car.app.CarContext;
import androidx.car.app.hardware.CarHardwareManager;
import androidx.car.app.hardware.common.CarValue;
import androidx.car.app.hardware.common.OnCarDataAvailableListener;
import androidx.car.app.hardware.info.CarHardwareLocation;
import androidx.car.app.hardware.info.CarSensors;
import androidx.car.app.managers.Manager;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoLocationManager;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import java.util.Iterator;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class AutoLocationManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final AutoLocationManager _instance = new AutoLocationManager();
    @Nullable
    private Double curLocationAlt;
    @Nullable
    private Double curLocationLat;
    @Nullable
    private Double curLocationLong;
    @Nullable
    private FusedLocationProviderClient fusedLocationProviderClient;
    private boolean isLocationUpdateStarted;
    @Nullable
    private CarHardwareLocation mCarHardwareLocation;
    private boolean mHasCarHardwareLocationPermission;
    private final String TAG = AutoLocationManager.class.getSimpleName();
    @NotNull
    private final LocationCallback locationCallback = new LocationCallback() { // from class: com.psa.mym.mycitroenconnect.car_console.utils.AutoLocationManager$locationCallback$1
        @Override // com.google.android.gms.location.LocationCallback
        public void onLocationResult(@NotNull LocationResult locationResult) {
            Intrinsics.checkNotNullParameter(locationResult, "locationResult");
            super.onLocationResult(locationResult);
            Iterator<Location> it = locationResult.getLocations().iterator();
            while (it.hasNext()) {
                Location next = it.next();
                AutoLocationManager.this.setLocationUpdateStarted(true);
                if ((next != null ? Double.valueOf(next.getLatitude()) : null) != null) {
                    if ((next != null ? Double.valueOf(next.getLongitude()) : null) != null) {
                        AutoLocationManager.this.setCurLocationLat(Double.valueOf(next.getLatitude()));
                        AutoLocationManager.this.setCurLocationLong(Double.valueOf(next.getLongitude()));
                    }
                }
                next.getAltitude();
                AutoLocationManager.this.setCurLocationAlt(Double.valueOf(next.getAltitude()));
            }
        }
    };
    @NotNull
    private final OnCarDataAvailableListener<CarHardwareLocation> mCarLocationListener = new OnCarDataAvailableListener() { // from class: h.a
        @Override // androidx.car.app.hardware.common.OnCarDataAvailableListener
        public final void onCarDataAvailable(Object obj) {
            AutoLocationManager.m67mCarLocationListener$lambda3(AutoLocationManager.this, (CarHardwareLocation) obj);
        }
    };

    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final AutoLocationManager getInstance() {
            AutoLocationManager autoLocationManager;
            synchronized (this) {
                autoLocationManager = AutoLocationManager._instance;
            }
            return autoLocationManager;
        }
    }

    private final void checkLocationSettings(final CarContext carContext, final FusedLocationProviderClient fusedLocationProviderClient, final LocationRequest locationRequest) {
        LocationSettingsRequest.Builder alwaysShow = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest).setAlwaysShow(true);
        Intrinsics.checkNotNullExpressionValue(alwaysShow, "Builder()\n            .a…     .setAlwaysShow(true)");
        final Task<LocationSettingsResponse> checkLocationSettings = LocationServices.getSettingsClient(carContext).checkLocationSettings(alwaysShow.build());
        Intrinsics.checkNotNullExpressionValue(checkLocationSettings, "getSettingsClient(carCon…Settings(builder.build())");
        checkLocationSettings.addOnCompleteListener(new OnCompleteListener() { // from class: h.b
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                AutoLocationManager.m66checkLocationSettings$lambda0(Task.this, this, fusedLocationProviderClient, locationRequest, carContext, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: checkLocationSettings$lambda-0  reason: not valid java name */
    public static final void m66checkLocationSettings$lambda0(Task locationSettingTask, AutoLocationManager this$0, FusedLocationProviderClient fusedLocationProviderClient, LocationRequest locationRequest, CarContext carContext, Task it) {
        Intrinsics.checkNotNullParameter(locationSettingTask, "$locationSettingTask");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fusedLocationProviderClient, "$fusedLocationProviderClient");
        Intrinsics.checkNotNullParameter(locationRequest, "$locationRequest");
        Intrinsics.checkNotNullParameter(carContext, "$carContext");
        Intrinsics.checkNotNullParameter(it, "it");
        try {
            LocationSettingsResponse locationSettingsResponse = (LocationSettingsResponse) locationSettingTask.getResult(ApiException.class);
            this$0.stopLocationUpdates();
            this$0.startLocationUpdates(fusedLocationProviderClient, locationRequest);
        } catch (ApiException unused) {
            AutoUtils.INSTANCE.showToast(carContext, "Please enable Location to continue!");
        }
    }

    private final void getCarLocationSensor(CarContext carContext) {
        Manager carService = carContext.getCarService(CarHardwareManager.class);
        Intrinsics.checkNotNullExpressionValue(carService, "carContext.getCarService…ger::class.java\n        )");
        CarSensors carSensors = ((CarHardwareManager) carService).getCarSensors();
        Intrinsics.checkNotNullExpressionValue(carSensors, "carHardwareManager.carSensors");
        this.mCarHardwareLocation = null;
        boolean z = true;
        try {
            carSensors.addCarHardwareLocationListener(1, ContextCompat.getMainExecutor(carContext), this.mCarLocationListener);
        } catch (SecurityException unused) {
            z = false;
        }
        this.mHasCarHardwareLocationPermission = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mCarLocationListener$lambda-3  reason: not valid java name */
    public static final void m67mCarLocationListener$lambda3(AutoLocationManager this$0, CarHardwareLocation data) {
        CarValue<Location> location;
        Location value;
        CarValue<Location> location2;
        Location value2;
        CarValue<Location> location3;
        Location value3;
        CarValue<Location> location4;
        Location value4;
        CarValue<Location> location5;
        Location value5;
        CarValue<Location> location6;
        Location value6;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(data, "data");
        synchronized (this$0) {
            this$0.isLocationUpdateStarted = true;
            this$0.mCarHardwareLocation = data;
            Double d2 = null;
            if (((data == null || (location6 = data.getLocation()) == null || (value6 = location6.getValue()) == null) ? null : Double.valueOf(value6.getLatitude())) != null) {
                CarHardwareLocation carHardwareLocation = this$0.mCarHardwareLocation;
                if (((carHardwareLocation == null || (location5 = carHardwareLocation.getLocation()) == null || (value5 = location5.getValue()) == null) ? null : Double.valueOf(value5.getLongitude())) != null) {
                    CarHardwareLocation carHardwareLocation2 = this$0.mCarHardwareLocation;
                    Double valueOf = (carHardwareLocation2 == null || (location4 = carHardwareLocation2.getLocation()) == null || (value4 = location4.getValue()) == null) ? null : Double.valueOf(value4.getLatitude());
                    Intrinsics.checkNotNull(valueOf);
                    this$0.curLocationLat = valueOf;
                    CarHardwareLocation carHardwareLocation3 = this$0.mCarHardwareLocation;
                    Double valueOf2 = (carHardwareLocation3 == null || (location3 = carHardwareLocation3.getLocation()) == null || (value3 = location3.getValue()) == null) ? null : Double.valueOf(value3.getLongitude());
                    Intrinsics.checkNotNull(valueOf2);
                    this$0.curLocationLong = valueOf2;
                }
            }
            CarHardwareLocation carHardwareLocation4 = this$0.mCarHardwareLocation;
            if (((carHardwareLocation4 == null || (location2 = carHardwareLocation4.getLocation()) == null || (value2 = location2.getValue()) == null) ? null : Double.valueOf(value2.getAltitude())) != null) {
                CarHardwareLocation carHardwareLocation5 = this$0.mCarHardwareLocation;
                if (carHardwareLocation5 != null && (location = carHardwareLocation5.getLocation()) != null && (value = location.getValue()) != null) {
                    d2 = Double.valueOf(value.getAltitude());
                }
                Intrinsics.checkNotNull(d2);
                this$0.curLocationAlt = d2;
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void resetLocationValues() {
        this.curLocationLat = null;
        this.curLocationLong = null;
    }

    @SuppressLint({"MissingPermission"})
    private final void setUpLocationListener(CarContext carContext) {
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(carContext);
        LocationRequest create = LocationRequest.create();
        create.setInterval(AutoConstants.UPDATE_INTERVAL_IN_MILLISECONDS);
        create.setFastestInterval(AutoConstants.FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        create.setPriority(100);
        Intrinsics.checkNotNullExpressionValue(create, "create()\n            .ap…GH_ACCURACY\n            }");
        FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationProviderClient;
        Intrinsics.checkNotNull(fusedLocationProviderClient);
        checkLocationSettings(carContext, fusedLocationProviderClient, create);
    }

    @SuppressLint({"MissingPermission"})
    private final void startLocationUpdates(FusedLocationProviderClient fusedLocationProviderClient, LocationRequest locationRequest) {
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, this.locationCallback, Looper.myLooper());
    }

    private final void stopLocationUpdates() {
        FusedLocationProviderClient fusedLocationProviderClient;
        LocationCallback locationCallback = this.locationCallback;
        if (locationCallback == null || (fusedLocationProviderClient = this.fusedLocationProviderClient) == null) {
            return;
        }
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    public final void checkForLocation(@NotNull CarContext carContext) {
        Intrinsics.checkNotNullParameter(carContext, "carContext");
        if (AppUtil.Companion.isLocationEnabled(carContext)) {
            if (Intrinsics.areEqual(getCurrentLatLong(), new Pair(null, null))) {
                getLocationUpdates(carContext);
                return;
            }
            return;
        }
        resetLocationValues();
        AutoUtils autoUtils = AutoUtils.INSTANCE;
        String string = carContext.getString(R.string.lbl_auto_required_location_toast);
        Intrinsics.checkNotNullExpressionValue(string, "carContext.getString(R.s…_required_location_toast)");
        autoUtils.showToast(carContext, string);
    }

    @Nullable
    public final Double getCurLocationAlt() {
        return this.curLocationAlt;
    }

    @Nullable
    public final Double getCurLocationLat() {
        return this.curLocationLat;
    }

    @Nullable
    public final Double getCurLocationLong() {
        return this.curLocationLong;
    }

    @NotNull
    public final Pair<LatLng, Double> getCurrentLatLong() {
        if (this.curLocationLat == null || this.curLocationLong == null) {
            return new Pair<>(null, null);
        }
        Double d2 = this.curLocationLat;
        Intrinsics.checkNotNull(d2);
        double doubleValue = d2.doubleValue();
        Double d3 = this.curLocationLong;
        Intrinsics.checkNotNull(d3);
        return new Pair<>(new LatLng(doubleValue, d3.doubleValue()), this.curLocationAlt);
    }

    public final void getLocationUpdates(@NotNull CarContext carContext) {
        Intrinsics.checkNotNullParameter(carContext, "carContext");
        setUpLocationListener(carContext);
    }

    @Nullable
    public final CarHardwareLocation getMCarHardwareLocation() {
        return this.mCarHardwareLocation;
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final boolean isLocationUpdateStarted() {
        return this.isLocationUpdateStarted;
    }

    public final void setCurLocationAlt(@Nullable Double d2) {
        this.curLocationAlt = d2;
    }

    public final void setCurLocationLat(@Nullable Double d2) {
        this.curLocationLat = d2;
    }

    public final void setCurLocationLong(@Nullable Double d2) {
        this.curLocationLong = d2;
    }

    public final void setLocationUpdateStarted(boolean z) {
        this.isLocationUpdateStarted = z;
    }

    public final void setMCarHardwareLocation(@Nullable CarHardwareLocation carHardwareLocation) {
        this.mCarHardwareLocation = carHardwareLocation;
    }
}
