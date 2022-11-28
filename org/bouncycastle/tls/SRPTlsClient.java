package org.bouncycastle.tls;

import java.util.Hashtable;
import org.bouncycastle.tls.crypto.TlsCrypto;
/* loaded from: classes4.dex */
public class SRPTlsClient extends AbstractTlsClient {
    private static final int[] DEFAULT_CIPHER_SUITES = {CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_128_CBC_SHA};

    /* renamed from: g  reason: collision with root package name */
    protected TlsSRPIdentity f14794g;

    public SRPTlsClient(TlsCrypto tlsCrypto, TlsSRPIdentity tlsSRPIdentity) {
        super(tlsCrypto);
        this.f14794g = tlsSRPIdentity;
    }

    public SRPTlsClient(TlsCrypto tlsCrypto, byte[] bArr, byte[] bArr2) {
        this(tlsCrypto, new BasicTlsSRPIdentity(bArr, bArr2));
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

    @Override // org.bouncycastle.tls.TlsClient
    public TlsAuthentication getAuthentication() {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.TlsClient
    public Hashtable getClientExtensions() {
        Hashtable ensureExtensionsInitialised = TlsExtensionsUtils.ensureExtensionsInitialised(super.getClientExtensions());
        TlsSRPUtils.addSRPExtension(ensureExtensionsInitialised, this.f14794g.getSRPIdentity());
        return ensureExtensionsInitialised;
    }

    public ProtocolVersion getClientVersion() {
        return ProtocolVersion.TLSv12;
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.TlsClient
    public TlsSRPIdentity getSRPIdentity() {
        return this.f14794g;
    }

    protected boolean o() {
        return false;
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.TlsClient
    public void processServerExtensions(Hashtable hashtable) {
        if (!TlsUtils.hasExpectedEmptyExtensionData(hashtable, TlsSRPUtils.EXT_SRP, (short) 47) && o()) {
            throw new TlsFatalAlert((short) 47);
        }
        super.processServerExtensions(hashtable);
    }
}
