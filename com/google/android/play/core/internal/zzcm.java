package com.google.android.play.core.internal;

import java.io.Closeable;
import java.io.InputStream;
/* loaded from: classes2.dex */
public abstract class zzcm implements Closeable {
    /* JADX INFO: Access modifiers changed from: protected */
    public abstract InputStream a(long j2, long j3);

    public abstract long zza();

    public final synchronized InputStream zzc() {
        return a(0L, zza());
    }
}
