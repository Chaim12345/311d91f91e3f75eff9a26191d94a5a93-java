package org.bouncycastle.crypto.engines;
/* loaded from: classes3.dex */
public class VMPCKSA3Engine extends VMPCEngine {
    @Override // org.bouncycastle.crypto.engines.VMPCEngine
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
        for (int i7 = 0; i7 < 768; i7++) {
            byte[] bArr5 = this.f13403b;
            int i8 = i7 & 255;
            byte b6 = bArr5[(this.f13404c + bArr5[i8] + bArr[i7 % bArr.length]) & 255];
            this.f13404c = b6;
            byte b7 = bArr5[i8];
            bArr5[i8] = bArr5[b6 & 255];
            bArr5[b6 & 255] = b7;
        }
        this.f13402a = (byte) 0;
    }

    @Override // org.bouncycastle.crypto.engines.VMPCEngine, org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "VMPC-KSA3";
    }
}
