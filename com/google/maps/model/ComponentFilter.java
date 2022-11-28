package com.google.maps.model;

import com.google.maps.internal.StringJoin;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes2.dex */
public class ComponentFilter implements StringJoin.UrlValue {
    public final String component;
    public final String value;

    public ComponentFilter(String str, String str2) {
        this.component = str;
        this.value = str2;
    }

    public static ComponentFilter administrativeArea(String str) {
        return new ComponentFilter("administrative_area", str);
    }

    public static ComponentFilter country(String str) {
        return new ComponentFilter("country", str);
    }

    public static ComponentFilter locality(String str) {
        return new ComponentFilter("locality", str);
    }

    public static ComponentFilter postalCode(String str) {
        return new ComponentFilter("postal_code", str);
    }

    public static ComponentFilter route(String str) {
        return new ComponentFilter(AppConstants.GEO_FENCE_MODE_ROUTE, str);
    }

    public String toString() {
        return toUrlValue();
    }

    @Override // com.google.maps.internal.StringJoin.UrlValue
    public String toUrlValue() {
        return StringJoin.join((char) AbstractJsonLexerKt.COLON, this.component, this.value);
    }
}
