package org.bouncycastle.crypto;
/* loaded from: classes3.dex */
public interface Wrapper {
    String getAlgorithmName();

    void init(boolean z, CipherParameters cipherParameters);

    byte[] unwrap(byte[] bArr, int i2, int i3);

    byte[] wrap(byte[] bArr, int i2, int i3);
}
