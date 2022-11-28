package com.google.android.libraries.places.internal;

import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
/* loaded from: classes2.dex */
public final class zzfo {
    public static Status zza(Intent intent) {
        try {
            zzha.zzc(intent, "Intent must not be null.");
            Status status = (Status) intent.getParcelableExtra("places/status");
            zzha.zze(status != null, "Intent expected to contain a Status, but doesn't.");
            return status;
        } catch (Error | RuntimeException e2) {
            zzev.zzb(e2);
            throw e2;
        }
    }

    public static Place zzb(Intent intent) {
        try {
            zzha.zzc(intent, "Intent must not be null.");
            Place place = (Place) intent.getParcelableExtra("places/selected_place");
            zzha.zze(place != null, "Intent expected to contain a Place, but doesn't.");
            return place;
        } catch (Error | RuntimeException e2) {
            zzev.zzb(e2);
            throw e2;
        }
    }
}
