package com.google.maps.model;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class PlacesSearchResponse implements Serializable {
    private static final long serialVersionUID = 1;
    public String[] htmlAttributions;
    public String nextPageToken;
    public PlacesSearchResult[] results;

    public String toString() {
        StringBuilder sb = new StringBuilder("[PlacesSearchResponse: ");
        sb.append(this.results.length);
        sb.append(" results");
        if (this.nextPageToken != null) {
            sb.append(", nextPageToken=");
            sb.append(this.nextPageToken);
        }
        String[] strArr = this.htmlAttributions;
        if (strArr != null && strArr.length > 0) {
            sb.append(", ");
            sb.append(this.htmlAttributions.length);
            sb.append(" htmlAttributions");
        }
        sb.append("]");
        return sb.toString();
    }
}
