package com.google.maps.model;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class DirectionsStep implements Serializable {
    private static final long serialVersionUID = 1;
    public Distance distance;
    public Duration duration;
    public LatLng endLocation;
    public String htmlInstructions;
    @Deprecated
    public String maneuver;
    public EncodedPolyline polyline;
    public LatLng startLocation;
    public DirectionsStep[] steps;
    public TransitDetails transitDetails;
    public TravelMode travelMode;

    public String toString() {
        StringBuilder sb = new StringBuilder("[DirectionsStep: ");
        sb.append("\"");
        sb.append(this.htmlInstructions);
        sb.append("\"");
        sb.append(String.format(" (%s -> %s", this.startLocation, this.endLocation));
        sb.append(")");
        sb.append(" ");
        sb.append(this.travelMode);
        sb.append(", duration=");
        sb.append(this.duration);
        sb.append(", distance=");
        sb.append(this.distance);
        DirectionsStep[] directionsStepArr = this.steps;
        if (directionsStepArr != null && directionsStepArr.length > 0) {
            sb.append(", ");
            sb.append(this.steps.length);
            sb.append(" substeps");
        }
        if (this.transitDetails != null) {
            sb.append(", transitDetails=");
            sb.append(this.transitDetails);
        }
        sb.append("]");
        return sb.toString();
    }
}
