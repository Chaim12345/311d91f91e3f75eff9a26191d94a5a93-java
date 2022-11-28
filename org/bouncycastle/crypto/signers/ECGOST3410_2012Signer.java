package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DSAExt;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes3.dex */
public class ECGOST3410_2012Signer implements DSAExt {

    /* renamed from: a  reason: collision with root package name */
    ECKeyParameters f13514a;

    /* renamed from: b  reason: collision with root package name */
    SecureRandom f13515b;

    protected ECMultiplier a() {
        return new FixedPointCombMultiplier();
    }

    @Override // org.bouncycastle.crypto.DSA
    public BigInteger[] generateSignature(byte[] bArr) {
        BigInteger bigInteger = new BigInteger(1, Arrays.reverse(bArr));
        ECDomainParameters parameters = this.f13514a.getParameters();
        BigInteger n2 = parameters.getN();
        BigInteger d2 = ((ECPrivateKeyParameters) this.f13514a).getD();
        ECMultiplier a2 = a();
        while (true) {
            BigInteger createRandomBigInteger = BigIntegers.createRandomBigInteger(n2.bitLength(), this.f13515b);
            BigInteger bigInteger2 = ECConstants.ZERO;
            if (!createRandomBigInteger.equals(bigInteger2)) {
                BigInteger mod = a2.multiply(parameters.getG(), createRandomBigInteger).normalize().getAffineXCoord().toBigInteger().mod(n2);
                if (mod.equals(bigInteger2)) {
                    continue;
                } else {
                    BigInteger mod2 = createRandomBigInteger.multiply(bigInteger).add(d2.multiply(mod)).mod(n2);
                    if (!mod2.equals(bigInteger2)) {
                        return new BigInteger[]{mod, mod2};
                    }
                }
            }
        }
    }

    @Override // org.bouncycastle.crypto.DSAExt
    public BigInteger getOrder() {
        return this.f13514a.getParameters().getN();
    }

    @Override // org.bouncycastle.crypto.DSA
    public void init(boolean z, CipherParameters cipherParameters) {
        ECKeyParameters eCKeyParameters;
        if (!z) {
            eCKeyParameters = (ECPublicKeyParameters) cipherParameters;
        } else if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.f13515b = parametersWithRandom.getRandom();
            this.f13514a = (ECPrivateKeyParameters) parametersWithRandom.getParameters();
            return;
        } else {
            this.f13515b = CryptoServicesRegistrar.getSecureRandom();
            eCKeyParameters = (ECPrivateKeyParameters) cipherParameters;
        }
        this.f13514a = eCKeyParameters;
    }

    @Override // org.bouncycastle.crypto.DSA
    public boolean verifySignature(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigInteger3 = new BigInteger(1, Arrays.reverse(bArr));
        BigInteger n2 = this.f13514a.getParameters().getN();
        BigInteger bigInteger4 = ECConstants.ONE;
        if (bigInteger.compareTo(bigInteger4) < 0 || bigInteger.compareTo(n2) >= 0 || bigInteger2.compareTo(bigInteger4) < 0 || bigInteger2.compareTo(n2) >= 0) {
            return false;
        }
        BigInteger modOddInverseVar = BigIntegers.modOddInverseVar(n2, bigInteger3);
        ECPoint normalize = ECAlgorithms.sumOfTwoMultiplies(this.f13514a.getParameters().getG(), bigInteger2.multiply(modOddInverseVar).mod(n2), ((ECPublicKeyParameters) this.f13514a).getQ(), n2.subtract(bigInteger).multiply(modOddInverseVar).mod(n2)).normalize();
        if (normalize.isInfinity()) {
            return false;
        }
        return normalize.getAffineXCoord().toBigInteger().mod(n2).equals(bigInteger);
    }
}
