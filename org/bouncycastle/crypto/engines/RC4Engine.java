package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
/* loaded from: classes3.dex */
public class RC4Engine implements StreamCipher {
    private static final int STATE_LENGTH = 256;
    private byte[] engineState = null;
    private int x = 0;
    private int y = 0;
    private byte[] workingKey = null;

    private void setKey(byte[] bArr) {
        this.workingKey = bArr;
        this.x = 0;
        this.y = 0;
        if (this.engineState == null) {
            this.engineState = new byte[256];
        }
        for (int i2 = 0; i2 < 256; i2++) {
            this.engineState[i2] = (byte) i2;
        }
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < 256; i5++) {
            byte[] bArr2 = this.engineState;
            i4 = ((bArr[i3] & 255) + bArr2[i5] + i4) & 255;
            byte b2 = bArr2[i5];
            bArr2[i5] = bArr2[i4];
            bArr2[i4] = b2;
            i3 = (i3 + 1) % bArr.length;
        }
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "RC4";
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            byte[] key = ((KeyParameter) cipherParameters).getKey();
            this.workingKey = key;
            setKey(key);
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to RC4 init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (i2 + i3 <= bArr.length) {
            if (i4 + i3 <= bArr2.length) {
                for (int i5 = 0; i5 < i3; i5++) {
                    int i6 = (this.x + 1) & 255;
                    this.x = i6;
                    byte[] bArr3 = this.engineState;
                    int i7 = (bArr3[i6] + this.y) & 255;
                    this.y = i7;
                    byte b2 = bArr3[i6];
                    bArr3[i6] = bArr3[i7];
                    bArr3[i7] = b2;
                    bArr2[i5 + i4] = (byte) (bArr3[(bArr3[i6] + bArr3[i7]) & 255] ^ bArr[i5 + i2]);
                }
                return i3;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new DataLengthException("input buffer too short");
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void reset() {
        setKey(this.workingKey);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b2) {
        int i2 = (this.x + 1) & 255;
        this.x = i2;
        byte[] bArr = this.engineState;
        int i3 = (bArr[i2] + this.y) & 255;
        this.y = i3;
        byte b3 = bArr[i2];
        bArr[i2] = bArr[i3];
        bArr[i3] = b3;
        return (byte) (b2 ^ bArr[(bArr[i2] + bArr[i3]) & 255]);
    }
}
