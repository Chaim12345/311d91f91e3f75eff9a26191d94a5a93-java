package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import sun.misc.Unsafe;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzfo<T> implements zzgb<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzgz.g();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzfl zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final int[] zzj;
    private final int zzk;
    private final int zzl;
    private final zzez zzm;
    private final zzgp zzn;
    private final zzdo zzo;
    private final zzfr zzp;
    private final zzfg zzq;

    private zzfo(int[] iArr, Object[] objArr, int i2, int i3, zzfl zzflVar, boolean z, boolean z2, int[] iArr2, int i4, int i5, zzfr zzfrVar, zzez zzezVar, zzgp zzgpVar, zzdo zzdoVar, zzfg zzfgVar, byte[] bArr) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i2;
        this.zzf = i3;
        this.zzi = z;
        boolean z3 = false;
        if (zzdoVar != null && zzdoVar.f(zzflVar)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzj = iArr2;
        this.zzk = i4;
        this.zzl = i5;
        this.zzp = zzfrVar;
        this.zzm = zzezVar;
        this.zzn = zzgpVar;
        this.zzo = zzdoVar;
        this.zzg = zzflVar;
        this.zzq = zzfgVar;
    }

    static zzgq b(Object obj) {
        zzec zzecVar = (zzec) obj;
        zzgq zzgqVar = zzecVar.zzc;
        if (zzgqVar == zzgq.zzc()) {
            zzgq b2 = zzgq.b();
            zzecVar.zzc = b2;
            return b2;
        }
        return zzgqVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzfo c(Class cls, zzfi zzfiVar, zzfr zzfrVar, zzez zzezVar, zzgp zzgpVar, zzdo zzdoVar, zzfg zzfgVar) {
        if (zzfiVar instanceof zzfw) {
            return d((zzfw) zzfiVar, zzfrVar, zzezVar, zzgpVar, zzdoVar, zzfgVar);
        }
        zzgm zzgmVar = (zzgm) zzfiVar;
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
    static zzfo d(zzfw zzfwVar, zzfr zzfrVar, zzez zzezVar, zzgp zzgpVar, zzdo zzdoVar, zzfg zzfgVar) {
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
        Field zzG;
        char charAt12;
        int i24;
        int i25;
        int i26;
        Object obj;
        Field zzG2;
        Object obj2;
        Field zzG3;
        int i27;
        char charAt13;
        int i28;
        char charAt14;
        int i29;
        char charAt15;
        int i30;
        char charAt16;
        boolean z = zzfwVar.zzc() == 2;
        String a2 = zzfwVar.a();
        int length = a2.length();
        char c2 = 55296;
        if (a2.charAt(0) >= 55296) {
            int i31 = 1;
            while (true) {
                i2 = i31 + 1;
                if (a2.charAt(i31) < 55296) {
                    break;
                }
                i31 = i2;
            }
        } else {
            i2 = 1;
        }
        int i32 = i2 + 1;
        int charAt17 = a2.charAt(i2);
        if (charAt17 >= 55296) {
            int i33 = charAt17 & 8191;
            int i34 = 13;
            while (true) {
                i30 = i32 + 1;
                charAt16 = a2.charAt(i32);
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
            int charAt18 = a2.charAt(i32);
            if (charAt18 >= 55296) {
                int i36 = charAt18 & 8191;
                int i37 = 13;
                while (true) {
                    i14 = i35 + 1;
                    charAt11 = a2.charAt(i35);
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
            int charAt19 = a2.charAt(i35);
            if (charAt19 >= 55296) {
                int i39 = charAt19 & 8191;
                int i40 = 13;
                while (true) {
                    i13 = i38 + 1;
                    charAt10 = a2.charAt(i38);
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
            charAt = a2.charAt(i38);
            if (charAt >= 55296) {
                int i42 = charAt & 8191;
                int i43 = 13;
                while (true) {
                    i12 = i41 + 1;
                    charAt9 = a2.charAt(i41);
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
            int charAt20 = a2.charAt(i41);
            if (charAt20 >= 55296) {
                int i45 = charAt20 & 8191;
                int i46 = 13;
                while (true) {
                    i11 = i44 + 1;
                    charAt8 = a2.charAt(i44);
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
            charAt2 = a2.charAt(i44);
            if (charAt2 >= 55296) {
                int i48 = charAt2 & 8191;
                int i49 = 13;
                while (true) {
                    i10 = i47 + 1;
                    charAt7 = a2.charAt(i47);
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
            int charAt21 = a2.charAt(i47);
            if (charAt21 >= 55296) {
                int i51 = charAt21 & 8191;
                int i52 = 13;
                while (true) {
                    i9 = i50 + 1;
                    charAt6 = a2.charAt(i50);
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
            int charAt22 = a2.charAt(i50);
            if (charAt22 >= 55296) {
                int i54 = charAt22 & 8191;
                int i55 = 13;
                while (true) {
                    i8 = i53 + 1;
                    charAt5 = a2.charAt(i53);
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
            charAt3 = a2.charAt(i53);
            if (charAt3 >= 55296) {
                int i57 = charAt3 & 8191;
                int i58 = 13;
                while (true) {
                    i7 = i56 + 1;
                    charAt4 = a2.charAt(i56);
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
        Object[] b2 = zzfwVar.b();
        Class<?> cls2 = zzfwVar.zza().getClass();
        int[] iArr3 = new int[charAt2 * 3];
        Object[] objArr2 = new Object[charAt2 + charAt2];
        int i60 = charAt3 + i6;
        int i61 = charAt3;
        int i62 = i60;
        int i63 = 0;
        int i64 = 0;
        while (i32 < length) {
            int i65 = i32 + 1;
            int charAt23 = a2.charAt(i32);
            if (charAt23 >= c2) {
                int i66 = charAt23 & 8191;
                int i67 = i65;
                int i68 = 13;
                while (true) {
                    i29 = i67 + 1;
                    charAt15 = a2.charAt(i67);
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
            int charAt24 = a2.charAt(i15);
            if (charAt24 >= c2) {
                int i70 = charAt24 & 8191;
                int i71 = i69;
                int i72 = 13;
                while (true) {
                    i28 = i71 + 1;
                    charAt14 = a2.charAt(i71);
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
                int charAt25 = a2.charAt(i17);
                if (charAt25 >= 55296) {
                    int i76 = charAt25 & 8191;
                    int i77 = i75;
                    int i78 = 13;
                    while (true) {
                        i27 = i77 + 1;
                        charAt13 = a2.charAt(i77);
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
                    objArr2[i80 + i80 + 1] = b2[i3];
                } else {
                    if (i79 == 12 && !z) {
                        int i81 = i63 / 3;
                        i26 = i3 + 1;
                        objArr2[i81 + i81 + 1] = b2[i3];
                    }
                    int i82 = charAt25 + charAt25;
                    obj = b2[i82];
                    if (obj instanceof Field) {
                        zzG2 = zzG(cls2, (String) obj);
                        b2[i82] = zzG2;
                    } else {
                        zzG2 = (Field) obj;
                    }
                    iArr2 = iArr3;
                    i18 = charAt;
                    int objectFieldOffset2 = (int) unsafe.objectFieldOffset(zzG2);
                    int i83 = i82 + 1;
                    obj2 = b2[i83];
                    if (obj2 instanceof Field) {
                        zzG3 = zzG(cls2, (String) obj2);
                        b2[i83] = zzG3;
                    } else {
                        zzG3 = (Field) obj2;
                    }
                    str = a2;
                    cls = cls2;
                    i21 = (int) unsafe.objectFieldOffset(zzG3);
                    objArr = objArr2;
                    objectFieldOffset = objectFieldOffset2;
                    i23 = 0;
                }
                i3 = i26;
                int i822 = charAt25 + charAt25;
                obj = b2[i822];
                if (obj instanceof Field) {
                }
                iArr2 = iArr3;
                i18 = charAt;
                int objectFieldOffset22 = (int) unsafe.objectFieldOffset(zzG2);
                int i832 = i822 + 1;
                obj2 = b2[i832];
                if (obj2 instanceof Field) {
                }
                str = a2;
                cls = cls2;
                i21 = (int) unsafe.objectFieldOffset(zzG3);
                objArr = objArr2;
                objectFieldOffset = objectFieldOffset22;
                i23 = 0;
            } else {
                iArr2 = iArr3;
                i18 = charAt;
                i19 = i5;
                int i84 = i3 + 1;
                Field zzG4 = zzG(cls2, (String) b2[i3]);
                if (i73 == 9 || i73 == 17) {
                    int i85 = i63 / 3;
                    objArr2[i85 + i85 + 1] = zzG4.getType();
                } else {
                    if (i73 == 27 || i73 == 49) {
                        int i86 = i63 / 3;
                        i24 = i84 + 1;
                        objArr2[i86 + i86 + 1] = b2[i84];
                    } else if (i73 == 12 || i73 == 30 || i73 == 44) {
                        if (!z) {
                            int i87 = i63 / 3;
                            i24 = i84 + 1;
                            objArr2[i87 + i87 + 1] = b2[i84];
                        }
                    } else if (i73 == 50) {
                        int i88 = i61 + 1;
                        iArr[i61] = i63;
                        int i89 = i63 / 3;
                        int i90 = i89 + i89;
                        int i91 = i84 + 1;
                        objArr2[i90] = b2[i84];
                        if ((charAt24 & 2048) != 0) {
                            i84 = i91 + 1;
                            objArr2[i90 + 1] = b2[i91];
                            i61 = i88;
                        } else {
                            i61 = i88;
                            i20 = i91;
                            objectFieldOffset = (int) unsafe.objectFieldOffset(zzG4);
                            objArr = objArr2;
                            if ((charAt24 & 4096) == 4096 || i73 > 17) {
                                str = a2;
                                cls = cls2;
                                i21 = 1048575;
                                i22 = i17;
                                i23 = 0;
                            } else {
                                int i92 = i17 + 1;
                                int charAt26 = a2.charAt(i17);
                                if (charAt26 >= 55296) {
                                    int i93 = charAt26 & 8191;
                                    int i94 = 13;
                                    while (true) {
                                        i22 = i92 + 1;
                                        charAt12 = a2.charAt(i92);
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
                                Object obj3 = b2[i95];
                                str = a2;
                                if (obj3 instanceof Field) {
                                    zzG = (Field) obj3;
                                } else {
                                    zzG = zzG(cls2, (String) obj3);
                                    b2[i95] = zzG;
                                }
                                cls = cls2;
                                i21 = (int) unsafe.objectFieldOffset(zzG);
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
                    objectFieldOffset = (int) unsafe.objectFieldOffset(zzG4);
                    objArr = objArr2;
                    if ((charAt24 & 4096) == 4096) {
                    }
                    str = a2;
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
                objectFieldOffset = (int) unsafe.objectFieldOffset(zzG4);
                objArr = objArr2;
                if ((charAt24 & 4096) == 4096) {
                }
                str = a2;
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
            a2 = str;
            iArr3 = iArr2;
            i5 = i19;
            c2 = 55296;
        }
        return new zzfo(iArr3, objArr2, charAt, i5, zzfwVar.zza(), z, false, iArr, charAt3, i60, zzfrVar, zzezVar, zzgpVar, zzdoVar, zzfgVar, null);
    }

    private static int zzA(int i2) {
        return (i2 >>> 20) & 255;
    }

    private final int zzB(int i2) {
        return this.zzc[i2 + 1];
    }

    private static long zzC(Object obj, long j2) {
        return ((Long) zzgz.f(obj, j2)).longValue();
    }

    private final zzeg zzD(int i2) {
        int i3 = i2 / 3;
        return (zzeg) this.zzd[i3 + i3 + 1];
    }

    private final zzgb zzE(int i2) {
        int i3 = i2 / 3;
        int i4 = i3 + i3;
        zzgb zzgbVar = (zzgb) this.zzd[i4];
        if (zzgbVar != null) {
            return zzgbVar;
        }
        zzgb zzb2 = zzfu.zza().zzb((Class) this.zzd[i4 + 1]);
        this.zzd[i4] = zzb2;
        return zzb2;
    }

    private final Object zzF(int i2) {
        int i3 = i2 / 3;
        return this.zzd[i3 + i3];
    }

    private static Field zzG(Class cls, String str) {
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

    private final void zzH(Object obj, Object obj2, int i2) {
        long zzB = zzB(i2) & 1048575;
        if (zzM(obj2, i2)) {
            Object f2 = zzgz.f(obj, zzB);
            Object f3 = zzgz.f(obj2, zzB);
            if (f2 != null && f3 != null) {
                f3 = zzel.d(f2, f3);
            } else if (f3 == null) {
                return;
            }
            zzgz.s(obj, zzB, f3);
            zzJ(obj, i2);
        }
    }

    private final void zzI(Object obj, Object obj2, int i2) {
        int zzB = zzB(i2);
        int i3 = this.zzc[i2];
        long j2 = zzB & 1048575;
        if (zzP(obj2, i3, i2)) {
            Object f2 = zzP(obj, i3, i2) ? zzgz.f(obj, j2) : null;
            Object f3 = zzgz.f(obj2, j2);
            if (f2 != null && f3 != null) {
                f3 = zzel.d(f2, f3);
            } else if (f3 == null) {
                return;
            }
            zzgz.s(obj, j2, f3);
            zzK(obj, i3, i2);
        }
    }

    private final void zzJ(Object obj, int i2) {
        int zzy = zzy(i2);
        long j2 = 1048575 & zzy;
        if (j2 == 1048575) {
            return;
        }
        zzgz.q(obj, j2, (1 << (zzy >>> 20)) | zzgz.c(obj, j2));
    }

    private final void zzK(Object obj, int i2, int i3) {
        zzgz.q(obj, zzy(i3) & 1048575, i2);
    }

    private final boolean zzL(Object obj, Object obj2, int i2) {
        return zzM(obj, i2) == zzM(obj2, i2);
    }

    private final boolean zzM(Object obj, int i2) {
        int zzy = zzy(i2);
        long j2 = zzy & 1048575;
        if (j2 != 1048575) {
            return (zzgz.c(obj, j2) & (1 << (zzy >>> 20))) != 0;
        }
        int zzB = zzB(i2);
        long j3 = zzB & 1048575;
        switch (zzA(zzB)) {
            case 0:
                return Double.doubleToRawLongBits(zzgz.a(obj, j3)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzgz.b(obj, j3)) != 0;
            case 2:
                return zzgz.d(obj, j3) != 0;
            case 3:
                return zzgz.d(obj, j3) != 0;
            case 4:
                return zzgz.c(obj, j3) != 0;
            case 5:
                return zzgz.d(obj, j3) != 0;
            case 6:
                return zzgz.c(obj, j3) != 0;
            case 7:
                return zzgz.w(obj, j3);
            case 8:
                Object f2 = zzgz.f(obj, j3);
                if (f2 instanceof String) {
                    return !((String) f2).isEmpty();
                } else if (f2 instanceof zzdb) {
                    return !zzdb.zzb.equals(f2);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return zzgz.f(obj, j3) != null;
            case 10:
                return !zzdb.zzb.equals(zzgz.f(obj, j3));
            case 11:
                return zzgz.c(obj, j3) != 0;
            case 12:
                return zzgz.c(obj, j3) != 0;
            case 13:
                return zzgz.c(obj, j3) != 0;
            case 14:
                return zzgz.d(obj, j3) != 0;
            case 15:
                return zzgz.c(obj, j3) != 0;
            case 16:
                return zzgz.d(obj, j3) != 0;
            case 17:
                return zzgz.f(obj, j3) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzN(Object obj, int i2, int i3, int i4, int i5) {
        return i3 == 1048575 ? zzM(obj, i2) : (i4 & i5) != 0;
    }

    private static boolean zzO(Object obj, int i2, zzgb zzgbVar) {
        return zzgbVar.zzj(zzgz.f(obj, i2 & 1048575));
    }

    private final boolean zzP(Object obj, int i2, int i3) {
        return zzgz.c(obj, (long) (zzy(i3) & 1048575)) == i2;
    }

    private static boolean zzQ(Object obj, long j2) {
        return ((Boolean) zzgz.f(obj, j2)).booleanValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0489  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void zzR(Object obj, zzdj zzdjVar) {
        Iterator it;
        Map.Entry entry;
        int length;
        int i2;
        int i3;
        boolean z;
        if (this.zzh) {
            zzds b2 = this.zzo.b(obj);
            if (!b2.f6415a.isEmpty()) {
                it = b2.zzf();
                entry = (Map.Entry) it.next();
                length = this.zzc.length;
                Unsafe unsafe = zzb;
                int i4 = 1048575;
                int i5 = 0;
                for (i2 = 0; i2 < length; i2 += 3) {
                    int zzB = zzB(i2);
                    int[] iArr = this.zzc;
                    int i6 = iArr[i2];
                    int zzA = zzA(zzB);
                    if (zzA <= 17) {
                        int i7 = iArr[i2 + 2];
                        int i8 = i7 & 1048575;
                        if (i8 != i4) {
                            i5 = unsafe.getInt(obj, i8);
                            i4 = i8;
                        }
                        i3 = 1 << (i7 >>> 20);
                    } else {
                        i3 = 0;
                    }
                    while (entry != null && this.zzo.a(entry) <= i6) {
                        this.zzo.g(zzdjVar, entry);
                        entry = it.hasNext() ? (Map.Entry) it.next() : null;
                    }
                    long j2 = zzB & 1048575;
                    switch (zzA) {
                        case 0:
                            if ((i3 & i5) != 0) {
                                zzdjVar.zzf(i6, zzgz.a(obj, j2));
                                break;
                            } else {
                                break;
                            }
                        case 1:
                            if ((i3 & i5) != 0) {
                                zzdjVar.zzo(i6, zzgz.b(obj, j2));
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if ((i3 & i5) != 0) {
                                zzdjVar.zzt(i6, unsafe.getLong(obj, j2));
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if ((i3 & i5) != 0) {
                                zzdjVar.zzK(i6, unsafe.getLong(obj, j2));
                                break;
                            } else {
                                break;
                            }
                        case 4:
                            if ((i3 & i5) != 0) {
                                zzdjVar.zzr(i6, unsafe.getInt(obj, j2));
                                break;
                            } else {
                                break;
                            }
                        case 5:
                            if ((i3 & i5) != 0) {
                                zzdjVar.zzm(i6, unsafe.getLong(obj, j2));
                                break;
                            } else {
                                break;
                            }
                        case 6:
                            if ((i3 & i5) != 0) {
                                zzdjVar.zzk(i6, unsafe.getInt(obj, j2));
                                break;
                            } else {
                                break;
                            }
                        case 7:
                            if ((i3 & i5) != 0) {
                                zzdjVar.zzb(i6, zzgz.w(obj, j2));
                                break;
                            } else {
                                break;
                            }
                        case 8:
                            if ((i3 & i5) != 0) {
                                zzT(i6, unsafe.getObject(obj, j2), zzdjVar);
                                break;
                            } else {
                                break;
                            }
                        case 9:
                            if ((i3 & i5) != 0) {
                                zzdjVar.zzv(i6, unsafe.getObject(obj, j2), zzE(i2));
                                break;
                            } else {
                                break;
                            }
                        case 10:
                            if ((i3 & i5) != 0) {
                                zzdjVar.zzd(i6, (zzdb) unsafe.getObject(obj, j2));
                                break;
                            } else {
                                break;
                            }
                        case 11:
                            if ((i3 & i5) != 0) {
                                zzdjVar.zzI(i6, unsafe.getInt(obj, j2));
                                break;
                            } else {
                                break;
                            }
                        case 12:
                            if ((i3 & i5) != 0) {
                                zzdjVar.zzi(i6, unsafe.getInt(obj, j2));
                                break;
                            } else {
                                break;
                            }
                        case 13:
                            if ((i3 & i5) != 0) {
                                zzdjVar.zzx(i6, unsafe.getInt(obj, j2));
                                break;
                            } else {
                                break;
                            }
                        case 14:
                            if ((i3 & i5) != 0) {
                                zzdjVar.zzz(i6, unsafe.getLong(obj, j2));
                                break;
                            } else {
                                break;
                            }
                        case 15:
                            if ((i3 & i5) != 0) {
                                zzdjVar.zzB(i6, unsafe.getInt(obj, j2));
                                break;
                            } else {
                                break;
                            }
                        case 16:
                            if ((i3 & i5) != 0) {
                                zzdjVar.zzD(i6, unsafe.getLong(obj, j2));
                                break;
                            } else {
                                break;
                            }
                        case 17:
                            if ((i3 & i5) != 0) {
                                zzdjVar.zzq(i6, unsafe.getObject(obj, j2), zzE(i2));
                                break;
                            } else {
                                break;
                            }
                        case 18:
                            z = false;
                            zzgd.zzL(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, false);
                            break;
                        case 19:
                            z = false;
                            zzgd.zzP(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, false);
                            break;
                        case 20:
                            z = false;
                            zzgd.zzS(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, false);
                            break;
                        case 21:
                            z = false;
                            zzgd.zzaa(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, false);
                            break;
                        case 22:
                            z = false;
                            zzgd.zzR(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, false);
                            break;
                        case 23:
                            z = false;
                            zzgd.zzO(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, false);
                            break;
                        case 24:
                            z = false;
                            zzgd.zzN(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, false);
                            break;
                        case 25:
                            z = false;
                            zzgd.zzJ(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, false);
                            break;
                        case 26:
                            zzgd.zzY(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar);
                            break;
                        case 27:
                            zzgd.zzT(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, zzE(i2));
                            break;
                        case 28:
                            zzgd.zzK(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar);
                            break;
                        case 29:
                            z = false;
                            zzgd.zzZ(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, false);
                            break;
                        case 30:
                            z = false;
                            zzgd.zzM(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, false);
                            break;
                        case 31:
                            z = false;
                            zzgd.zzU(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, false);
                            break;
                        case 32:
                            z = false;
                            zzgd.zzV(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, false);
                            break;
                        case 33:
                            z = false;
                            zzgd.zzW(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, false);
                            break;
                        case 34:
                            z = false;
                            zzgd.zzX(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, false);
                            break;
                        case 35:
                            zzgd.zzL(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, true);
                            break;
                        case 36:
                            zzgd.zzP(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, true);
                            break;
                        case 37:
                            zzgd.zzS(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, true);
                            break;
                        case 38:
                            zzgd.zzaa(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, true);
                            break;
                        case 39:
                            zzgd.zzR(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, true);
                            break;
                        case 40:
                            zzgd.zzO(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, true);
                            break;
                        case 41:
                            zzgd.zzN(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, true);
                            break;
                        case 42:
                            zzgd.zzJ(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, true);
                            break;
                        case 43:
                            zzgd.zzZ(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, true);
                            break;
                        case 44:
                            zzgd.zzM(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, true);
                            break;
                        case 45:
                            zzgd.zzU(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, true);
                            break;
                        case 46:
                            zzgd.zzV(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, true);
                            break;
                        case 47:
                            zzgd.zzW(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, true);
                            break;
                        case 48:
                            zzgd.zzX(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, true);
                            break;
                        case 49:
                            zzgd.zzQ(this.zzc[i2], (List) unsafe.getObject(obj, j2), zzdjVar, zzE(i2));
                            break;
                        case 50:
                            zzS(zzdjVar, i6, unsafe.getObject(obj, j2), i2);
                            break;
                        case 51:
                            if (zzP(obj, i6, i2)) {
                                zzdjVar.zzf(i6, zzn(obj, j2));
                            }
                            break;
                        case 52:
                            if (zzP(obj, i6, i2)) {
                                zzdjVar.zzo(i6, zzo(obj, j2));
                            }
                            break;
                        case 53:
                            if (zzP(obj, i6, i2)) {
                                zzdjVar.zzt(i6, zzC(obj, j2));
                            }
                            break;
                        case 54:
                            if (zzP(obj, i6, i2)) {
                                zzdjVar.zzK(i6, zzC(obj, j2));
                            }
                            break;
                        case 55:
                            if (zzP(obj, i6, i2)) {
                                zzdjVar.zzr(i6, zzr(obj, j2));
                            }
                            break;
                        case 56:
                            if (zzP(obj, i6, i2)) {
                                zzdjVar.zzm(i6, zzC(obj, j2));
                            }
                            break;
                        case 57:
                            if (zzP(obj, i6, i2)) {
                                zzdjVar.zzk(i6, zzr(obj, j2));
                            }
                            break;
                        case 58:
                            if (zzP(obj, i6, i2)) {
                                zzdjVar.zzb(i6, zzQ(obj, j2));
                            }
                            break;
                        case 59:
                            if (zzP(obj, i6, i2)) {
                                zzT(i6, unsafe.getObject(obj, j2), zzdjVar);
                            }
                            break;
                        case 60:
                            if (zzP(obj, i6, i2)) {
                                zzdjVar.zzv(i6, unsafe.getObject(obj, j2), zzE(i2));
                            }
                            break;
                        case 61:
                            if (zzP(obj, i6, i2)) {
                                zzdjVar.zzd(i6, (zzdb) unsafe.getObject(obj, j2));
                            }
                            break;
                        case 62:
                            if (zzP(obj, i6, i2)) {
                                zzdjVar.zzI(i6, zzr(obj, j2));
                            }
                            break;
                        case 63:
                            if (zzP(obj, i6, i2)) {
                                zzdjVar.zzi(i6, zzr(obj, j2));
                            }
                            break;
                        case 64:
                            if (zzP(obj, i6, i2)) {
                                zzdjVar.zzx(i6, zzr(obj, j2));
                            }
                            break;
                        case 65:
                            if (zzP(obj, i6, i2)) {
                                zzdjVar.zzz(i6, zzC(obj, j2));
                            }
                            break;
                        case 66:
                            if (zzP(obj, i6, i2)) {
                                zzdjVar.zzB(i6, zzr(obj, j2));
                            }
                            break;
                        case 67:
                            if (zzP(obj, i6, i2)) {
                                zzdjVar.zzD(i6, zzC(obj, j2));
                            }
                            break;
                        case 68:
                            if (zzP(obj, i6, i2)) {
                                zzdjVar.zzq(i6, unsafe.getObject(obj, j2), zzE(i2));
                            }
                            break;
                    }
                }
                while (entry != null) {
                    this.zzo.g(zzdjVar, entry);
                    entry = it.hasNext() ? (Map.Entry) it.next() : null;
                }
                zzgp zzgpVar = this.zzn;
                zzgpVar.j(zzgpVar.c(obj), zzdjVar);
            }
        }
        it = null;
        entry = null;
        length = this.zzc.length;
        Unsafe unsafe2 = zzb;
        int i42 = 1048575;
        int i52 = 0;
        while (i2 < length) {
        }
        while (entry != null) {
        }
        zzgp zzgpVar2 = this.zzn;
        zzgpVar2.j(zzgpVar2.c(obj), zzdjVar);
    }

    private final void zzS(zzdj zzdjVar, int i2, Object obj, int i3) {
        if (obj == null) {
            return;
        }
        zzfe zzfeVar = (zzfe) zzF(i3);
        throw null;
    }

    private static final void zzT(int i2, Object obj, zzdj zzdjVar) {
        if (obj instanceof String) {
            zzdjVar.zzG(i2, (String) obj);
        } else {
            zzdjVar.zzd(i2, (zzdb) obj);
        }
    }

    private static double zzn(Object obj, long j2) {
        return ((Double) zzgz.f(obj, j2)).doubleValue();
    }

    private static float zzo(Object obj, long j2) {
        return ((Float) zzgz.f(obj, j2)).floatValue();
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
        r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi.zzC(r11) + com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi.zzD(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x008c, code lost:
        if (zzP(r17, r11, r5) != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0094, code lost:
        if (zzP(r17, r11, r5) != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x009c, code lost:
        if (zzP(r17, r11, r5) != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0118, code lost:
        if (zzP(r17, r11, r5) != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x011f, code lost:
        if (zzP(r17, r11, r5) != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0126, code lost:
        if (zzP(r17, r11, r5) != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0128, code lost:
        r3 = zzr(r17, r3);
        r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi.zzD(r11 << 3);
        r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi.zzx(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x013c, code lost:
        if (zzP(r17, r11, r5) != false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0143, code lost:
        if (zzP(r17, r11, r5) != false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0145, code lost:
        r6 = r6 + (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi.zzD(r11 << 3) + com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi.zzE(zzC(r17, r3)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x015b, code lost:
        if (zzP(r17, r11, r5) != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x015d, code lost:
        r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi.zzD(r11 << 3) + 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x016b, code lost:
        if (zzP(r17, r11, r5) != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x016d, code lost:
        r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi.zzD(r11 << 3) + 8;
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
    private final int zzp(Object obj) {
        int i2;
        int zzD;
        Object object;
        int zzD2;
        int zzB;
        int zzD3;
        int i3;
        int A;
        boolean z;
        int j2;
        int o2;
        int zzC;
        Object object2;
        int zzD4;
        int zzr;
        Unsafe unsafe = zzb;
        int i4 = 1048575;
        int i5 = 1048575;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i6 < this.zzc.length) {
            int zzB2 = zzB(i6);
            int[] iArr = this.zzc;
            int i9 = iArr[i6];
            int zzA = zzA(zzB2);
            if (zzA <= 17) {
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
            long j3 = zzB2 & i4;
            switch (zzA) {
                case 0:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    zzD = zzdi.zzD(i9 << 3) + 8;
                    i7 += zzD;
                    break;
                case 1:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    zzD = zzdi.zzD(i9 << 3) + 4;
                    i7 += zzD;
                    break;
                case 2:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    i7 += zzdi.zzD(i9 << 3) + zzdi.zzE(unsafe.getLong(obj, j3));
                    break;
                case 3:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    i7 += zzdi.zzD(i9 << 3) + zzdi.zzE(unsafe.getLong(obj, j3));
                    break;
                case 4:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    int i12 = unsafe.getInt(obj, j3);
                    zzD2 = zzdi.zzD(i9 << 3);
                    zzB = zzdi.zzx(i12);
                    zzD3 = zzD2 + zzB;
                    i7 += zzD3;
                    break;
                case 5:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    zzD = zzdi.zzD(i9 << 3) + 8;
                    i7 += zzD;
                    break;
                case 6:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    zzD = zzdi.zzD(i9 << 3) + 4;
                    i7 += zzD;
                    break;
                case 7:
                    if ((i8 & i2) != 0) {
                        zzD = zzdi.zzD(i9 << 3) + 1;
                        i7 += zzD;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        object = unsafe.getObject(obj, j3);
                        if (!(object instanceof zzdb)) {
                            zzD2 = zzdi.zzD(i9 << 3);
                            zzB = zzdi.zzB((String) object);
                            zzD3 = zzD2 + zzB;
                            i7 += zzD3;
                            break;
                        }
                        int zzD5 = zzdi.zzD(i9 << 3);
                        int zzd = ((zzdb) object).zzd();
                        zzD3 = zzD5 + zzdi.zzD(zzd) + zzd;
                        i7 += zzD3;
                    }
                case 9:
                    if ((i8 & i2) != 0) {
                        zzD = zzgd.u(i9, unsafe.getObject(obj, j3), zzE(i6));
                        i7 += zzD;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if ((i8 & i2) != 0) {
                        object = unsafe.getObject(obj, j3);
                        int zzD52 = zzdi.zzD(i9 << 3);
                        int zzd2 = ((zzdb) object).zzd();
                        zzD3 = zzD52 + zzdi.zzD(zzd2) + zzd2;
                        i7 += zzD3;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if ((i8 & i2) != 0) {
                        i3 = unsafe.getInt(obj, j3);
                        zzD2 = zzdi.zzD(i9 << 3);
                        zzB = zzdi.zzD(i3);
                        zzD3 = zzD2 + zzB;
                        i7 += zzD3;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    int i122 = unsafe.getInt(obj, j3);
                    zzD2 = zzdi.zzD(i9 << 3);
                    zzB = zzdi.zzx(i122);
                    zzD3 = zzD2 + zzB;
                    i7 += zzD3;
                    break;
                case 13:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    zzD = zzdi.zzD(i9 << 3) + 4;
                    i7 += zzD;
                    break;
                case 14:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    zzD = zzdi.zzD(i9 << 3) + 8;
                    i7 += zzD;
                    break;
                case 15:
                    if ((i8 & i2) != 0) {
                        int i13 = unsafe.getInt(obj, j3);
                        zzD2 = zzdi.zzD(i9 << 3);
                        i3 = (i13 >> 31) ^ (i13 + i13);
                        zzB = zzdi.zzD(i3);
                        zzD3 = zzD2 + zzB;
                        i7 += zzD3;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if ((i2 & i8) != 0) {
                        long j4 = unsafe.getLong(obj, j3);
                        i7 += zzdi.zzD(i9 << 3) + zzdi.zzE((j4 >> 63) ^ (j4 + j4));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if ((i8 & i2) != 0) {
                        zzD = zzdi.d(i9, (zzfl) unsafe.getObject(obj, j3), zzE(i6));
                        i7 += zzD;
                        break;
                    } else {
                        break;
                    }
                case 18:
                case 23:
                    zzD = zzgd.n(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += zzD;
                    break;
                case 19:
                case 24:
                    zzD = zzgd.l(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += zzD;
                    break;
                case 20:
                    zzD = zzgd.s(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += zzD;
                    break;
                case 21:
                    zzD = zzgd.D(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += zzD;
                    break;
                case 22:
                    zzD = zzgd.q(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += zzD;
                    break;
                case 25:
                    zzD = zzgd.g(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += zzD;
                    break;
                case 26:
                    A = zzgd.A(i9, (List) unsafe.getObject(obj, j3));
                    i7 += A;
                    break;
                case 27:
                    A = zzgd.v(i9, (List) unsafe.getObject(obj, j3), zzE(i6));
                    i7 += A;
                    break;
                case 28:
                    A = zzgd.i(i9, (List) unsafe.getObject(obj, j3));
                    i7 += A;
                    break;
                case 29:
                    A = zzgd.B(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += A;
                    break;
                case 30:
                    z = false;
                    j2 = zzgd.j(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += j2;
                    break;
                case 31:
                    z = false;
                    j2 = zzgd.l(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += j2;
                    break;
                case 32:
                    z = false;
                    j2 = zzgd.n(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += j2;
                    break;
                case 33:
                    z = false;
                    j2 = zzgd.w(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += j2;
                    break;
                case 34:
                    z = false;
                    j2 = zzgd.y(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += j2;
                    break;
                case 35:
                    o2 = zzgd.o((List) unsafe.getObject(obj, j3));
                    break;
                case 36:
                    o2 = zzgd.m((List) unsafe.getObject(obj, j3));
                    break;
                case 37:
                    o2 = zzgd.t((List) unsafe.getObject(obj, j3));
                    break;
                case 38:
                    o2 = zzgd.E((List) unsafe.getObject(obj, j3));
                    break;
                case 39:
                    o2 = zzgd.r((List) unsafe.getObject(obj, j3));
                    break;
                case 40:
                    o2 = zzgd.o((List) unsafe.getObject(obj, j3));
                    break;
                case 41:
                    o2 = zzgd.m((List) unsafe.getObject(obj, j3));
                    break;
                case 42:
                    o2 = zzgd.h((List) unsafe.getObject(obj, j3));
                    break;
                case 43:
                    o2 = zzgd.C((List) unsafe.getObject(obj, j3));
                    break;
                case 44:
                    o2 = zzgd.k((List) unsafe.getObject(obj, j3));
                    break;
                case 45:
                    o2 = zzgd.m((List) unsafe.getObject(obj, j3));
                    break;
                case 46:
                    o2 = zzgd.o((List) unsafe.getObject(obj, j3));
                    break;
                case 47:
                    o2 = zzgd.x((List) unsafe.getObject(obj, j3));
                    break;
                case 48:
                    o2 = zzgd.z((List) unsafe.getObject(obj, j3));
                    break;
                case 49:
                    A = zzgd.p(i9, (List) unsafe.getObject(obj, j3), zzE(i6));
                    i7 += A;
                    break;
                case 50:
                    zzfg.zza(i9, unsafe.getObject(obj, j3), zzF(i6));
                    break;
                case 58:
                    if (zzP(obj, i9, i6)) {
                        A = zzdi.zzD(i9 << 3) + 1;
                        i7 += A;
                    }
                    break;
                case 59:
                    if (zzP(obj, i9, i6)) {
                        object2 = unsafe.getObject(obj, j3);
                        if (!(object2 instanceof zzdb)) {
                            zzC = zzdi.zzD(i9 << 3);
                            o2 = zzdi.zzB((String) object2);
                            zzD4 = zzC + o2;
                            i7 += zzD4;
                        }
                        int zzD6 = zzdi.zzD(i9 << 3);
                        int zzd3 = ((zzdb) object2).zzd();
                        zzD4 = zzD6 + zzdi.zzD(zzd3) + zzd3;
                        i7 += zzD4;
                    }
                    break;
                case 60:
                    if (zzP(obj, i9, i6)) {
                        A = zzgd.u(i9, unsafe.getObject(obj, j3), zzE(i6));
                        i7 += A;
                    }
                    break;
                case 61:
                    if (zzP(obj, i9, i6)) {
                        object2 = unsafe.getObject(obj, j3);
                        int zzD62 = zzdi.zzD(i9 << 3);
                        int zzd32 = ((zzdb) object2).zzd();
                        zzD4 = zzD62 + zzdi.zzD(zzd32) + zzd32;
                        i7 += zzD4;
                    }
                    break;
                case 62:
                    if (zzP(obj, i9, i6)) {
                        zzr = zzr(obj, j3);
                        zzC = zzdi.zzD(i9 << 3);
                        o2 = zzdi.zzD(zzr);
                        zzD4 = zzC + o2;
                        i7 += zzD4;
                    }
                    break;
                case 66:
                    if (zzP(obj, i9, i6)) {
                        int zzr2 = zzr(obj, j3);
                        zzC = zzdi.zzD(i9 << 3);
                        zzr = (zzr2 >> 31) ^ (zzr2 + zzr2);
                        o2 = zzdi.zzD(zzr);
                        zzD4 = zzC + o2;
                        i7 += zzD4;
                    }
                    break;
                case 67:
                    if (zzP(obj, i9, i6)) {
                        long zzC2 = zzC(obj, j3);
                        i7 += zzdi.zzD(i9 << 3) + zzdi.zzE((zzC2 >> 63) ^ (zzC2 + zzC2));
                    }
                    break;
                case 68:
                    if (zzP(obj, i9, i6)) {
                        A = zzdi.d(i9, (zzfl) unsafe.getObject(obj, j3), zzE(i6));
                        i7 += A;
                    }
                    break;
            }
            i6 += 3;
            i4 = 1048575;
        }
        int i14 = 0;
        zzgp zzgpVar = this.zzn;
        int a2 = i7 + zzgpVar.a(zzgpVar.c(obj));
        if (this.zzh) {
            zzds b2 = this.zzo.b(obj);
            for (int i15 = 0; i15 < b2.f6415a.zzb(); i15++) {
                Map.Entry zzg = b2.f6415a.zzg(i15);
                i14 += zzds.zza((zzdr) zzg.getKey(), zzg.getValue());
            }
            for (Map.Entry entry : b2.f6415a.zzc()) {
                i14 += zzds.zza((zzdr) entry.getKey(), entry.getValue());
            }
            return a2 + i14;
        }
        return a2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:162:0x030e, code lost:
        if ((r4 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb) != false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0311, code lost:
        r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi.zzD(r6 << 3);
        r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi.zzB((java.lang.String) r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0097, code lost:
        if ((r4 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb) != false) goto L53;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int zzq(Object obj) {
        long d2;
        Object f2;
        int c2;
        int c3;
        int c4;
        long d3;
        int n2;
        int o2;
        int zzC;
        int zzD;
        Unsafe unsafe = zzb;
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzc.length; i3 += 3) {
            int zzB = zzB(i3);
            int zzA = zzA(zzB);
            int i4 = this.zzc[i3];
            long j2 = zzB & 1048575;
            if (zzA >= zzdt.zzJ.zza() && zzA <= zzdt.zzW.zza()) {
                int i5 = this.zzc[i3 + 2];
            }
            switch (zzA) {
                case 0:
                    if (!zzM(obj, i3)) {
                        continue;
                    }
                    n2 = zzdi.zzD(i4 << 3) + 8;
                    break;
                case 1:
                    if (!zzM(obj, i3)) {
                        continue;
                    }
                    n2 = zzdi.zzD(i4 << 3) + 4;
                    break;
                case 2:
                    if (!zzM(obj, i3)) {
                        continue;
                    }
                    d2 = zzgz.d(obj, j2);
                    i2 += zzdi.zzD(i4 << 3) + zzdi.zzE(d2);
                case 3:
                    if (!zzM(obj, i3)) {
                        continue;
                    }
                    d2 = zzgz.d(obj, j2);
                    i2 += zzdi.zzD(i4 << 3) + zzdi.zzE(d2);
                case 4:
                    if (!zzM(obj, i3)) {
                        continue;
                    }
                    c3 = zzgz.c(obj, j2);
                    zzC = zzdi.zzD(i4 << 3);
                    o2 = zzdi.zzx(c3);
                    zzD = zzC + o2;
                    i2 += zzD;
                case 5:
                    if (!zzM(obj, i3)) {
                        continue;
                    }
                    n2 = zzdi.zzD(i4 << 3) + 8;
                    break;
                case 6:
                    if (!zzM(obj, i3)) {
                        continue;
                    }
                    n2 = zzdi.zzD(i4 << 3) + 4;
                    break;
                case 7:
                    if (!zzM(obj, i3)) {
                        continue;
                    }
                    n2 = zzdi.zzD(i4 << 3) + 1;
                    break;
                case 8:
                    if (zzM(obj, i3)) {
                        f2 = zzgz.f(obj, j2);
                        break;
                    } else {
                        continue;
                    }
                case 9:
                    if (!zzM(obj, i3)) {
                        continue;
                    }
                    n2 = zzgd.u(i4, zzgz.f(obj, j2), zzE(i3));
                    break;
                case 10:
                    if (!zzM(obj, i3)) {
                        continue;
                    }
                    f2 = zzgz.f(obj, j2);
                    int zzD2 = zzdi.zzD(i4 << 3);
                    int zzd = ((zzdb) f2).zzd();
                    zzD = zzD2 + zzdi.zzD(zzd) + zzd;
                    i2 += zzD;
                case 11:
                    if (zzM(obj, i3)) {
                        c2 = zzgz.c(obj, j2);
                        zzC = zzdi.zzD(i4 << 3);
                        o2 = zzdi.zzD(c2);
                        zzD = zzC + o2;
                        i2 += zzD;
                    } else {
                        continue;
                    }
                case 12:
                    if (!zzM(obj, i3)) {
                        continue;
                    }
                    c3 = zzgz.c(obj, j2);
                    zzC = zzdi.zzD(i4 << 3);
                    o2 = zzdi.zzx(c3);
                    zzD = zzC + o2;
                    i2 += zzD;
                case 13:
                    if (!zzM(obj, i3)) {
                        continue;
                    }
                    n2 = zzdi.zzD(i4 << 3) + 4;
                    break;
                case 14:
                    if (!zzM(obj, i3)) {
                        continue;
                    }
                    n2 = zzdi.zzD(i4 << 3) + 8;
                    break;
                case 15:
                    if (zzM(obj, i3)) {
                        c4 = zzgz.c(obj, j2);
                        zzC = zzdi.zzD(i4 << 3);
                        c2 = (c4 >> 31) ^ (c4 + c4);
                        o2 = zzdi.zzD(c2);
                        zzD = zzC + o2;
                        i2 += zzD;
                    } else {
                        continue;
                    }
                case 16:
                    if (zzM(obj, i3)) {
                        d3 = zzgz.d(obj, j2);
                        zzC = zzdi.zzD(i4 << 3);
                        o2 = zzdi.zzE((d3 >> 63) ^ (d3 + d3));
                        zzD = zzC + o2;
                        i2 += zzD;
                    } else {
                        continue;
                    }
                case 17:
                    if (!zzM(obj, i3)) {
                        continue;
                    }
                    n2 = zzdi.d(i4, (zzfl) zzgz.f(obj, j2), zzE(i3));
                    break;
                case 18:
                case 23:
                case 32:
                    n2 = zzgd.n(i4, (List) zzgz.f(obj, j2), false);
                    break;
                case 19:
                case 24:
                case 31:
                    n2 = zzgd.l(i4, (List) zzgz.f(obj, j2), false);
                    break;
                case 20:
                    n2 = zzgd.s(i4, (List) zzgz.f(obj, j2), false);
                    break;
                case 21:
                    n2 = zzgd.D(i4, (List) zzgz.f(obj, j2), false);
                    break;
                case 22:
                    n2 = zzgd.q(i4, (List) zzgz.f(obj, j2), false);
                    break;
                case 25:
                    n2 = zzgd.g(i4, (List) zzgz.f(obj, j2), false);
                    break;
                case 26:
                    n2 = zzgd.A(i4, (List) zzgz.f(obj, j2));
                    break;
                case 27:
                    n2 = zzgd.v(i4, (List) zzgz.f(obj, j2), zzE(i3));
                    break;
                case 28:
                    n2 = zzgd.i(i4, (List) zzgz.f(obj, j2));
                    break;
                case 29:
                    n2 = zzgd.B(i4, (List) zzgz.f(obj, j2), false);
                    break;
                case 30:
                    n2 = zzgd.j(i4, (List) zzgz.f(obj, j2), false);
                    break;
                case 33:
                    n2 = zzgd.w(i4, (List) zzgz.f(obj, j2), false);
                    break;
                case 34:
                    n2 = zzgd.y(i4, (List) zzgz.f(obj, j2), false);
                    break;
                case 35:
                    o2 = zzgd.o((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzC = zzdi.zzC(i4) + zzdi.zzD(o2);
                    zzD = zzC + o2;
                    i2 += zzD;
                case 36:
                    o2 = zzgd.m((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzC = zzdi.zzC(i4) + zzdi.zzD(o2);
                    zzD = zzC + o2;
                    i2 += zzD;
                case 37:
                    o2 = zzgd.t((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzC = zzdi.zzC(i4) + zzdi.zzD(o2);
                    zzD = zzC + o2;
                    i2 += zzD;
                case 38:
                    o2 = zzgd.E((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzC = zzdi.zzC(i4) + zzdi.zzD(o2);
                    zzD = zzC + o2;
                    i2 += zzD;
                case 39:
                    o2 = zzgd.r((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzC = zzdi.zzC(i4) + zzdi.zzD(o2);
                    zzD = zzC + o2;
                    i2 += zzD;
                case 40:
                    o2 = zzgd.o((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzC = zzdi.zzC(i4) + zzdi.zzD(o2);
                    zzD = zzC + o2;
                    i2 += zzD;
                case 41:
                    o2 = zzgd.m((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzC = zzdi.zzC(i4) + zzdi.zzD(o2);
                    zzD = zzC + o2;
                    i2 += zzD;
                case 42:
                    o2 = zzgd.h((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzC = zzdi.zzC(i4) + zzdi.zzD(o2);
                    zzD = zzC + o2;
                    i2 += zzD;
                case 43:
                    o2 = zzgd.C((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzC = zzdi.zzC(i4) + zzdi.zzD(o2);
                    zzD = zzC + o2;
                    i2 += zzD;
                case 44:
                    o2 = zzgd.k((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzC = zzdi.zzC(i4) + zzdi.zzD(o2);
                    zzD = zzC + o2;
                    i2 += zzD;
                case 45:
                    o2 = zzgd.m((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzC = zzdi.zzC(i4) + zzdi.zzD(o2);
                    zzD = zzC + o2;
                    i2 += zzD;
                case 46:
                    o2 = zzgd.o((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzC = zzdi.zzC(i4) + zzdi.zzD(o2);
                    zzD = zzC + o2;
                    i2 += zzD;
                case 47:
                    o2 = zzgd.x((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzC = zzdi.zzC(i4) + zzdi.zzD(o2);
                    zzD = zzC + o2;
                    i2 += zzD;
                case 48:
                    o2 = zzgd.z((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzC = zzdi.zzC(i4) + zzdi.zzD(o2);
                    zzD = zzC + o2;
                    i2 += zzD;
                case 49:
                    n2 = zzgd.p(i4, (List) zzgz.f(obj, j2), zzE(i3));
                    break;
                case 50:
                    zzfg.zza(i4, zzgz.f(obj, j2), zzF(i3));
                    continue;
                case 51:
                    if (!zzP(obj, i4, i3)) {
                        continue;
                    }
                    n2 = zzdi.zzD(i4 << 3) + 8;
                    break;
                case 52:
                    if (!zzP(obj, i4, i3)) {
                        continue;
                    }
                    n2 = zzdi.zzD(i4 << 3) + 4;
                    break;
                case 53:
                    if (!zzP(obj, i4, i3)) {
                        continue;
                    }
                    d2 = zzC(obj, j2);
                    i2 += zzdi.zzD(i4 << 3) + zzdi.zzE(d2);
                case 54:
                    if (!zzP(obj, i4, i3)) {
                        continue;
                    }
                    d2 = zzC(obj, j2);
                    i2 += zzdi.zzD(i4 << 3) + zzdi.zzE(d2);
                case 55:
                    if (!zzP(obj, i4, i3)) {
                        continue;
                    }
                    c3 = zzr(obj, j2);
                    zzC = zzdi.zzD(i4 << 3);
                    o2 = zzdi.zzx(c3);
                    zzD = zzC + o2;
                    i2 += zzD;
                case 56:
                    if (!zzP(obj, i4, i3)) {
                        continue;
                    }
                    n2 = zzdi.zzD(i4 << 3) + 8;
                    break;
                case 57:
                    if (!zzP(obj, i4, i3)) {
                        continue;
                    }
                    n2 = zzdi.zzD(i4 << 3) + 4;
                    break;
                case 58:
                    if (!zzP(obj, i4, i3)) {
                        continue;
                    }
                    n2 = zzdi.zzD(i4 << 3) + 1;
                    break;
                case 59:
                    if (zzP(obj, i4, i3)) {
                        f2 = zzgz.f(obj, j2);
                        break;
                    } else {
                        continue;
                    }
                case 60:
                    if (!zzP(obj, i4, i3)) {
                        continue;
                    }
                    n2 = zzgd.u(i4, zzgz.f(obj, j2), zzE(i3));
                    break;
                case 61:
                    if (!zzP(obj, i4, i3)) {
                        continue;
                    }
                    f2 = zzgz.f(obj, j2);
                    int zzD22 = zzdi.zzD(i4 << 3);
                    int zzd2 = ((zzdb) f2).zzd();
                    zzD = zzD22 + zzdi.zzD(zzd2) + zzd2;
                    i2 += zzD;
                case 62:
                    if (zzP(obj, i4, i3)) {
                        c2 = zzr(obj, j2);
                        zzC = zzdi.zzD(i4 << 3);
                        o2 = zzdi.zzD(c2);
                        zzD = zzC + o2;
                        i2 += zzD;
                    } else {
                        continue;
                    }
                case 63:
                    if (!zzP(obj, i4, i3)) {
                        continue;
                    }
                    c3 = zzr(obj, j2);
                    zzC = zzdi.zzD(i4 << 3);
                    o2 = zzdi.zzx(c3);
                    zzD = zzC + o2;
                    i2 += zzD;
                case 64:
                    if (!zzP(obj, i4, i3)) {
                        continue;
                    }
                    n2 = zzdi.zzD(i4 << 3) + 4;
                    break;
                case 65:
                    if (!zzP(obj, i4, i3)) {
                        continue;
                    }
                    n2 = zzdi.zzD(i4 << 3) + 8;
                    break;
                case 66:
                    if (zzP(obj, i4, i3)) {
                        c4 = zzr(obj, j2);
                        zzC = zzdi.zzD(i4 << 3);
                        c2 = (c4 >> 31) ^ (c4 + c4);
                        o2 = zzdi.zzD(c2);
                        zzD = zzC + o2;
                        i2 += zzD;
                    } else {
                        continue;
                    }
                case 67:
                    if (zzP(obj, i4, i3)) {
                        d3 = zzC(obj, j2);
                        zzC = zzdi.zzD(i4 << 3);
                        o2 = zzdi.zzE((d3 >> 63) ^ (d3 + d3));
                        zzD = zzC + o2;
                        i2 += zzD;
                    } else {
                        continue;
                    }
                case 68:
                    if (!zzP(obj, i4, i3)) {
                        continue;
                    }
                    n2 = zzdi.d(i4, (zzfl) zzgz.f(obj, j2), zzE(i3));
                    break;
                default:
            }
            i2 += n2;
        }
        zzgp zzgpVar = this.zzn;
        return i2 + zzgpVar.a(zzgpVar.c(obj));
    }

    private static int zzr(Object obj, long j2) {
        return ((Integer) zzgz.f(obj, j2)).intValue();
    }

    private final int zzs(Object obj, byte[] bArr, int i2, int i3, int i4, long j2, zzco zzcoVar) {
        Unsafe unsafe = zzb;
        Object zzF = zzF(i4);
        Object object = unsafe.getObject(obj, j2);
        if (!((zzff) object).zze()) {
            zzff zzb2 = zzff.zza().zzb();
            zzfg.zzb(zzb2, object);
            unsafe.putObject(obj, j2, zzb2);
        }
        zzfe zzfeVar = (zzfe) zzF;
        throw null;
    }

    private final int zzt(Object obj, byte[] bArr, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j2, int i9, zzco zzcoVar) {
        int m2;
        long j3;
        int i10;
        Object valueOf;
        int j4;
        Object obj2;
        Unsafe unsafe = zzb;
        long j5 = this.zzc[i9 + 2] & 1048575;
        switch (i8) {
            case 51:
                if (i6 == 1) {
                    unsafe.putObject(obj, j2, Double.valueOf(Double.longBitsToDouble(zzcp.o(bArr, i2))));
                    unsafe.putInt(obj, j5, i5);
                    return i2 + 8;
                }
                return i2;
            case 52:
                if (i6 == 5) {
                    unsafe.putObject(obj, j2, Float.valueOf(Float.intBitsToFloat(zzcp.b(bArr, i2))));
                    unsafe.putInt(obj, j5, i5);
                    return i2 + 4;
                }
                return i2;
            case 53:
            case 54:
                if (i6 == 0) {
                    m2 = zzcp.m(bArr, i2, zzcoVar);
                    j3 = zzcoVar.zzb;
                    valueOf = Long.valueOf(j3);
                    unsafe.putObject(obj, j2, valueOf);
                    unsafe.putInt(obj, j5, i5);
                    return m2;
                }
                return i2;
            case 55:
            case 62:
                if (i6 == 0) {
                    m2 = zzcp.j(bArr, i2, zzcoVar);
                    i10 = zzcoVar.zza;
                    valueOf = Integer.valueOf(i10);
                    unsafe.putObject(obj, j2, valueOf);
                    unsafe.putInt(obj, j5, i5);
                    return m2;
                }
                return i2;
            case 56:
            case 65:
                if (i6 == 1) {
                    unsafe.putObject(obj, j2, Long.valueOf(zzcp.o(bArr, i2)));
                    unsafe.putInt(obj, j5, i5);
                    return i2 + 8;
                }
                return i2;
            case 57:
            case 64:
                if (i6 == 5) {
                    unsafe.putObject(obj, j2, Integer.valueOf(zzcp.b(bArr, i2)));
                    unsafe.putInt(obj, j5, i5);
                    return i2 + 4;
                }
                return i2;
            case 58:
                if (i6 == 0) {
                    m2 = zzcp.m(bArr, i2, zzcoVar);
                    valueOf = Boolean.valueOf(zzcoVar.zzb != 0);
                    unsafe.putObject(obj, j2, valueOf);
                    unsafe.putInt(obj, j5, i5);
                    return m2;
                }
                return i2;
            case 59:
                if (i6 == 2) {
                    j4 = zzcp.j(bArr, i2, zzcoVar);
                    int i11 = zzcoVar.zza;
                    if (i11 == 0) {
                        obj2 = "";
                        unsafe.putObject(obj, j2, obj2);
                        unsafe.putInt(obj, j5, i5);
                        return j4;
                    } else if ((i7 & PKIFailureInfo.duplicateCertReq) == 0 || zzhe.i(bArr, j4, j4 + i11)) {
                        unsafe.putObject(obj, j2, new String(bArr, j4, i11, zzel.f6425a));
                        j4 += i11;
                        unsafe.putInt(obj, j5, i5);
                        return j4;
                    } else {
                        throw zzen.c();
                    }
                }
                return i2;
            case 60:
                if (i6 == 2) {
                    j4 = zzcp.d(zzE(i9), bArr, i2, i3, zzcoVar);
                    Object object = unsafe.getInt(obj, j5) == i5 ? unsafe.getObject(obj, j2) : null;
                    obj2 = zzcoVar.zzc;
                    if (object != null) {
                        obj2 = zzel.d(object, obj2);
                    }
                    unsafe.putObject(obj, j2, obj2);
                    unsafe.putInt(obj, j5, i5);
                    return j4;
                }
                return i2;
            case 61:
                if (i6 == 2) {
                    m2 = zzcp.a(bArr, i2, zzcoVar);
                    valueOf = zzcoVar.zzc;
                    unsafe.putObject(obj, j2, valueOf);
                    unsafe.putInt(obj, j5, i5);
                    return m2;
                }
                return i2;
            case 63:
                if (i6 == 0) {
                    int j6 = zzcp.j(bArr, i2, zzcoVar);
                    int i12 = zzcoVar.zza;
                    zzeg zzD = zzD(i9);
                    if (zzD == null || zzD.zza(i12)) {
                        unsafe.putObject(obj, j2, Integer.valueOf(i12));
                        unsafe.putInt(obj, j5, i5);
                    } else {
                        b(obj).d(i4, Long.valueOf(i12));
                    }
                    return j6;
                }
                return i2;
            case 66:
                if (i6 == 0) {
                    m2 = zzcp.j(bArr, i2, zzcoVar);
                    i10 = zzde.zzb(zzcoVar.zza);
                    valueOf = Integer.valueOf(i10);
                    unsafe.putObject(obj, j2, valueOf);
                    unsafe.putInt(obj, j5, i5);
                    return m2;
                }
                return i2;
            case 67:
                if (i6 == 0) {
                    m2 = zzcp.m(bArr, i2, zzcoVar);
                    j3 = zzde.zzc(zzcoVar.zzb);
                    valueOf = Long.valueOf(j3);
                    unsafe.putObject(obj, j2, valueOf);
                    unsafe.putInt(obj, j5, i5);
                    return m2;
                }
                return i2;
            case 68:
                if (i6 == 3) {
                    j4 = zzcp.c(zzE(i9), bArr, i2, i3, (i4 & (-8)) | 4, zzcoVar);
                    Object object2 = unsafe.getInt(obj, j5) == i5 ? unsafe.getObject(obj, j2) : null;
                    obj2 = zzcoVar.zzc;
                    if (object2 != null) {
                        obj2 = zzel.d(object2, obj2);
                    }
                    unsafe.putObject(obj, j2, obj2);
                    unsafe.putInt(obj, j5, i5);
                    return j4;
                }
                return i2;
            default:
                return i2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x02c1, code lost:
        if (r0 != r5) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x02c3, code lost:
        r15 = r31;
        r14 = r32;
        r12 = r33;
        r13 = r35;
        r11 = r36;
        r10 = r19;
        r1 = r20;
        r2 = r23;
        r6 = r26;
        r7 = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x02d9, code lost:
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x030a, code lost:
        if (r0 != r15) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x032b, code lost:
        if (r0 != r15) goto L137;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v9, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int zzu(Object obj, byte[] bArr, int i2, int i3, zzco zzcoVar) {
        byte b2;
        int i4;
        int i5;
        int i6;
        Unsafe unsafe;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        Unsafe unsafe2;
        int i13;
        int i14;
        Unsafe unsafe3;
        int i15;
        Object d2;
        int i16;
        Unsafe unsafe4;
        zzfo<T> zzfoVar = this;
        Object obj2 = obj;
        byte[] bArr2 = bArr;
        int i17 = i3;
        zzco zzcoVar2 = zzcoVar;
        Unsafe unsafe5 = zzb;
        int i18 = -1;
        int i19 = 1048575;
        int i20 = i2;
        int i21 = 1048575;
        int i22 = -1;
        int i23 = 0;
        int i24 = 0;
        while (i20 < i17) {
            int i25 = i20 + 1;
            byte b3 = bArr2[i20];
            if (b3 < 0) {
                i4 = zzcp.k(b3, bArr2, i25, zzcoVar2);
                b2 = zzcoVar2.zza;
            } else {
                b2 = b3;
                i4 = i25;
            }
            int i26 = b2 >>> 3;
            int i27 = b2 & 7;
            int zzx = i26 > i22 ? zzfoVar.zzx(i26, i23 / 3) : zzfoVar.zzw(i26);
            if (zzx == i18) {
                i5 = i4;
                i6 = i26;
                unsafe = unsafe5;
                i7 = i18;
                i8 = 0;
            } else {
                int[] iArr = zzfoVar.zzc;
                int i28 = iArr[zzx + 1];
                int zzA = zzA(i28);
                Unsafe unsafe6 = unsafe5;
                long j2 = i28 & i19;
                if (zzA <= 17) {
                    int i29 = iArr[zzx + 2];
                    int i30 = 1 << (i29 >>> 20);
                    int i31 = i29 & 1048575;
                    if (i31 != i21) {
                        if (i21 != 1048575) {
                            long j3 = i21;
                            unsafe4 = unsafe6;
                            unsafe4.putInt(obj2, j3, i24);
                        } else {
                            unsafe4 = unsafe6;
                        }
                        if (i31 != 1048575) {
                            i24 = unsafe4.getInt(obj2, i31);
                        }
                        unsafe2 = unsafe4;
                        i21 = i31;
                    } else {
                        unsafe2 = unsafe6;
                    }
                    switch (zzA) {
                        case 0:
                            i9 = 1048575;
                            i13 = i21;
                            i14 = zzx;
                            i6 = i26;
                            unsafe3 = unsafe2;
                            i15 = i4;
                            if (i27 != 1) {
                                i5 = i15;
                                unsafe = unsafe3;
                                i8 = i14;
                                i21 = i13;
                                i7 = -1;
                                break;
                            } else {
                                zzgz.o(obj2, j2, Double.longBitsToDouble(zzcp.o(bArr2, i15)));
                                i20 = i15 + 8;
                                i24 |= i30;
                                unsafe5 = unsafe3;
                                i23 = i14;
                                i21 = i13;
                                i22 = i6;
                                break;
                            }
                        case 1:
                            i9 = 1048575;
                            i13 = i21;
                            i14 = zzx;
                            i6 = i26;
                            unsafe3 = unsafe2;
                            i15 = i4;
                            if (i27 != 5) {
                                i5 = i15;
                                unsafe = unsafe3;
                                i8 = i14;
                                i21 = i13;
                                i7 = -1;
                                break;
                            } else {
                                zzgz.p(obj2, j2, Float.intBitsToFloat(zzcp.b(bArr2, i15)));
                                i20 = i15 + 4;
                                i24 |= i30;
                                unsafe5 = unsafe3;
                                i23 = i14;
                                i21 = i13;
                                i22 = i6;
                                break;
                            }
                        case 2:
                        case 3:
                            i9 = 1048575;
                            i13 = i21;
                            i14 = zzx;
                            i6 = i26;
                            unsafe3 = unsafe2;
                            i15 = i4;
                            if (i27 != 0) {
                                i5 = i15;
                                unsafe = unsafe3;
                                i8 = i14;
                                i21 = i13;
                                i7 = -1;
                                break;
                            } else {
                                int m2 = zzcp.m(bArr2, i15, zzcoVar2);
                                unsafe3.putLong(obj, j2, zzcoVar2.zzb);
                                i24 |= i30;
                                unsafe5 = unsafe3;
                                i23 = i14;
                                i20 = m2;
                                i21 = i13;
                                i22 = i6;
                                break;
                            }
                        case 4:
                        case 11:
                            i9 = 1048575;
                            i13 = i21;
                            i14 = zzx;
                            i6 = i26;
                            unsafe3 = unsafe2;
                            i15 = i4;
                            if (i27 != 0) {
                                i5 = i15;
                                unsafe = unsafe3;
                                i8 = i14;
                                i21 = i13;
                                i7 = -1;
                                break;
                            } else {
                                i20 = zzcp.j(bArr2, i15, zzcoVar2);
                                i16 = zzcoVar2.zza;
                                unsafe3.putInt(obj2, j2, i16);
                                i24 |= i30;
                                unsafe5 = unsafe3;
                                i23 = i14;
                                i21 = i13;
                                i22 = i6;
                                break;
                            }
                        case 5:
                        case 14:
                            i9 = 1048575;
                            i13 = i21;
                            i14 = zzx;
                            i6 = i26;
                            unsafe3 = unsafe2;
                            if (i27 != 1) {
                                i15 = i4;
                                i5 = i15;
                                unsafe = unsafe3;
                                i8 = i14;
                                i21 = i13;
                                i7 = -1;
                                break;
                            } else {
                                unsafe3.putLong(obj, j2, zzcp.o(bArr2, i4));
                                i20 = i4 + 8;
                                i24 |= i30;
                                unsafe5 = unsafe3;
                                i23 = i14;
                                i21 = i13;
                                i22 = i6;
                                break;
                            }
                        case 6:
                        case 13:
                            i9 = 1048575;
                            i13 = i21;
                            i14 = zzx;
                            i6 = i26;
                            unsafe3 = unsafe2;
                            if (i27 != 5) {
                                i15 = i4;
                                i5 = i15;
                                unsafe = unsafe3;
                                i8 = i14;
                                i21 = i13;
                                i7 = -1;
                                break;
                            } else {
                                unsafe3.putInt(obj2, j2, zzcp.b(bArr2, i4));
                                i20 = i4 + 4;
                                i24 |= i30;
                                unsafe5 = unsafe3;
                                i23 = i14;
                                i21 = i13;
                                i22 = i6;
                                break;
                            }
                        case 7:
                            i9 = 1048575;
                            i13 = i21;
                            i14 = zzx;
                            i6 = i26;
                            unsafe3 = unsafe2;
                            if (i27 != 0) {
                                i15 = i4;
                                i5 = i15;
                                unsafe = unsafe3;
                                i8 = i14;
                                i21 = i13;
                                i7 = -1;
                                break;
                            } else {
                                i20 = zzcp.m(bArr2, i4, zzcoVar2);
                                zzgz.m(obj2, j2, zzcoVar2.zzb != 0);
                                i24 |= i30;
                                unsafe5 = unsafe3;
                                i23 = i14;
                                i21 = i13;
                                i22 = i6;
                                break;
                            }
                        case 8:
                            i9 = 1048575;
                            i13 = i21;
                            i14 = zzx;
                            i6 = i26;
                            unsafe3 = unsafe2;
                            if (i27 != 2) {
                                i15 = i4;
                                i5 = i15;
                                unsafe = unsafe3;
                                i8 = i14;
                                i21 = i13;
                                i7 = -1;
                                break;
                            } else {
                                i20 = (i28 & PKIFailureInfo.duplicateCertReq) == 0 ? zzcp.g(bArr2, i4, zzcoVar2) : zzcp.h(bArr2, i4, zzcoVar2);
                                d2 = zzcoVar2.zzc;
                                unsafe3.putObject(obj2, j2, d2);
                                i24 |= i30;
                                unsafe5 = unsafe3;
                                i23 = i14;
                                i21 = i13;
                                i22 = i6;
                                break;
                            }
                        case 9:
                            i9 = 1048575;
                            i13 = i21;
                            i14 = zzx;
                            i6 = i26;
                            unsafe3 = unsafe2;
                            if (i27 != 2) {
                                i15 = i4;
                                i5 = i15;
                                unsafe = unsafe3;
                                i8 = i14;
                                i21 = i13;
                                i7 = -1;
                                break;
                            } else {
                                i20 = zzcp.d(zzfoVar.zzE(i14), bArr2, i4, i17, zzcoVar2);
                                Object object = unsafe3.getObject(obj2, j2);
                                d2 = object == null ? zzcoVar2.zzc : zzel.d(object, zzcoVar2.zzc);
                                unsafe3.putObject(obj2, j2, d2);
                                i24 |= i30;
                                unsafe5 = unsafe3;
                                i23 = i14;
                                i21 = i13;
                                i22 = i6;
                                break;
                            }
                        case 10:
                            i9 = 1048575;
                            i13 = i21;
                            i14 = zzx;
                            i6 = i26;
                            unsafe3 = unsafe2;
                            if (i27 != 2) {
                                i15 = i4;
                                i5 = i15;
                                unsafe = unsafe3;
                                i8 = i14;
                                i21 = i13;
                                i7 = -1;
                                break;
                            } else {
                                i20 = zzcp.a(bArr2, i4, zzcoVar2);
                                d2 = zzcoVar2.zzc;
                                unsafe3.putObject(obj2, j2, d2);
                                i24 |= i30;
                                unsafe5 = unsafe3;
                                i23 = i14;
                                i21 = i13;
                                i22 = i6;
                                break;
                            }
                        case 12:
                            i9 = 1048575;
                            i13 = i21;
                            i14 = zzx;
                            i6 = i26;
                            unsafe3 = unsafe2;
                            if (i27 != 0) {
                                i15 = i4;
                                i5 = i15;
                                unsafe = unsafe3;
                                i8 = i14;
                                i21 = i13;
                                i7 = -1;
                                break;
                            } else {
                                i20 = zzcp.j(bArr2, i4, zzcoVar2);
                                i16 = zzcoVar2.zza;
                                unsafe3.putInt(obj2, j2, i16);
                                i24 |= i30;
                                unsafe5 = unsafe3;
                                i23 = i14;
                                i21 = i13;
                                i22 = i6;
                                break;
                            }
                        case 15:
                            i9 = 1048575;
                            i13 = i21;
                            i14 = zzx;
                            i6 = i26;
                            unsafe3 = unsafe2;
                            if (i27 != 0) {
                                i15 = i4;
                                i5 = i15;
                                unsafe = unsafe3;
                                i8 = i14;
                                i21 = i13;
                                i7 = -1;
                                break;
                            } else {
                                i20 = zzcp.j(bArr2, i4, zzcoVar2);
                                i16 = zzde.zzb(zzcoVar2.zza);
                                unsafe3.putInt(obj2, j2, i16);
                                i24 |= i30;
                                unsafe5 = unsafe3;
                                i23 = i14;
                                i21 = i13;
                                i22 = i6;
                                break;
                            }
                        case 16:
                            if (i27 != 0) {
                                i13 = i21;
                                i6 = i26;
                                unsafe3 = unsafe2;
                                i15 = i4;
                                i14 = zzx;
                                i5 = i15;
                                unsafe = unsafe3;
                                i8 = i14;
                                i21 = i13;
                                i7 = -1;
                                break;
                            } else {
                                int m3 = zzcp.m(bArr2, i4, zzcoVar2);
                                i13 = i21;
                                i6 = i26;
                                i9 = 1048575;
                                unsafe2.putLong(obj, j2, zzde.zzc(zzcoVar2.zzb));
                                i24 |= i30;
                                unsafe5 = unsafe2;
                                i23 = zzx;
                                i20 = m3;
                                i21 = i13;
                                i22 = i6;
                                break;
                            }
                        default:
                            i13 = i21;
                            i14 = zzx;
                            i6 = i26;
                            unsafe3 = unsafe2;
                            i15 = i4;
                            i5 = i15;
                            unsafe = unsafe3;
                            i8 = i14;
                            i21 = i13;
                            i7 = -1;
                            break;
                    }
                    i19 = i9;
                    i18 = -1;
                } else {
                    i6 = i26;
                    int i32 = i4;
                    i9 = 1048575;
                    int i33 = i21;
                    if (zzA != 27) {
                        i8 = zzx;
                        if (zzA <= 49) {
                            i11 = i24;
                            i12 = i33;
                            unsafe = unsafe6;
                            i7 = -1;
                            i20 = zzv(obj, bArr, i32, i3, b2, i6, i27, i8, i28, zzA, j2, zzcoVar);
                        } else {
                            i10 = i32;
                            i11 = i24;
                            unsafe = unsafe6;
                            i12 = i33;
                            i7 = -1;
                            if (zzA != 50) {
                                i20 = zzt(obj, bArr, i10, i3, b2, i6, i27, i28, zzA, j2, i8, zzcoVar);
                            } else if (i27 == 2) {
                                i20 = zzs(obj, bArr, i10, i3, i8, j2, zzcoVar);
                            }
                        }
                        unsafe5 = unsafe;
                        i19 = 1048575;
                    } else if (i27 == 2) {
                        zzek zzekVar = (zzek) unsafe6.getObject(obj2, j2);
                        if (!zzekVar.zzc()) {
                            int size = zzekVar.size();
                            zzekVar = zzekVar.zzd(size == 0 ? 10 : size + size);
                            unsafe6.putObject(obj2, j2, zzekVar);
                        }
                        i20 = zzcp.e(zzfoVar.zzE(zzx), b2, bArr, i32, i3, zzekVar, zzcoVar);
                        unsafe5 = unsafe6;
                        i24 = i24;
                        i21 = i33;
                        i22 = i6;
                        i23 = zzx;
                        i19 = i9;
                        i18 = -1;
                    } else {
                        i8 = zzx;
                        i10 = i32;
                        i11 = i24;
                        unsafe = unsafe6;
                        i12 = i33;
                        i7 = -1;
                    }
                    i5 = i10;
                    i24 = i11;
                    i21 = i12;
                }
            }
            i20 = zzcp.i(b2, bArr, i5, i3, b(obj), zzcoVar);
            zzfoVar = this;
            obj2 = obj;
            bArr2 = bArr;
            i17 = i3;
            zzcoVar2 = zzcoVar;
            i18 = i7;
            i22 = i6;
            i23 = i8;
            unsafe5 = unsafe;
            i19 = 1048575;
        }
        int i34 = i24;
        Unsafe unsafe7 = unsafe5;
        if (i21 != i19) {
            unsafe7.putInt(obj, i21, i34);
        }
        if (i20 == i3) {
            return i20;
        }
        throw zzen.e();
    }

    /* JADX WARN: Code restructure failed: missing block: B:150:0x0258, code lost:
        if (r29.zzb != 0) goto L168;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x025a, code lost:
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x025c, code lost:
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x025d, code lost:
        r12.zze(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0260, code lost:
        if (r4 >= r19) goto L175;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0262, code lost:
        r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.j(r17, r4, r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0268, code lost:
        if (r20 == r29.zza) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x026b, code lost:
        r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.m(r17, r6, r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0273, code lost:
        if (r29.zzb == 0) goto L176;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x0276, code lost:
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0122, code lost:
        if (r4 == 0) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0124, code lost:
        r12.add(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb.zzb);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x012a, code lost:
        r12.add(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb.zzr(r17, r1, r4));
        r1 = r1 + r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0132, code lost:
        if (r1 >= r19) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0134, code lost:
        r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.j(r17, r1, r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x013a, code lost:
        if (r20 == r29.zza) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x013d, code lost:
        r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.j(r17, r4, r29);
        r4 = r29.zza;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0143, code lost:
        if (r4 < 0) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0147, code lost:
        if (r4 > (r17.length - r1)) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0149, code lost:
        if (r4 != 0) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0150, code lost:
        throw com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzen.f();
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0155, code lost:
        throw com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzen.d();
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0156, code lost:
        return r1;
     */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:285:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:287:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01a4  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:102:0x01b4 -> B:94:0x0193). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:116:0x01e6 -> B:117:0x01ea). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:122:0x01fc -> B:112:0x01d3). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:152:0x025c -> B:153:0x025d). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:159:0x0273 -> B:151:0x025a). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:64:0x012a -> B:65:0x0132). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:73:0x0149 -> B:63:0x0124). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:96:0x019e -> B:97:0x01a2). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int zzv(Object obj, byte[] bArr, int i2, int i3, int i4, int i5, int i6, int i7, long j2, int i8, long j3, zzco zzcoVar) {
        int i9;
        int i10;
        int i11;
        int i12;
        int j4;
        int i13 = i2;
        Unsafe unsafe = zzb;
        zzek zzekVar = (zzek) unsafe.getObject(obj, j3);
        if (!zzekVar.zzc()) {
            int size = zzekVar.size();
            zzekVar = zzekVar.zzd(size == 0 ? 10 : size + size);
            unsafe.putObject(obj, j3, zzekVar);
        }
        switch (i8) {
            case 18:
            case 35:
                if (i6 == 2) {
                    zzdk zzdkVar = (zzdk) zzekVar;
                    int j5 = zzcp.j(bArr, i13, zzcoVar);
                    int i14 = zzcoVar.zza + j5;
                    while (j5 < i14) {
                        zzdkVar.zze(Double.longBitsToDouble(zzcp.o(bArr, j5)));
                        j5 += 8;
                    }
                    if (j5 == i14) {
                        return j5;
                    }
                    throw zzen.f();
                }
                if (i6 == 1) {
                    zzdk zzdkVar2 = (zzdk) zzekVar;
                    long o2 = zzcp.o(bArr, i2);
                    while (true) {
                        zzdkVar2.zze(Double.longBitsToDouble(o2));
                        i9 = i13 + 8;
                        if (i9 < i3) {
                            i13 = zzcp.j(bArr, i9, zzcoVar);
                            if (i4 == zzcoVar.zza) {
                                o2 = zzcp.o(bArr, i13);
                            }
                        }
                    }
                    return i9;
                }
                return i13;
            case 19:
            case 36:
                if (i6 == 2) {
                    zzdu zzduVar = (zzdu) zzekVar;
                    int j6 = zzcp.j(bArr, i13, zzcoVar);
                    int i15 = zzcoVar.zza + j6;
                    while (j6 < i15) {
                        zzduVar.zzg(Float.intBitsToFloat(zzcp.b(bArr, j6)));
                        j6 += 4;
                    }
                    if (j6 == i15) {
                        return j6;
                    }
                    throw zzen.f();
                }
                if (i6 == 5) {
                    zzdu zzduVar2 = (zzdu) zzekVar;
                    int b2 = zzcp.b(bArr, i2);
                    while (true) {
                        zzduVar2.zzg(Float.intBitsToFloat(b2));
                        i10 = i13 + 4;
                        if (i10 < i3) {
                            i13 = zzcp.j(bArr, i10, zzcoVar);
                            if (i4 == zzcoVar.zza) {
                                b2 = zzcp.b(bArr, i13);
                            }
                        }
                    }
                    return i10;
                }
                return i13;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i6 == 2) {
                    zzfa zzfaVar = (zzfa) zzekVar;
                    int j7 = zzcp.j(bArr, i13, zzcoVar);
                    int i16 = zzcoVar.zza + j7;
                    while (j7 < i16) {
                        j7 = zzcp.m(bArr, j7, zzcoVar);
                        zzfaVar.zzf(zzcoVar.zzb);
                    }
                    if (j7 == i16) {
                        return j7;
                    }
                    throw zzen.f();
                }
                if (i6 == 0) {
                    zzfa zzfaVar2 = (zzfa) zzekVar;
                    do {
                        int m2 = zzcp.m(bArr, i13, zzcoVar);
                        zzfaVar2.zzf(zzcoVar.zzb);
                        if (m2 < i3) {
                            i13 = zzcp.j(bArr, m2, zzcoVar);
                        }
                        return m2;
                    } while (i4 == zzcoVar.zza);
                    return m2;
                }
                return i13;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i6 == 2) {
                    return zzcp.f(bArr, i13, zzekVar, zzcoVar);
                }
                if (i6 == 0) {
                    return zzcp.l(i4, bArr, i2, i3, zzekVar, zzcoVar);
                }
                return i13;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i6 == 2) {
                    zzfa zzfaVar3 = (zzfa) zzekVar;
                    int j8 = zzcp.j(bArr, i13, zzcoVar);
                    int i17 = zzcoVar.zza + j8;
                    while (j8 < i17) {
                        zzfaVar3.zzf(zzcp.o(bArr, j8));
                        j8 += 8;
                    }
                    if (j8 == i17) {
                        return j8;
                    }
                    throw zzen.f();
                }
                if (i6 == 1) {
                    zzfa zzfaVar4 = (zzfa) zzekVar;
                    long o3 = zzcp.o(bArr, i2);
                    while (true) {
                        zzfaVar4.zzf(o3);
                        i11 = i13 + 8;
                        if (i11 < i3) {
                            i13 = zzcp.j(bArr, i11, zzcoVar);
                            if (i4 == zzcoVar.zza) {
                                o3 = zzcp.o(bArr, i13);
                            }
                        }
                    }
                    return i11;
                }
                return i13;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i6 == 2) {
                    zzed zzedVar = (zzed) zzekVar;
                    int j9 = zzcp.j(bArr, i13, zzcoVar);
                    int i18 = zzcoVar.zza + j9;
                    while (j9 < i18) {
                        zzedVar.zzg(zzcp.b(bArr, j9));
                        j9 += 4;
                    }
                    if (j9 == i18) {
                        return j9;
                    }
                    throw zzen.f();
                }
                if (i6 == 5) {
                    zzed zzedVar2 = (zzed) zzekVar;
                    int b3 = zzcp.b(bArr, i2);
                    while (true) {
                        zzedVar2.zzg(b3);
                        i12 = i13 + 4;
                        if (i12 < i3) {
                            i13 = zzcp.j(bArr, i12, zzcoVar);
                            if (i4 == zzcoVar.zza) {
                                b3 = zzcp.b(bArr, i13);
                            }
                        }
                    }
                    return i12;
                }
                return i13;
            case 25:
            case 42:
                if (i6 != 2) {
                    if (i6 == 0) {
                        zzcq zzcqVar = (zzcq) zzekVar;
                        int m3 = zzcp.m(bArr, i13, zzcoVar);
                        break;
                    }
                    return i13;
                }
                zzcq zzcqVar2 = (zzcq) zzekVar;
                j4 = zzcp.j(bArr, i13, zzcoVar);
                int i19 = zzcoVar.zza + j4;
                while (j4 < i19) {
                    j4 = zzcp.m(bArr, j4, zzcoVar);
                    zzcqVar2.zze(zzcoVar.zzb != 0);
                }
                if (j4 != i19) {
                    throw zzen.f();
                }
                return j4;
            case 26:
                if (i6 == 2) {
                    if ((j2 & 536870912) == 0) {
                        int j10 = zzcp.j(bArr, i13, zzcoVar);
                        int i20 = zzcoVar.zza;
                        if (i20 < 0) {
                            throw zzen.d();
                        }
                        if (i20 != 0) {
                            String str = new String(bArr, j10, i20, zzel.f6425a);
                            zzekVar.add(str);
                            j10 += i20;
                            if (j10 < i3) {
                                int j11 = zzcp.j(bArr, j10, zzcoVar);
                                if (i4 != zzcoVar.zza) {
                                    return j10;
                                }
                                j10 = zzcp.j(bArr, j11, zzcoVar);
                                i20 = zzcoVar.zza;
                                if (i20 < 0) {
                                    throw zzen.d();
                                }
                                if (i20 != 0) {
                                    str = new String(bArr, j10, i20, zzel.f6425a);
                                    zzekVar.add(str);
                                    j10 += i20;
                                    if (j10 < i3) {
                                        return j10;
                                    }
                                }
                            }
                        }
                        zzekVar.add("");
                        if (j10 < i3) {
                        }
                    } else {
                        int j12 = zzcp.j(bArr, i13, zzcoVar);
                        int i21 = zzcoVar.zza;
                        if (i21 < 0) {
                            throw zzen.d();
                        }
                        if (i21 != 0) {
                            int i22 = j12 + i21;
                            if (!zzhe.i(bArr, j12, i22)) {
                                throw zzen.c();
                            }
                            String str2 = new String(bArr, j12, i21, zzel.f6425a);
                            zzekVar.add(str2);
                            j12 = i22;
                            if (j12 < i3) {
                                int j13 = zzcp.j(bArr, j12, zzcoVar);
                                if (i4 != zzcoVar.zza) {
                                    return j12;
                                }
                                j12 = zzcp.j(bArr, j13, zzcoVar);
                                int i23 = zzcoVar.zza;
                                if (i23 < 0) {
                                    throw zzen.d();
                                }
                                if (i23 != 0) {
                                    i22 = j12 + i23;
                                    if (!zzhe.i(bArr, j12, i22)) {
                                        throw zzen.c();
                                    }
                                    str2 = new String(bArr, j12, i23, zzel.f6425a);
                                    zzekVar.add(str2);
                                    j12 = i22;
                                    if (j12 < i3) {
                                        return j12;
                                    }
                                }
                            }
                        }
                        zzekVar.add("");
                        if (j12 < i3) {
                        }
                    }
                }
                return i13;
            case 27:
                if (i6 == 2) {
                    return zzcp.e(zzE(i7), i4, bArr, i2, i3, zzekVar, zzcoVar);
                }
                return i13;
            case 28:
                if (i6 == 2) {
                    int j14 = zzcp.j(bArr, i13, zzcoVar);
                    int i24 = zzcoVar.zza;
                    if (i24 < 0) {
                        throw zzen.d();
                    }
                    if (i24 > bArr.length - j14) {
                        throw zzen.f();
                    }
                }
                return i13;
            case 30:
            case 44:
                if (i6 != 2) {
                    if (i6 == 0) {
                        j4 = zzcp.l(i4, bArr, i2, i3, zzekVar, zzcoVar);
                    }
                    return i13;
                }
                j4 = zzcp.f(bArr, i13, zzekVar, zzcoVar);
                zzec zzecVar = (zzec) obj;
                zzgq zzgqVar = zzecVar.zzc;
                if (zzgqVar == zzgq.zzc()) {
                    zzgqVar = null;
                }
                Object a2 = zzgd.a(i5, zzekVar, zzD(i7), zzgqVar, this.zzn);
                if (a2 != null) {
                    zzecVar.zzc = (zzgq) a2;
                    return j4;
                }
                return j4;
            case 33:
            case 47:
                if (i6 == 2) {
                    zzed zzedVar3 = (zzed) zzekVar;
                    int j15 = zzcp.j(bArr, i13, zzcoVar);
                    int i25 = zzcoVar.zza + j15;
                    while (j15 < i25) {
                        j15 = zzcp.j(bArr, j15, zzcoVar);
                        zzedVar3.zzg(zzde.zzb(zzcoVar.zza));
                    }
                    if (j15 == i25) {
                        return j15;
                    }
                    throw zzen.f();
                }
                if (i6 == 0) {
                    zzed zzedVar4 = (zzed) zzekVar;
                    do {
                        int j16 = zzcp.j(bArr, i13, zzcoVar);
                        zzedVar4.zzg(zzde.zzb(zzcoVar.zza));
                        if (j16 < i3) {
                            i13 = zzcp.j(bArr, j16, zzcoVar);
                        }
                        return j16;
                    } while (i4 == zzcoVar.zza);
                    return j16;
                }
                return i13;
            case 34:
            case 48:
                if (i6 == 2) {
                    zzfa zzfaVar5 = (zzfa) zzekVar;
                    int j17 = zzcp.j(bArr, i13, zzcoVar);
                    int i26 = zzcoVar.zza + j17;
                    while (j17 < i26) {
                        j17 = zzcp.m(bArr, j17, zzcoVar);
                        zzfaVar5.zzf(zzde.zzc(zzcoVar.zzb));
                    }
                    if (j17 == i26) {
                        return j17;
                    }
                    throw zzen.f();
                }
                if (i6 == 0) {
                    zzfa zzfaVar6 = (zzfa) zzekVar;
                    do {
                        int m4 = zzcp.m(bArr, i13, zzcoVar);
                        zzfaVar6.zzf(zzde.zzc(zzcoVar.zzb));
                        if (m4 < i3) {
                            i13 = zzcp.j(bArr, m4, zzcoVar);
                        }
                        return m4;
                    } while (i4 == zzcoVar.zza);
                    return m4;
                }
                return i13;
            default:
                if (i6 == 3) {
                    zzgb zzE = zzE(i7);
                    int i27 = (i4 & (-8)) | 4;
                    int c2 = zzcp.c(zzE, bArr, i2, i3, i27, zzcoVar);
                    while (true) {
                        zzekVar.add(zzcoVar.zzc);
                        if (c2 < i3) {
                            int j18 = zzcp.j(bArr, c2, zzcoVar);
                            if (i4 == zzcoVar.zza) {
                                c2 = zzcp.c(zzE, bArr, j18, i3, i27, zzcoVar);
                            }
                        }
                    }
                    return c2;
                }
                return i13;
        }
    }

    private final int zzw(int i2) {
        if (i2 < this.zze || i2 > this.zzf) {
            return -1;
        }
        return zzz(i2, 0);
    }

    private final int zzx(int i2, int i3) {
        if (i2 < this.zze || i2 > this.zzf) {
            return -1;
        }
        return zzz(i2, i3);
    }

    private final int zzy(int i2) {
        return this.zzc[i2 + 2];
    }

    private final int zzz(int i2, int i3) {
        int length = (this.zzc.length / 3) - 1;
        while (i3 <= length) {
            int i4 = (length + i3) >>> 1;
            int i5 = i4 * 3;
            int i6 = this.zzc[i5];
            if (i2 == i6) {
                return i5;
            }
            if (i2 < i6) {
                length = i4 - 1;
            } else {
                i3 = i4 + 1;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x05fe, code lost:
        if (r2 == r3) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x0600, code lost:
        r30.putInt(r13, r2, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x0606, code lost:
        r2 = r8.zzk;
     */
    /* JADX WARN: Code restructure failed: missing block: B:199:0x060a, code lost:
        if (r2 >= r8.zzl) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x060c, code lost:
        r4 = r8.zzj[r2];
        r5 = r8.zzc[r4];
        r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgz.f(r13, r8.zzB(r4) & r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x061e, code lost:
        if (r5 != null) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x0625, code lost:
        if (r8.zzD(r4) != null) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:205:0x0627, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x062a, code lost:
        r5 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzff) r5;
        r0 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfe) r8.zzF(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x0632, code lost:
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x0633, code lost:
        if (r9 != 0) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x0635, code lost:
        if (r0 != r6) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x063c, code lost:
        throw com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzen.e();
     */
    /* JADX WARN: Code restructure failed: missing block: B:213:0x063d, code lost:
        if (r0 > r6) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:214:0x063f, code lost:
        if (r1 != r9) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:215:0x0641, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x0646, code lost:
        throw com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzen.e();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int a(Object obj, byte[] bArr, int i2, int i3, int i4, zzco zzcoVar) {
        Unsafe unsafe;
        int i5;
        int i6;
        Object obj2;
        zzfo<T> zzfoVar;
        int i7;
        int i8;
        byte b2;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        boolean z;
        Object obj3;
        byte[] bArr2;
        int i15;
        zzco zzcoVar2;
        int i16;
        int i17;
        Object obj4;
        Object valueOf;
        Object zze;
        long j2;
        int i18;
        int i19;
        int i20;
        int i21;
        boolean z2;
        int i22;
        int i23;
        int i24;
        int i25;
        int i26;
        int i27;
        int i28;
        long j3;
        int i29;
        int i30;
        int i31;
        zzfo<T> zzfoVar2 = this;
        Object obj5 = obj;
        byte[] bArr3 = bArr;
        int i32 = i3;
        int i33 = i4;
        zzco zzcoVar3 = zzcoVar;
        Unsafe unsafe2 = zzb;
        int i34 = i2;
        int i35 = 0;
        int i36 = 0;
        int i37 = 0;
        int i38 = -1;
        int i39 = 1048575;
        while (true) {
            Object obj6 = null;
            if (i34 < i32) {
                int i40 = i34 + 1;
                byte b3 = bArr3[i34];
                if (b3 < 0) {
                    int k2 = zzcp.k(b3, bArr3, i40, zzcoVar3);
                    b2 = zzcoVar3.zza;
                    i40 = k2;
                } else {
                    b2 = b3;
                }
                int i41 = b2 >>> 3;
                int i42 = b2 & 7;
                int zzx = i41 > i38 ? zzfoVar2.zzx(i41, i36 / 3) : zzfoVar2.zzw(i41);
                if (zzx == -1) {
                    i9 = i41;
                    i10 = i40;
                    i11 = b2;
                    i12 = i37;
                    unsafe = unsafe2;
                    i13 = i33;
                    i14 = 0;
                    z = true;
                } else {
                    int[] iArr = zzfoVar2.zzc;
                    int i43 = iArr[zzx + 1];
                    int zzA = zzA(i43);
                    long j4 = i43 & 1048575;
                    int i44 = b2;
                    if (zzA <= 17) {
                        int i45 = iArr[zzx + 2];
                        int i46 = 1 << (i45 >>> 20);
                        int i47 = i45 & 1048575;
                        if (i47 != i39) {
                            if (i39 != 1048575) {
                                j2 = j4;
                                obj5 = obj;
                                unsafe2.putInt(obj5, i39, i37);
                            } else {
                                j2 = j4;
                                obj5 = obj;
                            }
                            i37 = unsafe2.getInt(obj5, i47);
                            i18 = i47;
                        } else {
                            j2 = j4;
                            obj5 = obj;
                            i18 = i39;
                        }
                        int i48 = i37;
                        switch (zzA) {
                            case 0:
                                i19 = zzx;
                                i20 = i18;
                                i21 = i41;
                                long j5 = j2;
                                z2 = true;
                                i22 = i40;
                                if (i42 != 1) {
                                    i24 = i22;
                                    i13 = i4;
                                    z = z2;
                                    i12 = i48;
                                    unsafe = unsafe2;
                                    i14 = i19;
                                    i10 = i24;
                                    i11 = i44;
                                    i39 = i20;
                                    i9 = i21;
                                    break;
                                } else {
                                    zzgz.o(obj5, j5, Double.longBitsToDouble(zzcp.o(bArr3, i22)));
                                    i34 = i22 + 8;
                                    i37 = i48 | i46;
                                    i39 = i20;
                                    i32 = i3;
                                    i36 = i19;
                                    i38 = i21;
                                    i35 = i44;
                                    i33 = i4;
                                }
                            case 1:
                                i19 = zzx;
                                i20 = i18;
                                i21 = i41;
                                long j6 = j2;
                                i23 = i40;
                                if (i42 != 5) {
                                    i24 = i23;
                                    z2 = true;
                                    i13 = i4;
                                    z = z2;
                                    i12 = i48;
                                    unsafe = unsafe2;
                                    i14 = i19;
                                    i10 = i24;
                                    i11 = i44;
                                    i39 = i20;
                                    i9 = i21;
                                    break;
                                } else {
                                    zzgz.p(obj5, j6, Float.intBitsToFloat(zzcp.b(bArr3, i23)));
                                    i34 = i23 + 4;
                                    i37 = i48 | i46;
                                    i39 = i20;
                                    i32 = i3;
                                    i36 = i19;
                                    i38 = i21;
                                    i35 = i44;
                                    i33 = i4;
                                }
                            case 2:
                            case 3:
                                i19 = zzx;
                                i20 = i18;
                                i21 = i41;
                                long j7 = j2;
                                i23 = i40;
                                if (i42 != 0) {
                                    i24 = i23;
                                    z2 = true;
                                    i13 = i4;
                                    z = z2;
                                    i12 = i48;
                                    unsafe = unsafe2;
                                    i14 = i19;
                                    i10 = i24;
                                    i11 = i44;
                                    i39 = i20;
                                    i9 = i21;
                                    break;
                                } else {
                                    int m2 = zzcp.m(bArr3, i23, zzcoVar3);
                                    unsafe2.putLong(obj, j7, zzcoVar3.zzb);
                                    i37 = i48 | i46;
                                    i39 = i20;
                                    i32 = i3;
                                    i34 = m2;
                                    i36 = i19;
                                    i38 = i21;
                                    i35 = i44;
                                    i33 = i4;
                                }
                            case 4:
                            case 11:
                                i19 = zzx;
                                i20 = i18;
                                i21 = i41;
                                long j8 = j2;
                                i23 = i40;
                                if (i42 != 0) {
                                    i24 = i23;
                                    z2 = true;
                                    i13 = i4;
                                    z = z2;
                                    i12 = i48;
                                    unsafe = unsafe2;
                                    i14 = i19;
                                    i10 = i24;
                                    i11 = i44;
                                    i39 = i20;
                                    i9 = i21;
                                    break;
                                } else {
                                    i34 = zzcp.j(bArr3, i23, zzcoVar3);
                                    unsafe2.putInt(obj5, j8, zzcoVar3.zza);
                                    i37 = i48 | i46;
                                    i39 = i20;
                                    i32 = i3;
                                    i36 = i19;
                                    i38 = i21;
                                    i35 = i44;
                                    i33 = i4;
                                }
                            case 5:
                            case 14:
                                int i49 = zzx;
                                i20 = i18;
                                long j9 = j2;
                                if (i42 != 1) {
                                    i21 = i41;
                                    i44 = i44;
                                    i19 = i49;
                                    z2 = true;
                                    i24 = i40;
                                    i13 = i4;
                                    z = z2;
                                    i12 = i48;
                                    unsafe = unsafe2;
                                    i14 = i19;
                                    i10 = i24;
                                    i11 = i44;
                                    i39 = i20;
                                    i9 = i21;
                                    break;
                                } else {
                                    i44 = i44;
                                    i22 = i40;
                                    i21 = i41;
                                    i19 = i49;
                                    unsafe2.putLong(obj, j9, zzcp.o(bArr3, i40));
                                    i34 = i22 + 8;
                                    i37 = i48 | i46;
                                    i39 = i20;
                                    i32 = i3;
                                    i36 = i19;
                                    i38 = i21;
                                    i35 = i44;
                                    i33 = i4;
                                }
                            case 6:
                            case 13:
                                i36 = zzx;
                                i20 = i18;
                                i25 = i41;
                                i26 = i44;
                                long j10 = j2;
                                i27 = i3;
                                if (i42 != 5) {
                                    i24 = i40;
                                    i21 = i25;
                                    i44 = i26;
                                    i19 = i36;
                                    z2 = true;
                                    i13 = i4;
                                    z = z2;
                                    i12 = i48;
                                    unsafe = unsafe2;
                                    i14 = i19;
                                    i10 = i24;
                                    i11 = i44;
                                    i39 = i20;
                                    i9 = i21;
                                    break;
                                } else {
                                    unsafe2.putInt(obj5, j10, zzcp.b(bArr3, i40));
                                    i34 = i40 + 4;
                                    i37 = i48 | i46;
                                    i39 = i20;
                                    i35 = i26;
                                    i32 = i27;
                                    i38 = i25;
                                    i33 = i4;
                                }
                            case 7:
                                i36 = zzx;
                                i20 = i18;
                                i25 = i41;
                                i26 = i44;
                                long j11 = j2;
                                i27 = i3;
                                if (i42 != 0) {
                                    i24 = i40;
                                    i21 = i25;
                                    i44 = i26;
                                    i19 = i36;
                                    z2 = true;
                                    i13 = i4;
                                    z = z2;
                                    i12 = i48;
                                    unsafe = unsafe2;
                                    i14 = i19;
                                    i10 = i24;
                                    i11 = i44;
                                    i39 = i20;
                                    i9 = i21;
                                    break;
                                } else {
                                    i34 = zzcp.m(bArr3, i40, zzcoVar3);
                                    zzgz.m(obj5, j11, zzcoVar3.zzb != 0);
                                    i37 = i48 | i46;
                                    i39 = i20;
                                    i35 = i26;
                                    i32 = i27;
                                    i38 = i25;
                                    i33 = i4;
                                }
                            case 8:
                                i36 = zzx;
                                i20 = i18;
                                i25 = i41;
                                i26 = i44;
                                long j12 = j2;
                                i27 = i3;
                                if (i42 != 2) {
                                    i24 = i40;
                                    i21 = i25;
                                    i44 = i26;
                                    i19 = i36;
                                    z2 = true;
                                    i13 = i4;
                                    z = z2;
                                    i12 = i48;
                                    unsafe = unsafe2;
                                    i14 = i19;
                                    i10 = i24;
                                    i11 = i44;
                                    i39 = i20;
                                    i9 = i21;
                                    break;
                                } else {
                                    i34 = (536870912 & i43) == 0 ? zzcp.g(bArr3, i40, zzcoVar3) : zzcp.h(bArr3, i40, zzcoVar3);
                                    unsafe2.putObject(obj5, j12, zzcoVar3.zzc);
                                    i37 = i48 | i46;
                                    i39 = i20;
                                    i35 = i26;
                                    i32 = i27;
                                    i38 = i25;
                                    i33 = i4;
                                }
                            case 9:
                                i28 = zzx;
                                i20 = i18;
                                i25 = i41;
                                i26 = i44;
                                long j13 = j2;
                                if (i42 != 2) {
                                    i24 = i40;
                                    i21 = i25;
                                    i44 = i26;
                                    z2 = true;
                                    i19 = i28;
                                    i13 = i4;
                                    z = z2;
                                    i12 = i48;
                                    unsafe = unsafe2;
                                    i14 = i19;
                                    i10 = i24;
                                    i11 = i44;
                                    i39 = i20;
                                    i9 = i21;
                                    break;
                                } else {
                                    i27 = i3;
                                    i34 = zzcp.d(zzfoVar2.zzE(i28), bArr3, i40, i27, zzcoVar3);
                                    unsafe2.putObject(obj5, j13, (i48 & i46) == 0 ? zzcoVar3.zzc : zzel.d(unsafe2.getObject(obj5, j13), zzcoVar3.zzc));
                                    i37 = i48 | i46;
                                    i39 = i20;
                                    i36 = i28;
                                    i35 = i26;
                                    i32 = i27;
                                    i38 = i25;
                                    i33 = i4;
                                }
                            case 10:
                                i28 = zzx;
                                i20 = i18;
                                i25 = i41;
                                i26 = i44;
                                long j14 = j2;
                                if (i42 != 2) {
                                    i24 = i40;
                                    i21 = i25;
                                    i44 = i26;
                                    z2 = true;
                                    i19 = i28;
                                    i13 = i4;
                                    z = z2;
                                    i12 = i48;
                                    unsafe = unsafe2;
                                    i14 = i19;
                                    i10 = i24;
                                    i11 = i44;
                                    i39 = i20;
                                    i9 = i21;
                                    break;
                                } else {
                                    i34 = zzcp.a(bArr3, i40, zzcoVar3);
                                    unsafe2.putObject(obj5, j14, zzcoVar3.zzc);
                                    i37 = i48 | i46;
                                    i39 = i20;
                                    i36 = i28;
                                    i38 = i25;
                                    i35 = i26;
                                    i32 = i3;
                                    i33 = i4;
                                }
                            case 12:
                                i28 = zzx;
                                i20 = i18;
                                i25 = i41;
                                i26 = i44;
                                j3 = j2;
                                if (i42 != 0) {
                                    i24 = i40;
                                    i21 = i25;
                                    i44 = i26;
                                    z2 = true;
                                    i19 = i28;
                                    i13 = i4;
                                    z = z2;
                                    i12 = i48;
                                    unsafe = unsafe2;
                                    i14 = i19;
                                    i10 = i24;
                                    i11 = i44;
                                    i39 = i20;
                                    i9 = i21;
                                    break;
                                } else {
                                    i34 = zzcp.j(bArr3, i40, zzcoVar3);
                                    i29 = zzcoVar3.zza;
                                    zzeg zzD = zzfoVar2.zzD(i28);
                                    if (zzD != null && !zzD.zza(i29)) {
                                        b(obj).d(i26, Long.valueOf(i29));
                                        i37 = i48;
                                        i36 = i28;
                                        i38 = i25;
                                        i35 = i26;
                                        i39 = i20;
                                        i32 = i3;
                                        i33 = i4;
                                    }
                                    unsafe2.putInt(obj5, j3, i29);
                                    i37 = i48 | i46;
                                    i39 = i20;
                                    i36 = i28;
                                    i38 = i25;
                                    i35 = i26;
                                    i32 = i3;
                                    i33 = i4;
                                }
                                break;
                            case 15:
                                i28 = zzx;
                                i20 = i18;
                                i25 = i41;
                                i26 = i44;
                                if (i42 != 0) {
                                    i24 = i40;
                                    i21 = i25;
                                    i44 = i26;
                                    z2 = true;
                                    i19 = i28;
                                    i13 = i4;
                                    z = z2;
                                    i12 = i48;
                                    unsafe = unsafe2;
                                    i14 = i19;
                                    i10 = i24;
                                    i11 = i44;
                                    i39 = i20;
                                    i9 = i21;
                                    break;
                                } else {
                                    i34 = zzcp.j(bArr3, i40, zzcoVar3);
                                    i29 = zzde.zzb(zzcoVar3.zza);
                                    j3 = j2;
                                    unsafe2.putInt(obj5, j3, i29);
                                    i37 = i48 | i46;
                                    i39 = i20;
                                    i36 = i28;
                                    i38 = i25;
                                    i35 = i26;
                                    i32 = i3;
                                    i33 = i4;
                                }
                            case 16:
                                if (i42 != 0) {
                                    i20 = i18;
                                    i24 = i40;
                                    i19 = zzx;
                                    i21 = i41;
                                    z2 = true;
                                    i13 = i4;
                                    z = z2;
                                    i12 = i48;
                                    unsafe = unsafe2;
                                    i14 = i19;
                                    i10 = i24;
                                    i11 = i44;
                                    i39 = i20;
                                    i9 = i21;
                                    break;
                                } else {
                                    int m3 = zzcp.m(bArr3, i40, zzcoVar3);
                                    i25 = i41;
                                    i28 = zzx;
                                    int i50 = i18;
                                    i26 = i44;
                                    unsafe2.putLong(obj, j2, zzde.zzc(zzcoVar3.zzb));
                                    i37 = i48 | i46;
                                    i39 = i50;
                                    i34 = m3;
                                    i36 = i28;
                                    i38 = i25;
                                    i35 = i26;
                                    i32 = i3;
                                    i33 = i4;
                                }
                            default:
                                i19 = zzx;
                                i20 = i18;
                                i21 = i41;
                                long j15 = j2;
                                z2 = true;
                                i22 = i40;
                                if (i42 != 3) {
                                    i24 = i22;
                                    i13 = i4;
                                    z = z2;
                                    i12 = i48;
                                    unsafe = unsafe2;
                                    i14 = i19;
                                    i10 = i24;
                                    i11 = i44;
                                    i39 = i20;
                                    i9 = i21;
                                    break;
                                } else {
                                    i34 = zzcp.c(zzfoVar2.zzE(i19), bArr, i22, i3, (i21 << 3) | 4, zzcoVar);
                                    unsafe2.putObject(obj5, j15, (i48 & i46) == 0 ? zzcoVar3.zzc : zzel.d(unsafe2.getObject(obj5, j15), zzcoVar3.zzc));
                                    i37 = i48 | i46;
                                    bArr3 = bArr;
                                    i39 = i20;
                                    i32 = i3;
                                    i36 = i19;
                                    i38 = i21;
                                    i35 = i44;
                                    i33 = i4;
                                }
                        }
                    } else {
                        int i51 = i40;
                        obj5 = obj;
                        int i52 = zzx;
                        if (zzA != 27) {
                            i12 = i37;
                            i30 = i39;
                            if (zzA <= 49) {
                                i9 = i41;
                                z = true;
                                unsafe = unsafe2;
                                i14 = i52;
                                i34 = zzv(obj, bArr, i51, i3, i44, i41, i42, i52, i43, zzA, j4, zzcoVar);
                                if (i34 != i51) {
                                    zzfoVar2 = this;
                                    obj5 = obj;
                                    bArr3 = bArr;
                                    i38 = i9;
                                    i32 = i3;
                                    i33 = i4;
                                    zzcoVar3 = zzcoVar;
                                    i35 = i44;
                                    i37 = i12;
                                    i39 = i30;
                                    i36 = i14;
                                    unsafe2 = unsafe;
                                } else {
                                    i10 = i34;
                                    i11 = i44;
                                    i39 = i30;
                                    i13 = i4;
                                }
                            } else {
                                z = true;
                                i9 = i41;
                                unsafe = unsafe2;
                                i14 = i52;
                                i31 = i51;
                                if (zzA != 50) {
                                    i34 = zzt(obj, bArr, i31, i3, i44, i9, i42, i43, zzA, j4, i14, zzcoVar);
                                    if (i34 != i31) {
                                        zzfoVar2 = this;
                                        obj5 = obj;
                                        bArr3 = bArr;
                                        i38 = i9;
                                        i32 = i3;
                                        i33 = i4;
                                        zzcoVar3 = zzcoVar;
                                        i35 = i44;
                                        i37 = i12;
                                        i39 = i30;
                                        i36 = i14;
                                        unsafe2 = unsafe;
                                    } else {
                                        i10 = i34;
                                        i11 = i44;
                                        i39 = i30;
                                        i13 = i4;
                                    }
                                } else if (i42 == 2) {
                                    i34 = zzs(obj, bArr, i31, i3, i14, j4, zzcoVar);
                                    if (i34 != i31) {
                                        zzfoVar2 = this;
                                        obj5 = obj;
                                        bArr3 = bArr;
                                        i38 = i9;
                                        i32 = i3;
                                        i33 = i4;
                                        zzcoVar3 = zzcoVar;
                                        i35 = i44;
                                        i37 = i12;
                                        i39 = i30;
                                        i36 = i14;
                                        unsafe2 = unsafe;
                                    } else {
                                        i10 = i34;
                                        i11 = i44;
                                        i39 = i30;
                                        i13 = i4;
                                    }
                                }
                            }
                        } else if (i42 == 2) {
                            zzek zzekVar = (zzek) unsafe2.getObject(obj5, j4);
                            if (!zzekVar.zzc()) {
                                int size = zzekVar.size();
                                zzekVar = zzekVar.zzd(size == 0 ? 10 : size + size);
                                unsafe2.putObject(obj5, j4, zzekVar);
                            }
                            i35 = i44;
                            i34 = zzcp.e(zzfoVar2.zzE(i52), i35, bArr, i51, i3, zzekVar, zzcoVar);
                            bArr3 = bArr;
                            i32 = i3;
                            i38 = i41;
                            i36 = i52;
                            i37 = i37;
                            i39 = i39;
                            i33 = i4;
                        } else {
                            i12 = i37;
                            i30 = i39;
                            z = true;
                            i9 = i41;
                            unsafe = unsafe2;
                            i14 = i52;
                            i31 = i51;
                        }
                        i13 = i4;
                        i10 = i31;
                        i11 = i44;
                        i39 = i30;
                    }
                }
                if (i11 != i13 || i13 == 0) {
                    int i53 = i13;
                    if (this.zzh) {
                        zzcoVar2 = zzcoVar;
                        if (zzcoVar2.zzd != zzdn.zza()) {
                            i15 = i9;
                            zzea zzb2 = zzcoVar2.zzd.zzb(this.zzg, i15);
                            if (zzb2 == null) {
                                i34 = zzcp.i(i11, bArr, i10, i3, b(obj), zzcoVar);
                                obj3 = obj;
                                bArr2 = bArr;
                                i16 = i39;
                                i17 = i3;
                            } else {
                                obj3 = obj;
                                zzdy zzdyVar = (zzdy) obj3;
                                zzdyVar.q();
                                zzds zzdsVar = zzdyVar.zza;
                                zzhf zzhfVar = zzb2.f6424d.f6419b;
                                if (zzhfVar == zzhf.zzn) {
                                    zzcp.j(bArr, i10, zzcoVar2);
                                    throw null;
                                }
                                switch (zzhfVar.ordinal()) {
                                    case 0:
                                        bArr2 = bArr;
                                        i16 = i39;
                                        i17 = i3;
                                        obj6 = Double.valueOf(Double.longBitsToDouble(zzcp.o(bArr2, i10)));
                                        i10 += 8;
                                        obj4 = obj6;
                                        break;
                                    case 1:
                                        bArr2 = bArr;
                                        i16 = i39;
                                        i17 = i3;
                                        obj6 = Float.valueOf(Float.intBitsToFloat(zzcp.b(bArr2, i10)));
                                        i10 += 4;
                                        obj4 = obj6;
                                        break;
                                    case 2:
                                    case 3:
                                        bArr2 = bArr;
                                        i16 = i39;
                                        i17 = i3;
                                        i10 = zzcp.m(bArr2, i10, zzcoVar2);
                                        obj6 = Long.valueOf(zzcoVar2.zzb);
                                        obj4 = obj6;
                                        break;
                                    case 4:
                                    case 12:
                                        bArr2 = bArr;
                                        i16 = i39;
                                        i17 = i3;
                                        i10 = zzcp.j(bArr2, i10, zzcoVar2);
                                        obj6 = Integer.valueOf(zzcoVar2.zza);
                                        obj4 = obj6;
                                        break;
                                    case 5:
                                    case 15:
                                        bArr2 = bArr;
                                        i16 = i39;
                                        i17 = i3;
                                        obj6 = Long.valueOf(zzcp.o(bArr2, i10));
                                        i10 += 8;
                                        obj4 = obj6;
                                        break;
                                    case 6:
                                    case 14:
                                        bArr2 = bArr;
                                        i16 = i39;
                                        i17 = i3;
                                        obj6 = Integer.valueOf(zzcp.b(bArr2, i10));
                                        i10 += 4;
                                        obj4 = obj6;
                                        break;
                                    case 7:
                                        bArr2 = bArr;
                                        i16 = i39;
                                        i17 = i3;
                                        i10 = zzcp.m(bArr2, i10, zzcoVar2);
                                        if (zzcoVar2.zzb == 0) {
                                            z = false;
                                        }
                                        obj6 = Boolean.valueOf(z);
                                        obj4 = obj6;
                                        break;
                                    case 8:
                                        bArr2 = bArr;
                                        i16 = i39;
                                        i17 = i3;
                                        i10 = zzcp.g(bArr2, i10, zzcoVar2);
                                        obj4 = zzcoVar2.zzc;
                                        break;
                                    case 9:
                                        bArr2 = bArr;
                                        i16 = i39;
                                        i17 = i3;
                                        i10 = zzcp.c(zzfu.zza().zzb(zzb2.f6423c.getClass()), bArr, i10, i3, (i15 << 3) | 4, zzcoVar);
                                        obj4 = zzcoVar2.zzc;
                                        break;
                                    case 10:
                                        bArr2 = bArr;
                                        i10 = zzcp.d(zzfu.zza().zzb(zzb2.f6423c.getClass()), bArr2, i10, i3, zzcoVar2);
                                        obj4 = zzcoVar2.zzc;
                                        i16 = i39;
                                        i17 = i3;
                                        break;
                                    case 11:
                                        bArr2 = bArr;
                                        i10 = zzcp.a(bArr2, i10, zzcoVar2);
                                        obj4 = zzcoVar2.zzc;
                                        i16 = i39;
                                        i17 = i3;
                                        break;
                                    case 13:
                                        throw new IllegalStateException("Shouldn't reach here.");
                                    case 16:
                                        bArr2 = bArr;
                                        i10 = zzcp.j(bArr2, i10, zzcoVar2);
                                        valueOf = Integer.valueOf(zzde.zzb(zzcoVar2.zza));
                                        i16 = i39;
                                        obj4 = valueOf;
                                        i17 = i3;
                                        break;
                                    case 17:
                                        bArr2 = bArr;
                                        i10 = zzcp.m(bArr2, i10, zzcoVar2);
                                        valueOf = Long.valueOf(zzde.zzc(zzcoVar2.zzb));
                                        i16 = i39;
                                        obj4 = valueOf;
                                        i17 = i3;
                                        break;
                                    default:
                                        bArr2 = bArr;
                                        i16 = i39;
                                        i17 = i3;
                                        obj4 = obj6;
                                        break;
                                }
                                zzdz zzdzVar = zzb2.f6424d;
                                boolean z3 = zzdzVar.f6420c;
                                int ordinal = zzdzVar.f6419b.ordinal();
                                if ((ordinal == 9 || ordinal == 10) && (zze = zzdsVar.zze(zzb2.f6424d)) != null) {
                                    obj4 = zzel.d(zze, obj4);
                                }
                                zzdsVar.zzi(zzb2.f6424d, obj4);
                                i34 = i10;
                            }
                            i35 = i11;
                            i38 = i15;
                            obj5 = obj3;
                            bArr3 = bArr2;
                            i37 = i12;
                            i36 = i14;
                            i32 = i17;
                            zzfoVar2 = this;
                            i33 = i53;
                            zzcoVar3 = zzcoVar2;
                            unsafe2 = unsafe;
                            i39 = i16;
                        } else {
                            obj3 = obj;
                            bArr2 = bArr;
                            i15 = i9;
                        }
                    } else {
                        obj3 = obj;
                        bArr2 = bArr;
                        i15 = i9;
                        zzcoVar2 = zzcoVar;
                    }
                    i16 = i39;
                    i17 = i3;
                    i34 = zzcp.i(i11, bArr, i10, i3, b(obj), zzcoVar);
                    i35 = i11;
                    i38 = i15;
                    obj5 = obj3;
                    bArr3 = bArr2;
                    i37 = i12;
                    i36 = i14;
                    i32 = i17;
                    zzfoVar2 = this;
                    i33 = i53;
                    zzcoVar3 = zzcoVar2;
                    unsafe2 = unsafe;
                    i39 = i16;
                } else {
                    zzfoVar = this;
                    obj2 = obj;
                    i5 = i13;
                    i34 = i10;
                    i7 = i39;
                    i35 = i11;
                    i37 = i12;
                    i8 = 1048575;
                    i6 = i3;
                }
            } else {
                int i54 = i39;
                unsafe = unsafe2;
                i5 = i33;
                i6 = i32;
                obj2 = obj5;
                zzfoVar = zzfoVar2;
                i7 = i54;
                i8 = 1048575;
            }
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final int zza(Object obj) {
        return this.zzi ? zzq(obj) : zzp(obj);
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
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int zzb(Object obj) {
        int i2;
        double a2;
        float b2;
        long d2;
        int c2;
        boolean w;
        Object f2;
        int length = this.zzc.length;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4 += 3) {
            int zzB = zzB(i4);
            int i5 = this.zzc[i4];
            long j2 = 1048575 & zzB;
            int i6 = 37;
            switch (zzA(zzB)) {
                case 0:
                    i2 = i3 * 53;
                    a2 = zzgz.a(obj, j2);
                    d2 = Double.doubleToLongBits(a2);
                    c2 = zzel.zzc(d2);
                    i3 = i2 + c2;
                    break;
                case 1:
                    i2 = i3 * 53;
                    b2 = zzgz.b(obj, j2);
                    c2 = Float.floatToIntBits(b2);
                    i3 = i2 + c2;
                    break;
                case 2:
                case 3:
                case 5:
                case 14:
                case 16:
                    i2 = i3 * 53;
                    d2 = zzgz.d(obj, j2);
                    c2 = zzel.zzc(d2);
                    i3 = i2 + c2;
                    break;
                case 4:
                case 6:
                case 11:
                case 12:
                case 13:
                case 15:
                    i2 = i3 * 53;
                    c2 = zzgz.c(obj, j2);
                    i3 = i2 + c2;
                    break;
                case 7:
                    i2 = i3 * 53;
                    w = zzgz.w(obj, j2);
                    c2 = zzel.zza(w);
                    i3 = i2 + c2;
                    break;
                case 8:
                    i2 = i3 * 53;
                    c2 = ((String) zzgz.f(obj, j2)).hashCode();
                    i3 = i2 + c2;
                    break;
                case 9:
                    f2 = zzgz.f(obj, j2);
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
                    c2 = zzgz.f(obj, j2).hashCode();
                    i3 = i2 + c2;
                    break;
                case 17:
                    f2 = zzgz.f(obj, j2);
                    break;
                case 51:
                    if (zzP(obj, i5, i4)) {
                        i2 = i3 * 53;
                        a2 = zzn(obj, j2);
                        d2 = Double.doubleToLongBits(a2);
                        c2 = zzel.zzc(d2);
                        i3 = i2 + c2;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzP(obj, i5, i4)) {
                        i2 = i3 * 53;
                        b2 = zzo(obj, j2);
                        c2 = Float.floatToIntBits(b2);
                        i3 = i2 + c2;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (!zzP(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    d2 = zzC(obj, j2);
                    c2 = zzel.zzc(d2);
                    i3 = i2 + c2;
                    break;
                case 54:
                    if (!zzP(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    d2 = zzC(obj, j2);
                    c2 = zzel.zzc(d2);
                    i3 = i2 + c2;
                    break;
                case 55:
                    if (!zzP(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = zzr(obj, j2);
                    i3 = i2 + c2;
                    break;
                case 56:
                    if (!zzP(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    d2 = zzC(obj, j2);
                    c2 = zzel.zzc(d2);
                    i3 = i2 + c2;
                    break;
                case 57:
                    if (!zzP(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = zzr(obj, j2);
                    i3 = i2 + c2;
                    break;
                case 58:
                    if (zzP(obj, i5, i4)) {
                        i2 = i3 * 53;
                        w = zzQ(obj, j2);
                        c2 = zzel.zza(w);
                        i3 = i2 + c2;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (!zzP(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = ((String) zzgz.f(obj, j2)).hashCode();
                    i3 = i2 + c2;
                    break;
                case 60:
                    if (!zzP(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = zzgz.f(obj, j2).hashCode();
                    i3 = i2 + c2;
                    break;
                case 61:
                    if (!zzP(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = zzgz.f(obj, j2).hashCode();
                    i3 = i2 + c2;
                    break;
                case 62:
                    if (!zzP(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = zzr(obj, j2);
                    i3 = i2 + c2;
                    break;
                case 63:
                    if (!zzP(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = zzr(obj, j2);
                    i3 = i2 + c2;
                    break;
                case 64:
                    if (!zzP(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = zzr(obj, j2);
                    i3 = i2 + c2;
                    break;
                case 65:
                    if (!zzP(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    d2 = zzC(obj, j2);
                    c2 = zzel.zzc(d2);
                    i3 = i2 + c2;
                    break;
                case 66:
                    if (!zzP(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = zzr(obj, j2);
                    i3 = i2 + c2;
                    break;
                case 67:
                    if (!zzP(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    d2 = zzC(obj, j2);
                    c2 = zzel.zzc(d2);
                    i3 = i2 + c2;
                    break;
                case 68:
                    if (!zzP(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = zzgz.f(obj, j2).hashCode();
                    i3 = i2 + c2;
                    break;
            }
        }
        int hashCode = (i3 * 53) + this.zzn.c(obj).hashCode();
        return this.zzh ? (hashCode * 53) + this.zzo.b(obj).f6415a.hashCode() : hashCode;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final Object zze() {
        return ((zzec) this.zzg).p(4, null, null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final void zzf(Object obj) {
        int i2;
        int i3 = this.zzk;
        while (true) {
            i2 = this.zzl;
            if (i3 >= i2) {
                break;
            }
            long zzB = zzB(this.zzj[i3]) & 1048575;
            Object f2 = zzgz.f(obj, zzB);
            if (f2 != null) {
                ((zzff) f2).zzc();
                zzgz.s(obj, zzB, f2);
            }
            i3++;
        }
        int length = this.zzj.length;
        while (i2 < length) {
            this.zzm.a(obj, this.zzj[i2]);
            i2++;
        }
        this.zzn.g(obj);
        if (this.zzh) {
            this.zzo.e(obj);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final void zzg(Object obj, Object obj2) {
        Objects.requireNonNull(obj2);
        for (int i2 = 0; i2 < this.zzc.length; i2 += 3) {
            int zzB = zzB(i2);
            long j2 = 1048575 & zzB;
            int i3 = this.zzc[i2];
            switch (zzA(zzB)) {
                case 0:
                    if (zzM(obj2, i2)) {
                        zzgz.o(obj, j2, zzgz.a(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzM(obj2, i2)) {
                        zzgz.p(obj, j2, zzgz.b(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (!zzM(obj2, i2)) {
                        break;
                    }
                    zzgz.r(obj, j2, zzgz.d(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 3:
                    if (!zzM(obj2, i2)) {
                        break;
                    }
                    zzgz.r(obj, j2, zzgz.d(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 4:
                    if (!zzM(obj2, i2)) {
                        break;
                    }
                    zzgz.q(obj, j2, zzgz.c(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 5:
                    if (!zzM(obj2, i2)) {
                        break;
                    }
                    zzgz.r(obj, j2, zzgz.d(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 6:
                    if (!zzM(obj2, i2)) {
                        break;
                    }
                    zzgz.q(obj, j2, zzgz.c(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 7:
                    if (zzM(obj2, i2)) {
                        zzgz.m(obj, j2, zzgz.w(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (!zzM(obj2, i2)) {
                        break;
                    }
                    zzgz.s(obj, j2, zzgz.f(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 9:
                case 17:
                    zzH(obj, obj2, i2);
                    break;
                case 10:
                    if (!zzM(obj2, i2)) {
                        break;
                    }
                    zzgz.s(obj, j2, zzgz.f(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 11:
                    if (!zzM(obj2, i2)) {
                        break;
                    }
                    zzgz.q(obj, j2, zzgz.c(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 12:
                    if (!zzM(obj2, i2)) {
                        break;
                    }
                    zzgz.q(obj, j2, zzgz.c(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 13:
                    if (!zzM(obj2, i2)) {
                        break;
                    }
                    zzgz.q(obj, j2, zzgz.c(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 14:
                    if (!zzM(obj2, i2)) {
                        break;
                    }
                    zzgz.r(obj, j2, zzgz.d(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 15:
                    if (!zzM(obj2, i2)) {
                        break;
                    }
                    zzgz.q(obj, j2, zzgz.c(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 16:
                    if (!zzM(obj2, i2)) {
                        break;
                    }
                    zzgz.r(obj, j2, zzgz.d(obj2, j2));
                    zzJ(obj, i2);
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
                    this.zzm.b(obj, obj2, j2);
                    break;
                case 50:
                    zzgd.f(this.zzq, obj, obj2, j2);
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
                    if (!zzP(obj2, i3, i2)) {
                        break;
                    }
                    zzgz.s(obj, j2, zzgz.f(obj2, j2));
                    zzK(obj, i3, i2);
                    break;
                case 60:
                case 68:
                    zzI(obj, obj2, i2);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (!zzP(obj2, i3, i2)) {
                        break;
                    }
                    zzgz.s(obj, j2, zzgz.f(obj2, j2));
                    zzK(obj, i3, i2);
                    break;
            }
        }
        zzgd.d(this.zzn, obj, obj2);
        if (this.zzh) {
            zzgd.c(this.zzo, obj, obj2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final void zzh(Object obj, byte[] bArr, int i2, int i3, zzco zzcoVar) {
        if (this.zzi) {
            zzu(obj, bArr, i2, i3, zzcoVar);
        } else {
            a(obj, bArr, i2, i3, 0, zzcoVar);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final boolean zzi(Object obj, Object obj2) {
        int length = this.zzc.length;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int zzB = zzB(i2);
            long j2 = zzB & 1048575;
            switch (zzA(zzB)) {
                case 0:
                    if (zzL(obj, obj2, i2) && Double.doubleToLongBits(zzgz.a(obj, j2)) == Double.doubleToLongBits(zzgz.a(obj2, j2))) {
                        break;
                    }
                    return false;
                case 1:
                    if (zzL(obj, obj2, i2) && Float.floatToIntBits(zzgz.b(obj, j2)) == Float.floatToIntBits(zzgz.b(obj2, j2))) {
                        break;
                    }
                    return false;
                case 2:
                    if (zzL(obj, obj2, i2) && zzgz.d(obj, j2) == zzgz.d(obj2, j2)) {
                        break;
                    }
                    return false;
                case 3:
                    if (zzL(obj, obj2, i2) && zzgz.d(obj, j2) == zzgz.d(obj2, j2)) {
                        break;
                    }
                    return false;
                case 4:
                    if (zzL(obj, obj2, i2) && zzgz.c(obj, j2) == zzgz.c(obj2, j2)) {
                        break;
                    }
                    return false;
                case 5:
                    if (zzL(obj, obj2, i2) && zzgz.d(obj, j2) == zzgz.d(obj2, j2)) {
                        break;
                    }
                    return false;
                case 6:
                    if (zzL(obj, obj2, i2) && zzgz.c(obj, j2) == zzgz.c(obj2, j2)) {
                        break;
                    }
                    return false;
                case 7:
                    if (zzL(obj, obj2, i2) && zzgz.w(obj, j2) == zzgz.w(obj2, j2)) {
                        break;
                    }
                    return false;
                case 8:
                    if (zzL(obj, obj2, i2) && zzgd.e(zzgz.f(obj, j2), zzgz.f(obj2, j2))) {
                        break;
                    }
                    return false;
                case 9:
                    if (zzL(obj, obj2, i2) && zzgd.e(zzgz.f(obj, j2), zzgz.f(obj2, j2))) {
                        break;
                    }
                    return false;
                case 10:
                    if (zzL(obj, obj2, i2) && zzgd.e(zzgz.f(obj, j2), zzgz.f(obj2, j2))) {
                        break;
                    }
                    return false;
                case 11:
                    if (zzL(obj, obj2, i2) && zzgz.c(obj, j2) == zzgz.c(obj2, j2)) {
                        break;
                    }
                    return false;
                case 12:
                    if (zzL(obj, obj2, i2) && zzgz.c(obj, j2) == zzgz.c(obj2, j2)) {
                        break;
                    }
                    return false;
                case 13:
                    if (zzL(obj, obj2, i2) && zzgz.c(obj, j2) == zzgz.c(obj2, j2)) {
                        break;
                    }
                    return false;
                case 14:
                    if (zzL(obj, obj2, i2) && zzgz.d(obj, j2) == zzgz.d(obj2, j2)) {
                        break;
                    }
                    return false;
                case 15:
                    if (zzL(obj, obj2, i2) && zzgz.c(obj, j2) == zzgz.c(obj2, j2)) {
                        break;
                    }
                    return false;
                case 16:
                    if (zzL(obj, obj2, i2) && zzgz.d(obj, j2) == zzgz.d(obj2, j2)) {
                        break;
                    }
                    return false;
                case 17:
                    if (zzL(obj, obj2, i2) && zzgd.e(zzgz.f(obj, j2), zzgz.f(obj2, j2))) {
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
                    if (zzgd.e(zzgz.f(obj, j2), zzgz.f(obj2, j2))) {
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
                    long zzy = zzy(i2) & 1048575;
                    if (zzgz.c(obj, zzy) == zzgz.c(obj2, zzy) && zzgd.e(zzgz.f(obj, j2), zzgz.f(obj2, j2))) {
                        break;
                    }
                    return false;
            }
        }
        if (this.zzn.c(obj).equals(this.zzn.c(obj2))) {
            if (this.zzh) {
                return this.zzo.b(obj).equals(this.zzo.b(obj2));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    public final boolean zzj(Object obj) {
        int i2;
        int i3;
        int i4 = 1048575;
        int i5 = 0;
        int i6 = 0;
        while (i6 < this.zzk) {
            int i7 = this.zzj[i6];
            int i8 = this.zzc[i7];
            int zzB = zzB(i7);
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
            if ((268435456 & zzB) != 0 && !zzN(obj, i7, i2, i3, i11)) {
                return false;
            }
            int zzA = zzA(zzB);
            if (zzA != 9 && zzA != 17) {
                if (zzA != 27) {
                    if (zzA == 60 || zzA == 68) {
                        if (zzP(obj, i8, i7) && !zzO(obj, zzB, zzE(i7))) {
                            return false;
                        }
                    } else if (zzA != 49) {
                        if (zzA == 50 && !((zzff) zzgz.f(obj, zzB & 1048575)).isEmpty()) {
                            zzfe zzfeVar = (zzfe) zzF(i7);
                            throw null;
                        }
                    }
                }
                List list = (List) zzgz.f(obj, zzB & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzgb zzE = zzE(i7);
                    for (int i12 = 0; i12 < list.size(); i12++) {
                        if (!zzE.zzj(list.get(i12))) {
                            return false;
                        }
                    }
                    continue;
                }
            } else if (zzN(obj, i7, i2, i3, i11) && !zzO(obj, zzB, zzE(i7))) {
                return false;
            }
            i6++;
            i4 = i2;
            i5 = i3;
        }
        return !this.zzh || this.zzo.b(obj).zzk();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x04af  */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzm(Object obj, zzdj zzdjVar) {
        Iterator it;
        Map.Entry entry;
        int length;
        int i2;
        double a2;
        float b2;
        long d2;
        long d3;
        int c2;
        long d4;
        int c3;
        boolean w;
        int c4;
        int c5;
        int c6;
        long d5;
        int c7;
        long d6;
        if (!this.zzi) {
            zzR(obj, zzdjVar);
            return;
        }
        if (this.zzh) {
            zzds b3 = this.zzo.b(obj);
            if (!b3.f6415a.isEmpty()) {
                it = b3.zzf();
                entry = (Map.Entry) it.next();
                length = this.zzc.length;
                for (i2 = 0; i2 < length; i2 += 3) {
                    int zzB = zzB(i2);
                    int i3 = this.zzc[i2];
                    while (entry != null && this.zzo.a(entry) <= i3) {
                        this.zzo.g(zzdjVar, entry);
                        entry = it.hasNext() ? (Map.Entry) it.next() : null;
                    }
                    switch (zzA(zzB)) {
                        case 0:
                            if (zzM(obj, i2)) {
                                a2 = zzgz.a(obj, zzB & 1048575);
                                zzdjVar.zzf(i3, a2);
                                break;
                            } else {
                                break;
                            }
                        case 1:
                            if (zzM(obj, i2)) {
                                b2 = zzgz.b(obj, zzB & 1048575);
                                zzdjVar.zzo(i3, b2);
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (zzM(obj, i2)) {
                                d2 = zzgz.d(obj, zzB & 1048575);
                                zzdjVar.zzt(i3, d2);
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if (zzM(obj, i2)) {
                                d3 = zzgz.d(obj, zzB & 1048575);
                                zzdjVar.zzK(i3, d3);
                                break;
                            } else {
                                break;
                            }
                        case 4:
                            if (zzM(obj, i2)) {
                                c2 = zzgz.c(obj, zzB & 1048575);
                                zzdjVar.zzr(i3, c2);
                                break;
                            } else {
                                break;
                            }
                        case 5:
                            if (zzM(obj, i2)) {
                                d4 = zzgz.d(obj, zzB & 1048575);
                                zzdjVar.zzm(i3, d4);
                                break;
                            } else {
                                break;
                            }
                        case 6:
                            if (zzM(obj, i2)) {
                                c3 = zzgz.c(obj, zzB & 1048575);
                                zzdjVar.zzk(i3, c3);
                                break;
                            } else {
                                break;
                            }
                        case 7:
                            if (zzM(obj, i2)) {
                                w = zzgz.w(obj, zzB & 1048575);
                                zzdjVar.zzb(i3, w);
                                break;
                            } else {
                                break;
                            }
                        case 8:
                            if (!zzM(obj, i2)) {
                                break;
                            }
                            zzT(i3, zzgz.f(obj, zzB & 1048575), zzdjVar);
                            break;
                        case 9:
                            if (!zzM(obj, i2)) {
                                break;
                            }
                            zzdjVar.zzv(i3, zzgz.f(obj, zzB & 1048575), zzE(i2));
                            break;
                        case 10:
                            if (!zzM(obj, i2)) {
                                break;
                            }
                            zzdjVar.zzd(i3, (zzdb) zzgz.f(obj, zzB & 1048575));
                            break;
                        case 11:
                            if (zzM(obj, i2)) {
                                c4 = zzgz.c(obj, zzB & 1048575);
                                zzdjVar.zzI(i3, c4);
                                break;
                            } else {
                                break;
                            }
                        case 12:
                            if (zzM(obj, i2)) {
                                c5 = zzgz.c(obj, zzB & 1048575);
                                zzdjVar.zzi(i3, c5);
                                break;
                            } else {
                                break;
                            }
                        case 13:
                            if (zzM(obj, i2)) {
                                c6 = zzgz.c(obj, zzB & 1048575);
                                zzdjVar.zzx(i3, c6);
                                break;
                            } else {
                                break;
                            }
                        case 14:
                            if (zzM(obj, i2)) {
                                d5 = zzgz.d(obj, zzB & 1048575);
                                zzdjVar.zzz(i3, d5);
                                break;
                            } else {
                                break;
                            }
                        case 15:
                            if (zzM(obj, i2)) {
                                c7 = zzgz.c(obj, zzB & 1048575);
                                zzdjVar.zzB(i3, c7);
                                break;
                            } else {
                                break;
                            }
                        case 16:
                            if (zzM(obj, i2)) {
                                d6 = zzgz.d(obj, zzB & 1048575);
                                zzdjVar.zzD(i3, d6);
                                break;
                            } else {
                                break;
                            }
                        case 17:
                            if (!zzM(obj, i2)) {
                                break;
                            }
                            zzdjVar.zzq(i3, zzgz.f(obj, zzB & 1048575), zzE(i2));
                            break;
                        case 18:
                            zzgd.zzL(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, false);
                            break;
                        case 19:
                            zzgd.zzP(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, false);
                            break;
                        case 20:
                            zzgd.zzS(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, false);
                            break;
                        case 21:
                            zzgd.zzaa(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, false);
                            break;
                        case 22:
                            zzgd.zzR(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, false);
                            break;
                        case 23:
                            zzgd.zzO(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, false);
                            break;
                        case 24:
                            zzgd.zzN(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, false);
                            break;
                        case 25:
                            zzgd.zzJ(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, false);
                            break;
                        case 26:
                            zzgd.zzY(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar);
                            break;
                        case 27:
                            zzgd.zzT(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, zzE(i2));
                            break;
                        case 28:
                            zzgd.zzK(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar);
                            break;
                        case 29:
                            zzgd.zzZ(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, false);
                            break;
                        case 30:
                            zzgd.zzM(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, false);
                            break;
                        case 31:
                            zzgd.zzU(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, false);
                            break;
                        case 32:
                            zzgd.zzV(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, false);
                            break;
                        case 33:
                            zzgd.zzW(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, false);
                            break;
                        case 34:
                            zzgd.zzX(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, false);
                            break;
                        case 35:
                            zzgd.zzL(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, true);
                            break;
                        case 36:
                            zzgd.zzP(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, true);
                            break;
                        case 37:
                            zzgd.zzS(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, true);
                            break;
                        case 38:
                            zzgd.zzaa(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, true);
                            break;
                        case 39:
                            zzgd.zzR(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, true);
                            break;
                        case 40:
                            zzgd.zzO(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, true);
                            break;
                        case 41:
                            zzgd.zzN(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, true);
                            break;
                        case 42:
                            zzgd.zzJ(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, true);
                            break;
                        case 43:
                            zzgd.zzZ(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, true);
                            break;
                        case 44:
                            zzgd.zzM(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, true);
                            break;
                        case 45:
                            zzgd.zzU(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, true);
                            break;
                        case 46:
                            zzgd.zzV(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, true);
                            break;
                        case 47:
                            zzgd.zzW(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, true);
                            break;
                        case 48:
                            zzgd.zzX(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, true);
                            break;
                        case 49:
                            zzgd.zzQ(this.zzc[i2], (List) zzgz.f(obj, zzB & 1048575), zzdjVar, zzE(i2));
                            break;
                        case 50:
                            zzS(zzdjVar, i3, zzgz.f(obj, zzB & 1048575), i2);
                            break;
                        case 51:
                            if (zzP(obj, i3, i2)) {
                                a2 = zzn(obj, zzB & 1048575);
                                zzdjVar.zzf(i3, a2);
                                break;
                            } else {
                                break;
                            }
                        case 52:
                            if (zzP(obj, i3, i2)) {
                                b2 = zzo(obj, zzB & 1048575);
                                zzdjVar.zzo(i3, b2);
                                break;
                            } else {
                                break;
                            }
                        case 53:
                            if (zzP(obj, i3, i2)) {
                                d2 = zzC(obj, zzB & 1048575);
                                zzdjVar.zzt(i3, d2);
                                break;
                            } else {
                                break;
                            }
                        case 54:
                            if (zzP(obj, i3, i2)) {
                                d3 = zzC(obj, zzB & 1048575);
                                zzdjVar.zzK(i3, d3);
                                break;
                            } else {
                                break;
                            }
                        case 55:
                            if (zzP(obj, i3, i2)) {
                                c2 = zzr(obj, zzB & 1048575);
                                zzdjVar.zzr(i3, c2);
                                break;
                            } else {
                                break;
                            }
                        case 56:
                            if (zzP(obj, i3, i2)) {
                                d4 = zzC(obj, zzB & 1048575);
                                zzdjVar.zzm(i3, d4);
                                break;
                            } else {
                                break;
                            }
                        case 57:
                            if (zzP(obj, i3, i2)) {
                                c3 = zzr(obj, zzB & 1048575);
                                zzdjVar.zzk(i3, c3);
                                break;
                            } else {
                                break;
                            }
                        case 58:
                            if (zzP(obj, i3, i2)) {
                                w = zzQ(obj, zzB & 1048575);
                                zzdjVar.zzb(i3, w);
                                break;
                            } else {
                                break;
                            }
                        case 59:
                            if (!zzP(obj, i3, i2)) {
                                break;
                            }
                            zzT(i3, zzgz.f(obj, zzB & 1048575), zzdjVar);
                            break;
                        case 60:
                            if (!zzP(obj, i3, i2)) {
                                break;
                            }
                            zzdjVar.zzv(i3, zzgz.f(obj, zzB & 1048575), zzE(i2));
                            break;
                        case 61:
                            if (!zzP(obj, i3, i2)) {
                                break;
                            }
                            zzdjVar.zzd(i3, (zzdb) zzgz.f(obj, zzB & 1048575));
                            break;
                        case 62:
                            if (zzP(obj, i3, i2)) {
                                c4 = zzr(obj, zzB & 1048575);
                                zzdjVar.zzI(i3, c4);
                                break;
                            } else {
                                break;
                            }
                        case 63:
                            if (zzP(obj, i3, i2)) {
                                c5 = zzr(obj, zzB & 1048575);
                                zzdjVar.zzi(i3, c5);
                                break;
                            } else {
                                break;
                            }
                        case 64:
                            if (zzP(obj, i3, i2)) {
                                c6 = zzr(obj, zzB & 1048575);
                                zzdjVar.zzx(i3, c6);
                                break;
                            } else {
                                break;
                            }
                        case 65:
                            if (zzP(obj, i3, i2)) {
                                d5 = zzC(obj, zzB & 1048575);
                                zzdjVar.zzz(i3, d5);
                                break;
                            } else {
                                break;
                            }
                        case 66:
                            if (zzP(obj, i3, i2)) {
                                c7 = zzr(obj, zzB & 1048575);
                                zzdjVar.zzB(i3, c7);
                                break;
                            } else {
                                break;
                            }
                        case 67:
                            if (zzP(obj, i3, i2)) {
                                d6 = zzC(obj, zzB & 1048575);
                                zzdjVar.zzD(i3, d6);
                                break;
                            } else {
                                break;
                            }
                        case 68:
                            if (!zzP(obj, i3, i2)) {
                                break;
                            }
                            zzdjVar.zzq(i3, zzgz.f(obj, zzB & 1048575), zzE(i2));
                            break;
                    }
                }
                while (entry != null) {
                    this.zzo.g(zzdjVar, entry);
                    entry = it.hasNext() ? (Map.Entry) it.next() : null;
                }
                zzgp zzgpVar = this.zzn;
                zzgpVar.j(zzgpVar.c(obj), zzdjVar);
            }
        }
        it = null;
        entry = null;
        length = this.zzc.length;
        while (i2 < length) {
        }
        while (entry != null) {
        }
        zzgp zzgpVar2 = this.zzn;
        zzgpVar2.j(zzgpVar2.c(obj), zzdjVar);
    }
}
