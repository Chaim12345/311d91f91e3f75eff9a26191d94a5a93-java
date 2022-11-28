package com.google.firebase.messaging;

import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public final /* synthetic */ class c implements Executor {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ c f10080a = new c();

    private /* synthetic */ c() {
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
