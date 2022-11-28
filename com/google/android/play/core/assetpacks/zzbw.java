package com.google.android.play.core.assetpacks;

import java.io.FilterInputStream;
import java.io.InputStream;
import java.util.Arrays;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* loaded from: classes2.dex */
final class zzbw extends FilterInputStream {
    private final zzds zza;
    private byte[] zzb;
    private long zzc;
    private boolean zzd;
    private boolean zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbw(InputStream inputStream) {
        super(inputStream);
        this.zza = new zzds();
        this.zzb = new byte[4096];
        this.zzd = false;
        this.zze = false;
    }

    private final int zze(byte[] bArr, int i2, int i3) {
        return Math.max(0, super.read(bArr, i2, i3));
    }

    private final boolean zzf(int i2) {
        int zze = zze(this.zzb, 0, i2);
        if (zze != i2) {
            int i3 = i2 - zze;
            if (zze(this.zzb, zze, i3) != i3) {
                this.zza.zzb(this.zzb, 0, zze);
                return false;
            }
        }
        this.zza.zzb(this.zzb, 0, i2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long a() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzet b() {
        byte[] bArr;
        if (this.zzc <= 0) {
            if (this.zzd) {
            }
            return new zzbq(null, -1L, -1, false, false, null);
        }
        do {
            bArr = this.zzb;
        } while (read(bArr, 0, bArr.length) != -1);
        if (!this.zzd || this.zze) {
            return new zzbq(null, -1L, -1, false, false, null);
        }
        if (!zzf(30)) {
            this.zzd = true;
            return this.zza.zzc();
        }
        zzet zzc = this.zza.zzc();
        if (zzc.d()) {
            this.zze = true;
            return zzc;
        } else if (zzc.b() != BodyPartID.bodyIdMax) {
            int zza = this.zza.zza() - 30;
            long j2 = zza;
            int length = this.zzb.length;
            if (j2 > length) {
                do {
                    length += length;
                } while (length < j2);
                this.zzb = Arrays.copyOf(this.zzb, length);
            }
            if (!zzf(zza)) {
                this.zzd = true;
                return this.zza.zzc();
            }
            zzet zzc2 = this.zza.zzc();
            this.zzc = zzc2.b();
            return zzc2;
        } else {
            throw new zzck("Files bigger than 4GiB are not supported.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean c() {
        return this.zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean d() {
        return this.zzd;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i2, int i3) {
        long j2 = this.zzc;
        if (j2 <= 0 || this.zzd) {
            return -1;
        }
        int zze = zze(bArr, i2, (int) Math.min(j2, i3));
        this.zzc -= zze;
        if (zze == 0) {
            this.zzd = true;
            return 0;
        }
        return zze;
    }
}
