package com.google.firebase.messaging;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
/* loaded from: classes2.dex */
public final /* synthetic */ class f implements Continuation {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ f f10091a = new f();

    private /* synthetic */ f() {
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task task) {
        return FcmBroadcastProcessor.c(task);
    }
}
