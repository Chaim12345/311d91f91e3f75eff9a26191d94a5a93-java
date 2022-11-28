package com.google.firebase.heartbeatinfo;

import java.util.concurrent.ThreadFactory;
/* loaded from: classes2.dex */
public final /* synthetic */ class e implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ e f10043a = new e();

    private /* synthetic */ e() {
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread lambda$static$0;
        lambda$static$0 = DefaultHeartBeatController.lambda$static$0(runnable);
        return lambda$static$0;
    }
}
