package com.google.android.gms.internal.measurement;

import android.content.Context;
import java.util.Objects;
import javax.annotation.Nullable;
/* loaded from: classes.dex */
final class zzhb extends zzhw {
    private final Context zza;
    private final zzif zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhb(Context context, @Nullable zzif zzifVar) {
        Objects.requireNonNull(context, "Null context");
        this.zza = context;
        this.zzb = zzifVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhw
    public final Context a() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzhw
    @Nullable
    public final zzif b() {
        return this.zzb;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzhw) {
            zzhw zzhwVar = (zzhw) obj;
            if (this.zza.equals(zzhwVar.a())) {
                zzif zzifVar = this.zzb;
                zzif b2 = zzhwVar.b();
                if (zzifVar != null ? zzifVar.equals(b2) : b2 == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (this.zza.hashCode() ^ 1000003) * 1000003;
        zzif zzifVar = this.zzb;
        return hashCode ^ (zzifVar == null ? 0 : zzifVar.hashCode());
    }

    public final String toString() {
        String obj = this.zza.toString();
        String valueOf = String.valueOf(this.zzb);
        return "FlagsContext{context=" + obj + ", hermeticFileOverrides=" + valueOf + "}";
    }
}
