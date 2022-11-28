package org.bouncycastle.tls;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsDHConfig;
import org.bouncycastle.tls.crypto.TlsSecret;
/* loaded from: classes4.dex */
public class TlsDHanonKeyExchange extends AbstractTlsKeyExchange {

    /* renamed from: c  reason: collision with root package name */
    protected TlsDHGroupVerifier f14837c;

    /* renamed from: d  reason: collision with root package name */
    protected TlsDHConfig f14838d;

    /* renamed from: e  reason: collision with root package name */
    protected TlsAgreement f14839e;

    public TlsDHanonKeyExchange(int i2, TlsDHGroupVerifier tlsDHGroupVerifier) {
        this(i2, tlsDHGroupVerifier, null);
    }

    private TlsDHanonKeyExchange(int i2, TlsDHGroupVerifier tlsDHGroupVerifier, TlsDHConfig tlsDHConfig) {
        super(checkKeyExchange(i2));
        this.f14837c = tlsDHGroupVerifier;
        this.f14838d = tlsDHConfig;
    }

    public TlsDHanonKeyExchange(int i2, TlsDHConfig tlsDHConfig) {
        this(i2, null, tlsDHConfig);
    }

    private static int checkKeyExchange(int i2) {
        if (i2 == 11) {
            return i2;
        }
        throw new IllegalArgumentException("unsupported key exchange algorithm");
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void generateClientKeyExchange(OutputStream outputStream) {
        TlsUtils.writeOpaque16(this.f14839e.generateEphemeral(), outputStream);
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public TlsSecret generatePreMasterSecret() {
        return this.f14839e.calculateSecret();
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public byte[] generateServerKeyExchange() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        TlsDHUtils.writeDHConfig(this.f14838d, byteArrayOutputStream);
        TlsAgreement createDH = this.f14672b.getCrypto().createDHDomain(this.f14838d).createDH();
        this.f14839e = createDH;
        TlsUtils.writeOpaque16(createDH.generateEphemeral(), byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public short[] getClientCertificateTypes() {
        return null;
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processClientCertificate(Certificate certificate) {
        throw new TlsFatalAlert((short) 10);
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void processClientCredentials(TlsCredentials tlsCredentials) {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processClientKeyExchange(InputStream inputStream) {
        this.f14839e.receivePeerValue(TlsUtils.readOpaque16(inputStream, 1));
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processServerCertificate(Certificate certificate) {
        throw new TlsFatalAlert((short) 10);
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void processServerCredentials(TlsCredentials tlsCredentials) {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processServerKeyExchange(InputStream inputStream) {
        this.f14838d = TlsDHUtils.receiveDHConfig(this.f14672b, this.f14837c, inputStream);
        byte[] readOpaque16 = TlsUtils.readOpaque16(inputStream, 1);
        TlsAgreement createDH = this.f14672b.getCrypto().createDHDomain(this.f14838d).createDH();
        this.f14839e = createDH;
        createDH.receivePeerValue(readOpaque16);
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public boolean requiresServerKeyExchange() {
        return true;
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void skipServerCredentials() {
    }
}
