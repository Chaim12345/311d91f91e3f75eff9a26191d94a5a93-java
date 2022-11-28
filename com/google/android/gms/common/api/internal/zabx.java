package com.google.android.gms.common.api.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public final class zabx extends BroadcastReceiver {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    Context f5686a;
    private final zabw zab;

    public zabx(zabw zabwVar) {
        this.zab = zabwVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        if ("com.google.android.gms".equals(data != null ? data.getSchemeSpecificPart() : null)) {
            this.zab.zaa();
            zab();
        }
    }

    public final void zaa(Context context) {
        this.f5686a = context;
    }

    public final synchronized void zab() {
        Context context = this.f5686a;
        if (context != null) {
            context.unregisterReceiver(this);
        }
        this.f5686a = null;
    }
}
