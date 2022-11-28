package com.google.maps.model;

import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;
/* loaded from: classes2.dex */
public class PlacesSearchResult implements Serializable {
    private static final long serialVersionUID = 1;
    public String formattedAddress;
    public Geometry geometry;
    public URL icon;
    public String name;
    public OpeningHours openingHours;
    public boolean permanentlyClosed;
    public Photo[] photos;
    public String placeId;
    public float rating;
    @Deprecated
    public PlaceIdScope scope;
    public String[] types;
    public int userRatingsTotal;
    public String vicinity;

    public String toString() {
        StringBuilder sb = new StringBuilder("[PlacesSearchResult: ");
        sb.append("\"");
        sb.append(this.name);
        sb.append("\"");
        sb.append(", \"");
        sb.append(this.formattedAddress);
        sb.append("\"");
        sb.append(", geometry=");
        sb.append(this.geometry);
        sb.append(", placeId=");
        sb.append(this.placeId);
        if (this.vicinity != null) {
            sb.append(", vicinity=");
            sb.append(this.vicinity);
        }
        String[] strArr = this.types;
        if (strArr != null && strArr.length > 0) {
            sb.append(", types=");
            sb.append(Arrays.toString(this.types));
        }
        sb.append(", rating=");
        sb.append(this.rating);
        if (this.icon != null) {
            sb.append(", icon");
        }
        if (this.openingHours != null) {
            sb.append(", openingHours");
        }
        Photo[] photoArr = this.photos;
        if (photoArr != null && photoArr.length > 0) {
            sb.append(", ");
            sb.append(this.photos.length);
            sb.append(" photos");
        }
        if (this.permanentlyClosed) {
            sb.append(", permanentlyClosed");
        }
        if (this.userRatingsTotal > 0) {
            sb.append(", userRatingsTotal=");
            sb.append(this.userRatingsTotal);
        }
        sb.append("]");
        return sb.toString();
    }
}
