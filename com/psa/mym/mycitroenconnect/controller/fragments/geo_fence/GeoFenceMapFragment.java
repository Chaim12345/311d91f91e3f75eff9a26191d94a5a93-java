package com.psa.mym.mycitroenconnect.controller.fragments.geo_fence;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentViewModelLazyKt;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.card.MaterialCardView;
import com.google.maps.android.PolyUtil;
import com.google.maps.android.SphericalUtil;
import com.psa.mym.mycitroenconnect.api.body.geo_fence.Coordinates;
import com.psa.mym.mycitroenconnect.api.body.geo_fence.GeoFenceBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.dashboard.GeoFenceActivity;
import com.psa.mym.mycitroenconnect.controller.activities.dashboard.SearchLocationActivity;
import com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.GeoFenceMapFragment;
import com.psa.mym.mycitroenconnect.controller.viewmodel.GeoFenceViewModel;
import com.psa.mym.mycitroenconnect.model.GeoFenceCommonModel;
import com.psa.mym.mycitroenconnect.model.geo_fence.GeoFenceType;
import com.psa.mym.mycitroenconnect.model.geo_fence.GetGeoFenceResponseItem;
import com.psa.mym.mycitroenconnect.model.geo_fence.LocationData;
import com.psa.mym.mycitroenconnect.model.geo_fence.MapPolygonData;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.MapExtensionKt;
import com.psa.mym.mycitroenconnect.utils.google_map_direction.GMapV2Direction;
import com.psa.mym.mycitroenconnect.utils.google_map_direction.GMapV2DirectionAsyncTask;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Document;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class GeoFenceMapFragment extends Fragment implements View.OnClickListener, OnMapReadyCallback, GoogleMap.OnMarkerDragListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final float ZOOM_LEVEL = 10.0f;
    private double carLatitude;
    private double carLongitude;
    @Nullable
    private Circle circle;
    private double destinationLatitude;
    private double destinationLongitude;
    private GoogleMap googleMap;
    private boolean isSourceChange;
    private SupportMapFragment mapFragment;
    @Nullable
    private GeoFenceActivity parentActivity;
    @Nullable
    private Polygon polygon;
    @Nullable
    private PolygonOptions polygonOptions;
    private double sourceLatitude;
    private double sourceLongitude;
    @NotNull
    private ActivityResultLauncher<Intent> sourceResultLauncher;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String fenceType = "";
    @NotNull
    private String carAddress = "";
    @NotNull
    private String sourceAddress = "";
    @NotNull
    private String destinationAddress = "";
    private double radius = 10.0d;
    @NotNull
    private ArrayList<MapPolygonData> polygonDataList = new ArrayList<>();
    @Nullable
    private ArrayList<LatLng> latLngList = new ArrayList<>();
    @NotNull
    private final Lazy viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(GeoFenceViewModel.class), new GeoFenceMapFragment$special$$inlined$activityViewModels$default$1(this), new GeoFenceMapFragment$special$$inlined$activityViewModels$default$2(this));

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final GeoFenceMapFragment newInstance() {
            GeoFenceMapFragment geoFenceMapFragment = new GeoFenceMapFragment();
            geoFenceMapFragment.setArguments(new Bundle());
            return geoFenceMapFragment;
        }
    }

    public GeoFenceMapFragment() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: l.a
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                GeoFenceMapFragment.m138_init_$lambda1(GeoFenceMapFragment.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…          }\n            }");
        this.sourceResultLauncher = registerForActivityResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-1  reason: not valid java name */
    public static final void m138_init_$lambda1(GeoFenceMapFragment this$0, ActivityResult activityResult) {
        Intent data;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult == null || activityResult.getData() == null || activityResult.getResultCode() != -1 || (data = activityResult.getData()) == null) {
            return;
        }
        this$0.sourceLatitude = data.getDoubleExtra("latitude", 0.0d);
        this$0.sourceLongitude = data.getDoubleExtra("longitude", 0.0d);
        Logger logger = Logger.INSTANCE;
        logger.e("sourceLatitude = " + this$0.sourceLatitude + " & sourceLongitude = " + this$0.sourceLongitude);
        this$0.setSourceAddressText();
        String str = this$0.fenceType;
        if (Intrinsics.areEqual(str, AppConstants.GEO_FENCE_MODE_RADIUS)) {
            this$0.displayRadiusFence();
        } else if (Intrinsics.areEqual(str, AppConstants.GEO_FENCE_MODE_CUSTOM)) {
            this$0.displayCustomFence(false);
        }
    }

    private final MapPolygonData createMapPolygonData(String str, LatLng latLng) {
        return new MapPolygonData(str, latLng.latitude, latLng.longitude, latLng);
    }

    private final void displayCustomFence(boolean z) {
        GoogleMap googleMap = this.googleMap;
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            googleMap = null;
        }
        googleMap.clear();
        drawSourceMarker();
        drawCarMarker();
        drawCircle(new LatLng(this.sourceLatitude, this.sourceLongitude), z);
    }

    private final void displayRadiusFence() {
        float f2;
        GoogleMap googleMap = this.googleMap;
        GoogleMap googleMap2 = null;
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            googleMap = null;
        }
        googleMap.clear();
        Circle circle = this.circle;
        if (circle != null) {
            circle.remove();
        }
        double d2 = this.radius * 1000;
        boolean z = true;
        if (this.sourceLatitude == 0.0d) {
            if (this.sourceLongitude == 0.0d) {
                this.sourceLatitude = this.carLatitude;
                this.sourceLongitude = this.carLongitude;
                setSourceAddressText();
            }
        }
        LatLng latLng = new LatLng(this.sourceLatitude, this.sourceLongitude);
        drawSourceMarker();
        drawCarMarker();
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(latLng);
        circleOptions.radius(d2);
        circleOptions.strokeWidth(4.0f);
        circleOptions.strokeColor(ContextCompat.getColor(requireContext(), R.color.primary_color_1));
        circleOptions.fillColor(Color.parseColor("#1A9D0605"));
        GoogleMap googleMap3 = this.googleMap;
        if (googleMap3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
        } else {
            googleMap2 = googleMap3;
        }
        googleMap2.setMaxZoomPreference(25.0f);
        int i2 = (int) this.radius;
        if (i2 >= 0 && i2 < 6) {
            f2 = 12.0f;
        } else {
            if (6 <= i2 && i2 < 11) {
                f2 = 10.0f;
            } else {
                if (11 <= i2 && i2 < 31) {
                    f2 = 9.0f;
                } else {
                    if (31 <= i2 && i2 < 51) {
                        f2 = 8.0f;
                    } else {
                        if (!(51 <= i2 && i2 < 101)) {
                            if (999 > i2 || i2 >= 1001) {
                                z = false;
                            }
                            if (z) {
                                f2 = 16.0f;
                            }
                            this.circle = googleMap2.addCircle(circleOptions);
                        }
                        f2 = 6.0f;
                    }
                }
            }
        }
        MapExtensionKt.moveCameraOnMap$default(googleMap2, f2, latLng, false, 4, null);
        this.circle = googleMap2.addCircle(circleOptions);
    }

    private final void displayRouteFence(boolean z) {
        GoogleMap googleMap = this.googleMap;
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            googleMap = null;
        }
        googleMap.clear();
        drawSourceMarker();
        route(new LatLng(this.sourceLatitude, this.sourceLongitude), new LatLng(this.destinationLatitude, this.destinationLongitude), ContextCompat.getColor(requireContext(), R.color.primary_color_1), z);
        drawDestinationMarker();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"PotentialBehaviorOverride"})
    public final void drawBounds(LatLngBounds latLngBounds) {
        LatLng latLng = new LatLng(this.carLatitude, this.carLongitude);
        GoogleMap googleMap = this.googleMap;
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            googleMap = null;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        MapExtensionKt.drawMarker(googleMap, requireContext, latLng, R.drawable.ic_car_marker, this.carAddress);
        LatLng latLng2 = latLngBounds.northeast;
        LatLng latLng3 = new LatLng(latLng2.latitude, latLng2.longitude);
        LatLng latLng4 = new LatLng(latLngBounds.southwest.latitude, latLngBounds.northeast.longitude);
        LatLng latLng5 = latLngBounds.southwest;
        LatLng latLng6 = new LatLng(latLng5.latitude, latLng5.longitude);
        LatLng latLng7 = new LatLng(latLngBounds.northeast.latitude, latLngBounds.southwest.longitude);
        LatLng polygonCenterPoint = getPolygonCenterPoint(latLng3, latLng4);
        LatLng polygonCenterPoint2 = getPolygonCenterPoint(latLng4, latLng6);
        LatLng polygonCenterPoint3 = getPolygonCenterPoint(latLng6, latLng7);
        LatLng polygonCenterPoint4 = getPolygonCenterPoint(latLng7, latLng3);
        ArrayList<LatLng> arrayList = this.latLngList;
        if (arrayList != null) {
            arrayList.clear();
            arrayList.add(latLng3);
            arrayList.add(polygonCenterPoint);
            arrayList.add(latLng4);
            arrayList.add(polygonCenterPoint2);
            arrayList.add(latLng6);
            arrayList.add(polygonCenterPoint3);
            arrayList.add(latLng7);
            arrayList.add(polygonCenterPoint4);
            this.polygonDataList.clear();
            int i2 = 0;
            for (Object obj : arrayList) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                LatLng latLng8 = (LatLng) obj;
                String valueOf = String.valueOf(Random.Default.nextInt());
                GoogleMap googleMap2 = this.googleMap;
                if (googleMap2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                    googleMap2 = null;
                }
                MarkerOptions title = new MarkerOptions().position(latLng8).snippet(valueOf).title(String.valueOf(i3));
                AppUtil.Companion companion = AppUtil.Companion;
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                googleMap2.addMarker(title.icon(companion.getBitmapDescriptorFromVector(requireContext2, R.drawable.ic_move_icon_2)).draggable(true));
                this.polygonDataList.add(createMapPolygonData(valueOf, latLng8));
                i2 = i3;
            }
            PolyUtil.isLocationOnPath(latLng3, arrayList, true);
            ArrayList<LatLng> arrayList2 = this.latLngList;
            Intrinsics.checkNotNull(arrayList2);
            drawPolygon(arrayList2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void drawCarMarker() {
        GoogleMap googleMap = this.googleMap;
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            googleMap = null;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        MapExtensionKt.drawMarker(googleMap, requireContext, new LatLng(this.carLatitude, this.carLongitude), R.drawable.ic_car_marker, this.carAddress);
    }

    private final void drawCircle(LatLng latLng, boolean z) {
        GoogleMap googleMap;
        double d2 = 6.283185307179586d / 8;
        Logger logger = Logger.INSTANCE;
        logger.e("Phases: " + d2);
        double radians = Math.toRadians(5.0d);
        logger.e("Radius in degree: " + radians);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        ArrayList<LatLng> arrayList = this.latLngList;
        if (arrayList != null) {
            int i2 = 0;
            if (!z) {
                arrayList.clear();
                int i3 = 0;
                for (int i4 = 8; i3 < i4; i4 = 8) {
                    double d3 = i3 * d2;
                    arrayList.add(new LatLng(latLng.latitude + (Math.sin(d3) * radians), latLng.longitude + (Math.cos(d3) * radians)));
                    i3++;
                    builder = builder;
                }
            }
            LatLngBounds.Builder builder2 = builder;
            this.polygonDataList.clear();
            Iterator<T> it = arrayList.iterator();
            while (true) {
                googleMap = null;
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                int i5 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                LatLng latLng2 = (LatLng) next;
                String valueOf = String.valueOf(Random.Default.nextInt());
                GoogleMap googleMap2 = this.googleMap;
                if (googleMap2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                } else {
                    googleMap = googleMap2;
                }
                MarkerOptions title = new MarkerOptions().position(latLng2).snippet(valueOf).title(String.valueOf(i5));
                AppUtil.Companion companion = AppUtil.Companion;
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                googleMap.addMarker(title.icon(companion.getBitmapDescriptorFromVector(requireContext, R.drawable.ic_move_icon_2)).draggable(true));
                this.polygonDataList.add(createMapPolygonData(valueOf, latLng2));
                builder2.include(latLng2);
                i2 = i5;
            }
            drawPolygon(arrayList);
            LatLngBounds build = builder2.build();
            Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
            double d4 = 100;
            LatLng computeOffset = SphericalUtil.computeOffset(build.northeast, Math.sqrt(2.0d) * d4, 45.0d);
            LatLng computeOffset2 = SphericalUtil.computeOffset(build.southwest, d4 * Math.sqrt(2.0d), 225.0d);
            builder2.include(computeOffset);
            builder2.include(computeOffset2);
            GoogleMap googleMap3 = this.googleMap;
            if (googleMap3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            } else {
                googleMap = googleMap3;
            }
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder2.build(), 200));
        }
    }

    private final void drawDestinationMarker() {
        GoogleMap googleMap = this.googleMap;
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            googleMap = null;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        MapExtensionKt.drawMarker(googleMap, requireContext, new LatLng(this.destinationLatitude, this.destinationLongitude), R.drawable.ic_destination_marker, this.destinationAddress);
    }

    private final void drawPolygon(ArrayList<LatLng> arrayList) {
        Logger.INSTANCE.e("Draw Polygon");
        this.polygonOptions = new PolygonOptions();
        Polygon polygon = this.polygon;
        if (polygon != null && polygon != null) {
            polygon.remove();
        }
        PolygonOptions polygonOptions = this.polygonOptions;
        if (polygonOptions != null) {
            polygonOptions.addAll(arrayList).strokeWidth(1.0f).strokeColor(ContextCompat.getColor(requireContext(), R.color.primary_color_2)).fillColor(ContextCompat.getColor(requireContext(), 17170445));
            GoogleMap googleMap = this.googleMap;
            if (googleMap == null) {
                Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                googleMap = null;
            }
            this.polygon = googleMap.addPolygon(polygonOptions);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"PotentialBehaviorOverride"})
    public final void drawRouteBounds() {
        GetGeoFenceResponseItem geoFenceResponse;
        GeoFenceActivity geoFenceActivity = this.parentActivity;
        List<LocationData> coordinates = (geoFenceActivity == null || (geoFenceResponse = geoFenceActivity.getGeoFenceResponse()) == null) ? null : geoFenceResponse.getCoordinates();
        if (coordinates != null) {
            int size = coordinates.size();
            Logger.INSTANCE.e("size: " + size);
            ArrayList<LatLng> arrayList = this.latLngList;
            if (arrayList != null) {
                arrayList.clear();
                int i2 = 0;
                for (Object obj : coordinates) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    LocationData locationData = (LocationData) obj;
                    Logger.INSTANCE.e("Index: " + i2);
                    if (i2 != size - 1) {
                        Double gpsLat = locationData.getGpsLat();
                        Intrinsics.checkNotNull(gpsLat);
                        double doubleValue = gpsLat.doubleValue();
                        Double gpsLong = locationData.getGpsLong();
                        Intrinsics.checkNotNull(gpsLong);
                        arrayList.add(new LatLng(doubleValue, gpsLong.doubleValue()));
                    }
                    i2 = i3;
                }
                this.polygonDataList.clear();
                int i4 = 0;
                for (Object obj2 : arrayList) {
                    int i5 = i4 + 1;
                    if (i4 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    LatLng latLng = (LatLng) obj2;
                    String valueOf = String.valueOf(Random.Default.nextInt());
                    GoogleMap googleMap = this.googleMap;
                    if (googleMap == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                        googleMap = null;
                    }
                    MarkerOptions title = new MarkerOptions().position(latLng).snippet(valueOf).title(String.valueOf(i5));
                    AppUtil.Companion companion = AppUtil.Companion;
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    googleMap.addMarker(title.icon(companion.getBitmapDescriptorFromVector(requireContext, R.drawable.ic_move_icon_2)).draggable(true));
                    this.polygonDataList.add(createMapPolygonData(valueOf, latLng));
                    i4 = i5;
                }
                PolyUtil.isLocationOnPath(arrayList.get(0), arrayList, true);
                ArrayList<LatLng> arrayList2 = this.latLngList;
                Intrinsics.checkNotNull(arrayList2);
                drawPolygon(arrayList2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"PotentialBehaviorOverride"})
    public final void drawRouteBoundsForSet() {
        GeoFenceBody geoFenceBody;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        ArrayList<Coordinates> coordinates = (geoFenceCommonModel == null || (geoFenceBody = geoFenceCommonModel.getGeoFenceBody()) == null) ? null : geoFenceBody.getCoordinates();
        if (coordinates != null) {
            int size = coordinates.size();
            Logger.INSTANCE.e("size: " + size);
            ArrayList<LatLng> arrayList = this.latLngList;
            if (arrayList != null) {
                arrayList.clear();
                int i2 = 0;
                for (Object obj : coordinates) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    Coordinates coordinates2 = (Coordinates) obj;
                    Logger.INSTANCE.e("Index: " + i2);
                    if (i2 != size - 1) {
                        Double gpsLat = coordinates2.getGpsLat();
                        Intrinsics.checkNotNull(gpsLat);
                        double doubleValue = gpsLat.doubleValue();
                        Double gpsLong = coordinates2.getGpsLong();
                        Intrinsics.checkNotNull(gpsLong);
                        arrayList.add(new LatLng(doubleValue, gpsLong.doubleValue()));
                    }
                    i2 = i3;
                }
                this.polygonDataList.clear();
                int i4 = 0;
                for (Object obj2 : arrayList) {
                    int i5 = i4 + 1;
                    if (i4 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    LatLng latLng = (LatLng) obj2;
                    String valueOf = String.valueOf(Random.Default.nextInt());
                    GoogleMap googleMap = this.googleMap;
                    if (googleMap == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                        googleMap = null;
                    }
                    MarkerOptions title = new MarkerOptions().position(latLng).snippet(valueOf).title(String.valueOf(i5));
                    AppUtil.Companion companion = AppUtil.Companion;
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    googleMap.addMarker(title.icon(companion.getBitmapDescriptorFromVector(requireContext, R.drawable.ic_move_icon_2)).draggable(true));
                    this.polygonDataList.add(createMapPolygonData(valueOf, latLng));
                    i4 = i5;
                }
                PolyUtil.isLocationOnPath(arrayList.get(0), arrayList, true);
                ArrayList<LatLng> arrayList2 = this.latLngList;
                Intrinsics.checkNotNull(arrayList2);
                drawPolygon(arrayList2);
            }
        }
    }

    private final void drawSourceMarker() {
        GoogleMap googleMap = this.googleMap;
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            googleMap = null;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        MapExtensionKt.drawMarker(googleMap, requireContext, new LatLng(this.sourceLatitude, this.sourceLongitude), R.drawable.ic_source_marker, this.sourceAddress);
    }

    private final void getBundleData() {
        getArguments();
    }

    private final LatLng getPolygonCenterPoint(LatLng latLng, LatLng latLng2) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(latLng);
        builder.include(latLng2);
        LatLngBounds build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build.getCenter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GeoFenceViewModel getViewModel() {
        return (GeoFenceViewModel) this.viewModel$delegate.getValue();
    }

    private final void initView() {
        GeoFenceActivity geoFenceActivity;
        GeoFenceType geoFenceType;
        SupportMapFragment supportMapFragment = null;
        if (getActivity() == null || !(getActivity() instanceof GeoFenceActivity)) {
            geoFenceActivity = null;
        } else {
            FragmentActivity activity = getActivity();
            Objects.requireNonNull(activity, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.controller.activities.dashboard.GeoFenceActivity");
            geoFenceActivity = (GeoFenceActivity) activity;
        }
        this.parentActivity = geoFenceActivity;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        this.fenceType = String.valueOf((geoFenceCommonModel == null || (geoFenceType = geoFenceCommonModel.getGeoFenceType()) == null) ? null : geoFenceType.getFenceType());
        setListeners();
        SupportMapFragment newInstance = SupportMapFragment.newInstance();
        Intrinsics.checkNotNullExpressionValue(newInstance, "newInstance()");
        this.mapFragment = newInstance;
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "childFragmentManager.beginTransaction()");
        SupportMapFragment supportMapFragment2 = this.mapFragment;
        if (supportMapFragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapFragment");
            supportMapFragment2 = null;
        }
        beginTransaction.add(R.id.mapContainer, supportMapFragment2);
        beginTransaction.commit();
        SupportMapFragment supportMapFragment3 = this.mapFragment;
        if (supportMapFragment3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapFragment");
        } else {
            supportMapFragment = supportMapFragment3;
        }
        supportMapFragment.getMapAsync(this);
    }

    private final void navigateToMapSettingsFragment() {
        String str = this.fenceType;
        int hashCode = str.hashCode();
        if (hashCode != -1349088399) {
            if (hashCode != -938578798) {
                if (hashCode == 108704329 && str.equals(AppConstants.GEO_FENCE_MODE_ROUTE)) {
                    setDataForRouteFence();
                }
            } else if (str.equals(AppConstants.GEO_FENCE_MODE_RADIUS)) {
                setDataForRadiusFence();
            }
        } else if (str.equals(AppConstants.GEO_FENCE_MODE_CUSTOM)) {
            setDataForCustomFence();
        }
        GeoFenceActivity geoFenceActivity = this.parentActivity;
        if (geoFenceActivity != null) {
            geoFenceActivity.navigateToGeoFenceMapSettingsFragment();
        }
    }

    @JvmStatic
    @NotNull
    public static final GeoFenceMapFragment newInstance() {
        return Companion.newInstance();
    }

    @SuppressLint({"HandlerLeak"})
    private final void route(final LatLng latLng, final LatLng latLng2, final int i2, boolean z) {
        Logger logger = Logger.INSTANCE;
        logger.e("Flag " + z);
        final Looper mainLooper = Looper.getMainLooper();
        new GMapV2DirectionAsyncTask(new Handler(mainLooper) { // from class: com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.GeoFenceMapFragment$route$handler$1
            /* JADX WARN: Removed duplicated region for block: B:32:0x00e9 A[Catch: Exception -> 0x0169, TryCatch #0 {Exception -> 0x0169, blocks: (B:3:0x0010, B:5:0x0014, B:7:0x0044, B:8:0x0057, B:9:0x0065, B:11:0x006b, B:12:0x0075, B:14:0x00bb, B:19:0x00c7, B:21:0x00cf, B:23:0x00d5, B:26:0x00dd, B:32:0x00e9, B:50:0x0124, B:52:0x012c, B:53:0x0130, B:55:0x014c, B:56:0x0150, B:33:0x00ef, B:34:0x00f1, B:35:0x00f5, B:37:0x0101, B:39:0x0107, B:42:0x010f, B:48:0x011b, B:49:0x0121, B:57:0x0161, B:58:0x0168), top: B:66:0x0010 }] */
            /* JADX WARN: Removed duplicated region for block: B:33:0x00ef A[Catch: Exception -> 0x0169, TryCatch #0 {Exception -> 0x0169, blocks: (B:3:0x0010, B:5:0x0014, B:7:0x0044, B:8:0x0057, B:9:0x0065, B:11:0x006b, B:12:0x0075, B:14:0x00bb, B:19:0x00c7, B:21:0x00cf, B:23:0x00d5, B:26:0x00dd, B:32:0x00e9, B:50:0x0124, B:52:0x012c, B:53:0x0130, B:55:0x014c, B:56:0x0150, B:33:0x00ef, B:34:0x00f1, B:35:0x00f5, B:37:0x0101, B:39:0x0107, B:42:0x010f, B:48:0x011b, B:49:0x0121, B:57:0x0161, B:58:0x0168), top: B:66:0x0010 }] */
            /* JADX WARN: Removed duplicated region for block: B:48:0x011b A[Catch: Exception -> 0x0169, TryCatch #0 {Exception -> 0x0169, blocks: (B:3:0x0010, B:5:0x0014, B:7:0x0044, B:8:0x0057, B:9:0x0065, B:11:0x006b, B:12:0x0075, B:14:0x00bb, B:19:0x00c7, B:21:0x00cf, B:23:0x00d5, B:26:0x00dd, B:32:0x00e9, B:50:0x0124, B:52:0x012c, B:53:0x0130, B:55:0x014c, B:56:0x0150, B:33:0x00ef, B:34:0x00f1, B:35:0x00f5, B:37:0x0101, B:39:0x0107, B:42:0x010f, B:48:0x011b, B:49:0x0121, B:57:0x0161, B:58:0x0168), top: B:66:0x0010 }] */
            /* JADX WARN: Removed duplicated region for block: B:49:0x0121 A[Catch: Exception -> 0x0169, TryCatch #0 {Exception -> 0x0169, blocks: (B:3:0x0010, B:5:0x0014, B:7:0x0044, B:8:0x0057, B:9:0x0065, B:11:0x006b, B:12:0x0075, B:14:0x00bb, B:19:0x00c7, B:21:0x00cf, B:23:0x00d5, B:26:0x00dd, B:32:0x00e9, B:50:0x0124, B:52:0x012c, B:53:0x0130, B:55:0x014c, B:56:0x0150, B:33:0x00ef, B:34:0x00f1, B:35:0x00f5, B:37:0x0101, B:39:0x0107, B:42:0x010f, B:48:0x011b, B:49:0x0121, B:57:0x0161, B:58:0x0168), top: B:66:0x0010 }] */
            @Override // android.os.Handler
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void handleMessage(@NotNull Message msg) {
                GoogleMap googleMap;
                GoogleMap googleMap2;
                double d2;
                double d3;
                GeoFenceViewModel viewModel;
                GeoFenceViewModel viewModel2;
                boolean z2;
                GeoFenceMapFragment geoFenceMapFragment;
                GeoFenceBody geoFenceBody;
                GoogleMap googleMap3;
                GoogleMap googleMap4;
                GeoFenceActivity geoFenceActivity;
                boolean z3;
                GetGeoFenceResponseItem geoFenceResponse;
                Intrinsics.checkNotNullParameter(msg, "msg");
                try {
                    Object obj = msg.obj;
                    if (obj == null) {
                        throw new NullPointerException("null cannot be cast to non-null type org.w3c.dom.Document");
                    }
                    ArrayList<LatLng> direction = new GMapV2Direction().getDirection((Document) obj);
                    PolylineOptions color = new PolylineOptions().width(10.0f).color(i2);
                    Intrinsics.checkNotNullExpressionValue(color, "PolylineOptions()\n      …            .color(color)");
                    ArrayList arrayList = new ArrayList();
                    color.add(latLng);
                    int size = direction.size();
                    for (int i3 = 0; i3 < size; i3++) {
                        color.add(direction.get(i3));
                        arrayList.add(direction.get(i3));
                    }
                    color.add(latLng2);
                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        builder.include((LatLng) it.next());
                    }
                    LatLngBounds build = builder.build();
                    Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
                    double d4 = 100;
                    LatLng computeOffset = SphericalUtil.computeOffset(build.northeast, d4 * Math.sqrt(2.0d), 45.0d);
                    LatLng computeOffset2 = SphericalUtil.computeOffset(build.southwest, d4 * Math.sqrt(2.0d), 225.0d);
                    builder.include(computeOffset);
                    builder.include(computeOffset2);
                    LatLngBounds build2 = builder.build();
                    Intrinsics.checkNotNullExpressionValue(build2, "builder.build()");
                    viewModel = this.getViewModel();
                    GeoFenceCommonModel geoFenceCommonModel = viewModel.getGeoFenceCommonModel();
                    if (geoFenceCommonModel != null && geoFenceCommonModel.getFenceCreationMode() == 6) {
                        geoFenceActivity = this.parentActivity;
                        List<LocationData> coordinates = (geoFenceActivity == null || (geoFenceResponse = geoFenceActivity.getGeoFenceResponse()) == null) ? null : geoFenceResponse.getCoordinates();
                        if (coordinates != null && !coordinates.isEmpty()) {
                            z3 = false;
                            if (z3) {
                                this.drawRouteBounds();
                            } else {
                                geoFenceMapFragment = this;
                                geoFenceMapFragment.drawBounds(build2);
                            }
                        }
                        z3 = true;
                        if (z3) {
                        }
                    } else {
                        viewModel2 = this.getViewModel();
                        GeoFenceCommonModel geoFenceCommonModel2 = viewModel2.getGeoFenceCommonModel();
                        ArrayList<Coordinates> coordinates2 = (geoFenceCommonModel2 == null || (geoFenceBody = geoFenceCommonModel2.getGeoFenceBody()) == null) ? null : geoFenceBody.getCoordinates();
                        if (coordinates2 != null && !coordinates2.isEmpty()) {
                            z2 = false;
                            if (z2) {
                                this.drawRouteBoundsForSet();
                            } else {
                                geoFenceMapFragment = this;
                                geoFenceMapFragment.drawBounds(build2);
                            }
                        }
                        z2 = true;
                        if (z2) {
                        }
                    }
                    googleMap3 = this.googleMap;
                    if (googleMap3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                        googleMap3 = null;
                    }
                    googleMap3.addPolyline(color);
                    LatLngBounds.Builder builder2 = new LatLngBounds.Builder();
                    builder2.include(latLng);
                    builder2.include(latLng2);
                    googleMap4 = this.googleMap;
                    if (googleMap4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                        googleMap4 = null;
                    }
                    googleMap4.moveCamera(CameraUpdateFactory.newLatLngBounds(builder2.build(), 200));
                    AppUtil.Companion.dismissDialog();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    this.drawCarMarker();
                    googleMap = this.googleMap;
                    if (googleMap == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                        googleMap2 = null;
                    } else {
                        googleMap2 = googleMap;
                    }
                    d2 = this.carLatitude;
                    d3 = this.carLongitude;
                    MapExtensionKt.moveCameraOnMap(googleMap2, 10.0f, new LatLng(d2, d3), true);
                }
            }
        }, latLng, latLng2, new ArrayList(), GMapV2Direction.MODE_DRIVING).getDistanceFromGoogle();
    }

    private final void setCarAddressText() {
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.carAddress = companion.getAddressNameString(requireContext, this.carLatitude, this.carLongitude);
    }

    private final void setDataForCustomFence() {
        GeoFenceBody geoFenceBody;
        ArrayList<Coordinates> coordinates;
        GeoFenceBody geoFenceBody2;
        ArrayList<Coordinates> coordinates2;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        GeoFenceBody geoFenceBody3 = geoFenceCommonModel != null ? geoFenceCommonModel.getGeoFenceBody() : null;
        if (geoFenceBody3 != null) {
            geoFenceBody3.setSourceLocation(new Coordinates(Double.valueOf(this.sourceLatitude), Double.valueOf(this.sourceLongitude)));
        }
        GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
        GeoFenceBody geoFenceBody4 = geoFenceCommonModel2 != null ? geoFenceCommonModel2.getGeoFenceBody() : null;
        if (geoFenceBody4 != null) {
            geoFenceBody4.setDestinationLocation(new Coordinates(Double.valueOf(this.sourceLatitude), Double.valueOf(this.sourceLongitude)));
        }
        GeoFenceCommonModel geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
        GeoFenceBody geoFenceBody5 = geoFenceCommonModel3 != null ? geoFenceCommonModel3.getGeoFenceBody() : null;
        if (geoFenceBody5 != null) {
            geoFenceBody5.setCoordinates(new ArrayList<>());
        }
        for (MapPolygonData mapPolygonData : this.polygonDataList) {
            GeoFenceCommonModel geoFenceCommonModel4 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel4 != null && (geoFenceBody2 = geoFenceCommonModel4.getGeoFenceBody()) != null && (coordinates2 = geoFenceBody2.getCoordinates()) != null) {
                coordinates2.add(new Coordinates(Double.valueOf(mapPolygonData.getLatitude()), Double.valueOf(mapPolygonData.getLongitude())));
            }
        }
        GeoFenceCommonModel geoFenceCommonModel5 = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel5 == null || (geoFenceBody = geoFenceCommonModel5.getGeoFenceBody()) == null || (coordinates = geoFenceBody.getCoordinates()) == null) {
            return;
        }
        coordinates.add(new Coordinates(Double.valueOf(this.polygonDataList.get(0).getLatitude()), Double.valueOf(this.polygonDataList.get(0).getLongitude())));
    }

    private final void setDataForRadiusFence() {
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        GeoFenceBody geoFenceBody = geoFenceCommonModel != null ? geoFenceCommonModel.getGeoFenceBody() : null;
        if (geoFenceBody == null) {
            return;
        }
        geoFenceBody.setCentre(new Coordinates(Double.valueOf(this.sourceLatitude), Double.valueOf(this.sourceLongitude)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v28, types: [java.util.ArrayList] */
    private final void setDataForRouteFence() {
        Object locationData;
        GetGeoFenceResponseItem geoFenceGetResponse;
        GetGeoFenceResponseItem geoFenceGetResponse2;
        GeoFenceCommonModel geoFenceCommonModel;
        GeoFenceBody geoFenceBody;
        GeoFenceBody geoFenceBody2;
        ArrayList<Coordinates> coordinates;
        GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel2 != null && geoFenceCommonModel2.getFenceCreationMode() == 6) {
            GeoFenceCommonModel geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
            GetGeoFenceResponseItem geoFenceGetResponse3 = geoFenceCommonModel3 != null ? geoFenceCommonModel3.getGeoFenceGetResponse() : null;
            if (geoFenceGetResponse3 != null) {
                geoFenceGetResponse3.setCoordinates(new ArrayList());
            }
            for (MapPolygonData mapPolygonData : this.polygonDataList) {
                GeoFenceCommonModel geoFenceCommonModel4 = getViewModel().getGeoFenceCommonModel();
                List<LocationData> coordinates2 = (geoFenceCommonModel4 == null || (geoFenceGetResponse2 = geoFenceCommonModel4.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse2.getCoordinates();
                Objects.requireNonNull(coordinates2, "null cannot be cast to non-null type java.util.ArrayList<com.psa.mym.mycitroenconnect.model.geo_fence.LocationData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.psa.mym.mycitroenconnect.model.geo_fence.LocationData> }");
                ((ArrayList) coordinates2).add(new LocationData(Double.valueOf(mapPolygonData.getLatitude()), Double.valueOf(mapPolygonData.getLongitude())));
            }
            if (this.polygonDataList.size() > 0) {
                GeoFenceCommonModel geoFenceCommonModel5 = getViewModel().getGeoFenceCommonModel();
                List<LocationData> coordinates3 = (geoFenceCommonModel5 == null || (geoFenceGetResponse = geoFenceCommonModel5.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse.getCoordinates();
                Objects.requireNonNull(coordinates3, "null cannot be cast to non-null type java.util.ArrayList<com.psa.mym.mycitroenconnect.model.geo_fence.LocationData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.psa.mym.mycitroenconnect.model.geo_fence.LocationData> }");
                ArrayList<Coordinates> arrayList = (ArrayList) coordinates3;
                locationData = new LocationData(Double.valueOf(this.polygonDataList.get(0).getLatitude()), Double.valueOf(this.polygonDataList.get(0).getLongitude()));
                arrayList.add(locationData);
            }
        } else {
            GeoFenceCommonModel geoFenceCommonModel6 = getViewModel().getGeoFenceCommonModel();
            GeoFenceBody geoFenceBody3 = geoFenceCommonModel6 != null ? geoFenceCommonModel6.getGeoFenceBody() : null;
            if (geoFenceBody3 != null) {
                geoFenceBody3.setCoordinates(new ArrayList<>());
            }
            for (MapPolygonData mapPolygonData2 : this.polygonDataList) {
                GeoFenceCommonModel geoFenceCommonModel7 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel7 != null && (geoFenceBody2 = geoFenceCommonModel7.getGeoFenceBody()) != null && (coordinates = geoFenceBody2.getCoordinates()) != null) {
                    coordinates.add(new Coordinates(Double.valueOf(mapPolygonData2.getLatitude()), Double.valueOf(mapPolygonData2.getLongitude())));
                }
            }
            if (this.polygonDataList.size() > 0 && (geoFenceCommonModel = getViewModel().getGeoFenceCommonModel()) != null && (geoFenceBody = geoFenceCommonModel.getGeoFenceBody()) != null && (arrayList = geoFenceBody.getCoordinates()) != null) {
                locationData = new Coordinates(Double.valueOf(this.polygonDataList.get(0).getLatitude()), Double.valueOf(this.polygonDataList.get(0).getLongitude()));
                arrayList.add(locationData);
            }
        }
        GeoFenceCommonModel geoFenceCommonModel8 = getViewModel().getGeoFenceCommonModel();
        GeoFenceBody geoFenceBody4 = geoFenceCommonModel8 != null ? geoFenceCommonModel8.getGeoFenceBody() : null;
        if (geoFenceBody4 != null) {
            geoFenceBody4.setSourceLocation(new Coordinates(Double.valueOf(this.sourceLatitude), Double.valueOf(this.sourceLongitude)));
        }
        GeoFenceCommonModel geoFenceCommonModel9 = getViewModel().getGeoFenceCommonModel();
        GeoFenceBody geoFenceBody5 = geoFenceCommonModel9 != null ? geoFenceCommonModel9.getGeoFenceBody() : null;
        if (geoFenceBody5 == null) {
            return;
        }
        geoFenceBody5.setDestinationLocation(new Coordinates(Double.valueOf(this.destinationLatitude), Double.valueOf(this.destinationLongitude)));
    }

    private final void setDestinationAddressText() {
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.destinationAddress = companion.getAddressNameString(requireContext, this.destinationLatitude, this.destinationLongitude);
    }

    private final void setListeners() {
        ((MaterialCardView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.cvSource)).setOnClickListener(this);
        ((LinearLayoutCompat) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.llSource)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivSource)).setOnClickListener(this);
        ((AppCompatEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edtSource)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnBack)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnConfirm)).setOnClickListener(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void setMapData() {
        LatLng latLng;
        GoogleMap googleMap = this.googleMap;
        if (googleMap == null) {
            return;
        }
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            googleMap = null;
        }
        String str = this.fenceType;
        int hashCode = str.hashCode();
        if (hashCode == -1349088399) {
            if (str.equals(AppConstants.GEO_FENCE_MODE_CUSTOM)) {
                MapExtensionKt.moveCameraOnMap(googleMap, 10.0f, new LatLng(this.carLatitude, this.carLongitude), true);
                if (!(this.sourceLatitude == 0.0d)) {
                    if (!(this.sourceLongitude == 0.0d)) {
                        drawSourceMarker();
                        MapExtensionKt.moveCameraOnMap(googleMap, 10.0f, new LatLng(this.sourceLatitude, this.sourceLongitude), true);
                        GeoFenceActivity geoFenceActivity = this.parentActivity;
                        if ((geoFenceActivity != null ? geoFenceActivity.getGeoFenceResponse() : null) != null) {
                            displayCustomFence(true);
                            return;
                        }
                        displayCustomFence(false);
                    }
                }
                this.sourceLatitude = this.carLatitude;
                this.sourceLongitude = this.carLongitude;
                this.sourceAddress = this.carAddress;
                ((AppCompatEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edtSource)).setText(this.sourceAddress);
                displayCustomFence(false);
            }
        } else if (hashCode != -938578798) {
            if (hashCode == 108704329 && str.equals(AppConstants.GEO_FENCE_MODE_ROUTE)) {
                MapExtensionKt.moveCameraOnMap(googleMap, 10.0f, new LatLng(this.carLatitude, this.carLongitude), true);
                GeoFenceActivity geoFenceActivity2 = this.parentActivity;
                if ((geoFenceActivity2 != null ? geoFenceActivity2.getGeoFenceResponse() : null) != null) {
                    displayRouteFence(false);
                } else {
                    displayRouteFence(true);
                }
            }
        } else if (!str.equals(AppConstants.GEO_FENCE_MODE_RADIUS)) {
        } else {
            if (!(this.sourceLatitude == 0.0d)) {
                if (!(this.sourceLongitude == 0.0d)) {
                    drawSourceMarker();
                    latLng = new LatLng(this.sourceLatitude, this.sourceLongitude);
                    MapExtensionKt.moveCameraOnMap(googleMap, 10.0f, latLng, true);
                    displayRadiusFence();
                    if (this.radius == 0.0d) {
                        return;
                    }
                    this.radius = 10.0d;
                    return;
                }
            }
            latLng = new LatLng(this.carLatitude, this.carLongitude);
            MapExtensionKt.moveCameraOnMap(googleMap, 10.0f, latLng, true);
            displayRadiusFence();
            if (this.radius == 0.0d) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:118:0x01e0, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r0, (r1 == null || (r1 = r1.getGeoFenceGetResponse()) == null || (r1 = r1.getDestinationLocation()) == null) ? null : r1.getGpsLong()) == false) goto L191;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00ee, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r0, (r1 == null || (r1 = r1.getGeoFenceGetResponse()) == null || (r1 = r1.getSourceLocation()) == null) ? null : r1.getGpsLong()) == false) goto L209;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0371  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x03a9  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x03c0  */
    /* JADX WARN: Type inference failed for: r0v100 */
    /* JADX WARN: Type inference failed for: r0v164 */
    /* JADX WARN: Type inference failed for: r0v165 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v176 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v27 */
    /* JADX WARN: Type inference failed for: r0v42 */
    /* JADX WARN: Type inference failed for: r0v43 */
    /* JADX WARN: Type inference failed for: r0v52 */
    /* JADX WARN: Type inference failed for: r0v90 */
    /* JADX WARN: Type inference failed for: r0v91 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void setModelData() {
        ArrayList<LatLng> arrayList;
        GeoFenceBody geoFenceBody;
        GeoFenceBody geoFenceBody2;
        GeoFenceBody geoFenceBody3;
        GeoFenceBody geoFenceBody4;
        Coordinates sourceLocation;
        ArrayList<LatLng> arrayList2;
        GetGeoFenceResponseItem geoFenceGetResponse;
        GetGeoFenceResponseItem geoFenceGetResponse2;
        GetGeoFenceResponseItem geoFenceGetResponse3;
        GetGeoFenceResponseItem geoFenceGetResponse4;
        LocationData sourceLocation2;
        GeoFenceBody geoFenceBody5;
        Double radius;
        float doubleValue;
        GeoFenceBody geoFenceBody6;
        Coordinates centre;
        GetGeoFenceResponseItem geoFenceGetResponse5;
        Integer radius2;
        GetGeoFenceResponseItem geoFenceGetResponse6;
        LocationData centre2;
        ArrayList<LatLng> arrayList3;
        GeoFenceBody geoFenceBody7;
        GeoFenceBody geoFenceBody8;
        GeoFenceBody geoFenceBody9;
        GeoFenceBody geoFenceBody10;
        Coordinates destinationLocation;
        GeoFenceBody geoFenceBody11;
        Coordinates sourceLocation3;
        GetGeoFenceResponseItem geoFenceGetResponse7;
        LocationData sourceLocation4;
        Double gpsLong;
        GeoFenceBody geoFenceBody12;
        Double gpsLat;
        GeoFenceCommonModel geoFenceCommonModel;
        GeoFenceCommonModel geoFenceCommonModel2;
        GetGeoFenceResponseItem geoFenceGetResponse8;
        LocationData destinationLocation2;
        Double gpsLong2;
        GeoFenceBody geoFenceBody13;
        ArrayList<Coordinates> coordinates;
        GeoFenceCommonModel geoFenceCommonModel3;
        GeoFenceCommonModel geoFenceCommonModel4;
        ArrayList<LatLng> arrayList4;
        GeoFenceBody geoFenceBody14;
        GeoFenceBody geoFenceBody15;
        ArrayList<LatLng> arrayList5;
        GetGeoFenceResponseItem geoFenceGetResponse9;
        GetGeoFenceResponseItem geoFenceGetResponse10;
        GeoFenceBody geoFenceBody16;
        ArrayList<Coordinates> coordinates2;
        GeoFenceBody geoFenceBody17;
        GetGeoFenceResponseItem geoFenceGetResponse11;
        GeoFenceBody geoFenceBody18;
        GeoFenceCommonModel geoFenceCommonModel5;
        GeoFenceBody geoFenceBody19;
        Coordinates destinationLocation3;
        GeoFenceBody geoFenceBody20;
        Coordinates destinationLocation4;
        GetGeoFenceResponseItem geoFenceGetResponse12;
        LocationData destinationLocation5;
        GeoFenceBody geoFenceBody21;
        Coordinates destinationLocation6;
        GeoFenceBody geoFenceBody22;
        Coordinates sourceLocation5;
        GeoFenceBody geoFenceBody23;
        Coordinates sourceLocation6;
        GetGeoFenceResponseItem geoFenceGetResponse13;
        LocationData sourceLocation7;
        GeoFenceBody geoFenceBody24;
        Coordinates sourceLocation8;
        LatLng carLatLng;
        GeoFenceCommonModel geoFenceCommonModel6 = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel6 != null && (carLatLng = geoFenceCommonModel6.getCarLatLng()) != null) {
            this.carLatitude = carLatLng.latitude;
            this.carLongitude = carLatLng.longitude;
            setCarAddressText();
            Unit unit = Unit.INSTANCE;
        }
        String str = this.fenceType;
        int hashCode = str.hashCode();
        int i2 = 0;
        ArrayList<Coordinates> arrayList6 = null;
        r9 = null;
        List<LocationData> list = null;
        r9 = null;
        ArrayList<Coordinates> arrayList7 = null;
        r9 = null;
        ArrayList<Coordinates> arrayList8 = null;
        r9 = null;
        List<LocationData> list2 = null;
        arrayList6 = null;
        if (hashCode == -1349088399) {
            if (str.equals(AppConstants.GEO_FENCE_MODE_CUSTOM)) {
                MaterialCardView cvSource = (MaterialCardView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.cvSource);
                Intrinsics.checkNotNullExpressionValue(cvSource, "cvSource");
                ExtensionsKt.show(cvSource);
                GeoFenceCommonModel geoFenceCommonModel7 = getViewModel().getGeoFenceCommonModel();
                Integer valueOf = geoFenceCommonModel7 != null ? Integer.valueOf(geoFenceCommonModel7.getFenceCreationMode()) : null;
                if (valueOf != null && valueOf.intValue() == 6) {
                    GeoFenceCommonModel geoFenceCommonModel8 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel8 != null && (geoFenceGetResponse4 = geoFenceCommonModel8.getGeoFenceGetResponse()) != null && (sourceLocation2 = geoFenceGetResponse4.getSourceLocation()) != null) {
                        if (sourceLocation2.getGpsLat() != null && sourceLocation2.getGpsLong() != null) {
                            Double gpsLat2 = sourceLocation2.getGpsLat();
                            Intrinsics.checkNotNull(gpsLat2);
                            this.sourceLatitude = gpsLat2.doubleValue();
                            Double gpsLong3 = sourceLocation2.getGpsLong();
                            Intrinsics.checkNotNull(gpsLong3);
                            this.sourceLongitude = gpsLong3.doubleValue();
                            setSourceAddressText();
                        }
                        Unit unit2 = Unit.INSTANCE;
                    }
                    GeoFenceCommonModel geoFenceCommonModel9 = getViewModel().getGeoFenceCommonModel();
                    List<LocationData> coordinates3 = (geoFenceCommonModel9 == null || (geoFenceGetResponse3 = geoFenceCommonModel9.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse3.getCoordinates();
                    if ((coordinates3 == null || coordinates3.isEmpty()) == true) {
                        return;
                    }
                    GeoFenceCommonModel geoFenceCommonModel10 = getViewModel().getGeoFenceCommonModel();
                    List<LocationData> coordinates4 = (geoFenceCommonModel10 == null || (geoFenceGetResponse2 = geoFenceCommonModel10.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse2.getCoordinates();
                    Intrinsics.checkNotNull(coordinates4);
                    int size = coordinates4.size();
                    Logger.INSTANCE.e("size: " + size);
                    this.latLngList = new ArrayList<>();
                    GeoFenceCommonModel geoFenceCommonModel11 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel11 != null && (geoFenceGetResponse = geoFenceCommonModel11.getGeoFenceGetResponse()) != null) {
                        list2 = geoFenceGetResponse.getCoordinates();
                    }
                    Intrinsics.checkNotNull(list2);
                    for (Object obj : list2) {
                        int i3 = i2 + 1;
                        if (i2 < 0) {
                            CollectionsKt__CollectionsKt.throwIndexOverflow();
                        }
                        LocationData locationData = (LocationData) obj;
                        Logger.INSTANCE.e("Index: " + i2);
                        if (i2 != size - 1 && (arrayList2 = this.latLngList) != null) {
                            Double gpsLat3 = locationData.getGpsLat();
                            Intrinsics.checkNotNull(gpsLat3);
                            double doubleValue2 = gpsLat3.doubleValue();
                            Double gpsLong4 = locationData.getGpsLong();
                            Intrinsics.checkNotNull(gpsLong4);
                            arrayList2.add(new LatLng(doubleValue2, gpsLong4.doubleValue()));
                        }
                        i2 = i3;
                    }
                    return;
                }
                GeoFenceCommonModel geoFenceCommonModel12 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel12 != null && (geoFenceBody4 = geoFenceCommonModel12.getGeoFenceBody()) != null && (sourceLocation = geoFenceBody4.getSourceLocation()) != null) {
                    if (sourceLocation.getGpsLat() != null && sourceLocation.getGpsLong() != null) {
                        Double gpsLat4 = sourceLocation.getGpsLat();
                        Intrinsics.checkNotNull(gpsLat4);
                        this.sourceLatitude = gpsLat4.doubleValue();
                        Double gpsLong5 = sourceLocation.getGpsLong();
                        Intrinsics.checkNotNull(gpsLong5);
                        this.sourceLongitude = gpsLong5.doubleValue();
                        setSourceAddressText();
                    }
                    Unit unit3 = Unit.INSTANCE;
                }
                GeoFenceCommonModel geoFenceCommonModel13 = getViewModel().getGeoFenceCommonModel();
                ArrayList<Coordinates> coordinates5 = (geoFenceCommonModel13 == null || (geoFenceBody3 = geoFenceCommonModel13.getGeoFenceBody()) == null) ? null : geoFenceBody3.getCoordinates();
                if ((coordinates5 == null || coordinates5.isEmpty()) == true) {
                    return;
                }
                GeoFenceCommonModel geoFenceCommonModel14 = getViewModel().getGeoFenceCommonModel();
                ArrayList<Coordinates> coordinates6 = (geoFenceCommonModel14 == null || (geoFenceBody2 = geoFenceCommonModel14.getGeoFenceBody()) == null) ? null : geoFenceBody2.getCoordinates();
                Intrinsics.checkNotNull(coordinates6);
                int size2 = coordinates6.size();
                Logger.INSTANCE.e("size: " + size2);
                this.latLngList = new ArrayList<>();
                GeoFenceCommonModel geoFenceCommonModel15 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel15 != null && (geoFenceBody = geoFenceCommonModel15.getGeoFenceBody()) != null) {
                    arrayList6 = geoFenceBody.getCoordinates();
                }
                Intrinsics.checkNotNull(arrayList6);
                for (Object obj2 : arrayList6) {
                    int i4 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    Coordinates coordinates7 = (Coordinates) obj2;
                    Logger.INSTANCE.e("Index: " + i2);
                    if (i2 != size2 - 1 && (arrayList = this.latLngList) != null) {
                        Double gpsLat5 = coordinates7.getGpsLat();
                        Intrinsics.checkNotNull(gpsLat5);
                        double doubleValue3 = gpsLat5.doubleValue();
                        Double gpsLong6 = coordinates7.getGpsLong();
                        Intrinsics.checkNotNull(gpsLong6);
                        arrayList.add(new LatLng(doubleValue3, gpsLong6.doubleValue()));
                    }
                    i2 = i4;
                }
            }
        } else if (hashCode == -938578798) {
            if (str.equals(AppConstants.GEO_FENCE_MODE_RADIUS)) {
                MaterialCardView cvSource2 = (MaterialCardView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.cvSource);
                Intrinsics.checkNotNullExpressionValue(cvSource2, "cvSource");
                ExtensionsKt.show(cvSource2);
                GeoFenceCommonModel geoFenceCommonModel16 = getViewModel().getGeoFenceCommonModel();
                Integer valueOf2 = geoFenceCommonModel16 != null ? Integer.valueOf(geoFenceCommonModel16.getFenceCreationMode()) : null;
                if (valueOf2 != null && valueOf2.intValue() == 6) {
                    GeoFenceCommonModel geoFenceCommonModel17 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel17 != null && (geoFenceGetResponse6 = geoFenceCommonModel17.getGeoFenceGetResponse()) != null && (centre2 = geoFenceGetResponse6.getCentre()) != null) {
                        if (centre2.getGpsLat() != null && centre2.getGpsLong() != null) {
                            Double gpsLat6 = centre2.getGpsLat();
                            Intrinsics.checkNotNull(gpsLat6);
                            this.sourceLatitude = gpsLat6.doubleValue();
                            Double gpsLong7 = centre2.getGpsLong();
                            Intrinsics.checkNotNull(gpsLong7);
                            this.sourceLongitude = gpsLong7.doubleValue();
                            setSourceAddressText();
                        }
                        Unit unit4 = Unit.INSTANCE;
                    }
                    GeoFenceCommonModel geoFenceCommonModel18 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel18 == null || (geoFenceGetResponse5 = geoFenceCommonModel18.getGeoFenceGetResponse()) == null || (radius2 = geoFenceGetResponse5.getRadius()) == null) {
                        return;
                    }
                    doubleValue = radius2.intValue();
                } else {
                    GeoFenceCommonModel geoFenceCommonModel19 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel19 != null && (geoFenceBody6 = geoFenceCommonModel19.getGeoFenceBody()) != null && (centre = geoFenceBody6.getCentre()) != null) {
                        if (centre.getGpsLat() != null && centre.getGpsLong() != null) {
                            Double gpsLat7 = centre.getGpsLat();
                            Intrinsics.checkNotNull(gpsLat7);
                            this.sourceLatitude = gpsLat7.doubleValue();
                            Double gpsLong8 = centre.getGpsLong();
                            Intrinsics.checkNotNull(gpsLong8);
                            this.sourceLongitude = gpsLong8.doubleValue();
                            setSourceAddressText();
                        }
                        Unit unit5 = Unit.INSTANCE;
                    }
                    GeoFenceCommonModel geoFenceCommonModel20 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel20 == null || (geoFenceBody5 = geoFenceCommonModel20.getGeoFenceBody()) == null || (radius = geoFenceBody5.getRadius()) == null) {
                        return;
                    }
                    doubleValue = (float) radius.doubleValue();
                }
                this.radius = (int) (doubleValue / 1000);
                Unit unit6 = Unit.INSTANCE;
            }
        } else if (hashCode == 108704329 && str.equals(AppConstants.GEO_FENCE_MODE_ROUTE)) {
            MaterialCardView cvSource3 = (MaterialCardView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.cvSource);
            Intrinsics.checkNotNullExpressionValue(cvSource3, "cvSource");
            ExtensionsKt.hide(cvSource3);
            GeoFenceCommonModel geoFenceCommonModel21 = getViewModel().getGeoFenceCommonModel();
            Integer valueOf3 = geoFenceCommonModel21 != null ? Integer.valueOf(geoFenceCommonModel21.getFenceCreationMode()) : null;
            if (valueOf3 == null || valueOf3.intValue() != 6) {
                GeoFenceCommonModel geoFenceCommonModel22 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel22 != null && (geoFenceBody11 = geoFenceCommonModel22.getGeoFenceBody()) != null && (sourceLocation3 = geoFenceBody11.getSourceLocation()) != null) {
                    Double gpsLat8 = sourceLocation3.getGpsLat();
                    Intrinsics.checkNotNull(gpsLat8);
                    this.sourceLatitude = gpsLat8.doubleValue();
                    Double gpsLong9 = sourceLocation3.getGpsLong();
                    Intrinsics.checkNotNull(gpsLong9);
                    this.sourceLongitude = gpsLong9.doubleValue();
                    setSourceAddressText();
                    Unit unit7 = Unit.INSTANCE;
                }
                GeoFenceCommonModel geoFenceCommonModel23 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel23 != null && (geoFenceBody10 = geoFenceCommonModel23.getGeoFenceBody()) != null && (destinationLocation = geoFenceBody10.getDestinationLocation()) != null) {
                    Double gpsLat9 = destinationLocation.getGpsLat();
                    Intrinsics.checkNotNull(gpsLat9);
                    this.destinationLatitude = gpsLat9.doubleValue();
                    Double gpsLong10 = destinationLocation.getGpsLong();
                    Intrinsics.checkNotNull(gpsLong10);
                    this.destinationLongitude = gpsLong10.doubleValue();
                    setDestinationAddressText();
                    Unit unit8 = Unit.INSTANCE;
                }
                GeoFenceCommonModel geoFenceCommonModel24 = getViewModel().getGeoFenceCommonModel();
                ArrayList<Coordinates> coordinates8 = (geoFenceCommonModel24 == null || (geoFenceBody9 = geoFenceCommonModel24.getGeoFenceBody()) == null) ? null : geoFenceBody9.getCoordinates();
                if ((coordinates8 == null || coordinates8.isEmpty()) == true) {
                    return;
                }
                GeoFenceCommonModel geoFenceCommonModel25 = getViewModel().getGeoFenceCommonModel();
                ArrayList<Coordinates> coordinates9 = (geoFenceCommonModel25 == null || (geoFenceBody8 = geoFenceCommonModel25.getGeoFenceBody()) == null) ? null : geoFenceBody8.getCoordinates();
                Intrinsics.checkNotNull(coordinates9);
                int size3 = coordinates9.size();
                Logger.INSTANCE.e("size: " + size3);
                this.latLngList = new ArrayList<>();
                GeoFenceCommonModel geoFenceCommonModel26 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel26 != null && (geoFenceBody7 = geoFenceCommonModel26.getGeoFenceBody()) != null) {
                    arrayList8 = geoFenceBody7.getCoordinates();
                }
                Intrinsics.checkNotNull(arrayList8);
                for (Object obj3 : arrayList8) {
                    int i5 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    Coordinates coordinates10 = (Coordinates) obj3;
                    Logger.INSTANCE.e("Index: " + i2);
                    if (i2 != size3 - 1 && (arrayList3 = this.latLngList) != null) {
                        Double gpsLat10 = coordinates10.getGpsLat();
                        Intrinsics.checkNotNull(gpsLat10);
                        double doubleValue4 = gpsLat10.doubleValue();
                        Double gpsLong11 = coordinates10.getGpsLong();
                        Intrinsics.checkNotNull(gpsLong11);
                        arrayList3.add(new LatLng(doubleValue4, gpsLong11.doubleValue()));
                    }
                    i2 = i5;
                }
                return;
            }
            GeoFenceCommonModel geoFenceCommonModel27 = getViewModel().getGeoFenceCommonModel();
            Double gpsLat11 = (geoFenceCommonModel27 == null || (geoFenceBody24 = geoFenceCommonModel27.getGeoFenceBody()) == null || (sourceLocation8 = geoFenceBody24.getSourceLocation()) == null) ? null : sourceLocation8.getGpsLat();
            GeoFenceCommonModel geoFenceCommonModel28 = getViewModel().getGeoFenceCommonModel();
            if (Intrinsics.areEqual(gpsLat11, (geoFenceCommonModel28 == null || (geoFenceGetResponse13 = geoFenceCommonModel28.getGeoFenceGetResponse()) == null || (sourceLocation7 = geoFenceGetResponse13.getSourceLocation()) == null) ? null : sourceLocation7.getGpsLat())) {
                GeoFenceCommonModel geoFenceCommonModel29 = getViewModel().getGeoFenceCommonModel();
                Double gpsLong12 = (geoFenceCommonModel29 == null || (geoFenceBody23 = geoFenceCommonModel29.getGeoFenceBody()) == null || (sourceLocation6 = geoFenceBody23.getSourceLocation()) == null) ? null : sourceLocation6.getGpsLong();
                GeoFenceCommonModel geoFenceCommonModel30 = getViewModel().getGeoFenceCommonModel();
            }
            GeoFenceCommonModel geoFenceCommonModel31 = getViewModel().getGeoFenceCommonModel();
            if (((geoFenceCommonModel31 == null || (geoFenceBody12 = geoFenceCommonModel31.getGeoFenceBody()) == null) ? null : geoFenceBody12.getSourceLocation()) == null) {
                GeoFenceCommonModel geoFenceCommonModel32 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel32 != null && (geoFenceGetResponse7 = geoFenceCommonModel32.getGeoFenceGetResponse()) != null && (sourceLocation4 = geoFenceGetResponse7.getSourceLocation()) != null) {
                    Double gpsLat12 = sourceLocation4.getGpsLat();
                    Intrinsics.checkNotNull(gpsLat12);
                    this.sourceLatitude = gpsLat12.doubleValue();
                    gpsLong = sourceLocation4.getGpsLong();
                    Intrinsics.checkNotNull(gpsLong);
                    this.sourceLongitude = gpsLong.doubleValue();
                    Unit unit9 = Unit.INSTANCE;
                }
                setSourceAddressText();
                GeoFenceCommonModel geoFenceCommonModel33 = getViewModel().getGeoFenceCommonModel();
                gpsLat = (geoFenceCommonModel33 != null || (geoFenceBody21 = geoFenceCommonModel33.getGeoFenceBody()) == null || (destinationLocation6 = geoFenceBody21.getDestinationLocation()) == null) ? null : destinationLocation6.getGpsLat();
                geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
                if (Intrinsics.areEqual(gpsLat, (geoFenceCommonModel != null || (geoFenceGetResponse12 = geoFenceCommonModel.getGeoFenceGetResponse()) == null || (destinationLocation5 = geoFenceGetResponse12.getDestinationLocation()) == null) ? null : destinationLocation5.getGpsLat())) {
                    GeoFenceCommonModel geoFenceCommonModel34 = getViewModel().getGeoFenceCommonModel();
                    Double gpsLong13 = (geoFenceCommonModel34 == null || (geoFenceBody20 = geoFenceCommonModel34.getGeoFenceBody()) == null || (destinationLocation4 = geoFenceBody20.getDestinationLocation()) == null) ? null : destinationLocation4.getGpsLong();
                    GeoFenceCommonModel geoFenceCommonModel35 = getViewModel().getGeoFenceCommonModel();
                }
                geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
                if (((geoFenceCommonModel2 != null || (geoFenceBody13 = geoFenceCommonModel2.getGeoFenceBody()) == null) ? null : geoFenceBody13.getDestinationLocation()) == null) {
                    GeoFenceCommonModel geoFenceCommonModel36 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel36 != null && (geoFenceGetResponse8 = geoFenceCommonModel36.getGeoFenceGetResponse()) != null && (destinationLocation2 = geoFenceGetResponse8.getDestinationLocation()) != null) {
                        Double gpsLat13 = destinationLocation2.getGpsLat();
                        Intrinsics.checkNotNull(gpsLat13);
                        this.destinationLatitude = gpsLat13.doubleValue();
                        gpsLong2 = destinationLocation2.getGpsLong();
                        Intrinsics.checkNotNull(gpsLong2);
                        this.destinationLongitude = gpsLong2.doubleValue();
                        Unit unit10 = Unit.INSTANCE;
                    }
                    setDestinationAddressText();
                    GeoFenceCommonModel geoFenceCommonModel37 = getViewModel().getGeoFenceCommonModel();
                    coordinates = (geoFenceCommonModel37 != null || (geoFenceBody18 = geoFenceCommonModel37.getGeoFenceBody()) == null) ? null : geoFenceBody18.getCoordinates();
                    geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
                    if (!Intrinsics.areEqual(coordinates, (geoFenceCommonModel3 != null || (geoFenceGetResponse11 = geoFenceCommonModel3.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse11.getCoordinates())) {
                        GeoFenceCommonModel geoFenceCommonModel38 = getViewModel().getGeoFenceCommonModel();
                        if (((geoFenceCommonModel38 == null || (geoFenceBody17 = geoFenceCommonModel38.getGeoFenceBody()) == null) ? null : geoFenceBody17.getCoordinates()) == null) {
                            GeoFenceCommonModel geoFenceCommonModel39 = getViewModel().getGeoFenceCommonModel();
                            if (((geoFenceCommonModel39 == null || (geoFenceBody16 = geoFenceCommonModel39.getGeoFenceBody()) == null || (coordinates2 = geoFenceBody16.getCoordinates()) == null || coordinates2.size() != 0) ? false : true) != false) {
                                GeoFenceCommonModel geoFenceCommonModel40 = getViewModel().getGeoFenceCommonModel();
                                List<LocationData> coordinates11 = (geoFenceCommonModel40 == null || (geoFenceGetResponse10 = geoFenceCommonModel40.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse10.getCoordinates();
                                Intrinsics.checkNotNull(coordinates11);
                                int size4 = coordinates11.size();
                                Logger.INSTANCE.e("size: " + size4);
                                this.latLngList = new ArrayList<>();
                                GeoFenceCommonModel geoFenceCommonModel41 = getViewModel().getGeoFenceCommonModel();
                                if (geoFenceCommonModel41 != null && (geoFenceGetResponse9 = geoFenceCommonModel41.getGeoFenceGetResponse()) != null) {
                                    list = geoFenceGetResponse9.getCoordinates();
                                }
                                Intrinsics.checkNotNull(list);
                                for (Object obj4 : list) {
                                    int i6 = i2 + 1;
                                    if (i2 < 0) {
                                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                                    }
                                    LocationData locationData2 = (LocationData) obj4;
                                    Logger.INSTANCE.e("Index: " + i2);
                                    if (i2 != size4 - 1 && (arrayList5 = this.latLngList) != null) {
                                        Double gpsLat14 = locationData2.getGpsLat();
                                        Intrinsics.checkNotNull(gpsLat14);
                                        double doubleValue5 = gpsLat14.doubleValue();
                                        Double gpsLong14 = locationData2.getGpsLong();
                                        Intrinsics.checkNotNull(gpsLong14);
                                        arrayList5.add(new LatLng(doubleValue5, gpsLong14.doubleValue()));
                                    }
                                    i2 = i6;
                                }
                                return;
                            }
                        }
                    }
                    GeoFenceCommonModel geoFenceCommonModel42 = getViewModel().getGeoFenceCommonModel();
                    ArrayList<Coordinates> coordinates12 = (geoFenceCommonModel42 != null || (geoFenceBody15 = geoFenceCommonModel42.getGeoFenceBody()) == null) ? null : geoFenceBody15.getCoordinates();
                    Intrinsics.checkNotNull(coordinates12);
                    int size5 = coordinates12.size();
                    Logger.INSTANCE.e("size: " + size5);
                    this.latLngList = new ArrayList<>();
                    geoFenceCommonModel4 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel4 != null && (geoFenceBody14 = geoFenceCommonModel4.getGeoFenceBody()) != null) {
                        arrayList7 = geoFenceBody14.getCoordinates();
                    }
                    Intrinsics.checkNotNull(arrayList7);
                    for (Object obj5 : arrayList7) {
                        int i7 = i2 + 1;
                        if (i2 < 0) {
                            CollectionsKt__CollectionsKt.throwIndexOverflow();
                        }
                        Coordinates coordinates13 = (Coordinates) obj5;
                        Logger.INSTANCE.e("Index: " + i2);
                        if (i2 != size5 - 1 && (arrayList4 = this.latLngList) != null) {
                            Double gpsLat15 = coordinates13.getGpsLat();
                            Intrinsics.checkNotNull(gpsLat15);
                            double doubleValue6 = gpsLat15.doubleValue();
                            Double gpsLong15 = coordinates13.getGpsLong();
                            Intrinsics.checkNotNull(gpsLong15);
                            arrayList4.add(new LatLng(doubleValue6, gpsLong15.doubleValue()));
                        }
                        i2 = i7;
                    }
                }
                geoFenceCommonModel5 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel5 != null && (geoFenceBody19 = geoFenceCommonModel5.getGeoFenceBody()) != null && (destinationLocation3 = geoFenceBody19.getDestinationLocation()) != null) {
                    Double gpsLat16 = destinationLocation3.getGpsLat();
                    Intrinsics.checkNotNull(gpsLat16);
                    this.destinationLatitude = gpsLat16.doubleValue();
                    gpsLong2 = destinationLocation3.getGpsLong();
                    Intrinsics.checkNotNull(gpsLong2);
                    this.destinationLongitude = gpsLong2.doubleValue();
                    Unit unit102 = Unit.INSTANCE;
                }
                setDestinationAddressText();
                GeoFenceCommonModel geoFenceCommonModel372 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel372 != null) {
                }
                geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
                if (!Intrinsics.areEqual(coordinates, (geoFenceCommonModel3 != null || (geoFenceGetResponse11 = geoFenceCommonModel3.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse11.getCoordinates())) {
                }
                GeoFenceCommonModel geoFenceCommonModel422 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel422 != null) {
                }
                Intrinsics.checkNotNull(coordinates12);
                int size52 = coordinates12.size();
                Logger.INSTANCE.e("size: " + size52);
                this.latLngList = new ArrayList<>();
                geoFenceCommonModel4 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel4 != null) {
                    arrayList7 = geoFenceBody14.getCoordinates();
                }
                Intrinsics.checkNotNull(arrayList7);
                while (r1.hasNext()) {
                }
            }
            GeoFenceCommonModel geoFenceCommonModel43 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel43 != null && (geoFenceBody22 = geoFenceCommonModel43.getGeoFenceBody()) != null && (sourceLocation5 = geoFenceBody22.getSourceLocation()) != null) {
                Double gpsLat17 = sourceLocation5.getGpsLat();
                Intrinsics.checkNotNull(gpsLat17);
                this.sourceLatitude = gpsLat17.doubleValue();
                gpsLong = sourceLocation5.getGpsLong();
                Intrinsics.checkNotNull(gpsLong);
                this.sourceLongitude = gpsLong.doubleValue();
                Unit unit92 = Unit.INSTANCE;
            }
            setSourceAddressText();
            GeoFenceCommonModel geoFenceCommonModel332 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel332 != null) {
            }
            geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
            if (Intrinsics.areEqual(gpsLat, (geoFenceCommonModel != null || (geoFenceGetResponse12 = geoFenceCommonModel.getGeoFenceGetResponse()) == null || (destinationLocation5 = geoFenceGetResponse12.getDestinationLocation()) == null) ? null : destinationLocation5.getGpsLat())) {
            }
            geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
            if (((geoFenceCommonModel2 != null || (geoFenceBody13 = geoFenceCommonModel2.getGeoFenceBody()) == null) ? null : geoFenceBody13.getDestinationLocation()) == null) {
            }
            geoFenceCommonModel5 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel5 != null) {
                Double gpsLat162 = destinationLocation3.getGpsLat();
                Intrinsics.checkNotNull(gpsLat162);
                this.destinationLatitude = gpsLat162.doubleValue();
                gpsLong2 = destinationLocation3.getGpsLong();
                Intrinsics.checkNotNull(gpsLong2);
                this.destinationLongitude = gpsLong2.doubleValue();
                Unit unit1022 = Unit.INSTANCE;
            }
            setDestinationAddressText();
            GeoFenceCommonModel geoFenceCommonModel3722 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel3722 != null) {
            }
            geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
            if (!Intrinsics.areEqual(coordinates, (geoFenceCommonModel3 != null || (geoFenceGetResponse11 = geoFenceCommonModel3.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse11.getCoordinates())) {
            }
            GeoFenceCommonModel geoFenceCommonModel4222 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel4222 != null) {
            }
            Intrinsics.checkNotNull(coordinates12);
            int size522 = coordinates12.size();
            Logger.INSTANCE.e("size: " + size522);
            this.latLngList = new ArrayList<>();
            geoFenceCommonModel4 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel4 != null) {
            }
            Intrinsics.checkNotNull(arrayList7);
            while (r1.hasNext()) {
            }
        }
    }

    private final void setSourceAddressText() {
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.sourceAddress = companion.getAddressNameString(requireContext, this.sourceLatitude, this.sourceLongitude);
        ((AppCompatEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edtSource)).setText(this.sourceAddress);
    }

    private final void setUIData() {
        GeoFenceBody geoFenceBody;
        GetGeoFenceResponseItem geoFenceGetResponse;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        String str = null;
        Integer valueOf = geoFenceCommonModel != null ? Integer.valueOf(geoFenceCommonModel.getFenceCreationMode()) : null;
        if (valueOf != null && valueOf.intValue() == 6) {
            GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel2 != null && (geoFenceGetResponse = geoFenceCommonModel2.getGeoFenceGetResponse()) != null) {
                str = geoFenceGetResponse.getFenceMode();
            }
        } else {
            GeoFenceCommonModel geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel3 != null && (geoFenceBody = geoFenceCommonModel3.getGeoFenceBody()) != null) {
                str = geoFenceBody.getFenceMode();
            }
        }
        this.fenceType = String.valueOf(str);
        setModelData();
    }

    private final void updateShapeBasedOnMarker(Marker marker) {
        if (this.polygonDataList.size() > 1) {
            Iterator<MapPolygonData> it = this.polygonDataList.iterator();
            int i2 = 0;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                int i3 = i2 + 1;
                MapPolygonData next = it.next();
                String snippet = marker.getSnippet();
                Intrinsics.checkNotNull(snippet);
                if (Intrinsics.areEqual(snippet, next.getSnippet())) {
                    next.setLatitude(marker.getPosition().latitude);
                    next.setLongitude(marker.getPosition().longitude);
                    LatLng position = marker.getPosition();
                    Intrinsics.checkNotNullExpressionValue(position, "marker.position");
                    next.setLatLng(position);
                    this.polygonDataList.set(i2, next);
                    break;
                }
                i2 = i3;
            }
            ArrayList<LatLng> arrayList = new ArrayList<>();
            for (MapPolygonData mapPolygonData : this.polygonDataList) {
                arrayList.add(mapPolygonData.getLatLng());
            }
            drawPolygon(arrayList);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0027, code lost:
        if (r0.equals(com.psa.mym.mycitroenconnect.common.AppConstants.GEO_FENCE_MODE_RADIUS) != false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0030, code lost:
        if (r0.equals(com.psa.mym.mycitroenconnect.common.AppConstants.GEO_FENCE_MODE_CUSTOM) == false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0034, code lost:
        r0 = com.psa.mym.mycitroenconnect.R.id.edtSource;
        r1 = ((androidx.appcompat.widget.AppCompatEditText) _$_findCachedViewById(r0)).getText();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0040, code lost:
        if (r1 == null) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0046, code lost:
        if (r1.length() != 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0049, code lost:
        r1 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004b, code lost:
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004c, code lost:
        if (r1 != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004e, code lost:
        r0 = ((androidx.appcompat.widget.AppCompatEditText) _$_findCachedViewById(r0)).getText();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0058, code lost:
        if (r0 == null) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005a, code lost:
        r0 = kotlin.text.StringsKt__StringsJVMKt.isBlank(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x005e, code lost:
        if (r0 == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0061, code lost:
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0063, code lost:
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0064, code lost:
        if (r0 == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0066, code lost:
        r0 = requireContext();
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "requireContext()");
        r1 = getString(uat.psa.mym.mycitroenconnect.R.string.select_source_location);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, "getString(R.string.select_source_location)");
        com.psa.mym.mycitroenconnect.utils.ExtensionsKt.showToast(r0, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:?, code lost:
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean validate() {
        String str = this.fenceType;
        int hashCode = str.hashCode();
        if (hashCode != -1349088399) {
            if (hashCode != -938578798) {
                if (hashCode == 108704329 && str.equals(AppConstants.GEO_FENCE_MODE_ROUTE)) {
                    return true;
                }
            }
        }
        return false;
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
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnBack))) {
            GeoFenceActivity geoFenceActivity = this.parentActivity;
            if (geoFenceActivity != null) {
                geoFenceActivity.handleBackClick();
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(view, (MaterialCardView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.cvSource)) ? true : Intrinsics.areEqual(view, (LinearLayoutCompat) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.llSource)) ? true : Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivSource)) ? true : Intrinsics.areEqual(view, (AppCompatEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edtSource))) {
            this.isSourceChange = true;
            this.sourceResultLauncher.launch(new Intent(requireContext(), SearchLocationActivity.class));
        } else if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnConfirm)) && validate()) {
            this.isSourceChange = false;
            navigateToMapSettingsFragment();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getBundleData();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_geo_fence_map, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    @SuppressLint({"PotentialBehaviorOverride"})
    public void onMapReady(@NotNull GoogleMap map) {
        Intrinsics.checkNotNullParameter(map, "map");
        this.googleMap = map;
        if (map == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            map = null;
        }
        UiSettings uiSettings = map.getUiSettings();
        uiSettings.setAllGesturesEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setZoomGesturesEnabled(true);
        uiSettings.setMyLocationButtonEnabled(false);
        uiSettings.setScrollGesturesEnabled(true);
        uiSettings.setIndoorLevelPickerEnabled(false);
        map.setIndoorEnabled(false);
        uiSettings.setCompassEnabled(false);
        uiSettings.setMapToolbarEnabled(false);
        map.setOnMarkerDragListener(this);
        drawCarMarker();
        setMapData();
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
    public void onMarkerDrag(@NotNull Marker marker) {
        Intrinsics.checkNotNullParameter(marker, "marker");
        Logger.INSTANCE.e(marker.getSnippet());
        updateShapeBasedOnMarker(marker);
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
    public void onMarkerDragEnd(@NotNull Marker marker) {
        Intrinsics.checkNotNullParameter(marker, "marker");
        Logger.INSTANCE.e(marker.getSnippet());
        updateShapeBasedOnMarker(marker);
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
    public void onMarkerDragStart(@NotNull Marker marker) {
        Intrinsics.checkNotNullParameter(marker, "marker");
        Logger.INSTANCE.e(marker.getSnippet());
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.isSourceChange) {
            return;
        }
        setUIData();
        setMapData();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initView();
    }
}
