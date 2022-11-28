package org.bouncycastle.tls;

import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsEncryptor;
import org.bouncycastle.tls.crypto.TlsSecret;
/* loaded from: classes4.dex */
public class TlsRSAKeyExchange extends AbstractTlsKeyExchange {

    /* renamed from: c  reason: collision with root package name */
    protected TlsCredentialedDecryptor f14882c;

    /* renamed from: d  reason: collision with root package name */
    protected TlsEncryptor f14883d;

    /* renamed from: e  reason: collision with root package name */
    protected TlsSecret f14884e;

    public TlsRSAKeyExchange(int i2) {
        super(checkKeyExchange(i2));
        this.f14882c = null;
    }

    private static int checkKeyExchange(int i2) {
        if (i2 == 1) {
            return i2;
        }
        throw new IllegalArgumentException("unsupported key exchange algorithm");
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void generateClientKeyExchange(OutputStream outputStream) {
        this.f14884e = TlsUtils.generateEncryptedPreMasterSecret(this.f14672b, this.f14883d, outputStream);
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public TlsSecret generatePreMasterSecret() {
        TlsSecret tlsSecret = this.f14884e;
        this.f14884e = null;
        return tlsSecret;
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public short[] getClientCertificateTypes() {
        return new short[]{1, 2, 64};
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void processClientCredentials(TlsCredentials tlsCredentials) {
        TlsUtils.r0(tlsCredentials);
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processClientKeyExchange(InputStream inputStream) {
        this.f14884e = this.f14882c.decrypt(new TlsCryptoParameters(this.f14672b), TlsUtils.m0(this.f14672b, inputStream));
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processServerCertificate(Certificate certificate) {
        this.f14883d = certificate.getCertificateAt(0).createEncryptor(3);
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void processServerCredentials(TlsCredentials tlsCredentials) {
        this.f14882c = TlsUtils.q0(tlsCredentials);
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void skipServerCredentials() {
        throw new TlsFatalAlert((short) 80);
    }
}
