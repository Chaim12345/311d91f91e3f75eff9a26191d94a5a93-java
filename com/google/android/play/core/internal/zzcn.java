package com.google.android.play.core.internal;

import java.io.InputStream;
/* loaded from: classes2.dex */
public final class zzcn extends zzcm {
    private final zzcm zza;
    private final long zzb;
    private final long zzc;

    public zzcn(zzcm zzcmVar, long j2, long j3) {
        this.zza = zzcmVar;
        long zzd = zzd(j2);
        this.zzb = zzd;
        this.zzc = zzd(zzd + j3);
    }

    private final long zzd(long j2) {
        if (j2 < 0) {
            return 0L;
        }
        return j2 > this.zza.zza() ? this.zza.zza() : j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.play.core.internal.zzcm
    public final InputStream a(long j2, long j3) {
        long zzd = zzd(this.zzb);
        return this.zza.a(zzd, zzd(j3 + zzd) - zzd);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // com.google.android.play.core.internal.zzcm
    public final long zza() {
        return this.zzc - this.zzb;
    }
}
