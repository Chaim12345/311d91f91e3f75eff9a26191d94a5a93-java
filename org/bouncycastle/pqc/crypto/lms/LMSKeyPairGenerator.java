package org.bouncycastle.pqc.crypto.lms;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
/* loaded from: classes4.dex */
public class LMSKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {

    /* renamed from: a  reason: collision with root package name */
    LMSKeyGenerationParameters f14510a;

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        SecureRandom random = this.f14510a.getRandom();
        byte[] bArr = new byte[16];
        random.nextBytes(bArr);
        byte[] bArr2 = new byte[32];
        random.nextBytes(bArr2);
        LMSPrivateKeyParameters generateKeys = LMS.generateKeys(this.f14510a.getParameters().getLMSigParam(), this.f14510a.getParameters().getLMOTSParam(), 0, bArr, bArr2);
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) generateKeys.getPublicKey(), (AsymmetricKeyParameter) generateKeys);
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.f14510a = (LMSKeyGenerationParameters) keyGenerationParameters;
    }
}
