package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportScheduleCallback;
/* loaded from: classes.dex */
final class TransportImpl<T> implements Transport<T> {
    private final String name;
    private final Encoding payloadEncoding;
    private final Transformer<T, byte[]> transformer;
    private final TransportContext transportContext;
    private final TransportInternal transportInternal;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TransportImpl(TransportContext transportContext, String str, Encoding encoding, Transformer transformer, TransportInternal transportInternal) {
        this.transportContext = transportContext;
        this.name = str;
        this.payloadEncoding = encoding;
        this.transformer = transformer;
        this.transportInternal = transportInternal;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$send$0(Exception exc) {
    }

    @Override // com.google.android.datatransport.Transport
    public void schedule(Event<T> event, TransportScheduleCallback transportScheduleCallback) {
        this.transportInternal.send(SendRequest.builder().setTransportContext(this.transportContext).b(event).setTransportName(this.name).c(this.transformer).a(this.payloadEncoding).build(), transportScheduleCallback);
    }

    @Override // com.google.android.datatransport.Transport
    public void send(Event<T> event) {
        schedule(event, a.f5502a);
    }
}
