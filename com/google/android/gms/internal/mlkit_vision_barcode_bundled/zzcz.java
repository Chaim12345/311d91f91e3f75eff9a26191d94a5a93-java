package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.nio.charset.Charset;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class zzcz extends zzcy {

    /* renamed from: a  reason: collision with root package name */
    protected final byte[] f6411a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcz(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.f6411a = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public byte a(int i2) {
        return this.f6411a[i2];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public void b(byte[] bArr, int i2, int i3, int i4) {
        System.arraycopy(this.f6411a, i2, bArr, i3, i4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final int e(int i2, int i3, int i4) {
        return zzel.a(i2, this.f6411a, m() + i3, i4);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzdb) && zzd() == ((zzdb) obj).zzd()) {
            if (zzd() == 0) {
                return true;
            }
            if (obj instanceof zzcz) {
                zzcz zzczVar = (zzcz) obj;
                int j2 = j();
                int j3 = zzczVar.j();
                if (j2 == 0 || j3 == 0 || j2 == j3) {
                    return l(zzczVar, 0, zzd());
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final int f(int i2, int i3, int i4) {
        int m2 = m() + i3;
        return zzhe.f(i2, this.f6411a, m2, i4 + m2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    protected final String g(Charset charset) {
        return new String(this.f6411a, m(), zzd(), charset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final void h(zzcr zzcrVar) {
        ((zzdg) zzcrVar).zzc(this.f6411a, m(), zzd());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcy
    final boolean l(zzdb zzdbVar, int i2, int i3) {
        if (i3 > zzdbVar.zzd()) {
            int zzd = zzd();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i3);
            sb.append(zzd);
            throw new IllegalArgumentException(sb.toString());
        }
        int i4 = i2 + i3;
        if (i4 > zzdbVar.zzd()) {
            int zzd2 = zzdbVar.zzd();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(i3);
            sb2.append(", ");
            sb2.append(zzd2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (zzdbVar instanceof zzcz) {
            zzcz zzczVar = (zzcz) zzdbVar;
            byte[] bArr = this.f6411a;
            byte[] bArr2 = zzczVar.f6411a;
            int m2 = m() + i3;
            int m3 = m();
            int m4 = zzczVar.m() + i2;
            while (m3 < m2) {
                if (bArr[m3] != bArr2[m4]) {
                    return false;
                }
                m3++;
                m4++;
            }
            return true;
        } else {
            return zzdbVar.zzk(i2, i4).equals(zzk(0, i3));
        }
    }

    protected int m() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public byte zza(int i2) {
        return this.f6411a[i2];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public int zzd() {
        return this.f6411a.length;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final zzdb zzk(int i2, int i3) {
        int i4 = zzdb.i(i2, i3, zzd());
        return i4 == 0 ? zzdb.zzb : new zzcw(this.f6411a, m() + i2, i4);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final boolean zzn() {
        int m2 = m();
        return zzhe.i(this.f6411a, m2, zzd() + m2);
    }
}
