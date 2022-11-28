package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Map;
@WorkerThread
/* loaded from: classes2.dex */
final class zzfe implements Runnable {
    private final zzfc zza;
    private final int zzb;
    private final Throwable zzc;
    private final byte[] zzd;
    private final String zze;
    private final Map zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfe(String str, zzfc zzfcVar, int i2, Throwable th, byte[] bArr, Map map, zzfd zzfdVar) {
        Preconditions.checkNotNull(zzfcVar);
        this.zza = zzfcVar;
        this.zzb = i2;
        this.zzc = th;
        this.zzd = bArr;
        this.zze = str;
        this.zzf = map;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza(this.zze, this.zzb, this.zzc, this.zzd, this.zzf);
    }
}
