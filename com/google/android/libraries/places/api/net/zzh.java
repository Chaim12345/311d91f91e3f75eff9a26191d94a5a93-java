package com.google.android.libraries.places.api.net;

import com.google.android.libraries.places.api.model.Place;
import java.util.Objects;
/* loaded from: classes2.dex */
final class zzh extends FetchPlaceResponse {
    private final Place zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzh(Place place) {
        Objects.requireNonNull(place, "Null place");
        this.zza = place;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FetchPlaceResponse) {
            return this.zza.equals(((FetchPlaceResponse) obj).getPlace());
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.net.FetchPlaceResponse
    public final Place getPlace() {
        return this.zza;
    }

    public final int hashCode() {
        return this.zza.hashCode() ^ 1000003;
    }

    public final String toString() {
        String obj = this.zza.toString();
        StringBuilder sb = new StringBuilder(obj.length() + 26);
        sb.append("FetchPlaceResponse{place=");
        sb.append(obj);
        sb.append("}");
        return sb.toString();
    }
}
