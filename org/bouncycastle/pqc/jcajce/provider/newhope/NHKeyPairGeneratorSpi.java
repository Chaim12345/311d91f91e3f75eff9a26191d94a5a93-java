package org.bouncycastle.pqc.jcajce.provider.newhope;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.pqc.crypto.newhope.NHKeyPairGenerator;
import org.bouncycastle.pqc.crypto.newhope.NHPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.newhope.NHPublicKeyParameters;
/* loaded from: classes4.dex */
public class NHKeyPairGeneratorSpi extends KeyPairGenerator {

    /* renamed from: a  reason: collision with root package name */
    NHKeyPairGenerator f14620a;

    /* renamed from: b  reason: collision with root package name */
    SecureRandom f14621b;

    /* renamed from: c  reason: collision with root package name */
    boolean f14622c;

    public NHKeyPairGeneratorSpi() {
        super("NH");
        this.f14620a = new NHKeyPairGenerator();
        this.f14621b = CryptoServicesRegistrar.getSecureRandom();
        this.f14622c = false;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        if (!this.f14622c) {
            this.f14620a.init(new KeyGenerationParameters(this.f14621b, 1024));
            this.f14622c = true;
        }
        AsymmetricCipherKeyPair generateKeyPair = this.f14620a.generateKeyPair();
        return new KeyPair(new BCNHPublicKey((NHPublicKeyParameters) generateKeyPair.getPublic()), new BCNHPrivateKey((NHPrivateKeyParameters) generateKeyPair.getPrivate()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i2, SecureRandom secureRandom) {
        if (i2 != 1024) {
            throw new IllegalArgumentException("strength must be 1024 bits");
        }
        this.f14620a.init(new KeyGenerationParameters(secureRandom, 1024));
        this.f14622c = true;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        throw new InvalidAlgorithmParameterException("parameter object not recognised");
    }
}
