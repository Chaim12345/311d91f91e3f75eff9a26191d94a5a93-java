package com.google.android.libraries.places.internal;

import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.auto.value.AutoValue;
import java.util.ArrayList;
import java.util.List;
@AutoValue
/* loaded from: classes2.dex */
public abstract class zzfl implements Parcelable {
    public static zzfk zzm(AutocompleteActivityMode autocompleteActivityMode, List list, zzfj zzfjVar) {
        zzfb zzfbVar = new zzfb();
        zzfbVar.zza(new ArrayList());
        zzfbVar.zzf(autocompleteActivityMode);
        zzfbVar.zzh(list);
        zzfbVar.zzg(zzfjVar);
        zzfbVar.zzi(0);
        zzfbVar.zzj(0);
        return zzfbVar;
    }

    public abstract int zza();

    public abstract int zzb();

    @Nullable
    public abstract LocationBias zzc();

    @Nullable
    public abstract LocationRestriction zzd();

    @Nullable
    public abstract TypeFilter zze();

    public abstract zzfj zzf();

    public abstract zzfk zzg();

    public abstract AutocompleteActivityMode zzh();

    public abstract zzhs zzi();

    public abstract zzhs zzj();

    @Nullable
    public abstract String zzk();

    @Nullable
    public abstract String zzl();
}
