package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzci;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzei;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzek;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;
import java.util.List;
/* loaded from: classes2.dex */
public final class zzc extends zzec implements zzfm {
    private static final zzc zza;
    private byte zzA = 2;
    private int zze;
    private int zzf;
    private zzdb zzg;
    private String zzh;
    private int zzi;
    private zzr zzj;
    private zzy zzk;
    private zzci zzl;
    private zzag zzm;
    private zzao zzn;
    private zzaj zzo;
    private zzac zzp;
    private zzp zzq;
    private zzt zzr;
    private zzl zzs;
    private zzek zzt;
    private zzei zzu;
    private String zzv;
    private zzek zzw;
    private boolean zzx;
    private double zzy;
    private zzdb zzz;

    static {
        zzc zzcVar = new zzc();
        zza = zzcVar;
        zzec.n(zzc.class, zzcVar);
    }

    private zzc() {
        zzdb zzdbVar = zzdb.zzb;
        this.zzg = zzdbVar;
        this.zzh = "";
        this.zzt = zzec.j();
        this.zzu = zzec.i();
        this.zzv = "";
        this.zzw = zzec.j();
        this.zzx = true;
        this.zzz = zzdbVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void r(zzc zzcVar, int i2, zzae zzaeVar) {
        zzaeVar.getClass();
        zzek zzekVar = zzcVar.zzt;
        if (!zzekVar.zzc()) {
            zzcVar.zzt = zzec.k(zzekVar);
        }
        zzcVar.zzt.set(i2, zzaeVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    public final Object p(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            this.zzA = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zza;
                    }
                    return new zzb(null);
                }
                return new zzc();
            }
            return zzec.m(zza, "\u0001\u0015\u0000\u0001\u0001\u0015\u0015\u0000\u0003\u000b\u0001ᔌ\u0000\u0002ᔊ\u0001\u0003ᔈ\u0002\u0004ᔌ\u0003\u0005ᐉ\u0004\u0006ဉ\u0005\u0007ဉ\u0006\bᐉ\u0007\tᐉ\b\nᐉ\t\u000bЛ\fဈ\u000e\rЛ\u000eည\u0011\u000fᐉ\n\u0010ဉ\u000b\u0011ဉ\f\u0012\u0016\u0013ဉ\r\u0014ဇ\u000f\u0015က\u0010", new Object[]{"zze", "zzf", zze.f10350a, "zzg", "zzh", "zzi", zzh.f10351a, "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzt", zzae.class, "zzv", "zzw", zzae.class, "zzz", "zzp", "zzq", "zzr", "zzu", "zzs", "zzx", "zzy"});
        }
        return Byte.valueOf(this.zzA);
    }

    public final int zzA() {
        int zza2 = zzi.zza(this.zzi);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
    }

    public final int zza() {
        return this.zzt.size();
    }

    public final zzci zzb() {
        zzci zzciVar = this.zzl;
        return zzciVar == null ? zzci.zzb() : zzciVar;
    }

    public final zzp zzd() {
        zzp zzpVar = this.zzq;
        return zzpVar == null ? zzp.zzd() : zzpVar;
    }

    public final zzr zze() {
        zzr zzrVar = this.zzj;
        return zzrVar == null ? zzr.zzc() : zzrVar;
    }

    public final zzt zzf() {
        zzt zztVar = this.zzr;
        return zztVar == null ? zzt.zzb() : zztVar;
    }

    public final zzy zzh() {
        zzy zzyVar = this.zzk;
        return zzyVar == null ? zzy.zzb() : zzyVar;
    }

    public final zzac zzi() {
        zzac zzacVar = this.zzp;
        return zzacVar == null ? zzac.zzd() : zzacVar;
    }

    public final zzag zzj() {
        zzag zzagVar = this.zzm;
        return zzagVar == null ? zzag.zzb() : zzagVar;
    }

    public final zzaj zzk() {
        zzaj zzajVar = this.zzo;
        return zzajVar == null ? zzaj.zzb() : zzajVar;
    }

    public final zzao zzl() {
        zzao zzaoVar = this.zzn;
        return zzaoVar == null ? zzao.zzb() : zzaoVar;
    }

    public final zzdb zzm() {
        return this.zzg;
    }

    public final String zzn() {
        return this.zzh;
    }

    public final List zzo() {
        return this.zzt;
    }

    public final boolean zzq() {
        return (this.zze & 2048) != 0;
    }

    public final boolean zzr() {
        return (this.zze & 16) != 0;
    }

    public final boolean zzs() {
        return (this.zze & 4096) != 0;
    }

    public final boolean zzt() {
        return (this.zze & 32) != 0;
    }

    public final boolean zzu() {
        return (this.zze & 1024) != 0;
    }

    public final boolean zzv() {
        return (this.zze & 64) != 0;
    }

    public final boolean zzw() {
        return (this.zze & 128) != 0;
    }

    public final boolean zzx() {
        return (this.zze & 512) != 0;
    }

    public final boolean zzy() {
        return (this.zze & 256) != 0;
    }

    public final int zzz() {
        int zza2 = zzf.zza(this.zzf);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
    }
}
