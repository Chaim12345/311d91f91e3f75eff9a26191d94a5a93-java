package com.google.android.libraries.places.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.tasks.CancellationToken;
import java.util.Map;
/* loaded from: classes2.dex */
public abstract class zzca {
    private final zzen zza;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzca(zzen zzenVar) {
        this.zza = zzenVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public final CancellationToken zza() {
        return this.zza.getCancellationToken();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzen zzb() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract String zzc();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Map zzd();
}
