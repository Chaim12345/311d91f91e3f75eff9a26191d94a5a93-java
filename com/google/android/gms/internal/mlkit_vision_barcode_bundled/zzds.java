package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzds {
    private static final zzds zzb = new zzds(true);

    /* renamed from: a  reason: collision with root package name */
    final zzgl f6415a = new zzge(16);
    private boolean zzc;
    private boolean zzd;

    private zzds() {
    }

    private zzds(boolean z) {
        zzg();
        zzg();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int zza(zzdr zzdrVar, Object obj) {
        long longValue;
        int intValue;
        int intValue2;
        zzhf zzd = zzdrVar.zzd();
        int zza = zzdrVar.zza();
        zzdrVar.zzg();
        int zzC = zzdi.zzC(zza);
        if (zzd == zzhf.zzj) {
            zzel.e((zzfl) obj);
            zzC += zzC;
        }
        zzhg zzhgVar = zzhg.INT;
        int i2 = 4;
        switch (zzd.ordinal()) {
            case 0:
                ((Double) obj).doubleValue();
                i2 = 8;
                break;
            case 1:
                ((Float) obj).floatValue();
                break;
            case 2:
            case 3:
                longValue = ((Long) obj).longValue();
                i2 = zzdi.zzE(longValue);
                break;
            case 4:
                intValue = ((Integer) obj).intValue();
                i2 = zzdi.zzx(intValue);
                break;
            case 5:
            case 15:
                ((Long) obj).longValue();
                i2 = 8;
                break;
            case 6:
            case 14:
                ((Integer) obj).intValue();
                break;
            case 7:
                ((Boolean) obj).booleanValue();
                i2 = 1;
                break;
            case 8:
                if (!(obj instanceof zzdb)) {
                    i2 = zzdi.zzB((String) obj);
                    break;
                }
                i2 = zzdi.zzu((zzdb) obj);
                break;
            case 9:
                i2 = zzdi.zzw((zzfl) obj);
                break;
            case 10:
                if (!(obj instanceof zzes)) {
                    i2 = zzdi.zzz((zzfl) obj);
                    break;
                } else {
                    i2 = zzdi.zzy((zzes) obj);
                    break;
                }
            case 11:
                if (!(obj instanceof zzdb)) {
                    i2 = zzdi.zzt((byte[]) obj);
                    break;
                }
                i2 = zzdi.zzu((zzdb) obj);
                break;
            case 12:
                intValue2 = ((Integer) obj).intValue();
                i2 = zzdi.zzD(intValue2);
                break;
            case 13:
                if (obj instanceof zzee) {
                    intValue = ((zzee) obj).zza();
                    i2 = zzdi.zzx(intValue);
                    break;
                }
                intValue = ((Integer) obj).intValue();
                i2 = zzdi.zzx(intValue);
            case 16:
                int intValue3 = ((Integer) obj).intValue();
                intValue2 = (intValue3 >> 31) ^ (intValue3 + intValue3);
                i2 = zzdi.zzD(intValue2);
                break;
            case 17:
                long longValue2 = ((Long) obj).longValue();
                longValue = (longValue2 >> 63) ^ (longValue2 + longValue2);
                i2 = zzdi.zzE(longValue);
                break;
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
        return zzC + i2;
    }

    public static zzds zzd() {
        return zzb;
    }

    private static Object zzl(Object obj) {
        if (obj instanceof zzfq) {
            return ((zzfq) obj).zzc();
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, length);
            return bArr2;
        }
        return obj;
    }

    private final void zzm(Map.Entry entry) {
        AbstractMap abstractMap;
        Object zzl;
        Object zze;
        zzdr zzdrVar = (zzdr) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzes) {
            zzes zzesVar = (zzes) value;
            throw null;
        }
        zzdrVar.zzg();
        if (zzdrVar.zze() != zzhg.MESSAGE || (zze = zze(zzdrVar)) == null) {
            abstractMap = this.f6415a;
            zzl = zzl(value);
        } else {
            if (zze instanceof zzfq) {
                zzl = zzdrVar.zzc((zzfq) zze, (zzfq) value);
            } else {
                zzfk zzV = ((zzfl) zze).zzV();
                zzdrVar.zzb(zzV, (zzfl) value);
                zzl = zzV.zzl();
            }
            abstractMap = this.f6415a;
        }
        abstractMap.put(zzdrVar, zzl);
    }

    private static boolean zzn(Map.Entry entry) {
        zzdr zzdrVar = (zzdr) entry.getKey();
        if (zzdrVar.zze() == zzhg.MESSAGE) {
            zzdrVar.zzg();
            Object value = entry.getValue();
            if (value instanceof zzfm) {
                return ((zzfm) value).zzY();
            }
            if (value instanceof zzes) {
                return true;
            }
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        return true;
    }

    private static final int zzo(Map.Entry entry) {
        int zzD;
        int zzD2;
        zzdr zzdrVar = (zzdr) entry.getKey();
        Object value = entry.getValue();
        if (zzdrVar.zze() == zzhg.MESSAGE) {
            zzdrVar.zzg();
            zzdrVar.zzf();
            boolean z = value instanceof zzes;
            int zza = ((zzdr) entry.getKey()).zza();
            if (z) {
                int zzD3 = zzdi.zzD(8);
                int zza2 = ((zzes) value).zza();
                zzD = zzD3 + zzD3 + zzdi.zzD(16) + zzdi.zzD(zza);
                zzD2 = zzdi.zzD(24) + zzdi.zzD(zza2) + zza2;
            } else {
                int zzD4 = zzdi.zzD(8);
                zzD = zzD4 + zzD4 + zzdi.zzD(16) + zzdi.zzD(zza);
                zzD2 = zzdi.zzD(24) + zzdi.zzz((zzfl) value);
            }
            return zzD + zzD2;
        }
        return zza(zzdrVar, value);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzds) {
            return this.f6415a.equals(((zzds) obj).f6415a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f6415a.hashCode();
    }

    public final int zzb() {
        int i2 = 0;
        for (int i3 = 0; i3 < this.f6415a.zzb(); i3++) {
            i2 += zzo(this.f6415a.zzg(i3));
        }
        for (Map.Entry entry : this.f6415a.zzc()) {
            i2 += zzo(entry);
        }
        return i2;
    }

    /* renamed from: zzc */
    public final zzds clone() {
        zzds zzdsVar = new zzds();
        for (int i2 = 0; i2 < this.f6415a.zzb(); i2++) {
            Map.Entry zzg = this.f6415a.zzg(i2);
            zzdsVar.zzi((zzdr) zzg.getKey(), zzg.getValue());
        }
        for (Map.Entry entry : this.f6415a.zzc()) {
            zzdsVar.zzi((zzdr) entry.getKey(), entry.getValue());
        }
        zzdsVar.zzd = this.zzd;
        return zzdsVar;
    }

    public final Object zze(zzdr zzdrVar) {
        Object obj = this.f6415a.get(zzdrVar);
        if (obj instanceof zzes) {
            zzes zzesVar = (zzes) obj;
            throw null;
        }
        return obj;
    }

    public final Iterator zzf() {
        return this.zzd ? new zzer(this.f6415a.entrySet().iterator()) : this.f6415a.entrySet().iterator();
    }

    public final void zzg() {
        if (this.zzc) {
            return;
        }
        this.f6415a.zza();
        this.zzc = true;
    }

    public final void zzh(zzds zzdsVar) {
        for (int i2 = 0; i2 < zzdsVar.f6415a.zzb(); i2++) {
            zzm(zzdsVar.f6415a.zzg(i2));
        }
        for (Map.Entry entry : zzdsVar.f6415a.zzc()) {
            zzm(entry);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002a, code lost:
        if ((r7 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzee) == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0033, code lost:
        if ((r7 instanceof byte[]) == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0047, code lost:
        if (r0 == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0021, code lost:
        if ((r7 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzes) == false) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzi(zzdr zzdrVar, Object obj) {
        boolean z;
        zzdrVar.zzg();
        zzhf zzd = zzdrVar.zzd();
        zzel.b(obj);
        zzhf zzhfVar = zzhf.zza;
        zzhg zzhgVar = zzhg.INT;
        switch (zzd.zza().ordinal()) {
            case 0:
                z = obj instanceof Integer;
                break;
            case 1:
                z = obj instanceof Long;
                break;
            case 2:
                z = obj instanceof Float;
                break;
            case 3:
                z = obj instanceof Double;
                break;
            case 4:
                z = obj instanceof Boolean;
                break;
            case 5:
                z = obj instanceof String;
                break;
            case 6:
                if (!(obj instanceof zzdb)) {
                    break;
                }
                if (obj instanceof zzes) {
                    this.zzd = true;
                }
                this.f6415a.put(zzdrVar, obj);
                return;
            case 7:
                if (!(obj instanceof Integer)) {
                    break;
                }
                if (obj instanceof zzes) {
                }
                this.f6415a.put(zzdrVar, obj);
                return;
            case 8:
                if (!(obj instanceof zzfl)) {
                    break;
                }
                if (obj instanceof zzes) {
                }
                this.f6415a.put(zzdrVar, obj);
                return;
            default:
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(zzdrVar.zza()), zzdrVar.zzd().zza(), obj.getClass().getName()));
        }
    }

    public final boolean zzj() {
        return this.zzc;
    }

    public final boolean zzk() {
        for (int i2 = 0; i2 < this.f6415a.zzb(); i2++) {
            if (!zzn(this.f6415a.zzg(i2))) {
                return false;
            }
        }
        for (Map.Entry entry : this.f6415a.zzc()) {
            if (!zzn(entry)) {
                return false;
            }
        }
        return true;
    }
}
