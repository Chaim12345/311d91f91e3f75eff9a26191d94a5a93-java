package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.Intent;
/* loaded from: classes.dex */
final class zad extends zag {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Intent f5763a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Activity f5764b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ int f5765c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zad(Intent intent, Activity activity, int i2) {
        this.f5763a = intent;
        this.f5764b = activity;
        this.f5765c = i2;
    }

    @Override // com.google.android.gms.common.internal.zag
    public final void zaa() {
        Intent intent = this.f5763a;
        if (intent != null) {
            this.f5764b.startActivityForResult(intent, this.f5765c);
        }
    }
}
