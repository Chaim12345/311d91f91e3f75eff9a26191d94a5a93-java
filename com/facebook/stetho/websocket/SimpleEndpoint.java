package com.facebook.stetho.websocket;
/* loaded from: classes.dex */
public interface SimpleEndpoint {
    void onClose(SimpleSession simpleSession, int i2, String str);

    void onError(SimpleSession simpleSession, Throwable th);

    void onMessage(SimpleSession simpleSession, String str);

    void onMessage(SimpleSession simpleSession, byte[] bArr, int i2);

    void onOpen(SimpleSession simpleSession);
}
