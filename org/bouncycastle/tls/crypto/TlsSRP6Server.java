package org.bouncycastle.tls.crypto;

import java.math.BigInteger;
/* loaded from: classes4.dex */
public interface TlsSRP6Server {
    BigInteger calculateSecret(BigInteger bigInteger);

    BigInteger generateServerCredentials();
}
