package com.psa.mym.mycitroenconnect.controller.fragments.charging_station;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.maps.model.Geometry;
import com.google.maps.model.PlacesSearchResult;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.MainActivity;
import com.psa.mym.mycitroenconnect.controller.activities.dashboard.SearchLocationActivity;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.LocationUtility;
import com.psa.mym.mycitroenconnect.utils.NearbySearch;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.infowindow.MapWrapperLayout;
import com.psa.mym.mycitroenconnect.views.infowindow.OnInfoWindowElemTouchListener;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import kotlin.Deprecated;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ChargingStationFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final int REQUEST_DESTINATION_CODE = 453;
    private static final float ZOOM_LEVEL = 15.0f;
    @Nullable
    private LatLng carLatLng;
    @Nullable
    private GoogleMap googleMap;
    @Nullable
    private AppCompatImageView infoButton1;
    @Nullable
    private MaterialButton infoButton2;
    @Nullable
    private OnInfoWindowElemTouchListener infoButtonListener;
    @Nullable
    private OnInfoWindowElemTouchListener infoButtonListener2;
    @Nullable
    private ViewGroup infoWindow;
    @Nullable
    private LocationUtility locationUtility;
    private SupportMapFragment mapFragment;
    private MapWrapperLayout mapWrapperLayout;
    @Nullable
    private String param1;
    @Nullable
    private MainActivity parentActivity;
    @Nullable
    private View rootView;
    @Nullable
    private LatLng selectedLatLng;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private double destinationLatitude = 23.0153d;
    private double destinationLongitude = 72.5918d;
    @NotNull
    private String destinationAddress = "Geeta Mandir Bus Stand";
    @NotNull
    private String vehicleType = "";

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final ChargingStationFragment newInstance() {
            ChargingStationFragment chargingStationFragment = new ChargingStationFragment();
            chargingStationFragment.setArguments(new Bundle());
            return chargingStationFragment;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x008d, code lost:
        if (r11 != false) goto L39;
     */
    @SuppressLint({"ClickableViewAccessibility", "PotentialBehaviorOverride"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void initMapView() {
        PlacesSearchResult[] placesSearchResultArr;
        boolean z;
        boolean isBlank;
        Geometry geometry;
        com.google.maps.model.LatLng latLng;
        Geometry geometry2;
        com.google.maps.model.LatLng latLng2;
        GoogleMap googleMap = this.googleMap;
        if (googleMap != null) {
            googleMap.clear();
            float f2 = ZOOM_LEVEL;
            googleMap.setMaxZoomPreference(ZOOM_LEVEL);
            LatLng latLng3 = this.selectedLatLng;
            Intrinsics.checkNotNull(latLng3);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng3, ZOOM_LEVEL));
            LatLng latLng4 = this.selectedLatLng;
            Intrinsics.checkNotNull(latLng4);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng4, ZOOM_LEVEL));
            PlacesSearchResult[] placesSearchResultArr2 = new NearbySearch(this.selectedLatLng).run().results;
            if (placesSearchResultArr2 != null) {
                if (!(placesSearchResultArr2.length == 0)) {
                    int length = placesSearchResultArr2.length;
                    int i2 = 0;
                    while (i2 < length) {
                        PlacesSearchResult placesSearchResult = placesSearchResultArr2[i2];
                        Double valueOf = (placesSearchResult == null || (geometry2 = placesSearchResult.geometry) == null || (latLng2 = geometry2.location) == null) ? null : Double.valueOf(latLng2.lat);
                        Double valueOf2 = (placesSearchResult == null || (geometry = placesSearchResult.geometry) == null || (latLng = geometry.location) == null) ? null : Double.valueOf(latLng.lng);
                        if (valueOf == null || valueOf2 == null) {
                            placesSearchResultArr = placesSearchResultArr2;
                        } else {
                            String str = this.param1;
                            if (str == null || str.length() == 0) {
                                String str2 = this.param1;
                                if (str2 != null) {
                                    isBlank = StringsKt__StringsJVMKt.isBlank(str2);
                                    if (!isBlank) {
                                        z = false;
                                    }
                                }
                                z = true;
                            }
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(valueOf.doubleValue(), valueOf2.doubleValue()), f2));
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(valueOf.doubleValue(), valueOf2.doubleValue()), f2));
                            placesSearchResultArr = placesSearchResultArr2;
                            MarkerOptions title = new MarkerOptions().position(new LatLng(valueOf.doubleValue(), valueOf2.doubleValue())).title("Marker");
                            AppUtil.Companion companion = AppUtil.Companion;
                            Context requireContext = requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                            googleMap.addMarker(title.icon(companion.getBitmapDescriptorFromVector(requireContext, R.drawable.ic_marker_current_red)));
                            MapWrapperLayout mapWrapperLayout = this.mapWrapperLayout;
                            if (mapWrapperLayout == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mapWrapperLayout");
                                mapWrapperLayout = null;
                            }
                            GoogleMap googleMap2 = this.googleMap;
                            FragmentActivity requireActivity = requireActivity();
                            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                            mapWrapperLayout.init(googleMap2, getPixelsFromDp(requireActivity, 59));
                            ViewGroup viewGroup = (ViewGroup) getLayoutInflater().inflate(R.layout.map_info_view, (ViewGroup) null);
                            this.infoWindow = viewGroup;
                            Intrinsics.checkNotNull(viewGroup);
                            View findViewById = viewGroup.findViewById(R.id.ivCloseInfo);
                            Objects.requireNonNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatImageView");
                            this.infoButton1 = (AppCompatImageView) findViewById;
                            ViewGroup viewGroup2 = this.infoWindow;
                            Intrinsics.checkNotNull(viewGroup2);
                            View findViewById2 = viewGroup2.findViewById(R.id.btnNavigate);
                            Objects.requireNonNull(findViewById2, "null cannot be cast to non-null type com.google.android.material.button.MaterialButton");
                            this.infoButton2 = (MaterialButton) findViewById2;
                            final AppCompatImageView appCompatImageView = this.infoButton1;
                            this.infoButtonListener = new OnInfoWindowElemTouchListener(appCompatImageView) { // from class: com.psa.mym.mycitroenconnect.controller.fragments.charging_station.ChargingStationFragment$initMapView$1$1$1
                                @Override // com.psa.mym.mycitroenconnect.views.infowindow.OnInfoWindowElemTouchListener
                                protected void d(@NotNull View v, @NotNull Marker marker) {
                                    Intrinsics.checkNotNullParameter(v, "v");
                                    Intrinsics.checkNotNullParameter(marker, "marker");
                                    marker.hideInfoWindow();
                                }
                            };
                            AppCompatImageView appCompatImageView2 = this.infoButton1;
                            Intrinsics.checkNotNull(appCompatImageView2);
                            appCompatImageView2.setOnTouchListener(this.infoButtonListener);
                            final MaterialButton materialButton = this.infoButton2;
                            this.infoButtonListener2 = new OnInfoWindowElemTouchListener(materialButton) { // from class: com.psa.mym.mycitroenconnect.controller.fragments.charging_station.ChargingStationFragment$initMapView$1$1$2
                                @Override // com.psa.mym.mycitroenconnect.views.infowindow.OnInfoWindowElemTouchListener
                                protected void d(@NotNull View v, @NotNull Marker marker) {
                                    double d2;
                                    double d3;
                                    Intrinsics.checkNotNullParameter(v, "v");
                                    Intrinsics.checkNotNullParameter(marker, "marker");
                                    ChargingStationFragment.this.destinationLatitude = marker.getPosition().latitude;
                                    ChargingStationFragment.this.destinationLongitude = marker.getPosition().longitude;
                                    AppUtil.Companion companion2 = AppUtil.Companion;
                                    Context requireContext2 = ChargingStationFragment.this.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                                    d2 = ChargingStationFragment.this.destinationLatitude;
                                    d3 = ChargingStationFragment.this.destinationLongitude;
                                    companion2.navigateToDestination(requireContext2, d2, d3);
                                }
                            };
                            MaterialButton materialButton2 = this.infoButton2;
                            Intrinsics.checkNotNull(materialButton2);
                            materialButton2.setOnTouchListener(this.infoButtonListener2);
                            GoogleMap googleMap3 = this.googleMap;
                            Intrinsics.checkNotNull(googleMap3);
                            googleMap3.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.charging_station.ChargingStationFragment$initMapView$1$1$3
                                @Override // com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
                                @NotNull
                                public View getInfoContents(@NotNull Marker marker) {
                                    OnInfoWindowElemTouchListener onInfoWindowElemTouchListener;
                                    OnInfoWindowElemTouchListener onInfoWindowElemTouchListener2;
                                    MapWrapperLayout mapWrapperLayout2;
                                    ViewGroup viewGroup3;
                                    ViewGroup viewGroup4;
                                    Intrinsics.checkNotNullParameter(marker, "marker");
                                    onInfoWindowElemTouchListener = ChargingStationFragment.this.infoButtonListener;
                                    Intrinsics.checkNotNull(onInfoWindowElemTouchListener);
                                    onInfoWindowElemTouchListener.setMarker(marker);
                                    onInfoWindowElemTouchListener2 = ChargingStationFragment.this.infoButtonListener2;
                                    Intrinsics.checkNotNull(onInfoWindowElemTouchListener2);
                                    onInfoWindowElemTouchListener2.setMarker(marker);
                                    mapWrapperLayout2 = ChargingStationFragment.this.mapWrapperLayout;
                                    if (mapWrapperLayout2 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("mapWrapperLayout");
                                        mapWrapperLayout2 = null;
                                    }
                                    viewGroup3 = ChargingStationFragment.this.infoWindow;
                                    mapWrapperLayout2.setMarkerWithInfoWindow(marker, viewGroup3);
                                    viewGroup4 = ChargingStationFragment.this.infoWindow;
                                    Intrinsics.checkNotNull(viewGroup4);
                                    return viewGroup4;
                                }

                                @Override // com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
                                @Nullable
                                public View getInfoWindow(@NotNull Marker marker) {
                                    Intrinsics.checkNotNullParameter(marker, "marker");
                                    return null;
                                }
                            });
                        }
                        i2++;
                        placesSearchResultArr2 = placesSearchResultArr;
                        f2 = ZOOM_LEVEL;
                    }
                }
            }
        }
    }

    private final void initView() {
        AppCompatEditText appCompatEditText;
        int i2;
        if (Intrinsics.areEqual(this.vehicleType, AppConstants.ICE)) {
            appCompatEditText = (AppCompatEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edSearchStation);
            i2 = R.string.hint_find_fuel_station;
        } else {
            appCompatEditText = (AppCompatEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edSearchStation);
            i2 = R.string.hint_find_charging_station;
        }
        appCompatEditText.setHint(getString(i2));
        MainActivity mainActivity = this.parentActivity;
        SupportMapFragment supportMapFragment = null;
        View _$_findCachedViewById = mainActivity != null ? mainActivity._$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.mainAppHeader) : null;
        if (_$_findCachedViewById != null) {
            _$_findCachedViewById.setBackground(null);
        }
        Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.map);
        Objects.requireNonNull(findFragmentById, "null cannot be cast to non-null type com.google.android.gms.maps.SupportMapFragment");
        this.mapFragment = (SupportMapFragment) findFragmentById;
        View view = this.rootView;
        Intrinsics.checkNotNull(view);
        View findViewById = view.findViewById(R.id.map_relative_layout);
        Objects.requireNonNull(findViewById, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.views.infowindow.MapWrapperLayout");
        this.mapWrapperLayout = (MapWrapperLayout) findViewById;
        if (this.selectedLatLng == null) {
            this.selectedLatLng = new LatLng(23.0225d, 72.5714d);
        }
        if (this.carLatLng == null) {
            this.carLatLng = new LatLng(23.0225d, 72.5714d);
        }
        SupportMapFragment supportMapFragment2 = this.mapFragment;
        if (supportMapFragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapFragment");
        } else {
            supportMapFragment = supportMapFragment2;
        }
        supportMapFragment.getMapAsync(this);
        initializeLocation();
    }

    private final void initializeLocation() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        LocationUtility locationUtility = new LocationUtility(requireActivity);
        this.locationUtility = locationUtility;
        Intrinsics.checkNotNull(locationUtility);
        locationUtility.startLocationClient();
        Lifecycle lifecycle = getLifecycle();
        LocationUtility locationUtility2 = this.locationUtility;
        Intrinsics.checkNotNull(locationUtility2);
        lifecycle.addObserver(locationUtility2);
        LocationUtility locationUtility3 = this.locationUtility;
        Intrinsics.checkNotNull(locationUtility3);
        locationUtility3.getCurrentLocation().observe(this, a.f10520a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initializeLocation$lambda-6  reason: not valid java name */
    public static final void m137initializeLocation$lambda6(Location location) {
    }

    @JvmStatic
    @NotNull
    public static final ChargingStationFragment newInstance() {
        return Companion.newInstance();
    }

    private final void setListener() {
        int i2 = com.psa.mym.mycitroenconnect.R.id.edSearchStation;
        AppCompatEditText edSearchStation = (AppCompatEditText) _$_findCachedViewById(i2);
        Intrinsics.checkNotNullExpressionValue(edSearchStation, "edSearchStation");
        ExtensionsKt.onRightDrawableClicked(edSearchStation, new ChargingStationFragment$setListener$1(this));
        ((AppCompatEditText) _$_findCachedViewById(i2)).setOnClickListener(this);
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

    public final int getPixelsFromDp(@NotNull Context context, int i2) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (int) ((i2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    @Override // androidx.fragment.app.Fragment
    @Deprecated(message = "Deprecated in Java")
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        LocationUtility locationUtility;
        super.onActivityResult(i2, i3, intent);
        if (i2 == REQUEST_DESTINATION_CODE) {
            if (i3 != -1) {
                if (i3 == 1 && (locationUtility = this.locationUtility) != null) {
                    locationUtility.onActivityResult(i2, i3, intent);
                }
            } else if (intent != null) {
                this.destinationLatitude = intent.getDoubleExtra("latitude", 0.0d);
                this.destinationLongitude = intent.getDoubleExtra("longitude", 0.0d);
                String stringExtra = intent.getStringExtra("address");
                Intrinsics.checkNotNull(stringExtra);
                this.destinationAddress = stringExtra;
                this.selectedLatLng = new LatLng(this.destinationLatitude, this.destinationLongitude);
                ((AppCompatEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edSearchStation)).setText(this.destinationAddress);
                this.carLatLng = this.selectedLatLng;
                initMapView();
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatEditText) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edSearchStation))) {
            startActivityForResult(new Intent(requireContext(), SearchLocationActivity.class), REQUEST_DESTINATION_CODE);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        Window window;
        super.onCreate(bundle);
        getArguments();
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.vehicleType = companion.getVehicleType(requireContext);
        MainActivity mainActivity = this.parentActivity;
        if (mainActivity == null || (window = mainActivity.getWindow()) == null) {
            return;
        }
        window.setBackgroundDrawable(new ColorDrawable(0));
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.fragment_charging_station, viewGroup, false);
        this.rootView = inflate;
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        LocationUtility locationUtility = this.locationUtility;
        if (locationUtility != null) {
            getLifecycle().removeObserver(locationUtility);
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(@NotNull GoogleMap map) {
        Intrinsics.checkNotNullParameter(map, "map");
        this.googleMap = map;
        if (map != null) {
            UiSettings uiSettings = map.getUiSettings();
            uiSettings.setAllGesturesEnabled(true);
            uiSettings.setZoomControlsEnabled(true);
            uiSettings.setZoomGesturesEnabled(true);
            uiSettings.setMyLocationButtonEnabled(false);
            uiSettings.setIndoorLevelPickerEnabled(false);
            map.setIndoorEnabled(false);
            uiSettings.setCompassEnabled(false);
            uiSettings.setMapToolbarEnabled(false);
        }
        initMapView();
    }

    @Override // androidx.fragment.app.Fragment
    @Deprecated(message = "Deprecated in Java")
    public void onRequestPermissionsResult(int i2, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i2, permissions, grantResults);
        LocationUtility locationUtility = this.locationUtility;
        if (locationUtility != null) {
            locationUtility.onRequestPermissionsResult(i2, permissions, grantResults);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        MainActivity mainActivity;
        super.onResume();
        if (getActivity() == null || !(getActivity() instanceof MainActivity)) {
            mainActivity = null;
        } else {
            FragmentActivity activity = getActivity();
            Objects.requireNonNull(activity, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.controller.activities.MainActivity");
            mainActivity = (MainActivity) activity;
        }
        this.parentActivity = mainActivity;
        if (mainActivity != null) {
            mainActivity.showNavBar();
        }
        initView();
        setListener();
    }
}
