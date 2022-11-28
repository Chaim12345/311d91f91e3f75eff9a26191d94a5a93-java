package com.google.maps.model;

import com.google.maps.internal.StringJoin;
/* loaded from: classes2.dex */
public enum PlaceAutocompleteType implements StringJoin.UrlValue {
    GEOCODE("geocode"),
    ADDRESS("address"),
    ESTABLISHMENT("establishment"),
    REGIONS("(regions)"),
    CITIES("(cities)");
    
    private final String placeType;

    PlaceAutocompleteType(String str) {
        this.placeType = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.placeType;
    }

    @Override // com.google.maps.internal.StringJoin.UrlValue
    public String toUrlValue() {
        return this.placeType;
    }
}
