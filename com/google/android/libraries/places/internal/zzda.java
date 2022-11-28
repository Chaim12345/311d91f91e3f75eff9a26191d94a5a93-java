package com.google.android.libraries.places.internal;

import android.location.Location;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzda extends zzdf {
    private final Location zza;
    private final zzhs zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzda(FindCurrentPlaceRequest findCurrentPlaceRequest, Location location, zzhs zzhsVar, Locale locale, String str, boolean z, zzez zzezVar) {
        super(findCurrentPlaceRequest, locale, str, false, zzezVar);
        this.zza = location;
        this.zzb = zzhsVar;
    }

    @Override // com.google.android.libraries.places.internal.zzdf
    protected final String zze() {
        return "findplacefromuserlocation/json";
    }

    @Override // com.google.android.libraries.places.internal.zzdf
    public final Map zzf() {
        HashMap hashMap = new HashMap();
        zzdf.zzg(hashMap, "location", zzdx.zzc(this.zza), null);
        zzdf.zzg(hashMap, "wifiaccesspoints", zzdx.zzg(this.zzb, 4000), null);
        zzdf.zzg(hashMap, "precision", zzdx.zza(this.zza), null);
        zzdf.zzg(hashMap, "timestamp", Long.valueOf(this.zza.getTime()), null);
        zzdf.zzg(hashMap, "fields", zzdy.zza(((FindCurrentPlaceRequest) zzb()).getPlaceFields()), null);
        return hashMap;
    }
}
