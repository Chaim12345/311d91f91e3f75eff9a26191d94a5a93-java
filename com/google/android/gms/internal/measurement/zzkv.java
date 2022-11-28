package com.google.android.gms.internal.measurement;
/* loaded from: classes.dex */
final class zzkv extends zzkx {
    private zzkv() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzkv(zzku zzkuVar) {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzkx
    public final void a(Object obj, long j2) {
        ((zzkj) zzmv.f(obj, j2)).zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzkx
    public final void b(Object obj, Object obj2, long j2) {
        zzkj zzkjVar = (zzkj) zzmv.f(obj, j2);
        zzkj zzkjVar2 = (zzkj) zzmv.f(obj2, j2);
        int size = zzkjVar.size();
        int size2 = zzkjVar2.size();
        if (size > 0 && size2 > 0) {
            if (!zzkjVar.zzc()) {
                zzkjVar = zzkjVar.zzd(size2 + size);
            }
            zzkjVar.addAll(zzkjVar2);
        }
        if (size > 0) {
            zzkjVar2 = zzkjVar;
        }
        zzmv.s(obj, j2, zzkjVar2);
    }
}
