package org.bouncycastle.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.tls.DTLSReliableHandshake;
import org.bouncycastle.tls.SessionParameters;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class DTLSClientProtocol extends DTLSProtocol {

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class ClientHandshakeState {

        /* renamed from: a  reason: collision with root package name */
        TlsClient f14716a = null;

        /* renamed from: b  reason: collision with root package name */
        TlsClientContextImpl f14717b = null;

        /* renamed from: c  reason: collision with root package name */
        TlsSession f14718c = null;

        /* renamed from: d  reason: collision with root package name */
        SessionParameters f14719d = null;

        /* renamed from: e  reason: collision with root package name */
        TlsSecret f14720e = null;

        /* renamed from: f  reason: collision with root package name */
        int[] f14721f = null;

        /* renamed from: g  reason: collision with root package name */
        Hashtable f14722g = null;

        /* renamed from: h  reason: collision with root package name */
        Hashtable f14723h = null;

        /* renamed from: i  reason: collision with root package name */
        boolean f14724i = false;

        /* renamed from: j  reason: collision with root package name */
        boolean f14725j = false;

        /* renamed from: k  reason: collision with root package name */
        TlsKeyExchange f14726k = null;

        /* renamed from: l  reason: collision with root package name */
        TlsAuthentication f14727l = null;

        /* renamed from: m  reason: collision with root package name */
        CertificateStatus f14728m = null;

        /* renamed from: n  reason: collision with root package name */
        CertificateRequest f14729n = null;

        /* renamed from: o  reason: collision with root package name */
        TlsCredentials f14730o = null;

        /* renamed from: p  reason: collision with root package name */
        TlsHeartbeat f14731p = null;

        /* renamed from: q  reason: collision with root package name */
        short f14732q = 2;

        protected ClientHandshakeState() {
        }
    }

    protected static byte[] n(byte[] bArr, byte[] bArr2) {
        int readUint8 = 35 + TlsUtils.readUint8(bArr, 34);
        int i2 = readUint8 + 1;
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, readUint8);
        TlsUtils.checkUint8(bArr2.length);
        TlsUtils.writeUint8(bArr2.length, bArr3, readUint8);
        System.arraycopy(bArr2, 0, bArr3, i2, bArr2.length);
        System.arraycopy(bArr, i2, bArr3, bArr2.length + i2, bArr.length - i2);
        return bArr3;
    }

    public DTLSTransport connect(TlsClient tlsClient, DatagramTransport datagramTransport) {
        SessionParameters exportSessionParameters;
        if (tlsClient != null) {
            if (datagramTransport != null) {
                ClientHandshakeState clientHandshakeState = new ClientHandshakeState();
                clientHandshakeState.f14716a = tlsClient;
                TlsClientContextImpl tlsClientContextImpl = new TlsClientContextImpl(tlsClient.getCrypto());
                clientHandshakeState.f14717b = tlsClientContextImpl;
                tlsClient.init(tlsClientContextImpl);
                clientHandshakeState.f14717b.d(tlsClient);
                SecurityParameters securityParametersHandshake = clientHandshakeState.f14717b.getSecurityParametersHandshake();
                securityParametersHandshake.D = tlsClient.shouldUseExtendedPadding();
                TlsSession sessionToResume = clientHandshakeState.f14716a.getSessionToResume();
                if (sessionToResume != null && sessionToResume.isResumable() && (exportSessionParameters = sessionToResume.exportSessionParameters()) != null && (exportSessionParameters.isExtendedMasterSecret() || (!clientHandshakeState.f14716a.requiresExtendedMasterSecret() && clientHandshakeState.f14716a.allowLegacyResumption()))) {
                    TlsSecret masterSecret = exportSessionParameters.getMasterSecret();
                    synchronized (masterSecret) {
                        if (masterSecret.isAlive()) {
                            clientHandshakeState.f14718c = sessionToResume;
                            clientHandshakeState.f14719d = exportSessionParameters;
                            clientHandshakeState.f14720e = clientHandshakeState.f14717b.getCrypto().adoptSecret(masterSecret);
                        }
                    }
                }
                DTLSRecordLayer dTLSRecordLayer = new DTLSRecordLayer(clientHandshakeState.f14717b, clientHandshakeState.f14716a, datagramTransport);
                tlsClient.notifyCloseHandle(dTLSRecordLayer);
                try {
                    try {
                        try {
                            return i(clientHandshakeState, dTLSRecordLayer);
                        } catch (RuntimeException e2) {
                            h(clientHandshakeState, dTLSRecordLayer, (short) 80);
                            throw new TlsFatalAlert((short) 80, (Throwable) e2);
                        }
                    } catch (TlsFatalAlert e3) {
                        h(clientHandshakeState, dTLSRecordLayer, e3.getAlertDescription());
                        throw e3;
                    } catch (IOException e4) {
                        h(clientHandshakeState, dTLSRecordLayer, (short) 80);
                        throw e4;
                    }
                } finally {
                    securityParametersHandshake.a();
                }
            }
            throw new IllegalArgumentException("'transport' cannot be null");
        }
        throw new IllegalArgumentException("'client' cannot be null");
    }

    protected void h(ClientHandshakeState clientHandshakeState, DTLSRecordLayer dTLSRecordLayer, short s2) {
        dTLSRecordLayer.a(s2);
        m(clientHandshakeState);
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0335  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected DTLSTransport i(ClientHandshakeState clientHandshakeState, DTLSRecordLayer dTLSRecordLayer) {
        DTLSReliableHandshake.Message g2;
        TlsCredentialedSigner tlsCredentialedSigner;
        TlsStreamSigner tlsStreamSigner;
        SecurityParameters securityParametersHandshake = clientHandshakeState.f14717b.getSecurityParametersHandshake();
        DTLSReliableHandshake dTLSReliableHandshake = new DTLSReliableHandshake(clientHandshakeState.f14717b, dTLSRecordLayer, clientHandshakeState.f14716a.getHandshakeTimeoutMillis(), null);
        byte[] k2 = k(clientHandshakeState);
        dTLSRecordLayer.o(ProtocolVersion.DTLSv10);
        dTLSReliableHandshake.k((short) 1, k2);
        while (true) {
            g2 = dTLSReliableHandshake.g();
            if (g2.getType() != 3) {
                break;
            }
            byte[] n2 = n(k2, q(clientHandshakeState, g2.getBody()));
            dTLSReliableHandshake.i();
            dTLSReliableHandshake.k((short) 1, n2);
        }
        if (g2.getType() == 2) {
            ProtocolVersion d2 = dTLSRecordLayer.d();
            w(clientHandshakeState, d2);
            dTLSRecordLayer.o(d2);
            t(clientHandshakeState, g2.getBody());
            dTLSReliableHandshake.d().notifyPRFDetermined();
            DTLSProtocol.a(dTLSRecordLayer, securityParametersHandshake.getMaxFragmentLength());
            if (clientHandshakeState.f14724i) {
                securityParametersHandshake.f14814q = clientHandshakeState.f14720e;
                dTLSRecordLayer.g(TlsUtils.T(clientHandshakeState.f14717b));
                securityParametersHandshake.V = TlsUtils.l(clientHandshakeState.f14717b, dTLSReliableHandshake.d(), true);
                e(dTLSReliableHandshake.h((short) 20), securityParametersHandshake.getPeerVerifyData());
                securityParametersHandshake.U = TlsUtils.l(clientHandshakeState.f14717b, dTLSReliableHandshake.d(), false);
                dTLSReliableHandshake.k((short) 20, securityParametersHandshake.getLocalVerifyData());
                dTLSReliableHandshake.c();
                if (securityParametersHandshake.isExtendedMasterSecret()) {
                    securityParametersHandshake.A = securityParametersHandshake.getPeerVerifyData();
                }
                securityParametersHandshake.Q = clientHandshakeState.f14719d.getLocalCertificate();
                securityParametersHandshake.R = clientHandshakeState.f14719d.getPeerCertificate();
                securityParametersHandshake.x = clientHandshakeState.f14719d.getPSKIdentity();
                securityParametersHandshake.y = clientHandshakeState.f14719d.getSRPIdentity();
                clientHandshakeState.f14717b.e(clientHandshakeState.f14716a, clientHandshakeState.f14718c);
                dTLSRecordLayer.f(clientHandshakeState.f14731p, 1 == clientHandshakeState.f14732q);
                return new DTLSTransport(dTLSRecordLayer);
            }
            m(clientHandshakeState);
            clientHandshakeState.f14718c = TlsUtils.importSession(securityParametersHandshake.getSessionID(), null);
            DTLSReliableHandshake.Message g3 = dTLSReliableHandshake.g();
            if (g3.getType() == 23) {
                v(clientHandshakeState, g3.getBody());
                g3 = dTLSReliableHandshake.g();
            } else {
                clientHandshakeState.f14716a.processServerSupplementalData(null);
            }
            clientHandshakeState.f14726k = TlsUtils.U(clientHandshakeState.f14717b, clientHandshakeState.f14716a);
            if (g3.getType() == 11) {
                s(clientHandshakeState, g3.getBody());
                g3 = dTLSReliableHandshake.g();
            } else {
                clientHandshakeState.f14727l = null;
            }
            if (g3.getType() == 22) {
                if (securityParametersHandshake.getStatusRequestVersion() < 1) {
                    throw new TlsFatalAlert((short) 10);
                }
                p(clientHandshakeState, g3.getBody());
                g3 = dTLSReliableHandshake.g();
            }
            TlsUtils.l0(clientHandshakeState.f14717b, clientHandshakeState.f14728m, clientHandshakeState.f14726k, clientHandshakeState.f14727l, clientHandshakeState.f14722g, clientHandshakeState.f14723h);
            if (g3.getType() == 12) {
                u(clientHandshakeState, g3.getBody());
                g3 = dTLSReliableHandshake.g();
            } else {
                clientHandshakeState.f14726k.skipServerKeyExchange();
            }
            if (g3.getType() == 13) {
                o(clientHandshakeState, g3.getBody());
                TlsUtils.C(securityParametersHandshake, clientHandshakeState.f14729n);
                TlsUtils.A0(dTLSReliableHandshake.d(), securityParametersHandshake.getServerSigAlgs());
                g3 = dTLSReliableHandshake.g();
            }
            if (g3.getType() == 14) {
                if (g3.getBody().length == 0) {
                    Vector clientSupplementalData = clientHandshakeState.f14716a.getClientSupplementalData();
                    if (clientSupplementalData != null) {
                        dTLSReliableHandshake.k((short) 23, DTLSProtocol.d(clientSupplementalData));
                    }
                    CertificateRequest certificateRequest = clientHandshakeState.f14729n;
                    if (certificateRequest != null) {
                        TlsCredentials z = TlsUtils.z(clientHandshakeState.f14727l, certificateRequest);
                        clientHandshakeState.f14730o = z;
                        DTLSProtocol.f(clientHandshakeState.f14717b, dTLSReliableHandshake, z != null ? z.getCertificate() : null, null);
                    }
                    TlsCredentials tlsCredentials = clientHandshakeState.f14730o;
                    if (tlsCredentials != null) {
                        clientHandshakeState.f14726k.processClientCredentials(tlsCredentials);
                        TlsCredentials tlsCredentials2 = clientHandshakeState.f14730o;
                        if (tlsCredentials2 instanceof TlsCredentialedSigner) {
                            tlsCredentialedSigner = (TlsCredentialedSigner) tlsCredentials2;
                            tlsStreamSigner = tlsCredentialedSigner.getStreamSigner();
                            TlsUtils.t0(clientHandshakeState.f14717b, dTLSReliableHandshake.d(), tlsStreamSigner == null);
                            dTLSReliableHandshake.k((short) 16, l(clientHandshakeState));
                            securityParametersHandshake.v = TlsUtils.H(dTLSReliableHandshake.d());
                            TlsProtocol.k(clientHandshakeState.f14717b, clientHandshakeState.f14726k);
                            dTLSRecordLayer.g(TlsUtils.T(clientHandshakeState.f14717b));
                            if (tlsCredentialedSigner != null) {
                                dTLSReliableHandshake.k((short) 15, j(clientHandshakeState, TlsUtils.E(clientHandshakeState.f14717b, tlsCredentialedSigner, tlsStreamSigner, dTLSReliableHandshake.d())));
                            }
                            dTLSReliableHandshake.e();
                            securityParametersHandshake.U = TlsUtils.l(clientHandshakeState.f14717b, dTLSReliableHandshake.d(), false);
                            dTLSReliableHandshake.k((short) 20, securityParametersHandshake.getLocalVerifyData());
                            if (clientHandshakeState.f14725j) {
                                DTLSReliableHandshake.Message g4 = dTLSReliableHandshake.g();
                                if (g4.getType() != 4) {
                                    throw new TlsFatalAlert((short) 10);
                                }
                                securityParametersHandshake.w = TlsUtils.EMPTY_BYTES;
                                m(clientHandshakeState);
                                clientHandshakeState.f14718c = TlsUtils.importSession(securityParametersHandshake.getSessionID(), null);
                                r(clientHandshakeState, g4.getBody());
                            }
                            securityParametersHandshake.V = TlsUtils.l(clientHandshakeState.f14717b, dTLSReliableHandshake.d(), true);
                            e(dTLSReliableHandshake.h((short) 20), securityParametersHandshake.getPeerVerifyData());
                            dTLSReliableHandshake.c();
                            clientHandshakeState.f14720e = securityParametersHandshake.getMasterSecret();
                            clientHandshakeState.f14719d = new SessionParameters.Builder().setCipherSuite(securityParametersHandshake.getCipherSuite()).setCompressionAlgorithm(securityParametersHandshake.getCompressionAlgorithm()).setExtendedMasterSecret(securityParametersHandshake.isExtendedMasterSecret()).setLocalCertificate(securityParametersHandshake.getLocalCertificate()).setMasterSecret(clientHandshakeState.f14717b.getCrypto().adoptSecret(clientHandshakeState.f14720e)).setNegotiatedVersion(securityParametersHandshake.getNegotiatedVersion()).setPeerCertificate(securityParametersHandshake.getPeerCertificate()).setPSKIdentity(securityParametersHandshake.getPSKIdentity()).setSRPIdentity(securityParametersHandshake.getSRPIdentity()).setServerExtensions(clientHandshakeState.f14723h).build();
                            clientHandshakeState.f14718c = TlsUtils.importSession(securityParametersHandshake.getSessionID(), clientHandshakeState.f14719d);
                            securityParametersHandshake.A = securityParametersHandshake.getLocalVerifyData();
                            clientHandshakeState.f14717b.e(clientHandshakeState.f14716a, clientHandshakeState.f14718c);
                            dTLSRecordLayer.f(clientHandshakeState.f14731p, 1 != clientHandshakeState.f14732q);
                            return new DTLSTransport(dTLSRecordLayer);
                        }
                    } else {
                        clientHandshakeState.f14726k.skipClientCredentials();
                    }
                    tlsCredentialedSigner = null;
                    tlsStreamSigner = null;
                    TlsUtils.t0(clientHandshakeState.f14717b, dTLSReliableHandshake.d(), tlsStreamSigner == null);
                    dTLSReliableHandshake.k((short) 16, l(clientHandshakeState));
                    securityParametersHandshake.v = TlsUtils.H(dTLSReliableHandshake.d());
                    TlsProtocol.k(clientHandshakeState.f14717b, clientHandshakeState.f14726k);
                    dTLSRecordLayer.g(TlsUtils.T(clientHandshakeState.f14717b));
                    if (tlsCredentialedSigner != null) {
                    }
                    dTLSReliableHandshake.e();
                    securityParametersHandshake.U = TlsUtils.l(clientHandshakeState.f14717b, dTLSReliableHandshake.d(), false);
                    dTLSReliableHandshake.k((short) 20, securityParametersHandshake.getLocalVerifyData());
                    if (clientHandshakeState.f14725j) {
                    }
                    securityParametersHandshake.V = TlsUtils.l(clientHandshakeState.f14717b, dTLSReliableHandshake.d(), true);
                    e(dTLSReliableHandshake.h((short) 20), securityParametersHandshake.getPeerVerifyData());
                    dTLSReliableHandshake.c();
                    clientHandshakeState.f14720e = securityParametersHandshake.getMasterSecret();
                    clientHandshakeState.f14719d = new SessionParameters.Builder().setCipherSuite(securityParametersHandshake.getCipherSuite()).setCompressionAlgorithm(securityParametersHandshake.getCompressionAlgorithm()).setExtendedMasterSecret(securityParametersHandshake.isExtendedMasterSecret()).setLocalCertificate(securityParametersHandshake.getLocalCertificate()).setMasterSecret(clientHandshakeState.f14717b.getCrypto().adoptSecret(clientHandshakeState.f14720e)).setNegotiatedVersion(securityParametersHandshake.getNegotiatedVersion()).setPeerCertificate(securityParametersHandshake.getPeerCertificate()).setPSKIdentity(securityParametersHandshake.getPSKIdentity()).setSRPIdentity(securityParametersHandshake.getSRPIdentity()).setServerExtensions(clientHandshakeState.f14723h).build();
                    clientHandshakeState.f14718c = TlsUtils.importSession(securityParametersHandshake.getSessionID(), clientHandshakeState.f14719d);
                    securityParametersHandshake.A = securityParametersHandshake.getLocalVerifyData();
                    clientHandshakeState.f14717b.e(clientHandshakeState.f14716a, clientHandshakeState.f14718c);
                    dTLSRecordLayer.f(clientHandshakeState.f14731p, 1 != clientHandshakeState.f14732q);
                    return new DTLSTransport(dTLSRecordLayer);
                }
                throw new TlsFatalAlert((short) 50);
            }
            throw new TlsFatalAlert((short) 10);
        }
        throw new TlsFatalAlert((short) 10);
    }

    protected byte[] j(ClientHandshakeState clientHandshakeState, DigitallySigned digitallySigned) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        digitallySigned.encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] k(ClientHandshakeState clientHandshakeState) {
        ProtocolVersion protocolVersion;
        SessionParameters sessionParameters;
        TlsClientContextImpl tlsClientContextImpl = clientHandshakeState.f14717b;
        SecurityParameters securityParametersHandshake = tlsClientContextImpl.getSecurityParametersHandshake();
        tlsClientContextImpl.h(clientHandshakeState.f14716a.getProtocolVersions());
        ProtocolVersion latestDTLS = ProtocolVersion.getLatestDTLS(tlsClientContextImpl.getClientSupportedVersions());
        if (ProtocolVersion.a(latestDTLS)) {
            tlsClientContextImpl.i(latestDTLS);
            byte[] P = TlsUtils.P(clientHandshakeState.f14718c);
            boolean isFallback = clientHandshakeState.f14716a.isFallback();
            int[] cipherSuites = clientHandshakeState.f14716a.getCipherSuites();
            clientHandshakeState.f14721f = cipherSuites;
            if (P.length > 0 && (sessionParameters = clientHandshakeState.f14719d) != null && (!Arrays.contains(cipherSuites, sessionParameters.getCipherSuite()) || clientHandshakeState.f14719d.getCompressionAlgorithm() != 0)) {
                P = TlsUtils.EMPTY_BYTES;
            }
            byte[] bArr = P;
            clientHandshakeState.f14722g = TlsExtensionsUtils.ensureExtensionsInitialised(clientHandshakeState.f14716a.getClientExtensions());
            ProtocolVersion protocolVersion2 = ProtocolVersion.DTLSv12;
            if (latestDTLS.isLaterVersionOf(protocolVersion2)) {
                TlsExtensionsUtils.addSupportedVersionsExtensionClient(clientHandshakeState.f14722g, tlsClientContextImpl.getClientSupportedVersions());
                protocolVersion = protocolVersion2;
            } else {
                protocolVersion = latestDTLS;
            }
            tlsClientContextImpl.j(protocolVersion);
            securityParametersHandshake.I = TlsExtensionsUtils.getServerNameExtensionClient(clientHandshakeState.f14722g);
            if (TlsUtils.isSignatureAlgorithmsExtensionAllowed(latestDTLS)) {
                TlsUtils.A(securityParametersHandshake, clientHandshakeState.f14722g);
            }
            securityParametersHandshake.L = TlsExtensionsUtils.getSupportedGroupsExtension(clientHandshakeState.f14722g);
            TlsUtils.a(clientHandshakeState.f14717b, clientHandshakeState.f14716a, clientHandshakeState.f14722g);
            if (TlsUtils.W(tlsClientContextImpl.getClientSupportedVersions()) && clientHandshakeState.f14716a.shouldUseExtendedMasterSecret()) {
                TlsExtensionsUtils.addExtendedMasterSecretExtension(clientHandshakeState.f14722g);
            } else if (!TlsUtils.isTLSv13(latestDTLS) && clientHandshakeState.f14716a.requiresExtendedMasterSecret()) {
                throw new TlsFatalAlert((short) 80);
            }
            securityParametersHandshake.f14817t = TlsProtocol.i(protocolVersion2.isEqualOrLaterVersionOf(latestDTLS) && clientHandshakeState.f14716a.shouldUseGMTUnixTime(), clientHandshakeState.f14717b);
            boolean z = TlsUtils.getExtensionData(clientHandshakeState.f14722g, TlsProtocol.f14862s) == null;
            boolean z2 = !Arrays.contains(clientHandshakeState.f14721f, 255);
            if (z && z2) {
                clientHandshakeState.f14721f = Arrays.append(clientHandshakeState.f14721f, 255);
            }
            if (isFallback && !Arrays.contains(clientHandshakeState.f14721f, (int) CipherSuite.TLS_FALLBACK_SCSV)) {
                clientHandshakeState.f14721f = Arrays.append(clientHandshakeState.f14721f, (int) CipherSuite.TLS_FALLBACK_SCSV);
            }
            clientHandshakeState.f14731p = clientHandshakeState.f14716a.getHeartbeat();
            short heartbeatPolicy = clientHandshakeState.f14716a.getHeartbeatPolicy();
            clientHandshakeState.f14732q = heartbeatPolicy;
            if (clientHandshakeState.f14731p != null || 1 == heartbeatPolicy) {
                TlsExtensionsUtils.addHeartbeatExtension(clientHandshakeState.f14722g, new HeartbeatExtension(heartbeatPolicy));
            }
            ClientHello clientHello = new ClientHello(protocolVersion, securityParametersHandshake.getClientRandom(), bArr, TlsUtils.EMPTY_BYTES, clientHandshakeState.f14721f, clientHandshakeState.f14722g, 0);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            clientHello.encode(clientHandshakeState.f14717b, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
        throw new TlsFatalAlert((short) 80);
    }

    protected byte[] l(ClientHandshakeState clientHandshakeState) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        clientHandshakeState.f14726k.generateClientKeyExchange(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected void m(ClientHandshakeState clientHandshakeState) {
        TlsSecret tlsSecret = clientHandshakeState.f14720e;
        if (tlsSecret != null) {
            tlsSecret.destroy();
            clientHandshakeState.f14720e = null;
        }
        SessionParameters sessionParameters = clientHandshakeState.f14719d;
        if (sessionParameters != null) {
            sessionParameters.clear();
            clientHandshakeState.f14719d = null;
        }
        TlsSession tlsSession = clientHandshakeState.f14718c;
        if (tlsSession != null) {
            tlsSession.invalidate();
            clientHandshakeState.f14718c = null;
        }
    }

    protected void o(ClientHandshakeState clientHandshakeState, byte[] bArr) {
        if (clientHandshakeState.f14727l == null) {
            throw new TlsFatalAlert((short) 40);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        CertificateRequest parse = CertificateRequest.parse(clientHandshakeState.f14717b, byteArrayInputStream);
        TlsProtocol.b(byteArrayInputStream);
        clientHandshakeState.f14729n = TlsUtils.G0(parse, clientHandshakeState.f14726k);
    }

    protected void p(ClientHandshakeState clientHandshakeState, byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        clientHandshakeState.f14728m = CertificateStatus.parse(clientHandshakeState.f14717b, byteArrayInputStream);
        TlsProtocol.b(byteArrayInputStream);
    }

    protected byte[] q(ClientHandshakeState clientHandshakeState, byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ProtocolVersion readVersion = TlsUtils.readVersion(byteArrayInputStream);
        byte[] readOpaque8 = TlsUtils.readOpaque8(byteArrayInputStream, 0, ProtocolVersion.DTLSv12.isEqualOrEarlierVersionOf(readVersion) ? 255 : 32);
        TlsProtocol.b(byteArrayInputStream);
        if (readVersion.isEqualOrEarlierVersionOf(clientHandshakeState.f14717b.getClientVersion())) {
            return readOpaque8;
        }
        throw new TlsFatalAlert((short) 47);
    }

    protected void r(ClientHandshakeState clientHandshakeState, byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        NewSessionTicket parse = NewSessionTicket.parse(byteArrayInputStream);
        TlsProtocol.b(byteArrayInputStream);
        clientHandshakeState.f14716a.notifyNewSessionTicket(parse);
    }

    protected void s(ClientHandshakeState clientHandshakeState, byte[] bArr) {
        clientHandshakeState.f14727l = TlsUtils.o0(clientHandshakeState.f14717b, clientHandshakeState.f14716a, new ByteArrayInputStream(bArr));
    }

    protected void t(ClientHandshakeState clientHandshakeState, byte[] bArr) {
        TlsSession tlsSession;
        ServerHello parse = ServerHello.parse(new ByteArrayInputStream(bArr));
        ProtocolVersion version = parse.getVersion();
        clientHandshakeState.f14723h = parse.getExtensions();
        SecurityParameters securityParametersHandshake = clientHandshakeState.f14717b.getSecurityParametersHandshake();
        w(clientHandshakeState, version);
        securityParametersHandshake.u = parse.getRandom();
        if (!clientHandshakeState.f14717b.getClientVersion().equals(version)) {
            TlsUtils.m(version, securityParametersHandshake.getServerRandom());
        }
        byte[] sessionID = parse.getSessionID();
        securityParametersHandshake.w = sessionID;
        clientHandshakeState.f14716a.notifySessionID(sessionID);
        boolean z = false;
        clientHandshakeState.f14724i = sessionID.length > 0 && (tlsSession = clientHandshakeState.f14718c) != null && Arrays.areEqual(sessionID, tlsSession.getSessionID());
        int g2 = DTLSProtocol.g(parse.getCipherSuite(), (short) 47);
        if (!TlsUtils.Z(clientHandshakeState.f14721f, g2) || !TlsUtils.isValidVersionForCipherSuite(g2, securityParametersHandshake.getNegotiatedVersion())) {
            throw new TlsFatalAlert((short) 47);
        }
        TlsUtils.d0(securityParametersHandshake, g2);
        clientHandshakeState.f14716a.notifySelectedCipherSuite(g2);
        if (TlsUtils.isTLSv13(version)) {
            securityParametersHandshake.C = true;
        } else {
            boolean hasExtendedMasterSecretExtension = TlsExtensionsUtils.hasExtendedMasterSecretExtension(clientHandshakeState.f14723h);
            if (hasExtendedMasterSecretExtension) {
                if (!clientHandshakeState.f14724i && !clientHandshakeState.f14716a.shouldUseExtendedMasterSecret()) {
                    throw new TlsFatalAlert((short) 40);
                }
            } else if (clientHandshakeState.f14716a.requiresExtendedMasterSecret() || (clientHandshakeState.f14724i && !clientHandshakeState.f14716a.allowLegacyResumption())) {
                throw new TlsFatalAlert((short) 40);
            }
            securityParametersHandshake.C = hasExtendedMasterSecretExtension;
        }
        Hashtable hashtable = clientHandshakeState.f14723h;
        if (hashtable != null) {
            Enumeration keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                Integer num = (Integer) keys.nextElement();
                if (!num.equals(TlsProtocol.f14862s) && TlsUtils.getExtensionData(clientHandshakeState.f14722g, num) == null) {
                    throw new TlsFatalAlert(AlertDescription.unsupported_extension);
                }
            }
        }
        byte[] extensionData = TlsUtils.getExtensionData(clientHandshakeState.f14723h, TlsProtocol.f14862s);
        if (extensionData != null) {
            securityParametersHandshake.f14800c = true;
            if (!Arrays.constantTimeAreEqual(extensionData, TlsProtocol.j(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert((short) 40);
            }
        }
        clientHandshakeState.f14716a.notifySecureRenegotiation(securityParametersHandshake.isSecureRenegotiation());
        securityParametersHandshake.F = TlsExtensionsUtils.getALPNExtensionServer(clientHandshakeState.f14723h);
        securityParametersHandshake.G = true;
        HeartbeatExtension heartbeatExtension = TlsExtensionsUtils.getHeartbeatExtension(clientHandshakeState.f14723h);
        Hashtable hashtable2 = null;
        if (heartbeatExtension == null) {
            clientHandshakeState.f14731p = null;
            clientHandshakeState.f14732q = (short) 2;
        } else if (1 != heartbeatExtension.getMode()) {
            clientHandshakeState.f14731p = null;
        }
        Hashtable hashtable3 = clientHandshakeState.f14722g;
        Hashtable hashtable4 = clientHandshakeState.f14723h;
        if (!clientHandshakeState.f14724i) {
            hashtable2 = hashtable3;
        } else if (securityParametersHandshake.getCipherSuite() != clientHandshakeState.f14719d.getCipherSuite() || clientHandshakeState.f14719d.getCompressionAlgorithm() != 0 || !version.equals(clientHandshakeState.f14719d.getNegotiatedVersion())) {
            throw new TlsFatalAlert((short) 47);
        } else {
            hashtable4 = clientHandshakeState.f14719d.readServerExtensions();
        }
        if (hashtable4 != null && !hashtable4.isEmpty()) {
            boolean hasEncryptThenMACExtension = TlsExtensionsUtils.hasEncryptThenMACExtension(hashtable4);
            if (hasEncryptThenMACExtension && !TlsUtils.isBlockCipherSuite(securityParametersHandshake.getCipherSuite())) {
                throw new TlsFatalAlert((short) 47);
            }
            securityParametersHandshake.B = hasEncryptThenMACExtension;
            securityParametersHandshake.f14802e = DTLSProtocol.b(clientHandshakeState.f14724i, hashtable2, hashtable4, (short) 47);
            securityParametersHandshake.E = TlsExtensionsUtils.hasTruncatedHMacExtension(hashtable4);
            if (!clientHandshakeState.f14724i) {
                if (TlsUtils.hasExpectedEmptyExtensionData(hashtable4, TlsExtensionsUtils.EXT_status_request_v2, (short) 47)) {
                    securityParametersHandshake.T = 2;
                } else if (TlsUtils.hasExpectedEmptyExtensionData(hashtable4, TlsExtensionsUtils.EXT_status_request, (short) 47)) {
                    securityParametersHandshake.T = 1;
                }
            }
            if (!clientHandshakeState.f14724i && TlsUtils.hasExpectedEmptyExtensionData(hashtable4, TlsProtocol.f14863t, (short) 47)) {
                z = true;
            }
            clientHandshakeState.f14725j = z;
        }
        if (hashtable2 != null) {
            clientHandshakeState.f14716a.processServerExtensions(hashtable4);
        }
    }

    protected void u(ClientHandshakeState clientHandshakeState, byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        clientHandshakeState.f14726k.processServerKeyExchange(byteArrayInputStream);
        TlsProtocol.b(byteArrayInputStream);
    }

    protected void v(ClientHandshakeState clientHandshakeState, byte[] bArr) {
        clientHandshakeState.f14716a.processServerSupplementalData(TlsProtocol.M(new ByteArrayInputStream(bArr)));
    }

    protected void w(ClientHandshakeState clientHandshakeState, ProtocolVersion protocolVersion) {
        TlsClientContextImpl tlsClientContextImpl = clientHandshakeState.f14717b;
        SecurityParameters securityParametersHandshake = tlsClientContextImpl.getSecurityParametersHandshake();
        ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
        if (negotiatedVersion != null) {
            if (!negotiatedVersion.equals(protocolVersion)) {
                throw new TlsFatalAlert((short) 47);
            }
        } else if (!ProtocolVersion.contains(tlsClientContextImpl.getClientSupportedVersions(), protocolVersion)) {
            throw new TlsFatalAlert((short) 70);
        } else {
            securityParametersHandshake.S = protocolVersion;
            TlsUtils.f0(clientHandshakeState.f14717b, clientHandshakeState.f14716a);
        }
    }
}
