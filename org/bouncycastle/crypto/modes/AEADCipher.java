package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes3.dex */
public interface AEADCipher {
    int doFinal(byte[] bArr, int i2);

    String getAlgorithmName();

    byte[] getMac();

    int getOutputSize(int i2);

    int getUpdateOutputSize(int i2);

    void init(boolean z, CipherParameters cipherParameters);

    void processAADByte(byte b2);

    void processAADBytes(byte[] bArr, int i2, int i3);

    int processByte(byte b2, byte[] bArr, int i2);

    int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4);

    void reset();
}
