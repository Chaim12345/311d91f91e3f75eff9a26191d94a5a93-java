package com.google.maps.model;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class DistanceMatrix implements Serializable {
    private static final long serialVersionUID = 1;
    public final String[] destinationAddresses;
    public final String[] originAddresses;
    public final DistanceMatrixRow[] rows;

    public DistanceMatrix(String[] strArr, String[] strArr2, DistanceMatrixRow[] distanceMatrixRowArr) {
        this.originAddresses = strArr;
        this.destinationAddresses = strArr2;
        this.rows = distanceMatrixRowArr;
    }

    public String toString() {
        return String.format("DistanceMatrix: %d origins x %d destinations, %d rows", Integer.valueOf(this.originAddresses.length), Integer.valueOf(this.destinationAddresses.length), Integer.valueOf(this.rows.length));
    }
}
