package com.psa.mym.mycitroenconnect.controller.fragments.geo_fence;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
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
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.maps.android.PolyUtil;
import com.google.maps.android.SphericalUtil;
import com.psa.mym.mycitroenconnect.BusBaseFragment;
import com.psa.mym.mycitroenconnect.api.body.geo_fence.Coordinates;
import com.psa.mym.mycitroenconnect.api.body.geo_fence.GeoFenceBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.dashboard.GeoFenceActivity;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.SpeedSettingFragment;
import com.psa.mym.mycitroenconnect.controller.viewmodel.GeoFenceViewModel;
import com.psa.mym.mycitroenconnect.model.GeoFenceCommonModel;
import com.psa.mym.mycitroenconnect.model.MessageEvent;
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
import java.util.Arrays;
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
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.random.Random;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Document;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class GeoFenceMapSettingsFragment extends BusBaseFragment implements View.OnClickListener, OnMapReadyCallback {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final float MAX_ZOOM_LEVEL = 25.0f;
    private static final float ZOOM_LEVEL = 10.0f;
    private double carLatitude;
    private double carLongitude;
    @Nullable
    private Circle circle;
    private double destinationLatitude;
    private double destinationLongitude;
    private GoogleMap googleMap;
    private SupportMapFragment mapFragment;
    @Nullable
    private GeoFenceActivity parentActivity;
    @Nullable
    private Polygon polygon;
    @Nullable
    private PolygonOptions polygonOptions;
    @Nullable
    private AppCompatButton[] radButtonList;
    private double sourceLatitude;
    private double sourceLongitude;
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
    private double radiusVal = 10.0d;
    @NotNull
    private ArrayList<MapPolygonData> polygonDataList = new ArrayList<>();
    @Nullable
    private ArrayList<LatLng> latLngList = new ArrayList<>();
    @NotNull
    private final Lazy viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(GeoFenceViewModel.class), new GeoFenceMapSettingsFragment$special$$inlined$activityViewModels$default$1(this), new GeoFenceMapSettingsFragment$special$$inlined$activityViewModels$default$2(this));

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final GeoFenceMapSettingsFragment newInstance() {
            GeoFenceMapSettingsFragment geoFenceMapSettingsFragment = new GeoFenceMapSettingsFragment();
            geoFenceMapSettingsFragment.setArguments(new Bundle());
            return geoFenceMapSettingsFragment;
        }
    }

    private final MapPolygonData createMapPolygonData(String str, LatLng latLng) {
        return new MapPolygonData(str, latLng.latitude, latLng.longitude, latLng);
    }

    private final void displayCustomFence() {
        GoogleMap googleMap = this.googleMap;
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            googleMap = null;
        }
        googleMap.clear();
        drawSourceMarker();
        drawCarMarker();
        drawCustomFence();
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
        double d2 = this.radiusVal * 1000;
        boolean z = true;
        if (this.sourceLatitude == 0.0d) {
            if (this.sourceLongitude == 0.0d) {
                this.sourceLatitude = this.carLatitude;
                this.sourceLongitude = this.carLongitude;
                setSourceAddressText();
            }
        }
        GoogleMap googleMap3 = this.googleMap;
        if (googleMap3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
        } else {
            googleMap2 = googleMap3;
        }
        LatLng latLng = new LatLng(this.sourceLatitude, this.sourceLongitude);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        MapExtensionKt.drawMarker(googleMap2, requireContext, latLng, R.drawable.ic_source_marker, this.sourceAddress);
        LatLng latLng2 = new LatLng(this.carLatitude, this.carLongitude);
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        MapExtensionKt.drawMarker$default(googleMap2, requireContext2, latLng2, R.drawable.ic_car_marker, null, 8, null);
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(latLng);
        circleOptions.radius(d2);
        circleOptions.strokeWidth(4.0f);
        circleOptions.strokeColor(ContextCompat.getColor(requireContext(), R.color.primary_color_1));
        circleOptions.fillColor(Color.parseColor("#1A9D0605"));
        googleMap2.setMaxZoomPreference(MAX_ZOOM_LEVEL);
        int i2 = (int) this.radiusVal;
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

    private final void displayRouteFence() {
        GoogleMap googleMap = this.googleMap;
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            googleMap = null;
        }
        googleMap.clear();
        drawSourceMarker();
        drawDestinationMarker();
        drawRoute(new LatLng(this.sourceLatitude, this.sourceLongitude), new LatLng(this.destinationLatitude, this.destinationLongitude), ContextCompat.getColor(requireContext(), R.color.primary_color_1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void drawBounds(LatLngBounds latLngBounds) {
        drawCarMarker();
        LatLng latLng = latLngBounds.northeast;
        LatLng latLng2 = new LatLng(latLng.latitude, latLng.longitude);
        LatLng latLng3 = new LatLng(latLngBounds.southwest.latitude, latLngBounds.northeast.longitude);
        LatLng latLng4 = latLngBounds.southwest;
        LatLng latLng5 = new LatLng(latLng4.latitude, latLng4.longitude);
        LatLng latLng6 = new LatLng(latLngBounds.northeast.latitude, latLngBounds.southwest.longitude);
        LatLng polygonCenterPoint = getPolygonCenterPoint(latLng2, latLng3);
        LatLng polygonCenterPoint2 = getPolygonCenterPoint(latLng3, latLng5);
        LatLng polygonCenterPoint3 = getPolygonCenterPoint(latLng5, latLng6);
        LatLng polygonCenterPoint4 = getPolygonCenterPoint(latLng6, latLng2);
        ArrayList<LatLng> arrayList = this.latLngList;
        if (arrayList != null) {
            arrayList.clear();
            arrayList.add(latLng2);
            arrayList.add(polygonCenterPoint);
            arrayList.add(latLng3);
            arrayList.add(polygonCenterPoint2);
            arrayList.add(latLng5);
            arrayList.add(polygonCenterPoint3);
            arrayList.add(latLng6);
            arrayList.add(polygonCenterPoint4);
            this.polygonDataList.clear();
            int i2 = 0;
            for (Object obj : arrayList) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                LatLng latLng7 = (LatLng) obj;
                String valueOf = String.valueOf(Random.Default.nextInt());
                GoogleMap googleMap = this.googleMap;
                if (googleMap == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                    googleMap = null;
                }
                MarkerOptions title = new MarkerOptions().position(latLng7).snippet(valueOf).title(String.valueOf(i3));
                AppUtil.Companion companion = AppUtil.Companion;
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                googleMap.addMarker(title.icon(companion.getBitmapDescriptorFromVector(requireContext, R.drawable.ic_move_icon_2)));
                this.polygonDataList.add(createMapPolygonData(valueOf, latLng7));
                i2 = i3;
            }
            PolyUtil.isLocationOnPath(latLng2, arrayList, true);
            ArrayList<LatLng> arrayList2 = this.latLngList;
            Intrinsics.checkNotNull(arrayList2);
            drawPolygon(arrayList2);
        }
    }

    private final void drawCarMarker() {
        GoogleMap googleMap = this.googleMap;
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            googleMap = null;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        MapExtensionKt.drawMarker(googleMap, requireContext, new LatLng(this.carLatitude, this.carLongitude), R.drawable.ic_car_marker, this.carAddress);
    }

    private final void drawCustomFence() {
        GoogleMap googleMap;
        Logger logger = Logger.INSTANCE;
        logger.e("Phase: " + (6.283185307179586d / 8));
        logger.e("Radius in degree: " + Math.toRadians(5.0d));
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        ArrayList<LatLng> arrayList = this.latLngList;
        if (arrayList != null) {
            this.polygonDataList.clear();
            int i2 = 0;
            Iterator<T> it = arrayList.iterator();
            while (true) {
                googleMap = null;
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                LatLng latLng = (LatLng) next;
                String valueOf = String.valueOf(Random.Default.nextInt());
                GoogleMap googleMap2 = this.googleMap;
                if (googleMap2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                } else {
                    googleMap = googleMap2;
                }
                MarkerOptions title = new MarkerOptions().position(latLng).snippet(valueOf).title(String.valueOf(i3));
                AppUtil.Companion companion = AppUtil.Companion;
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                googleMap.addMarker(title.icon(companion.getBitmapDescriptorFromVector(requireContext, R.drawable.ic_move_icon_2)));
                this.polygonDataList.add(createMapPolygonData(valueOf, latLng));
                builder.include(latLng);
                i2 = i3;
            }
            drawPolygon(arrayList);
            LatLngBounds build = builder.build();
            Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
            double d2 = 100;
            LatLng computeOffset = SphericalUtil.computeOffset(build.northeast, Math.sqrt(2.0d) * d2, 45.0d);
            LatLng computeOffset2 = SphericalUtil.computeOffset(build.southwest, d2 * Math.sqrt(2.0d), 225.0d);
            builder.include(computeOffset);
            builder.include(computeOffset2);
            GoogleMap googleMap3 = this.googleMap;
            if (googleMap3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            } else {
                googleMap = googleMap3;
            }
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 200));
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
        Logger.INSTANCE.e("DrawPolygon");
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

    private final void drawRoute(final LatLng latLng, final LatLng latLng2, final int i2) {
        final Looper mainLooper = Looper.getMainLooper();
        new GMapV2DirectionAsyncTask(new Handler(mainLooper) { // from class: com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.GeoFenceMapSettingsFragment$drawRoute$handler$1
            @Override // android.os.Handler
            public void handleMessage(@NotNull Message msg) {
                GoogleMap googleMap;
                GoogleMap googleMap2;
                double d2;
                double d3;
                String str;
                double d4;
                double d5;
                GeoFenceViewModel viewModel;
                GeoFenceViewModel viewModel2;
                GeoFenceMapSettingsFragment geoFenceMapSettingsFragment;
                GeoFenceBody geoFenceBody;
                GoogleMap googleMap3;
                PolylineOptions polylineOptions;
                GoogleMap googleMap4;
                GeoFenceActivity geoFenceActivity;
                GetGeoFenceResponseItem geoFenceResponse;
                Intrinsics.checkNotNullParameter(msg, "msg");
                try {
                    Object obj = msg.obj;
                    if (obj == null) {
                        throw new NullPointerException("null cannot be cast to non-null type org.w3c.dom.Document");
                    }
                    ArrayList<LatLng> directionPoint = new GMapV2Direction().getDirection((Document) obj);
                    PolylineOptions color = new PolylineOptions().width(10.0f).color(i2);
                    Intrinsics.checkNotNullExpressionValue(color, "PolylineOptions()\n      …            .color(color)");
                    ArrayList arrayList = new ArrayList();
                    color.add(latLng);
                    Intrinsics.checkNotNullExpressionValue(directionPoint, "directionPoint");
                    for (LatLng latLng3 : directionPoint) {
                        color.add(latLng3);
                        arrayList.add(latLng3);
                    }
                    color.add(latLng2);
                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        builder.include((LatLng) it.next());
                    }
                    LatLngBounds build = builder.build();
                    Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
                    double d6 = 100;
                    LatLng computeOffset = SphericalUtil.computeOffset(build.northeast, d6 * Math.sqrt(2.0d), 45.0d);
                    LatLng computeOffset2 = SphericalUtil.computeOffset(build.southwest, d6 * Math.sqrt(2.0d), 225.0d);
                    builder.include(computeOffset);
                    builder.include(computeOffset2);
                    LatLngBounds build2 = builder.build();
                    Intrinsics.checkNotNullExpressionValue(build2, "builder.build()");
                    viewModel = this.getViewModel();
                    GeoFenceCommonModel geoFenceCommonModel = viewModel.getGeoFenceCommonModel();
                    boolean z = false;
                    if (geoFenceCommonModel != null && geoFenceCommonModel.getFenceCreationMode() == 6) {
                        geoFenceActivity = this.parentActivity;
                        List<LocationData> coordinates = (geoFenceActivity == null || (geoFenceResponse = geoFenceActivity.getGeoFenceResponse()) == null) ? null : geoFenceResponse.getCoordinates();
                        if ((coordinates == null || coordinates.isEmpty()) ? true : true) {
                            geoFenceMapSettingsFragment = this;
                            geoFenceMapSettingsFragment.drawBounds(build2);
                        } else {
                            this.drawRouteBounds();
                        }
                    } else {
                        viewModel2 = this.getViewModel();
                        GeoFenceCommonModel geoFenceCommonModel2 = viewModel2.getGeoFenceCommonModel();
                        ArrayList<Coordinates> coordinates2 = (geoFenceCommonModel2 == null || (geoFenceBody = geoFenceCommonModel2.getGeoFenceBody()) == null) ? null : geoFenceBody.getCoordinates();
                        if ((coordinates2 == null || coordinates2.isEmpty()) ? true : true) {
                            geoFenceMapSettingsFragment = this;
                            geoFenceMapSettingsFragment.drawBounds(build2);
                        } else {
                            this.drawRouteBoundsForSet();
                        }
                    }
                    googleMap3 = this.googleMap;
                    if (googleMap3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                        polylineOptions = color;
                        googleMap3 = null;
                    } else {
                        polylineOptions = color;
                    }
                    googleMap3.addPolyline(polylineOptions);
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
                    googleMap = this.googleMap;
                    if (googleMap == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                        googleMap2 = null;
                    } else {
                        googleMap2 = googleMap;
                    }
                    GeoFenceMapSettingsFragment geoFenceMapSettingsFragment2 = this;
                    Context requireContext = geoFenceMapSettingsFragment2.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    d2 = geoFenceMapSettingsFragment2.carLatitude;
                    d3 = geoFenceMapSettingsFragment2.carLongitude;
                    LatLng latLng4 = new LatLng(d2, d3);
                    str = geoFenceMapSettingsFragment2.carAddress;
                    MapExtensionKt.drawMarker(googleMap2, requireContext, latLng4, R.drawable.ic_car_marker, str);
                    d4 = geoFenceMapSettingsFragment2.carLatitude;
                    d5 = geoFenceMapSettingsFragment2.carLongitude;
                    MapExtensionKt.moveCameraOnMap(googleMap2, 10.0f, new LatLng(d4, d5), true);
                }
            }
        }, latLng, latLng2, new ArrayList(), GMapV2Direction.MODE_DRIVING).getDistanceFromGoogle();
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
                    googleMap.addMarker(title.icon(companion.getBitmapDescriptorFromVector(requireContext, R.drawable.ic_move_icon_2)).draggable(false));
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
                    googleMap.addMarker(title.icon(companion.getBitmapDescriptorFromVector(requireContext, R.drawable.ic_move_icon_2)).draggable(false));
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
        SupportMapFragment supportMapFragment = null;
        if (getActivity() == null || !(getActivity() instanceof GeoFenceActivity)) {
            geoFenceActivity = null;
        } else {
            FragmentActivity activity = getActivity();
            Objects.requireNonNull(activity, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.controller.activities.dashboard.GeoFenceActivity");
            geoFenceActivity = (GeoFenceActivity) activity;
        }
        this.parentActivity = geoFenceActivity;
        AppCompatEditText edtGeoFenceName = (AppCompatEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edtGeoFenceName);
        Intrinsics.checkNotNullExpressionValue(edtGeoFenceName, "edtGeoFenceName");
        ExtensionsKt.disableEmoji(edtGeoFenceName);
        AppCompatButton btnRad10 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad10);
        Intrinsics.checkNotNullExpressionValue(btnRad10, "btnRad10");
        AppCompatButton btnRad20 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad20);
        Intrinsics.checkNotNullExpressionValue(btnRad20, "btnRad20");
        AppCompatButton btnRad30 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad30);
        Intrinsics.checkNotNullExpressionValue(btnRad30, "btnRad30");
        AppCompatButton btnRad40 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad40);
        Intrinsics.checkNotNullExpressionValue(btnRad40, "btnRad40");
        AppCompatButton btnRad50 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad50);
        Intrinsics.checkNotNullExpressionValue(btnRad50, "btnRad50");
        AppCompatButton btnRadEdit = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEdit);
        Intrinsics.checkNotNullExpressionValue(btnRadEdit, "btnRadEdit");
        AppCompatButton btnRadEditVal = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEditVal);
        Intrinsics.checkNotNullExpressionValue(btnRadEditVal, "btnRadEditVal");
        this.radButtonList = new AppCompatButton[]{btnRad10, btnRad20, btnRad30, btnRad40, btnRad50, btnRadEdit, btnRadEditVal};
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
        setListeners();
    }

    private final boolean isRadiusSelected() {
        AppCompatButton[] appCompatButtonArr = this.radButtonList;
        if (appCompatButtonArr != null) {
            for (AppCompatButton appCompatButton : appCompatButtonArr) {
                if (appCompatButton.isSelected()) {
                    return true;
                }
            }
        }
        return false;
    }

    private final void navigateToGeoFenceMapFragment() {
        GeoFenceActivity geoFenceActivity = this.parentActivity;
        if (geoFenceActivity != null) {
            geoFenceActivity.navigateToGeoFenceMapFragment();
        }
    }

    private final void navigateToSetTimeFragment() {
        GeoFenceActivity geoFenceActivity = this.parentActivity;
        if (geoFenceActivity != null) {
            geoFenceActivity.navigateToSetTimeFragment();
        }
    }

    @JvmStatic
    @NotNull
    public static final GeoFenceMapSettingsFragment newInstance() {
        return Companion.newInstance();
    }

    private final void onRadiusDialogDismiss(String str) {
        boolean isBlank;
        boolean contains$default;
        String replace$default;
        isBlank = StringsKt__StringsJVMKt.isBlank(str);
        if (!isBlank) {
            if (!(str.length() > 0) || Intrinsics.areEqual(str, AppConstants.DEFAULT_VAL_SPEED_DLG)) {
                return;
            }
            contains$default = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "km", false, 2, (Object) null);
            if (contains$default) {
                int i2 = com.psa.mym.mycitroenconnect.R.id.btnRadEditVal;
                replace$default = StringsKt__StringsJVMKt.replace$default(str, "km", "", false, 4, (Object) null);
                ((AppCompatButton) _$_findCachedViewById(i2)).setText(replace$default);
                AppCompatButton btnRadEditVal = (AppCompatButton) _$_findCachedViewById(i2);
                Intrinsics.checkNotNullExpressionValue(btnRadEditVal, "btnRadEditVal");
                ExtensionsKt.show(btnRadEditVal);
                AppCompatButton btnRadEdit = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEdit);
                Intrinsics.checkNotNullExpressionValue(btnRadEdit, "btnRadEdit");
                ExtensionsKt.hide(btnRadEdit);
                AppCompatButton btnRad50 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad50);
                Intrinsics.checkNotNullExpressionValue(btnRad50, "btnRad50");
                ExtensionsKt.hide(btnRad50);
                AppCompatButton[] appCompatButtonArr = this.radButtonList;
                if (appCompatButtonArr != null) {
                    for (AppCompatButton appCompatButton : appCompatButtonArr) {
                        Context requireContext = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        ExtensionsKt.onSquareBtnDeselect(appCompatButton, requireContext);
                    }
                }
                int i3 = com.psa.mym.mycitroenconnect.R.id.btnRadEditVal;
                AppCompatButton btnRadEditVal2 = (AppCompatButton) _$_findCachedViewById(i3);
                Intrinsics.checkNotNullExpressionValue(btnRadEditVal2, "btnRadEditVal");
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                ExtensionsKt.onSquareBtnSelected(btnRadEditVal2, requireContext2);
                this.radiusVal = Double.parseDouble(((AppCompatButton) _$_findCachedViewById(i3)).getText().toString());
                displayRadiusFence();
            }
        }
    }

    private final void setCarAddressText() {
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.carAddress = companion.getAddressNameString(requireContext, this.carLatitude, this.carLongitude);
    }

    private final void setCustomFenceData() {
        GetGeoFenceResponseItem geoFenceGetResponse;
        Logger logger;
        StringBuilder sb;
        Gson gson;
        GetGeoFenceResponseItem getGeoFenceResponseItem;
        GeoFenceBody geoFenceBody;
        ArrayList<Coordinates> coordinates;
        GeoFenceBody geoFenceBody2;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel != null && geoFenceCommonModel.getFenceCreationMode() == 6) {
            GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel2 == null || (geoFenceGetResponse = geoFenceCommonModel2.getGeoFenceGetResponse()) == null) {
                return;
            }
            geoFenceGetResponse.setSourceLocation(new LocationData(Double.valueOf(this.sourceLatitude), Double.valueOf(this.sourceLongitude)));
            geoFenceGetResponse.setDestinationLocation(new LocationData(Double.valueOf(this.sourceLatitude), Double.valueOf(this.sourceLongitude)));
            geoFenceGetResponse.setCoordinates(new ArrayList());
            for (MapPolygonData mapPolygonData : this.polygonDataList) {
                List<LocationData> coordinates2 = geoFenceGetResponse.getCoordinates();
                Objects.requireNonNull(coordinates2, "null cannot be cast to non-null type java.util.ArrayList<com.psa.mym.mycitroenconnect.model.geo_fence.LocationData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.psa.mym.mycitroenconnect.model.geo_fence.LocationData> }");
                ((ArrayList) coordinates2).add(new LocationData(Double.valueOf(mapPolygonData.getLatitude()), Double.valueOf(mapPolygonData.getLongitude())));
            }
            if (this.polygonDataList.size() > 0) {
                List<LocationData> coordinates3 = geoFenceGetResponse.getCoordinates();
                Objects.requireNonNull(coordinates3, "null cannot be cast to non-null type java.util.ArrayList<com.psa.mym.mycitroenconnect.model.geo_fence.LocationData>{ kotlin.collections.TypeAliasesKt.ArrayList<com.psa.mym.mycitroenconnect.model.geo_fence.LocationData> }");
                ((ArrayList) coordinates3).add(new LocationData(Double.valueOf(this.polygonDataList.get(0).getLatitude()), Double.valueOf(this.polygonDataList.get(0).getLongitude())));
            }
            geoFenceGetResponse.setFenceName(String.valueOf(((AppCompatEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edtGeoFenceName)).getText()));
            geoFenceGetResponse.setFenceMode(AppConstants.GEO_FENCE_MODE_CUSTOM);
            logger = Logger.INSTANCE;
            sb = new StringBuilder();
            sb.append("GeoFenceBody Custom : ");
            getGeoFenceResponseItem = geoFenceGetResponse;
            gson = new Gson();
        } else {
            GeoFenceCommonModel geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel3 == null || (geoFenceBody = geoFenceCommonModel3.getGeoFenceBody()) == null) {
                return;
            }
            geoFenceBody.setFenceType("location");
            geoFenceBody.setFenceGeometry(AppConstants.GEO_FENCE_GEOMETRY_POLYGON);
            geoFenceBody.setFenceStatus("D");
            GeoFenceCommonModel geoFenceCommonModel4 = getViewModel().getGeoFenceCommonModel();
            geoFenceBody.setTransitionType((geoFenceCommonModel4 == null || (geoFenceBody2 = geoFenceCommonModel4.getGeoFenceBody()) == null) ? null : geoFenceBody2.getTransitionType());
            geoFenceBody.setSourceLocation(new Coordinates(Double.valueOf(this.sourceLatitude), Double.valueOf(this.sourceLongitude)));
            geoFenceBody.setDestinationLocation(new Coordinates(Double.valueOf(this.sourceLatitude), Double.valueOf(this.sourceLongitude)));
            geoFenceBody.setCoordinates(new ArrayList<>());
            for (MapPolygonData mapPolygonData2 : this.polygonDataList) {
                ArrayList<Coordinates> coordinates4 = geoFenceBody.getCoordinates();
                if (coordinates4 != null) {
                    coordinates4.add(new Coordinates(Double.valueOf(mapPolygonData2.getLatitude()), Double.valueOf(mapPolygonData2.getLongitude())));
                }
            }
            if (this.polygonDataList.size() > 0 && (coordinates = geoFenceBody.getCoordinates()) != null) {
                coordinates.add(new Coordinates(Double.valueOf(this.polygonDataList.get(0).getLatitude()), Double.valueOf(this.polygonDataList.get(0).getLongitude())));
            }
            geoFenceBody.setFenceName(String.valueOf(((AppCompatEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edtGeoFenceName)).getText()));
            geoFenceBody.setFenceMode(AppConstants.GEO_FENCE_MODE_CUSTOM);
            logger = Logger.INSTANCE;
            sb = new StringBuilder();
            sb.append("GeoFenceBody Custom : ");
            getGeoFenceResponseItem = geoFenceBody;
            gson = new Gson();
        }
        sb.append(gson.toJson(getGeoFenceResponseItem));
        logger.e(sb.toString());
    }

    private final void setDestinationAddressText() {
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.destinationAddress = companion.getAddressNameString(requireContext, this.destinationLatitude, this.destinationLongitude);
    }

    private final void setFenceName(String str) {
        ((AppCompatEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edtGeoFenceName)).setText(str);
    }

    private final void setListeners() {
        AppCompatButton[] appCompatButtonArr = this.radButtonList;
        if (appCompatButtonArr != null) {
            for (AppCompatButton appCompatButton : appCompatButtonArr) {
                appCompatButton.setOnClickListener(this);
            }
        }
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnChangeRoute)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnBack)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnNext)).setOnClickListener(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void setMapData() {
        GoogleMap googleMap = this.googleMap;
        if (googleMap == null) {
            return;
        }
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            googleMap = null;
        }
        LatLng latLng = new LatLng(this.carLatitude, this.carLongitude);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        MapExtensionKt.drawMarker(googleMap, requireContext, latLng, R.drawable.ic_car_marker, this.carAddress);
        String str = this.fenceType;
        int hashCode = str.hashCode();
        if (hashCode == -1349088399) {
            if (str.equals(AppConstants.GEO_FENCE_MODE_CUSTOM)) {
                MapExtensionKt.moveCameraOnMap$default(googleMap, 10.0f, latLng, false, 4, null);
                if (!(this.sourceLatitude == 0.0d)) {
                    if (!(this.sourceLongitude == 0.0d)) {
                        LatLng latLng2 = new LatLng(this.sourceLatitude, this.sourceLongitude);
                        Context requireContext2 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                        MapExtensionKt.drawMarker(googleMap, requireContext2, latLng2, R.drawable.ic_source_marker, this.carAddress);
                        MapExtensionKt.moveCameraOnMap(googleMap, 10.0f, latLng2, true);
                        displayCustomFence();
                    }
                }
                MapExtensionKt.moveCameraOnMap(googleMap, 10.0f, new LatLng(this.carLatitude, this.carLongitude), true);
                this.sourceLatitude = this.carLatitude;
                this.sourceLongitude = this.carLongitude;
                this.sourceAddress = this.carAddress;
                displayCustomFence();
            }
        } else if (hashCode != -938578798) {
            if (hashCode == 108704329 && str.equals(AppConstants.GEO_FENCE_MODE_ROUTE)) {
                MapExtensionKt.moveCameraOnMap(googleMap, 10.0f, latLng, true);
                displayRouteFence();
            }
        } else if (!str.equals(AppConstants.GEO_FENCE_MODE_RADIUS)) {
        } else {
            if (!(this.sourceLatitude == 0.0d)) {
                if (!(this.sourceLongitude == 0.0d)) {
                    LatLng latLng3 = new LatLng(this.sourceLatitude, this.sourceLongitude);
                    Context requireContext3 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                    MapExtensionKt.drawMarker(googleMap, requireContext3, latLng3, R.drawable.ic_source_marker, this.sourceAddress);
                    MapExtensionKt.moveCameraOnMap(googleMap, 10.0f, latLng3, true);
                    displayRadiusFence();
                    if (this.radiusVal == 0.0d) {
                        return;
                    }
                    this.radiusVal = 10.0d;
                    return;
                }
            }
            MapExtensionKt.moveCameraOnMap(googleMap, 10.0f, latLng, true);
            displayRadiusFence();
            ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad10)).performClick();
            if (this.radiusVal == 0.0d) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:125:0x01eb, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r0, (r1 == null || (r1 = r1.getGeoFenceGetResponse()) == null || (r1 = r1.getDestinationLocation()) == null) ? null : r1.getGpsLong()) == false) goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:337:0x061c, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r0, (r2 == null || (r2 = r2.getGeoFenceGetResponse()) == null || (r2 = r2.getCentre()) == null) ? null : r2.getGpsLong()) == false) goto L365;
     */
    /* JADX WARN: Code restructure failed: missing block: B:442:0x07e1, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r0, (r1 == null || (r1 = r1.getGeoFenceGetResponse()) == null || (r1 = r1.getSourceLocation()) == null) ? null : r1.getGpsLong()) == false) goto L548;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00f9, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r0, (r1 == null || (r1 = r1.getGeoFenceGetResponse()) == null || (r1 = r1.getSourceLocation()) == null) ? null : r1.getGpsLong()) == false) goto L216;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x037c  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:479:0x088b  */
    /* JADX WARN: Removed duplicated region for block: B:535:0x09c1  */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v201 */
    /* JADX WARN: Type inference failed for: r0v202 */
    /* JADX WARN: Type inference failed for: r0v211 */
    /* JADX WARN: Type inference failed for: r0v26 */
    /* JADX WARN: Type inference failed for: r0v280 */
    /* JADX WARN: Type inference failed for: r0v281 */
    /* JADX WARN: Type inference failed for: r0v292 */
    /* JADX WARN: Type inference failed for: r0v74 */
    /* JADX WARN: Type inference failed for: r0v75 */
    /* JADX WARN: Type inference failed for: r0v86 */
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
        GeoFenceBody geoFenceBody5;
        String fenceName;
        GetGeoFenceResponseItem geoFenceGetResponse;
        LocationData sourceLocation2;
        Double gpsLong;
        GeoFenceBody geoFenceBody6;
        ArrayList<Coordinates> coordinates;
        GeoFenceCommonModel geoFenceCommonModel;
        GeoFenceCommonModel geoFenceCommonModel2;
        ArrayList<LatLng> arrayList2;
        GeoFenceBody geoFenceBody7;
        GeoFenceBody geoFenceBody8;
        ArrayList<LatLng> arrayList3;
        GetGeoFenceResponseItem geoFenceGetResponse2;
        GetGeoFenceResponseItem geoFenceGetResponse3;
        GeoFenceBody geoFenceBody9;
        ArrayList<Coordinates> coordinates2;
        GeoFenceBody geoFenceBody10;
        GetGeoFenceResponseItem geoFenceGetResponse4;
        GeoFenceBody geoFenceBody11;
        GeoFenceBody geoFenceBody12;
        Coordinates sourceLocation3;
        GeoFenceBody geoFenceBody13;
        Coordinates sourceLocation4;
        GetGeoFenceResponseItem geoFenceGetResponse5;
        LocationData sourceLocation5;
        GeoFenceBody geoFenceBody14;
        Coordinates sourceLocation6;
        GetGeoFenceResponseItem geoFenceGetResponse6;
        String fenceName2;
        GeoFenceBody geoFenceBody15;
        Double radius;
        float doubleValue;
        GeoFenceBody geoFenceBody16;
        Coordinates centre;
        GeoFenceBody geoFenceBody17;
        String fenceName3;
        GetGeoFenceResponseItem geoFenceGetResponse7;
        LocationData centre2;
        Double gpsLong2;
        GeoFenceBody geoFenceBody18;
        GeoFenceCommonModel geoFenceCommonModel3;
        GetGeoFenceResponseItem geoFenceGetResponse8;
        Integer radius2;
        GeoFenceBody geoFenceBody19;
        Coordinates centre3;
        GeoFenceBody geoFenceBody20;
        Coordinates centre4;
        GetGeoFenceResponseItem geoFenceGetResponse9;
        LocationData centre5;
        GeoFenceBody geoFenceBody21;
        Coordinates centre6;
        GetGeoFenceResponseItem geoFenceGetResponse10;
        String fenceName4;
        ArrayList<LatLng> arrayList4;
        GeoFenceBody geoFenceBody22;
        GeoFenceBody geoFenceBody23;
        GeoFenceBody geoFenceBody24;
        GeoFenceBody geoFenceBody25;
        Coordinates destinationLocation;
        GeoFenceBody geoFenceBody26;
        Coordinates sourceLocation7;
        GeoFenceBody geoFenceBody27;
        String fenceName5;
        GetGeoFenceResponseItem geoFenceGetResponse11;
        LocationData sourceLocation8;
        Double gpsLong3;
        GeoFenceBody geoFenceBody28;
        Double gpsLat;
        GeoFenceCommonModel geoFenceCommonModel4;
        GeoFenceCommonModel geoFenceCommonModel5;
        GetGeoFenceResponseItem geoFenceGetResponse12;
        LocationData destinationLocation2;
        Double gpsLong4;
        GeoFenceBody geoFenceBody29;
        ArrayList<Coordinates> coordinates3;
        GeoFenceCommonModel geoFenceCommonModel6;
        GeoFenceCommonModel geoFenceCommonModel7;
        ArrayList<LatLng> arrayList5;
        GeoFenceBody geoFenceBody30;
        GeoFenceBody geoFenceBody31;
        ArrayList<LatLng> arrayList6;
        GetGeoFenceResponseItem geoFenceGetResponse13;
        GetGeoFenceResponseItem geoFenceGetResponse14;
        GeoFenceBody geoFenceBody32;
        ArrayList<Coordinates> coordinates4;
        GeoFenceBody geoFenceBody33;
        GetGeoFenceResponseItem geoFenceGetResponse15;
        GeoFenceBody geoFenceBody34;
        GeoFenceCommonModel geoFenceCommonModel8;
        GeoFenceBody geoFenceBody35;
        Coordinates destinationLocation3;
        GeoFenceBody geoFenceBody36;
        Coordinates destinationLocation4;
        GetGeoFenceResponseItem geoFenceGetResponse16;
        LocationData destinationLocation5;
        GeoFenceBody geoFenceBody37;
        Coordinates destinationLocation6;
        GeoFenceBody geoFenceBody38;
        Coordinates sourceLocation9;
        GeoFenceBody geoFenceBody39;
        Coordinates sourceLocation10;
        GetGeoFenceResponseItem geoFenceGetResponse17;
        LocationData sourceLocation11;
        GeoFenceBody geoFenceBody40;
        Coordinates sourceLocation12;
        GetGeoFenceResponseItem geoFenceGetResponse18;
        String fenceName6;
        LatLng carLatLng;
        GeoFenceCommonModel geoFenceCommonModel9 = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel9 != null && (carLatLng = geoFenceCommonModel9.getCarLatLng()) != null) {
            this.carLatitude = carLatLng.latitude;
            this.carLongitude = carLatLng.longitude;
            setCarAddressText();
            Unit unit = Unit.INSTANCE;
        }
        String str = this.fenceType;
        int hashCode = str.hashCode();
        int i2 = 0;
        ArrayList<Coordinates> arrayList7 = null;
        r8 = null;
        List<LocationData> list = null;
        r8 = null;
        ArrayList<Coordinates> arrayList8 = null;
        r8 = null;
        ArrayList<Coordinates> arrayList9 = null;
        r8 = null;
        Coordinates coordinates5 = null;
        r8 = null;
        List<LocationData> list2 = null;
        r8 = null;
        ArrayList<Coordinates> arrayList10 = null;
        arrayList7 = null;
        if (hashCode == -1349088399) {
            if (str.equals(AppConstants.GEO_FENCE_MODE_CUSTOM)) {
                GeoFenceCommonModel geoFenceCommonModel10 = getViewModel().getGeoFenceCommonModel();
                Integer valueOf = geoFenceCommonModel10 != null ? Integer.valueOf(geoFenceCommonModel10.getFenceCreationMode()) : null;
                if (valueOf == null || valueOf.intValue() != 6) {
                    GeoFenceCommonModel geoFenceCommonModel11 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel11 != null && (geoFenceBody5 = geoFenceCommonModel11.getGeoFenceBody()) != null && (fenceName = geoFenceBody5.getFenceName()) != null) {
                        setFenceName(fenceName);
                        Unit unit2 = Unit.INSTANCE;
                    }
                    GeoFenceCommonModel geoFenceCommonModel12 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel12 != null && (geoFenceBody4 = geoFenceCommonModel12.getGeoFenceBody()) != null && (sourceLocation = geoFenceBody4.getSourceLocation()) != null) {
                        Double gpsLat2 = sourceLocation.getGpsLat();
                        Intrinsics.checkNotNull(gpsLat2);
                        this.sourceLatitude = gpsLat2.doubleValue();
                        Double gpsLong5 = sourceLocation.getGpsLong();
                        Intrinsics.checkNotNull(gpsLong5);
                        this.sourceLongitude = gpsLong5.doubleValue();
                        setSourceAddressText();
                        Unit unit3 = Unit.INSTANCE;
                    }
                    GeoFenceCommonModel geoFenceCommonModel13 = getViewModel().getGeoFenceCommonModel();
                    ArrayList<Coordinates> coordinates6 = (geoFenceCommonModel13 == null || (geoFenceBody3 = geoFenceCommonModel13.getGeoFenceBody()) == null) ? null : geoFenceBody3.getCoordinates();
                    if ((coordinates6 == null || coordinates6.isEmpty()) == true) {
                        return;
                    }
                    GeoFenceCommonModel geoFenceCommonModel14 = getViewModel().getGeoFenceCommonModel();
                    ArrayList<Coordinates> coordinates7 = (geoFenceCommonModel14 == null || (geoFenceBody2 = geoFenceCommonModel14.getGeoFenceBody()) == null) ? null : geoFenceBody2.getCoordinates();
                    Intrinsics.checkNotNull(coordinates7);
                    int size = coordinates7.size();
                    Logger.INSTANCE.e("size: " + size);
                    this.latLngList = new ArrayList<>();
                    GeoFenceCommonModel geoFenceCommonModel15 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel15 != null && (geoFenceBody = geoFenceCommonModel15.getGeoFenceBody()) != null) {
                        arrayList7 = geoFenceBody.getCoordinates();
                    }
                    Intrinsics.checkNotNull(arrayList7);
                    for (Object obj : arrayList7) {
                        int i3 = i2 + 1;
                        if (i2 < 0) {
                            CollectionsKt__CollectionsKt.throwIndexOverflow();
                        }
                        Coordinates coordinates8 = (Coordinates) obj;
                        Logger.INSTANCE.e("Index: " + i2);
                        if (i2 != size - 1 && (arrayList = this.latLngList) != null) {
                            Double gpsLat3 = coordinates8.getGpsLat();
                            Intrinsics.checkNotNull(gpsLat3);
                            double doubleValue2 = gpsLat3.doubleValue();
                            Double gpsLong6 = coordinates8.getGpsLong();
                            Intrinsics.checkNotNull(gpsLong6);
                            arrayList.add(new LatLng(doubleValue2, gpsLong6.doubleValue()));
                        }
                        i2 = i3;
                    }
                    return;
                }
                GeoFenceCommonModel geoFenceCommonModel16 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel16 != null && (geoFenceGetResponse6 = geoFenceCommonModel16.getGeoFenceGetResponse()) != null && (fenceName2 = geoFenceGetResponse6.getFenceName()) != null) {
                    setFenceName(fenceName2);
                    Unit unit4 = Unit.INSTANCE;
                }
                GeoFenceCommonModel geoFenceCommonModel17 = getViewModel().getGeoFenceCommonModel();
                Double gpsLat4 = (geoFenceCommonModel17 == null || (geoFenceBody14 = geoFenceCommonModel17.getGeoFenceBody()) == null || (sourceLocation6 = geoFenceBody14.getSourceLocation()) == null) ? null : sourceLocation6.getGpsLat();
                GeoFenceCommonModel geoFenceCommonModel18 = getViewModel().getGeoFenceCommonModel();
                if (Intrinsics.areEqual(gpsLat4, (geoFenceCommonModel18 == null || (geoFenceGetResponse5 = geoFenceCommonModel18.getGeoFenceGetResponse()) == null || (sourceLocation5 = geoFenceGetResponse5.getSourceLocation()) == null) ? null : sourceLocation5.getGpsLat())) {
                    GeoFenceCommonModel geoFenceCommonModel19 = getViewModel().getGeoFenceCommonModel();
                    Double gpsLong7 = (geoFenceCommonModel19 == null || (geoFenceBody13 = geoFenceCommonModel19.getGeoFenceBody()) == null || (sourceLocation4 = geoFenceBody13.getSourceLocation()) == null) ? null : sourceLocation4.getGpsLong();
                    GeoFenceCommonModel geoFenceCommonModel20 = getViewModel().getGeoFenceCommonModel();
                }
                GeoFenceCommonModel geoFenceCommonModel21 = getViewModel().getGeoFenceCommonModel();
                if (((geoFenceCommonModel21 == null || (geoFenceBody6 = geoFenceCommonModel21.getGeoFenceBody()) == null) ? null : geoFenceBody6.getSourceLocation()) == null) {
                    GeoFenceCommonModel geoFenceCommonModel22 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel22 != null && (geoFenceGetResponse = geoFenceCommonModel22.getGeoFenceGetResponse()) != null && (sourceLocation2 = geoFenceGetResponse.getSourceLocation()) != null) {
                        Double gpsLat5 = sourceLocation2.getGpsLat();
                        Intrinsics.checkNotNull(gpsLat5);
                        this.sourceLatitude = gpsLat5.doubleValue();
                        gpsLong = sourceLocation2.getGpsLong();
                        Intrinsics.checkNotNull(gpsLong);
                        this.sourceLongitude = gpsLong.doubleValue();
                        Unit unit5 = Unit.INSTANCE;
                    }
                    setSourceAddressText();
                    GeoFenceCommonModel geoFenceCommonModel23 = getViewModel().getGeoFenceCommonModel();
                    coordinates = (geoFenceCommonModel23 != null || (geoFenceBody11 = geoFenceCommonModel23.getGeoFenceBody()) == null) ? null : geoFenceBody11.getCoordinates();
                    geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
                    if (!Intrinsics.areEqual(coordinates, (geoFenceCommonModel != null || (geoFenceGetResponse4 = geoFenceCommonModel.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse4.getCoordinates())) {
                        GeoFenceCommonModel geoFenceCommonModel24 = getViewModel().getGeoFenceCommonModel();
                        if (((geoFenceCommonModel24 == null || (geoFenceBody10 = geoFenceCommonModel24.getGeoFenceBody()) == null) ? null : geoFenceBody10.getCoordinates()) == null) {
                            GeoFenceCommonModel geoFenceCommonModel25 = getViewModel().getGeoFenceCommonModel();
                            if (((geoFenceCommonModel25 == null || (geoFenceBody9 = geoFenceCommonModel25.getGeoFenceBody()) == null || (coordinates2 = geoFenceBody9.getCoordinates()) == null || coordinates2.size() != 0) ? false : true) != false) {
                                GeoFenceCommonModel geoFenceCommonModel26 = getViewModel().getGeoFenceCommonModel();
                                List<LocationData> coordinates9 = (geoFenceCommonModel26 == null || (geoFenceGetResponse3 = geoFenceCommonModel26.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse3.getCoordinates();
                                Intrinsics.checkNotNull(coordinates9);
                                int size2 = coordinates9.size();
                                Logger.INSTANCE.e("size: " + size2);
                                this.latLngList = new ArrayList<>();
                                GeoFenceCommonModel geoFenceCommonModel27 = getViewModel().getGeoFenceCommonModel();
                                if (geoFenceCommonModel27 != null && (geoFenceGetResponse2 = geoFenceCommonModel27.getGeoFenceGetResponse()) != null) {
                                    list2 = geoFenceGetResponse2.getCoordinates();
                                }
                                Intrinsics.checkNotNull(list2);
                                for (Object obj2 : list2) {
                                    int i4 = i2 + 1;
                                    if (i2 < 0) {
                                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                                    }
                                    LocationData locationData = (LocationData) obj2;
                                    Logger.INSTANCE.e("Index: " + i2);
                                    if (i2 != size2 - 1 && (arrayList3 = this.latLngList) != null) {
                                        Double gpsLat6 = locationData.getGpsLat();
                                        Intrinsics.checkNotNull(gpsLat6);
                                        double doubleValue3 = gpsLat6.doubleValue();
                                        Double gpsLong8 = locationData.getGpsLong();
                                        Intrinsics.checkNotNull(gpsLong8);
                                        arrayList3.add(new LatLng(doubleValue3, gpsLong8.doubleValue()));
                                    }
                                    i2 = i4;
                                }
                                return;
                            }
                        }
                    }
                    GeoFenceCommonModel geoFenceCommonModel28 = getViewModel().getGeoFenceCommonModel();
                    ArrayList<Coordinates> coordinates10 = (geoFenceCommonModel28 != null || (geoFenceBody8 = geoFenceCommonModel28.getGeoFenceBody()) == null) ? null : geoFenceBody8.getCoordinates();
                    Intrinsics.checkNotNull(coordinates10);
                    int size3 = coordinates10.size();
                    Logger.INSTANCE.e("size: " + size3);
                    this.latLngList = new ArrayList<>();
                    geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel2 != null && (geoFenceBody7 = geoFenceCommonModel2.getGeoFenceBody()) != null) {
                        arrayList10 = geoFenceBody7.getCoordinates();
                    }
                    Intrinsics.checkNotNull(arrayList10);
                    for (Object obj3 : arrayList10) {
                        int i5 = i2 + 1;
                        if (i2 < 0) {
                            CollectionsKt__CollectionsKt.throwIndexOverflow();
                        }
                        Coordinates coordinates11 = (Coordinates) obj3;
                        Logger.INSTANCE.e("Index: " + i2);
                        if (i2 != size3 - 1 && (arrayList2 = this.latLngList) != null) {
                            Double gpsLat7 = coordinates11.getGpsLat();
                            Intrinsics.checkNotNull(gpsLat7);
                            double doubleValue4 = gpsLat7.doubleValue();
                            Double gpsLong9 = coordinates11.getGpsLong();
                            Intrinsics.checkNotNull(gpsLong9);
                            arrayList2.add(new LatLng(doubleValue4, gpsLong9.doubleValue()));
                        }
                        i2 = i5;
                    }
                }
                GeoFenceCommonModel geoFenceCommonModel29 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel29 != null && (geoFenceBody12 = geoFenceCommonModel29.getGeoFenceBody()) != null && (sourceLocation3 = geoFenceBody12.getSourceLocation()) != null) {
                    Double gpsLat8 = sourceLocation3.getGpsLat();
                    Intrinsics.checkNotNull(gpsLat8);
                    this.sourceLatitude = gpsLat8.doubleValue();
                    gpsLong = sourceLocation3.getGpsLong();
                    Intrinsics.checkNotNull(gpsLong);
                    this.sourceLongitude = gpsLong.doubleValue();
                    Unit unit52 = Unit.INSTANCE;
                }
                setSourceAddressText();
                GeoFenceCommonModel geoFenceCommonModel232 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel232 != null) {
                }
                geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
                if (!Intrinsics.areEqual(coordinates, (geoFenceCommonModel != null || (geoFenceGetResponse4 = geoFenceCommonModel.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse4.getCoordinates())) {
                }
                GeoFenceCommonModel geoFenceCommonModel282 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel282 != null) {
                }
                Intrinsics.checkNotNull(coordinates10);
                int size32 = coordinates10.size();
                Logger.INSTANCE.e("size: " + size32);
                this.latLngList = new ArrayList<>();
                geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel2 != null) {
                    arrayList10 = geoFenceBody7.getCoordinates();
                }
                Intrinsics.checkNotNull(arrayList10);
                while (r1.hasNext()) {
                }
            }
        } else if (hashCode == -938578798) {
            if (str.equals(AppConstants.GEO_FENCE_MODE_RADIUS)) {
                GeoFenceCommonModel geoFenceCommonModel30 = getViewModel().getGeoFenceCommonModel();
                Integer valueOf2 = geoFenceCommonModel30 != null ? Integer.valueOf(geoFenceCommonModel30.getFenceCreationMode()) : null;
                if (valueOf2 == null || valueOf2.intValue() != 6) {
                    GeoFenceCommonModel geoFenceCommonModel31 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel31 != null && (geoFenceBody17 = geoFenceCommonModel31.getGeoFenceBody()) != null && (fenceName3 = geoFenceBody17.getFenceName()) != null) {
                        setFenceName(fenceName3);
                        Unit unit6 = Unit.INSTANCE;
                    }
                    GeoFenceCommonModel geoFenceCommonModel32 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel32 != null && (geoFenceBody16 = geoFenceCommonModel32.getGeoFenceBody()) != null && (centre = geoFenceBody16.getCentre()) != null) {
                        Double gpsLat9 = centre.getGpsLat();
                        Intrinsics.checkNotNull(gpsLat9);
                        this.sourceLatitude = gpsLat9.doubleValue();
                        Double gpsLong10 = centre.getGpsLong();
                        Intrinsics.checkNotNull(gpsLong10);
                        this.sourceLongitude = gpsLong10.doubleValue();
                        setSourceAddressText();
                        Unit unit7 = Unit.INSTANCE;
                    }
                    GeoFenceCommonModel geoFenceCommonModel33 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel33 != null && (geoFenceBody15 = geoFenceCommonModel33.getGeoFenceBody()) != null && (radius = geoFenceBody15.getRadius()) != null) {
                        doubleValue = (float) radius.doubleValue();
                        this.radiusVal = (int) (doubleValue / 1000);
                        Unit unit8 = Unit.INSTANCE;
                    }
                    setRadiusButton();
                }
                GeoFenceCommonModel geoFenceCommonModel34 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel34 != null && (geoFenceGetResponse10 = geoFenceCommonModel34.getGeoFenceGetResponse()) != null && (fenceName4 = geoFenceGetResponse10.getFenceName()) != null) {
                    setFenceName(fenceName4);
                    Unit unit9 = Unit.INSTANCE;
                }
                GeoFenceCommonModel geoFenceCommonModel35 = getViewModel().getGeoFenceCommonModel();
                Double gpsLat10 = (geoFenceCommonModel35 == null || (geoFenceBody21 = geoFenceCommonModel35.getGeoFenceBody()) == null || (centre6 = geoFenceBody21.getCentre()) == null) ? null : centre6.getGpsLat();
                GeoFenceCommonModel geoFenceCommonModel36 = getViewModel().getGeoFenceCommonModel();
                if (Intrinsics.areEqual(gpsLat10, (geoFenceCommonModel36 == null || (geoFenceGetResponse9 = geoFenceCommonModel36.getGeoFenceGetResponse()) == null || (centre5 = geoFenceGetResponse9.getCentre()) == null) ? null : centre5.getGpsLat())) {
                    GeoFenceCommonModel geoFenceCommonModel37 = getViewModel().getGeoFenceCommonModel();
                    Double gpsLong11 = (geoFenceCommonModel37 == null || (geoFenceBody20 = geoFenceCommonModel37.getGeoFenceBody()) == null || (centre4 = geoFenceBody20.getCentre()) == null) ? null : centre4.getGpsLong();
                    GeoFenceCommonModel geoFenceCommonModel38 = getViewModel().getGeoFenceCommonModel();
                }
                GeoFenceCommonModel geoFenceCommonModel39 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel39 != null && (geoFenceBody18 = geoFenceCommonModel39.getGeoFenceBody()) != null) {
                    coordinates5 = geoFenceBody18.getCentre();
                }
                if (coordinates5 == null) {
                    GeoFenceCommonModel geoFenceCommonModel40 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel40 != null && (geoFenceGetResponse7 = geoFenceCommonModel40.getGeoFenceGetResponse()) != null && (centre2 = geoFenceGetResponse7.getCentre()) != null) {
                        Double gpsLat11 = centre2.getGpsLat();
                        Intrinsics.checkNotNull(gpsLat11);
                        this.sourceLatitude = gpsLat11.doubleValue();
                        gpsLong2 = centre2.getGpsLong();
                        Intrinsics.checkNotNull(gpsLong2);
                        this.sourceLongitude = gpsLong2.doubleValue();
                        Unit unit10 = Unit.INSTANCE;
                    }
                    geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel3 != null && (geoFenceGetResponse8 = geoFenceCommonModel3.getGeoFenceGetResponse()) != null && (radius2 = geoFenceGetResponse8.getRadius()) != null) {
                        doubleValue = radius2.intValue();
                        this.radiusVal = (int) (doubleValue / 1000);
                        Unit unit82 = Unit.INSTANCE;
                    }
                    setRadiusButton();
                }
                GeoFenceCommonModel geoFenceCommonModel41 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel41 != null && (geoFenceBody19 = geoFenceCommonModel41.getGeoFenceBody()) != null && (centre3 = geoFenceBody19.getCentre()) != null) {
                    Double gpsLat12 = centre3.getGpsLat();
                    Intrinsics.checkNotNull(gpsLat12);
                    this.sourceLatitude = gpsLat12.doubleValue();
                    gpsLong2 = centre3.getGpsLong();
                    Intrinsics.checkNotNull(gpsLong2);
                    this.sourceLongitude = gpsLong2.doubleValue();
                    Unit unit102 = Unit.INSTANCE;
                }
                geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel3 != null) {
                    doubleValue = radius2.intValue();
                    this.radiusVal = (int) (doubleValue / 1000);
                    Unit unit822 = Unit.INSTANCE;
                }
                setRadiusButton();
            }
        } else if (hashCode == 108704329 && str.equals(AppConstants.GEO_FENCE_MODE_ROUTE)) {
            GeoFenceCommonModel geoFenceCommonModel42 = getViewModel().getGeoFenceCommonModel();
            Integer valueOf3 = geoFenceCommonModel42 != null ? Integer.valueOf(geoFenceCommonModel42.getFenceCreationMode()) : null;
            if (valueOf3 == null || valueOf3.intValue() != 6) {
                GeoFenceCommonModel geoFenceCommonModel43 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel43 != null && (geoFenceBody27 = geoFenceCommonModel43.getGeoFenceBody()) != null && (fenceName5 = geoFenceBody27.getFenceName()) != null) {
                    setFenceName(fenceName5);
                    Unit unit11 = Unit.INSTANCE;
                }
                GeoFenceCommonModel geoFenceCommonModel44 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel44 != null && (geoFenceBody26 = geoFenceCommonModel44.getGeoFenceBody()) != null && (sourceLocation7 = geoFenceBody26.getSourceLocation()) != null) {
                    Double gpsLat13 = sourceLocation7.getGpsLat();
                    Intrinsics.checkNotNull(gpsLat13);
                    this.sourceLatitude = gpsLat13.doubleValue();
                    Double gpsLong12 = sourceLocation7.getGpsLong();
                    Intrinsics.checkNotNull(gpsLong12);
                    this.sourceLongitude = gpsLong12.doubleValue();
                    setSourceAddressText();
                    Unit unit12 = Unit.INSTANCE;
                }
                GeoFenceCommonModel geoFenceCommonModel45 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel45 != null && (geoFenceBody25 = geoFenceCommonModel45.getGeoFenceBody()) != null && (destinationLocation = geoFenceBody25.getDestinationLocation()) != null) {
                    Double gpsLat14 = destinationLocation.getGpsLat();
                    Intrinsics.checkNotNull(gpsLat14);
                    this.destinationLatitude = gpsLat14.doubleValue();
                    Double gpsLong13 = destinationLocation.getGpsLong();
                    Intrinsics.checkNotNull(gpsLong13);
                    this.destinationLongitude = gpsLong13.doubleValue();
                    setDestinationAddressText();
                    Unit unit13 = Unit.INSTANCE;
                }
                GeoFenceCommonModel geoFenceCommonModel46 = getViewModel().getGeoFenceCommonModel();
                ArrayList<Coordinates> coordinates12 = (geoFenceCommonModel46 == null || (geoFenceBody24 = geoFenceCommonModel46.getGeoFenceBody()) == null) ? null : geoFenceBody24.getCoordinates();
                if ((coordinates12 == null || coordinates12.isEmpty()) == true) {
                    return;
                }
                GeoFenceCommonModel geoFenceCommonModel47 = getViewModel().getGeoFenceCommonModel();
                ArrayList<Coordinates> coordinates13 = (geoFenceCommonModel47 == null || (geoFenceBody23 = geoFenceCommonModel47.getGeoFenceBody()) == null) ? null : geoFenceBody23.getCoordinates();
                Intrinsics.checkNotNull(coordinates13);
                int size4 = coordinates13.size();
                Logger.INSTANCE.e("size: " + size4);
                this.latLngList = new ArrayList<>();
                GeoFenceCommonModel geoFenceCommonModel48 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel48 != null && (geoFenceBody22 = geoFenceCommonModel48.getGeoFenceBody()) != null) {
                    arrayList9 = geoFenceBody22.getCoordinates();
                }
                Intrinsics.checkNotNull(arrayList9);
                for (Object obj4 : arrayList9) {
                    int i6 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    Coordinates coordinates14 = (Coordinates) obj4;
                    Logger.INSTANCE.e("Index: " + i2);
                    if (i2 != size4 - 1 && (arrayList4 = this.latLngList) != null) {
                        Double gpsLat15 = coordinates14.getGpsLat();
                        Intrinsics.checkNotNull(gpsLat15);
                        double doubleValue5 = gpsLat15.doubleValue();
                        Double gpsLong14 = coordinates14.getGpsLong();
                        Intrinsics.checkNotNull(gpsLong14);
                        arrayList4.add(new LatLng(doubleValue5, gpsLong14.doubleValue()));
                    }
                    i2 = i6;
                }
                return;
            }
            GeoFenceCommonModel geoFenceCommonModel49 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel49 != null && (geoFenceGetResponse18 = geoFenceCommonModel49.getGeoFenceGetResponse()) != null && (fenceName6 = geoFenceGetResponse18.getFenceName()) != null) {
                setFenceName(fenceName6);
                Unit unit14 = Unit.INSTANCE;
            }
            GeoFenceCommonModel geoFenceCommonModel50 = getViewModel().getGeoFenceCommonModel();
            Double gpsLat16 = (geoFenceCommonModel50 == null || (geoFenceBody40 = geoFenceCommonModel50.getGeoFenceBody()) == null || (sourceLocation12 = geoFenceBody40.getSourceLocation()) == null) ? null : sourceLocation12.getGpsLat();
            GeoFenceCommonModel geoFenceCommonModel51 = getViewModel().getGeoFenceCommonModel();
            if (Intrinsics.areEqual(gpsLat16, (geoFenceCommonModel51 == null || (geoFenceGetResponse17 = geoFenceCommonModel51.getGeoFenceGetResponse()) == null || (sourceLocation11 = geoFenceGetResponse17.getSourceLocation()) == null) ? null : sourceLocation11.getGpsLat())) {
                GeoFenceCommonModel geoFenceCommonModel52 = getViewModel().getGeoFenceCommonModel();
                Double gpsLong15 = (geoFenceCommonModel52 == null || (geoFenceBody39 = geoFenceCommonModel52.getGeoFenceBody()) == null || (sourceLocation10 = geoFenceBody39.getSourceLocation()) == null) ? null : sourceLocation10.getGpsLong();
                GeoFenceCommonModel geoFenceCommonModel53 = getViewModel().getGeoFenceCommonModel();
            }
            GeoFenceCommonModel geoFenceCommonModel54 = getViewModel().getGeoFenceCommonModel();
            if (((geoFenceCommonModel54 == null || (geoFenceBody28 = geoFenceCommonModel54.getGeoFenceBody()) == null) ? null : geoFenceBody28.getSourceLocation()) == null) {
                GeoFenceCommonModel geoFenceCommonModel55 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel55 != null && (geoFenceGetResponse11 = geoFenceCommonModel55.getGeoFenceGetResponse()) != null && (sourceLocation8 = geoFenceGetResponse11.getSourceLocation()) != null) {
                    Double gpsLat17 = sourceLocation8.getGpsLat();
                    Intrinsics.checkNotNull(gpsLat17);
                    this.sourceLatitude = gpsLat17.doubleValue();
                    gpsLong3 = sourceLocation8.getGpsLong();
                    Intrinsics.checkNotNull(gpsLong3);
                    this.sourceLongitude = gpsLong3.doubleValue();
                    Unit unit15 = Unit.INSTANCE;
                }
                setSourceAddressText();
                GeoFenceCommonModel geoFenceCommonModel56 = getViewModel().getGeoFenceCommonModel();
                gpsLat = (geoFenceCommonModel56 != null || (geoFenceBody37 = geoFenceCommonModel56.getGeoFenceBody()) == null || (destinationLocation6 = geoFenceBody37.getDestinationLocation()) == null) ? null : destinationLocation6.getGpsLat();
                geoFenceCommonModel4 = getViewModel().getGeoFenceCommonModel();
                if (Intrinsics.areEqual(gpsLat, (geoFenceCommonModel4 != null || (geoFenceGetResponse16 = geoFenceCommonModel4.getGeoFenceGetResponse()) == null || (destinationLocation5 = geoFenceGetResponse16.getDestinationLocation()) == null) ? null : destinationLocation5.getGpsLat())) {
                    GeoFenceCommonModel geoFenceCommonModel57 = getViewModel().getGeoFenceCommonModel();
                    Double gpsLong16 = (geoFenceCommonModel57 == null || (geoFenceBody36 = geoFenceCommonModel57.getGeoFenceBody()) == null || (destinationLocation4 = geoFenceBody36.getDestinationLocation()) == null) ? null : destinationLocation4.getGpsLong();
                    GeoFenceCommonModel geoFenceCommonModel58 = getViewModel().getGeoFenceCommonModel();
                }
                geoFenceCommonModel5 = getViewModel().getGeoFenceCommonModel();
                if (((geoFenceCommonModel5 != null || (geoFenceBody29 = geoFenceCommonModel5.getGeoFenceBody()) == null) ? null : geoFenceBody29.getDestinationLocation()) == null) {
                    GeoFenceCommonModel geoFenceCommonModel59 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel59 != null && (geoFenceGetResponse12 = geoFenceCommonModel59.getGeoFenceGetResponse()) != null && (destinationLocation2 = geoFenceGetResponse12.getDestinationLocation()) != null) {
                        Double gpsLat18 = destinationLocation2.getGpsLat();
                        Intrinsics.checkNotNull(gpsLat18);
                        this.destinationLatitude = gpsLat18.doubleValue();
                        gpsLong4 = destinationLocation2.getGpsLong();
                        Intrinsics.checkNotNull(gpsLong4);
                        this.destinationLongitude = gpsLong4.doubleValue();
                        Unit unit16 = Unit.INSTANCE;
                    }
                    setDestinationAddressText();
                    GeoFenceCommonModel geoFenceCommonModel60 = getViewModel().getGeoFenceCommonModel();
                    coordinates3 = (geoFenceCommonModel60 != null || (geoFenceBody34 = geoFenceCommonModel60.getGeoFenceBody()) == null) ? null : geoFenceBody34.getCoordinates();
                    geoFenceCommonModel6 = getViewModel().getGeoFenceCommonModel();
                    if (!Intrinsics.areEqual(coordinates3, (geoFenceCommonModel6 != null || (geoFenceGetResponse15 = geoFenceCommonModel6.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse15.getCoordinates())) {
                        GeoFenceCommonModel geoFenceCommonModel61 = getViewModel().getGeoFenceCommonModel();
                        if (((geoFenceCommonModel61 == null || (geoFenceBody33 = geoFenceCommonModel61.getGeoFenceBody()) == null) ? null : geoFenceBody33.getCoordinates()) == null) {
                            GeoFenceCommonModel geoFenceCommonModel62 = getViewModel().getGeoFenceCommonModel();
                            if (((geoFenceCommonModel62 == null || (geoFenceBody32 = geoFenceCommonModel62.getGeoFenceBody()) == null || (coordinates4 = geoFenceBody32.getCoordinates()) == null || coordinates4.size() != 0) ? false : true) != false) {
                                GeoFenceCommonModel geoFenceCommonModel63 = getViewModel().getGeoFenceCommonModel();
                                List<LocationData> coordinates15 = (geoFenceCommonModel63 == null || (geoFenceGetResponse14 = geoFenceCommonModel63.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse14.getCoordinates();
                                Intrinsics.checkNotNull(coordinates15);
                                int size5 = coordinates15.size();
                                Logger.INSTANCE.e("size: " + size5);
                                this.latLngList = new ArrayList<>();
                                GeoFenceCommonModel geoFenceCommonModel64 = getViewModel().getGeoFenceCommonModel();
                                if (geoFenceCommonModel64 != null && (geoFenceGetResponse13 = geoFenceCommonModel64.getGeoFenceGetResponse()) != null) {
                                    list = geoFenceGetResponse13.getCoordinates();
                                }
                                Intrinsics.checkNotNull(list);
                                for (Object obj5 : list) {
                                    int i7 = i2 + 1;
                                    if (i2 < 0) {
                                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                                    }
                                    LocationData locationData2 = (LocationData) obj5;
                                    Logger.INSTANCE.e("Index: " + i2);
                                    if (i2 != size5 - 1 && (arrayList6 = this.latLngList) != null) {
                                        Double gpsLat19 = locationData2.getGpsLat();
                                        Intrinsics.checkNotNull(gpsLat19);
                                        double doubleValue6 = gpsLat19.doubleValue();
                                        Double gpsLong17 = locationData2.getGpsLong();
                                        Intrinsics.checkNotNull(gpsLong17);
                                        arrayList6.add(new LatLng(doubleValue6, gpsLong17.doubleValue()));
                                    }
                                    i2 = i7;
                                }
                                return;
                            }
                        }
                    }
                    GeoFenceCommonModel geoFenceCommonModel65 = getViewModel().getGeoFenceCommonModel();
                    ArrayList<Coordinates> coordinates16 = (geoFenceCommonModel65 != null || (geoFenceBody31 = geoFenceCommonModel65.getGeoFenceBody()) == null) ? null : geoFenceBody31.getCoordinates();
                    Intrinsics.checkNotNull(coordinates16);
                    int size6 = coordinates16.size();
                    Logger.INSTANCE.e("size: " + size6);
                    this.latLngList = new ArrayList<>();
                    geoFenceCommonModel7 = getViewModel().getGeoFenceCommonModel();
                    if (geoFenceCommonModel7 != null && (geoFenceBody30 = geoFenceCommonModel7.getGeoFenceBody()) != null) {
                        arrayList8 = geoFenceBody30.getCoordinates();
                    }
                    Intrinsics.checkNotNull(arrayList8);
                    for (Object obj6 : arrayList8) {
                        int i8 = i2 + 1;
                        if (i2 < 0) {
                            CollectionsKt__CollectionsKt.throwIndexOverflow();
                        }
                        Coordinates coordinates17 = (Coordinates) obj6;
                        Logger.INSTANCE.e("Index: " + i2);
                        if (i2 != size6 - 1 && (arrayList5 = this.latLngList) != null) {
                            Double gpsLat20 = coordinates17.getGpsLat();
                            Intrinsics.checkNotNull(gpsLat20);
                            double doubleValue7 = gpsLat20.doubleValue();
                            Double gpsLong18 = coordinates17.getGpsLong();
                            Intrinsics.checkNotNull(gpsLong18);
                            arrayList5.add(new LatLng(doubleValue7, gpsLong18.doubleValue()));
                        }
                        i2 = i8;
                    }
                }
                geoFenceCommonModel8 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel8 != null && (geoFenceBody35 = geoFenceCommonModel8.getGeoFenceBody()) != null && (destinationLocation3 = geoFenceBody35.getDestinationLocation()) != null) {
                    Double gpsLat21 = destinationLocation3.getGpsLat();
                    Intrinsics.checkNotNull(gpsLat21);
                    this.destinationLatitude = gpsLat21.doubleValue();
                    gpsLong4 = destinationLocation3.getGpsLong();
                    Intrinsics.checkNotNull(gpsLong4);
                    this.destinationLongitude = gpsLong4.doubleValue();
                    Unit unit162 = Unit.INSTANCE;
                }
                setDestinationAddressText();
                GeoFenceCommonModel geoFenceCommonModel602 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel602 != null) {
                }
                geoFenceCommonModel6 = getViewModel().getGeoFenceCommonModel();
                if (!Intrinsics.areEqual(coordinates3, (geoFenceCommonModel6 != null || (geoFenceGetResponse15 = geoFenceCommonModel6.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse15.getCoordinates())) {
                }
                GeoFenceCommonModel geoFenceCommonModel652 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel652 != null) {
                }
                Intrinsics.checkNotNull(coordinates16);
                int size62 = coordinates16.size();
                Logger.INSTANCE.e("size: " + size62);
                this.latLngList = new ArrayList<>();
                geoFenceCommonModel7 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel7 != null) {
                    arrayList8 = geoFenceBody30.getCoordinates();
                }
                Intrinsics.checkNotNull(arrayList8);
                while (r1.hasNext()) {
                }
            }
            GeoFenceCommonModel geoFenceCommonModel66 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel66 != null && (geoFenceBody38 = geoFenceCommonModel66.getGeoFenceBody()) != null && (sourceLocation9 = geoFenceBody38.getSourceLocation()) != null) {
                Double gpsLat22 = sourceLocation9.getGpsLat();
                Intrinsics.checkNotNull(gpsLat22);
                this.sourceLatitude = gpsLat22.doubleValue();
                gpsLong3 = sourceLocation9.getGpsLong();
                Intrinsics.checkNotNull(gpsLong3);
                this.sourceLongitude = gpsLong3.doubleValue();
                Unit unit152 = Unit.INSTANCE;
            }
            setSourceAddressText();
            GeoFenceCommonModel geoFenceCommonModel562 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel562 != null) {
            }
            geoFenceCommonModel4 = getViewModel().getGeoFenceCommonModel();
            if (Intrinsics.areEqual(gpsLat, (geoFenceCommonModel4 != null || (geoFenceGetResponse16 = geoFenceCommonModel4.getGeoFenceGetResponse()) == null || (destinationLocation5 = geoFenceGetResponse16.getDestinationLocation()) == null) ? null : destinationLocation5.getGpsLat())) {
            }
            geoFenceCommonModel5 = getViewModel().getGeoFenceCommonModel();
            if (((geoFenceCommonModel5 != null || (geoFenceBody29 = geoFenceCommonModel5.getGeoFenceBody()) == null) ? null : geoFenceBody29.getDestinationLocation()) == null) {
            }
            geoFenceCommonModel8 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel8 != null) {
                Double gpsLat212 = destinationLocation3.getGpsLat();
                Intrinsics.checkNotNull(gpsLat212);
                this.destinationLatitude = gpsLat212.doubleValue();
                gpsLong4 = destinationLocation3.getGpsLong();
                Intrinsics.checkNotNull(gpsLong4);
                this.destinationLongitude = gpsLong4.doubleValue();
                Unit unit1622 = Unit.INSTANCE;
            }
            setDestinationAddressText();
            GeoFenceCommonModel geoFenceCommonModel6022 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel6022 != null) {
            }
            geoFenceCommonModel6 = getViewModel().getGeoFenceCommonModel();
            if (!Intrinsics.areEqual(coordinates3, (geoFenceCommonModel6 != null || (geoFenceGetResponse15 = geoFenceCommonModel6.getGeoFenceGetResponse()) == null) ? null : geoFenceGetResponse15.getCoordinates())) {
            }
            GeoFenceCommonModel geoFenceCommonModel6522 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel6522 != null) {
            }
            Intrinsics.checkNotNull(coordinates16);
            int size622 = coordinates16.size();
            Logger.INSTANCE.e("size: " + size622);
            this.latLngList = new ArrayList<>();
            geoFenceCommonModel7 = getViewModel().getGeoFenceCommonModel();
            if (geoFenceCommonModel7 != null) {
            }
            Intrinsics.checkNotNull(arrayList8);
            while (r1.hasNext()) {
            }
        }
    }

    private final void setRadiusButton() {
        AppCompatButton btnRad50;
        String str;
        AppCompatButton[] appCompatButtonArr = this.radButtonList;
        if (appCompatButtonArr != null) {
            for (AppCompatButton appCompatButton : appCompatButtonArr) {
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                ExtensionsKt.onSquareBtnDeselect(appCompatButton, requireContext);
            }
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%.0f", Arrays.copyOf(new Object[]{Double.valueOf(this.radiusVal)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        int hashCode = format.hashCode();
        if (hashCode == 1567) {
            if (format.equals("10")) {
                btnRad50 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad10);
                str = "btnRad10";
                Intrinsics.checkNotNullExpressionValue(btnRad50, str);
            }
            int i2 = com.psa.mym.mycitroenconnect.R.id.btnRadEditVal;
            ((AppCompatButton) _$_findCachedViewById(i2)).setText(format);
            AppCompatButton btnRadEditVal = (AppCompatButton) _$_findCachedViewById(i2);
            Intrinsics.checkNotNullExpressionValue(btnRadEditVal, "btnRadEditVal");
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            ExtensionsKt.onSquareBtnSelected(btnRadEditVal, requireContext2);
            AppCompatButton btnRadEditVal2 = (AppCompatButton) _$_findCachedViewById(i2);
            Intrinsics.checkNotNullExpressionValue(btnRadEditVal2, "btnRadEditVal");
            ExtensionsKt.show(btnRadEditVal2);
            AppCompatButton btnRadEdit = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEdit);
            Intrinsics.checkNotNullExpressionValue(btnRadEdit, "btnRadEdit");
            ExtensionsKt.hide(btnRadEdit);
            AppCompatButton btnRad502 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad50);
            Intrinsics.checkNotNullExpressionValue(btnRad502, "btnRad50");
            ExtensionsKt.hide(btnRad502);
            return;
        } else if (hashCode == 1598) {
            if (format.equals("20")) {
                btnRad50 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad20);
                str = "btnRad20";
                Intrinsics.checkNotNullExpressionValue(btnRad50, str);
            }
            int i22 = com.psa.mym.mycitroenconnect.R.id.btnRadEditVal;
            ((AppCompatButton) _$_findCachedViewById(i22)).setText(format);
            AppCompatButton btnRadEditVal3 = (AppCompatButton) _$_findCachedViewById(i22);
            Intrinsics.checkNotNullExpressionValue(btnRadEditVal3, "btnRadEditVal");
            Context requireContext22 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext22, "requireContext()");
            ExtensionsKt.onSquareBtnSelected(btnRadEditVal3, requireContext22);
            AppCompatButton btnRadEditVal22 = (AppCompatButton) _$_findCachedViewById(i22);
            Intrinsics.checkNotNullExpressionValue(btnRadEditVal22, "btnRadEditVal");
            ExtensionsKt.show(btnRadEditVal22);
            AppCompatButton btnRadEdit2 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEdit);
            Intrinsics.checkNotNullExpressionValue(btnRadEdit2, "btnRadEdit");
            ExtensionsKt.hide(btnRadEdit2);
            AppCompatButton btnRad5022 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad50);
            Intrinsics.checkNotNullExpressionValue(btnRad5022, "btnRad50");
            ExtensionsKt.hide(btnRad5022);
            return;
        } else if (hashCode == 1629) {
            if (format.equals("30")) {
                btnRad50 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad30);
                str = "btnRad30";
                Intrinsics.checkNotNullExpressionValue(btnRad50, str);
            }
            int i222 = com.psa.mym.mycitroenconnect.R.id.btnRadEditVal;
            ((AppCompatButton) _$_findCachedViewById(i222)).setText(format);
            AppCompatButton btnRadEditVal32 = (AppCompatButton) _$_findCachedViewById(i222);
            Intrinsics.checkNotNullExpressionValue(btnRadEditVal32, "btnRadEditVal");
            Context requireContext222 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext222, "requireContext()");
            ExtensionsKt.onSquareBtnSelected(btnRadEditVal32, requireContext222);
            AppCompatButton btnRadEditVal222 = (AppCompatButton) _$_findCachedViewById(i222);
            Intrinsics.checkNotNullExpressionValue(btnRadEditVal222, "btnRadEditVal");
            ExtensionsKt.show(btnRadEditVal222);
            AppCompatButton btnRadEdit22 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEdit);
            Intrinsics.checkNotNullExpressionValue(btnRadEdit22, "btnRadEdit");
            ExtensionsKt.hide(btnRadEdit22);
            AppCompatButton btnRad50222 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad50);
            Intrinsics.checkNotNullExpressionValue(btnRad50222, "btnRad50");
            ExtensionsKt.hide(btnRad50222);
            return;
        } else {
            if (hashCode != 1660) {
                if (hashCode == 1691 && format.equals("50")) {
                    btnRad50 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad50);
                    Intrinsics.checkNotNullExpressionValue(btnRad50, "btnRad50");
                }
            } else if (format.equals("40")) {
                btnRad50 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad40);
                str = "btnRad40";
                Intrinsics.checkNotNullExpressionValue(btnRad50, str);
            }
            int i2222 = com.psa.mym.mycitroenconnect.R.id.btnRadEditVal;
            ((AppCompatButton) _$_findCachedViewById(i2222)).setText(format);
            AppCompatButton btnRadEditVal322 = (AppCompatButton) _$_findCachedViewById(i2222);
            Intrinsics.checkNotNullExpressionValue(btnRadEditVal322, "btnRadEditVal");
            Context requireContext2222 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2222, "requireContext()");
            ExtensionsKt.onSquareBtnSelected(btnRadEditVal322, requireContext2222);
            AppCompatButton btnRadEditVal2222 = (AppCompatButton) _$_findCachedViewById(i2222);
            Intrinsics.checkNotNullExpressionValue(btnRadEditVal2222, "btnRadEditVal");
            ExtensionsKt.show(btnRadEditVal2222);
            AppCompatButton btnRadEdit222 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEdit);
            Intrinsics.checkNotNullExpressionValue(btnRadEdit222, "btnRadEdit");
            ExtensionsKt.hide(btnRadEdit222);
            AppCompatButton btnRad502222 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad50);
            Intrinsics.checkNotNullExpressionValue(btnRad502222, "btnRad50");
            ExtensionsKt.hide(btnRad502222);
            return;
        }
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        ExtensionsKt.onSquareBtnSelected(btnRad50, requireContext3);
    }

    private final void setRadiusFenceData() {
        GeoFenceBody geoFenceBody;
        GeoFenceBody geoFenceBody2;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel == null || (geoFenceBody = geoFenceCommonModel.getGeoFenceBody()) == null) {
            return;
        }
        geoFenceBody.setFenceType("location");
        geoFenceBody.setFenceGeometry(AppConstants.GEO_FENCE_GEOMETRY_CIRCLE);
        geoFenceBody.setFenceStatus("D");
        GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
        geoFenceBody.setTransitionType((geoFenceCommonModel2 == null || (geoFenceBody2 = geoFenceCommonModel2.getGeoFenceBody()) == null) ? null : geoFenceBody2.getTransitionType());
        geoFenceBody.setCentre(new Coordinates(Double.valueOf(this.sourceLatitude), Double.valueOf(this.sourceLongitude)));
        geoFenceBody.setCoordinates(null);
        geoFenceBody.setRadius(Double.valueOf(this.radiusVal * 1000));
        geoFenceBody.setFenceName(String.valueOf(((AppCompatEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edtGeoFenceName)).getText()));
        geoFenceBody.setFenceMode(AppConstants.GEO_FENCE_MODE_RADIUS);
        Logger logger = Logger.INSTANCE;
        logger.e("GeoFenceBody Radius " + new Gson().toJson(geoFenceBody));
    }

    private final void setRouteFenceData() {
        GeoFenceBody geoFenceBody;
        ArrayList<Coordinates> coordinates;
        GeoFenceBody geoFenceBody2;
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel == null || (geoFenceBody = geoFenceCommonModel.getGeoFenceBody()) == null) {
            return;
        }
        geoFenceBody.setFenceType("location");
        geoFenceBody.setFenceGeometry(AppConstants.GEO_FENCE_GEOMETRY_POLYGON);
        geoFenceBody.setFenceStatus("D");
        GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
        geoFenceBody.setTransitionType((geoFenceCommonModel2 == null || (geoFenceBody2 = geoFenceCommonModel2.getGeoFenceBody()) == null) ? null : geoFenceBody2.getTransitionType());
        geoFenceBody.setCoordinates(new ArrayList<>());
        for (MapPolygonData mapPolygonData : this.polygonDataList) {
            ArrayList<Coordinates> coordinates2 = geoFenceBody.getCoordinates();
            if (coordinates2 != null) {
                coordinates2.add(new Coordinates(Double.valueOf(mapPolygonData.getLatitude()), Double.valueOf(mapPolygonData.getLongitude())));
            }
        }
        if (this.polygonDataList.size() > 0 && (coordinates = geoFenceBody.getCoordinates()) != null) {
            coordinates.add(new Coordinates(Double.valueOf(this.polygonDataList.get(0).getLatitude()), Double.valueOf(this.polygonDataList.get(0).getLongitude())));
        }
        geoFenceBody.setSourceLocation(new Coordinates(Double.valueOf(this.sourceLatitude), Double.valueOf(this.sourceLongitude)));
        geoFenceBody.setDestinationLocation(new Coordinates(Double.valueOf(this.destinationLatitude), Double.valueOf(this.destinationLongitude)));
        geoFenceBody.setFenceName(String.valueOf(((AppCompatEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edtGeoFenceName)).getText()));
        geoFenceBody.setFenceMode(AppConstants.GEO_FENCE_MODE_ROUTE);
        Logger logger = Logger.INSTANCE;
        logger.e("GeoFenceBody Route " + new Gson().toJson(geoFenceBody));
    }

    private final void setSourceAddressText() {
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.sourceAddress = companion.getAddressNameString(requireContext, this.sourceLatitude, this.sourceLongitude);
    }

    private final void setUIData() {
        GeoFenceBody geoFenceBody;
        AppCompatEditText appCompatEditText;
        int i2;
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
        String valueOf2 = String.valueOf(str);
        this.fenceType = valueOf2;
        int hashCode = valueOf2.hashCode();
        if (hashCode != -1349088399) {
            if (hashCode != -938578798) {
                if (hashCode == 108704329 && valueOf2.equals(AppConstants.GEO_FENCE_MODE_ROUTE)) {
                    int i3 = com.psa.mym.mycitroenconnect.R.id.edtGeoFenceName;
                    ((AppCompatEditText) _$_findCachedViewById(i3)).setHint(getString(R.string.route_fence_name));
                    AppCompatButton btnChangeRoute = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnChangeRoute);
                    Intrinsics.checkNotNullExpressionValue(btnChangeRoute, "btnChangeRoute");
                    ExtensionsKt.show(btnChangeRoute);
                    AppCompatTextView tvSetRadius = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvSetRadius);
                    Intrinsics.checkNotNullExpressionValue(tvSetRadius, "tvSetRadius");
                    ExtensionsKt.hide(tvSetRadius);
                    LinearLayoutCompat layoutRadiusButtons = (LinearLayoutCompat) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.layoutRadiusButtons);
                    Intrinsics.checkNotNullExpressionValue(layoutRadiusButtons, "layoutRadiusButtons");
                    ExtensionsKt.hide(layoutRadiusButtons);
                    AppCompatButton btnBack = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnBack);
                    Intrinsics.checkNotNullExpressionValue(btnBack, "btnBack");
                    ExtensionsKt.hide(btnBack);
                    ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvSaveGeoFenceAs)).setText(getString(R.string.save_this_route_geo_fence_as));
                    appCompatEditText = (AppCompatEditText) _$_findCachedViewById(i3);
                    i2 = R.string.route_fence;
                    appCompatEditText.setText(getString(i2));
                }
            } else if (valueOf2.equals(AppConstants.GEO_FENCE_MODE_RADIUS)) {
                int i4 = com.psa.mym.mycitroenconnect.R.id.edtGeoFenceName;
                ((AppCompatEditText) _$_findCachedViewById(i4)).setHint(getString(R.string.radius_fence_name));
                AppCompatTextView tvSetRadius2 = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvSetRadius);
                Intrinsics.checkNotNullExpressionValue(tvSetRadius2, "tvSetRadius");
                ExtensionsKt.show(tvSetRadius2);
                LinearLayoutCompat layoutRadiusButtons2 = (LinearLayoutCompat) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.layoutRadiusButtons);
                Intrinsics.checkNotNullExpressionValue(layoutRadiusButtons2, "layoutRadiusButtons");
                ExtensionsKt.show(layoutRadiusButtons2);
                AppCompatButton btnBack2 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnBack);
                Intrinsics.checkNotNullExpressionValue(btnBack2, "btnBack");
                ExtensionsKt.show(btnBack2);
                AppCompatButton btnChangeRoute2 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnChangeRoute);
                Intrinsics.checkNotNullExpressionValue(btnChangeRoute2, "btnChangeRoute");
                ExtensionsKt.hide(btnChangeRoute2);
                ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvSaveGeoFenceAs)).setText(getString(R.string.save_this_geo_fence_radius_as));
                appCompatEditText = (AppCompatEditText) _$_findCachedViewById(i4);
                i2 = R.string.radius_fence;
                appCompatEditText.setText(getString(i2));
            }
        } else if (valueOf2.equals(AppConstants.GEO_FENCE_MODE_CUSTOM)) {
            int i5 = com.psa.mym.mycitroenconnect.R.id.edtGeoFenceName;
            ((AppCompatEditText) _$_findCachedViewById(i5)).setHint(getString(R.string.custom_fence_name));
            AppCompatButton btnBack3 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnBack);
            Intrinsics.checkNotNullExpressionValue(btnBack3, "btnBack");
            ExtensionsKt.show(btnBack3);
            AppCompatTextView tvSetRadius3 = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvSetRadius);
            Intrinsics.checkNotNullExpressionValue(tvSetRadius3, "tvSetRadius");
            ExtensionsKt.hide(tvSetRadius3);
            LinearLayoutCompat layoutRadiusButtons3 = (LinearLayoutCompat) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.layoutRadiusButtons);
            Intrinsics.checkNotNullExpressionValue(layoutRadiusButtons3, "layoutRadiusButtons");
            ExtensionsKt.hide(layoutRadiusButtons3);
            AppCompatButton btnChangeRoute3 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnChangeRoute);
            Intrinsics.checkNotNullExpressionValue(btnChangeRoute3, "btnChangeRoute");
            ExtensionsKt.hide(btnChangeRoute3);
            ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvSaveGeoFenceAs)).setText(getString(R.string.save_this_custom_geo_fence_as));
            appCompatEditText = (AppCompatEditText) _$_findCachedViewById(i5);
            i2 = R.string.custom_fence;
            appCompatEditText.setText(getString(i2));
        }
        setModelData();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean validateCustomFenceData() {
        boolean z;
        boolean isBlank;
        int i2 = com.psa.mym.mycitroenconnect.R.id.edtGeoFenceName;
        Editable text = ((AppCompatEditText) _$_findCachedViewById(i2)).getText();
        if (!(text == null || text.length() == 0)) {
            Editable text2 = ((AppCompatEditText) _$_findCachedViewById(i2)).getText();
            if (text2 != null) {
                isBlank = StringsKt__StringsJVMKt.isBlank(text2);
                if (!isBlank) {
                    z = false;
                    if (!z) {
                        setCustomFenceData();
                        return true;
                    }
                }
            }
            z = true;
            if (!z) {
            }
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getString(R.string.enter_fence_name);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.enter_fence_name)");
        ExtensionsKt.showToast(requireContext, string);
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean validateRadiusFenceData() {
        boolean z;
        Context requireContext;
        String string;
        String str;
        boolean isBlank;
        if (isRadiusSelected()) {
            int i2 = com.psa.mym.mycitroenconnect.R.id.edtGeoFenceName;
            Editable text = ((AppCompatEditText) _$_findCachedViewById(i2)).getText();
            if (text != null) {
                isBlank = StringsKt__StringsJVMKt.isBlank(text);
                if (!isBlank) {
                    z = false;
                    if (!z) {
                        Editable text2 = ((AppCompatEditText) _$_findCachedViewById(i2)).getText();
                        if (!(text2 == null || text2.length() == 0)) {
                            setRadiusFenceData();
                            return true;
                        }
                    }
                    requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    string = getString(R.string.enter_fence_name);
                    str = "getString(R.string.enter_fence_name)";
                }
            }
            z = true;
            if (!z) {
            }
            requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            string = getString(R.string.enter_fence_name);
            str = "getString(R.string.enter_fence_name)";
        } else {
            requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            string = getString(R.string.select_radius);
            str = "getString(R.string.select_radius)";
        }
        Intrinsics.checkNotNullExpressionValue(string, str);
        ExtensionsKt.showToast(requireContext, string);
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean validateRouteFenceData() {
        boolean z;
        boolean isBlank;
        int i2 = com.psa.mym.mycitroenconnect.R.id.edtGeoFenceName;
        Editable text = ((AppCompatEditText) _$_findCachedViewById(i2)).getText();
        if (!(text == null || text.length() == 0)) {
            Editable text2 = ((AppCompatEditText) _$_findCachedViewById(i2)).getText();
            if (text2 != null) {
                isBlank = StringsKt__StringsJVMKt.isBlank(text2);
                if (!isBlank) {
                    z = false;
                    if (!z) {
                        setRouteFenceData();
                        return true;
                    }
                }
            }
            z = true;
            if (!z) {
            }
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getString(R.string.enter_fence_name);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.enter_fence_name)");
        ExtensionsKt.showToast(requireContext, string);
        return false;
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

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        GeoFenceActivity geoFenceActivity;
        GeoFenceCommonModel geoFenceCommonModel;
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEdit)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEditVal))) {
            Bundle bundle = new Bundle();
            int i2 = com.psa.mym.mycitroenconnect.R.id.btnRadEditVal;
            if (((AppCompatButton) _$_findCachedViewById(i2)).getVisibility() == 0) {
                bundle.putString(AppConstants.SPEED_SELECTED_VAL, ((AppCompatButton) _$_findCachedViewById(i2)).getText().toString());
            }
            SpeedSettingFragment speedSettingFragment = new SpeedSettingFragment();
            bundle.putString("login", AppConstants.PAGE_MODE_RADIUS_SETTINGS);
            speedSettingFragment.setArguments(bundle);
            speedSettingFragment.show(getChildFragmentManager(), SpeedSettingFragment.TAG);
            speedSettingFragment.setCancelable(false);
            return;
        }
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad10)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad20)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad30)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad40)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRad50))) {
            AppCompatButton[] appCompatButtonArr = this.radButtonList;
            if (appCompatButtonArr != null) {
                for (AppCompatButton appCompatButton : appCompatButtonArr) {
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    ExtensionsKt.onSquareBtnDeselect(appCompatButton, requireContext);
                }
            }
            Objects.requireNonNull(view, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatButton");
            AppCompatButton appCompatButton2 = (AppCompatButton) view;
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            ExtensionsKt.onSquareBtnSelected(appCompatButton2, requireContext2);
            this.radiusVal = Double.parseDouble(appCompatButton2.getText().toString());
            displayRadiusFence();
        } else if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnChangeRoute))) {
            navigateToGeoFenceMapFragment();
        } else if (!Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnNext))) {
            if (!Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnBack)) || (geoFenceActivity = this.parentActivity) == null) {
                return;
            }
            geoFenceActivity.handleBackClick();
        } else if (validate() && (geoFenceCommonModel = getViewModel().getGeoFenceCommonModel()) != null) {
            int fenceCreationMode = geoFenceCommonModel.getFenceCreationMode();
            if (fenceCreationMode == 5) {
                navigateToSetTimeFragment();
            } else if (fenceCreationMode == 6 || fenceCreationMode == 7) {
                GeoFenceActivity geoFenceActivity2 = this.parentActivity;
                if (geoFenceActivity2 != null) {
                    geoFenceActivity2.navigateToSummaryFragment();
                }
            } else {
                Logger.INSTANCE.e("Fence Creation Mode: " + fenceCreationMode);
            }
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
        return inflater.inflate(R.layout.fragment_geo_fence_map_settings, viewGroup, false);
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(@NotNull GoogleMap map) {
        Intrinsics.checkNotNullParameter(map, "map");
        this.googleMap = map;
        if (map == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            map = null;
        }
        UiSettings uiSettings = map.getUiSettings();
        uiSettings.setAllGesturesEnabled(true);
        uiSettings.setZoomControlsEnabled(false);
        uiSettings.setZoomGesturesEnabled(true);
        uiSettings.setMyLocationButtonEnabled(false);
        uiSettings.setScrollGesturesEnabled(true);
        uiSettings.setIndoorLevelPickerEnabled(false);
        map.setIndoorEnabled(false);
        uiSettings.setCompassEnabled(false);
        uiSettings.setMapToolbarEnabled(false);
        setMapData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onRadiusValueSet(@NotNull MessageEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        onRadiusDialogDismiss(event.getMessage());
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setUIData();
        setMapData();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initView();
    }

    public final boolean validate() {
        String str = this.fenceType;
        int hashCode = str.hashCode();
        if (hashCode != -1349088399) {
            if (hashCode != -938578798) {
                if (hashCode == 108704329 && str.equals(AppConstants.GEO_FENCE_MODE_ROUTE)) {
                    return validateRouteFenceData();
                }
            } else if (str.equals(AppConstants.GEO_FENCE_MODE_RADIUS)) {
                return validateRadiusFenceData();
            }
        } else if (str.equals(AppConstants.GEO_FENCE_MODE_CUSTOM)) {
            return validateCustomFenceData();
        }
        return false;
    }
}
