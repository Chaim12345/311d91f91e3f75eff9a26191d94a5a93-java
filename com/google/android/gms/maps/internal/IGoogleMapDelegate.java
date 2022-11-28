package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IInterface;
import androidx.annotation.NonNull;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public interface IGoogleMapDelegate extends IInterface {
    com.google.android.gms.internal.maps.zzl addCircle(CircleOptions circleOptions);

    com.google.android.gms.internal.maps.zzo addGroundOverlay(GroundOverlayOptions groundOverlayOptions);

    com.google.android.gms.internal.maps.zzx addMarker(MarkerOptions markerOptions);

    com.google.android.gms.internal.maps.zzaa addPolygon(PolygonOptions polygonOptions);

    com.google.android.gms.internal.maps.zzad addPolyline(PolylineOptions polylineOptions);

    com.google.android.gms.internal.maps.zzag addTileOverlay(TileOverlayOptions tileOverlayOptions);

    void animateCamera(@NonNull IObjectWrapper iObjectWrapper);

    void animateCameraWithCallback(IObjectWrapper iObjectWrapper, @Nullable zzd zzdVar);

    void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i2, @Nullable zzd zzdVar);

    void clear();

    @NonNull
    CameraPosition getCameraPosition();

    com.google.android.gms.internal.maps.zzr getFocusedBuilding();

    void getMapAsync(zzar zzarVar);

    int getMapType();

    float getMaxZoomLevel();

    float getMinZoomLevel();

    @NonNull
    Location getMyLocation();

    @NonNull
    IProjectionDelegate getProjection();

    @NonNull
    IUiSettingsDelegate getUiSettings();

    boolean isBuildingsEnabled();

    boolean isIndoorEnabled();

    boolean isMyLocationEnabled();

    boolean isTrafficEnabled();

    void moveCamera(@NonNull IObjectWrapper iObjectWrapper);

    void onCreate(@NonNull Bundle bundle);

    void onDestroy();

    void onEnterAmbient(@NonNull Bundle bundle);

    void onExitAmbient();

    void onLowMemory();

    void onPause();

    void onResume();

    void onSaveInstanceState(@NonNull Bundle bundle);

    void onStart();

    void onStop();

    void resetMinMaxZoomPreference();

    void setBuildingsEnabled(boolean z);

    void setContentDescription(@Nullable String str);

    boolean setIndoorEnabled(boolean z);

    void setInfoWindowAdapter(@Nullable zzi zziVar);

    void setLatLngBoundsForCameraTarget(@Nullable LatLngBounds latLngBounds);

    void setLocationSource(@Nullable ILocationSourceDelegate iLocationSourceDelegate);

    boolean setMapStyle(@Nullable MapStyleOptions mapStyleOptions);

    void setMapType(int i2);

    void setMaxZoomPreference(float f2);

    void setMinZoomPreference(float f2);

    void setMyLocationEnabled(boolean z);

    void setOnCameraChangeListener(@Nullable zzn zznVar);

    void setOnCameraIdleListener(@Nullable zzp zzpVar);

    void setOnCameraMoveCanceledListener(@Nullable zzr zzrVar);

    void setOnCameraMoveListener(@Nullable zzt zztVar);

    void setOnCameraMoveStartedListener(@Nullable zzv zzvVar);

    void setOnCircleClickListener(@Nullable zzx zzxVar);

    void setOnGroundOverlayClickListener(@Nullable zzz zzzVar);

    void setOnIndoorStateChangeListener(@Nullable zzab zzabVar);

    void setOnInfoWindowClickListener(@Nullable zzad zzadVar);

    void setOnInfoWindowCloseListener(@Nullable zzaf zzafVar);

    void setOnInfoWindowLongClickListener(@Nullable zzah zzahVar);

    void setOnMapClickListener(@Nullable zzal zzalVar);

    void setOnMapLoadedCallback(@Nullable zzan zzanVar);

    void setOnMapLongClickListener(@Nullable zzap zzapVar);

    void setOnMarkerClickListener(@Nullable zzat zzatVar);

    void setOnMarkerDragListener(@Nullable zzav zzavVar);

    void setOnMyLocationButtonClickListener(@Nullable zzax zzaxVar);

    void setOnMyLocationChangeListener(@Nullable zzaz zzazVar);

    void setOnMyLocationClickListener(@Nullable zzbb zzbbVar);

    void setOnPoiClickListener(@Nullable zzbd zzbdVar);

    void setOnPolygonClickListener(@Nullable zzbf zzbfVar);

    void setOnPolylineClickListener(@Nullable zzbh zzbhVar);

    void setPadding(int i2, int i3, int i4, int i5);

    void setTrafficEnabled(boolean z);

    void setWatermarkEnabled(boolean z);

    void snapshot(zzbu zzbuVar, @Nullable IObjectWrapper iObjectWrapper);

    void snapshotForTest(zzbu zzbuVar);

    void stopAnimation();

    boolean useViewLifecycleWhenInFragment();
}
