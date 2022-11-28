package com.google.android.gms.internal.mlkit_common;

import java.util.logging.Logger;
import javax.annotation.CheckForNull;
/* loaded from: classes.dex */
final class zzaa {
    private static final Logger zza = Logger.getLogger(zzaa.class.getName());
    private static final zzz zzb = new zzz(null);

    private zzaa() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(@CheckForNull String str) {
        return str == null ? "" : str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean b(@CheckForNull String str) {
        return str == null || str.isEmpty();
    }
}
