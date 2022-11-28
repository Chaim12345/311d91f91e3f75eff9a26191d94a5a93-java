package org.bouncycastle.crypto.fpe;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.params.FPEParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Properties;
/* loaded from: classes3.dex */
public class FPEFF3_1Engine extends FPEEngine {
    public FPEFF3_1Engine() {
        this(new AESEngine());
    }

    public FPEFF3_1Engine(BlockCipher blockCipher) {
        super(blockCipher);
        if (blockCipher.getBlockSize() != 16) {
            throw new IllegalArgumentException("base cipher needs to be 128 bits");
        }
        if (Properties.isOverrideSet("org.bouncycastle.fpe.disable")) {
            throw new UnsupportedOperationException("FPE disabled");
        }
    }

    @Override // org.bouncycastle.crypto.fpe.FPEEngine
    protected int a(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        System.arraycopy(this.f13409c.getRadix() > 256 ? FPEEngine.c(SP80038G.o(this.f13407a, this.f13409c.getRadix(), this.f13409c.getTweak(), FPEEngine.d(bArr), i2, i3 / 2)) : SP80038G.n(this.f13407a, this.f13409c.getRadix(), this.f13409c.getTweak(), bArr, i2, i3), 0, bArr2, i4, i3);
        return i3;
    }

    @Override // org.bouncycastle.crypto.fpe.FPEEngine
    protected int b(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        System.arraycopy(this.f13409c.getRadix() > 256 ? FPEEngine.c(SP80038G.t(this.f13407a, this.f13409c.getRadix(), this.f13409c.getTweak(), FPEEngine.d(bArr), i2, i3 / 2)) : SP80038G.s(this.f13407a, this.f13409c.getRadix(), this.f13409c.getTweak(), bArr, i2, i3), 0, bArr2, i4, i3);
        return i3;
    }

    @Override // org.bouncycastle.crypto.fpe.FPEEngine
    public String getAlgorithmName() {
        return "FF3-1";
    }

    @Override // org.bouncycastle.crypto.fpe.FPEEngine
    public void init(boolean z, CipherParameters cipherParameters) {
        this.f13408b = z;
        FPEParameters fPEParameters = (FPEParameters) cipherParameters;
        this.f13409c = fPEParameters;
        this.f13407a.init(!fPEParameters.isUsingInverseFunction(), new KeyParameter(Arrays.reverse(this.f13409c.getKey().getKey())));
        if (this.f13409c.getTweak().length != 7) {
            throw new IllegalArgumentException("tweak should be 56 bits");
        }
    }
}
