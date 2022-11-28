package org.bouncycastle.jsse.provider;

import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.jsse.BCSNIHostName;
import org.bouncycastle.jsse.BCSNIServerName;
import org.bouncycastle.jsse.BCX509Key;
import org.bouncycastle.tls.AbstractTlsClient;
import org.bouncycastle.tls.CertificateRequest;
import org.bouncycastle.tls.CertificateStatusRequest;
import org.bouncycastle.tls.CertificateStatusRequestItemV2;
import org.bouncycastle.tls.DefaultTlsClient;
import org.bouncycastle.tls.OCSPStatusRequest;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.SecurityParameters;
import org.bouncycastle.tls.ServerName;
import org.bouncycastle.tls.SessionParameters;
import org.bouncycastle.tls.SignatureAlgorithm;
import org.bouncycastle.tls.TlsAuthentication;
import org.bouncycastle.tls.TlsCredentials;
import org.bouncycastle.tls.TlsDHGroupVerifier;
import org.bouncycastle.tls.TlsExtensionsUtils;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsServerCertificate;
import org.bouncycastle.tls.TlsSession;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.TrustedAuthority;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.IPAddress;
import org.bouncycastle.util.encoders.Hex;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ProvTlsClient extends DefaultTlsClient implements ProvTlsPeer {
    private static final Logger LOG = Logger.getLogger(ProvTlsClient.class.getName());
    private static final boolean provClientEnableCA = PropertyUtils.b("jdk.tls.client.enableCAExtension", false);
    private static final boolean provClientEnableSessionResumption = PropertyUtils.b("org.bouncycastle.jsse.client.enableSessionResumption", true);
    private static final boolean provClientEnableStatusRequest = PropertyUtils.b("jdk.tls.client.enableStatusRequestExtension", true);
    private static final boolean provClientEnableTrustedCAKeys = PropertyUtils.b("org.bouncycastle.jsse.client.enableTrustedCAKeysExtension", false);
    private static final boolean provEnableSNIExtension = PropertyUtils.b("jsse.enableSNIExtension", true);

    /* renamed from: g  reason: collision with root package name */
    protected final ProvTlsManager f14011g;

    /* renamed from: h  reason: collision with root package name */
    protected final ProvSSLParameters f14012h;

    /* renamed from: i  reason: collision with root package name */
    protected final JsseSecurityParameters f14013i;

    /* renamed from: j  reason: collision with root package name */
    protected ProvSSLSession f14014j;

    /* renamed from: k  reason: collision with root package name */
    protected boolean f14015k;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvTlsClient(ProvTlsManager provTlsManager, ProvSSLParameters provSSLParameters) {
        super(provTlsManager.getContextData().d());
        this.f14013i = new JsseSecurityParameters();
        this.f14014j = null;
        this.f14015k = false;
        this.f14011g = provTlsManager;
        this.f14012h = provSSLParameters.b();
    }

    private void handleKeyManagerMisses(LinkedHashMap<String, SignatureSchemeInfo> linkedHashMap, String str) {
        for (Map.Entry<String, SignatureSchemeInfo> entry : linkedHashMap.entrySet()) {
            String key = entry.getKey();
            if (key.equals(str)) {
                return;
            }
            Logger logger = LOG;
            if (logger.isLoggable(Level.FINER)) {
                logger.finer("Client found no credentials for signature scheme '" + entry.getValue() + "' (keyType '" + key + "')");
            }
        }
    }

    @Override // org.bouncycastle.tls.DefaultTlsClient, org.bouncycastle.tls.AbstractTlsPeer
    protected int[] a() {
        return this.f14011g.getContextData().c().a(getCrypto(), this.f14012h, getProtocolVersions());
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public boolean allowLegacyResumption() {
        return JsseUtils.b();
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer
    protected ProtocolVersion[] b() {
        return this.f14011g.getContextData().c().b(this.f14012h);
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient
    protected Vector e() {
        if (provClientEnableCA) {
            return JsseUtils.s(this.f14011g.getContextData().i());
        }
        return null;
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient
    protected CertificateStatusRequest f() {
        if (provClientEnableStatusRequest) {
            return new CertificateStatusRequest((short) 1, new OCSPStatusRequest(null, null));
        }
        return null;
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient
    protected Vector g() {
        if (provClientEnableStatusRequest) {
            OCSPStatusRequest oCSPStatusRequest = new OCSPStatusRequest(null, null);
            Vector vector = new Vector(2);
            vector.add(new CertificateStatusRequestItemV2((short) 2, oCSPStatusRequest));
            vector.add(new CertificateStatusRequestItemV2((short) 1, oCSPStatusRequest));
            return vector;
        }
        return null;
    }

    @Override // org.bouncycastle.tls.TlsClient
    public TlsAuthentication getAuthentication() {
        return new TlsAuthentication() { // from class: org.bouncycastle.jsse.provider.ProvTlsClient.1
            @Override // org.bouncycastle.tls.TlsAuthentication
            public TlsCredentials getClientCredentials(CertificateRequest certificateRequest) {
                ContextData contextData = ProvTlsClient.this.f14011g.getContextData();
                SecurityParameters securityParametersHandshake = ((AbstractTlsClient) ProvTlsClient.this).f14665a.getSecurityParametersHandshake();
                ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
                boolean isTLSv13 = TlsUtils.isTLSv13(negotiatedVersion);
                Vector serverSigAlgs = securityParametersHandshake.getServerSigAlgs();
                Vector serverSigAlgsCert = securityParametersHandshake.getServerSigAlgsCert();
                ProvTlsClient.this.f14013i.f13892d = contextData.g(serverSigAlgs);
                JsseSecurityParameters jsseSecurityParameters = ProvTlsClient.this.f14013i;
                jsseSecurityParameters.f13893e = serverSigAlgs == serverSigAlgsCert ? jsseSecurityParameters.f13892d : contextData.g(serverSigAlgsCert);
                if (ProvTlsClient.LOG.isLoggable(Level.FINEST)) {
                    ProvTlsClient.LOG.finest(JsseUtils.I("Peer signature_algorithms", ProvTlsClient.this.f14013i.f13892d));
                    JsseSecurityParameters jsseSecurityParameters2 = ProvTlsClient.this.f14013i;
                    if (jsseSecurityParameters2.f13893e != jsseSecurityParameters2.f13892d) {
                        ProvTlsClient.LOG.finest(JsseUtils.I("Peer signature_algorithms_cert", ProvTlsClient.this.f14013i.f13893e));
                    }
                }
                if (DummyX509KeyManager.f13874a == contextData.h()) {
                    return null;
                }
                X500Principal[] Z = JsseUtils.Z(certificateRequest.getCertificateAuthorities());
                byte[] certificateRequestContext = certificateRequest.getCertificateRequestContext();
                if (isTLSv13 == (certificateRequestContext != null)) {
                    short[] certificateTypes = certificateRequest.getCertificateTypes();
                    if (isTLSv13 == (certificateTypes == null)) {
                        return isTLSv13 ? ProvTlsClient.this.u(Z, certificateRequestContext) : TlsUtils.isSignatureAlgorithmsExtensionAllowed(negotiatedVersion) ? ProvTlsClient.this.t(Z, certificateTypes) : ProvTlsClient.this.v(Z, certificateTypes);
                    }
                    throw new TlsFatalAlert((short) 80);
                }
                throw new TlsFatalAlert((short) 80);
            }

            @Override // org.bouncycastle.tls.TlsAuthentication
            public void notifyServerCertificate(TlsServerCertificate tlsServerCertificate) {
                if (tlsServerCertificate == null || tlsServerCertificate.getCertificate() == null || tlsServerCertificate.getCertificate().isEmpty()) {
                    throw new TlsFatalAlert((short) 40);
                }
                X509Certificate[] O = JsseUtils.O(ProvTlsClient.this.getCrypto(), tlsServerCertificate.getCertificate());
                String r2 = JsseUtils.r(((AbstractTlsClient) ProvTlsClient.this).f14665a.getSecurityParametersHandshake().getKeyExchangeAlgorithm());
                ProvTlsClient.this.f14013i.f13894f = JsseUtils.K(tlsServerCertificate.getCertificateStatus());
                ProvTlsClient.this.f14011g.checkServerTrusted(O, r2);
            }
        };
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public JcaTlsCrypto getCrypto() {
        return this.f14011g.getContextData().d();
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.TlsClient
    public TlsDHGroupVerifier getDHGroupVerifier() {
        return new ProvDHGroupVerifier();
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public int getMaxCertificateChainLength() {
        return JsseUtils.A();
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public int getMaxHandshakeMessageSize() {
        return JsseUtils.B();
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.TlsClient
    public TlsSession getSessionToResume() {
        TlsSession m2;
        SessionParameters s2;
        if (provClientEnableSessionResumption) {
            ProvSSLSession sessionToResume = this.f14012h.getSessionToResume();
            if (sessionToResume == null) {
                sessionToResume = this.f14011g.getContextData().b().e(this.f14011g.getPeerHost(), this.f14011g.getPeerPort());
            }
            if (sessionToResume != null && (s2 = s(sessionToResume, (m2 = sessionToResume.m()))) != null) {
                this.f14014j = sessionToResume;
                if (!this.f14011g.getEnableSessionCreation()) {
                    this.f14667c = new int[]{s2.getCipherSuite()};
                }
                return m2;
            }
        }
        JsseUtils.c(this.f14011g);
        return null;
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient
    protected Vector i() {
        return JsseUtils.F(this.f14012h.getApplicationProtocols());
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsPeer
    public synchronized boolean isHandshakeComplete() {
        return this.f14015k;
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient
    protected Vector j() {
        String peerHostSNI;
        if (provEnableSNIExtension) {
            List<BCSNIServerName> serverNames = this.f14012h.getServerNames();
            if (serverNames == null && (peerHostSNI = this.f14011g.getPeerHostSNI()) != null && peerHostSNI.indexOf(46) > 0 && !IPAddress.isValid(peerHostSNI)) {
                try {
                    serverNames = Collections.singletonList(new BCSNIHostName(peerHostSNI));
                } catch (RuntimeException unused) {
                    Logger logger = LOG;
                    logger.fine("Failed to add peer host as default SNI host_name: " + peerHostSNI);
                }
            }
            if (serverNames == null || serverNames.isEmpty()) {
                return null;
            }
            Vector vector = new Vector(serverNames.size());
            for (BCSNIServerName bCSNIServerName : serverNames) {
                vector.add(new ServerName((short) bCSNIServerName.getType(), bCSNIServerName.getEncoded()));
            }
            return vector;
        }
        return null;
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient
    protected Vector k(Vector vector) {
        return NamedGroupInfo.k(this.f14013i.f13889a);
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient
    protected Vector l() {
        List a2 = this.f14011g.getContextData().a(false, this.f14012h, getProtocolVersions(), this.f14013i.f13889a);
        JsseSecurityParameters jsseSecurityParameters = this.f14013i;
        jsseSecurityParameters.f13890b = a2;
        jsseSecurityParameters.f13891c = a2;
        return SignatureSchemeInfo.l(a2);
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient
    protected Vector m() {
        return null;
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient
    protected Vector n() {
        Vector s2;
        if (!provClientEnableTrustedCAKeys || (s2 = JsseUtils.s(this.f14011g.getContextData().i())) == null) {
            return null;
        }
        Vector vector = new Vector(s2.size());
        Iterator it = s2.iterator();
        while (it.hasNext()) {
            vector.add(new TrustedAuthority((short) 2, (X500Name) it.next()));
        }
        return vector;
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public void notifyAlertRaised(short s2, short s3, String str, Throwable th) {
        super.notifyAlertRaised(s2, s3, str, th);
        Level level = s2 == 1 ? Level.FINE : s3 == 80 ? Level.WARNING : Level.INFO;
        Logger logger = LOG;
        if (logger.isLoggable(level)) {
            String o2 = JsseUtils.o("Client raised", s2, s3);
            if (str != null) {
                o2 = o2 + ": " + str;
            }
            logger.log(level, o2, th);
        }
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public void notifyAlertReceived(short s2, short s3) {
        super.notifyAlertReceived(s2, s3);
        Level level = s2 == 1 ? Level.FINE : Level.INFO;
        Logger logger = LOG;
        if (logger.isLoggable(level)) {
            logger.log(level, JsseUtils.o("Client received", s2, s3));
        }
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public void notifyHandshakeBeginning() {
        super.notifyHandshakeBeginning();
        ContextData contextData = this.f14011g.getContextData();
        ProtocolVersion[] protocolVersions = getProtocolVersions();
        this.f14013i.f13889a = contextData.e(this.f14012h, protocolVersions);
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public synchronized void notifyHandshakeComplete() {
        super.notifyHandshakeComplete();
        boolean z = true;
        this.f14015k = true;
        TlsSession session = this.f14665a.getSession();
        ProvSSLSession provSSLSession = this.f14014j;
        if (provSSLSession == null || provSSLSession.m() != session) {
            ProvSSLSessionContext b2 = this.f14011g.getContextData().b();
            String peerHost = this.f14011g.getPeerHost();
            int peerPort = this.f14011g.getPeerPort();
            JsseSessionParameters jsseSessionParameters = new JsseSessionParameters(this.f14012h.getEndpointIdentificationAlgorithm(), null);
            if (!provClientEnableSessionResumption || TlsUtils.isTLSv13(this.f14665a)) {
                z = false;
            }
            this.f14014j = b2.h(peerHost, peerPort, session, jsseSessionParameters, z);
        }
        this.f14011g.notifyHandshakeComplete(new ProvSSLConnection(this.f14665a, this.f14014j));
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public void notifySecureRenegotiation(boolean z) {
        if (!z && !PropertyUtils.b("sun.security.ssl.allowLegacyHelloMessages", true)) {
            throw new TlsFatalAlert((short) 40);
        }
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.TlsClient
    public void notifySelectedCipherSuite(int i2) {
        String u = this.f14011g.getContextData().c().u(this.f14012h, i2);
        Logger logger = LOG;
        logger.fine("Client notified of selected cipher suite: " + u);
        super.notifySelectedCipherSuite(i2);
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.TlsClient
    public void notifyServerVersion(ProtocolVersion protocolVersion) {
        String v = this.f14011g.getContextData().c().v(this.f14012h, protocolVersion);
        Logger logger = LOG;
        logger.fine("Client notified of selected protocol version: " + v);
        super.notifyServerVersion(protocolVersion);
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.TlsClient
    public void notifySessionID(byte[] bArr) {
        ProvSSLSession provSSLSession;
        if ((TlsUtils.isNullOrEmpty(bArr) || (provSSLSession = this.f14014j) == null || !Arrays.areEqual(bArr, provSSLSession.getId())) ? false : true) {
            Logger logger = LOG;
            logger.fine("Server resumed session: " + Hex.toHexString(bArr));
        } else {
            this.f14014j = null;
            if (TlsUtils.isNullOrEmpty(bArr)) {
                LOG.fine("Server did not specify a session ID");
            } else {
                Logger logger2 = LOG;
                logger2.fine("Server specified new session: " + Hex.toHexString(bArr));
            }
            JsseUtils.c(this.f14011g);
        }
        ProvTlsManager provTlsManager = this.f14011g;
        provTlsManager.notifyHandshakeSession(provTlsManager.getContextData().b(), this.f14665a.getSecurityParametersHandshake(), this.f14013i, this.f14014j);
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.TlsClient
    public void notifySessionToResume(TlsSession tlsSession) {
        if (tlsSession == null) {
            JsseUtils.c(this.f14011g);
        }
        super.notifySessionToResume(tlsSession);
    }

    @Override // org.bouncycastle.tls.AbstractTlsClient, org.bouncycastle.tls.TlsClient
    public void processServerExtensions(Hashtable hashtable) {
        super.processServerExtensions(hashtable);
        if (this.f14665a.getSecurityParametersHandshake().getClientServerNames() != null) {
            boolean hasServerNameExtensionServer = TlsExtensionsUtils.hasServerNameExtensionServer(hashtable);
            Logger logger = LOG;
            logger.finer("Server accepted SNI?: " + hasServerNameExtensionServer);
        }
    }

    protected String[] r(short[] sArr) {
        String[] strArr = new String[sArr.length];
        for (int i2 = 0; i2 < sArr.length; i2++) {
            strArr[i2] = JsseUtils.y(sArr[i2]);
        }
        return strArr;
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public boolean requiresCloseNotify() {
        return JsseUtils.U();
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public boolean requiresExtendedMasterSecret() {
        return !JsseUtils.a();
    }

    protected SessionParameters s(ProvSSLSession provSSLSession, TlsSession tlsSession) {
        SessionParameters exportSessionParameters;
        if (tlsSession == null || !tlsSession.isResumable() || (exportSessionParameters = tlsSession.exportSessionParameters()) == null || !ProtocolVersion.contains(getProtocolVersions(), exportSessionParameters.getNegotiatedVersion()) || !Arrays.contains(getCipherSuites(), exportSessionParameters.getCipherSuite()) || TlsUtils.isTLSv13(exportSessionParameters.getNegotiatedVersion())) {
            return null;
        }
        String endpointIdentificationAlgorithm = this.f14012h.getEndpointIdentificationAlgorithm();
        if (endpointIdentificationAlgorithm != null) {
            String endpointIDAlgorithm = provSSLSession.l().getEndpointIDAlgorithm();
            if (!endpointIdentificationAlgorithm.equalsIgnoreCase(endpointIDAlgorithm)) {
                Logger logger = LOG;
                logger.finer("Session not resumable - endpoint ID algorithm mismatch; connection: " + endpointIdentificationAlgorithm + ", session: " + endpointIDAlgorithm);
                return null;
            }
        }
        return exportSessionParameters;
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public boolean shouldUseExtendedMasterSecret() {
        return JsseUtils.a0();
    }

    protected TlsCredentials t(Principal[] principalArr, short[] sArr) {
        Logger logger;
        String str;
        short clientCertificateType;
        LinkedHashMap<String, SignatureSchemeInfo> linkedHashMap = new LinkedHashMap<>();
        for (SignatureSchemeInfo signatureSchemeInfo : this.f14013i.f13892d) {
            String g2 = signatureSchemeInfo.g();
            if (!linkedHashMap.containsKey(g2) && (clientCertificateType = SignatureAlgorithm.getClientCertificateType(signatureSchemeInfo.i())) >= 0 && Arrays.contains(sArr, clientCertificateType) && this.f14013i.f13890b.contains(signatureSchemeInfo)) {
                linkedHashMap.put(g2, signatureSchemeInfo);
            }
        }
        if (linkedHashMap.isEmpty()) {
            logger = LOG;
            str = "Client (1.2) found no usable signature schemes";
        } else {
            BCX509Key chooseClientKey = this.f14011g.chooseClientKey((String[]) linkedHashMap.keySet().toArray(TlsUtils.EMPTY_STRINGS), principalArr);
            if (chooseClientKey != null) {
                String keyType = chooseClientKey.getKeyType();
                handleKeyManagerMisses(linkedHashMap, keyType);
                SignatureSchemeInfo signatureSchemeInfo2 = linkedHashMap.get(keyType);
                if (signatureSchemeInfo2 != null) {
                    Logger logger2 = LOG;
                    if (logger2.isLoggable(Level.FINE)) {
                        logger2.fine("Client (1.2) selected credentials for signature scheme '" + signatureSchemeInfo2 + "' (keyType '" + keyType + "'), with private key algorithm '" + JsseUtils.D(chooseClientKey.getPrivateKey()) + "'");
                    }
                    return JsseUtils.k(this.f14665a, getCrypto(), chooseClientKey, signatureSchemeInfo2.j());
                }
                throw new TlsFatalAlert((short) 80, "Key manager returned invalid key type");
            }
            handleKeyManagerMisses(linkedHashMap, null);
            logger = LOG;
            str = "Client (1.2) did not select any credentials";
        }
        logger.fine(str);
        return null;
    }

    protected TlsCredentials u(Principal[] principalArr, byte[] bArr) {
        Logger logger;
        String str;
        LinkedHashMap<String, SignatureSchemeInfo> linkedHashMap = new LinkedHashMap<>();
        for (SignatureSchemeInfo signatureSchemeInfo : this.f14013i.f13892d) {
            if (signatureSchemeInfo.s() && this.f14013i.f13890b.contains(signatureSchemeInfo)) {
                String h2 = signatureSchemeInfo.h();
                if (!linkedHashMap.containsKey(h2)) {
                    linkedHashMap.put(h2, signatureSchemeInfo);
                }
            }
        }
        if (linkedHashMap.isEmpty()) {
            logger = LOG;
            str = "Client (1.3) found no usable signature schemes";
        } else {
            BCX509Key chooseClientKey = this.f14011g.chooseClientKey((String[]) linkedHashMap.keySet().toArray(TlsUtils.EMPTY_STRINGS), principalArr);
            if (chooseClientKey != null) {
                String keyType = chooseClientKey.getKeyType();
                handleKeyManagerMisses(linkedHashMap, keyType);
                SignatureSchemeInfo signatureSchemeInfo2 = linkedHashMap.get(keyType);
                if (signatureSchemeInfo2 != null) {
                    Logger logger2 = LOG;
                    if (logger2.isLoggable(Level.FINE)) {
                        logger2.fine("Client (1.3) selected credentials for signature scheme '" + signatureSchemeInfo2 + "' (keyType '" + keyType + "'), with private key algorithm '" + JsseUtils.D(chooseClientKey.getPrivateKey()) + "'");
                    }
                    return JsseUtils.l(this.f14665a, getCrypto(), chooseClientKey, signatureSchemeInfo2.j(), bArr);
                }
                throw new TlsFatalAlert((short) 80, "Key manager returned invalid key type");
            }
            handleKeyManagerMisses(linkedHashMap, null);
            logger = LOG;
            str = "Client (1.3) did not select any credentials";
        }
        logger.fine(str);
        return null;
    }

    protected TlsCredentials v(Principal[] principalArr, short[] sArr) {
        BCX509Key chooseClientKey;
        String[] r2 = r(sArr);
        if (r2.length >= 1 && (chooseClientKey = this.f14011g.chooseClientKey(r2, principalArr)) != null) {
            return JsseUtils.k(this.f14665a, getCrypto(), chooseClientKey, null);
        }
        return null;
    }
}
