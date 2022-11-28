package com.google.android.gms.measurement.internal;

import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzlc implements zzfc {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f7023a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzll f7024b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzlc(zzll zzllVar, String str) {
        this.f7024b = zzllVar;
        this.f7023a = str;
    }

    @Override // com.google.android.gms.measurement.internal.zzfc
    public final void zza(String str, int i2, Throwable th, byte[] bArr, Map map) {
        this.f7024b.j(i2, th, bArr, this.f7023a);
    }
}
