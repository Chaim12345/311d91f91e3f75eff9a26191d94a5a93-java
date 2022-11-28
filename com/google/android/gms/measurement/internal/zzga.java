package com.google.android.gms.measurement.internal;

import java.util.Map;
/* loaded from: classes2.dex */
final class zzga implements com.google.android.gms.internal.measurement.zzo {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f6745a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzgb f6746b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzga(zzgb zzgbVar, String str) {
        this.f6746b = zzgbVar;
        this.f6745a = str;
    }

    @Override // com.google.android.gms.internal.measurement.zzo
    public final String zza(String str) {
        Map map;
        map = this.f6746b.zzg;
        Map map2 = (Map) map.get(this.f6745a);
        if (map2 == null || !map2.containsKey(str)) {
            return null;
        }
        return (String) map2.get(str);
    }
}
