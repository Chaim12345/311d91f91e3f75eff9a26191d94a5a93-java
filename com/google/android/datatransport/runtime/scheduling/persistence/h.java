package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
/* loaded from: classes.dex */
public final /* synthetic */ class h implements SQLiteEventStore.Function {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ h f5565a = new h();

    private /* synthetic */ h() {
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public final Object apply(Object obj) {
        byte[] lambda$readPayload$15;
        lambda$readPayload$15 = SQLiteEventStore.lambda$readPayload$15((Cursor) obj);
        return lambda$readPayload$15;
    }
}
