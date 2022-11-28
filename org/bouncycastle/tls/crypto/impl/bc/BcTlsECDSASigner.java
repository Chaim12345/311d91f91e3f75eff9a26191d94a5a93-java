package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
/* loaded from: classes4.dex */
public class BcTlsECDSASigner extends BcTlsDSSSigner {
    public BcTlsECDSASigner(BcTlsCrypto bcTlsCrypto, ECPrivateKeyParameters eCPrivateKeyParameters) {
        super(bcTlsCrypto, eCPrivateKeyParameters);
    }

    @Override // org.bouncycastle.tls.crypto.impl.bc.BcTlsDSSSigner
    protected DSA a(int i2) {
        return new ECDSASigner(new HMacDSAKCalculator(this.f14984a.createDigest(i2)));
    }

    @Override // org.bouncycastle.tls.crypto.impl.bc.BcTlsDSSSigner
    protected short b() {
        return (short) 3;
    }
}
