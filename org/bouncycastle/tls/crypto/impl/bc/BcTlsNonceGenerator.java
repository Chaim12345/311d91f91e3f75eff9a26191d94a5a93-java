package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.crypto.prng.RandomGenerator;
import org.bouncycastle.tls.crypto.TlsNonceGenerator;
/* loaded from: classes4.dex */
final class BcTlsNonceGenerator implements TlsNonceGenerator {
    private final RandomGenerator randomGenerator;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BcTlsNonceGenerator(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    @Override // org.bouncycastle.tls.crypto.TlsNonceGenerator
    public byte[] generateNonce(int i2) {
        byte[] bArr = new byte[i2];
        this.randomGenerator.nextBytes(bArr);
        return bArr;
    }
}
