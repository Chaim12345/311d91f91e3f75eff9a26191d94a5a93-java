package org.bouncycastle.pqc.jcajce.provider.rainbow;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.pqc.crypto.rainbow.RainbowKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowKeyPairGenerator;
import org.bouncycastle.pqc.crypto.rainbow.RainbowParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.spec.RainbowParameterSpec;
/* loaded from: classes4.dex */
public class RainbowKeyPairGeneratorSpi extends KeyPairGenerator {

    /* renamed from: a  reason: collision with root package name */
    RainbowKeyGenerationParameters f14623a;

    /* renamed from: b  reason: collision with root package name */
    RainbowKeyPairGenerator f14624b;

    /* renamed from: c  reason: collision with root package name */
    SecureRandom f14625c;

    /* renamed from: d  reason: collision with root package name */
    boolean f14626d;

    public RainbowKeyPairGeneratorSpi() {
        super("Rainbow");
        this.f14624b = new RainbowKeyPairGenerator();
        this.f14625c = CryptoServicesRegistrar.getSecureRandom();
        this.f14626d = false;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        if (!this.f14626d) {
            RainbowKeyGenerationParameters rainbowKeyGenerationParameters = new RainbowKeyGenerationParameters(this.f14625c, new RainbowParameters(new RainbowParameterSpec().getVi()));
            this.f14623a = rainbowKeyGenerationParameters;
            this.f14624b.init(rainbowKeyGenerationParameters);
            this.f14626d = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.f14624b.generateKeyPair();
        return new KeyPair(new BCRainbowPublicKey((RainbowPublicKeyParameters) generateKeyPair.getPublic()), new BCRainbowPrivateKey((RainbowPrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i2, SecureRandom secureRandom) {
        this.f14625c = secureRandom;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        if (!(algorithmParameterSpec instanceof RainbowParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a RainbowParameterSpec");
        }
        RainbowKeyGenerationParameters rainbowKeyGenerationParameters = new RainbowKeyGenerationParameters(secureRandom, new RainbowParameters(((RainbowParameterSpec) algorithmParameterSpec).getVi()));
        this.f14623a = rainbowKeyGenerationParameters;
        this.f14624b.init(rainbowKeyGenerationParameters);
        this.f14626d = true;
    }
}
