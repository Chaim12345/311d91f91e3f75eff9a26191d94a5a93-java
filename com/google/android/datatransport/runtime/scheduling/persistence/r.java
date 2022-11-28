package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
/* loaded from: classes.dex */
public final /* synthetic */ class r implements SQLiteEventStore.Function {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ r f5575a = new r();

    private /* synthetic */ r() {
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public final Object apply(Object obj) {
        Object lambda$ensureBeginTransaction$25;
        lambda$ensureBeginTransaction$25 = SQLiteEventStore.lambda$ensureBeginTransaction$25((Throwable) obj);
        return lambda$ensureBeginTransaction$25;
    }
}
