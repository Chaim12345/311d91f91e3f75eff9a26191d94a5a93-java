package org.bouncycastle.openssl.jcajce;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.openssl.EncryptionException;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.util.Integers;
/* loaded from: classes4.dex */
class PEMUtilities {
    private static final Map KEYSIZES;
    private static final Set PKCS5_SCHEME_1;
    private static final Set PKCS5_SCHEME_2;
    private static final Map PRFS;
    private static final Map PRFS_SALT;

    static {
        HashMap hashMap = new HashMap();
        KEYSIZES = hashMap;
        HashSet hashSet = new HashSet();
        PKCS5_SCHEME_1 = hashSet;
        HashSet hashSet2 = new HashSet();
        PKCS5_SCHEME_2 = hashSet2;
        HashMap hashMap2 = new HashMap();
        PRFS = hashMap2;
        HashMap hashMap3 = new HashMap();
        PRFS_SALT = hashMap3;
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD2AndDES_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD2AndRC2_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD5AndRC2_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithSHA1AndRC2_CBC);
        hashSet2.add(PKCSObjectIdentifiers.id_PBES2);
        ASN1ObjectIdentifier aSN1ObjectIdentifier = PKCSObjectIdentifiers.des_EDE3_CBC;
        hashSet2.add(aSN1ObjectIdentifier);
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = NISTObjectIdentifiers.id_aes128_CBC;
        hashSet2.add(aSN1ObjectIdentifier2);
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = NISTObjectIdentifiers.id_aes192_CBC;
        hashSet2.add(aSN1ObjectIdentifier3);
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = NISTObjectIdentifiers.id_aes256_CBC;
        hashSet2.add(aSN1ObjectIdentifier4);
        hashMap.put(aSN1ObjectIdentifier.getId(), Integers.valueOf(192));
        hashMap.put(aSN1ObjectIdentifier2.getId(), Integers.valueOf(128));
        hashMap.put(aSN1ObjectIdentifier3.getId(), Integers.valueOf(192));
        hashMap.put(aSN1ObjectIdentifier4.getId(), Integers.valueOf(256));
        hashMap.put(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4.getId(), Integers.valueOf(128));
        hashMap.put(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4, Integers.valueOf(40));
        hashMap.put(PKCSObjectIdentifiers.pbeWithSHAAnd2_KeyTripleDES_CBC, Integers.valueOf(128));
        hashMap.put(PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC, Integers.valueOf(192));
        hashMap.put(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC2_CBC, Integers.valueOf(128));
        hashMap.put(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC, Integers.valueOf(40));
        ASN1ObjectIdentifier aSN1ObjectIdentifier5 = PKCSObjectIdentifiers.id_hmacWithSHA1;
        hashMap2.put(aSN1ObjectIdentifier5, "PBKDF2withHMACSHA1");
        ASN1ObjectIdentifier aSN1ObjectIdentifier6 = PKCSObjectIdentifiers.id_hmacWithSHA256;
        hashMap2.put(aSN1ObjectIdentifier6, "PBKDF2withHMACSHA256");
        ASN1ObjectIdentifier aSN1ObjectIdentifier7 = PKCSObjectIdentifiers.id_hmacWithSHA512;
        hashMap2.put(aSN1ObjectIdentifier7, "PBKDF2withHMACSHA512");
        ASN1ObjectIdentifier aSN1ObjectIdentifier8 = PKCSObjectIdentifiers.id_hmacWithSHA224;
        hashMap2.put(aSN1ObjectIdentifier8, "PBKDF2withHMACSHA224");
        ASN1ObjectIdentifier aSN1ObjectIdentifier9 = PKCSObjectIdentifiers.id_hmacWithSHA384;
        hashMap2.put(aSN1ObjectIdentifier9, "PBKDF2withHMACSHA384");
        ASN1ObjectIdentifier aSN1ObjectIdentifier10 = NISTObjectIdentifiers.id_hmacWithSHA3_224;
        hashMap2.put(aSN1ObjectIdentifier10, "PBKDF2withHMACSHA3-224");
        ASN1ObjectIdentifier aSN1ObjectIdentifier11 = NISTObjectIdentifiers.id_hmacWithSHA3_256;
        hashMap2.put(aSN1ObjectIdentifier11, "PBKDF2withHMACSHA3-256");
        ASN1ObjectIdentifier aSN1ObjectIdentifier12 = NISTObjectIdentifiers.id_hmacWithSHA3_384;
        hashMap2.put(aSN1ObjectIdentifier12, "PBKDF2withHMACSHA3-384");
        ASN1ObjectIdentifier aSN1ObjectIdentifier13 = NISTObjectIdentifiers.id_hmacWithSHA3_512;
        hashMap2.put(aSN1ObjectIdentifier13, "PBKDF2withHMACSHA3-512");
        ASN1ObjectIdentifier aSN1ObjectIdentifier14 = CryptoProObjectIdentifiers.gostR3411Hmac;
        hashMap2.put(aSN1ObjectIdentifier14, "PBKDF2withHMACGOST3411");
        hashMap3.put(aSN1ObjectIdentifier5, Integers.valueOf(20));
        hashMap3.put(aSN1ObjectIdentifier6, Integers.valueOf(32));
        hashMap3.put(aSN1ObjectIdentifier7, Integers.valueOf(64));
        hashMap3.put(aSN1ObjectIdentifier8, Integers.valueOf(28));
        hashMap3.put(aSN1ObjectIdentifier9, Integers.valueOf(48));
        hashMap3.put(aSN1ObjectIdentifier10, Integers.valueOf(28));
        hashMap3.put(aSN1ObjectIdentifier11, Integers.valueOf(32));
        hashMap3.put(aSN1ObjectIdentifier12, Integers.valueOf(48));
        hashMap3.put(aSN1ObjectIdentifier13, Integers.valueOf(64));
        hashMap3.put(aSN1ObjectIdentifier14, Integers.valueOf(32));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] a(boolean z, JcaJceHelper jcaJceHelper, byte[] bArr, char[] cArr, String str, byte[] bArr2) {
        String str2;
        String str3;
        String str4;
        String str5;
        byte[] bArr3;
        SecretKey key;
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
        if (str.endsWith("-CFB")) {
            str2 = "CFB";
            str3 = "NoPadding";
        } else {
            str2 = "CBC";
            str3 = "PKCS5Padding";
        }
        if (str.endsWith("-ECB") || "DES-EDE".equals(str) || "DES-EDE3".equals(str)) {
            ivParameterSpec = null;
            str2 = "ECB";
        }
        RC2ParameterSpec rC2ParameterSpec = ivParameterSpec;
        if (str.endsWith("-OFB")) {
            str5 = "OFB";
            str4 = "NoPadding";
        } else {
            str4 = str3;
            str5 = str2;
        }
        String str6 = "AES";
        int i2 = 1;
        if (str.startsWith("DES-EDE")) {
            key = getKey(jcaJceHelper, cArr, "DESede", 24, bArr2, !str.startsWith("DES-EDE3"));
            str6 = "DESede";
        } else if (str.startsWith("DES-")) {
            str6 = "DES";
            key = getKey(jcaJceHelper, cArr, "DES", 8, bArr2);
        } else if (str.startsWith("BF-")) {
            str6 = "Blowfish";
            key = getKey(jcaJceHelper, cArr, "Blowfish", 16, bArr2);
        } else {
            int i3 = 128;
            if (str.startsWith("RC2-")) {
                str6 = "RC2";
                if (str.startsWith("RC2-40-")) {
                    i3 = 40;
                } else if (str.startsWith("RC2-64-")) {
                    i3 = 64;
                }
                key = getKey(jcaJceHelper, cArr, "RC2", i3 / 8, bArr2);
                rC2ParameterSpec = rC2ParameterSpec == null ? new RC2ParameterSpec(i3) : new RC2ParameterSpec(i3, bArr2);
            } else if (!str.startsWith("AES-")) {
                throw new EncryptionException("unknown encryption with private key");
            } else {
                if (bArr2.length > 8) {
                    bArr3 = new byte[8];
                    System.arraycopy(bArr2, 0, bArr3, 0, 8);
                } else {
                    bArr3 = bArr2;
                }
                if (!str.startsWith("AES-128-")) {
                    if (str.startsWith("AES-192-")) {
                        i3 = 192;
                    } else if (!str.startsWith("AES-256-")) {
                        throw new EncryptionException("unknown AES encryption with private key");
                    } else {
                        i3 = 256;
                    }
                }
                key = getKey(jcaJceHelper, cArr, "AES", i3 / 8, bArr3);
            }
        }
        try {
            Cipher createCipher = jcaJceHelper.createCipher(str6 + "/" + str5 + "/" + str4);
            if (!z) {
                i2 = 2;
            }
            if (rC2ParameterSpec == null) {
                createCipher.init(i2, key);
            } else {
                createCipher.init(i2, key, rC2ParameterSpec);
            }
            return createCipher.doFinal(bArr);
        } catch (Exception e2) {
            throw new EncryptionException("exception using cipher - please check password and data.", e2);
        }
    }

    static int b(String str) {
        Map map = KEYSIZES;
        if (map.containsKey(str)) {
            return ((Integer) map.get(str)).intValue();
        }
        throw new IllegalStateException("no key size for algorithm: " + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Map map = PRFS_SALT;
        if (map.containsKey(aSN1ObjectIdentifier)) {
            return ((Integer) map.get(aSN1ObjectIdentifier)).intValue();
        }
        throw new IllegalStateException("no salt size for algorithm: " + aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean d(AlgorithmIdentifier algorithmIdentifier) {
        return algorithmIdentifier == null || algorithmIdentifier.getAlgorithm().equals((ASN1Primitive) PKCSObjectIdentifiers.id_hmacWithSHA1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean e(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return PKCS5_SCHEME_1.contains(aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return PKCS5_SCHEME_2.contains(aSN1ObjectIdentifier);
    }

    public static SecretKey generateSecretKeyForPKCS5Scheme2(JcaJceHelper jcaJceHelper, String str, char[] cArr, byte[] bArr, int i2) {
        return new SecretKeySpec(jcaJceHelper.createSecretKeyFactory("PBKDF2with8BIT").generateSecret(new PBEKeySpec(cArr, bArr, i2, b(str))).getEncoded(), str);
    }

    public static SecretKey generateSecretKeyForPKCS5Scheme2(JcaJceHelper jcaJceHelper, String str, char[] cArr, byte[] bArr, int i2, AlgorithmIdentifier algorithmIdentifier) {
        String str2 = (String) PRFS.get(algorithmIdentifier.getAlgorithm());
        if (str2 != null) {
            return new SecretKeySpec(jcaJceHelper.createSecretKeyFactory(str2).generateSecret(new PBEKeySpec(cArr, bArr, i2, b(str))).getEncoded(), str);
        }
        throw new NoSuchAlgorithmException("unknown PRF in PKCS#2: " + algorithmIdentifier.getAlgorithm());
    }

    private static SecretKey getKey(JcaJceHelper jcaJceHelper, char[] cArr, String str, int i2, byte[] bArr) {
        return getKey(jcaJceHelper, cArr, str, i2, bArr, false);
    }

    private static SecretKey getKey(JcaJceHelper jcaJceHelper, char[] cArr, String str, int i2, byte[] bArr, boolean z) {
        try {
            byte[] encoded = jcaJceHelper.createSecretKeyFactory("PBKDF-OpenSSL").generateSecret(new PBEKeySpec(cArr, bArr, 1, i2 * 8)).getEncoded();
            if (z && encoded.length >= 24) {
                System.arraycopy(encoded, 0, encoded, 16, 8);
            }
            return new SecretKeySpec(encoded, str);
        } catch (GeneralSecurityException e2) {
            throw new PEMException("Unable to create OpenSSL PBDKF: " + e2.getMessage(), e2);
        }
    }

    public static boolean isPKCS12(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return aSN1ObjectIdentifier.getId().startsWith(PKCSObjectIdentifiers.pkcs_12PbeIds.getId());
    }
}
