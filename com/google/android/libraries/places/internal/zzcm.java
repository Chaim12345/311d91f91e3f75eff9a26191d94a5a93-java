package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzcm extends zzdf {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcm(FetchPhotoRequest fetchPhotoRequest, String str, boolean z, zzez zzezVar) {
        super(fetchPhotoRequest, null, str, false, zzezVar);
    }

    @Override // com.google.android.libraries.places.internal.zzdf
    protected final String zze() {
        return "photo";
    }

    @Override // com.google.android.libraries.places.internal.zzdf
    public final Map zzf() {
        FetchPhotoRequest fetchPhotoRequest = (FetchPhotoRequest) zzb();
        PhotoMetadata photoMetadata = fetchPhotoRequest.getPhotoMetadata();
        HashMap hashMap = new HashMap();
        zzdf.zzg(hashMap, "maxheight", fetchPhotoRequest.getMaxHeight(), null);
        zzdf.zzg(hashMap, "maxwidth", fetchPhotoRequest.getMaxWidth(), null);
        hashMap.put("photoreference", photoMetadata.zza());
        return hashMap;
    }
}
