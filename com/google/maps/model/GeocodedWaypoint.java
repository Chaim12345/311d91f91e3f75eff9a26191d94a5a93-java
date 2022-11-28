package com.google.maps.model;

import java.io.Serializable;
import java.util.Arrays;
/* loaded from: classes2.dex */
public class GeocodedWaypoint implements Serializable {
    private static final long serialVersionUID = 1;
    public GeocodedWaypointStatus geocoderStatus;
    public boolean partialMatch;
    public String placeId;
    public AddressType[] types;

    public String toString() {
        StringBuilder sb = new StringBuilder("[GeocodedWaypoint");
        sb.append(" ");
        sb.append(this.geocoderStatus);
        if (this.partialMatch) {
            sb.append(" ");
            sb.append("PARTIAL MATCH");
        }
        sb.append(" placeId=");
        sb.append(this.placeId);
        sb.append(", types=");
        sb.append(Arrays.toString(this.types));
        return sb.toString();
    }
}
