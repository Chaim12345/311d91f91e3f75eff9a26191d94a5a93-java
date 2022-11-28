package org.bouncycastle.jcajce.provider.asymmetric.dsa;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;
import java.util.Hashtable;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.DSAKeyPairGenerator;
import org.bouncycastle.crypto.generators.DSAParametersGenerator;
import org.bouncycastle.crypto.params.DSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.DSAParameterGenerationParameters;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.PrimeCertaintyCalculator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.tls.CipherSuite;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Properties;
/* loaded from: classes3.dex */
public class KeyPairGeneratorSpi extends KeyPairGenerator {

    /* renamed from: a  reason: collision with root package name */
    DSAKeyGenerationParameters f13637a;

    /* renamed from: b  reason: collision with root package name */
    DSAKeyPairGenerator f13638b;

    /* renamed from: c  reason: collision with root package name */
    int f13639c;

    /* renamed from: d  reason: collision with root package name */
    SecureRandom f13640d;

    /* renamed from: e  reason: collision with root package name */
    boolean f13641e;
    private static Hashtable params = new Hashtable();
    private static Object lock = new Object();

    public KeyPairGeneratorSpi() {
        super("DSA");
        this.f13638b = new DSAKeyPairGenerator();
        this.f13639c = 2048;
        this.f13640d = CryptoServicesRegistrar.getSecureRandom();
        this.f13641e = false;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        DSAParametersGenerator dSAParametersGenerator;
        int i2;
        SecureRandom secureRandom;
        if (!this.f13641e) {
            Integer valueOf = Integers.valueOf(this.f13639c);
            if (params.containsKey(valueOf)) {
                this.f13637a = (DSAKeyGenerationParameters) params.get(valueOf);
            } else {
                synchronized (lock) {
                    if (params.containsKey(valueOf)) {
                        this.f13637a = (DSAKeyGenerationParameters) params.get(valueOf);
                    } else {
                        int defaultCertainty = PrimeCertaintyCalculator.getDefaultCertainty(this.f13639c);
                        int i3 = this.f13639c;
                        if (i3 == 1024) {
                            dSAParametersGenerator = new DSAParametersGenerator();
                            if (Properties.isOverrideSet("org.bouncycastle.dsa.FIPS186-2for1024bits")) {
                                i2 = this.f13639c;
                                secureRandom = this.f13640d;
                                dSAParametersGenerator.init(i2, defaultCertainty, secureRandom);
                                DSAKeyGenerationParameters dSAKeyGenerationParameters = new DSAKeyGenerationParameters(this.f13640d, dSAParametersGenerator.generateParameters());
                                this.f13637a = dSAKeyGenerationParameters;
                                params.put(valueOf, dSAKeyGenerationParameters);
                            } else {
                                dSAParametersGenerator.init(new DSAParameterGenerationParameters(1024, CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256, defaultCertainty, this.f13640d));
                                DSAKeyGenerationParameters dSAKeyGenerationParameters2 = new DSAKeyGenerationParameters(this.f13640d, dSAParametersGenerator.generateParameters());
                                this.f13637a = dSAKeyGenerationParameters2;
                                params.put(valueOf, dSAKeyGenerationParameters2);
                            }
                        } else if (i3 > 1024) {
                            DSAParameterGenerationParameters dSAParameterGenerationParameters = new DSAParameterGenerationParameters(i3, 256, defaultCertainty, this.f13640d);
                            dSAParametersGenerator = new DSAParametersGenerator(new SHA256Digest());
                            dSAParametersGenerator.init(dSAParameterGenerationParameters);
                            DSAKeyGenerationParameters dSAKeyGenerationParameters22 = new DSAKeyGenerationParameters(this.f13640d, dSAParametersGenerator.generateParameters());
                            this.f13637a = dSAKeyGenerationParameters22;
                            params.put(valueOf, dSAKeyGenerationParameters22);
                        } else {
                            dSAParametersGenerator = new DSAParametersGenerator();
                            i2 = this.f13639c;
                            secureRandom = this.f13640d;
                            dSAParametersGenerator.init(i2, defaultCertainty, secureRandom);
                            DSAKeyGenerationParameters dSAKeyGenerationParameters222 = new DSAKeyGenerationParameters(this.f13640d, dSAParametersGenerator.generateParameters());
                            this.f13637a = dSAKeyGenerationParameters222;
                            params.put(valueOf, dSAKeyGenerationParameters222);
                        }
                    }
                }
            }
            this.f13638b.init(this.f13637a);
            this.f13641e = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.f13638b.generateKeyPair();
        return new KeyPair(new BCDSAPublicKey((DSAPublicKeyParameters) generateKeyPair.getPublic()), new BCDSAPrivateKey((DSAPrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i2, SecureRandom secureRandom) {
        boolean z;
        if (i2 < 512 || i2 > 4096 || ((i2 < 1024 && i2 % 64 != 0) || (i2 >= 1024 && i2 % 1024 != 0))) {
            throw new InvalidParameterException("strength must be from 512 - 4096 and a multiple of 1024 above 1024");
        }
        DSAParameterSpec dSADefaultParameters = BouncyCastleProvider.CONFIGURATION.getDSADefaultParameters(i2);
        if (dSADefaultParameters != null) {
            DSAKeyGenerationParameters dSAKeyGenerationParameters = new DSAKeyGenerationParameters(secureRandom, new DSAParameters(dSADefaultParameters.getP(), dSADefaultParameters.getQ(), dSADefaultParameters.getG()));
            this.f13637a = dSAKeyGenerationParameters;
            this.f13638b.init(dSAKeyGenerationParameters);
            z = true;
        } else {
            this.f13639c = i2;
            this.f13640d = secureRandom;
            z = false;
        }
        this.f13641e = z;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        if (!(algorithmParameterSpec instanceof DSAParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a DSAParameterSpec");
        }
        DSAParameterSpec dSAParameterSpec = (DSAParameterSpec) algorithmParameterSpec;
        DSAKeyGenerationParameters dSAKeyGenerationParameters = new DSAKeyGenerationParameters(secureRandom, new DSAParameters(dSAParameterSpec.getP(), dSAParameterSpec.getQ(), dSAParameterSpec.getG()));
        this.f13637a = dSAKeyGenerationParameters;
        this.f13638b.init(dSAKeyGenerationParameters);
        this.f13641e = true;
    }
}
