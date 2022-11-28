package org.bouncycastle.crypto;
/* loaded from: classes3.dex */
public interface StreamCipher {
    String getAlgorithmName();

    void init(boolean z, CipherParameters cipherParameters);

    int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4);

    void reset();

    byte returnByte(byte b2);
}
