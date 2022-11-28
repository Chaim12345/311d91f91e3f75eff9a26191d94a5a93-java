package com.google.android.gms.common.internal;

import android.content.Intent;
import com.google.android.gms.common.api.internal.LifecycleFragment;
/* loaded from: classes.dex */
final class zaf extends zag {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Intent f5769a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ LifecycleFragment f5770b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaf(Intent intent, LifecycleFragment lifecycleFragment, int i2) {
        this.f5769a = intent;
        this.f5770b = lifecycleFragment;
    }

    @Override // com.google.android.gms.common.internal.zag
    public final void zaa() {
        Intent intent = this.f5769a;
        if (intent != null) {
            this.f5770b.startActivityForResult(intent, 2);
        }
    }
}
