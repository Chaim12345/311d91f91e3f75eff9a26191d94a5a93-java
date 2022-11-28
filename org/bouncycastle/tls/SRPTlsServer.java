package org.bouncycastle.tls;

import java.util.Hashtable;
import org.bouncycastle.tls.crypto.TlsCrypto;
/* loaded from: classes4.dex */
public class SRPTlsServer extends AbstractTlsServer {
    private static final int[] DEFAULT_CIPHER_SUITES = {CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_256_CBC_SHA, CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_128_CBC_SHA, CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_SRP_SHA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_SRP_SHA_WITH_AES_128_CBC_SHA};

    /* renamed from: p  reason: collision with root package name */
    protected TlsSRPIdentityManager f14795p;

    /* renamed from: q  reason: collision with root package name */
    protected byte[] f14796q;

    /* renamed from: r  reason: collision with root package name */
    protected TlsSRPLoginParameters f14797r;

    public SRPTlsServer(TlsCrypto tlsCrypto, TlsSRPIdentityManager tlsSRPIdentityManager) {
        super(tlsCrypto);
        this.f14796q = null;
        this.f14797r = null;
        this.f14795p = tlsSRPIdentityManager;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.tls.AbstractTlsPeer
    public int[] a() {
        return TlsUtils.getSupportedCipherSuites(getCrypto(), DEFAULT_CIPHER_SUITES);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.tls.AbstractTlsPeer
    public ProtocolVersion[] b() {
        return ProtocolVersion.TLSv12.downTo(ProtocolVersion.TLSv10);
    }

    @Override // org.bouncycastle.tls.TlsServer
    public TlsCredentials getCredentials() {
        switch (this.f14673a.getSecurityParametersHandshake().getKeyExchangeAlgorithm()) {
            case 21:
                return null;
            case 22:
                return u();
            case 23:
                return v();
            default:
                throw new TlsFatalAlert((short) 80);
        }
    }

    public ProtocolVersion getMaximumVersion() {
        return ProtocolVersion.TLSv12;
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public TlsSRPLoginParameters getSRPLoginParameters() {
        return this.f14797r;
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public int getSelectedCipherSuite() {
        int selectedCipherSuite = super.getSelectedCipherSuite();
        if (TlsSRPUtils.isSRPCipherSuite(selectedCipherSuite)) {
            byte[] bArr = this.f14796q;
            if (bArr != null) {
                this.f14797r = this.f14795p.getLoginParameters(bArr);
            }
            if (this.f14797r == null) {
                throw new TlsFatalAlert(AlertDescription.unknown_psk_identity);
            }
        }
        return selectedCipherSuite;
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public void processClientExtensions(Hashtable hashtable) {
        super.processClientExtensions(hashtable);
        this.f14796q = TlsSRPUtils.getSRPExtension(hashtable);
    }

    protected TlsCredentialedSigner u() {
        throw new TlsFatalAlert((short) 80);
    }

    protected TlsCredentialedSigner v() {
        throw new TlsFatalAlert((short) 80);
    }
}
