package com.google.android.gms.internal.measurement;
/* loaded from: classes.dex */
final class zzla implements zzlh {
    private final zzlh[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzla(zzlh... zzlhVarArr) {
        this.zza = zzlhVarArr;
    }

    @Override // com.google.android.gms.internal.measurement.zzlh
    public final zzlg zzb(Class cls) {
        zzlh[] zzlhVarArr = this.zza;
        for (int i2 = 0; i2 < 2; i2++) {
            zzlh zzlhVar = zzlhVarArr[i2];
            if (zzlhVar.zzc(cls)) {
                return zzlhVar.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
    }

    @Override // com.google.android.gms.internal.measurement.zzlh
    public final boolean zzc(Class cls) {
        zzlh[] zzlhVarArr = this.zza;
        for (int i2 = 0; i2 < 2; i2++) {
            if (zzlhVarArr[i2].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
