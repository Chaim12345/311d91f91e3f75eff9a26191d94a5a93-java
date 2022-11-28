package com.google.maps.model;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class DistanceMatrixElement implements Serializable {
    private static final long serialVersionUID = 1;
    public Distance distance;
    public Duration duration;
    public Duration durationInTraffic;
    public Fare fare;
    public DistanceMatrixElementStatus status;

    public String toString() {
        String format = String.format("[DistanceMatrixElement %s distance=%s, duration=%s", this.status, this.distance, this.duration);
        if (this.durationInTraffic != null) {
            format = format + ", durationInTraffic=" + this.durationInTraffic;
        }
        if (this.fare != null) {
            format = format + ", fare=" + this.fare;
        }
        return format + "]";
    }
}
