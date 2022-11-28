package org.bouncycastle.tls;

import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsECConfig;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.io.TeeInputStream;
/* loaded from: classes4.dex */
public class TlsECDHEKeyExchange extends AbstractTlsKeyExchange {

    /* renamed from: c  reason: collision with root package name */
    protected TlsECConfig f14840c;

    /* renamed from: d  reason: collision with root package name */
    protected TlsCredentialedSigner f14841d;

    /* renamed from: e  reason: collision with root package name */
    protected TlsCertificate f14842e;

    /* renamed from: f  reason: collision with root package name */
    protected TlsAgreement f14843f;

    public TlsECDHEKeyExchange(int i2) {
        this(i2, null);
    }

    public TlsECDHEKeyExchange(int i2, TlsECConfig tlsECConfig) {
        super(checkKeyExchange(i2));
        this.f14841d = null;
        this.f14842e = null;
        this.f14840c = tlsECConfig;
    }

    private static int checkKeyExchange(int i2) {
        if (i2 == 17 || i2 == 19) {
            return i2;
        }
        throw new IllegalArgumentException("unsupported key exchange algorithm");
    }

    protected void a(OutputStream outputStream) {
        TlsUtils.writeOpaque8(this.f14843f.generateEphemeral(), outputStream);
    }

    protected void b(byte[] bArr) {
        TlsECCUtils.checkPointEncoding(this.f14840c.getNamedGroup(), bArr);
        this.f14843f.receivePeerValue(bArr);
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void generateClientKeyExchange(OutputStream outputStream) {
        a(outputStream);
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public TlsSecret generatePreMasterSecret() {
        return this.f14843f.calculateSecret();
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public byte[] generateServerKeyExchange() {
        DigestInputBuffer digestInputBuffer = new DigestInputBuffer();
        TlsECCUtils.writeECConfig(this.f14840c, digestInputBuffer);
        this.f14843f = this.f14672b.getCrypto().createECDomain(this.f14840c).createECDH();
        a(digestInputBuffer);
        TlsUtils.F(this.f14672b, this.f14841d, null, digestInputBuffer);
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
        b(TlsUtils.readOpaque8(inputStream, 1));
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processServerCertificate(Certificate certificate) {
        this.f14842e = certificate.getCertificateAt(0);
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void processServerCredentials(TlsCredentials tlsCredentials) {
        this.f14841d = TlsUtils.r0(tlsCredentials);
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processServerKeyExchange(InputStream inputStream) {
        DigestInputBuffer digestInputBuffer = new DigestInputBuffer();
        TeeInputStream teeInputStream = new TeeInputStream(inputStream, digestInputBuffer);
        this.f14840c = TlsECCUtils.receiveECDHConfig(this.f14672b, teeInputStream);
        byte[] readOpaque8 = TlsUtils.readOpaque8(teeInputStream, 1);
        TlsUtils.L0(this.f14672b, inputStream, this.f14842e, null, digestInputBuffer);
        this.f14843f = this.f14672b.getCrypto().createECDomain(this.f14840c).createECDH();
        b(readOpaque8);
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
