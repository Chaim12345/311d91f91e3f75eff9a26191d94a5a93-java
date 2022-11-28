package com.google.maps.model;

import androidx.exifinterface.media.ExifInterface;
import com.google.maps.internal.StringJoin;
/* loaded from: classes2.dex */
public enum PriceLevel implements StringJoin.UrlValue {
    FREE("0"),
    INEXPENSIVE("1"),
    MODERATE(ExifInterface.GPS_MEASUREMENT_2D),
    EXPENSIVE(ExifInterface.GPS_MEASUREMENT_3D),
    VERY_EXPENSIVE("4"),
    UNKNOWN("Unknown");
    
    private final String priceLevel;

    PriceLevel(String str) {
        this.priceLevel = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.priceLevel;
    }

    @Override // com.google.maps.internal.StringJoin.UrlValue
    public String toUrlValue() {
        if (this != UNKNOWN) {
            return this.priceLevel;
        }
        throw new UnsupportedOperationException("Shouldn't use PriceLevel.UNKNOWN in a request.");
    }
}
