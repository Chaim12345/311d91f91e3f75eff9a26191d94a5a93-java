package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import sun.misc.Unsafe;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzlm<T> implements zzlu<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzmv.g();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzlj zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final int[] zzj;
    private final int zzk;
    private final int zzl;
    private final zzkx zzm;
    private final zzml zzn;
    private final zzjp zzo;
    private final zzlo zzp;
    private final zzle zzq;

    private zzlm(int[] iArr, Object[] objArr, int i2, int i3, zzlj zzljVar, boolean z, boolean z2, int[] iArr2, int i4, int i5, zzlo zzloVar, zzkx zzkxVar, zzml zzmlVar, zzjp zzjpVar, zzle zzleVar, byte[] bArr) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i2;
        this.zzf = i3;
        this.zzi = z;
        boolean z3 = false;
        if (zzjpVar != null && zzjpVar.c(zzljVar)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzj = iArr2;
        this.zzk = i4;
        this.zzl = i5;
        this.zzp = zzloVar;
        this.zzm = zzkxVar;
        this.zzn = zzmlVar;
        this.zzo = zzjpVar;
        this.zzg = zzljVar;
        this.zzq = zzleVar;
    }

    static zzmm b(Object obj) {
        zzkc zzkcVar = (zzkc) obj;
        zzmm zzmmVar = zzkcVar.zzc;
        if (zzmmVar == zzmm.zzc()) {
            zzmm b2 = zzmm.b();
            zzkcVar.zzc = b2;
            return b2;
        }
        return zzmmVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzlm c(Class cls, zzlg zzlgVar, zzlo zzloVar, zzkx zzkxVar, zzml zzmlVar, zzjp zzjpVar, zzle zzleVar) {
        if (zzlgVar instanceof zzlt) {
            return d((zzlt) zzlgVar, zzloVar, zzkxVar, zzmlVar, zzjpVar, zzleVar);
        }
        zzmi zzmiVar = (zzmi) zzlgVar;
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
    static zzlm d(zzlt zzltVar, zzlo zzloVar, zzkx zzkxVar, zzml zzmlVar, zzjp zzjpVar, zzle zzleVar) {
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
        boolean z = zzltVar.zzc() == 2;
        String a2 = zzltVar.a();
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
        Object[] b2 = zzltVar.b();
        Class<?> cls2 = zzltVar.zza().getClass();
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
        return new zzlm(iArr3, objArr2, charAt, i5, zzltVar.zza(), z, false, iArr, charAt3, i60, zzloVar, zzkxVar, zzmlVar, zzjpVar, zzleVar, null);
    }

    private static int zzA(int i2) {
        return (i2 >>> 20) & 255;
    }

    private final int zzB(int i2) {
        return this.zzc[i2 + 1];
    }

    private static long zzC(Object obj, long j2) {
        return ((Long) zzmv.f(obj, j2)).longValue();
    }

    private final zzkg zzD(int i2) {
        int i3 = i2 / 3;
        return (zzkg) this.zzd[i3 + i3 + 1];
    }

    private final zzlu zzE(int i2) {
        int i3 = i2 / 3;
        int i4 = i3 + i3;
        zzlu zzluVar = (zzlu) this.zzd[i4];
        if (zzluVar != null) {
            return zzluVar;
        }
        zzlu zzb2 = zzlr.zza().zzb((Class) this.zzd[i4 + 1]);
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
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private final void zzH(Object obj, Object obj2, int i2) {
        long zzB = zzB(i2) & 1048575;
        if (zzO(obj2, i2)) {
            Object f2 = zzmv.f(obj, zzB);
            Object f3 = zzmv.f(obj2, zzB);
            if (f2 != null && f3 != null) {
                f3 = zzkk.d(f2, f3);
            } else if (f3 == null) {
                return;
            }
            zzmv.s(obj, zzB, f3);
            zzJ(obj, i2);
        }
    }

    private final void zzI(Object obj, Object obj2, int i2) {
        int zzB = zzB(i2);
        int i3 = this.zzc[i2];
        long j2 = zzB & 1048575;
        if (zzR(obj2, i3, i2)) {
            Object f2 = zzR(obj, i3, i2) ? zzmv.f(obj, j2) : null;
            Object f3 = zzmv.f(obj2, j2);
            if (f2 != null && f3 != null) {
                f3 = zzkk.d(f2, f3);
            } else if (f3 == null) {
                return;
            }
            zzmv.s(obj, j2, f3);
            zzK(obj, i3, i2);
        }
    }

    private final void zzJ(Object obj, int i2) {
        int zzy = zzy(i2);
        long j2 = 1048575 & zzy;
        if (j2 == 1048575) {
            return;
        }
        zzmv.q(obj, j2, (1 << (zzy >>> 20)) | zzmv.c(obj, j2));
    }

    private final void zzK(Object obj, int i2, int i3) {
        zzmv.q(obj, zzy(i3) & 1048575, i2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final void zzL(Object obj, zznd zzndVar) {
        int i2;
        boolean z;
        if (this.zzh) {
            this.zzo.a(obj);
            throw null;
        }
        int length = this.zzc.length;
        Unsafe unsafe = zzb;
        int i3 = 1048575;
        int i4 = 1048575;
        int i5 = 0;
        int i6 = 0;
        while (i5 < length) {
            int zzB = zzB(i5);
            int[] iArr = this.zzc;
            int i7 = iArr[i5];
            int zzA = zzA(zzB);
            if (zzA <= 17) {
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
            long j2 = zzB & i3;
            switch (zzA) {
                case 0:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzndVar.zzf(i7, zzmv.a(obj, j2));
                        break;
                    }
                case 1:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzndVar.zzo(i7, zzmv.b(obj, j2));
                        break;
                    }
                case 2:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzndVar.zzt(i7, unsafe.getLong(obj, j2));
                        break;
                    }
                case 3:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzndVar.zzJ(i7, unsafe.getLong(obj, j2));
                        break;
                    }
                case 4:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzndVar.zzr(i7, unsafe.getInt(obj, j2));
                        break;
                    }
                case 5:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzndVar.zzm(i7, unsafe.getLong(obj, j2));
                        break;
                    }
                case 6:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzndVar.zzk(i7, unsafe.getInt(obj, j2));
                        break;
                    }
                case 7:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzndVar.zzb(i7, zzmv.w(obj, j2));
                        break;
                    }
                case 8:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzT(i7, unsafe.getObject(obj, j2), zzndVar);
                        break;
                    }
                case 9:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzndVar.zzv(i7, unsafe.getObject(obj, j2), zzE(i5));
                        break;
                    }
                case 10:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzndVar.zzd(i7, (zzjb) unsafe.getObject(obj, j2));
                        break;
                    }
                case 11:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzndVar.zzH(i7, unsafe.getInt(obj, j2));
                        break;
                    }
                case 12:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzndVar.zzi(i7, unsafe.getInt(obj, j2));
                        break;
                    }
                case 13:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzndVar.zzw(i7, unsafe.getInt(obj, j2));
                        break;
                    }
                case 14:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzndVar.zzy(i7, unsafe.getLong(obj, j2));
                        break;
                    }
                case 15:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzndVar.zzA(i7, unsafe.getInt(obj, j2));
                        break;
                    }
                case 16:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzndVar.zzC(i7, unsafe.getLong(obj, j2));
                        break;
                    }
                case 17:
                    if ((i6 & i2) == 0) {
                        break;
                    } else {
                        zzndVar.zzq(i7, unsafe.getObject(obj, j2), zzE(i5));
                        break;
                    }
                case 18:
                    zzlw.zzJ(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, false);
                    break;
                case 19:
                    zzlw.zzN(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, false);
                    break;
                case 20:
                    zzlw.zzQ(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, false);
                    break;
                case 21:
                    zzlw.zzY(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, false);
                    break;
                case 22:
                    zzlw.zzP(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, false);
                    break;
                case 23:
                    zzlw.zzM(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, false);
                    break;
                case 24:
                    zzlw.zzL(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, false);
                    break;
                case 25:
                    zzlw.zzH(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, false);
                    break;
                case 26:
                    zzlw.zzW(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar);
                    break;
                case 27:
                    zzlw.zzR(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, zzE(i5));
                    break;
                case 28:
                    zzlw.zzI(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar);
                    break;
                case 29:
                    z = false;
                    zzlw.zzX(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, false);
                    break;
                case 30:
                    z = false;
                    zzlw.zzK(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, false);
                    break;
                case 31:
                    z = false;
                    zzlw.zzS(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, false);
                    break;
                case 32:
                    z = false;
                    zzlw.zzT(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, false);
                    break;
                case 33:
                    z = false;
                    zzlw.zzU(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, false);
                    break;
                case 34:
                    z = false;
                    zzlw.zzV(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, false);
                    break;
                case 35:
                    zzlw.zzJ(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, true);
                    break;
                case 36:
                    zzlw.zzN(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, true);
                    break;
                case 37:
                    zzlw.zzQ(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, true);
                    break;
                case 38:
                    zzlw.zzY(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, true);
                    break;
                case 39:
                    zzlw.zzP(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, true);
                    break;
                case 40:
                    zzlw.zzM(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, true);
                    break;
                case 41:
                    zzlw.zzL(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, true);
                    break;
                case 42:
                    zzlw.zzH(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, true);
                    break;
                case 43:
                    zzlw.zzX(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, true);
                    break;
                case 44:
                    zzlw.zzK(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, true);
                    break;
                case 45:
                    zzlw.zzS(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, true);
                    break;
                case 46:
                    zzlw.zzT(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, true);
                    break;
                case 47:
                    zzlw.zzU(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, true);
                    break;
                case 48:
                    zzlw.zzV(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, true);
                    break;
                case 49:
                    zzlw.zzO(this.zzc[i5], (List) unsafe.getObject(obj, j2), zzndVar, zzE(i5));
                    break;
                case 50:
                    zzM(zzndVar, i7, unsafe.getObject(obj, j2), i5);
                    break;
                case 51:
                    if (zzR(obj, i7, i5)) {
                        zzndVar.zzf(i7, zzn(obj, j2));
                    }
                    break;
                case 52:
                    if (zzR(obj, i7, i5)) {
                        zzndVar.zzo(i7, zzo(obj, j2));
                    }
                    break;
                case 53:
                    if (zzR(obj, i7, i5)) {
                        zzndVar.zzt(i7, zzC(obj, j2));
                    }
                    break;
                case 54:
                    if (zzR(obj, i7, i5)) {
                        zzndVar.zzJ(i7, zzC(obj, j2));
                    }
                    break;
                case 55:
                    if (zzR(obj, i7, i5)) {
                        zzndVar.zzr(i7, zzr(obj, j2));
                    }
                    break;
                case 56:
                    if (zzR(obj, i7, i5)) {
                        zzndVar.zzm(i7, zzC(obj, j2));
                    }
                    break;
                case 57:
                    if (zzR(obj, i7, i5)) {
                        zzndVar.zzk(i7, zzr(obj, j2));
                    }
                    break;
                case 58:
                    if (zzR(obj, i7, i5)) {
                        zzndVar.zzb(i7, zzS(obj, j2));
                    }
                    break;
                case 59:
                    if (zzR(obj, i7, i5)) {
                        zzT(i7, unsafe.getObject(obj, j2), zzndVar);
                    }
                    break;
                case 60:
                    if (zzR(obj, i7, i5)) {
                        zzndVar.zzv(i7, unsafe.getObject(obj, j2), zzE(i5));
                    }
                    break;
                case 61:
                    if (zzR(obj, i7, i5)) {
                        zzndVar.zzd(i7, (zzjb) unsafe.getObject(obj, j2));
                    }
                    break;
                case 62:
                    if (zzR(obj, i7, i5)) {
                        zzndVar.zzH(i7, zzr(obj, j2));
                    }
                    break;
                case 63:
                    if (zzR(obj, i7, i5)) {
                        zzndVar.zzi(i7, zzr(obj, j2));
                    }
                    break;
                case 64:
                    if (zzR(obj, i7, i5)) {
                        zzndVar.zzw(i7, zzr(obj, j2));
                    }
                    break;
                case 65:
                    if (zzR(obj, i7, i5)) {
                        zzndVar.zzy(i7, zzC(obj, j2));
                    }
                    break;
                case 66:
                    if (zzR(obj, i7, i5)) {
                        zzndVar.zzA(i7, zzr(obj, j2));
                    }
                    break;
                case 67:
                    if (zzR(obj, i7, i5)) {
                        zzndVar.zzC(i7, zzC(obj, j2));
                    }
                    break;
                case 68:
                    if (zzR(obj, i7, i5)) {
                        zzndVar.zzq(i7, unsafe.getObject(obj, j2), zzE(i5));
                    }
                    break;
            }
            i5 += 3;
            i3 = 1048575;
        }
        zzml zzmlVar = this.zzn;
        zzmlVar.i(zzmlVar.c(obj), zzndVar);
    }

    private final void zzM(zznd zzndVar, int i2, Object obj, int i3) {
        if (obj == null) {
            return;
        }
        zzlc zzlcVar = (zzlc) zzF(i3);
        throw null;
    }

    private final boolean zzN(Object obj, Object obj2, int i2) {
        return zzO(obj, i2) == zzO(obj2, i2);
    }

    private final boolean zzO(Object obj, int i2) {
        int zzy = zzy(i2);
        long j2 = zzy & 1048575;
        if (j2 != 1048575) {
            return (zzmv.c(obj, j2) & (1 << (zzy >>> 20))) != 0;
        }
        int zzB = zzB(i2);
        long j3 = zzB & 1048575;
        switch (zzA(zzB)) {
            case 0:
                return Double.doubleToRawLongBits(zzmv.a(obj, j3)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzmv.b(obj, j3)) != 0;
            case 2:
                return zzmv.d(obj, j3) != 0;
            case 3:
                return zzmv.d(obj, j3) != 0;
            case 4:
                return zzmv.c(obj, j3) != 0;
            case 5:
                return zzmv.d(obj, j3) != 0;
            case 6:
                return zzmv.c(obj, j3) != 0;
            case 7:
                return zzmv.w(obj, j3);
            case 8:
                Object f2 = zzmv.f(obj, j3);
                if (f2 instanceof String) {
                    return !((String) f2).isEmpty();
                } else if (f2 instanceof zzjb) {
                    return !zzjb.zzb.equals(f2);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return zzmv.f(obj, j3) != null;
            case 10:
                return !zzjb.zzb.equals(zzmv.f(obj, j3));
            case 11:
                return zzmv.c(obj, j3) != 0;
            case 12:
                return zzmv.c(obj, j3) != 0;
            case 13:
                return zzmv.c(obj, j3) != 0;
            case 14:
                return zzmv.d(obj, j3) != 0;
            case 15:
                return zzmv.c(obj, j3) != 0;
            case 16:
                return zzmv.d(obj, j3) != 0;
            case 17:
                return zzmv.f(obj, j3) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzP(Object obj, int i2, int i3, int i4, int i5) {
        return i3 == 1048575 ? zzO(obj, i2) : (i4 & i5) != 0;
    }

    private static boolean zzQ(Object obj, int i2, zzlu zzluVar) {
        return zzluVar.zzk(zzmv.f(obj, i2 & 1048575));
    }

    private final boolean zzR(Object obj, int i2, int i3) {
        return zzmv.c(obj, (long) (zzy(i3) & 1048575)) == i2;
    }

    private static boolean zzS(Object obj, long j2) {
        return ((Boolean) zzmv.f(obj, j2)).booleanValue();
    }

    private static final void zzT(int i2, Object obj, zznd zzndVar) {
        if (obj instanceof String) {
            zzndVar.zzF(i2, (String) obj);
        } else {
            zzndVar.zzd(i2, (zzjb) obj);
        }
    }

    private static double zzn(Object obj, long j2) {
        return ((Double) zzmv.f(obj, j2)).doubleValue();
    }

    private static float zzo(Object obj, long j2) {
        return ((Float) zzmv.f(obj, j2)).floatValue();
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
        r4 = com.google.android.gms.internal.measurement.zzjj.zzz(r11) + com.google.android.gms.internal.measurement.zzjj.zzA(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x008c, code lost:
        if (zzR(r17, r11, r5) != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0094, code lost:
        if (zzR(r17, r11, r5) != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x009c, code lost:
        if (zzR(r17, r11, r5) != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0118, code lost:
        if (zzR(r17, r11, r5) != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x011f, code lost:
        if (zzR(r17, r11, r5) != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0126, code lost:
        if (zzR(r17, r11, r5) != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0128, code lost:
        r3 = zzr(r17, r3);
        r4 = com.google.android.gms.internal.measurement.zzjj.zzA(r11 << 3);
        r3 = com.google.android.gms.internal.measurement.zzjj.zzv(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x013c, code lost:
        if (zzR(r17, r11, r5) != false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0143, code lost:
        if (zzR(r17, r11, r5) != false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0145, code lost:
        r6 = r6 + (com.google.android.gms.internal.measurement.zzjj.zzA(r11 << 3) + com.google.android.gms.internal.measurement.zzjj.zzB(zzC(r17, r3)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x015b, code lost:
        if (zzR(r17, r11, r5) != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x015d, code lost:
        r3 = com.google.android.gms.internal.measurement.zzjj.zzA(r11 << 3) + 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x016b, code lost:
        if (zzR(r17, r11, r5) != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x016d, code lost:
        r3 = com.google.android.gms.internal.measurement.zzjj.zzA(r11 << 3) + 8;
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
        int zzA;
        Object object;
        int zzA2;
        int zzy;
        int zzA3;
        int i3;
        int A;
        boolean z;
        int j2;
        int o2;
        int zzz;
        Object object2;
        int zzA4;
        int zzr;
        Unsafe unsafe = zzb;
        int i4 = 1048575;
        int i5 = 1048575;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i6 < this.zzc.length) {
            int zzB = zzB(i6);
            int[] iArr = this.zzc;
            int i9 = iArr[i6];
            int zzA5 = zzA(zzB);
            if (zzA5 <= 17) {
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
            long j3 = zzB & i4;
            switch (zzA5) {
                case 0:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    zzA = zzjj.zzA(i9 << 3) + 8;
                    i7 += zzA;
                    break;
                case 1:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    zzA = zzjj.zzA(i9 << 3) + 4;
                    i7 += zzA;
                    break;
                case 2:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    i7 += zzjj.zzA(i9 << 3) + zzjj.zzB(unsafe.getLong(obj, j3));
                    break;
                case 3:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    i7 += zzjj.zzA(i9 << 3) + zzjj.zzB(unsafe.getLong(obj, j3));
                    break;
                case 4:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    int i12 = unsafe.getInt(obj, j3);
                    zzA2 = zzjj.zzA(i9 << 3);
                    zzy = zzjj.zzv(i12);
                    zzA3 = zzA2 + zzy;
                    i7 += zzA3;
                    break;
                case 5:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    zzA = zzjj.zzA(i9 << 3) + 8;
                    i7 += zzA;
                    break;
                case 6:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    zzA = zzjj.zzA(i9 << 3) + 4;
                    i7 += zzA;
                    break;
                case 7:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        zzA = zzjj.zzA(i9 << 3) + 1;
                        i7 += zzA;
                        break;
                    }
                case 8:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        object = unsafe.getObject(obj, j3);
                        if (!(object instanceof zzjb)) {
                            zzA2 = zzjj.zzA(i9 << 3);
                            zzy = zzjj.zzy((String) object);
                            zzA3 = zzA2 + zzy;
                            i7 += zzA3;
                            break;
                        }
                        int zzA6 = zzjj.zzA(i9 << 3);
                        int zzd = ((zzjb) object).zzd();
                        zzA3 = zzA6 + zzjj.zzA(zzd) + zzd;
                        i7 += zzA3;
                    }
                case 9:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        zzA = zzlw.u(i9, unsafe.getObject(obj, j3), zzE(i6));
                        i7 += zzA;
                        break;
                    }
                case 10:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        object = unsafe.getObject(obj, j3);
                        int zzA62 = zzjj.zzA(i9 << 3);
                        int zzd2 = ((zzjb) object).zzd();
                        zzA3 = zzA62 + zzjj.zzA(zzd2) + zzd2;
                        i7 += zzA3;
                        break;
                    }
                case 11:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        i3 = unsafe.getInt(obj, j3);
                        zzA2 = zzjj.zzA(i9 << 3);
                        zzy = zzjj.zzA(i3);
                        zzA3 = zzA2 + zzy;
                        i7 += zzA3;
                        break;
                    }
                case 12:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    int i122 = unsafe.getInt(obj, j3);
                    zzA2 = zzjj.zzA(i9 << 3);
                    zzy = zzjj.zzv(i122);
                    zzA3 = zzA2 + zzy;
                    i7 += zzA3;
                    break;
                case 13:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    zzA = zzjj.zzA(i9 << 3) + 4;
                    i7 += zzA;
                    break;
                case 14:
                    if ((i8 & i2) == 0) {
                        break;
                    }
                    zzA = zzjj.zzA(i9 << 3) + 8;
                    i7 += zzA;
                    break;
                case 15:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        int i13 = unsafe.getInt(obj, j3);
                        zzA2 = zzjj.zzA(i9 << 3);
                        i3 = (i13 >> 31) ^ (i13 + i13);
                        zzy = zzjj.zzA(i3);
                        zzA3 = zzA2 + zzy;
                        i7 += zzA3;
                        break;
                    }
                case 16:
                    if ((i2 & i8) == 0) {
                        break;
                    } else {
                        long j4 = unsafe.getLong(obj, j3);
                        i7 += zzjj.zzA(i9 << 3) + zzjj.zzB((j4 >> 63) ^ (j4 + j4));
                        break;
                    }
                case 17:
                    if ((i8 & i2) == 0) {
                        break;
                    } else {
                        zzA = zzjj.c(i9, (zzlj) unsafe.getObject(obj, j3), zzE(i6));
                        i7 += zzA;
                        break;
                    }
                case 18:
                case 23:
                    zzA = zzlw.n(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += zzA;
                    break;
                case 19:
                case 24:
                    zzA = zzlw.l(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += zzA;
                    break;
                case 20:
                    zzA = zzlw.s(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += zzA;
                    break;
                case 21:
                    zzA = zzlw.D(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += zzA;
                    break;
                case 22:
                    zzA = zzlw.q(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += zzA;
                    break;
                case 25:
                    zzA = zzlw.f(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += zzA;
                    break;
                case 26:
                    A = zzlw.A(i9, (List) unsafe.getObject(obj, j3));
                    i7 += A;
                    break;
                case 27:
                    A = zzlw.v(i9, (List) unsafe.getObject(obj, j3), zzE(i6));
                    i7 += A;
                    break;
                case 28:
                    A = zzlw.i(i9, (List) unsafe.getObject(obj, j3));
                    i7 += A;
                    break;
                case 29:
                    A = zzlw.B(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += A;
                    break;
                case 30:
                    z = false;
                    j2 = zzlw.j(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += j2;
                    break;
                case 31:
                    z = false;
                    j2 = zzlw.l(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += j2;
                    break;
                case 32:
                    z = false;
                    j2 = zzlw.n(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += j2;
                    break;
                case 33:
                    z = false;
                    j2 = zzlw.w(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += j2;
                    break;
                case 34:
                    z = false;
                    j2 = zzlw.y(i9, (List) unsafe.getObject(obj, j3), false);
                    i7 += j2;
                    break;
                case 35:
                    o2 = zzlw.o((List) unsafe.getObject(obj, j3));
                    break;
                case 36:
                    o2 = zzlw.m((List) unsafe.getObject(obj, j3));
                    break;
                case 37:
                    o2 = zzlw.t((List) unsafe.getObject(obj, j3));
                    break;
                case 38:
                    o2 = zzlw.E((List) unsafe.getObject(obj, j3));
                    break;
                case 39:
                    o2 = zzlw.r((List) unsafe.getObject(obj, j3));
                    break;
                case 40:
                    o2 = zzlw.o((List) unsafe.getObject(obj, j3));
                    break;
                case 41:
                    o2 = zzlw.m((List) unsafe.getObject(obj, j3));
                    break;
                case 42:
                    o2 = zzlw.h((List) unsafe.getObject(obj, j3));
                    break;
                case 43:
                    o2 = zzlw.C((List) unsafe.getObject(obj, j3));
                    break;
                case 44:
                    o2 = zzlw.k((List) unsafe.getObject(obj, j3));
                    break;
                case 45:
                    o2 = zzlw.m((List) unsafe.getObject(obj, j3));
                    break;
                case 46:
                    o2 = zzlw.o((List) unsafe.getObject(obj, j3));
                    break;
                case 47:
                    o2 = zzlw.x((List) unsafe.getObject(obj, j3));
                    break;
                case 48:
                    o2 = zzlw.z((List) unsafe.getObject(obj, j3));
                    break;
                case 49:
                    A = zzlw.p(i9, (List) unsafe.getObject(obj, j3), zzE(i6));
                    i7 += A;
                    break;
                case 50:
                    zzle.zza(i9, unsafe.getObject(obj, j3), zzF(i6));
                    break;
                case 58:
                    if (zzR(obj, i9, i6)) {
                        A = zzjj.zzA(i9 << 3) + 1;
                        i7 += A;
                    }
                    break;
                case 59:
                    if (zzR(obj, i9, i6)) {
                        object2 = unsafe.getObject(obj, j3);
                        if (!(object2 instanceof zzjb)) {
                            zzz = zzjj.zzA(i9 << 3);
                            o2 = zzjj.zzy((String) object2);
                            zzA4 = zzz + o2;
                            i7 += zzA4;
                        }
                        int zzA7 = zzjj.zzA(i9 << 3);
                        int zzd3 = ((zzjb) object2).zzd();
                        zzA4 = zzA7 + zzjj.zzA(zzd3) + zzd3;
                        i7 += zzA4;
                    }
                    break;
                case 60:
                    if (zzR(obj, i9, i6)) {
                        A = zzlw.u(i9, unsafe.getObject(obj, j3), zzE(i6));
                        i7 += A;
                    }
                    break;
                case 61:
                    if (zzR(obj, i9, i6)) {
                        object2 = unsafe.getObject(obj, j3);
                        int zzA72 = zzjj.zzA(i9 << 3);
                        int zzd32 = ((zzjb) object2).zzd();
                        zzA4 = zzA72 + zzjj.zzA(zzd32) + zzd32;
                        i7 += zzA4;
                    }
                    break;
                case 62:
                    if (zzR(obj, i9, i6)) {
                        zzr = zzr(obj, j3);
                        zzz = zzjj.zzA(i9 << 3);
                        o2 = zzjj.zzA(zzr);
                        zzA4 = zzz + o2;
                        i7 += zzA4;
                    }
                    break;
                case 66:
                    if (zzR(obj, i9, i6)) {
                        int zzr2 = zzr(obj, j3);
                        zzz = zzjj.zzA(i9 << 3);
                        zzr = (zzr2 >> 31) ^ (zzr2 + zzr2);
                        o2 = zzjj.zzA(zzr);
                        zzA4 = zzz + o2;
                        i7 += zzA4;
                    }
                    break;
                case 67:
                    if (zzR(obj, i9, i6)) {
                        long zzC = zzC(obj, j3);
                        i7 += zzjj.zzA(i9 << 3) + zzjj.zzB((zzC >> 63) ^ (zzC + zzC));
                    }
                    break;
                case 68:
                    if (zzR(obj, i9, i6)) {
                        A = zzjj.c(i9, (zzlj) unsafe.getObject(obj, j3), zzE(i6));
                        i7 += A;
                    }
                    break;
            }
            i6 += 3;
            i4 = 1048575;
        }
        zzml zzmlVar = this.zzn;
        int a2 = i7 + zzmlVar.a(zzmlVar.c(obj));
        if (this.zzh) {
            this.zzo.a(obj);
            throw null;
        }
        return a2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:162:0x030e, code lost:
        if ((r4 instanceof com.google.android.gms.internal.measurement.zzjb) != false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0311, code lost:
        r5 = com.google.android.gms.internal.measurement.zzjj.zzA(r6 << 3);
        r4 = com.google.android.gms.internal.measurement.zzjj.zzy((java.lang.String) r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0097, code lost:
        if ((r4 instanceof com.google.android.gms.internal.measurement.zzjb) != false) goto L53;
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
        int zzz;
        int zzA;
        Unsafe unsafe = zzb;
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzc.length; i3 += 3) {
            int zzB = zzB(i3);
            int zzA2 = zzA(zzB);
            int i4 = this.zzc[i3];
            long j2 = zzB & 1048575;
            if (zzA2 >= zzju.zzJ.zza() && zzA2 <= zzju.zzW.zza()) {
                int i5 = this.zzc[i3 + 2];
            }
            switch (zzA2) {
                case 0:
                    if (!zzO(obj, i3)) {
                        continue;
                    }
                    n2 = zzjj.zzA(i4 << 3) + 8;
                    break;
                case 1:
                    if (!zzO(obj, i3)) {
                        continue;
                    }
                    n2 = zzjj.zzA(i4 << 3) + 4;
                    break;
                case 2:
                    if (!zzO(obj, i3)) {
                        continue;
                    }
                    d2 = zzmv.d(obj, j2);
                    i2 += zzjj.zzA(i4 << 3) + zzjj.zzB(d2);
                case 3:
                    if (!zzO(obj, i3)) {
                        continue;
                    }
                    d2 = zzmv.d(obj, j2);
                    i2 += zzjj.zzA(i4 << 3) + zzjj.zzB(d2);
                case 4:
                    if (!zzO(obj, i3)) {
                        continue;
                    }
                    c3 = zzmv.c(obj, j2);
                    zzz = zzjj.zzA(i4 << 3);
                    o2 = zzjj.zzv(c3);
                    zzA = zzz + o2;
                    i2 += zzA;
                case 5:
                    if (!zzO(obj, i3)) {
                        continue;
                    }
                    n2 = zzjj.zzA(i4 << 3) + 8;
                    break;
                case 6:
                    if (!zzO(obj, i3)) {
                        continue;
                    }
                    n2 = zzjj.zzA(i4 << 3) + 4;
                    break;
                case 7:
                    if (!zzO(obj, i3)) {
                        continue;
                    }
                    n2 = zzjj.zzA(i4 << 3) + 1;
                    break;
                case 8:
                    if (zzO(obj, i3)) {
                        f2 = zzmv.f(obj, j2);
                        break;
                    } else {
                        continue;
                    }
                case 9:
                    if (!zzO(obj, i3)) {
                        continue;
                    }
                    n2 = zzlw.u(i4, zzmv.f(obj, j2), zzE(i3));
                    break;
                case 10:
                    if (!zzO(obj, i3)) {
                        continue;
                    }
                    f2 = zzmv.f(obj, j2);
                    int zzA3 = zzjj.zzA(i4 << 3);
                    int zzd = ((zzjb) f2).zzd();
                    zzA = zzA3 + zzjj.zzA(zzd) + zzd;
                    i2 += zzA;
                case 11:
                    if (zzO(obj, i3)) {
                        c2 = zzmv.c(obj, j2);
                        zzz = zzjj.zzA(i4 << 3);
                        o2 = zzjj.zzA(c2);
                        zzA = zzz + o2;
                        i2 += zzA;
                    } else {
                        continue;
                    }
                case 12:
                    if (!zzO(obj, i3)) {
                        continue;
                    }
                    c3 = zzmv.c(obj, j2);
                    zzz = zzjj.zzA(i4 << 3);
                    o2 = zzjj.zzv(c3);
                    zzA = zzz + o2;
                    i2 += zzA;
                case 13:
                    if (!zzO(obj, i3)) {
                        continue;
                    }
                    n2 = zzjj.zzA(i4 << 3) + 4;
                    break;
                case 14:
                    if (!zzO(obj, i3)) {
                        continue;
                    }
                    n2 = zzjj.zzA(i4 << 3) + 8;
                    break;
                case 15:
                    if (zzO(obj, i3)) {
                        c4 = zzmv.c(obj, j2);
                        zzz = zzjj.zzA(i4 << 3);
                        c2 = (c4 >> 31) ^ (c4 + c4);
                        o2 = zzjj.zzA(c2);
                        zzA = zzz + o2;
                        i2 += zzA;
                    } else {
                        continue;
                    }
                case 16:
                    if (zzO(obj, i3)) {
                        d3 = zzmv.d(obj, j2);
                        zzz = zzjj.zzA(i4 << 3);
                        o2 = zzjj.zzB((d3 >> 63) ^ (d3 + d3));
                        zzA = zzz + o2;
                        i2 += zzA;
                    } else {
                        continue;
                    }
                case 17:
                    if (!zzO(obj, i3)) {
                        continue;
                    }
                    n2 = zzjj.c(i4, (zzlj) zzmv.f(obj, j2), zzE(i3));
                    break;
                case 18:
                case 23:
                case 32:
                    n2 = zzlw.n(i4, (List) zzmv.f(obj, j2), false);
                    break;
                case 19:
                case 24:
                case 31:
                    n2 = zzlw.l(i4, (List) zzmv.f(obj, j2), false);
                    break;
                case 20:
                    n2 = zzlw.s(i4, (List) zzmv.f(obj, j2), false);
                    break;
                case 21:
                    n2 = zzlw.D(i4, (List) zzmv.f(obj, j2), false);
                    break;
                case 22:
                    n2 = zzlw.q(i4, (List) zzmv.f(obj, j2), false);
                    break;
                case 25:
                    n2 = zzlw.f(i4, (List) zzmv.f(obj, j2), false);
                    break;
                case 26:
                    n2 = zzlw.A(i4, (List) zzmv.f(obj, j2));
                    break;
                case 27:
                    n2 = zzlw.v(i4, (List) zzmv.f(obj, j2), zzE(i3));
                    break;
                case 28:
                    n2 = zzlw.i(i4, (List) zzmv.f(obj, j2));
                    break;
                case 29:
                    n2 = zzlw.B(i4, (List) zzmv.f(obj, j2), false);
                    break;
                case 30:
                    n2 = zzlw.j(i4, (List) zzmv.f(obj, j2), false);
                    break;
                case 33:
                    n2 = zzlw.w(i4, (List) zzmv.f(obj, j2), false);
                    break;
                case 34:
                    n2 = zzlw.y(i4, (List) zzmv.f(obj, j2), false);
                    break;
                case 35:
                    o2 = zzlw.o((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzz = zzjj.zzz(i4) + zzjj.zzA(o2);
                    zzA = zzz + o2;
                    i2 += zzA;
                case 36:
                    o2 = zzlw.m((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzz = zzjj.zzz(i4) + zzjj.zzA(o2);
                    zzA = zzz + o2;
                    i2 += zzA;
                case 37:
                    o2 = zzlw.t((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzz = zzjj.zzz(i4) + zzjj.zzA(o2);
                    zzA = zzz + o2;
                    i2 += zzA;
                case 38:
                    o2 = zzlw.E((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzz = zzjj.zzz(i4) + zzjj.zzA(o2);
                    zzA = zzz + o2;
                    i2 += zzA;
                case 39:
                    o2 = zzlw.r((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzz = zzjj.zzz(i4) + zzjj.zzA(o2);
                    zzA = zzz + o2;
                    i2 += zzA;
                case 40:
                    o2 = zzlw.o((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzz = zzjj.zzz(i4) + zzjj.zzA(o2);
                    zzA = zzz + o2;
                    i2 += zzA;
                case 41:
                    o2 = zzlw.m((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzz = zzjj.zzz(i4) + zzjj.zzA(o2);
                    zzA = zzz + o2;
                    i2 += zzA;
                case 42:
                    o2 = zzlw.h((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzz = zzjj.zzz(i4) + zzjj.zzA(o2);
                    zzA = zzz + o2;
                    i2 += zzA;
                case 43:
                    o2 = zzlw.C((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzz = zzjj.zzz(i4) + zzjj.zzA(o2);
                    zzA = zzz + o2;
                    i2 += zzA;
                case 44:
                    o2 = zzlw.k((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzz = zzjj.zzz(i4) + zzjj.zzA(o2);
                    zzA = zzz + o2;
                    i2 += zzA;
                case 45:
                    o2 = zzlw.m((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzz = zzjj.zzz(i4) + zzjj.zzA(o2);
                    zzA = zzz + o2;
                    i2 += zzA;
                case 46:
                    o2 = zzlw.o((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzz = zzjj.zzz(i4) + zzjj.zzA(o2);
                    zzA = zzz + o2;
                    i2 += zzA;
                case 47:
                    o2 = zzlw.x((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzz = zzjj.zzz(i4) + zzjj.zzA(o2);
                    zzA = zzz + o2;
                    i2 += zzA;
                case 48:
                    o2 = zzlw.z((List) unsafe.getObject(obj, j2));
                    if (o2 <= 0) {
                        continue;
                    }
                    zzz = zzjj.zzz(i4) + zzjj.zzA(o2);
                    zzA = zzz + o2;
                    i2 += zzA;
                case 49:
                    n2 = zzlw.p(i4, (List) zzmv.f(obj, j2), zzE(i3));
                    break;
                case 50:
                    zzle.zza(i4, zzmv.f(obj, j2), zzF(i3));
                    continue;
                case 51:
                    if (!zzR(obj, i4, i3)) {
                        continue;
                    }
                    n2 = zzjj.zzA(i4 << 3) + 8;
                    break;
                case 52:
                    if (!zzR(obj, i4, i3)) {
                        continue;
                    }
                    n2 = zzjj.zzA(i4 << 3) + 4;
                    break;
                case 53:
                    if (!zzR(obj, i4, i3)) {
                        continue;
                    }
                    d2 = zzC(obj, j2);
                    i2 += zzjj.zzA(i4 << 3) + zzjj.zzB(d2);
                case 54:
                    if (!zzR(obj, i4, i3)) {
                        continue;
                    }
                    d2 = zzC(obj, j2);
                    i2 += zzjj.zzA(i4 << 3) + zzjj.zzB(d2);
                case 55:
                    if (!zzR(obj, i4, i3)) {
                        continue;
                    }
                    c3 = zzr(obj, j2);
                    zzz = zzjj.zzA(i4 << 3);
                    o2 = zzjj.zzv(c3);
                    zzA = zzz + o2;
                    i2 += zzA;
                case 56:
                    if (!zzR(obj, i4, i3)) {
                        continue;
                    }
                    n2 = zzjj.zzA(i4 << 3) + 8;
                    break;
                case 57:
                    if (!zzR(obj, i4, i3)) {
                        continue;
                    }
                    n2 = zzjj.zzA(i4 << 3) + 4;
                    break;
                case 58:
                    if (!zzR(obj, i4, i3)) {
                        continue;
                    }
                    n2 = zzjj.zzA(i4 << 3) + 1;
                    break;
                case 59:
                    if (zzR(obj, i4, i3)) {
                        f2 = zzmv.f(obj, j2);
                        break;
                    } else {
                        continue;
                    }
                case 60:
                    if (!zzR(obj, i4, i3)) {
                        continue;
                    }
                    n2 = zzlw.u(i4, zzmv.f(obj, j2), zzE(i3));
                    break;
                case 61:
                    if (!zzR(obj, i4, i3)) {
                        continue;
                    }
                    f2 = zzmv.f(obj, j2);
                    int zzA32 = zzjj.zzA(i4 << 3);
                    int zzd2 = ((zzjb) f2).zzd();
                    zzA = zzA32 + zzjj.zzA(zzd2) + zzd2;
                    i2 += zzA;
                case 62:
                    if (zzR(obj, i4, i3)) {
                        c2 = zzr(obj, j2);
                        zzz = zzjj.zzA(i4 << 3);
                        o2 = zzjj.zzA(c2);
                        zzA = zzz + o2;
                        i2 += zzA;
                    } else {
                        continue;
                    }
                case 63:
                    if (!zzR(obj, i4, i3)) {
                        continue;
                    }
                    c3 = zzr(obj, j2);
                    zzz = zzjj.zzA(i4 << 3);
                    o2 = zzjj.zzv(c3);
                    zzA = zzz + o2;
                    i2 += zzA;
                case 64:
                    if (!zzR(obj, i4, i3)) {
                        continue;
                    }
                    n2 = zzjj.zzA(i4 << 3) + 4;
                    break;
                case 65:
                    if (!zzR(obj, i4, i3)) {
                        continue;
                    }
                    n2 = zzjj.zzA(i4 << 3) + 8;
                    break;
                case 66:
                    if (zzR(obj, i4, i3)) {
                        c4 = zzr(obj, j2);
                        zzz = zzjj.zzA(i4 << 3);
                        c2 = (c4 >> 31) ^ (c4 + c4);
                        o2 = zzjj.zzA(c2);
                        zzA = zzz + o2;
                        i2 += zzA;
                    } else {
                        continue;
                    }
                case 67:
                    if (zzR(obj, i4, i3)) {
                        d3 = zzC(obj, j2);
                        zzz = zzjj.zzA(i4 << 3);
                        o2 = zzjj.zzB((d3 >> 63) ^ (d3 + d3));
                        zzA = zzz + o2;
                        i2 += zzA;
                    } else {
                        continue;
                    }
                case 68:
                    if (!zzR(obj, i4, i3)) {
                        continue;
                    }
                    n2 = zzjj.c(i4, (zzlj) zzmv.f(obj, j2), zzE(i3));
                    break;
                default:
            }
            i2 += n2;
        }
        zzml zzmlVar = this.zzn;
        return i2 + zzmlVar.a(zzmlVar.c(obj));
    }

    private static int zzr(Object obj, long j2) {
        return ((Integer) zzmv.f(obj, j2)).intValue();
    }

    private final int zzs(Object obj, byte[] bArr, int i2, int i3, int i4, long j2, zzio zzioVar) {
        Unsafe unsafe = zzb;
        Object zzF = zzF(i4);
        Object object = unsafe.getObject(obj, j2);
        if (!((zzld) object).zze()) {
            zzld zzb2 = zzld.zza().zzb();
            zzle.zzb(zzb2, object);
            unsafe.putObject(obj, j2, zzb2);
        }
        zzlc zzlcVar = (zzlc) zzF;
        throw null;
    }

    private final int zzt(Object obj, byte[] bArr, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j2, int i9, zzio zzioVar) {
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
                    unsafe.putObject(obj, j2, Double.valueOf(Double.longBitsToDouble(zzip.n(bArr, i2))));
                    unsafe.putInt(obj, j5, i5);
                    return i2 + 8;
                }
                return i2;
            case 52:
                if (i6 == 5) {
                    unsafe.putObject(obj, j2, Float.valueOf(Float.intBitsToFloat(zzip.b(bArr, i2))));
                    unsafe.putInt(obj, j5, i5);
                    return i2 + 4;
                }
                return i2;
            case 53:
            case 54:
                if (i6 == 0) {
                    m2 = zzip.m(bArr, i2, zzioVar);
                    j3 = zzioVar.zzb;
                    valueOf = Long.valueOf(j3);
                    unsafe.putObject(obj, j2, valueOf);
                    unsafe.putInt(obj, j5, i5);
                    return m2;
                }
                return i2;
            case 55:
            case 62:
                if (i6 == 0) {
                    m2 = zzip.j(bArr, i2, zzioVar);
                    i10 = zzioVar.zza;
                    valueOf = Integer.valueOf(i10);
                    unsafe.putObject(obj, j2, valueOf);
                    unsafe.putInt(obj, j5, i5);
                    return m2;
                }
                return i2;
            case 56:
            case 65:
                if (i6 == 1) {
                    unsafe.putObject(obj, j2, Long.valueOf(zzip.n(bArr, i2)));
                    unsafe.putInt(obj, j5, i5);
                    return i2 + 8;
                }
                return i2;
            case 57:
            case 64:
                if (i6 == 5) {
                    unsafe.putObject(obj, j2, Integer.valueOf(zzip.b(bArr, i2)));
                    unsafe.putInt(obj, j5, i5);
                    return i2 + 4;
                }
                return i2;
            case 58:
                if (i6 == 0) {
                    m2 = zzip.m(bArr, i2, zzioVar);
                    valueOf = Boolean.valueOf(zzioVar.zzb != 0);
                    unsafe.putObject(obj, j2, valueOf);
                    unsafe.putInt(obj, j5, i5);
                    return m2;
                }
                return i2;
            case 59:
                if (i6 == 2) {
                    j4 = zzip.j(bArr, i2, zzioVar);
                    int i11 = zzioVar.zza;
                    if (i11 == 0) {
                        obj2 = "";
                        unsafe.putObject(obj, j2, obj2);
                        unsafe.putInt(obj, j5, i5);
                        return j4;
                    } else if ((i7 & PKIFailureInfo.duplicateCertReq) == 0 || zzna.f(bArr, j4, j4 + i11)) {
                        unsafe.putObject(obj, j2, new String(bArr, j4, i11, zzkk.f6100a));
                        j4 += i11;
                        unsafe.putInt(obj, j5, i5);
                        return j4;
                    } else {
                        throw zzkm.c();
                    }
                }
                return i2;
            case 60:
                if (i6 == 2) {
                    j4 = zzip.d(zzE(i9), bArr, i2, i3, zzioVar);
                    Object object = unsafe.getInt(obj, j5) == i5 ? unsafe.getObject(obj, j2) : null;
                    obj2 = zzioVar.zzc;
                    if (object != null) {
                        obj2 = zzkk.d(object, obj2);
                    }
                    unsafe.putObject(obj, j2, obj2);
                    unsafe.putInt(obj, j5, i5);
                    return j4;
                }
                return i2;
            case 61:
                if (i6 == 2) {
                    m2 = zzip.a(bArr, i2, zzioVar);
                    valueOf = zzioVar.zzc;
                    unsafe.putObject(obj, j2, valueOf);
                    unsafe.putInt(obj, j5, i5);
                    return m2;
                }
                return i2;
            case 63:
                if (i6 == 0) {
                    int j6 = zzip.j(bArr, i2, zzioVar);
                    int i12 = zzioVar.zza;
                    zzkg zzD = zzD(i9);
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
                    m2 = zzip.j(bArr, i2, zzioVar);
                    i10 = zzjf.zzb(zzioVar.zza);
                    valueOf = Integer.valueOf(i10);
                    unsafe.putObject(obj, j2, valueOf);
                    unsafe.putInt(obj, j5, i5);
                    return m2;
                }
                return i2;
            case 67:
                if (i6 == 0) {
                    m2 = zzip.m(bArr, i2, zzioVar);
                    j3 = zzjf.zzc(zzioVar.zzb);
                    valueOf = Long.valueOf(j3);
                    unsafe.putObject(obj, j2, valueOf);
                    unsafe.putInt(obj, j5, i5);
                    return m2;
                }
                return i2;
            case 68:
                if (i6 == 3) {
                    j4 = zzip.c(zzE(i9), bArr, i2, i3, (i4 & (-8)) | 4, zzioVar);
                    Object object2 = unsafe.getInt(obj, j5) == i5 ? unsafe.getObject(obj, j2) : null;
                    obj2 = zzioVar.zzc;
                    if (object2 != null) {
                        obj2 = zzkk.d(object2, obj2);
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
    private final int zzu(Object obj, byte[] bArr, int i2, int i3, zzio zzioVar) {
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
        zzlm<T> zzlmVar = this;
        Object obj2 = obj;
        byte[] bArr2 = bArr;
        int i17 = i3;
        zzio zzioVar2 = zzioVar;
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
                i4 = zzip.k(b3, bArr2, i25, zzioVar2);
                b2 = zzioVar2.zza;
            } else {
                b2 = b3;
                i4 = i25;
            }
            int i26 = b2 >>> 3;
            int i27 = b2 & 7;
            int zzx = i26 > i22 ? zzlmVar.zzx(i26, i23 / 3) : zzlmVar.zzw(i26);
            if (zzx == i18) {
                i5 = i4;
                i6 = i26;
                unsafe = unsafe5;
                i7 = i18;
                i8 = 0;
            } else {
                int[] iArr = zzlmVar.zzc;
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
                                zzmv.o(obj2, j2, Double.longBitsToDouble(zzip.n(bArr2, i15)));
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
                                zzmv.p(obj2, j2, Float.intBitsToFloat(zzip.b(bArr2, i15)));
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
                                int m2 = zzip.m(bArr2, i15, zzioVar2);
                                unsafe3.putLong(obj, j2, zzioVar2.zzb);
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
                                i20 = zzip.j(bArr2, i15, zzioVar2);
                                i16 = zzioVar2.zza;
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
                                unsafe3.putLong(obj, j2, zzip.n(bArr2, i4));
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
                                unsafe3.putInt(obj2, j2, zzip.b(bArr2, i4));
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
                                i20 = zzip.m(bArr2, i4, zzioVar2);
                                zzmv.m(obj2, j2, zzioVar2.zzb != 0);
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
                                i20 = (i28 & PKIFailureInfo.duplicateCertReq) == 0 ? zzip.g(bArr2, i4, zzioVar2) : zzip.h(bArr2, i4, zzioVar2);
                                d2 = zzioVar2.zzc;
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
                                i20 = zzip.d(zzlmVar.zzE(i14), bArr2, i4, i17, zzioVar2);
                                Object object = unsafe3.getObject(obj2, j2);
                                d2 = object == null ? zzioVar2.zzc : zzkk.d(object, zzioVar2.zzc);
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
                                i20 = zzip.a(bArr2, i4, zzioVar2);
                                d2 = zzioVar2.zzc;
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
                                i20 = zzip.j(bArr2, i4, zzioVar2);
                                i16 = zzioVar2.zza;
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
                                i20 = zzip.j(bArr2, i4, zzioVar2);
                                i16 = zzjf.zzb(zzioVar2.zza);
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
                                int m3 = zzip.m(bArr2, i4, zzioVar2);
                                i13 = i21;
                                i6 = i26;
                                i9 = 1048575;
                                unsafe2.putLong(obj, j2, zzjf.zzc(zzioVar2.zzb));
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
                            i20 = zzv(obj, bArr, i32, i3, b2, i6, i27, i8, i28, zzA, j2, zzioVar);
                        } else {
                            i10 = i32;
                            i11 = i24;
                            unsafe = unsafe6;
                            i12 = i33;
                            i7 = -1;
                            if (zzA != 50) {
                                i20 = zzt(obj, bArr, i10, i3, b2, i6, i27, i28, zzA, j2, i8, zzioVar);
                            } else if (i27 == 2) {
                                i20 = zzs(obj, bArr, i10, i3, i8, j2, zzioVar);
                            }
                        }
                        unsafe5 = unsafe;
                        i19 = 1048575;
                    } else if (i27 == 2) {
                        zzkj zzkjVar = (zzkj) unsafe6.getObject(obj2, j2);
                        if (!zzkjVar.zzc()) {
                            int size = zzkjVar.size();
                            zzkjVar = zzkjVar.zzd(size == 0 ? 10 : size + size);
                            unsafe6.putObject(obj2, j2, zzkjVar);
                        }
                        i20 = zzip.e(zzlmVar.zzE(zzx), b2, bArr, i32, i3, zzkjVar, zzioVar);
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
            i20 = zzip.i(b2, bArr, i5, i3, b(obj), zzioVar);
            zzlmVar = this;
            obj2 = obj;
            bArr2 = bArr;
            i17 = i3;
            zzioVar2 = zzioVar;
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
        throw zzkm.e();
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
        r6 = com.google.android.gms.internal.measurement.zzip.j(r17, r4, r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0268, code lost:
        if (r20 == r29.zza) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x026b, code lost:
        r4 = com.google.android.gms.internal.measurement.zzip.m(r17, r6, r29);
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
        r12.add(com.google.android.gms.internal.measurement.zzjb.zzb);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x012a, code lost:
        r12.add(com.google.android.gms.internal.measurement.zzjb.zzl(r17, r1, r4));
        r1 = r1 + r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0132, code lost:
        if (r1 >= r19) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0134, code lost:
        r4 = com.google.android.gms.internal.measurement.zzip.j(r17, r1, r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x013a, code lost:
        if (r20 == r29.zza) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x013d, code lost:
        r1 = com.google.android.gms.internal.measurement.zzip.j(r17, r4, r29);
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
        throw com.google.android.gms.internal.measurement.zzkm.f();
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0155, code lost:
        throw com.google.android.gms.internal.measurement.zzkm.d();
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
    private final int zzv(Object obj, byte[] bArr, int i2, int i3, int i4, int i5, int i6, int i7, long j2, int i8, long j3, zzio zzioVar) {
        int i9;
        int i10;
        int i11;
        int i12;
        int j4;
        int i13 = i2;
        Unsafe unsafe = zzb;
        zzkj zzkjVar = (zzkj) unsafe.getObject(obj, j3);
        if (!zzkjVar.zzc()) {
            int size = zzkjVar.size();
            zzkjVar = zzkjVar.zzd(size == 0 ? 10 : size + size);
            unsafe.putObject(obj, j3, zzkjVar);
        }
        switch (i8) {
            case 18:
            case 35:
                if (i6 == 2) {
                    zzjl zzjlVar = (zzjl) zzkjVar;
                    int j5 = zzip.j(bArr, i13, zzioVar);
                    int i14 = zzioVar.zza + j5;
                    while (j5 < i14) {
                        zzjlVar.zze(Double.longBitsToDouble(zzip.n(bArr, j5)));
                        j5 += 8;
                    }
                    if (j5 == i14) {
                        return j5;
                    }
                    throw zzkm.f();
                }
                if (i6 == 1) {
                    zzjl zzjlVar2 = (zzjl) zzkjVar;
                    long n2 = zzip.n(bArr, i2);
                    while (true) {
                        zzjlVar2.zze(Double.longBitsToDouble(n2));
                        i9 = i13 + 8;
                        if (i9 < i3) {
                            i13 = zzip.j(bArr, i9, zzioVar);
                            if (i4 == zzioVar.zza) {
                                n2 = zzip.n(bArr, i13);
                            }
                        }
                    }
                    return i9;
                }
                return i13;
            case 19:
            case 36:
                if (i6 == 2) {
                    zzjv zzjvVar = (zzjv) zzkjVar;
                    int j6 = zzip.j(bArr, i13, zzioVar);
                    int i15 = zzioVar.zza + j6;
                    while (j6 < i15) {
                        zzjvVar.zze(Float.intBitsToFloat(zzip.b(bArr, j6)));
                        j6 += 4;
                    }
                    if (j6 == i15) {
                        return j6;
                    }
                    throw zzkm.f();
                }
                if (i6 == 5) {
                    zzjv zzjvVar2 = (zzjv) zzkjVar;
                    int b2 = zzip.b(bArr, i2);
                    while (true) {
                        zzjvVar2.zze(Float.intBitsToFloat(b2));
                        i10 = i13 + 4;
                        if (i10 < i3) {
                            i13 = zzip.j(bArr, i10, zzioVar);
                            if (i4 == zzioVar.zza) {
                                b2 = zzip.b(bArr, i13);
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
                    zzky zzkyVar = (zzky) zzkjVar;
                    int j7 = zzip.j(bArr, i13, zzioVar);
                    int i16 = zzioVar.zza + j7;
                    while (j7 < i16) {
                        j7 = zzip.m(bArr, j7, zzioVar);
                        zzkyVar.zzg(zzioVar.zzb);
                    }
                    if (j7 == i16) {
                        return j7;
                    }
                    throw zzkm.f();
                }
                if (i6 == 0) {
                    zzky zzkyVar2 = (zzky) zzkjVar;
                    do {
                        int m2 = zzip.m(bArr, i13, zzioVar);
                        zzkyVar2.zzg(zzioVar.zzb);
                        if (m2 < i3) {
                            i13 = zzip.j(bArr, m2, zzioVar);
                        }
                        return m2;
                    } while (i4 == zzioVar.zza);
                    return m2;
                }
                return i13;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i6 == 2) {
                    return zzip.f(bArr, i13, zzkjVar, zzioVar);
                }
                if (i6 == 0) {
                    return zzip.l(i4, bArr, i2, i3, zzkjVar, zzioVar);
                }
                return i13;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i6 == 2) {
                    zzky zzkyVar3 = (zzky) zzkjVar;
                    int j8 = zzip.j(bArr, i13, zzioVar);
                    int i17 = zzioVar.zza + j8;
                    while (j8 < i17) {
                        zzkyVar3.zzg(zzip.n(bArr, j8));
                        j8 += 8;
                    }
                    if (j8 == i17) {
                        return j8;
                    }
                    throw zzkm.f();
                }
                if (i6 == 1) {
                    zzky zzkyVar4 = (zzky) zzkjVar;
                    long n3 = zzip.n(bArr, i2);
                    while (true) {
                        zzkyVar4.zzg(n3);
                        i11 = i13 + 8;
                        if (i11 < i3) {
                            i13 = zzip.j(bArr, i11, zzioVar);
                            if (i4 == zzioVar.zza) {
                                n3 = zzip.n(bArr, i13);
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
                    zzkd zzkdVar = (zzkd) zzkjVar;
                    int j9 = zzip.j(bArr, i13, zzioVar);
                    int i18 = zzioVar.zza + j9;
                    while (j9 < i18) {
                        zzkdVar.zzh(zzip.b(bArr, j9));
                        j9 += 4;
                    }
                    if (j9 == i18) {
                        return j9;
                    }
                    throw zzkm.f();
                }
                if (i6 == 5) {
                    zzkd zzkdVar2 = (zzkd) zzkjVar;
                    int b3 = zzip.b(bArr, i2);
                    while (true) {
                        zzkdVar2.zzh(b3);
                        i12 = i13 + 4;
                        if (i12 < i3) {
                            i13 = zzip.j(bArr, i12, zzioVar);
                            if (i4 == zzioVar.zza) {
                                b3 = zzip.b(bArr, i13);
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
                        zziq zziqVar = (zziq) zzkjVar;
                        int m3 = zzip.m(bArr, i13, zzioVar);
                        break;
                    }
                    return i13;
                }
                zziq zziqVar2 = (zziq) zzkjVar;
                j4 = zzip.j(bArr, i13, zzioVar);
                int i19 = zzioVar.zza + j4;
                while (j4 < i19) {
                    j4 = zzip.m(bArr, j4, zzioVar);
                    zziqVar2.zze(zzioVar.zzb != 0);
                }
                if (j4 != i19) {
                    throw zzkm.f();
                }
                return j4;
            case 26:
                if (i6 == 2) {
                    if ((j2 & 536870912) == 0) {
                        int j10 = zzip.j(bArr, i13, zzioVar);
                        int i20 = zzioVar.zza;
                        if (i20 < 0) {
                            throw zzkm.d();
                        }
                        if (i20 != 0) {
                            String str = new String(bArr, j10, i20, zzkk.f6100a);
                            zzkjVar.add(str);
                            j10 += i20;
                            if (j10 < i3) {
                                int j11 = zzip.j(bArr, j10, zzioVar);
                                if (i4 != zzioVar.zza) {
                                    return j10;
                                }
                                j10 = zzip.j(bArr, j11, zzioVar);
                                i20 = zzioVar.zza;
                                if (i20 < 0) {
                                    throw zzkm.d();
                                }
                                if (i20 != 0) {
                                    str = new String(bArr, j10, i20, zzkk.f6100a);
                                    zzkjVar.add(str);
                                    j10 += i20;
                                    if (j10 < i3) {
                                        return j10;
                                    }
                                }
                            }
                        }
                        zzkjVar.add("");
                        if (j10 < i3) {
                        }
                    } else {
                        int j12 = zzip.j(bArr, i13, zzioVar);
                        int i21 = zzioVar.zza;
                        if (i21 < 0) {
                            throw zzkm.d();
                        }
                        if (i21 != 0) {
                            int i22 = j12 + i21;
                            if (!zzna.f(bArr, j12, i22)) {
                                throw zzkm.c();
                            }
                            String str2 = new String(bArr, j12, i21, zzkk.f6100a);
                            zzkjVar.add(str2);
                            j12 = i22;
                            if (j12 < i3) {
                                int j13 = zzip.j(bArr, j12, zzioVar);
                                if (i4 != zzioVar.zza) {
                                    return j12;
                                }
                                j12 = zzip.j(bArr, j13, zzioVar);
                                int i23 = zzioVar.zza;
                                if (i23 < 0) {
                                    throw zzkm.d();
                                }
                                if (i23 != 0) {
                                    i22 = j12 + i23;
                                    if (!zzna.f(bArr, j12, i22)) {
                                        throw zzkm.c();
                                    }
                                    str2 = new String(bArr, j12, i23, zzkk.f6100a);
                                    zzkjVar.add(str2);
                                    j12 = i22;
                                    if (j12 < i3) {
                                        return j12;
                                    }
                                }
                            }
                        }
                        zzkjVar.add("");
                        if (j12 < i3) {
                        }
                    }
                }
                return i13;
            case 27:
                if (i6 == 2) {
                    return zzip.e(zzE(i7), i4, bArr, i2, i3, zzkjVar, zzioVar);
                }
                return i13;
            case 28:
                if (i6 == 2) {
                    int j14 = zzip.j(bArr, i13, zzioVar);
                    int i24 = zzioVar.zza;
                    if (i24 < 0) {
                        throw zzkm.d();
                    }
                    if (i24 > bArr.length - j14) {
                        throw zzkm.f();
                    }
                }
                return i13;
            case 30:
            case 44:
                if (i6 != 2) {
                    if (i6 == 0) {
                        j4 = zzip.l(i4, bArr, i2, i3, zzkjVar, zzioVar);
                    }
                    return i13;
                }
                j4 = zzip.f(bArr, i13, zzkjVar, zzioVar);
                zzkc zzkcVar = (zzkc) obj;
                zzmm zzmmVar = zzkcVar.zzc;
                if (zzmmVar == zzmm.zzc()) {
                    zzmmVar = null;
                }
                Object a2 = zzlw.a(i5, zzkjVar, zzD(i7), zzmmVar, this.zzn);
                if (a2 != null) {
                    zzkcVar.zzc = (zzmm) a2;
                    return j4;
                }
                return j4;
            case 33:
            case 47:
                if (i6 == 2) {
                    zzkd zzkdVar3 = (zzkd) zzkjVar;
                    int j15 = zzip.j(bArr, i13, zzioVar);
                    int i25 = zzioVar.zza + j15;
                    while (j15 < i25) {
                        j15 = zzip.j(bArr, j15, zzioVar);
                        zzkdVar3.zzh(zzjf.zzb(zzioVar.zza));
                    }
                    if (j15 == i25) {
                        return j15;
                    }
                    throw zzkm.f();
                }
                if (i6 == 0) {
                    zzkd zzkdVar4 = (zzkd) zzkjVar;
                    do {
                        int j16 = zzip.j(bArr, i13, zzioVar);
                        zzkdVar4.zzh(zzjf.zzb(zzioVar.zza));
                        if (j16 < i3) {
                            i13 = zzip.j(bArr, j16, zzioVar);
                        }
                        return j16;
                    } while (i4 == zzioVar.zza);
                    return j16;
                }
                return i13;
            case 34:
            case 48:
                if (i6 == 2) {
                    zzky zzkyVar5 = (zzky) zzkjVar;
                    int j17 = zzip.j(bArr, i13, zzioVar);
                    int i26 = zzioVar.zza + j17;
                    while (j17 < i26) {
                        j17 = zzip.m(bArr, j17, zzioVar);
                        zzkyVar5.zzg(zzjf.zzc(zzioVar.zzb));
                    }
                    if (j17 == i26) {
                        return j17;
                    }
                    throw zzkm.f();
                }
                if (i6 == 0) {
                    zzky zzkyVar6 = (zzky) zzkjVar;
                    do {
                        int m4 = zzip.m(bArr, i13, zzioVar);
                        zzkyVar6.zzg(zzjf.zzc(zzioVar.zzb));
                        if (m4 < i3) {
                            i13 = zzip.j(bArr, m4, zzioVar);
                        }
                        return m4;
                    } while (i4 == zzioVar.zza);
                    return m4;
                }
                return i13;
            default:
                if (i6 == 3) {
                    zzlu zzE = zzE(i7);
                    int i27 = (i4 & (-8)) | 4;
                    int c2 = zzip.c(zzE, bArr, i2, i3, i27, zzioVar);
                    while (true) {
                        zzkjVar.add(zzioVar.zzc);
                        if (c2 < i3) {
                            int j18 = zzip.j(bArr, c2, zzioVar);
                            if (i4 == zzioVar.zza) {
                                c2 = zzip.c(zzE, bArr, j18, i3, i27, zzioVar);
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
    public final int a(Object obj, byte[] bArr, int i2, int i3, int i4, zzio zzioVar) {
        Unsafe unsafe;
        int i5;
        Object obj2;
        zzlm<T> zzlmVar;
        byte b2;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        Object obj3;
        int i11;
        zzio zzioVar2;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        long j2;
        Object d2;
        long j3;
        int i19;
        int i20;
        int i21;
        zzlm<T> zzlmVar2 = this;
        Object obj4 = obj;
        byte[] bArr2 = bArr;
        int i22 = i3;
        int i23 = i4;
        zzio zzioVar3 = zzioVar;
        Unsafe unsafe2 = zzb;
        int i24 = i2;
        int i25 = 0;
        int i26 = 0;
        int i27 = 0;
        int i28 = -1;
        int i29 = 1048575;
        while (true) {
            if (i24 < i22) {
                int i30 = i24 + 1;
                byte b3 = bArr2[i24];
                if (b3 < 0) {
                    int k2 = zzip.k(b3, bArr2, i30, zzioVar3);
                    b2 = zzioVar3.zza;
                    i30 = k2;
                } else {
                    b2 = b3;
                }
                int i31 = b2 >>> 3;
                int i32 = b2 & 7;
                int zzx = i31 > i28 ? zzlmVar2.zzx(i31, i26 / 3) : zzlmVar2.zzw(i31);
                if (zzx == -1) {
                    i6 = i31;
                    i7 = i30;
                    i8 = b2;
                    i9 = i27;
                    unsafe = unsafe2;
                    i5 = i23;
                    i10 = 0;
                } else {
                    int[] iArr = zzlmVar2.zzc;
                    int i33 = iArr[zzx + 1];
                    int zzA = zzA(i33);
                    int i34 = i30;
                    long j4 = i33 & 1048575;
                    if (zzA <= 17) {
                        int i35 = iArr[zzx + 2];
                        int i36 = 1 << (i35 >>> 20);
                        int i37 = i35 & 1048575;
                        int i38 = b2;
                        if (i37 != i29) {
                            if (i29 != 1048575) {
                                unsafe2.putInt(obj4, i29, i27);
                            }
                            i27 = unsafe2.getInt(obj4, i37);
                            i12 = i37;
                        } else {
                            i12 = i29;
                        }
                        int i39 = i27;
                        switch (zzA) {
                            case 0:
                                i13 = zzx;
                                i6 = i31;
                                i14 = i34;
                                if (i32 == 1) {
                                    zzmv.o(obj4, j4, Double.longBitsToDouble(zzip.n(bArr2, i14)));
                                    i24 = i14 + 8;
                                    i27 = i39 | i36;
                                    i28 = i6;
                                    i23 = i4;
                                    i26 = i13;
                                    i25 = i38;
                                    i29 = i12;
                                    i22 = i3;
                                    break;
                                } else {
                                    i34 = i14;
                                    i5 = i4;
                                    i9 = i39;
                                    unsafe = unsafe2;
                                    i8 = i38;
                                    i7 = i34;
                                    i29 = i12;
                                    i10 = i13;
                                    break;
                                }
                            case 1:
                                i13 = zzx;
                                i6 = i31;
                                i14 = i34;
                                if (i32 == 5) {
                                    zzmv.p(obj4, j4, Float.intBitsToFloat(zzip.b(bArr2, i14)));
                                    i24 = i14 + 4;
                                    i27 = i39 | i36;
                                    i28 = i6;
                                    i23 = i4;
                                    i26 = i13;
                                    i25 = i38;
                                    i29 = i12;
                                    i22 = i3;
                                    break;
                                } else {
                                    i34 = i14;
                                    i5 = i4;
                                    i9 = i39;
                                    unsafe = unsafe2;
                                    i8 = i38;
                                    i7 = i34;
                                    i29 = i12;
                                    i10 = i13;
                                    break;
                                }
                            case 2:
                            case 3:
                                i13 = zzx;
                                i6 = i31;
                                i14 = i34;
                                if (i32 == 0) {
                                    int m2 = zzip.m(bArr2, i14, zzioVar3);
                                    unsafe2.putLong(obj, j4, zzioVar3.zzb);
                                    i27 = i39 | i36;
                                    i28 = i6;
                                    i23 = i4;
                                    i24 = m2;
                                    i26 = i13;
                                    i25 = i38;
                                    i29 = i12;
                                    i22 = i3;
                                    break;
                                } else {
                                    i34 = i14;
                                    i5 = i4;
                                    i9 = i39;
                                    unsafe = unsafe2;
                                    i8 = i38;
                                    i7 = i34;
                                    i29 = i12;
                                    i10 = i13;
                                    break;
                                }
                            case 4:
                            case 11:
                                i13 = zzx;
                                i6 = i31;
                                i14 = i34;
                                if (i32 == 0) {
                                    i24 = zzip.j(bArr2, i14, zzioVar3);
                                    unsafe2.putInt(obj4, j4, zzioVar3.zza);
                                    i27 = i39 | i36;
                                    i28 = i6;
                                    i23 = i4;
                                    i26 = i13;
                                    i25 = i38;
                                    i29 = i12;
                                    i22 = i3;
                                    break;
                                } else {
                                    i34 = i14;
                                    i5 = i4;
                                    i9 = i39;
                                    unsafe = unsafe2;
                                    i8 = i38;
                                    i7 = i34;
                                    i29 = i12;
                                    i10 = i13;
                                    break;
                                }
                            case 5:
                            case 14:
                                i6 = i31;
                                if (i32 == 1) {
                                    i13 = zzx;
                                    i38 = i38;
                                    i14 = i34;
                                    unsafe2.putLong(obj, j4, zzip.n(bArr2, i34));
                                    i24 = i14 + 8;
                                    i27 = i39 | i36;
                                    i28 = i6;
                                    i23 = i4;
                                    i26 = i13;
                                    i25 = i38;
                                    i29 = i12;
                                    i22 = i3;
                                    break;
                                } else {
                                    i13 = zzx;
                                    i38 = i38;
                                    i34 = i34;
                                    i5 = i4;
                                    i9 = i39;
                                    unsafe = unsafe2;
                                    i8 = i38;
                                    i7 = i34;
                                    i29 = i12;
                                    i10 = i13;
                                    break;
                                }
                            case 6:
                            case 13:
                                i15 = i38;
                                i6 = i31;
                                i16 = i34;
                                if (i32 == 5) {
                                    unsafe2.putInt(obj4, j4, zzip.b(bArr2, i16));
                                    i24 = i16 + 4;
                                    i27 = i39 | i36;
                                    i26 = zzx;
                                    i25 = i15;
                                    i29 = i12;
                                    i28 = i6;
                                    i23 = i4;
                                    break;
                                } else {
                                    i13 = zzx;
                                    i34 = i16;
                                    i38 = i15;
                                    i5 = i4;
                                    i9 = i39;
                                    unsafe = unsafe2;
                                    i8 = i38;
                                    i7 = i34;
                                    i29 = i12;
                                    i10 = i13;
                                    break;
                                }
                            case 7:
                                i15 = i38;
                                i6 = i31;
                                i16 = i34;
                                if (i32 == 0) {
                                    i24 = zzip.m(bArr2, i16, zzioVar3);
                                    zzmv.m(obj4, j4, zzioVar3.zzb != 0);
                                    i27 = i39 | i36;
                                    i26 = zzx;
                                    i25 = i15;
                                    i29 = i12;
                                    i28 = i6;
                                    i23 = i4;
                                    break;
                                } else {
                                    i13 = zzx;
                                    i34 = i16;
                                    i38 = i15;
                                    i5 = i4;
                                    i9 = i39;
                                    unsafe = unsafe2;
                                    i8 = i38;
                                    i7 = i34;
                                    i29 = i12;
                                    i10 = i13;
                                    break;
                                }
                            case 8:
                                i15 = i38;
                                i6 = i31;
                                i16 = i34;
                                if (i32 == 2) {
                                    i24 = (536870912 & i33) == 0 ? zzip.g(bArr2, i16, zzioVar3) : zzip.h(bArr2, i16, zzioVar3);
                                    unsafe2.putObject(obj4, j4, zzioVar3.zzc);
                                    i27 = i39 | i36;
                                    i26 = zzx;
                                    i25 = i15;
                                    i29 = i12;
                                    i28 = i6;
                                    i23 = i4;
                                    break;
                                } else {
                                    i13 = zzx;
                                    i34 = i16;
                                    i38 = i15;
                                    i5 = i4;
                                    i9 = i39;
                                    unsafe = unsafe2;
                                    i8 = i38;
                                    i7 = i34;
                                    i29 = i12;
                                    i10 = i13;
                                    break;
                                }
                            case 9:
                                i17 = zzx;
                                i15 = i38;
                                i6 = i31;
                                i18 = i34;
                                j2 = j4;
                                if (i32 == 2) {
                                    i24 = zzip.d(zzlmVar2.zzE(i17), bArr2, i18, i22, zzioVar3);
                                    d2 = (i39 & i36) == 0 ? zzioVar3.zzc : zzkk.d(unsafe2.getObject(obj4, j2), zzioVar3.zzc);
                                    unsafe2.putObject(obj4, j2, d2);
                                    i27 = i39 | i36;
                                    i28 = i6;
                                    i26 = i17;
                                    i25 = i15;
                                    i29 = i12;
                                    i23 = i4;
                                    break;
                                } else {
                                    i34 = i18;
                                    i13 = i17;
                                    i38 = i15;
                                    i5 = i4;
                                    i9 = i39;
                                    unsafe = unsafe2;
                                    i8 = i38;
                                    i7 = i34;
                                    i29 = i12;
                                    i10 = i13;
                                    break;
                                }
                            case 10:
                                i17 = zzx;
                                i15 = i38;
                                i6 = i31;
                                i18 = i34;
                                j2 = j4;
                                if (i32 == 2) {
                                    i24 = zzip.a(bArr2, i18, zzioVar3);
                                    d2 = zzioVar3.zzc;
                                    unsafe2.putObject(obj4, j2, d2);
                                    i27 = i39 | i36;
                                    i28 = i6;
                                    i26 = i17;
                                    i25 = i15;
                                    i29 = i12;
                                    i23 = i4;
                                    break;
                                } else {
                                    i34 = i18;
                                    i13 = i17;
                                    i38 = i15;
                                    i5 = i4;
                                    i9 = i39;
                                    unsafe = unsafe2;
                                    i8 = i38;
                                    i7 = i34;
                                    i29 = i12;
                                    i10 = i13;
                                    break;
                                }
                            case 12:
                                i17 = zzx;
                                i15 = i38;
                                i6 = i31;
                                i18 = i34;
                                j3 = j4;
                                if (i32 != 0) {
                                    i34 = i18;
                                    i13 = i17;
                                    i38 = i15;
                                    i5 = i4;
                                    i9 = i39;
                                    unsafe = unsafe2;
                                    i8 = i38;
                                    i7 = i34;
                                    i29 = i12;
                                    i10 = i13;
                                    break;
                                } else {
                                    i24 = zzip.j(bArr2, i18, zzioVar3);
                                    i19 = zzioVar3.zza;
                                    zzkg zzD = zzlmVar2.zzD(i17);
                                    if (zzD != null && !zzD.zza(i19)) {
                                        b(obj).d(i15, Long.valueOf(i19));
                                        i28 = i6;
                                        i27 = i39;
                                        i26 = i17;
                                        i25 = i15;
                                        i29 = i12;
                                        i23 = i4;
                                        break;
                                    }
                                    unsafe2.putInt(obj4, j3, i19);
                                    i27 = i39 | i36;
                                    i28 = i6;
                                    i26 = i17;
                                    i25 = i15;
                                    i29 = i12;
                                    i23 = i4;
                                }
                                break;
                            case 15:
                                i17 = zzx;
                                i15 = i38;
                                i6 = i31;
                                i18 = i34;
                                if (i32 == 0) {
                                    i24 = zzip.j(bArr2, i18, zzioVar3);
                                    i19 = zzjf.zzb(zzioVar3.zza);
                                    j3 = j4;
                                    unsafe2.putInt(obj4, j3, i19);
                                    i27 = i39 | i36;
                                    i28 = i6;
                                    i26 = i17;
                                    i25 = i15;
                                    i29 = i12;
                                    i23 = i4;
                                    break;
                                } else {
                                    i34 = i18;
                                    i13 = i17;
                                    i38 = i15;
                                    i5 = i4;
                                    i9 = i39;
                                    unsafe = unsafe2;
                                    i8 = i38;
                                    i7 = i34;
                                    i29 = i12;
                                    i10 = i13;
                                    break;
                                }
                            case 16:
                                if (i32 == 0) {
                                    int m3 = zzip.m(bArr2, i34, zzioVar3);
                                    i17 = zzx;
                                    i15 = i38;
                                    unsafe2.putLong(obj, j4, zzjf.zzc(zzioVar3.zzb));
                                    i27 = i39 | i36;
                                    i28 = i31;
                                    i24 = m3;
                                    i26 = i17;
                                    i25 = i15;
                                    i29 = i12;
                                    i23 = i4;
                                    break;
                                } else {
                                    i6 = i31;
                                    i13 = zzx;
                                    i5 = i4;
                                    i9 = i39;
                                    unsafe = unsafe2;
                                    i8 = i38;
                                    i7 = i34;
                                    i29 = i12;
                                    i10 = i13;
                                    break;
                                }
                            default:
                                i13 = zzx;
                                i6 = i31;
                                i14 = i34;
                                if (i32 == 3) {
                                    i24 = zzip.c(zzlmVar2.zzE(i13), bArr, i14, i3, (i6 << 3) | 4, zzioVar);
                                    unsafe2.putObject(obj4, j4, (i39 & i36) == 0 ? zzioVar3.zzc : zzkk.d(unsafe2.getObject(obj4, j4), zzioVar3.zzc));
                                    i27 = i39 | i36;
                                    bArr2 = bArr;
                                    i28 = i6;
                                    i23 = i4;
                                    i26 = i13;
                                    i25 = i38;
                                    i29 = i12;
                                    i22 = i3;
                                    break;
                                } else {
                                    i34 = i14;
                                    i5 = i4;
                                    i9 = i39;
                                    unsafe = unsafe2;
                                    i8 = i38;
                                    i7 = i34;
                                    i29 = i12;
                                    i10 = i13;
                                    break;
                                }
                        }
                    } else {
                        int i40 = zzx;
                        int i41 = b2;
                        if (zzA != 27) {
                            i9 = i27;
                            i20 = i29;
                            if (zzA <= 49) {
                                i6 = i31;
                                unsafe = unsafe2;
                                i10 = i40;
                                i24 = zzv(obj, bArr, i34, i3, i41, i31, i32, i40, i33, zzA, j4, zzioVar);
                                if (i24 != i34) {
                                    zzlmVar2 = this;
                                    obj4 = obj;
                                    bArr2 = bArr;
                                    i28 = i6;
                                    i22 = i3;
                                    i23 = i4;
                                    zzioVar3 = zzioVar;
                                    i25 = i41;
                                    i26 = i10;
                                    i27 = i9;
                                    i29 = i20;
                                    unsafe2 = unsafe;
                                } else {
                                    i5 = i4;
                                    i7 = i24;
                                    i8 = i41;
                                    i29 = i20;
                                }
                            } else {
                                i6 = i31;
                                unsafe = unsafe2;
                                i21 = i34;
                                i10 = i40;
                                if (zzA != 50) {
                                    i24 = zzt(obj, bArr, i21, i3, i41, i6, i32, i33, zzA, j4, i10, zzioVar);
                                    if (i24 != i21) {
                                        zzlmVar2 = this;
                                        obj4 = obj;
                                        bArr2 = bArr;
                                        i28 = i6;
                                        i22 = i3;
                                        i23 = i4;
                                        zzioVar3 = zzioVar;
                                        i25 = i41;
                                        i26 = i10;
                                        i27 = i9;
                                        i29 = i20;
                                        unsafe2 = unsafe;
                                    } else {
                                        i5 = i4;
                                        i7 = i24;
                                        i8 = i41;
                                        i29 = i20;
                                    }
                                } else if (i32 == 2) {
                                    i24 = zzs(obj, bArr, i21, i3, i10, j4, zzioVar);
                                    if (i24 != i21) {
                                        zzlmVar2 = this;
                                        obj4 = obj;
                                        bArr2 = bArr;
                                        i28 = i6;
                                        i22 = i3;
                                        i23 = i4;
                                        zzioVar3 = zzioVar;
                                        i25 = i41;
                                        i26 = i10;
                                        i27 = i9;
                                        i29 = i20;
                                        unsafe2 = unsafe;
                                    } else {
                                        i5 = i4;
                                        i7 = i24;
                                        i8 = i41;
                                        i29 = i20;
                                    }
                                }
                            }
                        } else if (i32 == 2) {
                            zzkj zzkjVar = (zzkj) unsafe2.getObject(obj4, j4);
                            if (!zzkjVar.zzc()) {
                                int size = zzkjVar.size();
                                zzkjVar = zzkjVar.zzd(size == 0 ? 10 : size + size);
                                unsafe2.putObject(obj4, j4, zzkjVar);
                            }
                            i25 = i41;
                            i12 = i29;
                            i24 = zzip.e(zzlmVar2.zzE(i40), i25, bArr, i34, i3, zzkjVar, zzioVar);
                            bArr2 = bArr;
                            i23 = i4;
                            i28 = i31;
                            i26 = i40;
                            i27 = i27;
                            i29 = i12;
                            i22 = i3;
                        } else {
                            i9 = i27;
                            i20 = i29;
                            i6 = i31;
                            unsafe = unsafe2;
                            i21 = i34;
                            i10 = i40;
                        }
                        i5 = i4;
                        i7 = i21;
                        i8 = i41;
                        i29 = i20;
                    }
                }
                if (i8 != i5 || i5 == 0) {
                    if (this.zzh) {
                        zzioVar2 = zzioVar;
                        if (zzioVar2.zzd != zzjo.zza()) {
                            i11 = i6;
                            if (zzioVar2.zzd.zzc(this.zzg, i11) != null) {
                                zzjz zzjzVar = (zzjz) obj;
                                throw null;
                            }
                            i24 = zzip.i(i8, bArr, i7, i3, b(obj), zzioVar);
                            obj3 = obj;
                            i22 = i3;
                            i25 = i8;
                            zzlmVar2 = this;
                            zzioVar3 = zzioVar2;
                            i28 = i11;
                            obj4 = obj3;
                            i26 = i10;
                            i27 = i9;
                            unsafe2 = unsafe;
                            bArr2 = bArr;
                            i23 = i5;
                        } else {
                            obj3 = obj;
                            i11 = i6;
                        }
                    } else {
                        obj3 = obj;
                        i11 = i6;
                        zzioVar2 = zzioVar;
                    }
                    i24 = zzip.i(i8, bArr, i7, i3, b(obj), zzioVar);
                    i22 = i3;
                    i25 = i8;
                    zzlmVar2 = this;
                    zzioVar3 = zzioVar2;
                    i28 = i11;
                    obj4 = obj3;
                    i26 = i10;
                    i27 = i9;
                    unsafe2 = unsafe;
                    bArr2 = bArr;
                    i23 = i5;
                } else {
                    zzlmVar = this;
                    obj2 = obj;
                    i24 = i7;
                    i25 = i8;
                    i27 = i9;
                }
            } else {
                unsafe = unsafe2;
                i5 = i23;
                obj2 = obj4;
                zzlmVar = zzlmVar2;
            }
        }
        if (i29 != 1048575) {
            unsafe.putInt(obj2, i29, i27);
        }
        for (int i42 = zzlmVar.zzk; i42 < zzlmVar.zzl; i42++) {
            int i43 = zzlmVar.zzj[i42];
            int i44 = zzlmVar.zzc[i43];
            Object f2 = zzmv.f(obj2, zzlmVar.zzB(i43) & 1048575);
            if (f2 != null && zzlmVar.zzD(i43) != null) {
                zzld zzldVar = (zzld) f2;
                zzlc zzlcVar = (zzlc) zzlmVar.zzF(i43);
                throw null;
            }
        }
        if (i5 == 0) {
            if (i24 != i3) {
                throw zzkm.e();
            }
        } else if (i24 > i3 || i25 != i5) {
            throw zzkm.e();
        }
        return i24;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
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
    @Override // com.google.android.gms.internal.measurement.zzlu
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
                    a2 = zzmv.a(obj, j2);
                    d2 = Double.doubleToLongBits(a2);
                    c2 = zzkk.zzc(d2);
                    i3 = i2 + c2;
                    break;
                case 1:
                    i2 = i3 * 53;
                    b2 = zzmv.b(obj, j2);
                    c2 = Float.floatToIntBits(b2);
                    i3 = i2 + c2;
                    break;
                case 2:
                case 3:
                case 5:
                case 14:
                case 16:
                    i2 = i3 * 53;
                    d2 = zzmv.d(obj, j2);
                    c2 = zzkk.zzc(d2);
                    i3 = i2 + c2;
                    break;
                case 4:
                case 6:
                case 11:
                case 12:
                case 13:
                case 15:
                    i2 = i3 * 53;
                    c2 = zzmv.c(obj, j2);
                    i3 = i2 + c2;
                    break;
                case 7:
                    i2 = i3 * 53;
                    w = zzmv.w(obj, j2);
                    c2 = zzkk.zza(w);
                    i3 = i2 + c2;
                    break;
                case 8:
                    i2 = i3 * 53;
                    c2 = ((String) zzmv.f(obj, j2)).hashCode();
                    i3 = i2 + c2;
                    break;
                case 9:
                    f2 = zzmv.f(obj, j2);
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
                    c2 = zzmv.f(obj, j2).hashCode();
                    i3 = i2 + c2;
                    break;
                case 17:
                    f2 = zzmv.f(obj, j2);
                    break;
                case 51:
                    if (zzR(obj, i5, i4)) {
                        i2 = i3 * 53;
                        a2 = zzn(obj, j2);
                        d2 = Double.doubleToLongBits(a2);
                        c2 = zzkk.zzc(d2);
                        i3 = i2 + c2;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzR(obj, i5, i4)) {
                        i2 = i3 * 53;
                        b2 = zzo(obj, j2);
                        c2 = Float.floatToIntBits(b2);
                        i3 = i2 + c2;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (!zzR(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    d2 = zzC(obj, j2);
                    c2 = zzkk.zzc(d2);
                    i3 = i2 + c2;
                    break;
                case 54:
                    if (!zzR(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    d2 = zzC(obj, j2);
                    c2 = zzkk.zzc(d2);
                    i3 = i2 + c2;
                    break;
                case 55:
                    if (!zzR(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = zzr(obj, j2);
                    i3 = i2 + c2;
                    break;
                case 56:
                    if (!zzR(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    d2 = zzC(obj, j2);
                    c2 = zzkk.zzc(d2);
                    i3 = i2 + c2;
                    break;
                case 57:
                    if (!zzR(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = zzr(obj, j2);
                    i3 = i2 + c2;
                    break;
                case 58:
                    if (zzR(obj, i5, i4)) {
                        i2 = i3 * 53;
                        w = zzS(obj, j2);
                        c2 = zzkk.zza(w);
                        i3 = i2 + c2;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (!zzR(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = ((String) zzmv.f(obj, j2)).hashCode();
                    i3 = i2 + c2;
                    break;
                case 60:
                    if (!zzR(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = zzmv.f(obj, j2).hashCode();
                    i3 = i2 + c2;
                    break;
                case 61:
                    if (!zzR(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = zzmv.f(obj, j2).hashCode();
                    i3 = i2 + c2;
                    break;
                case 62:
                    if (!zzR(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = zzr(obj, j2);
                    i3 = i2 + c2;
                    break;
                case 63:
                    if (!zzR(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = zzr(obj, j2);
                    i3 = i2 + c2;
                    break;
                case 64:
                    if (!zzR(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = zzr(obj, j2);
                    i3 = i2 + c2;
                    break;
                case 65:
                    if (!zzR(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    d2 = zzC(obj, j2);
                    c2 = zzkk.zzc(d2);
                    i3 = i2 + c2;
                    break;
                case 66:
                    if (!zzR(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = zzr(obj, j2);
                    i3 = i2 + c2;
                    break;
                case 67:
                    if (!zzR(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    d2 = zzC(obj, j2);
                    c2 = zzkk.zzc(d2);
                    i3 = i2 + c2;
                    break;
                case 68:
                    if (!zzR(obj, i5, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    c2 = zzmv.f(obj, j2).hashCode();
                    i3 = i2 + c2;
                    break;
            }
        }
        int hashCode = (i3 * 53) + this.zzn.c(obj).hashCode();
        if (this.zzh) {
            this.zzo.a(obj);
            throw null;
        }
        return hashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final Object zze() {
        return ((zzkc) this.zzg).n(4, null, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzf(Object obj) {
        int i2;
        int i3 = this.zzk;
        while (true) {
            i2 = this.zzl;
            if (i3 >= i2) {
                break;
            }
            long zzB = zzB(this.zzj[i3]) & 1048575;
            Object f2 = zzmv.f(obj, zzB);
            if (f2 != null) {
                ((zzld) f2).zzc();
                zzmv.s(obj, zzB, f2);
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
            this.zzo.b(obj);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzg(Object obj, Object obj2) {
        Objects.requireNonNull(obj2);
        for (int i2 = 0; i2 < this.zzc.length; i2 += 3) {
            int zzB = zzB(i2);
            long j2 = 1048575 & zzB;
            int i3 = this.zzc[i2];
            switch (zzA(zzB)) {
                case 0:
                    if (zzO(obj2, i2)) {
                        zzmv.o(obj, j2, zzmv.a(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzO(obj2, i2)) {
                        zzmv.p(obj, j2, zzmv.b(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (!zzO(obj2, i2)) {
                        break;
                    }
                    zzmv.r(obj, j2, zzmv.d(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 3:
                    if (!zzO(obj2, i2)) {
                        break;
                    }
                    zzmv.r(obj, j2, zzmv.d(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 4:
                    if (!zzO(obj2, i2)) {
                        break;
                    }
                    zzmv.q(obj, j2, zzmv.c(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 5:
                    if (!zzO(obj2, i2)) {
                        break;
                    }
                    zzmv.r(obj, j2, zzmv.d(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 6:
                    if (!zzO(obj2, i2)) {
                        break;
                    }
                    zzmv.q(obj, j2, zzmv.c(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 7:
                    if (zzO(obj2, i2)) {
                        zzmv.m(obj, j2, zzmv.w(obj2, j2));
                        zzJ(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (!zzO(obj2, i2)) {
                        break;
                    }
                    zzmv.s(obj, j2, zzmv.f(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 9:
                case 17:
                    zzH(obj, obj2, i2);
                    break;
                case 10:
                    if (!zzO(obj2, i2)) {
                        break;
                    }
                    zzmv.s(obj, j2, zzmv.f(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 11:
                    if (!zzO(obj2, i2)) {
                        break;
                    }
                    zzmv.q(obj, j2, zzmv.c(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 12:
                    if (!zzO(obj2, i2)) {
                        break;
                    }
                    zzmv.q(obj, j2, zzmv.c(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 13:
                    if (!zzO(obj2, i2)) {
                        break;
                    }
                    zzmv.q(obj, j2, zzmv.c(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 14:
                    if (!zzO(obj2, i2)) {
                        break;
                    }
                    zzmv.r(obj, j2, zzmv.d(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 15:
                    if (!zzO(obj2, i2)) {
                        break;
                    }
                    zzmv.q(obj, j2, zzmv.c(obj2, j2));
                    zzJ(obj, i2);
                    break;
                case 16:
                    if (!zzO(obj2, i2)) {
                        break;
                    }
                    zzmv.r(obj, j2, zzmv.d(obj2, j2));
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
                    zzlw.g(this.zzq, obj, obj2, j2);
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
                    if (!zzR(obj2, i3, i2)) {
                        break;
                    }
                    zzmv.s(obj, j2, zzmv.f(obj2, j2));
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
                    if (!zzR(obj2, i3, i2)) {
                        break;
                    }
                    zzmv.s(obj, j2, zzmv.f(obj2, j2));
                    zzK(obj, i3, i2);
                    break;
            }
        }
        zzlw.d(this.zzn, obj, obj2);
        if (this.zzh) {
            zzlw.c(this.zzo, obj, obj2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzh(Object obj, byte[] bArr, int i2, int i3, zzio zzioVar) {
        if (this.zzi) {
            zzu(obj, bArr, i2, i3, zzioVar);
        } else {
            a(obj, bArr, i2, i3, 0, zzioVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzi(Object obj, zznd zzndVar) {
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
            zzL(obj, zzndVar);
        } else if (this.zzh) {
            this.zzo.a(obj);
            throw null;
        } else {
            int length = this.zzc.length;
            for (int i2 = 0; i2 < length; i2 += 3) {
                int zzB = zzB(i2);
                int i3 = this.zzc[i2];
                switch (zzA(zzB)) {
                    case 0:
                        if (zzO(obj, i2)) {
                            a2 = zzmv.a(obj, zzB & 1048575);
                            zzndVar.zzf(i3, a2);
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzO(obj, i2)) {
                            b2 = zzmv.b(obj, zzB & 1048575);
                            zzndVar.zzo(i3, b2);
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzO(obj, i2)) {
                            d2 = zzmv.d(obj, zzB & 1048575);
                            zzndVar.zzt(i3, d2);
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzO(obj, i2)) {
                            d3 = zzmv.d(obj, zzB & 1048575);
                            zzndVar.zzJ(i3, d3);
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzO(obj, i2)) {
                            c2 = zzmv.c(obj, zzB & 1048575);
                            zzndVar.zzr(i3, c2);
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzO(obj, i2)) {
                            d4 = zzmv.d(obj, zzB & 1048575);
                            zzndVar.zzm(i3, d4);
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzO(obj, i2)) {
                            c3 = zzmv.c(obj, zzB & 1048575);
                            zzndVar.zzk(i3, c3);
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzO(obj, i2)) {
                            w = zzmv.w(obj, zzB & 1048575);
                            zzndVar.zzb(i3, w);
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (!zzO(obj, i2)) {
                            break;
                        }
                        zzT(i3, zzmv.f(obj, zzB & 1048575), zzndVar);
                        break;
                    case 9:
                        if (!zzO(obj, i2)) {
                            break;
                        }
                        zzndVar.zzv(i3, zzmv.f(obj, zzB & 1048575), zzE(i2));
                        break;
                    case 10:
                        if (!zzO(obj, i2)) {
                            break;
                        }
                        zzndVar.zzd(i3, (zzjb) zzmv.f(obj, zzB & 1048575));
                        break;
                    case 11:
                        if (zzO(obj, i2)) {
                            c4 = zzmv.c(obj, zzB & 1048575);
                            zzndVar.zzH(i3, c4);
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzO(obj, i2)) {
                            c5 = zzmv.c(obj, zzB & 1048575);
                            zzndVar.zzi(i3, c5);
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzO(obj, i2)) {
                            c6 = zzmv.c(obj, zzB & 1048575);
                            zzndVar.zzw(i3, c6);
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzO(obj, i2)) {
                            d5 = zzmv.d(obj, zzB & 1048575);
                            zzndVar.zzy(i3, d5);
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzO(obj, i2)) {
                            c7 = zzmv.c(obj, zzB & 1048575);
                            zzndVar.zzA(i3, c7);
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzO(obj, i2)) {
                            d6 = zzmv.d(obj, zzB & 1048575);
                            zzndVar.zzC(i3, d6);
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (!zzO(obj, i2)) {
                            break;
                        }
                        zzndVar.zzq(i3, zzmv.f(obj, zzB & 1048575), zzE(i2));
                        break;
                    case 18:
                        zzlw.zzJ(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 19:
                        zzlw.zzN(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 20:
                        zzlw.zzQ(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 21:
                        zzlw.zzY(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 22:
                        zzlw.zzP(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 23:
                        zzlw.zzM(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 24:
                        zzlw.zzL(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 25:
                        zzlw.zzH(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 26:
                        zzlw.zzW(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar);
                        break;
                    case 27:
                        zzlw.zzR(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, zzE(i2));
                        break;
                    case 28:
                        zzlw.zzI(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar);
                        break;
                    case 29:
                        zzlw.zzX(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 30:
                        zzlw.zzK(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 31:
                        zzlw.zzS(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 32:
                        zzlw.zzT(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 33:
                        zzlw.zzU(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 34:
                        zzlw.zzV(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 35:
                        zzlw.zzJ(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 36:
                        zzlw.zzN(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 37:
                        zzlw.zzQ(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 38:
                        zzlw.zzY(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 39:
                        zzlw.zzP(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 40:
                        zzlw.zzM(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 41:
                        zzlw.zzL(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 42:
                        zzlw.zzH(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 43:
                        zzlw.zzX(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 44:
                        zzlw.zzK(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 45:
                        zzlw.zzS(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 46:
                        zzlw.zzT(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 47:
                        zzlw.zzU(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 48:
                        zzlw.zzV(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 49:
                        zzlw.zzO(i3, (List) zzmv.f(obj, zzB & 1048575), zzndVar, zzE(i2));
                        break;
                    case 50:
                        zzM(zzndVar, i3, zzmv.f(obj, zzB & 1048575), i2);
                        break;
                    case 51:
                        if (zzR(obj, i3, i2)) {
                            a2 = zzn(obj, zzB & 1048575);
                            zzndVar.zzf(i3, a2);
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zzR(obj, i3, i2)) {
                            b2 = zzo(obj, zzB & 1048575);
                            zzndVar.zzo(i3, b2);
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zzR(obj, i3, i2)) {
                            d2 = zzC(obj, zzB & 1048575);
                            zzndVar.zzt(i3, d2);
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zzR(obj, i3, i2)) {
                            d3 = zzC(obj, zzB & 1048575);
                            zzndVar.zzJ(i3, d3);
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zzR(obj, i3, i2)) {
                            c2 = zzr(obj, zzB & 1048575);
                            zzndVar.zzr(i3, c2);
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zzR(obj, i3, i2)) {
                            d4 = zzC(obj, zzB & 1048575);
                            zzndVar.zzm(i3, d4);
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zzR(obj, i3, i2)) {
                            c3 = zzr(obj, zzB & 1048575);
                            zzndVar.zzk(i3, c3);
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zzR(obj, i3, i2)) {
                            w = zzS(obj, zzB & 1048575);
                            zzndVar.zzb(i3, w);
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (!zzR(obj, i3, i2)) {
                            break;
                        }
                        zzT(i3, zzmv.f(obj, zzB & 1048575), zzndVar);
                        break;
                    case 60:
                        if (!zzR(obj, i3, i2)) {
                            break;
                        }
                        zzndVar.zzv(i3, zzmv.f(obj, zzB & 1048575), zzE(i2));
                        break;
                    case 61:
                        if (!zzR(obj, i3, i2)) {
                            break;
                        }
                        zzndVar.zzd(i3, (zzjb) zzmv.f(obj, zzB & 1048575));
                        break;
                    case 62:
                        if (zzR(obj, i3, i2)) {
                            c4 = zzr(obj, zzB & 1048575);
                            zzndVar.zzH(i3, c4);
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zzR(obj, i3, i2)) {
                            c5 = zzr(obj, zzB & 1048575);
                            zzndVar.zzi(i3, c5);
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zzR(obj, i3, i2)) {
                            c6 = zzr(obj, zzB & 1048575);
                            zzndVar.zzw(i3, c6);
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zzR(obj, i3, i2)) {
                            d5 = zzC(obj, zzB & 1048575);
                            zzndVar.zzy(i3, d5);
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zzR(obj, i3, i2)) {
                            c7 = zzr(obj, zzB & 1048575);
                            zzndVar.zzA(i3, c7);
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zzR(obj, i3, i2)) {
                            d6 = zzC(obj, zzB & 1048575);
                            zzndVar.zzC(i3, d6);
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (!zzR(obj, i3, i2)) {
                            break;
                        }
                        zzndVar.zzq(i3, zzmv.f(obj, zzB & 1048575), zzE(i2));
                        break;
                }
            }
            zzml zzmlVar = this.zzn;
            zzmlVar.i(zzmlVar.c(obj), zzndVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final boolean zzj(Object obj, Object obj2) {
        int length = this.zzc.length;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int zzB = zzB(i2);
            long j2 = zzB & 1048575;
            switch (zzA(zzB)) {
                case 0:
                    if (zzN(obj, obj2, i2) && Double.doubleToLongBits(zzmv.a(obj, j2)) == Double.doubleToLongBits(zzmv.a(obj2, j2))) {
                        break;
                    }
                    return false;
                case 1:
                    if (zzN(obj, obj2, i2) && Float.floatToIntBits(zzmv.b(obj, j2)) == Float.floatToIntBits(zzmv.b(obj2, j2))) {
                        break;
                    }
                    return false;
                case 2:
                    if (zzN(obj, obj2, i2) && zzmv.d(obj, j2) == zzmv.d(obj2, j2)) {
                        break;
                    }
                    return false;
                case 3:
                    if (zzN(obj, obj2, i2) && zzmv.d(obj, j2) == zzmv.d(obj2, j2)) {
                        break;
                    }
                    return false;
                case 4:
                    if (zzN(obj, obj2, i2) && zzmv.c(obj, j2) == zzmv.c(obj2, j2)) {
                        break;
                    }
                    return false;
                case 5:
                    if (zzN(obj, obj2, i2) && zzmv.d(obj, j2) == zzmv.d(obj2, j2)) {
                        break;
                    }
                    return false;
                case 6:
                    if (zzN(obj, obj2, i2) && zzmv.c(obj, j2) == zzmv.c(obj2, j2)) {
                        break;
                    }
                    return false;
                case 7:
                    if (zzN(obj, obj2, i2) && zzmv.w(obj, j2) == zzmv.w(obj2, j2)) {
                        break;
                    }
                    return false;
                case 8:
                    if (zzN(obj, obj2, i2) && zzlw.e(zzmv.f(obj, j2), zzmv.f(obj2, j2))) {
                        break;
                    }
                    return false;
                case 9:
                    if (zzN(obj, obj2, i2) && zzlw.e(zzmv.f(obj, j2), zzmv.f(obj2, j2))) {
                        break;
                    }
                    return false;
                case 10:
                    if (zzN(obj, obj2, i2) && zzlw.e(zzmv.f(obj, j2), zzmv.f(obj2, j2))) {
                        break;
                    }
                    return false;
                case 11:
                    if (zzN(obj, obj2, i2) && zzmv.c(obj, j2) == zzmv.c(obj2, j2)) {
                        break;
                    }
                    return false;
                case 12:
                    if (zzN(obj, obj2, i2) && zzmv.c(obj, j2) == zzmv.c(obj2, j2)) {
                        break;
                    }
                    return false;
                case 13:
                    if (zzN(obj, obj2, i2) && zzmv.c(obj, j2) == zzmv.c(obj2, j2)) {
                        break;
                    }
                    return false;
                case 14:
                    if (zzN(obj, obj2, i2) && zzmv.d(obj, j2) == zzmv.d(obj2, j2)) {
                        break;
                    }
                    return false;
                case 15:
                    if (zzN(obj, obj2, i2) && zzmv.c(obj, j2) == zzmv.c(obj2, j2)) {
                        break;
                    }
                    return false;
                case 16:
                    if (zzN(obj, obj2, i2) && zzmv.d(obj, j2) == zzmv.d(obj2, j2)) {
                        break;
                    }
                    return false;
                case 17:
                    if (zzN(obj, obj2, i2) && zzlw.e(zzmv.f(obj, j2), zzmv.f(obj2, j2))) {
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
                    if (zzlw.e(zzmv.f(obj, j2), zzmv.f(obj2, j2))) {
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
                    if (zzmv.c(obj, zzy) == zzmv.c(obj2, zzy) && zzlw.e(zzmv.f(obj, j2), zzmv.f(obj2, j2))) {
                        break;
                    }
                    return false;
            }
        }
        if (this.zzn.c(obj).equals(this.zzn.c(obj2))) {
            if (this.zzh) {
                this.zzo.a(obj);
                this.zzo.a(obj2);
                throw null;
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final boolean zzk(Object obj) {
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
            if ((268435456 & zzB) != 0 && !zzP(obj, i7, i2, i3, i11)) {
                return false;
            }
            int zzA = zzA(zzB);
            if (zzA != 9 && zzA != 17) {
                if (zzA != 27) {
                    if (zzA == 60 || zzA == 68) {
                        if (zzR(obj, i8, i7) && !zzQ(obj, zzB, zzE(i7))) {
                            return false;
                        }
                    } else if (zzA != 49) {
                        if (zzA == 50 && !((zzld) zzmv.f(obj, zzB & 1048575)).isEmpty()) {
                            zzlc zzlcVar = (zzlc) zzF(i7);
                            throw null;
                        }
                    }
                }
                List list = (List) zzmv.f(obj, zzB & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzlu zzE = zzE(i7);
                    for (int i12 = 0; i12 < list.size(); i12++) {
                        if (!zzE.zzk(list.get(i12))) {
                            return false;
                        }
                    }
                    continue;
                }
            } else if (zzP(obj, i7, i2, i3, i11) && !zzQ(obj, zzB, zzE(i7))) {
                return false;
            }
            i6++;
            i4 = i2;
            i5 = i3;
        }
        if (this.zzh) {
            this.zzo.a(obj);
            throw null;
        }
        return true;
    }
}
