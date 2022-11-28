package com.google.android.datatransport.runtime;

import com.google.android.datatransport.TransportScheduleCallback;
/* loaded from: classes.dex */
public final /* synthetic */ class a implements TransportScheduleCallback {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ a f5502a = new a();

    private /* synthetic */ a() {
    }

    @Override // com.google.android.datatransport.TransportScheduleCallback
    public final void onSchedule(Exception exc) {
        TransportImpl.lambda$send$0(exc);
    }
}
