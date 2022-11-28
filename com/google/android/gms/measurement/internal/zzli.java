package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzli {

    /* renamed from: a  reason: collision with root package name */
    com.google.android.gms.internal.measurement.zzgc f7033a;

    /* renamed from: b  reason: collision with root package name */
    List f7034b;

    /* renamed from: c  reason: collision with root package name */
    List f7035c;

    /* renamed from: d  reason: collision with root package name */
    long f7036d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzll f7037e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzli(zzll zzllVar, zzlh zzlhVar) {
        this.f7037e = zzllVar;
    }

    private static final long zzb(com.google.android.gms.internal.measurement.zzfs zzfsVar) {
        return ((zzfsVar.zzd() / 1000) / 60) / 60;
    }

    public final boolean zza(long j2, com.google.android.gms.internal.measurement.zzfs zzfsVar) {
        Preconditions.checkNotNull(zzfsVar);
        if (this.f7035c == null) {
            this.f7035c = new ArrayList();
        }
        if (this.f7034b == null) {
            this.f7034b = new ArrayList();
        }
        if (this.f7035c.isEmpty() || zzb((com.google.android.gms.internal.measurement.zzfs) this.f7035c.get(0)) == zzb(zzfsVar)) {
            long zzbz = this.f7036d + zzfsVar.zzbz();
            this.f7037e.zzg();
            if (zzbz >= Math.max(0, ((Integer) zzen.zzh.zza(null)).intValue())) {
                return false;
            }
            this.f7036d = zzbz;
            this.f7035c.add(zzfsVar);
            this.f7034b.add(Long.valueOf(j2));
            int size = this.f7035c.size();
            this.f7037e.zzg();
            return size < Math.max(1, ((Integer) zzen.zzi.zza(null)).intValue());
        }
        return false;
    }
}
