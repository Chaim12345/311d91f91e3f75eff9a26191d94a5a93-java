package com.google.android.gms.cloudmessaging;

import android.os.Looper;
import android.os.Message;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzaa extends com.google.android.gms.internal.cloudmessaging.zzf {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Rpc f5603a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzaa(Rpc rpc, Looper looper) {
        super(looper);
        this.f5603a = rpc;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        Rpc.b(this.f5603a, message);
    }
}
