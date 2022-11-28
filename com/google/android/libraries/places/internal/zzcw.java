package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzcw extends zzdf {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcw(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest, Locale locale, String str, boolean z, zzez zzezVar) {
        super(findAutocompletePredictionsRequest, locale, str, false, zzezVar);
    }

    @Override // com.google.android.libraries.places.internal.zzdf
    protected final String zze() {
        return "autocomplete/json";
    }

    @Override // com.google.android.libraries.places.internal.zzdf
    public final Map zzf() {
        HashMap hashMap = new HashMap();
        FindAutocompletePredictionsRequest findAutocompletePredictionsRequest = (FindAutocompletePredictionsRequest) zzb();
        TypeFilter typeFilter = findAutocompletePredictionsRequest.getTypeFilter();
        String query = findAutocompletePredictionsRequest.getQuery();
        zzdf.zzg(hashMap, "input", query == null ? null : query.replaceFirst("^\\s+", "").replaceFirst("\\s+$", " "), null);
        zzdf.zzg(hashMap, "types", typeFilter != null ? zzdz.zza(typeFilter) : null, null);
        zzdf.zzg(hashMap, "sessiontoken", findAutocompletePredictionsRequest.getSessionToken(), null);
        zzdf.zzg(hashMap, "origin", zzdx.zzd(findAutocompletePredictionsRequest.getOrigin()), null);
        zzdf.zzg(hashMap, "locationbias", zzdx.zze(findAutocompletePredictionsRequest.getLocationBias()), null);
        zzdf.zzg(hashMap, "locationrestriction", zzdx.zzf(findAutocompletePredictionsRequest.getLocationRestriction()), null);
        zzdf.zzg(hashMap, "components", zzdx.zzb(findAutocompletePredictionsRequest.getCountries()), null);
        return hashMap;
    }
}
