package com.google.android.play.core.assetpacks;

import java.io.InputStream;
/* loaded from: classes2.dex */
final class zzbj extends InputStream {
    private final InputStream zza;
    private long zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbj(InputStream inputStream, long j2) {
        this.zza = inputStream;
        this.zzb = j2;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        super.close();
        this.zza.close();
        this.zzb = 0L;
    }

    @Override // java.io.InputStream
    public final int read() {
        long j2 = this.zzb;
        if (j2 <= 0) {
            return -1;
        }
        this.zzb = j2 - 1;
        return this.zza.read();
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i2, int i3) {
        long j2 = this.zzb;
        if (j2 <= 0) {
            return -1;
        }
        int read = this.zza.read(bArr, i2, (int) Math.min(i3, j2));
        if (read != -1) {
            this.zzb -= read;
        }
        return read;
    }
}
