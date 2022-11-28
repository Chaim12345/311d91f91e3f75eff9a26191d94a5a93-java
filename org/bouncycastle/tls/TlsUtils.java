package org.bouncycastle.tls;

import com.google.common.base.Ascii;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import okhttp3.internal.ws.WebSocketProtocol;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.bsi.BSIObjectIdentifiers;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.asn1.eac.EACObjectIdentifiers;
import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSASSAPSSparams;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.tls.Certificate;
import org.bouncycastle.tls.OfferedPsks;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsCipher;
import org.bouncycastle.tls.crypto.TlsCrypto;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsCryptoUtils;
import org.bouncycastle.tls.crypto.TlsDHConfig;
import org.bouncycastle.tls.crypto.TlsECConfig;
import org.bouncycastle.tls.crypto.TlsEncryptor;
import org.bouncycastle.tls.crypto.TlsHash;
import org.bouncycastle.tls.crypto.TlsHashOutputStream;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
import org.bouncycastle.tls.crypto.TlsStreamVerifier;
import org.bouncycastle.tls.crypto.TlsVerifier;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Shorts;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes4.dex */
public class TlsUtils {
    private static byte[] DOWNGRADE_TLS11 = Hex.decodeStrict("444F574E47524400");
    private static byte[] DOWNGRADE_TLS12 = Hex.decodeStrict("444F574E47524401");
    private static final Hashtable CERT_SIG_ALG_OIDS = createCertSigAlgOIDs();
    private static final Vector DEFAULT_SUPPORTED_SIG_ALGS = createDefaultSupportedSigAlgs();
    public static final byte[] EMPTY_BYTES = new byte[0];
    public static final short[] EMPTY_SHORTS = new short[0];
    public static final int[] EMPTY_INTS = new int[0];
    public static final long[] EMPTY_LONGS = new long[0];
    public static final String[] EMPTY_STRINGS = new String[0];

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void A(SecurityParameters securityParameters, Hashtable hashtable) {
        securityParameters.J = TlsExtensionsUtils.getSignatureAlgorithmsExtension(hashtable);
        securityParameters.K = TlsExtensionsUtils.getSignatureAlgorithmsCertExtension(hashtable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void A0(TlsHandshakeHash tlsHandshakeHash, Vector vector) {
        if (vector != null) {
            for (int i2 = 0; i2 < vector.size(); i2++) {
                SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) vector.elementAt(i2);
                int cryptoHashAlgorithm = SignatureScheme.getCryptoHashAlgorithm(SignatureScheme.from(signatureAndHashAlgorithm));
                if (cryptoHashAlgorithm >= 0) {
                    tlsHandshakeHash.trackHashAlgorithm(cryptoHashAlgorithm);
                } else if (8 == signatureAndHashAlgorithm.getHash()) {
                    tlsHandshakeHash.forceBuffering();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentials B(TlsServer tlsServer) {
        return H0(tlsServer.getCredentials());
    }

    static int[] B0(int[] iArr, int i2) {
        if (i2 >= iArr.length) {
            return iArr;
        }
        int[] iArr2 = new int[i2];
        System.arraycopy(iArr, 0, iArr2, 0, i2);
        return iArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void C(SecurityParameters securityParameters, CertificateRequest certificateRequest) {
        securityParameters.H = certificateRequest.getCertificateTypes();
        securityParameters.M = certificateRequest.getSupportedSignatureAlgorithms();
        securityParameters.N = certificateRequest.getSupportedSignatureAlgorithmsCert();
        if (securityParameters.getServerSigAlgsCert() == null) {
            securityParameters.N = securityParameters.getServerSigAlgs();
        }
    }

    static short[] C0(short[] sArr, int i2) {
        if (i2 >= sArr.length) {
            return sArr;
        }
        short[] sArr2 = new short[i2];
        System.arraycopy(sArr, 0, sArr2, 0, i2);
        return sArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DigitallySigned D(TlsContext tlsContext, TlsCredentialedSigner tlsCredentialedSigner, TlsHandshakeHash tlsHandshakeHash) {
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = tlsCredentialedSigner.getSignatureAndHashAlgorithm();
        if (signatureAndHashAlgorithm != null) {
            return new DigitallySigned(signatureAndHashAlgorithm, generate13CertificateVerify(tlsContext.getCrypto(), tlsCredentialedSigner, tlsContext.isServer() ? "TLS 1.3, server CertificateVerify" : "TLS 1.3, client CertificateVerify", tlsHandshakeHash, signatureAndHashAlgorithm));
        }
        throw new TlsFatalAlert((short) 80);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void D0(TlsContext tlsContext) {
        update13TrafficSecret(tlsContext, tlsContext.isServer());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DigitallySigned E(TlsClientContext tlsClientContext, TlsCredentialedSigner tlsCredentialedSigner, TlsStreamSigner tlsStreamSigner, TlsHandshakeHash tlsHandshakeHash) {
        byte[] generateRawSignature;
        SecurityParameters securityParametersHandshake = tlsClientContext.getSecurityParametersHandshake();
        ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
        if (isTLSv13(negotiatedVersion)) {
            throw new TlsFatalAlert((short) 80);
        }
        SignatureAndHashAlgorithm R = R(negotiatedVersion, tlsCredentialedSigner);
        if (tlsStreamSigner != null) {
            tlsHandshakeHash.copyBufferTo(tlsStreamSigner.getOutputStream());
            generateRawSignature = tlsStreamSigner.getSignature();
        } else {
            generateRawSignature = tlsCredentialedSigner.generateRawSignature(R == null ? securityParametersHandshake.getSessionHash() : tlsHandshakeHash.getFinalHash(SignatureScheme.getCryptoHashAlgorithm(SignatureScheme.from(R))));
        }
        return new DigitallySigned(R, generateRawSignature);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void E0(TlsContext tlsContext) {
        update13TrafficSecret(tlsContext, !tlsContext.isServer());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void F(TlsContext tlsContext, TlsCredentialedSigner tlsCredentialedSigner, byte[] bArr, DigestInputBuffer digestInputBuffer) {
        byte[] generateRawSignature;
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = getSignatureAndHashAlgorithm(tlsContext, tlsCredentialedSigner);
        TlsStreamSigner streamSigner = tlsCredentialedSigner.getStreamSigner();
        if (streamSigner != null) {
            y0(tlsContext, bArr, digestInputBuffer, streamSigner.getOutputStream());
            generateRawSignature = streamSigner.getSignature();
        } else {
            generateRawSignature = tlsCredentialedSigner.generateRawSignature(k(tlsContext, signatureAndHashAlgorithm, bArr, digestInputBuffer));
        }
        new DigitallySigned(signatureAndHashAlgorithm, generateRawSignature).encode(digestInputBuffer);
    }

    static TlsCredentialedSigner F0(TlsCredentials tlsCredentials) {
        if (tlsCredentials == null) {
            return null;
        }
        if (tlsCredentials instanceof TlsCredentialedSigner) {
            return (TlsCredentialedSigner) tlsCredentials;
        }
        throw new TlsFatalAlert((short) 80);
    }

    static SignatureAndHashAlgorithm G(TlsCertificate tlsCertificate, TlsCertificate tlsCertificate2) {
        String sigAlgOID = tlsCertificate.getSigAlgOID();
        if (sigAlgOID != null) {
            if (PKCSObjectIdentifiers.id_RSASSA_PSS.getId().equals(sigAlgOID)) {
                RSASSAPSSparams rSASSAPSSparams = RSASSAPSSparams.getInstance(tlsCertificate.getSigAlgParams());
                if (rSASSAPSSparams != null) {
                    ASN1ObjectIdentifier algorithm = rSASSAPSSparams.getHashAlgorithm().getAlgorithm();
                    if (NISTObjectIdentifiers.id_sha256.equals((ASN1Primitive) algorithm)) {
                        if (tlsCertificate2.supportsSignatureAlgorithmCA((short) 9)) {
                            return SignatureAndHashAlgorithm.rsa_pss_pss_sha256;
                        }
                        if (tlsCertificate2.supportsSignatureAlgorithmCA((short) 4)) {
                            return SignatureAndHashAlgorithm.rsa_pss_rsae_sha256;
                        }
                        return null;
                    } else if (NISTObjectIdentifiers.id_sha384.equals((ASN1Primitive) algorithm)) {
                        if (tlsCertificate2.supportsSignatureAlgorithmCA((short) 10)) {
                            return SignatureAndHashAlgorithm.rsa_pss_pss_sha384;
                        }
                        if (tlsCertificate2.supportsSignatureAlgorithmCA((short) 5)) {
                            return SignatureAndHashAlgorithm.rsa_pss_rsae_sha384;
                        }
                        return null;
                    } else if (NISTObjectIdentifiers.id_sha512.equals((ASN1Primitive) algorithm)) {
                        if (tlsCertificate2.supportsSignatureAlgorithmCA((short) 11)) {
                            return SignatureAndHashAlgorithm.rsa_pss_pss_sha512;
                        }
                        if (tlsCertificate2.supportsSignatureAlgorithmCA((short) 6)) {
                            return SignatureAndHashAlgorithm.rsa_pss_rsae_sha512;
                        }
                        return null;
                    } else {
                        return null;
                    }
                }
                return null;
            }
            return (SignatureAndHashAlgorithm) CERT_SIG_ALG_OIDS.get(sigAlgOID);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CertificateRequest G0(CertificateRequest certificateRequest, TlsKeyExchange tlsKeyExchange) {
        short[] clientCertificateTypes = tlsKeyExchange.getClientCertificateTypes();
        if (isNullOrEmpty(clientCertificateTypes)) {
            throw new TlsFatalAlert((short) 10);
        }
        CertificateRequest j0 = j0(certificateRequest, clientCertificateTypes);
        if (j0 != null) {
            return j0;
        }
        throw new TlsFatalAlert((short) 47);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] H(TlsHandshakeHash tlsHandshakeHash) {
        return tlsHandshakeHash.forkPRFHash().calculateHash();
    }

    static TlsCredentials H0(TlsCredentials tlsCredentials) {
        if (tlsCredentials == null || (tlsCredentials instanceof TlsCredentialedAgreement ? 1 : 0) + 0 + (tlsCredentials instanceof TlsCredentialedDecryptor ? 1 : 0) + (tlsCredentials instanceof TlsCredentialedSigner ? 1 : 0) == 1) {
            return tlsCredentials;
        }
        throw new TlsFatalAlert((short) 80);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static int I(SecurityParameters securityParameters, int i2) {
        ProtocolVersion negotiatedVersion = securityParameters.getNegotiatedVersion();
        boolean isTLSv13 = isTLSv13(negotiatedVersion);
        boolean z = !isTLSv13 && isTLSv12(negotiatedVersion);
        boolean isSSL = negotiatedVersion.isSSL();
        switch (i2) {
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
                break;
            default:
                switch (i2) {
                    case 103:
                    case 104:
                    case 105:
                    case 106:
                    case 107:
                    case 108:
                    case 109:
                        break;
                    default:
                        switch (i2) {
                            case CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256 /* 156 */:
                            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256 /* 158 */:
                            case CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256 /* 160 */:
                            case CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256 /* 162 */:
                            case CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256 /* 164 */:
                            case CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256 /* 166 */:
                            case CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256 /* 168 */:
                            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256 /* 170 */:
                            case CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256 /* 172 */:
                                break;
                            case CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384 /* 157 */:
                            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 /* 159 */:
                            case CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384 /* 161 */:
                            case CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384 /* 163 */:
                            case CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384 /* 165 */:
                            case CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384 /* 167 */:
                            case CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384 /* 169 */:
                            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384 /* 171 */:
                            case CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384 /* 173 */:
                                if (z) {
                                    return 3;
                                }
                                throw new TlsFatalAlert((short) 47);
                            default:
                                switch (i2) {
                                    case CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384 /* 175 */:
                                    case CipherSuite.TLS_PSK_WITH_NULL_SHA384 /* 177 */:
                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384 /* 179 */:
                                    case CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384 /* 181 */:
                                    case CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384 /* 183 */:
                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384 /* 49208 */:
                                    case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA384 /* 49211 */:
                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49301 */:
                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49303 */:
                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49305 */:
                                        if (isTLSv13) {
                                            throw new TlsFatalAlert((short) 47);
                                        }
                                        if (z) {
                                            return 3;
                                        }
                                        return isSSL ? 0 : 1;
                                    case CipherSuite.TLS_RSA_WITH_ARIA_128_CBC_SHA256 /* 49212 */:
                                    case CipherSuite.TLS_DH_DSS_WITH_ARIA_128_CBC_SHA256 /* 49214 */:
                                    case CipherSuite.TLS_DH_RSA_WITH_ARIA_128_CBC_SHA256 /* 49216 */:
                                    case CipherSuite.TLS_DHE_DSS_WITH_ARIA_128_CBC_SHA256 /* 49218 */:
                                    case CipherSuite.TLS_DHE_RSA_WITH_ARIA_128_CBC_SHA256 /* 49220 */:
                                    case CipherSuite.TLS_DH_anon_WITH_ARIA_128_CBC_SHA256 /* 49222 */:
                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_128_CBC_SHA256 /* 49224 */:
                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_128_CBC_SHA256 /* 49226 */:
                                    case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_128_CBC_SHA256 /* 49228 */:
                                    case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_128_CBC_SHA256 /* 49230 */:
                                    case CipherSuite.TLS_RSA_WITH_ARIA_128_GCM_SHA256 /* 49232 */:
                                    case CipherSuite.TLS_DHE_RSA_WITH_ARIA_128_GCM_SHA256 /* 49234 */:
                                    case CipherSuite.TLS_DH_RSA_WITH_ARIA_128_GCM_SHA256 /* 49236 */:
                                    case CipherSuite.TLS_DHE_DSS_WITH_ARIA_128_GCM_SHA256 /* 49238 */:
                                    case CipherSuite.TLS_DH_DSS_WITH_ARIA_128_GCM_SHA256 /* 49240 */:
                                    case CipherSuite.TLS_DH_anon_WITH_ARIA_128_GCM_SHA256 /* 49242 */:
                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_128_GCM_SHA256 /* 49244 */:
                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_128_GCM_SHA256 /* 49246 */:
                                    case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_128_GCM_SHA256 /* 49248 */:
                                    case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_128_GCM_SHA256 /* 49250 */:
                                    case CipherSuite.TLS_PSK_WITH_ARIA_128_CBC_SHA256 /* 49252 */:
                                    case CipherSuite.TLS_DHE_PSK_WITH_ARIA_128_CBC_SHA256 /* 49254 */:
                                    case CipherSuite.TLS_RSA_PSK_WITH_ARIA_128_CBC_SHA256 /* 49256 */:
                                    case CipherSuite.TLS_PSK_WITH_ARIA_128_GCM_SHA256 /* 49258 */:
                                    case CipherSuite.TLS_DHE_PSK_WITH_ARIA_128_GCM_SHA256 /* 49260 */:
                                    case CipherSuite.TLS_RSA_PSK_WITH_ARIA_128_GCM_SHA256 /* 49262 */:
                                    case CipherSuite.TLS_ECDHE_PSK_WITH_ARIA_128_CBC_SHA256 /* 49264 */:
                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49266 */:
                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49268 */:
                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49270 */:
                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49272 */:
                                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49274 */:
                                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49276 */:
                                    case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49278 */:
                                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49280 */:
                                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49282 */:
                                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_GCM_SHA256 /* 49284 */:
                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49286 */:
                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49288 */:
                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49290 */:
                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49292 */:
                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49294 */:
                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49296 */:
                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49298 */:
                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CCM_SHA256 /* 53253 */:
                                        break;
                                    case CipherSuite.TLS_RSA_WITH_ARIA_256_CBC_SHA384 /* 49213 */:
                                    case CipherSuite.TLS_DH_DSS_WITH_ARIA_256_CBC_SHA384 /* 49215 */:
                                    case CipherSuite.TLS_DH_RSA_WITH_ARIA_256_CBC_SHA384 /* 49217 */:
                                    case CipherSuite.TLS_DHE_DSS_WITH_ARIA_256_CBC_SHA384 /* 49219 */:
                                    case CipherSuite.TLS_DHE_RSA_WITH_ARIA_256_CBC_SHA384 /* 49221 */:
                                    case CipherSuite.TLS_DH_anon_WITH_ARIA_256_CBC_SHA384 /* 49223 */:
                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_256_CBC_SHA384 /* 49225 */:
                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_256_CBC_SHA384 /* 49227 */:
                                    case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_256_CBC_SHA384 /* 49229 */:
                                    case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_256_CBC_SHA384 /* 49231 */:
                                    case CipherSuite.TLS_RSA_WITH_ARIA_256_GCM_SHA384 /* 49233 */:
                                    case CipherSuite.TLS_DHE_RSA_WITH_ARIA_256_GCM_SHA384 /* 49235 */:
                                    case CipherSuite.TLS_DH_RSA_WITH_ARIA_256_GCM_SHA384 /* 49237 */:
                                    case CipherSuite.TLS_DHE_DSS_WITH_ARIA_256_GCM_SHA384 /* 49239 */:
                                    case CipherSuite.TLS_DH_DSS_WITH_ARIA_256_GCM_SHA384 /* 49241 */:
                                    case CipherSuite.TLS_DH_anon_WITH_ARIA_256_GCM_SHA384 /* 49243 */:
                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_256_GCM_SHA384 /* 49245 */:
                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_256_GCM_SHA384 /* 49247 */:
                                    case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_256_GCM_SHA384 /* 49249 */:
                                    case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_256_GCM_SHA384 /* 49251 */:
                                    case CipherSuite.TLS_PSK_WITH_ARIA_256_CBC_SHA384 /* 49253 */:
                                    case CipherSuite.TLS_DHE_PSK_WITH_ARIA_256_CBC_SHA384 /* 49255 */:
                                    case CipherSuite.TLS_RSA_PSK_WITH_ARIA_256_CBC_SHA384 /* 49257 */:
                                    case CipherSuite.TLS_PSK_WITH_ARIA_256_GCM_SHA384 /* 49259 */:
                                    case CipherSuite.TLS_DHE_PSK_WITH_ARIA_256_GCM_SHA384 /* 49261 */:
                                    case CipherSuite.TLS_RSA_PSK_WITH_ARIA_256_GCM_SHA384 /* 49263 */:
                                    case CipherSuite.TLS_ECDHE_PSK_WITH_ARIA_256_CBC_SHA384 /* 49265 */:
                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49267 */:
                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49269 */:
                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49271 */:
                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49273 */:
                                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49275 */:
                                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49277 */:
                                    case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49279 */:
                                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49281 */:
                                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49283 */:
                                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_GCM_SHA384 /* 49285 */:
                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49287 */:
                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49289 */:
                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49291 */:
                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49293 */:
                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49295 */:
                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49297 */:
                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49299 */:
                                        break;
                                    default:
                                        switch (i2) {
                                            case CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA384 /* 185 */:
                                                break;
                                            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 186 */:
                                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256 /* 187 */:
                                            case 188:
                                            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256 /* 189 */:
                                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 190 */:
                                            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256 /* 191 */:
                                            case 192:
                                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256 /* 193 */:
                                            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 194 */:
                                            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256 /* 195 */:
                                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 196 */:
                                            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256 /* 197 */:
                                                break;
                                            case CipherSuite.TLS_SM4_GCM_SM3 /* 198 */:
                                            case CipherSuite.TLS_SM4_CCM_SM3 /* 199 */:
                                                if (isTLSv13) {
                                                    return 7;
                                                }
                                                throw new TlsFatalAlert((short) 47);
                                            default:
                                                switch (i2) {
                                                    case CipherSuite.TLS_AES_128_GCM_SHA256 /* 4865 */:
                                                    case CipherSuite.TLS_CHACHA20_POLY1305_SHA256 /* 4867 */:
                                                    case CipherSuite.TLS_AES_128_CCM_SHA256 /* 4868 */:
                                                    case CipherSuite.TLS_AES_128_CCM_8_SHA256 /* 4869 */:
                                                        if (isTLSv13) {
                                                            return 4;
                                                        }
                                                        throw new TlsFatalAlert((short) 47);
                                                    case CipherSuite.TLS_AES_256_GCM_SHA384 /* 4866 */:
                                                        if (isTLSv13) {
                                                            return 5;
                                                        }
                                                        throw new TlsFatalAlert((short) 47);
                                                    default:
                                                        switch (i2) {
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 /* 49187 */:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 /* 49189 */:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 /* 49191 */:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 /* 49193 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 /* 49195 */:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 /* 49197 */:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 /* 49199 */:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 /* 49201 */:
                                                                break;
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 /* 49188 */:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 /* 49190 */:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 /* 49192 */:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 /* 49194 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 /* 49196 */:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 /* 49198 */:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 /* 49200 */:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 /* 49202 */:
                                                                break;
                                                            default:
                                                                switch (i2) {
                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49307 */:
                                                                        break;
                                                                    case CipherSuite.TLS_RSA_WITH_AES_128_CCM /* 49308 */:
                                                                    case CipherSuite.TLS_RSA_WITH_AES_256_CCM /* 49309 */:
                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM /* 49310 */:
                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM /* 49311 */:
                                                                    case CipherSuite.TLS_RSA_WITH_AES_128_CCM_8 /* 49312 */:
                                                                    case CipherSuite.TLS_RSA_WITH_AES_256_CCM_8 /* 49313 */:
                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8 /* 49314 */:
                                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8 /* 49315 */:
                                                                    case CipherSuite.TLS_PSK_WITH_AES_128_CCM /* 49316 */:
                                                                    case CipherSuite.TLS_PSK_WITH_AES_256_CCM /* 49317 */:
                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM /* 49318 */:
                                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM /* 49319 */:
                                                                    case CipherSuite.TLS_PSK_WITH_AES_128_CCM_8 /* 49320 */:
                                                                    case CipherSuite.TLS_PSK_WITH_AES_256_CCM_8 /* 49321 */:
                                                                    case CipherSuite.TLS_PSK_DHE_WITH_AES_128_CCM_8 /* 49322 */:
                                                                    case CipherSuite.TLS_PSK_DHE_WITH_AES_256_CCM_8 /* 49323 */:
                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM /* 49324 */:
                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM /* 49325 */:
                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8 /* 49326 */:
                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8 /* 49327 */:
                                                                        break;
                                                                    default:
                                                                        switch (i2) {
                                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52392 */:
                                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 /* 52393 */:
                                                                            case CipherSuite.TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52394 */:
                                                                            case CipherSuite.TLS_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52395 */:
                                                                            case CipherSuite.TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52396 */:
                                                                            case CipherSuite.TLS_DHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52397 */:
                                                                            case CipherSuite.TLS_RSA_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52398 */:
                                                                                break;
                                                                            default:
                                                                                switch (i2) {
                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_GCM_SHA256 /* 53249 */:
                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CCM_8_SHA256 /* 53251 */:
                                                                                        break;
                                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_GCM_SHA384 /* 53250 */:
                                                                                        break;
                                                                                    default:
                                                                                        if (isTLSv13) {
                                                                                            throw new TlsFatalAlert((short) 47);
                                                                                        }
                                                                                        if (z) {
                                                                                            return 2;
                                                                                        }
                                                                                        return isSSL ? 0 : 1;
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
        if (z) {
            return 2;
        }
        throw new TlsFatalAlert((short) 47);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void I0(TlsServerContext tlsServerContext, CertificateRequest certificateRequest, DigitallySigned digitallySigned, TlsHandshakeHash tlsHandshakeHash) {
        SecurityParameters securityParametersHandshake = tlsServerContext.getSecurityParametersHandshake();
        TlsCertificate certificateAt = securityParametersHandshake.getPeerCertificate().getCertificateAt(0);
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        verifySupportedSignatureAlgorithm(securityParametersHandshake.getServerSigAlgs(), algorithm);
        try {
            if (!verify13CertificateVerify(tlsServerContext.getCrypto(), digitallySigned, certificateAt.createVerifier(SignatureScheme.from(algorithm)), "TLS 1.3, client CertificateVerify", tlsHandshakeHash)) {
                throw new TlsFatalAlert((short) 51);
            }
        } catch (TlsFatalAlert e2) {
            throw e2;
        } catch (Exception e3) {
            throw new TlsFatalAlert((short) 51, (Throwable) e3);
        }
    }

    static int J(int i2) {
        if (i2 == 198 || i2 == 199) {
            return 7;
        }
        switch (i2) {
            case CipherSuite.TLS_AES_128_GCM_SHA256 /* 4865 */:
            case CipherSuite.TLS_CHACHA20_POLY1305_SHA256 /* 4867 */:
            case CipherSuite.TLS_AES_128_CCM_SHA256 /* 4868 */:
            case CipherSuite.TLS_AES_128_CCM_8_SHA256 /* 4869 */:
                return 4;
            case CipherSuite.TLS_AES_256_GCM_SHA384 /* 4866 */:
                return 5;
            default:
                return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void J0(TlsClientContext tlsClientContext, DigitallySigned digitallySigned, TlsHandshakeHash tlsHandshakeHash) {
        SecurityParameters securityParametersHandshake = tlsClientContext.getSecurityParametersHandshake();
        TlsCertificate certificateAt = securityParametersHandshake.getPeerCertificate().getCertificateAt(0);
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        verifySupportedSignatureAlgorithm(securityParametersHandshake.getClientSigAlgs(), algorithm);
        try {
            if (!verify13CertificateVerify(tlsClientContext.getCrypto(), digitallySigned, certificateAt.createVerifier(SignatureScheme.from(algorithm)), "TLS 1.3, server CertificateVerify", tlsHandshakeHash)) {
                throw new TlsFatalAlert((short) 51);
            }
        } catch (TlsFatalAlert e2) {
            throw e2;
        } catch (Exception e3) {
            throw new TlsFatalAlert((short) 51, (Throwable) e3);
        }
    }

    static int[] K(int[] iArr) {
        int[] iArr2 = new int[Math.min(3, iArr.length)];
        int i2 = 0;
        for (int i3 : iArr) {
            int J = J(i3);
            if (J >= 0 && !Arrays.contains(iArr2, J)) {
                iArr2[i2] = J;
                i2++;
            }
        }
        return B0(iArr2, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void K0(TlsServerContext tlsServerContext, CertificateRequest certificateRequest, DigitallySigned digitallySigned, TlsHandshakeHash tlsHandshakeHash) {
        short signature;
        boolean verifyRawSignature;
        SecurityParameters securityParametersHandshake = tlsServerContext.getSecurityParametersHandshake();
        TlsCertificate certificateAt = securityParametersHandshake.getPeerCertificate().getCertificateAt(0);
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm == null) {
            signature = certificateAt.getLegacySignatureAlgorithm();
            short legacyClientCertType = getLegacyClientCertType(signature);
            if (legacyClientCertType < 0 || !Arrays.contains(certificateRequest.getCertificateTypes(), legacyClientCertType)) {
                throw new TlsFatalAlert((short) 43);
            }
        } else {
            signature = algorithm.getSignature();
            if (!b0(signature, certificateRequest.getCertificateTypes())) {
                throw new TlsFatalAlert((short) 47);
            }
            verifySupportedSignatureAlgorithm(securityParametersHandshake.getServerSigAlgs(), algorithm);
        }
        try {
            TlsVerifier createVerifier = certificateAt.createVerifier(signature);
            TlsStreamVerifier streamVerifier = createVerifier.getStreamVerifier(digitallySigned);
            if (streamVerifier != null) {
                tlsHandshakeHash.copyBufferTo(streamVerifier.getOutputStream());
                verifyRawSignature = streamVerifier.isVerified();
            } else {
                verifyRawSignature = createVerifier.verifyRawSignature(digitallySigned, isTLSv12(tlsServerContext) ? tlsHandshakeHash.getFinalHash(SignatureScheme.getCryptoHashAlgorithm(SignatureScheme.from(algorithm))) : securityParametersHandshake.getSessionHash());
            }
            if (!verifyRawSignature) {
                throw new TlsFatalAlert((short) 51);
            }
        } catch (TlsFatalAlert e2) {
            throw e2;
        } catch (Exception e3) {
            throw new TlsFatalAlert((short) 51, (Throwable) e3);
        }
    }

    static TlsSecret L(TlsCrypto tlsCrypto, TlsPSK tlsPSK) {
        int hashForPRF = TlsCryptoUtils.getHashForPRF(tlsPSK.getPRFAlgorithm());
        return tlsCrypto.hkdfInit(hashForPRF).hkdfExtract(hashForPRF, tlsPSK.getKey());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void L0(TlsContext tlsContext, InputStream inputStream, TlsCertificate tlsCertificate, byte[] bArr, DigestInputBuffer digestInputBuffer) {
        short s2;
        boolean verifyRawSignature;
        DigitallySigned parse = DigitallySigned.parse(tlsContext, inputStream);
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        int keyExchangeAlgorithm = securityParametersHandshake.getKeyExchangeAlgorithm();
        SignatureAndHashAlgorithm algorithm = parse.getAlgorithm();
        if (algorithm == null) {
            s2 = getLegacySignatureAlgorithmServer(keyExchangeAlgorithm);
        } else {
            short signature = algorithm.getSignature();
            if (!c0(signature, keyExchangeAlgorithm)) {
                throw new TlsFatalAlert((short) 47);
            }
            verifySupportedSignatureAlgorithm(securityParametersHandshake.getClientSigAlgs(), algorithm);
            s2 = signature;
        }
        TlsVerifier createVerifier = tlsCertificate.createVerifier(s2);
        TlsStreamVerifier streamVerifier = createVerifier.getStreamVerifier(parse);
        if (streamVerifier != null) {
            y0(tlsContext, bArr, digestInputBuffer, streamVerifier.getOutputStream());
            verifyRawSignature = streamVerifier.isVerified();
        } else {
            verifyRawSignature = createVerifier.verifyRawSignature(parse, k(tlsContext, algorithm, bArr, digestInputBuffer));
        }
        if (!verifyRawSignature) {
            throw new TlsFatalAlert((short) 51);
        }
    }

    static TlsSecret[] M(TlsCrypto tlsCrypto, TlsPSK[] tlsPSKArr) {
        int length = tlsPSKArr.length;
        TlsSecret[] tlsSecretArr = new TlsSecret[length];
        for (int i2 = 0; i2 < length; i2++) {
            tlsSecretArr[i2] = L(tlsCrypto, tlsPSKArr[i2]);
        }
        return tlsSecretArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void M0(ProtocolVersion protocolVersion, byte[] bArr) {
        byte[] bArr2;
        ProtocolVersion equivalentTLSVersion = protocolVersion.getEquivalentTLSVersion();
        if (ProtocolVersion.TLSv12 == equivalentTLSVersion) {
            bArr2 = DOWNGRADE_TLS12;
        } else if (!equivalentTLSVersion.isEqualOrEarlierVersionOf(ProtocolVersion.TLSv11)) {
            throw new TlsFatalAlert((short) 80);
        } else {
            bArr2 = DOWNGRADE_TLS11;
        }
        System.arraycopy(bArr2, 0, bArr, bArr.length - bArr2.length, bArr2.length);
    }

    static TlsPSKExternal[] N(TlsClient tlsClient, int[] iArr) {
        Vector externalPSKs = tlsClient.getExternalPSKs();
        if (isNullOrEmpty(externalPSKs)) {
            return null;
        }
        int[] K = K(iArr);
        int size = externalPSKs.size();
        TlsPSKExternal[] tlsPSKExternalArr = new TlsPSKExternal[size];
        for (int i2 = 0; i2 < size; i2++) {
            Object elementAt = externalPSKs.elementAt(i2);
            if (!(elementAt instanceof TlsPSKExternal)) {
                throw new TlsFatalAlert((short) 80, "External PSKs element is not a TlsPSKExternal");
            }
            TlsPSKExternal tlsPSKExternal = (TlsPSKExternal) elementAt;
            if (!Arrays.contains(K, tlsPSKExternal.getPRFAlgorithm())) {
                throw new TlsFatalAlert((short) 80, "External PSK incompatible with offered cipher suites");
            }
            tlsPSKExternalArr[i2] = tlsPSKExternal;
        }
        return tlsPSKExternalArr;
    }

    static void N0(TlsContext tlsContext, byte[] bArr, OutputStream outputStream) {
        if (isSSL(tlsContext)) {
            SSL3Utils.d(bArr, outputStream);
        } else {
            writeOpaque16(bArr, outputStream);
        }
    }

    static Vector O(TlsPSK[] tlsPSKArr, int i2) {
        Vector vector = new Vector(tlsPSKArr.length);
        for (int i3 = 0; i3 < tlsPSKArr.length; i3++) {
            if (tlsPSKArr[i3].getPRFAlgorithm() == i2) {
                vector.add(Integers.valueOf(i3));
            }
        }
        return vector;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] P(TlsSession tlsSession) {
        byte[] sessionID;
        return (tlsSession == null || (sessionID = tlsSession.getSessionID()) == null || sessionID.length <= 0 || sessionID.length > 32) ? EMPTY_BYTES : sessionID;
    }

    public static TlsSecret PRF(SecurityParameters securityParameters, TlsSecret tlsSecret, String str, byte[] bArr, int i2) {
        return tlsSecret.deriveUsingPRF(securityParameters.getPRFAlgorithm(), str, bArr, i2);
    }

    public static TlsSecret PRF(TlsContext tlsContext, TlsSecret tlsSecret, String str, byte[] bArr, int i2) {
        return PRF(tlsContext.getSecurityParametersHandshake(), tlsSecret, str, bArr, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsSecret Q(TlsCrypto tlsCrypto, TlsSecret tlsSecret) {
        if (tlsSecret != null) {
            synchronized (tlsSecret) {
                if (tlsSecret.isAlive()) {
                    return tlsCrypto.adoptSecret(tlsSecret);
                }
                return null;
            }
        }
        return null;
    }

    static SignatureAndHashAlgorithm R(ProtocolVersion protocolVersion, TlsCredentialedSigner tlsCredentialedSigner) {
        if (isTLSv12(protocolVersion)) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = tlsCredentialedSigner.getSignatureAndHashAlgorithm();
            if (signatureAndHashAlgorithm != null) {
                return signatureAndHashAlgorithm;
            }
            throw new TlsFatalAlert((short) 80);
        }
        return null;
    }

    static boolean S(TlsCrypto tlsCrypto) {
        return tlsCrypto.hasSignatureAlgorithm((short) 1) || tlsCrypto.hasSignatureAlgorithm((short) 4) || tlsCrypto.hasSignatureAlgorithm((short) 5) || tlsCrypto.hasSignatureAlgorithm((short) 6) || tlsCrypto.hasSignatureAlgorithm((short) 9) || tlsCrypto.hasSignatureAlgorithm((short) 10) || tlsCrypto.hasSignatureAlgorithm((short) 11);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCipher T(TlsContext tlsContext) {
        int cipherSuite = tlsContext.getSecurityParametersHandshake().getCipherSuite();
        int encryptionAlgorithm = getEncryptionAlgorithm(cipherSuite);
        int mACAlgorithm = getMACAlgorithm(cipherSuite);
        if (encryptionAlgorithm < 0 || mACAlgorithm < 0) {
            throw new TlsFatalAlert((short) 80);
        }
        return tlsContext.getCrypto().createCipher(new TlsCryptoParameters(tlsContext), encryptionAlgorithm, mACAlgorithm);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsKeyExchange U(TlsClientContext tlsClientContext, TlsClient tlsClient) {
        TlsKeyExchange createKeyExchangeClient = createKeyExchangeClient(tlsClient, tlsClientContext.getSecurityParametersHandshake().getKeyExchangeAlgorithm());
        createKeyExchangeClient.init(tlsClientContext);
        return createKeyExchangeClient;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsKeyExchange V(TlsServerContext tlsServerContext, TlsServer tlsServer) {
        TlsKeyExchange createKeyExchangeServer = createKeyExchangeServer(tlsServer, tlsServerContext.getSecurityParametersHandshake().getKeyExchangeAlgorithm());
        createKeyExchangeServer.init(tlsServerContext);
        return createKeyExchangeServer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean W(ProtocolVersion[] protocolVersionArr) {
        return ProtocolVersion.contains(protocolVersionArr, ProtocolVersion.DTLSv12) || ProtocolVersion.contains(protocolVersionArr, ProtocolVersion.DTLSv10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean X(ProtocolVersion[] protocolVersionArr) {
        return ProtocolVersion.contains(protocolVersionArr, ProtocolVersion.TLSv12) || ProtocolVersion.contains(protocolVersionArr, ProtocolVersion.TLSv11) || ProtocolVersion.contains(protocolVersionArr, ProtocolVersion.TLSv10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean Y(int i2, int i3) {
        if (i3 != 0 && i3 != 1) {
            if (i3 != 5) {
                if (i3 != 10) {
                    switch (i3) {
                        case 13:
                            return i2 == 1 || i2 == 13;
                        case 14:
                        case 15:
                        case 16:
                            break;
                        default:
                            switch (i3) {
                                case 18:
                                    break;
                                case 19:
                                case 20:
                                    break;
                                case 21:
                                    return i2 == 1;
                                default:
                                    switch (i3) {
                                        case 41:
                                            return i2 == 1 || i2 == 2;
                                        case 42:
                                            return i2 == 1 || i2 == 4 || i2 == 8;
                                        case 43:
                                            return i2 == 1 || i2 == 2 || i2 == 6;
                                        case 44:
                                            return i2 == 1 || i2 == 6;
                                        case 45:
                                            break;
                                        default:
                                            switch (i3) {
                                                case 47:
                                                case 50:
                                                    break;
                                                case 48:
                                                    return i2 == 13;
                                                case 49:
                                                    break;
                                                case 51:
                                                    break;
                                                default:
                                                    return !ExtensionType.isRecognized(i3);
                                            }
                                    }
                            }
                    }
                }
            }
            return i2 == 1 || i2 == 11 || i2 == 13;
        }
        return i2 == 1 || i2 == 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean Z(int[] iArr, int i2) {
        return (iArr == null || !Arrays.contains(iArr, i2) || i2 == 0 || CipherSuite.isSCSV(i2)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Hashtable a(TlsClientContext tlsClientContext, TlsClient tlsClient, Hashtable hashtable) {
        if (isTLSv13(tlsClientContext.getClientVersion()) && hashtable.containsKey(TlsExtensionsUtils.EXT_supported_groups)) {
            int[] supportedGroupsExtension = TlsExtensionsUtils.getSupportedGroupsExtension(hashtable);
            Vector earlyKeyShareGroups = tlsClient.getEarlyKeyShareGroups();
            Hashtable hashtable2 = new Hashtable(3);
            Vector vector = new Vector(2);
            collectKeyShares(tlsClientContext.getCrypto(), supportedGroupsExtension, earlyKeyShareGroups, hashtable2, vector);
            TlsExtensionsUtils.addKeyShareClientHello(hashtable, vector);
            return hashtable2;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a0(ProtocolVersion protocolVersion, int[] iArr, Hashtable hashtable, int i2) {
        return iArr != null && Arrays.contains(iArr, i2) && !hashtable.containsKey(Integers.valueOf(i2)) && NamedGroup.canBeNegotiated(i2, protocolVersion);
    }

    private static void addCertSigAlgOID(Hashtable hashtable, ASN1ObjectIdentifier aSN1ObjectIdentifier, SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        hashtable.put(aSN1ObjectIdentifier.getId(), signatureAndHashAlgorithm);
    }

    private static void addCertSigAlgOID(Hashtable hashtable, ASN1ObjectIdentifier aSN1ObjectIdentifier, short s2, short s3) {
        addCertSigAlgOID(hashtable, aSN1ObjectIdentifier, SignatureAndHashAlgorithm.getInstance(s2, s3));
    }

    public static void addIfSupported(Vector vector, TlsCrypto tlsCrypto, int i2) {
        if (tlsCrypto.hasNamedGroup(i2)) {
            vector.addElement(Integers.valueOf(i2));
        }
    }

    public static void addIfSupported(Vector vector, TlsCrypto tlsCrypto, SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        if (tlsCrypto.hasSignatureAndHashAlgorithm(signatureAndHashAlgorithm)) {
            vector.addElement(signatureAndHashAlgorithm);
        }
    }

    public static void addIfSupported(Vector vector, TlsCrypto tlsCrypto, int[] iArr) {
        for (int i2 : iArr) {
            addIfSupported(vector, tlsCrypto, i2);
        }
    }

    public static boolean addToSet(Vector vector, int i2) {
        boolean z = !vector.contains(Integers.valueOf(i2));
        if (z) {
            vector.add(Integers.valueOf(i2));
        }
        return z;
    }

    private static boolean areCertificatesEqual(Certificate certificate, Certificate certificate2) {
        int length = certificate.getLength();
        if (certificate2.getLength() == length) {
            for (int i2 = 0; i2 < length; i2++) {
                try {
                    if (!Arrays.areEqual(certificate.getCertificateAt(i2).getEncoded(), certificate2.getCertificateAt(i2).getEncoded())) {
                        return false;
                    }
                } catch (IOException unused) {
                }
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Hashtable b(TlsClientContext tlsClientContext, Hashtable hashtable, int i2) {
        int[] iArr = {i2};
        Vector vectorOfOne = vectorOfOne(Integers.valueOf(i2));
        Hashtable hashtable2 = new Hashtable(1, 1.0f);
        Vector vector = new Vector(1);
        collectKeyShares(tlsClientContext.getCrypto(), iArr, vectorOfOne, hashtable2, vector);
        TlsExtensionsUtils.addKeyShareClientHello(hashtable, vector);
        if (hashtable2.isEmpty() || vector.isEmpty()) {
            throw new TlsFatalAlert((short) 80);
        }
        return hashtable2;
    }

    static boolean b0(short s2, short[] sArr) {
        short clientCertificateType = SignatureAlgorithm.getClientCertificateType(s2);
        return clientCertificateType >= 0 && Arrays.contains(sArr, clientCertificateType);
    }

    static void c(TlsPSK[] tlsPSKArr, Hashtable hashtable) {
        Vector vector = new Vector(tlsPSKArr.length);
        for (TlsPSK tlsPSK : tlsPSKArr) {
            vector.add(new PskIdentity(tlsPSK.getIdentity(), 0L));
        }
        TlsExtensionsUtils.addPreSharedKeyClientHello(hashtable, new OfferedPsks(vector));
    }

    static boolean c0(short s2, int i2) {
        if (i2 == 0) {
            return s2 != 0;
        }
        if (i2 != 3) {
            if (i2 != 5) {
                if (i2 == 17) {
                    return s2 == 3 || s2 == 7 || s2 == 8;
                } else if (i2 != 19) {
                    if (i2 != 22) {
                        if (i2 != 23) {
                            return false;
                        }
                    }
                }
            }
            if (s2 != 1 && s2 != 4 && s2 != 5 && s2 != 6) {
                switch (s2) {
                    case 9:
                    case 10:
                    case 11:
                        break;
                    default:
                        return false;
                }
            }
            return true;
        }
        return 2 == s2;
    }

    public static byte[] calculateExporterSeed(SecurityParameters securityParameters, byte[] bArr) {
        byte[] clientRandom = securityParameters.getClientRandom();
        byte[] serverRandom = securityParameters.getServerRandom();
        if (bArr == null) {
            return Arrays.concatenate(clientRandom, serverRandom);
        }
        if (isValidUint16(bArr.length)) {
            byte[] bArr2 = new byte[2];
            writeUint16(bArr.length, bArr2, 0);
            return Arrays.concatenate(clientRandom, serverRandom, bArr2, bArr);
        }
        throw new IllegalArgumentException("'context' must have length less than 2^16 (or be null)");
    }

    private static byte[] calculateFinishedHMAC(int i2, int i3, TlsSecret tlsSecret, byte[] bArr) {
        TlsSecret hkdfExpandLabel = TlsCryptoUtils.hkdfExpandLabel(tlsSecret, i2, "finished", EMPTY_BYTES, i3);
        try {
            return hkdfExpandLabel.calculateHMAC(i2, bArr, 0, bArr.length);
        } finally {
            hkdfExpandLabel.destroy();
        }
    }

    private static byte[] calculateFinishedHMAC(SecurityParameters securityParameters, TlsSecret tlsSecret, byte[] bArr) {
        return calculateFinishedHMAC(securityParameters.getPRFCryptoHashAlgorithm(), securityParameters.getPRFHashLength(), tlsSecret, bArr);
    }

    private static void checkDowngradeMarker(byte[] bArr, byte[] bArr2) {
        int length = bArr2.length;
        if (constantTimeAreEqual(length, bArr2, 0, bArr, bArr.length - length)) {
            throw new TlsFatalAlert((short) 47);
        }
    }

    public static void checkPeerSigAlgs(TlsContext tlsContext, TlsCertificate[] tlsCertificateArr) {
        if (tlsContext.isServer()) {
            checkSigAlgOfClientCerts(tlsContext, tlsCertificateArr);
        } else {
            checkSigAlgOfServerCerts(tlsContext, tlsCertificateArr);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0042 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void checkSigAlgOfClientCerts(TlsContext tlsContext, TlsCertificate[] tlsCertificateArr) {
        boolean z;
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        short[] clientCertTypes = securityParametersHandshake.getClientCertTypes();
        Vector serverSigAlgsCert = securityParametersHandshake.getServerSigAlgsCert();
        int length = tlsCertificateArr.length - 1;
        int i2 = 0;
        while (i2 < length) {
            TlsCertificate tlsCertificate = tlsCertificateArr[i2];
            i2++;
            SignatureAndHashAlgorithm G = G(tlsCertificate, tlsCertificateArr[i2]);
            if (G != null) {
                if (serverSigAlgsCert != null) {
                    z = containsSignatureAlgorithm(serverSigAlgsCert, G);
                    continue;
                } else if (clientCertTypes != null) {
                    for (short s2 : clientCertTypes) {
                        if (G.getSignature() == getLegacySignatureAlgorithmClientCert(s2)) {
                            z = true;
                            continue;
                            break;
                        }
                    }
                }
                if (!z) {
                    throw new TlsFatalAlert((short) 42);
                }
            }
            z = false;
            continue;
            if (!z) {
            }
        }
    }

    private static void checkSigAlgOfServerCerts(TlsContext tlsContext, TlsCertificate[] tlsCertificateArr) {
        boolean z;
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        Vector clientSigAlgsCert = securityParametersHandshake.getClientSigAlgsCert();
        Vector vector = (securityParametersHandshake.getClientSigAlgs() == clientSigAlgsCert || isTLSv13(securityParametersHandshake.getNegotiatedVersion())) ? null : null;
        int length = tlsCertificateArr.length - 1;
        int i2 = 0;
        while (i2 < length) {
            TlsCertificate tlsCertificate = tlsCertificateArr[i2];
            i2++;
            SignatureAndHashAlgorithm G = G(tlsCertificate, tlsCertificateArr[i2]);
            if (G != null && (clientSigAlgsCert != null ? containsSignatureAlgorithm(clientSigAlgsCert, G) || (vector != null && containsSignatureAlgorithm(vector, G)) : getLegacySignatureAlgorithmServerCert(securityParametersHandshake.getKeyExchangeAlgorithm()) == G.getSignature())) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (!z) {
                throw new TlsFatalAlert((short) 42);
            }
        }
    }

    public static void checkUint16(int i2) {
        if (!isValidUint16(i2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint16(long j2) {
        if (!isValidUint16(j2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint24(int i2) {
        if (!isValidUint24(i2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint24(long j2) {
        if (!isValidUint24(j2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint32(long j2) {
        if (!isValidUint32(j2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint48(long j2) {
        if (!isValidUint48(j2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint64(long j2) {
        if (!isValidUint64(j2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint8(int i2) {
        if (!isValidUint8(i2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint8(long j2) {
        if (!isValidUint8(j2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint8(short s2) {
        if (!isValidUint8(s2)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static SignatureAndHashAlgorithm chooseSignatureAndHashAlgorithm(ProtocolVersion protocolVersion, Vector vector, short s2) {
        short hash;
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = null;
        if (isTLSv12(protocolVersion)) {
            if (vector == null) {
                vector = getDefaultSignatureAlgorithms(s2);
            }
            for (int i2 = 0; i2 < vector.size(); i2++) {
                SignatureAndHashAlgorithm signatureAndHashAlgorithm2 = (SignatureAndHashAlgorithm) vector.elementAt(i2);
                if (signatureAndHashAlgorithm2.getSignature() == s2 && (hash = signatureAndHashAlgorithm2.getHash()) >= 2) {
                    if (signatureAndHashAlgorithm != null) {
                        short hash2 = signatureAndHashAlgorithm.getHash();
                        if (hash2 < 4) {
                            if (hash <= hash2) {
                            }
                        } else if (hash >= 4) {
                            if (hash >= hash2) {
                            }
                        }
                    }
                    signatureAndHashAlgorithm = signatureAndHashAlgorithm2;
                }
            }
            if (signatureAndHashAlgorithm != null) {
                return signatureAndHashAlgorithm;
            }
            throw new TlsFatalAlert((short) 80);
        }
        return null;
    }

    public static SignatureAndHashAlgorithm chooseSignatureAndHashAlgorithm(TlsContext tlsContext, Vector vector, short s2) {
        return chooseSignatureAndHashAlgorithm(tlsContext.getServerVersion(), vector, s2);
    }

    public static byte[] clone(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return bArr.length == 0 ? EMPTY_BYTES : (byte[]) bArr.clone();
    }

    public static String[] clone(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        return strArr.length < 1 ? EMPTY_STRINGS : (String[]) strArr.clone();
    }

    private static void collectKeyShares(TlsCrypto tlsCrypto, int[] iArr, Vector vector, Hashtable hashtable, Vector vector2) {
        if (isNullOrEmpty(iArr) || vector == null || vector.isEmpty()) {
            return;
        }
        for (int i2 : iArr) {
            Integer valueOf = Integers.valueOf(i2);
            if (vector.contains(valueOf) && !hashtable.containsKey(valueOf) && tlsCrypto.hasNamedGroup(i2)) {
                TlsAgreement tlsAgreement = null;
                if (NamedGroup.refersToASpecificCurve(i2)) {
                    if (tlsCrypto.hasECDHAgreement()) {
                        tlsAgreement = tlsCrypto.createECDomain(new TlsECConfig(i2)).createECDH();
                    }
                } else if (NamedGroup.refersToASpecificFiniteField(i2) && tlsCrypto.hasDHAgreement()) {
                    tlsAgreement = tlsCrypto.createDHDomain(new TlsDHConfig(i2, true)).createDH();
                }
                if (tlsAgreement != null) {
                    vector2.addElement(new KeyShareEntry(i2, tlsAgreement.generateEphemeral()));
                    hashtable.put(valueOf, tlsAgreement);
                }
            }
        }
    }

    public static boolean constantTimeAreEqual(int i2, byte[] bArr, int i3, byte[] bArr2, int i4) {
        int i5 = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            i5 |= bArr[i3 + i6] ^ bArr2[i4 + i6];
        }
        return i5 == 0;
    }

    public static boolean containsAnySignatureAlgorithm(Vector vector, short s2) {
        for (int i2 = 0; i2 < vector.size(); i2++) {
            if (((SignatureAndHashAlgorithm) vector.elementAt(i2)).getSignature() == s2) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsNonAscii(String str) {
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) >= 128) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsNonAscii(byte[] bArr) {
        for (byte b2 : bArr) {
            if ((b2 & 255) >= 128) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsSignatureAlgorithm(Vector vector, SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        for (int i2 = 0; i2 < vector.size(); i2++) {
            if (((SignatureAndHashAlgorithm) vector.elementAt(i2)).equals(signatureAndHashAlgorithm)) {
                return true;
            }
        }
        return false;
    }

    public static byte[] copyOfRangeExact(byte[] bArr, int i2, int i3) {
        int i4 = i3 - i2;
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArr, i2, bArr2, 0, i4);
        return bArr2;
    }

    private static Hashtable createCertSigAlgOIDs() {
        Hashtable hashtable = new Hashtable();
        addCertSigAlgOID(hashtable, NISTObjectIdentifiers.dsa_with_sha224, (short) 3, (short) 2);
        addCertSigAlgOID(hashtable, NISTObjectIdentifiers.dsa_with_sha256, (short) 4, (short) 2);
        addCertSigAlgOID(hashtable, NISTObjectIdentifiers.dsa_with_sha384, (short) 5, (short) 2);
        addCertSigAlgOID(hashtable, NISTObjectIdentifiers.dsa_with_sha512, (short) 6, (short) 2);
        addCertSigAlgOID(hashtable, OIWObjectIdentifiers.dsaWithSHA1, (short) 2, (short) 2);
        addCertSigAlgOID(hashtable, OIWObjectIdentifiers.sha1WithRSA, (short) 2, (short) 1);
        addCertSigAlgOID(hashtable, PKCSObjectIdentifiers.sha1WithRSAEncryption, (short) 2, (short) 1);
        addCertSigAlgOID(hashtable, PKCSObjectIdentifiers.sha224WithRSAEncryption, (short) 3, (short) 1);
        addCertSigAlgOID(hashtable, PKCSObjectIdentifiers.sha256WithRSAEncryption, (short) 4, (short) 1);
        addCertSigAlgOID(hashtable, PKCSObjectIdentifiers.sha384WithRSAEncryption, (short) 5, (short) 1);
        addCertSigAlgOID(hashtable, PKCSObjectIdentifiers.sha512WithRSAEncryption, (short) 6, (short) 1);
        addCertSigAlgOID(hashtable, X9ObjectIdentifiers.ecdsa_with_SHA1, (short) 2, (short) 3);
        addCertSigAlgOID(hashtable, X9ObjectIdentifiers.ecdsa_with_SHA224, (short) 3, (short) 3);
        addCertSigAlgOID(hashtable, X9ObjectIdentifiers.ecdsa_with_SHA256, (short) 4, (short) 3);
        addCertSigAlgOID(hashtable, X9ObjectIdentifiers.ecdsa_with_SHA384, (short) 5, (short) 3);
        addCertSigAlgOID(hashtable, X9ObjectIdentifiers.ecdsa_with_SHA512, (short) 6, (short) 3);
        addCertSigAlgOID(hashtable, X9ObjectIdentifiers.id_dsa_with_sha1, (short) 2, (short) 2);
        addCertSigAlgOID(hashtable, EACObjectIdentifiers.id_TA_ECDSA_SHA_1, (short) 2, (short) 3);
        addCertSigAlgOID(hashtable, EACObjectIdentifiers.id_TA_ECDSA_SHA_224, (short) 3, (short) 3);
        addCertSigAlgOID(hashtable, EACObjectIdentifiers.id_TA_ECDSA_SHA_256, (short) 4, (short) 3);
        addCertSigAlgOID(hashtable, EACObjectIdentifiers.id_TA_ECDSA_SHA_384, (short) 5, (short) 3);
        addCertSigAlgOID(hashtable, EACObjectIdentifiers.id_TA_ECDSA_SHA_512, (short) 6, (short) 3);
        addCertSigAlgOID(hashtable, EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_1, (short) 2, (short) 1);
        addCertSigAlgOID(hashtable, EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_256, (short) 4, (short) 1);
        addCertSigAlgOID(hashtable, BSIObjectIdentifiers.ecdsa_plain_SHA1, (short) 2, (short) 3);
        addCertSigAlgOID(hashtable, BSIObjectIdentifiers.ecdsa_plain_SHA224, (short) 3, (short) 3);
        addCertSigAlgOID(hashtable, BSIObjectIdentifiers.ecdsa_plain_SHA256, (short) 4, (short) 3);
        addCertSigAlgOID(hashtable, BSIObjectIdentifiers.ecdsa_plain_SHA384, (short) 5, (short) 3);
        addCertSigAlgOID(hashtable, BSIObjectIdentifiers.ecdsa_plain_SHA512, (short) 6, (short) 3);
        addCertSigAlgOID(hashtable, EdECObjectIdentifiers.id_Ed25519, SignatureAndHashAlgorithm.ed25519);
        addCertSigAlgOID(hashtable, EdECObjectIdentifiers.id_Ed448, SignatureAndHashAlgorithm.ed448);
        addCertSigAlgOID(hashtable, RosstandartObjectIdentifiers.id_tc26_signwithdigest_gost_3410_12_256, SignatureAndHashAlgorithm.gostr34102012_256);
        addCertSigAlgOID(hashtable, RosstandartObjectIdentifiers.id_tc26_signwithdigest_gost_3410_12_512, SignatureAndHashAlgorithm.gostr34102012_512);
        return hashtable;
    }

    private static Vector createDefaultSupportedSigAlgs() {
        Vector vector = new Vector();
        vector.addElement(SignatureAndHashAlgorithm.ed25519);
        vector.addElement(SignatureAndHashAlgorithm.ed448);
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 4, (short) 3));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 5, (short) 3));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 6, (short) 3));
        vector.addElement(SignatureAndHashAlgorithm.rsa_pss_rsae_sha256);
        vector.addElement(SignatureAndHashAlgorithm.rsa_pss_rsae_sha384);
        vector.addElement(SignatureAndHashAlgorithm.rsa_pss_rsae_sha512);
        vector.addElement(SignatureAndHashAlgorithm.rsa_pss_pss_sha256);
        vector.addElement(SignatureAndHashAlgorithm.rsa_pss_pss_sha384);
        vector.addElement(SignatureAndHashAlgorithm.rsa_pss_pss_sha512);
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 4, (short) 1));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 5, (short) 1));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 6, (short) 1));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 4, (short) 2));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 5, (short) 2));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 6, (short) 2));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 3, (short) 3));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 3, (short) 1));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 3, (short) 2));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 2, (short) 3));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 2, (short) 1));
        vector.addElement(SignatureAndHashAlgorithm.getInstance((short) 2, (short) 2));
        return vector;
    }

    private static TlsHash createHash(TlsCrypto tlsCrypto, short s2) {
        return tlsCrypto.createHash(TlsCryptoUtils.getHash(s2));
    }

    private static TlsKeyExchange createKeyExchangeClient(TlsClient tlsClient, int i2) {
        TlsKeyExchangeFactory keyExchangeFactory = tlsClient.getKeyExchangeFactory();
        if (i2 != 1) {
            if (i2 == 3 || i2 == 5) {
                return keyExchangeFactory.createDHEKeyExchangeClient(i2, tlsClient.getDHGroupVerifier());
            }
            if (i2 == 7 || i2 == 9) {
                return keyExchangeFactory.createDHKeyExchange(i2);
            }
            if (i2 != 11) {
                switch (i2) {
                    case 13:
                    case 15:
                    case 24:
                        return keyExchangeFactory.createPSKKeyExchangeClient(i2, tlsClient.getPSKIdentity(), null);
                    case 14:
                        return keyExchangeFactory.createPSKKeyExchangeClient(i2, tlsClient.getPSKIdentity(), tlsClient.getDHGroupVerifier());
                    case 16:
                    case 18:
                        return keyExchangeFactory.createECDHKeyExchange(i2);
                    case 17:
                    case 19:
                        return keyExchangeFactory.createECDHEKeyExchangeClient(i2);
                    case 20:
                        return keyExchangeFactory.createECDHanonKeyExchangeClient(i2);
                    case 21:
                    case 22:
                    case 23:
                        return keyExchangeFactory.createSRPKeyExchangeClient(i2, tlsClient.getSRPIdentity(), tlsClient.getSRPConfigVerifier());
                    default:
                        throw new TlsFatalAlert((short) 80);
                }
            }
            return keyExchangeFactory.createDHanonKeyExchangeClient(i2, tlsClient.getDHGroupVerifier());
        }
        return keyExchangeFactory.createRSAKeyExchange(i2);
    }

    private static TlsKeyExchange createKeyExchangeServer(TlsServer tlsServer, int i2) {
        TlsKeyExchangeFactory keyExchangeFactory = tlsServer.getKeyExchangeFactory();
        if (i2 != 1) {
            if (i2 == 3 || i2 == 5) {
                return keyExchangeFactory.createDHEKeyExchangeServer(i2, tlsServer.getDHConfig());
            }
            if (i2 == 7 || i2 == 9) {
                return keyExchangeFactory.createDHKeyExchange(i2);
            }
            if (i2 != 11) {
                switch (i2) {
                    case 13:
                    case 15:
                        return keyExchangeFactory.createPSKKeyExchangeServer(i2, tlsServer.getPSKIdentityManager(), null, null);
                    case 14:
                        return keyExchangeFactory.createPSKKeyExchangeServer(i2, tlsServer.getPSKIdentityManager(), tlsServer.getDHConfig(), null);
                    case 16:
                    case 18:
                        return keyExchangeFactory.createECDHKeyExchange(i2);
                    case 17:
                    case 19:
                        return keyExchangeFactory.createECDHEKeyExchangeServer(i2, tlsServer.getECDHConfig());
                    case 20:
                        return keyExchangeFactory.createECDHanonKeyExchangeServer(i2, tlsServer.getECDHConfig());
                    case 21:
                    case 22:
                    case 23:
                        return keyExchangeFactory.createSRPKeyExchangeServer(i2, tlsServer.getSRPLoginParameters());
                    case 24:
                        return keyExchangeFactory.createPSKKeyExchangeServer(i2, tlsServer.getPSKIdentityManager(), null, tlsServer.getECDHConfig());
                    default:
                        throw new TlsFatalAlert((short) 80);
                }
            }
            return keyExchangeFactory.createDHanonKeyExchangeServer(i2, tlsServer.getDHConfig());
        }
        return keyExchangeFactory.createRSAKeyExchange(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static OfferedPsks.BindersConfig d(TlsClientContext tlsClientContext, TlsClient tlsClient, Hashtable hashtable, int[] iArr) {
        TlsPSKExternal[] N;
        if (isTLSv13(tlsClientContext.getClientVersion()) && (N = N(tlsClient, iArr)) != null) {
            short[] pskKeyExchangeModes = tlsClient.getPskKeyExchangeModes();
            if (isNullOrEmpty(pskKeyExchangeModes)) {
                throw new TlsFatalAlert((short) 80, "External PSKs configured but no PskKeyExchangeMode available");
            }
            TlsSecret[] M = M(tlsClientContext.getCrypto(), N);
            int b2 = OfferedPsks.b(N);
            c(N, hashtable);
            TlsExtensionsUtils.addPSKKeyExchangeModesExtension(hashtable, pskKeyExchangeModes);
            return new OfferedPsks.BindersConfig(N, pskKeyExchangeModes, M, b2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d0(SecurityParameters securityParameters, int i2) {
        int i3;
        securityParameters.f14801d = i2;
        securityParameters.P = getKeyExchangeAlgorithm(i2);
        int I = I(securityParameters, i2);
        securityParameters.f14803f = I;
        if (I == 0 || I == 1) {
            i3 = -1;
            securityParameters.f14804g = -1;
            securityParameters.f14805h = (short) -1;
        } else {
            int hashForPRF = TlsCryptoUtils.getHashForPRF(I);
            securityParameters.f14804g = hashForPRF;
            securityParameters.f14805h = getHashAlgorithmForPRFAlgorithm(I);
            i3 = TlsCryptoUtils.getHashOutputSize(hashForPRF);
        }
        securityParameters.f14806i = i3;
        ProtocolVersion negotiatedVersion = securityParameters.getNegotiatedVersion();
        securityParameters.f14807j = isTLSv13(negotiatedVersion) ? securityParameters.getPRFHashLength() : negotiatedVersion.isSSL() ? 36 : 12;
    }

    public static byte[] decodeOpaque16(byte[] bArr) {
        return decodeOpaque16(bArr, 0);
    }

    public static byte[] decodeOpaque16(byte[] bArr, int i2) {
        if (bArr != null) {
            if (bArr.length >= 2) {
                int readUint16 = readUint16(bArr, 0);
                if (bArr.length != readUint16 + 2 || readUint16 < i2) {
                    throw new TlsFatalAlert((short) 50);
                }
                return copyOfRangeExact(bArr, 2, bArr.length);
            }
            throw new TlsFatalAlert((short) 50);
        }
        throw new IllegalArgumentException("'buf' cannot be null");
    }

    public static byte[] decodeOpaque8(byte[] bArr) {
        return decodeOpaque8(bArr, 0);
    }

    public static byte[] decodeOpaque8(byte[] bArr, int i2) {
        if (bArr != null) {
            if (bArr.length >= 1) {
                short readUint8 = readUint8(bArr, 0);
                if (bArr.length != readUint8 + 1 || readUint8 < i2) {
                    throw new TlsFatalAlert((short) 50);
                }
                return copyOfRangeExact(bArr, 1, bArr.length);
            }
            throw new TlsFatalAlert((short) 50);
        }
        throw new IllegalArgumentException("'buf' cannot be null");
    }

    public static int decodeUint16(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length == 2) {
                return readUint16(bArr, 0);
            }
            throw new TlsFatalAlert((short) 50);
        }
        throw new IllegalArgumentException("'buf' cannot be null");
    }

    public static long decodeUint32(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length == 4) {
                return readUint32(bArr, 0);
            }
            throw new TlsFatalAlert((short) 50);
        }
        throw new IllegalArgumentException("'buf' cannot be null");
    }

    public static short decodeUint8(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length == 1) {
                return readUint8(bArr, 0);
            }
            throw new TlsFatalAlert((short) 50);
        }
        throw new IllegalArgumentException("'buf' cannot be null");
    }

    public static short[] decodeUint8ArrayWithUint8Length(byte[] bArr) {
        if (bArr != null) {
            int i2 = 0;
            int readUint8 = readUint8(bArr, 0);
            if (bArr.length == readUint8 + 1) {
                short[] sArr = new short[readUint8];
                while (i2 < readUint8) {
                    int i3 = i2 + 1;
                    sArr[i2] = readUint8(bArr, i3);
                    i2 = i3;
                }
                return sArr;
            }
            throw new TlsFatalAlert((short) 50);
        }
        throw new IllegalArgumentException("'buf' cannot be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static OfferedPsks.BindersConfig e(TlsClientContext tlsClientContext, OfferedPsks.BindersConfig bindersConfig, Hashtable hashtable) {
        Vector O = O(bindersConfig.f14773a, J(tlsClientContext.getSecurityParametersHandshake().getCipherSuite()));
        if (O.isEmpty()) {
            return null;
        }
        int size = O.size();
        if (size < bindersConfig.f14773a.length) {
            TlsPSK[] tlsPSKArr = new TlsPSK[size];
            TlsSecret[] tlsSecretArr = new TlsSecret[size];
            for (int i2 = 0; i2 < size; i2++) {
                int intValue = ((Integer) O.elementAt(i2)).intValue();
                tlsPSKArr[i2] = bindersConfig.f14773a[intValue];
                tlsSecretArr[i2] = bindersConfig.f14775c[intValue];
            }
            bindersConfig = new OfferedPsks.BindersConfig(tlsPSKArr, bindersConfig.f14774b, tlsSecretArr, OfferedPsks.b(tlsPSKArr));
        }
        c(bindersConfig.f14773a, hashtable);
        return bindersConfig;
    }

    static void e0(SecurityParameters securityParameters) {
        if (!isSignatureAlgorithmsExtensionAllowed(securityParameters.getNegotiatedVersion())) {
            securityParameters.J = null;
            securityParameters.K = null;
            return;
        }
        if (securityParameters.getClientSigAlgs() == null) {
            securityParameters.J = getLegacySupportedSignatureAlgorithms();
        }
        if (securityParameters.getClientSigAlgsCert() == null) {
            securityParameters.K = securityParameters.getClientSigAlgs();
        }
    }

    public static byte[] encodeOpaque16(byte[] bArr) {
        checkUint16(bArr.length);
        byte[] bArr2 = new byte[bArr.length + 2];
        writeUint16(bArr.length, bArr2, 0);
        System.arraycopy(bArr, 0, bArr2, 2, bArr.length);
        return bArr2;
    }

    public static byte[] encodeOpaque24(byte[] bArr) {
        checkUint24(bArr.length);
        byte[] bArr2 = new byte[bArr.length + 3];
        writeUint24(bArr.length, bArr2, 0);
        System.arraycopy(bArr, 0, bArr2, 3, bArr.length);
        return bArr2;
    }

    public static byte[] encodeOpaque8(byte[] bArr) {
        checkUint8(bArr.length);
        return Arrays.prepend(bArr, (byte) bArr.length);
    }

    public static void encodeSupportedSignatureAlgorithms(Vector vector, OutputStream outputStream) {
        if (vector == null || vector.size() < 1 || vector.size() >= 32768) {
            throw new IllegalArgumentException("'supportedSignatureAlgorithms' must have length from 1 to (2^15 - 1)");
        }
        int size = vector.size() * 2;
        checkUint16(size);
        writeUint16(size, outputStream);
        for (int i2 = 0; i2 < vector.size(); i2++) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) vector.elementAt(i2);
            if (signatureAndHashAlgorithm.getSignature() == 0) {
                throw new IllegalArgumentException("SignatureAlgorithm.anonymous MUST NOT appear in the signature_algorithms extension");
            }
            signatureAndHashAlgorithm.encode(outputStream);
        }
    }

    public static byte[] encodeUint16(int i2) {
        checkUint16(i2);
        byte[] bArr = new byte[2];
        writeUint16(i2, bArr, 0);
        return bArr;
    }

    public static byte[] encodeUint16ArrayWithUint16Length(int[] iArr) {
        byte[] bArr = new byte[(iArr.length * 2) + 2];
        writeUint16ArrayWithUint16Length(iArr, bArr, 0);
        return bArr;
    }

    public static byte[] encodeUint24(int i2) {
        checkUint24(i2);
        byte[] bArr = new byte[3];
        writeUint24(i2, bArr, 0);
        return bArr;
    }

    public static byte[] encodeUint32(long j2) {
        checkUint32(j2);
        byte[] bArr = new byte[4];
        writeUint32(j2, bArr, 0);
        return bArr;
    }

    public static byte[] encodeUint8(short s2) {
        checkUint8(s2);
        byte[] bArr = new byte[1];
        writeUint8(s2, bArr, 0);
        return bArr;
    }

    public static byte[] encodeUint8ArrayWithUint8Length(short[] sArr) {
        byte[] bArr = new byte[sArr.length + 1];
        writeUint8ArrayWithUint8Length(sArr, bArr, 0);
        return bArr;
    }

    public static byte[] encodeVersion(ProtocolVersion protocolVersion) {
        return new byte[]{(byte) protocolVersion.getMajorVersion(), (byte) protocolVersion.getMinorVersion()};
    }

    private static void establish13TrafficSecrets(TlsContext tlsContext, byte[] bArr, TlsSecret tlsSecret, String str, String str2, RecordStream recordStream) {
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        securityParametersHandshake.f14815r = t(securityParametersHandshake, tlsSecret, str, bArr);
        if (str2 != null) {
            securityParametersHandshake.f14816s = t(securityParametersHandshake, tlsSecret, str2, bArr);
        }
        recordStream.q(T(tlsContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void f(TlsHandshakeHash tlsHandshakeHash) {
        byte[] H = H(tlsHandshakeHash);
        tlsHandshakeHash.reset();
        int length = H.length;
        checkUint8(length);
        int i2 = length + 4;
        byte[] bArr = new byte[i2];
        writeUint8((short) HandshakeType.message_hash, bArr, 0);
        writeUint24(length, bArr, 1);
        System.arraycopy(H, 0, bArr, 4, length);
        tlsHandshakeHash.update(bArr, 0, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void f0(TlsClientContext tlsClientContext, TlsClient tlsClient) {
        SecurityParameters securityParametersHandshake = tlsClientContext.getSecurityParametersHandshake();
        ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
        if (!ProtocolVersion.a(negotiatedVersion)) {
            throw new TlsFatalAlert((short) 80);
        }
        e0(securityParametersHandshake);
        tlsClient.notifyServerVersion(negotiatedVersion);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] g(TlsContext tlsContext, TlsCertificate tlsCertificate, byte[] bArr) {
        return h(tlsContext, tlsCertificate, bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void g0(TlsServerContext tlsServerContext) {
        SecurityParameters securityParametersHandshake = tlsServerContext.getSecurityParametersHandshake();
        if (!ProtocolVersion.b(securityParametersHandshake.getNegotiatedVersion())) {
            throw new TlsFatalAlert((short) 80);
        }
        e0(securityParametersHandshake);
    }

    private static byte[] generate13CertificateVerify(TlsCrypto tlsCrypto, TlsCredentialedSigner tlsCredentialedSigner, String str, TlsHandshakeHash tlsHandshakeHash, SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        TlsStreamSigner streamSigner = tlsCredentialedSigner.getStreamSigner();
        byte[] certificateVerifyHeader = getCertificateVerifyHeader(str);
        byte[] H = H(tlsHandshakeHash);
        if (streamSigner != null) {
            OutputStream outputStream = streamSigner.getOutputStream();
            outputStream.write(certificateVerifyHeader, 0, certificateVerifyHeader.length);
            outputStream.write(H, 0, H.length);
            return streamSigner.getSignature();
        }
        TlsHash createHash = tlsCrypto.createHash(SignatureScheme.getCryptoHashAlgorithm(SignatureScheme.from(signatureAndHashAlgorithm)));
        createHash.update(certificateVerifyHeader, 0, certificateVerifyHeader.length);
        createHash.update(H, 0, H.length);
        return tlsCredentialedSigner.generateRawSignature(createHash.calculateHash());
    }

    public static TlsSecret generateEncryptedPreMasterSecret(TlsContext tlsContext, TlsEncryptor tlsEncryptor, OutputStream outputStream) {
        TlsSecret generateRSAPreMasterSecret = tlsContext.getCrypto().generateRSAPreMasterSecret(tlsContext.getRSAPreMasterSecretVersion());
        N0(tlsContext, generateRSAPreMasterSecret.encrypt(tlsEncryptor), outputStream);
        return generateRSAPreMasterSecret;
    }

    private static byte[] getCertificateVerifyHeader(String str) {
        int length = str.length();
        int i2 = length + 64;
        byte[] bArr = new byte[i2 + 1];
        for (int i3 = 0; i3 < 64; i3++) {
            bArr[i3] = 32;
        }
        for (int i4 = 0; i4 < length; i4++) {
            bArr[i4 + 64] = (byte) str.charAt(i4);
        }
        bArr[i2] = 0;
        return bArr;
    }

    public static int getCipherType(int i2) {
        return getEncryptionAlgorithmType(getEncryptionAlgorithm(i2));
    }

    public static int getCommonCipherSuite13(ProtocolVersion protocolVersion, int[] iArr, int[] iArr2, boolean z) {
        if (z) {
            iArr2 = iArr;
            iArr = iArr2;
        }
        for (int i2 : iArr) {
            if (Arrays.contains(iArr2, i2) && isValidVersionForCipherSuite(i2, protocolVersion)) {
                return i2;
            }
        }
        return -1;
    }

    public static int[] getCommonCipherSuites(int[] iArr, int[] iArr2, boolean z) {
        if (z) {
            iArr2 = iArr;
            iArr = iArr2;
        }
        int min = Math.min(iArr.length, iArr2.length);
        int[] iArr3 = new int[min];
        int i2 = 0;
        for (int i3 : iArr) {
            if (!q(iArr3, 0, i2, i3) && Arrays.contains(iArr2, i3)) {
                iArr3[i2] = i3;
                i2++;
            }
        }
        return i2 < min ? Arrays.copyOf(iArr3, i2) : iArr3;
    }

    public static Vector getDefaultDSSSignatureAlgorithms() {
        return getDefaultSignatureAlgorithms((short) 2);
    }

    public static Vector getDefaultECDSASignatureAlgorithms() {
        return getDefaultSignatureAlgorithms((short) 3);
    }

    public static Vector getDefaultRSASignatureAlgorithms() {
        return getDefaultSignatureAlgorithms((short) 1);
    }

    public static SignatureAndHashAlgorithm getDefaultSignatureAlgorithm(short s2) {
        if (s2 == 1 || s2 == 2 || s2 == 3) {
            return SignatureAndHashAlgorithm.getInstance((short) 2, s2);
        }
        return null;
    }

    public static Vector getDefaultSignatureAlgorithms(short s2) {
        SignatureAndHashAlgorithm defaultSignatureAlgorithm = getDefaultSignatureAlgorithm(s2);
        return defaultSignatureAlgorithm == null ? new Vector() : vectorOfOne(defaultSignatureAlgorithm);
    }

    public static Vector getDefaultSupportedSignatureAlgorithms(TlsContext tlsContext) {
        TlsCrypto crypto = tlsContext.getCrypto();
        int size = DEFAULT_SUPPORTED_SIG_ALGS.size();
        Vector vector = new Vector(size);
        for (int i2 = 0; i2 < size; i2++) {
            addIfSupported(vector, crypto, (SignatureAndHashAlgorithm) DEFAULT_SUPPORTED_SIG_ALGS.elementAt(i2));
        }
        return vector;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:817)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARN: Removed duplicated region for block: B:45:0x004e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0060 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int getEncryptionAlgorithm(int r1) {
        /*
            r0 = 0
            switch(r1) {
                case 2: goto L60;
                case 10: goto L5e;
                case 13: goto L5e;
                case 16: goto L5e;
                case 19: goto L5e;
                case 22: goto L5e;
                case 27: goto L5e;
                case 147: goto L5e;
                case 148: goto L5b;
                case 149: goto L58;
                case 150: goto L55;
                case 151: goto L55;
                case 152: goto L55;
                case 153: goto L55;
                case 154: goto L55;
                case 155: goto L55;
                case 156: goto L52;
                case 157: goto L4f;
                case 158: goto L52;
                case 159: goto L4f;
                case 160: goto L52;
                case 161: goto L4f;
                case 162: goto L52;
                case 163: goto L4f;
                case 164: goto L52;
                case 165: goto L4f;
                case 166: goto L52;
                case 167: goto L4f;
                case 168: goto L52;
                case 169: goto L4f;
                case 170: goto L52;
                case 171: goto L4f;
                case 172: goto L52;
                case 173: goto L4f;
                case 174: goto L5b;
                case 175: goto L58;
                case 176: goto L4e;
                case 177: goto L4e;
                case 178: goto L5b;
                case 179: goto L58;
                case 180: goto L4e;
                case 181: goto L4e;
                case 182: goto L5b;
                case 183: goto L58;
                case 184: goto L4e;
                case 185: goto L4e;
                case 186: goto L4b;
                case 187: goto L4b;
                case 188: goto L4b;
                case 189: goto L4b;
                case 190: goto L4b;
                case 191: goto L4b;
                case 192: goto L48;
                case 193: goto L48;
                case 194: goto L48;
                case 195: goto L48;
                case 196: goto L48;
                case 197: goto L48;
                case 198: goto L45;
                case 199: goto L42;
                case 49153: goto L60;
                case 49170: goto L5e;
                case 49171: goto L5b;
                case 49172: goto L58;
                case 49173: goto L60;
                case 49175: goto L5e;
                case 49176: goto L5b;
                case 49177: goto L58;
                case 49178: goto L5e;
                case 49179: goto L5e;
                case 49180: goto L5e;
                case 49181: goto L5b;
                case 49182: goto L5b;
                case 49183: goto L5b;
                case 49184: goto L58;
                case 49185: goto L58;
                case 49186: goto L58;
                case 49187: goto L5b;
                case 49188: goto L58;
                case 49189: goto L5b;
                case 49190: goto L58;
                case 49191: goto L5b;
                case 49192: goto L58;
                case 49193: goto L5b;
                case 49194: goto L58;
                case 49195: goto L52;
                case 49196: goto L4f;
                case 49197: goto L52;
                case 49198: goto L4f;
                case 49199: goto L52;
                case 49200: goto L4f;
                case 49201: goto L52;
                case 49202: goto L4f;
                case 49204: goto L5e;
                case 49205: goto L5b;
                case 49206: goto L58;
                case 49207: goto L5b;
                case 49208: goto L58;
                case 49209: goto L60;
                case 49210: goto L4e;
                case 49211: goto L4e;
                case 49212: goto L3f;
                case 49213: goto L3c;
                case 49214: goto L3f;
                case 49215: goto L3c;
                case 49216: goto L3f;
                case 49217: goto L3c;
                case 49218: goto L3f;
                case 49219: goto L3c;
                case 49220: goto L3f;
                case 49221: goto L3c;
                case 49222: goto L3f;
                case 49223: goto L3c;
                case 49224: goto L3f;
                case 49225: goto L3c;
                case 49226: goto L3f;
                case 49227: goto L3c;
                case 49228: goto L3f;
                case 49229: goto L3c;
                case 49230: goto L3f;
                case 49231: goto L3c;
                case 49232: goto L39;
                case 49233: goto L36;
                case 49234: goto L39;
                case 49235: goto L36;
                case 49236: goto L39;
                case 49237: goto L36;
                case 49238: goto L39;
                case 49239: goto L36;
                case 49240: goto L39;
                case 49241: goto L36;
                case 49242: goto L39;
                case 49243: goto L36;
                case 49244: goto L39;
                case 49245: goto L36;
                case 49246: goto L39;
                case 49247: goto L36;
                case 49248: goto L39;
                case 49249: goto L36;
                case 49250: goto L39;
                case 49251: goto L36;
                case 49252: goto L3f;
                case 49253: goto L3c;
                case 49254: goto L3f;
                case 49255: goto L3c;
                case 49256: goto L3f;
                case 49257: goto L3c;
                case 49258: goto L39;
                case 49259: goto L36;
                case 49260: goto L39;
                case 49261: goto L36;
                case 49262: goto L39;
                case 49263: goto L36;
                case 49264: goto L3f;
                case 49265: goto L3c;
                case 49266: goto L4b;
                case 49267: goto L48;
                case 49268: goto L4b;
                case 49269: goto L48;
                case 49270: goto L4b;
                case 49271: goto L48;
                case 49272: goto L4b;
                case 49273: goto L48;
                case 49274: goto L33;
                case 49275: goto L30;
                case 49276: goto L33;
                case 49277: goto L30;
                case 49278: goto L33;
                case 49279: goto L30;
                case 49280: goto L33;
                case 49281: goto L30;
                case 49282: goto L33;
                case 49283: goto L30;
                case 49284: goto L33;
                case 49285: goto L30;
                case 49286: goto L33;
                case 49287: goto L30;
                case 49288: goto L33;
                case 49289: goto L30;
                case 49290: goto L33;
                case 49291: goto L30;
                case 49292: goto L33;
                case 49293: goto L30;
                case 49294: goto L33;
                case 49295: goto L30;
                case 49296: goto L33;
                case 49297: goto L30;
                case 49298: goto L33;
                case 49299: goto L30;
                case 49300: goto L4b;
                case 49301: goto L48;
                case 49302: goto L4b;
                case 49303: goto L48;
                case 49304: goto L4b;
                case 49305: goto L48;
                case 49306: goto L4b;
                case 49307: goto L48;
                case 49308: goto L2d;
                case 49309: goto L2a;
                case 49310: goto L2d;
                case 49311: goto L2a;
                case 49312: goto L27;
                case 49313: goto L24;
                case 49314: goto L27;
                case 49315: goto L24;
                case 49316: goto L2d;
                case 49317: goto L2a;
                case 49318: goto L2d;
                case 49319: goto L2a;
                case 49320: goto L27;
                case 49321: goto L24;
                case 49322: goto L27;
                case 49323: goto L24;
                case 49324: goto L2d;
                case 49325: goto L2a;
                case 49326: goto L27;
                case 49327: goto L24;
                case 52392: goto L21;
                case 52393: goto L21;
                case 52394: goto L21;
                case 52395: goto L21;
                case 52396: goto L21;
                case 52397: goto L21;
                case 52398: goto L21;
                case 53249: goto L52;
                case 53250: goto L4f;
                case 53251: goto L27;
                case 53253: goto L2d;
                default: goto L4;
            }
        L4:
            switch(r1) {
                case 44: goto L60;
                case 45: goto L60;
                case 46: goto L60;
                case 47: goto L5b;
                case 48: goto L5b;
                case 49: goto L5b;
                case 50: goto L5b;
                case 51: goto L5b;
                case 52: goto L5b;
                case 53: goto L58;
                case 54: goto L58;
                case 55: goto L58;
                case 56: goto L58;
                case 57: goto L58;
                case 58: goto L58;
                case 59: goto L4e;
                case 60: goto L5b;
                case 61: goto L58;
                case 62: goto L5b;
                case 63: goto L5b;
                case 64: goto L5b;
                case 65: goto L4b;
                case 66: goto L4b;
                case 67: goto L4b;
                case 68: goto L4b;
                case 69: goto L4b;
                case 70: goto L4b;
                default: goto L7;
            }
        L7:
            switch(r1) {
                case 103: goto L5b;
                case 104: goto L58;
                case 105: goto L58;
                case 106: goto L58;
                case 107: goto L58;
                case 108: goto L5b;
                case 109: goto L58;
                default: goto La;
            }
        La:
            switch(r1) {
                case 132: goto L48;
                case 133: goto L48;
                case 134: goto L48;
                case 135: goto L48;
                case 136: goto L48;
                case 137: goto L48;
                default: goto Ld;
            }
        Ld:
            switch(r1) {
                case 139: goto L5e;
                case 140: goto L5b;
                case 141: goto L58;
                default: goto L10;
            }
        L10:
            switch(r1) {
                case 143: goto L5e;
                case 144: goto L5b;
                case 145: goto L58;
                default: goto L13;
            }
        L13:
            switch(r1) {
                case 4865: goto L52;
                case 4866: goto L4f;
                case 4867: goto L21;
                case 4868: goto L2d;
                case 4869: goto L27;
                default: goto L16;
            }
        L16:
            switch(r1) {
                case 49155: goto L5e;
                case 49156: goto L5b;
                case 49157: goto L58;
                case 49158: goto L60;
                default: goto L19;
            }
        L19:
            switch(r1) {
                case 49160: goto L5e;
                case 49161: goto L5b;
                case 49162: goto L58;
                case 49163: goto L60;
                default: goto L1c;
            }
        L1c:
            switch(r1) {
                case 49165: goto L5e;
                case 49166: goto L5b;
                case 49167: goto L58;
                case 49168: goto L60;
                default: goto L1f;
            }
        L1f:
            r1 = -1
            return r1
        L21:
            r1 = 21
            return r1
        L24:
            r1 = 18
            return r1
        L27:
            r1 = 16
            return r1
        L2a:
            r1 = 17
            return r1
        L2d:
            r1 = 15
            return r1
        L30:
            r1 = 20
            return r1
        L33:
            r1 = 19
            return r1
        L36:
            r1 = 25
            return r1
        L39:
            r1 = 24
            return r1
        L3c:
            r1 = 23
            return r1
        L3f:
            r1 = 22
            return r1
        L42:
            r1 = 26
            return r1
        L45:
            r1 = 27
            return r1
        L48:
            r1 = 13
            return r1
        L4b:
            r1 = 12
            return r1
        L4e:
            return r0
        L4f:
            r1 = 11
            return r1
        L52:
            r1 = 10
            return r1
        L55:
            r1 = 14
            return r1
        L58:
            r1 = 9
            return r1
        L5b:
            r1 = 8
            return r1
        L5e:
            r1 = 7
            return r1
        L60:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.tls.TlsUtils.getEncryptionAlgorithm(int):int");
    }

    public static int getEncryptionAlgorithmType(int i2) {
        switch (i2) {
            case 0:
            case 1:
            case 2:
                return 0;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 12:
            case 13:
            case 14:
            case 22:
            case 23:
            case 28:
                return 1;
            case 10:
            case 11:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 24:
            case 25:
            case 26:
            case 27:
                return 2;
            default:
                return -1;
        }
    }

    public static byte[] getExtensionData(Hashtable hashtable, Integer num) {
        if (hashtable == null) {
            return null;
        }
        return (byte[]) hashtable.get(num);
    }

    public static short getHashAlgorithmForPRFAlgorithm(int i2) {
        if (i2 == 0 || i2 == 1) {
            throw new IllegalArgumentException("legacy PRF not a valid algorithm");
        }
        if (i2 != 2) {
            if (i2 != 3) {
                if (i2 != 4) {
                    if (i2 != 5) {
                        throw new IllegalArgumentException("unknown PRFAlgorithm: " + PRFAlgorithm.getText(i2));
                    }
                }
            }
            return (short) 5;
        }
        return (short) 4;
    }

    public static int getKeyExchangeAlgorithm(int i2) {
        switch (i2) {
            case 2:
            case 10:
            case CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA /* 150 */:
            case CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256 /* 156 */:
            case CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384 /* 157 */:
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 186 */:
            case 192:
            case CipherSuite.TLS_RSA_WITH_ARIA_128_CBC_SHA256 /* 49212 */:
            case CipherSuite.TLS_RSA_WITH_ARIA_256_CBC_SHA384 /* 49213 */:
            case CipherSuite.TLS_RSA_WITH_ARIA_128_GCM_SHA256 /* 49232 */:
            case CipherSuite.TLS_RSA_WITH_ARIA_256_GCM_SHA384 /* 49233 */:
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49274 */:
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49275 */:
            case CipherSuite.TLS_RSA_WITH_AES_128_CCM /* 49308 */:
            case CipherSuite.TLS_RSA_WITH_AES_256_CCM /* 49309 */:
            case CipherSuite.TLS_RSA_WITH_AES_128_CCM_8 /* 49312 */:
            case CipherSuite.TLS_RSA_WITH_AES_256_CCM_8 /* 49313 */:
                return 1;
            case 13:
            case CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA /* 151 */:
            case CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256 /* 164 */:
            case CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384 /* 165 */:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256 /* 187 */:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256 /* 193 */:
            case CipherSuite.TLS_DH_DSS_WITH_ARIA_128_CBC_SHA256 /* 49214 */:
            case CipherSuite.TLS_DH_DSS_WITH_ARIA_256_CBC_SHA384 /* 49215 */:
            case CipherSuite.TLS_DH_DSS_WITH_ARIA_128_GCM_SHA256 /* 49240 */:
            case CipherSuite.TLS_DH_DSS_WITH_ARIA_256_GCM_SHA384 /* 49241 */:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49282 */:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49283 */:
                return 7;
            case 16:
            case CipherSuite.TLS_DH_RSA_WITH_SEED_CBC_SHA /* 152 */:
            case CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256 /* 160 */:
            case CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384 /* 161 */:
            case 188:
            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 194 */:
            case CipherSuite.TLS_DH_RSA_WITH_ARIA_128_CBC_SHA256 /* 49216 */:
            case CipherSuite.TLS_DH_RSA_WITH_ARIA_256_CBC_SHA384 /* 49217 */:
            case CipherSuite.TLS_DH_RSA_WITH_ARIA_128_GCM_SHA256 /* 49236 */:
            case CipherSuite.TLS_DH_RSA_WITH_ARIA_256_GCM_SHA384 /* 49237 */:
            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49278 */:
            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49279 */:
                return 9;
            case 19:
            case CipherSuite.TLS_DHE_DSS_WITH_SEED_CBC_SHA /* 153 */:
            case CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256 /* 162 */:
            case CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384 /* 163 */:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256 /* 189 */:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256 /* 195 */:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_128_CBC_SHA256 /* 49218 */:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_256_CBC_SHA384 /* 49219 */:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_128_GCM_SHA256 /* 49238 */:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_256_GCM_SHA384 /* 49239 */:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49280 */:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49281 */:
                return 3;
            case 22:
            case CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA /* 154 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256 /* 158 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 /* 159 */:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 190 */:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 196 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_128_CBC_SHA256 /* 49220 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_256_CBC_SHA384 /* 49221 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_128_GCM_SHA256 /* 49234 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_256_GCM_SHA384 /* 49235 */:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49276 */:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49277 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM /* 49310 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM /* 49311 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8 /* 49314 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8 /* 49315 */:
            case CipherSuite.TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52394 */:
                return 5;
            case 27:
            case CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA /* 155 */:
            case CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256 /* 166 */:
            case CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384 /* 167 */:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256 /* 191 */:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256 /* 197 */:
            case CipherSuite.TLS_DH_anon_WITH_ARIA_128_CBC_SHA256 /* 49222 */:
            case CipherSuite.TLS_DH_anon_WITH_ARIA_256_CBC_SHA384 /* 49223 */:
            case CipherSuite.TLS_DH_anon_WITH_ARIA_128_GCM_SHA256 /* 49242 */:
            case CipherSuite.TLS_DH_anon_WITH_ARIA_256_GCM_SHA384 /* 49243 */:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_GCM_SHA256 /* 49284 */:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_GCM_SHA384 /* 49285 */:
                return 11;
            case CipherSuite.TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA /* 147 */:
            case CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA /* 148 */:
            case CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA /* 149 */:
            case CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256 /* 172 */:
            case CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384 /* 173 */:
            case CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA256 /* 182 */:
            case CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384 /* 183 */:
            case CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA256 /* 184 */:
            case CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA384 /* 185 */:
            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_128_CBC_SHA256 /* 49256 */:
            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_256_CBC_SHA384 /* 49257 */:
            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_128_GCM_SHA256 /* 49262 */:
            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_256_GCM_SHA384 /* 49263 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49298 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49299 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49304 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49305 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52398 */:
                return 15;
            case CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256 /* 168 */:
            case CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384 /* 169 */:
            case CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256 /* 174 */:
            case CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384 /* 175 */:
            case CipherSuite.TLS_PSK_WITH_NULL_SHA256 /* 176 */:
            case CipherSuite.TLS_PSK_WITH_NULL_SHA384 /* 177 */:
            case CipherSuite.TLS_PSK_WITH_ARIA_128_CBC_SHA256 /* 49252 */:
            case CipherSuite.TLS_PSK_WITH_ARIA_256_CBC_SHA384 /* 49253 */:
            case CipherSuite.TLS_PSK_WITH_ARIA_128_GCM_SHA256 /* 49258 */:
            case CipherSuite.TLS_PSK_WITH_ARIA_256_GCM_SHA384 /* 49259 */:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49294 */:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49295 */:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49300 */:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49301 */:
            case CipherSuite.TLS_PSK_WITH_AES_128_CCM /* 49316 */:
            case CipherSuite.TLS_PSK_WITH_AES_256_CCM /* 49317 */:
            case CipherSuite.TLS_PSK_WITH_AES_128_CCM_8 /* 49320 */:
            case CipherSuite.TLS_PSK_WITH_AES_256_CCM_8 /* 49321 */:
            case CipherSuite.TLS_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52395 */:
                return 13;
            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256 /* 170 */:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384 /* 171 */:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256 /* 178 */:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384 /* 179 */:
            case CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256 /* 180 */:
            case CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384 /* 181 */:
            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_128_CBC_SHA256 /* 49254 */:
            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_256_CBC_SHA384 /* 49255 */:
            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_128_GCM_SHA256 /* 49260 */:
            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_256_GCM_SHA384 /* 49261 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49296 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49297 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49302 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49303 */:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM /* 49318 */:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM /* 49319 */:
            case CipherSuite.TLS_PSK_DHE_WITH_AES_128_CCM_8 /* 49322 */:
            case CipherSuite.TLS_PSK_DHE_WITH_AES_256_CCM_8 /* 49323 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52397 */:
                return 14;
            case CipherSuite.TLS_SM4_GCM_SM3 /* 198 */:
            case CipherSuite.TLS_SM4_CCM_SM3 /* 199 */:
                return 0;
            case CipherSuite.TLS_ECDH_ECDSA_WITH_NULL_SHA /* 49153 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 /* 49189 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 /* 49190 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 /* 49197 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 /* 49198 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_128_CBC_SHA256 /* 49226 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_256_CBC_SHA384 /* 49227 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_128_GCM_SHA256 /* 49246 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_256_GCM_SHA384 /* 49247 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49268 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49269 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49288 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49289 */:
                return 16;
            case CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA /* 49170 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA /* 49171 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA /* 49172 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 /* 49191 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 /* 49192 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 /* 49199 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 /* 49200 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_128_CBC_SHA256 /* 49228 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_256_CBC_SHA384 /* 49229 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_128_GCM_SHA256 /* 49248 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_256_GCM_SHA384 /* 49249 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49270 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49271 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49290 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49291 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52392 */:
                return 19;
            case CipherSuite.TLS_ECDH_anon_WITH_NULL_SHA /* 49173 */:
            case CipherSuite.TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA /* 49175 */:
            case CipherSuite.TLS_ECDH_anon_WITH_AES_128_CBC_SHA /* 49176 */:
            case CipherSuite.TLS_ECDH_anon_WITH_AES_256_CBC_SHA /* 49177 */:
                return 20;
            case CipherSuite.TLS_SRP_SHA_WITH_3DES_EDE_CBC_SHA /* 49178 */:
            case CipherSuite.TLS_SRP_SHA_WITH_AES_128_CBC_SHA /* 49181 */:
            case CipherSuite.TLS_SRP_SHA_WITH_AES_256_CBC_SHA /* 49184 */:
                return 21;
            case CipherSuite.TLS_SRP_SHA_RSA_WITH_3DES_EDE_CBC_SHA /* 49179 */:
            case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_128_CBC_SHA /* 49182 */:
            case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_256_CBC_SHA /* 49185 */:
                return 23;
            case CipherSuite.TLS_SRP_SHA_DSS_WITH_3DES_EDE_CBC_SHA /* 49180 */:
            case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_128_CBC_SHA /* 49183 */:
            case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_256_CBC_SHA /* 49186 */:
                return 22;
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 /* 49187 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 /* 49188 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 /* 49195 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 /* 49196 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_128_CBC_SHA256 /* 49224 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_256_CBC_SHA384 /* 49225 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_128_GCM_SHA256 /* 49244 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_256_GCM_SHA384 /* 49245 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49266 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49267 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49286 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49287 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM /* 49324 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM /* 49325 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8 /* 49326 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8 /* 49327 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 /* 52393 */:
                return 17;
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 /* 49193 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 /* 49194 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 /* 49201 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 /* 49202 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_128_CBC_SHA256 /* 49230 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_256_CBC_SHA384 /* 49231 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_128_GCM_SHA256 /* 49250 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_256_GCM_SHA384 /* 49251 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49272 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49273 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49292 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49293 */:
                return 18;
            case CipherSuite.TLS_ECDHE_PSK_WITH_3DES_EDE_CBC_SHA /* 49204 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA /* 49205 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA /* 49206 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256 /* 49207 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384 /* 49208 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA /* 49209 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA256 /* 49210 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA384 /* 49211 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_ARIA_128_CBC_SHA256 /* 49264 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_ARIA_256_CBC_SHA384 /* 49265 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49306 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49307 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52396 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_GCM_SHA256 /* 53249 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_GCM_SHA384 /* 53250 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CCM_8_SHA256 /* 53251 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CCM_SHA256 /* 53253 */:
                return 24;
            default:
                switch (i2) {
                    case 44:
                        return 13;
                    case 45:
                        return 14;
                    case 46:
                        return 15;
                    case 47:
                    case 53:
                    case 59:
                    case 60:
                    case 61:
                    case 65:
                        return 1;
                    case 48:
                    case 54:
                    case 62:
                    case 66:
                        return 7;
                    case 49:
                    case 55:
                    case 63:
                    case 67:
                        return 9;
                    case 50:
                    case 56:
                    case 64:
                    case 68:
                        return 3;
                    case 51:
                    case 57:
                    case 69:
                        return 5;
                    case 52:
                    case 58:
                    case 70:
                        return 11;
                    default:
                        switch (i2) {
                            case 103:
                            case 107:
                                return 5;
                            case 104:
                                return 7;
                            case 105:
                                return 9;
                            case 106:
                                return 3;
                            case 108:
                            case 109:
                                return 11;
                            default:
                                switch (i2) {
                                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA /* 132 */:
                                        return 1;
                                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA /* 133 */:
                                        return 7;
                                    case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA /* 134 */:
                                        return 9;
                                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA /* 135 */:
                                        return 3;
                                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA /* 136 */:
                                        return 5;
                                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA /* 137 */:
                                        return 11;
                                    default:
                                        switch (i2) {
                                            case CipherSuite.TLS_PSK_WITH_3DES_EDE_CBC_SHA /* 139 */:
                                            case CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA /* 140 */:
                                            case CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA /* 141 */:
                                                return 13;
                                            default:
                                                switch (i2) {
                                                    case CipherSuite.TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA /* 143 */:
                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA /* 144 */:
                                                    case 145:
                                                        return 14;
                                                    default:
                                                        switch (i2) {
                                                            case CipherSuite.TLS_AES_128_GCM_SHA256 /* 4865 */:
                                                            case CipherSuite.TLS_AES_256_GCM_SHA384 /* 4866 */:
                                                            case CipherSuite.TLS_CHACHA20_POLY1305_SHA256 /* 4867 */:
                                                            case CipherSuite.TLS_AES_128_CCM_SHA256 /* 4868 */:
                                                            case CipherSuite.TLS_AES_128_CCM_8_SHA256 /* 4869 */:
                                                                return 0;
                                                            default:
                                                                switch (i2) {
                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA /* 49155 */:
                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA /* 49156 */:
                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA /* 49157 */:
                                                                        return 16;
                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA /* 49158 */:
                                                                        return 17;
                                                                    default:
                                                                        switch (i2) {
                                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA /* 49160 */:
                                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA /* 49161 */:
                                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA /* 49162 */:
                                                                                return 17;
                                                                            case CipherSuite.TLS_ECDH_RSA_WITH_NULL_SHA /* 49163 */:
                                                                                return 18;
                                                                            default:
                                                                                switch (i2) {
                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA /* 49165 */:
                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA /* 49166 */:
                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA /* 49167 */:
                                                                                        return 18;
                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_NULL_SHA /* 49168 */:
                                                                                        return 19;
                                                                                    default:
                                                                                        return -1;
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }

    public static Vector getKeyExchangeAlgorithms(int[] iArr) {
        Vector vector = new Vector();
        if (iArr != null) {
            for (int i2 : iArr) {
                addToSet(vector, getKeyExchangeAlgorithm(i2));
            }
            vector.removeElement(Integers.valueOf(-1));
        }
        return vector;
    }

    public static short getLegacyClientCertType(short s2) {
        short s3 = 1;
        if (s2 != 1) {
            s3 = 2;
            if (s2 != 2) {
                return s2 != 3 ? (short) -1 : (short) 64;
            }
        }
        return s3;
    }

    public static short getLegacySignatureAlgorithmClient(short s2) {
        short s3 = 1;
        if (s2 != 1) {
            s3 = 2;
            if (s2 != 2) {
                return s2 != 64 ? (short) -1 : (short) 3;
            }
        }
        return s3;
    }

    public static short getLegacySignatureAlgorithmClientCert(short s2) {
        if (s2 != 1) {
            if (s2 != 2) {
                if (s2 != 3) {
                    if (s2 != 4) {
                        switch (s2) {
                            case 64:
                            case 66:
                                return (short) 3;
                            case 65:
                                break;
                            default:
                                return (short) -1;
                        }
                    }
                }
            }
            return (short) 2;
        }
        return (short) 1;
    }

    public static short getLegacySignatureAlgorithmServer(int i2) {
        if (i2 != 3) {
            if (i2 != 5) {
                if (i2 != 17) {
                    if (i2 != 19) {
                        if (i2 != 22) {
                            return i2 != 23 ? (short) -1 : (short) 1;
                        }
                        return (short) 2;
                    }
                    return (short) 1;
                }
                return (short) 3;
            }
            return (short) 1;
        }
        return (short) 2;
    }

    public static short getLegacySignatureAlgorithmServerCert(int i2) {
        if (i2 != 1) {
            if (i2 == 3) {
                return (short) 2;
            }
            if (i2 != 5) {
                if (i2 == 7) {
                    return (short) 2;
                }
                if (i2 != 9) {
                    if (i2 == 22) {
                        return (short) 2;
                    }
                    if (i2 != 23) {
                        switch (i2) {
                            case 15:
                            case 18:
                            case 19:
                                break;
                            case 16:
                            case 17:
                                return (short) 3;
                            default:
                                return (short) -1;
                        }
                    }
                }
            }
        }
        return (short) 1;
    }

    public static Vector getLegacySupportedSignatureAlgorithms() {
        Vector vector = new Vector(3);
        vector.add(SignatureAndHashAlgorithm.getInstance((short) 2, (short) 2));
        vector.add(SignatureAndHashAlgorithm.getInstance((short) 2, (short) 3));
        vector.add(SignatureAndHashAlgorithm.getInstance((short) 2, (short) 1));
        return vector;
    }

    public static int getMACAlgorithm(int i2) {
        switch (i2) {
            case 2:
            case 10:
            case 13:
            case 16:
            case 19:
            case 22:
            case 27:
            case CipherSuite.TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA /* 147 */:
            case CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA /* 148 */:
            case CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA /* 149 */:
            case CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA /* 150 */:
            case CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA /* 151 */:
            case CipherSuite.TLS_DH_RSA_WITH_SEED_CBC_SHA /* 152 */:
            case CipherSuite.TLS_DHE_DSS_WITH_SEED_CBC_SHA /* 153 */:
            case CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA /* 154 */:
            case CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA /* 155 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_NULL_SHA /* 49153 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA /* 49170 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA /* 49171 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA /* 49172 */:
            case CipherSuite.TLS_ECDH_anon_WITH_NULL_SHA /* 49173 */:
            case CipherSuite.TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA /* 49175 */:
            case CipherSuite.TLS_ECDH_anon_WITH_AES_128_CBC_SHA /* 49176 */:
            case CipherSuite.TLS_ECDH_anon_WITH_AES_256_CBC_SHA /* 49177 */:
            case CipherSuite.TLS_SRP_SHA_WITH_3DES_EDE_CBC_SHA /* 49178 */:
            case CipherSuite.TLS_SRP_SHA_RSA_WITH_3DES_EDE_CBC_SHA /* 49179 */:
            case CipherSuite.TLS_SRP_SHA_DSS_WITH_3DES_EDE_CBC_SHA /* 49180 */:
            case CipherSuite.TLS_SRP_SHA_WITH_AES_128_CBC_SHA /* 49181 */:
            case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_128_CBC_SHA /* 49182 */:
            case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_128_CBC_SHA /* 49183 */:
            case CipherSuite.TLS_SRP_SHA_WITH_AES_256_CBC_SHA /* 49184 */:
            case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_256_CBC_SHA /* 49185 */:
            case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_256_CBC_SHA /* 49186 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_3DES_EDE_CBC_SHA /* 49204 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA /* 49205 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA /* 49206 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA /* 49209 */:
                return 2;
            case CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256 /* 156 */:
            case CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384 /* 157 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256 /* 158 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 /* 159 */:
            case CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256 /* 160 */:
            case CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384 /* 161 */:
            case CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256 /* 162 */:
            case CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384 /* 163 */:
            case CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256 /* 164 */:
            case CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384 /* 165 */:
            case CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256 /* 166 */:
            case CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384 /* 167 */:
            case CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256 /* 168 */:
            case CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384 /* 169 */:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256 /* 170 */:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384 /* 171 */:
            case CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256 /* 172 */:
            case CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384 /* 173 */:
            case CipherSuite.TLS_SM4_GCM_SM3 /* 198 */:
            case CipherSuite.TLS_SM4_CCM_SM3 /* 199 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 /* 49195 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 /* 49196 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 /* 49197 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 /* 49198 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 /* 49199 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 /* 49200 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 /* 49201 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 /* 49202 */:
            case CipherSuite.TLS_RSA_WITH_ARIA_128_GCM_SHA256 /* 49232 */:
            case CipherSuite.TLS_RSA_WITH_ARIA_256_GCM_SHA384 /* 49233 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_128_GCM_SHA256 /* 49234 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_256_GCM_SHA384 /* 49235 */:
            case CipherSuite.TLS_DH_RSA_WITH_ARIA_128_GCM_SHA256 /* 49236 */:
            case CipherSuite.TLS_DH_RSA_WITH_ARIA_256_GCM_SHA384 /* 49237 */:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_128_GCM_SHA256 /* 49238 */:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_256_GCM_SHA384 /* 49239 */:
            case CipherSuite.TLS_DH_DSS_WITH_ARIA_128_GCM_SHA256 /* 49240 */:
            case CipherSuite.TLS_DH_DSS_WITH_ARIA_256_GCM_SHA384 /* 49241 */:
            case CipherSuite.TLS_DH_anon_WITH_ARIA_128_GCM_SHA256 /* 49242 */:
            case CipherSuite.TLS_DH_anon_WITH_ARIA_256_GCM_SHA384 /* 49243 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_128_GCM_SHA256 /* 49244 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_256_GCM_SHA384 /* 49245 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_128_GCM_SHA256 /* 49246 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_256_GCM_SHA384 /* 49247 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_128_GCM_SHA256 /* 49248 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_256_GCM_SHA384 /* 49249 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_128_GCM_SHA256 /* 49250 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_256_GCM_SHA384 /* 49251 */:
            case CipherSuite.TLS_PSK_WITH_ARIA_128_GCM_SHA256 /* 49258 */:
            case CipherSuite.TLS_PSK_WITH_ARIA_256_GCM_SHA384 /* 49259 */:
            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_128_GCM_SHA256 /* 49260 */:
            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_256_GCM_SHA384 /* 49261 */:
            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_128_GCM_SHA256 /* 49262 */:
            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_256_GCM_SHA384 /* 49263 */:
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49274 */:
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49275 */:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49276 */:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49277 */:
            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49278 */:
            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49279 */:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49280 */:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49281 */:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49282 */:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49283 */:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_GCM_SHA256 /* 49284 */:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_GCM_SHA384 /* 49285 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49286 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49287 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49288 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49289 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49290 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49291 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49292 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49293 */:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49294 */:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49295 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49296 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49297 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49298 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49299 */:
            case CipherSuite.TLS_RSA_WITH_AES_128_CCM /* 49308 */:
            case CipherSuite.TLS_RSA_WITH_AES_256_CCM /* 49309 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM /* 49310 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM /* 49311 */:
            case CipherSuite.TLS_RSA_WITH_AES_128_CCM_8 /* 49312 */:
            case CipherSuite.TLS_RSA_WITH_AES_256_CCM_8 /* 49313 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8 /* 49314 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8 /* 49315 */:
            case CipherSuite.TLS_PSK_WITH_AES_128_CCM /* 49316 */:
            case CipherSuite.TLS_PSK_WITH_AES_256_CCM /* 49317 */:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM /* 49318 */:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM /* 49319 */:
            case CipherSuite.TLS_PSK_WITH_AES_128_CCM_8 /* 49320 */:
            case CipherSuite.TLS_PSK_WITH_AES_256_CCM_8 /* 49321 */:
            case CipherSuite.TLS_PSK_DHE_WITH_AES_128_CCM_8 /* 49322 */:
            case CipherSuite.TLS_PSK_DHE_WITH_AES_256_CCM_8 /* 49323 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM /* 49324 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM /* 49325 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8 /* 49326 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8 /* 49327 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52392 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 /* 52393 */:
            case CipherSuite.TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52394 */:
            case CipherSuite.TLS_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52395 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52396 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52397 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52398 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_GCM_SHA256 /* 53249 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_GCM_SHA384 /* 53250 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CCM_8_SHA256 /* 53251 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CCM_SHA256 /* 53253 */:
                return 0;
            case CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256 /* 174 */:
            case CipherSuite.TLS_PSK_WITH_NULL_SHA256 /* 176 */:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256 /* 178 */:
            case CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256 /* 180 */:
            case CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA256 /* 182 */:
            case CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA256 /* 184 */:
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 186 */:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256 /* 187 */:
            case 188:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256 /* 189 */:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 190 */:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256 /* 191 */:
            case 192:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256 /* 193 */:
            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 194 */:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256 /* 195 */:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 196 */:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256 /* 197 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 /* 49187 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 /* 49189 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 /* 49191 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 /* 49193 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256 /* 49207 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA256 /* 49210 */:
            case CipherSuite.TLS_RSA_WITH_ARIA_128_CBC_SHA256 /* 49212 */:
            case CipherSuite.TLS_DH_DSS_WITH_ARIA_128_CBC_SHA256 /* 49214 */:
            case CipherSuite.TLS_DH_RSA_WITH_ARIA_128_CBC_SHA256 /* 49216 */:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_128_CBC_SHA256 /* 49218 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_128_CBC_SHA256 /* 49220 */:
            case CipherSuite.TLS_DH_anon_WITH_ARIA_128_CBC_SHA256 /* 49222 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_128_CBC_SHA256 /* 49224 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_128_CBC_SHA256 /* 49226 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_128_CBC_SHA256 /* 49228 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_128_CBC_SHA256 /* 49230 */:
            case CipherSuite.TLS_PSK_WITH_ARIA_128_CBC_SHA256 /* 49252 */:
            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_128_CBC_SHA256 /* 49254 */:
            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_128_CBC_SHA256 /* 49256 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_ARIA_128_CBC_SHA256 /* 49264 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49266 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49268 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49270 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49272 */:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49300 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49302 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49304 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49306 */:
                return 3;
            case CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384 /* 175 */:
            case CipherSuite.TLS_PSK_WITH_NULL_SHA384 /* 177 */:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384 /* 179 */:
            case CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384 /* 181 */:
            case CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384 /* 183 */:
            case CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA384 /* 185 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 /* 49188 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 /* 49190 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 /* 49192 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 /* 49194 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384 /* 49208 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA384 /* 49211 */:
            case CipherSuite.TLS_RSA_WITH_ARIA_256_CBC_SHA384 /* 49213 */:
            case CipherSuite.TLS_DH_DSS_WITH_ARIA_256_CBC_SHA384 /* 49215 */:
            case CipherSuite.TLS_DH_RSA_WITH_ARIA_256_CBC_SHA384 /* 49217 */:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_256_CBC_SHA384 /* 49219 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_256_CBC_SHA384 /* 49221 */:
            case CipherSuite.TLS_DH_anon_WITH_ARIA_256_CBC_SHA384 /* 49223 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_256_CBC_SHA384 /* 49225 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_ARIA_256_CBC_SHA384 /* 49227 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_256_CBC_SHA384 /* 49229 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_ARIA_256_CBC_SHA384 /* 49231 */:
            case CipherSuite.TLS_PSK_WITH_ARIA_256_CBC_SHA384 /* 49253 */:
            case CipherSuite.TLS_DHE_PSK_WITH_ARIA_256_CBC_SHA384 /* 49255 */:
            case CipherSuite.TLS_RSA_PSK_WITH_ARIA_256_CBC_SHA384 /* 49257 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_ARIA_256_CBC_SHA384 /* 49265 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49267 */:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49269 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49271 */:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49273 */:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49301 */:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49303 */:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49305 */:
            case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49307 */:
                return 4;
            default:
                switch (i2) {
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 65:
                    case 66:
                    case 67:
                    case 68:
                    case 69:
                    case 70:
                        return 2;
                    case 59:
                    case 60:
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                        return 3;
                    default:
                        switch (i2) {
                            case 103:
                            case 104:
                            case 105:
                            case 106:
                            case 107:
                            case 108:
                            case 109:
                                return 3;
                            default:
                                switch (i2) {
                                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA /* 132 */:
                                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA /* 133 */:
                                    case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA /* 134 */:
                                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA /* 135 */:
                                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA /* 136 */:
                                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA /* 137 */:
                                        return 2;
                                    default:
                                        switch (i2) {
                                            case CipherSuite.TLS_PSK_WITH_3DES_EDE_CBC_SHA /* 139 */:
                                            case CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA /* 140 */:
                                            case CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA /* 141 */:
                                                return 2;
                                            default:
                                                switch (i2) {
                                                    case CipherSuite.TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA /* 143 */:
                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA /* 144 */:
                                                    case 145:
                                                        return 2;
                                                    default:
                                                        switch (i2) {
                                                            case CipherSuite.TLS_AES_128_GCM_SHA256 /* 4865 */:
                                                            case CipherSuite.TLS_AES_256_GCM_SHA384 /* 4866 */:
                                                            case CipherSuite.TLS_CHACHA20_POLY1305_SHA256 /* 4867 */:
                                                            case CipherSuite.TLS_AES_128_CCM_SHA256 /* 4868 */:
                                                            case CipherSuite.TLS_AES_128_CCM_8_SHA256 /* 4869 */:
                                                                return 0;
                                                            default:
                                                                switch (i2) {
                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA /* 49155 */:
                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA /* 49156 */:
                                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA /* 49157 */:
                                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA /* 49158 */:
                                                                        return 2;
                                                                    default:
                                                                        switch (i2) {
                                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA /* 49160 */:
                                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA /* 49161 */:
                                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA /* 49162 */:
                                                                            case CipherSuite.TLS_ECDH_RSA_WITH_NULL_SHA /* 49163 */:
                                                                                return 2;
                                                                            default:
                                                                                switch (i2) {
                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA /* 49165 */:
                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA /* 49166 */:
                                                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA /* 49167 */:
                                                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_NULL_SHA /* 49168 */:
                                                                                        return 2;
                                                                                    default:
                                                                                        return -1;
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:817)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    public static org.bouncycastle.tls.ProtocolVersion getMinimumVersion(int r0) {
        /*
            switch(r0) {
                case 59: goto L24;
                case 60: goto L24;
                case 61: goto L24;
                case 62: goto L24;
                case 63: goto L24;
                case 64: goto L24;
                default: goto L3;
            }
        L3:
            switch(r0) {
                case 103: goto L24;
                case 104: goto L24;
                case 105: goto L24;
                case 106: goto L24;
                case 107: goto L24;
                case 108: goto L24;
                case 109: goto L24;
                default: goto L6;
            }
        L6:
            switch(r0) {
                case 156: goto L24;
                case 157: goto L24;
                case 158: goto L24;
                case 159: goto L24;
                case 160: goto L24;
                case 161: goto L24;
                case 162: goto L24;
                case 163: goto L24;
                case 164: goto L24;
                case 165: goto L24;
                case 166: goto L24;
                case 167: goto L24;
                case 168: goto L24;
                case 169: goto L24;
                case 170: goto L24;
                case 171: goto L24;
                case 172: goto L24;
                case 173: goto L24;
                default: goto L9;
            }
        L9:
            switch(r0) {
                case 186: goto L24;
                case 187: goto L24;
                case 188: goto L24;
                case 189: goto L24;
                case 190: goto L24;
                case 191: goto L24;
                case 192: goto L24;
                case 193: goto L24;
                case 194: goto L24;
                case 195: goto L24;
                case 196: goto L24;
                case 197: goto L24;
                case 198: goto L21;
                case 199: goto L21;
                default: goto Lc;
            }
        Lc:
            switch(r0) {
                case 4865: goto L21;
                case 4866: goto L21;
                case 4867: goto L21;
                case 4868: goto L21;
                case 4869: goto L21;
                default: goto Lf;
            }
        Lf:
            switch(r0) {
                case 49187: goto L24;
                case 49188: goto L24;
                case 49189: goto L24;
                case 49190: goto L24;
                case 49191: goto L24;
                case 49192: goto L24;
                case 49193: goto L24;
                case 49194: goto L24;
                case 49195: goto L24;
                case 49196: goto L24;
                case 49197: goto L24;
                case 49198: goto L24;
                case 49199: goto L24;
                case 49200: goto L24;
                case 49201: goto L24;
                case 49202: goto L24;
                default: goto L12;
            }
        L12:
            switch(r0) {
                case 49212: goto L24;
                case 49213: goto L24;
                case 49214: goto L24;
                case 49215: goto L24;
                case 49216: goto L24;
                case 49217: goto L24;
                case 49218: goto L24;
                case 49219: goto L24;
                case 49220: goto L24;
                case 49221: goto L24;
                case 49222: goto L24;
                case 49223: goto L24;
                case 49224: goto L24;
                case 49225: goto L24;
                case 49226: goto L24;
                case 49227: goto L24;
                case 49228: goto L24;
                case 49229: goto L24;
                case 49230: goto L24;
                case 49231: goto L24;
                case 49232: goto L24;
                case 49233: goto L24;
                case 49234: goto L24;
                case 49235: goto L24;
                case 49236: goto L24;
                case 49237: goto L24;
                case 49238: goto L24;
                case 49239: goto L24;
                case 49240: goto L24;
                case 49241: goto L24;
                case 49242: goto L24;
                case 49243: goto L24;
                case 49244: goto L24;
                case 49245: goto L24;
                case 49246: goto L24;
                case 49247: goto L24;
                case 49248: goto L24;
                case 49249: goto L24;
                case 49250: goto L24;
                case 49251: goto L24;
                case 49252: goto L24;
                case 49253: goto L24;
                case 49254: goto L24;
                case 49255: goto L24;
                case 49256: goto L24;
                case 49257: goto L24;
                case 49258: goto L24;
                case 49259: goto L24;
                case 49260: goto L24;
                case 49261: goto L24;
                case 49262: goto L24;
                case 49263: goto L24;
                case 49264: goto L24;
                case 49265: goto L24;
                case 49266: goto L24;
                case 49267: goto L24;
                case 49268: goto L24;
                case 49269: goto L24;
                case 49270: goto L24;
                case 49271: goto L24;
                case 49272: goto L24;
                case 49273: goto L24;
                case 49274: goto L24;
                case 49275: goto L24;
                case 49276: goto L24;
                case 49277: goto L24;
                case 49278: goto L24;
                case 49279: goto L24;
                case 49280: goto L24;
                case 49281: goto L24;
                case 49282: goto L24;
                case 49283: goto L24;
                case 49284: goto L24;
                case 49285: goto L24;
                case 49286: goto L24;
                case 49287: goto L24;
                case 49288: goto L24;
                case 49289: goto L24;
                case 49290: goto L24;
                case 49291: goto L24;
                case 49292: goto L24;
                case 49293: goto L24;
                case 49294: goto L24;
                case 49295: goto L24;
                case 49296: goto L24;
                case 49297: goto L24;
                case 49298: goto L24;
                case 49299: goto L24;
                case 53253: goto L24;
                default: goto L15;
            }
        L15:
            switch(r0) {
                case 49308: goto L24;
                case 49309: goto L24;
                case 49310: goto L24;
                case 49311: goto L24;
                case 49312: goto L24;
                case 49313: goto L24;
                case 49314: goto L24;
                case 49315: goto L24;
                case 49316: goto L24;
                case 49317: goto L24;
                case 49318: goto L24;
                case 49319: goto L24;
                case 49320: goto L24;
                case 49321: goto L24;
                case 49322: goto L24;
                case 49323: goto L24;
                case 49324: goto L24;
                case 49325: goto L24;
                case 49326: goto L24;
                case 49327: goto L24;
                default: goto L18;
            }
        L18:
            switch(r0) {
                case 52392: goto L24;
                case 52393: goto L24;
                case 52394: goto L24;
                case 52395: goto L24;
                case 52396: goto L24;
                case 52397: goto L24;
                case 52398: goto L24;
                default: goto L1b;
            }
        L1b:
            switch(r0) {
                case 53249: goto L24;
                case 53250: goto L24;
                case 53251: goto L24;
                default: goto L1e;
            }
        L1e:
            org.bouncycastle.tls.ProtocolVersion r0 = org.bouncycastle.tls.ProtocolVersion.SSLv3
            return r0
        L21:
            org.bouncycastle.tls.ProtocolVersion r0 = org.bouncycastle.tls.ProtocolVersion.TLSv13
            return r0
        L24:
            org.bouncycastle.tls.ProtocolVersion r0 = org.bouncycastle.tls.ProtocolVersion.TLSv12
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.tls.TlsUtils.getMinimumVersion(int):org.bouncycastle.tls.ProtocolVersion");
    }

    public static Vector getNamedGroupRoles(Vector vector) {
        Vector vector2 = new Vector();
        for (int i2 = 0; i2 < vector.size(); i2++) {
            int intValue = ((Integer) vector.elementAt(i2)).intValue();
            if (intValue != 0) {
                if (intValue != 3 && intValue != 5 && intValue != 7 && intValue != 9 && intValue != 11 && intValue != 14) {
                    if (intValue != 24) {
                        switch (intValue) {
                            case 16:
                            case 17:
                                addToSet(vector2, 2);
                                addToSet(vector2, 3);
                                break;
                        }
                    }
                } else {
                    addToSet(vector2, 1);
                }
            } else {
                addToSet(vector2, 1);
            }
            addToSet(vector2, 2);
        }
        return vector2;
    }

    public static Vector getNamedGroupRoles(int[] iArr) {
        return getNamedGroupRoles(getKeyExchangeAlgorithms(iArr));
    }

    public static ASN1ObjectIdentifier getOIDForHashAlgorithm(short s2) {
        switch (s2) {
            case 1:
                return PKCSObjectIdentifiers.md5;
            case 2:
                return X509ObjectIdentifiers.id_SHA1;
            case 3:
                return NISTObjectIdentifiers.id_sha224;
            case 4:
                return NISTObjectIdentifiers.id_sha256;
            case 5:
                return NISTObjectIdentifiers.id_sha384;
            case 6:
                return NISTObjectIdentifiers.id_sha512;
            default:
                throw new IllegalArgumentException("invalid HashAlgorithm: " + HashAlgorithm.getText(s2));
        }
    }

    public static SignatureAndHashAlgorithm getSignatureAndHashAlgorithm(TlsContext tlsContext, TlsCredentialedSigner tlsCredentialedSigner) {
        return R(tlsContext.getServerVersion(), tlsCredentialedSigner);
    }

    public static int[] getSupportedCipherSuites(TlsCrypto tlsCrypto, int[] iArr) {
        return getSupportedCipherSuites(tlsCrypto, iArr, 0, iArr.length);
    }

    public static int[] getSupportedCipherSuites(TlsCrypto tlsCrypto, int[] iArr, int i2) {
        return getSupportedCipherSuites(tlsCrypto, iArr, 0, i2);
    }

    public static int[] getSupportedCipherSuites(TlsCrypto tlsCrypto, int[] iArr, int i2, int i3) {
        int[] iArr2 = new int[i3];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = iArr[i2 + i5];
            if (isSupportedCipherSuite(tlsCrypto, i6)) {
                iArr2[i4] = i6;
                i4++;
            }
        }
        return i4 < i3 ? Arrays.copyOf(iArr2, i4) : iArr2;
    }

    public static Vector getUsableSignatureAlgorithms(Vector vector) {
        if (vector == null) {
            Vector vector2 = new Vector(3);
            vector2.addElement(Shorts.valueOf((short) 1));
            vector2.addElement(Shorts.valueOf((short) 2));
            vector2.addElement(Shorts.valueOf((short) 3));
            return vector2;
        }
        Vector vector3 = new Vector();
        for (int i2 = 0; i2 < vector.size(); i2++) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) vector.elementAt(i2);
            if (signatureAndHashAlgorithm.getHash() >= 2) {
                Short valueOf = Shorts.valueOf(signatureAndHashAlgorithm.getSignature());
                if (!vector3.contains(valueOf)) {
                    vector3.addElement(valueOf);
                }
            }
        }
        return vector3;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static byte[] h(TlsContext tlsContext, TlsCertificate tlsCertificate, byte[] bArr, int i2, int i3) {
        short s2;
        TlsHash createHash;
        String sigAlgOID = tlsCertificate.getSigAlgOID();
        short s3 = 4;
        if (sigAlgOID != null) {
            if (PKCSObjectIdentifiers.id_RSASSA_PSS.getId().equals(sigAlgOID)) {
                RSASSAPSSparams rSASSAPSSparams = RSASSAPSSparams.getInstance(tlsCertificate.getSigAlgParams());
                if (rSASSAPSSparams != null) {
                    ASN1ObjectIdentifier algorithm = rSASSAPSSparams.getHashAlgorithm().getAlgorithm();
                    if (NISTObjectIdentifiers.id_sha256.equals((ASN1Primitive) algorithm)) {
                        s2 = 4;
                    } else if (NISTObjectIdentifiers.id_sha384.equals((ASN1Primitive) algorithm)) {
                        s2 = 5;
                    } else if (NISTObjectIdentifiers.id_sha512.equals((ASN1Primitive) algorithm)) {
                        s2 = 6;
                    }
                }
            } else {
                SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) CERT_SIG_ALG_OIDS.get(sigAlgOID);
                if (signatureAndHashAlgorithm != null) {
                    s2 = signatureAndHashAlgorithm.getHash();
                }
            }
            if (s2 != 1 && s2 != 2) {
                s3 = s2 == 8 ? s2 : (short) 0;
            }
            if (s3 != 0 || (createHash = createHash(tlsContext.getCrypto(), s3)) == null) {
                return EMPTY_BYTES;
            }
            createHash.update(bArr, i2, i3);
            return createHash.calculateHash();
        }
        s2 = 0;
        if (s2 != 1) {
            if (s2 == 8) {
            }
        }
        if (s3 != 0) {
        }
        return EMPTY_BYTES;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void h0(TlsClientContext tlsClientContext, TlsClient tlsClient) {
        SecurityParameters securityParametersHandshake = tlsClientContext.getSecurityParametersHandshake();
        ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
        if (!ProtocolVersion.c(negotiatedVersion)) {
            throw new TlsFatalAlert((short) 80);
        }
        e0(securityParametersHandshake);
        tlsClient.notifyServerVersion(negotiatedVersion);
    }

    public static boolean hasExpectedEmptyExtensionData(Hashtable hashtable, Integer num, short s2) {
        byte[] extensionData = getExtensionData(hashtable, num);
        if (extensionData == null) {
            return false;
        }
        if (extensionData.length == 0) {
            return true;
        }
        throw new TlsFatalAlert(s2);
    }

    public static boolean hasSigningCapability(short s2) {
        return s2 == 1 || s2 == 2 || s2 == 64;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsSecret i(TlsContext tlsContext, TlsSecret tlsSecret) {
        byte[] p2;
        String str;
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        if (securityParametersHandshake.isExtendedMasterSecret()) {
            p2 = securityParametersHandshake.getSessionHash();
            str = ExporterLabel.extended_master_secret;
        } else {
            p2 = p(securityParametersHandshake.getClientRandom(), securityParametersHandshake.getServerRandom());
            str = "master secret";
        }
        return PRF(securityParametersHandshake, tlsSecret, str, p2, 48);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void i0(TlsServerContext tlsServerContext) {
        SecurityParameters securityParametersHandshake = tlsServerContext.getSecurityParametersHandshake();
        if (!ProtocolVersion.d(securityParametersHandshake.getNegotiatedVersion())) {
            throw new TlsFatalAlert((short) 80);
        }
        e0(securityParametersHandshake);
    }

    public static TlsSession importSession(byte[] bArr, SessionParameters sessionParameters) {
        return new TlsSessionImpl(bArr, sessionParameters);
    }

    public static boolean isAEADCipherSuite(int i2) {
        return 2 == getCipherType(i2);
    }

    public static boolean isBlockCipherSuite(int i2) {
        return 1 == getCipherType(i2);
    }

    public static boolean isNullOrContainsNull(Object[] objArr) {
        if (objArr == null) {
            return true;
        }
        for (Object obj : objArr) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() < 1;
    }

    public static boolean isNullOrEmpty(Vector vector) {
        return vector == null || vector.isEmpty();
    }

    public static boolean isNullOrEmpty(byte[] bArr) {
        return bArr == null || bArr.length < 1;
    }

    public static boolean isNullOrEmpty(int[] iArr) {
        return iArr == null || iArr.length < 1;
    }

    public static boolean isNullOrEmpty(Object[] objArr) {
        return objArr == null || objArr.length < 1;
    }

    public static boolean isNullOrEmpty(short[] sArr) {
        return sArr == null || sArr.length < 1;
    }

    public static boolean isSSL(TlsContext tlsContext) {
        return tlsContext.getServerVersion().isSSL();
    }

    private static boolean isSafeRenegotiationServerCertificate(TlsClientContext tlsClientContext, Certificate certificate) {
        Certificate peerCertificate;
        SecurityParameters securityParametersConnection = tlsClientContext.getSecurityParametersConnection();
        if (securityParametersConnection == null || (peerCertificate = securityParametersConnection.getPeerCertificate()) == null) {
            return false;
        }
        return areCertificatesEqual(peerCertificate, certificate);
    }

    public static boolean isSignatureAlgorithmsExtensionAllowed(ProtocolVersion protocolVersion) {
        return protocolVersion != null && ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isStreamCipherSuite(int i2) {
        return getCipherType(i2) == 0;
    }

    public static boolean isSupportedCipherSuite(TlsCrypto tlsCrypto, int i2) {
        return isSupportedKeyExchange(tlsCrypto, getKeyExchangeAlgorithm(i2)) && tlsCrypto.hasEncryptionAlgorithm(getEncryptionAlgorithm(i2)) && tlsCrypto.hasMacAlgorithm(getMACAlgorithm(i2));
    }

    public static boolean isSupportedKeyExchange(TlsCrypto tlsCrypto, int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 3) {
                    return tlsCrypto.hasDHAgreement() && tlsCrypto.hasSignatureAlgorithm((short) 2);
                } else if (i2 == 5) {
                    return tlsCrypto.hasDHAgreement() && S(tlsCrypto);
                } else {
                    if (i2 != 7 && i2 != 9 && i2 != 11) {
                        switch (i2) {
                            case 13:
                                break;
                            case 14:
                                break;
                            case 15:
                                break;
                            case 16:
                            case 18:
                            case 20:
                            case 24:
                                return tlsCrypto.hasECDHAgreement();
                            case 17:
                                return tlsCrypto.hasECDHAgreement() && (tlsCrypto.hasSignatureAlgorithm((short) 3) || tlsCrypto.hasSignatureAlgorithm((short) 7) || tlsCrypto.hasSignatureAlgorithm((short) 8));
                            case 19:
                                return tlsCrypto.hasECDHAgreement() && S(tlsCrypto);
                            case 21:
                                return tlsCrypto.hasSRPAuthentication();
                            case 22:
                                return tlsCrypto.hasSRPAuthentication() && tlsCrypto.hasSignatureAlgorithm((short) 2);
                            case 23:
                                return tlsCrypto.hasSRPAuthentication() && S(tlsCrypto);
                            default:
                                return false;
                        }
                    }
                    return tlsCrypto.hasDHAgreement();
                }
            }
            return tlsCrypto.hasRSAEncryption();
        }
        return true;
    }

    public static boolean isTLSv10(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv10.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isTLSv10(TlsContext tlsContext) {
        return isTLSv10(tlsContext.getServerVersion());
    }

    public static boolean isTLSv11(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv11.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isTLSv11(TlsContext tlsContext) {
        return isTLSv11(tlsContext.getServerVersion());
    }

    public static boolean isTLSv12(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isTLSv12(TlsContext tlsContext) {
        return isTLSv12(tlsContext.getServerVersion());
    }

    public static boolean isTLSv13(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv13.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isTLSv13(TlsContext tlsContext) {
        return isTLSv13(tlsContext.getServerVersion());
    }

    public static boolean isValidCipherSuiteForSignatureAlgorithms(int i2, Vector vector) {
        int keyExchangeAlgorithm = getKeyExchangeAlgorithm(i2);
        if (keyExchangeAlgorithm == 0 || keyExchangeAlgorithm == 3 || keyExchangeAlgorithm == 5 || keyExchangeAlgorithm == 17 || keyExchangeAlgorithm == 19 || keyExchangeAlgorithm == 22 || keyExchangeAlgorithm == 23) {
            int size = vector.size();
            for (int i3 = 0; i3 < size; i3++) {
                Short sh = (Short) vector.elementAt(i3);
                if (sh != null && c0(sh.shortValue(), keyExchangeAlgorithm)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static boolean isValidCipherSuiteForVersion(int i2, ProtocolVersion protocolVersion) {
        return isValidVersionForCipherSuite(i2, protocolVersion);
    }

    public static boolean isValidSignatureSchemeForServerKeyExchange(int i2, int i3) {
        return c0(SignatureScheme.getSignatureAlgorithm(i2), i3);
    }

    public static boolean isValidUint16(int i2) {
        return (65535 & i2) == i2;
    }

    public static boolean isValidUint16(long j2) {
        return (WebSocketProtocol.PAYLOAD_SHORT_MAX & j2) == j2;
    }

    public static boolean isValidUint24(int i2) {
        return (16777215 & i2) == i2;
    }

    public static boolean isValidUint24(long j2) {
        return (16777215 & j2) == j2;
    }

    public static boolean isValidUint32(long j2) {
        return (BodyPartID.bodyIdMax & j2) == j2;
    }

    public static boolean isValidUint48(long j2) {
        return (281474976710655L & j2) == j2;
    }

    public static boolean isValidUint64(long j2) {
        return true;
    }

    public static boolean isValidUint8(int i2) {
        return (i2 & 255) == i2;
    }

    public static boolean isValidUint8(long j2) {
        return (255 & j2) == j2;
    }

    public static boolean isValidUint8(short s2) {
        return (s2 & 255) == s2;
    }

    public static boolean isValidVersionForCipherSuite(int i2, ProtocolVersion protocolVersion) {
        ProtocolVersion equivalentTLSVersion = protocolVersion.getEquivalentTLSVersion();
        ProtocolVersion minimumVersion = getMinimumVersion(i2);
        if (minimumVersion == equivalentTLSVersion) {
            return true;
        }
        if (minimumVersion.isEarlierVersionOf(equivalentTLSVersion)) {
            ProtocolVersion protocolVersion2 = ProtocolVersion.TLSv13;
            return protocolVersion2.isEqualOrEarlierVersionOf(minimumVersion) || protocolVersion2.isLaterVersionOf(equivalentTLSVersion);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] j(TlsCrypto tlsCrypto, boolean z, int i2, TlsSecret tlsSecret, byte[] bArr) {
        int hashOutputSize = TlsCryptoUtils.getHashOutputSize(i2);
        TlsSecret s2 = s(i2, hashOutputSize, tlsSecret, z ? "ext binder" : "res binder", tlsCrypto.createHash(i2).calculateHash());
        try {
            return calculateFinishedHMAC(i2, hashOutputSize, s2, bArr);
        } finally {
            s2.destroy();
        }
    }

    static CertificateRequest j0(CertificateRequest certificateRequest, short[] sArr) {
        if (r(sArr, certificateRequest.getCertificateTypes())) {
            return certificateRequest;
        }
        short[] s0 = s0(certificateRequest.getCertificateTypes(), sArr);
        if (s0.length < 1) {
            return null;
        }
        return new CertificateRequest(s0, certificateRequest.getSupportedSignatureAlgorithms(), certificateRequest.getCertificateAuthorities());
    }

    static byte[] k(TlsContext tlsContext, SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr, DigestInputBuffer digestInputBuffer) {
        TlsCrypto crypto = tlsContext.getCrypto();
        TlsHash combinedHash = signatureAndHashAlgorithm == null ? new CombinedHash(crypto) : createHash(crypto, signatureAndHashAlgorithm.getHash());
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        byte[] concatenate = Arrays.concatenate(securityParametersHandshake.getClientRandom(), securityParametersHandshake.getServerRandom());
        combinedHash.update(concatenate, 0, concatenate.length);
        if (bArr != null) {
            combinedHash.update(bArr, 0, bArr.length);
        }
        digestInputBuffer.b(combinedHash);
        return combinedHash.calculateHash();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void k0(TlsServerContext tlsServerContext, Certificate certificate, TlsKeyExchange tlsKeyExchange, TlsServer tlsServer) {
        SecurityParameters securityParametersHandshake = tlsServerContext.getSecurityParametersHandshake();
        if (securityParametersHandshake.getPeerCertificate() != null) {
            throw new TlsFatalAlert((short) 10);
        }
        if (!isTLSv13(securityParametersHandshake.getNegotiatedVersion())) {
            if (certificate.isEmpty()) {
                tlsKeyExchange.skipClientCredentials();
            } else {
                tlsKeyExchange.processClientCertificate(certificate);
            }
        }
        securityParametersHandshake.R = certificate;
        tlsServer.notifyClientCertificate(certificate);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] l(TlsContext tlsContext, TlsHandshakeHash tlsHandshakeHash, boolean z) {
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        ProtocolVersion negotiatedVersion = securityParametersHandshake.getNegotiatedVersion();
        if (isTLSv13(negotiatedVersion)) {
            return calculateFinishedHMAC(securityParametersHandshake, z ? securityParametersHandshake.getBaseKeyServer() : securityParametersHandshake.getBaseKeyClient(), H(tlsHandshakeHash));
        } else if (negotiatedVersion.isSSL()) {
            return SSL3Utils.a(tlsHandshakeHash, z);
        } else {
            return PRF(securityParametersHandshake, securityParametersHandshake.getMasterSecret(), z ? ExporterLabel.server_finished : ExporterLabel.client_finished, H(tlsHandshakeHash), securityParametersHandshake.getVerifyDataLength()).extract();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void l0(TlsClientContext tlsClientContext, CertificateStatus certificateStatus, TlsKeyExchange tlsKeyExchange, TlsAuthentication tlsAuthentication, Hashtable hashtable, Hashtable hashtable2) {
        SecurityParameters securityParametersHandshake = tlsClientContext.getSecurityParametersHandshake();
        boolean isTLSv13 = isTLSv13(securityParametersHandshake.getNegotiatedVersion());
        if (tlsAuthentication != null) {
            Certificate peerCertificate = securityParametersHandshake.getPeerCertificate();
            o(peerCertificate, hashtable, hashtable2);
            if (!isTLSv13) {
                tlsKeyExchange.processServerCertificate(peerCertificate);
            }
            tlsAuthentication.notifyServerCertificate(new TlsServerCertificateImpl(peerCertificate, certificateStatus));
        } else if (isTLSv13) {
            throw new TlsFatalAlert((short) 80);
        } else {
            if (securityParametersHandshake.isRenegotiating()) {
                throw new TlsFatalAlert((short) 40);
            }
            tlsKeyExchange.skipServerCredentials();
            securityParametersHandshake.z = EMPTY_BYTES;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void m(ProtocolVersion protocolVersion, byte[] bArr) {
        ProtocolVersion equivalentTLSVersion = protocolVersion.getEquivalentTLSVersion();
        if (equivalentTLSVersion.isEqualOrEarlierVersionOf(ProtocolVersion.TLSv11)) {
            checkDowngradeMarker(bArr, DOWNGRADE_TLS11);
        }
        if (equivalentTLSVersion.isEqualOrEarlierVersionOf(ProtocolVersion.TLSv12)) {
            checkDowngradeMarker(bArr, DOWNGRADE_TLS12);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] m0(TlsContext tlsContext, InputStream inputStream) {
        return isSSL(tlsContext) ? SSL3Utils.c(inputStream) : readOpaque16(inputStream);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:5:0x000a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void n(Hashtable hashtable, int i2, short s2) {
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            Integer num = (Integer) keys.nextElement();
            if (num == null || !Y(i2, num.intValue())) {
                throw new TlsFatalAlert(s2, "Invalid extension: " + ExtensionType.getText(num.intValue()));
            }
            while (keys.hasMoreElements()) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsAuthentication n0(TlsClientContext tlsClientContext, TlsClient tlsClient, ByteArrayInputStream byteArrayInputStream) {
        SecurityParameters securityParametersHandshake = tlsClientContext.getSecurityParametersHandshake();
        if (securityParametersHandshake.getPeerCertificate() == null) {
            Certificate parse = Certificate.parse(new Certificate.ParseOptions().setMaxChainLength(tlsClient.getMaxCertificateChainLength()), tlsClientContext, byteArrayInputStream, null);
            TlsProtocol.b(byteArrayInputStream);
            if (parse.getCertificateRequestContext().length <= 0) {
                if (parse.isEmpty()) {
                    throw new TlsFatalAlert((short) 50);
                }
                securityParametersHandshake.R = parse;
                securityParametersHandshake.z = null;
                TlsAuthentication authentication = tlsClient.getAuthentication();
                if (authentication != null) {
                    return authentication;
                }
                throw new TlsFatalAlert((short) 80);
            }
            throw new TlsFatalAlert((short) 47);
        }
        throw new TlsFatalAlert((short) 10);
    }

    static void o(Certificate certificate, Hashtable hashtable, Hashtable hashtable2) {
        byte[] extension = certificate.getCertificateAt(0).getExtension(TlsObjectIdentifiers.id_pe_tlsfeature);
        if (extension != null) {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) readASN1Object(extension);
            for (int i2 = 0; i2 < aSN1Sequence.size(); i2++) {
                if (!(aSN1Sequence.getObjectAt(i2) instanceof ASN1Integer)) {
                    throw new TlsFatalAlert((short) 42);
                }
            }
            requireDEREncoding(aSN1Sequence, extension);
            for (int i3 = 0; i3 < aSN1Sequence.size(); i3++) {
                BigInteger positiveValue = ((ASN1Integer) aSN1Sequence.getObjectAt(i3)).getPositiveValue();
                if (positiveValue.bitLength() <= 16) {
                    Integer valueOf = Integers.valueOf(positiveValue.intValue());
                    if (hashtable.containsKey(valueOf) && !hashtable2.containsKey(valueOf)) {
                        throw new TlsFatalAlert((short) 46);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsAuthentication o0(TlsClientContext tlsClientContext, TlsClient tlsClient, ByteArrayInputStream byteArrayInputStream) {
        SecurityParameters securityParametersHandshake = tlsClientContext.getSecurityParametersHandshake();
        if (securityParametersHandshake.getPeerCertificate() == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Certificate parse = Certificate.parse(new Certificate.ParseOptions().setMaxChainLength(tlsClient.getMaxCertificateChainLength()), tlsClientContext, byteArrayInputStream, byteArrayOutputStream);
            TlsProtocol.b(byteArrayInputStream);
            if (parse.isEmpty()) {
                throw new TlsFatalAlert((short) 50);
            }
            if (!securityParametersHandshake.isRenegotiating() || isSafeRenegotiationServerCertificate(tlsClientContext, parse)) {
                securityParametersHandshake.R = parse;
                securityParametersHandshake.z = byteArrayOutputStream.toByteArray();
                TlsAuthentication authentication = tlsClient.getAuthentication();
                if (authentication != null) {
                    return authentication;
                }
                throw new TlsFatalAlert((short) 80);
            }
            throw new TlsFatalAlert((short) 46, "Server certificate changed unsafely in renegotiation handshake");
        }
        throw new TlsFatalAlert((short) 10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] p(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentialedAgreement p0(TlsCredentials tlsCredentials) {
        if (tlsCredentials instanceof TlsCredentialedAgreement) {
            return (TlsCredentialedAgreement) tlsCredentials;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public static Vector parseSupportedSignatureAlgorithms(InputStream inputStream) {
        int readUint16 = readUint16(inputStream);
        if (readUint16 < 2 || (readUint16 & 1) != 0) {
            throw new TlsFatalAlert((short) 50);
        }
        int i2 = readUint16 / 2;
        Vector vector = new Vector(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            SignatureAndHashAlgorithm parse = SignatureAndHashAlgorithm.parse(inputStream);
            if (parse.getSignature() != 0) {
                vector.addElement(parse);
            }
        }
        return vector;
    }

    static boolean q(int[] iArr, int i2, int i3, int i4) {
        for (int i5 = 0; i5 < i3; i5++) {
            if (i4 == iArr[i2 + i5]) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentialedDecryptor q0(TlsCredentials tlsCredentials) {
        if (tlsCredentials instanceof TlsCredentialedDecryptor) {
            return (TlsCredentialedDecryptor) tlsCredentials;
        }
        throw new TlsFatalAlert((short) 80);
    }

    static boolean r(short[] sArr, short[] sArr2) {
        for (short s2 : sArr2) {
            if (!Arrays.contains(sArr, s2)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentialedSigner r0(TlsCredentials tlsCredentials) {
        if (tlsCredentials instanceof TlsCredentialedSigner) {
            return (TlsCredentialedSigner) tlsCredentials;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public static ASN1Primitive readASN1Object(byte[] bArr) {
        ASN1InputStream aSN1InputStream = new ASN1InputStream(bArr);
        ASN1Primitive readObject = aSN1InputStream.readObject();
        if (readObject != null) {
            if (aSN1InputStream.readObject() == null) {
                return readObject;
            }
            throw new TlsFatalAlert((short) 50);
        }
        throw new TlsFatalAlert((short) 50);
    }

    public static byte[] readAllOrNothing(int i2, InputStream inputStream) {
        if (i2 < 1) {
            return EMPTY_BYTES;
        }
        byte[] bArr = new byte[i2];
        int readFully = Streams.readFully(inputStream, bArr);
        if (readFully == 0) {
            return null;
        }
        if (readFully == i2) {
            return bArr;
        }
        throw new EOFException();
    }

    public static ASN1Primitive readDERObject(byte[] bArr) {
        ASN1Primitive readASN1Object = readASN1Object(bArr);
        requireDEREncoding(readASN1Object, bArr);
        return readASN1Object;
    }

    public static void readFully(byte[] bArr, InputStream inputStream) {
        int length = bArr.length;
        if (length > 0 && length != Streams.readFully(inputStream, bArr)) {
            throw new EOFException();
        }
    }

    public static byte[] readFully(int i2, InputStream inputStream) {
        if (i2 < 1) {
            return EMPTY_BYTES;
        }
        byte[] bArr = new byte[i2];
        if (i2 == Streams.readFully(inputStream, bArr)) {
            return bArr;
        }
        throw new EOFException();
    }

    public static int readInt32(byte[] bArr, int i2) {
        int i3 = i2 + 1;
        int i4 = i3 + 1;
        return (bArr[i4 + 1] & 255) | (bArr[i2] << Ascii.CAN) | ((bArr[i3] & 255) << 16) | ((bArr[i4] & 255) << 8);
    }

    public static byte[] readOpaque16(InputStream inputStream) {
        return readFully(readUint16(inputStream), inputStream);
    }

    public static byte[] readOpaque16(InputStream inputStream, int i2) {
        int readUint16 = readUint16(inputStream);
        if (readUint16 >= i2) {
            return readFully(readUint16, inputStream);
        }
        throw new TlsFatalAlert((short) 50);
    }

    public static byte[] readOpaque24(InputStream inputStream) {
        return readFully(readUint24(inputStream), inputStream);
    }

    public static byte[] readOpaque24(InputStream inputStream, int i2) {
        int readUint24 = readUint24(inputStream);
        if (readUint24 >= i2) {
            return readFully(readUint24, inputStream);
        }
        throw new TlsFatalAlert((short) 50);
    }

    public static byte[] readOpaque8(InputStream inputStream) {
        return readFully(readUint8(inputStream), inputStream);
    }

    public static byte[] readOpaque8(InputStream inputStream, int i2) {
        short readUint8 = readUint8(inputStream);
        if (readUint8 >= i2) {
            return readFully(readUint8, inputStream);
        }
        throw new TlsFatalAlert((short) 50);
    }

    public static byte[] readOpaque8(InputStream inputStream, int i2, int i3) {
        short readUint8 = readUint8(inputStream);
        if (readUint8 < i2 || i3 < readUint8) {
            throw new TlsFatalAlert((short) 50);
        }
        return readFully(readUint8, inputStream);
    }

    public static int readUint16(InputStream inputStream) {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if (read2 >= 0) {
            return read2 | (read << 8);
        }
        throw new EOFException();
    }

    public static int readUint16(byte[] bArr, int i2) {
        return (bArr[i2 + 1] & 255) | ((bArr[i2] & 255) << 8);
    }

    public static int[] readUint16Array(int i2, InputStream inputStream) {
        int[] iArr = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i3] = readUint16(inputStream);
        }
        return iArr;
    }

    public static int readUint24(InputStream inputStream) {
        int read = inputStream.read();
        int read2 = inputStream.read();
        int read3 = inputStream.read();
        if (read3 >= 0) {
            return read3 | (read << 16) | (read2 << 8);
        }
        throw new EOFException();
    }

    public static int readUint24(byte[] bArr, int i2) {
        int i3 = i2 + 1;
        return (bArr[i3 + 1] & 255) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
    }

    public static long readUint32(InputStream inputStream) {
        int read = inputStream.read();
        int read2 = inputStream.read();
        int read3 = inputStream.read();
        int read4 = inputStream.read();
        if (read4 >= 0) {
            return (read4 | (read << 24) | (read2 << 16) | (read3 << 8)) & BodyPartID.bodyIdMax;
        }
        throw new EOFException();
    }

    public static long readUint32(byte[] bArr, int i2) {
        int i3 = i2 + 1;
        int i4 = i3 + 1;
        return ((bArr[i4 + 1] & 255) | ((bArr[i2] & 255) << 24) | ((bArr[i3] & 255) << 16) | ((bArr[i4] & 255) << 8)) & BodyPartID.bodyIdMax;
    }

    public static long readUint48(InputStream inputStream) {
        return ((readUint24(inputStream) & BodyPartID.bodyIdMax) << 24) | (BodyPartID.bodyIdMax & readUint24(inputStream));
    }

    public static long readUint48(byte[] bArr, int i2) {
        int readUint24 = readUint24(bArr, i2);
        return (readUint24(bArr, i2 + 3) & BodyPartID.bodyIdMax) | ((readUint24 & BodyPartID.bodyIdMax) << 24);
    }

    public static short readUint8(InputStream inputStream) {
        int read = inputStream.read();
        if (read >= 0) {
            return (short) read;
        }
        throw new EOFException();
    }

    public static short readUint8(byte[] bArr, int i2) {
        return (short) (bArr[i2] & 255);
    }

    public static short[] readUint8Array(int i2, InputStream inputStream) {
        short[] sArr = new short[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            sArr[i3] = readUint8(inputStream);
        }
        return sArr;
    }

    public static short[] readUint8ArrayWithUint8Length(InputStream inputStream, int i2) {
        short readUint8 = readUint8(inputStream);
        if (readUint8 >= i2) {
            return readUint8Array(readUint8, inputStream);
        }
        throw new TlsFatalAlert((short) 50);
    }

    public static ProtocolVersion readVersion(InputStream inputStream) {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if (read2 >= 0) {
            return ProtocolVersion.get(read, read2);
        }
        throw new EOFException();
    }

    public static ProtocolVersion readVersion(byte[] bArr, int i2) {
        return ProtocolVersion.get(bArr[i2] & 255, bArr[i2 + 1] & 255);
    }

    public static void requireDEREncoding(ASN1Object aSN1Object, byte[] bArr) {
        if (!Arrays.areEqual(aSN1Object.getEncoded(ASN1Encoding.DER), bArr)) {
            throw new TlsFatalAlert((short) 50);
        }
    }

    static TlsSecret s(int i2, int i3, TlsSecret tlsSecret, String str, byte[] bArr) {
        if (bArr.length == i3) {
            return TlsCryptoUtils.hkdfExpandLabel(tlsSecret, i2, str, bArr, i3);
        }
        throw new TlsFatalAlert((short) 80);
    }

    static short[] s0(short[] sArr, short[] sArr2) {
        short[] sArr3 = new short[Math.min(sArr.length, sArr2.length)];
        int i2 = 0;
        for (int i3 = 0; i3 < sArr2.length; i3++) {
            if (Arrays.contains(sArr, sArr2[i3])) {
                sArr3[i2] = sArr2[i3];
                i2++;
            }
        }
        return C0(sArr3, i2);
    }

    static TlsSecret t(SecurityParameters securityParameters, TlsSecret tlsSecret, String str, byte[] bArr) {
        return s(securityParameters.getPRFCryptoHashAlgorithm(), securityParameters.getPRFHashLength(), tlsSecret, str, bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void t0(TlsContext tlsContext, TlsHandshakeHash tlsHandshakeHash, boolean z) {
        if (z || !tlsContext.getCrypto().hasAllRawSignatureAlgorithms()) {
            tlsHandshakeHash.forceBuffering();
        }
        tlsHandshakeHash.sealHashAlgorithms();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentialedSigner u(TlsAuthentication tlsAuthentication, CertificateRequest certificateRequest) {
        return F0(tlsAuthentication.getClientCredentials(certificateRequest));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyShareEntry u0(Vector vector, int i2) {
        KeyShareEntry keyShareEntry;
        if (vector == null || 1 != vector.size() || (keyShareEntry = (KeyShareEntry) vector.elementAt(0)) == null || keyShareEntry.getNamedGroup() != i2) {
            return null;
        }
        return keyShareEntry;
    }

    private static TlsSecret update13TrafficSecret(SecurityParameters securityParameters, TlsSecret tlsSecret) {
        return TlsCryptoUtils.hkdfExpandLabel(tlsSecret, securityParameters.getPRFCryptoHashAlgorithm(), "traffic upd", EMPTY_BYTES, securityParameters.getPRFHashLength());
    }

    private static void update13TrafficSecret(TlsContext tlsContext, boolean z) {
        TlsSecret trafficSecretClient;
        SecurityParameters securityParametersConnection = tlsContext.getSecurityParametersConnection();
        if (z) {
            trafficSecretClient = securityParametersConnection.getTrafficSecretServer();
            securityParametersConnection.f14816s = update13TrafficSecret(securityParametersConnection, trafficSecretClient);
        } else {
            trafficSecretClient = securityParametersConnection.getTrafficSecretClient();
            securityParametersConnection.f14815r = update13TrafficSecret(securityParametersConnection, trafficSecretClient);
        }
        if (trafficSecretClient != null) {
            trafficSecretClient.destroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void v(TlsContext tlsContext, byte[] bArr, RecordStream recordStream) {
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        TlsSecret masterSecret = securityParametersHandshake.getMasterSecret();
        establish13TrafficSecrets(tlsContext, bArr, masterSecret, "c ap traffic", "s ap traffic", recordStream);
        securityParametersHandshake.f14812o = t(securityParametersHandshake, masterSecret, "exp master", bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyShareEntry v0(TlsCrypto tlsCrypto, ProtocolVersion protocolVersion, Vector vector, int[] iArr, int[] iArr2) {
        if (vector == null || isNullOrEmpty(iArr) || isNullOrEmpty(iArr2)) {
            return null;
        }
        for (int i2 = 0; i2 < vector.size(); i2++) {
            KeyShareEntry keyShareEntry = (KeyShareEntry) vector.elementAt(i2);
            int namedGroup = keyShareEntry.getNamedGroup();
            if (NamedGroup.canBeNegotiated(namedGroup, protocolVersion) && Arrays.contains(iArr2, namedGroup) && Arrays.contains(iArr, namedGroup) && tlsCrypto.hasNamedGroup(namedGroup) && ((!NamedGroup.refersToASpecificCurve(namedGroup) || tlsCrypto.hasECDHAgreement()) && (!NamedGroup.refersToASpecificFiniteField(namedGroup) || tlsCrypto.hasDHAgreement()))) {
                return keyShareEntry;
            }
        }
        return null;
    }

    public static Vector vectorOfOne(Object obj) {
        Vector vector = new Vector(1);
        vector.addElement(obj);
        return vector;
    }

    private static boolean verify13CertificateVerify(TlsCrypto tlsCrypto, DigitallySigned digitallySigned, TlsVerifier tlsVerifier, String str, TlsHandshakeHash tlsHandshakeHash) {
        TlsStreamVerifier streamVerifier = tlsVerifier.getStreamVerifier(digitallySigned);
        byte[] certificateVerifyHeader = getCertificateVerifyHeader(str);
        byte[] H = H(tlsHandshakeHash);
        if (streamVerifier != null) {
            OutputStream outputStream = streamVerifier.getOutputStream();
            outputStream.write(certificateVerifyHeader, 0, certificateVerifyHeader.length);
            outputStream.write(H, 0, H.length);
            return streamVerifier.isVerified();
        }
        TlsHash createHash = tlsCrypto.createHash(SignatureScheme.getCryptoHashAlgorithm(SignatureScheme.from(digitallySigned.getAlgorithm())));
        createHash.update(certificateVerifyHeader, 0, certificateVerifyHeader.length);
        createHash.update(H, 0, H.length);
        return tlsVerifier.verifyRawSignature(digitallySigned, createHash.calculateHash());
    }

    public static void verifySupportedSignatureAlgorithm(Vector vector, SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        if (vector == null || vector.size() < 1 || vector.size() >= 32768) {
            throw new IllegalArgumentException("'supportedSignatureAlgorithms' must have length from 1 to (2^15 - 1)");
        }
        if (signatureAndHashAlgorithm == null) {
            throw new IllegalArgumentException("'signatureAlgorithm' cannot be null");
        }
        if (signatureAndHashAlgorithm.getSignature() == 0 || !containsSignatureAlgorithm(vector, signatureAndHashAlgorithm)) {
            throw new TlsFatalAlert((short) 47);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void w(TlsContext tlsContext, byte[] bArr, RecordStream recordStream) {
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        establish13TrafficSecrets(tlsContext, bArr, securityParametersHandshake.getHandshakeSecret(), "c hs traffic", "s hs traffic", recordStream);
        securityParametersHandshake.f14808k = securityParametersHandshake.getTrafficSecretClient();
        securityParametersHandshake.f14809l = securityParametersHandshake.getTrafficSecretServer();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int w0(TlsCrypto tlsCrypto, ProtocolVersion protocolVersion, int[] iArr, int[] iArr2) {
        if (isNullOrEmpty(iArr) || isNullOrEmpty(iArr2)) {
            return -1;
        }
        for (int i2 : iArr) {
            if (NamedGroup.canBeNegotiated(i2, protocolVersion) && Arrays.contains(iArr2, i2) && tlsCrypto.hasNamedGroup(i2) && ((!NamedGroup.refersToASpecificCurve(i2) || tlsCrypto.hasECDHAgreement()) && (!NamedGroup.refersToASpecificFiniteField(i2) || tlsCrypto.hasDHAgreement()))) {
                return i2;
            }
        }
        return -1;
    }

    public static void writeGMTUnixTime(byte[] bArr, int i2) {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        bArr[i2] = (byte) (currentTimeMillis >>> 24);
        bArr[i2 + 1] = (byte) (currentTimeMillis >>> 16);
        bArr[i2 + 2] = (byte) (currentTimeMillis >>> 8);
        bArr[i2 + 3] = (byte) currentTimeMillis;
    }

    public static void writeOpaque16(byte[] bArr, OutputStream outputStream) {
        checkUint16(bArr.length);
        writeUint16(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeOpaque16(byte[] bArr, byte[] bArr2, int i2) {
        checkUint16(bArr.length);
        writeUint16(bArr.length, bArr2, i2);
        System.arraycopy(bArr, 0, bArr2, i2 + 2, bArr.length);
    }

    public static void writeOpaque24(byte[] bArr, OutputStream outputStream) {
        checkUint24(bArr.length);
        writeUint24(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeOpaque24(byte[] bArr, byte[] bArr2, int i2) {
        checkUint24(bArr.length);
        writeUint24(bArr.length, bArr2, i2);
        System.arraycopy(bArr, 0, bArr2, i2 + 3, bArr.length);
    }

    public static void writeOpaque8(byte[] bArr, OutputStream outputStream) {
        checkUint8(bArr.length);
        writeUint8(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeOpaque8(byte[] bArr, byte[] bArr2, int i2) {
        checkUint8(bArr.length);
        writeUint8(bArr.length, bArr2, i2);
        System.arraycopy(bArr, 0, bArr2, i2 + 1, bArr.length);
    }

    public static void writeUint16(int i2, OutputStream outputStream) {
        outputStream.write(i2 >>> 8);
        outputStream.write(i2);
    }

    public static void writeUint16(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) (i2 >>> 8);
        bArr[i3 + 1] = (byte) i2;
    }

    public static void writeUint16Array(int[] iArr, OutputStream outputStream) {
        for (int i2 : iArr) {
            writeUint16(i2, outputStream);
        }
    }

    public static void writeUint16Array(int[] iArr, byte[] bArr, int i2) {
        for (int i3 : iArr) {
            writeUint16(i3, bArr, i2);
            i2 += 2;
        }
    }

    public static void writeUint16ArrayWithUint16Length(int[] iArr, OutputStream outputStream) {
        int length = iArr.length * 2;
        checkUint16(length);
        writeUint16(length, outputStream);
        writeUint16Array(iArr, outputStream);
    }

    public static void writeUint16ArrayWithUint16Length(int[] iArr, byte[] bArr, int i2) {
        int length = iArr.length * 2;
        checkUint16(length);
        writeUint16(length, bArr, i2);
        writeUint16Array(iArr, bArr, i2 + 2);
    }

    public static void writeUint24(int i2, OutputStream outputStream) {
        outputStream.write((byte) (i2 >>> 16));
        outputStream.write((byte) (i2 >>> 8));
        outputStream.write((byte) i2);
    }

    public static void writeUint24(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) (i2 >>> 16);
        bArr[i3 + 1] = (byte) (i2 >>> 8);
        bArr[i3 + 2] = (byte) i2;
    }

    public static void writeUint32(long j2, OutputStream outputStream) {
        outputStream.write((byte) (j2 >>> 24));
        outputStream.write((byte) (j2 >>> 16));
        outputStream.write((byte) (j2 >>> 8));
        outputStream.write((byte) j2);
    }

    public static void writeUint32(long j2, byte[] bArr, int i2) {
        bArr[i2] = (byte) (j2 >>> 24);
        bArr[i2 + 1] = (byte) (j2 >>> 16);
        bArr[i2 + 2] = (byte) (j2 >>> 8);
        bArr[i2 + 3] = (byte) j2;
    }

    public static void writeUint48(long j2, OutputStream outputStream) {
        outputStream.write((byte) (j2 >>> 40));
        outputStream.write((byte) (j2 >>> 32));
        outputStream.write((byte) (j2 >>> 24));
        outputStream.write((byte) (j2 >>> 16));
        outputStream.write((byte) (j2 >>> 8));
        outputStream.write((byte) j2);
    }

    public static void writeUint48(long j2, byte[] bArr, int i2) {
        bArr[i2] = (byte) (j2 >>> 40);
        bArr[i2 + 1] = (byte) (j2 >>> 32);
        bArr[i2 + 2] = (byte) (j2 >>> 24);
        bArr[i2 + 3] = (byte) (j2 >>> 16);
        bArr[i2 + 4] = (byte) (j2 >>> 8);
        bArr[i2 + 5] = (byte) j2;
    }

    public static void writeUint64(long j2, OutputStream outputStream) {
        outputStream.write((byte) (j2 >>> 56));
        outputStream.write((byte) (j2 >>> 48));
        outputStream.write((byte) (j2 >>> 40));
        outputStream.write((byte) (j2 >>> 32));
        outputStream.write((byte) (j2 >>> 24));
        outputStream.write((byte) (j2 >>> 16));
        outputStream.write((byte) (j2 >>> 8));
        outputStream.write((byte) j2);
    }

    public static void writeUint64(long j2, byte[] bArr, int i2) {
        bArr[i2] = (byte) (j2 >>> 56);
        bArr[i2 + 1] = (byte) (j2 >>> 48);
        bArr[i2 + 2] = (byte) (j2 >>> 40);
        bArr[i2 + 3] = (byte) (j2 >>> 32);
        bArr[i2 + 4] = (byte) (j2 >>> 24);
        bArr[i2 + 5] = (byte) (j2 >>> 16);
        bArr[i2 + 6] = (byte) (j2 >>> 8);
        bArr[i2 + 7] = (byte) j2;
    }

    public static void writeUint8(int i2, OutputStream outputStream) {
        outputStream.write(i2);
    }

    public static void writeUint8(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) i2;
    }

    public static void writeUint8(short s2, OutputStream outputStream) {
        outputStream.write(s2);
    }

    public static void writeUint8(short s2, byte[] bArr, int i2) {
        bArr[i2] = (byte) s2;
    }

    public static void writeUint8Array(short[] sArr, OutputStream outputStream) {
        for (short s2 : sArr) {
            writeUint8(s2, outputStream);
        }
    }

    public static void writeUint8Array(short[] sArr, byte[] bArr, int i2) {
        for (short s2 : sArr) {
            writeUint8(s2, bArr, i2);
            i2++;
        }
    }

    public static void writeUint8ArrayWithUint8Length(short[] sArr, OutputStream outputStream) {
        checkUint8(sArr.length);
        writeUint8(sArr.length, outputStream);
        writeUint8Array(sArr, outputStream);
    }

    public static void writeUint8ArrayWithUint8Length(short[] sArr, byte[] bArr, int i2) {
        checkUint8(sArr.length);
        writeUint8(sArr.length, bArr, i2);
        writeUint8Array(sArr, bArr, i2 + 1);
    }

    public static void writeVersion(ProtocolVersion protocolVersion, OutputStream outputStream) {
        outputStream.write(protocolVersion.getMajorVersion());
        outputStream.write(protocolVersion.getMinorVersion());
    }

    public static void writeVersion(ProtocolVersion protocolVersion, byte[] bArr, int i2) {
        bArr[i2] = (byte) protocolVersion.getMajorVersion();
        bArr[i2 + 1] = (byte) protocolVersion.getMinorVersion();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void x(TlsContext tlsContext, TlsSecret tlsSecret, TlsSecret tlsSecret2) {
        TlsCrypto crypto = tlsContext.getCrypto();
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        int pRFCryptoHashAlgorithm = securityParametersHandshake.getPRFCryptoHashAlgorithm();
        TlsSecret hkdfInit = crypto.hkdfInit(pRFCryptoHashAlgorithm);
        byte[] calculateHash = crypto.createHash(pRFCryptoHashAlgorithm).calculateHash();
        if (tlsSecret == null) {
            tlsSecret = crypto.hkdfInit(pRFCryptoHashAlgorithm).hkdfExtract(pRFCryptoHashAlgorithm, hkdfInit);
        }
        if (tlsSecret2 == null) {
            tlsSecret2 = hkdfInit;
        }
        TlsSecret hkdfExtract = t(securityParametersHandshake, tlsSecret, "derived", calculateHash).hkdfExtract(pRFCryptoHashAlgorithm, tlsSecret2);
        if (tlsSecret2 != hkdfInit) {
            tlsSecret2.destroy();
        }
        TlsSecret hkdfExtract2 = t(securityParametersHandshake, hkdfExtract, "derived", calculateHash).hkdfExtract(pRFCryptoHashAlgorithm, hkdfInit);
        securityParametersHandshake.f14811n = tlsSecret;
        securityParametersHandshake.f14813p = hkdfExtract;
        securityParametersHandshake.f14814q = hkdfExtract2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static OfferedPsks.SelectedConfig x0(TlsServerContext tlsServerContext, TlsServer tlsServer, Hashtable hashtable, HandshakeMessageInput handshakeMessageInput, TlsHandshakeHash tlsHandshakeHash, boolean z) {
        TlsPSKExternal externalPSK;
        int indexOfIdentity;
        byte[] calculateHash;
        OfferedPsks preSharedKeyClientHello = TlsExtensionsUtils.getPreSharedKeyClientHello(hashtable);
        boolean z2 = true;
        if (preSharedKeyClientHello != null) {
            short[] pSKKeyExchangeModesExtension = TlsExtensionsUtils.getPSKKeyExchangeModesExtension(hashtable);
            if (isNullOrEmpty(pSKKeyExchangeModesExtension)) {
                throw new TlsFatalAlert(AlertDescription.missing_extension);
            }
            if (Arrays.contains(pSKKeyExchangeModesExtension, (short) 1) && (externalPSK = tlsServer.getExternalPSK(preSharedKeyClientHello.getIdentities())) != null && (indexOfIdentity = preSharedKeyClientHello.getIndexOfIdentity(new PskIdentity(externalPSK.getIdentity(), 0L))) >= 0) {
                byte[] bArr = (byte[]) preSharedKeyClientHello.getBinders().elementAt(indexOfIdentity);
                TlsCrypto crypto = tlsServerContext.getCrypto();
                TlsSecret L = L(crypto, externalPSK);
                int hashForPRF = TlsCryptoUtils.getHashForPRF(externalPSK.getPRFAlgorithm());
                int bindersSize = preSharedKeyClientHello.getBindersSize();
                handshakeMessageInput.a(tlsHandshakeHash, bindersSize);
                if (z) {
                    calculateHash = tlsHandshakeHash.getFinalHash(hashForPRF);
                } else {
                    TlsHash createHash = crypto.createHash(hashForPRF);
                    tlsHandshakeHash.copyBufferTo(new TlsHashOutputStream(createHash));
                    calculateHash = createHash.calculateHash();
                }
                handshakeMessageInput.b(tlsHandshakeHash, bindersSize);
                if (Arrays.constantTimeAreEqual(j(crypto, true, hashForPRF, L, calculateHash), bArr)) {
                    return new OfferedPsks.SelectedConfig(indexOfIdentity, externalPSK, pSKKeyExchangeModesExtension, L);
                }
                if (z2) {
                    handshakeMessageInput.updateHash(tlsHandshakeHash);
                    return null;
                }
                return null;
            }
        }
        z2 = false;
        if (z2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentialedSigner y(TlsServer tlsServer) {
        return F0(tlsServer.getCredentials());
    }

    static void y0(TlsContext tlsContext, byte[] bArr, DigestInputBuffer digestInputBuffer, OutputStream outputStream) {
        SecurityParameters securityParametersHandshake = tlsContext.getSecurityParametersHandshake();
        outputStream.write(Arrays.concatenate(securityParametersHandshake.getClientRandom(), securityParametersHandshake.getServerRandom()));
        if (bArr != null) {
            outputStream.write(bArr);
        }
        digestInputBuffer.a(outputStream);
        outputStream.close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsCredentials z(TlsAuthentication tlsAuthentication, CertificateRequest certificateRequest) {
        return H0(tlsAuthentication.getClientCredentials(certificateRequest));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TlsAuthentication z0(TlsClientContext tlsClientContext) {
        SecurityParameters securityParametersHandshake = tlsClientContext.getSecurityParametersHandshake();
        if (securityParametersHandshake.getPeerCertificate() == null) {
            securityParametersHandshake.R = null;
            securityParametersHandshake.z = null;
            return null;
        }
        throw new TlsFatalAlert((short) 80);
    }
}
