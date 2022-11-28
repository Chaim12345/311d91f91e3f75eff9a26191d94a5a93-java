package com.google.maps.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
/* loaded from: classes2.dex */
public class TransitDetails implements Serializable {
    private static final long serialVersionUID = 1;
    public StopDetails arrivalStop;
    public ZonedDateTime arrivalTime;
    public StopDetails departureStop;
    public ZonedDateTime departureTime;
    public String headsign;
    public long headway;
    public TransitLine line;
    public int numStops;

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(this.departureStop);
        sb.append(" at ");
        sb.append(this.departureTime);
        sb.append(" -> ");
        sb.append(this.arrivalStop);
        sb.append(" at ");
        sb.append(this.arrivalTime);
        if (this.headsign != null) {
            sb.append(" (");
            sb.append(this.headsign);
            sb.append(" )");
        }
        if (this.line != null) {
            sb.append(" on ");
            sb.append(this.line);
        }
        sb.append(", ");
        sb.append(this.numStops);
        sb.append(" stops");
        sb.append(", headway=");
        sb.append(this.headway);
        sb.append(" s");
        sb.append("]");
        return sb.toString();
    }
}
