package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.internal.base.zaq;
/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"HandlerLeak"})
/* loaded from: classes.dex */
public final class zac extends zaq {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GoogleApiAvailability f5819a;
    private final Context zab;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zac(GoogleApiAvailability googleApiAvailability, Context context) {
        super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
        this.f5819a = googleApiAvailability;
        this.zab = context.getApplicationContext();
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i2 = message.what;
        if (i2 != 1) {
            StringBuilder sb = new StringBuilder(50);
            sb.append("Don't know how to handle this message: ");
            sb.append(i2);
            return;
        }
        int isGooglePlayServicesAvailable = this.f5819a.isGooglePlayServicesAvailable(this.zab);
        if (this.f5819a.isUserResolvableError(isGooglePlayServicesAvailable)) {
            this.f5819a.showErrorNotification(this.zab, isGooglePlayServicesAvailable);
        }
    }
}
