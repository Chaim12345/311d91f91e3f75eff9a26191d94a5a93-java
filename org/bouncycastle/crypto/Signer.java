package org.bouncycastle.crypto;
/* loaded from: classes3.dex */
public interface Signer {
    byte[] generateSignature();

    void init(boolean z, CipherParameters cipherParameters);

    void reset();

    void update(byte b2);

    void update(byte[] bArr, int i2, int i3);

    boolean verifySignature(byte[] bArr);
}
