package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.tls.Certificate;
import org.bouncycastle.tls.OfferedPsks;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsCrypto;
import org.bouncycastle.tls.crypto.TlsDHConfig;
import org.bouncycastle.tls.crypto.TlsECConfig;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class TlsServerProtocol extends TlsProtocol {
    protected TlsServer u;
    TlsServerContextImpl v;
    protected int[] w;
    protected TlsKeyExchange x;
    protected CertificateRequest y;

    public TlsServerProtocol() {
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
    }

    public TlsServerProtocol(InputStream inputStream, OutputStream outputStream) {
        super(inputStream, outputStream);
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
    }

    protected void A0(CertificateRequest certificateRequest) {
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 13);
        certificateRequest.encode(this.v, handshakeMessageOutput);
        handshakeMessageOutput.c(this);
    }

    protected void B0(CertificateStatus certificateStatus) {
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 22);
        certificateStatus.encode(handshakeMessageOutput);
        handshakeMessageOutput.c(this);
    }

    protected void C0(NewSessionTicket newSessionTicket) {
        if (newSessionTicket == null) {
            throw new TlsFatalAlert((short) 80);
        }
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 4);
        newSessionTicket.encode(handshakeMessageOutput);
        handshakeMessageOutput.c(this);
    }

    protected void D0() {
        HandshakeMessageOutput.d(this, (short) 14, TlsUtils.EMPTY_BYTES);
    }

    protected void E0(ServerHello serverHello) {
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 2);
        serverHello.encode(this.v, handshakeMessageOutput);
        handshakeMessageOutput.c(this);
    }

    protected void F0(byte[] bArr) {
        HandshakeMessageOutput.d(this, (short) 12, bArr);
    }

    protected void G0() {
        if (this.y != null) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    protected void H0() {
        if (l0()) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    public void accept(TlsServer tlsServer) {
        if (tlsServer == null) {
            throw new IllegalArgumentException("'tlsServer' cannot be null");
        }
        if (this.u != null) {
            throw new IllegalStateException("'accept' can only be called once");
        }
        this.u = tlsServer;
        TlsServerContextImpl tlsServerContextImpl = new TlsServerContextImpl(tlsServer.getCrypto());
        this.v = tlsServerContextImpl;
        tlsServer.init(tlsServerContextImpl);
        tlsServer.notifyCloseHandle(this);
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
    }

    protected boolean l0() {
        Certificate peerCertificate;
        if (this.y == null || (peerCertificate = this.v.getSecurityParametersHandshake().getPeerCertificate()) == null || peerCertificate.isEmpty()) {
            return false;
        }
        TlsKeyExchange tlsKeyExchange = this.x;
        return tlsKeyExchange == null || tlsKeyExchange.requiresCertificateVerify();
    }

    @Override // org.bouncycastle.tls.TlsProtocol
    protected TlsContext m() {
        return this.v;
    }

    protected ServerHello m0(ClientHello clientHello) {
        if (this.f14871h >= 0) {
            SecurityParameters securityParametersHandshake = this.v.getSecurityParametersHandshake();
            ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
            Hashtable hashtable = new Hashtable();
            TlsExtensionsUtils.addSupportedVersionsExtensionServer(hashtable, negotiatedVersion);
            int i2 = this.f14871h;
            if (i2 >= 0) {
                TlsExtensionsUtils.addKeyShareHelloRetryRequest(hashtable, i2);
            }
            byte[] bArr = this.f14870g;
            if (bArr != null) {
                TlsExtensionsUtils.addCookieExtension(hashtable, bArr);
            }
            TlsUtils.n(hashtable, 6, (short) 80);
            return new ServerHello(clientHello.getSessionID(), securityParametersHandshake.getCipherSuite(), hashtable);
        }
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.TlsProtocol
    AbstractTlsContext n() {
        return this.v;
    }

    protected ServerHello n0(ClientHello clientHello, HandshakeMessageInput handshakeMessageInput, boolean z) {
        KeyShareEntry keyShareEntry;
        TlsAgreement createDH;
        SecurityParameters securityParametersHandshake = this.v.getSecurityParametersHandshake();
        if (securityParametersHandshake.isRenegotiating()) {
            throw new TlsFatalAlert((short) 80);
        }
        byte[] sessionID = clientHello.getSessionID();
        Hashtable extensions = clientHello.getExtensions();
        if (extensions != null) {
            ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
            TlsCrypto crypto = this.v.getCrypto();
            OfferedPsks.SelectedConfig x0 = TlsUtils.x0(this.v, this.u, extensions, handshakeMessageInput, this.f14866c, z);
            Vector keyShareClientHello = TlsExtensionsUtils.getKeyShareClientHello(extensions);
            TlsSecret tlsSecret = null;
            if (!z) {
                this.f14872i = extensions;
                securityParametersHandshake.f14800c = false;
                TlsExtensionsUtils.getPaddingExtension(extensions);
                securityParametersHandshake.I = TlsExtensionsUtils.getServerNameExtensionClient(extensions);
                TlsUtils.A(securityParametersHandshake, extensions);
                if (x0 == null && securityParametersHandshake.getClientSigAlgs() == null) {
                    throw new TlsFatalAlert(AlertDescription.missing_extension);
                }
                this.u.processClientExtensions(extensions);
                TlsSession importSession = TlsUtils.importSession(TlsUtils.EMPTY_BYTES, null);
                this.f14867d = importSession;
                this.f14868e = null;
                this.f14869f = null;
                securityParametersHandshake.w = importSession.getSessionID();
                this.u.notifySession(this.f14867d);
                TlsUtils.i0(this.v);
                securityParametersHandshake.u = TlsProtocol.i(false, this.v);
                if (!negotiatedVersion.equals(ProtocolVersion.getLatestTLS(this.u.getProtocolVersions()))) {
                    TlsUtils.M0(negotiatedVersion, securityParametersHandshake.getServerRandom());
                }
                int selectedCipherSuite = this.u.getSelectedCipherSuite();
                if (!TlsUtils.Z(this.w, selectedCipherSuite) || !TlsUtils.isValidVersionForCipherSuite(selectedCipherSuite, negotiatedVersion)) {
                    throw new TlsFatalAlert((short) 80);
                }
                TlsUtils.d0(securityParametersHandshake, selectedCipherSuite);
                int[] clientSupportedGroups = securityParametersHandshake.getClientSupportedGroups();
                int[] serverSupportedGroups = securityParametersHandshake.getServerSupportedGroups();
                KeyShareEntry v0 = TlsUtils.v0(crypto, negotiatedVersion, keyShareClientHello, clientSupportedGroups, serverSupportedGroups);
                if (v0 == null) {
                    int w0 = TlsUtils.w0(crypto, negotiatedVersion, clientSupportedGroups, serverSupportedGroups);
                    this.f14871h = w0;
                    if (w0 >= 0) {
                        this.f14870g = this.v.getNonceGenerator().generateNonce(16);
                        return m0(clientHello);
                    }
                    throw new TlsFatalAlert((short) 40);
                }
                v0.getNamedGroup();
                int i2 = serverSupportedGroups[0];
                keyShareEntry = v0;
            } else if (this.f14871h < 0) {
                throw new TlsFatalAlert((short) 80);
            } else {
                if (x0 == null) {
                    if (securityParametersHandshake.getClientSigAlgs() == null) {
                        throw new TlsFatalAlert(AlertDescription.missing_extension);
                    }
                } else if (x0.f14778b.getPRFAlgorithm() != securityParametersHandshake.getPRFAlgorithm()) {
                    throw new TlsFatalAlert((short) 47);
                }
                if (!Arrays.areEqual(this.f14870g, TlsExtensionsUtils.getCookieExtension(extensions))) {
                    throw new TlsFatalAlert((short) 47);
                }
                this.f14870g = null;
                keyShareEntry = TlsUtils.u0(keyShareClientHello, this.f14871h);
                if (keyShareEntry == null) {
                    throw new TlsFatalAlert((short) 47);
                }
            }
            Hashtable hashtable = new Hashtable();
            Hashtable ensureExtensionsInitialised = TlsExtensionsUtils.ensureExtensionsInitialised(this.u.getServerExtensions());
            this.u.getServerExtensionsForConnection(ensureExtensionsInitialised);
            ProtocolVersion protocolVersion = ProtocolVersion.TLSv12;
            TlsExtensionsUtils.addSupportedVersionsExtensionServer(hashtable, negotiatedVersion);
            securityParametersHandshake.C = true;
            securityParametersHandshake.F = TlsExtensionsUtils.getALPNExtensionServer(ensureExtensionsInitialised);
            securityParametersHandshake.G = true;
            if (!ensureExtensionsInitialised.isEmpty()) {
                securityParametersHandshake.f14802e = E(extensions, ensureExtensionsInitialised, (short) 80);
            }
            securityParametersHandshake.B = false;
            securityParametersHandshake.E = false;
            securityParametersHandshake.T = extensions.containsKey(TlsExtensionsUtils.EXT_status_request) ? 1 : 0;
            this.f14878o = false;
            if (x0 != null) {
                tlsSecret = x0.f14779c;
                this.f14876m = true;
                TlsExtensionsUtils.addPreSharedKeyServerHello(hashtable, x0.f14777a);
            }
            int namedGroup = keyShareEntry.getNamedGroup();
            if (NamedGroup.refersToASpecificCurve(namedGroup)) {
                createDH = crypto.createECDomain(new TlsECConfig(namedGroup)).createECDH();
            } else if (!NamedGroup.refersToASpecificFiniteField(namedGroup)) {
                throw new TlsFatalAlert((short) 80);
            } else {
                createDH = crypto.createDHDomain(new TlsDHConfig(namedGroup, true)).createDH();
            }
            TlsExtensionsUtils.addKeyShareServerHello(hashtable, new KeyShareEntry(namedGroup, createDH.generateEphemeral()));
            createDH.receivePeerValue(keyShareEntry.getKeyExchange());
            TlsUtils.x(this.v, tlsSecret, createDH.calculateSecret());
            this.f14873j = ensureExtensionsInitialised;
            a(securityParametersHandshake.getMaxFragmentLength());
            TlsUtils.n(hashtable, 2, (short) 80);
            return new ServerHello(protocolVersion, securityParametersHandshake.getServerRandom(), sessionID, securityParametersHandshake.getCipherSuite(), hashtable);
        }
        throw new TlsFatalAlert(AlertDescription.missing_extension);
    }

    @Override // org.bouncycastle.tls.TlsProtocol
    protected TlsPeer o() {
        return this.u;
    }

    /* JADX WARN: Removed duplicated region for block: B:128:0x02f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected ServerHello o0(ClientHello clientHello, HandshakeMessageInput handshakeMessageInput) {
        ProtocolVersion latestTLS;
        ProtocolVersion serverVersion;
        ProtocolVersion version = clientHello.getVersion();
        if (version.isTLS()) {
            this.w = clientHello.getCipherSuites();
            SecurityParameters securityParametersHandshake = this.v.getSecurityParametersHandshake();
            this.v.h(TlsExtensionsUtils.getSupportedVersionsExtensionClient(clientHello.getExtensions()));
            if (this.v.getClientSupportedVersions() == null) {
                latestTLS = ProtocolVersion.TLSv12;
                if (!version.isLaterVersionOf(latestTLS)) {
                    latestTLS = version;
                }
                this.v.h(latestTLS.downTo(ProtocolVersion.SSLv3));
            } else {
                latestTLS = ProtocolVersion.getLatestTLS(this.v.getClientSupportedVersions());
            }
            this.f14864a.s(latestTLS);
            if (ProtocolVersion.f14787f.isEqualOrEarlierVersionOf(latestTLS)) {
                if (!securityParametersHandshake.isRenegotiating()) {
                    this.v.i(latestTLS);
                } else if (!latestTLS.equals(this.v.getClientVersion()) && !latestTLS.equals(this.v.getServerVersion())) {
                    throw new TlsFatalAlert((short) 47);
                }
                this.u.notifyClientVersion(this.v.getClientVersion());
                securityParametersHandshake.f14817t = clientHello.getRandom();
                this.u.notifyFallback(Arrays.contains(this.w, (int) CipherSuite.TLS_FALLBACK_SCSV));
                this.u.notifyOfferedCipherSuites(this.w);
                if (securityParametersHandshake.isRenegotiating()) {
                    serverVersion = this.v.getServerVersion();
                } else {
                    serverVersion = this.u.getServerVersion();
                    if (!ProtocolVersion.contains(this.v.getClientSupportedVersions(), serverVersion)) {
                        throw new TlsFatalAlert((short) 80);
                    }
                    securityParametersHandshake.S = serverVersion;
                }
                ProtocolVersion protocolVersion = serverVersion;
                securityParametersHandshake.L = TlsExtensionsUtils.getSupportedGroupsExtension(clientHello.getExtensions());
                securityParametersHandshake.O = this.u.getSupportedGroups();
                boolean z = false;
                if (ProtocolVersion.TLSv13.isEqualOrEarlierVersionOf(protocolVersion)) {
                    this.f14864a.p(true);
                    this.f14864a.s(ProtocolVersion.TLSv12);
                    return n0(clientHello, handshakeMessageInput, false);
                }
                this.f14864a.s(protocolVersion);
                Hashtable extensions = clientHello.getExtensions();
                this.f14872i = extensions;
                Integer num = TlsProtocol.f14862s;
                byte[] extensionData = TlsUtils.getExtensionData(extensions, num);
                if (!securityParametersHandshake.isRenegotiating()) {
                    if (Arrays.contains(this.w, 255)) {
                        securityParametersHandshake.f14800c = true;
                    }
                    if (extensionData != null) {
                        securityParametersHandshake.f14800c = true;
                        if (!Arrays.constantTimeAreEqual(extensionData, TlsProtocol.j(TlsUtils.EMPTY_BYTES))) {
                            throw new TlsFatalAlert((short) 40);
                        }
                    }
                } else if (!securityParametersHandshake.isSecureRenegotiation()) {
                    throw new TlsFatalAlert((short) 80);
                } else {
                    if (Arrays.contains(this.w, 255)) {
                        throw new TlsFatalAlert((short) 40);
                    }
                    if (extensionData == null) {
                        throw new TlsFatalAlert((short) 40);
                    }
                    if (!Arrays.constantTimeAreEqual(extensionData, TlsProtocol.j(this.v.getSecurityParametersConnection().getPeerVerifyData()))) {
                        throw new TlsFatalAlert((short) 40);
                    }
                }
                this.u.notifySecureRenegotiation(securityParametersHandshake.isSecureRenegotiation());
                boolean hasExtendedMasterSecretExtension = TlsExtensionsUtils.hasExtendedMasterSecretExtension(this.f14872i);
                Hashtable hashtable = this.f14872i;
                if (hashtable != null) {
                    TlsExtensionsUtils.getPaddingExtension(hashtable);
                    securityParametersHandshake.I = TlsExtensionsUtils.getServerNameExtensionClient(this.f14872i);
                    if (TlsUtils.isSignatureAlgorithmsExtensionAllowed(latestTLS)) {
                        TlsUtils.A(securityParametersHandshake, this.f14872i);
                    }
                    securityParametersHandshake.L = TlsExtensionsUtils.getSupportedGroupsExtension(this.f14872i);
                    this.u.processClientExtensions(this.f14872i);
                }
                boolean l2 = l(this.u.getSessionToResume(clientHello.getSessionID()));
                this.f14875l = l2;
                if (!l2) {
                    byte[] newSessionID = this.u.getNewSessionID();
                    if (newSessionID == null) {
                        newSessionID = TlsUtils.EMPTY_BYTES;
                    }
                    this.f14867d = TlsUtils.importSession(newSessionID, null);
                    this.f14868e = null;
                    this.f14869f = null;
                }
                securityParametersHandshake.w = this.f14867d.getSessionID();
                this.u.notifySession(this.f14867d);
                TlsUtils.i0(this.v);
                securityParametersHandshake.u = TlsProtocol.i(this.u.shouldUseGMTUnixTime(), this.v);
                if (!protocolVersion.equals(ProtocolVersion.getLatestTLS(this.u.getProtocolVersions()))) {
                    TlsUtils.M0(protocolVersion, securityParametersHandshake.getServerRandom());
                }
                int cipherSuite = this.f14875l ? this.f14868e.getCipherSuite() : this.u.getSelectedCipherSuite();
                if (TlsUtils.Z(this.w, cipherSuite) && TlsUtils.isValidVersionForCipherSuite(cipherSuite, protocolVersion)) {
                    TlsUtils.d0(securityParametersHandshake, cipherSuite);
                    this.v.j(version);
                    Hashtable ensureExtensionsInitialised = TlsExtensionsUtils.ensureExtensionsInitialised(this.f14875l ? this.f14868e.readServerExtensions() : this.u.getServerExtensions());
                    this.f14873j = ensureExtensionsInitialised;
                    this.u.getServerExtensionsForConnection(ensureExtensionsInitialised);
                    if (securityParametersHandshake.isRenegotiating()) {
                        if (!securityParametersHandshake.isSecureRenegotiation()) {
                            throw new TlsFatalAlert((short) 80);
                        }
                        SecurityParameters securityParametersConnection = this.v.getSecurityParametersConnection();
                        this.f14873j.put(num, TlsProtocol.j(TlsUtils.p(securityParametersConnection.getPeerVerifyData(), securityParametersConnection.getLocalVerifyData())));
                    } else if (securityParametersHandshake.isSecureRenegotiation()) {
                        if (TlsUtils.getExtensionData(this.f14873j, num) == null) {
                            this.f14873j.put(num, TlsProtocol.j(TlsUtils.EMPTY_BYTES));
                        }
                    }
                    if (!this.f14875l) {
                        if (hasExtendedMasterSecretExtension && !protocolVersion.isSSL() && this.u.shouldUseExtendedMasterSecret()) {
                            z = true;
                        }
                        securityParametersHandshake.C = z;
                        if (!securityParametersHandshake.isExtendedMasterSecret()) {
                            if (this.u.requiresExtendedMasterSecret()) {
                                throw new TlsFatalAlert((short) 40);
                            }
                            securityParametersHandshake.F = TlsExtensionsUtils.getALPNExtensionServer(this.f14873j);
                            securityParametersHandshake.G = true;
                            if (!this.f14873j.isEmpty()) {
                                securityParametersHandshake.B = TlsExtensionsUtils.hasEncryptThenMACExtension(this.f14873j);
                                securityParametersHandshake.f14802e = E(this.f14872i, this.f14873j, (short) 80);
                                securityParametersHandshake.E = TlsExtensionsUtils.hasTruncatedHMacExtension(this.f14873j);
                                if (!this.f14875l) {
                                    if (TlsUtils.hasExpectedEmptyExtensionData(this.f14873j, TlsExtensionsUtils.EXT_status_request_v2, (short) 80)) {
                                        securityParametersHandshake.T = 2;
                                    } else if (TlsUtils.hasExpectedEmptyExtensionData(this.f14873j, TlsExtensionsUtils.EXT_status_request, (short) 80)) {
                                        securityParametersHandshake.T = 1;
                                    }
                                    this.f14878o = TlsUtils.hasExpectedEmptyExtensionData(this.f14873j, TlsProtocol.f14863t, (short) 80);
                                }
                            }
                            a(securityParametersHandshake.getMaxFragmentLength());
                            return new ServerHello(protocolVersion, securityParametersHandshake.getServerRandom(), this.f14867d.getSessionID(), securityParametersHandshake.getCipherSuite(), this.f14873j);
                        }
                    } else if (!this.f14868e.isExtendedMasterSecret()) {
                        throw new TlsFatalAlert((short) 80);
                    } else {
                        if (!hasExtendedMasterSecretExtension) {
                            throw new TlsFatalAlert((short) 40);
                        }
                        securityParametersHandshake.C = true;
                    }
                    TlsExtensionsUtils.addExtendedMasterSecretExtension(this.f14873j);
                    securityParametersHandshake.F = TlsExtensionsUtils.getALPNExtensionServer(this.f14873j);
                    securityParametersHandshake.G = true;
                    if (!this.f14873j.isEmpty()) {
                    }
                    a(securityParametersHandshake.getMaxFragmentLength());
                    return new ServerHello(protocolVersion, securityParametersHandshake.getServerRandom(), this.f14867d.getSessionID(), securityParametersHandshake.getCipherSuite(), this.f14873j);
                }
                throw new TlsFatalAlert((short) 80);
            }
            throw new TlsFatalAlert((short) 70);
        }
        throw new TlsFatalAlert((short) 47);
    }

    protected void p0(short s2, HandshakeMessageInput handshakeMessageInput) {
        if (!B()) {
            throw new TlsFatalAlert((short) 80);
        }
        if (this.f14875l) {
            throw new TlsFatalAlert((short) 80);
        }
        if (s2 == 1) {
            short s3 = this.f14874k;
            if (s3 == 0) {
                throw new TlsFatalAlert((short) 80);
            }
            if (s3 != 2) {
                throw new TlsFatalAlert((short) 10);
            }
            ClientHello w0 = w0(handshakeMessageInput);
            this.f14874k = (short) 3;
            ServerHello n0 = n0(w0, handshakeMessageInput, true);
            E0(n0);
            this.f14874k = (short) 4;
            z0(n0, true);
        } else if (s2 == 11) {
            if (this.f14874k != 20) {
                throw new TlsFatalAlert((short) 10);
            }
            r0(handshakeMessageInput);
            this.f14874k = (short) 15;
        } else if (s2 == 15) {
            if (this.f14874k != 15) {
                throw new TlsFatalAlert((short) 10);
            }
            s0(handshakeMessageInput);
            handshakeMessageInput.updateHash(this.f14866c);
            this.f14874k = (short) 17;
        } else if (s2 != 20) {
            if (s2 != 24) {
                throw new TlsFatalAlert((short) 10);
            }
            N(handshakeMessageInput);
        } else {
            short s4 = this.f14874k;
            if (s4 != 15) {
                if (s4 != 17) {
                    if (s4 != 20) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    G0();
                }
                t0(handshakeMessageInput);
                this.f14874k = (short) 18;
                this.f14864a.p(false);
                this.f14864a.c(false);
                h();
            }
            H0();
            t0(handshakeMessageInput);
            this.f14874k = (short) 18;
            this.f14864a.p(false);
            this.f14864a.c(false);
            h();
        }
    }

    protected void q0(Certificate certificate) {
        if (this.y == null) {
            throw new TlsFatalAlert((short) 80);
        }
        TlsUtils.k0(this.v, certificate, this.x, this.u);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0018, code lost:
        if (r0 != 14) goto L15;
     */
    @Override // org.bouncycastle.tls.TlsProtocol
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void r(short s2) {
        if (41 == s2 && this.y != null && TlsUtils.isSSL(this.v)) {
            short s3 = this.f14874k;
            if (s3 == 12) {
                this.u.processClientSupplementalData(null);
            }
            q0(Certificate.EMPTY_CHAIN);
            this.f14874k = (short) 15;
            return;
        }
        super.r(s2);
    }

    protected void r0(ByteArrayInputStream byteArrayInputStream) {
        if (this.y == null) {
            throw new TlsFatalAlert((short) 10);
        }
        Certificate parse = Certificate.parse(new Certificate.ParseOptions().setMaxChainLength(this.u.getMaxCertificateChainLength()), this.v, byteArrayInputStream, null);
        TlsProtocol.b(byteArrayInputStream);
        q0(parse);
    }

    protected void s0(ByteArrayInputStream byteArrayInputStream) {
        Certificate peerCertificate = this.v.getSecurityParametersHandshake().getPeerCertificate();
        if (peerCertificate == null || peerCertificate.isEmpty()) {
            throw new TlsFatalAlert((short) 80);
        }
        DigitallySigned parse = DigitallySigned.parse(this.v, byteArrayInputStream);
        TlsProtocol.b(byteArrayInputStream);
        TlsUtils.I0(this.v, this.y, parse, this.f14866c);
    }

    protected void t0(ByteArrayInputStream byteArrayInputStream) {
        C(byteArrayInputStream);
    }

    protected void u0(ByteArrayInputStream byteArrayInputStream) {
        if (this.y == null) {
            throw new TlsFatalAlert((short) 10);
        }
        Certificate parse = Certificate.parse(new Certificate.ParseOptions().setMaxChainLength(this.u.getMaxCertificateChainLength()), this.v, byteArrayInputStream, null);
        TlsProtocol.b(byteArrayInputStream);
        q0(parse);
    }

    protected void v0(ByteArrayInputStream byteArrayInputStream) {
        DigitallySigned parse = DigitallySigned.parse(this.v, byteArrayInputStream);
        TlsProtocol.b(byteArrayInputStream);
        TlsUtils.K0(this.v, this.y, parse, this.f14866c);
        this.f14866c = this.f14866c.stopTracking();
    }

    @Override // org.bouncycastle.tls.TlsProtocol
    protected void w(short s2, HandshakeMessageInput handshakeMessageInput) {
        CertificateStatus certificateStatus;
        SecurityParameters securityParameters = this.v.getSecurityParameters();
        if (this.f14874k > 1 && TlsUtils.isTLSv13(securityParameters.getNegotiatedVersion())) {
            p0(s2, handshakeMessageInput);
        } else if (!A()) {
            throw new TlsFatalAlert((short) 80);
        } else {
            if (this.f14875l) {
                if (s2 != 20 || this.f14874k != 20) {
                    throw new TlsFatalAlert((short) 10);
                }
                D(handshakeMessageInput);
                this.f14874k = (short) 18;
                h();
                return;
            }
            Certificate certificate = null;
            if (s2 != 1) {
                if (s2 == 11) {
                    short s3 = this.f14874k;
                    if (s3 == 12) {
                        this.u.processClientSupplementalData(null);
                    } else if (s3 != 14) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    u0(handshakeMessageInput);
                    this.f14874k = (short) 15;
                    return;
                } else if (s2 == 20) {
                    short s4 = this.f14874k;
                    if (s4 != 16) {
                        if (s4 != 17) {
                            throw new TlsFatalAlert((short) 10);
                        }
                    } else if (l0()) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    D(handshakeMessageInput);
                    handshakeMessageInput.updateHash(this.f14866c);
                    this.f14874k = (short) 18;
                    if (this.f14878o) {
                        C0(this.u.getNewSessionTicket());
                        this.f14874k = (short) 19;
                    }
                    Y();
                    a0();
                    this.f14874k = (short) 20;
                    h();
                    return;
                } else if (s2 == 23) {
                    if (this.f14874k != 12) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    this.u.processClientSupplementalData(TlsProtocol.M(handshakeMessageInput));
                    this.f14874k = (short) 14;
                    return;
                } else if (s2 == 15) {
                    if (this.f14874k != 16) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    if (!l0()) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    v0(handshakeMessageInput);
                    handshakeMessageInput.updateHash(this.f14866c);
                    this.f14874k = (short) 17;
                    return;
                } else if (s2 != 16) {
                    throw new TlsFatalAlert((short) 10);
                } else {
                    short s5 = this.f14874k;
                    if (s5 == 12) {
                        this.u.processClientSupplementalData(null);
                    } else if (s5 != 14) {
                        if (s5 != 15) {
                            throw new TlsFatalAlert((short) 10);
                        }
                        x0(handshakeMessageInput);
                        this.f14874k = (short) 16;
                        return;
                    }
                    if (this.y == null) {
                        this.x.skipClientCredentials();
                    } else if (TlsUtils.isTLSv12(this.v)) {
                        throw new TlsFatalAlert((short) 10);
                    } else {
                        if (TlsUtils.isSSL(this.v)) {
                            throw new TlsFatalAlert((short) 10);
                        }
                        q0(Certificate.EMPTY_CHAIN);
                    }
                    x0(handshakeMessageInput);
                    this.f14874k = (short) 16;
                    return;
                }
            }
            if (z()) {
                if (!x()) {
                    return;
                }
                this.f14874k = (short) 0;
            }
            short s6 = this.f14874k;
            if (s6 != 0) {
                if (s6 == 21) {
                    throw new TlsFatalAlert((short) 80);
                }
                throw new TlsFatalAlert((short) 10);
            }
            ClientHello w0 = w0(handshakeMessageInput);
            this.f14874k = (short) 1;
            ServerHello o0 = o0(w0, handshakeMessageInput);
            this.f14866c.notifyPRFDetermined();
            if (TlsUtils.isTLSv13(securityParameters.getNegotiatedVersion())) {
                this.f14866c.sealHashAlgorithms();
                if (o0.isHelloRetryRequest()) {
                    TlsUtils.f(this.f14866c);
                    E0(o0);
                    this.f14874k = (short) 2;
                    Z();
                    return;
                }
                E0(o0);
                this.f14874k = (short) 4;
                Z();
                z0(o0, false);
                return;
            }
            handshakeMessageInput.updateHash(this.f14866c);
            E0(o0);
            this.f14874k = (short) 4;
            if (this.f14875l) {
                securityParameters.f14814q = this.f14869f;
                this.f14864a.q(TlsUtils.T(this.v));
                Y();
                a0();
                this.f14874k = (short) 20;
                return;
            }
            Vector serverSupplementalData = this.u.getServerSupplementalData();
            if (serverSupplementalData != null) {
                b0(serverSupplementalData);
                this.f14874k = (short) 6;
            }
            this.x = TlsUtils.V(this.v, this.u);
            TlsCredentials B = TlsUtils.B(this.u);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            TlsKeyExchange tlsKeyExchange = this.x;
            if (B == null) {
                tlsKeyExchange.skipServerCredentials();
            } else {
                tlsKeyExchange.processServerCredentials(B);
                certificate = B.getCertificate();
                X(certificate, byteArrayOutputStream);
                this.f14874k = (short) 7;
            }
            securityParameters.z = byteArrayOutputStream.toByteArray();
            if (certificate == null || certificate.isEmpty()) {
                securityParameters.T = 0;
            }
            if (securityParameters.getStatusRequestVersion() > 0 && (certificateStatus = this.u.getCertificateStatus()) != null) {
                B0(certificateStatus);
                this.f14874k = (short) 8;
            }
            byte[] generateServerKeyExchange = this.x.generateServerKeyExchange();
            if (generateServerKeyExchange != null) {
                F0(generateServerKeyExchange);
                this.f14874k = (short) 10;
            }
            if (B != null) {
                CertificateRequest certificateRequest = this.u.getCertificateRequest();
                this.y = certificateRequest;
                if (certificateRequest != null) {
                    if (TlsUtils.isTLSv12(this.v) != (this.y.getSupportedSignatureAlgorithms() != null)) {
                        throw new TlsFatalAlert((short) 80);
                    }
                    CertificateRequest G0 = TlsUtils.G0(this.y, this.x);
                    this.y = G0;
                    TlsUtils.C(securityParameters, G0);
                    TlsUtils.A0(this.f14866c, securityParameters.getServerSigAlgs());
                    A0(this.y);
                    this.f14874k = (short) 11;
                } else if (!this.x.requiresCertificateVerify()) {
                    throw new TlsFatalAlert((short) 80);
                }
            }
            D0();
            this.f14874k = (short) 12;
            TlsUtils.t0(this.v, this.f14866c, false);
        }
    }

    protected ClientHello w0(ByteArrayInputStream byteArrayInputStream) {
        return ClientHello.parse(byteArrayInputStream, null);
    }

    protected void x0(ByteArrayInputStream byteArrayInputStream) {
        this.x.processClientKeyExchange(byteArrayInputStream);
        TlsProtocol.b(byteArrayInputStream);
        boolean isSSL = TlsUtils.isSSL(this.v);
        if (isSSL) {
            TlsProtocol.k(this.v, this.x);
        }
        this.v.getSecurityParametersHandshake().v = TlsUtils.H(this.f14866c);
        if (!isSSL) {
            TlsProtocol.k(this.v, this.x);
        }
        this.f14864a.q(TlsUtils.T(this.v));
        if (l0()) {
            return;
        }
        this.f14866c = this.f14866c.stopTracking();
    }

    protected void y0(Hashtable hashtable) {
        byte[] f0 = TlsProtocol.f0(hashtable);
        HandshakeMessageOutput handshakeMessageOutput = new HandshakeMessageOutput((short) 8);
        TlsUtils.writeOpaque16(f0, handshakeMessageOutput);
        handshakeMessageOutput.c(this);
    }

    protected void z0(ServerHello serverHello, boolean z) {
        SecurityParameters securityParametersHandshake = this.v.getSecurityParametersHandshake();
        TlsUtils.w(this.v, TlsUtils.H(this.f14866c), this.f14864a);
        this.f14864a.d();
        this.f14864a.c(true);
        y0(this.f14873j);
        this.f14874k = (short) 5;
        if (!this.f14876m) {
            CertificateRequest certificateRequest = this.u.getCertificateRequest();
            this.y = certificateRequest;
            if (certificateRequest != null) {
                if (!certificateRequest.hasCertificateRequestContext(TlsUtils.EMPTY_BYTES)) {
                    throw new TlsFatalAlert((short) 80);
                }
                TlsUtils.C(securityParametersHandshake, this.y);
                A0(this.y);
                this.f14874k = (short) 11;
            }
            TlsCredentialedSigner y = TlsUtils.y(this.u);
            if (y == null) {
                throw new TlsFatalAlert((short) 80);
            }
            T(y.getCertificate());
            securityParametersHandshake.z = null;
            this.f14874k = (short) 7;
            U(TlsUtils.D(this.v, y, this.f14866c));
            this.f14874k = (short) 17;
        }
        V();
        this.f14874k = (short) 20;
        TlsUtils.v(this.v, TlsUtils.H(this.f14866c), this.f14864a);
        this.f14864a.d();
    }
}
