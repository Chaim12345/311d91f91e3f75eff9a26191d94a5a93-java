package org.bouncycastle.crypto.engines;

import androidx.core.view.MotionEventCompat;
import com.google.common.base.Ascii;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
/* loaded from: classes3.dex */
public class Grain128Engine implements StreamCipher {
    private static final int STATE_SIZE = 4;
    private int index = 4;
    private boolean initialised = false;
    private int[] lfsr;
    private int[] nfsr;
    private byte[] out;
    private int output;
    private byte[] workingIV;
    private byte[] workingKey;

    private byte getKeyStream() {
        if (this.index > 3) {
            oneRound();
            this.index = 0;
        }
        byte[] bArr = this.out;
        int i2 = this.index;
        this.index = i2 + 1;
        return bArr[i2];
    }

    private int getOutput() {
        int[] iArr = this.nfsr;
        int i2 = (iArr[0] >>> 2) | (iArr[1] << 30);
        int i3 = (iArr[0] >>> 12) | (iArr[1] << 20);
        int i4 = (iArr[0] >>> 15) | (iArr[1] << 17);
        int i5 = (iArr[1] >>> 4) | (iArr[2] << 28);
        int i6 = (iArr[1] >>> 13) | (iArr[2] << 19);
        int i7 = iArr[2];
        int i8 = (iArr[2] >>> 9) | (iArr[3] << 23);
        int i9 = (iArr[2] >>> 25) | (iArr[3] << 7);
        int i10 = (iArr[3] << 1) | (iArr[2] >>> 31);
        int[] iArr2 = this.lfsr;
        int i11 = (iArr2[0] >>> 8) | (iArr2[1] << 24);
        int i12 = (iArr2[0] >>> 13) | (iArr2[1] << 19);
        int i13 = (iArr2[0] >>> 20) | (iArr2[1] << 12);
        int i14 = (iArr2[1] >>> 10) | (iArr2[2] << 22);
        int i15 = (iArr2[1] >>> 28) | (iArr2[2] << 4);
        int i16 = (iArr2[2] >>> 15) | (iArr2[3] << 17);
        int i17 = i10 & i3 & ((iArr2[2] >>> 31) | (iArr2[3] << 1));
        return ((((((((i17 ^ ((((i12 & i13) ^ (i3 & i11)) ^ (i10 & i14)) ^ (i15 & i16))) ^ ((iArr2[2] >>> 29) | (iArr2[3] << 3))) ^ i2) ^ i4) ^ i5) ^ i6) ^ i7) ^ i8) ^ i9;
    }

    private int getOutputLFSR() {
        int[] iArr = this.lfsr;
        int i2 = iArr[0];
        int i3 = (iArr[0] >>> 7) | (iArr[1] << 25);
        int i4 = (iArr[1] >>> 6) | (iArr[2] << 26);
        return iArr[3] ^ ((((i3 ^ i2) ^ i4) ^ ((iArr[2] >>> 6) | (iArr[3] << 26))) ^ ((iArr[2] >>> 17) | (iArr[3] << 15)));
    }

    private int getOutputNFSR() {
        int[] iArr = this.nfsr;
        int i2 = iArr[0];
        int i3 = (iArr[0] >>> 3) | (iArr[1] << 29);
        int i4 = (iArr[0] >>> 11) | (iArr[1] << 21);
        int i5 = (iArr[0] >>> 13) | (iArr[1] << 19);
        int i6 = (iArr[0] >>> 17) | (iArr[1] << 15);
        int i7 = (iArr[0] >>> 18) | (iArr[1] << 14);
        int i8 = (iArr[0] >>> 26) | (iArr[1] << 6);
        int i9 = (iArr[0] >>> 27) | (iArr[1] << 5);
        int i10 = (iArr[1] >>> 8) | (iArr[2] << 24);
        int i11 = (iArr[1] >>> 16) | (iArr[2] << 16);
        int i12 = (iArr[1] >>> 24) | (iArr[2] << 8);
        int i13 = (iArr[1] >>> 27) | (iArr[2] << 5);
        int i14 = (iArr[1] >>> 29) | (iArr[2] << 3);
        int i15 = (iArr[2] >>> 1) | (iArr[3] << 31);
        return (((((((iArr[3] ^ (((i2 ^ i8) ^ i12) ^ ((iArr[2] >>> 27) | (iArr[3] << 5)))) ^ (i3 & ((iArr[2] >>> 3) | (iArr[3] << 29)))) ^ (i4 & i5)) ^ (i6 & i7)) ^ (i9 & i13)) ^ (i10 & i11)) ^ (i14 & i15)) ^ (((iArr[2] >>> 4) | (iArr[3] << 28)) & ((iArr[2] >>> 20) | (iArr[3] << 12)));
    }

    private void initGrain() {
        for (int i2 = 0; i2 < 8; i2++) {
            this.output = getOutput();
            this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) ^ this.output);
            this.lfsr = shift(this.lfsr, getOutputLFSR() ^ this.output);
        }
        this.initialised = true;
    }

    private void oneRound() {
        int output = getOutput();
        this.output = output;
        byte[] bArr = this.out;
        bArr[0] = (byte) output;
        bArr[1] = (byte) (output >> 8);
        bArr[2] = (byte) (output >> 16);
        bArr[3] = (byte) (output >> 24);
        this.nfsr = shift(this.nfsr, getOutputNFSR() ^ this.lfsr[0]);
        this.lfsr = shift(this.lfsr, getOutputLFSR());
    }

    private void setKey(byte[] bArr, byte[] bArr2) {
        bArr2[12] = -1;
        bArr2[13] = -1;
        bArr2[14] = -1;
        bArr2[15] = -1;
        this.workingKey = bArr;
        this.workingIV = bArr2;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.nfsr;
            if (i2 >= iArr.length) {
                return;
            }
            byte[] bArr3 = this.workingKey;
            int i4 = i3 + 3;
            int i5 = i3 + 2;
            int i6 = i3 + 1;
            iArr[i2] = (bArr3[i3] & 255) | (bArr3[i4] << Ascii.CAN) | ((bArr3[i5] << 16) & 16711680) | ((bArr3[i6] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
            int[] iArr2 = this.lfsr;
            byte[] bArr4 = this.workingIV;
            iArr2[i2] = (bArr4[i3] & 255) | (bArr4[i4] << Ascii.CAN) | ((bArr4[i5] << 16) & 16711680) | ((bArr4[i6] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
            i3 += 4;
            i2++;
        }
    }

    private int[] shift(int[] iArr, int i2) {
        iArr[0] = iArr[1];
        iArr[1] = iArr[2];
        iArr[2] = iArr[3];
        iArr[3] = i2;
        return iArr;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "Grain-128";
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("Grain-128 Init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] iv = parametersWithIV.getIV();
        if (iv == null || iv.length != 12) {
            throw new IllegalArgumentException("Grain-128  requires exactly 12 bytes of IV");
        }
        if (!(parametersWithIV.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("Grain-128 Init parameters must include a key");
        }
        KeyParameter keyParameter = (KeyParameter) parametersWithIV.getParameters();
        this.workingIV = new byte[keyParameter.getKey().length];
        this.workingKey = new byte[keyParameter.getKey().length];
        this.lfsr = new int[4];
        this.nfsr = new int[4];
        this.out = new byte[4];
        System.arraycopy(iv, 0, this.workingIV, 0, iv.length);
        System.arraycopy(keyParameter.getKey(), 0, this.workingKey, 0, keyParameter.getKey().length);
        reset();
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (!this.initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (i2 + i3 <= bArr.length) {
            if (i4 + i3 <= bArr2.length) {
                for (int i5 = 0; i5 < i3; i5++) {
                    bArr2[i4 + i5] = (byte) (bArr[i2 + i5] ^ getKeyStream());
                }
                return i3;
            }
            throw new OutputLengthException("output buffer too short");
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void reset() {
        this.index = 4;
        setKey(this.workingKey, this.workingIV);
        initGrain();
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b2) {
        if (this.initialised) {
            return (byte) (b2 ^ getKeyStream());
        }
        throw new IllegalStateException(getAlgorithmName() + " not initialised");
    }
}
