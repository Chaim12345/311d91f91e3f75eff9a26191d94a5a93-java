package org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.KeyGeneratorSpi;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;
/* loaded from: classes3.dex */
public class BaseKeyGenerator extends KeyGeneratorSpi {

    /* renamed from: a  reason: collision with root package name */
    protected String f13781a;

    /* renamed from: b  reason: collision with root package name */
    protected int f13782b;

    /* renamed from: c  reason: collision with root package name */
    protected CipherKeyGenerator f13783c;

    /* renamed from: d  reason: collision with root package name */
    protected boolean f13784d = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseKeyGenerator(String str, int i2, CipherKeyGenerator cipherKeyGenerator) {
        this.f13781a = str;
        this.f13782b = i2;
        this.f13783c = cipherKeyGenerator;
    }

    @Override // javax.crypto.KeyGeneratorSpi
    protected SecretKey engineGenerateKey() {
        if (this.f13784d) {
            this.f13783c.init(new KeyGenerationParameters(CryptoServicesRegistrar.getSecureRandom(), this.f13782b));
            this.f13784d = false;
        }
        return new SecretKeySpec(this.f13783c.generateKey(), this.f13781a);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.crypto.KeyGeneratorSpi
    public void engineInit(int i2, SecureRandom secureRandom) {
        if (secureRandom == null) {
            try {
                secureRandom = CryptoServicesRegistrar.getSecureRandom();
            } catch (IllegalArgumentException e2) {
                throw new InvalidParameterException(e2.getMessage());
            }
        }
        this.f13783c.init(new KeyGenerationParameters(secureRandom, i2));
        this.f13784d = false;
    }

    @Override // javax.crypto.KeyGeneratorSpi
    protected void engineInit(SecureRandom secureRandom) {
        if (secureRandom != null) {
            this.f13783c.init(new KeyGenerationParameters(secureRandom, this.f13782b));
            this.f13784d = false;
        }
    }

    @Override // javax.crypto.KeyGeneratorSpi
    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        throw new InvalidAlgorithmParameterException("Not Implemented");
    }
}
