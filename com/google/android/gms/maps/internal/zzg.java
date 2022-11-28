package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
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
/* loaded from: classes2.dex */
public final class zzg extends com.google.android.gms.internal.maps.zza implements IGoogleMapDelegate {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IGoogleMapDelegate");
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.internal.maps.zzl addCircle(CircleOptions circleOptions) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzd(b2, circleOptions);
        Parcel a2 = a(35, b2);
        com.google.android.gms.internal.maps.zzl zzb = com.google.android.gms.internal.maps.zzk.zzb(a2.readStrongBinder());
        a2.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.internal.maps.zzo addGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzd(b2, groundOverlayOptions);
        Parcel a2 = a(12, b2);
        com.google.android.gms.internal.maps.zzo zzb = com.google.android.gms.internal.maps.zzn.zzb(a2.readStrongBinder());
        a2.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.internal.maps.zzx addMarker(MarkerOptions markerOptions) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzd(b2, markerOptions);
        Parcel a2 = a(11, b2);
        com.google.android.gms.internal.maps.zzx zzb = com.google.android.gms.internal.maps.zzw.zzb(a2.readStrongBinder());
        a2.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.internal.maps.zzaa addPolygon(PolygonOptions polygonOptions) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzd(b2, polygonOptions);
        Parcel a2 = a(10, b2);
        com.google.android.gms.internal.maps.zzaa zzb = com.google.android.gms.internal.maps.zzz.zzb(a2.readStrongBinder());
        a2.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.internal.maps.zzad addPolyline(PolylineOptions polylineOptions) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzd(b2, polylineOptions);
        Parcel a2 = a(9, b2);
        com.google.android.gms.internal.maps.zzad zzb = com.google.android.gms.internal.maps.zzac.zzb(a2.readStrongBinder());
        a2.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.internal.maps.zzag addTileOverlay(TileOverlayOptions tileOverlayOptions) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzd(b2, tileOverlayOptions);
        Parcel a2 = a(13, b2);
        com.google.android.gms.internal.maps.zzag zzb = com.google.android.gms.internal.maps.zzaf.zzb(a2.readStrongBinder());
        a2.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void animateCamera(IObjectWrapper iObjectWrapper) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, iObjectWrapper);
        c(5, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void animateCameraWithCallback(IObjectWrapper iObjectWrapper, zzd zzdVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, iObjectWrapper);
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzdVar);
        c(6, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i2, zzd zzdVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, iObjectWrapper);
        b2.writeInt(i2);
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzdVar);
        c(7, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void clear() {
        c(14, b());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final CameraPosition getCameraPosition() {
        Parcel a2 = a(1, b());
        CameraPosition cameraPosition = (CameraPosition) com.google.android.gms.internal.maps.zzc.zza(a2, CameraPosition.CREATOR);
        a2.recycle();
        return cameraPosition;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.internal.maps.zzr getFocusedBuilding() {
        Parcel a2 = a(44, b());
        com.google.android.gms.internal.maps.zzr zzb = com.google.android.gms.internal.maps.zzq.zzb(a2.readStrongBinder());
        a2.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void getMapAsync(zzar zzarVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzarVar);
        c(53, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final int getMapType() {
        Parcel a2 = a(15, b());
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final float getMaxZoomLevel() {
        Parcel a2 = a(2, b());
        float readFloat = a2.readFloat();
        a2.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final float getMinZoomLevel() {
        Parcel a2 = a(3, b());
        float readFloat = a2.readFloat();
        a2.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final Location getMyLocation() {
        Parcel a2 = a(23, b());
        Location location = (Location) com.google.android.gms.internal.maps.zzc.zza(a2, Location.CREATOR);
        a2.recycle();
        return location;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final IProjectionDelegate getProjection() {
        IProjectionDelegate zzbsVar;
        Parcel a2 = a(26, b());
        IBinder readStrongBinder = a2.readStrongBinder();
        if (readStrongBinder == null) {
            zzbsVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
            zzbsVar = queryLocalInterface instanceof IProjectionDelegate ? (IProjectionDelegate) queryLocalInterface : new zzbs(readStrongBinder);
        }
        a2.recycle();
        return zzbsVar;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final IUiSettingsDelegate getUiSettings() {
        IUiSettingsDelegate zzbyVar;
        Parcel a2 = a(25, b());
        IBinder readStrongBinder = a2.readStrongBinder();
        if (readStrongBinder == null) {
            zzbyVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            zzbyVar = queryLocalInterface instanceof IUiSettingsDelegate ? (IUiSettingsDelegate) queryLocalInterface : new zzby(readStrongBinder);
        }
        a2.recycle();
        return zzbyVar;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean isBuildingsEnabled() {
        Parcel a2 = a(40, b());
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean isIndoorEnabled() {
        Parcel a2 = a(19, b());
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean isMyLocationEnabled() {
        Parcel a2 = a(21, b());
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean isTrafficEnabled() {
        Parcel a2 = a(17, b());
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void moveCamera(IObjectWrapper iObjectWrapper) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, iObjectWrapper);
        c(4, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onCreate(Bundle bundle) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzd(b2, bundle);
        c(54, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onDestroy() {
        c(57, b());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onEnterAmbient(Bundle bundle) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzd(b2, bundle);
        c(81, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onExitAmbient() {
        c(82, b());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onLowMemory() {
        c(58, b());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onPause() {
        c(56, b());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onResume() {
        c(55, b());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onSaveInstanceState(Bundle bundle) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzd(b2, bundle);
        Parcel a2 = a(60, b2);
        if (a2.readInt() != 0) {
            bundle.readFromParcel(a2);
        }
        a2.recycle();
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onStart() {
        c(101, b());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onStop() {
        c(102, b());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void resetMinMaxZoomPreference() {
        c(94, b());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setBuildingsEnabled(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        c(41, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setContentDescription(String str) {
        Parcel b2 = b();
        b2.writeString(str);
        c(61, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean setIndoorEnabled(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        Parcel a2 = a(20, b2);
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setInfoWindowAdapter(zzi zziVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zziVar);
        c(33, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzd(b2, latLngBounds);
        c(95, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, iLocationSourceDelegate);
        c(24, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean setMapStyle(MapStyleOptions mapStyleOptions) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzd(b2, mapStyleOptions);
        Parcel a2 = a(91, b2);
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setMapType(int i2) {
        Parcel b2 = b();
        b2.writeInt(i2);
        c(16, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setMaxZoomPreference(float f2) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        c(93, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setMinZoomPreference(float f2) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        c(92, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setMyLocationEnabled(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        c(22, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraChangeListener(zzn zznVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zznVar);
        c(27, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraIdleListener(zzp zzpVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzpVar);
        c(99, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraMoveCanceledListener(zzr zzrVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzrVar);
        c(98, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraMoveListener(zzt zztVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zztVar);
        c(97, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraMoveStartedListener(zzv zzvVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzvVar);
        c(96, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCircleClickListener(zzx zzxVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzxVar);
        c(89, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnGroundOverlayClickListener(zzz zzzVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzzVar);
        c(83, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnIndoorStateChangeListener(zzab zzabVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzabVar);
        c(45, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnInfoWindowClickListener(zzad zzadVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzadVar);
        c(32, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnInfoWindowCloseListener(zzaf zzafVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzafVar);
        c(86, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnInfoWindowLongClickListener(zzah zzahVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzahVar);
        c(84, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMapClickListener(zzal zzalVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzalVar);
        c(28, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMapLoadedCallback(zzan zzanVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzanVar);
        c(42, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMapLongClickListener(zzap zzapVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzapVar);
        c(29, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMarkerClickListener(zzat zzatVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzatVar);
        c(30, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMarkerDragListener(zzav zzavVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzavVar);
        c(31, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMyLocationButtonClickListener(zzax zzaxVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzaxVar);
        c(37, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMyLocationChangeListener(zzaz zzazVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzazVar);
        c(36, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMyLocationClickListener(zzbb zzbbVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzbbVar);
        c(107, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnPoiClickListener(zzbd zzbdVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzbdVar);
        c(80, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnPolygonClickListener(zzbf zzbfVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzbfVar);
        c(85, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnPolylineClickListener(zzbh zzbhVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzbhVar);
        c(87, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setPadding(int i2, int i3, int i4, int i5) {
        Parcel b2 = b();
        b2.writeInt(i2);
        b2.writeInt(i3);
        b2.writeInt(i4);
        b2.writeInt(i5);
        c(39, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setTrafficEnabled(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        c(18, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setWatermarkEnabled(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        c(51, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void snapshot(zzbu zzbuVar, IObjectWrapper iObjectWrapper) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzbuVar);
        com.google.android.gms.internal.maps.zzc.zzf(b2, iObjectWrapper);
        c(38, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void snapshotForTest(zzbu zzbuVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzbuVar);
        c(71, b2);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void stopAnimation() {
        c(8, b());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean useViewLifecycleWhenInFragment() {
        Parcel a2 = a(59, b());
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }
}
