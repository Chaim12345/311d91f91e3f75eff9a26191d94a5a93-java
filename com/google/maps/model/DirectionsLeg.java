package com.google.maps.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
/* loaded from: classes2.dex */
public class DirectionsLeg implements Serializable {
    private static final long serialVersionUID = 1;
    public ZonedDateTime arrivalTime;
    public ZonedDateTime departureTime;
    public Distance distance;
    public Duration duration;
    public Duration durationInTraffic;
    public String endAddress;
    public LatLng endLocation;
    public String startAddress;
    public LatLng startLocation;
    public DirectionsStep[] steps;

    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("[DirectionsLeg: \"%s\" -> \"%s\" (%s -> %s)", this.startAddress, this.endAddress, this.startLocation, this.endLocation));
        if (this.departureTime != null) {
            sb.append(", departureTime=");
            sb.append(this.departureTime);
        }
        if (this.arrivalTime != null) {
            sb.append(", arrivalTime=");
            sb.append(this.arrivalTime);
        }
        if (this.durationInTraffic != null) {
            sb.append(", durationInTraffic=");
            sb.append(this.durationInTraffic);
        }
        sb.append(", duration=");
        sb.append(this.duration);
        sb.append(", distance=");
        sb.append(this.distance);
        sb.append(": ");
        sb.append(this.steps.length);
        sb.append(" steps]");
        return sb.toString();
    }
}
