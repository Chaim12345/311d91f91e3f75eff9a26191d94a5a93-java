package com.google.android.gms.measurement.internal;

import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzld implements zzfc {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzll f7025a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzld(zzll zzllVar) {
        this.f7025a = zzllVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzfc
    public final void zza(String str, int i2, Throwable th, byte[] bArr, Map map) {
        this.f7025a.h(str, i2, th, bArr, map);
    }
}
