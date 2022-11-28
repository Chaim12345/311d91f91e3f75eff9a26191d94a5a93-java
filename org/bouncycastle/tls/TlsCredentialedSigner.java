package org.bouncycastle.tls;

import org.bouncycastle.tls.crypto.TlsStreamSigner;
/* loaded from: classes4.dex */
public interface TlsCredentialedSigner extends TlsCredentials {
    byte[] generateRawSignature(byte[] bArr);

    SignatureAndHashAlgorithm getSignatureAndHashAlgorithm();

    TlsStreamSigner getStreamSigner();
}
