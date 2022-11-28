package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.model.TypeFilter;
/* loaded from: classes2.dex */
public final class zzdz {
    private static final zzhv zza;

    static {
        zzhu zzhuVar = new zzhu();
        zzhuVar.zza(TypeFilter.ADDRESS, "address");
        zzhuVar.zza(TypeFilter.CITIES, "(cities)");
        zzhuVar.zza(TypeFilter.ESTABLISHMENT, "establishment");
        zzhuVar.zza(TypeFilter.GEOCODE, "geocode");
        zzhuVar.zza(TypeFilter.REGIONS, "(regions)");
        zza = zzhuVar.zzb();
    }

    public static String zza(TypeFilter typeFilter) {
        String str = (String) zza.get(typeFilter);
        return str == null ? "" : str;
    }
}
