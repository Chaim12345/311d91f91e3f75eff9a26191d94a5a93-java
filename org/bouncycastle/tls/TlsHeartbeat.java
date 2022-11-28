package org.bouncycastle.tls;
/* loaded from: classes4.dex */
public interface TlsHeartbeat {
    byte[] generatePayload();

    int getIdleMillis();

    int getTimeoutMillis();
}
