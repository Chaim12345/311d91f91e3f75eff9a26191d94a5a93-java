package com.google.android.play.core.internal;

import java.io.File;
import java.util.Set;
import org.apache.http.cookie.ClientCookie;
/* loaded from: classes2.dex */
final class zzbp implements zzaz {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(ClassLoader classLoader, Set set) {
        zzbk.zzc(classLoader, set, new zzbn());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean b(ClassLoader classLoader, File file, File file2, boolean z) {
        return zzbf.c(classLoader, file, file2, z, new zzbh(), ClientCookie.PATH_ATTR, new zzbo());
    }

    @Override // com.google.android.play.core.internal.zzaz
    public final void zza(ClassLoader classLoader, Set set) {
        a(classLoader, set);
    }

    @Override // com.google.android.play.core.internal.zzaz
    public final boolean zzb(ClassLoader classLoader, File file, File file2, boolean z) {
        return b(classLoader, file, file2, z);
    }
}
