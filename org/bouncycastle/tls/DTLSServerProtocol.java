package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.tls.Certificate;
import org.bouncycastle.tls.DTLSReliableHandshake;
import org.bouncycastle.tls.SessionParameters;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class DTLSServerProtocol extends DTLSProtocol {

    /* renamed from: a  reason: collision with root package name */
    protected boolean f14734a = true;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class ServerHandshakeState {

        /* renamed from: a  reason: collision with root package name */
        TlsServer f14735a = null;

        /* renamed from: b  reason: collision with root package name */
        TlsServerContextImpl f14736b = null;

        /* renamed from: c  reason: collision with root package name */
        TlsSession f14737c = null;

        /* renamed from: d  reason: collision with root package name */
        SessionParameters f14738d = null;

        /* renamed from: e  reason: collision with root package name */
        TlsSecret f14739e = null;

        /* renamed from: f  reason: collision with root package name */
        int[] f14740f = null;

        /* renamed from: g  reason: collision with root package name */
        Hashtable f14741g = null;

        /* renamed from: h  reason: collision with root package name */
        Hashtable f14742h = null;

        /* renamed from: i  reason: collision with root package name */
        boolean f14743i = false;

        /* renamed from: j  reason: collision with root package name */
        boolean f14744j = false;

        /* renamed from: k  reason: collision with root package name */
        boolean f14745k = false;

        /* renamed from: l  reason: collision with root package name */
        TlsKeyExchange f14746l = null;

        /* renamed from: m  reason: collision with root package name */
        TlsCredentials f14747m = null;

        /* renamed from: n  reason: collision with root package name */
        CertificateRequest f14748n = null;

        /* renamed from: o  reason: collision with root package name */
        TlsHeartbeat f14749o = null;

        /* renamed from: p  reason: collision with root package name */
        short f14750p = 2;

        protected ServerHandshakeState() {
        }
    }

    public DTLSTransport accept(TlsServer tlsServer, DatagramTransport datagramTransport) {
        return accept(tlsServer, datagramTransport, null);
    }

    public DTLSTransport accept(TlsServer tlsServer, DatagramTransport datagramTransport, DTLSRequest dTLSRequest) {
        if (tlsServer != null) {
            if (datagramTransport != null) {
                ServerHandshakeState serverHandshakeState = new ServerHandshakeState();
                serverHandshakeState.f14735a = tlsServer;
                TlsServerContextImpl tlsServerContextImpl = new TlsServerContextImpl(tlsServer.getCrypto());
                serverHandshakeState.f14736b = tlsServerContextImpl;
                tlsServer.init(tlsServerContextImpl);
                serverHandshakeState.f14736b.d(tlsServer);
                SecurityParameters securityParametersHandshake = serverHandshakeState.f14736b.getSecurityParametersHandshake();
                securityParametersHandshake.D = tlsServer.shouldUseExtendedPadding();
                DTLSRecordLayer dTLSRecordLayer = new DTLSRecordLayer(serverHandshakeState.f14736b, serverHandshakeState.f14735a, datagramTransport);
                tlsServer.notifyCloseHandle(dTLSRecordLayer);
                try {
                    try {
                        try {
                            try {
                                return v(serverHandshakeState, dTLSRecordLayer, dTLSRequest);
                            } catch (RuntimeException e2) {
                                h(serverHandshakeState, dTLSRecordLayer, (short) 80);
                                throw new TlsFatalAlert((short) 80, (Throwable) e2);
                            }
                        } catch (TlsFatalAlert e3) {
                            h(serverHandshakeState, dTLSRecordLayer, e3.getAlertDescription());
                            throw e3;
                        }
                    } catch (IOException e4) {
                        h(serverHandshakeState, dTLSRecordLayer, (short) 80);
                        throw e4;
                    }
                } finally {
                    securityParametersHandshake.a();
                }
            }
            throw new IllegalArgumentException("'transport' cannot be null");
        }
        throw new IllegalArgumentException("'server' cannot be null");
    }

    public boolean getVerifyRequests() {
        return this.f14734a;
    }

    protected void h(ServerHandshakeState serverHandshakeState, DTLSRecordLayer dTLSRecordLayer, short s2) {
        dTLSRecordLayer.a(s2);
        n(serverHandshakeState);
    }

    protected boolean i(ServerHandshakeState serverHandshakeState) {
        Certificate peerCertificate;
        if (serverHandshakeState.f14748n == null || (peerCertificate = serverHandshakeState.f14736b.getSecurityParametersHandshake().getPeerCertificate()) == null || peerCertificate.isEmpty()) {
            return false;
        }
        TlsKeyExchange tlsKeyExchange = serverHandshakeState.f14746l;
        return tlsKeyExchange == null || tlsKeyExchange.requiresCertificateVerify();
    }

    protected byte[] j(ServerHandshakeState serverHandshakeState, CertificateRequest certificateRequest) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        certificateRequest.encode(serverHandshakeState.f14736b, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] k(ServerHandshakeState serverHandshakeState, CertificateStatus certificateStatus) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        certificateStatus.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] l(ServerHandshakeState serverHandshakeState, NewSessionTicket newSessionTicket) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        newSessionTicket.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] m(ServerHandshakeState serverHandshakeState, DTLSRecordLayer dTLSRecordLayer) {
        ProtocolVersion protocolVersion;
        TlsServerContextImpl tlsServerContextImpl = serverHandshakeState.f14736b;
        SecurityParameters securityParametersHandshake = tlsServerContextImpl.getSecurityParametersHandshake();
        ProtocolVersion serverVersion = serverHandshakeState.f14735a.getServerVersion();
        if (ProtocolVersion.contains(tlsServerContextImpl.getClientSupportedVersions(), serverVersion)) {
            securityParametersHandshake.S = serverVersion;
            TlsUtils.g0(tlsServerContextImpl);
            ProtocolVersion protocolVersion2 = ProtocolVersion.DTLSv12;
            boolean z = false;
            securityParametersHandshake.u = TlsProtocol.i(protocolVersion2.isEqualOrLaterVersionOf(serverVersion) && serverHandshakeState.f14735a.shouldUseGMTUnixTime(), tlsServerContextImpl);
            if (!serverVersion.equals(ProtocolVersion.getLatestDTLS(serverHandshakeState.f14735a.getProtocolVersions()))) {
                TlsUtils.M0(serverVersion, securityParametersHandshake.getServerRandom());
            }
            int g2 = DTLSProtocol.g(serverHandshakeState.f14735a.getSelectedCipherSuite(), (short) 80);
            if (TlsUtils.Z(serverHandshakeState.f14740f, g2) && TlsUtils.isValidVersionForCipherSuite(g2, securityParametersHandshake.getNegotiatedVersion())) {
                TlsUtils.d0(securityParametersHandshake, g2);
                Hashtable ensureExtensionsInitialised = TlsExtensionsUtils.ensureExtensionsInitialised(serverHandshakeState.f14735a.getServerExtensions());
                serverHandshakeState.f14742h = ensureExtensionsInitialised;
                serverHandshakeState.f14735a.getServerExtensionsForConnection(ensureExtensionsInitialised);
                if (serverVersion.isLaterVersionOf(protocolVersion2)) {
                    TlsExtensionsUtils.addSupportedVersionsExtensionServer(serverHandshakeState.f14742h, serverVersion);
                    protocolVersion = protocolVersion2;
                } else {
                    protocolVersion = serverVersion;
                }
                if (securityParametersHandshake.isSecureRenegotiation()) {
                    Hashtable hashtable = serverHandshakeState.f14742h;
                    Integer num = TlsProtocol.f14862s;
                    if (TlsUtils.getExtensionData(hashtable, num) == null) {
                        serverHandshakeState.f14742h.put(num, TlsProtocol.j(TlsUtils.EMPTY_BYTES));
                    }
                }
                if (TlsUtils.isTLSv13(serverVersion)) {
                    securityParametersHandshake.C = true;
                } else {
                    securityParametersHandshake.C = serverHandshakeState.f14743i && serverHandshakeState.f14735a.shouldUseExtendedMasterSecret();
                    if (securityParametersHandshake.isExtendedMasterSecret()) {
                        TlsExtensionsUtils.addExtendedMasterSecretExtension(serverHandshakeState.f14742h);
                    } else if (serverHandshakeState.f14735a.requiresExtendedMasterSecret()) {
                        throw new TlsFatalAlert((short) 40);
                    } else {
                        if (serverHandshakeState.f14744j && !serverHandshakeState.f14735a.allowLegacyResumption()) {
                            throw new TlsFatalAlert((short) 80);
                        }
                    }
                }
                if (serverHandshakeState.f14749o != null || 1 == serverHandshakeState.f14750p) {
                    TlsExtensionsUtils.addHeartbeatExtension(serverHandshakeState.f14742h, new HeartbeatExtension(serverHandshakeState.f14750p));
                }
                securityParametersHandshake.F = TlsExtensionsUtils.getALPNExtensionServer(serverHandshakeState.f14742h);
                securityParametersHandshake.G = true;
                if (!serverHandshakeState.f14742h.isEmpty()) {
                    securityParametersHandshake.B = TlsExtensionsUtils.hasEncryptThenMACExtension(serverHandshakeState.f14742h);
                    securityParametersHandshake.f14802e = DTLSProtocol.b(serverHandshakeState.f14744j, serverHandshakeState.f14741g, serverHandshakeState.f14742h, (short) 80);
                    securityParametersHandshake.E = TlsExtensionsUtils.hasTruncatedHMacExtension(serverHandshakeState.f14742h);
                    if (!serverHandshakeState.f14744j) {
                        if (TlsUtils.hasExpectedEmptyExtensionData(serverHandshakeState.f14742h, TlsExtensionsUtils.EXT_status_request_v2, (short) 80)) {
                            securityParametersHandshake.T = 2;
                        } else if (TlsUtils.hasExpectedEmptyExtensionData(serverHandshakeState.f14742h, TlsExtensionsUtils.EXT_status_request, (short) 80)) {
                            securityParametersHandshake.T = 1;
                        }
                    }
                    if (!serverHandshakeState.f14744j && TlsUtils.hasExpectedEmptyExtensionData(serverHandshakeState.f14742h, TlsProtocol.f14863t, (short) 80)) {
                        z = true;
                    }
                    serverHandshakeState.f14745k = z;
                }
                DTLSProtocol.a(dTLSRecordLayer, securityParametersHandshake.getMaxFragmentLength());
                ServerHello serverHello = new ServerHello(protocolVersion, securityParametersHandshake.getServerRandom(), serverHandshakeState.f14737c.getSessionID(), securityParametersHandshake.getCipherSuite(), serverHandshakeState.f14742h);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                serverHello.encode(serverHandshakeState.f14736b, byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            }
            throw new TlsFatalAlert((short) 80);
        }
        throw new TlsFatalAlert((short) 80);
    }

    protected void n(ServerHandshakeState serverHandshakeState) {
        TlsSecret tlsSecret = serverHandshakeState.f14739e;
        if (tlsSecret != null) {
            tlsSecret.destroy();
            serverHandshakeState.f14739e = null;
        }
        SessionParameters sessionParameters = serverHandshakeState.f14738d;
        if (sessionParameters != null) {
            sessionParameters.clear();
            serverHandshakeState.f14738d = null;
        }
        TlsSession tlsSession = serverHandshakeState.f14737c;
        if (tlsSession != null) {
            tlsSession.invalidate();
            serverHandshakeState.f14737c = null;
        }
    }

    protected void o(ServerHandshakeState serverHandshakeState, Certificate certificate) {
        if (serverHandshakeState.f14748n == null) {
            throw new TlsFatalAlert((short) 80);
        }
        TlsUtils.k0(serverHandshakeState.f14736b, certificate, serverHandshakeState.f14746l, serverHandshakeState.f14735a);
    }

    protected void p(ServerHandshakeState serverHandshakeState, byte[] bArr, TlsHandshakeHash tlsHandshakeHash) {
        if (serverHandshakeState.f14748n == null) {
            throw new IllegalStateException();
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        TlsServerContextImpl tlsServerContextImpl = serverHandshakeState.f14736b;
        DigitallySigned parse = DigitallySigned.parse(tlsServerContextImpl, byteArrayInputStream);
        TlsProtocol.b(byteArrayInputStream);
        TlsUtils.K0(tlsServerContextImpl, serverHandshakeState.f14748n, parse, tlsHandshakeHash);
    }

    protected void q(ServerHandshakeState serverHandshakeState, byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        Certificate parse = Certificate.parse(new Certificate.ParseOptions().setMaxChainLength(serverHandshakeState.f14735a.getMaxCertificateChainLength()), serverHandshakeState.f14736b, byteArrayInputStream, null);
        TlsProtocol.b(byteArrayInputStream);
        o(serverHandshakeState, parse);
    }

    protected void r(ServerHandshakeState serverHandshakeState, ClientHello clientHello) {
        ProtocolVersion version = clientHello.getVersion();
        serverHandshakeState.f14740f = clientHello.getCipherSuites();
        serverHandshakeState.f14741g = clientHello.getExtensions();
        TlsServerContextImpl tlsServerContextImpl = serverHandshakeState.f14736b;
        SecurityParameters securityParametersHandshake = tlsServerContextImpl.getSecurityParametersHandshake();
        if (!version.isDTLS()) {
            throw new TlsFatalAlert((short) 47);
        }
        tlsServerContextImpl.j(version);
        tlsServerContextImpl.h(TlsExtensionsUtils.getSupportedVersionsExtensionClient(serverHandshakeState.f14741g));
        if (tlsServerContextImpl.getClientSupportedVersions() == null) {
            ProtocolVersion protocolVersion = ProtocolVersion.DTLSv12;
            if (version.isLaterVersionOf(protocolVersion)) {
                version = protocolVersion;
            }
            tlsServerContextImpl.h(version.downTo(ProtocolVersion.DTLSv10));
        } else {
            version = ProtocolVersion.getLatestDTLS(tlsServerContextImpl.getClientSupportedVersions());
        }
        if (!ProtocolVersion.f14786e.isEqualOrEarlierVersionOf(version)) {
            throw new TlsFatalAlert((short) 70);
        }
        tlsServerContextImpl.i(version);
        serverHandshakeState.f14735a.notifyClientVersion(tlsServerContextImpl.getClientVersion());
        securityParametersHandshake.f14817t = clientHello.getRandom();
        serverHandshakeState.f14735a.notifyFallback(Arrays.contains(serverHandshakeState.f14740f, (int) CipherSuite.TLS_FALLBACK_SCSV));
        serverHandshakeState.f14735a.notifyOfferedCipherSuites(serverHandshakeState.f14740f);
        if (Arrays.contains(serverHandshakeState.f14740f, 255)) {
            securityParametersHandshake.f14800c = true;
        }
        byte[] extensionData = TlsUtils.getExtensionData(serverHandshakeState.f14741g, TlsProtocol.f14862s);
        if (extensionData != null) {
            securityParametersHandshake.f14800c = true;
            if (!Arrays.constantTimeAreEqual(extensionData, TlsProtocol.j(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert((short) 40);
            }
        }
        serverHandshakeState.f14735a.notifySecureRenegotiation(securityParametersHandshake.isSecureRenegotiation());
        serverHandshakeState.f14743i = TlsExtensionsUtils.hasExtendedMasterSecretExtension(serverHandshakeState.f14741g);
        Hashtable hashtable = serverHandshakeState.f14741g;
        if (hashtable != null) {
            TlsExtensionsUtils.getPaddingExtension(hashtable);
            securityParametersHandshake.I = TlsExtensionsUtils.getServerNameExtensionClient(serverHandshakeState.f14741g);
            if (TlsUtils.isSignatureAlgorithmsExtensionAllowed(version)) {
                TlsUtils.A(securityParametersHandshake, serverHandshakeState.f14741g);
            }
            securityParametersHandshake.L = TlsExtensionsUtils.getSupportedGroupsExtension(serverHandshakeState.f14741g);
            HeartbeatExtension heartbeatExtension = TlsExtensionsUtils.getHeartbeatExtension(serverHandshakeState.f14741g);
            if (heartbeatExtension != null) {
                if (1 == heartbeatExtension.getMode()) {
                    serverHandshakeState.f14749o = serverHandshakeState.f14735a.getHeartbeat();
                }
                serverHandshakeState.f14750p = serverHandshakeState.f14735a.getHeartbeatPolicy();
            }
            serverHandshakeState.f14735a.processClientExtensions(serverHandshakeState.f14741g);
        }
    }

    protected void s(ServerHandshakeState serverHandshakeState, byte[] bArr) {
        r(serverHandshakeState, ClientHello.parse(new ByteArrayInputStream(bArr), NullOutputStream.f14767a));
    }

    public void setVerifyRequests(boolean z) {
        this.f14734a = z;
    }

    protected void t(ServerHandshakeState serverHandshakeState, byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        serverHandshakeState.f14746l.processClientKeyExchange(byteArrayInputStream);
        TlsProtocol.b(byteArrayInputStream);
    }

    protected void u(ServerHandshakeState serverHandshakeState, byte[] bArr) {
        serverHandshakeState.f14735a.processClientSupplementalData(TlsProtocol.M(new ByteArrayInputStream(bArr)));
    }

    protected DTLSTransport v(ServerHandshakeState serverHandshakeState, DTLSRecordLayer dTLSRecordLayer, DTLSRequest dTLSRequest) {
        Certificate certificate;
        CertificateStatus certificateStatus;
        SecurityParameters securityParametersHandshake = serverHandshakeState.f14736b.getSecurityParametersHandshake();
        DTLSReliableHandshake dTLSReliableHandshake = new DTLSReliableHandshake(serverHandshakeState.f14736b, dTLSRecordLayer, serverHandshakeState.f14735a.getHandshakeTimeoutMillis(), dTLSRequest);
        if (dTLSRequest == null) {
            DTLSReliableHandshake.Message g2 = dTLSReliableHandshake.g();
            if (g2.getType() != 1) {
                throw new TlsFatalAlert((short) 10);
            }
            s(serverHandshakeState, g2.getBody());
        } else {
            r(serverHandshakeState, dTLSRequest.a());
        }
        byte[] bArr = TlsUtils.EMPTY_BYTES;
        TlsSession importSession = TlsUtils.importSession(bArr, null);
        serverHandshakeState.f14737c = importSession;
        serverHandshakeState.f14738d = null;
        serverHandshakeState.f14739e = null;
        securityParametersHandshake.w = importSession.getSessionID();
        serverHandshakeState.f14735a.notifySession(serverHandshakeState.f14737c);
        byte[] m2 = m(serverHandshakeState, dTLSRecordLayer);
        ProtocolVersion serverVersion = serverHandshakeState.f14736b.getServerVersion();
        dTLSRecordLayer.n(serverVersion);
        dTLSRecordLayer.o(serverVersion);
        dTLSReliableHandshake.k((short) 2, m2);
        dTLSReliableHandshake.d().notifyPRFDetermined();
        Vector serverSupplementalData = serverHandshakeState.f14735a.getServerSupplementalData();
        if (serverSupplementalData != null) {
            dTLSReliableHandshake.k((short) 23, DTLSProtocol.d(serverSupplementalData));
        }
        serverHandshakeState.f14746l = TlsUtils.V(serverHandshakeState.f14736b, serverHandshakeState.f14735a);
        serverHandshakeState.f14747m = TlsUtils.B(serverHandshakeState.f14735a);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        TlsCredentials tlsCredentials = serverHandshakeState.f14747m;
        if (tlsCredentials == null) {
            serverHandshakeState.f14746l.skipServerCredentials();
            certificate = null;
        } else {
            serverHandshakeState.f14746l.processServerCredentials(tlsCredentials);
            certificate = serverHandshakeState.f14747m.getCertificate();
            DTLSProtocol.f(serverHandshakeState.f14736b, dTLSReliableHandshake, certificate, byteArrayOutputStream);
        }
        securityParametersHandshake.z = byteArrayOutputStream.toByteArray();
        if (certificate == null || certificate.isEmpty()) {
            securityParametersHandshake.T = 0;
        }
        if (securityParametersHandshake.getStatusRequestVersion() > 0 && (certificateStatus = serverHandshakeState.f14735a.getCertificateStatus()) != null) {
            dTLSReliableHandshake.k((short) 22, k(serverHandshakeState, certificateStatus));
        }
        byte[] generateServerKeyExchange = serverHandshakeState.f14746l.generateServerKeyExchange();
        if (generateServerKeyExchange != null) {
            dTLSReliableHandshake.k((short) 12, generateServerKeyExchange);
        }
        if (serverHandshakeState.f14747m != null) {
            CertificateRequest certificateRequest = serverHandshakeState.f14735a.getCertificateRequest();
            serverHandshakeState.f14748n = certificateRequest;
            if (certificateRequest != null) {
                if (TlsUtils.isTLSv12(serverHandshakeState.f14736b) != (serverHandshakeState.f14748n.getSupportedSignatureAlgorithms() != null)) {
                    throw new TlsFatalAlert((short) 80);
                }
                CertificateRequest G0 = TlsUtils.G0(serverHandshakeState.f14748n, serverHandshakeState.f14746l);
                serverHandshakeState.f14748n = G0;
                TlsUtils.C(securityParametersHandshake, G0);
                TlsUtils.A0(dTLSReliableHandshake.d(), securityParametersHandshake.getServerSigAlgs());
                dTLSReliableHandshake.k((short) 13, j(serverHandshakeState, serverHandshakeState.f14748n));
            } else if (!serverHandshakeState.f14746l.requiresCertificateVerify()) {
                throw new TlsFatalAlert((short) 80);
            }
        }
        dTLSReliableHandshake.k((short) 14, bArr);
        TlsUtils.t0(serverHandshakeState.f14736b, dTLSReliableHandshake.d(), false);
        DTLSReliableHandshake.Message g3 = dTLSReliableHandshake.g();
        if (g3.getType() == 23) {
            u(serverHandshakeState, g3.getBody());
            g3 = dTLSReliableHandshake.g();
        } else {
            serverHandshakeState.f14735a.processClientSupplementalData(null);
        }
        if (serverHandshakeState.f14748n == null) {
            serverHandshakeState.f14746l.skipClientCredentials();
        } else if (g3.getType() == 11) {
            q(serverHandshakeState, g3.getBody());
            g3 = dTLSReliableHandshake.g();
        } else if (TlsUtils.isTLSv12(serverHandshakeState.f14736b)) {
            throw new TlsFatalAlert((short) 10);
        } else {
            o(serverHandshakeState, Certificate.EMPTY_CHAIN);
        }
        if (g3.getType() == 16) {
            t(serverHandshakeState, g3.getBody());
            securityParametersHandshake.v = TlsUtils.H(dTLSReliableHandshake.d());
            TlsProtocol.k(serverHandshakeState.f14736b, serverHandshakeState.f14746l);
            dTLSRecordLayer.g(TlsUtils.T(serverHandshakeState.f14736b));
            TlsHandshakeHash e2 = dTLSReliableHandshake.e();
            if (i(serverHandshakeState)) {
                p(serverHandshakeState, dTLSReliableHandshake.h((short) 15), e2);
            }
            securityParametersHandshake.V = TlsUtils.l(serverHandshakeState.f14736b, dTLSReliableHandshake.d(), false);
            e(dTLSReliableHandshake.h((short) 20), securityParametersHandshake.getPeerVerifyData());
            if (serverHandshakeState.f14745k) {
                dTLSReliableHandshake.k((short) 4, l(serverHandshakeState, serverHandshakeState.f14735a.getNewSessionTicket()));
            }
            securityParametersHandshake.U = TlsUtils.l(serverHandshakeState.f14736b, dTLSReliableHandshake.d(), true);
            dTLSReliableHandshake.k((short) 20, securityParametersHandshake.getLocalVerifyData());
            dTLSReliableHandshake.c();
            serverHandshakeState.f14739e = securityParametersHandshake.getMasterSecret();
            serverHandshakeState.f14738d = new SessionParameters.Builder().setCipherSuite(securityParametersHandshake.getCipherSuite()).setCompressionAlgorithm(securityParametersHandshake.getCompressionAlgorithm()).setExtendedMasterSecret(securityParametersHandshake.isExtendedMasterSecret()).setLocalCertificate(securityParametersHandshake.getLocalCertificate()).setMasterSecret(serverHandshakeState.f14736b.getCrypto().adoptSecret(serverHandshakeState.f14739e)).setNegotiatedVersion(securityParametersHandshake.getNegotiatedVersion()).setPeerCertificate(securityParametersHandshake.getPeerCertificate()).setPSKIdentity(securityParametersHandshake.getPSKIdentity()).setSRPIdentity(securityParametersHandshake.getSRPIdentity()).setServerExtensions(serverHandshakeState.f14742h).build();
            serverHandshakeState.f14737c = TlsUtils.importSession(serverHandshakeState.f14737c.getSessionID(), serverHandshakeState.f14738d);
            securityParametersHandshake.A = securityParametersHandshake.getPeerVerifyData();
            serverHandshakeState.f14736b.e(serverHandshakeState.f14735a, serverHandshakeState.f14737c);
            dTLSRecordLayer.f(serverHandshakeState.f14749o, 1 == serverHandshakeState.f14750p);
            return new DTLSTransport(dTLSRecordLayer);
        }
        throw new TlsFatalAlert((short) 10);
    }
}
