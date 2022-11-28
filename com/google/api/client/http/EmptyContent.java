package com.google.api.client.http;

import java.io.OutputStream;
/* loaded from: classes2.dex */
public class EmptyContent implements HttpContent {
    @Override // com.google.api.client.http.HttpContent
    public long getLength() {
        return 0L;
    }

    @Override // com.google.api.client.http.HttpContent
    public String getType() {
        return null;
    }

    @Override // com.google.api.client.http.HttpContent
    public boolean retrySupported() {
        return true;
    }

    @Override // com.google.api.client.http.HttpContent, com.google.api.client.util.StreamingContent
    public void writeTo(OutputStream outputStream) {
        outputStream.flush();
    }
}
