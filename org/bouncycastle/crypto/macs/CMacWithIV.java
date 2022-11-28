package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes3.dex */
public class CMacWithIV extends CMac {
    public CMacWithIV(BlockCipher blockCipher) {
        super(blockCipher);
    }

    public CMacWithIV(BlockCipher blockCipher, int i2) {
        super(blockCipher, i2);
    }

    @Override // org.bouncycastle.crypto.macs.CMac
    void a(CipherParameters cipherParameters) {
    }
}
