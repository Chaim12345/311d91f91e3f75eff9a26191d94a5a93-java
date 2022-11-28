package com.google.android.gms.internal.measurement;
/* loaded from: classes.dex */
final class zzlf {
    private static final zzle zza;
    private static final zzle zzb;

    static {
        zzle zzleVar;
        try {
            zzleVar = (zzle) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzleVar = null;
        }
        zza = zzleVar;
        zzb = new zzle();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzle a() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzle b() {
        return zzb;
    }
}
