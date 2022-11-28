package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes2.dex */
final class zzdq {
    private static final zzdo zza = new zzdp();
    private static final zzdo zzb;

    static {
        zzdo zzdoVar;
        try {
            zzdoVar = (zzdo) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzdoVar = null;
        }
        zzb = zzdoVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzdo a() {
        zzdo zzdoVar = zzb;
        if (zzdoVar != null) {
            return zzdoVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzdo b() {
        return zza;
    }
}
