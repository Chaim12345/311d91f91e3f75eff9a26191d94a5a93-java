package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
/* loaded from: classes.dex */
public final /* synthetic */ class k implements SQLiteEventStore.Function {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ k f5568a = new k();

    private /* synthetic */ k() {
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public final Object apply(Object obj) {
        Long lambda$getTransportContextId$2;
        lambda$getTransportContextId$2 = SQLiteEventStore.lambda$getTransportContextId$2((Cursor) obj);
        return lambda$getTransportContextId$2;
    }
}
