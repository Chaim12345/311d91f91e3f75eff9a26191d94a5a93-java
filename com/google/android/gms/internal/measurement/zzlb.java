package com.google.android.gms.internal.measurement;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzlb implements zzlv {
    private static final zzlh zza = new zzkz();
    private final zzlh zzb;

    public zzlb() {
        zzlh zzlhVar;
        zzlh[] zzlhVarArr = new zzlh[2];
        zzlhVarArr[0] = zzjx.zza();
        try {
            zzlhVar = (zzlh) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            zzlhVar = zza;
        }
        zzlhVarArr[1] = zzlhVar;
        zzla zzlaVar = new zzla(zzlhVarArr);
        zzkk.c(zzlaVar, "messageInfoFactory");
        this.zzb = zzlaVar;
    }

    private static boolean zzb(zzlg zzlgVar) {
        return zzlgVar.zzc() == 1;
    }

    @Override // com.google.android.gms.internal.measurement.zzlv
    public final zzlu zza(Class cls) {
        zzlo a2;
        zzkx c2;
        zzml zzA;
        zzjp zzjpVar;
        zzle a3;
        zzml zzz;
        zzjp a4;
        zzlw.zzG(cls);
        zzlg zzb = this.zzb.zzb(cls);
        if (zzb.zzb()) {
            if (zzkc.class.isAssignableFrom(cls)) {
                zzz = zzlw.zzB();
                a4 = zzjr.b();
            } else {
                zzz = zzlw.zzz();
                a4 = zzjr.a();
            }
            return zzln.a(zzz, a4, zzb.zza());
        }
        if (zzkc.class.isAssignableFrom(cls)) {
            boolean zzb2 = zzb(zzb);
            a2 = zzlp.b();
            c2 = zzkx.d();
            zzA = zzlw.zzB();
            zzjpVar = zzb2 ? zzjr.b() : null;
            a3 = zzlf.b();
        } else {
            boolean zzb3 = zzb(zzb);
            a2 = zzlp.a();
            c2 = zzkx.c();
            if (zzb3) {
                zzA = zzlw.zzz();
                zzjpVar = zzjr.a();
            } else {
                zzA = zzlw.zzA();
                zzjpVar = null;
            }
            a3 = zzlf.a();
        }
        return zzlm.c(cls, zzb, a2, c2, zzA, zzjpVar, a3);
    }
}
