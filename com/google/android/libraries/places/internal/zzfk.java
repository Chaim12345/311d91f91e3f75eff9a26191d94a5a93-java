package com.google.android.libraries.places.internal;

import androidx.annotation.Nullable;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.auto.value.AutoValue;
import java.util.List;
@AutoValue.Builder
/* loaded from: classes2.dex */
public abstract class zzfk {
    public abstract zzfk zza(List list);

    public abstract zzfk zzb(@Nullable String str);

    public abstract zzfk zzc(@Nullable String str);

    public abstract zzfk zzd(@Nullable LocationBias locationBias);

    public abstract zzfk zze(@Nullable LocationRestriction locationRestriction);

    public abstract zzfk zzf(AutocompleteActivityMode autocompleteActivityMode);

    public abstract zzfk zzg(zzfj zzfjVar);

    public abstract zzfk zzh(List list);

    public abstract zzfk zzi(int i2);

    public abstract zzfk zzj(int i2);

    public abstract zzfk zzk(@Nullable TypeFilter typeFilter);

    public abstract zzfl zzl();

    public final zzfk zzm(@Nullable String str) {
        return zza(str == null ? zzhs.zzm() : zzhs.zzn(str));
    }
}
