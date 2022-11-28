package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes2.dex */
final class zzey extends zzez {
    private zzey() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzey(zzew zzewVar) {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzez
    public final void a(Object obj, long j2) {
        ((zzek) zzgz.f(obj, j2)).zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzez
    public final void b(Object obj, Object obj2, long j2) {
        zzek zzekVar = (zzek) zzgz.f(obj, j2);
        zzek zzekVar2 = (zzek) zzgz.f(obj2, j2);
        int size = zzekVar.size();
        int size2 = zzekVar2.size();
        if (size > 0 && size2 > 0) {
            if (!zzekVar.zzc()) {
                zzekVar = zzekVar.zzd(size2 + size);
            }
            zzekVar.addAll(zzekVar2);
        }
        if (size > 0) {
            zzekVar2 = zzekVar;
        }
        zzgz.s(obj, j2, zzekVar2);
    }
}
