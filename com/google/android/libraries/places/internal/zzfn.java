package com.google.android.libraries.places.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import com.google.auto.value.AutoValue;
import java.util.List;
import java.util.Objects;
@AutoValue
/* loaded from: classes2.dex */
public abstract class zzfn {
    public static zzfn zzg() {
        return zzr(3).zzf();
    }

    public static zzfn zzh(String str) {
        Objects.requireNonNull(str);
        zzfm zzr = zzr(6);
        zzr.zzd(str);
        return zzr.zzf();
    }

    public static zzfn zzi(String str, Status status) {
        Objects.requireNonNull(str);
        Objects.requireNonNull(status);
        zzfm zzr = zzr(7);
        zzr.zzd(str);
        zzr.zze(status);
        return zzr.zzf();
    }

    public static zzfn zzj(List list) {
        Objects.requireNonNull(list);
        zzfm zzr = zzr(5);
        zzr.zzc(list);
        return zzr.zzf();
    }

    public static zzfn zzk() {
        return zzr(2).zzf();
    }

    public static zzfn zzl() {
        zzfm zzr = zzr(10);
        zzr.zze(new Status(16));
        return zzr.zzf();
    }

    public static zzfn zzm(AutocompletePrediction autocompletePrediction, Status status) {
        Objects.requireNonNull(autocompletePrediction);
        Objects.requireNonNull(status);
        zzfm zzr = zzr(9);
        zzr.zzb(autocompletePrediction);
        zzr.zze(status);
        return zzr.zzf();
    }

    public static zzfn zzn(Place place) {
        Objects.requireNonNull(place);
        zzfm zzr = zzr(8);
        zzr.zza(place);
        return zzr.zzf();
    }

    public static zzfn zzo() {
        return zzr(1).zzf();
    }

    public static zzfn zzp() {
        return zzr(4).zzf();
    }

    public static zzfn zzq(Status status) {
        Objects.requireNonNull(status);
        zzfm zzr = zzr(10);
        zzr.zze(status);
        return zzr.zzf();
    }

    private static zzfm zzr(int i2) {
        zzff zzffVar = new zzff();
        zzffVar.zzg(i2);
        return zzffVar;
    }

    @Nullable
    public abstract Status zza();

    @Nullable
    public abstract AutocompletePrediction zzb();

    @Nullable
    public abstract Place zzc();

    @Nullable
    public abstract zzhs zzd();

    @Nullable
    public abstract String zze();

    public abstract int zzf();
}
