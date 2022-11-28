package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
final class zzaei implements zzaep {
    private final zzaep[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaei(zzaep... zzaepVarArr) {
        this.zza = zzaepVarArr;
    }

    @Override // com.google.android.libraries.places.internal.zzaep
    public final zzaeo zzb(Class cls) {
        zzaep[] zzaepVarArr = this.zza;
        for (int i2 = 0; i2 < 2; i2++) {
            zzaep zzaepVar = zzaepVarArr[i2];
            if (zzaepVar.zzc(cls)) {
                return zzaepVar.zzb(cls);
            }
        }
        String name = cls.getName();
        throw new UnsupportedOperationException(name.length() != 0 ? "No factory is available for message type: ".concat(name) : new String("No factory is available for message type: "));
    }

    @Override // com.google.android.libraries.places.internal.zzaep
    public final boolean zzc(Class cls) {
        zzaep[] zzaepVarArr = this.zza;
        for (int i2 = 0; i2 < 2; i2++) {
            if (zzaepVarArr[i2].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
