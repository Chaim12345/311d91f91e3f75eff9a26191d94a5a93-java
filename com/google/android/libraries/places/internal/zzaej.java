package com.google.android.libraries.places.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzaej implements zzafd {
    private static final zzaep zza = new zzaeh();
    private final zzaep zzb;

    public zzaej() {
        zzaep zzaepVar;
        zzaep[] zzaepVarArr = new zzaep[2];
        zzaepVarArr[0] = zzadg.zza();
        try {
            zzaepVar = (zzaep) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            zzaepVar = zza;
        }
        zzaepVarArr[1] = zzaepVar;
        zzaei zzaeiVar = new zzaei(zzaepVarArr);
        zzads.zzf(zzaeiVar, "messageInfoFactory");
        this.zzb = zzaeiVar;
    }

    private static boolean zzb(zzaeo zzaeoVar) {
        return zzaeoVar.zzc() == 1;
    }

    @Override // com.google.android.libraries.places.internal.zzafd
    public final zzafc zza(Class cls) {
        zzaew zza2;
        zzaef zzc;
        zzaft zzA;
        zzada zzadaVar;
        zzaem zza3;
        zzaft zzz;
        zzada zza4;
        zzafe.zzE(cls);
        zzaeo zzb = this.zzb.zzb(cls);
        if (zzb.zzb()) {
            if (zzadk.class.isAssignableFrom(cls)) {
                zzz = zzafe.zzB();
                zza4 = zzadc.zzb();
            } else {
                zzz = zzafe.zzz();
                zza4 = zzadc.zza();
            }
            return zzaev.zzg(zzz, zza4, zzb.zza());
        }
        if (zzadk.class.isAssignableFrom(cls)) {
            boolean zzb2 = zzb(zzb);
            zza2 = zzaex.zzb();
            zzc = zzaef.zzd();
            zzA = zzafe.zzB();
            zzadaVar = zzb2 ? zzadc.zzb() : null;
            zza3 = zzaen.zzb();
        } else {
            boolean zzb3 = zzb(zzb);
            zza2 = zzaex.zza();
            zzc = zzaef.zzc();
            if (zzb3) {
                zzA = zzafe.zzz();
                zzadaVar = zzadc.zza();
            } else {
                zzA = zzafe.zzA();
                zzadaVar = null;
            }
            zza3 = zzaen.zza();
        }
        return zzaeu.zzg(cls, zzb, zza2, zzc, zzA, zzadaVar, zza3);
    }
}
