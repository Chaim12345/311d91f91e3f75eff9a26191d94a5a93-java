package com.google.maps.model;

import java.io.Serializable;
import java.net.URL;
import java.time.Instant;
import java.util.Arrays;
/* loaded from: classes2.dex */
public class PlaceDetails implements Serializable {
    private static final long serialVersionUID = 1;
    public AddressComponent[] addressComponents;
    public String adrAddress;
    public AlternatePlaceIds[] altIds;
    public String formattedAddress;
    public String formattedPhoneNumber;
    public Geometry geometry;
    public String[] htmlAttributions;
    public URL icon;
    public String internationalPhoneNumber;
    public String name;
    public OpeningHours openingHours;
    public boolean permanentlyClosed;
    public Photo[] photos;
    public String placeId;
    public PlusCode plusCode;
    public PriceLevel priceLevel;
    public float rating;
    public Review[] reviews;
    @Deprecated
    public PlaceIdScope scope;
    public AddressType[] types;
    public URL url;
    public int userRatingsTotal;
    public int utcOffset;
    public String vicinity;
    public URL website;

    @Deprecated
    /* loaded from: classes2.dex */
    public static class AlternatePlaceIds implements Serializable {
        private static final long serialVersionUID = 1;
        public String placeId;
        public PlaceIdScope scope;

        public String toString() {
            return String.format("%s (%s)", this.placeId, this.scope);
        }
    }

    /* loaded from: classes2.dex */
    public static class Review implements Serializable {
        private static final long serialVersionUID = 1;
        public AspectRating[] aspects;
        public String authorName;
        public URL authorUrl;
        public String language;
        public String profilePhotoUrl;
        public int rating;
        public String relativeTimeDescription;
        public String text;
        public Instant time;

        /* loaded from: classes2.dex */
        public static class AspectRating implements Serializable {
            private static final long serialVersionUID = 1;
            public int rating;
            public RatingType type;

            /* loaded from: classes2.dex */
            public enum RatingType {
                APPEAL,
                ATMOSPHERE,
                DECOR,
                FACILITIES,
                FOOD,
                OVERALL,
                QUALITY,
                SERVICE,
                UNKNOWN
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[PlaceDetails: ");
        sb.append("\"");
        sb.append(this.name);
        sb.append("\"");
        sb.append(" ");
        sb.append(this.placeId);
        sb.append(" (");
        sb.append(this.scope);
        sb.append(")");
        sb.append(" address=\"");
        sb.append(this.formattedAddress);
        sb.append("\"");
        sb.append(" geometry=");
        sb.append(this.geometry);
        if (this.vicinity != null) {
            sb.append(", vicinity=");
            sb.append(this.vicinity);
        }
        AddressType[] addressTypeArr = this.types;
        if (addressTypeArr != null && addressTypeArr.length > 0) {
            sb.append(", types=");
            sb.append(Arrays.toString(this.types));
        }
        AlternatePlaceIds[] alternatePlaceIdsArr = this.altIds;
        if (alternatePlaceIdsArr != null && alternatePlaceIdsArr.length > 0) {
            sb.append(", altIds=");
            sb.append(Arrays.toString(this.altIds));
        }
        if (this.formattedPhoneNumber != null) {
            sb.append(", phone=");
            sb.append(this.formattedPhoneNumber);
        }
        if (this.internationalPhoneNumber != null) {
            sb.append(", internationalPhoneNumber=");
            sb.append(this.internationalPhoneNumber);
        }
        if (this.url != null) {
            sb.append(", url=");
            sb.append(this.url);
        }
        if (this.website != null) {
            sb.append(", website=");
            sb.append(this.website);
        }
        if (this.icon != null) {
            sb.append(", icon");
        }
        if (this.openingHours != null) {
            sb.append(", openingHours");
            sb.append(", utcOffset=");
            sb.append(this.utcOffset);
        }
        if (this.priceLevel != null) {
            sb.append(", priceLevel=");
            sb.append(this.priceLevel);
        }
        sb.append(", rating=");
        sb.append(this.rating);
        if (this.permanentlyClosed) {
            sb.append(", permanentlyClosed");
        }
        if (this.userRatingsTotal > 0) {
            sb.append(", userRatingsTotal=");
            sb.append(this.userRatingsTotal);
        }
        Photo[] photoArr = this.photos;
        if (photoArr != null && photoArr.length > 0) {
            sb.append(", ");
            sb.append(this.photos.length);
            sb.append(" photos");
        }
        Review[] reviewArr = this.reviews;
        if (reviewArr != null && reviewArr.length > 0) {
            sb.append(", ");
            sb.append(this.reviews.length);
            sb.append(" reviews");
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
