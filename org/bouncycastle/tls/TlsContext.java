package org.bouncycastle.tls;

import org.bouncycastle.tls.crypto.TlsCrypto;
import org.bouncycastle.tls.crypto.TlsNonceGenerator;
/* loaded from: classes4.dex */
public interface TlsContext {
    byte[] exportChannelBinding(int i2);

    byte[] exportEarlyKeyingMaterial(String str, byte[] bArr, int i2);

    byte[] exportKeyingMaterial(String str, byte[] bArr, int i2);

    ProtocolVersion[] getClientSupportedVersions();

    ProtocolVersion getClientVersion();

    TlsCrypto getCrypto();

    TlsNonceGenerator getNonceGenerator();

    ProtocolVersion getRSAPreMasterSecretVersion();

    TlsSession getResumableSession();

    SecurityParameters getSecurityParameters();

    SecurityParameters getSecurityParametersConnection();

    SecurityParameters getSecurityParametersHandshake();

    ProtocolVersion getServerVersion();

    TlsSession getSession();

    Object getUserObject();

    boolean isServer();

    void setUserObject(Object obj);
}
