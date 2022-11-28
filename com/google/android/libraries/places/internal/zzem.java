package com.google.android.libraries.places.internal;

import androidx.annotation.Nullable;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes2.dex */
public final class zzem {
    @Nullable
    private volatile String zza;
    @Nullable
    private volatile Locale zzb;
    private volatile boolean zzc;

    public final synchronized String zza() {
        zzha.zzi(zzf(), "ApiConfig must be initialized.");
        Objects.requireNonNull(this.zza);
        return this.zza;
    }

    public final synchronized Locale zzb() {
        zzha.zzi(zzf(), "ApiConfig must be initialized.");
        return this.zzb == null ? Locale.getDefault() : this.zzb;
    }

    public final synchronized void zzc() {
        this.zza = null;
        this.zzb = null;
    }

    public final synchronized void zzd(String str, @Nullable Locale locale, boolean z) {
        zzha.zzc(str, "API Key must not be null.");
        zzha.zze(!str.isEmpty(), "API Key must not be empty.");
        this.zza = str;
        this.zzb = locale;
        this.zzc = false;
    }

    public final boolean zze() {
        return false;
    }

    public final synchronized boolean zzf() {
        return this.zza != null;
    }
}
