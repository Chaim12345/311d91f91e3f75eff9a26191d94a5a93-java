package com.google.android.libraries.places.api.model;

import com.google.android.libraries.places.api.model.OpeningHours;
import java.util.List;
import java.util.Objects;
/* loaded from: classes2.dex */
final class zzk extends OpeningHours.Builder {
    private List zza;
    private List zzb;

    @Override // com.google.android.libraries.places.api.model.OpeningHours.Builder
    public final List<Period> getPeriods() {
        List<Period> list = this.zza;
        if (list != null) {
            return list;
        }
        throw new IllegalStateException("Property \"periods\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.OpeningHours.Builder
    public final List<String> getWeekdayText() {
        List<String> list = this.zzb;
        if (list != null) {
            return list;
        }
        throw new IllegalStateException("Property \"weekdayText\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.OpeningHours.Builder
    public final OpeningHours.Builder setPeriods(List<Period> list) {
        Objects.requireNonNull(list, "Null periods");
        this.zza = list;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.OpeningHours.Builder
    public final OpeningHours.Builder setWeekdayText(List<String> list) {
        Objects.requireNonNull(list, "Null weekdayText");
        this.zzb = list;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.OpeningHours.Builder
    final OpeningHours zza() {
        List list;
        List list2 = this.zza;
        if (list2 == null || (list = this.zzb) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.zza == null) {
                sb.append(" periods");
            }
            if (this.zzb == null) {
                sb.append(" weekdayText");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new zzal(list2, list);
    }
}
