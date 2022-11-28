package org.bouncycastle.tls;

import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.tls.crypto.TlsCrypto;
import org.bouncycastle.tls.crypto.TlsDHConfig;
import org.bouncycastle.tls.crypto.TlsECConfig;
/* loaded from: classes4.dex */
public abstract class AbstractTlsServer extends AbstractTlsPeer implements TlsServer {

    /* renamed from: a  reason: collision with root package name */
    protected TlsServerContext f14673a;

    /* renamed from: b  reason: collision with root package name */
    protected ProtocolVersion[] f14674b;

    /* renamed from: c  reason: collision with root package name */
    protected int[] f14675c;

    /* renamed from: d  reason: collision with root package name */
    protected int[] f14676d;

    /* renamed from: e  reason: collision with root package name */
    protected boolean f14677e;

    /* renamed from: f  reason: collision with root package name */
    protected short f14678f;

    /* renamed from: g  reason: collision with root package name */
    protected boolean f14679g;

    /* renamed from: h  reason: collision with root package name */
    protected boolean f14680h;

    /* renamed from: i  reason: collision with root package name */
    protected CertificateStatusRequest f14681i;

    /* renamed from: j  reason: collision with root package name */
    protected Vector f14682j;

    /* renamed from: k  reason: collision with root package name */
    protected Vector f14683k;

    /* renamed from: l  reason: collision with root package name */
    protected int f14684l;

    /* renamed from: m  reason: collision with root package name */
    protected Vector f14685m;

    /* renamed from: n  reason: collision with root package name */
    protected ProtocolName f14686n;

    /* renamed from: o  reason: collision with root package name */
    protected final Hashtable f14687o;

    public AbstractTlsServer(TlsCrypto tlsCrypto) {
        super(tlsCrypto);
        this.f14687o = new Hashtable();
    }

    protected boolean c() {
        return true;
    }

    protected boolean d() {
        return true;
    }

    protected boolean e() {
        return false;
    }

    protected boolean f() {
        return false;
    }

    protected boolean g() {
        return false;
    }

    public CertificateRequest getCertificateRequest() {
        return null;
    }

    public CertificateStatus getCertificateStatus() {
        return null;
    }

    @Override // org.bouncycastle.tls.TlsPeer
    public int[] getCipherSuites() {
        return this.f14675c;
    }

    @Override // org.bouncycastle.tls.TlsServer
    public TlsDHConfig getDHConfig() {
        return TlsDHUtils.createNamedDHConfig(this.f14673a, n(TlsDHUtils.getMinimumFiniteFieldBits(this.f14684l)));
    }

    @Override // org.bouncycastle.tls.TlsServer
    public TlsECConfig getECDHConfig() {
        return TlsECCUtils.createNamedECConfig(this.f14673a, p(TlsECCUtils.getMinimumCurveBits(this.f14684l)));
    }

    @Override // org.bouncycastle.tls.TlsServer
    public TlsPSKExternal getExternalPSK(Vector vector) {
        return null;
    }

    public byte[] getNewSessionID() {
        return null;
    }

    @Override // org.bouncycastle.tls.TlsServer
    public NewSessionTicket getNewSessionTicket() {
        return new NewSessionTicket(0L, TlsUtils.EMPTY_BYTES);
    }

    @Override // org.bouncycastle.tls.TlsServer
    public TlsPSKIdentityManager getPSKIdentityManager() {
        return null;
    }

    @Override // org.bouncycastle.tls.TlsPeer
    public ProtocolVersion[] getProtocolVersions() {
        return this.f14674b;
    }

    @Override // org.bouncycastle.tls.TlsServer
    public TlsSRPLoginParameters getSRPLoginParameters() {
        return null;
    }

    public int getSelectedCipherSuite() {
        int[] commonCipherSuites;
        SecurityParameters securityParametersHandshake = this.f14673a.getSecurityParametersHandshake();
        ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
        if (TlsUtils.isTLSv13(negotiatedVersion)) {
            int commonCipherSuite13 = TlsUtils.getCommonCipherSuite13(negotiatedVersion, this.f14676d, getCipherSuites(), l());
            if (commonCipherSuite13 >= 0 && m(commonCipherSuite13)) {
                return commonCipherSuite13;
            }
        } else {
            Vector usableSignatureAlgorithms = TlsUtils.getUsableSignatureAlgorithms(securityParametersHandshake.getClientSigAlgs());
            int h2 = h();
            int i2 = i();
            for (int i3 : TlsUtils.getCommonCipherSuites(this.f14676d, getCipherSuites(), l())) {
                if (k(i3, h2, i2, usableSignatureAlgorithms) && m(i3)) {
                    return i3;
                }
            }
        }
        throw new TlsFatalAlert((short) 40, "No selectable cipher suite");
    }

    public Hashtable getServerExtensions() {
        Hashtable hashtable;
        Integer num;
        if (!TlsUtils.isTLSv13(this.f14673a)) {
            if (this.f14677e && d() && TlsUtils.isBlockCipherSuite(this.f14684l)) {
                TlsExtensionsUtils.addEncryptThenMACExtension(this.f14687o);
            }
            if (this.f14679g && f()) {
                TlsExtensionsUtils.addTruncatedHMacExtension(this.f14687o);
            }
            if (this.f14680h && TlsECCUtils.isECCCipherSuite(this.f14684l)) {
                TlsExtensionsUtils.addSupportedPointFormatsExtension(this.f14687o, new short[]{0});
            }
            if (this.f14682j == null || !e()) {
                if (this.f14681i != null && c()) {
                    hashtable = this.f14687o;
                    num = TlsExtensionsUtils.EXT_status_request;
                }
                if (this.f14683k != null && g()) {
                    TlsExtensionsUtils.addTrustedCAKeysExtensionServer(this.f14687o);
                }
            } else {
                hashtable = this.f14687o;
                num = TlsExtensionsUtils.EXT_status_request_v2;
            }
            TlsExtensionsUtils.addEmptyExtensionData(hashtable, num);
            if (this.f14683k != null) {
                TlsExtensionsUtils.addTrustedCAKeysExtensionServer(this.f14687o);
            }
        } else if (this.f14681i != null) {
            c();
        }
        short s2 = this.f14678f;
        if (s2 >= 0 && MaxFragmentLength.isValid(s2)) {
            TlsExtensionsUtils.addMaxFragmentLengthExtension(this.f14687o, this.f14678f);
        }
        return this.f14687o;
    }

    @Override // org.bouncycastle.tls.TlsServer
    public void getServerExtensionsForConnection(Hashtable hashtable) {
        Vector vector;
        if (!t() && (vector = this.f14685m) != null && !vector.isEmpty()) {
            this.f14686n = r();
        }
        ProtocolName protocolName = this.f14686n;
        if (protocolName == null) {
            hashtable.remove(TlsExtensionsUtils.EXT_application_layer_protocol_negotiation);
        } else {
            TlsExtensionsUtils.addALPNExtensionServer(hashtable, protocolName);
        }
    }

    @Override // org.bouncycastle.tls.TlsServer
    public Vector getServerSupplementalData() {
        return null;
    }

    public ProtocolVersion getServerVersion() {
        ProtocolVersion[] clientSupportedVersions;
        ProtocolVersion[] protocolVersions = getProtocolVersions();
        for (ProtocolVersion protocolVersion : this.f14673a.getClientSupportedVersions()) {
            if (ProtocolVersion.contains(protocolVersions, protocolVersion)) {
                return protocolVersion;
            }
        }
        throw new TlsFatalAlert((short) 70);
    }

    public TlsSession getSessionToResume(byte[] bArr) {
        return null;
    }

    public int[] getSupportedGroups() {
        return new int[]{29, 30, 23, 24, 256, 257, NamedGroup.ffdhe4096};
    }

    protected int h() {
        int[] clientSupportedGroups = this.f14673a.getSecurityParametersHandshake().getClientSupportedGroups();
        if (clientSupportedGroups == null) {
            return NamedGroup.getMaximumCurveBits();
        }
        int i2 = 0;
        for (int i3 : clientSupportedGroups) {
            i2 = Math.max(i2, NamedGroup.getCurveBits(i3));
        }
        return i2;
    }

    protected int i() {
        int[] clientSupportedGroups = this.f14673a.getSecurityParametersHandshake().getClientSupportedGroups();
        if (clientSupportedGroups == null) {
            return NamedGroup.getMaximumFiniteFieldBits();
        }
        int i2 = 0;
        for (int i3 : clientSupportedGroups) {
            i2 = Math.max(i2, NamedGroup.getFiniteFieldBits(i3));
        }
        return i2;
    }

    @Override // org.bouncycastle.tls.TlsServer
    public void init(TlsServerContext tlsServerContext) {
        this.f14673a = tlsServerContext;
        this.f14674b = b();
        this.f14675c = a();
    }

    protected Vector j() {
        return null;
    }

    protected boolean k(int i2, int i3, int i4, Vector vector) {
        return TlsUtils.isValidVersionForCipherSuite(i2, this.f14673a.getServerVersion()) && i3 >= TlsECCUtils.getMinimumCurveBits(i2) && i4 >= TlsDHUtils.getMinimumFiniteFieldBits(i2) && TlsUtils.isValidCipherSuiteForSignatureAlgorithms(i2, vector);
    }

    protected boolean l() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean m(int i2) {
        this.f14684l = i2;
        return true;
    }

    protected int n(int i2) {
        int[] clientSupportedGroups = this.f14673a.getSecurityParametersHandshake().getClientSupportedGroups();
        if (clientSupportedGroups == null) {
            return o(i2);
        }
        for (int i3 : clientSupportedGroups) {
            if (NamedGroup.getFiniteFieldBits(i3) >= i2) {
                return i3;
            }
        }
        return -1;
    }

    public void notifyClientCertificate(Certificate certificate) {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.TlsServer
    public void notifyClientVersion(ProtocolVersion protocolVersion) {
    }

    @Override // org.bouncycastle.tls.TlsServer
    public void notifyFallback(boolean z) {
        ProtocolVersion latestDTLS;
        if (z) {
            ProtocolVersion[] protocolVersions = getProtocolVersions();
            ProtocolVersion clientVersion = this.f14673a.getClientVersion();
            if (clientVersion.isTLS()) {
                latestDTLS = ProtocolVersion.getLatestTLS(protocolVersions);
            } else if (!clientVersion.isDTLS()) {
                throw new TlsFatalAlert((short) 80);
            } else {
                latestDTLS = ProtocolVersion.getLatestDTLS(protocolVersions);
            }
            if (latestDTLS != null && latestDTLS.isLaterVersionOf(clientVersion)) {
                throw new TlsFatalAlert((short) 86);
            }
        }
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public void notifyHandshakeBeginning() {
        super.notifyHandshakeBeginning();
        this.f14676d = null;
        this.f14677e = false;
        this.f14678f = (short) 0;
        this.f14679g = false;
        this.f14680h = false;
        this.f14681i = null;
        this.f14684l = -1;
        this.f14686n = null;
        this.f14687o.clear();
    }

    @Override // org.bouncycastle.tls.TlsServer
    public void notifyOfferedCipherSuites(int[] iArr) {
        this.f14676d = iArr;
    }

    public void notifySession(TlsSession tlsSession) {
    }

    protected int o(int i2) {
        if (i2 <= 2048) {
            return 256;
        }
        if (i2 <= 3072) {
            return 257;
        }
        if (i2 <= 4096) {
            return NamedGroup.ffdhe4096;
        }
        if (i2 <= 6144) {
            return NamedGroup.ffdhe6144;
        }
        if (i2 <= 8192) {
            return NamedGroup.ffdhe8192;
        }
        return -1;
    }

    protected int p(int i2) {
        int[] clientSupportedGroups = this.f14673a.getSecurityParametersHandshake().getClientSupportedGroups();
        if (clientSupportedGroups == null) {
            return q(i2);
        }
        for (int i3 : clientSupportedGroups) {
            if (NamedGroup.getCurveBits(i3) >= i2) {
                return i3;
            }
        }
        return -1;
    }

    public void processClientExtensions(Hashtable hashtable) {
        Vector vector;
        if (hashtable != null) {
            this.f14685m = TlsExtensionsUtils.getALPNExtensionClient(hashtable);
            if (t() && (vector = this.f14685m) != null && !vector.isEmpty()) {
                this.f14686n = r();
            }
            this.f14677e = TlsExtensionsUtils.hasEncryptThenMACExtension(hashtable);
            this.f14679g = TlsExtensionsUtils.hasTruncatedHMacExtension(hashtable);
            this.f14682j = TlsExtensionsUtils.getStatusRequestV2Extension(hashtable);
            this.f14683k = TlsExtensionsUtils.getTrustedCAKeysExtensionClient(hashtable);
            this.f14680h = TlsExtensionsUtils.getSupportedPointFormatsExtension(hashtable) != null;
            this.f14681i = TlsExtensionsUtils.getStatusRequestExtension(hashtable);
            short maxFragmentLengthExtension = TlsExtensionsUtils.getMaxFragmentLengthExtension(hashtable);
            this.f14678f = maxFragmentLengthExtension;
            if (maxFragmentLengthExtension >= 0 && !MaxFragmentLength.isValid(maxFragmentLengthExtension)) {
                throw new TlsFatalAlert((short) 47);
            }
        }
    }

    @Override // org.bouncycastle.tls.TlsServer
    public void processClientSupplementalData(Vector vector) {
        if (vector != null) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    protected int q(int i2) {
        if (i2 <= 256) {
            return 23;
        }
        if (i2 <= 384) {
            return 24;
        }
        return i2 <= 521 ? 25 : -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProtocolName r() {
        Vector j2 = j();
        if (j2 == null || j2.isEmpty()) {
            return null;
        }
        ProtocolName s2 = s(this.f14685m, j2);
        if (s2 != null) {
            return s2;
        }
        throw new TlsFatalAlert(AlertDescription.no_application_protocol);
    }

    protected ProtocolName s(Vector vector, Vector vector2) {
        for (int i2 = 0; i2 < vector2.size(); i2++) {
            ProtocolName protocolName = (ProtocolName) vector2.elementAt(i2);
            if (vector.contains(protocolName)) {
                return protocolName;
            }
        }
        return null;
    }

    protected boolean t() {
        return true;
    }
}
