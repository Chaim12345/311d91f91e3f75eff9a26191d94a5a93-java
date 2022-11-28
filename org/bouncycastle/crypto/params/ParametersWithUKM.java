package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes3.dex */
public class ParametersWithUKM implements CipherParameters {
    private CipherParameters parameters;
    private byte[] ukm;

    public ParametersWithUKM(CipherParameters cipherParameters, byte[] bArr) {
        this(cipherParameters, bArr, 0, bArr.length);
    }

    public ParametersWithUKM(CipherParameters cipherParameters, byte[] bArr, int i2, int i3) {
        byte[] bArr2 = new byte[i3];
        this.ukm = bArr2;
        this.parameters = cipherParameters;
        System.arraycopy(bArr, i2, bArr2, 0, i3);
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }

    public byte[] getUKM() {
        return this.ukm;
    }
}
