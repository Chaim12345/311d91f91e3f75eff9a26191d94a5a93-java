package com.google.android.gms.dynamic;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
/* loaded from: classes.dex */
final class zae implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f5840a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Intent f5841b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zae(Context context, Intent intent) {
        this.f5840a = context;
        this.f5841b = intent;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        try {
            this.f5840a.startActivity(this.f5841b);
        } catch (ActivityNotFoundException e2) {
            Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", e2);
        }
    }
}
