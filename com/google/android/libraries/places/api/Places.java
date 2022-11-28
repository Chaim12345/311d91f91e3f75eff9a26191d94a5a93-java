package com.google.android.libraries.places.api;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.internal.zzei;
import com.google.android.libraries.places.internal.zzej;
import com.google.android.libraries.places.internal.zzek;
import com.google.android.libraries.places.internal.zzem;
import com.google.android.libraries.places.internal.zzet;
import com.google.android.libraries.places.internal.zzev;
import com.google.android.libraries.places.internal.zzha;
import java.util.Locale;
/* loaded from: classes2.dex */
public final class Places {
    private static final zzem zza = new zzem();
    @Nullable
    private static volatile zzek zzb;

    private Places() {
    }

    @RecentlyNonNull
    public static synchronized PlacesClient createClient(@RecentlyNonNull Context context) {
        PlacesClient zza2;
        synchronized (Places.class) {
            try {
                zzha.zzc(context, "Context must not be null.");
                zza2 = zza(context, zzet.zzd(context).zze());
            } catch (Error | RuntimeException e2) {
                zzev.zzb(e2);
                throw e2;
            }
        }
        return zza2;
    }

    public static synchronized void deinitialize() {
        synchronized (Places.class) {
            zza.zzc();
        }
    }

    public static void initialize(@RecentlyNonNull Context context, @RecentlyNonNull String str) {
        try {
            zzb(context, str, null, false);
        } catch (Error | RuntimeException e2) {
            zzev.zzb(e2);
            throw e2;
        }
    }

    public static synchronized void initialize(@RecentlyNonNull Context context, @RecentlyNonNull String str, @Nullable Locale locale) {
        synchronized (Places.class) {
            try {
                zzb(context, str, locale, false);
            } catch (Error | RuntimeException e2) {
                zzev.zzb(e2);
                throw e2;
            }
        }
    }

    public static synchronized boolean isInitialized() {
        boolean zzf;
        synchronized (Places.class) {
            try {
                zzf = zza.zzf();
            } catch (Error | RuntimeException e2) {
                zzev.zzb(e2);
                throw e2;
            }
        }
        return zzf;
    }

    public static synchronized PlacesClient zza(Context context, zzet zzetVar) {
        PlacesClient zzb2;
        synchronized (Places.class) {
            try {
                zzha.zzc(context, "Context must not be null.");
                zzha.zzi(isInitialized(), "Places must be initialized first.");
                zzej zza2 = zzei.zza();
                zza2.zzc(context);
                zza2.zza(zza);
                zza2.zzb(zzetVar);
                zzb2 = zza2.zzd().zzb();
            } catch (Error | RuntimeException e2) {
                zzev.zzb(e2);
                throw e2;
            }
        }
        return zzb2;
    }

    public static synchronized void zzb(@RecentlyNonNull Context context, @RecentlyNonNull String str, @Nullable Locale locale, boolean z) {
        synchronized (Places.class) {
            try {
                zzha.zzc(context, "Application context must not be null.");
                zzha.zzc(str, "API Key must not be null.");
                zzha.zze(!str.isEmpty(), "API Key must not be empty.");
                zzev.zza(context.getApplicationContext(), false);
                zza.zzd(str, locale, false);
            } catch (Error | RuntimeException e2) {
                zzev.zzb(e2);
                throw e2;
            }
        }
    }
}
