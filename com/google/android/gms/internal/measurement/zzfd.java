package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public final class zzfd extends zzjy implements zzlk {
    private zzfd() {
        super(zzfe.o());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfd(zzey zzeyVar) {
        super(zzfe.o());
    }

    public final int zza() {
        return ((zzfe) this.f6098a).zzb();
    }

    public final zzfc zzb(int i2) {
        return ((zzfe) this.f6098a).zzd(i2);
    }

    public final zzfd zzc() {
        if (this.f6099b) {
            b();
            this.f6099b = false;
        }
        zzfe.q((zzfe) this.f6098a);
        return this;
    }

    public final zzfd zzd(int i2, zzfb zzfbVar) {
        if (this.f6099b) {
            b();
            this.f6099b = false;
        }
        zzfe.p((zzfe) this.f6098a, i2, (zzfc) zzfbVar.zzaE());
        return this;
    }

    public final String zze() {
        return ((zzfe) this.f6098a).zzi();
    }

    public final List zzf() {
        return Collections.unmodifiableList(((zzfe) this.f6098a).zzj());
    }

    public final List zzg() {
        return Collections.unmodifiableList(((zzfe) this.f6098a).zzk());
    }
}
