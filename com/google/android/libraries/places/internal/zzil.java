package com.google.android.libraries.places.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import javax.annotation.CheckForNull;
/* loaded from: classes2.dex */
final class zzil extends zzhv {
    static final zzhv zza = new zzil(null, new Object[0], 0);
    final transient Object[] zzb;
    @CheckForNull
    private final transient Object zzc;
    private final transient int zzd;

    private zzil(@CheckForNull Object obj, Object[] objArr, int i2) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r5v4, types: [int[]] */
    /* JADX WARN: Type inference failed for: r5v7 */
    public static zzil zzf(int i2, Object[] objArr, zzhu zzhuVar) {
        short[] sArr;
        byte[] bArr;
        Object[] objArr2;
        int i3 = i2;
        Object[] objArr3 = objArr;
        if (i3 == 0) {
            return (zzil) zza;
        }
        Object obj = null;
        if (i3 == 1) {
            Object obj2 = objArr3[0];
            obj2.getClass();
            Object obj3 = objArr3[1];
            obj3.getClass();
            zzhh.zza(obj2, obj3);
            return new zzil(null, objArr3, 1);
        }
        zzha.zzb(i3, objArr3.length >> 1, FirebaseAnalytics.Param.INDEX);
        char c2 = 2;
        int max = Math.max(i3, 2);
        int i4 = 1073741824;
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1);
            i4 = highestOneBit + highestOneBit;
            while (i4 * 0.7d < max) {
                i4 += i4;
            }
        } else {
            zzha.zze(max < 1073741824, "collection too large");
        }
        if (i3 == 1) {
            Object obj4 = objArr3[0];
            obj4.getClass();
            Object obj5 = objArr3[1];
            obj5.getClass();
            zzhh.zza(obj4, obj5);
        } else {
            int i5 = i4 - 1;
            char c3 = 65535;
            if (i4 <= 128) {
                byte[] bArr2 = new byte[i4];
                Arrays.fill(bArr2, (byte) -1);
                int i6 = 0;
                for (int i7 = 0; i7 < i3; i7++) {
                    int i8 = i7 + i7;
                    int i9 = i6 + i6;
                    Object obj6 = objArr3[i8];
                    obj6.getClass();
                    Object obj7 = objArr3[i8 ^ 1];
                    obj7.getClass();
                    zzhh.zza(obj6, obj7);
                    int zza2 = zzho.zza(obj6.hashCode());
                    while (true) {
                        int i10 = zza2 & i5;
                        int i11 = bArr2[i10] & 255;
                        if (i11 == 255) {
                            bArr2[i10] = (byte) i9;
                            if (i6 < i7) {
                                objArr3[i9] = obj6;
                                objArr3[i9 ^ 1] = obj7;
                            }
                            i6++;
                        } else if (obj6.equals(objArr3[i11])) {
                            int i12 = i11 ^ 1;
                            Object obj8 = objArr3[i12];
                            obj8.getClass();
                            zzht zzhtVar = new zzht(obj6, obj7, obj8);
                            objArr3[i12] = obj7;
                            obj = zzhtVar;
                            break;
                        } else {
                            zza2 = i10 + 1;
                        }
                    }
                }
                if (i6 == i3) {
                    bArr = bArr2;
                    c2 = 2;
                    obj = bArr;
                } else {
                    obj = new Object[]{bArr2, Integer.valueOf(i6), obj};
                    c2 = 2;
                }
            } else if (i4 <= 32768) {
                sArr = new short[i4];
                Arrays.fill(sArr, (short) -1);
                int i13 = 0;
                for (int i14 = 0; i14 < i3; i14++) {
                    int i15 = i14 + i14;
                    int i16 = i13 + i13;
                    Object obj9 = objArr3[i15];
                    obj9.getClass();
                    Object obj10 = objArr3[i15 ^ 1];
                    obj10.getClass();
                    zzhh.zza(obj9, obj10);
                    int zza3 = zzho.zza(obj9.hashCode());
                    while (true) {
                        int i17 = zza3 & i5;
                        char c4 = (char) sArr[i17];
                        if (c4 == 65535) {
                            sArr[i17] = (short) i16;
                            if (i13 < i14) {
                                objArr3[i16] = obj9;
                                objArr3[i16 ^ 1] = obj10;
                            }
                            i13++;
                        } else if (obj9.equals(objArr3[c4])) {
                            int i18 = c4 ^ 1;
                            Object obj11 = objArr3[i18];
                            obj11.getClass();
                            zzht zzhtVar2 = new zzht(obj9, obj10, obj11);
                            objArr3[i18] = obj10;
                            obj = zzhtVar2;
                            break;
                        } else {
                            zza3 = i17 + 1;
                        }
                    }
                }
                if (i13 != i3) {
                    c2 = 2;
                    objArr2 = new Object[]{sArr, Integer.valueOf(i13), obj};
                    obj = objArr2;
                }
                bArr = sArr;
                c2 = 2;
                obj = bArr;
            } else {
                sArr = new int[i4];
                Arrays.fill((int[]) sArr, -1);
                int i19 = 0;
                int i20 = 0;
                while (i19 < i3) {
                    int i21 = i19 + i19;
                    int i22 = i20 + i20;
                    Object obj12 = objArr3[i21];
                    obj12.getClass();
                    Object obj13 = objArr3[i21 ^ 1];
                    obj13.getClass();
                    zzhh.zza(obj12, obj13);
                    int zza4 = zzho.zza(obj12.hashCode());
                    while (true) {
                        int i23 = zza4 & i5;
                        ?? r15 = sArr[i23];
                        if (r15 == c3) {
                            sArr[i23] = i22;
                            if (i20 < i19) {
                                objArr3[i22] = obj12;
                                objArr3[i22 ^ 1] = obj13;
                            }
                            i20++;
                        } else if (obj12.equals(objArr3[r15])) {
                            int i24 = r15 ^ 1;
                            Object obj14 = objArr3[i24];
                            obj14.getClass();
                            zzht zzhtVar3 = new zzht(obj12, obj13, obj14);
                            objArr3[i24] = obj13;
                            obj = zzhtVar3;
                            break;
                        } else {
                            zza4 = i23 + 1;
                            c3 = 65535;
                        }
                    }
                    i19++;
                    c3 = 65535;
                }
                if (i20 != i3) {
                    c2 = 2;
                    objArr2 = new Object[]{sArr, Integer.valueOf(i20), obj};
                    obj = objArr2;
                }
                bArr = sArr;
                c2 = 2;
                obj = bArr;
            }
        }
        if (obj instanceof Object[]) {
            Object[] objArr4 = (Object[]) obj;
            zzhuVar.zzc = (zzht) objArr4[c2];
            Object obj15 = objArr4[0];
            int intValue = ((Integer) objArr4[1]).intValue();
            objArr3 = Arrays.copyOf(objArr3, intValue + intValue);
            obj = obj15;
            i3 = intValue;
        }
        return new zzil(obj, objArr3, i3);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x009e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009f A[RETURN] */
    @Override // com.google.android.libraries.places.internal.zzhv, java.util.Map
    @CheckForNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object get(@CheckForNull Object obj) {
        Object obj2;
        Object obj3 = this.zzc;
        Object[] objArr = this.zzb;
        int i2 = this.zzd;
        if (obj != null) {
            if (i2 == 1) {
                Object obj4 = objArr[0];
                obj4.getClass();
                if (obj4.equals(obj)) {
                    obj2 = objArr[1];
                    obj2.getClass();
                }
            } else if (obj3 != null) {
                if (obj3 instanceof byte[]) {
                    byte[] bArr = (byte[]) obj3;
                    int length = bArr.length - 1;
                    int zza2 = zzho.zza(obj.hashCode());
                    while (true) {
                        int i3 = zza2 & length;
                        int i4 = bArr[i3] & 255;
                        if (i4 == 255) {
                            break;
                        } else if (obj.equals(objArr[i4])) {
                            obj2 = objArr[i4 ^ 1];
                            break;
                        } else {
                            zza2 = i3 + 1;
                        }
                    }
                } else if (obj3 instanceof short[]) {
                    short[] sArr = (short[]) obj3;
                    int length2 = sArr.length - 1;
                    int zza3 = zzho.zza(obj.hashCode());
                    while (true) {
                        int i5 = zza3 & length2;
                        char c2 = (char) sArr[i5];
                        if (c2 == 65535) {
                            break;
                        } else if (obj.equals(objArr[c2])) {
                            obj2 = objArr[c2 ^ 1];
                            break;
                        } else {
                            zza3 = i5 + 1;
                        }
                    }
                } else {
                    int[] iArr = (int[]) obj3;
                    int length3 = iArr.length - 1;
                    int zza4 = zzho.zza(obj.hashCode());
                    while (true) {
                        int i6 = zza4 & length3;
                        int i7 = iArr[i6];
                        if (i7 == -1) {
                            break;
                        } else if (obj.equals(objArr[i7])) {
                            obj2 = objArr[i7 ^ 1];
                            break;
                        } else {
                            zza4 = i6 + 1;
                        }
                    }
                }
            }
            if (obj2 != null) {
                return null;
            }
            return obj2;
        }
        obj2 = null;
        if (obj2 != null) {
        }
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.libraries.places.internal.zzhv
    final zzhp zza() {
        return new zzik(this.zzb, 1, this.zzd);
    }

    @Override // com.google.android.libraries.places.internal.zzhv
    final zzhw zzc() {
        return new zzii(this, this.zzb, 0, this.zzd);
    }

    @Override // com.google.android.libraries.places.internal.zzhv
    final zzhw zzd() {
        return new zzij(this, new zzik(this.zzb, 0, this.zzd));
    }
}
