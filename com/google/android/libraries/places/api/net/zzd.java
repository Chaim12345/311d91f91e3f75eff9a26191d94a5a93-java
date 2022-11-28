package com.google.android.libraries.places.api.net;

import android.graphics.Bitmap;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzd extends FetchPhotoResponse {
    private final Bitmap zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzd(Bitmap bitmap) {
        Objects.requireNonNull(bitmap, "Null bitmap");
        this.zza = bitmap;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FetchPhotoResponse) {
            return this.zza.equals(((FetchPhotoResponse) obj).getBitmap());
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.net.FetchPhotoResponse
    public final Bitmap getBitmap() {
        return this.zza;
    }

    public final int hashCode() {
        return this.zza.hashCode() ^ 1000003;
    }

    public final String toString() {
        String obj = this.zza.toString();
        StringBuilder sb = new StringBuilder(obj.length() + 27);
        sb.append("FetchPhotoResponse{bitmap=");
        sb.append(obj);
        sb.append("}");
        return sb.toString();
    }
}
