package com.google.android.datatransport.cct;

import com.google.android.datatransport.cct.CctTransportBackend;
import com.google.android.datatransport.runtime.retries.RetryStrategy;
/* loaded from: classes.dex */
public final /* synthetic */ class b implements RetryStrategy {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ b f5488a = new b();

    private /* synthetic */ b() {
    }

    @Override // com.google.android.datatransport.runtime.retries.RetryStrategy
    public final Object shouldRetry(Object obj, Object obj2) {
        CctTransportBackend.HttpRequest lambda$send$0;
        lambda$send$0 = CctTransportBackend.lambda$send$0((CctTransportBackend.HttpRequest) obj, (CctTransportBackend.HttpResponse) obj2);
        return lambda$send$0;
    }
}
