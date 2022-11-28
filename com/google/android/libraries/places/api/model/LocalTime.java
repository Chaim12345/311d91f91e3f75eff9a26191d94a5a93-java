package com.google.android.libraries.places.api.model;

import android.os.Parcelable;
import androidx.annotation.IntRange;
import androidx.annotation.RecentlyNonNull;
import com.google.android.libraries.places.internal.zzha;
import com.google.android.libraries.places.internal.zzie;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes2.dex */
public abstract class LocalTime implements Parcelable, Comparable<LocalTime> {
    @RecentlyNonNull
    public static LocalTime newInstance(@IntRange(from = 0, to = 23) int i2, @IntRange(from = 0, to = 59) int i3) {
        try {
            zzi zziVar = new zzi();
            zziVar.zza(i2);
            zziVar.zzb(i3);
            LocalTime zzc = zziVar.zzc();
            int hours = zzc.getHours();
            zzha.zzj(zzie.zzc(0, 23).zze(Integer.valueOf(hours)), "Hours must not be out-of-range: 0 to 23, but was: %s.", hours);
            int minutes = zzc.getMinutes();
            zzha.zzj(zzie.zzc(0, 59).zze(Integer.valueOf(minutes)), "Minutes must not be out-of-range: 0 to 59, but was: %s.", minutes);
            return zzc;
        } catch (IllegalStateException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(@RecentlyNonNull LocalTime localTime) {
        int hours;
        int hours2;
        zzha.zzc(localTime, "compare must not be null.");
        if (this == localTime) {
            return 0;
        }
        if (getHours() == localTime.getHours()) {
            hours = getMinutes();
            hours2 = localTime.getMinutes();
        } else {
            hours = getHours();
            hours2 = localTime.getHours();
        }
        return hours - hours2;
    }

    @IntRange(from = 0, to = 23)
    public abstract int getHours();

    @IntRange(from = 0, to = 59)
    public abstract int getMinutes();
}
