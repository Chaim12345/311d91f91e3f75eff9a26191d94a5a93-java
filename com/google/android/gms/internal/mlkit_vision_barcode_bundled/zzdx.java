package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes2.dex */
public class zzdx extends zzdw implements zzfm {
    /* JADX INFO: Access modifiers changed from: protected */
    public zzdx(zzdy zzdyVar) {
        super(zzdyVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdw
    public final void b() {
        super.b();
        zzdy zzdyVar = (zzdy) this.f6416a;
        zzdyVar.zza = zzdyVar.zza.clone();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdw, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfk
    /* renamed from: zza */
    public final zzdy zzm() {
        zzec zzm;
        if (this.f6417b) {
            zzm = this.f6416a;
        } else {
            ((zzdy) this.f6416a).zza.zzg();
            zzm = super.zzm();
        }
        return (zzdy) zzm;
    }
}
