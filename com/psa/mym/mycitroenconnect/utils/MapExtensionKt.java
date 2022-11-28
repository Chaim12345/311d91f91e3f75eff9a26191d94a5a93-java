package com.psa.mym.mycitroenconnect.utils;

import android.content.Context;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class MapExtensionKt {
    public static final void boundMarkersOnMap(@NotNull GoogleMap googleMap, @NotNull ArrayList<LatLng> latLng, int i2) {
        Intrinsics.checkNotNullParameter(googleMap, "<this>");
        Intrinsics.checkNotNullParameter(latLng, "latLng");
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        Iterator<LatLng> it = latLng.iterator();
        while (it.hasNext()) {
            builder.include(it.next());
        }
        LatLngBounds build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        CameraUpdate newLatLngBounds = CameraUpdateFactory.newLatLngBounds(build, i2);
        Intrinsics.checkNotNullExpressionValue(newLatLngBounds, "newLatLngBounds(bounds, padding)");
        googleMap.moveCamera(newLatLngBounds);
    }

    public static /* synthetic */ void boundMarkersOnMap$default(GoogleMap googleMap, ArrayList arrayList, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 5;
        }
        boundMarkersOnMap(googleMap, arrayList, i2);
    }

    @NotNull
    public static final Marker drawMarker(@NotNull GoogleMap googleMap, @NotNull Context context, @Nullable LatLng latLng, int i2, @Nullable String str) {
        Intrinsics.checkNotNullParameter(googleMap, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        MarkerOptions markerOptions = new MarkerOptions();
        Intrinsics.checkNotNull(latLng);
        Marker addMarker = googleMap.addMarker(markerOptions.position(latLng).title(str).icon(AppUtil.Companion.getBitmapDescriptorFromVector(context, i2)));
        Intrinsics.checkNotNull(addMarker);
        return addMarker;
    }

    public static /* synthetic */ Marker drawMarker$default(GoogleMap googleMap, Context context, LatLng latLng, int i2, String str, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i2 = R.drawable.ic_marker;
        }
        if ((i3 & 8) != 0) {
            str = null;
        }
        return drawMarker(googleMap, context, latLng, i2, str);
    }

    @NotNull
    public static final Marker drawMarkerPNG(@NotNull GoogleMap googleMap, @NotNull Context context, @Nullable LatLng latLng, int i2, @Nullable String str) {
        Intrinsics.checkNotNullParameter(googleMap, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        MarkerOptions markerOptions = new MarkerOptions();
        Intrinsics.checkNotNull(latLng);
        Marker addMarker = googleMap.addMarker(markerOptions.position(latLng).title(str).icon(AppUtil.Companion.getBitmapDescriptorFromPNG(context, i2)));
        Intrinsics.checkNotNull(addMarker);
        return addMarker;
    }

    public static /* synthetic */ Marker drawMarkerPNG$default(GoogleMap googleMap, Context context, LatLng latLng, int i2, String str, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i2 = R.drawable.ic_car_marker;
        }
        if ((i3 & 8) != 0) {
            str = null;
        }
        return drawMarkerPNG(googleMap, context, latLng, i2, str);
    }

    public static final void moveCameraOnMap(@NotNull GoogleMap googleMap, float f2, @NotNull LatLng latLng, boolean z) {
        CameraUpdate zoomTo;
        Intrinsics.checkNotNullParameter(googleMap, "<this>");
        Intrinsics.checkNotNullParameter(latLng, "latLng");
        if (z) {
            zoomTo = CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(latLng.latitude, latLng.longitude)).zoom(f2).build());
        } else {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, f2));
            zoomTo = CameraUpdateFactory.zoomTo(f2);
        }
        googleMap.animateCamera(zoomTo);
    }

    public static /* synthetic */ void moveCameraOnMap$default(GoogleMap googleMap, float f2, LatLng latLng, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            f2 = 15.5f;
        }
        if ((i2 & 4) != 0) {
            z = true;
        }
        moveCameraOnMap(googleMap, f2, latLng, z);
    }
}
