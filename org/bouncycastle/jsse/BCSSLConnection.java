package org.bouncycastle.jsse;
/* loaded from: classes3.dex */
public interface BCSSLConnection {
    String getApplicationProtocol();

    byte[] getChannelBinding(String str);

    BCExtendedSSLSession getSession();
}
