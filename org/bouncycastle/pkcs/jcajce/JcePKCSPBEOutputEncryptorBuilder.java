package org.bouncycastle.pkcs.jcajce;

import java.io.OutputStream;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.misc.ScryptParams;
import org.bouncycastle.asn1.pkcs.EncryptionScheme;
import org.bouncycastle.asn1.pkcs.KeyDerivationFunc;
import org.bouncycastle.asn1.pkcs.PBES2Parameters;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.util.PBKDF2Config;
import org.bouncycastle.crypto.util.PBKDFConfig;
import org.bouncycastle.crypto.util.ScryptConfig;
import org.bouncycastle.jcajce.PKCS12KeyWithParameters;
import org.bouncycastle.jcajce.io.CipherOutputStream;
import org.bouncycastle.jcajce.spec.ScryptKeySpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.AlgorithmNameFinder;
import org.bouncycastle.operator.DefaultAlgorithmNameFinder;
import org.bouncycastle.operator.DefaultSecretKeySizeProvider;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.operator.SecretKeySizeProvider;
/* loaded from: classes4.dex */
public class JcePKCSPBEOutputEncryptorBuilder {
    private ASN1ObjectIdentifier algorithm;
    private AlgorithmNameFinder algorithmNameFinder;
    private JcaJceHelper helper;
    private int iterationCount;
    private ASN1ObjectIdentifier keyEncAlgorithm;
    private SecretKeySizeProvider keySizeProvider;
    private final PBKDFConfig pbkdf;
    private PBKDF2Config.Builder pbkdfBuilder;
    private SecureRandom random;

    public JcePKCSPBEOutputEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.helper = new DefaultJcaJceHelper();
        this.keySizeProvider = DefaultSecretKeySizeProvider.INSTANCE;
        this.algorithmNameFinder = new DefaultAlgorithmNameFinder();
        this.iterationCount = 1024;
        this.pbkdfBuilder = new PBKDF2Config.Builder();
        this.pbkdf = null;
        if (isPKCS12(aSN1ObjectIdentifier)) {
            this.algorithm = aSN1ObjectIdentifier;
        } else {
            this.algorithm = PKCSObjectIdentifiers.id_PBES2;
        }
        this.keyEncAlgorithm = aSN1ObjectIdentifier;
    }

    public JcePKCSPBEOutputEncryptorBuilder(PBKDFConfig pBKDFConfig, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.helper = new DefaultJcaJceHelper();
        this.keySizeProvider = DefaultSecretKeySizeProvider.INSTANCE;
        this.algorithmNameFinder = new DefaultAlgorithmNameFinder();
        this.iterationCount = 1024;
        this.pbkdfBuilder = new PBKDF2Config.Builder();
        this.algorithm = PKCSObjectIdentifiers.id_PBES2;
        this.pbkdf = pBKDFConfig;
        this.keyEncAlgorithm = aSN1ObjectIdentifier;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] PKCS12PasswordToBytes(char[] cArr) {
        if (cArr == null || cArr.length <= 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[(cArr.length + 1) * 2];
        for (int i2 = 0; i2 != cArr.length; i2++) {
            int i3 = i2 * 2;
            bArr[i3] = (byte) (cArr[i2] >>> '\b');
            bArr[i3 + 1] = (byte) cArr[i2];
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] PKCS5PasswordToBytes(char[] cArr) {
        if (cArr != null) {
            int length = cArr.length;
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 != length; i2++) {
                bArr[i2] = (byte) cArr[i2];
            }
            return bArr;
        }
        return new byte[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPKCS12(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return aSN1ObjectIdentifier.on(PKCSObjectIdentifiers.pkcs_12PbeIds) || aSN1ObjectIdentifier.on(BCObjectIdentifiers.bc_pbe_sha1_pkcs12) || aSN1ObjectIdentifier.on(BCObjectIdentifiers.bc_pbe_sha256_pkcs12);
    }

    private SecretKey simplifyPbeKey(SecretKey secretKey) {
        return (!this.algorithmNameFinder.hasAlgorithmName(this.keyEncAlgorithm) || this.algorithmNameFinder.getAlgorithmName(this.keyEncAlgorithm).indexOf("AES") < 0) ? secretKey : new SecretKeySpec(secretKey.getEncoded(), "AES");
    }

    public OutputEncryptor build(final char[] cArr) {
        Cipher createCipher;
        final AlgorithmIdentifier algorithmIdentifier;
        final Cipher cipher;
        if (this.random == null) {
            this.random = new SecureRandom();
        }
        try {
            if (isPKCS12(this.algorithm)) {
                byte[] bArr = new byte[20];
                this.random.nextBytes(bArr);
                cipher = this.helper.createCipher(this.algorithm.getId());
                cipher.init(1, new PKCS12KeyWithParameters(cArr, bArr, this.iterationCount));
                algorithmIdentifier = new AlgorithmIdentifier(this.algorithm, new PKCS12PBEParams(bArr, this.iterationCount));
            } else if (!this.algorithm.equals((ASN1Primitive) PKCSObjectIdentifiers.id_PBES2)) {
                throw new OperatorCreationException("unrecognised algorithm");
            } else {
                PBKDFConfig pBKDFConfig = this.pbkdf;
                if (pBKDFConfig == null) {
                    pBKDFConfig = this.pbkdfBuilder.build();
                }
                ASN1ObjectIdentifier aSN1ObjectIdentifier = MiscObjectIdentifiers.id_scrypt;
                if (aSN1ObjectIdentifier.equals((ASN1Primitive) pBKDFConfig.getAlgorithm())) {
                    ScryptConfig scryptConfig = (ScryptConfig) pBKDFConfig;
                    byte[] bArr2 = new byte[scryptConfig.getSaltLength()];
                    this.random.nextBytes(bArr2);
                    ScryptParams scryptParams = new ScryptParams(bArr2, scryptConfig.getCostParameter(), scryptConfig.getBlockSize(), scryptConfig.getParallelizationParameter());
                    SecretKey generateSecret = this.helper.createSecretKeyFactory("SCRYPT").generateSecret(new ScryptKeySpec(cArr, bArr2, scryptConfig.getCostParameter(), scryptConfig.getBlockSize(), scryptConfig.getParallelizationParameter(), this.keySizeProvider.getKeySize(new AlgorithmIdentifier(this.keyEncAlgorithm))));
                    createCipher = this.helper.createCipher(this.keyEncAlgorithm.getId());
                    createCipher.init(1, simplifyPbeKey(generateSecret), this.random);
                    algorithmIdentifier = new AlgorithmIdentifier(this.algorithm, createCipher.getParameters() != null ? new PBES2Parameters(new KeyDerivationFunc(aSN1ObjectIdentifier, scryptParams), new EncryptionScheme(this.keyEncAlgorithm, ASN1Primitive.fromByteArray(createCipher.getParameters().getEncoded()))) : new PBES2Parameters(new KeyDerivationFunc(aSN1ObjectIdentifier, scryptParams), new EncryptionScheme(this.keyEncAlgorithm)));
                } else {
                    PBKDF2Config pBKDF2Config = (PBKDF2Config) pBKDFConfig;
                    byte[] bArr3 = new byte[pBKDF2Config.getSaltLength()];
                    this.random.nextBytes(bArr3);
                    SecretKey generateSecret2 = this.helper.createSecretKeyFactory(JceUtils.a(pBKDF2Config.getPRF().getAlgorithm())).generateSecret(new PBEKeySpec(cArr, bArr3, pBKDF2Config.getIterationCount(), this.keySizeProvider.getKeySize(new AlgorithmIdentifier(this.keyEncAlgorithm))));
                    createCipher = this.helper.createCipher(this.keyEncAlgorithm.getId());
                    createCipher.init(1, simplifyPbeKey(generateSecret2), this.random);
                    algorithmIdentifier = new AlgorithmIdentifier(this.algorithm, createCipher.getParameters() != null ? new PBES2Parameters(new KeyDerivationFunc(PKCSObjectIdentifiers.id_PBKDF2, new PBKDF2Params(bArr3, pBKDF2Config.getIterationCount(), pBKDF2Config.getPRF())), new EncryptionScheme(this.keyEncAlgorithm, ASN1Primitive.fromByteArray(createCipher.getParameters().getEncoded()))) : new PBES2Parameters(new KeyDerivationFunc(PKCSObjectIdentifiers.id_PBKDF2, new PBKDF2Params(bArr3, pBKDF2Config.getIterationCount(), pBKDF2Config.getPRF())), new EncryptionScheme(this.keyEncAlgorithm)));
                }
                cipher = createCipher;
            }
            return new OutputEncryptor() { // from class: org.bouncycastle.pkcs.jcajce.JcePKCSPBEOutputEncryptorBuilder.1
                @Override // org.bouncycastle.operator.OutputEncryptor
                public AlgorithmIdentifier getAlgorithmIdentifier() {
                    return algorithmIdentifier;
                }

                @Override // org.bouncycastle.operator.OutputEncryptor
                public GenericKey getKey() {
                    return JcePKCSPBEOutputEncryptorBuilder.this.isPKCS12(algorithmIdentifier.getAlgorithm()) ? new GenericKey(algorithmIdentifier, JcePKCSPBEOutputEncryptorBuilder.PKCS12PasswordToBytes(cArr)) : new GenericKey(algorithmIdentifier, JcePKCSPBEOutputEncryptorBuilder.PKCS5PasswordToBytes(cArr));
                }

                @Override // org.bouncycastle.operator.OutputEncryptor
                public OutputStream getOutputStream(OutputStream outputStream) {
                    return new CipherOutputStream(outputStream, cipher);
                }
            };
        } catch (Exception e2) {
            throw new OperatorCreationException("unable to create OutputEncryptor: " + e2.getMessage(), e2);
        }
    }

    public JcePKCSPBEOutputEncryptorBuilder setIterationCount(int i2) {
        if (this.pbkdf == null) {
            this.iterationCount = i2;
            this.pbkdfBuilder.withIterationCount(i2);
            return this;
        }
        throw new IllegalStateException("set iteration count using PBKDFDef");
    }

    public JcePKCSPBEOutputEncryptorBuilder setKeySizeProvider(SecretKeySizeProvider secretKeySizeProvider) {
        this.keySizeProvider = secretKeySizeProvider;
        return this;
    }

    public JcePKCSPBEOutputEncryptorBuilder setPRF(AlgorithmIdentifier algorithmIdentifier) {
        if (this.pbkdf == null) {
            this.pbkdfBuilder.withPRF(algorithmIdentifier);
            return this;
        }
        throw new IllegalStateException("set PRF count using PBKDFDef");
    }

    public JcePKCSPBEOutputEncryptorBuilder setProvider(String str) {
        this.helper = new NamedJcaJceHelper(str);
        return this;
    }

    public JcePKCSPBEOutputEncryptorBuilder setProvider(Provider provider) {
        this.helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcePKCSPBEOutputEncryptorBuilder setRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }
}
