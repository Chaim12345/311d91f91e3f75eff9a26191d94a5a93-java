package org.bouncycastle.tls;
/* loaded from: classes4.dex */
public interface TlsSession {
    SessionParameters exportSessionParameters();

    byte[] getSessionID();

    void invalidate();

    boolean isResumable();
}
