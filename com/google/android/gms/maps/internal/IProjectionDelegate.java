package com.google.android.gms.maps.internal;

import android.os.IInterface;
import androidx.annotation.NonNull;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;
/* loaded from: classes2.dex */
public interface IProjectionDelegate extends IInterface {
    @NonNull
    LatLng fromScreenLocation(@NonNull IObjectWrapper iObjectWrapper);

    @NonNull
    VisibleRegion getVisibleRegion();

    @NonNull
    IObjectWrapper toScreenLocation(@NonNull LatLng latLng);
}
