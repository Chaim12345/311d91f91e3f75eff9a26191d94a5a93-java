package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;
/* loaded from: classes2.dex */
public final class zzbv extends com.google.android.gms.internal.maps.zza implements IStreetViewPanoramaDelegate {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void animateTo(StreetViewPanoramaCamera streetViewPanoramaCamera, long j2) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzd(b2, streetViewPanoramaCamera);
        b2.writeLong(j2);
        c(9, b2);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enablePanning(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        c(2, b2);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enableStreetNames(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        c(4, b2);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enableUserNavigation(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        c(3, b2);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enableZoom(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        c(1, b2);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final StreetViewPanoramaCamera getPanoramaCamera() {
        Parcel a2 = a(10, b());
        StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera) com.google.android.gms.internal.maps.zzc.zza(a2, StreetViewPanoramaCamera.CREATOR);
        a2.recycle();
        return streetViewPanoramaCamera;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final StreetViewPanoramaLocation getStreetViewPanoramaLocation() {
        Parcel a2 = a(14, b());
        StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation) com.google.android.gms.internal.maps.zzc.zza(a2, StreetViewPanoramaLocation.CREATOR);
        a2.recycle();
        return streetViewPanoramaLocation;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isPanningGesturesEnabled() {
        Parcel a2 = a(6, b());
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isStreetNamesEnabled() {
        Parcel a2 = a(8, b());
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isUserNavigationEnabled() {
        Parcel a2 = a(7, b());
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isZoomGesturesEnabled() {
        Parcel a2 = a(5, b());
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final IObjectWrapper orientationToPoint(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzd(b2, streetViewPanoramaOrientation);
        Parcel a2 = a(19, b2);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final StreetViewPanoramaOrientation pointToOrientation(IObjectWrapper iObjectWrapper) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, iObjectWrapper);
        Parcel a2 = a(18, b2);
        StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation) com.google.android.gms.internal.maps.zzc.zza(a2, StreetViewPanoramaOrientation.CREATOR);
        a2.recycle();
        return streetViewPanoramaOrientation;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaCameraChangeListener(zzbj zzbjVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzbjVar);
        c(16, b2);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaChangeListener(zzbl zzblVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzblVar);
        c(15, b2);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaClickListener(zzbn zzbnVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzbnVar);
        c(17, b2);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaLongClickListener(zzbp zzbpVar) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzf(b2, zzbpVar);
        c(20, b2);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPosition(LatLng latLng) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzd(b2, latLng);
        c(12, b2);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithID(String str) {
        Parcel b2 = b();
        b2.writeString(str);
        c(11, b2);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithRadius(LatLng latLng, int i2) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzd(b2, latLng);
        b2.writeInt(i2);
        c(13, b2);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithRadiusAndSource(LatLng latLng, int i2, StreetViewSource streetViewSource) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzd(b2, latLng);
        b2.writeInt(i2);
        com.google.android.gms.internal.maps.zzc.zzd(b2, streetViewSource);
        c(22, b2);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithSource(LatLng latLng, StreetViewSource streetViewSource) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzd(b2, latLng);
        com.google.android.gms.internal.maps.zzc.zzd(b2, streetViewSource);
        c(21, b2);
    }
}
