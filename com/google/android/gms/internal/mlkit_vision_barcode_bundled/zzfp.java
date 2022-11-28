package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzfp implements zzgb {
    private final zzfl zza;
    private final zzgp zzb;
    private final boolean zzc;
    private final zzdo zzd;

    private zzfp(zzgp zzgpVar, zzdo zzdoVar, zzfl zzflVar) {
        this.zzb = zzgpVar;
        this.zzc = zzdoVar.f(zzflVar);
        this.zzd = zzdoVar;
        this.zza = zzflVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzfp a(zzgp zzgpVar, zzdo zzdoVar, zzfl zzflVar) {
        return new zzfp(zzgpVar, zzdoVar, zzflVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final int zza(Object obj) {
        zzgp zzgpVar = this.zzb;
        int b2 = zzgpVar.b(zzgpVar.c(obj));
        return this.zzc ? b2 + this.zzd.b(obj).zzb() : b2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final int zzb(Object obj) {
        int hashCode = this.zzb.c(obj).hashCode();
        return this.zzc ? (hashCode * 53) + this.zzd.b(obj).f6415a.hashCode() : hashCode;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final Object zze() {
        return this.zza.zzU().zzm();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final void zzf(Object obj) {
        this.zzb.g(obj);
        this.zzd.e(obj);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final void zzg(Object obj, Object obj2) {
        zzgd.d(this.zzb, obj, obj2);
        if (this.zzc) {
            zzgd.c(this.zzd, obj, obj2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00bf A[EDGE_INSN: B:56:0x00bf->B:33:0x00bf ?: BREAK  , SYNTHETIC] */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzh(Object obj, byte[] bArr, int i2, int i3, zzco zzcoVar) {
        zzec zzecVar = (zzec) obj;
        zzgq zzgqVar = zzecVar.zzc;
        if (zzgqVar == zzgq.zzc()) {
            zzgqVar = zzgq.b();
            zzecVar.zzc = zzgqVar;
        }
        zzds q2 = ((zzdy) obj).q();
        Object obj2 = null;
        while (i2 < i3) {
            int j2 = zzcp.j(bArr, i2, zzcoVar);
            int i4 = zzcoVar.zza;
            if (i4 == 11) {
                int i5 = 0;
                zzdb zzdbVar = null;
                while (j2 < i3) {
                    j2 = zzcp.j(bArr, j2, zzcoVar);
                    int i6 = zzcoVar.zza;
                    int i7 = i6 & 7;
                    int i8 = i6 >>> 3;
                    if (i8 != 2) {
                        if (i8 == 3) {
                            if (obj2 != null) {
                                zzea zzeaVar = (zzea) obj2;
                                j2 = zzcp.d(zzfu.zza().zzb(zzeaVar.f6423c.getClass()), bArr, j2, i3, zzcoVar);
                                q2.zzi(zzeaVar.f6424d, zzcoVar.zzc);
                            } else if (i7 == 2) {
                                j2 = zzcp.a(bArr, j2, zzcoVar);
                                zzdbVar = (zzdb) zzcoVar.zzc;
                            }
                        }
                        if (i6 != 12) {
                            break;
                        }
                        j2 = zzcp.n(i6, bArr, j2, i3, zzcoVar);
                    } else if (i7 == 0) {
                        j2 = zzcp.j(bArr, j2, zzcoVar);
                        i5 = zzcoVar.zza;
                        obj2 = this.zzd.d(zzcoVar.zzd, this.zza, i5);
                    } else if (i6 != 12) {
                    }
                }
                if (zzdbVar != null) {
                    zzgqVar.d((i5 << 3) | 2, zzdbVar);
                }
                i2 = j2;
            } else if ((i4 & 7) == 2) {
                Object d2 = this.zzd.d(zzcoVar.zzd, this.zza, i4 >>> 3);
                if (d2 != null) {
                    zzea zzeaVar2 = (zzea) d2;
                    i2 = zzcp.d(zzfu.zza().zzb(zzeaVar2.f6423c.getClass()), bArr, j2, i3, zzcoVar);
                    q2.zzi(zzeaVar2.f6424d, zzcoVar.zzc);
                } else {
                    i2 = zzcp.i(i4, bArr, j2, i3, zzgqVar, zzcoVar);
                }
                obj2 = d2;
            } else {
                i2 = zzcp.n(i4, bArr, j2, i3, zzcoVar);
            }
        }
        if (i2 != i3) {
            throw zzen.e();
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final boolean zzi(Object obj, Object obj2) {
        if (this.zzb.c(obj).equals(this.zzb.c(obj2))) {
            if (this.zzc) {
                return this.zzd.b(obj).equals(this.zzd.b(obj2));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final boolean zzj(Object obj) {
        return this.zzd.b(obj).zzk();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final void zzm(Object obj, zzdj zzdjVar) {
        Iterator zzf = this.zzd.b(obj).zzf();
        while (zzf.hasNext()) {
            Map.Entry entry = (Map.Entry) zzf.next();
            zzdr zzdrVar = (zzdr) entry.getKey();
            if (zzdrVar.zze() != zzhg.MESSAGE) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            zzdrVar.zzg();
            zzdrVar.zzf();
            zzdjVar.zzw(zzdrVar.zza(), entry instanceof zzeq ? ((zzeq) entry).zza().zzb() : entry.getValue());
        }
        zzgp zzgpVar = this.zzb;
        zzgpVar.i(zzgpVar.c(obj), zzdjVar);
    }
}
