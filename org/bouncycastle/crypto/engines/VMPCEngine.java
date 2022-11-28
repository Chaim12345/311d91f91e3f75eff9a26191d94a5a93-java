package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
/* loaded from: classes3.dex */
public class VMPCEngine implements StreamCipher {

    /* renamed from: a  reason: collision with root package name */
    protected byte f13402a = 0;

    /* renamed from: b  reason: collision with root package name */
    protected byte[] f13403b = null;

    /* renamed from: c  reason: collision with root package name */
    protected byte f13404c = 0;

    /* renamed from: d  reason: collision with root package name */
    protected byte[] f13405d;

    /* renamed from: e  reason: collision with root package name */
    protected byte[] f13406e;

    protected void a(byte[] bArr, byte[] bArr2) {
        this.f13404c = (byte) 0;
        this.f13403b = new byte[256];
        for (int i2 = 0; i2 < 256; i2++) {
            this.f13403b[i2] = (byte) i2;
        }
        for (int i3 = 0; i3 < 768; i3++) {
            byte[] bArr3 = this.f13403b;
            int i4 = i3 & 255;
            byte b2 = bArr3[(this.f13404c + bArr3[i4] + bArr[i3 % bArr.length]) & 255];
            this.f13404c = b2;
            byte b3 = bArr3[i4];
            bArr3[i4] = bArr3[b2 & 255];
            bArr3[b2 & 255] = b3;
        }
        for (int i5 = 0; i5 < 768; i5++) {
            byte[] bArr4 = this.f13403b;
            int i6 = i5 & 255;
            byte b4 = bArr4[(this.f13404c + bArr4[i6] + bArr2[i5 % bArr2.length]) & 255];
            this.f13404c = b4;
            byte b5 = bArr4[i6];
            bArr4[i6] = bArr4[b4 & 255];
            bArr4[b4 & 255] = b5;
        }
        this.f13402a = (byte) 0;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "VMPC";
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("VMPC init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        if (!(parametersWithIV.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("VMPC init parameters must include a key");
        }
        KeyParameter keyParameter = (KeyParameter) parametersWithIV.getParameters();
        byte[] iv = parametersWithIV.getIV();
        this.f13405d = iv;
        if (iv == null || iv.length < 1 || iv.length > 768) {
            throw new IllegalArgumentException("VMPC requires 1 to 768 bytes of IV");
        }
        byte[] key = keyParameter.getKey();
        this.f13406e = key;
        a(key, this.f13405d);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (i2 + i3 <= bArr.length) {
            if (i4 + i3 <= bArr2.length) {
                for (int i5 = 0; i5 < i3; i5++) {
                    byte[] bArr3 = this.f13403b;
                    byte b2 = this.f13404c;
                    byte b3 = this.f13402a;
                    byte b4 = bArr3[(b2 + bArr3[b3 & 255]) & 255];
                    this.f13404c = b4;
                    byte b5 = bArr3[(bArr3[bArr3[b4 & 255] & 255] + 1) & 255];
                    byte b6 = bArr3[b3 & 255];
                    bArr3[b3 & 255] = bArr3[b4 & 255];
                    bArr3[b4 & 255] = b6;
                    this.f13402a = (byte) ((b3 + 1) & 255);
                    bArr2[i5 + i4] = (byte) (bArr[i5 + i2] ^ b5);
                }
                return i3;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new DataLengthException("input buffer too short");
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void reset() {
        a(this.f13406e, this.f13405d);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b2) {
        byte[] bArr = this.f13403b;
        byte b3 = this.f13404c;
        byte b4 = this.f13402a;
        byte b5 = bArr[(b3 + bArr[b4 & 255]) & 255];
        this.f13404c = b5;
        byte b6 = bArr[(bArr[bArr[b5 & 255] & 255] + 1) & 255];
        byte b7 = bArr[b4 & 255];
        bArr[b4 & 255] = bArr[b5 & 255];
        bArr[b5 & 255] = b7;
        this.f13402a = (byte) ((b4 + 1) & 255);
        return (byte) (b2 ^ b6);
    }
}
