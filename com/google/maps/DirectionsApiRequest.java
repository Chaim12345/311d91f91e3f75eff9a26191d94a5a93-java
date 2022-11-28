package com.google.maps;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.maps.DirectionsApi;
import com.google.maps.internal.StringJoin;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TransitMode;
import com.google.maps.model.TransitRoutingPreference;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import java.time.Instant;
import java.util.Objects;
/* loaded from: classes2.dex */
public class DirectionsApiRequest extends PendingResultBase<DirectionsResult, DirectionsApiRequest, DirectionsApi.Response> {

    /* renamed from: a  reason: collision with root package name */
    protected boolean f10266a;

    /* renamed from: b  reason: collision with root package name */
    protected Waypoint[] f10267b;

    /* loaded from: classes2.dex */
    public static class Waypoint {
        private boolean isStopover;
        private String location;

        public Waypoint(LatLng latLng) {
            this(latLng, true);
        }

        public Waypoint(LatLng latLng, boolean z) {
            Objects.requireNonNull(latLng, "location may not be null");
            this.location = latLng.toString();
            this.isStopover = z;
        }

        public Waypoint(String str) {
            this(str, true);
        }

        public Waypoint(String str, boolean z) {
            Objects.requireNonNull(str, "address may not be null");
            this.location = str;
            this.isStopover = z;
        }

        public String toString() {
            if (this.isStopover) {
                return this.location;
            }
            return "via:" + this.location;
        }
    }

    public DirectionsApiRequest(GeoApiContext geoApiContext) {
        super(geoApiContext, DirectionsApi.f10265a, DirectionsApi.Response.class);
    }

    public DirectionsApiRequest alternatives(boolean z) {
        return (DirectionsApiRequest) c("alternatives", z ? "true" : "false");
    }

    public DirectionsApiRequest arrivalTime(Instant instant) {
        return (DirectionsApiRequest) c("arrival_time", Long.toString(instant.toEpochMilli() / 1000));
    }

    public DirectionsApiRequest avoid(DirectionsApi.RouteRestriction... routeRestrictionArr) {
        return (DirectionsApiRequest) c("avoid", StringJoin.join('|', (StringJoin.UrlValue[]) routeRestrictionArr));
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.PendingResultBase, com.google.maps.DirectionsApiRequest] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ DirectionsApiRequest channel(String str) {
        return super.channel(str);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.PendingResultBase, com.google.maps.DirectionsApiRequest] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ DirectionsApiRequest custom(String str, String str2) {
        return super.custom(str, str2);
    }

    public DirectionsApiRequest departureTime(Instant instant) {
        return (DirectionsApiRequest) c("departure_time", Long.toString(instant.toEpochMilli() / 1000));
    }

    public DirectionsApiRequest departureTimeNow() {
        return (DirectionsApiRequest) c("departure_time", "now");
    }

    public DirectionsApiRequest destination(LatLng latLng) {
        return destination(latLng.toString());
    }

    public DirectionsApiRequest destination(String str) {
        return (DirectionsApiRequest) c(FirebaseAnalytics.Param.DESTINATION, str);
    }

    public DirectionsApiRequest destinationPlaceId(String str) {
        return (DirectionsApiRequest) c(FirebaseAnalytics.Param.DESTINATION, prefixPlaceId(str));
    }

    @Override // com.google.maps.PendingResultBase
    protected void g() {
        if (!f().containsKey("origin")) {
            throw new IllegalArgumentException("Request must contain 'origin'");
        }
        if (!f().containsKey(FirebaseAnalytics.Param.DESTINATION)) {
            throw new IllegalArgumentException("Request must contain 'destination'");
        }
        if (f().containsKey("arrival_time") && f().containsKey("departure_time")) {
            throw new IllegalArgumentException("Transit request must not contain both a departureTime and an arrivalTime");
        }
        if (f().containsKey("traffic_model") && !f().containsKey("departure_time")) {
            throw new IllegalArgumentException("Specifying a traffic model requires that departure time be provided.");
        }
    }

    public DirectionsApiRequest mode(TravelMode travelMode) {
        return (DirectionsApiRequest) b("mode", travelMode);
    }

    public DirectionsApiRequest optimizeWaypoints(boolean z) {
        this.f10266a = z;
        Waypoint[] waypointArr = this.f10267b;
        return waypointArr != null ? waypoints(waypointArr) : this;
    }

    public DirectionsApiRequest origin(LatLng latLng) {
        return origin(latLng.toString());
    }

    public DirectionsApiRequest origin(String str) {
        return (DirectionsApiRequest) c("origin", str);
    }

    public DirectionsApiRequest originPlaceId(String str) {
        return (DirectionsApiRequest) c("origin", prefixPlaceId(str));
    }

    public String prefixPlaceId(String str) {
        return "place_id:" + str;
    }

    public DirectionsApiRequest region(String str) {
        return (DirectionsApiRequest) c("region", str);
    }

    public DirectionsApiRequest trafficModel(TrafficModel trafficModel) {
        return (DirectionsApiRequest) b("traffic_model", trafficModel);
    }

    public DirectionsApiRequest transitMode(TransitMode... transitModeArr) {
        return (DirectionsApiRequest) c("transit_mode", StringJoin.join('|', (StringJoin.UrlValue[]) transitModeArr));
    }

    public DirectionsApiRequest transitRoutingPreference(TransitRoutingPreference transitRoutingPreference) {
        return (DirectionsApiRequest) b("transit_routing_preference", transitRoutingPreference);
    }

    public DirectionsApiRequest units(Unit unit) {
        return (DirectionsApiRequest) b("units", unit);
    }

    public DirectionsApiRequest waypoints(Waypoint... waypointArr) {
        if (waypointArr == null || waypointArr.length == 0) {
            this.f10267b = new Waypoint[0];
            c("waypoints", "");
            return this;
        }
        this.f10267b = waypointArr;
        String[] strArr = new String[waypointArr.length];
        for (int i2 = 0; i2 < waypointArr.length; i2++) {
            strArr[i2] = waypointArr[i2].toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.f10266a ? "optimize:true|" : "");
        sb.append(StringJoin.join('|', strArr));
        c("waypoints", sb.toString());
        return this;
    }

    public DirectionsApiRequest waypoints(LatLng... latLngArr) {
        Waypoint[] waypointArr = new Waypoint[latLngArr.length];
        for (int i2 = 0; i2 < latLngArr.length; i2++) {
            waypointArr[i2] = new Waypoint(latLngArr[i2]);
        }
        return waypoints(waypointArr);
    }

    public DirectionsApiRequest waypoints(String... strArr) {
        Waypoint[] waypointArr = new Waypoint[strArr.length];
        for (int i2 = 0; i2 < strArr.length; i2++) {
            waypointArr[i2] = new Waypoint(strArr[i2]);
        }
        return waypoints(waypointArr);
    }

    public DirectionsApiRequest waypointsFromPlaceIds(String... strArr) {
        Waypoint[] waypointArr = new Waypoint[strArr.length];
        for (int i2 = 0; i2 < strArr.length; i2++) {
            waypointArr[i2] = new Waypoint(prefixPlaceId(strArr[i2]));
        }
        return waypoints(waypointArr);
    }
}
