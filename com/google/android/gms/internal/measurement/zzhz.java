package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
public final class zzhz implements zzhj {
    @GuardedBy("SharedPreferencesLoader.class")
    private static final Map zza = new ArrayMap();
    private final SharedPreferences zzb;
    private final SharedPreferences.OnSharedPreferenceChangeListener zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static zzhz a(Context context, String str) {
        zzhz zzhzVar;
        if (zzha.zza()) {
            throw null;
        }
        synchronized (zzhz.class) {
            zzhzVar = (zzhz) zza.get(null);
            if (zzhzVar == null) {
                StrictMode.allowThreadDiskReads();
                throw null;
            }
        }
        return zzhzVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void b() {
        synchronized (zzhz.class) {
            Map map = zza;
            Iterator it = map.values().iterator();
            if (it.hasNext()) {
                SharedPreferences sharedPreferences = ((zzhz) it.next()).zzb;
                throw null;
            }
            map.clear();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzhj
    @Nullable
    public final Object zzb(String str) {
        throw null;
    }
}
