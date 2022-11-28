package com.google.crypto.tink.subtle;

import java.util.Arrays;
/* loaded from: classes2.dex */
class XChaCha20 extends ChaCha20Base {
    /* JADX INFO: Access modifiers changed from: package-private */
    public XChaCha20(byte[] bArr, int i2) {
        super(bArr, i2);
    }

    static int[] j(int[] iArr, int[] iArr2) {
        ChaCha20Base.g(r0, iArr);
        int[] iArr3 = {0, 0, 0, 0, iArr3[12], iArr3[13], iArr3[14], iArr3[15], 0, 0, 0, 0, iArr2[0], iArr2[1], iArr2[2], iArr2[3]};
        ChaCha20Base.h(iArr3);
        return Arrays.copyOf(iArr3, 8);
    }

    @Override // com.google.crypto.tink.subtle.ChaCha20Base
    int[] b(int[] iArr, int i2) {
        if (iArr.length == e() / 4) {
            int[] iArr2 = new int[16];
            ChaCha20Base.g(iArr2, j(this.f9836a, iArr));
            iArr2[12] = i2;
            iArr2[13] = 0;
            iArr2[14] = iArr[4];
            iArr2[15] = iArr[5];
            return iArr2;
        }
        throw new IllegalArgumentException(String.format("XChaCha20 uses 192-bit nonces, but got a %d-bit nonce", Integer.valueOf(iArr.length * 32)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.subtle.ChaCha20Base
    public int e() {
        return 24;
    }
}
