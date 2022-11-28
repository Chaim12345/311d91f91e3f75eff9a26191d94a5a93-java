package com.google.android.libraries.places.internal;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes2.dex */
abstract class zzdf extends zzca {
    @Nullable
    private final Locale zza;
    private final String zzb;
    private final zzez zzc;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzdf(zzen zzenVar, @Nullable Locale locale, String str, boolean z, zzez zzezVar) {
        super(zzenVar);
        this.zza = locale;
        this.zzb = str;
        this.zzc = zzezVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void zzg(Map map, String str, @Nullable Object obj, @Nullable Object obj2) {
        String obj3 = obj != null ? obj.toString() : null;
        if (TextUtils.isEmpty(obj3)) {
            return;
        }
        map.put(str, obj3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzca
    public final String zzc() {
        zzdr zzdrVar = new zzdr(zze(), this.zzb);
        zzdrVar.zza(this.zza);
        zzdrVar.zzb(zzf());
        return zzdrVar.zzc();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzca
    public final Map zzd() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.zzc.zza());
        hashMap.put("X-Places-Android-Sdk", new String("2.6.0"));
        return hashMap;
    }

    protected abstract String zze();

    protected abstract Map zzf();
}
