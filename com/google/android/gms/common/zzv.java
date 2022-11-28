package com.google.android.gms.common;

import java.util.concurrent.Callable;
/* loaded from: classes.dex */
final class zzv extends zzw {
    private final Callable<String> zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzv(Callable callable, zzu zzuVar) {
        super(false, null, null);
        this.zzd = callable;
    }

    @Override // com.google.android.gms.common.zzw
    final String a() {
        try {
            return this.zzd.call();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }
}
