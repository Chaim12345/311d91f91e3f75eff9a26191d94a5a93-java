package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
/* loaded from: classes.dex */
public final class zzc {

    /* renamed from: a  reason: collision with root package name */
    final zzf f5938a;

    /* renamed from: b  reason: collision with root package name */
    zzg f5939b;

    /* renamed from: c  reason: collision with root package name */
    final zzab f5940c;
    private final zzz zzd;

    public zzc() {
        zzf zzfVar = new zzf();
        this.f5938a = zzfVar;
        this.f5939b = zzfVar.f6054b.zza();
        this.f5940c = new zzab();
        this.zzd = new zzz();
        zzfVar.f6056d.zza("internal.registerCallback", new Callable() { // from class: com.google.android.gms.internal.measurement.zza
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzc.this.a();
            }
        });
        zzfVar.f6056d.zza("internal.eventLogger", new Callable() { // from class: com.google.android.gms.internal.measurement.zzb
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return new zzk(zzc.this.f5940c);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzai a() {
        return new zzv(this.zzd);
    }

    public final zzab zza() {
        return this.f5940c;
    }

    public final void zzc(zzgs zzgsVar) {
        zzai zzaiVar;
        try {
            this.f5939b = this.f5938a.f6054b.zza();
            if (this.f5938a.zza(this.f5939b, (zzgx[]) zzgsVar.zzc().toArray(new zzgx[0])) instanceof zzag) {
                throw new IllegalStateException("Program loading failed");
            }
            for (zzgq zzgqVar : zzgsVar.zza().zzd()) {
                List zzc = zzgqVar.zzc();
                String zzb = zzgqVar.zzb();
                Iterator it = zzc.iterator();
                while (it.hasNext()) {
                    zzap zza = this.f5938a.zza(this.f5939b, (zzgx) it.next());
                    if (!(zza instanceof zzam)) {
                        throw new IllegalArgumentException("Invalid rule definition");
                    }
                    zzg zzgVar = this.f5939b;
                    if (zzgVar.zzh(zzb)) {
                        zzap zzd = zzgVar.zzd(zzb);
                        if (!(zzd instanceof zzai)) {
                            throw new IllegalStateException("Invalid function name: ".concat(String.valueOf(zzb)));
                        }
                        zzaiVar = (zzai) zzd;
                    } else {
                        zzaiVar = null;
                    }
                    if (zzaiVar == null) {
                        throw new IllegalStateException("Rule function is undefined: ".concat(String.valueOf(zzb)));
                    }
                    zzaiVar.zza(this.f5939b, Collections.singletonList(zza));
                }
            }
        } catch (Throwable th) {
            throw new zzd(th);
        }
    }

    public final void zzd(String str, Callable callable) {
        this.f5938a.f6056d.zza(str, callable);
    }

    public final boolean zze(zzaa zzaaVar) {
        try {
            this.f5940c.zzd(zzaaVar);
            this.f5938a.f6055c.zzg("runtime.counter", new zzah(Double.valueOf(0.0d)));
            this.zzd.zzb(this.f5939b.zza(), this.f5940c);
            if (zzg()) {
                return true;
            }
            return zzf();
        } catch (Throwable th) {
            throw new zzd(th);
        }
    }

    public final boolean zzf() {
        return !this.f5940c.zzc().isEmpty();
    }

    public final boolean zzg() {
        zzab zzabVar = this.f5940c;
        return !zzabVar.zzb().equals(zzabVar.zza());
    }
}
