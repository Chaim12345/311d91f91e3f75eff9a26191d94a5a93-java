package com.psa.mym.mycitroenconnect.car_console.utils;

import android.location.Location;
import com.google.android.gms.maps.model.LatLng;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class AutoConstants {
    public static final int DISMISS_TIME_MILLIS = 2000;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 30000;
    public static final double INITIAL_SEARCH_LOCATION_LAT = 0.0d;
    public static final double INITIAL_SEARCH_LOCATION_LNG = 0.0d;
    public static final int LOADING_TIME_MILLIS = 3000;
    public static final int MESSAGE_INVALIDATE_TIME_MILLIS = 500;
    @NotNull
    public static final String STATE_API_CALL_RESULT = "api_call_result";
    @NotNull
    public static final String STATE_LOADING = "loading";
    @NotNull
    public static final String STATE_LOADING_FINISHED = "loading_finished";
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 60000;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static Location INITIAL_SEARCH_LOCATION = new Location("myCitroenConnect");

    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Location getINITIAL_SEARCH_LOCATION() {
            return AutoConstants.INITIAL_SEARCH_LOCATION;
        }

        @NotNull
        public final Location getInitialSearchLocation() {
            Pair<LatLng, Double> currentLatLong = AutoLocationManager.Companion.getInstance().getCurrentLatLong();
            LatLng component1 = currentLatLong.component1();
            Double component2 = currentLatLong.component2();
            if ((component1 != null ? Double.valueOf(component1.latitude) : null) != null) {
                double d2 = component1.longitude;
                getINITIAL_SEARCH_LOCATION().setLatitude(component1.latitude);
                getINITIAL_SEARCH_LOCATION().setLongitude(component1.longitude);
                Location initial_search_location = getINITIAL_SEARCH_LOCATION();
                Intrinsics.checkNotNull(component2);
                initial_search_location.setAltitude(component2.doubleValue());
            } else {
                getINITIAL_SEARCH_LOCATION().setLatitude(0.0d);
                getINITIAL_SEARCH_LOCATION().setLongitude(0.0d);
            }
            return getINITIAL_SEARCH_LOCATION();
        }

        public final void setINITIAL_SEARCH_LOCATION(@NotNull Location location) {
            Intrinsics.checkNotNullParameter(location, "<set-?>");
            AutoConstants.INITIAL_SEARCH_LOCATION = location;
        }
    }
}
