package com.google.android.play.core.splitinstall.testing;

import java.util.Map;
import java.util.Objects;
/* loaded from: classes2.dex */
final class zza extends zzs {
    private Integer zza;
    private Map zzb;

    @Override // com.google.android.play.core.splitinstall.testing.zzs
    final zzs a(int i2) {
        this.zza = Integer.valueOf(i2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.splitinstall.testing.zzs
    public final zzs b(Map map) {
        Objects.requireNonNull(map, "Null splitInstallErrorCodeByModule");
        this.zzb = map;
        return this;
    }

    @Override // com.google.android.play.core.splitinstall.testing.zzs
    final zzt c() {
        if (this.zzb != null) {
            return new zzc(this.zza, this.zzb, null);
        }
        throw new IllegalStateException("Missing required properties: splitInstallErrorCodeByModule");
    }

    @Override // com.google.android.play.core.splitinstall.testing.zzs
    final Map d() {
        Map map = this.zzb;
        if (map != null) {
            return map;
        }
        throw new IllegalStateException("Property \"splitInstallErrorCodeByModule\" has not been set");
    }
}
