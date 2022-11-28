package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.math.ec.WNafUtil;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes3.dex */
public class ECKeyPairGenerator implements AsymmetricCipherKeyPairGenerator, ECConstants {

    /* renamed from: a  reason: collision with root package name */
    ECDomainParameters f13420a;

    /* renamed from: b  reason: collision with root package name */
    SecureRandom f13421b;

    protected ECMultiplier a() {
        return new FixedPointCombMultiplier();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        BigInteger n2 = this.f13420a.getN();
        int bitLength = n2.bitLength();
        int i2 = bitLength >>> 2;
        while (true) {
            BigInteger createRandomBigInteger = BigIntegers.createRandomBigInteger(bitLength, this.f13421b);
            if (createRandomBigInteger.compareTo(ECConstants.ONE) >= 0 && createRandomBigInteger.compareTo(n2) < 0 && WNafUtil.getNafWeight(createRandomBigInteger) >= i2) {
                return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new ECPublicKeyParameters(a().multiply(this.f13420a.getG(), createRandomBigInteger), this.f13420a), (AsymmetricKeyParameter) new ECPrivateKeyParameters(createRandomBigInteger, this.f13420a));
            }
        }
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        ECKeyGenerationParameters eCKeyGenerationParameters = (ECKeyGenerationParameters) keyGenerationParameters;
        this.f13421b = eCKeyGenerationParameters.getRandom();
        this.f13420a = eCKeyGenerationParameters.getDomainParameters();
    }
}
