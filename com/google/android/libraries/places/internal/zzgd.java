package com.google.android.libraries.places.internal;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
/* loaded from: classes2.dex */
public final class zzgd implements ViewModelProvider.Factory {
    private final zzfs zza;
    private final zzgi zzb;
    private final zzgj zzc;

    public zzgd(zzfs zzfsVar, zzgi zzgiVar, zzgj zzgjVar) {
        this.zza = zzfsVar;
        this.zzb = zzgiVar;
        this.zzc = zzgjVar;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public final ViewModel create(Class cls) {
        zzha.zze(cls == zzgf.class, "This factory can only be used to instantiate its enclosing class.");
        return new zzgf(this.zza, this.zzb, this.zzc, null);
    }
}
