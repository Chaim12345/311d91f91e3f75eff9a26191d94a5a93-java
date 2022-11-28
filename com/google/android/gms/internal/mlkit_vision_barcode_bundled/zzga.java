package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.nio.charset.Charset;
import java.util.Iterator;
import org.bouncycastle.tls.CipherSuite;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzga extends zzdb {

    /* renamed from: a  reason: collision with root package name */
    static final int[] f6431a = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, 233, 377, TypedValues.Motion.TYPE_QUANTIZE_MOTIONSTEPS, AppConstants.REQ_CONTACT_PERMISSION_SETTINGS, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, Integer.MAX_VALUE};
    private final int zzc;
    private final zzdb zzd;
    private final zzdb zze;
    private final int zzf;
    private final int zzg;

    private zzga(zzdb zzdbVar, zzdb zzdbVar2) {
        this.zzd = zzdbVar;
        this.zze = zzdbVar2;
        int zzd = zzdbVar.zzd();
        this.zzf = zzd;
        this.zzc = zzd + zzdbVar2.zzd();
        this.zzg = Math.max(zzdbVar.c(), zzdbVar2.c()) + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzga(zzdb zzdbVar, zzdb zzdbVar2, zzfx zzfxVar) {
        this(zzdbVar, zzdbVar2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzdb l(zzdb zzdbVar, zzdb zzdbVar2) {
        if (zzdbVar2.zzd() == 0) {
            return zzdbVar;
        }
        if (zzdbVar.zzd() == 0) {
            return zzdbVar2;
        }
        int zzd = zzdbVar.zzd() + zzdbVar2.zzd();
        if (zzd < 128) {
            return zzB(zzdbVar, zzdbVar2);
        }
        if (zzdbVar instanceof zzga) {
            zzga zzgaVar = (zzga) zzdbVar;
            if (zzgaVar.zze.zzd() + zzdbVar2.zzd() < 128) {
                return new zzga(zzgaVar.zzd, zzB(zzgaVar.zze, zzdbVar2));
            } else if (zzgaVar.zzd.c() > zzgaVar.zze.c() && zzgaVar.zzg > zzdbVar2.c()) {
                return new zzga(zzgaVar.zzd, new zzga(zzgaVar.zze, zzdbVar2));
            }
        }
        return zzd >= m(Math.max(zzdbVar.c(), zzdbVar2.c()) + 1) ? new zzga(zzdbVar, zzdbVar2) : zzfy.a(new zzfy(null), zzdbVar, zzdbVar2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int m(int i2) {
        int[] iArr = f6431a;
        int length = iArr.length;
        if (i2 >= 47) {
            return Integer.MAX_VALUE;
        }
        return iArr[i2];
    }

    private static zzdb zzB(zzdb zzdbVar, zzdb zzdbVar2) {
        int zzd = zzdbVar.zzd();
        int zzd2 = zzdbVar2.zzd();
        byte[] bArr = new byte[zzd + zzd2];
        zzdbVar.zzx(bArr, 0, 0, zzd);
        zzdbVar2.zzx(bArr, 0, zzd, zzd2);
        return new zzcz(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final byte a(int i2) {
        int i3 = this.zzf;
        return i2 < i3 ? this.zzd.a(i2) : this.zze.a(i2 - i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final void b(byte[] bArr, int i2, int i3, int i4) {
        int i5 = this.zzf;
        if (i2 + i4 <= i5) {
            this.zzd.b(bArr, i2, i3, i4);
        } else if (i2 >= i5) {
            this.zze.b(bArr, i2 - i5, i3, i4);
        } else {
            int i6 = i5 - i2;
            this.zzd.b(bArr, i2, i3, i6);
            this.zze.b(bArr, 0, i3 + i6, i4 - i6);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final int c() {
        return this.zzg;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final boolean d() {
        return this.zzc >= m(this.zzg);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final int e(int i2, int i3, int i4) {
        int i5 = this.zzf;
        if (i3 + i4 <= i5) {
            return this.zzd.e(i2, i3, i4);
        }
        if (i3 >= i5) {
            return this.zze.e(i2, i3 - i5, i4);
        }
        int i6 = i5 - i3;
        return this.zze.e(this.zzd.e(i2, i3, i6), 0, i4 - i6);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdb)) {
            return false;
        }
        zzdb zzdbVar = (zzdb) obj;
        if (this.zzc != zzdbVar.zzd()) {
            return false;
        }
        if (this.zzc == 0) {
            return true;
        }
        int j2 = j();
        int j3 = zzdbVar.j();
        if (j2 != 0 && j3 != 0 && j2 != j3) {
            return false;
        }
        zzfz zzfzVar = new zzfz(this, null);
        zzcy next = zzfzVar.next();
        zzfz zzfzVar2 = new zzfz(zzdbVar, null);
        zzcy next2 = zzfzVar2.next();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int zzd = next.zzd() - i2;
            int zzd2 = next2.zzd() - i3;
            int min = Math.min(zzd, zzd2);
            if (!(i2 == 0 ? next.l(next2, i3, min) : next2.l(next, i2, min))) {
                return false;
            }
            i4 += min;
            int i5 = this.zzc;
            if (i4 >= i5) {
                if (i4 == i5) {
                    return true;
                }
                throw new IllegalStateException();
            }
            if (min == zzd) {
                next = zzfzVar.next();
                i2 = 0;
            } else {
                i2 += min;
            }
            if (min == zzd2) {
                next2 = zzfzVar2.next();
                i3 = 0;
            } else {
                i3 += min;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final int f(int i2, int i3, int i4) {
        int i5 = this.zzf;
        if (i3 + i4 <= i5) {
            return this.zzd.f(i2, i3, i4);
        }
        if (i3 >= i5) {
            return this.zze.f(i2, i3 - i5, i4);
        }
        int i6 = i5 - i3;
        return this.zze.f(this.zzd.f(i2, i3, i6), 0, i4 - i6);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    protected final String g(Charset charset) {
        return new String(zzy(), charset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final void h(zzcr zzcrVar) {
        this.zzd.h(zzcrVar);
        this.zze.h(zzcrVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return new zzfx(this);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final byte zza(int i2) {
        zzdb.k(i2, this.zzc);
        return a(i2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final int zzd() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final zzdb zzk(int i2, int i3) {
        int i4 = zzdb.i(i2, i3, this.zzc);
        if (i4 == 0) {
            return zzdb.zzb;
        }
        if (i4 == this.zzc) {
            return this;
        }
        int i5 = this.zzf;
        if (i3 <= i5) {
            return this.zzd.zzk(i2, i3);
        }
        if (i2 >= i5) {
            return this.zze.zzk(i2 - i5, i3 - i5);
        }
        zzdb zzdbVar = this.zzd;
        return new zzga(zzdbVar.zzk(i2, zzdbVar.zzd()), this.zze.zzk(0, i3 - this.zzf));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final boolean zzn() {
        int f2 = this.zzd.f(0, 0, this.zzf);
        zzdb zzdbVar = this.zze;
        return zzdbVar.f(f2, 0, zzdbVar.zzd()) == 0;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final zzcx zzq() {
        return new zzfx(this);
    }
}
