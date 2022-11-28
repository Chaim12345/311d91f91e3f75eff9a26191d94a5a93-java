package com.google.android.gms.internal.measurement;

import java.nio.charset.Charset;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class zziy extends zzix {

    /* renamed from: a  reason: collision with root package name */
    protected final byte[] f6093a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zziy(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.f6093a = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzjb
    public byte a(int i2) {
        return this.f6093a[i2];
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    protected final int b(int i2, int i3, int i4) {
        return zzkk.a(i2, this.f6093a, 0, i4);
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    protected final String c(Charset charset) {
        return new String(this.f6093a, 0, zzd(), charset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzjb
    public final void d(zzir zzirVar) {
        ((zzjg) zzirVar).zzc(this.f6093a, 0, zzd());
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzjb) && zzd() == ((zzjb) obj).zzd()) {
            if (zzd() == 0) {
                return true;
            }
            if (obj instanceof zziy) {
                zziy zziyVar = (zziy) obj;
                int f2 = f();
                int f3 = zziyVar.f();
                if (f2 == 0 || f3 == 0 || f2 == f3) {
                    int zzd = zzd();
                    if (zzd > zziyVar.zzd()) {
                        throw new IllegalArgumentException("Length too large: " + zzd + zzd());
                    } else if (zzd > zziyVar.zzd()) {
                        throw new IllegalArgumentException("Ran off end of other: 0, " + zzd + ", " + zziyVar.zzd());
                    } else {
                        byte[] bArr = this.f6093a;
                        byte[] bArr2 = zziyVar.f6093a;
                        zziyVar.g();
                        int i2 = 0;
                        int i3 = 0;
                        while (i2 < zzd) {
                            if (bArr[i2] != bArr2[i3]) {
                                return false;
                            }
                            i2++;
                            i3++;
                        }
                        return true;
                    }
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    protected int g() {
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    public byte zza(int i2) {
        return this.f6093a[i2];
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    public int zzd() {
        return this.f6093a.length;
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    public final zzjb zzf(int i2, int i3) {
        int e2 = zzjb.e(0, i3, zzd());
        return e2 == 0 ? zzjb.zzb : new zziv(this.f6093a, 0, e2);
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    public final boolean zzi() {
        return zzna.f(this.f6093a, 0, zzd());
    }
}
