package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.runtime.AutoValue_SendRequest;
import com.google.auto.value.AutoValue;
/* JADX INFO: Access modifiers changed from: package-private */
@AutoValue
/* loaded from: classes.dex */
public abstract class SendRequest {

    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class Builder {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder a(Encoding encoding);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder b(Event event);

        public abstract SendRequest build();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder c(Transformer transformer);

        public <T> Builder setEvent(Event<T> event, Encoding encoding, Transformer<T, byte[]> transformer) {
            b(event);
            a(encoding);
            c(transformer);
            return this;
        }

        public abstract Builder setTransportContext(TransportContext transportContext);

        public abstract Builder setTransportName(String str);
    }

    public static Builder builder() {
        return new AutoValue_SendRequest.Builder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Event a();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Transformer b();

    public abstract Encoding getEncoding();

    public byte[] getPayload() {
        return (byte[]) b().apply(a().getPayload());
    }

    public abstract TransportContext getTransportContext();

    public abstract String getTransportName();
}
