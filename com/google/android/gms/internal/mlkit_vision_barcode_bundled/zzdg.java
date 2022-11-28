package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzdg extends zzdi {
    private final byte[] zzb;
    private final int zzc;
    private int zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdg(byte[] bArr, int i2, int i3) {
        super(null);
        Objects.requireNonNull(bArr, "buffer");
        int length = bArr.length;
        if (((length - i3) | i3) < 0) {
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(length), 0, Integer.valueOf(i3)));
        }
        this.zzb = bArr;
        this.zzd = 0;
        this.zzc = i3;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi
    public final int zza() {
        return this.zzc - this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi
    public final void zzb(byte b2) {
        try {
            byte[] bArr = this.zzb;
            int i2 = this.zzd;
            this.zzd = i2 + 1;
            bArr[i2] = b2;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzdh(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e2);
        }
    }

    public final void zzc(byte[] bArr, int i2, int i3) {
        try {
            System.arraycopy(bArr, i2, this.zzb, this.zzd, i3);
            this.zzd += i3;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzdh(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), Integer.valueOf(i3)), e2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi
    public final void zzd(int i2, boolean z) {
        zzq(i2 << 3);
        zzb(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi
    public final void zze(int i2, zzdb zzdbVar) {
        zzq((i2 << 3) | 2);
        zzq(zzdbVar.zzd());
        zzdbVar.h(this);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi
    public final void zzf(int i2, int i3) {
        zzq((i2 << 3) | 5);
        zzg(i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi
    public final void zzg(int i2) {
        try {
            byte[] bArr = this.zzb;
            int i3 = this.zzd;
            int i4 = i3 + 1;
            this.zzd = i4;
            bArr[i3] = (byte) (i2 & 255);
            int i5 = i4 + 1;
            this.zzd = i5;
            bArr[i4] = (byte) ((i2 >> 8) & 255);
            int i6 = i5 + 1;
            this.zzd = i6;
            bArr[i5] = (byte) ((i2 >> 16) & 255);
            this.zzd = i6 + 1;
            bArr[i6] = (byte) ((i2 >> 24) & 255);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzdh(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi
    public final void zzh(int i2, long j2) {
        zzq((i2 << 3) | 1);
        zzi(j2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi
    public final void zzi(long j2) {
        try {
            byte[] bArr = this.zzb;
            int i2 = this.zzd;
            int i3 = i2 + 1;
            this.zzd = i3;
            bArr[i2] = (byte) (((int) j2) & 255);
            int i4 = i3 + 1;
            this.zzd = i4;
            bArr[i3] = (byte) (((int) (j2 >> 8)) & 255);
            int i5 = i4 + 1;
            this.zzd = i5;
            bArr[i4] = (byte) (((int) (j2 >> 16)) & 255);
            int i6 = i5 + 1;
            this.zzd = i6;
            bArr[i5] = (byte) (((int) (j2 >> 24)) & 255);
            int i7 = i6 + 1;
            this.zzd = i7;
            bArr[i6] = (byte) (((int) (j2 >> 32)) & 255);
            int i8 = i7 + 1;
            this.zzd = i8;
            bArr[i7] = (byte) (((int) (j2 >> 40)) & 255);
            int i9 = i8 + 1;
            this.zzd = i9;
            bArr[i8] = (byte) (((int) (j2 >> 48)) & 255);
            this.zzd = i9 + 1;
            bArr[i9] = (byte) (((int) (j2 >> 56)) & 255);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzdh(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi
    public final void zzj(int i2, int i3) {
        zzq(i2 << 3);
        zzk(i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi
    public final void zzk(int i2) {
        if (i2 >= 0) {
            zzq(i2);
        } else {
            zzs(i2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi
    public final void zzl(byte[] bArr, int i2, int i3) {
        zzc(bArr, 0, i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi
    public final void zzm(int i2, String str) {
        zzq((i2 << 3) | 2);
        zzn(str);
    }

    public final void zzn(String str) {
        int i2 = this.zzd;
        try {
            int zzD = zzdi.zzD(str.length() * 3);
            int zzD2 = zzdi.zzD(str.length());
            if (zzD2 != zzD) {
                zzq(zzhe.e(str));
                byte[] bArr = this.zzb;
                int i3 = this.zzd;
                this.zzd = zzhe.d(str, bArr, i3, this.zzc - i3);
                return;
            }
            int i4 = i2 + zzD2;
            this.zzd = i4;
            int d2 = zzhe.d(str, this.zzb, i4, this.zzc - i4);
            this.zzd = i2;
            zzq((d2 - i2) - zzD2);
            this.zzd = d2;
        } catch (zzhd e2) {
            this.zzd = i2;
            b(str, e2);
        } catch (IndexOutOfBoundsException e3) {
            throw new zzdh(e3);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi
    public final void zzo(int i2, int i3) {
        zzq((i2 << 3) | i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi
    public final void zzp(int i2, int i3) {
        zzq(i2 << 3);
        zzq(i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi
    public final void zzq(int i2) {
        boolean z;
        z = zzdi.zzc;
        if (z) {
            int i3 = zzcn.zza;
        }
        while ((i2 & (-128)) != 0) {
            try {
                byte[] bArr = this.zzb;
                int i4 = this.zzd;
                this.zzd = i4 + 1;
                bArr[i4] = (byte) ((i2 & 127) | 128);
                i2 >>>= 7;
            } catch (IndexOutOfBoundsException e2) {
                throw new zzdh(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e2);
            }
        }
        byte[] bArr2 = this.zzb;
        int i5 = this.zzd;
        this.zzd = i5 + 1;
        bArr2[i5] = (byte) i2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi
    public final void zzr(int i2, long j2) {
        zzq(i2 << 3);
        zzs(j2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi
    public final void zzs(long j2) {
        boolean z;
        z = zzdi.zzc;
        if (z && this.zzc - this.zzd >= 10) {
            while ((j2 & (-128)) != 0) {
                byte[] bArr = this.zzb;
                int i2 = this.zzd;
                this.zzd = i2 + 1;
                zzgz.n(bArr, i2, (byte) ((((int) j2) & 127) | 128));
                j2 >>>= 7;
            }
            byte[] bArr2 = this.zzb;
            int i3 = this.zzd;
            this.zzd = i3 + 1;
            zzgz.n(bArr2, i3, (byte) j2);
            return;
        }
        while ((j2 & (-128)) != 0) {
            try {
                byte[] bArr3 = this.zzb;
                int i4 = this.zzd;
                this.zzd = i4 + 1;
                bArr3[i4] = (byte) ((((int) j2) & 127) | 128);
                j2 >>>= 7;
            } catch (IndexOutOfBoundsException e2) {
                throw new zzdh(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e2);
            }
        }
        byte[] bArr4 = this.zzb;
        int i5 = this.zzd;
        this.zzd = i5 + 1;
        bArr4[i5] = (byte) j2;
    }
}
