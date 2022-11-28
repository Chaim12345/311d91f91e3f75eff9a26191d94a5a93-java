package com.google.android.gms.internal.measurement;
/* loaded from: classes.dex */
final class zziv extends zziy {
    private final int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zziv(byte[] bArr, int i2, int i3) {
        super(bArr);
        zzjb.e(0, i3, bArr.length);
        this.zzc = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zziy, com.google.android.gms.internal.measurement.zzjb
    public final byte a(int i2) {
        return this.f6093a[i2];
    }

    @Override // com.google.android.gms.internal.measurement.zziy
    protected final int g() {
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zziy, com.google.android.gms.internal.measurement.zzjb
    public final byte zza(int i2) {
        int i3 = this.zzc;
        if (((i3 - (i2 + 1)) | i2) < 0) {
            if (i2 < 0) {
                throw new ArrayIndexOutOfBoundsException("Index < 0: " + i2);
            }
            throw new ArrayIndexOutOfBoundsException("Index > length: " + i2 + ", " + i3);
        }
        return this.f6093a[i2];
    }

    @Override // com.google.android.gms.internal.measurement.zziy, com.google.android.gms.internal.measurement.zzjb
    public final int zzd() {
        return this.zzc;
    }
}
