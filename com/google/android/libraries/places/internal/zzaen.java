package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
final class zzaen {
    private static final zzaem zza;
    private static final zzaem zzb;

    static {
        zzaem zzaemVar;
        try {
            zzaemVar = (zzaem) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzaemVar = null;
        }
        zza = zzaemVar;
        zzb = new zzaem();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzaem zza() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzaem zzb() {
        return zzb;
    }
}
