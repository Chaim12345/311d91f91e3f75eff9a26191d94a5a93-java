package org.bouncycastle.cert.crmf.jcajce;

import java.io.IOException;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Null;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.iana.IANAObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.jcajce.util.AlgorithmParametersUtils;
import org.bouncycastle.jcajce.util.JcaJceHelper;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class CRMFHelper {

    /* renamed from: a  reason: collision with root package name */
    protected static final Map f13073a;

    /* renamed from: b  reason: collision with root package name */
    protected static final Map f13074b;

    /* renamed from: c  reason: collision with root package name */
    protected static final Map f13075c;

    /* renamed from: d  reason: collision with root package name */
    protected static final Map f13076d;

    /* renamed from: e  reason: collision with root package name */
    protected static final Map f13077e;
    private JcaJceHelper helper;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface JCECallback {
        Object doInJCE();
    }

    static {
        HashMap hashMap = new HashMap();
        f13073a = hashMap;
        HashMap hashMap2 = new HashMap();
        f13074b = hashMap2;
        HashMap hashMap3 = new HashMap();
        f13075c = hashMap3;
        HashMap hashMap4 = new HashMap();
        f13076d = hashMap4;
        HashMap hashMap5 = new HashMap();
        f13077e = hashMap5;
        hashMap.put(PKCSObjectIdentifiers.des_EDE3_CBC, "DESEDE");
        hashMap.put(NISTObjectIdentifiers.id_aes128_CBC, "AES");
        hashMap.put(NISTObjectIdentifiers.id_aes192_CBC, "AES");
        hashMap.put(NISTObjectIdentifiers.id_aes256_CBC, "AES");
        hashMap2.put(CMSAlgorithm.DES_EDE3_CBC, "DESEDE/CBC/PKCS5Padding");
        hashMap2.put(CMSAlgorithm.AES128_CBC, "AES/CBC/PKCS5Padding");
        hashMap2.put(CMSAlgorithm.AES192_CBC, "AES/CBC/PKCS5Padding");
        hashMap2.put(CMSAlgorithm.AES256_CBC, "AES/CBC/PKCS5Padding");
        ASN1ObjectIdentifier aSN1ObjectIdentifier = PKCSObjectIdentifiers.rsaEncryption;
        hashMap2.put(new ASN1ObjectIdentifier(aSN1ObjectIdentifier.getId()), "RSA/ECB/PKCS1Padding");
        hashMap3.put(OIWObjectIdentifiers.idSHA1, "SHA1");
        hashMap3.put(NISTObjectIdentifiers.id_sha224, "SHA224");
        hashMap3.put(NISTObjectIdentifiers.id_sha256, "SHA256");
        hashMap3.put(NISTObjectIdentifiers.id_sha384, "SHA384");
        hashMap3.put(NISTObjectIdentifiers.id_sha512, "SHA512");
        hashMap5.put(IANAObjectIdentifiers.hmacSHA1, "HMACSHA1");
        hashMap5.put(PKCSObjectIdentifiers.id_hmacWithSHA1, "HMACSHA1");
        hashMap5.put(PKCSObjectIdentifiers.id_hmacWithSHA224, "HMACSHA224");
        hashMap5.put(PKCSObjectIdentifiers.id_hmacWithSHA256, "HMACSHA256");
        hashMap5.put(PKCSObjectIdentifiers.id_hmacWithSHA384, "HMACSHA384");
        hashMap5.put(PKCSObjectIdentifiers.id_hmacWithSHA512, "HMACSHA512");
        hashMap4.put(aSN1ObjectIdentifier, "RSA");
        hashMap4.put(X9ObjectIdentifiers.id_dsa, "DSA");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CRMFHelper(JcaJceHelper jcaJceHelper) {
        this.helper = jcaJceHelper;
    }

    static Object h(JCECallback jCECallback) {
        try {
            return jCECallback.doInJCE();
        } catch (InvalidAlgorithmParameterException e2) {
            throw new CRMFException("algorithm parameters invalid.", e2);
        } catch (InvalidKeyException e3) {
            throw new CRMFException("key invalid in message.", e3);
        } catch (NoSuchAlgorithmException e4) {
            throw new CRMFException("can't find algorithm.", e4);
        } catch (NoSuchProviderException e5) {
            throw new CRMFException("can't find provider.", e5);
        } catch (InvalidParameterSpecException e6) {
            throw new CRMFException("MAC algorithm parameter spec invalid.", e6);
        } catch (NoSuchPaddingException e7) {
            throw new CRMFException("required padding not supported.", e7);
        }
    }

    AlgorithmParameterGenerator a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str = (String) f13073a.get(aSN1ObjectIdentifier);
        if (str != null) {
            try {
                return this.helper.createAlgorithmParameterGenerator(str);
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return this.helper.createAlgorithmParameterGenerator(aSN1ObjectIdentifier.getId());
    }

    AlgorithmParameters b(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str = (String) f13073a.get(aSN1ObjectIdentifier);
        if (str != null) {
            try {
                return this.helper.createAlgorithmParameters(str);
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return this.helper.createAlgorithmParameters(aSN1ObjectIdentifier.getId());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cipher c(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        try {
            String str = (String) f13074b.get(aSN1ObjectIdentifier);
            if (str != null) {
                try {
                    return this.helper.createCipher(str);
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            return this.helper.createCipher(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException e2) {
            throw new CRMFException("cannot create cipher: " + e2.getMessage(), e2);
        }
    }

    public KeyGenerator createKeyGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        try {
            String str = (String) f13073a.get(aSN1ObjectIdentifier);
            if (str != null) {
                try {
                    return this.helper.createKeyGenerator(str);
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            return this.helper.createKeyGenerator(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException e2) {
            throw new CRMFException("cannot create key generator: " + e2.getMessage(), e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cipher d(final Key key, final AlgorithmIdentifier algorithmIdentifier) {
        return (Cipher) h(new JCECallback() { // from class: org.bouncycastle.cert.crmf.jcajce.CRMFHelper.1
            @Override // org.bouncycastle.cert.crmf.jcajce.CRMFHelper.JCECallback
            public Object doInJCE() {
                Cipher c2 = CRMFHelper.this.c(algorithmIdentifier.getAlgorithm());
                ASN1Primitive aSN1Primitive = (ASN1Primitive) algorithmIdentifier.getParameters();
                ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
                if (aSN1Primitive != null && !(aSN1Primitive instanceof ASN1Null)) {
                    try {
                        AlgorithmParameters b2 = CRMFHelper.this.b(algorithmIdentifier.getAlgorithm());
                        try {
                            AlgorithmParametersUtils.loadParameters(b2, aSN1Primitive);
                            c2.init(2, key, b2);
                        } catch (IOException e2) {
                            throw new CRMFException("error decoding algorithm parameters.", e2);
                        }
                    } catch (NoSuchAlgorithmException e3) {
                        if (!algorithm.equals((ASN1Primitive) CMSAlgorithm.DES_EDE3_CBC) && !algorithm.equals((ASN1Primitive) CMSAlgorithm.IDEA_CBC) && !algorithm.equals((ASN1Primitive) CMSAlgorithm.AES128_CBC) && !algorithm.equals((ASN1Primitive) CMSAlgorithm.AES192_CBC) && !algorithm.equals((ASN1Primitive) CMSAlgorithm.AES256_CBC)) {
                            throw e3;
                        }
                        c2.init(2, key, new IvParameterSpec(ASN1OctetString.getInstance(aSN1Primitive).getOctets()));
                    }
                } else if (algorithm.equals((ASN1Primitive) CMSAlgorithm.DES_EDE3_CBC) || algorithm.equals((ASN1Primitive) CMSAlgorithm.IDEA_CBC) || algorithm.equals((ASN1Primitive) CMSAlgorithm.CAST5_CBC)) {
                    c2.init(2, key, new IvParameterSpec(new byte[8]));
                } else {
                    c2.init(2, key);
                }
                return c2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageDigest e(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        try {
            String str = (String) f13075c.get(aSN1ObjectIdentifier);
            if (str != null) {
                try {
                    return this.helper.createMessageDigest(str);
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            return this.helper.createMessageDigest(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException e2) {
            throw new CRMFException("cannot create cipher: " + e2.getMessage(), e2);
        }
    }

    KeyFactory f(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        try {
            String str = (String) f13076d.get(aSN1ObjectIdentifier);
            if (str != null) {
                try {
                    return this.helper.createKeyFactory(str);
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            return this.helper.createKeyFactory(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException e2) {
            throw new CRMFException("cannot create cipher: " + e2.getMessage(), e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Mac g(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        try {
            String str = (String) f13077e.get(aSN1ObjectIdentifier);
            if (str != null) {
                try {
                    return this.helper.createMac(str);
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            return this.helper.createMac(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException e2) {
            throw new CRMFException("cannot create mac: " + e2.getMessage(), e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlgorithmParameters i(ASN1ObjectIdentifier aSN1ObjectIdentifier, SecretKey secretKey, SecureRandom secureRandom) {
        try {
            AlgorithmParameterGenerator a2 = a(aSN1ObjectIdentifier);
            if (aSN1ObjectIdentifier.equals((ASN1Primitive) CMSAlgorithm.RC2_CBC)) {
                byte[] bArr = new byte[8];
                secureRandom.nextBytes(bArr);
                try {
                    a2.init(new RC2ParameterSpec(secretKey.getEncoded().length * 8, bArr), secureRandom);
                } catch (InvalidAlgorithmParameterException e2) {
                    throw new CRMFException("parameters generation error: " + e2, e2);
                }
            }
            return a2.generateParameters();
        } catch (NoSuchAlgorithmException unused) {
            return null;
        } catch (GeneralSecurityException e3) {
            throw new CRMFException("exception creating algorithm parameter generator: " + e3, e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlgorithmIdentifier j(ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmParameters algorithmParameters) {
        ASN1Encodable extractParameters;
        if (algorithmParameters != null) {
            try {
                extractParameters = AlgorithmParametersUtils.extractParameters(algorithmParameters);
            } catch (IOException e2) {
                throw new CRMFException("cannot encode parameters: " + e2.getMessage(), e2);
            }
        } else {
            extractParameters = DERNull.INSTANCE;
        }
        return new AlgorithmIdentifier(aSN1ObjectIdentifier, extractParameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PublicKey k(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        try {
            return f(subjectPublicKeyInfo.getAlgorithm().getAlgorithm()).generatePublic(new X509EncodedKeySpec(subjectPublicKeyInfo.getEncoded()));
        } catch (Exception e2) {
            throw new CRMFException("invalid key: " + e2.getMessage(), e2);
        }
    }
}
