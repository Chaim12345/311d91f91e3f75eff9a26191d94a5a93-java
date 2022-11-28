package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
final class zzaed extends zzaef {
    private zzaed() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzaed(zzaec zzaecVar) {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzaef
    public final void zza(Object obj, long j2) {
        ((zzadr) zzagd.zzf(obj, j2)).zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzaef
    public final void zzb(Object obj, Object obj2, long j2) {
        zzadr zzadrVar = (zzadr) zzagd.zzf(obj, j2);
        zzadr zzadrVar2 = (zzadr) zzagd.zzf(obj2, j2);
        int size = zzadrVar.size();
        int size2 = zzadrVar2.size();
        if (size > 0 && size2 > 0) {
            if (!zzadrVar.zzc()) {
                zzadrVar = zzadrVar.zzf(size2 + size);
            }
            zzadrVar.addAll(zzadrVar2);
        }
        if (size > 0) {
            zzadrVar2 = zzadrVar;
        }
        zzagd.zzs(obj, j2, zzadrVar2);
    }
}
