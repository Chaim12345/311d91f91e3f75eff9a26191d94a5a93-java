package com.google.maps.model;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class SnappedSpeedLimitResponse implements Serializable {
    private static final long serialVersionUID = 1;
    public SnappedPoint[] snappedPoints;
    public SpeedLimit[] speedLimits;

    public String toString() {
        StringBuilder sb = new StringBuilder("[SnappedSpeedLimitResponse:");
        SpeedLimit[] speedLimitArr = this.speedLimits;
        if (speedLimitArr != null && speedLimitArr.length > 0) {
            sb.append(" ");
            sb.append(this.speedLimits.length);
            sb.append(" speedLimits");
        }
        SnappedPoint[] snappedPointArr = this.snappedPoints;
        if (snappedPointArr != null && snappedPointArr.length > 0) {
            sb.append(" ");
            sb.append(this.snappedPoints.length);
            sb.append(" speedLimits");
        }
        sb.append("]");
        return sb.toString();
    }
}
