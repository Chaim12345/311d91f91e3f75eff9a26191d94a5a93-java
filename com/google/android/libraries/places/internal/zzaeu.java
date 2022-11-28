package com.google.android.libraries.places.internal;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import sun.misc.Unsafe;
/* loaded from: classes2.dex */
final class zzaeu<T> implements zzafc<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzagd.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final zzaer zze;
    private final boolean zzf;
    private final boolean zzg;
    private final int[] zzh;
    private final int zzi;
    private final int zzj;
    private final zzaef zzk;
    private final zzaft zzl;
    private final zzada zzm;
    private final zzaew zzn;
    private final zzaem zzo;

    private zzaeu(int[] iArr, Object[] objArr, int i2, int i3, zzaer zzaerVar, boolean z, boolean z2, int[] iArr2, int i4, int i5, zzaew zzaewVar, zzaef zzaefVar, zzaft zzaftVar, zzada zzadaVar, zzaem zzaemVar, byte[] bArr) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzg = z;
        boolean z3 = false;
        if (zzadaVar != null && zzadaVar.zzc(zzaerVar)) {
            z3 = true;
        }
        this.zzf = z3;
        this.zzh = iArr2;
        this.zzi = i4;
        this.zzj = i5;
        this.zzn = zzaewVar;
        this.zzk = zzaefVar;
        this.zzl = zzaftVar;
        this.zzm = zzadaVar;
        this.zze = zzaerVar;
        this.zzo = zzaemVar;
    }

    private final boolean zzA(Object obj, int i2) {
        int zzo = zzo(i2);
        long j2 = zzo & 1048575;
        if (j2 != 1048575) {
            return (zzagd.zzc(obj, j2) & (1 << (zzo >>> 20))) != 0;
        }
        int zzq = zzq(i2);
        long j3 = zzq & 1048575;
        switch (zzp(zzq)) {
            case 0:
                return Double.doubleToRawLongBits(zzagd.zza(obj, j3)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzagd.zzb(obj, j3)) != 0;
            case 2:
                return zzagd.zzd(obj, j3) != 0;
            case 3:
                return zzagd.zzd(obj, j3) != 0;
            case 4:
                return zzagd.zzc(obj, j3) != 0;
            case 5:
                return zzagd.zzd(obj, j3) != 0;
            case 6:
                return zzagd.zzc(obj, j3) != 0;
            case 7:
                return zzagd.zzw(obj, j3);
            case 8:
                Object zzf = zzagd.zzf(obj, j3);
                if (zzf instanceof String) {
                    return !((String) zzf).isEmpty();
                } else if (zzf instanceof zzacp) {
                    return !zzacp.zzb.equals(zzf);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return zzagd.zzf(obj, j3) != null;
            case 10:
                return !zzacp.zzb.equals(zzagd.zzf(obj, j3));
            case 11:
                return zzagd.zzc(obj, j3) != 0;
            case 12:
                return zzagd.zzc(obj, j3) != 0;
            case 13:
                return zzagd.zzc(obj, j3) != 0;
            case 14:
                return zzagd.zzd(obj, j3) != 0;
            case 15:
                return zzagd.zzc(obj, j3) != 0;
            case 16:
                return zzagd.zzd(obj, j3) != 0;
            case 17:
                return zzagd.zzf(obj, j3) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzB(Object obj, int i2, int i3, int i4, int i5) {
        return i3 == 1048575 ? zzA(obj, i2) : (i4 & i5) != 0;
    }

    private static boolean zzC(Object obj, int i2, zzafc zzafcVar) {
        return zzafcVar.zzf(zzagd.zzf(obj, i2 & 1048575));
    }

    private final boolean zzD(Object obj, int i2, int i3) {
        return zzagd.zzc(obj, (long) (zzo(i3) & 1048575)) == i2;
    }

    private static boolean zzE(Object obj, long j2) {
        return ((Boolean) zzagd.zzf(obj, j2)).booleanValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final void zzF(Object obj, zzacy zzacyVar) {
        int i2;
        boolean z;
        if (this.zzf) {
            this.zzm.zza(obj);
            throw null;
        }
        int length = this.zzc.length;
        Unsafe unsafe = zzb;
        int i3 = 1048575;
        int i4 = 1048575;
        int i5 = 0;
        int i6 = 0;
        while (i5 < length) {
            int zzq = zzq(i5);
            int[] iArr = this.zzc;
            int i7 = iArr[i5];
            int zzp = zzp(zzq);
            if (zzp <= 17) {
                int i8 = iArr[i5 + 2];
                int i9 = i8 & i3;
                if (i9 != i4) {
                    i6 = unsafe.getInt(obj, i9);
                    i4 = i9;
                }
                i2 = 1 << (i8 >>> 20);
            } else {
                i2 = 0;
            }
            long j2 = zzq & i3;
            switch (zzp) {
                case 0:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzacyVar.zzf(i7, zzagd.zza(obj, j2));
                        break;
                    }
                case 1:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzacyVar.zzn(i7, zzagd.zzb(obj, j2));
                        break;
                    }
                case 2:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzacyVar.zzs(i7, unsafe.getLong(obj, j2));
                        break;
                    }
                case 3:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzacyVar.zzH(i7, unsafe.getLong(obj, j2));
                        break;
                    }
                case 4:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzacyVar.zzq(i7, unsafe.getInt(obj, j2));
                        break;
                    }
                case 5:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzacyVar.zzl(i7, unsafe.getLong(obj, j2));
                        break;
                    }
                case 6:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzacyVar.zzj(i7, unsafe.getInt(obj, j2));
                        break;
                    }
                case 7:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzacyVar.zzb(i7, zzagd.zzw(obj, j2));
                        break;
                    }
                case 8:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzH(i7, unsafe.getObject(obj, j2), zzacyVar);
                        break;
                    }
                case 9:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzacyVar.zzu(i7, unsafe.getObject(obj, j2), zzs(i5));
                        break;
                    }
                case 10:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzacyVar.zzd(i7, (zzacp) unsafe.getObject(obj, j2));
                        break;
                    }
                case 11:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzacyVar.zzF(i7, unsafe.getInt(obj, j2));
                        break;
                    }
                case 12:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzacyVar.zzh(i7, unsafe.getInt(obj, j2));
                        break;
                    }
                case 13:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzacyVar.zzv(i7, unsafe.getInt(obj, j2));
                        break;
                    }
                case 14:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzacyVar.zzx(i7, unsafe.getLong(obj, j2));
                        break;
                    }
                case 15:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzacyVar.zzz(i7, unsafe.getInt(obj, j2));
                        break;
                    }
                case 16:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzacyVar.zzB(i7, unsafe.getLong(obj, j2));
                        break;
                    }
                case 17:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzacyVar.zzp(i7, unsafe.getObject(obj, j2), zzs(i5));
                        break;
                    }
                case 18:
                    zzafe.zzJ(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, false);
                    break;
                case 19:
                    zzafe.zzN(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, false);
                    break;
                case 20:
                    zzafe.zzQ(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, false);
                    break;
                case 21:
                    zzafe.zzY(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, false);
                    break;
                case 22:
                    zzafe.zzP(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, false);
                    break;
                case 23:
                    zzafe.zzM(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, false);
                    break;
                case 24:
                    zzafe.zzL(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, false);
                    break;
                case 25:
                    zzafe.zzH(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, false);
                    break;
                case 26:
                    zzafe.zzW(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar);
                    break;
                case 27:
                    zzafe.zzR(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, zzs(i5));
                    break;
                case 28:
                    zzafe.zzI(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar);
                    break;
                case 29:
                    z = false;
                    zzafe.zzX(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, false);
                    break;
                case 30:
                    z = false;
                    zzafe.zzK(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, false);
                    break;
                case 31:
                    z = false;
                    zzafe.zzS(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, false);
                    break;
                case 32:
                    z = false;
                    zzafe.zzT(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, false);
                    break;
                case 33:
                    z = false;
                    zzafe.zzU(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, false);
                    break;
                case 34:
                    z = false;
                    zzafe.zzV(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, false);
                    break;
                case 35:
                    zzafe.zzJ(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, true);
                    break;
                case 36:
                    zzafe.zzN(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, true);
                    break;
                case 37:
                    zzafe.zzQ(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, true);
                    break;
                case 38:
                    zzafe.zzY(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, true);
                    break;
                case 39:
                    zzafe.zzP(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, true);
                    break;
                case 40:
                    zzafe.zzM(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, true);
                    break;
                case 41:
                    zzafe.zzL(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, true);
                    break;
                case 42:
                    zzafe.zzH(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, true);
                    break;
                case 43:
                    zzafe.zzX(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, true);
                    break;
                case 44:
                    zzafe.zzK(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, true);
                    break;
                case 45:
                    zzafe.zzS(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, true);
                    break;
                case 46:
                    zzafe.zzT(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, true);
                    break;
                case 47:
                    zzafe.zzU(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, true);
                    break;
                case 48:
                    zzafe.zzV(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, true);
                    break;
                case 49:
                    zzafe.zzO(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzacyVar, zzs(i5));
                    break;
                case 50:
                    zzG(zzacyVar, i7, unsafe.getObject(obj, j2), i5);
                    break;
                case 51:
                    if (zzD(obj, i7, i5)) {
                        zzacyVar.zzf(i7, zzj(obj, j2));
                    }
                    break;
                case 52:
                    if (zzD(obj, i7, i5)) {
                        zzacyVar.zzn(i7, zzk(obj, j2));
                    }
                    break;
                case 53:
                    if (zzD(obj, i7, i5)) {
                        zzacyVar.zzs(i7, zzr(obj, j2));
                    }
                    break;
                case 54:
                    if (zzD(obj, i7, i5)) {
                        zzacyVar.zzH(i7, zzr(obj, j2));
                    }
                    break;
                case 55:
                    if (zzD(obj, i7, i5)) {
                        zzacyVar.zzq(i7, zzn(obj, j2));
                    }
                    break;
                case 56:
                    if (zzD(obj, i7, i5)) {
                        zzacyVar.zzl(i7, zzr(obj, j2));
                    }
                    break;
                case 57:
                    if (zzD(obj, i7, i5)) {
                        zzacyVar.zzj(i7, zzn(obj, j2));
                    }
                    break;
                case 58:
                    if (zzD(obj, i7, i5)) {
                        zzacyVar.zzb(i7, zzE(obj, j2));
                    }
                    break;
                case 59:
                    if (zzD(obj, i7, i5)) {
                        zzH(i7, unsafe.getObject(obj, j2), zzacyVar);
                    }
                    break;
                case 60:
                    if (zzD(obj, i7, i5)) {
                        zzacyVar.zzu(i7, unsafe.getObject(obj, j2), zzs(i5));
                    }
                    break;
                case 61:
                    if (zzD(obj, i7, i5)) {
                        zzacyVar.zzd(i7, (zzacp) unsafe.getObject(obj, j2));
                    }
                    break;
                case 62:
                    if (zzD(obj, i7, i5)) {
                        zzacyVar.zzF(i7, zzn(obj, j2));
                    }
                    break;
                case 63:
                    if (zzD(obj, i7, i5)) {
                        zzacyVar.zzh(i7, zzn(obj, j2));
                    }
                    break;
                case 64:
                    if (zzD(obj, i7, i5)) {
                        zzacyVar.zzv(i7, zzn(obj, j2));
                    }
                    break;
                case 65:
                    if (zzD(obj, i7, i5)) {
                        zzacyVar.zzx(i7, zzr(obj, j2));
                    }
                    break;
                case 66:
                    if (zzD(obj, i7, i5)) {
                        zzacyVar.zzz(i7, zzn(obj, j2));
                    }
                    break;
                case 67:
                    if (zzD(obj, i7, i5)) {
                        zzacyVar.zzB(i7, zzr(obj, j2));
                    }
                    break;
                case 68:
                    if (zzD(obj, i7, i5)) {
                        zzacyVar.zzp(i7, unsafe.getObject(obj, j2), zzs(i5));
                    }
                    break;
            }
            i5 += 3;
            i3 = 1048575;
        }
        zzaft zzaftVar = this.zzl;
        zzaftVar.zzg(zzaftVar.zzc(obj), zzacyVar);
    }

    private final void zzG(zzacy zzacyVar, int i2, Object obj, int i3) {
        if (obj == null) {
            return;
        }
        zzaek zzaekVar = (zzaek) zzt(i3);
        throw null;
    }

    private static final void zzH(int i2, Object obj, zzacy zzacyVar) {
        if (obj instanceof String) {
            zzacyVar.zzD(i2, (String) obj);
        } else {
            zzacyVar.zzd(i2, (zzacp) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzaeu zzg(Class cls, zzaeo zzaeoVar, zzaew zzaewVar, zzaef zzaefVar, zzaft zzaftVar, zzada zzadaVar, zzaem zzaemVar) {
        if (zzaeoVar instanceof zzafb) {
            return zzh((zzafb) zzaeoVar, zzaewVar, zzaefVar, zzaftVar, zzadaVar, zzaemVar);
        }
        zzafq zzafqVar = (zzafq) zzaeoVar;
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0385  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static zzaeu zzh(zzafb zzafbVar, zzaew zzaewVar, zzaef zzaefVar, zzaft zzaftVar, zzada zzadaVar, zzaem zzaemVar) {
        int i2;
        int charAt;
        int charAt2;
        int charAt3;
        int[] iArr;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        char charAt4;
        int i8;
        char charAt5;
        int i9;
        char charAt6;
        int i10;
        char charAt7;
        int i11;
        char charAt8;
        int i12;
        char charAt9;
        int i13;
        char charAt10;
        int i14;
        char charAt11;
        int i15;
        int i16;
        int i17;
        int[] iArr2;
        int i18;
        int i19;
        int i20;
        int objectFieldOffset;
        Object[] objArr;
        String str;
        Class<?> cls;
        int i21;
        int i22;
        int i23;
        Field zzu;
        char charAt12;
        int i24;
        int i25;
        int i26;
        Object obj;
        Field zzu2;
        Object obj2;
        Field zzu3;
        int i27;
        char charAt13;
        int i28;
        char charAt14;
        int i29;
        char charAt15;
        int i30;
        char charAt16;
        boolean z = zzafbVar.zzc() == 2;
        String zzd = zzafbVar.zzd();
        int length = zzd.length();
        char c2 = 55296;
        if (zzd.charAt(0) >= 55296) {
            int i31 = 1;
            while (true) {
                i2 = i31 + 1;
                if (zzd.charAt(i31) < 55296) {
                    break;
                }
                i31 = i2;
            }
        } else {
            i2 = 1;
        }
        int i32 = i2 + 1;
        int charAt17 = zzd.charAt(i2);
        if (charAt17 >= 55296) {
            int i33 = charAt17 & 8191;
            int i34 = 13;
            while (true) {
                i30 = i32 + 1;
                charAt16 = zzd.charAt(i32);
                if (charAt16 < 55296) {
                    break;
                }
                i33 |= (charAt16 & 8191) << i34;
                i34 += 13;
                i32 = i30;
            }
            charAt17 = i33 | (charAt16 << i34);
            i32 = i30;
        }
        if (charAt17 == 0) {
            charAt = 0;
            i6 = 0;
            charAt2 = 0;
            i5 = 0;
            charAt3 = 0;
            i3 = 0;
            iArr = zza;
            i4 = 0;
        } else {
            int i35 = i32 + 1;
            int charAt18 = zzd.charAt(i32);
            if (charAt18 >= 55296) {
                int i36 = charAt18 & 8191;
                int i37 = 13;
                while (true) {
                    i14 = i35 + 1;
                    charAt11 = zzd.charAt(i35);
                    if (charAt11 < 55296) {
                        break;
                    }
                    i36 |= (charAt11 & 8191) << i37;
                    i37 += 13;
                    i35 = i14;
                }
                charAt18 = i36 | (charAt11 << i37);
                i35 = i14;
            }
            int i38 = i35 + 1;
            int charAt19 = zzd.charAt(i35);
            if (charAt19 >= 55296) {
                int i39 = charAt19 & 8191;
                int i40 = 13;
                while (true) {
                    i13 = i38 + 1;
                    charAt10 = zzd.charAt(i38);
                    if (charAt10 < 55296) {
                        break;
                    }
                    i39 |= (charAt10 & 8191) << i40;
                    i40 += 13;
                    i38 = i13;
                }
                charAt19 = i39 | (charAt10 << i40);
                i38 = i13;
            }
            int i41 = i38 + 1;
            charAt = zzd.charAt(i38);
            if (charAt >= 55296) {
                int i42 = charAt & 8191;
                int i43 = 13;
                while (true) {
                    i12 = i41 + 1;
                    charAt9 = zzd.charAt(i41);
                    if (charAt9 < 55296) {
                        break;
                    }
                    i42 |= (charAt9 & 8191) << i43;
                    i43 += 13;
                    i41 = i12;
                }
                charAt = i42 | (charAt9 << i43);
                i41 = i12;
            }
            int i44 = i41 + 1;
            int charAt20 = zzd.charAt(i41);
            if (charAt20 >= 55296) {
                int i45 = charAt20 & 8191;
                int i46 = 13;
                while (true) {
                    i11 = i44 + 1;
                    charAt8 = zzd.charAt(i44);
                    if (charAt8 < 55296) {
                        break;
                    }
                    i45 |= (charAt8 & 8191) << i46;
                    i46 += 13;
                    i44 = i11;
                }
                charAt20 = i45 | (charAt8 << i46);
                i44 = i11;
            }
            int i47 = i44 + 1;
            charAt2 = zzd.charAt(i44);
            if (charAt2 >= 55296) {
                int i48 = charAt2 & 8191;
                int i49 = 13;
                while (true) {
                    i10 = i47 + 1;
                    charAt7 = zzd.charAt(i47);
                    if (charAt7 < 55296) {
                        break;
                    }
                    i48 |= (charAt7 & 8191) << i49;
                    i49 += 13;
                    i47 = i10;
                }
                charAt2 = i48 | (charAt7 << i49);
                i47 = i10;
            }
            int i50 = i47 + 1;
            int charAt21 = zzd.charAt(i47);
            if (charAt21 >= 55296) {
                int i51 = charAt21 & 8191;
                int i52 = 13;
                while (true) {
                    i9 = i50 + 1;
                    charAt6 = zzd.charAt(i50);
                    if (charAt6 < 55296) {
                        break;
                    }
                    i51 |= (charAt6 & 8191) << i52;
                    i52 += 13;
                    i50 = i9;
                }
                charAt21 = i51 | (charAt6 << i52);
                i50 = i9;
            }
            int i53 = i50 + 1;
            int charAt22 = zzd.charAt(i50);
            if (charAt22 >= 55296) {
                int i54 = charAt22 & 8191;
                int i55 = 13;
                while (true) {
                    i8 = i53 + 1;
                    charAt5 = zzd.charAt(i53);
                    if (charAt5 < 55296) {
                        break;
                    }
                    i54 |= (charAt5 & 8191) << i55;
                    i55 += 13;
                    i53 = i8;
                }
                charAt22 = i54 | (charAt5 << i55);
                i53 = i8;
            }
            int i56 = i53 + 1;
            charAt3 = zzd.charAt(i53);
            if (charAt3 >= 55296) {
                int i57 = charAt3 & 8191;
                int i58 = 13;
                while (true) {
                    i7 = i56 + 1;
                    charAt4 = zzd.charAt(i56);
                    if (charAt4 < 55296) {
                        break;
                    }
                    i57 |= (charAt4 & 8191) << i58;
                    i58 += 13;
                    i56 = i7;
                }
                charAt3 = i57 | (charAt4 << i58);
                i56 = i7;
            }
            iArr = new int[charAt3 + charAt21 + charAt22];
            i3 = charAt18 + charAt18 + charAt19;
            i4 = charAt18;
            i32 = i56;
            int i59 = charAt21;
            i5 = charAt20;
            i6 = i59;
        }
        Unsafe unsafe = zzb;
        Object[] zze = zzafbVar.zze();
        Class<?> cls2 = zzafbVar.zza().getClass();
        int[] iArr3 = new int[charAt2 * 3];
        Object[] objArr2 = new Object[charAt2 + charAt2];
        int i60 = charAt3 + i6;
        int i61 = charAt3;
        int i62 = i60;
        int i63 = 0;
        int i64 = 0;
        while (i32 < length) {
            int i65 = i32 + 1;
            int charAt23 = zzd.charAt(i32);
            if (charAt23 >= c2) {
                int i66 = charAt23 & 8191;
                int i67 = i65;
                int i68 = 13;
                while (true) {
                    i29 = i67 + 1;
                    charAt15 = zzd.charAt(i67);
                    if (charAt15 < c2) {
                        break;
                    }
                    i66 |= (charAt15 & 8191) << i68;
                    i68 += 13;
                    i67 = i29;
                }
                charAt23 = i66 | (charAt15 << i68);
                i15 = i29;
            } else {
                i15 = i65;
            }
            int i69 = i15 + 1;
            int charAt24 = zzd.charAt(i15);
            if (charAt24 >= c2) {
                int i70 = charAt24 & 8191;
                int i71 = i69;
                int i72 = 13;
                while (true) {
                    i28 = i71 + 1;
                    charAt14 = zzd.charAt(i71);
                    i16 = length;
                    if (charAt14 < 55296) {
                        break;
                    }
                    i70 |= (charAt14 & 8191) << i72;
                    i72 += 13;
                    i71 = i28;
                    length = i16;
                }
                charAt24 = i70 | (charAt14 << i72);
                i17 = i28;
            } else {
                i16 = length;
                i17 = i69;
            }
            int i73 = charAt24 & 255;
            int i74 = charAt3;
            if ((charAt24 & 1024) != 0) {
                iArr[i64] = i63;
                i64++;
            }
            if (i73 >= 51) {
                int i75 = i17 + 1;
                int charAt25 = zzd.charAt(i17);
                if (charAt25 >= 55296) {
                    int i76 = charAt25 & 8191;
                    int i77 = i75;
                    int i78 = 13;
                    while (true) {
                        i27 = i77 + 1;
                        charAt13 = zzd.charAt(i77);
                        i19 = i5;
                        if (charAt13 < 55296) {
                            break;
                        }
                        i76 |= (charAt13 & 8191) << i78;
                        i78 += 13;
                        i77 = i27;
                        i5 = i19;
                    }
                    charAt25 = i76 | (charAt13 << i78);
                    i25 = i27;
                } else {
                    i19 = i5;
                    i25 = i75;
                }
                int i79 = i73 - 51;
                i22 = i25;
                if (i79 == 9 || i79 == 17) {
                    int i80 = i63 / 3;
                    i26 = i3 + 1;
                    objArr2[i80 + i80 + 1] = zze[i3];
                } else {
                    if (i79 == 12 && !z) {
                        int i81 = i63 / 3;
                        i26 = i3 + 1;
                        objArr2[i81 + i81 + 1] = zze[i3];
                    }
                    int i82 = charAt25 + charAt25;
                    obj = zze[i82];
                    if (obj instanceof Field) {
                        zzu2 = zzu(cls2, (String) obj);
                        zze[i82] = zzu2;
                    } else {
                        zzu2 = (Field) obj;
                    }
                    iArr2 = iArr3;
                    i18 = charAt;
                    int objectFieldOffset2 = (int) unsafe.objectFieldOffset(zzu2);
                    int i83 = i82 + 1;
                    obj2 = zze[i83];
                    if (obj2 instanceof Field) {
                        zzu3 = zzu(cls2, (String) obj2);
                        zze[i83] = zzu3;
                    } else {
                        zzu3 = (Field) obj2;
                    }
                    str = zzd;
                    cls = cls2;
                    i21 = (int) unsafe.objectFieldOffset(zzu3);
                    objArr = objArr2;
                    objectFieldOffset = objectFieldOffset2;
                    i23 = 0;
                }
                i3 = i26;
                int i822 = charAt25 + charAt25;
                obj = zze[i822];
                if (obj instanceof Field) {
                }
                iArr2 = iArr3;
                i18 = charAt;
                int objectFieldOffset22 = (int) unsafe.objectFieldOffset(zzu2);
                int i832 = i822 + 1;
                obj2 = zze[i832];
                if (obj2 instanceof Field) {
                }
                str = zzd;
                cls = cls2;
                i21 = (int) unsafe.objectFieldOffset(zzu3);
                objArr = objArr2;
                objectFieldOffset = objectFieldOffset22;
                i23 = 0;
            } else {
                iArr2 = iArr3;
                i18 = charAt;
                i19 = i5;
                int i84 = i3 + 1;
                Field zzu4 = zzu(cls2, (String) zze[i3]);
                if (i73 == 9 || i73 == 17) {
                    int i85 = i63 / 3;
                    objArr2[i85 + i85 + 1] = zzu4.getType();
                } else {
                    if (i73 == 27 || i73 == 49) {
                        int i86 = i63 / 3;
                        i24 = i84 + 1;
                        objArr2[i86 + i86 + 1] = zze[i84];
                    } else if (i73 == 12 || i73 == 30 || i73 == 44) {
                        if (!z) {
                            int i87 = i63 / 3;
                            i24 = i84 + 1;
                            objArr2[i87 + i87 + 1] = zze[i84];
                        }
                    } else if (i73 == 50) {
                        int i88 = i61 + 1;
                        iArr[i61] = i63;
                        int i89 = i63 / 3;
                        int i90 = i89 + i89;
                        int i91 = i84 + 1;
                        objArr2[i90] = zze[i84];
                        if ((charAt24 & 2048) != 0) {
                            i84 = i91 + 1;
                            objArr2[i90 + 1] = zze[i91];
                            i61 = i88;
                        } else {
                            i61 = i88;
                            i20 = i91;
                            objectFieldOffset = (int) unsafe.objectFieldOffset(zzu4);
                            objArr = objArr2;
                            if ((charAt24 & 4096) == 4096 || i73 > 17) {
                                str = zzd;
                                cls = cls2;
                                i21 = 1048575;
                                i22 = i17;
                                i23 = 0;
                            } else {
                                int i92 = i17 + 1;
                                int charAt26 = zzd.charAt(i17);
                                if (charAt26 >= 55296) {
                                    int i93 = charAt26 & 8191;
                                    int i94 = 13;
                                    while (true) {
                                        i22 = i92 + 1;
                                        charAt12 = zzd.charAt(i92);
                                        if (charAt12 < 55296) {
                                            break;
                                        }
                                        i93 |= (charAt12 & 8191) << i94;
                                        i94 += 13;
                                        i92 = i22;
                                    }
                                    charAt26 = i93 | (charAt12 << i94);
                                } else {
                                    i22 = i92;
                                }
                                int i95 = i4 + i4 + (charAt26 / 32);
                                Object obj3 = zze[i95];
                                str = zzd;
                                if (obj3 instanceof Field) {
                                    zzu = (Field) obj3;
                                } else {
                                    zzu = zzu(cls2, (String) obj3);
                                    zze[i95] = zzu;
                                }
                                cls = cls2;
                                i21 = (int) unsafe.objectFieldOffset(zzu);
                                i23 = charAt26 % 32;
                            }
                            if (i73 >= 18 && i73 <= 49) {
                                iArr[i62] = objectFieldOffset;
                                i62++;
                            }
                            i3 = i20;
                        }
                    }
                    i20 = i24;
                    objectFieldOffset = (int) unsafe.objectFieldOffset(zzu4);
                    objArr = objArr2;
                    if ((charAt24 & 4096) == 4096) {
                    }
                    str = zzd;
                    cls = cls2;
                    i21 = 1048575;
                    i22 = i17;
                    i23 = 0;
                    if (i73 >= 18) {
                        iArr[i62] = objectFieldOffset;
                        i62++;
                    }
                    i3 = i20;
                }
                i20 = i84;
                objectFieldOffset = (int) unsafe.objectFieldOffset(zzu4);
                objArr = objArr2;
                if ((charAt24 & 4096) == 4096) {
                }
                str = zzd;
                cls = cls2;
                i21 = 1048575;
                i22 = i17;
                i23 = 0;
                if (i73 >= 18) {
                }
                i3 = i20;
            }
            int i96 = i63 + 1;
            iArr2[i63] = charAt23;
            int i97 = i96 + 1;
            iArr2[i96] = ((charAt24 & 256) != 0 ? 268435456 : 0) | ((charAt24 & 512) != 0 ? PKIFailureInfo.duplicateCertReq : 0) | (i73 << 20) | objectFieldOffset;
            i63 = i97 + 1;
            iArr2[i97] = i21 | (i23 << 20);
            cls2 = cls;
            charAt = i18;
            charAt3 = i74;
            i32 = i22;
            length = i16;
            objArr2 = objArr;
            zzd = str;
            iArr3 = iArr2;
            i5 = i19;
            c2 = 55296;
        }
        return new zzaeu(iArr3, objArr2, charAt, i5, zzafbVar.zza(), z, false, iArr, charAt3, i60, zzaewVar, zzaefVar, zzaftVar, zzadaVar, zzaemVar, null);
    }

    private static double zzj(Object obj, long j2) {
        return ((Double) zzagd.zzf(obj, j2)).doubleValue();
    }

    private static float zzk(Object obj, long j2) {
        return ((Float) zzagd.zzf(obj, j2)).floatValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x020c, code lost:
        if (r3 > 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0219, code lost:
        if (r3 > 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0226, code lost:
        if (r3 > 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0233, code lost:
        if (r3 > 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0240, code lost:
        if (r3 > 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x024d, code lost:
        if (r3 > 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x024f, code lost:
        r4 = com.google.android.libraries.places.internal.zzacx.zzz(r11) + com.google.android.libraries.places.internal.zzacx.zzA(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x008c, code lost:
        if (zzD(r17, r11, r5) != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0094, code lost:
        if (zzD(r17, r11, r5) != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x009c, code lost:
        if (zzD(r17, r11, r5) != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0118, code lost:
        if (zzD(r17, r11, r5) != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x011f, code lost:
        if (zzD(r17, r11, r5) != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0126, code lost:
        if (zzD(r17, r11, r5) != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0128, code lost:
        r3 = zzn(r17, r3);
        r4 = com.google.android.libraries.places.internal.zzacx.zzA(r11 << 3);
        r3 = com.google.android.libraries.places.internal.zzacx.zzv(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x013c, code lost:
        if (zzD(r17, r11, r5) != false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0143, code lost:
        if (zzD(r17, r11, r5) != false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0145, code lost:
        r6 = r6 + (com.google.android.libraries.places.internal.zzacx.zzA(r11 << 3) + com.google.android.libraries.places.internal.zzacx.zzB(zzr(r17, r3)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x015b, code lost:
        if (zzD(r17, r11, r5) != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x015d, code lost:
        r3 = com.google.android.libraries.places.internal.zzacx.zzA(r11 << 3) + 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x016b, code lost:
        if (zzD(r17, r11, r5) != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x016d, code lost:
        r3 = com.google.android.libraries.places.internal.zzacx.zzA(r11 << 3) + 8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x019e, code lost:
        if (r3 > 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01ac, code lost:
        if (r3 > 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01ba, code lost:
        if (r3 > 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01c8, code lost:
        if (r3 > 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01d6, code lost:
        if (r3 > 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01e4, code lost:
        if (r3 > 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01f2, code lost:
        if (r3 > 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01ff, code lost:
        if (r3 > 0) goto L70;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int zzl(Object obj) {
        int i2;
        int zzA;
        Object object;
        int zzA2;
        int zzy;
        int zzA3;
        int i3;
        int zzu;
        boolean z;
        int zzd;
        int zzi;
        int zzz;
        Object object2;
        int zzA4;
        int zzn;
        Unsafe unsafe = zzb;
        int i4 = 1048575;
        int i5 = 1048575;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i6 < this.zzc.length) {
            int zzq = zzq(i6);
            int[] iArr = this.zzc;
            int i9 = iArr[i6];
            int zzp = zzp(zzq);
            if (zzp <= 17) {
                int i10 = iArr[i6 + 2];
                int i11 = i10 & i4;
                i2 = 1 << (i10 >>> 20);
                if (i11 != i5) {
                    i8 = unsafe.getInt(obj, i11);
                    i5 = i11;
                }
            } else {
                i2 = 0;
            }
            long j2 = zzq & i4;
            switch (zzp) {
                case 0:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    zzA = zzacx.zzA(i9 << 3) + 8;
                    i7 += zzA;
                    break;
                case 1:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    zzA = zzacx.zzA(i9 << 3) + 4;
                    i7 += zzA;
                    break;
                case 2:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    i7 += zzacx.zzA(i9 << 3) + zzacx.zzB(unsafe.getLong(obj, j2));
                    break;
                case 3:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    i7 += zzacx.zzA(i9 << 3) + zzacx.zzB(unsafe.getLong(obj, j2));
                    break;
                case 4:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    int i12 = unsafe.getInt(obj, j2);
                    zzA2 = zzacx.zzA(i9 << 3);
                    zzy = zzacx.zzv(i12);
                    zzA3 = zzA2 + zzy;
                    i7 += zzA3;
                    break;
                case 5:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    zzA = zzacx.zzA(i9 << 3) + 8;
                    i7 += zzA;
                    break;
                case 6:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    zzA = zzacx.zzA(i9 << 3) + 4;
                    i7 += zzA;
                    break;
                case 7:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        zzA = zzacx.zzA(i9 << 3) + 1;
                        i7 += zzA;
                        break;
                    }
                case 8:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        object = unsafe.getObject(obj, j2);
                        if (!(object instanceof zzacp)) {
                            zzA2 = zzacx.zzA(i9 << 3);
                            zzy = zzacx.zzy((String) object);
                            zzA3 = zzA2 + zzy;
                            i7 += zzA3;
                            break;
                        }
                        int zzA5 = zzacx.zzA(i9 << 3);
                        int zzd2 = ((zzacp) object).zzd();
                        zzA3 = zzA5 + zzacx.zzA(zzd2) + zzd2;
                        i7 += zzA3;
                    }
                case 9:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        zzA = zzafe.zzo(i9, unsafe.getObject(obj, j2), zzs(i6));
                        i7 += zzA;
                        break;
                    }
                case 10:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        object = unsafe.getObject(obj, j2);
                        int zzA52 = zzacx.zzA(i9 << 3);
                        int zzd22 = ((zzacp) object).zzd();
                        zzA3 = zzA52 + zzacx.zzA(zzd22) + zzd22;
                        i7 += zzA3;
                        break;
                    }
                case 11:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        i3 = unsafe.getInt(obj, j2);
                        zzA2 = zzacx.zzA(i9 << 3);
                        zzy = zzacx.zzA(i3);
                        zzA3 = zzA2 + zzy;
                        i7 += zzA3;
                        break;
                    }
                case 12:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    int i122 = unsafe.getInt(obj, j2);
                    zzA2 = zzacx.zzA(i9 << 3);
                    zzy = zzacx.zzv(i122);
                    zzA3 = zzA2 + zzy;
                    i7 += zzA3;
                    break;
                case 13:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    zzA = zzacx.zzA(i9 << 3) + 4;
                    i7 += zzA;
                    break;
                case 14:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    zzA = zzacx.zzA(i9 << 3) + 8;
                    i7 += zzA;
                    break;
                case 15:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        int i13 = unsafe.getInt(obj, j2);
                        zzA2 = zzacx.zzA(i9 << 3);
                        i3 = (i13 >> 31) ^ (i13 + i13);
                        zzy = zzacx.zzA(i3);
                        zzA3 = zzA2 + zzy;
                        i7 += zzA3;
                        break;
                    }
                case 16:
                    if ((i2 & i8) == 0) {
                        break;
                    } else {
                        long j3 = unsafe.getLong(obj, j2);
                        i7 += zzacx.zzA(i9 << 3) + zzacx.zzB((j3 >> 63) ^ (j3 + j3));
                        break;
                    }
                case 17:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        zzA = zzacx.zzu(i9, (zzaer) unsafe.getObject(obj, j2), zzs(i6));
                        i7 += zzA;
                        break;
                    }
                case 18:
                case 23:
                    zzA = zzafe.zzh(i9, (List) unsafe.getObject(obj, j2), false);
                    i7 += zzA;
                    break;
                case 19:
                case 24:
                    zzA = zzafe.zzf(i9, (List) unsafe.getObject(obj, j2), false);
                    i7 += zzA;
                    break;
                case 20:
                    zzA = zzafe.zzm(i9, (List) unsafe.getObject(obj, j2), false);
                    i7 += zzA;
                    break;
                case 21:
                    zzA = zzafe.zzx(i9, (List) unsafe.getObject(obj, j2), false);
                    i7 += zzA;
                    break;
                case 22:
                    zzA = zzafe.zzk(i9, (List) unsafe.getObject(obj, j2), false);
                    i7 += zzA;
                    break;
                case 25:
                    zzA = zzafe.zza(i9, (List) unsafe.getObject(obj, j2), false);
                    i7 += zzA;
                    break;
                case 26:
                    zzu = zzafe.zzu(i9, (List) unsafe.getObject(obj, j2));
                    i7 += zzu;
                    break;
                case 27:
                    zzu = zzafe.zzp(i9, (List) unsafe.getObject(obj, j2), zzs(i6));
                    i7 += zzu;
                    break;
                case 28:
                    zzu = zzafe.zzc(i9, (List) unsafe.getObject(obj, j2));
                    i7 += zzu;
                    break;
                case 29:
                    zzu = zzafe.zzv(i9, (List) unsafe.getObject(obj, j2), false);
                    i7 += zzu;
                    break;
                case 30:
                    z = false;
                    zzd = zzafe.zzd(i9, (List) unsafe.getObject(obj, j2), false);
                    i7 += zzd;
                    break;
                case 31:
                    z = false;
                    zzd = zzafe.zzf(i9, (List) unsafe.getObject(obj, j2), false);
                    i7 += zzd;
                    break;
                case 32:
                    z = false;
                    zzd = zzafe.zzh(i9, (List) unsafe.getObject(obj, j2), false);
                    i7 += zzd;
                    break;
                case 33:
                    z = false;
                    zzd = zzafe.zzq(i9, (List) unsafe.getObject(obj, j2), false);
                    i7 += zzd;
                    break;
                case 34:
                    z = false;
                    zzd = zzafe.zzs(i9, (List) unsafe.getObject(obj, j2), false);
                    i7 += zzd;
                    break;
                case 35:
                    zzi = zzafe.zzi((List) unsafe.getObject(obj, j2));
                    break;
                case 36:
                    zzi = zzafe.zzg((List) unsafe.getObject(obj, j2));
                    break;
                case 37:
                    zzi = zzafe.zzn((List) unsafe.getObject(obj, j2));
                    break;
                case 38:
                    zzi = zzafe.zzy((List) unsafe.getObject(obj, j2));
                    break;
                case 39:
                    zzi = zzafe.zzl((List) unsafe.getObject(obj, j2));
                    break;
                case 40:
                    zzi = zzafe.zzi((List) unsafe.getObject(obj, j2));
                    break;
                case 41:
                    zzi = zzafe.zzg((List) unsafe.getObject(obj, j2));
                    break;
                case 42:
                    zzi = zzafe.zzb((List) unsafe.getObject(obj, j2));
                    break;
                case 43:
                    zzi = zzafe.zzw((List) unsafe.getObject(obj, j2));
                    break;
                case 44:
                    zzi = zzafe.zze((List) unsafe.getObject(obj, j2));
                    break;
                case 45:
                    zzi = zzafe.zzg((List) unsafe.getObject(obj, j2));
                    break;
                case 46:
                    zzi = zzafe.zzi((List) unsafe.getObject(obj, j2));
                    break;
                case 47:
                    zzi = zzafe.zzr((List) unsafe.getObject(obj, j2));
                    break;
                case 48:
                    zzi = zzafe.zzt((List) unsafe.getObject(obj, j2));
                    break;
                case 49:
                    zzu = zzafe.zzj(i9, (List) unsafe.getObject(obj, j2), zzs(i6));
                    i7 += zzu;
                    break;
                case 50:
                    zzaem.zza(i9, unsafe.getObject(obj, j2), zzt(i6));
                    break;
                case 58:
                    if (zzD(obj, i9, i6)) {
                        zzu = zzacx.zzA(i9 << 3) + 1;
                        i7 += zzu;
                    }
                    break;
                case 59:
                    if (zzD(obj, i9, i6)) {
                        object2 = unsafe.getObject(obj, j2);
                        if (!(object2 instanceof zzacp)) {
                            zzz = zzacx.zzA(i9 << 3);
                            zzi = zzacx.zzy((String) object2);
                            zzA4 = zzz + zzi;
                            i7 += zzA4;
                        }
                        int zzA6 = zzacx.zzA(i9 << 3);
                        int zzd3 = ((zzacp) object2).zzd();
                        zzA4 = zzA6 + zzacx.zzA(zzd3) + zzd3;
                        i7 += zzA4;
                    }
                    break;
                case 60:
                    if (zzD(obj, i9, i6)) {
                        zzu = zzafe.zzo(i9, unsafe.getObject(obj, j2), zzs(i6));
                        i7 += zzu;
                    }
                    break;
                case 61:
                    if (zzD(obj, i9, i6)) {
                        object2 = unsafe.getObject(obj, j2);
                        int zzA62 = zzacx.zzA(i9 << 3);
                        int zzd32 = ((zzacp) object2).zzd();
                        zzA4 = zzA62 + zzacx.zzA(zzd32) + zzd32;
                        i7 += zzA4;
                    }
                    break;
                case 62:
                    if (zzD(obj, i9, i6)) {
                        zzn = zzn(obj, j2);
                        zzz = zzacx.zzA(i9 << 3);
                        zzi = zzacx.zzA(zzn);
                        zzA4 = zzz + zzi;
                        i7 += zzA4;
                    }
                    break;
                case 66:
                    if (zzD(obj, i9, i6)) {
                        int zzn2 = zzn(obj, j2);
                        zzz = zzacx.zzA(i9 << 3);
                        zzn = (zzn2 >> 31) ^ (zzn2 + zzn2);
                        zzi = zzacx.zzA(zzn);
                        zzA4 = zzz + zzi;
                        i7 += zzA4;
                    }
                    break;
                case 67:
                    if (zzD(obj, i9, i6)) {
                        long zzr = zzr(obj, j2);
                        i7 += zzacx.zzA(i9 << 3) + zzacx.zzB((zzr >> 63) ^ (zzr + zzr));
                    }
                    break;
                case 68:
                    if (zzD(obj, i9, i6)) {
                        zzu = zzacx.zzu(i9, (zzaer) unsafe.getObject(obj, j2), zzs(i6));
                        i7 += zzu;
                    }
                    break;
            }
            i6 += 3;
            i4 = 1048575;
        }
        zzaft zzaftVar = this.zzl;
        int zza2 = i7 + zzaftVar.zza(zzaftVar.zzc(obj));
        if (this.zzf) {
            this.zzm.zza(obj);
            throw null;
        }
        return zza2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:162:0x030e, code lost:
        if ((r4 instanceof com.google.android.libraries.places.internal.zzacp) != false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0311, code lost:
        r5 = com.google.android.libraries.places.internal.zzacx.zzA(r6 << 3);
        r4 = com.google.android.libraries.places.internal.zzacx.zzy((java.lang.String) r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0097, code lost:
        if ((r4 instanceof com.google.android.libraries.places.internal.zzacp) != false) goto L53;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int zzm(Object obj) {
        long zzd;
        Object zzf;
        int zzc;
        int zzc2;
        int zzc3;
        long zzd2;
        int zzh;
        int zzi;
        int zzz;
        int zzA;
        Unsafe unsafe = zzb;
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzc.length; i3 += 3) {
            int zzq = zzq(i3);
            int zzp = zzp(zzq);
            int i4 = this.zzc[i3];
            long j2 = zzq & 1048575;
            if (zzp >= zzadf.zzJ.zza() && zzp <= zzadf.zzW.zza()) {
                int i5 = this.zzc[i3 + 2];
            }
            switch (zzp) {
                case 0:
                    if (!zzA(obj, i3)) {
                        continue;
                    }
                    zzh = zzacx.zzA(i4 << 3) + 8;
                    break;
                case 1:
                    if (!zzA(obj, i3)) {
                        continue;
                    }
                    zzh = zzacx.zzA(i4 << 3) + 4;
                    break;
                case 2:
                    if (!zzA(obj, i3)) {
                        continue;
                    }
                    zzd = zzagd.zzd(obj, j2);
                    i2 += zzacx.zzA(i4 << 3) + zzacx.zzB(zzd);
                case 3:
                    if (!zzA(obj, i3)) {
                        continue;
                    }
                    zzd = zzagd.zzd(obj, j2);
                    i2 += zzacx.zzA(i4 << 3) + zzacx.zzB(zzd);
                case 4:
                    if (!zzA(obj, i3)) {
                        continue;
                    }
                    zzc2 = zzagd.zzc(obj, j2);
                    zzz = zzacx.zzA(i4 << 3);
                    zzi = zzacx.zzv(zzc2);
                    zzA = zzz + zzi;
                    i2 += zzA;
                case 5:
                    if (!zzA(obj, i3)) {
                        continue;
                    }
                    zzh = zzacx.zzA(i4 << 3) + 8;
                    break;
                case 6:
                    if (!zzA(obj, i3)) {
                        continue;
                    }
                    zzh = zzacx.zzA(i4 << 3) + 4;
                    break;
                case 7:
                    if (!zzA(obj, i3)) {
                        continue;
                    }
                    zzh = zzacx.zzA(i4 << 3) + 1;
                    break;
                case 8:
                    if (zzA(obj, i3)) {
                        zzf = zzagd.zzf(obj, j2);
                        break;
                    } else {
                        continue;
                    }
                case 9:
                    if (!zzA(obj, i3)) {
                        continue;
                    }
                    zzh = zzafe.zzo(i4, zzagd.zzf(obj, j2), zzs(i3));
                    break;
                case 10:
                    if (!zzA(obj, i3)) {
                        continue;
                    }
                    zzf = zzagd.zzf(obj, j2);
                    int zzA2 = zzacx.zzA(i4 << 3);
                    int zzd3 = ((zzacp) zzf).zzd();
                    zzA = zzA2 + zzacx.zzA(zzd3) + zzd3;
                    i2 += zzA;
                case 11:
                    if (zzA(obj, i3)) {
                        zzc = zzagd.zzc(obj, j2);
                        zzz = zzacx.zzA(i4 << 3);
                        zzi = zzacx.zzA(zzc);
                        zzA = zzz + zzi;
                        i2 += zzA;
                    } else {
                        continue;
                    }
                case 12:
                    if (!zzA(obj, i3)) {
                        continue;
                    }
                    zzc2 = zzagd.zzc(obj, j2);
                    zzz = zzacx.zzA(i4 << 3);
                    zzi = zzacx.zzv(zzc2);
                    zzA = zzz + zzi;
                    i2 += zzA;
                case 13:
                    if (!zzA(obj, i3)) {
                        continue;
                    }
                    zzh = zzacx.zzA(i4 << 3) + 4;
                    break;
                case 14:
                    if (!zzA(obj, i3)) {
                        continue;
                    }
                    zzh = zzacx.zzA(i4 << 3) + 8;
                    break;
                case 15:
                    if (zzA(obj, i3)) {
                        zzc3 = zzagd.zzc(obj, j2);
                        zzz = zzacx.zzA(i4 << 3);
                        zzc = (zzc3 >> 31) ^ (zzc3 + zzc3);
                        zzi = zzacx.zzA(zzc);
                        zzA = zzz + zzi;
                        i2 += zzA;
                    } else {
                        continue;
                    }
                case 16:
                    if (zzA(obj, i3)) {
                        zzd2 = zzagd.zzd(obj, j2);
                        zzz = zzacx.zzA(i4 << 3);
                        zzi = zzacx.zzB((zzd2 >> 63) ^ (zzd2 + zzd2));
                        zzA = zzz + zzi;
                        i2 += zzA;
                    } else {
                        continue;
                    }
                case 17:
                    if (!zzA(obj, i3)) {
                        continue;
                    }
                    zzh = zzacx.zzu(i4, (zzaer) zzagd.zzf(obj, j2), zzs(i3));
                    break;
                case 18:
                case 23:
                case 32:
                    zzh = zzafe.zzh(i4, (List) zzagd.zzf(obj, j2), false);
                    break;
                case 19:
                case 24:
                case 31:
                    zzh = zzafe.zzf(i4, (List) zzagd.zzf(obj, j2), false);
                    break;
                case 20:
                    zzh = zzafe.zzm(i4, (List) zzagd.zzf(obj, j2), false);
                    break;
                case 21:
                    zzh = zzafe.zzx(i4, (List) zzagd.zzf(obj, j2), false);
                    break;
                case 22:
                    zzh = zzafe.zzk(i4, (List) zzagd.zzf(obj, j2), false);
                    break;
                case 25:
                    zzh = zzafe.zza(i4, (List) zzagd.zzf(obj, j2), false);
                    break;
                case 26:
                    zzh = zzafe.zzu(i4, (List) zzagd.zzf(obj, j2));
                    break;
                case 27:
                    zzh = zzafe.zzp(i4, (List) zzagd.zzf(obj, j2), zzs(i3));
                    break;
                case 28:
                    zzh = zzafe.zzc(i4, (List) zzagd.zzf(obj, j2));
                    break;
                case 29:
                    zzh = zzafe.zzv(i4, (List) zzagd.zzf(obj, j2), false);
                    break;
                case 30:
                    zzh = zzafe.zzd(i4, (List) zzagd.zzf(obj, j2), false);
                    break;
                case 33:
                    zzh = zzafe.zzq(i4, (List) zzagd.zzf(obj, j2), false);
                    break;
                case 34:
                    zzh = zzafe.zzs(i4, (List) zzagd.zzf(obj, j2), false);
                    break;
                case 35:
                    zzi = zzafe.zzi((List) unsafe.getObject(obj, j2));
                    if (zzi <= 0) {
                        continue;
                    }
                    zzz = zzacx.zzz(i4) + zzacx.zzA(zzi);
                    zzA = zzz + zzi;
                    i2 += zzA;
                case 36:
                    zzi = zzafe.zzg((List) unsafe.getObject(obj, j2));
                    if (zzi <= 0) {
                        continue;
                    }
                    zzz = zzacx.zzz(i4) + zzacx.zzA(zzi);
                    zzA = zzz + zzi;
                    i2 += zzA;
                case 37:
                    zzi = zzafe.zzn((List) unsafe.getObject(obj, j2));
                    if (zzi <= 0) {
                        continue;
                    }
                    zzz = zzacx.zzz(i4) + zzacx.zzA(zzi);
                    zzA = zzz + zzi;
                    i2 += zzA;
                case 38:
                    zzi = zzafe.zzy((List) unsafe.getObject(obj, j2));
                    if (zzi <= 0) {
                        continue;
                    }
                    zzz = zzacx.zzz(i4) + zzacx.zzA(zzi);
                    zzA = zzz + zzi;
                    i2 += zzA;
                case 39:
                    zzi = zzafe.zzl((List) unsafe.getObject(obj, j2));
                    if (zzi <= 0) {
                        continue;
                    }
                    zzz = zzacx.zzz(i4) + zzacx.zzA(zzi);
                    zzA = zzz + zzi;
                    i2 += zzA;
                case 40:
                    zzi = zzafe.zzi((List) unsafe.getObject(obj, j2));
                    if (zzi <= 0) {
                        continue;
                    }
                    zzz = zzacx.zzz(i4) + zzacx.zzA(zzi);
                    zzA = zzz + zzi;
                    i2 += zzA;
                case 41:
                    zzi = zzafe.zzg((List) unsafe.getObject(obj, j2));
                    if (zzi <= 0) {
                        continue;
                    }
                    zzz = zzacx.zzz(i4) + zzacx.zzA(zzi);
                    zzA = zzz + zzi;
                    i2 += zzA;
                case 42:
                    zzi = zzafe.zzb((List) unsafe.getObject(obj, j2));
                    if (zzi <= 0) {
                        continue;
                    }
                    zzz = zzacx.zzz(i4) + zzacx.zzA(zzi);
                    zzA = zzz + zzi;
                    i2 += zzA;
                case 43:
                    zzi = zzafe.zzw((List) unsafe.getObject(obj, j2));
                    if (zzi <= 0) {
                        continue;
                    }
                    zzz = zzacx.zzz(i4) + zzacx.zzA(zzi);
                    zzA = zzz + zzi;
                    i2 += zzA;
                case 44:
                    zzi = zzafe.zze((List) unsafe.getObject(obj, j2));
                    if (zzi <= 0) {
                        continue;
                    }
                    zzz = zzacx.zzz(i4) + zzacx.zzA(zzi);
                    zzA = zzz + zzi;
                    i2 += zzA;
                case 45:
                    zzi = zzafe.zzg((List) unsafe.getObject(obj, j2));
                    if (zzi <= 0) {
                        continue;
                    }
                    zzz = zzacx.zzz(i4) + zzacx.zzA(zzi);
                    zzA = zzz + zzi;
                    i2 += zzA;
                case 46:
                    zzi = zzafe.zzi((List) unsafe.getObject(obj, j2));
                    if (zzi <= 0) {
                        continue;
                    }
                    zzz = zzacx.zzz(i4) + zzacx.zzA(zzi);
                    zzA = zzz + zzi;
                    i2 += zzA;
                case 47:
                    zzi = zzafe.zzr((List) unsafe.getObject(obj, j2));
                    if (zzi <= 0) {
                        continue;
                    }
                    zzz = zzacx.zzz(i4) + zzacx.zzA(zzi);
                    zzA = zzz + zzi;
                    i2 += zzA;
                case 48:
                    zzi = zzafe.zzt((List) unsafe.getObject(obj, j2));
                    if (zzi <= 0) {
                        continue;
                    }
                    zzz = zzacx.zzz(i4) + zzacx.zzA(zzi);
                    zzA = zzz + zzi;
                    i2 += zzA;
                case 49:
                    zzh = zzafe.zzj(i4, (List) zzagd.zzf(obj, j2), zzs(i3));
                    break;
                case 50:
                    zzaem.zza(i4, zzagd.zzf(obj, j2), zzt(i3));
                    continue;
                case 51:
                    if (!zzD(obj, i4, i3)) {
                        continue;
                    }
                    zzh = zzacx.zzA(i4 << 3) + 8;
                    break;
                case 52:
                    if (!zzD(obj, i4, i3)) {
                        continue;
                    }
                    zzh = zzacx.zzA(i4 << 3) + 4;
                    break;
                case 53:
                    if (!zzD(obj, i4, i3)) {
                        continue;
                    }
                    zzd = zzr(obj, j2);
                    i2 += zzacx.zzA(i4 << 3) + zzacx.zzB(zzd);
                case 54:
                    if (!zzD(obj, i4, i3)) {
                        continue;
                    }
                    zzd = zzr(obj, j2);
                    i2 += zzacx.zzA(i4 << 3) + zzacx.zzB(zzd);
                case 55:
                    if (!zzD(obj, i4, i3)) {
                        continue;
                    }
                    zzc2 = zzn(obj, j2);
                    zzz = zzacx.zzA(i4 << 3);
                    zzi = zzacx.zzv(zzc2);
                    zzA = zzz + zzi;
                    i2 += zzA;
                case 56:
                    if (!zzD(obj, i4, i3)) {
                        continue;
                    }
                    zzh = zzacx.zzA(i4 << 3) + 8;
                    break;
                case 57:
                    if (!zzD(obj, i4, i3)) {
                        continue;
                    }
                    zzh = zzacx.zzA(i4 << 3) + 4;
                    break;
                case 58:
                    if (!zzD(obj, i4, i3)) {
                        continue;
                    }
                    zzh = zzacx.zzA(i4 << 3) + 1;
                    break;
                case 59:
                    if (zzD(obj, i4, i3)) {
                        zzf = zzagd.zzf(obj, j2);
                        break;
                    } else {
                        continue;
                    }
                case 60:
                    if (!zzD(obj, i4, i3)) {
                        continue;
                    }
                    zzh = zzafe.zzo(i4, zzagd.zzf(obj, j2), zzs(i3));
                    break;
                case 61:
                    if (!zzD(obj, i4, i3)) {
                        continue;
                    }
                    zzf = zzagd.zzf(obj, j2);
                    int zzA22 = zzacx.zzA(i4 << 3);
                    int zzd32 = ((zzacp) zzf).zzd();
                    zzA = zzA22 + zzacx.zzA(zzd32) + zzd32;
                    i2 += zzA;
                case 62:
                    if (zzD(obj, i4, i3)) {
                        zzc = zzn(obj, j2);
                        zzz = zzacx.zzA(i4 << 3);
                        zzi = zzacx.zzA(zzc);
                        zzA = zzz + zzi;
                        i2 += zzA;
                    } else {
                        continue;
                    }
                case 63:
                    if (!zzD(obj, i4, i3)) {
                        continue;
                    }
                    zzc2 = zzn(obj, j2);
                    zzz = zzacx.zzA(i4 << 3);
                    zzi = zzacx.zzv(zzc2);
                    zzA = zzz + zzi;
                    i2 += zzA;
                case 64:
                    if (!zzD(obj, i4, i3)) {
                        continue;
                    }
                    zzh = zzacx.zzA(i4 << 3) + 4;
                    break;
                case 65:
                    if (!zzD(obj, i4, i3)) {
                        continue;
                    }
                    zzh = zzacx.zzA(i4 << 3) + 8;
                    break;
                case 66:
                    if (zzD(obj, i4, i3)) {
                        zzc3 = zzn(obj, j2);
                        zzz = zzacx.zzA(i4 << 3);
                        zzc = (zzc3 >> 31) ^ (zzc3 + zzc3);
                        zzi = zzacx.zzA(zzc);
                        zzA = zzz + zzi;
                        i2 += zzA;
                    } else {
                        continue;
                    }
                case 67:
                    if (zzD(obj, i4, i3)) {
                        zzd2 = zzr(obj, j2);
                        zzz = zzacx.zzA(i4 << 3);
                        zzi = zzacx.zzB((zzd2 >> 63) ^ (zzd2 + zzd2));
                        zzA = zzz + zzi;
                        i2 += zzA;
                    } else {
                        continue;
                    }
                case 68:
                    if (!zzD(obj, i4, i3)) {
                        continue;
                    }
                    zzh = zzacx.zzu(i4, (zzaer) zzagd.zzf(obj, j2), zzs(i3));
                    break;
                default:
            }
            i2 += zzh;
        }
        zzaft zzaftVar = this.zzl;
        return i2 + zzaftVar.zza(zzaftVar.zzc(obj));
    }

    private static int zzn(Object obj, long j2) {
        return ((Integer) zzagd.zzf(obj, j2)).intValue();
    }

    private final int zzo(int i2) {
        return this.zzc[i2 + 2];
    }

    private static int zzp(int i2) {
        return (i2 >>> 20) & 255;
    }

    private final int zzq(int i2) {
        return this.zzc[i2 + 1];
    }

    private static long zzr(Object obj, long j2) {
        return ((Long) zzagd.zzf(obj, j2)).longValue();
    }

    private final zzafc zzs(int i2) {
        int i3 = i2 / 3;
        int i4 = i3 + i3;
        zzafc zzafcVar = (zzafc) this.zzd[i4];
        if (zzafcVar != null) {
            return zzafcVar;
        }
        zzafc zzb2 = zzaez.zza().zzb((Class) this.zzd[i4 + 1]);
        this.zzd[i4] = zzb2;
        return zzb2;
    }

    private final Object zzt(int i2) {
        int i3 = i2 / 3;
        return this.zzd[i3 + i3];
    }

    private static Field zzu(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + name.length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    private final void zzv(Object obj, Object obj2, int i2) {
        long zzq = zzq(i2) & 1048575;
        if (zzA(obj2, i2)) {
            Object zzf = zzagd.zzf(obj, zzq);
            Object zzf2 = zzagd.zzf(obj2, zzq);
            if (zzf != null && zzf2 != null) {
                zzf2 = zzads.zzg(zzf, zzf2);
            } else if (zzf2 == null) {
                return;
            }
            zzagd.zzs(obj, zzq, zzf2);
            zzx(obj, i2);
        }
    }

    private final void zzw(Object obj, Object obj2, int i2) {
        int zzq = zzq(i2);
        int i3 = this.zzc[i2];
        long j2 = zzq & 1048575;
        if (zzD(obj2, i3, i2)) {
            Object zzf = zzD(obj, i3, i2) ? zzagd.zzf(obj, j2) : null;
            Object zzf2 = zzagd.zzf(obj2, j2);
            if (zzf != null && zzf2 != null) {
                zzf2 = zzads.zzg(zzf, zzf2);
            } else if (zzf2 == null) {
                return;
            }
            zzagd.zzs(obj, j2, zzf2);
            zzy(obj, i3, i2);
        }
    }

    private final void zzx(Object obj, int i2) {
        int zzo = zzo(i2);
        long j2 = 1048575 & zzo;
        if (j2 == 1048575) {
            return;
        }
        zzagd.zzq(obj, j2, (1 << (zzo >>> 20)) | zzagd.zzc(obj, j2));
    }

    private final void zzy(Object obj, int i2, int i3) {
        zzagd.zzq(obj, zzo(i3) & 1048575, i2);
    }

    private final boolean zzz(Object obj, Object obj2, int i2) {
        return zzA(obj, i2) == zzA(obj2, i2);
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final int zza(Object obj) {
        return this.zzg ? zzm(obj) : zzl(obj);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00c8, code lost:
        if (r3 != null) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00da, code lost:
        if (r3 != null) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00dc, code lost:
        r7 = r3.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00e0, code lost:
        r2 = (r2 * 53) + r7;
     */
    @Override // com.google.android.libraries.places.internal.zzafc
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int zzb(Object obj) {
        int i2;
        double zza2;
        float zzb2;
        long zzd;
        int zzc;
        boolean zzw;
        Object zzf;
        int length = this.zzc.length;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4 += 3) {
            int zzq = zzq(i4);
            int i5 = this.zzc[i4];
            long j2 = 1048575 & zzq;
            int i6 = 37;
            switch (zzp(zzq)) {
                case 0:
                    i2 = i3 * 53;
                    zza2 = zzagd.zza(obj, j2);
                    zzd = Double.doubleToLongBits(zza2);
                    zzc = zzads.zzc(zzd);
                    i3 = i2 + zzc;
                    break;
                case 1:
                    i2 = i3 * 53;
                    zzb2 = zzagd.zzb(obj, j2);
                    zzc = Float.floatToIntBits(zzb2);
                    i3 = i2 + zzc;
                    break;
                case 2:
                case 3:
                case 5:
                case 14:
                case 16:
                    i2 = i3 * 53;
                    zzd = zzagd.zzd(obj, j2);
                    zzc = zzads.zzc(zzd);
                    i3 = i2 + zzc;
                    break;
                case 4:
                case 6:
                case 11:
                case 12:
                case 13:
                case 15:
                    i2 = i3 * 53;
                    zzc = zzagd.zzc(obj, j2);
                    i3 = i2 + zzc;
                    break;
                case 7:
                    i2 = i3 * 53;
                    zzw = zzagd.zzw(obj, j2);
                    zzc = zzads.zza(zzw);
                    i3 = i2 + zzc;
                    break;
                case 8:
                    i2 = i3 * 53;
                    zzc = ((String) zzagd.zzf(obj, j2)).hashCode();
                    i3 = i2 + zzc;
                    break;
                case 9:
                    zzf = zzagd.zzf(obj, j2);
                    break;
                case 10:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                    i2 = i3 * 53;
                    zzc = zzagd.zzf(obj, j2).hashCode();
                    i3 = i2 + zzc;
                    break;
                case 17:
                    zzf = zzagd.zzf(obj, j2);
                    break;
                case 51:
                    if (zzD(obj, i5, i4)) {
                        i2 = i3 * 53;
                        zza2 = zzj(obj, j2);
                        zzd = Double.doubleToLongBits(zza2);
                        zzc = zzads.zzc(zzd);
                        i3 = i2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzD(obj, i5, i4)) {
                        i2 = i3 * 53;
                        zzb2 = zzk(obj, j2);
                        zzc = Float.floatToIntBits(zzb2);
                        i3 = i2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (!zzD(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    zzd = zzr(obj, j2);
                    zzc = zzads.zzc(zzd);
                    i3 = i2 + zzc;
                    break;
                case 54:
                    if (!zzD(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    zzd = zzr(obj, j2);
                    zzc = zzads.zzc(zzd);
                    i3 = i2 + zzc;
                    break;
                case 55:
                    if (!zzD(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    zzc = zzn(obj, j2);
                    i3 = i2 + zzc;
                    break;
                case 56:
                    if (!zzD(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    zzd = zzr(obj, j2);
                    zzc = zzads.zzc(zzd);
                    i3 = i2 + zzc;
                    break;
                case 57:
                    if (!zzD(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    zzc = zzn(obj, j2);
                    i3 = i2 + zzc;
                    break;
                case 58:
                    if (zzD(obj, i5, i4)) {
                        i2 = i3 * 53;
                        zzw = zzE(obj, j2);
                        zzc = zzads.zza(zzw);
                        i3 = i2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (!zzD(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    zzc = ((String) zzagd.zzf(obj, j2)).hashCode();
                    i3 = i2 + zzc;
                    break;
                case 60:
                    if (!zzD(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    zzc = zzagd.zzf(obj, j2).hashCode();
                    i3 = i2 + zzc;
                    break;
                case 61:
                    if (!zzD(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    zzc = zzagd.zzf(obj, j2).hashCode();
                    i3 = i2 + zzc;
                    break;
                case 62:
                    if (!zzD(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    zzc = zzn(obj, j2);
                    i3 = i2 + zzc;
                    break;
                case 63:
                    if (!zzD(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    zzc = zzn(obj, j2);
                    i3 = i2 + zzc;
                    break;
                case 64:
                    if (!zzD(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    zzc = zzn(obj, j2);
                    i3 = i2 + zzc;
                    break;
                case 65:
                    if (!zzD(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    zzd = zzr(obj, j2);
                    zzc = zzads.zzc(zzd);
                    i3 = i2 + zzc;
                    break;
                case 66:
                    if (!zzD(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    zzc = zzn(obj, j2);
                    i3 = i2 + zzc;
                    break;
                case 67:
                    if (!zzD(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    zzd = zzr(obj, j2);
                    zzc = zzads.zzc(zzd);
                    i3 = i2 + zzc;
                    break;
                case 68:
                    if (!zzD(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    zzc = zzagd.zzf(obj, j2).hashCode();
                    i3 = i2 + zzc;
                    break;
            }
        }
        int hashCode = (i3 * 53) + this.zzl.zzc(obj).hashCode();
        if (this.zzf) {
            this.zzm.zza(obj);
            throw null;
        }
        return hashCode;
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final void zzc(Object obj) {
        int i2;
        int i3 = this.zzi;
        while (true) {
            i2 = this.zzj;
            if (i3 >= i2) {
                break;
            }
            long zzq = zzq(this.zzh[i3]) & 1048575;
            Object zzf = zzagd.zzf(obj, zzq);
            if (zzf != null) {
                ((zzael) zzf).zzb();
                zzagd.zzs(obj, zzq, zzf);
            }
            i3++;
        }
        int length = this.zzh.length;
        while (i2 < length) {
            this.zzk.zza(obj, this.zzh[i2]);
            i2++;
        }
        this.zzl.zze(obj);
        if (this.zzf) {
            this.zzm.zzb(obj);
        }
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final void zzd(Object obj, Object obj2) {
        Objects.requireNonNull(obj2);
        for (int i2 = 0; i2 < this.zzc.length; i2 += 3) {
            int zzq = zzq(i2);
            long j2 = 1048575 & zzq;
            int i3 = this.zzc[i2];
            switch (zzp(zzq)) {
                case 0:
                    if (zzA(obj2, i2)) {
                        zzagd.zzo(obj, j2, zzagd.zza(obj2, j2));
                        zzx(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzA(obj2, i2)) {
                        zzagd.zzp(obj, j2, zzagd.zzb(obj2, j2));
                        zzx(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (!zzA(obj2, i2)) {
                        break;
                    }
                    zzagd.zzr(obj, j2, zzagd.zzd(obj2, j2));
                    zzx(obj, i2);
                    break;
                case 3:
                    if (!zzA(obj2, i2)) {
                        break;
                    }
                    zzagd.zzr(obj, j2, zzagd.zzd(obj2, j2));
                    zzx(obj, i2);
                    break;
                case 4:
                    if (!zzA(obj2, i2)) {
                        break;
                    }
                    zzagd.zzq(obj, j2, zzagd.zzc(obj2, j2));
                    zzx(obj, i2);
                    break;
                case 5:
                    if (!zzA(obj2, i2)) {
                        break;
                    }
                    zzagd.zzr(obj, j2, zzagd.zzd(obj2, j2));
                    zzx(obj, i2);
                    break;
                case 6:
                    if (!zzA(obj2, i2)) {
                        break;
                    }
                    zzagd.zzq(obj, j2, zzagd.zzc(obj2, j2));
                    zzx(obj, i2);
                    break;
                case 7:
                    if (zzA(obj2, i2)) {
                        zzagd.zzm(obj, j2, zzagd.zzw(obj2, j2));
                        zzx(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (!zzA(obj2, i2)) {
                        break;
                    }
                    zzagd.zzs(obj, j2, zzagd.zzf(obj2, j2));
                    zzx(obj, i2);
                    break;
                case 9:
                case 17:
                    zzv(obj, obj2, i2);
                    break;
                case 10:
                    if (!zzA(obj2, i2)) {
                        break;
                    }
                    zzagd.zzs(obj, j2, zzagd.zzf(obj2, j2));
                    zzx(obj, i2);
                    break;
                case 11:
                    if (!zzA(obj2, i2)) {
                        break;
                    }
                    zzagd.zzq(obj, j2, zzagd.zzc(obj2, j2));
                    zzx(obj, i2);
                    break;
                case 12:
                    if (!zzA(obj2, i2)) {
                        break;
                    }
                    zzagd.zzq(obj, j2, zzagd.zzc(obj2, j2));
                    zzx(obj, i2);
                    break;
                case 13:
                    if (!zzA(obj2, i2)) {
                        break;
                    }
                    zzagd.zzq(obj, j2, zzagd.zzc(obj2, j2));
                    zzx(obj, i2);
                    break;
                case 14:
                    if (!zzA(obj2, i2)) {
                        break;
                    }
                    zzagd.zzr(obj, j2, zzagd.zzd(obj2, j2));
                    zzx(obj, i2);
                    break;
                case 15:
                    if (!zzA(obj2, i2)) {
                        break;
                    }
                    zzagd.zzq(obj, j2, zzagd.zzc(obj2, j2));
                    zzx(obj, i2);
                    break;
                case 16:
                    if (!zzA(obj2, i2)) {
                        break;
                    }
                    zzagd.zzr(obj, j2, zzagd.zzd(obj2, j2));
                    zzx(obj, i2);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzk.zzb(obj, obj2, j2);
                    break;
                case 50:
                    zzafe.zzG(this.zzo, obj, obj2, j2);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (!zzD(obj2, i3, i2)) {
                        break;
                    }
                    zzagd.zzs(obj, j2, zzagd.zzf(obj2, j2));
                    zzy(obj, i3, i2);
                    break;
                case 60:
                case 68:
                    zzw(obj, obj2, i2);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (!zzD(obj2, i3, i2)) {
                        break;
                    }
                    zzagd.zzs(obj, j2, zzagd.zzf(obj2, j2));
                    zzy(obj, i3, i2);
                    break;
            }
        }
        zzafe.zzD(this.zzl, obj, obj2);
        if (this.zzf) {
            zzafe.zzC(this.zzm, obj, obj2);
        }
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final boolean zze(Object obj, Object obj2) {
        int length = this.zzc.length;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int zzq = zzq(i2);
            long j2 = zzq & 1048575;
            switch (zzp(zzq)) {
                case 0:
                    if (zzz(obj, obj2, i2) && Double.doubleToLongBits(zzagd.zza(obj, j2)) == Double.doubleToLongBits(zzagd.zza(obj2, j2))) {
                        break;
                    }
                    return false;
                case 1:
                    if (zzz(obj, obj2, i2) && Float.floatToIntBits(zzagd.zzb(obj, j2)) == Float.floatToIntBits(zzagd.zzb(obj2, j2))) {
                        break;
                    }
                    return false;
                case 2:
                    if (zzz(obj, obj2, i2) && zzagd.zzd(obj, j2) == zzagd.zzd(obj2, j2)) {
                        break;
                    }
                    return false;
                case 3:
                    if (zzz(obj, obj2, i2) && zzagd.zzd(obj, j2) == zzagd.zzd(obj2, j2)) {
                        break;
                    }
                    return false;
                case 4:
                    if (zzz(obj, obj2, i2) && zzagd.zzc(obj, j2) == zzagd.zzc(obj2, j2)) {
                        break;
                    }
                    return false;
                case 5:
                    if (zzz(obj, obj2, i2) && zzagd.zzd(obj, j2) == zzagd.zzd(obj2, j2)) {
                        break;
                    }
                    return false;
                case 6:
                    if (zzz(obj, obj2, i2) && zzagd.zzc(obj, j2) == zzagd.zzc(obj2, j2)) {
                        break;
                    }
                    return false;
                case 7:
                    if (zzz(obj, obj2, i2) && zzagd.zzw(obj, j2) == zzagd.zzw(obj2, j2)) {
                        break;
                    }
                    return false;
                case 8:
                    if (zzz(obj, obj2, i2) && zzafe.zzF(zzagd.zzf(obj, j2), zzagd.zzf(obj2, j2))) {
                        break;
                    }
                    return false;
                case 9:
                    if (zzz(obj, obj2, i2) && zzafe.zzF(zzagd.zzf(obj, j2), zzagd.zzf(obj2, j2))) {
                        break;
                    }
                    return false;
                case 10:
                    if (zzz(obj, obj2, i2) && zzafe.zzF(zzagd.zzf(obj, j2), zzagd.zzf(obj2, j2))) {
                        break;
                    }
                    return false;
                case 11:
                    if (zzz(obj, obj2, i2) && zzagd.zzc(obj, j2) == zzagd.zzc(obj2, j2)) {
                        break;
                    }
                    return false;
                case 12:
                    if (zzz(obj, obj2, i2) && zzagd.zzc(obj, j2) == zzagd.zzc(obj2, j2)) {
                        break;
                    }
                    return false;
                case 13:
                    if (zzz(obj, obj2, i2) && zzagd.zzc(obj, j2) == zzagd.zzc(obj2, j2)) {
                        break;
                    }
                    return false;
                case 14:
                    if (zzz(obj, obj2, i2) && zzagd.zzd(obj, j2) == zzagd.zzd(obj2, j2)) {
                        break;
                    }
                    return false;
                case 15:
                    if (zzz(obj, obj2, i2) && zzagd.zzc(obj, j2) == zzagd.zzc(obj2, j2)) {
                        break;
                    }
                    return false;
                case 16:
                    if (zzz(obj, obj2, i2) && zzagd.zzd(obj, j2) == zzagd.zzd(obj2, j2)) {
                        break;
                    }
                    return false;
                case 17:
                    if (zzz(obj, obj2, i2) && zzafe.zzF(zzagd.zzf(obj, j2), zzagd.zzf(obj2, j2))) {
                        break;
                    }
                    return false;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                    if (zzafe.zzF(zzagd.zzf(obj, j2), zzagd.zzf(obj2, j2))) {
                        break;
                    } else {
                        return false;
                    }
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long zzo = zzo(i2) & 1048575;
                    if (zzagd.zzc(obj, zzo) == zzagd.zzc(obj2, zzo) && zzafe.zzF(zzagd.zzf(obj, j2), zzagd.zzf(obj2, j2))) {
                        break;
                    }
                    return false;
            }
        }
        if (this.zzl.zzc(obj).equals(this.zzl.zzc(obj2))) {
            if (this.zzf) {
                this.zzm.zza(obj);
                this.zzm.zza(obj2);
                throw null;
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final boolean zzf(Object obj) {
        int i2;
        int i3;
        int i4 = 1048575;
        int i5 = 0;
        int i6 = 0;
        while (i6 < this.zzi) {
            int i7 = this.zzh[i6];
            int i8 = this.zzc[i7];
            int zzq = zzq(i7);
            int i9 = this.zzc[i7 + 2];
            int i10 = i9 & 1048575;
            int i11 = 1 << (i9 >>> 20);
            if (i10 != i4) {
                if (i10 != 1048575) {
                    i5 = zzb.getInt(obj, i10);
                }
                i3 = i5;
                i2 = i10;
            } else {
                i2 = i4;
                i3 = i5;
            }
            if ((268435456 & zzq) != 0 && !zzB(obj, i7, i2, i3, i11)) {
                return false;
            }
            int zzp = zzp(zzq);
            if (zzp != 9 && zzp != 17) {
                if (zzp != 27) {
                    if (zzp == 60 || zzp == 68) {
                        if (zzD(obj, i8, i7) && !zzC(obj, zzq, zzs(i7))) {
                            return false;
                        }
                    } else if (zzp != 49) {
                        if (zzp == 50 && !((zzael) zzagd.zzf(obj, zzq & 1048575)).isEmpty()) {
                            zzaek zzaekVar = (zzaek) zzt(i7);
                            throw null;
                        }
                    }
                }
                List list = (List) zzagd.zzf(obj, zzq & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzafc zzs = zzs(i7);
                    for (int i12 = 0; i12 < list.size(); i12++) {
                        if (!zzs.zzf(list.get(i12))) {
                            return false;
                        }
                    }
                    continue;
                }
            } else if (zzB(obj, i7, i2, i3, i11) && !zzC(obj, zzq, zzs(i7))) {
                return false;
            }
            i6++;
            i4 = i2;
            i5 = i3;
        }
        if (this.zzf) {
            this.zzm.zza(obj);
            throw null;
        }
        return true;
    }

    @Override // com.google.android.libraries.places.internal.zzafc
    public final void zzi(Object obj, zzacy zzacyVar) {
        double zza2;
        float zzb2;
        long zzd;
        long zzd2;
        int zzc;
        long zzd3;
        int zzc2;
        boolean zzw;
        int zzc3;
        int zzc4;
        int zzc5;
        long zzd4;
        int zzc6;
        long zzd5;
        if (!this.zzg) {
            zzF(obj, zzacyVar);
        } else if (this.zzf) {
            this.zzm.zza(obj);
            throw null;
        } else {
            int length = this.zzc.length;
            for (int i2 = 0; i2 < length; i2 += 3) {
                int zzq = zzq(i2);
                int i3 = this.zzc[i2];
                switch (zzp(zzq)) {
                    case 0:
                        if (zzA(obj, i2)) {
                            zza2 = zzagd.zza(obj, zzq & 1048575);
                            zzacyVar.zzf(i3, zza2);
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzA(obj, i2)) {
                            zzb2 = zzagd.zzb(obj, zzq & 1048575);
                            zzacyVar.zzn(i3, zzb2);
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzA(obj, i2)) {
                            zzd = zzagd.zzd(obj, zzq & 1048575);
                            zzacyVar.zzs(i3, zzd);
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzA(obj, i2)) {
                            zzd2 = zzagd.zzd(obj, zzq & 1048575);
                            zzacyVar.zzH(i3, zzd2);
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzA(obj, i2)) {
                            zzc = zzagd.zzc(obj, zzq & 1048575);
                            zzacyVar.zzq(i3, zzc);
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzA(obj, i2)) {
                            zzd3 = zzagd.zzd(obj, zzq & 1048575);
                            zzacyVar.zzl(i3, zzd3);
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzA(obj, i2)) {
                            zzc2 = zzagd.zzc(obj, zzq & 1048575);
                            zzacyVar.zzj(i3, zzc2);
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzA(obj, i2)) {
                            zzw = zzagd.zzw(obj, zzq & 1048575);
                            zzacyVar.zzb(i3, zzw);
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (!zzA(obj, i2)) {
                            break;
                        }
                        zzH(i3, zzagd.zzf(obj, zzq & 1048575), zzacyVar);
                        break;
                    case 9:
                        if (!zzA(obj, i2)) {
                            break;
                        }
                        zzacyVar.zzu(i3, zzagd.zzf(obj, zzq & 1048575), zzs(i2));
                        break;
                    case 10:
                        if (!zzA(obj, i2)) {
                            break;
                        }
                        zzacyVar.zzd(i3, (zzacp) zzagd.zzf(obj, zzq & 1048575));
                        break;
                    case 11:
                        if (zzA(obj, i2)) {
                            zzc3 = zzagd.zzc(obj, zzq & 1048575);
                            zzacyVar.zzF(i3, zzc3);
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzA(obj, i2)) {
                            zzc4 = zzagd.zzc(obj, zzq & 1048575);
                            zzacyVar.zzh(i3, zzc4);
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzA(obj, i2)) {
                            zzc5 = zzagd.zzc(obj, zzq & 1048575);
                            zzacyVar.zzv(i3, zzc5);
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzA(obj, i2)) {
                            zzd4 = zzagd.zzd(obj, zzq & 1048575);
                            zzacyVar.zzx(i3, zzd4);
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzA(obj, i2)) {
                            zzc6 = zzagd.zzc(obj, zzq & 1048575);
                            zzacyVar.zzz(i3, zzc6);
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzA(obj, i2)) {
                            zzd5 = zzagd.zzd(obj, zzq & 1048575);
                            zzacyVar.zzB(i3, zzd5);
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (!zzA(obj, i2)) {
                            break;
                        }
                        zzacyVar.zzp(i3, zzagd.zzf(obj, zzq & 1048575), zzs(i2));
                        break;
                    case 18:
                        zzafe.zzJ(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                        break;
                    case 19:
                        zzafe.zzN(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                        break;
                    case 20:
                        zzafe.zzQ(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                        break;
                    case 21:
                        zzafe.zzY(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                        break;
                    case 22:
                        zzafe.zzP(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                        break;
                    case 23:
                        zzafe.zzM(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                        break;
                    case 24:
                        zzafe.zzL(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                        break;
                    case 25:
                        zzafe.zzH(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                        break;
                    case 26:
                        zzafe.zzW(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar);
                        break;
                    case 27:
                        zzafe.zzR(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, zzs(i2));
                        break;
                    case 28:
                        zzafe.zzI(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar);
                        break;
                    case 29:
                        zzafe.zzX(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                        break;
                    case 30:
                        zzafe.zzK(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                        break;
                    case 31:
                        zzafe.zzS(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                        break;
                    case 32:
                        zzafe.zzT(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                        break;
                    case 33:
                        zzafe.zzU(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                        break;
                    case 34:
                        zzafe.zzV(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, false);
                        break;
                    case 35:
                        zzafe.zzJ(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                        break;
                    case 36:
                        zzafe.zzN(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                        break;
                    case 37:
                        zzafe.zzQ(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                        break;
                    case 38:
                        zzafe.zzY(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                        break;
                    case 39:
                        zzafe.zzP(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                        break;
                    case 40:
                        zzafe.zzM(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                        break;
                    case 41:
                        zzafe.zzL(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                        break;
                    case 42:
                        zzafe.zzH(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                        break;
                    case 43:
                        zzafe.zzX(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                        break;
                    case 44:
                        zzafe.zzK(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                        break;
                    case 45:
                        zzafe.zzS(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                        break;
                    case 46:
                        zzafe.zzT(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                        break;
                    case 47:
                        zzafe.zzU(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                        break;
                    case 48:
                        zzafe.zzV(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, true);
                        break;
                    case 49:
                        zzafe.zzO(i3, (List) zzagd.zzf(obj, zzq & 1048575), zzacyVar, zzs(i2));
                        break;
                    case 50:
                        zzG(zzacyVar, i3, zzagd.zzf(obj, zzq & 1048575), i2);
                        break;
                    case 51:
                        if (zzD(obj, i3, i2)) {
                            zza2 = zzj(obj, zzq & 1048575);
                            zzacyVar.zzf(i3, zza2);
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zzD(obj, i3, i2)) {
                            zzb2 = zzk(obj, zzq & 1048575);
                            zzacyVar.zzn(i3, zzb2);
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zzD(obj, i3, i2)) {
                            zzd = zzr(obj, zzq & 1048575);
                            zzacyVar.zzs(i3, zzd);
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zzD(obj, i3, i2)) {
                            zzd2 = zzr(obj, zzq & 1048575);
                            zzacyVar.zzH(i3, zzd2);
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zzD(obj, i3, i2)) {
                            zzc = zzn(obj, zzq & 1048575);
                            zzacyVar.zzq(i3, zzc);
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zzD(obj, i3, i2)) {
                            zzd3 = zzr(obj, zzq & 1048575);
                            zzacyVar.zzl(i3, zzd3);
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zzD(obj, i3, i2)) {
                            zzc2 = zzn(obj, zzq & 1048575);
                            zzacyVar.zzj(i3, zzc2);
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zzD(obj, i3, i2)) {
                            zzw = zzE(obj, zzq & 1048575);
                            zzacyVar.zzb(i3, zzw);
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (!zzD(obj, i3, i2)) {
                            break;
                        }
                        zzH(i3, zzagd.zzf(obj, zzq & 1048575), zzacyVar);
                        break;
                    case 60:
                        if (!zzD(obj, i3, i2)) {
                            break;
                        }
                        zzacyVar.zzu(i3, zzagd.zzf(obj, zzq & 1048575), zzs(i2));
                        break;
                    case 61:
                        if (!zzD(obj, i3, i2)) {
                            break;
                        }
                        zzacyVar.zzd(i3, (zzacp) zzagd.zzf(obj, zzq & 1048575));
                        break;
                    case 62:
                        if (zzD(obj, i3, i2)) {
                            zzc3 = zzn(obj, zzq & 1048575);
                            zzacyVar.zzF(i3, zzc3);
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zzD(obj, i3, i2)) {
                            zzc4 = zzn(obj, zzq & 1048575);
                            zzacyVar.zzh(i3, zzc4);
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zzD(obj, i3, i2)) {
                            zzc5 = zzn(obj, zzq & 1048575);
                            zzacyVar.zzv(i3, zzc5);
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zzD(obj, i3, i2)) {
                            zzd4 = zzr(obj, zzq & 1048575);
                            zzacyVar.zzx(i3, zzd4);
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zzD(obj, i3, i2)) {
                            zzc6 = zzn(obj, zzq & 1048575);
                            zzacyVar.zzz(i3, zzc6);
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zzD(obj, i3, i2)) {
                            zzd5 = zzr(obj, zzq & 1048575);
                            zzacyVar.zzB(i3, zzd5);
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (!zzD(obj, i3, i2)) {
                            break;
                        }
                        zzacyVar.zzp(i3, zzagd.zzf(obj, zzq & 1048575), zzs(i2));
                        break;
                }
            }
            zzaft zzaftVar = this.zzl;
            zzaftVar.zzg(zzaftVar.zzc(obj), zzacyVar);
        }
    }
}
