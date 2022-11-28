package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public final class zzfr extends zzjy implements zzlk {
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private zzfr() {
        super(r0);
        zzfs zzfsVar;
        zzfsVar = zzfs.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ zzfr(zzfj zzfjVar) {
        super(r1);
        zzfs zzfsVar;
        zzfsVar = zzfs.zza;
    }

    public final int zza() {
        return ((zzfs) this.f6098a).zzb();
    }

    public final long zzb() {
        return ((zzfs) this.f6098a).zzc();
    }

    public final long zzc() {
        return ((zzfs) this.f6098a).zzd();
    }

    public final zzfr zzd(Iterable iterable) {
        if (this.f6099b) {
            b();
            this.f6099b = false;
        }
        zzfs.r((zzfs) this.f6098a, iterable);
        return this;
    }

    public final zzfr zze(zzfv zzfvVar) {
        if (this.f6099b) {
            b();
            this.f6099b = false;
        }
        zzfs.q((zzfs) this.f6098a, (zzfw) zzfvVar.zzaE());
        return this;
    }

    public final zzfr zzf(zzfw zzfwVar) {
        if (this.f6099b) {
            b();
            this.f6099b = false;
        }
        zzfs.q((zzfs) this.f6098a, zzfwVar);
        return this;
    }

    public final zzfr zzg() {
        if (this.f6099b) {
            b();
            this.f6099b = false;
        }
        ((zzfs) this.f6098a).zzf = zzkc.i();
        return this;
    }

    public final zzfr zzh(int i2) {
        if (this.f6099b) {
            b();
            this.f6099b = false;
        }
        zzfs.t((zzfs) this.f6098a, i2);
        return this;
    }

    public final zzfr zzi(String str) {
        if (this.f6099b) {
            b();
            this.f6099b = false;
        }
        zzfs.u((zzfs) this.f6098a, str);
        return this;
    }

    public final zzfr zzj(int i2, zzfv zzfvVar) {
        if (this.f6099b) {
            b();
            this.f6099b = false;
        }
        zzfs.p((zzfs) this.f6098a, i2, (zzfw) zzfvVar.zzaE());
        return this;
    }

    public final zzfr zzk(int i2, zzfw zzfwVar) {
        if (this.f6099b) {
            b();
            this.f6099b = false;
        }
        zzfs.p((zzfs) this.f6098a, i2, zzfwVar);
        return this;
    }

    public final zzfr zzl(long j2) {
        if (this.f6099b) {
            b();
            this.f6099b = false;
        }
        zzfs.w((zzfs) this.f6098a, j2);
        return this;
    }

    public final zzfr zzm(long j2) {
        if (this.f6099b) {
            b();
            this.f6099b = false;
        }
        zzfs.v((zzfs) this.f6098a, j2);
        return this;
    }

    public final zzfw zzn(int i2) {
        return ((zzfs) this.f6098a).zzg(i2);
    }

    public final String zzo() {
        return ((zzfs) this.f6098a).zzh();
    }

    public final List zzp() {
        return Collections.unmodifiableList(((zzfs) this.f6098a).zzi());
    }

    public final boolean zzq() {
        return ((zzfs) this.f6098a).zzu();
    }
}
