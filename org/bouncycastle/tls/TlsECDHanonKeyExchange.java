package org.bouncycastle.tls;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsECConfig;
import org.bouncycastle.tls.crypto.TlsSecret;
/* loaded from: classes4.dex */
public class TlsECDHanonKeyExchange extends AbstractTlsKeyExchange {

    /* renamed from: c  reason: collision with root package name */
    protected TlsECConfig f14846c;

    /* renamed from: d  reason: collision with root package name */
    protected TlsAgreement f14847d;

    public TlsECDHanonKeyExchange(int i2) {
        this(i2, null);
    }

    public TlsECDHanonKeyExchange(int i2, TlsECConfig tlsECConfig) {
        super(checkKeyExchange(i2));
        this.f14846c = tlsECConfig;
    }

    private static int checkKeyExchange(int i2) {
        if (i2 == 20) {
            return i2;
        }
        throw new IllegalArgumentException("unsupported key exchange algorithm");
    }

    protected void a(OutputStream outputStream) {
        TlsUtils.writeOpaque8(this.f14847d.generateEphemeral(), outputStream);
    }

    protected void b(byte[] bArr) {
        TlsECCUtils.checkPointEncoding(this.f14846c.getNamedGroup(), bArr);
        this.f14847d.receivePeerValue(bArr);
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void generateClientKeyExchange(OutputStream outputStream) {
        a(outputStream);
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public TlsSecret generatePreMasterSecret() {
        return this.f14847d.calculateSecret();
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public byte[] generateServerKeyExchange() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        TlsECCUtils.writeECConfig(this.f14846c, byteArrayOutputStream);
        this.f14847d = this.f14672b.getCrypto().createECDomain(this.f14846c).createECDH();
        a(byteArrayOutputStream);
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
        b(TlsUtils.readOpaque8(inputStream, 1));
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
        this.f14846c = TlsECCUtils.receiveECDHConfig(this.f14672b, inputStream);
        byte[] readOpaque8 = TlsUtils.readOpaque8(inputStream, 1);
        this.f14847d = this.f14672b.getCrypto().createECDomain(this.f14846c).createECDH();
        b(readOpaque8);
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public boolean requiresServerKeyExchange() {
        return true;
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void skipServerCredentials() {
    }
}
