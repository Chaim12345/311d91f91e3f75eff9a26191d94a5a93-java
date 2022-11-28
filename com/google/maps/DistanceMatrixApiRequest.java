package com.google.maps;

import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.internal.StringJoin;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TransitMode;
import com.google.maps.model.TransitRoutingPreference;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import java.time.Instant;
/* loaded from: classes2.dex */
public class DistanceMatrixApiRequest extends PendingResultBase<DistanceMatrix, DistanceMatrixApiRequest, DistanceMatrixApi.Response> {
    public DistanceMatrixApiRequest(GeoApiContext geoApiContext) {
        super(geoApiContext, DistanceMatrixApi.f10268a, DistanceMatrixApi.Response.class);
    }

    public DistanceMatrixApiRequest arrivalTime(Instant instant) {
        return (DistanceMatrixApiRequest) c("arrival_time", Long.toString(instant.toEpochMilli() / 1000));
    }

    public DistanceMatrixApiRequest avoid(DirectionsApi.RouteRestriction routeRestriction) {
        return (DistanceMatrixApiRequest) b("avoid", routeRestriction);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.DistanceMatrixApiRequest, com.google.maps.PendingResultBase] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ DistanceMatrixApiRequest channel(String str) {
        return super.channel(str);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.DistanceMatrixApiRequest, com.google.maps.PendingResultBase] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ DistanceMatrixApiRequest custom(String str, String str2) {
        return super.custom(str, str2);
    }

    public DistanceMatrixApiRequest departureTime(Instant instant) {
        return (DistanceMatrixApiRequest) c("departure_time", Long.toString(instant.toEpochMilli() / 1000));
    }

    public DistanceMatrixApiRequest destinations(LatLng... latLngArr) {
        return (DistanceMatrixApiRequest) c("destinations", StringJoin.join('|', (StringJoin.UrlValue[]) latLngArr));
    }

    public DistanceMatrixApiRequest destinations(String... strArr) {
        return (DistanceMatrixApiRequest) c("destinations", StringJoin.join('|', strArr));
    }

    @Override // com.google.maps.PendingResultBase
    protected void g() {
        if (!f().containsKey("origins")) {
            throw new IllegalArgumentException("Request must contain 'origins'");
        }
        if (!f().containsKey("destinations")) {
            throw new IllegalArgumentException("Request must contain 'destinations'");
        }
        if (f().containsKey("arrival_time") && f().containsKey("departure_time")) {
            throw new IllegalArgumentException("Transit request must not contain both a departureTime and an arrivalTime");
        }
    }

    public DistanceMatrixApiRequest mode(TravelMode travelMode) {
        if (TravelMode.DRIVING.equals(travelMode) || TravelMode.WALKING.equals(travelMode) || TravelMode.BICYCLING.equals(travelMode) || TravelMode.TRANSIT.equals(travelMode)) {
            return (DistanceMatrixApiRequest) b("mode", travelMode);
        }
        throw new IllegalArgumentException("Distance Matrix API travel modes must be Driving, Transit, Walking or Bicycling");
    }

    public DistanceMatrixApiRequest origins(LatLng... latLngArr) {
        return (DistanceMatrixApiRequest) c("origins", StringJoin.join('|', (StringJoin.UrlValue[]) latLngArr));
    }

    public DistanceMatrixApiRequest origins(String... strArr) {
        return (DistanceMatrixApiRequest) c("origins", StringJoin.join('|', strArr));
    }

    public DistanceMatrixApiRequest trafficModel(TrafficModel trafficModel) {
        return (DistanceMatrixApiRequest) b("traffic_model", trafficModel);
    }

    public DistanceMatrixApiRequest transitModes(TransitMode... transitModeArr) {
        return (DistanceMatrixApiRequest) c("transit_mode", StringJoin.join('|', (StringJoin.UrlValue[]) transitModeArr));
    }

    public DistanceMatrixApiRequest transitRoutingPreference(TransitRoutingPreference transitRoutingPreference) {
        return (DistanceMatrixApiRequest) b("transit_routing_preference", transitRoutingPreference);
    }

    public DistanceMatrixApiRequest units(Unit unit) {
        return (DistanceMatrixApiRequest) b("units", unit);
    }
}
