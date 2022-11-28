package com.google.android.gms.common.api.internal;

import android.app.Dialog;
/* loaded from: classes.dex */
final class zan extends zabw {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Dialog f5705a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zao f5706b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zan(zao zaoVar, Dialog dialog) {
        this.f5706b = zaoVar;
        this.f5705a = dialog;
    }

    @Override // com.google.android.gms.common.api.internal.zabw
    public final void zaa() {
        this.f5706b.f5707a.zad();
        if (this.f5705a.isShowing()) {
            this.f5705a.dismiss();
        }
    }
}
