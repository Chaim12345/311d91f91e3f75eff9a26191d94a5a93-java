package org.bouncycastle.tls;

import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsDHConfig;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.io.TeeInputStream;
/* loaded from: classes4.dex */
public class TlsDHEKeyExchange extends AbstractTlsKeyExchange {

    /* renamed from: c  reason: collision with root package name */
    protected TlsDHGroupVerifier f14830c;

    /* renamed from: d  reason: collision with root package name */
    protected TlsDHConfig f14831d;

    /* renamed from: e  reason: collision with root package name */
    protected TlsCredentialedSigner f14832e;

    /* renamed from: f  reason: collision with root package name */
    protected TlsCertificate f14833f;

    /* renamed from: g  reason: collision with root package name */
    protected TlsAgreement f14834g;

    public TlsDHEKeyExchange(int i2, TlsDHGroupVerifier tlsDHGroupVerifier) {
        this(i2, tlsDHGroupVerifier, null);
    }

    private TlsDHEKeyExchange(int i2, TlsDHGroupVerifier tlsDHGroupVerifier, TlsDHConfig tlsDHConfig) {
        super(checkKeyExchange(i2));
        this.f14832e = null;
        this.f14833f = null;
        this.f14830c = tlsDHGroupVerifier;
        this.f14831d = tlsDHConfig;
    }

    public TlsDHEKeyExchange(int i2, TlsDHConfig tlsDHConfig) {
        this(i2, null, tlsDHConfig);
    }

    private static int checkKeyExchange(int i2) {
        if (i2 == 3 || i2 == 5) {
            return i2;
        }
        throw new IllegalArgumentException("unsupported key exchange algorithm");
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void generateClientKeyExchange(OutputStream outputStream) {
        TlsUtils.writeOpaque16(this.f14834g.generateEphemeral(), outputStream);
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public TlsSecret generatePreMasterSecret() {
        return this.f14834g.calculateSecret();
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public byte[] generateServerKeyExchange() {
        DigestInputBuffer digestInputBuffer = new DigestInputBuffer();
        TlsDHUtils.writeDHConfig(this.f14831d, digestInputBuffer);
        TlsAgreement createDH = this.f14672b.getCrypto().createDHDomain(this.f14831d).createDH();
        this.f14834g = createDH;
        TlsUtils.writeOpaque16(createDH.generateEphemeral(), digestInputBuffer);
        TlsUtils.F(this.f14672b, this.f14832e, null, digestInputBuffer);
        return digestInputBuffer.toByteArray();
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public short[] getClientCertificateTypes() {
        return new short[]{2, 64, 1};
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void processClientCredentials(TlsCredentials tlsCredentials) {
        TlsUtils.r0(tlsCredentials);
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processClientKeyExchange(InputStream inputStream) {
        this.f14834g.receivePeerValue(TlsUtils.readOpaque16(inputStream, 1));
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processServerCertificate(Certificate certificate) {
        this.f14833f = certificate.getCertificateAt(0);
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void processServerCredentials(TlsCredentials tlsCredentials) {
        this.f14832e = TlsUtils.r0(tlsCredentials);
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processServerKeyExchange(InputStream inputStream) {
        DigestInputBuffer digestInputBuffer = new DigestInputBuffer();
        TeeInputStream teeInputStream = new TeeInputStream(inputStream, digestInputBuffer);
        this.f14831d = TlsDHUtils.receiveDHConfig(this.f14672b, this.f14830c, teeInputStream);
        byte[] readOpaque16 = TlsUtils.readOpaque16(teeInputStream, 1);
        TlsUtils.L0(this.f14672b, inputStream, this.f14833f, null, digestInputBuffer);
        TlsAgreement createDH = this.f14672b.getCrypto().createDHDomain(this.f14831d).createDH();
        this.f14834g = createDH;
        createDH.receivePeerValue(readOpaque16);
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public boolean requiresServerKeyExchange() {
        return true;
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void skipServerCredentials() {
        throw new TlsFatalAlert((short) 80);
    }
}
