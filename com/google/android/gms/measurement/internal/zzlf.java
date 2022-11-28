package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import kotlinx.coroutines.DebugKt;
/* loaded from: classes2.dex */
final class zzlf implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f7028a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f7029b = "_err";

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Bundle f7030c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzlg f7031d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzlf(zzlg zzlgVar, String str, String str2, Bundle bundle) {
        this.f7031d = zzlgVar;
        this.f7028a = str;
        this.f7030c = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f7031d.f7032a.e((zzaw) Preconditions.checkNotNull(this.f7031d.f7032a.zzv().R(this.f7028a, this.f7029b, this.f7030c, DebugKt.DEBUG_PROPERTY_VALUE_AUTO, this.f7031d.f7032a.zzav().currentTimeMillis(), false, true)), this.f7028a);
    }
}
