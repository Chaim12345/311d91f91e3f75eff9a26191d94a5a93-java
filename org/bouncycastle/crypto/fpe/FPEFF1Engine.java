package org.bouncycastle.crypto.fpe;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.params.FPEParameters;
import org.bouncycastle.util.Properties;
/* loaded from: classes3.dex */
public class FPEFF1Engine extends FPEEngine {
    public FPEFF1Engine() {
        this(new AESEngine());
    }

    public FPEFF1Engine(BlockCipher blockCipher) {
        super(blockCipher);
        if (blockCipher.getBlockSize() != 16) {
            throw new IllegalArgumentException("base cipher needs to be 128 bits");
        }
        if (Properties.isOverrideSet("org.bouncycastle.fpe.disable") || Properties.isOverrideSet("org.bouncycastle.fpe.disable_ff1")) {
            throw new UnsupportedOperationException("FF1 encryption disabled");
        }
    }

    @Override // org.bouncycastle.crypto.fpe.FPEEngine
    protected int a(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        System.arraycopy(this.f13409c.getRadix() > 256 ? FPEEngine.c(SP80038G.m(this.f13407a, this.f13409c.getRadix(), this.f13409c.getTweak(), FPEEngine.d(bArr), i2, i3 / 2)) : SP80038G.l(this.f13407a, this.f13409c.getRadix(), this.f13409c.getTweak(), bArr, i2, i3), 0, bArr2, i4, i3);
        return i3;
    }

    @Override // org.bouncycastle.crypto.fpe.FPEEngine
    protected int b(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        System.arraycopy(this.f13409c.getRadix() > 256 ? FPEEngine.c(SP80038G.q(this.f13407a, this.f13409c.getRadix(), this.f13409c.getTweak(), FPEEngine.d(bArr), i2, i3 / 2)) : SP80038G.p(this.f13407a, this.f13409c.getRadix(), this.f13409c.getTweak(), bArr, i2, i3), 0, bArr2, i4, i3);
        return i3;
    }

    @Override // org.bouncycastle.crypto.fpe.FPEEngine
    public String getAlgorithmName() {
        return "FF1";
    }

    @Override // org.bouncycastle.crypto.fpe.FPEEngine
    public void init(boolean z, CipherParameters cipherParameters) {
        this.f13408b = z;
        FPEParameters fPEParameters = (FPEParameters) cipherParameters;
        this.f13409c = fPEParameters;
        this.f13407a.init(!fPEParameters.isUsingInverseFunction(), this.f13409c.getKey());
    }
}
