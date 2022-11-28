package com.google.android.gms.common.internal;

import android.content.Intent;
import androidx.fragment.app.Fragment;
/* loaded from: classes.dex */
final class zae extends zag {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Intent f5766a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Fragment f5767b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ int f5768c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zae(Intent intent, Fragment fragment, int i2) {
        this.f5766a = intent;
        this.f5767b = fragment;
        this.f5768c = i2;
    }

    @Override // com.google.android.gms.common.internal.zag
    public final void zaa() {
        Intent intent = this.f5766a;
        if (intent != null) {
            this.f5767b.startActivityForResult(intent, this.f5768c);
        }
    }
}
