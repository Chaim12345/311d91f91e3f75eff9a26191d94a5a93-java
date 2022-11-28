package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
final class zzadg implements zzaep {
    private static final zzadg zza = new zzadg();

    private zzadg() {
    }

    public static zzadg zza() {
        return zza;
    }

    @Override // com.google.android.libraries.places.internal.zzaep
    public final zzaeo zzb(Class cls) {
        if (!zzadk.class.isAssignableFrom(cls)) {
            String name = cls.getName();
            throw new IllegalArgumentException(name.length() != 0 ? "Unsupported message type: ".concat(name) : new String("Unsupported message type: "));
        }
        try {
            return (zzaeo) zzadk.zzy(cls.asSubclass(zzadk.class)).zzb(3, null, null);
        } catch (Exception e2) {
            String name2 = cls.getName();
            throw new RuntimeException(name2.length() != 0 ? "Unable to get message info for ".concat(name2) : new String("Unable to get message info for "), e2);
        }
    }

    @Override // com.google.android.libraries.places.internal.zzaep
    public final boolean zzc(Class cls) {
        return zzadk.class.isAssignableFrom(cls);
    }
}
