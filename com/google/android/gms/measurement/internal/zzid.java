package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import kotlinx.coroutines.DebugKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzid implements zzls {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzip f6860a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzid(zzip zzipVar) {
        this.f6860a = zzipVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzls
    public final void zza(String str, String str2, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            this.f6860a.zzE(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_err", bundle);
        } else {
            this.f6860a.zzG(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_err", bundle, str);
        }
    }
}
