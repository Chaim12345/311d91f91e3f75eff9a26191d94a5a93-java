package com.google.android.gms.maps.internal;

import android.os.IInterface;
import androidx.annotation.NonNull;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
/* loaded from: classes2.dex */
public interface ICameraUpdateFactoryDelegate extends IInterface {
    @NonNull
    IObjectWrapper newCameraPosition(@NonNull CameraPosition cameraPosition);

    @NonNull
    IObjectWrapper newLatLng(@NonNull LatLng latLng);

    @NonNull
    IObjectWrapper newLatLngBounds(@NonNull LatLngBounds latLngBounds, int i2);

    @NonNull
    IObjectWrapper newLatLngBoundsWithSize(@NonNull LatLngBounds latLngBounds, int i2, int i3, int i4);

    @NonNull
    IObjectWrapper newLatLngZoom(@NonNull LatLng latLng, float f2);

    @NonNull
    IObjectWrapper scrollBy(float f2, float f3);

    @NonNull
    IObjectWrapper zoomBy(float f2);

    @NonNull
    IObjectWrapper zoomByWithFocus(float f2, int i2, int i3);

    @NonNull
    IObjectWrapper zoomIn();

    @NonNull
    IObjectWrapper zoomOut();

    @NonNull
    IObjectWrapper zoomTo(float f2);
}
