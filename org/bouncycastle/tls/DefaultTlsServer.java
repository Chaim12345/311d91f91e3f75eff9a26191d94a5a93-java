package org.bouncycastle.tls;

import org.bouncycastle.tls.crypto.TlsCrypto;
/* loaded from: classes4.dex */
public abstract class DefaultTlsServer extends AbstractTlsServer {
    private static final int[] DEFAULT_CIPHER_SUITES = {CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, 107, 103, 57, 51, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, 61, 60, 53, 47};

    public DefaultTlsServer(TlsCrypto tlsCrypto) {
        super(tlsCrypto);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.tls.AbstractTlsPeer
    public int[] a() {
        return TlsUtils.getSupportedCipherSuites(getCrypto(), DEFAULT_CIPHER_SUITES);
    }

    public TlsCredentials getCredentials() {
        int keyExchangeAlgorithm = this.f14673a.getSecurityParametersHandshake().getKeyExchangeAlgorithm();
        if (keyExchangeAlgorithm != 1) {
            if (keyExchangeAlgorithm != 3) {
                if (keyExchangeAlgorithm != 5) {
                    if (keyExchangeAlgorithm == 11) {
                        return null;
                    }
                    if (keyExchangeAlgorithm == 17) {
                        return v();
                    }
                    if (keyExchangeAlgorithm != 19) {
                        if (keyExchangeAlgorithm == 20) {
                            return null;
                        }
                        throw new TlsFatalAlert((short) 80);
                    }
                }
                return x();
            }
            return u();
        }
        return w();
    }

    protected TlsCredentialedSigner u() {
        throw new TlsFatalAlert((short) 80);
    }

    protected TlsCredentialedSigner v() {
        throw new TlsFatalAlert((short) 80);
    }

    protected TlsCredentialedDecryptor w() {
        throw new TlsFatalAlert((short) 80);
    }

    protected TlsCredentialedSigner x() {
        throw new TlsFatalAlert((short) 80);
    }
}
