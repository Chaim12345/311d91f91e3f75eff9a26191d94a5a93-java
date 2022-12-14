package com.google.android.libraries.places.internal;

import com.google.api.client.googleapis.media.MediaHttpDownloader;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
/* loaded from: classes2.dex */
public final class zzya extends zzadk implements zzaes {
    private static final zzya zzb;
    private zzwj zzA;
    private zzaao zzB;
    private boolean zzC;
    private zzwt zzE;
    private boolean zzF;
    private int zzH;
    private int zzK;
    private int zze;
    private int zzf;
    private zzln zzh;
    private zzop zzi;
    private int zzj;
    private float zzk;
    private zzzm zzm;
    private zzww zzo;
    private zzxd zzp;
    private zzze zzq;
    private zzyo zzr;
    private zzyx zzs;
    private zzyt zzt;
    private zzzj zzu;
    private zzxp zzv;
    private zzyc zzw;
    private zzyz zzx;
    private zzxs zzy;
    private zzxg zzz;
    private byte zzM = 2;
    private int zzg = 1;
    private zzadr zzl = zzadk.zzB();
    private zzadr zzn = zzadk.zzB();
    private String zzD = "";
    private String zzG = "";
    private String zzI = "";
    private String zzJ = "";
    private String zzL = "";

    static {
        zzya zzyaVar = new zzya();
        zzb = zzyaVar;
        zzadk.zzG(zzya.class, zzyaVar);
    }

    private zzya() {
    }

    public static zzxv zza() {
        return (zzxv) zzb.zzx();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzd(zzya zzyaVar, zzzm zzzmVar) {
        zzzmVar.getClass();
        zzyaVar.zzm = zzzmVar;
        zzyaVar.zze |= 64;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zze(zzya zzyaVar, zzze zzzeVar) {
        zzzeVar.getClass();
        zzyaVar.zzq = zzzeVar;
        zzyaVar.zze |= 512;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzf(zzya zzyaVar, zzyo zzyoVar) {
        zzyoVar.getClass();
        zzyaVar.zzr = zzyoVar;
        zzyaVar.zze |= 1024;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzg(zzya zzyaVar, zzln zzlnVar) {
        zzlnVar.getClass();
        zzyaVar.zzh = zzlnVar;
        zzyaVar.zze |= 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzh(zzya zzyaVar, zzxp zzxpVar) {
        zzxpVar.getClass();
        zzyaVar.zzv = zzxpVar;
        zzyaVar.zze |= 16384;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzi(zzya zzyaVar, zzwt zzwtVar) {
        zzwtVar.getClass();
        zzyaVar.zzE = zzwtVar;
        zzyaVar.zze |= 8388608;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzj(zzya zzyaVar, boolean z) {
        zzyaVar.zze |= 16777216;
        zzyaVar.zzF = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzk(zzya zzyaVar, String str) {
        str.getClass();
        zzyaVar.zze |= MediaHttpDownloader.MAXIMUM_CHUNK_SIZE;
        zzyaVar.zzG = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzl(zzya zzyaVar, String str) {
        zzyaVar.zze |= 134217728;
        zzyaVar.zzI = "2.6.0";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzm(zzya zzyaVar, String str) {
        str.getClass();
        zzyaVar.zze |= 1073741824;
        zzyaVar.zzL = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzn(zzya zzyaVar, int i2) {
        zzyaVar.zzg = i2;
        zzyaVar.zze |= 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzo(zzya zzyaVar, int i2) {
        zzyaVar.zzK = i2 - 1;
        zzyaVar.zze |= PKIFailureInfo.duplicateCertReq;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzadk
    public final Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            this.zzM = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zzb;
                    }
                    return new zzxv(null);
                }
                return new zzya();
            }
            return zzadk.zzF(zzb, "\u0001!\u0000\u0001\u0001!!\u0000\u0002\u0003\u0001???\u0001\u0002???\u0002\u0003???\u0003\u0004\u001b\u0005???\u0006\u0006\u001b\u0007???\u0007\b???\b\t???\u0004\n???\u0005\u000b???\u0015\f???\t\r???\u0016\u000e???\n\u000f???\u000b\u0010???\f\u0011???\r\u0012???\u000e\u0013???\u000f\u0014???\u0010\u0015???\u0011\u0016???\u0012\u0017???\u0013\u0018???\u0017\u0019???\u0000\u001a???\u0014\u001b???\u0018\u001c???\u0019\u001d???\u001a\u001e???\u001b\u001f???\u001c ???\u001d!???\u001e", new Object[]{"zze", "zzg", zzxx.zza, "zzh", "zzi", "zzl", zzaas.class, "zzm", "zzn", zzww.class, "zzo", "zzp", "zzj", zzxw.zza, "zzk", "zzC", "zzq", "zzD", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzA", "zzE", "zzf", "zzB", "zzF", "zzG", "zzH", zzxy.zza, "zzI", "zzJ", "zzK", zzxz.zza, "zzL"});
        }
        return Byte.valueOf(this.zzM);
    }
}
