package org.bouncycastle.openssl;

import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.asn1.pkcs.RSAPublicKey;
import org.bouncycastle.asn1.sec.ECPrivateKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DSAParameter;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.io.pem.PemHeader;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemObjectParser;
import org.bouncycastle.util.io.pem.PemReader;
/* loaded from: classes4.dex */
public class PEMParser extends PemReader {
    public static final String TYPE_ATTRIBUTE_CERTIFICATE = "ATTRIBUTE CERTIFICATE";
    public static final String TYPE_CERTIFICATE = "CERTIFICATE";
    public static final String TYPE_CERTIFICATE_REQUEST = "CERTIFICATE REQUEST";
    public static final String TYPE_CMS = "CMS";
    public static final String TYPE_DSA_PRIVATE_KEY = "DSA PRIVATE KEY";
    public static final String TYPE_EC_PARAMETERS = "EC PARAMETERS";
    public static final String TYPE_EC_PRIVATE_KEY = "EC PRIVATE KEY";
    public static final String TYPE_ENCRYPTED_PRIVATE_KEY = "ENCRYPTED PRIVATE KEY";
    public static final String TYPE_NEW_CERTIFICATE_REQUEST = "NEW CERTIFICATE REQUEST";
    public static final String TYPE_PKCS7 = "PKCS7";
    public static final String TYPE_PRIVATE_KEY = "PRIVATE KEY";
    public static final String TYPE_PUBLIC_KEY = "PUBLIC KEY";
    public static final String TYPE_RSA_PRIVATE_KEY = "RSA PRIVATE KEY";
    public static final String TYPE_RSA_PUBLIC_KEY = "RSA PUBLIC KEY";
    public static final String TYPE_TRUSTED_CERTIFICATE = "TRUSTED CERTIFICATE";
    public static final String TYPE_X509_CERTIFICATE = "X509 CERTIFICATE";
    public static final String TYPE_X509_CRL = "X509 CRL";

    /* renamed from: a  reason: collision with root package name */
    protected final Map f14398a;

    /* loaded from: classes4.dex */
    private class DSAKeyPairParser implements PEMKeyPairParser {
        private DSAKeyPairParser(PEMParser pEMParser) {
        }

        @Override // org.bouncycastle.openssl.PEMKeyPairParser
        public PEMKeyPair parse(byte[] bArr) {
            try {
                ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(bArr);
                if (aSN1Sequence.size() == 6) {
                    ASN1Integer aSN1Integer = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1));
                    ASN1Integer aSN1Integer2 = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(2));
                    ASN1Integer aSN1Integer3 = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(3));
                    ASN1Integer aSN1Integer4 = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(4));
                    ASN1Integer aSN1Integer5 = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(5));
                    ASN1ObjectIdentifier aSN1ObjectIdentifier = X9ObjectIdentifiers.id_dsa;
                    return new PEMKeyPair(new SubjectPublicKeyInfo(new AlgorithmIdentifier(aSN1ObjectIdentifier, new DSAParameter(aSN1Integer.getValue(), aSN1Integer2.getValue(), aSN1Integer3.getValue())), aSN1Integer4), new PrivateKeyInfo(new AlgorithmIdentifier(aSN1ObjectIdentifier, new DSAParameter(aSN1Integer.getValue(), aSN1Integer2.getValue(), aSN1Integer3.getValue())), aSN1Integer5));
                }
                throw new PEMException("malformed sequence in DSA private key");
            } catch (IOException e2) {
                throw e2;
            } catch (Exception e3) {
                throw new PEMException("problem creating DSA private key: " + e3.toString(), e3);
            }
        }
    }

    /* loaded from: classes4.dex */
    private class ECCurveParamsParser implements PemObjectParser {
        private ECCurveParamsParser(PEMParser pEMParser) {
        }

        @Override // org.bouncycastle.util.io.pem.PemObjectParser
        public Object parseObject(PemObject pemObject) {
            try {
                ASN1Primitive fromByteArray = ASN1Primitive.fromByteArray(pemObject.getContent());
                if (fromByteArray instanceof ASN1ObjectIdentifier) {
                    return ASN1Primitive.fromByteArray(pemObject.getContent());
                }
                if (fromByteArray instanceof ASN1Sequence) {
                    return X9ECParameters.getInstance(fromByteArray);
                }
                return null;
            } catch (IOException e2) {
                throw e2;
            } catch (Exception e3) {
                throw new PEMException("exception extracting EC named curve: " + e3.toString());
            }
        }
    }

    /* loaded from: classes4.dex */
    private class ECDSAKeyPairParser implements PEMKeyPairParser {
        private ECDSAKeyPairParser(PEMParser pEMParser) {
        }

        @Override // org.bouncycastle.openssl.PEMKeyPairParser
        public PEMKeyPair parse(byte[] bArr) {
            try {
                ECPrivateKey eCPrivateKey = ECPrivateKey.getInstance(ASN1Sequence.getInstance(bArr));
                AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, eCPrivateKey.getParametersObject());
                PrivateKeyInfo privateKeyInfo = new PrivateKeyInfo(algorithmIdentifier, eCPrivateKey);
                return eCPrivateKey.getPublicKey() != null ? new PEMKeyPair(new SubjectPublicKeyInfo(algorithmIdentifier, eCPrivateKey.getPublicKey().getBytes()), privateKeyInfo) : new PEMKeyPair(null, privateKeyInfo);
            } catch (IOException e2) {
                throw e2;
            } catch (Exception e3) {
                throw new PEMException("problem creating EC private key: " + e3.toString(), e3);
            }
        }
    }

    /* loaded from: classes4.dex */
    private class EncryptedPrivateKeyParser implements PemObjectParser {
        public EncryptedPrivateKeyParser(PEMParser pEMParser) {
        }

        @Override // org.bouncycastle.util.io.pem.PemObjectParser
        public Object parseObject(PemObject pemObject) {
            try {
                return new PKCS8EncryptedPrivateKeyInfo(EncryptedPrivateKeyInfo.getInstance(pemObject.getContent()));
            } catch (Exception e2) {
                throw new PEMException("problem parsing ENCRYPTED PRIVATE KEY: " + e2.toString(), e2);
            }
        }
    }

    /* loaded from: classes4.dex */
    private class KeyPairParser implements PemObjectParser {
        private final PEMKeyPairParser pemKeyPairParser;

        public KeyPairParser(PEMParser pEMParser, PEMKeyPairParser pEMKeyPairParser) {
            this.pemKeyPairParser = pEMKeyPairParser;
        }

        @Override // org.bouncycastle.util.io.pem.PemObjectParser
        public Object parseObject(PemObject pemObject) {
            boolean z = false;
            String str = null;
            for (PemHeader pemHeader : pemObject.getHeaders()) {
                if (pemHeader.getName().equals("Proc-Type") && pemHeader.getValue().equals("4,ENCRYPTED")) {
                    z = true;
                } else if (pemHeader.getName().equals("DEK-Info")) {
                    str = pemHeader.getValue();
                }
            }
            byte[] content = pemObject.getContent();
            try {
                if (z) {
                    StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
                    return new PEMEncryptedKeyPair(stringTokenizer.nextToken(), Hex.decode(stringTokenizer.nextToken()), content, this.pemKeyPairParser);
                }
                return this.pemKeyPairParser.parse(content);
            } catch (IOException e2) {
                if (z) {
                    throw new PEMException("exception decoding - please check password and data.", e2);
                }
                throw new PEMException(e2.getMessage(), e2);
            } catch (IllegalArgumentException e3) {
                if (z) {
                    throw new PEMException("exception decoding - please check password and data.", e3);
                }
                throw new PEMException(e3.getMessage(), e3);
            }
        }
    }

    /* loaded from: classes4.dex */
    private class PKCS10CertificationRequestParser implements PemObjectParser {
        private PKCS10CertificationRequestParser(PEMParser pEMParser) {
        }

        @Override // org.bouncycastle.util.io.pem.PemObjectParser
        public Object parseObject(PemObject pemObject) {
            try {
                return new PKCS10CertificationRequest(pemObject.getContent());
            } catch (Exception e2) {
                throw new PEMException("problem parsing certrequest: " + e2.toString(), e2);
            }
        }
    }

    /* loaded from: classes4.dex */
    private class PKCS7Parser implements PemObjectParser {
        private PKCS7Parser(PEMParser pEMParser) {
        }

        @Override // org.bouncycastle.util.io.pem.PemObjectParser
        public Object parseObject(PemObject pemObject) {
            try {
                return ContentInfo.getInstance(new ASN1InputStream(pemObject.getContent()).readObject());
            } catch (Exception e2) {
                throw new PEMException("problem parsing PKCS7 object: " + e2.toString(), e2);
            }
        }
    }

    /* loaded from: classes4.dex */
    private class PrivateKeyParser implements PemObjectParser {
        public PrivateKeyParser(PEMParser pEMParser) {
        }

        @Override // org.bouncycastle.util.io.pem.PemObjectParser
        public Object parseObject(PemObject pemObject) {
            try {
                return PrivateKeyInfo.getInstance(pemObject.getContent());
            } catch (Exception e2) {
                throw new PEMException("problem parsing PRIVATE KEY: " + e2.toString(), e2);
            }
        }
    }

    /* loaded from: classes4.dex */
    private class PublicKeyParser implements PemObjectParser {
        public PublicKeyParser(PEMParser pEMParser) {
        }

        @Override // org.bouncycastle.util.io.pem.PemObjectParser
        public Object parseObject(PemObject pemObject) {
            return SubjectPublicKeyInfo.getInstance(pemObject.getContent());
        }
    }

    /* loaded from: classes4.dex */
    private class RSAKeyPairParser implements PEMKeyPairParser {
        private RSAKeyPairParser(PEMParser pEMParser) {
        }

        @Override // org.bouncycastle.openssl.PEMKeyPairParser
        public PEMKeyPair parse(byte[] bArr) {
            try {
                ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(bArr);
                if (aSN1Sequence.size() == 9) {
                    RSAPrivateKey rSAPrivateKey = RSAPrivateKey.getInstance(aSN1Sequence);
                    RSAPublicKey rSAPublicKey = new RSAPublicKey(rSAPrivateKey.getModulus(), rSAPrivateKey.getPublicExponent());
                    AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE);
                    return new PEMKeyPair(new SubjectPublicKeyInfo(algorithmIdentifier, rSAPublicKey), new PrivateKeyInfo(algorithmIdentifier, rSAPrivateKey));
                }
                throw new PEMException("malformed sequence in RSA private key");
            } catch (IOException e2) {
                throw e2;
            } catch (Exception e3) {
                throw new PEMException("problem creating RSA private key: " + e3.toString(), e3);
            }
        }
    }

    /* loaded from: classes4.dex */
    private class RSAPublicKeyParser implements PemObjectParser {
        public RSAPublicKeyParser(PEMParser pEMParser) {
        }

        @Override // org.bouncycastle.util.io.pem.PemObjectParser
        public Object parseObject(PemObject pemObject) {
            try {
                return new SubjectPublicKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE), RSAPublicKey.getInstance(pemObject.getContent()));
            } catch (IOException e2) {
                throw e2;
            } catch (Exception e3) {
                throw new PEMException("problem extracting key: " + e3.toString(), e3);
            }
        }
    }

    /* loaded from: classes4.dex */
    private class X509AttributeCertificateParser implements PemObjectParser {
        private X509AttributeCertificateParser(PEMParser pEMParser) {
        }

        @Override // org.bouncycastle.util.io.pem.PemObjectParser
        public Object parseObject(PemObject pemObject) {
            return new X509AttributeCertificateHolder(pemObject.getContent());
        }
    }

    /* loaded from: classes4.dex */
    private class X509CRLParser implements PemObjectParser {
        private X509CRLParser(PEMParser pEMParser) {
        }

        @Override // org.bouncycastle.util.io.pem.PemObjectParser
        public Object parseObject(PemObject pemObject) {
            try {
                return new X509CRLHolder(pemObject.getContent());
            } catch (Exception e2) {
                throw new PEMException("problem parsing cert: " + e2.toString(), e2);
            }
        }
    }

    /* loaded from: classes4.dex */
    private class X509CertificateParser implements PemObjectParser {
        private X509CertificateParser(PEMParser pEMParser) {
        }

        @Override // org.bouncycastle.util.io.pem.PemObjectParser
        public Object parseObject(PemObject pemObject) {
            try {
                return new X509CertificateHolder(pemObject.getContent());
            } catch (Exception e2) {
                throw new PEMException("problem parsing cert: " + e2.toString(), e2);
            }
        }
    }

    /* loaded from: classes4.dex */
    private class X509TrustedCertificateParser implements PemObjectParser {
        private X509TrustedCertificateParser(PEMParser pEMParser) {
        }

        @Override // org.bouncycastle.util.io.pem.PemObjectParser
        public Object parseObject(PemObject pemObject) {
            try {
                return new X509TrustedCertificateBlock(pemObject.getContent());
            } catch (Exception e2) {
                throw new PEMException("problem parsing cert: " + e2.toString(), e2);
            }
        }
    }

    public PEMParser(Reader reader) {
        super(reader);
        HashMap hashMap = new HashMap();
        this.f14398a = hashMap;
        hashMap.put(TYPE_CERTIFICATE_REQUEST, new PKCS10CertificationRequestParser());
        hashMap.put(TYPE_NEW_CERTIFICATE_REQUEST, new PKCS10CertificationRequestParser());
        hashMap.put(TYPE_CERTIFICATE, new X509CertificateParser());
        hashMap.put(TYPE_TRUSTED_CERTIFICATE, new X509TrustedCertificateParser());
        hashMap.put(TYPE_X509_CERTIFICATE, new X509CertificateParser());
        hashMap.put(TYPE_X509_CRL, new X509CRLParser());
        hashMap.put(TYPE_PKCS7, new PKCS7Parser());
        hashMap.put(TYPE_CMS, new PKCS7Parser());
        hashMap.put(TYPE_ATTRIBUTE_CERTIFICATE, new X509AttributeCertificateParser());
        hashMap.put(TYPE_EC_PARAMETERS, new ECCurveParamsParser());
        hashMap.put(TYPE_PUBLIC_KEY, new PublicKeyParser(this));
        hashMap.put(TYPE_RSA_PUBLIC_KEY, new RSAPublicKeyParser(this));
        hashMap.put(TYPE_RSA_PRIVATE_KEY, new KeyPairParser(this, new RSAKeyPairParser()));
        hashMap.put(TYPE_DSA_PRIVATE_KEY, new KeyPairParser(this, new DSAKeyPairParser()));
        hashMap.put(TYPE_EC_PRIVATE_KEY, new KeyPairParser(this, new ECDSAKeyPairParser()));
        hashMap.put(TYPE_ENCRYPTED_PRIVATE_KEY, new EncryptedPrivateKeyParser(this));
        hashMap.put(TYPE_PRIVATE_KEY, new PrivateKeyParser(this));
    }

    public Set<String> getSupportedTypes() {
        return Collections.unmodifiableSet(this.f14398a.keySet());
    }

    public Object readObject() {
        PemObject readPemObject = readPemObject();
        if (readPemObject != null) {
            String type = readPemObject.getType();
            Object obj = this.f14398a.get(type);
            if (obj != null) {
                return ((PemObjectParser) obj).parseObject(readPemObject);
            }
            throw new IOException("unrecognised object: " + type);
        }
        return null;
    }
}
