package com.google.android.play.core.internal;

import java.io.File;
import java.util.Set;
import org.apache.http.cookie.ClientCookie;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzbs implements zzaz {
    @Override // com.google.android.play.core.internal.zzaz
    public final void zza(ClassLoader classLoader, Set set) {
        zzbp.a(classLoader, set);
    }

    @Override // com.google.android.play.core.internal.zzaz
    public final boolean zzb(ClassLoader classLoader, File file, File file2, boolean z) {
        return zzbf.c(classLoader, file, file2, z, new zzbh(), ClientCookie.PATH_ATTR, new zzbr());
    }
}
