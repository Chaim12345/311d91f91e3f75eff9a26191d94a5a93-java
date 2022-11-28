package org.bouncycastle.crypto;
/* loaded from: classes3.dex */
public interface Digest {
    int doFinal(byte[] bArr, int i2);

    String getAlgorithmName();

    int getDigestSize();

    void reset();

    void update(byte b2);

    void update(byte[] bArr, int i2, int i3);
}
