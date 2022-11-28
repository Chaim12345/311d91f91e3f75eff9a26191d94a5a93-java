package org.bouncycastle.jsse.provider;

import java.io.IOException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.security.auth.x500.X500Principal;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.http.message.TokenParser;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ocsp.OCSPResponse;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.bouncycastle.jsse.BCSNIHostName;
import org.bouncycastle.jsse.BCSNIMatcher;
import org.bouncycastle.jsse.BCSNIServerName;
import org.bouncycastle.jsse.BCX509ExtendedTrustManager;
import org.bouncycastle.jsse.BCX509Key;
import org.bouncycastle.jsse.java.security.BCCryptoPrimitive;
import org.bouncycastle.tls.AlertDescription;
import org.bouncycastle.tls.AlertLevel;
import org.bouncycastle.tls.Certificate;
import org.bouncycastle.tls.CertificateEntry;
import org.bouncycastle.tls.CertificateStatus;
import org.bouncycastle.tls.NamedGroup;
import org.bouncycastle.tls.ProtocolName;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.SecurityParameters;
import org.bouncycastle.tls.ServerName;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.TlsContext;
import org.bouncycastle.tls.TlsCredentialedDecryptor;
import org.bouncycastle.tls.TlsCredentialedSigner;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.TrustedAuthority;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaDefaultTlsCredentialedSigner;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCertificate;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto;
import org.bouncycastle.tls.crypto.impl.jcajce.JceDefaultTlsCredentialedDecryptor;
/* loaded from: classes3.dex */
abstract class JsseUtils {
    private static final boolean provTlsAllowLegacyMasterSecret = PropertyUtils.b("jdk.tls.allowLegacyMasterSecret", true);
    private static final boolean provTlsAllowLegacyResumption = PropertyUtils.b("jdk.tls.allowLegacyResumption", false);
    private static final int provTlsMaxCertificateChainLength = PropertyUtils.c("jdk.tls.maxCertificateChainLength", 10, 1, Integer.MAX_VALUE);
    private static final int provTlsMaxHandshakeMessageSize = PropertyUtils.c("jdk.tls.maxHandshakeMessageSize", 32768, 1024, Integer.MAX_VALUE);
    private static final boolean provTlsRequireCloseNotify = PropertyUtils.b("com.sun.net.ssl.requireCloseNotify", true);
    private static final boolean provTlsUseExtendedMasterSecret = PropertyUtils.b("jdk.tls.useExtendedMasterSecret", true);

    /* renamed from: a  reason: collision with root package name */
    static final Set f13896a = Collections.unmodifiableSet(EnumSet.of(BCCryptoPrimitive.KEY_AGREEMENT));

    /* renamed from: b  reason: collision with root package name */
    static final Set f13897b = Collections.unmodifiableSet(EnumSet.of(BCCryptoPrimitive.KEY_ENCAPSULATION));

    /* renamed from: c  reason: collision with root package name */
    static final Set f13898c = Collections.unmodifiableSet(EnumSet.of(BCCryptoPrimitive.SIGNATURE));

    /* renamed from: d  reason: collision with root package name */
    static String f13899d = "";

    /* renamed from: e  reason: collision with root package name */
    static X509Certificate[] f13900e = new X509Certificate[0];

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class BCUnknownServerName extends BCSNIServerName {
        /* JADX INFO: Access modifiers changed from: package-private */
        public BCUnknownServerName(int i2, byte[] bArr) {
            super(i2, bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int A() {
        return provTlsMaxCertificateChainLength;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int B() {
        return provTlsMaxHandshakeMessageSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1ObjectIdentifier C(PublicKey publicKey) {
        ASN1Encodable parameters;
        try {
            AlgorithmIdentifier algorithm = SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()).getAlgorithm();
            if (!X9ObjectIdentifiers.id_ecPublicKey.equals((ASN1Primitive) algorithm.getAlgorithm()) || (parameters = algorithm.getParameters()) == null) {
                return null;
            }
            ASN1Primitive aSN1Primitive = parameters.toASN1Primitive();
            if (aSN1Primitive instanceof ASN1ObjectIdentifier) {
                return (ASN1ObjectIdentifier) aSN1Primitive;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String D(PrivateKey privateKey) {
        String algorithm = privateKey.getAlgorithm();
        if ("RSA".equalsIgnoreCase(algorithm)) {
            if (PKCSObjectIdentifiers.id_RSASSA_PSS.equals((ASN1Primitive) PrivateKeyInfo.getInstance(privateKey.getEncoded()).getPrivateKeyAlgorithm().getAlgorithm())) {
                return "RSASSA-PSS";
            }
        }
        return algorithm;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List E(Vector vector) {
        if (vector == null || vector.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(vector.size());
        Iterator it = vector.iterator();
        while (it.hasNext()) {
            arrayList.add(((ProtocolName) it.next()).getUtf8Decoding());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Vector F(String[] strArr) {
        if (TlsUtils.isNullOrEmpty(strArr)) {
            return null;
        }
        Vector vector = new Vector(strArr.length);
        for (String str : strArr) {
            vector.add(ProtocolName.asUtf8Encoding(str));
        }
        return vector;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String G(PublicKey publicKey) {
        String algorithm = publicKey.getAlgorithm();
        if ("RSA".equalsIgnoreCase(algorithm)) {
            if (PKCSObjectIdentifiers.id_RSASSA_PSS.equals((ASN1Primitive) SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()).getAlgorithm().getAlgorithm())) {
                return "RSASSA-PSS";
            }
        }
        return algorithm;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
        if ((r1 instanceof org.bouncycastle.jsse.BCSNIHostName) == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0021, code lost:
        return (org.bouncycastle.jsse.BCSNIHostName) r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002b, code lost:
        return new org.bouncycastle.jsse.BCSNIHostName(r1.getEncoded());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static BCSNIHostName H(List list) {
        if (list != null) {
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BCSNIServerName bCSNIServerName = (BCSNIServerName) it.next();
                if (bCSNIServerName != null && bCSNIServerName.getType() == 0) {
                    break;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String I(String str, List list) {
        String[] f2 = SignatureSchemeInfo.f(list);
        StringBuilder sb = new StringBuilder(str);
        sb.append(AbstractJsonLexerKt.COLON);
        for (String str2 : f2) {
            sb.append(TokenParser.SP);
            sb.append(str2);
        }
        return sb.toString();
    }

    static byte[] J(OCSPResponse oCSPResponse) {
        return oCSPResponse == null ? TlsUtils.EMPTY_BYTES : oCSPResponse.getEncoded(ASN1Encoding.DER);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List K(CertificateStatus certificateStatus) {
        if (certificateStatus != null) {
            short statusType = certificateStatus.getStatusType();
            if (statusType != 1) {
                if (statusType != 2) {
                    return null;
                }
                Vector oCSPResponseList = certificateStatus.getOCSPResponseList();
                int size = oCSPResponseList.size();
                ArrayList arrayList = new ArrayList(size);
                for (int i2 = 0; i2 < size; i2++) {
                    arrayList.add(J((OCSPResponse) oCSPResponseList.elementAt(i2)));
                }
                return Collections.unmodifiableList(arrayList);
            }
            return Collections.singletonList(J(certificateStatus.getOCSPResponse()));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X500Principal L(JcaTlsCrypto jcaTlsCrypto, Certificate certificate) {
        if (certificate == null || certificate.isEmpty()) {
            return null;
        }
        try {
            return N(jcaTlsCrypto, certificate.getCertificateAt(0)).getSubjectX500Principal();
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X500Principal[] M(Vector vector) {
        if (vector == null || vector.isEmpty()) {
            return null;
        }
        int size = vector.size();
        X500Principal[] x500PrincipalArr = new X500Principal[size];
        for (int i2 = 0; i2 < size; i2++) {
            TrustedAuthority trustedAuthority = (TrustedAuthority) vector.get(i2);
            if (2 != trustedAuthority.getIdentifierType()) {
                return null;
            }
            x500PrincipalArr[i2] = Y(trustedAuthority.getX509Name());
        }
        return x500PrincipalArr;
    }

    static X509Certificate N(JcaTlsCrypto jcaTlsCrypto, TlsCertificate tlsCertificate) {
        return JcaTlsCertificate.convert(jcaTlsCrypto, tlsCertificate).getX509Certificate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X509Certificate[] O(JcaTlsCrypto jcaTlsCrypto, Certificate certificate) {
        if (certificate == null || certificate.isEmpty()) {
            return f13900e;
        }
        try {
            int length = certificate.getLength();
            X509Certificate[] x509CertificateArr = new X509Certificate[length];
            for (int i2 = 0; i2 < length; i2++) {
                x509CertificateArr[i2] = JcaTlsCertificate.convert(jcaTlsCrypto, certificate.getCertificateAt(i2)).getX509Certificate();
            }
            return x509CertificateArr;
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X509Certificate[] P(java.security.cert.Certificate[] certificateArr) {
        if (certificateArr == null) {
            return null;
        }
        if (certificateArr instanceof X509Certificate[]) {
            if (f(certificateArr)) {
                return null;
            }
            return (X509Certificate[]) certificateArr;
        }
        X509Certificate[] x509CertificateArr = new X509Certificate[certificateArr.length];
        for (int i2 = 0; i2 < certificateArr.length; i2++) {
            java.security.cert.Certificate certificate = certificateArr[i2];
            if (!(certificate instanceof X509Certificate)) {
                return null;
            }
            x509CertificateArr[i2] = (X509Certificate) certificate;
        }
        return x509CertificateArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean Q(String str) {
        return !R(str);
    }

    static boolean R(String str) {
        return str == null || str.length() < 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean S(String str) {
        ProtocolVersion j2 = ProvSSLContextSpi.j(str);
        return j2 != null && TlsUtils.isTLSv12(j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String T(String str) {
        if (R(str)) {
            return str;
        }
        int length = str.length();
        char[] cArr = new char[length];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            if (!Character.isWhitespace(charAt)) {
                cArr[i2] = charAt;
                i2++;
            }
        }
        return i2 == 0 ? f13899d : i2 == length ? str : new String(cArr, 0, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean U() {
        return provTlsRequireCloseNotify;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] V(String[] strArr, int i2) {
        return i2 < strArr.length ? i(strArr, i2) : strArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String W(String str) {
        return stripOuterChars(str, '\"', '\"');
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String X(String str) {
        return stripOuterChars(str, AbstractJsonLexerKt.BEGIN_LIST, AbstractJsonLexerKt.END_LIST);
    }

    static X500Principal Y(X500Name x500Name) {
        if (x500Name == null) {
            return null;
        }
        return new X500Principal(x500Name.getEncoded(ASN1Encoding.DER));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X500Principal[] Z(Vector vector) {
        if (vector == null) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int size = vector.size();
        for (int i2 = 0; i2 < size; i2++) {
            X500Principal Y = Y((X500Name) vector.get(i2));
            if (Y != null) {
                linkedHashSet.add(Y);
            }
        }
        return (X500Principal[]) linkedHashSet.toArray(new X500Principal[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a() {
        return provTlsAllowLegacyMasterSecret;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a0() {
        return provTlsUseExtendedMasterSecret;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean b() {
        return provTlsAllowLegacyResumption;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void c(ProvTlsManager provTlsManager) {
        if (!provTlsManager.getEnableSessionCreation()) {
            throw new IllegalStateException("Cannot resume session and session creation is disabled");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object[] d(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        return (Object[]) objArr.clone();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean e(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f(Object[] objArr) {
        for (Object obj : objArr) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }

    static BCSNIServerName g(ServerName serverName) {
        short nameType = serverName.getNameType();
        byte[] nameData = serverName.getNameData();
        return nameType != 0 ? new BCUnknownServerName(nameType, nameData) : new BCSNIHostName(nameData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List h(Vector vector) {
        if (vector == null || vector.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(vector.size());
        Enumeration elements = vector.elements();
        while (elements.hasMoreElements()) {
            arrayList.add(g((ServerName) elements.nextElement()));
        }
        return Collections.unmodifiableList(arrayList);
    }

    static String[] i(String[] strArr, int i2) {
        String[] strArr2 = new String[i2];
        System.arraycopy(strArr, 0, strArr2, 0, Math.min(strArr.length, i2));
        return strArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentialedDecryptor j(JcaTlsCrypto jcaTlsCrypto, BCX509Key bCX509Key) {
        return new JceDefaultTlsCredentialedDecryptor(jcaTlsCrypto, t(jcaTlsCrypto, bCX509Key.getCertificateChain()), bCX509Key.getPrivateKey());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentialedSigner k(TlsContext tlsContext, JcaTlsCrypto jcaTlsCrypto, BCX509Key bCX509Key, SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        return new JcaDefaultTlsCredentialedSigner(new TlsCryptoParameters(tlsContext), jcaTlsCrypto, bCX509Key.getPrivateKey(), t(jcaTlsCrypto, bCX509Key.getCertificateChain()), signatureAndHashAlgorithm);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentialedSigner l(TlsContext tlsContext, JcaTlsCrypto jcaTlsCrypto, BCX509Key bCX509Key, SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr) {
        return new JcaDefaultTlsCredentialedSigner(new TlsCryptoParameters(tlsContext), jcaTlsCrypto, bCX509Key.getPrivateKey(), u(jcaTlsCrypto, bCX509Key.getCertificateChain(), bArr), signatureAndHashAlgorithm);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean m(Object obj, Object obj2) {
        return obj == obj2 || !(obj == null || obj2 == null || !obj.equals(obj2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x000e, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static BCSNIServerName n(Vector vector, Collection collection) {
        if (vector.isEmpty()) {
            return null;
        }
        List h2 = h(vector);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            BCSNIMatcher bCSNIMatcher = (BCSNIMatcher) it.next();
            if (bCSNIMatcher != null) {
                int type = bCSNIMatcher.getType();
                Iterator it2 = h2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    BCSNIServerName bCSNIServerName = (BCSNIServerName) it2.next();
                    if (bCSNIServerName != null && bCSNIServerName.getType() == type) {
                        if (bCSNIMatcher.matches(bCSNIServerName)) {
                            return bCSNIServerName;
                        }
                    }
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String o(String str, short s2, short s3) {
        return str + " " + AlertLevel.getText(s2) + " " + AlertDescription.getText(s3) + " alert";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String p(SecurityParameters securityParameters) {
        if (securityParameters == null || !securityParameters.isApplicationProtocolSet()) {
            return null;
        }
        ProtocolName applicationProtocol = securityParameters.getApplicationProtocol();
        return applicationProtocol == null ? "" : applicationProtocol.getUtf8Decoding();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String q(short s2) {
        switch (s2) {
            case 1:
                return "RSA";
            case 2:
                return "DSA";
            case 3:
                return "EC";
            case 4:
            case 5:
            case 6:
                return "RSA";
            case 7:
                return EdDSAParameterSpec.Ed25519;
            case 8:
                return EdDSAParameterSpec.Ed448;
            case 9:
            case 10:
            case 11:
                return "RSASSA-PSS";
            default:
                switch (s2) {
                    case 26:
                    case 27:
                    case 28:
                        return "EC";
                    default:
                        throw new IllegalArgumentException();
                }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String r(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 3) {
                    if (i2 != 5) {
                        if (i2 != 7) {
                            if (i2 != 9) {
                                if (i2 != 22) {
                                    if (i2 != 23) {
                                        switch (i2) {
                                            case 16:
                                                return "ECDH_ECDSA";
                                            case 17:
                                                return "ECDHE_ECDSA";
                                            case 18:
                                                return "ECDH_RSA";
                                            case 19:
                                                return "ECDHE_RSA";
                                            default:
                                                throw new IllegalArgumentException();
                                        }
                                    }
                                    return "SRP_RSA";
                                }
                                return "SRP_DSS";
                            }
                            return "DH_RSA";
                        }
                        return "DH_DSS";
                    }
                    return "DHE_RSA";
                }
                return "DHE_DSS";
            }
            return "KE:RSA";
        }
        return "UNKNOWN";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Vector s(BCX509ExtendedTrustManager bCX509ExtendedTrustManager) {
        X509Certificate[] acceptedIssuers;
        HashSet<X500Principal> hashSet = new HashSet();
        for (X509Certificate x509Certificate : bCX509ExtendedTrustManager.getAcceptedIssuers()) {
            hashSet.add(x509Certificate.getBasicConstraints() >= 0 ? x509Certificate.getSubjectX500Principal() : x509Certificate.getIssuerX500Principal());
        }
        if (hashSet.isEmpty()) {
            return null;
        }
        Vector vector = new Vector(hashSet.size());
        for (X500Principal x500Principal : hashSet) {
            vector.add(X500Name.getInstance(x500Principal.getEncoded()));
        }
        return vector;
    }

    private static String stripOuterChars(String str, char c2, char c3) {
        int length;
        return (str == null || (length = str.length() - 1) <= 0 || str.charAt(0) != c2 || str.charAt(length) != c3) ? str : str.substring(1, length);
    }

    static Certificate t(JcaTlsCrypto jcaTlsCrypto, X509Certificate[] x509CertificateArr) {
        if (TlsUtils.isNullOrEmpty(x509CertificateArr)) {
            throw new IllegalArgumentException();
        }
        TlsCertificate[] tlsCertificateArr = new TlsCertificate[x509CertificateArr.length];
        for (int i2 = 0; i2 < x509CertificateArr.length; i2++) {
            tlsCertificateArr[i2] = new JcaTlsCertificate(jcaTlsCrypto, x509CertificateArr[i2]);
        }
        return new Certificate(tlsCertificateArr);
    }

    static Certificate u(JcaTlsCrypto jcaTlsCrypto, X509Certificate[] x509CertificateArr, byte[] bArr) {
        if (TlsUtils.isNullOrEmpty(x509CertificateArr)) {
            throw new IllegalArgumentException();
        }
        CertificateEntry[] certificateEntryArr = new CertificateEntry[x509CertificateArr.length];
        for (int i2 = 0; i2 < x509CertificateArr.length; i2++) {
            certificateEntryArr[i2] = new CertificateEntry(new JcaTlsCertificate(jcaTlsCrypto, x509CertificateArr[i2]), null);
        }
        return new Certificate(bArr, certificateEntryArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String v(String str, String str2) {
        if (str.endsWith("withRSAandMGF1")) {
            return str + ":" + str2;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String w(Key key) {
        return key instanceof PrivateKey ? D((PrivateKey) key) : key instanceof PublicKey ? G((PublicKey) key) : key.getAlgorithm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String x(String str, int i2) {
        if (i2 < 0) {
            return str;
        }
        return str + "/" + NamedGroup.getStandardName(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String y(short s2) {
        if (s2 != 1) {
            if (s2 != 2) {
                if (s2 == 64) {
                    return "EC";
                }
                throw new IllegalArgumentException();
            }
            return "DSA";
        }
        return "RSA";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String z(int i2) {
        return r(i2);
    }
}
