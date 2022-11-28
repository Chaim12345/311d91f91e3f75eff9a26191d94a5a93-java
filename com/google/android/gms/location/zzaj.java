package com.google.android.gms.location;

import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public final /* synthetic */ class zzaj implements Executor {
    public static final /* synthetic */ zzaj zza = new zzaj();

    private /* synthetic */ zzaj() {
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
