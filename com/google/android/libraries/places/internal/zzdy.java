package com.google.android.libraries.places.internal;

import android.text.TextUtils;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.libraries.places.api.model.Place;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes2.dex */
public final class zzdy {
    private static final zzhv zza;

    static {
        zzhu zzhuVar = new zzhu();
        zzhuVar.zza(Place.Field.ADDRESS, "formatted_address");
        zzhuVar.zza(Place.Field.ADDRESS_COMPONENTS, "address_components");
        zzhuVar.zza(Place.Field.BUSINESS_STATUS, "business_status");
        zzhuVar.zza(Place.Field.ID, "place_id");
        zzhuVar.zza(Place.Field.LAT_LNG, "geometry/location");
        zzhuVar.zza(Place.Field.NAME, AppMeasurementSdk.ConditionalUserProperty.NAME);
        zzhuVar.zza(Place.Field.OPENING_HOURS, "opening_hours");
        zzhuVar.zza(Place.Field.PHONE_NUMBER, "international_phone_number");
        zzhuVar.zza(Place.Field.PHOTO_METADATAS, "photos");
        zzhuVar.zza(Place.Field.PLUS_CODE, "plus_code");
        zzhuVar.zza(Place.Field.PRICE_LEVEL, "price_level");
        zzhuVar.zza(Place.Field.RATING, "rating");
        zzhuVar.zza(Place.Field.TYPES, "types");
        zzhuVar.zza(Place.Field.USER_RATINGS_TOTAL, "user_ratings_total");
        zzhuVar.zza(Place.Field.UTC_OFFSET, "utc_offset");
        zzhuVar.zza(Place.Field.VIEWPORT, "geometry/viewport");
        zzhuVar.zza(Place.Field.WEBSITE_URI, "website");
        zzhuVar.zza(Place.Field.ICON_URL, "icon_mask_base_uri");
        zzhuVar.zza(Place.Field.ICON_BACKGROUND_COLOR, "icon_background_color");
        zza = zzhuVar.zzb();
    }

    public static String zza(List list) {
        StringBuilder sb = new StringBuilder();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) zza.get((Place.Field) it.next());
            if (!TextUtils.isEmpty(str)) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static List zzb(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) zza.get((Place.Field) it.next());
            if (str != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }
}
