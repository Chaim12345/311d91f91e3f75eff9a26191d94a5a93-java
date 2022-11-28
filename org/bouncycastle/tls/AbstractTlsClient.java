package org.bouncycastle.tls;

import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.tls.crypto.TlsCrypto;
import org.bouncycastle.util.Integers;
/* loaded from: classes4.dex */
public abstract class AbstractTlsClient extends AbstractTlsPeer implements TlsClient {

    /* renamed from: a  reason: collision with root package name */
    protected TlsClientContext f14665a;

    /* renamed from: b  reason: collision with root package name */
    protected ProtocolVersion[] f14666b;

    /* renamed from: c  reason: collision with root package name */
    protected int[] f14667c;

    /* renamed from: d  reason: collision with root package name */
    protected Vector f14668d;

    /* renamed from: e  reason: collision with root package name */
    protected Vector f14669e;

    /* renamed from: f  reason: collision with root package name */
    protected Vector f14670f;

    public AbstractTlsClient(TlsCrypto tlsCrypto) {
        super(tlsCrypto);
    }

    protected boolean c(Integer num, byte[] bArr) {
        int intValue = num.intValue();
        if (intValue == 10) {
            TlsExtensionsUtils.readSupportedGroupsExtension(bArr);
            return true;
        } else if (intValue != 11) {
            return false;
        } else {
            TlsExtensionsUtils.readSupportedPointFormatsExtension(bArr);
            return true;
        }
    }

    protected void d(Hashtable hashtable, Integer num) {
        byte[] extensionData = TlsUtils.getExtensionData(hashtable, num);
        if (extensionData != null && !c(num, extensionData)) {
            throw new TlsFatalAlert((short) 47);
        }
    }

    protected Vector e() {
        return null;
    }

    protected CertificateStatusRequest f() {
        return new CertificateStatusRequest((short) 1, new OCSPStatusRequest(null, null));
    }

    protected Vector g() {
        return null;
    }

    @Override // org.bouncycastle.tls.TlsPeer
    public int[] getCipherSuites() {
        return this.f14667c;
    }

    @Override // org.bouncycastle.tls.TlsClient
    public Hashtable getClientExtensions() {
        Vector e2;
        Hashtable hashtable = new Hashtable();
        boolean z = false;
        boolean z2 = false;
        for (ProtocolVersion protocolVersion : getProtocolVersions()) {
            if (TlsUtils.isTLSv13(protocolVersion)) {
                z = true;
            } else {
                z2 = true;
            }
        }
        Vector i2 = i();
        if (i2 != null) {
            TlsExtensionsUtils.addALPNExtensionClient(hashtable, i2);
        }
        Vector j2 = j();
        if (j2 != null) {
            TlsExtensionsUtils.addServerNameExtensionClient(hashtable, j2);
        }
        CertificateStatusRequest f2 = f();
        if (f2 != null) {
            TlsExtensionsUtils.addStatusRequestExtension(hashtable, f2);
        }
        if (z && (e2 = e()) != null) {
            TlsExtensionsUtils.addCertificateAuthoritiesExtension(hashtable, e2);
        }
        if (z2) {
            TlsExtensionsUtils.addEncryptThenMACExtension(hashtable);
            Vector g2 = g();
            if (g2 != null) {
                TlsExtensionsUtils.addStatusRequestV2Extension(hashtable, g2);
            }
            Vector n2 = n();
            if (n2 != null) {
                TlsExtensionsUtils.addTrustedCAKeysExtensionClient(hashtable, n2);
            }
        }
        if (TlsUtils.isSignatureAlgorithmsExtensionAllowed(this.f14665a.getClientVersion())) {
            Vector l2 = l();
            if (l2 != null && !l2.isEmpty()) {
                this.f14669e = l2;
                TlsExtensionsUtils.addSignatureAlgorithmsExtension(hashtable, l2);
            }
            Vector m2 = m();
            if (m2 != null && !m2.isEmpty()) {
                this.f14670f = m2;
                TlsExtensionsUtils.addSignatureAlgorithmsCertExtension(hashtable, m2);
            }
        }
        Vector h2 = h();
        Vector k2 = k(h2);
        if (k2 != null && !k2.isEmpty()) {
            this.f14668d = k2;
            TlsExtensionsUtils.addSupportedGroupsExtension(hashtable, k2);
        }
        if (z2 && (h2.contains(Integers.valueOf(2)) || h2.contains(Integers.valueOf(3)))) {
            TlsExtensionsUtils.addSupportedPointFormatsExtension(hashtable, new short[]{0});
        }
        return hashtable;
    }

    @Override // org.bouncycastle.tls.TlsClient
    public Vector getClientSupplementalData() {
        return null;
    }

    public TlsDHGroupVerifier getDHGroupVerifier() {
        return new DefaultTlsDHGroupVerifier();
    }

    @Override // org.bouncycastle.tls.TlsClient
    public Vector getEarlyKeyShareGroups() {
        Object elementAt;
        Vector vector = this.f14668d;
        if (vector == null || vector.isEmpty()) {
            return null;
        }
        int i2 = 29;
        if (!this.f14668d.contains(Integers.valueOf(29))) {
            i2 = 23;
            if (!this.f14668d.contains(Integers.valueOf(23))) {
                elementAt = this.f14668d.elementAt(0);
                return TlsUtils.vectorOfOne(elementAt);
            }
        }
        elementAt = Integers.valueOf(i2);
        return TlsUtils.vectorOfOne(elementAt);
    }

    @Override // org.bouncycastle.tls.TlsClient
    public Vector getExternalPSKs() {
        return null;
    }

    @Override // org.bouncycastle.tls.TlsClient
    public TlsPSKIdentity getPSKIdentity() {
        return null;
    }

    @Override // org.bouncycastle.tls.TlsPeer
    public ProtocolVersion[] getProtocolVersions() {
        return this.f14666b;
    }

    @Override // org.bouncycastle.tls.TlsClient
    public TlsSRPConfigVerifier getSRPConfigVerifier() {
        return new DefaultTlsSRPConfigVerifier();
    }

    @Override // org.bouncycastle.tls.TlsClient
    public TlsSRPIdentity getSRPIdentity() {
        return null;
    }

    public TlsSession getSessionToResume() {
        return null;
    }

    protected Vector h() {
        Vector namedGroupRoles = TlsUtils.getNamedGroupRoles(getCipherSuites());
        Vector vector = this.f14669e;
        Vector vector2 = this.f14670f;
        if (vector == null || TlsUtils.containsAnySignatureAlgorithm(vector, (short) 3) || (vector2 != null && TlsUtils.containsAnySignatureAlgorithm(vector2, (short) 3))) {
            TlsUtils.addToSet(namedGroupRoles, 3);
        }
        return namedGroupRoles;
    }

    protected Vector i() {
        return null;
    }

    @Override // org.bouncycastle.tls.TlsClient
    public void init(TlsClientContext tlsClientContext) {
        this.f14665a = tlsClientContext;
        this.f14666b = b();
        this.f14667c = a();
    }

    @Override // org.bouncycastle.tls.TlsClient
    public boolean isFallback() {
        return false;
    }

    protected Vector j() {
        return null;
    }

    protected Vector k(Vector vector) {
        TlsCrypto crypto = getCrypto();
        Vector vector2 = new Vector();
        if (vector.contains(Integers.valueOf(2))) {
            TlsUtils.addIfSupported(vector2, crypto, new int[]{29, 30});
        }
        if (vector.contains(Integers.valueOf(2)) || vector.contains(Integers.valueOf(3))) {
            TlsUtils.addIfSupported(vector2, crypto, new int[]{23, 24});
        }
        if (vector.contains(Integers.valueOf(1))) {
            TlsUtils.addIfSupported(vector2, crypto, new int[]{256, 257, NamedGroup.ffdhe4096});
        }
        return vector2;
    }

    protected Vector l() {
        return TlsUtils.getDefaultSupportedSignatureAlgorithms(this.f14665a);
    }

    protected Vector m() {
        return null;
    }

    protected Vector n() {
        return null;
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public void notifyHandshakeBeginning() {
        super.notifyHandshakeBeginning();
        this.f14668d = null;
        this.f14669e = null;
        this.f14670f = null;
    }

    @Override // org.bouncycastle.tls.TlsClient
    public void notifyNewSessionTicket(NewSessionTicket newSessionTicket) {
    }

    public void notifySelectedCipherSuite(int i2) {
    }

    @Override // org.bouncycastle.tls.TlsClient
    public void notifySelectedPSK(TlsPSK tlsPSK) {
    }

    public void notifyServerVersion(ProtocolVersion protocolVersion) {
    }

    public void notifySessionID(byte[] bArr) {
    }

    public void notifySessionToResume(TlsSession tlsSession) {
    }

    public void processServerExtensions(Hashtable hashtable) {
        if (hashtable == null) {
            return;
        }
        SecurityParameters securityParametersHandshake = this.f14665a.getSecurityParametersHandshake();
        if (TlsUtils.isTLSv13(securityParametersHandshake.getNegotiatedVersion())) {
            return;
        }
        d(hashtable, TlsExtensionsUtils.EXT_signature_algorithms);
        d(hashtable, TlsExtensionsUtils.EXT_signature_algorithms_cert);
        d(hashtable, TlsExtensionsUtils.EXT_supported_groups);
        if (TlsECCUtils.isECCCipherSuite(securityParametersHandshake.getCipherSuite())) {
            TlsExtensionsUtils.getSupportedPointFormatsExtension(hashtable);
        } else {
            d(hashtable, TlsExtensionsUtils.EXT_ec_point_formats);
        }
        d(hashtable, TlsExtensionsUtils.EXT_padding);
    }

    @Override // org.bouncycastle.tls.TlsClient
    public void processServerSupplementalData(Vector vector) {
        if (vector != null) {
            throw new TlsFatalAlert((short) 10);
        }
    }
}
