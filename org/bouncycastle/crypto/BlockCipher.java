package org.bouncycastle.crypto;
/* loaded from: classes3.dex */
public interface BlockCipher {
    String getAlgorithmName();

    int getBlockSize();

    void init(boolean z, CipherParameters cipherParameters);

    int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3);

    void reset();
}
