package org.bouncycastle.jcajce.provider.asymmetric.dh;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Hashtable;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.generators.DHBasicKeyPairGenerator;
import org.bouncycastle.crypto.generators.DHParametersGenerator;
import org.bouncycastle.crypto.params.DHKeyGenerationParameters;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHPrivateKeyParameters;
import org.bouncycastle.crypto.params.DHPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.PrimeCertaintyCalculator;
import org.bouncycastle.jcajce.spec.DHDomainParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Integers;
/* loaded from: classes3.dex */
public class KeyPairGeneratorSpi extends KeyPairGenerator {

    /* renamed from: a  reason: collision with root package name */
    DHKeyGenerationParameters f13627a;

    /* renamed from: b  reason: collision with root package name */
    DHBasicKeyPairGenerator f13628b;

    /* renamed from: c  reason: collision with root package name */
    int f13629c;

    /* renamed from: d  reason: collision with root package name */
    SecureRandom f13630d;

    /* renamed from: e  reason: collision with root package name */
    boolean f13631e;
    private static Hashtable params = new Hashtable();
    private static Object lock = new Object();

    public KeyPairGeneratorSpi() {
        super("DH");
        this.f13628b = new DHBasicKeyPairGenerator();
        this.f13629c = 2048;
        this.f13630d = CryptoServicesRegistrar.getSecureRandom();
        this.f13631e = false;
    }

    private DHKeyGenerationParameters convertParams(SecureRandom secureRandom, DHParameterSpec dHParameterSpec) {
        return dHParameterSpec instanceof DHDomainParameterSpec ? new DHKeyGenerationParameters(secureRandom, ((DHDomainParameterSpec) dHParameterSpec).getDomainParameters()) : new DHKeyGenerationParameters(secureRandom, new DHParameters(dHParameterSpec.getP(), dHParameterSpec.getG(), null, dHParameterSpec.getL()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        DHKeyGenerationParameters convertParams;
        if (!this.f13631e) {
            Integer valueOf = Integers.valueOf(this.f13629c);
            if (params.containsKey(valueOf)) {
                convertParams = (DHKeyGenerationParameters) params.get(valueOf);
            } else {
                DHParameterSpec dHDefaultParameters = BouncyCastleProvider.CONFIGURATION.getDHDefaultParameters(this.f13629c);
                if (dHDefaultParameters != null) {
                    convertParams = convertParams(this.f13630d, dHDefaultParameters);
                } else {
                    synchronized (lock) {
                        if (params.containsKey(valueOf)) {
                            this.f13627a = (DHKeyGenerationParameters) params.get(valueOf);
                        } else {
                            DHParametersGenerator dHParametersGenerator = new DHParametersGenerator();
                            int i2 = this.f13629c;
                            dHParametersGenerator.init(i2, PrimeCertaintyCalculator.getDefaultCertainty(i2), this.f13630d);
                            DHKeyGenerationParameters dHKeyGenerationParameters = new DHKeyGenerationParameters(this.f13630d, dHParametersGenerator.generateParameters());
                            this.f13627a = dHKeyGenerationParameters;
                            params.put(valueOf, dHKeyGenerationParameters);
                        }
                    }
                    this.f13628b.init(this.f13627a);
                    this.f13631e = true;
                }
            }
            this.f13627a = convertParams;
            this.f13628b.init(this.f13627a);
            this.f13631e = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.f13628b.generateKeyPair();
        return new KeyPair(new BCDHPublicKey((DHPublicKeyParameters) generateKeyPair.getPublic()), new BCDHPrivateKey((DHPrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i2, SecureRandom secureRandom) {
        this.f13629c = i2;
        this.f13630d = secureRandom;
        this.f13631e = false;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        if (!(algorithmParameterSpec instanceof DHParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a DHParameterSpec");
        }
        try {
            DHKeyGenerationParameters convertParams = convertParams(secureRandom, (DHParameterSpec) algorithmParameterSpec);
            this.f13627a = convertParams;
            this.f13628b.init(convertParams);
            this.f13631e = true;
        } catch (IllegalArgumentException e2) {
            throw new InvalidAlgorithmParameterException(e2.getMessage(), e2);
        }
    }
}
