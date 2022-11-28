package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzfd implements zzgc {
    private static final zzfj zza = new zzfb();
    private final zzfj zzb;

    public zzfd() {
        zzfj zzfjVar;
        zzfj[] zzfjVarArr = new zzfj[2];
        zzfjVarArr[0] = zzdv.zza();
        try {
            zzfjVar = (zzfj) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            zzfjVar = zza;
        }
        zzfjVarArr[1] = zzfjVar;
        zzfc zzfcVar = new zzfc(zzfjVarArr);
        zzel.c(zzfcVar, "messageInfoFactory");
        this.zzb = zzfcVar;
    }

    private static boolean zzb(zzfi zzfiVar) {
        return zzfiVar.zzc() == 1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgc
    public final zzgb zza(Class cls) {
        zzfr a2;
        zzez c2;
        zzgp zzA;
        zzdo zzdoVar;
        zzfg a3;
        zzgp zzz;
        zzdo a4;
        zzgd.zzG(cls);
        zzfi zzb = this.zzb.zzb(cls);
        if (zzb.zzb()) {
            if (zzec.class.isAssignableFrom(cls)) {
                zzz = zzgd.zzB();
                a4 = zzdq.b();
            } else {
                zzz = zzgd.zzz();
                a4 = zzdq.a();
            }
            return zzfp.a(zzz, a4, zzb.zza());
        }
        if (zzec.class.isAssignableFrom(cls)) {
            boolean zzb2 = zzb(zzb);
            a2 = zzfs.b();
            c2 = zzez.d();
            zzA = zzgd.zzB();
            zzdoVar = zzb2 ? zzdq.b() : null;
            a3 = zzfh.b();
        } else {
            boolean zzb3 = zzb(zzb);
            a2 = zzfs.a();
            c2 = zzez.c();
            if (zzb3) {
                zzA = zzgd.zzz();
                zzdoVar = zzdq.a();
            } else {
                zzA = zzgd.zzA();
                zzdoVar = null;
            }
            a3 = zzfh.a();
        }
        return zzfo.c(cls, zzb, a2, c2, zzA, zzdoVar, a3);
    }
}
