package com.google.crypto.tink.subtle;
/* loaded from: classes2.dex */
class ChaCha20 extends ChaCha20Base {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ChaCha20(byte[] bArr, int i2) {
        super(bArr, i2);
    }

    @Override // com.google.crypto.tink.subtle.ChaCha20Base
    int[] b(int[] iArr, int i2) {
        if (iArr.length == e() / 4) {
            int[] iArr2 = new int[16];
            ChaCha20Base.g(iArr2, this.f9836a);
            iArr2[12] = i2;
            System.arraycopy(iArr, 0, iArr2, 13, iArr.length);
            return iArr2;
        }
        throw new IllegalArgumentException(String.format("ChaCha20 uses 96-bit nonces, but got a %d-bit nonce", Integer.valueOf(iArr.length * 32)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.crypto.tink.subtle.ChaCha20Base
    public int e() {
        return 12;
    }
}
