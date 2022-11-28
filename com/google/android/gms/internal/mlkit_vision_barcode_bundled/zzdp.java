package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Map;
/* loaded from: classes2.dex */
final class zzdp extends zzdo {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo
    public final int a(Map.Entry entry) {
        return ((zzdz) entry.getKey()).f6418a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo
    public final zzds b(Object obj) {
        return ((zzdy) obj).zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo
    public final zzds c(Object obj) {
        return ((zzdy) obj).q();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo
    public final Object d(zzdn zzdnVar, zzfl zzflVar, int i2) {
        return zzdnVar.zzb(zzflVar, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo
    public final void e(Object obj) {
        ((zzdy) obj).zza.zzg();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo
    public final boolean f(zzfl zzflVar) {
        return zzflVar instanceof zzdy;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo
    public final void g(zzdj zzdjVar, Map.Entry entry) {
        zzdz zzdzVar = (zzdz) entry.getKey();
        zzhf zzhfVar = zzhf.zza;
        switch (zzdzVar.f6419b.ordinal()) {
            case 0:
                zzdjVar.zzf(zzdzVar.f6418a, ((Double) entry.getValue()).doubleValue());
                return;
            case 1:
                zzdjVar.zzo(zzdzVar.f6418a, ((Float) entry.getValue()).floatValue());
                return;
            case 2:
                zzdjVar.zzt(zzdzVar.f6418a, ((Long) entry.getValue()).longValue());
                return;
            case 3:
                zzdjVar.zzK(zzdzVar.f6418a, ((Long) entry.getValue()).longValue());
                return;
            case 4:
                zzdjVar.zzr(zzdzVar.f6418a, ((Integer) entry.getValue()).intValue());
                return;
            case 5:
                zzdjVar.zzm(zzdzVar.f6418a, ((Long) entry.getValue()).longValue());
                return;
            case 6:
                zzdjVar.zzk(zzdzVar.f6418a, ((Integer) entry.getValue()).intValue());
                return;
            case 7:
                zzdjVar.zzb(zzdzVar.f6418a, ((Boolean) entry.getValue()).booleanValue());
                return;
            case 8:
                zzdjVar.zzG(zzdzVar.f6418a, (String) entry.getValue());
                return;
            case 9:
                zzdjVar.zzq(zzdzVar.f6418a, entry.getValue(), zzfu.zza().zzb(entry.getValue().getClass()));
                return;
            case 10:
                zzdjVar.zzv(zzdzVar.f6418a, entry.getValue(), zzfu.zza().zzb(entry.getValue().getClass()));
                return;
            case 11:
                zzdjVar.zzd(zzdzVar.f6418a, (zzdb) entry.getValue());
                return;
            case 12:
                zzdjVar.zzI(zzdzVar.f6418a, ((Integer) entry.getValue()).intValue());
                return;
            case 13:
                zzdjVar.zzr(zzdzVar.f6418a, ((Integer) entry.getValue()).intValue());
                return;
            case 14:
                zzdjVar.zzx(zzdzVar.f6418a, ((Integer) entry.getValue()).intValue());
                return;
            case 15:
                zzdjVar.zzz(zzdzVar.f6418a, ((Long) entry.getValue()).longValue());
                return;
            case 16:
                zzdjVar.zzB(zzdzVar.f6418a, ((Integer) entry.getValue()).intValue());
                return;
            case 17:
                zzdjVar.zzD(zzdzVar.f6418a, ((Long) entry.getValue()).longValue());
                return;
            default:
                return;
        }
    }
}
