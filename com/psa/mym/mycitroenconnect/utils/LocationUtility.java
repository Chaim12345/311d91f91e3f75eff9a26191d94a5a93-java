package com.psa.mym.mycitroenconnect.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Looper;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public class LocationUtility implements LifecycleObserver {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 150000;
    public static final int REQUEST_CHECK_SETTINGS = 1;
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 300000;
    @NotNull
    private final Activity activity;
    @NotNull
    private MutableLiveData<Location> currentLocation;
    @Nullable
    private FusedLocationProviderClient fusedLocationProviderClient;
    @NotNull
    private final LocationCallback locationCallback;
    @Nullable
    private LocationRequest mLocationRequest;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public LocationUtility(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        this.currentLocation = new MutableLiveData<>();
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
        this.locationCallback = new LocationCallback() { // from class: com.psa.mym.mycitroenconnect.utils.LocationUtility$locationCallback$1
            @Override // com.google.android.gms.location.LocationCallback
            public void onLocationResult(@NotNull LocationResult locationResult) {
                Intrinsics.checkNotNullParameter(locationResult, "locationResult");
                super.onLocationResult(locationResult);
                LocationUtility.this.getCurrentLocation().setValue(locationResult.getLocations().get(0));
            }
        };
    }

    private final void checkLocationSettings() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        LocationRequest locationRequest = this.mLocationRequest;
        Intrinsics.checkNotNull(locationRequest);
        LocationSettingsRequest.Builder alwaysShow = builder.addLocationRequest(locationRequest).setAlwaysShow(true);
        Intrinsics.checkNotNullExpressionValue(alwaysShow, "Builder()\n            .a…     .setAlwaysShow(true)");
        Task<LocationSettingsResponse> checkLocationSettings = LocationServices.getSettingsClient(this.activity).checkLocationSettings(alwaysShow.build());
        Intrinsics.checkNotNullExpressionValue(checkLocationSettings, "getSettingsClient(activi…Settings(builder.build())");
        checkLocationSettings.addOnCompleteListener(new OnCompleteListener() { // from class: com.psa.mym.mycitroenconnect.utils.g
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                LocationUtility.m166checkLocationSettings$lambda1(LocationUtility.this, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: checkLocationSettings$lambda-1  reason: not valid java name */
    public static final void m166checkLocationSettings$lambda1(LocationUtility this$0, Task it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        try {
            this$0.startLocationUpdates();
        } catch (ApiException e2) {
            this$0.enableGPSDialog(e2);
        }
    }

    private final void enableGPSDialog(ApiException apiException) {
        if (apiException.getStatusCode() != 6) {
            return;
        }
        try {
            ((ResolvableApiException) apiException).startResolutionForResult(this.activity, 1);
        } catch (Exception e2) {
            if (e2 instanceof IntentSender.SendIntentException) {
                return;
            }
            boolean z = e2 instanceof ClassCastException;
        }
    }

    private final void initLocationRequest() {
        LocationRequest create = LocationRequest.create();
        create.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        create.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        create.setPriority(100);
        this.mLocationRequest = create;
    }

    private final void stopLocationUpdates() {
        FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationProviderClient;
        if (fusedLocationProviderClient != null) {
            fusedLocationProviderClient.removeLocationUpdates(this.locationCallback);
        }
    }

    @NotNull
    public final MutableLiveData<Location> getCurrentLocation() {
        return this.currentLocation;
    }

    public final void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        Logger logger = Logger.INSTANCE;
        logger.e("Data: " + intent);
        if (i2 == 1 && i3 == -1) {
            startLocationUpdates();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public final void onPause() {
    }

    public final void onRequestPermissionsResult(int i2, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        Logger logger = Logger.INSTANCE;
        logger.e("Permissions: " + permissions);
        if (i2 == 1) {
            if ((!(grantResults.length == 0)) && grantResults[0] == 0) {
                startLocationUpdates();
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public final void onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public final void onStart() {
        startLocationUpdates();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public final void onStop() {
        stopLocationUpdates();
    }

    public final void setCurrentLocation(@NotNull MutableLiveData<Location> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.currentLocation = mutableLiveData;
    }

    public final void startLocationClient() {
        initLocationRequest();
        checkLocationSettings();
    }

    @SuppressLint({"MissingPermission"})
    public final void startLocationUpdates() {
        Dexter.withActivity(this.activity).withPermissions("android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION").withListener(new MultiplePermissionsListener() { // from class: com.psa.mym.mycitroenconnect.utils.LocationUtility$startLocationUpdates$1
            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
            public void onPermissionRationaleShouldBeShown(@Nullable List<PermissionRequest> list, @NotNull PermissionToken token) {
                Intrinsics.checkNotNullParameter(token, "token");
                token.continuePermissionRequest();
            }

            /* JADX WARN: Code restructure failed: missing block: B:4:0x000b, code lost:
                r4 = r3.f10747a.fusedLocationProviderClient;
             */
            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onPermissionsChecked(@NotNull MultiplePermissionsReport report) {
                FusedLocationProviderClient fusedLocationProviderClient;
                LocationRequest locationRequest;
                LocationCallback locationCallback;
                Intrinsics.checkNotNullParameter(report, "report");
                if (!report.areAllPermissionsGranted() || fusedLocationProviderClient == null) {
                    return;
                }
                locationRequest = LocationUtility.this.mLocationRequest;
                Intrinsics.checkNotNull(locationRequest);
                locationCallback = LocationUtility.this.locationCallback;
                fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
            }
        }).check();
    }
}
