package com.google.android.play.core.splitinstall.testing;

import androidx.annotation.Nullable;
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public abstract class zzt {
    public static final zzt zza = a().e();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzs a() {
        zza zzaVar = new zza();
        zzaVar.b(new HashMap());
        return zzaVar;
    }

    @Nullable
    @SplitInstallErrorCode
    public abstract Integer zza();

    public abstract Map zzb();
}
