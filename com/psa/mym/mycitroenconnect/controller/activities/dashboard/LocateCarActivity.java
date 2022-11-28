package com.psa.mym.mycitroenconnect.controller.activities.dashboard;

import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.app.ShareCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;
import com.google.maps.android.SphericalUtil;
import com.psa.mym.mycitroenconnect.BusBaseActivity;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoConstants;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.dashboard.LocateCarActivity;
import com.psa.mym.mycitroenconnect.model.VehicleStatus;
import com.psa.mym.mycitroenconnect.model.streaming.MessageTypeResponse;
import com.psa.mym.mycitroenconnect.services.SnapShotService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.GpsStatusDetector;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.MapExtensionKt;
import com.psa.mym.mycitroenconnect.utils.google_map_direction.GMapV2Direction;
import com.psa.mym.mycitroenconnect.utils.google_map_direction.GMapV2DirectionAsyncTask;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayout;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import kotlin.Deprecated;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
import org.apache.http.protocol.HTTP;
import org.bouncycastle.tls.CipherSuite;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Document;
import uat.psa.mym.mycitroenconnect.R;
@SuppressLint({"MissingPermission"})
/* loaded from: classes2.dex */
public final class LocateCarActivity extends BusBaseActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMyLocationButtonClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, GpsStatusDetector.GpsStatusDetectorCallBack {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final int REQUEST_LOCATION = 199;
    private static final float ZOOM_LEVEL = 10.0f;
    @NotNull
    private ActivityResultLauncher<String[]> activityResultLauncher;
    private double carLatitude;
    private double carLongitude;
    @Nullable
    private Marker carMarker;
    private double coarseAngle;
    @NotNull
    private final LocateCarActivity$eventSourceListener$1 eventSourceListener;
    @Nullable
    private GoogleMap googleMap;
    @Nullable
    private ArrayList<MessageTypeResponse> list;
    @Nullable
    private GoogleApiClient mGoogleApiClient;
    @Nullable
    private GpsStatusDetector mGpsStatusDetector;
    @Nullable
    private LocationRequest mLocationRequest;
    private SupportMapFragment mapFragment;
    private final int maxRetryCount;
    @Nullable
    private Polyline polyline;
    private int retryCounter;
    private final long retryDelayMillis;
    private boolean shouldRetry;
    private double sourceLatitude;
    private double sourceLongitude;
    @Nullable
    private Marker sourceMarker;
    @Nullable
    private EventSource sseEventSource;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String carAddress = "";
    @NotNull
    private String sourceAddress = "";
    @Nullable
    private String ignitionStatus = AppConstants.IGNITION_STATUS_STOP;

    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    private interface LatLngInterpolatorNew {

        /* loaded from: classes2.dex */
        public static final class LinearFixed implements LatLngInterpolatorNew {
            @Override // com.psa.mym.mycitroenconnect.controller.activities.dashboard.LocateCarActivity.LatLngInterpolatorNew
            @NotNull
            public LatLng interpolate(float f2, @NotNull LatLng a2, @NotNull LatLng b2) {
                Intrinsics.checkNotNullParameter(a2, "a");
                Intrinsics.checkNotNullParameter(b2, "b");
                double d2 = b2.latitude;
                double d3 = a2.latitude;
                double d4 = f2;
                double d5 = ((d2 - d3) * d4) + d3;
                double d6 = b2.longitude - a2.longitude;
                if (Math.abs(d6) > 180.0d) {
                    d6 -= Math.signum(d6) * 360;
                }
                return new LatLng(d5, (d6 * d4) + a2.longitude);
            }
        }

        @NotNull
        LatLng interpolate(float f2, @NotNull LatLng latLng, @NotNull LatLng latLng2);
    }

    public LocateCarActivity() {
        ActivityResultLauncher<String[]> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() { // from class: com.psa.mym.mycitroenconnect.controller.activities.dashboard.c
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                LocateCarActivity.m86_init_$lambda0(LocateCarActivity.this, (Map) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…)\n            }\n        }");
        this.activityResultLauncher = registerForActivityResult;
        this.shouldRetry = true;
        this.maxRetryCount = 5;
        this.retryDelayMillis = AutoConstants.FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS;
        this.eventSourceListener = new LocateCarActivity$eventSourceListener$1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-0  reason: not valid java name */
    public static final void m86_init_$lambda0(LocateCarActivity this$0, Map map) {
        boolean z;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        loop0: while (true) {
            z = true;
            for (Boolean b2 : map.values()) {
                if (z) {
                    Intrinsics.checkNotNullExpressionValue(b2, "b");
                    if (b2.booleanValue()) {
                        break;
                    }
                }
                z = false;
            }
        }
        if (!z) {
            String string = this$0.getResources().getString(R.string.location_permission_denied);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…cation_permission_denied)");
            ExtensionsKt.showToast(this$0, string);
            AppUtil.Companion.dismissDialog();
            return;
        }
        AppUtil.Companion.dismissDialog();
        this$0.buildGoogleApiClient();
        GoogleMap googleMap = this$0.googleMap;
        Intrinsics.checkNotNull(googleMap);
        googleMap.setMyLocationEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void animateBottomButtonsView() {
        LinearLayoutCompat llBtns = (LinearLayoutCompat) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.llBtns);
        Intrinsics.checkNotNullExpressionValue(llBtns, "llBtns");
        ExtensionsKt.bottomToTopAnimation(llBtns, this, LocateCarActivity$animateBottomButtonsView$1.INSTANCE, new LocateCarActivity$animateBottomButtonsView$2(this), LocateCarActivity$animateBottomButtonsView$3.INSTANCE);
    }

    private final void animateMapView() {
        ConstraintLayout animationView = (ConstraintLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.animationView);
        Intrinsics.checkNotNullExpressionValue(animationView, "animationView");
        ExtensionsKt.zoomOutAnimation(animationView, this, LocateCarActivity$animateMapView$1.INSTANCE, new LocateCarActivity$animateMapView$2(this), LocateCarActivity$animateMapView$3.INSTANCE);
    }

    private final void animateMarkerNew(final Location location, final Marker marker) {
        if (marker != null) {
            final LatLng position = marker.getPosition();
            Intrinsics.checkNotNullExpressionValue(position, "marker.position");
            final LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            final LatLngInterpolatorNew.LinearFixed linearFixed = new LatLngInterpolatorNew.LinearFixed();
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat.setDuration(SkeletonLayout.DEFAULT_SHIMMER_DURATION_IN_MILLIS);
            ofFloat.setInterpolator(new LinearInterpolator());
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.dashboard.a
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    LocateCarActivity.m87animateMarkerNew$lambda9(LocateCarActivity.LatLngInterpolatorNew.LinearFixed.this, position, latLng, marker, this, location, valueAnimator);
                }
            });
            ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.psa.mym.mycitroenconnect.controller.activities.dashboard.LocateCarActivity$animateMarkerNew$2
            });
            ofFloat.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: animateMarkerNew$lambda-9  reason: not valid java name */
    public static final void m87animateMarkerNew$lambda9(LatLngInterpolatorNew.LinearFixed latLngInterpolator, LatLng startPosition, LatLng endPosition, Marker marker, LocateCarActivity this$0, Location destination, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(latLngInterpolator, "$latLngInterpolator");
        Intrinsics.checkNotNullParameter(startPosition, "$startPosition");
        Intrinsics.checkNotNullParameter(endPosition, "$endPosition");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(destination, "$destination");
        try {
            marker.setPosition(latLngInterpolator.interpolate(valueAnimator.getAnimatedFraction(), startPosition, endPosition));
            marker.setRotation((float) this$0.coarseAngle);
            marker.setFlat(true);
            this$0.moveCamera(new LatLng(destination.getLatitude(), destination.getLongitude()));
        } catch (Exception e2) {
            Logger logger = Logger.INSTANCE;
            e2.printStackTrace();
            logger.e(String.valueOf(Unit.INSTANCE));
        }
    }

    private final synchronized void buildGoogleApiClient() {
        GoogleApiClient build = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        this.mGoogleApiClient = build;
        Intrinsics.checkNotNull(build);
        build.connect();
    }

    private final void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.ACCESS_FINE_LOCATION")) {
                new AlertDialog.Builder(this).setTitle(getString(R.string.location_permission_required)).setMessage(getString(R.string.location_permission_required_msg)).setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.dashboard.b
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i2) {
                        LocateCarActivity.m88checkLocationPermission$lambda3(LocateCarActivity.this, dialogInterface, i2);
                    }
                }).create().show();
            } else {
                this.activityResultLauncher.launch(new String[]{"android.permission.ACCESS_FINE_LOCATION"});
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: checkLocationPermission$lambda-3  reason: not valid java name */
    public static final void m88checkLocationPermission$lambda3(LocateCarActivity this$0, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activityResultLauncher.launch(new String[]{"android.permission.ACCESS_FINE_LOCATION"});
        dialogInterface.dismiss();
    }

    private final void closeConnection() {
        Logger.INSTANCE.d("closeConnection");
        EventSource eventSource = this.sseEventSource;
        if (eventSource != null) {
            eventSource.cancel();
        }
    }

    static /* synthetic */ void g(LocateCarActivity locateCarActivity, LatLng latLng, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            latLng = new LatLng(locateCarActivity.carLatitude, locateCarActivity.carLongitude);
        }
        locateCarActivity.setCarMarker(latLng);
    }

    private final float getBearing(LatLng latLng, LatLng latLng2) {
        double abs = Math.abs(latLng.latitude - latLng2.latitude);
        double abs2 = Math.abs(latLng.longitude - latLng2.longitude);
        double d2 = latLng.latitude;
        double d3 = latLng2.latitude;
        if (d2 >= d3 || latLng.longitude >= latLng2.longitude) {
            if (d2 >= d3 && latLng.longitude < latLng2.longitude) {
                double d4 = 90;
                return (float) ((d4 - Math.toDegrees(Math.atan(abs2 / abs))) + d4);
            } else if (d2 < d3 || latLng.longitude < latLng2.longitude) {
                if (d2 >= d3 || latLng.longitude < latLng2.longitude) {
                    return -1.0f;
                }
                return (float) ((90 - Math.toDegrees(Math.atan(abs2 / abs))) + 270);
            } else {
                return (float) (Math.toDegrees(Math.atan(abs2 / abs)) + ((double) CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256));
            }
        }
        return (float) Math.toDegrees(Math.atan(abs2 / abs));
    }

    private final void getSnapShotVehicleStatus() {
        new SnapShotService().getVehicleStatus(this, SharedPref.Companion.getVinNumber(this));
    }

    private final OkHttpClient getUnsafeOkHttpClient() {
        try {
            TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.psa.mym.mycitroenconnect.controller.activities.dashboard.LocateCarActivity$getUnsafeOkHttpClient$trustAllCerts$1
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(@Nullable X509Certificate[] x509CertificateArr, @Nullable String str) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(@Nullable X509Certificate[] x509CertificateArr, @Nullable String str) {
                }

                @Override // javax.net.ssl.X509TrustManager
                @NotNull
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sSLContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            Intrinsics.checkNotNullExpressionValue(sslSocketFactory, "sslSocketFactory");
            OkHttpClient.Builder hostnameVerifier = builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustManagerArr[0]).hostnameVerifier(f.f10395a);
            TimeUnit timeUnit = TimeUnit.SECONDS;
            return hostnameVerifier.connectTimeout(60L, timeUnit).writeTimeout(60L, timeUnit).readTimeout(60L, timeUnit).build();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getUnsafeOkHttpClient$lambda-6  reason: not valid java name */
    public static final boolean m89getUnsafeOkHttpClient$lambda6(String str, SSLSession sSLSession) {
        return true;
    }

    private final void init() {
        ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvDbHeaderTitle)).setText(getString(R.string.label_locate_car));
        AppCompatTextView tvDbSubTitle = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvDbSubTitle);
        Intrinsics.checkNotNullExpressionValue(tvDbSubTitle, "tvDbSubTitle");
        ExtensionsKt.hide(tvDbSubTitle);
        String ignitionState = SharedPref.Companion.getIgnitionState(this);
        this.ignitionStatus = ignitionState;
        Intrinsics.checkNotNull(ignitionState);
        setIgnitionStatus(ignitionState);
        this.mGpsStatusDetector = new GpsStatusDetector(this);
        this.list = new ArrayList<>();
        ((MaterialCardView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.cvRouteDetails)).setVisibility(0);
        SupportMapFragment newInstance = SupportMapFragment.newInstance();
        Intrinsics.checkNotNullExpressionValue(newInstance, "newInstance()");
        this.mapFragment = newInstance;
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "supportFragmentManager.beginTransaction()");
        SupportMapFragment supportMapFragment = this.mapFragment;
        SupportMapFragment supportMapFragment2 = null;
        if (supportMapFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapFragment");
            supportMapFragment = null;
        }
        beginTransaction.add(R.id.mapContainer, supportMapFragment);
        beginTransaction.commit();
        SupportMapFragment supportMapFragment3 = this.mapFragment;
        if (supportMapFragment3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapFragment");
        } else {
            supportMapFragment2 = supportMapFragment3;
        }
        supportMapFragment2.getMapAsync(this);
        animateMapView();
    }

    private final void moveCamera(LatLng latLng) {
        GoogleMap googleMap = this.googleMap;
        Intrinsics.checkNotNull(googleMap);
        CameraPosition cameraPosition = googleMap.getCameraPosition();
        Intrinsics.checkNotNullExpressionValue(cameraPosition, "googleMap!!.cameraPosition");
        Logger.INSTANCE.e(String.valueOf(cameraPosition));
        CameraPosition build = new CameraPosition.Builder().target(latLng).bearing(cameraPosition.bearing).zoom(cameraPosition.zoom).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .t…oom)\n            .build()");
        GoogleMap googleMap2 = this.googleMap;
        Intrinsics.checkNotNull(googleMap2);
        googleMap2.moveCamera(CameraUpdateFactory.newCameraPosition(build));
    }

    @SuppressLint({"HandlerLeak"})
    private final void route(final LatLng latLng, final int i2) {
        final Looper mainLooper = Looper.getMainLooper();
        new GMapV2DirectionAsyncTask(new Handler(mainLooper) { // from class: com.psa.mym.mycitroenconnect.controller.activities.dashboard.LocateCarActivity$route$handler$1
            @Override // android.os.Handler
            public void handleMessage(@NotNull Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                try {
                    LocateCarActivity.this.updateRoute(msg, latLng, i2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }, new LatLng(this.sourceLatitude, this.sourceLongitude), latLng, new ArrayList(), GMapV2Direction.MODE_DRIVING).getDistanceFromGoogle();
    }

    private final void setCarAddress() {
        this.carAddress = AppUtil.Companion.getAddressNameShortString(this, this.carLatitude, this.carLongitude);
        ((MaterialTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvDestination)).setText(this.carAddress);
    }

    private final void setCarMarker(LatLng latLng) {
        GoogleMap googleMap = this.googleMap;
        if (googleMap != null) {
            Intrinsics.checkNotNull(googleMap);
            this.carMarker = googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_car_marker)).rotation((float) this.coarseAngle));
        }
    }

    private final void setIgnitionOnOff(String str, @DrawableRes int i2, @DrawableRes int i3) {
        AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvIgnitionStatus);
        if (appCompatTextView != null) {
            appCompatTextView.setText(str);
            appCompatTextView.setBackground(AppCompatResources.getDrawable(this, i2));
            appCompatTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(AppCompatResources.getDrawable(this, i3), (Drawable) null, (Drawable) null, (Drawable) null);
        }
    }

    private final void setIgnitionStatus(String str) {
        String string;
        int i2;
        int i3;
        Locale locale = Locale.ROOT;
        String lowerCase = str.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        String lowerCase2 = AppConstants.IGNITION_STATUS_CONTACT.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        if (Intrinsics.areEqual(lowerCase, lowerCase2)) {
            string = getString(R.string.on);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.on)");
            i2 = R.drawable.ic_ignition_on_background;
            i3 = R.drawable.ic_ignition_on;
        } else {
            string = getString(R.string.off);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.off)");
            i2 = R.drawable.ic_ignition_off_background;
            i3 = R.drawable.ic_ignition_off;
        }
        setIgnitionOnOff(string, i2, i3);
    }

    private final void setLastUpdateOn(long j2) {
        ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvLastUpdated)).setText(getString(R.string.last_updated_on, new Object[]{ExtensionsKt.getLastUpdatedDate(new Date(j2))}));
    }

    private final void setListeners() {
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivBack)).setOnClickListener(this);
        ((MaterialButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnShare)).setOnClickListener(this);
        ((MaterialButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnNavigateToCar)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.zoomMyCar)).setOnClickListener(this);
    }

    private final void setSourceAddress() {
        this.sourceAddress = AppUtil.Companion.getAddressNameShortString(this, this.sourceLatitude, this.sourceLongitude);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showMap(final String str) {
        runOnUiThread(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.activities.dashboard.e
            @Override // java.lang.Runnable
            public final void run() {
                LocateCarActivity.m90showMap$lambda7(str, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showMap$lambda-7  reason: not valid java name */
    public static final void m90showMap$lambda7(String message, LocateCarActivity this$0) {
        boolean z;
        String str;
        Intrinsics.checkNotNullParameter(message, "$message");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MessageTypeResponse messageTypeResponse = (MessageTypeResponse) new Gson().fromJson(message, (Class<Object>) MessageTypeResponse.class);
        this$0.setLastUpdateOn(messageTypeResponse.getSignalTimeStamp());
        if (((int) messageTypeResponse.getGpsLat()) == 180 || ((int) messageTypeResponse.getGpstLong()) == 180) {
            return;
        }
        ArrayList<MessageTypeResponse> arrayList = this$0.list;
        Intrinsics.checkNotNull(arrayList);
        Iterator<MessageTypeResponse> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            MessageTypeResponse next = it.next();
            if (Double.valueOf(next.getGpsLat()).equals(Double.valueOf(messageTypeResponse.getGpsLat())) && Double.valueOf(next.getGpstLong()).equals(Double.valueOf(messageTypeResponse.getGpstLong()))) {
                z = true;
                break;
            }
        }
        GoogleMap googleMap = this$0.googleMap;
        Intrinsics.checkNotNull(googleMap);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        GoogleMap googleMap2 = this$0.googleMap;
        Intrinsics.checkNotNull(googleMap2);
        googleMap2.getUiSettings().setScrollGesturesEnabled(true);
        ArrayList<MessageTypeResponse> arrayList2 = this$0.list;
        Intrinsics.checkNotNull(arrayList2);
        arrayList2.add(new MessageTypeResponse(null, null, null, null, null, null, messageTypeResponse.getGpsLat(), messageTypeResponse.getGpstLong(), null, null, null, null, 0L, 0.0d, null, 0.0d, 0, 0, 0.0d, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, -193, FrameMetricsAggregator.EVERY_DURATION, null));
        Location location = new Location("gps");
        location.setLatitude(messageTypeResponse.getGpsLat());
        location.setLongitude(messageTypeResponse.getGpstLong());
        this$0.carLatitude = messageTypeResponse.getGpsLat();
        this$0.carLongitude = messageTypeResponse.getGpstLong();
        this$0.coarseAngle = messageTypeResponse.getCoarseAngle();
        if (messageTypeResponse.getIgnitionStatus() != null) {
            str = messageTypeResponse.getIgnitionStatus();
            Intrinsics.checkNotNull(str);
        } else {
            str = AppConstants.IGNITION_STATUS_STOP;
        }
        this$0.ignitionStatus = str;
        SharedPref.Companion companion = SharedPref.Companion;
        Intrinsics.checkNotNull(str);
        companion.setIgnitionState(this$0, str);
        String str2 = this$0.ignitionStatus;
        Intrinsics.checkNotNull(str2);
        this$0.setIgnitionStatus(str2);
        String str3 = this$0.ignitionStatus;
        if (str3 != null) {
            Intrinsics.checkNotNull(str3);
            Locale locale = Locale.ROOT;
            String lowerCase = str3.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            String lowerCase2 = AppConstants.IGNITION_STATUS_CONTACT.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            if (Intrinsics.areEqual(lowerCase, lowerCase2)) {
                GoogleMap googleMap3 = this$0.googleMap;
                if (googleMap3 != null) {
                    if (googleMap3 != null) {
                        googleMap3.setMaxZoomPreference(15.0f);
                    }
                    int i2 = com.psa.mym.mycitroenconnect.R.id.cvRouteDetails;
                    if (((MaterialCardView) this$0._$_findCachedViewById(i2)).getVisibility() == 0) {
                        ((MaterialCardView) this$0._$_findCachedViewById(i2)).setVisibility(8);
                        Polyline polyline = this$0.polyline;
                        if (polyline != null) {
                            Intrinsics.checkNotNull(polyline);
                            polyline.remove();
                        }
                        Marker marker = this$0.sourceMarker;
                        if (marker != null) {
                            Intrinsics.checkNotNull(marker);
                            marker.remove();
                        }
                    }
                    if (this$0.carMarker == null) {
                        this$0.setCarMarker(new LatLng((-1) * messageTypeResponse.getGpsLat(), messageTypeResponse.getGpstLong()));
                    }
                    Logger.INSTANCE.e("Is Found?: " + z);
                    if (z) {
                        return;
                    }
                    this$0.animateMarkerNew(location, this$0.carMarker);
                    return;
                }
                return;
            }
        }
        GoogleMap googleMap4 = this$0.googleMap;
        if (googleMap4 != null) {
            googleMap4.resetMinMaxZoomPreference();
        }
        int i3 = com.psa.mym.mycitroenconnect.R.id.cvRouteDetails;
        if (((MaterialCardView) this$0._$_findCachedViewById(i3)).getVisibility() == 8) {
            Marker marker2 = this$0.carMarker;
            if (marker2 != null) {
                Intrinsics.checkNotNull(marker2);
                marker2.remove();
            }
            Polyline polyline2 = this$0.polyline;
            if (polyline2 != null) {
                Intrinsics.checkNotNull(polyline2);
                polyline2.remove();
            }
            Marker marker3 = this$0.sourceMarker;
            if (marker3 != null) {
                Intrinsics.checkNotNull(marker3);
                marker3.remove();
            }
            this$0.carMarker = null;
            ((MaterialCardView) this$0._$_findCachedViewById(i3)).setVisibility(0);
            this$0.setCarAddress();
            this$0.viewRoute();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startTracking() {
        int i2;
        OkHttpClient unsafeOkHttpClient = getUnsafeOkHttpClient();
        StringBuilder sb = new StringBuilder();
        sb.append("https://lb1.cvip-preprod.citroen.in:40545/cpi/vehicleStatus/stream/");
        SharedPref.Companion companion = SharedPref.Companion;
        sb.append(companion.getVinNumber(this));
        Request.Builder addHeader = new Request.Builder().url(sb.toString()).addHeader("Accept", "text/event-stream");
        final Request build = addHeader.addHeader("Authorization", "Bearer " + companion.getPrimaryUserToken(this)).build();
        final EventSource.Factory createFactory = EventSources.createFactory(unsafeOkHttpClient);
        if (!this.shouldRetry || (i2 = this.retryCounter) == 0 || i2 >= this.maxRetryCount) {
            this.sseEventSource = createFactory.newEventSource(build, this.eventSourceListener);
            return;
        }
        Runnable runnable = new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.activities.dashboard.d
            @Override // java.lang.Runnable
            public final void run() {
                LocateCarActivity.m91startTracking$lambda5(LocateCarActivity.this, createFactory, build);
            }
        };
        Logger logger = Logger.INSTANCE;
        logger.e("Retry with count : " + this.retryCounter);
        new Handler(Looper.getMainLooper()).postDelayed(runnable, this.retryDelayMillis);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startTracking$lambda-5  reason: not valid java name */
    public static final void m91startTracking$lambda5(LocateCarActivity this$0, EventSource.Factory factory, Request request) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(factory, "$factory");
        Intrinsics.checkNotNullParameter(request, "$request");
        this$0.sseEventSource = factory.newEventSource(request, this$0.eventSourceListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateRoute(Message message, LatLng latLng, int i2) {
        CharSequence trim;
        Object obj = message.obj;
        Objects.requireNonNull(obj, "null cannot be cast to non-null type org.w3c.dom.Document");
        Document document = (Document) obj;
        GMapV2Direction gMapV2Direction = new GMapV2Direction();
        String dist = gMapV2Direction.getDistanceText(document);
        ((MaterialTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvDistance)).setText(dist);
        ((MaterialTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvTime)).setText(gMapV2Direction.getDurationText(document));
        String startAddress = gMapV2Direction.getStartAddress(document);
        Intrinsics.checkNotNullExpressionValue(startAddress, "md.getStartAddress(doc)");
        this.sourceAddress = startAddress;
        GoogleMap googleMap = this.googleMap;
        Intrinsics.checkNotNull(googleMap);
        this.sourceMarker = MapExtensionKt.drawMarker(googleMap, this, new LatLng(this.sourceLatitude, this.sourceLongitude), R.drawable.ic_blue_marker, this.sourceAddress);
        ArrayList<LatLng> direction = gMapV2Direction.getDirection(document);
        PolylineOptions color = new PolylineOptions().width(10.0f).color(i2);
        Intrinsics.checkNotNullExpressionValue(color, "PolylineOptions().width(10.0f).color(color)");
        ArrayList arrayList = new ArrayList();
        int size = direction.size();
        for (int i3 = 0; i3 < size; i3++) {
            color.add(direction.get(i3));
            arrayList.add(direction.get(i3));
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            builder.include((LatLng) it.next());
        }
        LatLngBounds build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        double d2 = 100;
        LatLng computeOffset = SphericalUtil.computeOffset(build.northeast, Math.sqrt(2.0d) * d2, 45.0d);
        LatLng computeOffset2 = SphericalUtil.computeOffset(build.southwest, d2 * Math.sqrt(2.0d), 225.0d);
        builder.include(computeOffset);
        builder.include(computeOffset2);
        Intrinsics.checkNotNullExpressionValue(dist, "dist");
        trim = StringsKt__StringsKt.trim((CharSequence) new Regex("[^a-zA-Z]+").replace(dist, ""));
        if (Intrinsics.areEqual(trim.toString(), "m")) {
            GoogleMap googleMap2 = this.googleMap;
            Intrinsics.checkNotNull(googleMap2);
            googleMap2.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(this.carLatitude, this.carLongitude), 16.0f));
        } else {
            GoogleMap googleMap3 = this.googleMap;
            Intrinsics.checkNotNull(googleMap3);
            this.polyline = googleMap3.addPolyline(color);
            LatLngBounds.Builder builder2 = new LatLngBounds.Builder();
            builder2.include(new LatLng(this.sourceLatitude, this.sourceLongitude));
            builder2.include(latLng);
            GoogleMap googleMap4 = this.googleMap;
            Intrinsics.checkNotNull(googleMap4);
            googleMap4.moveCamera(CameraUpdateFactory.newLatLngBounds(builder2.build(), 200));
        }
        AppUtil.Companion.dismissDialog();
    }

    private final void viewRoute() {
        String str = this.ignitionStatus;
        if (str != null) {
            Intrinsics.checkNotNull(str);
            Locale locale = Locale.ROOT;
            String lowerCase = str.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            String lowerCase2 = AppConstants.IGNITION_STATUS_CONTACT.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            if (Intrinsics.areEqual(lowerCase, lowerCase2)) {
                Location location = new Location("gps");
                location.setLatitude((-1) * this.carLatitude);
                location.setLongitude(this.carLongitude);
                Marker marker = this.carMarker;
                if (marker != null) {
                    Intrinsics.checkNotNull(marker);
                    marker.remove();
                }
                g(this, null, 1, null);
                animateMarkerNew(location, this.carMarker);
                startTracking();
            }
        }
        Polyline polyline = this.polyline;
        if (polyline != null) {
            Intrinsics.checkNotNull(polyline);
            polyline.remove();
        }
        Marker marker2 = this.sourceMarker;
        if (marker2 != null) {
            Intrinsics.checkNotNull(marker2);
            marker2.remove();
        }
        Marker marker3 = this.carMarker;
        if (marker3 != null) {
            Intrinsics.checkNotNull(marker3);
            marker3.remove();
        }
        g(this, null, 1, null);
        route(new LatLng(this.carLatitude, this.carLongitude), ContextCompat.getColor(this, R.color.primary_color_1));
        startTracking();
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

    public final int getMaxRetryCount() {
        return this.maxRetryCount;
    }

    @Subscribe
    public final void getResponse(@NotNull VehicleStatus response) {
        Intrinsics.checkNotNullParameter(response, "response");
        AppUtil.Companion.dismissDialog();
        Double gpsLat = response.getGpsLat();
        Intrinsics.checkNotNull(gpsLat);
        this.carLatitude = gpsLat.doubleValue();
        Double gpsLong = response.getGpsLong();
        Intrinsics.checkNotNull(gpsLong);
        this.carLongitude = gpsLong.doubleValue();
        Double coarseAngle = response.getCoarseAngle();
        Intrinsics.checkNotNull(coarseAngle);
        this.coarseAngle = coarseAngle.doubleValue();
        Long signalTimeStamp = response.getSignalTimeStamp();
        if (signalTimeStamp != null) {
            setLastUpdateOn(signalTimeStamp.longValue());
        }
        setCarAddress();
        viewRoute();
    }

    public final int getRetryCounter() {
        return this.retryCounter;
    }

    public final boolean getShouldRetry() {
        return this.shouldRetry;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    @Deprecated(message = "Deprecated in Java")
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        super.onActivityResult(i2, i3, intent);
        GpsStatusDetector gpsStatusDetector = this.mGpsStatusDetector;
        if (gpsStatusDetector != null && gpsStatusDetector != null) {
            gpsStatusDetector.checkOnActivityResult(i2, i3);
        }
        if (i2 == 199) {
            if (i3 == 0) {
                String string = getResources().getString(R.string.location_permission_denied);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…cation_permission_denied)");
                ExtensionsKt.showToast(this, string);
                AppUtil.Companion.dismissDialog();
            } else if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                AppUtil.Companion.dismissDialog();
                FusedLocationProviderApi fusedLocationProviderApi = LocationServices.FusedLocationApi;
                GoogleApiClient googleApiClient = this.mGoogleApiClient;
                Intrinsics.checkNotNull(googleApiClient);
                LocationRequest locationRequest = this.mLocationRequest;
                Intrinsics.checkNotNull(locationRequest);
                fusedLocationProviderApi.requestLocationUpdates(googleApiClient, locationRequest, this);
            }
        }
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivBack))) {
            finish();
        } else if (Intrinsics.areEqual(view, (MaterialButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnShare))) {
            new ShareCompat.IntentBuilder(this).setType(HTTP.PLAIN_TEXT_TYPE).setChooserTitle(getString(R.string.lbl_share_via)).setText("http://maps.google.com?q=" + this.carLatitude + AbstractJsonLexerKt.COMMA + this.carLongitude).startChooser();
        } else if (Intrinsics.areEqual(view, (MaterialButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnNavigateToCar))) {
            AppUtil.Companion.navigateToDestination(this, this.carLatitude, this.carLongitude);
        } else if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.zoomMyCar))) {
            GoogleMap googleMap = this.googleMap;
            Intrinsics.checkNotNull(googleMap);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(this.carLatitude, this.carLongitude), 16.0f));
        }
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest locationRequest = new LocationRequest();
        this.mLocationRequest = locationRequest;
        locationRequest.setInterval(1000L);
        LocationRequest locationRequest2 = this.mLocationRequest;
        if (locationRequest2 != null) {
            locationRequest2.setFastestInterval(1000L);
        }
        LocationRequest locationRequest3 = this.mLocationRequest;
        if (locationRequest3 != null) {
            locationRequest3.setPriority(102);
        }
        GpsStatusDetector gpsStatusDetector = this.mGpsStatusDetector;
        if (gpsStatusDetector != null) {
            gpsStatusDetector.checkGpsStatus();
        }
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public void onConnectionFailed(@NotNull ConnectionResult p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Logger logger = Logger.INSTANCE;
        logger.e("Map connection failed : " + p0.getErrorMessage());
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public void onConnectionSuspended(int i2) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        GpsStatusDetector gpsStatusDetector;
        super.onCreate(bundle);
        setContentView(R.layout.activity_locate_car);
        init();
        setListeners();
        if (this.mLocationRequest == null || (gpsStatusDetector = this.mGpsStatusDetector) == null) {
            return;
        }
        gpsStatusDetector.checkGpsStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        closeConnection();
    }

    @Override // com.psa.mym.mycitroenconnect.utils.GpsStatusDetector.GpsStatusDetectorCallBack
    public void onGpsAlertCanceledByUser() {
        Logger.INSTANCE.e("onGpsAlertCanceledByUser");
        finish();
        String string = getResources().getString(R.string.fetching_your_cars_location);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…ching_your_cars_location)");
        ExtensionsKt.showToast(this, string);
    }

    @Override // com.psa.mym.mycitroenconnect.utils.GpsStatusDetector.GpsStatusDetectorCallBack
    public void onGpsSettingStatus(boolean z) {
        Logger.INSTANCE.e("onGpsSettingStatus: true");
        if (this.mLocationRequest != null) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                FusedLocationProviderApi fusedLocationProviderApi = LocationServices.FusedLocationApi;
                GoogleApiClient googleApiClient = this.mGoogleApiClient;
                Intrinsics.checkNotNull(googleApiClient);
                LocationRequest locationRequest = this.mLocationRequest;
                Intrinsics.checkNotNull(locationRequest);
                fusedLocationProviderApi.requestLocationUpdates(googleApiClient, locationRequest, this);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    @Override // com.google.android.gms.location.LocationListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onLocationChanged(@NotNull Location location) {
        GoogleApiClient googleApiClient;
        Intrinsics.checkNotNullParameter(location, "location");
        GoogleMap googleMap = this.googleMap;
        Intrinsics.checkNotNull(googleMap);
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        this.sourceLatitude = location.getLatitude();
        this.sourceLongitude = location.getLongitude();
        CameraPosition build = CameraPosition.builder().target(new LatLng(this.carLatitude, this.carLongitude)).zoom(16.0f).build();
        Intrinsics.checkNotNullExpressionValue(build, "builder()\n            .t…16f)\n            .build()");
        GoogleMap googleMap2 = this.googleMap;
        Intrinsics.checkNotNull(googleMap2);
        googleMap2.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(this.carLatitude, this.carLongitude), 10.0f));
        GoogleMap googleMap3 = this.googleMap;
        Intrinsics.checkNotNull(googleMap3);
        googleMap3.moveCamera(CameraUpdateFactory.newCameraPosition(build));
        String str = this.ignitionStatus;
        if (str != null) {
            Intrinsics.checkNotNull(str);
            Locale locale = Locale.ROOT;
            String lowerCase = str.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            String lowerCase2 = AppConstants.IGNITION_STATUS_CONTACT.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            if (Intrinsics.areEqual(lowerCase, lowerCase2)) {
                startTracking();
                googleApiClient = this.mGoogleApiClient;
                if (googleApiClient == null) {
                    FusedLocationProviderApi fusedLocationProviderApi = LocationServices.FusedLocationApi;
                    Intrinsics.checkNotNull(googleApiClient);
                    fusedLocationProviderApi.removeLocationUpdates(googleApiClient, this);
                    return;
                }
                return;
            }
        }
        getSnapShotVehicleStatus();
        googleApiClient = this.mGoogleApiClient;
        if (googleApiClient == null) {
        }
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnMapClickListener
    public void onMapClick(@NotNull LatLng p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(@NotNull GoogleMap map) {
        Intrinsics.checkNotNullParameter(map, "map");
        this.googleMap = map;
        Intrinsics.checkNotNull(map);
        UiSettings uiSettings = map.getUiSettings();
        uiSettings.setAllGesturesEnabled(false);
        uiSettings.setCompassEnabled(false);
        uiSettings.setMapToolbarEnabled(false);
        uiSettings.setIndoorLevelPickerEnabled(false);
        uiSettings.setZoomControlsEnabled(false);
        uiSettings.setRotateGesturesEnabled(true);
        uiSettings.setZoomGesturesEnabled(true);
        uiSettings.setScrollGesturesEnabled(true);
        uiSettings.setTiltGesturesEnabled(true);
        GoogleMap googleMap = this.googleMap;
        Intrinsics.checkNotNull(googleMap);
        googleMap.clear();
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            buildGoogleApiClient();
        } else {
            checkLocationPermission();
        }
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
    public boolean onMarkerClick(@NotNull Marker p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return false;
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener
    public boolean onMyLocationButtonClick() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Logger.INSTANCE.e("onResume");
    }

    public final void setRetryCounter(int i2) {
        this.retryCounter = i2;
    }

    public final void setShouldRetry(boolean z) {
        this.shouldRetry = z;
    }
}
