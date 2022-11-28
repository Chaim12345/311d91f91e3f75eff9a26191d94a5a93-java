package com.google.android.gms.internal.measurement;

import com.google.api.client.googleapis.media.MediaHttpDownloader;
import java.util.List;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
/* loaded from: classes.dex */
public final class zzgc extends zzkc implements zzlk {
    public static final /* synthetic */ int zza = 0;
    private static final zzgc zze;
    private boolean zzA;
    private long zzC;
    private int zzD;
    private boolean zzG;
    private int zzJ;
    private int zzK;
    private int zzL;
    private long zzN;
    private long zzO;
    private int zzR;
    private zzgf zzT;
    private long zzV;
    private long zzW;
    private int zzZ;
    private boolean zzaa;
    private boolean zzac;
    private zzfy zzad;
    private int zzf;
    private int zzg;
    private int zzh;
    private long zzk;
    private long zzl;
    private long zzm;
    private long zzn;
    private long zzo;
    private int zzt;
    private long zzx;
    private long zzy;
    private zzkj zzi = zzkc.i();
    private zzkj zzj = zzkc.i();
    private String zzp = "";
    private String zzq = "";
    private String zzr = "";
    private String zzs = "";
    private String zzu = "";
    private String zzv = "";
    private String zzw = "";
    private String zzz = "";
    private String zzB = "";
    private String zzE = "";
    private String zzF = "";
    private zzkj zzH = zzkc.i();
    private String zzI = "";
    private String zzM = "";
    private String zzP = "";
    private String zzQ = "";
    private String zzS = "";
    private zzkh zzU = zzkc.f();
    private String zzX = "";
    private String zzY = "";
    private String zzab = "";
    private String zzae = "";
    private zzkj zzaf = zzkc.i();
    private String zzag = "";

    static {
        zzgc zzgcVar = new zzgc();
        zze = zzgcVar;
        zzkc.m(zzgc.class, zzgcVar);
    }

    private zzgc() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void A(zzgc zzgcVar, String str) {
        str.getClass();
        zzgcVar.zzf |= 8192;
        zzgcVar.zzw = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void B(zzgc zzgcVar, long j2) {
        zzgcVar.zzf |= 16384;
        zzgcVar.zzx = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void C(zzgc zzgcVar, long j2) {
        zzgcVar.zzf |= 32768;
        zzgcVar.zzy = 64000L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void D(zzgc zzgcVar, String str) {
        str.getClass();
        zzgcVar.zzf |= 65536;
        zzgcVar.zzz = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void E(zzgc zzgcVar) {
        zzgcVar.zzf &= -65537;
        zzgcVar.zzz = zze.zzz;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void F(zzgc zzgcVar, boolean z) {
        zzgcVar.zzf |= 131072;
        zzgcVar.zzA = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void G(zzgc zzgcVar) {
        zzgcVar.zzf &= -131073;
        zzgcVar.zzA = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void H(zzgc zzgcVar, String str) {
        str.getClass();
        zzgcVar.zzf |= 262144;
        zzgcVar.zzB = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void I(zzgc zzgcVar) {
        zzgcVar.zzf &= -262145;
        zzgcVar.zzB = zze.zzB;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void J(zzgc zzgcVar, long j2) {
        zzgcVar.zzf |= 524288;
        zzgcVar.zzC = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void K(zzgc zzgcVar, int i2) {
        zzgcVar.zzf |= 1048576;
        zzgcVar.zzD = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void L(zzgc zzgcVar, String str) {
        zzgcVar.zzf |= 2097152;
        zzgcVar.zzE = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void M(zzgc zzgcVar) {
        zzgcVar.zzf &= -2097153;
        zzgcVar.zzE = zze.zzE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void N(zzgc zzgcVar, String str) {
        str.getClass();
        zzgcVar.zzf |= 4194304;
        zzgcVar.zzF = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void O(zzgc zzgcVar, boolean z) {
        zzgcVar.zzf |= 8388608;
        zzgcVar.zzG = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void P(zzgc zzgcVar, Iterable iterable) {
        zzkj zzkjVar = zzgcVar.zzH;
        if (!zzkjVar.zzc()) {
            zzgcVar.zzH = zzkc.j(zzkjVar);
        }
        zzil.b(iterable, zzgcVar.zzH);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void R(zzgc zzgcVar, String str) {
        str.getClass();
        zzgcVar.zzf |= 16777216;
        zzgcVar.zzI = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void S(zzgc zzgcVar, int i2) {
        zzgcVar.zzf |= MediaHttpDownloader.MAXIMUM_CHUNK_SIZE;
        zzgcVar.zzJ = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void T(zzgc zzgcVar, int i2) {
        zzgcVar.zzf |= 1;
        zzgcVar.zzh = 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void U(zzgc zzgcVar) {
        zzgcVar.zzf &= -268435457;
        zzgcVar.zzM = zze.zzM;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void V(zzgc zzgcVar, long j2) {
        zzgcVar.zzf |= PKIFailureInfo.duplicateCertReq;
        zzgcVar.zzN = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void W(zzgc zzgcVar, String str) {
        zzgcVar.zzg |= 128;
        zzgcVar.zzY = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void X(zzgc zzgcVar, Iterable iterable) {
        zzgcVar.zzbO();
        zzil.b(iterable, zzgcVar.zzi);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void Y(zzgc zzgcVar, String str) {
        str.getClass();
        zzgcVar.zzg |= 8192;
        zzgcVar.zzae = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void Z(zzgc zzgcVar) {
        zzgcVar.zzg &= -8193;
        zzgcVar.zzae = zze.zzae;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a0(zzgc zzgcVar, Iterable iterable) {
        zzkj zzkjVar = zzgcVar.zzaf;
        if (!zzkjVar.zzc()) {
            zzgcVar.zzaf = zzkc.j(zzkjVar);
        }
        zzil.b(iterable, zzgcVar.zzaf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void c0(zzgc zzgcVar, String str) {
        str.getClass();
        zzgcVar.zzg |= 16384;
        zzgcVar.zzag = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void d0(zzgc zzgcVar, int i2) {
        zzgcVar.zzbO();
        zzgcVar.zzi.remove(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void e0(zzgc zzgcVar, int i2, zzgl zzglVar) {
        zzglVar.getClass();
        zzgcVar.zzbP();
        zzgcVar.zzj.set(i2, zzglVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void f0(zzgc zzgcVar, zzgl zzglVar) {
        zzglVar.getClass();
        zzgcVar.zzbP();
        zzgcVar.zzj.add(zzglVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void g0(zzgc zzgcVar, Iterable iterable) {
        zzgcVar.zzbP();
        zzil.b(iterable, zzgcVar.zzj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void h0(zzgc zzgcVar, int i2) {
        zzgcVar.zzbP();
        zzgcVar.zzj.remove(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void i0(zzgc zzgcVar, long j2) {
        zzgcVar.zzf |= 2;
        zzgcVar.zzk = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void j0(zzgc zzgcVar, long j2) {
        zzgcVar.zzf |= 4;
        zzgcVar.zzl = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void k0(zzgc zzgcVar, long j2) {
        zzgcVar.zzf |= 8;
        zzgcVar.zzm = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void l0(zzgc zzgcVar, long j2) {
        zzgcVar.zzf |= 16;
        zzgcVar.zzn = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void m0(zzgc zzgcVar) {
        zzgcVar.zzf &= -17;
        zzgcVar.zzn = 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void n0(zzgc zzgcVar, long j2) {
        zzgcVar.zzf |= 32;
        zzgcVar.zzo = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void o(zzgc zzgcVar, long j2) {
        zzgcVar.zzf |= 1073741824;
        zzgcVar.zzO = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void o0(zzgc zzgcVar) {
        zzgcVar.zzf &= -33;
        zzgcVar.zzo = 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void p(zzgc zzgcVar) {
        zzgcVar.zzf &= Integer.MAX_VALUE;
        zzgcVar.zzP = zze.zzP;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void p0(zzgc zzgcVar, String str) {
        zzgcVar.zzf |= 64;
        zzgcVar.zzp = "android";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void q(zzgc zzgcVar, int i2) {
        zzgcVar.zzg |= 2;
        zzgcVar.zzR = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void q0(zzgc zzgcVar, String str) {
        str.getClass();
        zzgcVar.zzf |= 128;
        zzgcVar.zzq = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void r(zzgc zzgcVar, int i2, zzfs zzfsVar) {
        zzfsVar.getClass();
        zzgcVar.zzbO();
        zzgcVar.zzi.set(i2, zzfsVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void r0(zzgc zzgcVar) {
        zzgcVar.zzf &= -129;
        zzgcVar.zzq = zze.zzq;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void s(zzgc zzgcVar, String str) {
        str.getClass();
        zzgcVar.zzg |= 4;
        zzgcVar.zzS = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void s0(zzgc zzgcVar, String str) {
        str.getClass();
        zzgcVar.zzf |= 256;
        zzgcVar.zzr = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void t(zzgc zzgcVar, zzgf zzgfVar) {
        zzgfVar.getClass();
        zzgcVar.zzT = zzgfVar;
        zzgcVar.zzg |= 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void t0(zzgc zzgcVar) {
        zzgcVar.zzf &= -257;
        zzgcVar.zzr = zze.zzr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void u(zzgc zzgcVar, Iterable iterable) {
        zzkh zzkhVar = zzgcVar.zzU;
        if (!zzkhVar.zzc()) {
            int size = zzkhVar.size();
            zzgcVar.zzU = zzkhVar.zzg(size == 0 ? 10 : size + size);
        }
        zzil.b(iterable, zzgcVar.zzU);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void u0(zzgc zzgcVar, String str) {
        str.getClass();
        zzgcVar.zzf |= 512;
        zzgcVar.zzs = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void v(zzgc zzgcVar, zzfs zzfsVar) {
        zzfsVar.getClass();
        zzgcVar.zzbO();
        zzgcVar.zzi.add(zzfsVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void v0(zzgc zzgcVar, int i2) {
        zzgcVar.zzf |= 1024;
        zzgcVar.zzt = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void w(zzgc zzgcVar, long j2) {
        zzgcVar.zzg |= 16;
        zzgcVar.zzV = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void x(zzgc zzgcVar, long j2) {
        zzgcVar.zzg |= 32;
        zzgcVar.zzW = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void y(zzgc zzgcVar, String str) {
        str.getClass();
        zzgcVar.zzf |= 2048;
        zzgcVar.zzu = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void z(zzgc zzgcVar, String str) {
        str.getClass();
        zzgcVar.zzf |= 4096;
        zzgcVar.zzv = str;
    }

    private final void zzbO() {
        zzkj zzkjVar = this.zzi;
        if (zzkjVar.zzc()) {
            return;
        }
        this.zzi = zzkc.j(zzkjVar);
    }

    private final void zzbP() {
        zzkj zzkjVar = this.zzj;
        if (zzkjVar.zzc()) {
            return;
        }
        this.zzj = zzkc.j(zzkjVar);
    }

    public static zzgb zzu() {
        return (zzgb) zze.d();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzkc
    public final Object n(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            return null;
                        }
                        return zze;
                    }
                    return new zzgb(null);
                }
                return new zzgc();
            }
            return zzkc.l(zze, "\u00014\u0000\u0002\u0001A4\u0000\u0005\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဂ\u0001\u0005ဂ\u0002\u0006ဂ\u0003\u0007ဂ\u0005\bဈ\u0006\tဈ\u0007\nဈ\b\u000bဈ\t\fင\n\rဈ\u000b\u000eဈ\f\u0010ဈ\r\u0011ဂ\u000e\u0012ဂ\u000f\u0013ဈ\u0010\u0014ဇ\u0011\u0015ဈ\u0012\u0016ဂ\u0013\u0017င\u0014\u0018ဈ\u0015\u0019ဈ\u0016\u001aဂ\u0004\u001cဇ\u0017\u001d\u001b\u001eဈ\u0018\u001fင\u0019 င\u001a!င\u001b\"ဈ\u001c#ဂ\u001d$ဂ\u001e%ဈ\u001f&ဈ 'င!)ဈ\",ဉ#-\u001d.ဂ$/ဂ%2ဈ&4ဈ'5ဌ(7ဇ)9ဈ*:ဇ+;ဉ,?ဈ-@\u001aAဈ.", new Object[]{"zzf", "zzg", "zzh", "zzi", zzfs.class, "zzj", zzgl.class, "zzk", "zzl", "zzm", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzA", "zzB", "zzC", "zzD", "zzE", "zzF", "zzn", "zzG", "zzH", zzfo.class, "zzI", "zzJ", "zzK", "zzL", "zzM", "zzN", "zzO", "zzP", "zzQ", "zzR", "zzS", "zzT", "zzU", "zzV", "zzW", "zzX", "zzY", "zzZ", zzfk.f6058a, "zzaa", "zzab", "zzac", "zzad", "zzae", "zzaf", "zzag"});
        }
        return (byte) 1;
    }

    public final String zzA() {
        return this.zzu;
    }

    public final String zzB() {
        return this.zzw;
    }

    public final String zzC() {
        return this.zzY;
    }

    public final String zzD() {
        return this.zzr;
    }

    public final String zzE() {
        return this.zzP;
    }

    public final String zzF() {
        return this.zzI;
    }

    public final String zzG() {
        return this.zzF;
    }

    public final String zzH() {
        return this.zzE;
    }

    public final String zzI() {
        return this.zzq;
    }

    public final String zzJ() {
        return this.zzp;
    }

    public final String zzK() {
        return this.zzz;
    }

    public final String zzL() {
        return this.zzae;
    }

    public final String zzM() {
        return this.zzs;
    }

    public final List zzN() {
        return this.zzH;
    }

    public final List zzO() {
        return this.zzi;
    }

    public final List zzP() {
        return this.zzj;
    }

    public final int zza() {
        return this.zzJ;
    }

    public final boolean zzaY() {
        return this.zzA;
    }

    public final boolean zzaZ() {
        return this.zzG;
    }

    public final int zzb() {
        return this.zzD;
    }

    public final boolean zzba() {
        return (this.zzf & 1073741824) != 0;
    }

    public final boolean zzbb() {
        return (this.zzf & MediaHttpDownloader.MAXIMUM_CHUNK_SIZE) != 0;
    }

    public final boolean zzbc() {
        return (this.zzf & 1048576) != 0;
    }

    public final boolean zzbd() {
        return (this.zzf & PKIFailureInfo.duplicateCertReq) != 0;
    }

    public final boolean zzbe() {
        return (this.zzg & 128) != 0;
    }

    public final boolean zzbf() {
        return (this.zzf & 524288) != 0;
    }

    public final boolean zzbg() {
        return (this.zzg & 16) != 0;
    }

    public final boolean zzbh() {
        return (this.zzf & 8) != 0;
    }

    public final boolean zzbi() {
        return (this.zzf & 16384) != 0;
    }

    public final boolean zzbj() {
        return (this.zzf & 131072) != 0;
    }

    public final boolean zzbk() {
        return (this.zzf & 32) != 0;
    }

    public final boolean zzbl() {
        return (this.zzf & 16) != 0;
    }

    public final boolean zzbm() {
        return (this.zzf & 1) != 0;
    }

    public final boolean zzbn() {
        return (this.zzg & 2) != 0;
    }

    public final boolean zzbo() {
        return (this.zzf & 8388608) != 0;
    }

    public final boolean zzbp() {
        return (this.zzg & 8192) != 0;
    }

    public final boolean zzbq() {
        return (this.zzf & 4) != 0;
    }

    public final boolean zzbr() {
        return (this.zzf & 1024) != 0;
    }

    public final boolean zzbs() {
        return (this.zzf & 2) != 0;
    }

    public final boolean zzbt() {
        return (this.zzf & 32768) != 0;
    }

    public final int zzc() {
        return this.zzi.size();
    }

    public final int zzd() {
        return this.zzh;
    }

    public final int zze() {
        return this.zzR;
    }

    public final int zzf() {
        return this.zzt;
    }

    public final int zzg() {
        return this.zzj.size();
    }

    public final long zzh() {
        return this.zzO;
    }

    public final long zzi() {
        return this.zzN;
    }

    public final long zzj() {
        return this.zzC;
    }

    public final long zzk() {
        return this.zzV;
    }

    public final long zzm() {
        return this.zzm;
    }

    public final long zzn() {
        return this.zzx;
    }

    public final long zzo() {
        return this.zzo;
    }

    public final long zzp() {
        return this.zzn;
    }

    public final long zzq() {
        return this.zzl;
    }

    public final long zzr() {
        return this.zzk;
    }

    public final long zzs() {
        return this.zzy;
    }

    public final zzfs zzt(int i2) {
        return (zzfs) this.zzi.get(i2);
    }

    public final zzgl zzw(int i2) {
        return (zzgl) this.zzj.get(i2);
    }

    public final String zzx() {
        return this.zzS;
    }

    public final String zzy() {
        return this.zzv;
    }

    public final String zzz() {
        return this.zzB;
    }
}
