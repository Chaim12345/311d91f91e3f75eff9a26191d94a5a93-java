package com.google.api.client.googleapis.services.json;

import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.CommonGoogleClientRequestInitializer;
/* loaded from: classes2.dex */
public class CommonGoogleJsonClientRequestInitializer extends CommonGoogleClientRequestInitializer {

    /* loaded from: classes2.dex */
    public static class Builder extends CommonGoogleClientRequestInitializer.Builder {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.client.googleapis.services.CommonGoogleClientRequestInitializer.Builder
        /* renamed from: b */
        public Builder a() {
            return this;
        }
    }

    @Deprecated
    public CommonGoogleJsonClientRequestInitializer() {
    }

    @Deprecated
    public CommonGoogleJsonClientRequestInitializer(String str) {
        super(str);
    }

    @Deprecated
    public CommonGoogleJsonClientRequestInitializer(String str, String str2) {
        super(str, str2);
    }

    protected void a(AbstractGoogleJsonClientRequest abstractGoogleJsonClientRequest) {
    }

    @Override // com.google.api.client.googleapis.services.CommonGoogleClientRequestInitializer, com.google.api.client.googleapis.services.GoogleClientRequestInitializer
    public final void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
        super.initialize(abstractGoogleClientRequest);
        a((AbstractGoogleJsonClientRequest) abstractGoogleClientRequest);
    }
}
