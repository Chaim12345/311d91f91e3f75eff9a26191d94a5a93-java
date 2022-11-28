package com.google.android.libraries.places.api.model;

import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class zzx extends TimeOfWeek {
    private final DayOfWeek zza;
    private final LocalTime zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzx(DayOfWeek dayOfWeek, LocalTime localTime) {
        Objects.requireNonNull(dayOfWeek, "Null day");
        this.zza = dayOfWeek;
        Objects.requireNonNull(localTime, "Null time");
        this.zzb = localTime;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TimeOfWeek) {
            TimeOfWeek timeOfWeek = (TimeOfWeek) obj;
            if (this.zza.equals(timeOfWeek.getDay()) && this.zzb.equals(timeOfWeek.getTime())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.TimeOfWeek
    public final DayOfWeek getDay() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.model.TimeOfWeek
    public final LocalTime getTime() {
        return this.zzb;
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode();
    }

    public final String toString() {
        String obj = this.zza.toString();
        String obj2 = this.zzb.toString();
        StringBuilder sb = new StringBuilder(obj.length() + 23 + obj2.length());
        sb.append("TimeOfWeek{day=");
        sb.append(obj);
        sb.append(", time=");
        sb.append(obj2);
        sb.append("}");
        return sb.toString();
    }
}
