package org.bouncycastle.tls;

import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsSecret;
/* loaded from: classes4.dex */
public interface TlsCredentialedAgreement extends TlsCredentials {
    TlsSecret generateAgreement(TlsCertificate tlsCertificate);
}
