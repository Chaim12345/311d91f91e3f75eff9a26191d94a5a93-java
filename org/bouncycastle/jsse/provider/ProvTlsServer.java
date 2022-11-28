package org.bouncycastle.jsse.provider;

import java.math.BigInteger;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.jsse.BCSNIMatcher;
import org.bouncycastle.jsse.BCSNIServerName;
import org.bouncycastle.jsse.BCX509Key;
import org.bouncycastle.jsse.java.security.BCAlgorithmConstraints;
import org.bouncycastle.tls.AlertDescription;
import org.bouncycastle.tls.Certificate;
import org.bouncycastle.tls.CertificateRequest;
import org.bouncycastle.tls.CertificateStatus;
import org.bouncycastle.tls.DefaultTlsServer;
import org.bouncycastle.tls.ProtocolName;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.SecurityParameters;
import org.bouncycastle.tls.SessionParameters;
import org.bouncycastle.tls.TlsCredentials;
import org.bouncycastle.tls.TlsDHUtils;
import org.bouncycastle.tls.TlsExtensionsUtils;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsSession;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.DHGroup;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ProvTlsServer extends DefaultTlsServer implements ProvTlsPeer {
    private static final String PROPERTY_DEFAULT_DHE_PARAMETERS = "jdk.tls.server.defaultDHEParameters";
    private static final boolean provServerEnableStatusRequest = false;

    /* renamed from: p  reason: collision with root package name */
    protected final ProvTlsManager f14017p;

    /* renamed from: q  reason: collision with root package name */
    protected final ProvSSLParameters f14018q;

    /* renamed from: r  reason: collision with root package name */
    protected final JsseSecurityParameters f14019r;

    /* renamed from: s  reason: collision with root package name */
    protected ProvSSLSession f14020s;

    /* renamed from: t  reason: collision with root package name */
    protected BCSNIServerName f14021t;
    protected Set u;
    protected TlsCredentials v;
    protected boolean w;
    private static final Logger LOG = Logger.getLogger(ProvTlsServer.class.getName());
    private static final int provEphemeralDHKeySize = PropertyUtils.c("jdk.tls.ephemeralDHKeySize", 2048, 1024, 8192);
    private static final DHGroup[] provServerDefaultDHEParameters = getDefaultDHEParameters();
    private static final boolean provServerEnableCA = PropertyUtils.b("jdk.tls.server.enableCAExtension", true);
    private static final boolean provServerEnableSessionResumption = PropertyUtils.b("org.bouncycastle.jsse.server.enableSessionResumption", true);
    private static final boolean provServerEnableTrustedCAKeys = PropertyUtils.b("org.bouncycastle.jsse.server.enableTrustedCAKeysExtension", false);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvTlsServer(ProvTlsManager provTlsManager, ProvSSLParameters provSSLParameters) {
        super(provTlsManager.getContextData().d());
        this.f14019r = new JsseSecurityParameters();
        this.f14020s = null;
        this.f14021t = null;
        this.u = null;
        this.v = null;
        this.w = false;
        this.f14017p = provTlsManager;
        this.f14018q = provSSLParameters.b();
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0087 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static DHGroup[] getDefaultDHEParameters() {
        String W;
        int length;
        int i2;
        int indexOf;
        int i3;
        int indexOf2;
        String h2 = PropertyUtils.h(PROPERTY_DEFAULT_DHE_PARAMETERS);
        if (h2 != null && (length = (W = JsseUtils.W(JsseUtils.T(h2))).length()) >= 1) {
            ArrayList arrayList = new ArrayList();
            int i4 = -1;
            do {
                int i5 = i4 + 1;
                if (i5 >= length || '{' != W.charAt(i5) || (indexOf = W.indexOf(44, (i2 = i5 + 1))) <= i2 || (indexOf2 = W.indexOf(125, (i3 = indexOf + 1))) <= i3) {
                    break;
                }
                try {
                    BigInteger parseDHParameter = parseDHParameter(W, i2, indexOf);
                    BigInteger parseDHParameter2 = parseDHParameter(W, i3, indexOf2);
                    DHGroup standardGroupForDHParameters = TlsDHUtils.getStandardGroupForDHParameters(parseDHParameter, parseDHParameter2);
                    if (standardGroupForDHParameters == null) {
                        if (parseDHParameter.isProbablePrime(120)) {
                            standardGroupForDHParameters = new DHGroup(parseDHParameter, null, parseDHParameter2, 0);
                        } else {
                            Logger logger = LOG;
                            Level level = Level.WARNING;
                            logger.log(level, "Non-prime modulus ignored in security property [jdk.tls.server.defaultDHEParameters]: " + parseDHParameter.toString(16));
                            i4 = indexOf2 + 1;
                            if (i4 < length) {
                                return (DHGroup[]) arrayList.toArray(new DHGroup[arrayList.size()]);
                            }
                        }
                    }
                    arrayList.add(standardGroupForDHParameters);
                    i4 = indexOf2 + 1;
                    if (i4 < length) {
                    }
                } catch (Exception unused) {
                }
            } while (',' == W.charAt(i4));
            LOG.log(Level.WARNING, "Invalid syntax for security property [jdk.tls.server.defaultDHEParameters]");
            return null;
        }
        return null;
    }

    private void handleKeyManagerMisses(LinkedHashMap<String, SignatureSchemeInfo> linkedHashMap, String str) {
        for (Map.Entry<String, SignatureSchemeInfo> entry : linkedHashMap.entrySet()) {
            String key = entry.getKey();
            if (key.equals(str)) {
                return;
            }
            this.u.add(key);
            Logger logger = LOG;
            if (logger.isLoggable(Level.FINER)) {
                logger.finer("Server found no credentials for signature scheme '" + entry.getValue() + "' (keyType '" + key + "')");
            }
        }
    }

    private static BigInteger parseDHParameter(String str, int i2, int i3) {
        return new BigInteger(str.substring(i2, i3), 16);
    }

    protected TlsCredentials A(Principal[] principalArr, int i2) {
        int keyExchangeAlgorithm = TlsUtils.getKeyExchangeAlgorithm(i2);
        if (keyExchangeAlgorithm != 0) {
            if (keyExchangeAlgorithm == 1 || keyExchangeAlgorithm == 3 || keyExchangeAlgorithm == 5 || keyExchangeAlgorithm == 17 || keyExchangeAlgorithm == 19) {
                return (1 == keyExchangeAlgorithm || !TlsUtils.isSignatureAlgorithmsExtensionAllowed(this.f14673a.getServerVersion())) ? D(principalArr, keyExchangeAlgorithm) : B(principalArr, keyExchangeAlgorithm);
            }
            return null;
        }
        return C(principalArr, TlsUtils.EMPTY_BYTES);
    }

    protected TlsCredentials B(Principal[] principalArr, int i2) {
        Logger logger;
        StringBuilder sb;
        String str;
        BCAlgorithmConstraints algorithmConstraints = this.f14018q.getAlgorithmConstraints();
        short legacySignatureAlgorithmServer = TlsUtils.getLegacySignatureAlgorithmServer(i2);
        LinkedHashMap<String, SignatureSchemeInfo> linkedHashMap = new LinkedHashMap<>();
        for (SignatureSchemeInfo signatureSchemeInfo : this.f14019r.f13892d) {
            if (TlsUtils.isValidSignatureSchemeForServerKeyExchange(signatureSchemeInfo.m(), i2)) {
                String z = legacySignatureAlgorithmServer == signatureSchemeInfo.i() ? JsseUtils.z(i2) : signatureSchemeInfo.g();
                if (!this.u.contains(z) && !linkedHashMap.containsKey(z) && signatureSchemeInfo.o(algorithmConstraints, false, true, this.f14019r.f13889a)) {
                    linkedHashMap.put(z, signatureSchemeInfo);
                }
            }
        }
        if (linkedHashMap.isEmpty()) {
            logger = LOG;
            sb = new StringBuilder();
            str = "Server (1.2) has no key types to try for KeyExchangeAlgorithm ";
        } else {
            BCX509Key chooseServerKey = this.f14017p.chooseServerKey((String[]) linkedHashMap.keySet().toArray(TlsUtils.EMPTY_STRINGS), principalArr);
            if (chooseServerKey != null) {
                String keyType = chooseServerKey.getKeyType();
                handleKeyManagerMisses(linkedHashMap, keyType);
                SignatureSchemeInfo signatureSchemeInfo2 = linkedHashMap.get(keyType);
                if (signatureSchemeInfo2 != null) {
                    Logger logger2 = LOG;
                    if (logger2.isLoggable(Level.FINE)) {
                        logger2.fine("Server (1.2) selected credentials for signature scheme '" + signatureSchemeInfo2 + "' (keyType '" + keyType + "'), with private key algorithm '" + JsseUtils.D(chooseServerKey.getPrivateKey()) + "'");
                    }
                    return JsseUtils.k(this.f14673a, getCrypto(), chooseServerKey, signatureSchemeInfo2.j());
                }
                throw new TlsFatalAlert((short) 80, "Key manager returned invalid key type");
            }
            handleKeyManagerMisses(linkedHashMap, null);
            logger = LOG;
            sb = new StringBuilder();
            str = "Server (1.2) did not select any credentials for KeyExchangeAlgorithm ";
        }
        sb.append(str);
        sb.append(i2);
        logger.fine(sb.toString());
        return null;
    }

    protected TlsCredentials C(Principal[] principalArr, byte[] bArr) {
        Logger logger;
        String str;
        BCAlgorithmConstraints algorithmConstraints = this.f14018q.getAlgorithmConstraints();
        LinkedHashMap<String, SignatureSchemeInfo> linkedHashMap = new LinkedHashMap<>();
        for (SignatureSchemeInfo signatureSchemeInfo : this.f14019r.f13892d) {
            String h2 = signatureSchemeInfo.h();
            if (!this.u.contains(h2) && !linkedHashMap.containsKey(h2) && signatureSchemeInfo.o(algorithmConstraints, true, false, this.f14019r.f13889a)) {
                linkedHashMap.put(h2, signatureSchemeInfo);
            }
        }
        if (linkedHashMap.isEmpty()) {
            logger = LOG;
            str = "Server (1.3) found no usable signature schemes";
        } else {
            BCX509Key chooseServerKey = this.f14017p.chooseServerKey((String[]) linkedHashMap.keySet().toArray(TlsUtils.EMPTY_STRINGS), principalArr);
            if (chooseServerKey != null) {
                String keyType = chooseServerKey.getKeyType();
                handleKeyManagerMisses(linkedHashMap, keyType);
                SignatureSchemeInfo signatureSchemeInfo2 = linkedHashMap.get(keyType);
                if (signatureSchemeInfo2 != null) {
                    Logger logger2 = LOG;
                    if (logger2.isLoggable(Level.FINE)) {
                        logger2.fine("Server (1.3) selected credentials for signature scheme '" + signatureSchemeInfo2 + "' (keyType '" + keyType + "'), with private key algorithm '" + JsseUtils.D(chooseServerKey.getPrivateKey()) + "'");
                    }
                    return JsseUtils.l(this.f14673a, getCrypto(), chooseServerKey, signatureSchemeInfo2.j(), bArr);
                }
                throw new TlsFatalAlert((short) 80, "Key manager returned invalid key type");
            }
            handleKeyManagerMisses(linkedHashMap, null);
            logger = LOG;
            str = "Server (1.3) did not select any credentials";
        }
        logger.fine(str);
        return null;
    }

    protected TlsCredentials D(Principal[] principalArr, int i2) {
        String z = JsseUtils.z(i2);
        if (this.u.contains(z)) {
            return null;
        }
        BCX509Key chooseServerKey = this.f14017p.chooseServerKey(new String[]{z}, principalArr);
        if (chooseServerKey != null) {
            return 1 == i2 ? JsseUtils.j(getCrypto(), chooseServerKey) : JsseUtils.k(this.f14673a, getCrypto(), chooseServerKey, null);
        }
        this.u.add(z);
        return null;
    }

    @Override // org.bouncycastle.tls.DefaultTlsServer, org.bouncycastle.tls.AbstractTlsPeer
    protected int[] a() {
        return this.f14017p.getContextData().c().a(getCrypto(), this.f14018q, getProtocolVersions());
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public boolean allowLegacyResumption() {
        return JsseUtils.b();
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer
    protected ProtocolVersion[] b() {
        return this.f14017p.getContextData().c().b(this.f14018q);
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected boolean c() {
        return false;
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected boolean e() {
        return false;
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected boolean g() {
        return this.f14019r.f13895g != null;
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public CertificateRequest getCertificateRequest() {
        if (y()) {
            ContextData contextData = this.f14017p.getContextData();
            ProtocolVersion serverVersion = this.f14673a.getServerVersion();
            List a2 = contextData.a(true, this.f14018q, new ProtocolVersion[]{serverVersion}, this.f14019r.f13889a);
            JsseSecurityParameters jsseSecurityParameters = this.f14019r;
            jsseSecurityParameters.f13890b = a2;
            jsseSecurityParameters.f13891c = a2;
            Vector l2 = SignatureSchemeInfo.l(a2);
            Vector s2 = provServerEnableCA ? JsseUtils.s(contextData.i()) : null;
            if (TlsUtils.isTLSv13(serverVersion)) {
                byte[] bArr = TlsUtils.EMPTY_BYTES;
                JsseSecurityParameters jsseSecurityParameters2 = this.f14019r;
                List list = jsseSecurityParameters2.f13890b;
                List list2 = jsseSecurityParameters2.f13891c;
                return new CertificateRequest(bArr, l2, list != list2 ? SignatureSchemeInfo.l(list2) : null, s2);
            }
            return new CertificateRequest(new short[]{64, 1, 2}, l2, s2);
        }
        return null;
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public CertificateStatus getCertificateStatus() {
        return null;
    }

    @Override // org.bouncycastle.tls.DefaultTlsServer, org.bouncycastle.tls.TlsServer
    public TlsCredentials getCredentials() {
        return this.v;
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public JcaTlsCrypto getCrypto() {
        return this.f14017p.getContextData().d();
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public int getMaxCertificateChainLength() {
        return JsseUtils.A();
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public int getMaxHandshakeMessageSize() {
        return JsseUtils.B();
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public byte[] getNewSessionID() {
        if (!provServerEnableSessionResumption || TlsUtils.isTLSv13(this.f14673a)) {
            return null;
        }
        return this.f14673a.getNonceGenerator().generateNonce(32);
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public int getSelectedCipherSuite() {
        ContextData contextData = this.f14017p.getContextData();
        SecurityParameters securityParametersHandshake = this.f14673a.getSecurityParametersHandshake();
        NamedGroupInfo.s(this.f14019r.f13889a, securityParametersHandshake.getClientSupportedGroups());
        Vector clientSigAlgs = securityParametersHandshake.getClientSigAlgs();
        Vector clientSigAlgsCert = securityParametersHandshake.getClientSigAlgsCert();
        this.f14019r.f13892d = contextData.g(clientSigAlgs);
        JsseSecurityParameters jsseSecurityParameters = this.f14019r;
        jsseSecurityParameters.f13893e = clientSigAlgs == clientSigAlgsCert ? jsseSecurityParameters.f13892d : contextData.g(clientSigAlgsCert);
        Logger logger = LOG;
        if (logger.isLoggable(Level.FINEST)) {
            logger.finest(JsseUtils.I("Peer signature_algorithms", this.f14019r.f13892d));
            JsseSecurityParameters jsseSecurityParameters2 = this.f14019r;
            List list = jsseSecurityParameters2.f13893e;
            if (list != jsseSecurityParameters2.f13892d) {
                logger.finest(JsseUtils.I("Peer signature_algorithms_cert", list));
            }
        }
        if (DummyX509KeyManager.f13874a != contextData.h()) {
            this.u = new HashSet();
            int selectedCipherSuite = super.getSelectedCipherSuite();
            this.u = null;
            String u = this.f14017p.getContextData().c().u(this.f14018q, selectedCipherSuite);
            logger.fine("Server selected cipher suite: " + u);
            return selectedCipherSuite;
        }
        throw new TlsFatalAlert((short) 40);
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public Hashtable<Integer, byte[]> getServerExtensions() {
        super.getServerExtensions();
        if (this.f14021t != null) {
            TlsExtensionsUtils.addServerNameExtensionServer(this.f14687o);
        }
        return this.f14687o;
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public ProtocolVersion getServerVersion() {
        ProtocolVersion serverVersion = super.getServerVersion();
        String v = this.f14017p.getContextData().c().v(this.f14018q, serverVersion);
        Logger logger = LOG;
        logger.fine("Server selected protocol version: " + v);
        return serverVersion;
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public TlsSession getSessionToResume(byte[] bArr) {
        ProvSSLSession f2;
        ProvSSLSessionContext f3 = this.f14017p.getContextData().f();
        if (provServerEnableSessionResumption && (f2 = f3.f(bArr)) != null) {
            TlsSession m2 = f2.m();
            if (z(f2, m2)) {
                this.f14020s = f2;
                return m2;
            }
        }
        JsseUtils.c(this.f14017p);
        return null;
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public int[] getSupportedGroups() {
        this.f14019r.f13889a = this.f14017p.getContextData().e(this.f14018q, new ProtocolVersion[]{this.f14673a.getServerVersion()});
        return NamedGroupInfo.l(this.f14019r.f13889a);
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected int h() {
        return NamedGroupInfo.g(this.f14019r.f13889a);
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected int i() {
        int h2 = NamedGroupInfo.h(this.f14019r.f13889a);
        if (h2 >= provEphemeralDHKeySize) {
            return h2;
        }
        return 0;
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsPeer
    public synchronized boolean isHandshakeComplete() {
        return this.w;
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected Vector j() {
        return JsseUtils.F(this.f14018q.getApplicationProtocols());
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected boolean l() {
        return this.f14018q.getUseCipherSuitesOrder();
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected boolean m(int i2) {
        TlsCredentials A = A(this.f14019r.f13895g, i2);
        if (A != null) {
            boolean m2 = super.m(i2);
            if (m2) {
                this.v = A;
            }
            return m2;
        }
        String d2 = ProvSSLContextSpi.d(i2);
        Logger logger = LOG;
        logger.finer("Server found no credentials for cipher suite: " + d2);
        return false;
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected int n(int i2) {
        return NamedGroupInfo.u(this.f14019r.f13889a, Math.max(i2, provEphemeralDHKeySize));
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public void notifyAlertRaised(short s2, short s3, String str, Throwable th) {
        Level level = s2 == 1 ? Level.FINE : s3 == 80 ? Level.WARNING : Level.INFO;
        Logger logger = LOG;
        if (logger.isLoggable(level)) {
            String o2 = JsseUtils.o("Server raised", s2, s3);
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
            logger.log(level, JsseUtils.o("Server received", s2, s3));
        }
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public void notifyClientCertificate(Certificate certificate) {
        if (!y()) {
            throw new TlsFatalAlert((short) 80);
        }
        if (certificate == null || certificate.isEmpty()) {
            if (this.f14018q.getNeedClientAuth()) {
                throw new TlsFatalAlert(TlsUtils.isTLSv13(this.f14673a) ? AlertDescription.certificate_required : (short) 40);
            }
            return;
        }
        X509Certificate[] O = JsseUtils.O(getCrypto(), certificate);
        TlsCertificate certificateAt = certificate.getCertificateAt(0);
        short legacySignatureAlgorithm = certificateAt.supportsSignatureAlgorithm((short) 7) ? (short) 7 : certificateAt.supportsSignatureAlgorithm((short) 8) ? (short) 8 : certificateAt.getLegacySignatureAlgorithm();
        if (legacySignatureAlgorithm < 0) {
            throw new TlsFatalAlert((short) 43);
        }
        this.f14017p.checkClientTrusted(O, JsseUtils.q(legacySignatureAlgorithm));
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public synchronized void notifyHandshakeComplete() {
        super.notifyHandshakeComplete();
        boolean z = true;
        this.w = true;
        TlsSession session = this.f14673a.getSession();
        ProvSSLSession provSSLSession = this.f14020s;
        if (provSSLSession == null || provSSLSession.m() != session) {
            ProvSSLSessionContext f2 = this.f14017p.getContextData().f();
            String peerHost = this.f14017p.getPeerHost();
            int peerPort = this.f14017p.getPeerPort();
            JsseSessionParameters jsseSessionParameters = new JsseSessionParameters(null, this.f14021t);
            if (!provServerEnableSessionResumption || TlsUtils.isTLSv13(this.f14673a) || !this.f14673a.getSecurityParametersConnection().isExtendedMasterSecret()) {
                z = false;
            }
            this.f14020s = f2.h(peerHost, peerPort, session, jsseSessionParameters, z);
        }
        this.f14017p.notifyHandshakeComplete(new ProvSSLConnection(this.f14673a, this.f14020s));
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public void notifySecureRenegotiation(boolean z) {
        if (!z && !PropertyUtils.b("sun.security.ssl.allowLegacyHelloMessages", true)) {
            throw new TlsFatalAlert((short) 40);
        }
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public void notifySession(TlsSession tlsSession) {
        Logger logger;
        String str;
        byte[] sessionID = tlsSession.getSessionID();
        ProvSSLSession provSSLSession = this.f14020s;
        if (provSSLSession != null && provSSLSession.m() == tlsSession) {
            LOG.fine("Server resumed session: " + Hex.toHexString(sessionID));
        } else {
            this.f14020s = null;
            if (TlsUtils.isNullOrEmpty(sessionID)) {
                logger = LOG;
                str = "Server did not specify a session ID";
            } else {
                logger = LOG;
                str = "Server specified new session: " + Hex.toHexString(sessionID);
            }
            logger.fine(str);
            JsseUtils.c(this.f14017p);
        }
        ProvTlsManager provTlsManager = this.f14017p;
        provTlsManager.notifyHandshakeSession(provTlsManager.getContextData().f(), this.f14673a.getSecurityParametersHandshake(), this.f14019r, this.f14020s);
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected int o(int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected int p(int i2) {
        return NamedGroupInfo.t(this.f14019r.f13889a, i2);
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer, org.bouncycastle.tls.TlsServer
    public void processClientExtensions(Hashtable hashtable) {
        Logger logger;
        String str;
        super.processClientExtensions(hashtable);
        Vector clientServerNames = this.f14673a.getSecurityParametersHandshake().getClientServerNames();
        if (clientServerNames != null) {
            Collection<BCSNIMatcher> sNIMatchers = this.f14018q.getSNIMatchers();
            if (sNIMatchers == null || sNIMatchers.isEmpty()) {
                logger = LOG;
                str = "Server ignored SNI (no matchers specified)";
            } else {
                BCSNIServerName n2 = JsseUtils.n(clientServerNames, sNIMatchers);
                this.f14021t = n2;
                if (n2 == null) {
                    throw new TlsFatalAlert(AlertDescription.unrecognized_name);
                }
                logger = LOG;
                str = "Server accepted SNI: " + this.f14021t;
            }
            logger.fine(str);
        }
        if (TlsUtils.isTLSv13(this.f14673a)) {
            this.f14019r.f13895g = JsseUtils.Z(TlsExtensionsUtils.getCertificateAuthoritiesExtension(hashtable));
        } else if (provServerEnableTrustedCAKeys) {
            this.f14019r.f13895g = JsseUtils.M(this.f14683k);
        }
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected int q(int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected ProtocolName r() {
        if (this.f14018q.getEngineAPSelector() == null && this.f14018q.getSocketAPSelector() == null) {
            return super.r();
        }
        List E = JsseUtils.E(this.f14685m);
        String selectApplicationProtocol = this.f14017p.selectApplicationProtocol(Collections.unmodifiableList(E));
        if (selectApplicationProtocol != null) {
            if (selectApplicationProtocol.length() < 1) {
                return null;
            }
            if (E.contains(selectApplicationProtocol)) {
                return ProtocolName.asUtf8Encoding(selectApplicationProtocol);
            }
            throw new TlsFatalAlert(AlertDescription.no_application_protocol);
        }
        throw new TlsFatalAlert(AlertDescription.no_application_protocol);
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public boolean requiresCloseNotify() {
        return JsseUtils.U();
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public boolean requiresExtendedMasterSecret() {
        return !JsseUtils.a();
    }

    @Override // org.bouncycastle.tls.AbstractTlsPeer, org.bouncycastle.tls.TlsPeer
    public boolean shouldUseExtendedMasterSecret() {
        return JsseUtils.a0();
    }

    @Override // org.bouncycastle.tls.AbstractTlsServer
    protected boolean t() {
        return this.f14018q.getEngineAPSelector() == null && this.f14018q.getSocketAPSelector() == null;
    }

    protected boolean y() {
        return this.f14018q.getNeedClientAuth() || this.f14018q.getWantClientAuth();
    }

    protected boolean z(ProvSSLSession provSSLSession, TlsSession tlsSession) {
        SessionParameters exportSessionParameters;
        if (tlsSession != null && tlsSession.isResumable()) {
            ProtocolVersion negotiatedVersion = this.f14673a.getSecurityParametersHandshake().getNegotiatedVersion();
            if (!TlsUtils.isTLSv13(negotiatedVersion) && (exportSessionParameters = tlsSession.exportSessionParameters()) != null && negotiatedVersion.equals(exportSessionParameters.getNegotiatedVersion()) && Arrays.contains(getCipherSuites(), exportSessionParameters.getCipherSuite()) && Arrays.contains(this.f14676d, exportSessionParameters.getCipherSuite()) && exportSessionParameters.isExtendedMasterSecret()) {
                JsseSessionParameters l2 = provSSLSession.l();
                BCSNIServerName bCSNIServerName = this.f14021t;
                BCSNIServerName matchedSNIServerName = l2.getMatchedSNIServerName();
                if (JsseUtils.m(bCSNIServerName, matchedSNIServerName)) {
                    return true;
                }
                Logger logger = LOG;
                logger.finest("Session not resumable - SNI mismatch; connection: " + bCSNIServerName + ", session: " + matchedSNIServerName);
                return false;
            }
            return false;
        }
        return false;
    }
}
