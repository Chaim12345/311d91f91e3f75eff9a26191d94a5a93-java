package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.tls.OfferedPsks;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
/* loaded from: classes4.dex */
public class TlsClientProtocol extends TlsProtocol {
    protected TlsAuthentication A;
    protected CertificateStatus B;
    protected CertificateRequest C;
    protected TlsClient u;
    TlsClientContextImpl v;
    protected Hashtable w;
    OfferedPsks.BindersConfig x;
    protected ClientHello y;
    protected TlsKeyExchange z;

    public TlsClientProtocol() {
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
    }

    public TlsClientProtocol(InputStream inputStream, OutputStream outputStream) {
        super(inputStream, outputStream);
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
    }

    protected ServerHello A0(ByteArrayInputStream byteArrayInputStream) {
        return ServerHello.parse(byteArrayInputStream);
    }

    protected void B0() {
        Hashtable extensions = this.y.getExtensions();
        extensions.remove(TlsExtensionsUtils.EXT_cookie);
        extensions.remove(TlsExtensionsUtils.EXT_early_data);
        extensions.remove(TlsExtensionsUtils.EXT_key_share);
        extensions.remove(TlsExtensionsUtils.EXT_pre_shared_key);
        byte[] bArr = this.f14870g;
        if (bArr != null) {
            TlsExtensionsUtils.addCookieExtension(extensions, bArr);
            this.f14870g = null;
        }
        OfferedPsks.BindersConfig bindersConfig = this.x;
        if (bindersConfig != null) {
            OfferedPsks.BindersConfig e2 = TlsUtils.e(this.v, bindersConfig, extensions);
            this.x = e2;
            if (e2 == null) {
                this.u.notifySelectedPSK(null);
            }
        }
        int i2 = this.f14871h;
        if (i2 < 0) {
            throw new TlsFatalAlert((short) 80);
        }
        this.w = TlsUtils.b(this.v, extensions, i2);
        this.f14864a.p(true);
        Z();
        E0();
    }

    protected void C0(DigitallySigned digitallySigned) {
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 15);
        digitallySigned.encode(handshakeMessageOutput);
        handshakeMessageOutput.c(this);
    }

    protected void D0() {
        ProtocolVersion[] protocolVersions;
        ProtocolVersion earliestTLS;
        ProtocolVersion latestTLS;
        byte[] bArr;
        ProtocolVersion protocolVersion;
        SessionParameters sessionParameters;
        SessionParameters sessionParameters2;
        SecurityParameters securityParametersHandshake = this.v.getSecurityParametersHandshake();
        if (securityParametersHandshake.isRenegotiating()) {
            earliestTLS = this.v.getClientVersion();
            protocolVersions = earliestTLS.only();
            latestTLS = earliestTLS;
        } else {
            protocolVersions = this.u.getProtocolVersions();
            ProtocolVersion protocolVersion2 = ProtocolVersion.SSLv3;
            if (ProtocolVersion.contains(protocolVersions, protocolVersion2)) {
                this.f14864a.s(protocolVersion2);
            } else {
                this.f14864a.s(ProtocolVersion.TLSv10);
            }
            earliestTLS = ProtocolVersion.getEarliestTLS(protocolVersions);
            latestTLS = ProtocolVersion.getLatestTLS(protocolVersions);
            if (!ProtocolVersion.c(latestTLS)) {
                throw new TlsFatalAlert((short) 80);
            }
            this.v.i(latestTLS);
        }
        this.v.h(protocolVersions);
        ProtocolVersion protocolVersion3 = ProtocolVersion.TLSv12;
        boolean isEqualOrLaterVersionOf = protocolVersion3.isEqualOrLaterVersionOf(earliestTLS);
        boolean isEqualOrEarlierVersionOf = ProtocolVersion.TLSv13.isEqualOrEarlierVersionOf(latestTLS);
        l(isEqualOrLaterVersionOf ? this.u.getSessionToResume() : null);
        this.u.notifySessionToResume(this.f14867d);
        byte[] P = TlsUtils.P(this.f14867d);
        boolean isFallback = this.u.isFallback();
        int[] cipherSuites = this.u.getCipherSuites();
        if (P.length > 0 && (sessionParameters2 = this.f14868e) != null && (!Arrays.contains(cipherSuites, sessionParameters2.getCipherSuite()) || this.f14868e.getCompressionAlgorithm() != 0)) {
            P = TlsUtils.EMPTY_BYTES;
        }
        Hashtable ensureExtensionsInitialised = TlsExtensionsUtils.ensureExtensionsInitialised(this.u.getClientExtensions());
        this.f14872i = ensureExtensionsInitialised;
        if (isEqualOrEarlierVersionOf) {
            TlsExtensionsUtils.addSupportedVersionsExtensionClient(ensureExtensionsInitialised, protocolVersions);
            if (P.length < 1) {
                P = this.v.getNonceGenerator().generateNonce(32);
            }
            bArr = P;
            protocolVersion = protocolVersion3;
        } else {
            bArr = P;
            protocolVersion = latestTLS;
        }
        this.v.j(protocolVersion);
        securityParametersHandshake.I = TlsExtensionsUtils.getServerNameExtensionClient(this.f14872i);
        if (TlsUtils.isSignatureAlgorithmsExtensionAllowed(latestTLS)) {
            TlsUtils.A(securityParametersHandshake, this.f14872i);
        }
        securityParametersHandshake.L = TlsExtensionsUtils.getSupportedGroupsExtension(this.f14872i);
        this.x = TlsUtils.d(this.v, this.u, this.f14872i, cipherSuites);
        this.w = TlsUtils.a(this.v, this.u, this.f14872i);
        if (TlsUtils.X(protocolVersions) && (this.u.shouldUseExtendedMasterSecret() || ((sessionParameters = this.f14868e) != null && sessionParameters.isExtendedMasterSecret()))) {
            TlsExtensionsUtils.addExtendedMasterSecretExtension(this.f14872i);
        } else if (!isEqualOrEarlierVersionOf && this.u.requiresExtendedMasterSecret()) {
            throw new TlsFatalAlert((short) 80);
        }
        securityParametersHandshake.f14817t = TlsProtocol.i(!isEqualOrEarlierVersionOf && this.u.shouldUseGMTUnixTime(), this.v);
        if (!securityParametersHandshake.isRenegotiating()) {
            boolean z = TlsUtils.getExtensionData(this.f14872i, TlsProtocol.f14862s) == null;
            boolean z2 = !Arrays.contains(cipherSuites, 255);
            if (z && z2) {
                cipherSuites = Arrays.append(cipherSuites, 255);
            }
        } else if (!securityParametersHandshake.isSecureRenegotiation()) {
            throw new TlsFatalAlert((short) 80);
        } else {
            this.f14872i.put(TlsProtocol.f14862s, TlsProtocol.j(this.v.getSecurityParametersConnection().getLocalVerifyData()));
        }
        int[] append = (!isFallback || Arrays.contains(cipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV)) ? cipherSuites : Arrays.append(cipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV);
        OfferedPsks.BindersConfig bindersConfig = this.x;
        this.y = new ClientHello(protocolVersion, securityParametersHandshake.getClientRandom(), bArr, null, append, this.f14872i, bindersConfig != null ? bindersConfig.f14776d : 0);
        E0();
    }

    protected void E0() {
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 1);
        this.y.encode(this.v, handshakeMessageOutput);
        handshakeMessageOutput.b(this.f14866c, this.y.getBindersSize());
        if (this.x != null) {
            OfferedPsks.a(handshakeMessageOutput, this.v.getCrypto(), this.f14866c, this.x);
        }
        handshakeMessageOutput.e(this, this.f14866c, this.y.getBindersSize());
    }

    protected void F0() {
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 16);
        this.z.generateClientKeyExchange(handshakeMessageOutput);
        handshakeMessageOutput.c(this);
    }

    protected void G0() {
        this.C = null;
    }

    protected void H0() {
        if (!this.f14876m) {
            throw new TlsFatalAlert((short) 10);
        }
        this.A = TlsUtils.z0(this.v);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.tls.TlsProtocol
    public void c(boolean z) {
        super.c(z);
        D0();
        this.f14874k = (short) 1;
    }

    public void connect(TlsClient tlsClient) {
        if (tlsClient == null) {
            throw new IllegalArgumentException("'tlsClient' cannot be null");
        }
        if (this.u != null) {
            throw new IllegalStateException("'connect' can only be called once");
        }
        this.u = tlsClient;
        TlsClientContextImpl tlsClientContextImpl = new TlsClientContextImpl(tlsClient.getCrypto());
        this.v = tlsClientContextImpl;
        tlsClient.init(tlsClientContextImpl);
        tlsClient.notifyCloseHandle(this);
        c(false);
        if (this.f14879p) {
            d();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.tls.TlsProtocol
    public void f() {
        super.f();
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void l0(short s2, HandshakeMessageInput handshakeMessageInput) {
        CertificateRequest certificateRequest;
        if (!B() || this.f14875l) {
            throw new TlsFatalAlert((short) 80);
        }
        if (s2 == 2) {
            short s3 = this.f14874k;
            if (s3 == 1) {
                throw new TlsFatalAlert((short) 80);
            }
            if (s3 != 3) {
                throw new TlsFatalAlert((short) 10);
            }
            ServerHello A0 = A0(handshakeMessageInput);
            if (A0.isHelloRetryRequest()) {
                throw new TlsFatalAlert((short) 10);
            }
            p0(A0, true);
            handshakeMessageInput.updateHash(this.f14866c);
            this.f14874k = (short) 4;
            q0(A0, true);
        } else if (s2 == 4) {
            u0(handshakeMessageInput);
        } else if (s2 == 8) {
            if (this.f14874k != 4) {
                throw new TlsFatalAlert((short) 10);
            }
            t0(handshakeMessageInput);
            this.f14874k = (short) 5;
        } else if (s2 == 11) {
            short s4 = this.f14874k;
            if (s4 == 5) {
                G0();
            } else if (s4 != 11) {
                throw new TlsFatalAlert((short) 10);
            }
            v0(handshakeMessageInput);
            this.f14874k = (short) 7;
        } else if (s2 == 13) {
            short s5 = this.f14874k;
            if (s5 != 5) {
                if (s5 == 21) {
                    throw new TlsFatalAlert((short) 10);
                }
                throw new TlsFatalAlert((short) 10);
            }
            s0(handshakeMessageInput, false);
            this.f14874k = (short) 11;
        } else if (s2 == 15) {
            if (this.f14874k != 7) {
                throw new TlsFatalAlert((short) 10);
            }
            w0(handshakeMessageInput);
            handshakeMessageInput.updateHash(this.f14866c);
            this.f14874k = (short) 9;
        } else if (s2 != 20) {
            if (s2 != 24) {
                throw new TlsFatalAlert((short) 10);
            }
            N(handshakeMessageInput);
        } else {
            short s6 = this.f14874k;
            if (s6 != 5) {
                if (s6 != 9) {
                    if (s6 != 11) {
                        throw new TlsFatalAlert((short) 10);
                    }
                }
                x0(handshakeMessageInput);
                handshakeMessageInput.updateHash(this.f14866c);
                this.f14874k = (short) 20;
                byte[] H = TlsUtils.H(this.f14866c);
                this.f14864a.p(false);
                certificateRequest = this.C;
                if (certificateRequest != null) {
                    TlsCredentialedSigner u = TlsUtils.u(this.A, certificateRequest);
                    Certificate certificate = u != null ? u.getCertificate() : null;
                    if (certificate == null) {
                        certificate = Certificate.EMPTY_CHAIN_TLS13;
                    }
                    T(certificate);
                    this.f14874k = (short) 15;
                    if (u != null) {
                        U(TlsUtils.D(this.v, u, this.f14866c));
                        this.f14874k = (short) 17;
                    }
                }
                V();
                this.f14874k = (short) 18;
                TlsUtils.v(this.v, H, this.f14864a);
                this.f14864a.d();
                this.f14864a.c(false);
                h();
            }
            G0();
            H0();
            x0(handshakeMessageInput);
            handshakeMessageInput.updateHash(this.f14866c);
            this.f14874k = (short) 20;
            byte[] H2 = TlsUtils.H(this.f14866c);
            this.f14864a.p(false);
            certificateRequest = this.C;
            if (certificateRequest != null) {
            }
            V();
            this.f14874k = (short) 18;
            TlsUtils.v(this.v, H2, this.f14864a);
            this.f14864a.d();
            this.f14864a.c(false);
            h();
        }
    }

    @Override // org.bouncycastle.tls.TlsProtocol
    protected TlsContext m() {
        return this.v;
    }

    protected void m0() {
        TlsUtils.l0(this.v, this.B, this.z, this.A, this.f14872i, this.f14873j);
    }

    @Override // org.bouncycastle.tls.TlsProtocol
    AbstractTlsContext n() {
        return this.v;
    }

    protected void n0(Vector vector) {
        this.u.processServerSupplementalData(vector);
        this.f14874k = (short) 6;
        this.z = TlsUtils.U(this.v, this.u);
    }

    @Override // org.bouncycastle.tls.TlsProtocol
    protected TlsPeer o() {
        return this.u;
    }

    protected void o0(ServerHello serverHello) {
        ProtocolVersion protocolVersion = ProtocolVersion.TLSv12;
        this.f14864a.s(protocolVersion);
        SecurityParameters securityParametersHandshake = this.v.getSecurityParametersHandshake();
        if (securityParametersHandshake.isRenegotiating()) {
            throw new TlsFatalAlert((short) 80);
        }
        ProtocolVersion version = serverHello.getVersion();
        byte[] sessionID = serverHello.getSessionID();
        int cipherSuite = serverHello.getCipherSuite();
        if (!protocolVersion.equals(version) || !Arrays.areEqual(this.y.getSessionID(), sessionID) || !TlsUtils.Z(this.y.getCipherSuites(), cipherSuite)) {
            throw new TlsFatalAlert((short) 47);
        }
        Hashtable extensions = serverHello.getExtensions();
        if (extensions == null) {
            throw new TlsFatalAlert((short) 47);
        }
        TlsUtils.n(extensions, 6, (short) 47);
        Enumeration keys = extensions.keys();
        while (keys.hasMoreElements()) {
            Integer num = (Integer) keys.nextElement();
            if (44 != num.intValue() && TlsUtils.getExtensionData(this.f14872i, num) == null) {
                throw new TlsFatalAlert(AlertDescription.unsupported_extension);
            }
        }
        ProtocolVersion supportedVersionsExtensionServer = TlsExtensionsUtils.getSupportedVersionsExtensionServer(extensions);
        if (supportedVersionsExtensionServer == null) {
            throw new TlsFatalAlert(AlertDescription.missing_extension);
        }
        if (!ProtocolVersion.TLSv13.isEqualOrEarlierVersionOf(supportedVersionsExtensionServer) || !ProtocolVersion.contains(this.v.getClientSupportedVersions(), supportedVersionsExtensionServer) || !TlsUtils.isValidVersionForCipherSuite(cipherSuite, supportedVersionsExtensionServer)) {
            throw new TlsFatalAlert((short) 47);
        }
        OfferedPsks.BindersConfig bindersConfig = this.x;
        if (bindersConfig != null && !Arrays.contains(bindersConfig.f14774b, (short) 1)) {
            this.x = null;
            this.u.notifySelectedPSK(null);
        }
        int keyShareHelloRetryRequest = TlsExtensionsUtils.getKeyShareHelloRetryRequest(extensions);
        if (!TlsUtils.a0(supportedVersionsExtensionServer, securityParametersHandshake.getClientSupportedGroups(), this.w, keyShareHelloRetryRequest)) {
            throw new TlsFatalAlert((short) 47);
        }
        byte[] cookieExtension = TlsExtensionsUtils.getCookieExtension(extensions);
        securityParametersHandshake.S = supportedVersionsExtensionServer;
        TlsUtils.h0(this.v, this.u);
        this.f14875l = false;
        byte[] bArr = TlsUtils.EMPTY_BYTES;
        securityParametersHandshake.w = bArr;
        this.u.notifySessionID(bArr);
        TlsUtils.d0(securityParametersHandshake, cipherSuite);
        this.u.notifySelectedCipherSuite(cipherSuite);
        this.w = null;
        this.f14870g = cookieExtension;
        this.f14871h = keyShareHelloRetryRequest;
    }

    protected void p0(ServerHello serverHello, boolean z) {
        TlsSecret tlsSecret;
        TlsPSK tlsPSK;
        TlsSecret calculateSecret;
        SecurityParameters securityParametersHandshake = this.v.getSecurityParametersHandshake();
        ProtocolVersion version = serverHello.getVersion();
        byte[] sessionID = serverHello.getSessionID();
        int cipherSuite = serverHello.getCipherSuite();
        if (!ProtocolVersion.TLSv12.equals(version) || !Arrays.areEqual(this.y.getSessionID(), sessionID)) {
            throw new TlsFatalAlert((short) 47);
        }
        Hashtable extensions = serverHello.getExtensions();
        if (extensions == null) {
            throw new TlsFatalAlert((short) 47);
        }
        TlsUtils.n(extensions, 2, (short) 47);
        if (z) {
            ProtocolVersion supportedVersionsExtensionServer = TlsExtensionsUtils.getSupportedVersionsExtensionServer(extensions);
            if (supportedVersionsExtensionServer == null) {
                throw new TlsFatalAlert(AlertDescription.missing_extension);
            }
            if (!securityParametersHandshake.getNegotiatedVersion().equals(supportedVersionsExtensionServer) || securityParametersHandshake.getCipherSuite() != cipherSuite) {
                throw new TlsFatalAlert((short) 47);
            }
        } else if (!TlsUtils.Z(this.y.getCipherSuites(), cipherSuite) || !TlsUtils.isValidVersionForCipherSuite(cipherSuite, securityParametersHandshake.getNegotiatedVersion())) {
            throw new TlsFatalAlert((short) 47);
        } else {
            this.f14875l = false;
            byte[] bArr = TlsUtils.EMPTY_BYTES;
            securityParametersHandshake.w = bArr;
            this.u.notifySessionID(bArr);
            TlsUtils.d0(securityParametersHandshake, cipherSuite);
            this.u.notifySelectedCipherSuite(cipherSuite);
        }
        this.y = null;
        securityParametersHandshake.u = serverHello.getRandom();
        securityParametersHandshake.f14800c = false;
        securityParametersHandshake.C = true;
        securityParametersHandshake.T = this.f14872i.containsKey(TlsExtensionsUtils.EXT_status_request) ? 1 : 0;
        int preSharedKeyServerHello = TlsExtensionsUtils.getPreSharedKeyServerHello(extensions);
        if (preSharedKeyServerHello >= 0) {
            OfferedPsks.BindersConfig bindersConfig = this.x;
            if (bindersConfig != null) {
                TlsPSK[] tlsPSKArr = bindersConfig.f14773a;
                if (preSharedKeyServerHello < tlsPSKArr.length) {
                    tlsPSK = tlsPSKArr[preSharedKeyServerHello];
                    if (tlsPSK.getPRFAlgorithm() != securityParametersHandshake.getPRFAlgorithm()) {
                        throw new TlsFatalAlert((short) 47);
                    }
                    tlsSecret = this.x.f14775c[preSharedKeyServerHello];
                    this.f14876m = true;
                }
            }
            throw new TlsFatalAlert((short) 47);
        }
        tlsSecret = null;
        tlsPSK = null;
        this.u.notifySelectedPSK(tlsPSK);
        KeyShareEntry keyShareServerHello = TlsExtensionsUtils.getKeyShareServerHello(extensions);
        if (keyShareServerHello == null) {
            if (z || tlsSecret == null || !Arrays.contains(this.x.f14774b, (short) 0)) {
                throw new TlsFatalAlert((short) 47);
            }
            calculateSecret = null;
        } else if (tlsSecret != null && !Arrays.contains(this.x.f14774b, (short) 1)) {
            throw new TlsFatalAlert((short) 47);
        } else {
            TlsAgreement tlsAgreement = (TlsAgreement) this.w.get(Integers.valueOf(keyShareServerHello.getNamedGroup()));
            if (tlsAgreement == null) {
                throw new TlsFatalAlert((short) 47);
            }
            tlsAgreement.receivePeerValue(keyShareServerHello.getKeyExchange());
            calculateSecret = tlsAgreement.calculateSecret();
        }
        this.w = null;
        this.x = null;
        TlsUtils.x(this.v, tlsSecret, calculateSecret);
        y();
        this.f14867d = TlsUtils.importSession(securityParametersHandshake.getSessionID(), null);
    }

    protected void q0(ServerHello serverHello, boolean z) {
        TlsUtils.w(this.v, TlsUtils.H(this.f14866c), this.f14864a);
        if (!z) {
            this.f14864a.p(true);
            Z();
        }
        this.f14864a.d();
        this.f14864a.c(false);
    }

    protected void r0(ServerHello serverHello) {
        TlsSession tlsSession;
        Hashtable extensions = serverHello.getExtensions();
        ProtocolVersion version = serverHello.getVersion();
        ProtocolVersion supportedVersionsExtensionServer = TlsExtensionsUtils.getSupportedVersionsExtensionServer(extensions);
        if (supportedVersionsExtensionServer != null) {
            if (!ProtocolVersion.TLSv12.equals(version) || !ProtocolVersion.TLSv13.isEqualOrEarlierVersionOf(supportedVersionsExtensionServer)) {
                throw new TlsFatalAlert((short) 47);
            }
            version = supportedVersionsExtensionServer;
        }
        SecurityParameters securityParametersHandshake = this.v.getSecurityParametersHandshake();
        if (securityParametersHandshake.isRenegotiating()) {
            if (!version.equals(securityParametersHandshake.getNegotiatedVersion())) {
                throw new TlsFatalAlert((short) 47);
            }
        } else if (!ProtocolVersion.contains(this.v.getClientSupportedVersions(), version)) {
            throw new TlsFatalAlert((short) 70);
        } else {
            ProtocolVersion protocolVersion = ProtocolVersion.TLSv12;
            if (!version.isLaterVersionOf(protocolVersion)) {
                protocolVersion = version;
            }
            this.f14864a.s(protocolVersion);
            securityParametersHandshake.S = version;
        }
        TlsUtils.h0(this.v, this.u);
        if (ProtocolVersion.TLSv13.isEqualOrEarlierVersionOf(version)) {
            p0(serverHello, false);
            return;
        }
        int[] cipherSuites = this.y.getCipherSuites();
        this.y = null;
        this.f14870g = null;
        this.f14871h = -1;
        securityParametersHandshake.u = serverHello.getRandom();
        if (!this.v.getClientVersion().equals(version)) {
            TlsUtils.m(version, securityParametersHandshake.getServerRandom());
        }
        byte[] sessionID = serverHello.getSessionID();
        securityParametersHandshake.w = sessionID;
        this.u.notifySessionID(sessionID);
        this.f14875l = sessionID.length > 0 && (tlsSession = this.f14867d) != null && Arrays.areEqual(sessionID, tlsSession.getSessionID());
        int cipherSuite = serverHello.getCipherSuite();
        if (!TlsUtils.Z(cipherSuites, cipherSuite) || !TlsUtils.isValidVersionForCipherSuite(cipherSuite, securityParametersHandshake.getNegotiatedVersion())) {
            throw new TlsFatalAlert((short) 47);
        }
        TlsUtils.d0(securityParametersHandshake, cipherSuite);
        this.u.notifySelectedCipherSuite(cipherSuite);
        this.f14873j = extensions;
        if (extensions != null) {
            Enumeration keys = extensions.keys();
            while (keys.hasMoreElements()) {
                Integer num = (Integer) keys.nextElement();
                if (!num.equals(TlsProtocol.f14862s) && TlsUtils.getExtensionData(this.f14872i, num) == null) {
                    throw new TlsFatalAlert(AlertDescription.unsupported_extension);
                }
            }
        }
        byte[] extensionData = TlsUtils.getExtensionData(this.f14873j, TlsProtocol.f14862s);
        if (securityParametersHandshake.isRenegotiating()) {
            if (!securityParametersHandshake.isSecureRenegotiation()) {
                throw new TlsFatalAlert((short) 80);
            }
            if (extensionData == null) {
                throw new TlsFatalAlert((short) 40);
            }
            SecurityParameters securityParametersConnection = this.v.getSecurityParametersConnection();
            if (!Arrays.constantTimeAreEqual(extensionData, TlsProtocol.j(TlsUtils.p(securityParametersConnection.getLocalVerifyData(), securityParametersConnection.getPeerVerifyData())))) {
                throw new TlsFatalAlert((short) 40);
            }
        } else if (extensionData == null) {
            securityParametersHandshake.f14800c = false;
        } else {
            securityParametersHandshake.f14800c = true;
            if (!Arrays.constantTimeAreEqual(extensionData, TlsProtocol.j(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert((short) 40);
            }
        }
        this.u.notifySecureRenegotiation(securityParametersHandshake.isSecureRenegotiation());
        boolean hasExtendedMasterSecretExtension = TlsExtensionsUtils.hasExtendedMasterSecretExtension(this.f14873j);
        if (hasExtendedMasterSecretExtension) {
            if (version.isSSL() || (!this.f14875l && !this.u.shouldUseExtendedMasterSecret())) {
                throw new TlsFatalAlert((short) 40);
            }
        } else if (this.u.requiresExtendedMasterSecret() || (this.f14875l && !this.u.allowLegacyResumption())) {
            throw new TlsFatalAlert((short) 40);
        }
        securityParametersHandshake.C = hasExtendedMasterSecretExtension;
        securityParametersHandshake.F = TlsExtensionsUtils.getALPNExtensionServer(this.f14873j);
        securityParametersHandshake.G = true;
        Hashtable hashtable = this.f14872i;
        Hashtable hashtable2 = this.f14873j;
        if (this.f14875l) {
            if (securityParametersHandshake.getCipherSuite() != this.f14868e.getCipherSuite() || this.f14868e.getCompressionAlgorithm() != 0 || !version.equals(this.f14868e.getNegotiatedVersion())) {
                throw new TlsFatalAlert((short) 47);
            }
            hashtable2 = this.f14868e.readServerExtensions();
            hashtable = null;
        }
        if (hashtable2 != null && !hashtable2.isEmpty()) {
            boolean hasEncryptThenMACExtension = TlsExtensionsUtils.hasEncryptThenMACExtension(hashtable2);
            if (hasEncryptThenMACExtension && !TlsUtils.isBlockCipherSuite(securityParametersHandshake.getCipherSuite())) {
                throw new TlsFatalAlert((short) 47);
            }
            securityParametersHandshake.B = hasEncryptThenMACExtension;
            securityParametersHandshake.f14802e = E(hashtable, hashtable2, (short) 47);
            securityParametersHandshake.E = TlsExtensionsUtils.hasTruncatedHMacExtension(hashtable2);
            if (!this.f14875l) {
                if (TlsUtils.hasExpectedEmptyExtensionData(hashtable2, TlsExtensionsUtils.EXT_status_request_v2, (short) 47)) {
                    securityParametersHandshake.T = 2;
                } else if (TlsUtils.hasExpectedEmptyExtensionData(hashtable2, TlsExtensionsUtils.EXT_status_request, (short) 47)) {
                    securityParametersHandshake.T = 1;
                }
                this.f14878o = TlsUtils.hasExpectedEmptyExtensionData(hashtable2, TlsProtocol.f14863t, (short) 47);
            }
        }
        if (hashtable != null) {
            this.u.processServerExtensions(hashtable2);
        }
        a(securityParametersHandshake.getMaxFragmentLength());
        if (this.f14875l) {
            securityParametersHandshake.f14814q = this.f14869f;
            this.f14864a.q(TlsUtils.T(this.v));
            return;
        }
        y();
        this.f14867d = TlsUtils.importSession(securityParametersHandshake.getSessionID(), null);
    }

    protected void s0(ByteArrayInputStream byteArrayInputStream, boolean z) {
        if (z) {
            throw new TlsFatalAlert((short) 80);
        }
        if (this.f14876m) {
            throw new TlsFatalAlert((short) 10);
        }
        CertificateRequest parse = CertificateRequest.parse(this.v, byteArrayInputStream);
        TlsProtocol.b(byteArrayInputStream);
        if (!parse.hasCertificateRequestContext(TlsUtils.EMPTY_BYTES)) {
            throw new TlsFatalAlert((short) 47);
        }
        this.C = parse;
        TlsUtils.C(this.v.getSecurityParametersHandshake(), parse);
    }

    protected void t0(ByteArrayInputStream byteArrayInputStream) {
        byte[] readOpaque16 = TlsUtils.readOpaque16(byteArrayInputStream);
        TlsProtocol.b(byteArrayInputStream);
        Hashtable K = TlsProtocol.K(8, readOpaque16);
        this.f14873j = K;
        Enumeration keys = K.keys();
        while (keys.hasMoreElements()) {
            if (TlsUtils.getExtensionData(this.f14872i, (Integer) keys.nextElement()) == null) {
                throw new TlsFatalAlert(AlertDescription.unsupported_extension);
            }
        }
        SecurityParameters securityParametersHandshake = this.v.getSecurityParametersHandshake();
        ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
        securityParametersHandshake.F = TlsExtensionsUtils.getALPNExtensionServer(this.f14873j);
        securityParametersHandshake.G = true;
        Hashtable hashtable = this.f14872i;
        Hashtable hashtable2 = this.f14873j;
        if (this.f14875l) {
            if (securityParametersHandshake.getCipherSuite() != this.f14868e.getCipherSuite() || this.f14868e.getCompressionAlgorithm() != 0 || !negotiatedVersion.equals(this.f14868e.getNegotiatedVersion())) {
                throw new TlsFatalAlert((short) 47);
            }
            hashtable = null;
            hashtable2 = this.f14868e.readServerExtensions();
        }
        securityParametersHandshake.f14802e = E(hashtable, hashtable2, (short) 47);
        securityParametersHandshake.B = false;
        securityParametersHandshake.E = false;
        securityParametersHandshake.T = this.f14872i.containsKey(TlsExtensionsUtils.EXT_status_request) ? 1 : 0;
        this.f14878o = false;
        if (hashtable != null) {
            this.u.processServerExtensions(this.f14873j);
        }
        a(securityParametersHandshake.getMaxFragmentLength());
    }

    protected void u0(ByteArrayInputStream byteArrayInputStream) {
        if (!z()) {
            throw new TlsFatalAlert((short) 10);
        }
        TlsUtils.readUint32(byteArrayInputStream);
        TlsUtils.readUint32(byteArrayInputStream);
        TlsUtils.readOpaque8(byteArrayInputStream);
        TlsUtils.readOpaque16(byteArrayInputStream);
        TlsUtils.readOpaque16(byteArrayInputStream);
        TlsProtocol.b(byteArrayInputStream);
    }

    protected void v0(ByteArrayInputStream byteArrayInputStream) {
        if (this.f14876m) {
            throw new TlsFatalAlert((short) 10);
        }
        this.A = TlsUtils.n0(this.v, this.u, byteArrayInputStream);
        m0();
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0121  */
    @Override // org.bouncycastle.tls.TlsProtocol
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void w(short s2, HandshakeMessageInput handshakeMessageInput) {
        Vector clientSupplementalData;
        CertificateRequest certificateRequest;
        Certificate certificate;
        TlsCredentialedSigner tlsCredentialedSigner;
        TlsStreamSigner tlsStreamSigner;
        boolean isSSL;
        SecurityParameters securityParameters = this.v.getSecurityParameters();
        if (this.f14874k > 1 && TlsUtils.isTLSv13(securityParameters.getNegotiatedVersion())) {
            l0(s2, handshakeMessageInput);
        } else if (!A()) {
            throw new TlsFatalAlert((short) 80);
        } else {
            if (this.f14875l) {
                if (s2 != 20 || this.f14874k != 4) {
                    throw new TlsFatalAlert((short) 10);
                }
                D(handshakeMessageInput);
                handshakeMessageInput.updateHash(this.f14866c);
                this.f14874k = (short) 20;
                Y();
                a0();
                this.f14874k = (short) 18;
                h();
            } else if (s2 == 0) {
                TlsProtocol.b(handshakeMessageInput);
                if (z()) {
                    x();
                }
            } else if (s2 == 2) {
                if (this.f14874k != 1) {
                    throw new TlsFatalAlert((short) 10);
                }
                ServerHello A0 = A0(handshakeMessageInput);
                if (A0.isHelloRetryRequest()) {
                    o0(A0);
                    this.f14866c.notifyPRFDetermined();
                    TlsUtils.f(this.f14866c);
                    handshakeMessageInput.updateHash(this.f14866c);
                    this.f14874k = (short) 2;
                    B0();
                    this.f14866c.sealHashAlgorithms();
                    this.f14874k = (short) 3;
                    return;
                }
                r0(A0);
                this.f14866c.notifyPRFDetermined();
                handshakeMessageInput.updateHash(this.f14866c);
                this.f14874k = (short) 4;
                if (TlsUtils.isTLSv13(securityParameters.getNegotiatedVersion())) {
                    this.f14866c.sealHashAlgorithms();
                    q0(A0, false);
                }
            } else {
                short s3 = 19;
                TlsStreamSigner tlsStreamSigner2 = null;
                if (s2 != 4) {
                    if (s2 == 20) {
                        short s4 = this.f14874k;
                        if (s4 != 18) {
                            if (s4 != 19) {
                                throw new TlsFatalAlert((short) 10);
                            }
                        } else if (this.f14878o) {
                            throw new TlsFatalAlert((short) 10);
                        }
                        D(handshakeMessageInput);
                        this.f14874k = (short) 20;
                        h();
                        return;
                    }
                    s3 = 8;
                    if (s2 != 22) {
                        if (s2 == 23) {
                            if (this.f14874k != 4) {
                                throw new TlsFatalAlert((short) 10);
                            }
                            n0(TlsProtocol.M(handshakeMessageInput));
                            return;
                        }
                        switch (s2) {
                            case 11:
                                short s5 = this.f14874k;
                                if (s5 == 4) {
                                    n0(null);
                                } else if (s5 != 6) {
                                    throw new TlsFatalAlert((short) 10);
                                }
                                this.A = TlsUtils.o0(this.v, this.u, handshakeMessageInput);
                                this.f14874k = (short) 7;
                                return;
                            case 12:
                                short s6 = this.f14874k;
                                if (s6 == 4) {
                                    n0(null);
                                } else if (s6 != 6) {
                                    if (s6 != 7 && s6 != 8) {
                                        throw new TlsFatalAlert((short) 10);
                                    }
                                    m0();
                                    this.z.processServerKeyExchange(handshakeMessageInput);
                                    TlsProtocol.b(handshakeMessageInput);
                                    this.f14874k = (short) 10;
                                    return;
                                }
                                this.A = null;
                                m0();
                                this.z.processServerKeyExchange(handshakeMessageInput);
                                TlsProtocol.b(handshakeMessageInput);
                                this.f14874k = (short) 10;
                                return;
                            case 13:
                                short s7 = this.f14874k;
                                if (s7 == 7 || s7 == 8) {
                                    m0();
                                    this.z.skipServerKeyExchange();
                                } else if (s7 != 10) {
                                    throw new TlsFatalAlert((short) 10);
                                }
                                y0(handshakeMessageInput);
                                TlsUtils.C(securityParameters, this.C);
                                TlsUtils.A0(this.f14866c, securityParameters.getServerSigAlgs());
                                this.f14874k = (short) 11;
                                return;
                            case 14:
                                short s8 = this.f14874k;
                                if (s8 == 4) {
                                    n0(null);
                                } else if (s8 != 6) {
                                    if (s8 != 7 && s8 != 8) {
                                        if (s8 != 10 && s8 != 11) {
                                            throw new TlsFatalAlert((short) 10);
                                        }
                                        TlsProtocol.b(handshakeMessageInput);
                                        this.f14874k = (short) 12;
                                        clientSupplementalData = this.u.getClientSupplementalData();
                                        if (clientSupplementalData != null) {
                                            b0(clientSupplementalData);
                                            this.f14874k = (short) 14;
                                        }
                                        certificateRequest = this.C;
                                        if (certificateRequest == null) {
                                            this.z.skipClientCredentials();
                                            tlsCredentialedSigner = null;
                                        } else {
                                            TlsCredentials z = TlsUtils.z(this.A, certificateRequest);
                                            if (z == null) {
                                                this.z.skipClientCredentials();
                                                tlsCredentialedSigner = null;
                                                certificate = null;
                                                tlsStreamSigner = null;
                                            } else {
                                                this.z.processClientCredentials(z);
                                                certificate = z.getCertificate();
                                                if (z instanceof TlsCredentialedSigner) {
                                                    tlsCredentialedSigner = (TlsCredentialedSigner) z;
                                                    tlsStreamSigner = tlsCredentialedSigner.getStreamSigner();
                                                } else {
                                                    tlsCredentialedSigner = null;
                                                    tlsStreamSigner = null;
                                                }
                                            }
                                            X(certificate, null);
                                            this.f14874k = (short) 15;
                                            tlsStreamSigner2 = tlsStreamSigner;
                                        }
                                        TlsUtils.t0(this.v, this.f14866c, tlsStreamSigner2 != null);
                                        F0();
                                        this.f14874k = (short) 16;
                                        isSSL = TlsUtils.isSSL(this.v);
                                        if (isSSL) {
                                            TlsProtocol.k(this.v, this.z);
                                        }
                                        securityParameters.v = TlsUtils.H(this.f14866c);
                                        if (!isSSL) {
                                            TlsProtocol.k(this.v, this.z);
                                        }
                                        this.f14864a.q(TlsUtils.T(this.v));
                                        if (tlsCredentialedSigner != null) {
                                            C0(TlsUtils.E(this.v, tlsCredentialedSigner, tlsStreamSigner2, this.f14866c));
                                            this.f14874k = (short) 17;
                                        }
                                        this.f14866c = this.f14866c.stopTracking();
                                        Y();
                                        a0();
                                        this.f14874k = (short) 18;
                                        return;
                                    }
                                    m0();
                                    this.z.skipServerKeyExchange();
                                    TlsProtocol.b(handshakeMessageInput);
                                    this.f14874k = (short) 12;
                                    clientSupplementalData = this.u.getClientSupplementalData();
                                    if (clientSupplementalData != null) {
                                    }
                                    certificateRequest = this.C;
                                    if (certificateRequest == null) {
                                    }
                                    TlsUtils.t0(this.v, this.f14866c, tlsStreamSigner2 != null);
                                    F0();
                                    this.f14874k = (short) 16;
                                    isSSL = TlsUtils.isSSL(this.v);
                                    if (isSSL) {
                                    }
                                    securityParameters.v = TlsUtils.H(this.f14866c);
                                    if (!isSSL) {
                                    }
                                    this.f14864a.q(TlsUtils.T(this.v));
                                    if (tlsCredentialedSigner != null) {
                                    }
                                    this.f14866c = this.f14866c.stopTracking();
                                    Y();
                                    a0();
                                    this.f14874k = (short) 18;
                                    return;
                                }
                                this.A = null;
                                m0();
                                this.z.skipServerKeyExchange();
                                TlsProtocol.b(handshakeMessageInput);
                                this.f14874k = (short) 12;
                                clientSupplementalData = this.u.getClientSupplementalData();
                                if (clientSupplementalData != null) {
                                }
                                certificateRequest = this.C;
                                if (certificateRequest == null) {
                                }
                                TlsUtils.t0(this.v, this.f14866c, tlsStreamSigner2 != null);
                                F0();
                                this.f14874k = (short) 16;
                                isSSL = TlsUtils.isSSL(this.v);
                                if (isSSL) {
                                }
                                securityParameters.v = TlsUtils.H(this.f14866c);
                                if (!isSSL) {
                                }
                                this.f14864a.q(TlsUtils.T(this.v));
                                if (tlsCredentialedSigner != null) {
                                }
                                this.f14866c = this.f14866c.stopTracking();
                                Y();
                                a0();
                                this.f14874k = (short) 18;
                                return;
                            default:
                                throw new TlsFatalAlert((short) 10);
                        }
                    } else if (this.f14874k != 7) {
                        throw new TlsFatalAlert((short) 10);
                    } else {
                        if (securityParameters.getStatusRequestVersion() < 1) {
                            throw new TlsFatalAlert((short) 10);
                        }
                        this.B = CertificateStatus.parse(this.v, handshakeMessageInput);
                        TlsProtocol.b(handshakeMessageInput);
                    }
                } else if (this.f14874k != 18) {
                    throw new TlsFatalAlert((short) 10);
                } else {
                    if (!this.f14878o) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    securityParameters.w = TlsUtils.EMPTY_BYTES;
                    y();
                    this.f14867d = TlsUtils.importSession(securityParameters.getSessionID(), null);
                    z0(handshakeMessageInput);
                }
                this.f14874k = s3;
            }
        }
    }

    protected void w0(ByteArrayInputStream byteArrayInputStream) {
        Certificate peerCertificate = this.v.getSecurityParametersHandshake().getPeerCertificate();
        if (peerCertificate == null || peerCertificate.isEmpty()) {
            throw new TlsFatalAlert((short) 80);
        }
        DigitallySigned parse = DigitallySigned.parse(this.v, byteArrayInputStream);
        TlsProtocol.b(byteArrayInputStream);
        TlsUtils.J0(this.v, parse, this.f14866c);
    }

    protected void x0(ByteArrayInputStream byteArrayInputStream) {
        C(byteArrayInputStream);
    }

    protected void y0(ByteArrayInputStream byteArrayInputStream) {
        if (this.A == null) {
            throw new TlsFatalAlert((short) 40);
        }
        CertificateRequest parse = CertificateRequest.parse(this.v, byteArrayInputStream);
        TlsProtocol.b(byteArrayInputStream);
        this.C = TlsUtils.G0(parse, this.z);
    }

    protected void z0(ByteArrayInputStream byteArrayInputStream) {
        NewSessionTicket parse = NewSessionTicket.parse(byteArrayInputStream);
        TlsProtocol.b(byteArrayInputStream);
        this.u.notifyNewSessionTicket(parse);
    }
}
